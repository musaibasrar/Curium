package org.ideoholic.curium.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Derives the selection sequence of fee categories from their names so that month-wise
 * (e.g. "April Tuition Fee", "May Tuition Fee") and ordinal (e.g. "1st Exam Fee", "2nd Exam Fee")
 * fee categories are collected in order. Categories whose names carry no month/ordinal are not sequenced.
 */
public final class FeeSequenceUtil {

	private static final int ACADEMIC_YEAR_START_MONTH = 3;

	private static final String MONTH_GROUP_PREFIX = "month:";
	private static final String ORDINAL_GROUP_PREFIX = "ordinal:";

	private static final Pattern MONTH_PATTERN = Pattern.compile(
			"\\b(jan(?:uary)?|feb(?:ruary)?|mar(?:ch)?|apr(?:il)?|may|jun(?:e)?|jul(?:y)?|aug(?:ust)?|sep(?:t(?:ember)?)?|oct(?:ober)?|nov(?:ember)?|dec(?:ember)?)\\b",
			Pattern.CASE_INSENSITIVE);

	private static final Pattern YEAR_PATTERN = Pattern.compile("\\b(19|20)\\d{2}\\b");

	private static final Pattern ORDINAL_DIGIT_PATTERN = Pattern.compile("\\b(\\d{1,2})\\s*(st|nd|rd|th)\\b",
			Pattern.CASE_INSENSITIVE);

	private static final Pattern ORDINAL_WORD_PATTERN = Pattern.compile(
			"\\b(first|second|third|fourth|fifth|sixth|seventh|eighth|ninth|tenth|eleventh|twelfth)\\b",
			Pattern.CASE_INSENSITIVE);

	private static final List<String> MONTHS = java.util.Arrays.asList("jan", "feb", "mar", "apr", "may", "jun",
			"jul", "aug", "sep", "oct", "nov", "dec");

	private static final List<String> ORDINAL_WORDS = java.util.Arrays.asList("first", "second", "third", "fourth",
			"fifth", "sixth", "seventh", "eighth", "ninth", "tenth", "eleventh", "twelfth");

	private FeeSequenceUtil() {
	}

	/**
	 * @return the sequence group of the fee category (categories in the same group must be selected in order),
	 *         or empty string if the category is not sequenced.
	 */
	public static String getSequenceGroup(String feesCategoryName) {
		String[] parsed = parse(feesCategoryName);
		return parsed == null ? "" : parsed[0];
	}

	/**
	 * Computes the position of every fee category inside its sequence group.
	 * Month-wise groups start from the month of the first inserted fee category of that group and follow
	 * cyclically (Dec -> Jan); ordinal groups (exam fees) follow their ordinal number. Not sequenced -> -1.
	 *
	 * @param feesCategories key -> fees category name, iterated in insertion order (first inserted first)
	 * @return key -> sequence order
	 */
	public static Map<Integer, Integer> getSequenceOrders(Map<Integer, String> feesCategories) {
		// starting month of every month-wise group = month of its first inserted fee category
		Map<String, Integer> startMonths = new HashMap<String, Integer>();
		for (String feesCategoryName : feesCategories.values()) {
			String[] parsed = parse(feesCategoryName);
			if (parsed != null && parsed[0].startsWith(MONTH_GROUP_PREFIX) && !startMonths.containsKey(parsed[0])) {
				startMonths.put(parsed[0], Integer.parseInt(parsed[1]));
			}
		}

		Map<Integer, Integer> sequenceOrders = new HashMap<Integer, Integer>();
		for (Map.Entry<Integer, String> entry : feesCategories.entrySet()) {
			String[] parsed = parse(entry.getValue());
			int order = -1;
			if (parsed != null) {
				order = Integer.parseInt(parsed[1]);
				if (parsed[0].startsWith(MONTH_GROUP_PREFIX)) {
					order = (order - startMonths.get(parsed[0]) + 12) % 12;
				}
			}
			sequenceOrders.put(entry.getKey(), order);
		}
		return sequenceOrders;
	}

	/**
	 * Validates that, inside every sequence group, no fee category is selected while an earlier outstanding one
	 * of the same group is left unselected.
	 *
	 * @param outstandingFees outstanding (due amount > 0) fee categories : sfsid -> fees category name
	 * @param sequenceOrders  sfsid -> sequence order (see {@link #getSequenceOrders(Map)})
	 * @param selectedSfsIds  selected sfsids
	 * @return error message if the sequence is violated, otherwise null
	 */
	public static String validateSequentialSelection(Map<Integer, String> outstandingFees,
			Map<Integer, Integer> sequenceOrders, Set<Integer> selectedSfsIds) {
		Map<String, List<Integer>> groups = new LinkedHashMap<String, List<Integer>>();
		for (Map.Entry<Integer, String> entry : outstandingFees.entrySet()) {
			String group = getSequenceGroup(entry.getValue());
			if (!group.isEmpty()) {
				if (!groups.containsKey(group)) {
					groups.put(group, new ArrayList<Integer>());
				}
				groups.get(group).add(entry.getKey());
			}
		}

		for (List<Integer> sfsIds : groups.values()) {
			for (Integer selected : sfsIds) {
				if (!selectedSfsIds.contains(selected)) {
					continue;
				}
				int selectedOrder = sequenceOrders.get(selected);
				for (Integer previous : sfsIds) {
					if (sequenceOrders.get(previous) < selectedOrder
							&& !selectedSfsIds.contains(previous)) {
						return "Please select " + outstandingFees.get(previous) + " before "
								+ outstandingFees.get(selected);
					}
				}
			}
		}
		return null;
	}

	/** @return {group, month index (0-11) or ordinal number} or null if the fee category is not sequenced */
	private static String[] parse(String feesCategoryName) {
		if (feesCategoryName == null || feesCategoryName.trim().isEmpty()) {
			return null;
		}
		String name = feesCategoryName.toLowerCase(Locale.ENGLISH);

		Matcher monthMatcher = MONTH_PATTERN.matcher(name);
		if (monthMatcher.find()) {
			int month = MONTHS.indexOf(monthMatcher.group(1).substring(0, 3));
			int order = (month - ACADEMIC_YEAR_START_MONTH + 12) % 12;
			String rest = name.substring(0, monthMatcher.start()) + " " + name.substring(monthMatcher.end());
			rest = YEAR_PATTERN.matcher(rest).replaceAll(" ");
			return new String[] { MONTH_GROUP_PREFIX + normalize(rest), String.valueOf(order) };
		}

		Matcher ordinalMatcher = ORDINAL_DIGIT_PATTERN.matcher(name);
		if (ordinalMatcher.find()) {
			String rest = name.substring(0, ordinalMatcher.start()) + " " + name.substring(ordinalMatcher.end());
			return new String[] { ORDINAL_GROUP_PREFIX + normalize(rest), ordinalMatcher.group(1).replaceFirst("^0+(?=\\d)", "") };
		}

		Matcher ordinalWordMatcher = ORDINAL_WORD_PATTERN.matcher(name);
		if (ordinalWordMatcher.find()) {
			int order = ORDINAL_WORDS.indexOf(ordinalWordMatcher.group(1)) + 1;
			String rest = name.substring(0, ordinalWordMatcher.start()) + " " + name.substring(ordinalWordMatcher.end());
			return new String[] { ORDINAL_GROUP_PREFIX + normalize(rest), String.valueOf(order) };
		}
		return null;
	}

	private static String normalize(String value) {
		return value.replaceAll("[^a-z0-9]+", " ").trim().replaceAll("\\s+", " ");
	}
}

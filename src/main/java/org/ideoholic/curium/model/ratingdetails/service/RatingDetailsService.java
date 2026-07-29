package org.ideoholic.curium.model.ratingdetails.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.assessmentdetails.dao.HolisticAssessmentDAO;
import org.ideoholic.curium.model.assessmentdetails.dto.HolisticAssessment;
import org.ideoholic.curium.model.assessmentsubjectdetails.dao.AssessmentSubjectDetailsDAO;
import org.ideoholic.curium.model.assessmentsubjectdetails.dto.AssessmentSubject;
import org.ideoholic.curium.model.assessmentsubjectdetails.dto.AssessmentSubjectMaster;
import org.ideoholic.curium.model.documents.dto.SearchStudentResponseDto;
import org.ideoholic.curium.model.marksdetails.dto.SubjectGrade;
import org.ideoholic.curium.model.parents.dto.Parents;
import org.ideoholic.curium.model.ratingdetails.dao.RatingDetailsDAO;
import org.ideoholic.curium.model.ratingdetails.dto.AssessmentSubjectsDto;
import org.ideoholic.curium.model.ratingdetails.dto.CategorySummaryDto;
import org.ideoholic.curium.model.ratingdetails.dto.GenerateAssessmentReportDto;
import org.ideoholic.curium.model.ratingdetails.dto.GenerateAssessmentReportResponseDto;
import org.ideoholic.curium.model.ratingdetails.dto.AssessmentRank;
import org.ideoholic.curium.model.ratingdetails.dto.HolisticRating;
import org.ideoholic.curium.model.ratingdetails.dto.OverallSummaryDto;
import org.ideoholic.curium.model.ratingdetails.dto.RatingDto;
import org.ideoholic.curium.model.ratingdetails.dto.RatingUpdateDto;
import org.ideoholic.curium.model.ratingdetails.dto.RatingViewDto;
import org.ideoholic.curium.model.ratingdetails.dto.SearchStudentAssessmentDto;
import org.ideoholic.curium.model.ratingdetails.dto.StudentReportCardDto;
import org.ideoholic.curium.model.ratingdetails.dto.StudentRatingGraphDto;
import org.ideoholic.curium.model.ratingdetails.dto.StudentRatingGraphResponseDto;
import org.ideoholic.curium.model.ratingdetails.dto.SubjectRatingDto;
import org.ideoholic.curium.model.student.dao.studentDetailsDAO;
import org.ideoholic.curium.model.student.dto.Student;
import org.ideoholic.curium.util.DataUtil;
import org.ideoholic.curium.util.AssessmentSheet;
import org.ideoholic.curium.util.AssessmentsDetails;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * Service for Holistic Development Assessment Ratings
 * Duplicated from MarksDetailsService with grade-based rating support
 * Key Feature: Converts grades (A+, A, B+, etc.) to numeric values for calculations
 */
@Slf4j
@Service
public class RatingDetailsService {

	/**
	 * Grade to Numeric Value Conversion Map
	 * Maps grade codes to numeric values for calculations and rankings
	 */
	private Map<String, Float> getGradeToNumericMap() {
		Map<String, Float> gradeMap = new HashMap<>();
		gradeMap.put("A+", 95.0f);
		gradeMap.put("A", 90.0f);
		gradeMap.put("B+", 85.0f);
		gradeMap.put("B", 80.0f);
		gradeMap.put("C+", 75.0f);
		gradeMap.put("C", 70.0f);
		gradeMap.put("D", 65.0f);
		gradeMap.put("F", 50.0f);
		gradeMap.put("AB", 0.0f); // Absent
		return gradeMap;
	}

	/**
	 * Convert grade code to numeric value
	 * @param gradeCode Grade code (A+, A, B+, etc.)
	 * @return Numeric value for calculations
	 */
	private float convertGradeToNumeric(String gradeCode) {
		Map<String, Float> gradeMap = getGradeToNumericMap();
		return gradeMap.getOrDefault(gradeCode.toUpperCase().trim(), 0.0f);
	}

	/**
	 * Add ratings with grade-to-numeric conversion
	 * Stores both grade (for display) and numeric value (for calculations)
	 */
	public ResultResponse addRatings(RatingUpdateDto dto, String branchId, String currentAcademicYear, String userId) {

		ResultResponse result = ResultResponse.builder().build();

		String[] studentIds = dto.getStudentIds();
		String[] studentsRatings = dto.getStudentsRatings(); // These are grade codes: A+, B+, etc.
		String[] assessmentidName = dto.getAssessment().split("__");
		String subject = dto.getSubject();
		String classSelected = dto.getClassSearch();
		
		log.debug("Subject id: {}, Assessment id: {}", subject, assessmentidName[0]);
		
		int sizeOfArray = 0;
		Map<Integer, String> mapOfRatings = new HashMap<>();
		List<Integer> ids = new ArrayList<>();
		List<String> studentsRatingsList = new ArrayList<>();

		if (studentsRatings != null) {
			for (String ratingGrade : studentsRatings) {
				// Handle absent students
				if(!ratingGrade.equalsIgnoreCase("AB")) {
					studentsRatingsList.add(ratingGrade);
				} else {
					studentsRatingsList.add("AB"); // Absent marker
				}
			}
		}

		if (studentIds != null && subject != null) {

			for (String id : studentIds) {
				log.debug("Student id: {}", id);
				ids.add(Integer.valueOf(id));
			}

			sizeOfArray = ids.size();
			log.debug("Total students: {}", studentIds.length);

			for (int i = 0; i < sizeOfArray; i++) {
				mapOfRatings.put(ids.get(i), studentsRatingsList.get(i));
			}

			Set mapSet = mapOfRatings.entrySet();
			Iterator mapIterator = mapSet.iterator();

			int assessmentid = Integer.parseInt(assessmentidName[0]);
			int assessmentsubjectid = Integer.parseInt(subject);
			List<HolisticRating> ratingList = new ArrayList<>();

			while (mapIterator.hasNext()) {
				Map.Entry mapEntry = (Entry) mapIterator.next();

				HolisticRating rating = new HolisticRating();
				rating.setAssessmentid(assessmentid);
				rating.setAssessmentsubjectid(assessmentsubjectid);
				
				String gradeCode = (String) mapEntry.getValue();
				
				// CRITICAL: Store both grade and numeric value
				rating.setRatinggrade(gradeCode); // Store grade for display (A+, B+, etc.)
				rating.setRatingvalue(convertGradeToNumeric(gradeCode)); // Store numeric for calculations
				
				rating.setSid((int) mapEntry.getKey());
				rating.setAcademicyear(currentAcademicYear);
				rating.setBranchid(Integer.parseInt(branchId));
				rating.setUserid(Integer.parseInt(userId));
				rating.setAssessmentsubsubjectid(0);
				ratingList.add(rating);
			}

			String output = new RatingDetailsDAO().addRatings(ratingList);
			
			if(output.equals("success")){
				result.setMessage("true");
				result.setSuccess(true);
			} else if (output.contains("Duplicate")){
				result.setMessage("Duplicate");
				result.setSuccess(false);
			}
		}

		return result;
	}

	/**
	 * Search for students to enter ratings
	 */
	public SearchStudentResponseDto Search(SearchStudentAssessmentDto dto, String branchId) {
		SearchStudentResponseDto result = SearchStudentResponseDto.builder().build();

		if(branchId != null){
			
			String queryMain = "From Parents as parents where";
			String studentname = DataUtil.emptyString(dto.getStudentName());

			String addClass = dto.getAddClass();
			String addSec = dto.getAddSec();
			String conClassStudying = "";

			if (!addClass.equalsIgnoreCase("")) {
				conClassStudying = addClass+"--" +"%";
			}
			if (!addSec.equalsIgnoreCase("")) {
				conClassStudying = addClass;
				conClassStudying = conClassStudying+"--"+addSec+"%";
			}

			String classStudying = DataUtil.emptyString(conClassStudying);
			String querySub = "";

			if (!studentname.equalsIgnoreCase("")) {
				querySub = " parents.Student.name like '%" + studentname + "%'";
			}

			if (!classStudying.equalsIgnoreCase("")) {
				querySub = " parents.Student.classstudying like '" + classStudying
						+ "' AND parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0 AND parents.branchid="+Integer.parseInt(branchId);
			} else if (classStudying.equalsIgnoreCase("") && !querySub.equalsIgnoreCase("")) {
				querySub = querySub + " AND parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0 AND parents.branchid="+Integer.parseInt(branchId);
			}

			queryMain = queryMain + querySub;
			log.debug("Search query: {}", queryMain);
			
			List<Parents> searchStudentList = new studentDetailsDAO().getStudentsList(queryMain);
			result.setSearchStudentList(searchStudentList);

			result.setClassSearch(addClass);
			result.setSuccess(true);
		}
		return result;
	}

	/**
	 * View ratings for students in a class/section with a specific assessment/subject
	 * Similar to viewMarks but for ratings with grade-to-numeric conversion
	 */
	public RatingDto viewRatings(RatingViewDto dto, String branchId) {
		RatingDto result = RatingDto.builder().build();
		
		if(branchId != null) {
			try {
				// Build query to search for students similar to viewMarks
				String queryMain = "From Parents as parents where";
				String studentname = DataUtil.emptyString(dto.getStudentName());
				
				String addClass = dto.getAddClass();
				String addSec = dto.getAddSec();
				String conClassStudying = "";
				
				if (!addClass.equalsIgnoreCase("")) {
					conClassStudying = addClass + "--" + "%";
				}
				if (!addSec.equalsIgnoreCase("")) {
					conClassStudying = addClass;
					conClassStudying = conClassStudying + "--" + addSec + "%";
				}
				
				String classStudying = DataUtil.emptyString(conClassStudying);
				String querySub = "";
				
				if (!studentname.equalsIgnoreCase("")) {
					querySub = " parents.Student.name like '%" + studentname + "%'";
				}
				
				if (!classStudying.equalsIgnoreCase("")) {
					querySub = " parents.Student.classstudying like '" + classStudying
							+ "' AND parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0 AND parents.branchid=" + Integer.parseInt(branchId);
				} else if (classStudying.equalsIgnoreCase("") && !querySub.equalsIgnoreCase("")) {
					querySub = querySub + " AND parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0 AND parents.branchid=" + Integer.parseInt(branchId);
				}
				
				queryMain = queryMain + querySub;
				log.debug("Search query: {}", queryMain);
				
				List<Parents> searchStudentList = new studentDetailsDAO().getStudentsList(queryMain);
				
				// Get assessment subject and assessment details
				String assessment = dto.getAssessment();
				String subject = dto.getSubject();
				
				String[] assessmentIdName = assessment.contains(":") ? assessment.split(":") : assessment.split("__");
				int assessmentId = Integer.parseInt(assessmentIdName[0]);
				int assessmentSubjectId = Integer.parseInt(subject);
				
				List<Parents> newStudentList = new ArrayList<Parents>();
				List<HolisticRating> newRatingsList = new ArrayList<HolisticRating>();
				
				// For each student, fetch ratings for this assessment/subject
				for (Parents parent : searchStudentList) {
					List<HolisticRating> singleRatingsList = new RatingDetailsDAO().readListOfRatings(
						parent.getStudent().getSid(),
						assessmentSubjectId,
						assessmentId,
						dto.getAcademicYear()
					);
					
					// Filter for ratings where assessmentsubsubjectid == 0
					for (HolisticRating rating : singleRatingsList) {
						int subSubjectId = rating.getAssessmentsubsubjectid();
						if (subSubjectId == 0) {
							newStudentList.add(parent);
							newRatingsList.add(rating);
							log.debug("Rating Details - Grade: {}, Numeric: {}", rating.getRatinggrade(), rating.getRatingvalue());
						}
					}
				}
				
				result.setNewStudentList(newStudentList);
				result.setNewRatingsList(newRatingsList);
				result.setSubjectSelected(dto.getSubjectSelected());
				result.setAssessmentSelected(dto.getAssessmentSelected());
				result.setSubject(Integer.toString(assessmentSubjectId));
				result.setAssessment(assessment);
				result.setSuccess(true);
				
			} catch (Exception e) {
				log.error("Error viewing ratings", e);
				result.setSuccess(false);
			}
		}
		
		return result;
	}

	/**
	 * Update ratings with grade-to-numeric conversion
	 */
	public ResultResponse updateRatings(RatingUpdateDto dto, String currentAcademicYear, String branchId) {
		
		ResultResponse result = ResultResponse.builder().build();
		
		String[] ratingIds = dto.getRatingId();
		String[] studentsRatings = dto.getStudentsRatings(); // Grade codes

		if (ratingIds == null || studentsRatings == null) {
			log.warn("updateRatings payload missing: ratingIds={}, studentsRatings={}, branchId={}, academicYear={}",
					ratingIds == null ? "null" : ratingIds.length,
					studentsRatings == null ? "null" : studentsRatings.length, branchId, currentAcademicYear);
			result.setSuccess(false);
			result.setMessage("Missing rating payload");
			return result;
		}

		if (ratingIds.length != studentsRatings.length) {
			log.warn("updateRatings payload size mismatch: ratingIds={}, studentsRatings={}, branchId={}, academicYear={}",
					ratingIds.length, studentsRatings.length, branchId, currentAcademicYear);
			result.setSuccess(false);
			result.setMessage("Invalid rating payload size");
			return result;
		}
		
		if(ratingIds != null && studentsRatings != null) {
			
			List<HolisticRating> ratingList = new ArrayList<>();
			
			for(int i = 0; i < ratingIds.length; i++) {
				HolisticRating rating = new HolisticRating();
				rating.setRatingid(Integer.parseInt(ratingIds[i]));
				
				String gradeCode = studentsRatings[i];
				
				// CRITICAL: Update both grade and numeric value
				rating.setRatinggrade(gradeCode); // Update grade for display
				rating.setRatingvalue(convertGradeToNumeric(gradeCode)); // Update numeric for calculations
				
				ratingList.add(rating);
			}
			
			boolean updateSuccess = new RatingDetailsDAO().updateRatings(ratingList);
			
			if(updateSuccess) {
				result.setSuccess(true);
				result.setMessage("Ratings updated successfully");
			} else {
				result.setSuccess(false);
				result.setMessage("Failed to update ratings");
			}
		}
		
		return result;
	}

	/**
	 * Delete multiple ratings
	 */
	public ResultResponse deleteMultiple(RatingUpdateDto dto) {
		
		String[] ratingIds = dto.getRatingId();
		
		if(ratingIds != null) {
			List<Integer> ids = new ArrayList<>();
			for(String id : ratingIds) {
				ids.add(Integer.valueOf(id));
			}
			new RatingDetailsDAO().deleteMultiple(ids);
			return ResultResponse.builder().success(true).build();
		}
		
		return ResultResponse.builder().success(false).build();
	}

	/**
	 * Generate assessment rank for students
	 * Uses numeric values for ranking calculations
	 */
	public ResultResponse getStudentList(String branchId) {
		List<Student> studentList = new studentDetailsDAO().readListOfObjectsForIcon(Integer.parseInt(branchId));
		return ResultResponse.builder().resultList(studentList).success(true).build();
	}

	public AssessmentSubjectsDto getSubjectAssessments(String branchId) {
		AssessmentSubjectsDto result = new AssessmentSubjectsDto();
		result.setAssessmentSubjects(new AssessmentSubjectDetailsDAO().readAllAssessmentSubjects(Integer.parseInt(branchId)));
		result.setAssessments(new HolisticAssessmentDAO().readListOfAssessments(Integer.parseInt(branchId)));
		result.setSuccess(true);
		return result;
	}

	public SearchStudentResponseDto rankSearch(SearchStudentAssessmentDto dto, String branchId) {
		return Search(dto, branchId);
	}

	public StudentRatingGraphResponseDto getStudentGraph(StudentRatingGraphDto dto, String branchId,
			String currentAcademicYear) {
		StudentRatingGraphResponseDto result = StudentRatingGraphResponseDto.builder().build();

		if (branchId != null && dto.getStudentIds() != null && dto.getStudentIds().length > 0) {
			Student searchStudent = new studentDetailsDAO().readUniqueObject(Integer.parseInt(dto.getStudentIds()[0]));

			List<HolisticAssessment> assessmentList = dto.getAssessmentsList();
			if (assessmentList == null || assessmentList.isEmpty()) {
				assessmentList = new HolisticAssessmentDAO().readListOfAssessments(Integer.parseInt(branchId));
			}

			List<AssessmentsDetails> assessmentDetails = new ArrayList<AssessmentsDetails>();
			for (HolisticAssessment assessment : assessmentList) {
				AssessmentsDetails details = new AssessmentsDetails();
				details.setAssessmentName("\"" + assessment.getAssessmentname() + "\"");

				List<Integer> studentIds = new ArrayList<Integer>();
				studentIds.add(searchStudent.getSid());
				List<HolisticRating> ratings = new RatingDetailsDAO().readListOfRatingsForAllAssessments(studentIds,
						currentAcademicYear, Integer.parseInt(branchId));

				List<String> subjects = new ArrayList<String>();
				List<String> grades = new ArrayList<String>();
				for (HolisticRating rating : ratings) {
					if (assessment.getAssessmentid().equals(rating.getAssessmentid())) {
						AssessmentSubject subject = new AssessmentSubjectDetailsDAO()
								.getAssessmentSubjectDetails(rating.getAssessmentsubjectid());
						if (subject != null) {
							subjects.add("\"" + subject.getSubjectname() + "\"");
							grades.add(rating.getRatinggrade());
						}
					}
				}

				details.setSubjects(subjects);
				details.setRatings(grades);
				if (!grades.isEmpty()) {
					assessmentDetails.add(details);
				}
			}

			result.setAssessmentDetailsGraph(assessmentDetails);
			result.setAssessmentDetailsGraphSize(assessmentDetails.size());
			result.setSearchStudent(searchStudent.getName().toUpperCase());
			result.setSuccess(true);
		}

		return result;
	}

	public GenerateAssessmentReportResponseDto generateReport(GenerateAssessmentReportDto dto, String currentAcademicYear,
			String branchId) {
		GenerateAssessmentReportResponseDto result = GenerateAssessmentReportResponseDto.builder().build();
		List<AssessmentSheet> assessmentSheetList = new ArrayList<AssessmentSheet>();

		if (branchId != null && currentAcademicYear != null && dto.getStudentIds() != null) {
			List<HolisticAssessment> assessments = new HolisticAssessmentDAO().readListOfAssessments(Integer.parseInt(branchId));

			for (String studentId : dto.getStudentIds()) {
				AssessmentSheet sheet = new AssessmentSheet();
				Parents parent = new studentDetailsDAO().readUniqueObjectParents(Integer.parseInt(studentId));
				sheet.setParents(parent);

				List<Integer> studentIds = new ArrayList<Integer>();
				studentIds.add(Integer.parseInt(studentId));
				List<HolisticRating> ratings = new RatingDetailsDAO().readListOfRatingsForAllAssessments(studentIds,
						currentAcademicYear, Integer.parseInt(branchId));

				Map<String, Map<String, String>> subjectAssessmentRatings = new HashMap<String, Map<String, String>>();
				for (HolisticRating rating : ratings) {
					AssessmentSubject subject = new AssessmentSubjectDetailsDAO()
							.getAssessmentSubjectDetails(rating.getAssessmentsubjectid());
					if (subject == null) {
						continue;
					}

					String assessmentName = "";
					for (HolisticAssessment assessment : assessments) {
						if (assessment.getAssessmentid().equals(rating.getAssessmentid())) {
							assessmentName = assessment.getAssessmentname();
							break;
						}
					}

					if (!subjectAssessmentRatings.containsKey(subject.getSubjectname())) {
						subjectAssessmentRatings.put(subject.getSubjectname(), new HashMap<String, String>());
					}
					subjectAssessmentRatings.get(subject.getSubjectname()).put(assessmentName, rating.getRatinggrade());
				}

				sheet.setSubjectAssessmentRatings(subjectAssessmentRatings);
				assessmentSheetList.add(sheet);
			}

			int endLoop = assessments.size() / 5;
			result.setEndLoop(endLoop + 1);
			result.setAssessmentSheetList(assessmentSheetList);
			result.setSuccess(true);
		}

		return result;
	}

	public GenerateAssessmentReportResponseDto generateRankReport(GenerateAssessmentReportDto dto, String branchId,
			String currentAcademicYear, String userId) {
		if (dto.getAssessmentDetailsID() != null && dto.getAssessmentClass() != null) {
			generateAssessmentRank(dto.getAssessmentDetailsID(), dto.getAssessmentClass(), currentAcademicYear, branchId);
		}
		return generateReport(dto, currentAcademicYear, branchId);
	}

	public GenerateAssessmentReportResponseDto getStartDate() {
		GenerateAssessmentReportResponseDto result = GenerateAssessmentReportResponseDto.builder().build();
		result.setStartDate(new DataUtil().getPropertiesValue("startdate"));
		return result;
	}

	public ResultResponse generateAssessmentRank(String assessmentId, String classSearch, String academicYear, String branchId) {
		
		ResultResponse result = ResultResponse.builder().build();
		
		try {
			// Get all students in the class
			String queryMain = "From Parents as parents where parents.Student.classstudying like '" + classSearch + "%' AND parents.Student.archive=0 AND parents.branchid=" + Integer.parseInt(branchId);
			List<Parents> studentList = new studentDetailsDAO().getStudentsList(queryMain);
			
			List<AssessmentRank> assessmentRankList = new ArrayList<>();
			
			// Calculate total ratings for each student
			for(Parents parent : studentList) {
				Integer studentId = parent.getStudent().getSid();
				
				List<Integer> studentIds = new ArrayList<>();
				studentIds.add(studentId);
				
				List<HolisticRating> ratings = new RatingDetailsDAO().readListOfRatingsForAllAssessments(
					studentIds, 
					academicYear, 
					Integer.parseInt(branchId)
				);
				
				float totalRating = 0.0f;
				for(HolisticRating rating : ratings) {
					if(rating.getAssessmentid().equals(Integer.parseInt(assessmentId))) {
						totalRating += rating.getRatingvalue(); // Use numeric value for calculation
					}
				}
				
				AssessmentRank assessmentRank = new AssessmentRank();
				assessmentRank.setSid(studentId);
				assessmentRank.setAssessmentid(Integer.parseInt(assessmentId));
				assessmentRank.setRatingobtained(totalRating);
				assessmentRank.setAcademicyear(academicYear);
				assessmentRank.setBranchid(Integer.parseInt(branchId));
				assessmentRank.setStatus("Pass"); // Determine based on criteria
				
				assessmentRankList.add(assessmentRank);
			}
			
			// Sort by total rating (descending)
			assessmentRankList.sort((r1, r2) -> Float.compare(r2.getRatingobtained(), r1.getRatingobtained()));
			
			// Assign ranks
			for(int i = 0; i < assessmentRankList.size(); i++) {
				assessmentRankList.get(i).setRank(i + 1);
			}
			
			// Save ranks
			boolean saveSuccess = new RatingDetailsDAO().addAssessmentRank(assessmentRankList);
			
			if(saveSuccess) {
				result.setSuccess(true);
				result.setMessage("Assessment ranks generated successfully");
			} else {
				result.setSuccess(false);
				result.setMessage("Failed to generate ranks");
			}
			
		} catch (Exception e) {
			log.error("Error generating assessment ranks", e);
			result.setSuccess(false);
			result.setMessage("Error: " + e.getMessage());
		}
		
		return result;
	}

	/**
	 * Build complete Assessment Progress Report for a single student
	 * Orchestrates data fetching, grouping by category, calculating summaries
	 */
	public StudentReportCardDto buildAssessmentProgressReport(Integer studentId, String assessmentName, String academicYear, int branchId) {
		StudentReportCardDto reportCard = StudentReportCardDto.builder().build();

		try {
			// Fetch student information
			studentDetailsDAO studentDAO = new studentDetailsDAO();
			Parents studentInfo = studentDAO.readUniqueObjectParents(studentId);

			if (studentInfo == null) {
				log.warn("Student not found: studentId={}", studentId);
				return reportCard;
			}

			// Fetch all ratings for this student
			RatingDetailsDAO ratingDAO = new RatingDetailsDAO();
			List<Object[]> progressData = ratingDAO.fetchStudentProgressData(studentId, academicYear, branchId);

			if (progressData == null || progressData.isEmpty()) {
				log.debug("No ratings found for studentId={}, academicYear={}", studentId, academicYear);
				reportCard.setStudentInfo(studentInfo);
				reportCard.setAssessmentName(assessmentName);
				reportCard.setAcademicYear(academicYear);
				return reportCard;
			}

			// Group ratings by category
			List<CategorySummaryDto> categorySummaries = getCategorizedSubjectRatings(progressData);

			// Calculate overall summary
			OverallSummaryDto overallSummary = calculateOverallSummary(categorySummaries);

			// Build report card
			reportCard.setStudentId(studentId);
			reportCard.setStudentInfo(studentInfo);
			reportCard.setAssessmentName(assessmentName);
			reportCard.setAcademicYear(academicYear);
			reportCard.setCategorySummaries(categorySummaries);
			reportCard.setOverallSummary(overallSummary);
			reportCard.setReportGeneratedDate(new java.text.SimpleDateFormat("dd-MM-yyyy").format(new java.util.Date()));

		} catch (Exception e) {
			log.error("Error building assessment progress report for studentId=" + studentId, e);
		}

		return reportCard;
	}

	/**
	 * Build progress report cards for multiple students.
	 */
	public List<StudentReportCardDto> buildAssessmentProgressReports(String[] studentIds, String assessmentName,
			String academicYear, String branchId) {
		List<StudentReportCardDto> reportCards = new ArrayList<>();

		if (studentIds == null || branchId == null || academicYear == null) {
			return reportCards;
		}

		int parsedBranchId = Integer.parseInt(branchId);
		for (String studentId : studentIds) {
			if (studentId == null || studentId.trim().isEmpty()) {
				continue;
			}

			StudentReportCardDto card = buildAssessmentProgressReport(Integer.parseInt(studentId.trim()), assessmentName,
					academicYear, parsedBranchId);
			if (card != null && card.getStudentInfo() != null) {
				reportCards.add(card);
			}
		}

		return reportCards;
	}

	/**
	 * Group student ratings by assessment category
	 * Calculates category-level aggregates (average, grade, etc.)
	 */
	private List<CategorySummaryDto> getCategorizedSubjectRatings(List<Object[]> progressData) {
		Map<String, List<SubjectRatingDto>> categoryMap = new HashMap<>();

		try {
			// Group subjects by category
			for (Object[] row : progressData) {
				HolisticRating rating = (HolisticRating) row[0];
				String subjectName = (String) row[1];
				String category = (String) row[2];

				if (category == null || category.isEmpty()) {
					category = "General";
				}

				SubjectRatingDto subjectRating = SubjectRatingDto.builder()
						.subjectId(rating.getAssessmentsubjectid())
						.subjectName(subjectName)
						.ratingGrade(rating.getRatinggrade())
						.ratingValue((int) rating.getRatingvalue())
						.displayScore((int) rating.getRatingvalue() + "/100")
						.remark("") // Default - can be populated from remarks table if available
						.build();

				categoryMap.computeIfAbsent(category, k -> new ArrayList<>()).add(subjectRating);
			}

			// Convert map to list of CategorySummaryDto
			List<CategorySummaryDto> categoryList = new ArrayList<>();
			for (Map.Entry<String, List<SubjectRatingDto>> entry : categoryMap.entrySet()) {
				String categoryName = entry.getKey();
				List<SubjectRatingDto> subjects = entry.getValue();

				// Calculate category average
				double avgScore = subjects.stream()
						.mapToDouble(s -> s.getRatingValue())
						.average()
						.orElse(0.0);

				String categoryGrade = mapNumericToGrade(avgScore);

				CategorySummaryDto categorySummary = CategorySummaryDto.builder()
						.categoryName(categoryName)
						.subjects(subjects)
						.averageScore(avgScore)
						.categoryGrade(categoryGrade)
						.totalSubjects(subjects.size())
						.subjectsAttempted(subjects.size())
						.build();

				categoryList.add(categorySummary);
			}

			// Sort by category name
			categoryList.sort((c1, c2) -> c1.getCategoryName().compareTo(c2.getCategoryName()));

			return categoryList;

		} catch (Exception e) {
			log.error("Error categorizing subject ratings", e);
			return new ArrayList<>();
		}
	}

	/**
	 * Calculate overall assessment summary across all categories
	 * Derives overall grade, aggregate remarks, strengths/improvements
	 */
	private OverallSummaryDto calculateOverallSummary(List<CategorySummaryDto> categorySummaries) {
		OverallSummaryDto summary = OverallSummaryDto.builder().build();

		try {
			if (categorySummaries == null || categorySummaries.isEmpty()) {
				return summary;
			}

			// Calculate overall statistics
			double totalScore = 0.0;
			int totalSubjects = 0;

			for (CategorySummaryDto category : categorySummaries) {
				for (SubjectRatingDto subject : category.getSubjects()) {
					totalScore += subject.getRatingValue();
					totalSubjects++;
				}
			}

			double overallPercentage = totalSubjects > 0 ? totalScore / totalSubjects : 0.0;
			String overallGrade = mapNumericToGrade(overallPercentage);
			double avgAcrossCategories = categorySummaries.stream()
					.mapToDouble(CategorySummaryDto::getAverageScore)
					.average()
					.orElse(0.0);

			// Derive aggregate remarks from top N categories with lowest scores
			String remarks = deriveTeacherRemarks(categorySummaries);

			summary.setOverallGrade(overallGrade);
			summary.setOverallPercentage(overallPercentage);
			summary.setAverageAcrossCategories(avgAcrossCategories);
			summary.setTotalRatedSubjects(totalSubjects);
			summary.setTotalCategories(categorySummaries.size());
			summary.setTeacherOverallRemarks(remarks);

		} catch (Exception e) {
			log.error("Error calculating overall summary", e);
		}

		return summary;
	}

	/**
	 * Derive teacher remarks by concatenating top N subject remarks
	 * Prioritizes subjects from lowest-performing categories (areas for improvement)
	 */
	private String deriveTeacherRemarks(List<CategorySummaryDto> categorySummaries) {
		StringBuilder remarks = new StringBuilder();

		try {
			int topN = 3; // Take top 3 remarks

			// Sort categories by average score (low to high) to prioritize improvement areas
			List<CategorySummaryDto> sortedByScore = new ArrayList<>(categorySummaries);
			sortedByScore.sort((c1, c2) -> Double.compare(c1.getAverageScore(), c2.getAverageScore()));

			int remarkCount = 0;
			for (CategorySummaryDto category : sortedByScore) {
				if (remarkCount >= topN) break;

				// Collect non-empty remarks from this category
				for (SubjectRatingDto subject : category.getSubjects()) {
					if (remarkCount >= topN) break;

					if (subject.getRemark() != null && !subject.getRemark().isEmpty()) {
						if (remarks.length() > 0) {
							remarks.append(". ");
						}
						remarks.append(subject.getRemark());
						remarkCount++;
					}
				}
			}

			// Add category-level observations
			if (remarks.length() == 0) {
				// If no subject remarks, create generic observations
				for (CategorySummaryDto category : sortedByScore) {
					String observation = generateCategoryObservation(category);
					if (remarks.length() > 0) {
						remarks.append(". ");
					}
					remarks.append(observation);
					break; // Just one observation
				}
			}

		} catch (Exception e) {
			log.error("Error deriving teacher remarks", e);
			remarks.append("Assessment in progress");
		}

		return remarks.toString().isEmpty() ? "Assessment data pending" : remarks.toString();
	}

	/**
	 * Generate observation text for a category based on average score
	 */
	private String generateCategoryObservation(CategorySummaryDto category) {
		String categoryName = category.getCategoryName();
		String grade = category.getCategoryGrade();
		double avgScore = category.getAverageScore();

		if (avgScore > 90) {
			return categoryName + " performance is excellent (Grade: " + grade + ")";
		} else if (avgScore > 80) {
			return categoryName + " shows good progress (Grade: " + grade + ")";
		} else if (avgScore > 70) {
			return categoryName + " is developing well (Grade: " + grade + ")";
		} else if (avgScore > 60) {
			return categoryName + " needs additional focus (Grade: " + grade + ")";
		} else {
			return categoryName + " requires immediate attention (Grade: " + grade + ")";
		}
	}

	/**
	 * Map numeric score to letter grade
	 * Grade thresholds: >90=A+, >80=A, >75=B+, >70=B, >65=C, >=60=D, <60=F
	 */
	private String mapNumericToGrade(double numericScore) {
		if (numericScore > 90) return "A+";
		if (numericScore > 80) return "A";
		if (numericScore > 75) return "B+";
		if (numericScore > 70) return "B";
		if (numericScore > 65) return "C";
		if (numericScore >= 60) return "D";
		return "F";
	}
}

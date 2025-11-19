package org.ideoholic.curium.model.std.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.parents.dto.Parents;
import org.ideoholic.curium.model.std.dao.StandardDetailsDAO;
import org.ideoholic.curium.model.std.dto.ClassDto;
import org.ideoholic.curium.model.std.dto.ClassIdsDto;
import org.ideoholic.curium.model.std.dto.Classhierarchy;
import org.ideoholic.curium.model.std.dto.Classsec;
import org.ideoholic.curium.model.std.dto.StdOfClassDto;
import org.ideoholic.curium.model.std.dto.UpperLowerClassDto;
import org.ideoholic.curium.model.student.dto.Student;
import org.ideoholic.curium.model.student.dto.StudentIdsDto;
import org.ideoholic.curium.util.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StandardService {

	@Autowired
	private StandardDetailsDAO standardDetailsDao;
	
	public ResultResponse createClass(ClassDto classDto, String branchId, String userId) {
		if (branchId != null) {
			Classsec classsec = new Classsec();
			classsec.setClassdetails(DataUtil.emptyString(classDto.getClassDetails()));
			classsec.setSection(DataUtil.emptyString(classDto.getSection()));
			classsec.setBranchid(Integer.parseInt(branchId));
			classsec.setUserid(Integer.parseInt(userId));
			standardDetailsDao.create(classsec);
			ResultResponse result = viewClasses(branchId);
			result.setSuccess(true);
			return result;
		}

		return ResultResponse.builder().success(false).build();

	}

	public ResultResponse viewClasses(String branchId) {

		if (branchId != null) {
			List<Classsec> classsecList = standardDetailsDao.viewClasses(Integer.parseInt(branchId));
			return ResultResponse.builder().resultList(classsecList).success(true).build();
		}

		return ResultResponse.builder().success(false).build();
	}

	public ResultResponse deleteClasses(ClassIdsDto dto, String branchId) {
		String[] classIds = dto.getClassIds();
		if (classIds != null) {
			List<Integer> ids = new ArrayList<>();
			for (String id : classIds) {
				ids.add(Integer.valueOf(id));
			}
			standardDetailsDao.deleteMultiple(ids);
			ResultResponse result = viewClasses(branchId);
			return result;
		}
		return ResultResponse.builder().success(false).build();
	}

	public ResultResponse addClassHierarchy(UpperLowerClassDto dto, String branchId, String userId) {

		if (branchId != null) {
			Classhierarchy classHierarchy = new Classhierarchy();
			classHierarchy.setLowerclass(DataUtil.emptyString(dto.getLowerClass()));
			classHierarchy.setUpperclass(DataUtil.emptyString(dto.getUpperClass()));
			classHierarchy.setBranchid(Integer.parseInt(branchId));
			classHierarchy.setUserid(Integer.parseInt(userId));
			standardDetailsDao.createClassHierarchy(classHierarchy);
			return viewClasses(branchId);
		}
		return ResultResponse.builder().success(false).build();
	}

	public ResultResponse deleteClassHierarchy(ClassIdsDto dto, String branchId) {
		String[] classIds = dto.getClassIds();
		if (classIds != null) {
			List<Integer> ids = new ArrayList<>();
			for (String id : classIds) {
				ids.add(Integer.valueOf(id));
			}
			standardDetailsDao.deleteClassHierarchy(ids);
			return viewClasses(branchId);
		}
		return ResultResponse.builder().success(false).build();
	}

	public ResultResponse viewClassHierarchy(String branchId) {
		ResultResponse result = ResultResponse.builder().build();
		if (branchId != null) {
			List<Classhierarchy> classHierarchy = standardDetailsDao
					.viewClassHierarchy(Integer.parseInt(branchId));
			result.setResultList(classHierarchy);
		}
		result.setSuccess(true);
		return result;
	}

	public ResultResponse graduateMultiple(StudentIdsDto dto) {
		String[] studentIds = dto.getStudentIds();
		ResultResponse result = ResultResponse.builder().build();
		List<Integer> ids = new ArrayList<>();
		for (String id : studentIds) {
			log.debug("id" + id);
			ids.add(Integer.valueOf(id));
		}
		if (standardDetailsDao.graduateMultiple(ids)) {
			result.setSuccess(true);
		}
		return result;

	}

	public ResultResponse droppedoutMultiple(StudentIdsDto dto) {
		String[] studentIds = dto.getStudentIds();
		ResultResponse result = ResultResponse.builder().build();
		List<Integer> ids = new ArrayList<>();
		for (String id : studentIds) {
			log.debug("id" + id);
			ids.add(Integer.valueOf(id));
		}
		if (standardDetailsDao.droppedoutMultiple(ids)) {
			result.setSuccess(true);
		}
		return result;
	}

	public ResultResponse leftoutMultiple(StudentIdsDto dto) {
		String[] studentIds = dto.getStudentIds();
		ResultResponse result = ResultResponse.builder().build();
		List<Integer> ids = new ArrayList<>();
		for (String id : studentIds) {
			log.debug("id" + id);
			ids.add(Integer.valueOf(id));

		}
		if (standardDetailsDao.leftoutMultiple(ids)) {
			result.setSuccess(true);
		}
		return result;
	}

	public ResultResponse viewGraduated() {

		ResultResponse result = ResultResponse.builder().build();

		try {
			List<Student> list = standardDetailsDao.readListOfStudentsGraduated();
			result.setResultList(list);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ResultResponse viewDropped() {

		ResultResponse result = ResultResponse.builder().build();

		try {
			List<Student> list = standardDetailsDao.readListOfStudentsDropped();
			result.setResultList(list);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void restoreMultipleGraduate(StudentIdsDto dto) {
		String[] studentIds = dto.getStudentIds();
		if (studentIds != null) {
			List<Integer> ids = new ArrayList<>();
			for (String id : studentIds) {
				ids.add(Integer.valueOf(id));

			}
			standardDetailsDao.restoreMultipleGraduate(ids);
		}
	}

	public void restoreMultipleDroppedout(StudentIdsDto dto) {
		String[] studentIds = dto.getStudentIds();
		if (studentIds != null) {
			List<Integer> ids = new ArrayList<>();
			for (String id : studentIds) {
				ids.add(Integer.valueOf(id));

			}
			standardDetailsDao.restoreMultipleDroppedout(ids);
		}
	}

	public ResultResponse searchByClass(StdOfClassDto dto, String branchId, String currentAcademicYear) {

		String classofStd = dto.getClassOfStd();
		ResultResponse result = ResultResponse.builder().build();

		if (branchId != null) {
			try {
				if (classofStd != null) {
					classofStd = classofStd + "--";
				}
				List<Parents> studentList = standardDetailsDao.getStudentsByClass(classofStd,
						Integer.parseInt(branchId), currentAcademicYear);
				result.setResultList(studentList);
				result.setSuccess(true);
			} catch (Exception e) {
				result.setSuccess(false);
			}
		}
		return result;

	}

	public ResultResponse viewleft() {

		ResultResponse result = ResultResponse.builder().build();
		try {
			List<Student> list = standardDetailsDao.readListOfStudentsLeft();
			result.setResultList(list);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void restoreMultipleLeftout(StudentIdsDto dto) {
		String[] studentIds = dto.getStudentIds();
		if (studentIds != null) {
			List<Integer> ids = new ArrayList<>();
			for (String id : studentIds) {
				ids.add(Integer.valueOf(id));
			}
			standardDetailsDao.restoreMultipleLeftout(ids);
		}
	}

	public ResultResponse viewClassesForTeacher(String classteacher,String branchId) {
		List<String> classTeacherList = Arrays.asList(classteacher.split("\\s*,\\s*"));
		List<Classsec> finalClasssecList = new ArrayList<Classsec>();
		
		classTeacherList = classTeacherList.stream()
					               .filter(s -> s != null && !s.isEmpty())
						               .collect(Collectors.toList());
						
		if (classTeacherList.size() > 0) {
			for (String className : classTeacherList) {
				String cleanedClass = className.replaceAll("--.*$", "").trim();

				Classsec classRow = new Classsec();
				classRow.setClassdetails(cleanedClass);
				classRow.setSection(""); // or null
				classRow.setBranchid(Integer.parseInt(branchId));
				// classRow.setUserid(userid); // Set your actual user ID
				finalClasssecList.add(classRow);
			}

			Map<String, Classsec> uniqueByClassdetails = finalClasssecList.stream()
					.filter(c -> c.getClassdetails() != null && !c.getClassdetails().isEmpty())
					.collect(Collectors.toMap(Classsec::getClassdetails, // key = classdetails
							c -> c, // value = Classsec object
							(existing, replacement) -> existing // keep the first encountered
					));

			finalClasssecList = new ArrayList<>(uniqueByClassdetails.values());

			List<Classsec> classList = standardDetailsDao.viewClasses(Integer.parseInt(branchId));
			List<String> dbSections = classList.stream()
					.filter(c -> c.getClassdetails() == null || c.getClassdetails().trim().isEmpty())
					.filter(c -> c.getSection() != null && !c.getSection().trim().isEmpty()).map(Classsec::getSection)
					.collect(Collectors.toList());

			for (String section : dbSections) {
				Classsec sectionRow = new Classsec();
				sectionRow.setClassdetails(""); // or null
				sectionRow.setSection(section.trim());
				sectionRow.setBranchid(Integer.parseInt(branchId));
				finalClasssecList.add(sectionRow);
			}

		} else {
			List<Classsec> classsecList = standardDetailsDao.viewClasses(Integer.parseInt(branchId));
			return ResultResponse.builder().resultList(classsecList).success(true).build();
		}
		return ResultResponse.builder().resultList(finalClasssecList).success(true).build();
	}

}

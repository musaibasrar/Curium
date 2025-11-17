package org.ideoholic.curium.model.diary.service;

import java.util.ArrayList;
import java.util.List;

import org.ideoholic.curium.model.diary.dao.diaryDAO;
import org.ideoholic.curium.model.diary.dto.AddDiaryDto;
import org.ideoholic.curium.model.diary.dto.DairyIdsDto;
import org.ideoholic.curium.model.diary.dto.Diary;
import org.ideoholic.curium.model.diary.dto.DiaryDetailsMessageResponseDto;
import org.ideoholic.curium.model.diary.dto.DiaryResponseDto;
import org.ideoholic.curium.model.student.dao.StudentDetailsDAO;
import org.ideoholic.curium.model.student.dto.Student;
import org.ideoholic.curium.model.student.dto.StudentIdDto;
import org.ideoholic.curium.model.student.dto.StudentIdPageDto;
import org.ideoholic.curium.model.user.dto.Login;
import org.ideoholic.curium.util.DataUtil;
import org.ideoholic.curium.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiaryService {
	@Autowired
	private diaryDAO diarysDao;
	
	@Autowired
	private StudentDetailsDAO studentDetailsDao;
		
	public void adddetail() {
		Login login = new Login();
		/*
		 * String Id=login.getUsername(); Student student = new
		 * studentDetailsDAO().readploginUniqueObject(Id);
		 
		String studentcls = student.getClassstudying();
		request.setAttribute("studentcls", studentcls);
		*/

	}

	public void addDiary(AddDiaryDto addDiaryDto, String branchId, String userLoginId, String currentAcademicYear) {
		Diary diary = new Diary();

		if (branchId != null) {

			String secString = DataUtil.emptyString(addDiaryDto.getAddSec());
			String classString = addDiaryDto.getAddClass() + "--" + secString;

			diary.setClasssec(DataUtil.emptyString(classString));
			diary.setMessage(addDiaryDto.getMessageBody());
			diary.setSubject(addDiaryDto.getSubject());
			diary.setBranchid(Integer.parseInt(branchId));
			diary.setUserid(Integer.parseInt(userLoginId));
			diary.setAcademicyear(currentAcademicYear);
			diary.setCreateddate(DateUtil.indiandateParser(addDiaryDto.getCreatedDate()));
			diary.setEnddate(DateUtil.indiandateParser(addDiaryDto.getEndDate()));
			diary.setStartdate(DateUtil.indiandateParser(addDiaryDto.getStartDate()));
			diary = diarysDao.create(diary);
		}
	}

	public DiaryResponseDto viewDiary(String strPage, String branchId) {
		DiaryResponseDto diaryResponseDto = new DiaryResponseDto();

		if (branchId != null) {
			try {
				int page = 1;
				int recordsPerPage = 100;
				if (!"".equalsIgnoreCase(DataUtil.emptyString(strPage))) {
					page = Integer.parseInt(strPage);
				}
				List<Diary> diaryDetails = diarysDao.readListOfObjects((page - 1) * recordsPerPage,
						recordsPerPage, Integer.parseInt(branchId));
				int noOfRecords = diarysDao.getNoOfRecords(Integer.parseInt(branchId));
				int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
				diaryResponseDto.setDiary(diaryDetails);
				diaryResponseDto.setNoOfPages(noOfPages);
				diaryResponseDto.setCurrentPage(page);


				diaryResponseDto.setSuccess(true);
			} catch (Exception e) {
				e.printStackTrace();
				diaryResponseDto.setSuccess(false);
			}
		}
		return diaryResponseDto;
	}


	public DiaryResponseDto viewDiaryParent(StudentIdPageDto studentIdPageDto, String branchId) {
		DiaryResponseDto diaryResponseDto = new DiaryResponseDto();

		if (studentIdPageDto.getStudentBranchId() != null) {
			try {
				Student student = studentDetailsDao.readploginUniqueObject(studentIdPageDto.getStudentId());
				String classsec = student.getClassstudying();
				int page = 1;
				int recordsPerPage = 100;
				if (!"".equalsIgnoreCase(DataUtil.emptyString(studentIdPageDto.getPage()))) {
					page = Integer.parseInt(studentIdPageDto.getPage());
				}
				List<Diary> diaryDetails = diarysDao.readListOfParentObjects((page - 1) * recordsPerPage,
					 recordsPerPage, Integer.parseInt(branchId), classsec);
				int noOfRecords = diarysDao.getNoOfRecords(Integer.parseInt(branchId));
				int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
				diaryResponseDto.setDiaryparents(diaryDetails);
				diaryResponseDto.setNoOfPages(noOfPages);
				diaryResponseDto.setCurrentPage(page);
				diaryResponseDto.setSuccess(true);

			} catch (Exception e) {
				e.printStackTrace();
				diaryResponseDto.setSuccess(false);

			}
		}
		return diaryResponseDto;
	}

	public void deleteRecord(DairyIdsDto dairyIdsDto) {
		String[] idDiary = dairyIdsDto.getIdDiary();
		if (idDiary != null) {
			List<Integer> ids = new ArrayList<>();
			for (String id : idDiary) {
				System.out.println("id" + id);
				ids.add(Integer.valueOf(id));
			}
			diarysDao.deleteRecord(ids);
		}
	}
	
	public DiaryDetailsMessageResponseDto viewDetailsOfDiaryMessage(StudentIdDto studentIdDto) {
		DiaryDetailsMessageResponseDto viewDetailsOfDiaryMessageResponseDto = new DiaryDetailsMessageResponseDto();
		long id = Long.parseLong(studentIdDto.getDiaryId());
		Diary diary = diarysDao.getMessage(id);
		viewDetailsOfDiaryMessageResponseDto.setDiary(diary);
		viewDetailsOfDiaryMessageResponseDto.setSuccess(true);

		return viewDetailsOfDiaryMessageResponseDto;
	  }
}
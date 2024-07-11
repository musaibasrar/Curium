package org.ideoholic.curium.model.diary.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.dto.RequestPageDto;
import org.ideoholic.curium.model.diary.dao.diaryDAO;
import org.ideoholic.curium.model.diary.dto.*;
import org.ideoholic.curium.model.student.dao.studentDetailsDAO;
import org.ideoholic.curium.model.student.dto.Student;
import org.ideoholic.curium.model.student.dto.StudentIdDto;
import org.ideoholic.curium.model.student.dto.StudentIdPageDto;
import org.ideoholic.curium.model.user.dto.Login;
import org.ideoholic.curium.util.DataUtil;
import org.ideoholic.curium.util.DateUtil;

public class DiaryService {
	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession httpSession;
	private String BRANCHID = "branchid";
	/**
	 * Size of a byte buffer to read/write file
	 */
	private static final int BUFFER_SIZE = 4096;

	public DiaryService(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.httpSession = request.getSession();
	}

	public void adddetail() {
		// TODO Auto-generated method stub
		Login login = new Login();
		/*
		 * String Id=login.getUsername(); Student student = new
		 * studentDetailsDAO().readploginUniqueObject(Id);
		 
		String studentcls = student.getClassstudying();
		request.setAttribute("studentcls", studentcls);
		*/

	}

	public void addDiary(AddDiaryDto addDiaryDto, String branchId, String userLoginId, String currentAcademicYear) {
		// TODO Auto-generated method stub
		Diary diary = new Diary();

		if (branchId != null) {

			String secString = DataUtil.emptyString(addDiaryDto.getAddSec());
			String classString = addDiaryDto.getAddClass() + "--" + secString;

			diary.setClasssec(DataUtil.emptyString(classString));
			diary.setMessage(addDiaryDto.getMessageBody());
			diary.setSubject(addDiaryDto.getSubject());
			diary.setBranchid(branchId);
			diary.setUserid(Integer.parseInt(userLoginId));
			diary.setAcademicyear(currentAcademicYear);
			diary.setCreateddate(DateUtil.indiandateParser(addDiaryDto.getCreatedDate()));
			diary.setEnddate(DateUtil.indiandateParser(addDiaryDto.getEndDate()));
			diary.setStartdate(DateUtil.indiandateParser(addDiaryDto.getStartDate()));
			diary = new diaryDAO().create(diary);
		}
	}

	public DiaryResponseDto viewDiary(String strPage, String branchId) {
		DiaryResponseDto diaryResponseDto = new DiaryResponseDto();
		// TODO Auto-generated method stub
		boolean result = false;

		if (branchId != null) {
			try {
				int page = 1;
				int recordsPerPage = 100;
				if (!"".equalsIgnoreCase(DataUtil.emptyString(strPage))) {
					page = Integer.parseInt(strPage);
				}
				List<Object[]> list = new diaryDAO().readListOfObjects((page - 1) * recordsPerPage,
						recordsPerPage, Integer.parseInt(branchId));

				List<Diary> diaryDetails = new ArrayList<Diary>();
				for (Object[] diaryObject : list) {
					Diary diary = new Diary();

					diary.setId((Integer) diaryObject[0]);
					diary.setClasssec((String) diaryObject[1]);
					diary.setAcademicyear((String) diaryObject[2]);
					diary.setBranchid((String) diaryObject[3]);
					diary.setSubject((String) diaryObject[4]);
					diary.setMessage((String) diaryObject[5]);
					diary.setStartdate((Date) diaryObject[6]);
					diary.setEnddate((Date) diaryObject[7]);
					diary.setCreateddate((Date) diaryObject[8]);
					diaryDetails.add(diary);
				}


				int noOfRecords = new diaryDAO().getNoOfRecords(Integer.parseInt(branchId));
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
//viewDiaryparent


	public DiaryResponseDto viewDiaryParent(StudentIdPageDto studentIdPageDto, String branchId) {
		DiaryResponseDto diaryResponseDto = new DiaryResponseDto();
		boolean result = false;

		if (branchId != null) {
			try {
				Student student = new studentDetailsDAO().readploginUniqueObject(studentIdPageDto.getStudentId());
				String classsec = student.getClassstudying();
				int page = 1;
				int recordsPerPage = 100;
				if (!"".equalsIgnoreCase(DataUtil.emptyString(studentIdPageDto.getPage()))) {
					page = Integer.parseInt(studentIdPageDto.getPage());
				}
				List<Object[]> list = new diaryDAO().readListOfParentObjects((page - 1) * recordsPerPage,
						recordsPerPage, Integer.parseInt(branchId), classsec);

				List<Diary> diaryDetails = new ArrayList<Diary>();
				for (Object[] diaryObject : list) {
					Diary diary = new Diary();

					diary.setId((Integer) diaryObject[0]);
					diary.setClasssec((String) diaryObject[1]);
					diary.setAcademicyear((String) diaryObject[2]);
					diary.setBranchid((String) diaryObject[3]);
					diary.setSubject((String) diaryObject[4]);
					diary.setMessage((String) diaryObject[5]);
					diary.setStartdate((Date) diaryObject[6]);
					diary.setEnddate((Date) diaryObject[7]);
					diary.setCreateddate((Date) diaryObject[8]);
					diaryDetails.add(diary);
				}


				int noOfRecords = new diaryDAO().getNoOfRecords(Integer.parseInt(branchId));
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
		// TODO Auto-generated method stub
		String[] idDiary = dairyIdsDto.getIdDiary();
		if (idDiary != null) {
			List<Integer> ids = new ArrayList();
			for (String id : idDiary) {
				System.out.println("id" + id);
				ids.add(Integer.valueOf(id));
			}
			new diaryDAO().deleteRecord(ids);
		}
	}
	
	public ViewDetailsOfDiaryMessageResponseDto viewDetailsOfDiaryMessage(StudentIdDto studentIdDto) {
		ViewDetailsOfDiaryMessageResponseDto viewDetailsOfDiaryMessageResponseDto = new ViewDetailsOfDiaryMessageResponseDto();
		boolean result = false;
		long id = Long.parseLong(studentIdDto.getStudentId());
		Diary diary = new diaryDAO().getMessage(id);
		viewDetailsOfDiaryMessageResponseDto.setDiary(diary);
		viewDetailsOfDiaryMessageResponseDto.setSuccess(true);

		return viewDetailsOfDiaryMessageResponseDto;
	  }
	}



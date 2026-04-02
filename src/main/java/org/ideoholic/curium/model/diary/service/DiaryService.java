package org.ideoholic.curium.model.diary.service;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.codec.binary.Base64;
import org.ideoholic.curium.model.account.service.AccountService;
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
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DiaryService {

	@Autowired
	private diaryDAO diarysDao;
	
	@Autowired
	private StudentDetailsDAO studentDetailsDao;
		
	private String BRANCHID = "branchid";
	/**
	 * Size of a byte buffer to read/write file
	 */
	private static final int BUFFER_SIZE = 4096;
	
	private static final String IMAGE_PNG = "image/png";
	private static final String IMAGE_JPEG = "image/jpeg";
	private static final String IMAGE_JPG = "image/jpg";
	private static final String APPLICATION_PDF = "application/pdf";

	private static final Set<String> ALLOWED_TYPES = Set.of(
	    IMAGE_PNG, IMAGE_JPEG, IMAGE_JPG, APPLICATION_PDF
	);
	
	private String processFile(MultipartFile file) throws IOException {

	    if (file == null || file.getOriginalFilename() == null || file.getOriginalFilename().isEmpty()) {
	        return null;
	    }

	    String contentType = file.getContentType();

	    if (!ALLOWED_TYPES.contains(contentType)) {
	        log.warn("Invalid file type: {}", contentType);
	        return null;
	    }

	    byte[] bytesEncoded = Base64.encodeBase64(file.getBytes());
	    return "data:" + contentType + ";base64," + new String(bytesEncoded);
	}

	
	public void adddetail() {
		Login login = new Login();
	}
	
	public void addDiary(AddDiaryDto addDiaryDto, MultipartFile[] listOfFiles,
            String branchId, String userLoginId, String currentAcademicYear) {

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

			try {
				if (listOfFiles != null && listOfFiles.length > 0) {

					if (listOfFiles.length > 0) {
						diary.setAttachment1(processFile(listOfFiles[0]));
					}
					if (listOfFiles.length > 1) {
						diary.setAttachment2(processFile(listOfFiles[1]));
					}
					if (listOfFiles.length > 2) {
						diary.setAttachment3(processFile(listOfFiles[2]));
					}
				}

			} catch (IOException e) {
				log.error("Error processing file upload", e);
			}

			diary = new diaryDAO().create(diary);
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
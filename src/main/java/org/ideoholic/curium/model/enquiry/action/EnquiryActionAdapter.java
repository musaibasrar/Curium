package org.ideoholic.curium.model.enquiry.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.enquiry.dto.AdmissionEnquiryDto;
import org.ideoholic.curium.model.enquiry.dto.AdmissionEnquiryResponseDto;
import org.ideoholic.curium.model.enquiry.dto.CertificateDto;
import org.ideoholic.curium.model.enquiry.dto.CertificateResponseDto;
import org.ideoholic.curium.model.enquiry.service.EnquiryService;
import org.ideoholic.curium.util.Constants;
import org.ideoholic.curium.util.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnquiryActionAdapter {

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private EnquiryService enquiryService;

	@Autowired
	private HttpSession httpSession;

	public void getCertificate() {
		
		String branchId = DataUtil.getSessionAttributeOrElseNull(httpSession, Constants.BRANCHID);
		String userId = DataUtil.getSessionAttributeOrElseNull(httpSession, Constants.USERID);

		CertificateDto dto = new CertificateDto();
		dto.setName(request.getParameter("subject"));
		dto.setPlace(request.getParameter("place"));
		dto.setMobile(request.getParameter("mobile"));
		dto.setDate(request.getParameter("date"));
		dto.setBranchId(branchId);
		dto.setUserId(userId);

		CertificateResponseDto responseDto = enquiryService.getCertificate(dto);
		request.setAttribute("name", responseDto.getName());
		request.setAttribute("place", responseDto.getPlace());
		request.setAttribute("mobile", responseDto.getMobile());
		request.setAttribute("date", responseDto.getDate());
	}

	public boolean saveEnquiryForm() {
		String branchId = DataUtil.getSessionAttributeOrElseNull(httpSession, Constants.BRANCHID);
		String userId = DataUtil.getSessionAttributeOrElseNull(httpSession, Constants.USERID);
		
		AdmissionEnquiryDto admissionEnquiryDto = new AdmissionEnquiryDto();
		admissionEnquiryDto.setName(request.getParameter("name"));
		admissionEnquiryDto.setGender(request.getParameter("gender"));
		admissionEnquiryDto.setCaste(request.getParameter("caste"));
		admissionEnquiryDto.setPlaceOfBirth(request.getParameter("placeofbirth"));
		admissionEnquiryDto.setSurName(request.getParameter("surname"));
		admissionEnquiryDto.setPreviousClassPassed(request.getParameter("previousclasspass"));
		admissionEnquiryDto.setPreviousSchoolName(request.getParameter("previousschoolname"));
		admissionEnquiryDto.setReligion(request.getParameter("religion"));
		admissionEnquiryDto.setFathername(request.getParameter("fathername"));
		admissionEnquiryDto.setFatherQualification(request.getParameter("fatherqualification"));
		admissionEnquiryDto.setMothername(request.getParameter("mothername"));
		admissionEnquiryDto.setMotherQualification(request.getParameter("motherqualification"));
		admissionEnquiryDto.setAdmissionclass(request.getParameter("classadmittedin"));
		admissionEnquiryDto.setBrothereducation(request.getParameter("brothereducation"));
		admissionEnquiryDto.setSistereducation(request.getParameter("sistereducation"));
		admissionEnquiryDto.setOccupation(request.getParameter("occupation"));
		admissionEnquiryDto.setAcademicYear(request.getParameter("academicyear"));
		admissionEnquiryDto.setDateofbirth(request.getParameter("dateofbirth"));
		// admissionEnquiryDto.setDateofbirth(DateUtil.indiandateParser(request.getParameter("dateofbirth")));
		admissionEnquiryDto.setAddress(request.getParameter("address"));
		admissionEnquiryDto.setNotes(request.getParameter("notes"));
		admissionEnquiryDto.setMobileno(request.getParameter("contactno"));
		admissionEnquiryDto.setBranchId(branchId);
		admissionEnquiryDto.setUserId(userId);
		AdmissionEnquiryResponseDto admissionEnquiryResponseDto = enquiryService.saveEnquiryForm(admissionEnquiryDto);

		request.setAttribute("name", admissionEnquiryResponseDto.getName());
		request.setAttribute("gender", admissionEnquiryResponseDto.getGender());
		request.setAttribute("caste", admissionEnquiryResponseDto.getCaste());
		request.setAttribute("placeofbirth", admissionEnquiryResponseDto.getPlaceOfBirth());
		request.setAttribute("surname", admissionEnquiryResponseDto.getSurName());
		request.setAttribute("previousclasspass", admissionEnquiryResponseDto.getPreviousClassPassed());
		request.setAttribute("previousschoolname", admissionEnquiryResponseDto.getPreviousSchoolName());
		request.setAttribute("religion", admissionEnquiryResponseDto.getReligion());
		request.setAttribute("fathername", admissionEnquiryResponseDto.getFathername());
		request.setAttribute("fatherqualification", admissionEnquiryResponseDto.getFatherQualification());
		request.setAttribute("mothername", admissionEnquiryResponseDto.getMothername());
		request.setAttribute("motherqualification", admissionEnquiryResponseDto.getMotherQualification());
		request.setAttribute("classadmittedin", admissionEnquiryResponseDto.getAdmissionclass());
		request.setAttribute("brothereducation", admissionEnquiryResponseDto.getBrothereducation());
		request.setAttribute("sistereducation", admissionEnquiryResponseDto.getSistereducation());
		request.setAttribute("occupation", admissionEnquiryResponseDto.getOccupation());
		request.setAttribute("academicyear", admissionEnquiryResponseDto.getAcademicYear());
		request.setAttribute("dateofbirth", admissionEnquiryResponseDto.getDateofbirth());
		request.setAttribute("address", admissionEnquiryResponseDto.getAddress());
		request.setAttribute("notes", admissionEnquiryResponseDto.getNotes());
		request.setAttribute("contactno", admissionEnquiryResponseDto.getMobileno());
		return admissionEnquiryResponseDto.isSuccess();
	}

	public void getStudentLastEnquiry() {

		AdmissionEnquiryResponseDto admissionEnquiryResponseDto = enquiryService
				.getStudentLastEnquiry(DataUtil.getSessionAttributeOrElseNull(httpSession, Constants.BRANCHID));
		request.setAttribute("admissionEnquiry", admissionEnquiryResponseDto.getAdmissionEnquiry());

	}

	public void viewEnquiry() {

		AdmissionEnquiryResponseDto admissionEnquiryResponseDto = enquiryService
				.viewEnquiry(DataUtil.getSessionAttributeOrElseNull(httpSession, Constants.BRANCHID));
		request.setAttribute("admissionEnquiryList", admissionEnquiryResponseDto.getAdmissionEnquiryList());

	}

	public void getStudentEnquiry() {

		AdmissionEnquiryDto admissionEnquiryDto = new AdmissionEnquiryDto();
		admissionEnquiryDto.setId(Integer.parseInt(request.getParameter("id")));
		AdmissionEnquiryResponseDto admissionEnquiryResponseDto = enquiryService.getStudentEnquiry(admissionEnquiryDto);
		request.setAttribute("admissionEnquiry", admissionEnquiryResponseDto.getAdmissionEnquiry());

	}

	public boolean updateEnquiry() {
		String branchId = DataUtil.getSessionAttributeOrElseNull(httpSession, Constants.BRANCHID);
		String userId = DataUtil.getSessionAttributeOrElseNull(httpSession, Constants.USERID);

		AdmissionEnquiryDto admissionEnquiryDto = new AdmissionEnquiryDto();
		admissionEnquiryDto.setId(Integer.parseInt(request.getParameter("id")));
		admissionEnquiryDto.setName(request.getParameter("name"));
		admissionEnquiryDto.setGender(request.getParameter("gender"));
		admissionEnquiryDto.setAcademicYear(request.getParameter("academicyear"));
		admissionEnquiryDto.setCaste(request.getParameter("caste"));
		admissionEnquiryDto.setPlaceOfBirth(request.getParameter("placeofbirth"));
		admissionEnquiryDto.setSurName(request.getParameter("surname"));
		admissionEnquiryDto.setPreviousClassPassed(request.getParameter("previousclasspass"));
		admissionEnquiryDto.setPreviousSchoolName(request.getParameter("previousschoolname"));
		admissionEnquiryDto.setReligion(request.getParameter("religion"));
		admissionEnquiryDto.setFathername(request.getParameter("fathername"));
		admissionEnquiryDto.setFatherQualification(request.getParameter("fatherqualification"));
		admissionEnquiryDto.setMothername(request.getParameter("mothername"));
		admissionEnquiryDto.setMotherQualification(request.getParameter("motherqualification"));
		admissionEnquiryDto.setAdmissionclass(request.getParameter("classadmittedin"));
		admissionEnquiryDto.setBrothereducation(request.getParameter("brothereducation"));
		admissionEnquiryDto.setSistereducation(request.getParameter("sistereducation"));
		admissionEnquiryDto.setOccupation(request.getParameter("occupation"));
		admissionEnquiryDto.setAcademicYear(request.getParameter("academicyear"));
		admissionEnquiryDto.setDateofbirth(request.getParameter("dateofbirth"));
		admissionEnquiryDto.setAddress(request.getParameter("address"));
		admissionEnquiryDto.setNotes(request.getParameter("notes"));
		admissionEnquiryDto.setMobileno(request.getParameter("contactno"));
		admissionEnquiryDto.setBranchId(branchId);
		admissionEnquiryDto.setUserId(userId);
		AdmissionEnquiryResponseDto admissionEnquiryResponseDto = enquiryService.updateEnquiry(admissionEnquiryDto);
		return admissionEnquiryResponseDto.isSuccess();

	}

	public void deleteEnquiry() {
		AdmissionEnquiryDto admissionEnquiryDto = new AdmissionEnquiryDto();
		admissionEnquiryDto.setEnquiryIds(request.getParameterValues("id"));
		enquiryService.deleteEnquiry(admissionEnquiryDto);
		AdmissionEnquiryResponseDto admissionEnquiryResponseDto = enquiryService.deleteEnquiry(admissionEnquiryDto);

		request.setAttribute("deletesuccess", admissionEnquiryResponseDto.isSuccess());

	}
}

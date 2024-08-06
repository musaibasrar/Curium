package org.ideoholic.curium.model.diary.action;

import org.ideoholic.curium.dto.RequestPageDto;
import org.ideoholic.curium.model.diary.dto.*;
import org.ideoholic.curium.model.diary.service.DiaryService;
import org.ideoholic.curium.model.student.dto.StudentIdDto;
import org.ideoholic.curium.model.student.dto.StudentIdPageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@Service
public class DiaryActionAdapter {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;
    @Autowired
    private HttpSession httpSession;
    @Autowired
    private DiaryService diaryService;

    private String BRANCHID = "branchid";

    public void addDiary() {
        AddDiaryDto addDiaryDto = new AddDiaryDto();
        addDiaryDto.setAddSec(request.getParameter("addsec"));
        addDiaryDto.setAddClass(request.getParameter("addclass"));
        addDiaryDto.setMessageBody(request.getParameter("messagebody"));
        addDiaryDto.setSubject(request.getParameter("subject"));
        addDiaryDto.setCreatedDate(request.getParameter("createddate"));
        addDiaryDto.setEndDate(request.getParameter("enddate"));
        addDiaryDto.setStartDate(request.getParameter("startdate"));


        diaryService.addDiary(addDiaryDto, httpSession.getAttribute(BRANCHID).toString(),
                httpSession.getAttribute("userloginid").toString(),
                httpSession.getAttribute("currentAcademicYear").toString());

    }

    public boolean viewDiary() {

        String page = request.getParameter("page");

        DiaryResponseDto diaryResponseDto = diaryService.viewDiary(page, httpSession.getAttribute(BRANCHID).toString());

        request.setAttribute("diary", diaryResponseDto.getDiary());
        request.setAttribute("noOfPages", diaryResponseDto.getNoOfPages());
        request.setAttribute("currentPage", diaryResponseDto.getCurrentPage());

        return diaryResponseDto.isSuccess();
    }

    public void deleteRecord() {
        DairyIdsDto dairyIdsDto = new DairyIdsDto();
        dairyIdsDto.setIdDiary(request.getParameterValues("id"));
        diaryService.deleteRecord(dairyIdsDto);
    }
    public boolean viewDiaryParent() {

        StudentIdPageDto studentIdPageDto = new StudentIdPageDto();
        studentIdPageDto.setStudentId(request.getParameter("id"));
        studentIdPageDto.setPage(request.getParameter("page"));

        DiaryResponseDto diaryResponseDto = diaryService.viewDiaryParent(studentIdPageDto, httpSession.getAttribute(BRANCHID).toString());
        request.setAttribute("diaryparents", diaryResponseDto.getDiaryparents());
        request.setAttribute("noOfPages", diaryResponseDto.getNoOfPages());
        request.setAttribute("currentPage", diaryResponseDto.getCurrentPage());
        return diaryResponseDto.isSuccess();
    }
    public boolean viewDetailsOfDiaryMessage() {
        StudentIdDto studentIdDto =new StudentIdDto();
        studentIdDto.setStudentId(request.getParameter("id").toString());
        ViewDetailsOfDiaryMessageResponseDto viewDetailsOfDiaryMessageResponseDto = diaryService.viewDetailsOfDiaryMessage(studentIdDto);
        httpSession.setAttribute("diary", viewDetailsOfDiaryMessageResponseDto.getDiary());
        return viewDetailsOfDiaryMessageResponseDto.isSuccess();
    }
}

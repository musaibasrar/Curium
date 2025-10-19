package org.ideoholic.curium.model.diary.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.diary.dto.AddDiaryDto;
import org.ideoholic.curium.model.diary.dto.DairyIdsDto;
import org.ideoholic.curium.model.diary.dto.DiaryDetailsMessageResponseDto;
import org.ideoholic.curium.model.diary.dto.DiaryResponseDto;
import org.ideoholic.curium.model.diary.service.DiaryService;
import org.ideoholic.curium.model.student.dto.StudentIdDto;
import org.ideoholic.curium.model.student.dto.StudentIdPageDto;
import org.ideoholic.curium.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiaryActionAdapter {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpSession httpSession;
    @Autowired
    private DiaryService diaryService;

    public void addDiary() {
        AddDiaryDto addDiaryDto = new AddDiaryDto();
        addDiaryDto.setAddSec(request.getParameter("addsec"));
        addDiaryDto.setAddClass(request.getParameter("addclass"));
        addDiaryDto.setMessageBody(request.getParameter("messagebody"));
        addDiaryDto.setSubject(request.getParameter("subject"));
        addDiaryDto.setCreatedDate(request.getParameter("createddate"));
        addDiaryDto.setEndDate(request.getParameter("enddate"));
        addDiaryDto.setStartDate(request.getParameter("startdate"));


        diaryService.addDiary(addDiaryDto, httpSession.getAttribute(Constants.BRANCHID).toString(),
                httpSession.getAttribute(Constants.USERID).toString(),
                httpSession.getAttribute(Constants.CURRENTACADEMICYEAR).toString());

    }

    public boolean viewDiary() {

        String page = request.getParameter("page");

        DiaryResponseDto diaryResponseDto = diaryService.viewDiary(page, httpSession.getAttribute(Constants.BRANCHID).toString());

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

        DiaryResponseDto diaryResponseDto = diaryService.viewDiaryParent(studentIdPageDto, httpSession.getAttribute(Constants.BRANCHID).toString());
        request.setAttribute("diaryparents", diaryResponseDto.getDiaryparents());
        request.setAttribute("noOfPages", diaryResponseDto.getNoOfPages());
        request.setAttribute("currentPage", diaryResponseDto.getCurrentPage());
        return diaryResponseDto.isSuccess();
    }
    public boolean viewDetailsOfDiaryMessage() {
        StudentIdDto studentIdDto =new StudentIdDto();
        studentIdDto.setDiaryId(request.getParameter("id").toString());
        DiaryDetailsMessageResponseDto viewDetailsOfDiaryMessageResponseDto = diaryService.viewDetailsOfDiaryMessage(studentIdDto);
        httpSession.setAttribute("diary", viewDetailsOfDiaryMessageResponseDto.getDiary());
        return viewDetailsOfDiaryMessageResponseDto.isSuccess();
    }
}

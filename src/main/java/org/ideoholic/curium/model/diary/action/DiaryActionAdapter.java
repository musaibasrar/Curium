package org.ideoholic.curium.model.diary.action;

import org.ideoholic.curium.model.diary.dto.*;
import org.ideoholic.curium.model.diary.service.DiaryService;
import org.ideoholic.curium.util.ResultResponse;
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

    private String BRANCHID = "branchid";

    public void addDiary() {
        DiaryService diaryService = new DiaryService(request, response);
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
        DiaryService diaryService = new DiaryService(request, response);
        ViewDiaryDto viewDiaryDto = new ViewDiaryDto();
        viewDiaryDto.setPage(request.getParameter("page"));

        DiaryResponseDto diaryResponseDto = diaryService.viewDiary(viewDiaryDto, httpSession.getAttribute(BRANCHID).toString());

        request.setAttribute("diary", diaryResponseDto.getDiary());
        request.setAttribute("noOfPages", diaryResponseDto.getNoOfPages());
        request.setAttribute("currentPage", diaryResponseDto.getCurrentPage());

        return diaryResponseDto.isSuccess();
    }

    public void deleteRecord() {
        DiaryService diaryService = new DiaryService(request, response);
        DairyIdsDto dairyIdsDto = new DairyIdsDto();
        dairyIdsDto.setIdDiary(request.getParameterValues("id"));
        diaryService.deleteRecord(dairyIdsDto);
    }
    public boolean viewDiaryParent() {
        DiaryService diaryService = new DiaryService(request,response);

        ViewDiaryParentDto viewDiaryParentDto = new ViewDiaryParentDto();
        viewDiaryParentDto.setStudentId(request.getParameter("id"));
        viewDiaryParentDto.setPage(request.getParameter("page"));

        DiaryResponseDto diaryResponseDto = diaryService.viewDiaryParent(viewDiaryParentDto, httpSession.getAttribute(BRANCHID).toString());
        request.setAttribute("diaryparents", diaryResponseDto.getDiaryparents());
        request.setAttribute("noOfPages", diaryResponseDto.getNoOfPages());
        request.setAttribute("currentPage", diaryResponseDto.getCurrentPage());
        return diaryResponseDto.isSuccess();
    }
//    ViewDiaryParentDto viewDiaryParentDto = new ViewDiaryParentDto();
//viewDiaryParentDto.setStudentId(request.getParameter("id"));
//viewDiaryParentDto.setPage(request.getParameter("page"));
}

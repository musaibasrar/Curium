package org.ideoholic.curium.model.diary.action;

import org.ideoholic.curium.model.diary.dto.AddDiaryDto;
import org.ideoholic.curium.model.diary.dto.ViewDiaryDto;
import org.ideoholic.curium.model.diary.dto.ViewDiaryResponseDto;
import org.ideoholic.curium.model.diary.service.Diaryservice;
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
        Diaryservice diaryservice = new Diaryservice(request, response);
        AddDiaryDto addDiaryDto = new AddDiaryDto();
        addDiaryDto.setAddSec(request.getParameter("addsec"));
        addDiaryDto.setAddClass(request.getParameter("addclass"));
        addDiaryDto.setMessageBody(request.getParameter("messagebody"));
        addDiaryDto.setSubject(request.getParameter("subject"));
        addDiaryDto.setCreatedDate(request.getParameter("createddate"));
        addDiaryDto.setEndDate(request.getParameter("enddate"));
        addDiaryDto.setStartDate(request.getParameter("startdate"));


        diaryservice.addDiary(addDiaryDto, httpSession.getAttribute(BRANCHID).toString(),
                                           httpSession.getAttribute("userloginid").toString(),
                                           httpSession.getAttribute("currentAcademicYear").toString());

    }
    public boolean viewDiary() {
        Diaryservice diaryservice = new Diaryservice(request,response);
        ViewDiaryDto viewDiaryDto = new ViewDiaryDto();
        viewDiaryDto.setPage(request.getParameter("page"));

        ViewDiaryResponseDto viewDiaryResponseDto = diaryservice.viewDiary(viewDiaryDto, httpSession.getAttribute(BRANCHID).toString());

        request.setAttribute("diary", viewDiaryResponseDto.getDiary());
        request.setAttribute("noOfPages", viewDiaryResponseDto.getNoOfPages());
        request.setAttribute("currentPage", viewDiaryResponseDto.getCurrentPage());

        return viewDiaryResponseDto.isSuccess();
    }
}

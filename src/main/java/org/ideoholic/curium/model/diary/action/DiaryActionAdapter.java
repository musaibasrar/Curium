package org.ideoholic.curium.model.diary.action;

import org.ideoholic.curium.model.diary.dto.AddDiaryDto;
import org.ideoholic.curium.model.diary.service.Diaryservice;
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

        addDiaryDto.setUserLoginId(httpSession.getAttribute("userloginid").toString());
        addDiaryDto.setCurrentAcademicYear(httpSession.getAttribute("currentAcademicYear").toString());
        diaryservice.addDiary(addDiaryDto, httpSession.getAttribute(BRANCHID).toString());

    }
}

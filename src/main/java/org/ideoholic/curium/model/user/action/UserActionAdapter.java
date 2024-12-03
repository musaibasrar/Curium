package org.ideoholic.curium.model.user.action;

import org.ideoholic.curium.model.adminexpenses.service.AdminService;
import org.ideoholic.curium.model.feescollection.action.FeesCollectionActionAdapter;
import org.ideoholic.curium.model.std.action.StandardActionAdapter;
import org.ideoholic.curium.model.user.dto.SearchByDateDto;
import org.ideoholic.curium.model.user.dto.SearchByDateResponseDto;
import org.ideoholic.curium.model.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Service
public class UserActionAdapter {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;
    @Autowired
    private HttpSession httpSession;
    @Autowired
    private StandardActionAdapter standardActionAdapter;
    @Autowired
    private AdminService adminService;
    @Autowired
    private FeesCollectionActionAdapter feesCollectionActionAdapter;

    private String BRANCHID = "branchid";
    private String USERID = "userloginid";
    private String USERNAME = "username";
    private String CURRENTACADEMICYEAR = "currentAcademicYear";


    public void searchByDate() {
        UserService userService = new UserService(request, response, standardActionAdapter, adminService, feesCollectionActionAdapter);

        SearchByDateDto dto  = new SearchByDateDto();
        dto.setBranchId(request.getParameter("selectedbranchid"));
        dto.setToDate(request.getParameter("todate"));
        dto.setFromDate(request.getParameter("fromdate"));
        dto.setOneDay(request.getParameter("oneday"));
        dto.setModeOfPayment(request.getParameter("modeofpayment"));

        SearchByDateResponseDto responseDto = userService.searchByDate(dto, httpSession.getAttribute(BRANCHID).toString(), httpSession.getAttribute("dayone").toString(), httpSession.getAttribute("datefrom").toString(), httpSession.getAttribute("dateto").toString());
        httpSession.setAttribute("feesdetailsbranchname", responseDto.getFeesDetailsBranchName());
        httpSession.setAttribute("branchname", responseDto.getBranchName());
        httpSession.setAttribute("dayone", responseDto.getOneDay());
        httpSession.setAttribute("datefrom", responseDto.getDateFrom());
        httpSession.setAttribute("dateto", responseDto.getDateTo());
        httpSession.setAttribute("datefrom", responseDto.getFromDate());
        httpSession.setAttribute("dateto", responseDto.getDateTo());
        httpSession.setAttribute("dayone", responseDto.getOneDay());
        httpSession.setAttribute("searchfeesdetailslist", responseDto.getFeesMap());
        httpSession.setAttribute("sumofdetailsfees", responseDto.getSumOfFees());
        httpSession.setAttribute("sumofonlyfee", responseDto.getSumOfOnlyFee());
        httpSession.setAttribute("sumoffine", responseDto.getFine());
        httpSession.setAttribute("sumofmisc", responseDto.getMisc());
    }
}

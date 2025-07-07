package org.ideoholic.curium.model.user.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.adminexpenses.service.AdminService;
import org.ideoholic.curium.model.feescollection.action.FeesCollectionActionAdapter;
import org.ideoholic.curium.model.std.action.StandardActionAdapter;
import org.ideoholic.curium.model.user.dto.*;
import org.ideoholic.curium.model.user.service.UserService;
import org.ideoholic.curium.util.Constants;
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

        SearchByDateResponseDto responseDto = userService.searchByDate(dto, httpSession.getAttribute(BRANCHID).toString(), httpSession.getAttribute("dayone"), httpSession.getAttribute("datefrom"), httpSession.getAttribute("dateto"));
        httpSession.setAttribute("feesdetailsbranchname", responseDto.getFeesDetailsBranchName());
        httpSession.setAttribute("dayone", responseDto.getDayOne());
        httpSession.setAttribute("datefrom", responseDto.getDateFrom());
        httpSession.setAttribute("dateto", responseDto.getDateTo());
        httpSession.setAttribute("searchfeesdetailslist", responseDto.getFeesMap());
        httpSession.setAttribute("sumofdetailsfees", responseDto.getSumOfFees());
        httpSession.setAttribute("sumofonlyfee", responseDto.getSumOfOnlyFee());
        httpSession.setAttribute("sumoffine", responseDto.getFine());
        httpSession.setAttribute("sumofmisc", responseDto.getMisc());
    }

    public void advanceSearchByParents() {
        UserService userService = new UserService(request, response, standardActionAdapter, adminService, feesCollectionActionAdapter);

        SearchByParentDto dto = new SearchByParentDto();
        dto.setFathersName(request.getParameter("fathersname"));
        dto.setMothersName(request.getParameter("mothersname"));
        dto.setContactNumber(request.getParameter("contactnumber"));

        ResultResponse resultResponse = userService.advanceSearchByParents(dto, httpSession.getAttribute(BRANCHID).toString());
        request.setAttribute("studentList", resultResponse.getResultList());
    }

    public boolean backupData(String fileName) {
        UserService userService = new UserService(request, response, standardActionAdapter, adminService, feesCollectionActionAdapter);

        ResultResponse resultResponse = userService.backupData(fileName);
        request.setAttribute("Backuplocation", resultResponse.getMessage());

        return resultResponse.isSuccess();
    }

    public void advanceSearch() {
        UserService userService = new UserService(request, response, standardActionAdapter, adminService, feesCollectionActionAdapter);

        AdvanceSearchDto dto = new AdvanceSearchDto();
        dto.setName(request.getParameter("name"));
        dto.setGender(request.getParameter("gender"));
        dto.setDateOfBirth(request.getParameter("dateofbirth"));
        dto.setAge(request.getParameter("age"));
        dto.setAddClass(request.getParameter("addclass"));
        dto.setAddSec(request.getParameter("addsec"));
        dto.setAdmClassE(request.getParameter("admclassE"));
        dto.setAdmSecE(request.getParameter("admsecE"));
        dto.setAdmNo(request.getParameter("admnno"));
        dto.setDateOfAdmission(request.getParameter("dateofadmission"));
        dto.setBloodGroup(request.getParameter("bloodgroup"));
        dto.setNationality(request.getParameter("nationality"));
        dto.setReligion(request.getParameter("religion"));
        dto.setCaste(request.getParameter("caste"));
        dto.setMotherTongue(request.getParameter("motherT"));
        dto.setCreatedDate(request.getParameter("remarks"));
        dto.setSts(request.getParameter("sts"));
        dto.setUId(request.getParameter("uid"));

        ResultResponse resultResponse = userService.advanceSearch(dto, httpSession.getAttribute(BRANCHID).toString());
        request.setAttribute("searchStudentList", resultResponse.getResultList());

    }

    public void dashBoard() {
        UserService userService = new UserService(request, response, standardActionAdapter, adminService, feesCollectionActionAdapter);

        SearchByDateDto dto = new SearchByDateDto();
        dto.setBranchId(request.getParameter("selectedbranchid"));
        dto.setToDate(request.getParameter("todate"));
        dto.setFromDate(request.getParameter("fromdate"));

        DashBoardResponseDto responseDto = userService.dashBoard(dto, httpSession.getAttribute(BRANCHID).toString(), httpSession.getAttribute(CURRENTACADEMICYEAR).toString());
        request.setAttribute("totalteachers", responseDto.getTeacherSize());
        httpSession.setAttribute("expensesdatebranchname", responseDto.getDailyExpensesResponseDto().getExpensesDateBranchName());
        request.setAttribute("dayone", responseDto.getDailyExpensesResponseDto().getDayOne());
        request.setAttribute("dailyadminexpenses", responseDto.getDailyExpensesResponseDto().getDailyAdminExpenses());
        request.setAttribute("dailyexpenses", responseDto.getDailyExpensesResponseDto().getDailyExpenses());
        request.setAttribute("monthlyexpenses", responseDto.getMonthlyExpensesResponseDto().getMonthlyExpenses());
        request.setAttribute("monthlistexpenses", responseDto.getMonthlyExpensesResponseDto().getMonthListExpenses());
        request.setAttribute("totalboysgirls", responseDto.getBoysGirls());
        request.setAttribute("studentxaxis", responseDto.getXaxisList());
        request.setAttribute("studentyaxis", responseDto.getYaxisList());
        request.setAttribute("totalstudents",responseDto.getTotalStudents());
    }

    public boolean authenticateUser() {
        UserService userService = new UserService(request, response, standardActionAdapter, adminService, feesCollectionActionAdapter);

        UserAuthenticationDto dto = new UserAuthenticationDto();
        dto.setUserName(request.getParameter("loginName"));
        dto.setPassword(request.getParameter("password"));

        UserAuthenticationResponseDto responseDto = userService.authenticateUser(dto);
        httpSession.setAttribute("currentAcademicYear", responseDto.getAcademicYear());
        httpSession.setAttribute("username", responseDto.getUserName());
        httpSession.setAttribute("branchid", responseDto.getBranchId());
        httpSession.setAttribute("branchname", responseDto.getBranchName());
        httpSession.setAttribute("branchcode", responseDto.getBranchCode());
        httpSession.setAttribute("branchaddress", responseDto.getBranchAddress());
        httpSession.setAttribute("branchcontact", responseDto.getBranchContact());
        httpSession.setAttribute("userType", responseDto.getUserType());
        httpSession.setAttribute("typeOfUser", responseDto.getTypeOfUser());
        httpSession.setAttribute("userAuth", responseDto.getUserAuth());
        httpSession.setAttribute("userloginid", responseDto.getUserLoginId());
        httpSession.setAttribute("todaysAttendance", responseDto.getAttendanceStatus());
        httpSession.setAttribute("subbranchname",responseDto.getSubBranchName());
        httpSession.setAttribute("previousAcademicYears", responseDto.getPreviousAcademicYears());
        return responseDto.isSuccess();
    }

    public boolean authenticateMultiUser() {
        UserService userService = new UserService(request, response, standardActionAdapter, adminService, feesCollectionActionAdapter);

        String branchId = request.getParameter(BRANCHID);

        UserAuthenticationResponseDto responseDto = userService.authenticateMultiUser(httpSession.getAttribute("username").toString(), branchId);
        httpSession.setAttribute("currentAcademicYear", responseDto.getAcademicYear());
        httpSession.setAttribute("username", responseDto.getUserName());
        httpSession.setAttribute("branchid", responseDto.getBranchId());
        httpSession.setAttribute("branchname", responseDto.getBranchName());
        httpSession.setAttribute("branchcode", responseDto.getBranchCode());
        httpSession.setAttribute("branchaddress", responseDto.getBranchAddress());
        httpSession.setAttribute("branchcontact", responseDto.getBranchContact());
        httpSession.setAttribute("userType", responseDto.getUserType());
        httpSession.setAttribute("typeOfUser", responseDto.getTypeOfUser());
        httpSession.setAttribute("userAuth", responseDto.getUserAuth());
        httpSession.setAttribute("superuserAuth", responseDto.getSuperUserAuth());
        httpSession.setAttribute("userloginid", responseDto.getUserLoginId());
        httpSession.setAttribute(Constants.USERID, responseDto.getUserLoginId());
        httpSession.setAttribute("subbranchname",responseDto.getSubBranchName());
        httpSession.setAttribute("previousAcademicYears", responseDto.getPreviousAcademicYears());
        return responseDto.isSuccess();
    }

    public boolean ChangePassword() {
        UserService userService = new UserService(request, response, standardActionAdapter, adminService, feesCollectionActionAdapter);

        UserAuthenticationDto dto = new UserAuthenticationDto();
        dto.setCurrentPassword(request.getParameter("currentpassword"));
        dto.setNewPassword(request.getParameter("newpassword"));
        dto.setConfirmNewPassword(request.getParameter("confirmpassword"));

        ResultResponse resultResponse = userService.ChangePassword(dto);

        return resultResponse.isSuccess();
    }
}

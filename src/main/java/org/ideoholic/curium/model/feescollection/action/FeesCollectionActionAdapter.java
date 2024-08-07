package org.ideoholic.curium.model.feescollection.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.feescollection.dto.*;
import org.ideoholic.curium.model.feescollection.service.FeesCollectionService;
import org.ideoholic.curium.model.std.action.StandardActionAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Service
public class FeesCollectionActionAdapter {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private HttpSession httpSession;

    @Autowired
    StandardActionAdapter standardActionAdapter;

    private String BRANCHID = "branchid";
    private String USERID = "userloginid";
    private String USERNAME = "username";
    private String CURRENTACADEMICYEAR = "currentAcademicYear";



    public void getFeesReport() {
        FeesCollectionService feesCollectionService = new FeesCollectionService(request, response, standardActionAdapter);

        FeesReportDto dto = new FeesReportDto();
        dto.setAcademicYear(request.getParameter("academicyear"));
        dto.setFeesCat(request.getParameterValues("feescategory"));
        dto.setAddClass(request.getParameterValues("classsearch"));

        ResultResponse resultResponse = feesCollectionService.getFeesReport(dto, httpSession.getAttribute(BRANCHID).toString(), httpSession.getAttribute(CURRENTACADEMICYEAR).toString());
        httpSession.setAttribute("studentfeesreportlist", resultResponse.getResultList());
    }

    public void undoFeesReceipt() {
        FeesCollectionService feesCollectionService = new FeesCollectionService(request, response, standardActionAdapter);

        String receiptId = request.getParameter("id");
        
        ResultResponse resultResponse = feesCollectionService.undoFeesReceipt(receiptId, httpSession.getAttribute(CURRENTACADEMICYEAR).toString());
        request.setAttribute("cancelreceiptresult", resultResponse.isSuccess());
    }

    public void viewCancelledReceipts() {
        FeesCollectionService feesCollectionService = new FeesCollectionService(request, response, standardActionAdapter);

        CancelledReceiptsDto dto = new CancelledReceiptsDto();
        dto.setBranchId(request.getParameter("selectedbranchid"));
        dto.setToDate(request.getParameter("todate"));
        dto.setFromDate(request.getParameter("fromdate"));
        dto.setOneDay(request.getParameter("oneday"));

        CancelledReceiptsResponseDto responseDto = feesCollectionService.viewCancelledReceipts(dto, httpSession.getAttribute(BRANCHID).toString(), httpSession.getAttribute("dayone").toString(), httpSession.getAttribute("dayonecancel").toString(), httpSession.getAttribute("datefromcancel").toString(), httpSession.getAttribute("datetocancel").toString());
        httpSession.setAttribute("feesdetailsbranchname", responseDto.getFeesDetailsBranchName());
        httpSession.setAttribute("branchname", responseDto.getBranchName());
        httpSession.setAttribute("searchfeesdetailslistcancelled", responseDto.getFeesDetailsList());
        httpSession.setAttribute("sumofdetailsfeescancelled", responseDto.getSumOfFees());
        httpSession.setAttribute("dayonecancel", dto.getOneDay());
        httpSession.setAttribute("datefromcancel", responseDto.getDateFromCancel());
        httpSession.setAttribute("datetocancel", responseDto.getDateToCancel());
    }

    public void getStampFees() {
        FeesCollectionService feesCollectionService = new FeesCollectionService(request, response, standardActionAdapter);

        StampFeesDto dto = new StampFeesDto();
        dto.setAcademicYear(request.getParameter("academicyear"));
        dto.setId(request.getParameter("studentId"));
        dto.setStudentName(request.getParameter("studentname"));
        dto.setAdmissionNo(request.getParameter("admissionno"));
        dto.setClassAndSec(request.getParameter("classandsec"));
        dto.setStudentId(request.getParameter("studentId"));
        dto.setDateOfFees(request.getParameter("dateoffees"));

        StampFeeResponseDto responseDto = feesCollectionService.getStampFees(dto, httpSession.getAttribute(CURRENTACADEMICYEAR).toString());
        request.setAttribute("studentfeesdetailspreviousyear", responseDto.getFeesMapPreviousYear());
        request.setAttribute("previousyear", responseDto.getPreviousYear());
        request.setAttribute("studentfeesdetails", responseDto.getFeesMap());
        request.setAttribute("studentNameDetails", responseDto.getStudentNameDetails());
        request.setAttribute("admnoDetails", responseDto.getAdmNoDetails());
        request.setAttribute("classandsecDetails", responseDto.getClassAndSecDetails());
        request.setAttribute("studentIdDetails", responseDto.getStudentIdDetails());
        request.setAttribute("dateoffeesDetails", responseDto.getDateOfFeesDetails());
    }

    public void getFeesDetails() {
        FeesCollectionService feesCollectionService = new FeesCollectionService(request, response, standardActionAdapter);

        FeesReportDto dto = new FeesReportDto();
        dto.setId(request.getParameter("studentId"));
        dto.setAcademicYear(request.getParameter("academicyear"));

        FeesDetailsResponseDto responseDto = feesCollectionService.getFeesDetails(dto);
        request.setAttribute("receiptinfo", responseDto.getReceiptInfo());
        httpSession.setAttribute("feesstructure", responseDto.getFeesStructure());
        httpSession.setAttribute("sumoffees", responseDto.getTotalSum());
        httpSession.setAttribute("dueamount", responseDto.getDueAmount());
        httpSession.setAttribute("totalfees", responseDto.getTotalFeesAmount());
        httpSession.setAttribute("academicPerYear", responseDto.getAcademicPerYear());
        httpSession.setAttribute("currentAcademicYear", responseDto.getCurrentAcademicYear());

    }
}

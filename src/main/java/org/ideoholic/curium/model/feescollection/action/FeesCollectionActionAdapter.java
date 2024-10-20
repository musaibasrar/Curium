package org.ideoholic.curium.model.feescollection.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.feescollection.dto.*;
import org.ideoholic.curium.model.feescollection.service.FeesCollectionService;
import org.ideoholic.curium.model.std.action.StandardActionAdapter;
import org.ideoholic.curium.model.std.dto.ClassesHierarchyDto;
import org.ideoholic.curium.model.std.dto.Classsec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

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

        CancelledReceiptsResponseDto responseDto = feesCollectionService.viewCancelledReceipts(dto, httpSession.getAttribute(BRANCHID).toString());
        httpSession.setAttribute("feesdetailsbranchname", responseDto.getFeesDetailsBranchName());
        httpSession.setAttribute("branchname", responseDto.getBranchName());
        httpSession.setAttribute("searchfeesdetailslistcancelled", responseDto.getFeesDetailsList());
        httpSession.setAttribute("sumofdetailsfeescancelled", responseDto.getSumOfFees());
        httpSession.setAttribute("dayonecancel", responseDto.getDayOneCancel());
        httpSession.setAttribute("datefromcancel", responseDto.getDateFromCancel());
        httpSession.setAttribute("datetocancel", responseDto.getDateToCancel());
    }

    public void getStampFees() {
        FeesCollectionService feesCollectionService = new FeesCollectionService(request, response, standardActionAdapter);

        StampFeeDto dto = new StampFeeDto();
        dto.setAcademicYear(request.getParameter("academicyear"));
        dto.setId(request.getParameter("studentId"));
        dto.setStudentName(request.getParameter("studentname"));
        dto.setAdmissionNo(request.getParameter("admissionno"));
        dto.setClassAndSec(request.getParameter("classandsec"));
        dto.setStudentId(request.getParameter("studentId"));
        dto.setDateOfFees(request.getParameter("dateoffees"));

        StampFeeResponseDto responseDto = feesCollectionService.getStampFees(dto, httpSession.getAttribute(CURRENTACADEMICYEAR).toString());
        request.setAttribute("studentfeesdetailspreviousyear", responseDto.getPreviousYearFeesMap());
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
        dto.setStudentId(request.getParameter("studentId"));
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

    public void previewFeesDetails() {
        FeesCollectionService feesCollectionService = new FeesCollectionService(request, response, standardActionAdapter);

        String sId = request.getParameter("sid");
        String receiptNo = request.getParameter("id");

        DetailsResponseDto responseDto = feesCollectionService.previewFeesDetails(sId, receiptNo, httpSession.getAttribute(CURRENTACADEMICYEAR).toString());
        httpSession.setAttribute("student", responseDto.getStudent());
        request.setAttribute("recieptdate", responseDto.getReceiptDate());
        request.setAttribute("recieptinfo", responseDto.getReceiptInfo());
        request.setAttribute("feescatmap", responseDto.getFeeCatMap());
        request.setAttribute("duplicate", responseDto.getDuplicate());
    }

    public void previewDetails() {
        FeesCollectionService feesCollectionService = new FeesCollectionService(request, response, standardActionAdapter);

        String receiptNumber = request.getParameter("id");
        String duplicate = request.getParameter("duplicate");

        DetailsResponseDto responseDto = feesCollectionService.previewDetails(receiptNumber, duplicate, httpSession.getAttribute(CURRENTACADEMICYEAR).toString());
        httpSession.setAttribute("parents", responseDto.getParents());
        httpSession.setAttribute("student", responseDto.getStudent());
        request.setAttribute("recieptdate", responseDto.getReceiptDate());
        request.setAttribute("recieptinfo", responseDto.getReceiptInfo());
        request.setAttribute("feescatmap", responseDto.getFeeCatMap());
        request.setAttribute("duplicate", responseDto.getDuplicate());
        request.setAttribute("user", responseDto.getUserLogin());
        httpSession.setAttribute("grandTotal", responseDto.getGrandTotal());
    }

    public void getotherStampFees() {
        FeesCollectionService feesCollectionService = new FeesCollectionService(request, response, standardActionAdapter);

        StampFeeDto dto = new StampFeeDto();
        dto.setAcademicYear(request.getParameter("academicyear"));
        dto.setStudentId(request.getParameter("studentId"));
        dto.setStudentName(request.getParameter("studentname"));
        dto.setAdmissionNo(request.getParameter("admissionno"));
        dto.setClassAndSec(request.getParameter("classandsec"));
        dto.setDateOfFees(request.getParameter("dateoffees"));
        StampFeeResponseDto responseDto = feesCollectionService.getotherStampFees(dto, httpSession.getAttribute(CURRENTACADEMICYEAR).toString());
        request.setAttribute("studentotherfeesdetails", responseDto.getOtherFeesMap());
        request.setAttribute("studentNameDetails", responseDto.getStudentNameDetails());
        request.setAttribute("admnoDetails", responseDto.getAdmNoDetails());
        request.setAttribute("classandsecDetails", responseDto.getClassAndSecDetails());
        request.setAttribute("studentIdDetails", responseDto.getStudentIdDetails());
        request.setAttribute("dateoffeesDetails", responseDto.getDateOfFeesDetails());
    }

    public void getotherFeesDetails() {
        FeesCollectionService feesCollectionService = new FeesCollectionService(request, response, standardActionAdapter);

        FeesReportDto dto = new FeesReportDto();
        dto.setStudentId(request.getParameter("studentId"));
        dto.setAcademicYear(request.getParameter("academicyear"));

        FeesDetailsResponseDto responseDto = feesCollectionService.getotherFeesDetails(dto);
        request.setAttribute("receiptinfo", responseDto.getReceiptInfo());
        httpSession.setAttribute("feesstructure", responseDto.getFeesStructure());
        httpSession.setAttribute("sumoffees", responseDto.getTotalSum());
        httpSession.setAttribute("dueamount", responseDto.getDueAmount());
        httpSession.setAttribute("totalfees", responseDto.getTotalFeesAmount());
        httpSession.setAttribute("academicPerYear", responseDto.getAcademicPerYear());
        httpSession.setAttribute("currentAcademicYear", responseDto.getCurrentAcademicYear());
    }

    public void getotherFeesReport() {
        FeesCollectionService feesCollectionService = new FeesCollectionService(request, response, standardActionAdapter);

        FeesReportDto dto = new FeesReportDto();
        dto.setAcademicYear(request.getParameter("academicyear"));
        dto.setFeesCat(request.getParameterValues("otherfeescategory"));
        dto.setAddClass(request.getParameterValues("classsearch"));

        ResultResponse resultResponse = feesCollectionService.getotherFeesReport(dto, httpSession.getAttribute(BRANCHID).toString(), httpSession.getAttribute(CURRENTACADEMICYEAR).toString());
        httpSession.setAttribute("studentotherfeesreportlist", resultResponse.getResultList());
    }

    public void otherpreviewDetails() {
        FeesCollectionService feesCollectionService = new FeesCollectionService(request, response, standardActionAdapter);

        String receiptNumber = request.getParameter("id");
        String duplicate = request.getParameter("duplicate");

        DetailsResponseDto responseDto = feesCollectionService.otherpreviewDetails(receiptNumber, duplicate, httpSession.getAttribute(CURRENTACADEMICYEAR).toString());
        httpSession.setAttribute("parents", responseDto.getParents());
        httpSession.setAttribute("student", responseDto.getStudent());
        request.setAttribute("recieptdate", responseDto.getReceiptDate());
        request.setAttribute("recieptinfo", responseDto.getReceiptInfo());
        request.setAttribute("feescatmap", responseDto.getFeeCatMap());
        request.setAttribute("duplicate", responseDto.getDuplicate());
        request.setAttribute("user", responseDto.getUserLogin());
        httpSession.setAttribute("grandTotal", responseDto.getGrandTotal());
    }

    public void searchOtherFeesCollection() {
        FeesCollectionService feesCollectionService = new FeesCollectionService(request, response, standardActionAdapter);

        CancelledReceiptsDto dto = new CancelledReceiptsDto();
        dto.setBranchId(request.getParameter("selectedbranchid"));
        dto.setToDate(request.getParameter("todate"));
        dto.setFromDate(request.getParameter("fromdate"));
        dto.setOneDay(request.getParameter("oneday"));

        CancelledReceiptsResponseDto responseDto = feesCollectionService.searchOtherFeesCollection(dto, httpSession.getAttribute(BRANCHID).toString());
        httpSession.setAttribute("feesdetailsbranchname", responseDto.getFeesDetailsBranchName());
        httpSession.setAttribute("branchname", responseDto.getBranchName());
        httpSession.setAttribute("dayone", responseDto.getDayOneCancel());
        httpSession.setAttribute("datefrom", responseDto.getDateFromCancel());
        httpSession.setAttribute("dateto", responseDto.getDateToCancel());
        httpSession.setAttribute("searchotherfeesdetailslist", responseDto.getOtherfeesDetailsList());
        httpSession.setAttribute("sumofotherdetailsfees", responseDto.getSumOfFees());
    }

    public void previewOtherFeesDetails() {
        FeesCollectionService feesCollectionService = new FeesCollectionService(request, response, standardActionAdapter);

        String sId= request.getParameter("sid");
        String receiptNo = request.getParameter("id");

        DetailsResponseDto responseDto = feesCollectionService.previewOtherFeesDetails(sId, receiptNo, httpSession.getAttribute(CURRENTACADEMICYEAR).toString());
        httpSession.setAttribute("student", responseDto.getStudent());
        request.setAttribute("recieptdate", responseDto.getReceiptDate());
        request.setAttribute("recieptinfo", responseDto.getOtherReceiptInfo());
        request.setAttribute("feescatmap", responseDto.getFeeCatMap());
        request.setAttribute("duplicate", responseDto.getDuplicate());
    }

    public void cancelOtherFeesReceipt() {
        FeesCollectionService feesCollectionService = new FeesCollectionService(request, response, standardActionAdapter);

        String receiptId = request.getParameter("receiptid");
        String journalId = request.getParameter("journalid");
        String feesReceiptId = request.getParameter("id");

        ResultResponse resultResponse = feesCollectionService.cancelOtherFeesReceipt(receiptId, journalId, feesReceiptId, httpSession.getAttribute(CURRENTACADEMICYEAR).toString());
        request.setAttribute("cancelreceiptresult", resultResponse.isSuccess());
    }

    public void getFeesDetailsDashBoard() {
        FeesCollectionService feesCollectionService = new FeesCollectionService(request, response, standardActionAdapter);

        ClassesHierarchyDto dto = new ClassesHierarchyDto();
        dto.setSelectedBranchId(request.getParameter("selectedbranchid"));
        standardActionAdapter.viewClasses();
        dto.setClasssecList((List<Classsec>)httpSession.getAttribute("classdetailslist"));

        FeesDashboardResponseDto responseDto = feesCollectionService.getFeesDetailsDashBoard(dto, httpSession.getAttribute(BRANCHID).toString(), httpSession.getAttribute(CURRENTACADEMICYEAR).toString());
        request.setAttribute("totalFeesAmountDashBoard", responseDto.getTotalFeesAmount());
        request.setAttribute("totalPaidAmountDashBoard", responseDto.getTotalPaidAmount());
        request.setAttribute("totalDueAmountDashBoard", responseDto.getTotalDueAmount());
        httpSession.setAttribute("feesdetailsbranchname", responseDto.getBranchIdName());
        httpSession.setAttribute("branchname", responseDto.getBranchName());
        httpSession.setAttribute("sumOfFeesDaily", responseDto.getSumOfFeesDaily());
        httpSession.setAttribute("sumOfFeesMonthly", responseDto.getSumOfFeesMonthly());
        httpSession.setAttribute("Currentmonth", responseDto.getCurrentMonth());
    }

    public void getDefaultersReport() {
        FeesCollectionService feesCollectionService = new FeesCollectionService(request, response, standardActionAdapter);

        FeesReportDto dto = new FeesReportDto();
        dto.setAcademicYear(request.getParameter("academicyear"));
        dto.setFeesCat(request.getParameterValues("feescategory"));
        dto.setAddClass(request.getParameterValues("classsearch"));

        ResultResponse resultResponse = feesCollectionService.getDefaultersReport(dto, httpSession.getAttribute(BRANCHID).toString(), httpSession.getAttribute(CURRENTACADEMICYEAR).toString());
        httpSession.setAttribute("studentfeesreportlist", resultResponse.getResultList());
    }

    public void getFeesReportDue() {
        FeesCollectionService feesCollectionService = new FeesCollectionService(request, response, standardActionAdapter);

        FeesReportDto dto = new FeesReportDto();
        dto.setAcademicYear(request.getParameter("academicyear"));
        dto.setFeesCat(request.getParameterValues("feescategory"));
        dto.setAddClass(request.getParameterValues("classsearch"));

        ResultResponse resultResponse = feesCollectionService.getFeesReportDue(dto, httpSession.getAttribute(BRANCHID).toString(), httpSession.getAttribute(CURRENTACADEMICYEAR).toString());
        httpSession.setAttribute("studentfeesreportlist", resultResponse.getResultList());
    }

    public void getFeesStampDueReport() {
        FeesCollectionService feesCollectionService = new FeesCollectionService(request, response, standardActionAdapter);

        FeesReportDto dto = new FeesReportDto();
        dto.setAcademicYear(request.getParameter("academicyear"));
        dto.setAddClass(request.getParameterValues("classsearch"));

        ResultResponse resultResponse = feesCollectionService.getFeesStampDueReport(dto, httpSession.getAttribute(BRANCHID).toString(), httpSession.getAttribute(CURRENTACADEMICYEAR).toString());
        httpSession.setAttribute("studentfeesreportlist", resultResponse.getResultList());
    }

    public boolean printOtherDataForFees() {
        FeesCollectionService feesCollectionService = new FeesCollectionService(request, response, standardActionAdapter);

        CancelledReceiptsDto dto = new CancelledReceiptsDto();
        dto.setFeesIds(request.getParameterValues("feesIDs"));
        dto.setToDate(request.getParameter("todate"));
        dto.setFromDate(request.getParameter("fromdate"));
        dto.setOneDay(request.getParameter("oneday"));

        CancelledReceiptsResponseDto responseDto = feesCollectionService.printOtherDataForFees(dto);
        request.setAttribute("feesmap", responseDto.getFeesMap());
        request.setAttribute("sumofdetailsfees", responseDto.getSumOfFees());
        httpSession.setAttribute("daterangefeescollection", responseDto.getDateToCancel());

        return responseDto.isSuccess();
    }

    public void viewCancelledOtherFeesReceipts() {
        FeesCollectionService feesCollectionService = new FeesCollectionService(request, response, standardActionAdapter);

        CancelledReceiptsDto dto = new CancelledReceiptsDto();
        dto.setBranchId(request.getParameter("selectedbranchid"));
        dto.setToDate(request.getParameter("todate"));
        dto.setFromDate(request.getParameter("fromdate"));
        dto.setOneDay(request.getParameter("oneday"));

        CancelledReceiptsResponseDto responseDto = feesCollectionService.viewCancelledOtherFeesReceipts(dto, httpSession.getAttribute(BRANCHID).toString());
        httpSession.setAttribute("feesdetailsbranchname", responseDto.getFeesDetailsBranchName());
        httpSession.setAttribute("branchname", responseDto.getBranchName());
        httpSession.setAttribute("dayonecancel", responseDto.getDayOneCancel());
        httpSession.setAttribute("datefromcancel", responseDto.getDateFromCancel());
        httpSession.setAttribute("datetocancel", responseDto.getDateToCancel());
        httpSession.setAttribute("searchfeesdetailslistcancelled", responseDto.getOtherfeesDetailsList());
        httpSession.setAttribute("sumofdetailsfeescancelled", responseDto.getSumOfFees());
    }

    public void getFeesCollectionCategory() {
        FeesCollectionService feesCollectionService = new FeesCollectionService(request, response, standardActionAdapter);

        FeesCategoryDto dto = new FeesCategoryDto();
        dto.setBranchId(request.getParameter("selectedbranchid"));
        dto.setToDate(request.getParameter("todate"));
        dto.setFromDate(request.getParameter("fromdate"));
        dto.setToDateOtherFees(request.getParameter("todate"));
        dto.setFromDateOtherFees(request.getParameter("fromdate"));
        dto.setOneDayOtherFees(request.getParameter("oneday"));

        FeesCategoryResponseDto responseDto = feesCollectionService.getFeesCollectionCategory(dto, httpSession.getAttribute(BRANCHID).toString());
        httpSession.setAttribute("feesdetailsbranchname", responseDto.getFeesDetailsBranchName());
        httpSession.setAttribute("branchname", responseDto.getBranchName());
        httpSession.setAttribute("dayone", responseDto.getDayOne());
        httpSession.setAttribute("datefrom", responseDto.getDateFrom());
        httpSession.setAttribute("dateto", responseDto.getDateTo());
        httpSession.setAttribute("feeCategoryCollectionMap", responseDto.getFeeCategoryCollectionMapReport());
        httpSession.setAttribute("feesbycash", responseDto.getFeesByCash());
        httpSession.setAttribute("feesbybank", responseDto.getFeesByBank());
        httpSession.setAttribute("feesbycashotherfees", responseDto.getFeesByCashOtherFees());
        httpSession.setAttribute("feesbycashotherfees", responseDto.getFeesByBankOtherFees());
    }

    public boolean downlaod() {
        FeesCollectionService feesCollectionService = new FeesCollectionService(request, response, standardActionAdapter);

        ResultResponse resultResponse = feesCollectionService.downlaod();

        return resultResponse.isSuccess();
    }

    public void exportDataForStudentsOtherFeesReport() {
        FeesCollectionService feesCollectionService = new FeesCollectionService(request, response, standardActionAdapter);

        StudentFeesDto dto = new StudentFeesDto();
        dto.setStudentotherfeesreportList((List<Studentotherfeesreport>) httpSession.getAttribute("studentotherfeesreportlist"));

        ResultResponse resultResponse = feesCollectionService.exportDataForStudentsOtherFeesReport(dto);
    }

    public void printFeesDueHeadWiseReport() {
        FeesCollectionService feesCollectionService = new FeesCollectionService(request, response, standardActionAdapter);

        StudentFeesDto dto = new StudentFeesDto();
        dto.setStudentFeesReportList((List<StudentFeesReport>) httpSession.getAttribute("studentfeesreportlist"));

        feesCollectionService.printFeesDueHeadWiseReport(dto);
    }

    public void cancelFeesReceipt() {
        FeesCollectionService feesCollectionService = new FeesCollectionService(request, response, standardActionAdapter);

        String receiptId = request.getParameter("receiptid");
        String journalId = request.getParameter("journalid");
        String feesReceiptId = request.getParameter("id");

        ResultResponse resultResponse = feesCollectionService.cancelFeesReceipt(receiptId, journalId, feesReceiptId, httpSession.getAttribute(CURRENTACADEMICYEAR).toString());
        request.setAttribute("cancelreceiptresult", resultResponse.isSuccess());
    }

    public void exportDataForStudentsFeesReport() {
        FeesCollectionService feesCollectionService = new FeesCollectionService(request, response, standardActionAdapter);

        StudentFeesDto dto = new StudentFeesDto();
        dto.setStudentFeesReportList((List<StudentFeesReport>) httpSession.getAttribute("studentfeesreportlist"));

        ResultResponse resultResponse = feesCollectionService.exportDataForStudentsFeesReport(dto);
    }

    public Receiptinfo add() {
        FeesCollectionService feesCollectionService = new FeesCollectionService(request, response, standardActionAdapter);

        AddFeesCollectionDto dto = new AddFeesCollectionDto();
        dto.setStudentId(request.getParameter("studentIdDetails"));
        dto.setAmountPaying(request.getParameterValues("amountpaying"));
        dto.setFineAmount(request.getParameter("fineamount"));
        dto.setMiscAmount(request.getParameter("miscamount"));
        dto.setStudentSfsIds(request.getParameterValues("studentsfsids"));
        dto.setPaymentMethod(request.getParameter("paymentmethod"));
        dto.setAckNo(request.getParameter("ackno"));
        dto.setTransferDate(request.getParameter("transferdate"));
        dto.setTransferBankName(request.getParameter("transferbankname"));
        dto.setChequeNo(request.getParameter("chequeno"));
        dto.setChequeDate(request.getParameter("chequedate"));
        dto.setChequeBankName(request.getParameter("chequebankname"));
        dto.setAcademicYear(request.getParameter("academicyear"));
        dto.setDateOfFeesDetails(request.getParameter("dateoffeesDetails"));
        dto.setClassAndSecDetails(request.getParameter("classandsecDetails"));

        Receiptinfo receiptinfo = feesCollectionService.add(dto, httpSession.getAttribute(CURRENTACADEMICYEAR).toString(), httpSession.getAttribute(BRANCHID).toString(), httpSession.getAttribute(USERID).toString(), httpSession.getAttribute(USERNAME).toString());
        return receiptinfo;
    }

    public void preview(Receiptinfo receiptInfo) {
        FeesCollectionService feesCollectionService = new FeesCollectionService(request, response, standardActionAdapter);

        DetailsResponseDto responseDto = feesCollectionService.preview(receiptInfo, httpSession.getAttribute(CURRENTACADEMICYEAR).toString());
        httpSession.setAttribute("student", responseDto.getStudent());
        request.setAttribute("recieptdate", responseDto.getReceiptDate());
        request.setAttribute("recieptinfo", responseDto.getReceiptInfo());
        request.setAttribute("feescatmap", responseDto.getFeeCatMap());
    }

    public Otherreceiptinfo addother() {
        FeesCollectionService feesCollectionService = new FeesCollectionService(request, response, standardActionAdapter);

        AddFeesCollectionDto dto = new AddFeesCollectionDto();
        dto.setStudentId(request.getParameter("studentIdDetails"));
        dto.setAmountPaying(request.getParameterValues("amountpaying"));
        dto.setFine(request.getParameterValues("fine"));
        dto.setStudentSfsIds(request.getParameterValues("studentsfsids"));
        dto.setPaymentMethod(request.getParameter("paymentmethod"));
        dto.setAckNo(request.getParameter("ackno"));
        dto.setTransferDate(request.getParameter("transferdate"));
        dto.setTransferBankName(request.getParameter("transferbankname"));
        dto.setChequeNo(request.getParameter("chequeno"));
        dto.setChequeDate(request.getParameter("chequedate"));
        dto.setChequeBankName(request.getParameter("chequebankname"));
        dto.setAcademicYear(request.getParameter("academicyear"));
        dto.setDateOfFeesDetails(request.getParameter("dateoffeesDetails"));
        dto.setClassAndSecDetails(request.getParameter("classandsecDetails"));

        Otherreceiptinfo otherreceiptinfo = feesCollectionService.addother(dto, httpSession.getAttribute(CURRENTACADEMICYEAR).toString(), httpSession.getAttribute(BRANCHID).toString(), httpSession.getAttribute(USERID).toString(), httpSession.getAttribute(USERNAME).toString());
        return otherreceiptinfo;
    }

    public void otherpreview(Otherreceiptinfo receiptInfo) {
        FeesCollectionService feesCollectionService = new FeesCollectionService(request, response, standardActionAdapter);

        DetailsResponseDto responseDto = feesCollectionService.otherpreview(receiptInfo, httpSession.getAttribute(CURRENTACADEMICYEAR).toString());
        httpSession.setAttribute("student", responseDto.getStudent());
        request.setAttribute("recieptdate", responseDto.getReceiptDate());
        request.setAttribute("recieptinfo", responseDto.getOtherReceiptInfo());
        request.setAttribute("feescatmap", responseDto.getFeeCatMap());
    }
}

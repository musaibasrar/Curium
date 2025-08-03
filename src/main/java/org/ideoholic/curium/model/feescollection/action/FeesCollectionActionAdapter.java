package org.ideoholic.curium.model.feescollection.action;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.feescollection.dto.AddFeesCollectionDto;
import org.ideoholic.curium.model.feescollection.dto.CancelledReceiptsDto;
import org.ideoholic.curium.model.feescollection.dto.CancelledReceiptsResponseDto;
import org.ideoholic.curium.model.feescollection.dto.DetailsResponseDto;
import org.ideoholic.curium.model.feescollection.dto.FeesCategoryDto;
import org.ideoholic.curium.model.feescollection.dto.FeesCategoryResponseDto;
import org.ideoholic.curium.model.feescollection.dto.FeesDashboardResponseDto;
import org.ideoholic.curium.model.feescollection.dto.FeesDetailsResponseDto;
import org.ideoholic.curium.model.feescollection.dto.FeesReportDto;
import org.ideoholic.curium.model.feescollection.dto.Otherreceiptinfo;
import org.ideoholic.curium.model.feescollection.dto.PreviewDto;
import org.ideoholic.curium.model.feescollection.dto.PreviewResponseDto;
import org.ideoholic.curium.model.feescollection.dto.Receiptinfo;
import org.ideoholic.curium.model.feescollection.dto.StampFeeDto;
import org.ideoholic.curium.model.feescollection.dto.StampFeeResponseDto;
import org.ideoholic.curium.model.feescollection.dto.StudentFeesDto;
import org.ideoholic.curium.model.feescollection.dto.StudentFeesReport;
import org.ideoholic.curium.model.feescollection.dto.Studentotherfeesreport;
import org.ideoholic.curium.model.feescollection.service.FeesCollectionService;
import org.ideoholic.curium.model.std.action.StandardActionAdapter;
import org.ideoholic.curium.model.std.dto.ClassesHierarchyDto;
import org.ideoholic.curium.model.std.dto.Classsec;
import org.ideoholic.curium.model.std.service.StandardService;
import org.ideoholic.curium.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FeesCollectionActionAdapter {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private HttpSession httpSession;

    @Autowired
	private StandardService standardService;
    
    @Autowired
    private FeesCollectionService feesCollectionService;
    
	@Autowired
	private StandardActionAdapter standardActionAdapter;

    public void getFeesReport() {

        FeesReportDto dto = new FeesReportDto();
        dto.setAcademicYear(request.getParameter("academicyear"));
        dto.setFeesCat(request.getParameterValues("feescategory"));
        dto.setAddClass(request.getParameterValues("classsearch"));
        dto.setStudentType(request.getParameter("studenttype"));
        ResultResponse resultResponse = feesCollectionService.getFeesReport(dto, httpSession.getAttribute(Constants.BRANCHID).toString(), httpSession.getAttribute(Constants.CURRENTACADEMICYEAR).toString());
        httpSession.setAttribute("studentfeesreportlist", resultResponse.getResultList());
    }

    public void undoFeesReceipt() {

        String receiptId = request.getParameter("id");
        
        ResultResponse resultResponse = feesCollectionService.undoFeesReceipt(receiptId, httpSession.getAttribute(Constants.CURRENTACADEMICYEAR).toString());
        request.setAttribute("cancelreceiptresult", resultResponse.isSuccess());
    }

    public void viewCancelledReceipts() {

        CancelledReceiptsDto dto = new CancelledReceiptsDto();
        dto.setBranchId(request.getParameter("selectedbranchid"));
        dto.setToDate(request.getParameter("todate"));
        dto.setFromDate(request.getParameter("fromdate"));
        dto.setOneDay(request.getParameter("oneday"));

        CancelledReceiptsResponseDto responseDto = feesCollectionService.viewCancelledReceipts(dto, httpSession.getAttribute(Constants.BRANCHID).toString());
        httpSession.setAttribute("feesdetailsbranchname", responseDto.getFeesDetailsBranchName());
        httpSession.setAttribute("branchname", responseDto.getBranchName());
        httpSession.setAttribute("searchfeesdetailslistcancelled", responseDto.getFeesDetailsList());
        httpSession.setAttribute("sumofdetailsfeescancelled", responseDto.getSumOfFees());
        httpSession.setAttribute("dayonecancel", responseDto.getDayOneCancel());
        httpSession.setAttribute("datefromcancel", responseDto.getDateFromCancel());
        httpSession.setAttribute("datetocancel", responseDto.getDateToCancel());
    }

    public void getStampFees() {

        StampFeeDto dto = new StampFeeDto();
        dto.setAcademicYear(request.getParameter("academicyear"));
        dto.setId(request.getParameter("studentId"));
        dto.setStudentName(request.getParameter("studentname"));
        dto.setAdmissionNo(request.getParameter("admissionno"));
        dto.setClassAndSec(request.getParameter("classandsec"));
        dto.setStudentId(request.getParameter("studentId"));
        dto.setDateOfFees(request.getParameter("dateoffees"));
        dto.setFatherName(request.getParameter("fathername"));

        StampFeeResponseDto responseDto = feesCollectionService.getStampFees(dto, httpSession.getAttribute(Constants.CURRENTACADEMICYEAR).toString());
        request.setAttribute("studentfeesdetailspreviousyear", responseDto.getPreviousYearFeesMap());
        request.setAttribute("previousyear", responseDto.getPreviousYear());
        request.setAttribute("studentfeesdetails", responseDto.getFeesMap());
        request.setAttribute("studentNameDetails", responseDto.getStudentNameDetails());
        request.setAttribute("admnoDetails", responseDto.getAdmNoDetails());
        request.setAttribute("classandsecDetails", responseDto.getClassAndSecDetails());
        request.setAttribute("studentIdDetails", responseDto.getStudentIdDetails());
        request.setAttribute("dateoffeesDetails", responseDto.getDateOfFeesDetails());
        request.setAttribute("fatherNameDetails", responseDto.getFatherName());
    }

    public FeesDetailsResponseDto getFeesDetails() {

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
        httpSession.setAttribute(Constants.CURRENTACADEMICYEAR, responseDto.getCurrentAcademicYear());
       return  responseDto;
    }

    public void previewFeesDetails() {

        String sId = request.getParameter("sid");
        String receiptNo = request.getParameter("id");

        DetailsResponseDto responseDto = feesCollectionService.previewFeesDetails(sId, receiptNo, httpSession.getAttribute(Constants.CURRENTACADEMICYEAR).toString());
        httpSession.setAttribute("student", responseDto.getStudent());
        request.setAttribute("recieptdate", responseDto.getReceiptDate());
        request.setAttribute("recieptinfo", responseDto.getReceiptInfo());
        request.setAttribute("feescatmap", responseDto.getFeeCatMap());
        request.setAttribute("duplicate", responseDto.getDuplicate());
    }

    public void previewDetails() {

        String receiptNumber = request.getParameter("id");
        String duplicate = request.getParameter("duplicate");

        DetailsResponseDto responseDto = feesCollectionService.previewDetails(receiptNumber, duplicate, httpSession.getAttribute(Constants.CURRENTACADEMICYEAR).toString());
        httpSession.setAttribute("parents", responseDto.getParents());
        httpSession.setAttribute("student", responseDto.getStudent());
        request.setAttribute("recieptdate", responseDto.getReceiptDate());
        request.setAttribute("recieptinfo", responseDto.getReceiptInfo());
        request.setAttribute("feescatmap", responseDto.getFeeCatMap());
        request.setAttribute("duplicate", responseDto.getDuplicate());
        request.setAttribute("user", responseDto.getUserLogin());
        httpSession.setAttribute("grandTotal", responseDto.getGrandTotal());
        request.setAttribute("narrationreceipt", responseDto.getNarration());
    }

    public void getotherStampFees() {

        StampFeeDto dto = new StampFeeDto();
        dto.setAcademicYear(request.getParameter("academicyear"));
        dto.setStudentId(request.getParameter("studentId"));
        dto.setStudentName(request.getParameter("studentname"));
        dto.setAdmissionNo(request.getParameter("admissionno"));
        dto.setClassAndSec(request.getParameter("classandsec"));
        dto.setDateOfFees(request.getParameter("dateoffees"));
        StampFeeResponseDto responseDto = feesCollectionService.getotherStampFees(dto, httpSession.getAttribute(Constants.CURRENTACADEMICYEAR).toString());
        request.setAttribute("studentotherfeesdetails", responseDto.getOtherFeesMap());
        request.setAttribute("studentNameDetails", responseDto.getStudentNameDetails());
        request.setAttribute("admnoDetails", responseDto.getAdmNoDetails());
        request.setAttribute("classandsecDetails", responseDto.getClassAndSecDetails());
        request.setAttribute("studentIdDetails", responseDto.getStudentIdDetails());
        request.setAttribute("dateoffeesDetails", responseDto.getDateOfFeesDetails());
    }

    public FeesDetailsResponseDto getotherFeesDetails() {

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
        httpSession.setAttribute(Constants.CURRENTACADEMICYEAR, responseDto.getCurrentAcademicYear());
        return responseDto;
    }

    public void getotherFeesReport() {

        FeesReportDto dto = new FeesReportDto();
        dto.setAcademicYear(request.getParameter("academicyear"));
        dto.setFeesCat(request.getParameterValues("otherfeescategory"));
        dto.setAddClass(request.getParameterValues("classsearch"));

        ResultResponse resultResponse = feesCollectionService.getotherFeesReport(dto, httpSession.getAttribute(Constants.BRANCHID).toString(), httpSession.getAttribute(Constants.CURRENTACADEMICYEAR).toString());
        httpSession.setAttribute("studentotherfeesreportlist", resultResponse.getResultList());
    }

    public void otherpreviewDetails() {

        String receiptNumber = request.getParameter("id");
        String duplicate = request.getParameter("duplicate");

        DetailsResponseDto responseDto = feesCollectionService.otherpreviewDetails(receiptNumber, duplicate, httpSession.getAttribute(Constants.CURRENTACADEMICYEAR).toString());
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

        CancelledReceiptsDto dto = new CancelledReceiptsDto();
        dto.setBranchId(request.getParameter("selectedbranchid"));
        dto.setToDate(request.getParameter("todate"));
        dto.setFromDate(request.getParameter("fromdate"));
        dto.setOneDay(request.getParameter("oneday"));
        dto.setModeOfPayment(request.getParameter("modeofpayment"));

        CancelledReceiptsResponseDto responseDto = feesCollectionService.searchOtherFeesCollection(dto, httpSession.getAttribute(Constants.BRANCHID).toString());
        httpSession.setAttribute("feesdetailsbranchname", responseDto.getFeesDetailsBranchName());
        httpSession.setAttribute("branchname", responseDto.getBranchName());
        httpSession.setAttribute("dayone", responseDto.getDayOneCancel());
        httpSession.setAttribute("datefrom", responseDto.getDateFromCancel());
        httpSession.setAttribute("dateto", responseDto.getDateToCancel());
        httpSession.setAttribute("searchotherfeesdetailslist", responseDto.getFeesMap());
        httpSession.setAttribute("sumofotherdetailsfees", responseDto.getSumOfFees());
    }

    public void previewOtherFeesDetails() {

        String sId= request.getParameter("sid");
        String receiptNo = request.getParameter("id");

        DetailsResponseDto responseDto = feesCollectionService.previewOtherFeesDetails(sId, receiptNo, httpSession.getAttribute(Constants.CURRENTACADEMICYEAR).toString());
        httpSession.setAttribute("student", responseDto.getStudent());
        request.setAttribute("recieptdate", responseDto.getReceiptDate());
        request.setAttribute("recieptinfo", responseDto.getOtherReceiptInfo());
        request.setAttribute("feescatmap", responseDto.getFeeCatMap());
        request.setAttribute("duplicate", responseDto.getDuplicate());
    }

    public void cancelOtherFeesReceipt() {

        String receiptId = request.getParameter("receiptid");
        String journalId = request.getParameter("journalid");
        String feesReceiptId = request.getParameter("id");

        ResultResponse resultResponse = feesCollectionService.cancelOtherFeesReceipt(receiptId, journalId, feesReceiptId, httpSession.getAttribute(Constants.CURRENTACADEMICYEAR).toString());
        request.setAttribute("cancelreceiptresult", resultResponse.isSuccess());
    }

    public void getFeesDetailsDashBoard() {

        ResultResponse resultResponse = standardService.viewClasses(httpSession.getAttribute(Constants.BRANCHID).toString());
        httpSession.setAttribute("classdetailslist", resultResponse.getResultList());

        ClassesHierarchyDto dto = ClassesHierarchyDto.builder()
            .selectedBranchId(request.getParameter("selectedbranchid"))
            .classsecList((List<Classsec>)httpSession.getAttribute("classdetailslist"))
            .build();

        FeesDashboardResponseDto responseDto = feesCollectionService.getFeesDetailsDashBoard(dto, httpSession.getAttribute(Constants.BRANCHID).toString(), httpSession.getAttribute(Constants.CURRENTACADEMICYEAR).toString());
        request.setAttribute("totalFeesAmountDashBoard", responseDto.getTotalFeesAmount());
        request.setAttribute("totalPaidAmountDashBoard", responseDto.getTotalPaidAmount());
        request.setAttribute("totalDueAmountDashBoard", responseDto.getTotalDueAmount());
        httpSession.setAttribute("feesdetailsbranchname", responseDto.getBranchIdName());
        httpSession.setAttribute("branchname", responseDto.getBranchName());
        httpSession.setAttribute("sumOfFeesDaily", responseDto.getSumOfFeesDaily());
        httpSession.setAttribute("sumOfFeesMonthly", responseDto.getSumOfFeesMonthly());
        httpSession.setAttribute("Currentmonth", responseDto.getCurrentMonth());
        httpSession.setAttribute("classdetailslist", responseDto.getClasssecList());

    }

    public void getDefaultersReport() {

        FeesReportDto dto = new FeesReportDto();
        dto.setAcademicYear(request.getParameter("academicyear"));
        dto.setFeesCat(request.getParameterValues("feescategory"));
        dto.setAddClass(request.getParameterValues("classsearch"));

        ResultResponse resultResponse = feesCollectionService.getDefaultersReport(dto, httpSession.getAttribute(Constants.BRANCHID).toString(), httpSession.getAttribute(Constants.CURRENTACADEMICYEAR).toString());
        httpSession.setAttribute("studentfeesreportlist", resultResponse.getResultList());
    }

    public void getFeesReportDue() {

        FeesReportDto dto = new FeesReportDto();
        dto.setAcademicYear(request.getParameter("academicyear"));
        dto.setFeesCat(request.getParameterValues("feescategory"));
        dto.setAddClass(request.getParameterValues("classsearch"));

        ResultResponse resultResponse = feesCollectionService.getFeesReportDue(dto, httpSession.getAttribute(Constants.BRANCHID).toString(), httpSession.getAttribute(Constants.CURRENTACADEMICYEAR).toString());
        httpSession.setAttribute("studentfeesreportlist", resultResponse.getResultList());
    }

    public void getFeesStampDueReport() {

        FeesReportDto dto = new FeesReportDto();
        dto.setAcademicYear(request.getParameter("academicyear"));
        dto.setAddClass(request.getParameterValues("classsearch"));

        ResultResponse resultResponse = feesCollectionService.getFeesStampDueReport(dto, httpSession.getAttribute(Constants.BRANCHID).toString(), httpSession.getAttribute(Constants.CURRENTACADEMICYEAR).toString());
        httpSession.setAttribute("studentfeesreportlist", resultResponse.getResultList());
    }

    public boolean printOtherDataForFees() {

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

        CancelledReceiptsDto dto = new CancelledReceiptsDto();
        dto.setBranchId(request.getParameter("selectedbranchid"));
        dto.setToDate(request.getParameter("todate"));
        dto.setFromDate(request.getParameter("fromdate"));
        dto.setOneDay(request.getParameter("oneday"));

        CancelledReceiptsResponseDto responseDto = feesCollectionService.viewCancelledOtherFeesReceipts(dto, httpSession.getAttribute(Constants.BRANCHID).toString());
        httpSession.setAttribute("feesdetailsbranchname", responseDto.getFeesDetailsBranchName());
        httpSession.setAttribute("branchname", responseDto.getBranchName());
        httpSession.setAttribute("dayonecancel", responseDto.getDayOneCancel());
        httpSession.setAttribute("datefromcancel", responseDto.getDateFromCancel());
        httpSession.setAttribute("datetocancel", responseDto.getDateToCancel());
        httpSession.setAttribute("searchfeesdetailslistcancelled", responseDto.getOtherfeesDetailsList());
        httpSession.setAttribute("sumofdetailsfeescancelled", responseDto.getSumOfFees());
    }

    public void getFeesCollectionCategory() {

        FeesCategoryDto dto = new FeesCategoryDto();
        dto.setBranchId(request.getParameter("selectedbranchid"));
        dto.setToDate(request.getParameter("todate"));
        dto.setFromDate(request.getParameter("fromdate"));
        dto.setToDateOtherFees(request.getParameter("todate"));
        dto.setFromDateOtherFees(request.getParameter("fromdate"));
        dto.setOneDayOtherFees(request.getParameter("oneday"));

        FeesCategoryResponseDto responseDto = feesCollectionService.getFeesCollectionCategory(dto, httpSession.getAttribute(Constants.BRANCHID).toString());
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

        ResultResponse resultResponse = feesCollectionService.downlaod();

        return resultResponse.isSuccess();
    }

    public void exportDataForStudentsOtherFeesReport() {

        StudentFeesDto dto = new StudentFeesDto();
        dto.setStudentotherfeesreportList((List<Studentotherfeesreport>) httpSession.getAttribute("studentotherfeesreportlist"));

        ResultResponse resultResponse = feesCollectionService.exportDataForStudentsOtherFeesReport(dto);
    }

    public ResultResponse printFeesDueHeadWiseReport() {

        StudentFeesDto dto = new StudentFeesDto();
        dto.setStudentFeesReportList((List<StudentFeesReport>) httpSession.getAttribute("studentfeesreportlist"));

        return feesCollectionService.printFeesDueHeadWiseReport(dto);
    }

    public void cancelFeesReceipt() {

        String receiptId = request.getParameter("receiptid");
        String journalId = request.getParameter("journalid");
        String feesReceiptId = request.getParameter("id");

        ResultResponse resultResponse = feesCollectionService.cancelFeesReceipt(receiptId, journalId, feesReceiptId, httpSession.getAttribute(Constants.CURRENTACADEMICYEAR).toString());
        request.setAttribute("cancelreceiptresult", resultResponse.isSuccess());
    }

    public ResultResponse exportDataForStudentsFeesReport() {

        StudentFeesDto dto = new StudentFeesDto();
        dto.setStudentFeesReportList((List<StudentFeesReport>) httpSession.getAttribute("studentfeesreportlist"));

        return feesCollectionService.exportDataForStudentsFeesReport(dto);
    }

    public Receiptinfo add() {

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
        dto.setNarrationReceipt(request.getParameter("narrationreceipt"));

        dto.setSid(request.getParameter("studentId"));
        dto.setFeesIDS(request.getParameterValues("feesIDS"));
        dto.setFeesMonths(request.getParameterValues("feesQuantities"));
        dto.setFeesAmounts(request.getParameterValues("feesAmounts"));
        dto.setFeesCat(request.getParameterValues("feesNames"));


        Receiptinfo receiptinfo = feesCollectionService.add(dto, httpSession.getAttribute(Constants.CURRENTACADEMICYEAR).toString(), httpSession.getAttribute(Constants.BRANCHID).toString(), httpSession.getAttribute(Constants.USERID).toString(), httpSession.getAttribute(Constants.USERNAME).toString());
        return receiptinfo;
    }

    public PreviewResponseDto preview() {
        PreviewDto dto = new PreviewDto();
        dto.setSid(Long.parseLong(request.getParameter("sid")));
        dto.setId(Integer.parseInt(request.getParameter("id")));
        dto.setIdFees(Long.parseLong(request.getParameter("id")));

        PreviewResponseDto result = feesCollectionService.preview(dto);
        httpSession.setAttribute("feescollection", result.getFeesCollections());
        httpSession.setAttribute("student", result.getStudent());
        httpSession.setAttribute("parents", result.getParents());
        httpSession.setAttribute("grandTotal", result.getGrandTotal()+" "+"Only");
        httpSession.setAttribute("feesdetails", result.getFeesdetails());

        return result;
    }
    
    public void preview(Receiptinfo receiptInfo) {
        DetailsResponseDto responseDto = feesCollectionService.preview(receiptInfo, httpSession.getAttribute(Constants.CURRENTACADEMICYEAR).toString());
        httpSession.setAttribute("student", responseDto.getStudent());
        request.setAttribute("recieptdate", responseDto.getReceiptDate());
        request.setAttribute("recieptinfo", responseDto.getReceiptInfo());
        request.setAttribute("feescatmap", responseDto.getFeeCatMap());
        request.setAttribute("narrationreceipt", responseDto.getNarration());
    }

    public Otherreceiptinfo addother() {

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

        Otherreceiptinfo otherreceiptinfo = feesCollectionService.addother(dto, httpSession.getAttribute(Constants.CURRENTACADEMICYEAR).toString(), httpSession.getAttribute(Constants.BRANCHID).toString(), httpSession.getAttribute(Constants.USERID).toString(), httpSession.getAttribute(Constants.USERNAME).toString());
        return otherreceiptinfo;
    }

    
    public void otherpreview(Otherreceiptinfo receiptInfo) {
        DetailsResponseDto responseDto = feesCollectionService.otherpreview(receiptInfo, httpSession.getAttribute(Constants.CURRENTACADEMICYEAR).toString());
        httpSession.setAttribute("student", responseDto.getStudent());
        request.setAttribute("recieptdate", responseDto.getReceiptDate());
        request.setAttribute("recieptinfo", responseDto.getOtherReceiptInfo());
        request.setAttribute("feescatmap", responseDto.getFeeCatMap());
    }

	public boolean readFileForFees(MultipartFile uploadedFiles) throws FileNotFoundException, IOException {
		ResultResponse result = feesCollectionService.readFileForFees(uploadedFiles,
                httpSession.getAttribute(Constants.CURRENTACADEMICYEAR).toString(), httpSession.getAttribute(Constants.BRANCHID).toString(), httpSession.getAttribute(Constants.USERID).toString(), httpSession.getAttribute(Constants.USERNAME).toString()
        );
		return result.isSuccess();
	}

	public Receiptinfo feesPaymentTypeModify() {

        AddFeesCollectionDto dto = new AddFeesCollectionDto();
        dto.setStudentId(request.getParameter("receiptnumber"));
        dto.setPaymentMethod(request.getParameter("paymentmethod"));
        dto.setAckNo(request.getParameter("ackno"));
        dto.setTransferDate(request.getParameter("transferdate"));
        dto.setTransferBankName(request.getParameter("transferbankname"));
        dto.setChequeNo(request.getParameter("chequeno"));
        dto.setChequeDate(request.getParameter("chequedate"));
        dto.setChequeBankName(request.getParameter("chequebankname"));
        
        Receiptinfo receiptinfo = feesCollectionService.feesPaymentTypeModify(dto, httpSession.getAttribute(Constants.CURRENTACADEMICYEAR).toString(), httpSession.getAttribute(Constants.BRANCHID).toString(), httpSession.getAttribute(Constants.USERID).toString(), httpSession.getAttribute(Constants.USERNAME).toString());
        return receiptinfo;
    }

	public boolean readFileForOtherFees(MultipartFile uploadedFiles) throws FileNotFoundException, IOException {
		return feesCollectionService.readFileForOtherFees(uploadedFiles);
	}
	
	
}
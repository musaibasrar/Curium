package org.ideoholic.curium.model.feescollection.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.exceptions.CustomErrorMessage;
import org.ideoholic.curium.exceptions.CustomResponseException;
import org.ideoholic.curium.model.feescategory.dto.CancelFeesReceiptDto;
import org.ideoholic.curium.model.feescategory.service.FeesService;
import org.ideoholic.curium.model.feescollection.dto.AddFeesCollectionDto;
import org.ideoholic.curium.model.feescollection.dto.CancelFeesReceiptResponseDto;
import org.ideoholic.curium.model.feescollection.dto.CancelledReceiptsDto;
import org.ideoholic.curium.model.feescollection.dto.CancelledReceiptsResponseDto;
import org.ideoholic.curium.model.feescollection.dto.DetailsResponseDto;
import org.ideoholic.curium.model.feescollection.dto.FeesCategoryDto;
import org.ideoholic.curium.model.feescollection.dto.FeesCategoryResponseDto;
import org.ideoholic.curium.model.feescollection.dto.FeesDashboardResponseDto;
import org.ideoholic.curium.model.feescollection.dto.FeesDetailsResponseDto;
import org.ideoholic.curium.model.feescollection.dto.FeesReportDto;
import org.ideoholic.curium.model.feescollection.dto.Feescollection;
import org.ideoholic.curium.model.feescollection.dto.OtherStampFeesResponseDto;
import org.ideoholic.curium.model.feescollection.dto.Otherreceiptinfo;
import org.ideoholic.curium.model.feescollection.dto.PreviewDto;
import org.ideoholic.curium.model.feescollection.dto.Receiptinfo;
import org.ideoholic.curium.model.feescollection.dto.StampFeeDto;
import org.ideoholic.curium.model.feescollection.dto.StampFeeResponseDto;
import org.ideoholic.curium.model.feescollection.dto.StampFeesResponseDto;
import org.ideoholic.curium.model.feescollection.dto.StudentFeesDto;
import org.ideoholic.curium.model.feescollection.service.FeesCollectionService;
import org.ideoholic.curium.model.parents.dto.ParentListResponseDto;
import org.ideoholic.curium.model.std.dto.ClassesHierarchyDto;
import org.ideoholic.curium.model.std.service.StandardService;
import org.ideoholic.curium.model.user.dto.SearchByDateDto;
import org.ideoholic.curium.model.user.dto.SearchByDateResponseDto;
import org.ideoholic.curium.model.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeesCollectionApiActionImpl implements FeesCollectionApiAction {
    @Autowired
    private FeesCollectionService feesCollectionService;

    @Autowired
    private StandardService standardService;

    @Autowired
    private FeesService feesService;

    @Autowired
    private UserService userService;

    public ResponseEntity<ResultResponse> searchFeesReport(FeesReportDto dto, String branchId, String currentAcademicYear) {
       ResultResponse result =  feesCollectionService.getFeesReport(dto,branchId,currentAcademicYear);
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<ResultResponse> undoFeesReceipt(String receiptId, String currentAcademicYear) {
       ResultResponse result = feesCollectionService.undoFeesReceipt(receiptId,currentAcademicYear);
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<CancelledReceiptsResponseDto> viewCancelledReceipts(CancelledReceiptsDto dto, String branchId) {
        CancelledReceiptsResponseDto result = feesCollectionService.viewCancelledReceipts(dto,branchId);
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<CancelFeesReceiptResponseDto> cancelFeesReceipt(@RequestBody CancelFeesReceiptDto cancelFeesReceiptDto,String branchId,String currentAcademicYear) {
       CancelFeesReceiptResponseDto result = new CancelFeesReceiptResponseDto();
        ResultResponse resultResponse = feesCollectionService.cancelFeesReceipt(cancelFeesReceiptDto.getReceiptId(),cancelFeesReceiptDto.getJournalId(),cancelFeesReceiptDto.getFeesReceiptId(),currentAcademicYear);
        result.setSuccess(resultResponse.isSuccess());
        SearchByDateResponseDto searchByDateResponseDto = userService.searchByDate(SearchByDateDto.builder()
        		.branchId(cancelFeesReceiptDto.getSelectedBranchId())
        		.toDate(cancelFeesReceiptDto.getToDate())
        		.fromDate(cancelFeesReceiptDto.getFromDate())
        		.oneDay(cancelFeesReceiptDto.getOneDay())
        		.modeOfPayment(cancelFeesReceiptDto.getModeOfPayment())
        		.build(),
        		branchId, cancelFeesReceiptDto.getDayOne(), cancelFeesReceiptDto.getDayFrom(),cancelFeesReceiptDto.getDateTo());
        result.setFeesDetailsBranchName(searchByDateResponseDto.getFeesDetailsBranchName());
        result.setBranchName(searchByDateResponseDto.getBranchName());
        result.setSumOfFees(searchByDateResponseDto.getSumOfOnlyFee());
        result.setSuccess(result.isSuccess() && searchByDateResponseDto.isSuccess());
        
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<StampFeesResponseDto> stampFees(StampFeeDto stampFeeDto, String branchId, String currentAcademicYear) {
        StampFeesResponseDto result = new StampFeesResponseDto();

        StampFeeResponseDto stampFeeResponseDto= feesCollectionService.getStampFees(stampFeeDto,currentAcademicYear);
        result.setPreviousYearFeesMap(stampFeeResponseDto.getPreviousYearFeesMap());
        result.setPreviousYear(stampFeeResponseDto.getPreviousYear());
        result.setFeesMap(stampFeeResponseDto.getFeesMap());
        result.setStudentNameDetails(stampFeeResponseDto.getStudentNameDetails());
        result.setAdmNoDetails(stampFeeResponseDto.getAdmNoDetails());
        result.setClassAndSecDetails(stampFeeResponseDto.getClassAndSecDetails());
        result.setStudentIdDetails(stampFeeResponseDto.getStudentIdDetails());
        result.setDateOfFeesDetails(stampFeeResponseDto.getDateOfFeesDetails());
        result.setSuccess(stampFeeResponseDto.isSuccess());

        FeesReportDto feesReportDto = new FeesReportDto();
        feesReportDto.setStudentId(stampFeeDto.getStudentId());
        feesReportDto.setAcademicYear(stampFeeDto.getAcademicYear());
        
        FeesDetailsResponseDto feesDetailsResponseDto = feesCollectionService.getFeesDetails(feesReportDto);
        result.setFeesStructure(feesDetailsResponseDto.getFeesStructure());
        result.setTotalSum(feesDetailsResponseDto.getTotalSum());
        result.setDueAmount(feesDetailsResponseDto.getDueAmount());
        result.setTotalFeesAmount(feesDetailsResponseDto.getTotalFeesAmount());
        result.setAcademicPerYear(feesDetailsResponseDto.getAcademicPerYear());
        result.setCurrentAcademicYear(feesDetailsResponseDto.getCurrentAcademicYear());
        result.setSuccess(result.isSuccess() & feesDetailsResponseDto.isSuccess());

        ResultResponse resultResponse = standardService.viewClasses(branchId);
        result.setClassSecList(resultResponse.getResultList());
        result.setSuccess(result.isSuccess() & resultResponse.isSuccess());

        ParentListResponseDto parentListResponseDto = feesService.viewAllStudentsList(branchId);
        result.setParentsList(parentListResponseDto.getParentsList());
        result.setSuccess(result.isSuccess() & parentListResponseDto.isSuccess());

        return ResponseEntity.ok(result);
    }

    public ResponseEntity<DetailsResponseDto> viewDetails(String sId, String strReceiptNo, String currentAcademicYear) {
        DetailsResponseDto result = feesCollectionService.previewFeesDetails(sId,strReceiptNo,currentAcademicYear);
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<DetailsResponseDto> printReceipt(String strReceiptNumber, String duplicate, String currentAcademicYear) {
        DetailsResponseDto result = feesCollectionService.previewDetails(strReceiptNumber,duplicate,currentAcademicYear);

        return ResponseEntity.ok(result);
    }
    
    public ResponseEntity<Feescollection> feesAdd(AddFeesCollectionDto dto , String currentAcademicYear, String branchId, String userId, String userName) {
    	Receiptinfo receiptInfo = feesCollectionService.add(dto, currentAcademicYear, branchId, userId, userName);
    	if(receiptInfo.getReceiptnumber()!=null){
            //under implementation
            /*SmsService smsSerivce = new SmsService(request, response);
              smsSerivce.sendSMS(DataUtil.emptyString(request.getParameter("contactnumber")),
              "We have received Rs."+DataUtil.emptyString(request.getParameter("grandTotalAmount"))+" towards fees collection.");*/
    		feesCollectionService.preview(PreviewDto.builder()
    				.sid(Integer.parseInt(dto.getSid()))
    				.id(Integer.parseInt(dto.getId()))
    				.idFees(Integer.parseInt(dto.getIdFees()))
    				.build());
    	}else{
            throw new CustomResponseException(CustomErrorMessage.ERROR);
        }
		return null;
    }

    public ResponseEntity<ResultResponse> exportDataForStudentsFeesReport(StudentFeesDto dto) {
       ResultResponse result  = feesCollectionService.exportDataForStudentsFeesReport(dto);
        return ResponseEntity.ok(result);
    }


    public ResponseEntity<ResultResponse> download() {
        ResultResponse result = feesCollectionService.downlaod();
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.EXPORTFAILURE);
    }

    public ResponseEntity<ResultResponse> searchFeesStampDueReport(FeesReportDto dto, String branchId, String currentAcademicYear) {
       ResultResponse result = feesCollectionService.getFeesStampDueReport(dto,branchId,currentAcademicYear);
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<DetailsResponseDto> printFeesReceipt(String receiptNumber, String duplicate, String currentAcademicYear) {
        DetailsResponseDto result = feesCollectionService.previewDetails(receiptNumber,duplicate,currentAcademicYear);
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<OtherStampFeesResponseDto> otherStampFees(StampFeeDto stampFeeDto, String currentAcademicYear,String branchId) {
       OtherStampFeesResponseDto result = new OtherStampFeesResponseDto();

        StampFeeResponseDto stampFeeResponseDto = feesCollectionService.getotherStampFees(stampFeeDto,currentAcademicYear);
        result.setOtherFeesMap(stampFeeResponseDto.getOtherFeesMap());
        result.setStudentNameDetails(stampFeeResponseDto.getStudentNameDetails());
        result.setAdmNoDetails(stampFeeResponseDto.getAdmNoDetails());
        result.setClassAndSecDetails(stampFeeResponseDto.getClassAndSecDetails());
        result.setStudentIdDetails(stampFeeResponseDto.getStudentIdDetails());
        result.setDateOfFeesDetails(stampFeeResponseDto.getDateOfFeesDetails());
        result.setSuccess(stampFeeResponseDto.isSuccess());

        FeesReportDto feesReportDto = new FeesReportDto();
        feesReportDto.setStudentId(stampFeeDto.getStudentId());
        feesReportDto.setAcademicYear(stampFeeDto.getAcademicYear());
        FeesDetailsResponseDto feesDetailsResponseDto = feesCollectionService.getotherFeesDetails(feesReportDto);
        result.setOtherFeesStructure(feesDetailsResponseDto.getOtherFeesStructure());
        result.setTotalSum(feesDetailsResponseDto.getTotalSum());
        result.setDueAmount(feesDetailsResponseDto.getDueAmount());
        result.setTotalFeesAmount(feesDetailsResponseDto.getTotalFeesAmount());
        result.setAcademicPerYear(feesDetailsResponseDto.getAcademicPerYear());
        result.setCurrentAcademicYear(feesReportDto.getAcademicYear());
        result.setSuccess(result.isSuccess() & feesDetailsResponseDto.isSuccess());

        ResultResponse resultResponse = standardService.viewClasses(branchId);
        result.setClassSecList(resultResponse.getResultList());
        result.setSuccess(result.isSuccess() & resultResponse.isSuccess());

        ParentListResponseDto parentListResponseDto = feesService.viewAllStudentsList(branchId);
        result.setParentsList(parentListResponseDto.getParentsList());
        result.setSuccess(result.isSuccess() & parentListResponseDto.isSuccess());

        return ResponseEntity.ok(result);
    }

    public ResponseEntity<ResultResponse> othersearchFeesReport(FeesReportDto dto, String branchId, String currentAcademicYear) {
       ResultResponse result = feesCollectionService.getotherFeesReport(dto,branchId,currentAcademicYear);
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<DetailsResponseDto> otherprintReceipt(String receiptNumber, String duplicate, String currentAcademicYear) {
        DetailsResponseDto result = feesCollectionService.otherpreviewDetails(receiptNumber,duplicate,currentAcademicYear);

        return ResponseEntity.ok(result);
    }

    public ResponseEntity<Otherreceiptinfo> feesAddother(AddFeesCollectionDto dto, String currentAcademicYear, String branchId, String userId, String userName) {
        Otherreceiptinfo receiptInfo = feesCollectionService.addother(dto,currentAcademicYear,branchId,userId,userName);
        if(receiptInfo.getReceiptnumber()!=null){
            //under implementation
            /*SmsService smsSerivce = new SmsService(request, response);
            smsSerivce.sendSMS(DataUtil.emptyString(request.getParameter("contactnumber")),
            "We have received Rs."+DataUtil.emptyString(request.getParameter("grandTotalAmount"))+" towards fees collection.");*/
            feesCollectionService.otherpreview(receiptInfo, currentAcademicYear);
            return ResponseEntity.ok(receiptInfo);
        }else{
            throw new CustomResponseException(CustomErrorMessage.ERROR);
        }

    }

    public ResponseEntity<CancelledReceiptsResponseDto> searchOtherFeesCollection(CancelledReceiptsDto dto, String branchId) {
        CancelledReceiptsResponseDto result = feesCollectionService.searchOtherFeesCollection(dto,branchId);
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<DetailsResponseDto> viewOtherFeesDetails(String sId, String receiptNo, String currentAcademicYear) {
        DetailsResponseDto result = feesCollectionService.previewOtherFeesDetails(sId,receiptNo,currentAcademicYear);
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<CancelledReceiptsResponseDto> cancelOtherFeesReceipt(CancelledReceiptsDto cancelledReceiptsDto, String receiptId, String journalId, String feesReceiptId, String branchId, String currentAcademicYear) {
       feesCollectionService.cancelOtherFeesReceipt(receiptId,journalId,feesReceiptId,currentAcademicYear);
		return searchOtherFeesCollection(cancelledReceiptsDto, branchId);
    }

    public ResponseEntity<ResultResponse> exportDataForStudentsOtherFeesReport(StudentFeesDto dto) {
        ResultResponse result = feesCollectionService.exportDataForStudentsOtherFeesReport(dto);
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<FeesDashboardResponseDto> feesSummaryReport(ClassesHierarchyDto dto, String branchId, String currentAcademicYear) {
        FeesDashboardResponseDto result = feesCollectionService.getFeesDetailsDashBoard(dto,branchId,currentAcademicYear);
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<FeesCategoryResponseDto> searchByDateFeesCollectionCategory(FeesCategoryDto dto, String branchId) {
        FeesCategoryResponseDto result = feesCollectionService.getFeesCollectionCategory(dto,branchId);
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<FeesCategoryResponseDto> searchByDateFeesCollectionCategoryPrint(FeesCategoryDto dto, String branchId) {
        FeesCategoryResponseDto result = feesCollectionService.getFeesCollectionCategory(dto,branchId);
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<ResultResponse> searchFeesDueHeadWiseReport(FeesReportDto dto, String branchId, String currentAcademicYear) {
        ResultResponse result = feesCollectionService.getFeesReport(dto,branchId,currentAcademicYear);
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<ResultResponse> printFeesDueHeadWiseReport(StudentFeesDto dto) {
        ResultResponse result = feesCollectionService.printFeesDueHeadWiseReport(dto);
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<CancelledReceiptsResponseDto> printOtherFeesData(CancelledReceiptsDto dto) {
        CancelledReceiptsResponseDto result = feesCollectionService.printOtherDataForFees(dto);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }else{
            throw new CustomResponseException(CustomErrorMessage.ERROR);
        }

    }

    public ResponseEntity<ResultResponse> searchDefaultersReport(FeesReportDto dto, String branchId, String currentAcademicYear) {
       ResultResponse result = feesCollectionService.getDefaultersReport(dto,branchId,currentAcademicYear);
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<CancelledReceiptsResponseDto> viewCancelledOtherFeesReceipts(CancelledReceiptsDto dto, String branchId) {
        CancelledReceiptsResponseDto result = feesCollectionService.viewCancelledOtherFeesReceipts(dto,branchId);
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<ResultResponse> searchFeesReportDue(FeesReportDto dto, String branchId, String currentAcademicYear) {
        ResultResponse result = feesCollectionService.getFeesReportDue(dto,branchId,currentAcademicYear);
        return ResponseEntity.ok(result);
    }
}
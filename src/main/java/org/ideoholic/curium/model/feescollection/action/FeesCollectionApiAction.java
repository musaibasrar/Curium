package org.ideoholic.curium.model.feescollection.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.feescategory.dto.CancelFeesReceiptDto;
import org.ideoholic.curium.model.feescollection.dto.AddFeesCollectionDto;
import org.ideoholic.curium.model.feescollection.dto.CancelFeesReceiptResponseDto;
import org.ideoholic.curium.model.feescollection.dto.CancelledReceiptsDto;
import org.ideoholic.curium.model.feescollection.dto.CancelledReceiptsResponseDto;
import org.ideoholic.curium.model.feescollection.dto.DetailsResponseDto;
import org.ideoholic.curium.model.feescollection.dto.FeesCategoryDto;
import org.ideoholic.curium.model.feescollection.dto.FeesCategoryResponseDto;
import org.ideoholic.curium.model.feescollection.dto.FeesDashboardResponseDto;
import org.ideoholic.curium.model.feescollection.dto.FeesReportDto;
import org.ideoholic.curium.model.feescollection.dto.Feescollection;
import org.ideoholic.curium.model.feescollection.dto.OtherStampFeesResponseDto;
import org.ideoholic.curium.model.feescollection.dto.Otherreceiptinfo;
import org.ideoholic.curium.model.feescollection.dto.StampFeeDto;
import org.ideoholic.curium.model.feescollection.dto.StampFeesResponseDto;
import org.ideoholic.curium.model.feescollection.dto.StudentFeesDto;
import org.ideoholic.curium.model.std.dto.ClassesHierarchyDto;
import org.ideoholic.curium.util.Constants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/api/v1/feesCollection")
public interface FeesCollectionApiAction {

    @PostMapping("/searchFeesReport")
    ResponseEntity<ResultResponse> searchFeesReport(@RequestBody FeesReportDto dto, @RequestHeader(value = Constants.BRANCHID) String branchId, @RequestHeader(value = Constants.CURRENTACADEMICYEAR) String currentAcademicYear);

    @GetMapping("/undoFeesReceipt")
    ResponseEntity<ResultResponse> undoFeesReceipt(@RequestParam(value = "receiptId") String receiptId, @RequestHeader(value = Constants.CURRENTACADEMICYEAR) String currentAcademicYear);

    @PostMapping("/viewCancelledReceipts")
    ResponseEntity<CancelledReceiptsResponseDto> viewCancelledReceipts(@RequestBody CancelledReceiptsDto dto, @RequestHeader(value = Constants.BRANCHID) String branchId);


    @PostMapping("/cancelFeesReceipt")
    ResponseEntity<CancelFeesReceiptResponseDto> cancelFeesReceipt(@RequestBody CancelFeesReceiptDto cancelFeesReceiptDto, @RequestHeader(value = Constants.BRANCHID) String branchId, @RequestHeader(value = Constants.CURRENTACADEMICYEAR) String currentAcademicYear);


    @PostMapping("/stampFees")
    ResponseEntity<StampFeesResponseDto> stampFees(@RequestBody StampFeeDto stampFeeDto, @RequestHeader(value = Constants.BRANCHID) String branchId, @RequestHeader(value = Constants.CURRENTACADEMICYEAR) String currentAcademicYear);


    @GetMapping("/viewDetails")
    ResponseEntity<DetailsResponseDto> viewDetails(@RequestParam(value = "sid") String sId, @RequestParam(value = "receiptNo") String strReceiptNo, @RequestHeader(value = Constants.CURRENTACADEMICYEAR) String currentAcademicYear);


    @GetMapping("/printReceipt")
    ResponseEntity<DetailsResponseDto> printReceipt(@RequestParam(value = "receiptNumber") String strReceiptNumber, @RequestParam(value = "duplicate") String duplicate, @RequestHeader(value = Constants.CURRENTACADEMICYEAR) String currentAcademicYear);


    @PostMapping("/feesAdd")
    ResponseEntity<Feescollection> feesAdd(@RequestBody AddFeesCollectionDto dto, @RequestHeader(value = Constants.CURRENTACADEMICYEAR) String currentAcademicYear, @RequestHeader(value = Constants.BRANCHID) String branchId, @RequestHeader(value = Constants.USERID) String userId, @RequestHeader(value = Constants.USERNAME)String userName);
    
    @PostMapping("/exportDataForStudentsFeesReport")
    ResponseEntity<ResultResponse> exportDataForStudentsFeesReport(@RequestBody StudentFeesDto dto);


    @PostMapping("/download")
    ResponseEntity<ResultResponse> download();


    @PostMapping("/searchFeesStampDueReport")
    ResponseEntity<ResultResponse> searchFeesStampDueReport(@RequestBody FeesReportDto dto, @RequestHeader(value = Constants.BRANCHID) String branchId, @RequestHeader(value = Constants.CURRENTACADEMICYEAR) String currentAcademicYear);


    @GetMapping("/printFeesReceipt")
    ResponseEntity<DetailsResponseDto> printFeesReceipt(@RequestParam(value = "receiptNumber") String receiptNumber, @RequestParam(value = "duplicate") String duplicate, @RequestHeader(value = Constants.CURRENTACADEMICYEAR) String currentAcademicYear);


    @PostMapping("/otherStampFees")
    ResponseEntity<OtherStampFeesResponseDto> otherStampFees(@RequestBody StampFeeDto stampFeeDto, @RequestHeader(value = Constants.CURRENTACADEMICYEAR) String currentAcademicYear, @RequestHeader(value = Constants.BRANCHID) String branchId);


    @PostMapping("/othersearchFeesReport")
    ResponseEntity<ResultResponse> othersearchFeesReport(@RequestBody FeesReportDto dto, @RequestHeader(value = Constants.BRANCHID) String branchId, @RequestHeader(value = Constants.CURRENTACADEMICYEAR) String currentAcademicYear);


    @GetMapping("/otherprintReceipt")
    ResponseEntity<DetailsResponseDto> otherprintReceipt(@RequestParam(value = "receiptNumber") String receiptNumber, @RequestParam(value = "duplicate") String duplicate, @RequestHeader(value = Constants.CURRENTACADEMICYEAR) String currentAcademicYear);


    @PostMapping("/feesAddother")
    ResponseEntity<Otherreceiptinfo> feesAddother(@RequestBody AddFeesCollectionDto dto, @RequestHeader(value = Constants.CURRENTACADEMICYEAR) String currentAcademicYear, @RequestHeader(value = Constants.BRANCHID) String branchId, @RequestHeader(value = Constants.USERID) String userId, @RequestHeader(value = Constants.USERNAME) String userName);


    @PostMapping("/searchOtherFeesCollection")
    ResponseEntity<CancelledReceiptsResponseDto> searchOtherFeesCollection(@RequestBody CancelledReceiptsDto dto, @RequestHeader(value = Constants.BRANCHID) String branchId);


    @GetMapping("/viewOtherFeesDetails")
    ResponseEntity<DetailsResponseDto> viewOtherFeesDetails(@RequestParam(value = "sid") String sId, @RequestParam(value = "receiptNo") String receiptNo, @RequestHeader(value = Constants.CURRENTACADEMICYEAR) String currentAcademicYear);


    @PostMapping("/cancelOtherFeesReceipt")
    ResponseEntity<CancelledReceiptsResponseDto> cancelOtherFeesReceipt(@RequestBody CancelledReceiptsDto cancelledReceiptsDto, @RequestParam(value = "receiptId") String receiptId, @RequestParam(value = "journalId") String journalId, @RequestParam(value = "feesReceiptId") String feesReceiptId, @RequestHeader(value = Constants.BRANCHID) String branchId, @RequestHeader(value = Constants.CURRENTACADEMICYEAR) String currentAcademicYear);


    @PostMapping("/exportDataForStudentsOtherFeesReport")
    ResponseEntity<ResultResponse> exportDataForStudentsOtherFeesReport(@RequestBody StudentFeesDto dto);

    @PostMapping("/feesSummaryReport")
    ResponseEntity<FeesDashboardResponseDto> feesSummaryReport(@RequestBody ClassesHierarchyDto dto, @RequestHeader(value = Constants.BRANCHID) String branchId, @RequestHeader(value = Constants.CURRENTACADEMICYEAR) String currentAcademicYear);


    @PostMapping("/searchByDateFeesCollectionCategory")
    ResponseEntity<FeesCategoryResponseDto> searchByDateFeesCollectionCategory(@RequestBody FeesCategoryDto dto, @RequestHeader(value = Constants.BRANCHID) String branchId);


    @PostMapping("/searchByDateFeesCollectionCategoryPrint")
    ResponseEntity<FeesCategoryResponseDto> searchByDateFeesCollectionCategoryPrint(@RequestBody FeesCategoryDto dto, @RequestHeader(value = Constants.BRANCHID) String branchId);


    @PostMapping("/searchFeesDueHeadWiseReport")
    ResponseEntity<ResultResponse> searchFeesDueHeadWiseReport(@RequestBody FeesReportDto dto, @RequestHeader(value = Constants.BRANCHID) String branchId, @RequestHeader(value = Constants.CURRENTACADEMICYEAR) String currentAcademicYear);


    @PostMapping("/printFeesDueHeadWiseReport")
    ResponseEntity<ResultResponse> printFeesDueHeadWiseReport(@RequestBody StudentFeesDto dto);


    @PostMapping("/printOtherDataForFees")
    ResponseEntity<CancelledReceiptsResponseDto> printOtherFeesData(@RequestBody CancelledReceiptsDto dto);


    @PostMapping("/searchDefaultersReport")
    ResponseEntity<ResultResponse> searchDefaultersReport(@RequestBody FeesReportDto dto, @RequestHeader(value = Constants.BRANCHID) String branchId, @RequestHeader(value = Constants.CURRENTACADEMICYEAR) String currentAcademicYear);

    @PostMapping("/viewCancelledOtherFeesReceipts")
    ResponseEntity<CancelledReceiptsResponseDto> viewCancelledOtherFeesReceipts(@RequestBody CancelledReceiptsDto dto, @RequestHeader(value = Constants.BRANCHID) String branchId);


    @PostMapping("/searchFeesReportDue")
    ResponseEntity<ResultResponse> searchFeesReportDue(@RequestBody FeesReportDto dto, @RequestHeader(value = Constants.BRANCHID) String branchId, @RequestHeader(value = Constants.CURRENTACADEMICYEAR) String currentAcademicYear);

}
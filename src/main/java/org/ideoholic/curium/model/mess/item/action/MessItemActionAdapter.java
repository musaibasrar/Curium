package org.ideoholic.curium.model.mess.item.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.mess.item.dto.*;
import org.ideoholic.curium.model.mess.item.service.MessItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Service
public class MessItemActionAdapter {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private HttpSession httpSession;
    
    @Autowired
    private MessItemsService messItemsService;
    
    private String BRANCHID = "branchid";
    private String USERID = "userloginid";

    public void generateStockReceivedReport() {
        StockReportDto dto = new StockReportDto();
        dto.setFromDate(request.getParameter("transactiondatefrom"));
        dto.setToDate(request.getParameter("transactiondateto"));
        dto.setSupplierId(request.getParameter("supplier"));
        dto.setItem(request.getParameter("itemname"));

        StockReportResponseDto responseDto = messItemsService.generateStockReceivedReport(dto, httpSession.getAttribute(BRANCHID).toString());
        httpSession.setAttribute("supplierselected", responseDto.getSupplierSelected());
        httpSession.setAttribute("itemselected", responseDto.getItemSelected());
        httpSession.setAttribute("messstockentrylist", responseDto.getMessStockEntryList());
        httpSession.setAttribute("transactionfromdateselected", responseDto.getTransactionFromDateSelected());
        httpSession.setAttribute("transactiontodateselected", responseDto.getTransactionToDateSelected());

    }

    public void receiveStockReport() {
        ResultResponse resultResponse = messItemsService.receiveStockReport(httpSession.getAttribute(BRANCHID).toString());
        request.setAttribute("itemslist", resultResponse.getResultList());
    }

    public void getIssuanceStock() {
        ResultResponse resultResponse = messItemsService.getIssuanceStock();
        request.setAttribute("itemslist", resultResponse.getResultList());
    }

    public void getBatchStock() {
        ResultResponse resultResponse = messItemsService.getBatchStock();
        request.setAttribute("messstockentrylist", resultResponse.getResultList());
    }

    public void getCurrentStock() {
        ResultResponse resultResponse = messItemsService.getCurrentStock();
        request.setAttribute("currentstocklist", resultResponse.getResultList());
    }

    public void addItemDetails() {
        ItemDetailsDto dto = new ItemDetailsDto();
        dto.setItemName(request.getParameter("itemname"));
        dto.setUnitOfMeasure(request.getParameter("unitofmeasure"));
        dto.setMinStock(request.getParameter("minstock"));

        ItemDetailsResponseDto responseDto = messItemsService.addItemDetails(dto, httpSession.getAttribute(BRANCHID).toString(), httpSession.getAttribute(USERID).toString());
        request.setAttribute("itemsave", responseDto.isItemSave());
        request.setAttribute("itemname", responseDto.getItemName());

    }

    public String viewItemDetails() {
        ResultResponse resultResponse = messItemsService.viewItemDetails(httpSession.getAttribute(BRANCHID).toString());
        request.setAttribute("messstockavailabilitylist", resultResponse.getResultList());

        if(resultResponse.isSuccess()){
            return "additems";
        }
        else return "error";
    }

    public void deleteMultipleItems() {
        MessIdsDto dto = new MessIdsDto();
        dto.setMessIds(request.getParameterValues("messitemsids"));

        ResultResponse resultResponse = messItemsService.deleteMultipleItems(dto);
        request.setAttribute("itemsdelete", resultResponse.isSuccess());
    }

    public void updateItems() {
        ItemsDto dto = new ItemsDto();
        dto.setMessIds(request.getParameterValues("messitemidsz"));


        Map<String, String> allRequestParameters = new HashMap<>();
        Enumeration<String> enumeration = request.getParameterNames();
        while (enumeration.hasMoreElements()) {
            String fieldName = enumeration.nextElement();
            String fieldValue = request.getParameter(fieldName);
            allRequestParameters.put(fieldName, fieldValue);
        }
        dto.setRequestParams(allRequestParameters);


        ResultResponse resultResponse = messItemsService.updateItems(dto);
        request.setAttribute("itemsupdate", resultResponse.isSuccess());
    }


    public void savePurchase() {
        PurchaseDto dto = new PurchaseDto();
        dto.setItemsTotal(request.getParameter("itemsGrandTotalAmountWithoutGST"));
        dto.setItemIds(request.getParameterValues("itemids"));
        dto.setItemsName(request.getParameterValues("itemsname"));
        dto.setItemsQuantity(request.getParameterValues("itemsquantity"));
        dto.setSalesPrice(request.getParameterValues("price"));
        dto.setBatchNo(request.getParameterValues("batchno"));
        dto.setLineTotal(request.getParameterValues("linetotal"));
        dto.setSupplierId(request.getParameter("supplierid"));
        dto.setPurchasePrice(request.getParameterValues("purchaseprice"));
        dto.setStateGst(request.getParameterValues("sgst"));
        dto.setCenterGst(request.getParameterValues("cgst"));
        dto.setItemEntryDate(request.getParameter("itementrydate"));
        dto.setInvoiceDate(request.getParameter("invoicedate"));
        dto.setSupplierReferenceNo(request.getParameter("supplierreferenceno"));

        ResultResponse resultResponse = messItemsService.savePurchase(dto, httpSession.getAttribute(BRANCHID).toString(), httpSession.getAttribute(USERID).toString());
        request.setAttribute("itemsreceived", resultResponse.isSuccess());
    }

    public void cancelPurchase() {InvoiceIdsDto dto = new InvoiceIdsDto();
        dto.setInvoiceId(request.getParameterValues("invoiceid"));

        ResultResponse resultResponse = messItemsService.cancelPurchase(dto);
    }

    public void getInvoiceDetails() {
        String page = request.getParameter("page");

        InvoiceDetailsResponseDto responseDto = messItemsService.getInvoiceDetails(page, httpSession.getAttribute(BRANCHID).toString());
        request.setAttribute("invoicelist", responseDto.getInvoiceSuppliersMap());
        request.setAttribute("noOfPages", responseDto.getNoOfPages());
        request.setAttribute("currentPage", responseDto.getCurrentPage());
    }

    public void generateStockIssuanceReport() {
        IssuanceReportDto dto = new IssuanceReportDto();
        dto.setFromDate(request.getParameter("transactiondatefrom"));
        dto.setToDate(request.getParameter("transactiondateto"));
        dto.setIssueTo(request.getParameter("issuedto"));
        dto.setPurpose(request.getParameter("purpose"));
        dto.setItem(request.getParameter("itemname"));

        IssuanceReportResponseDto responseDto = messItemsService.generateStockIssuanceReport(dto);
        httpSession.setAttribute("issuedtoselected", responseDto.getIssuedToSelected());
        httpSession.setAttribute("itemselected", responseDto.getItemSelected());
        httpSession.setAttribute("stockissuancelist", responseDto.getStockIssuanceList());
        httpSession.setAttribute("transactionfromdateselected", responseDto.getTransactionFromDateSelected());
        httpSession.setAttribute("transactiontodateselected", responseDto.getTransactionToDateSelected());
    }

    public void getCurrentStockToIssue() {
        ResultResponse resultResponse = messItemsService.getCurrentStockToIssue();
        request.setAttribute("stocklist", resultResponse.getResultList());
    }
}

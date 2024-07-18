package org.ideoholic.curium.model.mess.item.action;

import org.ideoholic.curium.model.mess.item.dto.*;
import org.ideoholic.curium.model.mess.item.service.MessItemsService;
import org.ideoholic.curium.util.ResultResponse;
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
    
    private String BRANCHID = "branchid";
    private String USERID = "userloginid";

    public void generateStockReceivedReport() {
        MessItemsService messItemsService = new MessItemsService(request, response);

        StockReportDto dto = new StockReportDto();
        dto.setFromDate(request.getParameter("transactiondatefrom"));
        dto.setToDate(request.getParameter("transactiondateto"));
        dto.setSupplierId(request.getParameter("supplier"));
        dto.setItem(request.getParameter("itemname"));

        StockReportResponseDto responseDto = messItemsService.generateStockReceivedReport(dto);
        httpSession.setAttribute("supplierselected", responseDto.getSupplierSelected());
        httpSession.setAttribute("itemselected", responseDto.getItemSelected());
        httpSession.setAttribute("messstockentrylist", responseDto.getMessStockEntryList());
        httpSession.setAttribute("transactionfromdateselected", responseDto.getTransactionFromDateSelected());
        httpSession.setAttribute("transactiontodateselected", responseDto.getTransactionToDateSelected());

    }

    public void receiveStockReport() {
        MessItemsService messItemsService = new MessItemsService(request, response);

        ResultResponse resultResponse = messItemsService.receiveStockReport();
        request.setAttribute("itemslist", resultResponse.getResultList());
    }

    public void getIssuanceStock() {
        MessItemsService messItemsService = new MessItemsService(request, response);

        ResultResponse resultResponse = messItemsService.getIssuanceStock();
        request.setAttribute("itemslist", resultResponse.getResultList());
    }

    public void getBatchStock() {
        MessItemsService messItemsService = new MessItemsService(request, response);

        ResultResponse resultResponse = messItemsService.getBatchStock();
        request.setAttribute("messstockentrylist", resultResponse.getResultList());
    }

    public void getCurrentStock() {
        MessItemsService messItemsService = new MessItemsService(request, response);

        ResultResponse resultResponse = messItemsService.getCurrentStock();
        request.setAttribute("currentstocklist", resultResponse.getResultList());
    }

    public void addItemDetails() {
        MessItemsService messItemsService = new MessItemsService(request, response);

        ItemDetailsDto dto = new ItemDetailsDto();
        dto.setItemName(request.getParameter("itemname"));
        dto.setUnitOfMeasure(request.getParameter("unitofmeasure"));
        dto.setMinStock(request.getParameter("minstock"));

        ItemDetailsResponseDto responseDto = messItemsService.addItemDetails(dto, httpSession.getAttribute(BRANCHID).toString(), httpSession.getAttribute(USERID).toString());
        request.setAttribute("itemsave", responseDto.isItemSave());
        request.setAttribute("itemname", responseDto.getItemName());

    }

    public String viewItemDetails() {
        MessItemsService messItemsService = new MessItemsService(request, response);

        ResultResponse resultResponse = messItemsService.viewItemDetails(httpSession.getAttribute(BRANCHID).toString());
        request.setAttribute("messstockavailabilitylist", resultResponse.getResultList());

        if(resultResponse.isSuccess()){
            return "additems";
        }
        else return "error";
    }

    public void deleteMultipleItems() {
        MessItemsService messItemsService = new MessItemsService(request, response);

        MessIdsDto dto = new MessIdsDto();
        dto.setMessIds(request.getParameterValues("messitemsids"));

        ResultResponse resultResponse = messItemsService.deleteMultipleItems(dto);
        request.setAttribute("itemsdelete", resultResponse.isSuccess());
    }

    public void updateItems() {
        MessItemsService messItemsService = new MessItemsService(request, response);

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
        MessItemsService messItemsService = new MessItemsService(request, response);

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
        dto.setSGst(request.getParameterValues("sgst"));
        dto.setCGst(request.getParameterValues("cgst"));
        dto.setItemEntryDate(request.getParameter("itementrydate"));
        dto.setInvoiceDate(request.getParameter("invoicedate"));
        dto.setSupplierReferenceNo(request.getParameter("supplierreferenceno"));
        dto.setTransportationCharges(request.getParameter("transportationcharges"));

        ResultResponse resultResponse = messItemsService.savePurchase(dto, httpSession.getAttribute(BRANCHID).toString(), httpSession.getAttribute(USERID).toString());
        request.setAttribute("itemsreceived", resultResponse.isSuccess());
    }

    public void cancelPurchase() {
        MessItemsService messItemsService= new MessItemsService(request, response);

        InvoiceIdsDto dto = new InvoiceIdsDto();
        dto.setInvoiceId(request.getParameterValues("invoiceid"));

        ResultResponse resultResponse = messItemsService.cancelPurchase(dto);
    }
}

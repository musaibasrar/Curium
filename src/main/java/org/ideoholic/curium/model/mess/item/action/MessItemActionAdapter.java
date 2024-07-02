package org.ideoholic.curium.model.mess.item.action;

import org.ideoholic.curium.model.mess.item.dto.StockReportDto;
import org.ideoholic.curium.model.mess.item.dto.StockReportResponseDto;
import org.ideoholic.curium.model.mess.item.service.MessItemsService;
import org.ideoholic.curium.util.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
}

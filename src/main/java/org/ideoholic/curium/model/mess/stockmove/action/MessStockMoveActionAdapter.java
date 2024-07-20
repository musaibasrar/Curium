package org.ideoholic.curium.model.mess.stockmove.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.mess.stockmove.dto.ClassSearchDto;
import org.ideoholic.curium.model.mess.stockmove.dto.MoveStockResponseDto;
import org.ideoholic.curium.model.mess.stockmove.dto.StockMoveDto;
import org.ideoholic.curium.model.mess.stockmove.dto.StockMoveResponseDto;
import org.ideoholic.curium.model.mess.stockmove.service.MessStockMoveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Service
public class MessStockMoveActionAdapter {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private HttpSession httpSession;

    private String BRANCHID = "branchid";
    private String USERID = "userloginid";


    public void viewStockEntryDetails() {
        MessStockMoveService messStockMoveService = new MessStockMoveService(request, response);

        ResultResponse resultResponse = messStockMoveService.viewStockEntryDetails(httpSession.getAttribute(BRANCHID).toString());
        request.setAttribute("messstockitemdetailslist", resultResponse.getResultList());
    }

    public void viewStockMoveDetails() {
        MessStockMoveService messStockMoveService = new MessStockMoveService(request, response);

        String page = request.getParameter("page");

        StockMoveResponseDto responseDto = messStockMoveService.viewStockMoveDetails(page, httpSession.getAttribute(BRANCHID).toString());
        request.setAttribute("noOfPages", responseDto.getNoOfPages());
        request.setAttribute("currentPage", page);
        request.setAttribute("messstockmovelist", responseDto.getMessStockMoveList());
    }

    public void saveStockMove() {MessStockMoveService messStockMoveService = new MessStockMoveService(request, response);

        StockMoveDto dto = new StockMoveDto();
        dto.setStockEntryIds(request.getParameterValues("ids"));
        dto.setItemsName(request.getParameterValues("itemsname"));
        dto.setItemsIds(request.getParameterValues("itemsids"));
        dto.setIssueQuantity(request.getParameterValues("issuequantity"));
        dto.setItemUintPrice(request.getParameterValues("itemunitprice"));
        dto.setPurchasePrice(request.getParameterValues("purchaseprice"));
        dto.setCustDetails(request.getParameter("issuedto"));
        dto.setSgst(request.getParameterValues("sgst"));
        dto.setCgst(request.getParameterValues("cgst"));
        dto.setUom(request.getParameterValues("itemsunitofmeasure"));
        dto.setBatchNo(request.getParameterValues("batchno"));
        dto.setSingleItemTotal(request.getParameterValues("linetotal"));
        dto.setPaymentMethodBankTransfer(request.getParameter("paymentmethodbanktransfer"));
        dto.setPaymentMethodChequeTransfer(request.getParameter("paymentmethodchequetransfer"));
        dto.setPaymentMethodCash(request.getParameter("paymentmethodcash"));
        dto.setAckNo(request.getParameter("ackno"));
        dto.setTransferDate(request.getParameter("transferdate"));
        dto.setTransferBankName(request.getParameter("transferbankname"));
        dto.setChequeNo(request.getParameter("chequeno"));
        dto.setChequeDate(request.getParameter("chequedate"));
        dto.setChequeBankName(request.getParameter("chequebankname"));
        dto.setTotalCashAmount(request.getParameter("totalcashamount"));
        dto.setTotalBankTransferAmount(request.getParameter("totalbanktransferamount"));
        dto.setTotalChequeTransferAmount(request.getParameter("totalchequetransferamount"));
        dto.setItemsGrandTotalAmountWOGST(request.getParameter("itemsGrandTotalAmountWithoutGST"));
        dto.setTransactionDate(request.getParameter("transactiondate"));

        MoveStockResponseDto responseDto = messStockMoveService.saveStockMove(dto, httpSession.getAttribute(BRANCHID).toString(), httpSession.getAttribute(USERID).toString(), httpSession.getAttribute("username").toString());
        request.setAttribute("billdetails", responseDto.getBillDetails());
        request.setAttribute("billdetailstransactiondate", dto.getTransactionDate());
        request.setAttribute("billdetailsstudentname", responseDto.getBillDetailsStudentName());
        request.setAttribute("billdetailsclassstudying", responseDto.getBillDetailsClassStudying());
        request.setAttribute("billdetailsfathername", responseDto.getBillDetailsFatherName());
        request.setAttribute("billdetailstotaltotal", responseDto.getBillDetailsTotalTotal());
        request.setAttribute("billgrandtotal", responseDto.getBillGrandTotal());
        request.setAttribute("billno", responseDto.getBillNo());
        request.setAttribute("billdetails", responseDto.getBillDetails());
        request.setAttribute("billdetailstransactiondate", responseDto.getBillDetailsTransactionDate());
        request.setAttribute("billdetailscustomername", responseDto.getBillDetailsCustomerName());
        request.setAttribute("itemissued", responseDto.isItemsIssued());
    }

    public void viewStockDueDetails() {
        MessStockMoveService messStockMoveService = new MessStockMoveService(request, response);

        ClassSearchDto dto = new ClassSearchDto();
        dto.setClassSearch(request.getParameter("classsearch"));

        ResultResponse resultResponse = messStockMoveService.viewStockDueDetails(dto, httpSession.getAttribute(BRANCHID).toString());
        request.setAttribute("studentsduelist", resultResponse.getResultList());
    }

    public void getCustomerLastPrice() throws IOException {
        MessStockMoveService messStockMoveService = new MessStockMoveService(request,response);

        String customerName = request.getParameter("customerName");
        String custDetails = request.getParameter("customerName");
        String itemId = request.getParameter("itemid");

        messStockMoveService.getCustomerLastPrice(customerName, custDetails, itemId, httpSession.getAttribute(BRANCHID).toString());
    }
}

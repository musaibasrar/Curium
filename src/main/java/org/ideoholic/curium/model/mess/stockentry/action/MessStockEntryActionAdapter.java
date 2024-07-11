package org.ideoholic.curium.model.mess.stockentry.action;

import org.ideoholic.curium.model.mess.stockentry.dto.MessStockEntryResponseDto;
import org.ideoholic.curium.model.mess.stockentry.service.MessStockEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Service
public class MessStockEntryActionAdapter {
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private HttpSession httpSession;

    private String BRANCHID = "branchid";


    public void getMRVDetails() throws IOException {
        MessStockEntryService messStockEntryService = new MessStockEntryService(request, response);

        String invoiceDetailsId = request.getParameter("invoicedetailsid");
        String supplierRefNo = request.getParameter("supplierreferenceno");
        String invoiceTotal = request.getParameter("invoicetotal");
        String supplierName = request.getParameter("suppliername");
        String invoiceDate = request.getParameter("entrydate");

        MessStockEntryResponseDto responseDto = messStockEntryService.getMRVDetails(invoiceDetailsId, supplierRefNo, invoiceTotal, supplierName, invoiceDate, httpSession.getAttribute(BRANCHID).toString());
        request.setAttribute("supplierreferenceno", responseDto.getSupplierRefNo());
        request.setAttribute("invoiceTotal", responseDto.getInvoiceTotal());
        request.setAttribute("suppliername", responseDto.getSupplierName());
        request.setAttribute("entrydate", responseDto.getInvoiceDate());
    }
}

package org.ideoholic.curium.model.mess.stockmove.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.mess.stockmove.dto.StockMoveResponseDto;
import org.ideoholic.curium.model.mess.stockmove.service.MessStockMoveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
    }
}

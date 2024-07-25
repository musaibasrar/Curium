package org.ideoholic.curium.model.mess.supplier.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.mess.supplier.dto.ChequeDetailsDto;
import org.ideoholic.curium.model.mess.supplier.dto.ChequeDto;
import org.ideoholic.curium.model.mess.supplier.service.MessSuppliersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Service
public class MessSuppliersActionAdapter {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private HttpSession httpSession;

    private String BRANCHID = "branchid";
    private String USERID = "userloginid";

    public boolean viewSuppliersDetails() {
        MessSuppliersService messSuppliersService = new MessSuppliersService(request, response);

        ResultResponse resultResponse = messSuppliersService.viewSuppliersDetails(httpSession.getAttribute(BRANCHID).toString());
        request.setAttribute("messsupplierslist", resultResponse.getResultList());

        return resultResponse.isSuccess();
    }

    public void viewBalanceSuppliers() {
        MessSuppliersService messSuppliersService = new MessSuppliersService(request, response);

        ResultResponse resultResponse = messSuppliersService.viewBalanceSuppliers(httpSession.getAttribute(BRANCHID).toString());
        request.setAttribute("supplierbalancedetails", resultResponse.getResultList());
    }

    public void cancelCheque() {
        MessSuppliersService messSuppliersService = new MessSuppliersService(request, response);

        ChequeDetailsDto dto = new ChequeDetailsDto();
        dto.setSupplierIds(request.getParameterValues("supplierpaymentid"));

        Map<String, String> allRequestParameters = new HashMap<>();
        Enumeration<String> enumeration = request.getParameterNames();
        while (enumeration.hasMoreElements()) {
            String fieldName = enumeration.nextElement();
            String fieldValue = request.getParameter(fieldName);
            allRequestParameters.put(fieldName, fieldValue);
        }
        dto.setRequestParams(allRequestParameters);

        ResultResponse resultResponse = messSuppliersService.cancelCheque(dto, httpSession.getAttribute(BRANCHID).toString(), httpSession.getAttribute(USERID).toString());
        request.setAttribute("chequecancelled", resultResponse.isSuccess());
    }

    public void clearedCheque() {
        MessSuppliersService messSuppliersService = new MessSuppliersService(request, response);

        ChequeDetailsDto dto = new ChequeDetailsDto();
        dto.setDate(request.getParameter("cleardate"));
        dto.setBankName(request.getParameter("bankname"));
        dto.setSupplierIds(request.getParameterValues("supplierpaymentid"));

        Map<String, String> allRequestParameters = new HashMap<>();
        Enumeration<String> enumeration = request.getParameterNames();
        while (enumeration.hasMoreElements()) {
            String fieldName = enumeration.nextElement();
            String fieldValue = request.getParameter(fieldName);
            allRequestParameters.put(fieldName, fieldValue);
        }
        dto.setRequestParams(allRequestParameters);

        ResultResponse resultResponse = messSuppliersService.clearedCheque(dto, httpSession.getAttribute(BRANCHID).toString(), httpSession.getAttribute(USERID).toString());
        request.setAttribute("chequecleared", resultResponse.isSuccess());
    }

    public void issueCheque() {
        MessSuppliersService messSuppliersService = new MessSuppliersService(request, response);

        ChequeDto dto = new ChequeDto();
        dto.setDate(request.getParameter("transactiondate"));
        dto.setSupplierId(request.getParameter("supplierid"));
        dto.setChequeNo(request.getParameter("chequeno"));
        dto.setIssueAmount(request.getParameter("chequeamount"));

        ResultResponse resultResponse = messSuppliersService.issueCheque(dto, httpSession.getAttribute(BRANCHID).toString(), httpSession.getAttribute(USERID).toString());
        request.setAttribute("supplierpaymentissued", resultResponse.getResultList());
    }
}

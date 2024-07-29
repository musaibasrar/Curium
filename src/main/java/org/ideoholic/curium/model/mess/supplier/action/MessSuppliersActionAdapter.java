package org.ideoholic.curium.model.mess.supplier.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.mess.item.dto.MessIdsDto;
import org.ideoholic.curium.model.mess.supplier.dto.*;
import org.ideoholic.curium.model.mess.supplier.service.MessSuppliersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Service
public class MessSuppliersActionAdapter {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpSession httpSession;
    
    @Autowired
    private MessSuppliersService messSuppliersService;

    private String BRANCHID = "branchid";
    private String USERID = "userloginid";

    public boolean viewSuppliersDetails() {
        
        ResultResponse resultResponse = messSuppliersService.viewSuppliersDetails(httpSession.getAttribute(BRANCHID).toString());
        request.setAttribute("messsupplierslist", resultResponse.getResultList());

        return resultResponse.isSuccess();
    }

    public void viewBalanceSuppliers() {
        ResultResponse resultResponse = messSuppliersService.viewBalanceSuppliers(httpSession.getAttribute(BRANCHID).toString());
        request.setAttribute("supplierbalancedetails", resultResponse.getResultList());
    }

    public void cancelCheque() {
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
        ChequeDto dto = new ChequeDto();
        dto.setDate(request.getParameter("transactiondate"));
        dto.setSupplierId(request.getParameter("supplierid"));
        dto.setChequeNo(request.getParameter("chequeno"));
        dto.setIssueAmount(request.getParameter("chequeamount"));

        ResultResponse resultResponse = messSuppliersService.issueCheque(dto, httpSession.getAttribute(BRANCHID).toString(), httpSession.getAttribute(USERID).toString());
        request.setAttribute("supplierpaymentissued", resultResponse.isSuccess());
    }

    public void viewSuppliersPaymentDetails() {
        String page = request.getParameter("page");

        PaymentDetailsResponseDto responseDto = messSuppliersService.viewSuppliersPaymentDetails(page, httpSession.getAttribute(BRANCHID).toString());

        request.setAttribute("supplierpaymentlist", responseDto.getSupplierPaymentList());
        request.setAttribute("noOfPages", responseDto.getNoOfPages());
        request.setAttribute("currentPage", responseDto.getPage());
    }

    public void updateSuppliers() {
        MessIdsDto dto = new MessIdsDto();
        dto.setMessIds(request.getParameterValues("messsuppliersids"));

        Map<String, String> allRequestParameters = new HashMap<>();
        Enumeration<String> enumeration = request.getParameterNames();
        while (enumeration.hasMoreElements()) {
            String fieldName = enumeration.nextElement();
            String fieldValue = request.getParameter(fieldName);
            allRequestParameters.put(fieldName, fieldValue);
        }
        dto.setRequestParams(allRequestParameters);

        ResultResponse resultResponse = messSuppliersService.updateSuppliers(dto);
        request.setAttribute("suppliersupdate", resultResponse.isSuccess());
    }

    public void deliveredCheque() {
        ChequeDetailsDto dto = new ChequeDetailsDto();
        dto.setDate(request.getParameter("deliverydate"));
        dto.setSupplierIds(request.getParameterValues("supplierpaymentid"));

        ResultResponse resultResponse = messSuppliersService.deliveredCheque(dto, httpSession.getAttribute(BRANCHID).toString());
        request.setAttribute("chequedelivered", resultResponse.isSuccess());
    }

    public void deleteMultipleSuppliers() {
        MessIdsDto dto = new MessIdsDto();
        dto.setMessIds(request.getParameterValues("messsuppliersids"));

        Map<String, String> allRequestParameter = new HashMap<>();
        Enumeration<String> enumeration = request.getParameterNames();
        while (enumeration.hasMoreElements()){
            String fieldName = enumeration.nextElement();;
            String fieldValue = request.getParameter(fieldName);
            allRequestParameter.put(fieldName, fieldValue);
        }
        dto.setRequestParams(allRequestParameter);

        ResultResponse resultResponse = messSuppliersService.deleteMultipleSuppliers(dto);
        request.setAttribute("suppliersdelete", resultResponse.isSuccess());
    }

    public void getSupplierBalance() throws IOException {
        String supplierId = request.getParameter("supplierid");

        messSuppliersService.getSupplierBalance(supplierId);
    }

    public void addSupplierDetails() {
        SuppliersDetailsDto dto = new SuppliersDetailsDto();
        dto.setName(request.getParameter("suppliername"));
        dto.setExternalId(request.getParameter("externalid"));
        dto.setContactNumber(request.getParameter("contactnumber"));
        dto.setBankAccountNo(request.getParameter("bankaccountno"));
        dto.setIfscCode(request.getParameter("ifsccode"));
        dto.setAddress(request.getParameter("address"));
        dto.setPayTo(request.getParameter("payto"));

        SuppliersDetailsResponseDto responseDto = messSuppliersService.addSupplierDetails(dto, httpSession.getAttribute(BRANCHID).toString(), httpSession.getAttribute(USERID).toString());
        request.setAttribute("suppliersave", responseDto.isSupplierSave());
        request.setAttribute("suppliername", responseDto.getSupplierName());
    }
}

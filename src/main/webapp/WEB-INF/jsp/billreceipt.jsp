<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%-- Include tenant globals for property-driven behavior --%>
<%@ include file="/WEB-INF/jsp/common/_tenant_globals.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tax Invoice</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        .invoice-container {
            width: 80%;
            margin: auto;
            border: 1px solid #000;
            padding: 20px;
        }
        .header, .footer {
            text-align: center;
            font-weight: bold;
        }
        .details, .table {
            width: 100%;
            margin-top: 20px;
        }
        .table, .table th, .table td {
            border: 1px solid black;
            border-collapse: collapse;
            text-align: center;
        }
        .table th, .table td {
            padding: 8px;
        }
        .total {
            text-align: right;
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <div class="invoice-container">
        <div class="header">
            <h2>M/s. PATEL TRADERS</h2>
            <p>Authorised Dealer: Metaroll TMT 550 Steel | Ultratech Cement</p>
        </div>
        
        <div class="details">
            <p><strong>Invoice No:</strong> 308</p>
            <p><strong>Invoice Date:</strong> <c:out value="${billdetailstransactiondate}" /></p>
            <p><strong>State:</strong> Karnataka</p>
            <p><strong>GSTIN:</strong> 29BGJPD5342K276</p>
        </div>

        <table class="table">
            <tr>
                <th>Sl. No</th>
                <th>HSN Code</th>
                <th>Description</th>
                <th>Qty. (Kg)</th>
                <th>Rate</th>
                <th>Amount</th>
                <th>Taxable Value</th>
            </tr>
            <c:forEach items="${billdetail}" var="feescatmap">
            <tr>
                <td>1</td>
                <td><c:out value="${feescatmap.batchno}" /></td>
                <td><c:out value="${feescatmap.itemname}" /></td>
                <td><c:out value="${feescatmap.quantity}" /></td>
                <td><c:out value="${feescatmap.salesprice}" /></td>
                <td><c:out value="${feescatmap.itemname}" /></td>
                <td>42189</td>
            </tr>
            </c:forEach>
        </table>

        <div class="total">
            <p><strong>Total Amount Before Tax:</strong> ${itemsGrandTotalAmountWithoutGST}</p>
            <p><strong>CGST :</strong>  ${sumcgst}</p>
            <p><strong>SGST :</strong>  ${sumsgst}</p>
            <p><strong>Grand Total:</strong> <c:out value="${billgrandtotal}" /></p>
        </div>
        
        <div class="footer">
            <p>Certified that the particulars given above are true & correct</p>
            <p>For: M/s. PATEL TRADERS</p>
            <p>Authorized Signature</p>
        </div>
    </div>
        <table>
        <tr>
            <td align="center">
                <button id="print" type="button" onclick="window.print()">Print</button>
            </td>
        </tr>
    </table>
    
</body>
</html>

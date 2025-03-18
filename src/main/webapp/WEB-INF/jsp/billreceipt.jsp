<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tax Invoice</title>
    <style>
    table {
        border:1px solid black;
        border-radius: 5px;
        text-align:center;
        width: 100%; 
        border-collapse: collapse;
        margin-bottom: 2px;
    }
    th,td{
        border:1px solid black;
        text-align:center;
        border-collapse: collapse;
    }
    td{
        text-align: left;
    }
</style>



</head>
<body>
        <div style="border:2px solid black;padding:2px;">
            <table style="border:1px solid black;text-align:center;width: 100%;">
                <tr>
                    <td style="border:0px solid black;text-align:center;">TAX INVOICE</td>
                </tr>
                <tr>
                    <td style="border:0px solid black;font-size:20px;font-weight:bold;text-align:center;">M/s. PATEL TRADERS</td>
                </tr>
                <tr>
                    <td style="border:0px solid black;text-align:center;">Authorised Dealer: Metaroll TMT 550 stee I Ultratech Cement</td>
                </tr>
                <tr>
                    <td style="border:0px solid black;text-align:center;">Chalwa Complex, Udgir Road, Shivnagar, Bidar, Email: pateltrader@gmail.com</td>
                </tr>
            </table>
        <table style="border:1px solid black;text-align:center;width: 100%;">
            <tr>
                <td style="border:1px solid black;">INVOICE No.&emsp;&emsp;&emsp;&emsp;   :5677</td><td style="border:1px solid black;">To</td>
            </tr> 
            <tr>
                <td style="border:1px solid black;">INVOICE Date&emsp;&emsp;&emsp;&emsp;: ${billdetailstransactiondate}</td><td style="border:1px solid black;">Name &emsp;&emsp;:${studentname}</td>
            </tr>
            <tr>
                <td style="border:1px solid black;">Reserve Charges(Y/N)&emsp;:Y</td><td rowspan="2" style="border:1px solid black;">Address &emsp; :</td>
            </tr>
            <tr>
                <td style="border:1px solid black;">State &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;:Karnataka</td>
            </tr>
            <tr>
                <td style="border:1px solid black;">State Code&emsp;&emsp;&emsp;&emsp;&emsp;&nbsp;&nbsp;:2</td><td style="border:1px solid black;text-align:center;"></td>
            </tr> 
        </table>
<table style="border:1px solid black;text-align:center;width: 100%;">
    <thead>
        <tr>
            <th>SL No.</th><th>HSN Code</th><th>DESCRIPTION</th><th>Qty in kg</th><th>Rate</th><th>Amount</th><th>Taxable Value</th>
        </tr>
    </thead>
    <tbody>
    
        <c:set var="rowCount" value="0" />
     <c:forEach items="${billdetail}" var="feescatmap">
            <c:set var="totalamountbeforetax" value="${feescatmap.quantity * feescatmap.salesprice}" />
            <tr>
                <td>1</td>
                <td><c:out value="${feescatmap.batchno}" /></td>
                <td><c:out value="${feescatmap.itemname}" /></td>
                <td><c:out value="${feescatmap.quantity}" /></td>
                <td><c:out value="${feescatmap.salesprice}" /></td>
                <td><c:out value="${feescatmap.itemname}" /></td>
                <td><c:out value="${totalamountbeforetax}" /></td>
            </tr>
              <c:set var="rowCount" value="${rowCount + 1}" />
            </c:forEach> 
            <c:forEach begin="${rowCount + 1}" end="10" var="emptyRow">
          <tr>
        <td><c:out value="${emptyRow}" /></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        </tr>
        </c:forEach>
        <!-- <tr>
            <td>001</td><td>455677</td><td>It is OK</td><td>59 kg</td><td>3 per/pc</td><td>20000</td><td>50000</td>
        </tr> -->
        <tr>
            <td colspan="4" style="text-align:center;">TOTAL INVOICE AMOUNT IN WORDS</td><td colspan="2">TOTAL AMOUNT BEFORE TAX</td><td>${itemsGrandTotalAmountWithoutGST}</td>
        </tr>
        <tr>
            <td colspan="4" rowspan="3" id="amountInWords" style="text-align:center;">${noinwords}</td><td colspan="2">Add CGST: ${sumcgst}</td><td>${totalcgst}</td>
        </tr>
        <tr>
            <td colspan="2">Add SGST: ${sumsgst}</td><td>${totalsgst}</td>
        </tr>
        <tr>
            <td colspan="2">Add IGST: 9%</td><td></td>
        </tr>
       
        <tr>
            <td colspan="4" style="text-align: center;">TERMS AND CONDITION</td><td colspan="2" >GRAND TOTAL</td><td id="grandTotal">  ${grandtotal} </td>
        </tr>
        <tr>
            <td colspan="4"></td><td colspan="3" style="text-align:center;">&nbsp;<br>
            &nbsp; <br>
            &nbsp;<br>
            &nbsp;<br>&nbsp;<br>
            Authorised Signature</td>
        </tr>
    </tbody>
</table>
</div>
 
                <button id="print" type="button" onclick="window.print()">Print</button>
           
    </body>
</html>

<%-- 
    Document   : ${name}
    Created on : ${date}, ${time}
    Author     : ${user}
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
<title>RECIEPT</title>
<head>
<style type="text/css">
.dataText {
	font-family: Tahoma;
	color: black;
	font-size: 10px;
	letter-spacing: normal;
	text-align: center;
	fon
}

.headerText {
	width: 10px;
	font-family: Tahoma;
	font-size: 12px;
	color: black;
	font-weight: normal;
	width: auto;
	height: 22px;
	vertical-align: middle;
	text-align: center;
}

.headerTextLeft {
	width: 10px;
	font-family: Tahoma;
	font-size: 12px;
	color: #FFFFFF;
	font-weight: normal;
	width: auto;
	height: 22px;
	vertical-align: middle;
	text-align: left;
}

.dataTextBold {
	font-weight: bold;
	font-family: Tahoma;
	color: black;
	font-size: 12px;
	letter-spacing: normal;
	text-align: center;
}

.dataTextBoldLeft {
	font-weight: normal;
	font-family: Tahoma;
	color: black;
	font-size: 8px;
	letter-spacing: normal;
	text-align: left;
}

.dataTextBoldCenter {
	font-weight: normal;
	font-family: Tahoma;
	color: black;
	font-size: 24px;
	letter-spacing: normal;
	text-align: center;
}

.addressLine{
	font-weight: normal;
	font-family: ariel;
	color: black;
	font-size: 16px;
	letter-spacing: normal;
	text-align: center;
}


</style>


<!-- <style type="text/css">

        @media print {
            .fontsize { font-size: 15px ;
                        font-weight: bold;
                        font-family: 'Times New Roman';
                        
                        
            }
            .header,.hide { visibility: hidden }
            .bodymargin{
            	margin-top: 0px;
                margin-left: 0px ;
                margin-right: 0px;
            }
            
        }
        
        @page {
              size: auto;   /* auto is the current printer page size */
           	  margin: 0mm;  /* this affects the margin in the printer settings */ 
            
        }

        @media screen {
            .fontsize { font-size: 15px;
                        font-weight: bold;
                        font-family: 'Times New Roman'
            }
            .bodymargin{
                margin-left: 0px ;
                margin-right: 0px;
            }
        }
    </style> -->
    
  <style type="text/css">



        @media print {
            .fontsize { font-size: 15px ;
                        font-weight: bold;
                        font-family: 'Times New Roman';
                        
                        
            }
            .header,.hide { visibility: hidden }
            .bodymargin{
                margin-left: 0px ;
                margin-right: 0px;
            }
            
        }
        
        @page {
              
             margin-left:  1cm;
             margin-right: 1cm;
             margin-bottom: 1cm;
             margin-top: 1cm;
        }

        @media screen {
            .fontsize { font-size: 15px;
                        font-weight: bold;
                        font-family: 'Times New Roman'
            }
            .bodymargin{
                margin-left: 1px ;
                margin-right: 1px;
            }
        }
    </style>
    
    
 <script type="text/javascript">

		function PrintPage(){
			window.print();
		}
		
</script>
    
</head>
<body style="text-align: center" class="bodymargin" onload="window.print();">
<div style="page-break-inside: avoid;border-collapse:collapse;">
	<form method="post" class="bodymargin">
			<div class="invoice-box">
<div width="100%">
<table align="center" width="60%" style="float: left;">
    <tr>
        <td style="font-size: 80px;">
              NORTUN
        </td>
    </tr>
</table>
<table width="40%"  align="right" style="float: right;">
    <tr>
        <td>NORTUN TRADING</td>
    </tr>
    <tr>
        <td>Building Tools & Hardware LLC</td>
    </tr>
    <tr>
        <td>Mob: 050 845 8466 , Tel: 06 744 3786</td>
    </tr>
    <tr>
        <td>Email: ahussain@nortunproduds.com</td>
    </tr>
    <tr>
        <td>P.O.Box:18581, Ajman UAE</td>
    </tr>
     <tr>
        <td>TRN:100356259000003</td>
    </tr>
</table>
</div>


<table width="100%">
<tr>
    <td align="center">
       <h2>TAX INVOICE</h2> 
    </td>
</tr>
</table>
<table width="50%" style="border: 1px solid black;float: left;">
<tr>
    <td>
        <b>Customer Name & Address :</b><c:out value="${billdetailsstudentname}" />
    </td>
</tr>
<tr>
    <td>
        &nbsp;
    </td>
</tr>
<tr>
    <td>
        Tel : 
    </td>
</tr>    
<tr>
    <td>
        TRN :
    </td>
</tr>

   
</table>
<table width="50%"  style="border: 1px solid black;float: right;">
    <tr>
    <td>
          INV. No.
    </td>
    <td> INV. Date:</td>
    </tr>
     <tr>
    <td>
         
    </td>
    <td> Payment Terms:</td>
    </tr>
    <tr>
    <td colspan="2">
       LPO. No. 
    </td>
    </tr>
     <tr>
    <td colspan="2">
         DO.No.  
    </td>
    </tr>
</table>
<div style="width:100%; height:500px; border:1px solid black;">
<table width="100%" >

<tr style="background-color: rgb(192, 53, 169);">
<th>Sl No</th>
<th>Code</th>
<th>Description</th>
<th>Qty</th>
<th>Unit</th>
<th>Unit Price (AED)</th>
<th>Amount</th>
<th>VAT Rate</th>
<th>VAT Amount</th>
<th>Total Amount</th>
</tr>

<c:forEach items="${billdetails}" var="feescatmap">
				<tr>
					<td class="dataText"><c:out
							value="${feescatmap.itemname}" /></td>
					<td class="dataText"></td>
					<td class="dataText"></td>
					<td class="dataText"></td>
					<td class="dataText"></td>
					<td class="dataText"></td>
					<td class="dataText"></td>
					<td class="dataText"></td>
					<td class="dataText"></td>
					<td class="dataText"></td>
					<%-- Rs. <td class="dataText">Rs. <c:out value="${unitdispensedose.amount}" /></td> --%>
				</tr>
			</c:forEach>

</table>
</div>

<table class="total-table">

<tr>
<td>Sub Total</td>
<td></td>
</tr>

<tr>
<td>VAT @ 5%</td>
<td></td>
</tr>

<tr>
<td><b>Grand Total</b></td>
<td></td>
</tr>

</table>

<div class="footer">

<div>
<b>NET Amount in Words AED :</b>
</div>

<div class="signature">

<div>
Receiver's Signature & Stamp
</div>

<div>
For NORTUN TRADING L.L.C
</div>

</div>

<div class="note">
All claims must be in writing and made within 3 days after receipt of goods.
</div>

</div>


</div>
<table>
<tr>
<td>
<br>
</td>
</tr>
<tr>
<td>
<br>
</td>
</tr>
<tr>
<td>
<br>
</td>
</tr>
<tr>
<td>
<br>
</td>
</tr><tr>
<td>
<br>
</td>
</tr><tr>
<td>
<br>
</td>
</tr><tr>
<td>
<br>
</td>
</tr><tr>
<td>
<br>
</td>
</tr><tr>
<td>
<br>
</td>
</tr><tr>
<td>
<br>
</td>
</tr>
</table>
		
		<div class="invoice-box">
<div width="100%">
<table align="center" width="60%" style="float: left;">
    <tr>
        <td style="font-size: 80px;">
              NORTUN
        </td>
    </tr>
</table>
<table width="40%"  align="right" style="float: right;">
    <tr>
        <td>NORTUN TRADING</td>
    </tr>
    <tr>
        <td>Building Tools & Hardware LLC</td>
    </tr>
    <tr>
        <td>Mob: 050 845 8466 , Tel: 06 744 3786</td>
    </tr>
    <tr>
        <td>Email: ahussain@nortunproduds.com</td>
    </tr>
    <tr>
        <td>P.O.Box:18581, Ajman UAE</td>
    </tr>
     <tr>
        <td>TRN:100356259000003</td>
    </tr>
</table>
</div>


<table width="100%">
<tr>
    <td align="center">
       <h2>TAX INVOICE</h2> 
    </td>
</tr>
</table>
<table width="50%" style="border: 1px solid black;float: left;">
<tr>
    <td>
        <b>Customer Name & Address :</b>
    </td>
</tr>
<tr>
    <td>
        &nbsp;
    </td>
</tr>
<tr>
    <td>
        Tel : 
    </td>
</tr>    
<tr>
    <td>
        TRN :
    </td>
</tr>

   
</table>
<table width="50%"  style="border: 1px solid black;float: right;">
    <tr>
    <td>
          INV. No.
    </td>
    <td> INV. Date:</td>
    </tr>
     <tr>
    <td>
         
    </td>
    <td> Payment Terms:</td>
    </tr>
    <tr>
    <td colspan="2">
       LPO. No. 
    </td>
    </tr>
     <tr>
    <td colspan="2">
         DO.No.  
    </td>
    </tr>
</table>
<div style="width:100%; height:500px; border:1px solid black;">
<table width="100%" >

<tr style="background-color: rgb(192, 53, 169);">
<th>Sl No</th>
<th>Code</th>
<th>Description</th>
<th>Qty</th>
<th>Unit</th>
<th>Unit Price (AED)</th>
<th>Amount</th>
<th>VAT Rate</th>
<th>VAT Amount</th>
<th>Total Amount</th>
</tr>

<tr>
<td>1</td>
<td></td>
<td></td>
<td></td>
<td></td>
<td></td>
<td></td>
<td>5%</td>
<td></td>
<td></td>
</tr>

<tr>
<td>2</td>
<td></td>
<td></td>
<td></td>
<td></td>
<td></td>
<td></td>
<td>5%</td>
<td></td>
<td></td>
</tr>

</table>
</div>

<table class="total-table">

<tr>
<td>Sub Total</td>
<td></td>
</tr>

<tr>
<td>VAT @ 5%</td>
<td></td>
</tr>

<tr>
<td><b>Grand Total</b></td>
<td></td>
</tr>

</table>

<div class="footer">

<div>
<b>NET Amount in Words AED :</b>
</div>

<div class="signature">

<div>
Receiver's Signature & Stamp
</div>

<div>
For NORTUN TRADING L.L.C
</div>

</div>

<div class="note">
All claims must be in writing and made within 3 days after receipt of goods.
</div>

</div>


</div>
	</form>
	
	</div>
	<a id="print" onclick="PrintPage()">Print</a>
</body>
</html>

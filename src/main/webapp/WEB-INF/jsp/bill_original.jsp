
<%-- 
    Document   : ${name}
    Created on : ${date}, ${time}
    Author     : ${user}
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE  html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Bill</title>
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
              
             margin-left:  0cm;
             margin-right: 0cm;
             margin-bottom: 0cm;
             margin-top: 0cm;
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
        
* {
	margin: 0;
	padding: 0;
	text-indent: 0;
}

.s1 {
	color: black;
	font-family: "Arial Black", sans-serif;
	font-style: normal;
	font-weight: normal;
	text-decoration: none;
	font-size: 11pt;
}

.s2 {
	color: black;
	font-family: "Arial Black", sans-serif;
	font-style: normal;
	font-weight: normal;
	text-decoration: none;
	font-size: 12pt;
	vertical-align: 4pt;
}

.s3 {
	color: black;
	font-family: Arial, sans-serif;
	font-style: normal;
	font-weight: bold;
	text-decoration: none;
	font-size: 20pt;
}

.s4 {
	color: black;
	font-family: "Arial Black", sans-serif;
	font-style: normal;
	font-weight: normal;
	text-decoration: none;
	font-size: 12pt;
}

.s5 {
	color: black;
	font-family: Arial, sans-serif;
	font-style: normal;
	font-weight: normal;
	text-decoration: none;
	font-size: 8pt;
}

.s6 {
	color: black;
	font-family: Arial, sans-serif;
	font-style: normal;
	font-weight: bold;
	text-decoration: none;
	font-size: 8pt;
}

table, tbody {
	vertical-align: top;
	overflow: visible;
}
</style>
</head>
<body style="text-align: center" class="bodymargin" onload="window.print();">
	<p style="text-indent: 0pt; text-align: left;">
		<br />
	</p>
	<table style="border-collapse: collapse; margin-left: 6.325pt"
		cellspacing="0">
		<tr style="height: 79pt">
			<td
				style="width: 555pt; border-top-style: solid; border-top-width: 2pt; border-left-style: solid; border-left-width: 2pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 2pt"
				colspan="11">
				<p class="s1"
					style="padding-left: 18pt; text-indent: 0pt; line-height: 22pt; text-align: center;">
					<span class="s3">TAX INVOICE</span>
				<br>
				</p>
				
				<p class="s1"
					style="padding-left: 31pt; text-indent: 0pt; line-height: 16pt; text-align: center;">
					<span class="s4">Hisabaat</span>
				</p></td>
		</tr>
		<tr style="height: 19pt">
			<td
				style="width: 210pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 2pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt"
				colspan="3" rowspan="4"><p class="s5"
					style="padding-top: 2pt; padding-left: 2pt; text-indent: 0pt; text-align: left;">Name
					&amp; Address Of Buyer<br>${billdetailscustomername}<br>${billdetailscustomercontact}<br>${billdetailscustomeraddress}</p></td>
			<td
				style="width: 165pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt"
				colspan="4" rowspan="4"><p class="s5"
					style="padding-left: 4pt; text-indent: 0pt; text-align: left;">Shipped
					To <br>${billdetailscustomername}<br>${billdetailscustomercontact}<br>${billdetailscustomeraddress}</p></p></td>
			<td
				style="width: 90pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt"
				colspan="2"><p class="s6"
					style="padding-top: 5pt; padding-left: 15pt; text-indent: 0pt; text-align: left;">Invoice
					No.</p></td>
			<td
				style="width: 90pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 2pt"
				colspan="2"><p style="text-indent: 0pt; text-align: left;">&nbsp;&nbsp;${billno}
					<br />
				</p></td>
		</tr>
		<tr style="height: 19pt">
			<td
				style="width: 90pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt"
				colspan="2"><p class="s5"
					style="padding-top: 5pt; padding-left: 15pt; text-indent: 0pt; text-align: left;">Date
					Of Issue</p></td>
			<td
				style="width: 90pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 2pt"
				colspan="2"><p style="text-indent: 0pt; text-align: left;"> &nbsp;&nbsp;${billdetailstransactiondate}
					<br />
				</p></td>
		</tr>
		<tr style="height: 19pt">
			<td
				style="width: 90pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt"
				colspan="2"><p class="s5"
					style="padding-top: 5pt; padding-left: 15pt; text-indent: 0pt; text-align: left;">GST
					No.</p></td>
			<td
				style="width: 90pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 2pt"
				colspan="2"><p style="text-indent: 0pt; text-align: left;">&nbsp;&nbsp;123456798
					<br />
				</p></td>
		</tr>
		 <tr style="height: 19pt">
			<td
				style="width: 90pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt"
				colspan="2"><p class="s5"
					style="padding-top: 5pt; padding-left: 15pt; text-indent: 0pt; text-align: left;">State</p></td>
			<td
				style="width: 90pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 2pt"
				colspan="2"><p style="text-indent: 0pt; text-align: left;">&nbsp;&nbsp;Karnataka
					<br />
				</p></td>
		</tr>
		<!-- <tr style="height: 19pt">
			<td
				style="width: 210pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 2pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt"
				colspan="3"><p class="s5"
					style="padding-top: 4pt; padding-left: 2pt; text-indent: 0pt; text-align: left;">Buyer
					GSTIN No. :</p></td>
			<td
				style="width: 165pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt"
				colspan="4"><p class="s5"
					style="padding-top: 4pt; padding-left: 3pt; text-indent: 0pt; text-align: left;">Place
					Of Supply :</p></td>
			<td
				style="width: 90pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt"
				colspan="2"><p class="s5"
					style="padding-top: 5pt; padding-left: 15pt; text-indent: 0pt; text-align: left;">Electronic
					Ref No.</p></td>
			<td
				style="width: 90pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 2pt"
				colspan="2"><p style="text-indent: 0pt; text-align: left;">
					<br />
				</p></td>
		</tr> -->
		<tr style="height: 39pt">
			<td
				style="width: 15pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 2pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt"><p
					style="text-indent: 0pt; text-align: left;">
					<br />
				</p>
				<p class="s6"
					style="padding-left: 1pt; text-indent: 0pt; text-align: left;">Sr. No.</p>
				</td>
			<td
				style="width: 150pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt"><p
					style="text-indent: 0pt; text-align: left;">
					<br />
				</p>
				<p class="s6"
					style="padding-left: 42pt; text-indent: 0pt; text-align: left;">Description</p></td>
			<td
				style="width: 45pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt"><p
					style="text-indent: 0pt; text-align: left;">
					<br />
				</p>
				<p class="s6"
					style="padding-left: 1pt; text-indent: 0pt; text-align: center;">Batch No.</p></td>
			<td
				style="width: 45pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt"><p
					style="text-indent: 0pt; text-align: left;">
					<br />
				</p>
				<p class="s6"
					style="padding-left: 6pt; text-indent: 0pt; text-align: left;">Quantity</p></td>
			<td
				style="width: 30pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt"><p
					style="text-indent: 0pt; text-align: left;">
					<br />
				</p>
				<p class="s6"
					style="padding-left: 5pt; text-indent: 0pt; text-align: left;">Units</p></td>
			<td
				style="width: 45pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt"><p
					style="text-indent: 0pt; text-align: left;">
					<br />
				</p>
				<p class="s6"
					style="padding-left: 13pt; text-indent: 0pt; text-align: left;">Rate</p></td>
			<td
				style="width: 45pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt"><p
					style="text-indent: 0pt; text-align: left;">
					<br />
				</p>
				<p class="s6"
					style="padding-left: 13pt; padding-right: 5pt; text-indent: -4pt; line-height: 148%; text-align: left;">Taxable
					Value</p></td>
			<td
				style="width: 30pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt;">
				</td>
			<td
				style="width: 60pt; border-top-style: solid; border-top-width: 1pt;  border-bottom-style: solid; border-bottom-width: 1pt;border-right-style: solid; border-right-width: 1pt"><p
					style="text-indent: 0pt; text-align: left;">
					<br />
				</p>
				<p class="s6"
					style="padding-left: 0pt; text-indent: 0pt; text-align: left;">SGST</p></td>
			<td
				style="width: 30pt; border-top-style: solid; border-top-width: 1pt; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt;"><p
					style="text-indent: 0pt; text-align: left;">
					<br />
				</p>
				</td>
			<td
				style="width: 60pt; border-top-style: solid; border-top-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 2pt"><p
					style="text-indent: 0pt; text-align: left;">
					<br />
				</p>
				<p class="s6"
					style="padding-left: 0pt; text-indent: 0pt; text-align: left;">CGST</p></td>
		</tr>
		
		<c:forEach items="${billdetails}" var="billdetails" varStatus="status">
		
		<tr style="height: 19pt">
			<td
				style="width: 15pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 2pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt"><p
					style="text-indent: 0pt; text-align: center;">
					<br />
					${status.index+1}
				</p></td>
			<td
				style="width: 150pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt"><p
					style="text-indent: 0pt; text-align: center;">
					<br />
					${billdetails.externalid}
				</p></td>
			<td
				style="width: 45pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt"><p
					style="text-indent: 0pt; text-align: left;">
					<br />
					${billdetails.batchno}
				</p></td>
			<td
				style="width: 45pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt"><p
					style="text-indent: 0pt; text-align: left;">
					<br />
					${billdetails.quantity}
				</p></td>
			<td
				style="width: 30pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt"><p
					style="text-indent: 0pt; text-align: left;">
					<br />
					${billdetails.}
				</p></td>
			<td
				style="width: 45pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt"><p
					style="text-indent: 0pt; text-align: left;">
					<br />
				</p></td>
			<td
				style="width: 45pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt"><p
					style="text-indent: 0pt; text-align: left;">
					<br />
				</p></td>
			<td
				style="width: 30pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; "><p
					style="text-indent: 0pt; text-align: left;">
					<br />
				</p></td>
			<td
				style="width: 60pt; border-top-style: solid; border-top-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt"><p
					style="text-indent: 0pt; text-align: left;">
					<br />
				</p></td>
			<td
				style="width: 30pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; "><p
					style="text-indent: 0pt; text-align: left;">
					<br />
					 <c:set var="itemTotal" value="${itemTotal + salesrate * billdetails.quantity}" />
								<fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${salesrate * billdetails.quantity}" />
				</p></td>
			<td
				style="width: 60pt; border-top-style: solid; border-top-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 2pt"><p
					style="text-indent: 0pt; text-align: left;">
					<br />
				</p></td>
		</tr>
		</c:forEach>
		<tr style="height: 19pt">
			<td
				style="width: 105pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 2pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt"
				colspan="2"><p class="s5"
					style="padding-top: 5pt; padding-left: 4pt; text-indent: 0pt; text-align: left;">Transporter
					Name</p></td>
			<td
				style="width: 105pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt"><p
					style="text-indent: 0pt; text-align: left;">
					<br />
				</p></td>
			<td
				style="width: 75pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt"
				colspan="2"><p class="s5"
					style="padding-top: 6pt; padding-left: 3pt; text-indent: 0pt; text-align: left;">SGST</p></td>
			<td
				style="width: 90pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt"
				colspan="2"><p style="text-indent: 0pt; text-align: left;">
					<br />
				</p></td>
			<td
				style="width: 30pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt"><p
					style="text-indent: 0pt; text-align: left;">
					<br />
				</p></td>
			<td
				style="width: 60pt; border-top-style: solid; border-top-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt"><p
					style="text-indent: 0pt; text-align: left;">
					<br />
				</p></td>
			<td
				style="width: 30pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt"><p
					style="text-indent: 0pt; text-align: left;">
					<br />
				</p></td>
			<td
				style="width: 60pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 2pt"><p
					style="text-indent: 0pt; text-align: left;">
					<br />
				</p></td>
		</tr>
		<tr style="height: 19pt">
			<td
				style="width: 105pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 2pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt"
				colspan="2"><p class="s5"
					style="padding-top: 5pt; padding-left: 4pt; text-indent: 0pt; text-align: left;">Vehicle
					No.</p></td>
			<td
				style="width: 105pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt"><p
					style="text-indent: 0pt; text-align: left;">
					<br />
				</p></td>
			<td
				style="width: 75pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt"
				colspan="2"><p class="s5"
					style="padding-top: 6pt; padding-left: 3pt; text-indent: 0pt; text-align: left;">CGST</p></td>
			<td
				style="width: 90pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt"
				colspan="2"><p style="text-indent: 0pt; text-align: left;">
					<br />
				</p></td>
			<td
				style="width: 30pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt;"><p
					style="text-indent: 0pt; text-align: left;">
					<br />
				</p></td>
			<td
				style="width: 60pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt"><p
					style="text-indent: 0pt; text-align: left;">
					<br />
				</p></td>
			<td
				style="width: 30pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt"><p
					style="text-indent: 0pt; text-align: left;">
					<br />
				</p></td>
			<td
				style="width: 60pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 2pt"><p
					style="text-indent: 0pt; text-align: left;">
					<br />
				</p></td>
		</tr>
		<tr style="height: 19pt">
			<td
				style="width: 105pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 2pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt"
				colspan="2"><p class="s5"
					style="padding-top: 4pt; padding-left: 3pt; text-indent: 0pt; text-align: left;">L.R.No.</p></td>
			<td
				style="width: 105pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt"><p
					style="text-indent: 0pt; text-align: left;">
					<br />
				</p></td>
			<td
				style="width: 75pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt"
				colspan="2"><p class="s5"
					style="padding-top: 6pt; padding-left: 3pt; text-indent: 0pt; text-align: left;">Total</p></td>
			<td
				style="width: 90pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt"
				colspan="2"><p style="text-indent: 0pt; text-align: left;">
					<br />
				</p></td>
			<td
				style="width: 30pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt"><p
					style="text-indent: 0pt; text-align: left;">
					<br />
				</p></td>
			<td
				style="width: 60pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt;"><p
					style="text-indent: 0pt; text-align: left;">
					<br />
				</p></td>
			<td
				style="width: 30pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt"><p
					style="text-indent: 0pt; text-align: left;">
					<br />
				</p></td>
			<td
				style="width: 60pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 2pt"><p
					style="text-indent: 0pt; text-align: left;">
					<br />
				</p></td>
		</tr>
		<!-- <tr style="height: 19pt">
			<td
				style="width: 105pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 2pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt"
				colspan="2"><p class="s5"
					style="padding-top: 5pt; padding-left: 3pt; text-indent: 0pt; text-align: left;">P.O.
					No. &amp; Dt.</p></td>
			<td
				style="width: 105pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt"><p
					style="text-indent: 0pt; text-align: left;">
					<br />
				</p></td>
			<td
				style="width: 75pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt"
				colspan="2"><p class="s5"
					style="padding-top: 6pt; padding-left: 3pt; text-indent: 0pt; text-align: left;">Sub
					Total</p></td>
			<td
				style="width: 90pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt"
				colspan="2"><p style="text-indent: 0pt; text-align: left;">
					<br />
				</p></td>
			<td
				style="width: 90pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt"
				colspan="2"><p style="text-indent: 0pt; text-align: left;">
					<br />
				</p></td>
			<td
				style="width: 90pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 2pt"
				colspan="2"><p style="text-indent: 0pt; text-align: left;">
					<br />
				</p></td>
		</tr> -->
		<tr style="height: 19pt">
			<td
				style="width: 105pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 2pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt"
				colspan="2"><p class="s5"
					style="padding-top: 4pt; padding-left: 3pt; text-indent: 0pt; text-align: left;">Delivery
					Challan No. &amp; Dt.</p></td>
			<td
				style="width: 105pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt"><p
					style="text-indent: 0pt; text-align: left;">
					<br />
				</p></td>
			<td
				style="width: 165pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt"
				colspan="4"><p class="s6"
					style="padding-top: 5pt; padding-left: 79pt; text-indent: 0pt; text-align: left;">Total
					Invoice Value</p></td>
			<td
				style="width: 180pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 2pt"
				colspan="4"><p style="text-indent: 0pt; text-align: left;">
					<br />
				</p></td>
		</tr>
		<tr style="height: 59pt">
			<td
				style="width: 210pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 2pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt"
				colspan="3"><p class="s6"
					style="padding-top: 4pt; padding-left: 3pt; text-indent: 0pt; text-align: left;">BANK
					DETAILS :</p></td>
			<td
				style="width: 165pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt"
				colspan="4"><p style="text-indent: 0pt; text-align: left;">
					<br />
				</p>
				<p class="s6"
					style="padding-left: 40pt; text-indent: 0pt; text-align: left;">Total
					Invoice Value(In Words)</p></td>
			<td
				style="width: 180pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 2pt"
				colspan="4"><p style="text-indent: 0pt; text-align: left;">
					<br />
				</p></td>
		</tr>
		<tr style="height: 99pt">
			<td
				style="width: 345pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 2pt; border-bottom-style: solid; border-bottom-width: 2pt; border-right-style: solid; border-right-width: 1pt"
				colspan="7"><p class="s6"
					style="padding-left: 2pt; text-indent: 0pt; text-align: left;">Terms
					&amp; Conditions</p></td>
			<td
				style="width: 210pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 2pt; border-right-style: solid; border-right-width: 2pt"
				colspan="4"><p style="text-indent: 0pt; text-align: left;">
					<br />
				</p>
				<p class="s5"
					style="padding-top: 6pt; padding-left: 131pt; text-indent: 0pt; line-height: 9pt; text-align: left;">Authorized
					Signatory</p></td>
		</tr>
	</table>
	<p style="text-indent: 0pt; text-align: left;" />
</body>
</html>
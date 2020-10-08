<%-- 
    Document   : Payment_Voucher
    Created on : 06/10/2020, 3:16 PM
    Author     : Musaib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
<title>Payment Voucher</title>
<head>
<style type="text/css">
<!--
.headerText {
	width: 10px;
	font-family: Tahoma;
	font-size: 12px;
	color: #FFFFFF;
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
	font-family: Tahoma;
	font-size: 14px;
	font-style: normal;
	text-transform: capitalize;
	color: #325F6D;
	text-align: right;
	vertical-align: middle;
	font-weight: bold;
}

.dataTextBoldCenter {
	font-weight: bold;
	font-family: Tahoma;
	color: black;
	font-size: 22px;
	letter-spacing: normal;
	text-align: center;
}

.addressLine{
	font-weight: normal;
	font-family: ariel;
	color: black;
	font-size: 18px;
	letter-spacing: normal;
	text-align: center;
	text-decoration: underline;
}

.dataText {
	font-family: Tahoma;
	color: black;
	font-size: 12px;
	letter-spacing: normal;
	text-align: center;
}

.myclass {
	border-top-style: solid;
	border-right-style: solid;
	border-bottom-style: solid;
	border-left-style: solid;
	border-top-color: #5d7e9b;
	border-right-color: #5d7e9b;
	border-bottom-color: #5d7e9b;
	border-left-color: #5d7e9b;
	border-top-width: 1px;
	border-right-width: 1px;
	border-bottom-width: 1px;
	border-left-width: 1px;
	width: auto;
	height: auto;
	color: black;
	text-transform: capitalize;
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

	

</head>




        @media print {
            .fontsize { font-size: 25px ;
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
            .fontsize { font-size: 25px;
                        font-weight: bold;
                        font-family: 'Times New Roman'
            }
            .bodymargin{
                margin-left: 0px ;
                margin-right: 0px;
            }
        }
    </style>




<body style="text-align: center" class="bodymargin" onload="window.print();">
	<form method="post" class="bodymargin">
		<table width="100%" style="border-collapse: collapse;">
			<tr>
				<td style="width: 100%;" align="center">
				<label class="dataTextBoldCenter">&nbsp;&nbsp;&nbsp;${branchname}</label><br><br>
				<label class="addressLine">Payment Voucher</label><br>
				</td>
			</tr>
</table>

		<TABLE  width="100%" style="border-collapse:collapse;">
                <tr>
                    <td ><hr width="100%"></td>
                </tr>
            </TABLE>

		<table  width="100%" border="0" align="left" id="table1">
		
			<tr>
			<td></td>
			
			</tr>
			
			<tr>
				<td class="dataTextBoldLeft" >Voucher Number:</td>
				<td align="left"><c:out value="${adminexpenses.idAdminExpenses}" /></td>
				<td class="dataTextBoldLeft" >Date:</td>
				<td align="left"> <fmt:formatDate value="${adminexpenses.entrydate}" pattern="dd/MM/yyyy"/></td>
			</tr>
			<tr>
                    <td colspan="4"><hr width="100%"></td>
                </tr>
			<tr>
			<td></td>
			</tr>
			<tr>
				<td class="dataTextBoldLeft" >&nbsp;&nbsp;&nbsp;Amount (Rs.):</td>
				<td align="left"><c:out	value="${adminexpenses.priceofitem}" />	</td>
			</tr>

			<tr>
			<td></td>
			</tr>
			<tr>
				<td class="dataTextBoldLeft" >Paid To:</td>
				<td align="left">  <c:out value="${adminexpenses.paidto}" /></td>
			</tr>

			<tr>
			<td></td>
			</tr>
			
			<tr>
				<td class="dataTextBoldLeft" >On Account of: </td>
				<td align="left"><c:out value="${adminexpenses.itemdescription}" /></td>
			</tr>

			<tr>
			<td></td>
			</tr>
			
			<tr>
			<td></td>
			</tr>
			
			<tr>
				<td class="dataTextBoldLeft" >&nbsp;&nbsp;&nbsp;Cheque #: </td>
				<td align="left"><c:out value="${adminexpenses.chequeno}" /> </td>
			</tr>

			<tr>
			<td></td>
			
			</tr>
			<tr>
			<td></td>
			
			</tr>

		</table>
		<TABLE id="dataTable" width="100%" border="0"
			style="page-break-after: always; border-collapse: collapse;">

			<tr>
			<td><hr width="100%"></td>
			<td><hr width="100%"></td>
			</tr>
		<tr>
			<td><br><br></td>
		</tr>
	
	<tr>

		<td><br>Prepared By</td>
		<td>Approved By</td>
	</tr>
		</TABLE>

	</form>
</body>
</html>

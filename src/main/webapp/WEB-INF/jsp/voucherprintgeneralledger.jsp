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

/* General table styling */
    .datatable {
        width: 100%;
        border-collapse: collapse; /* Ensures borders don't double up */
    }
    
    /* Table header styling */
    .datatable th.datath {
        border: 1px solid black; /* Borders for table headers */
        padding: 8px;
        background-color: #f2f2f2; /* Light gray background for headers */
        text-align: center;
    }
    
    /* Table body styling */
    .datatable td.datatd {
        border: 1px solid black; /* Borders for table cells */
        padding: 8px;
        text-align: left; /* Align text to the left */
    }
    
    /* Row styling */
    .datatable .trClass {
        background-color: #ffffff; /* White background for rows */
    }
    
    /* Optional: Hover effect for rows */
    .datatable .trClass:hover {
        background-color: #f9f9f9; /* Light gray background on hover */
    }
    
    /* Text alignment for the amount column */
    .datatable .amount-column {
        text-align: right; /* Right-align the amount column */
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
				<td align="center">
				<img src="/gnyanganga/images/gnyanganga.jpg" width="80" height="80"/>
				</td>
				<td style="width: 100%;" align="center">
				<label class="dataTextBoldCenter">&nbsp;&nbsp;&nbsp;${branchname}</label><br><br>
				<label class="addressLine">Payment Voucher</label><br>
				</td>
			</tr>
</table>

		<TABLE  width="100%" border="1" style="border-collapse:collapse;">
                <tr>

                    <td colspan="4" ></td>

                </tr>
            </TABLE>
		
            <table class="datatable">
    <thead>
        <tr>
            <th class="datath">Sl.No</th>
            <th class="datath">Voucher No.</th>
            <th class="datath">Date</th>
            <th class="datath">Dr Account -- Cr Account</th>
            <th class="datath">Narration</th>
            <th class="datath">Amount</th>
        </tr>
    </thead>
    <tbody>
        <fmt:setLocale value="en_IN" scope="session"/>
        <c:set var="vouchertotal" value="${0}" />
        <c:forEach items="${vouchertransactions}" var="vouchertransactions" varStatus="status">
            <tr class="trClass">
                <td class="datatd">
                    <c:out value="${status.index+1}" />
                </td>
                <td class="datatd"><c:out value="${vouchertransactions.key.transactionsid}" /></td>
                <td class="datatd"><c:out value="${vouchertransactions.key.transactiondate}" /></td>
                <td class="datatd"><c:out value="${vouchertransactions.value}" /></td>
                <td class="datatd"><c:out value="${vouchertransactions.key.narration}" /></td>
                <td class="datatd amount-column">
                    <c:set var="vouchertotal" value="${vouchertotal + vouchertransactions.key.dramount}" />
                    <fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${vouchertransactions.key.dramount}"/>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
			<br>
			<table width="13%" border="0" style="border-color: #4b6a84;float: right;padding-right: 15px;"
				id="myTable">

				<tbody>
					<tr align="right">
					
							<td class="dataTextRight" >
								<label style="color: #eb6000"><b>
									Total</b>
							</label> 
							</td>
							
							<td class="dataTextRight">
								<label style="color: #eb6000"><b>
									<fmt:formatNumber type="currency"  value="${vouchertotal}" />
								</b>
								</label>
							</td>
					</tr>
				</tbody>
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

		<td>Secretary</td>
		<td>Accountant</td>
		<td>Prepared By</td>
	</tr>
		</TABLE>

	</form>
</body>
</html>

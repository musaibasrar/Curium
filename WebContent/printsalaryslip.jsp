<%-- 
    Document   : Print Salary Slip
    Created on : May 01 2018, 11:18:00 AM
    Author     : Musaib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html moznomarginboxes>
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
	font-weight: bold;
	font-family: arial;
	color: #4b6a84;
	font-size: 15px;
	letter-spacing: normal;
	text-align: left;
}

.dataTextBoldCenter {
	font-weight: bold;
	font-family: Tahoma;
	color: black;
	font-size: 18px;
	letter-spacing: normal;
	text-align: center;
}

.addressLine{
	font-weight: bold;
	font-family: ariel;
	color: black;
	font-size: 14px;
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
-->
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
    </style>


</head>

<body style="text-align: center" class="bodymargin">
	<form method="post" class="bodymargin">
		<br>
		<table width="100%" style="border-collapse: collapse;">
			<tr>
				<td class="dataTextBoldCenter" style="width: 100%">
				Divine M.A. English Higher Primary & High School </td>
			</tr>
			<tr>
			<td class="addressLine"><br>PAY SLIP FOR THE MONTH OF <c:out value="${processsalarydetails.month}" /> <c:out value="${processsalarydetails.year}" /></td>
			</tr>

			<tr>
			<td></td></tr>
			<tr></tr>
</table>



		<table >
		<tr>
			<td></td>
			
			</tr>
			<tr>
			<td></td>
			
			</tr>
			<tr>
				<td class="dataTextBoldLeft" style="width: 60%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				Staff Name:&nbsp;&nbsp;&nbsp;
					 <c:out value="${processsalarydetails.teacher.teachername}" />
				</td>
			
				<td class="dataTextBoldLeft" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				Designation:&nbsp;&nbsp;&nbsp;<c:out value="${processsalarydetails.teacher.designation}" />
				</td>
			</tr>
			
			
			<tr>
			<td class="dataTextBoldLeft" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			Joining Date:&nbsp;&nbsp;&nbsp;<c:out
						value="${processsalarydetails.teacher.dateofjoining}" />
				</td>
			</tr>
			
			<tr>
			<td></td>
			
			</tr>

		</table>
		<TABLE  width="100%" border="1" style="border-collapse:collapse;">
                <tr>

                    <td colspan="4" ></td>

                </tr>
            </TABLE>
            
            
            
            <table width="50%" border="0" style="border-color: #4b6a84;float: left;margin-bottom:50px;">

				<thead>
					<tr><br></tr>
					<tr>
						
						<th>Earnings</th>
						
						<th>Amount(Rs)</th>
						
					</tr>
				</thead>

				<tbody>
				
						<c:forEach items="${earningmap}" var="earningmap">

						<tr class="trClass" style="border-color: #000000" >
							<td class="dataText" style="text-align: center"><c:out value="${earningmap.key}" /></td>
							
							<td class="dataText" style="text-align: center"><c:out value="${earningmap.value}" /></td>
							
						</tr>
					</c:forEach>
					
				</tbody>
				
			</table>
			<table width="50%" border="0" style="border-color: #4b6a84;float: left"	>

				<thead>
					<tr>
						<th>Dedcutions</th>
						<th>Amount(Rs)</th>
					</tr>
				</thead>

				<tbody>
					<c:forEach items="${deductionmap}" var="deductionmap">

						<tr class="trClass" style="border-color: #000000" >
							<td class="dataText" style="text-align: center"><c:out value="${deductionmap.key}" /></td>
							
							<td class="dataText" style="text-align: center"><c:out value="${deductionmap.value}" /></td>
							
						</tr>
					</c:forEach>
				</tbody>
				
			</table>
			<br><br><br><br><br><br><br><br><br><br>
			<table width="100%" border="1" style="border-color: #4b6a84;float: left"	>

				<thead>
					<tr>
						<th>Total Earning</th>
						<th>Total Deduction</th>
						<th>Total Pay</th>
					</tr>
				</thead>

				<tbody>

						<tr class="trClass" style="border-color: #000000" >
							<td class="dataText" style="text-align: center"><c:out value="${totalearning}" /></td>
							
							<td class="dataText" style="text-align: center"><c:out value="${totaldeduction}" /></td>
							
							<td class="dataText" style="text-align: center"><c:out value="${netpay}" /></td>
							
						</tr>
				</tbody>
				
			</table>
			<br><br>
			<TABLE id="dataTable" width="100%" border="0"
			style="page-break-after: always; border-collapse: collapse;">

			<tr>
			<td>
			<br>
			<br><br><br></td>
			</tr>
			<tr>
			<td></td>
			</tr>
			<tr>
			<td></td>
			</tr>
		<tr>
		<td></td>
		<td align="left">Accountant Signature</td>	
			<td align="centre">Principal Signature</td>
			<td align="centre">Staff's Signature</td>
			</tr>
			
			<tr>
            	<td>
            		
                   <button id="print" onclick="window.print();" 
                   this.style.visibility = 'hidden', loading.style.visibility = 'visible'" class="hide">Print</button>     
                </td>
             </tr>
		</TABLE>

	</form>
</body>
</html>

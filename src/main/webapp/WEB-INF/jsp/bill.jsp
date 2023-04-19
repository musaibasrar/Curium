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
			<div style="border: 1px solid;border-radius: 15px">
		<table width="100%" style="border-collapse: collapse;">
			<tr>
				<td align="left" style="padding-left: 50px;">
				<img src="/alfarooq/images/alfarooq.jpg" width="110" height="60"/>
				</td> 
				<td style="width: 100%;" align="left">
				<label class="dataTextBoldCenter" style="padding-left: 25px;">&nbsp;&nbsp;&nbsp;${branchname}</label><br>
				<label class="addressLine" style="padding-left: 45px;">${branchaddress}<br></label>
				<label class="addressLine" style="padding-left: 145px;">Contact:&nbsp;${branchcontact} </label>
				</td>
			</tr>
		</table>

<TABLE  width="100%" border="1" style="border-collapse:collapse;">
                <tr>
                    <td colspan="4" ></td>
                </tr>
            </TABLE>

		<table style="padding-left: 5px;">
		
			<tr>
			<td></td>
			
			</tr>
			<tr >
			
				<td class="dataTextBoldLeft" style="width: 50%;font-size: 14px;">Student
					Name:&nbsp;<label style="text-transform: capitalize;font-size: 14px;"><c:out value="${billdetailsstudentname}" /></label>
				</td>
				<td class="dataTextBoldLeft" style="width: 50%;font-size: 14px;" >Fathers
					Name:&nbsp;<label style="text-transform: capitalize;"><c:out value="${billdetailsfathername}" /></label>
				</td>
			</tr>
			<tr>
			<td></td>
			</tr>
			<tr>
				<td class="dataTextBoldLeft" style="font-size: 14px;">
					Class:&nbsp;<c:out value="${billdetailsclassstudying}" />
				</td>

				<td class="dataTextBoldLeft" style="font-size: 14px;">Date:&nbsp;<c:out
						value="${billdetailstransactiondate}" />
				</td>
				<td></td>
			</tr>

			<tr>
			<td></td>
			</tr>
			<tr>
				<td class="dataTextBoldLeft" style="font-size: 14px;">Receipt No:&nbsp;<c:out
						value="${billno}" /> 
				</td>
				<td class="dataTextBoldLeft" style="font-size: 14px;">Year:&nbsp;<c:out value="${currentAcademicYear}" />
				</td>
				<td></td>
			</tr>

			<tr>
			<td></td>
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

		<TABLE id="dataTable" width="100%" border="0"
			style="border-collapse: collapse;">


			<tr>

				<td class="headerText">Particulars</td>
				<td class="headerText">Fees Amount</td>
				

			</tr>
			
			<tr>
			
			<td><hr width="100%"></td>
			<td><hr width="100%"></td>
			<td><hr width="100%"></td>
			</tr>


			<c:forEach items="${billdetails}" var="feescatmap">
				<tr>
					<td class="dataText"><c:out
							value="${feescatmap.itemname}" /></td>
					<td class="dataText">Rs. <c:out
							value="${feescatmap.salesprice}" /></td>
					
					<%-- Rs. <td class="dataText">Rs. <c:out value="${unitdispensedose.amount}" /></td> --%>
				</tr>
			</c:forEach>
			</table>
			
			
			<TABLE width="100%" border="0">
			
			<tr>
			
			<td><hr width="100%"></td>
			<td><hr width="100%"></td>
			<td><hr width="100%"></td>
			</tr>
			
			
			<tr>
				<td class="headerText" ></td>
				<td class="headerText"></td>
				<td class="headerText" style="padding-left: 500px;"><label style="font-weight: bold;">Total:</label>&nbsp;&nbsp;&nbsp;Rs.  <c:out value="${billgrandtotal}" /></td>
			</tr>
			
			</table>
			
		<%-- <TABLE width="100%" border="0">
			<tr>
			
			<td><hr width="100%"></td>
			<td><hr width="100%"></td>
			<td><hr width="100%"></td>
			</tr>

			<tr>
				<td class="headerText">
					Total Fees: Rs. ${totalfees}
				</td>
				<td class="headerText">
					Total fees paid : Rs.  ${sumoffees}
				</td>
				<td class="headerText">
				 Total fees Due : Rs.  ${dueamount} 
				</td>
				
			</tr>
			
			<tr>
			<td><hr width="100%"></td>
			<td><hr width="100%"></td>
			<td><hr width="100%"></td>
			</tr>
			</table> --%>
			
			<TABLE width="100%" border="0">
		<tr style="font-size: 10px;">
		<td align="left" style="padding-left: 20px;">In Words:<label style="text-transform: capitalize;"> Rupees <c:out value="${billdetailstotaltotal}" /><c:out value="${duplicate}" /></label></td>
		</tr>

		<tr style="font-size: 10px;">
		<td align="left" style="padding-left: 20px;">Note: Fees once deposited will not be refunded under any Circumstances</td>
		</tr>
		</table>
		
		<TABLE width="100%" border="0">
		<tr style="font-size: 10px;">
		<%-- <td align="left" style="padding-left: 20px;">Payment Mode:&nbsp;${recieptinfo.paymenttype}<br><br>Cashier Name:<label style="text-transform: capitalize;">${username}</label>
		Date&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;${recieptdate}</td> --%>
		
		<td>Received with thanks,<br><br> Cashier/Accountant</td>
		</tr>
		</TABLE>
		</div>
		<br>
		<div style="border: 1px solid;border-radius: 15px">
		<table width="100%" style="border-collapse: collapse;">
			<tr>
				<td align="left" style="padding-left: 50px;">
				<img src="/alfarooq/images/alfarooq.jpg" width="110" height="60"/>
				</td> 
				<td style="width: 100%;" align="left">
				<label class="dataTextBoldCenter" style="padding-left: 25px;">&nbsp;&nbsp;&nbsp;${branchname}</label><br>
				<label class="addressLine" style="padding-left: 45px;">${branchaddress}<br></label>
				<label class="addressLine" style="padding-left: 145px;">Contact:&nbsp;${branchcontact} </label>
				</td>
			</tr>
		</table>

<TABLE  width="100%" border="1" style="border-collapse:collapse;">
                <tr>
                    <td colspan="4" ></td>
                </tr>
            </TABLE>

		<table style="padding-left: 5px;">
		
			<tr>
			<td></td>
			
			</tr>
			<tr >
			
				<td class="dataTextBoldLeft" style="width: 50%;font-size: 14px;">Student
					Name:&nbsp;<label style="text-transform: capitalize;font-size: 14px;"><c:out value="${student.name}" /></label>
				</td>
			
				<td class="dataTextBoldLeft" style="font-size: 14px;">UID:&nbsp;<c:out value="${student.studentexternalid}" />
				</td>
				

				

				<td class="dataTextBoldLeft" style="font-size: 14px;">&nbsp;&nbsp;&nbsp;Receipt No:&nbsp;<c:out
						value="${recieptinfo.branchreceiptnumber}" /> 
				</td>
				
				<td class="dataTextBoldLeft" style="font-size: 14px;">&nbsp;Year:&nbsp;<c:out value="${recieptinfo.academicyear}" />
				</td>

			</tr>
			<tr>
			<td></td>
			</tr>
			<tr style="font-size: 20px;">
			<td class="dataTextBoldLeft" style="width: 50%;font-size: 14px;" >Fathers
					Name:&nbsp;<label style="text-transform: capitalize;"><c:out value="${parents.fathersname}" /></label>
				</td>
			
				<td class="dataTextBoldLeft" style="font-size: 14px;">
					Class:&nbsp;<c:out value="${recieptinfo.classsec}" />
				</td>

			<td class="dataTextBoldLeft" style="font-size: 14px;">&nbsp;&nbsp;&nbsp;Date:&nbsp;<c:out
						value="${recieptdate}" />
				</td>
				
				<td></td>
			</tr>

			<tr>
			<td></td>
			
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

		<TABLE id="dataTable" width="100%" border="0"
			style="border-collapse: collapse;">


			<tr>

				<td class="headerText">Particulars</td>
				<td class="headerText">Fees Amount</td>
				

			</tr>
			
			<tr>
			
			<td><hr width="100%"></td>
			<td><hr width="100%"></td>
			<td><hr width="100%"></td>
			</tr>


			<c:forEach items="${feescatmap}" var="feescatmap">
				<tr>
					<td class="dataText"><c:out
							value="${feescatmap.key}" /></td>
					<td class="dataText">Rs. <c:out
							value="${feescatmap.value}" /></td>
					
					<%-- Rs. <td class="dataText">Rs. <c:out value="${unitdispensedose.amount}" /></td> --%>
				</tr>
			</c:forEach>
			
			</table>
			
			
			<TABLE width="100%" border="0">
			
			<tr>
			
			<td><hr width="100%"></td>
			<td><hr width="100%"></td>
			<td><hr width="100%"></td>
			</tr>
			
			
			<tr>
				<td class="headerText" ></td>
				<td class="headerText"></td>
				<td class="headerText" style="padding-left: 500px;"><label style="font-weight: bold;">Total:</label>&nbsp;&nbsp;&nbsp;Rs.  <c:out value="${recieptinfo.totalamount}" /></td>
			</tr>
			
			</table>
			
		<TABLE width="100%" border="0">
			<tr>
			
			<td><hr width="100%"></td>
			<td><hr width="100%"></td>
			<td><hr width="100%"></td>
			</tr>

			<tr>
				<td class="headerText">
					Total Fees: Rs. ${totalfees}
				</td>
				<td class="headerText">
					Total fees paid : Rs.  ${sumoffees}
				</td>
				<td class="headerText">
				 Total fees Due : Rs.  ${dueamount} 
				</td>
				
			</tr>
			
			<tr>
			<td><hr width="100%"></td>
			<td><hr width="100%"></td>
			<td><hr width="100%"></td>
			</tr>
			</table>
			
			<TABLE width="100%" border="0">
		<tr style="font-size: 10px;">
		<td align="left" style="padding-left: 20px;">In Words:<label style="text-transform: capitalize;"> Rupees <c:out value="${grandTotal}" /><c:out value="${duplicate}" /></label></td>
		</tr>

		<tr style="font-size: 10px;">
		<td align="left" style="padding-left: 20px;">Note: Fees once deposited will not be refunded under any Circumstances</td>
		</tr>
		</table>
		
		<TABLE width="100%" border="0">
		<tr style="font-size: 10px;">
		<td align="left" style="padding-left: 20px;">Payment Mode:&nbsp;${recieptinfo.paymenttype}<br><br>Cashier Name:<label style="text-transform: capitalize;">${username}</label>
		Date&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;${recieptdate}</td>
		
		<td>Received with thanks,<br><br> Cashier/Accountant</td>
		</tr>
		</TABLE>
		</div>
	</form>
	
	</div>
	<a id="print" onclick="PrintPage()">Print</a>
</body>
</html>

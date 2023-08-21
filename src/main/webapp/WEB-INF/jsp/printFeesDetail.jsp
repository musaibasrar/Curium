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
<title>FEES RECIEPT</title>
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

	

</head>




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




<body style="text-align: center" class="bodymargin">
<div style="page-break-inside: avoid;border-collapse:collapse;">
	<form method="post" class="bodymargin">
			<div style="border: 1px solid;border-radius: 15px">
		<table width="100%" style="border-collapse: collapse;">
			<tr>
				<td align="center" style="padding-left: 140px;">
				<img src="/lilyrose/images/lilyrose.jpg" width="90" height="90"/>
				</td> 
				<td style="width: 100%;" align="left">
				<label class="dataTextBoldCenter" style="padding-left: 10px;">&nbsp;&nbsp;&nbsp;${branchname}</label><br>
				<label class="addressLine" style="padding-left: 55px;font-size: 13px;">Preprimary | Primary | High School<br></label>
				<label class="addressLine" style="padding-left: 58px;font-size: 13px;">Recognised by Govt of Karnataka<br></label>
				<label class="addressLine" style="padding-left: 15px;">${branchaddress}<br></label>
				<label class="addressLine" style="padding-left: 20px;">Contact:&nbsp;${branchcontact} </label>
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
				<c:if test="${recieptinfo.fine > 0}">
				<tr>
					<td class="dataText"><c:out
							value="Fine" /></td>
					<td class="dataText">Rs. <c:out
							value="${recieptinfo.fine}" /></td>
				</tr>
			</c:if>
				
			<c:if test="${recieptinfo.misc > 0}">	
				<tr>
					<td class="dataText"><c:out
							value="Misc" /></td>
					<td class="dataText">Rs. <c:out
							value="${recieptinfo.misc}" /></td>
				</tr>
			</c:if>
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
				<td class="headerText" style="padding-left: 220px;"><label style="font-weight: bold;">Total:</label>&nbsp;&nbsp;&nbsp;Rs.  <c:out value="${recieptinfo.totalamount}" /></td>
			</tr>
			
			</table>
			
		<TABLE width="100%" border="0">
			<%-- <tr>
			
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
				
			</tr> --%>
			
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
		&nbsp;&nbsp;Date&nbsp;${recieptdate}</td>
		<td>Received with thanks,<br><br> Cashier/Accountant</td>
		</tr>
		</TABLE>
		</div>
		<br>
		<div style="border: 1px solid;border-radius: 15px">
		<table width="100%" style="border-collapse: collapse;">
			<tr>
				<td align="center" style="padding-left: 140px;">
				<img src="/lilyrose/images/lilyrose.jpg" width="70" height="70"/>
				</td> 
				<td style="width: 100%;" align="left">
				<label class="dataTextBoldCenter" style="padding-left: 10px;">&nbsp;&nbsp;&nbsp;${branchname}</label><br>
				<label class="addressLine" style="padding-left: 55px;font-size: 13px;">Preprimary | Primary | High School<br></label>
				<label class="addressLine" style="padding-left: 58px;font-size: 13px;">Recognised by Govt of Karnataka<br></label>
				<label class="addressLine" style="padding-left: 15px;">${branchaddress}<br></label>
				<label class="addressLine" style="padding-left: 20px;">Contact:&nbsp;${branchcontact} </label>
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
			
			<c:if test="${recieptinfo.fine > 0}">
				<tr>
					<td class="dataText"><c:out
							value="Fine" /></td>
					<td class="dataText">Rs. <c:out
							value="${recieptinfo.fine}" /></td>
				</tr>
			</c:if>
				
			<c:if test="${recieptinfo.misc > 0}">	
				<tr>
					<td class="dataText"><c:out
							value="Misc" /></td>
					<td class="dataText">Rs. <c:out
							value="${recieptinfo.misc}" /></td>
				</tr>
			</c:if>
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
				<td class="headerText" style="padding-left: 220px;"><label style="font-weight: bold;">Total:</label>&nbsp;&nbsp;&nbsp;Rs.  <c:out value="${recieptinfo.totalamount}" /></td>
			</tr>
			
			</table>
			
		<TABLE width="100%" border="0">
			<%-- <tr>
			
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
				
			</tr> --%>
			
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
		&nbsp;&nbsp;Date&nbsp;${recieptdate}</td>
		<td>Received with thanks,<br><br> Cashier/Accountant</td>
		</tr>
		</TABLE>
		</div>
	</form>
	
	</div>
</body>
</html>

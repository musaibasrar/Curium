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
	//font-family: Tahoma;
	color: black;
	font-size: 8px;
	letter-spacing: normal;
	text-align: left;
}

.dataTextBoldCenter {
	font-weight: bold;
	//font-family: Tahoma;
	color: black;
	font-size: 20px;
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




<body style="text-align: center;font-weight:bold;" class="bodymargin">
<div style="page-break-inside: avoid;border-collapse:collapse;">
	<form method="post" class="bodymargin">
			<div style="border: 1px solid;border-radius: 15px;padding:0px;">
			
			<table width="100%" style="page-break-inside: avoid;border-collapse: collapse;margin-left: auto;margin-right: auto;">
                        		
			<tr>
				<td rowspan="2" style="border-right:1px solid black;text-align:center;padding:7px;"><img src="/hamidullah/images/hamidullah.jpg" width="80" height="80"/></td>
				<td>
				<label class="addressLine">Quadri Group of Institutions</label><br>
				<label class="dataTextBoldCenter" style="text-transform: uppercase;">${branchname}&nbsp;MANGALGI</label><br>
				<label class="addressLine">Post:${branchaddress},&nbsp;PHONE NO: 8483-278786</label>
				</td>
			</tr>
			   
		</table>
		

<TABLE  width="100%" border="1" style="border-collapse:collapse;">
                <tr>
                    <td colspan="4" ></td>
                </tr>
            </TABLE>

		<table width="100%" align="center" style="padding:0px;border-collapse:collapse;">
		
			<tr>
			<td width="20%"  style="border:1px solid black;text-align:center;margin:0px;">
			FEES RECEIPT </td>
			<td colspan="3" style="border:1px solid black;text-align:center;margin:0px;"> SCHOOL COPY</td>
			 <td colspan="2" style="border:1px solid black;text-align:center;margin:0px;">
			 <c:set var="yearParts" value="${fn:split(recieptinfo.academicyear, '/')}" />
			  Academic Year&nbsp;${yearParts[0]}-${yearParts[1]}</td>
			</tr>
			<tr>
			<td style="border:1px solid black;">
			Receipt No:&nbsp;</td>
			<td style="border:1px solid black;"><c:out	value="${recieptinfo.branchreceiptnumber}" />
			</td>
			<td style="border:1px solid black;">
			UID No.&nbsp;</td>
			<td style="border:1px solid black;"><c:out	value="${student.studentexternalid}" />
			</td>
			<td style="border:1px solid black;">
			SATS 
			</td>
			<td style="border:1px solid black;font-size:12px"><c:out value="${student.sts}" /></td>
			</tr>
			<tr>
			<td  style="border:1px solid black;text-align:center;">Student's
					Name:&nbsp;</td>
					<td width="55%" colspan="3" style="border:1px solid black;"><label style="text-transform: capitalize;font-size: 14px;"><c:out value="${student.name}" /></td>
			<td style="border:1px solid black;text-align:center;">Class&nbsp;&nbsp;</td><td style="border:1px solid black;">
			<c:set var="classParts" value="${fn:split(recieptinfo.classsec, '--')}"/>
			<c:out value="${classParts[0]}" /></td>
			<td></td>
			</tr>
			<tr>
			<td style="border:1px solid black;text-align:center;">Father's
					Name:&nbsp;</td><td colspan="3" style="border:1px solid black;"><label style="text-transform: capitalize;"><c:out value="${parents.fathersname}" /></label></td>
			<td style="border:1px solid black;text-align:center;">Section&nbsp;
			</td><td style="border:1px solid black;"><c:out value="${classParts[1]}" /></td>
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

				<td class="headerText" style="font-weight:bold;">Particulars of Fees</td>
				<td class="headerText" style="font-weight:bold;">Fees Amount</td>
				

			</tr>
			
			<tr>
			
			<td><hr width="100%"></td>
			<td><hr width="100%"></td>
			<td><hr width="100%"></td>
			</tr>


			<c:forEach items="${feescatmap}" var="feescatmap">
				<tr>
					<td class="dataText" style="font-weight:bold;"><c:out
							value="${feescatmap.key}" /></td>
					<td class="dataText" style="font-weight:bold;">Rs. <c:out
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
				<td class="headerText" style="padding-left: 220px;font-weight:bold;""><label>Total:</label>&nbsp;&nbsp;&nbsp;Rs.  <c:out value="${recieptinfo.totalamount}" /></td>
			</tr>
			
			</table>
			
		<TABLE width="100%" border="0">
			<tr>
			
			<td><hr width="100%"></td>
			<td><hr width="100%"></td>
			<td><hr width="100%"></td>
			</tr>

			<tr>
				<td class="headerText" style="font-weight:bold;">
					Total Fees: Rs. ${totalfees}
				</td>
				<td class="headerText" style="font-weight:bold;">
					Total fees paid : Rs.  ${sumoffees}
				</td>
				<td class="headerText" style="font-weight:bold;">
				 Total fees Due : Rs.  ${dueamount} 
				</td>
				
			</tr>
			
			<!-- <tr>
			<td><hr width="100%"></td>
			<td><hr width="100%"></td>
			<td><hr width="100%"></td>
			</tr> -->
			</table>
			
			<TABLE width="100%" style="border-top:1px solid black;border-collapse:collapse;">
			<tr>
			<td><br></td>
			<td style="border-left:1px solid black"></td>
		<tr style="font-size: 10px;">
		<td align="left" style="padding-left: 20px;font-weight:bold;">In Words:<label style="text-transform: capitalize;"> Rupees <c:out value="${grandTotal}" /><c:out value="${duplicate}" /></label></td>
		<td style="border-left:1px solid black;border-collapse:collapse;"></td>
		</tr>
		 <tr style="font-size: 10px;">
		<td align="left" style="padding-left: 20px;font-weight:bold;">Payment Mode:&nbsp;${recieptinfo.paymenttype}&nbsp;&nbsp;&nbsp;</label>
		</td>
		<td style="border-left:1px solid black;border-collapse:collapse;"></td>
		</tr>
		<tr style="font-size: 10px;">
		<td align="left" style="padding-left: 20px;font-weight:bold;">Received with thanks.
		</td>
		<td style="border-left:1px solid black;border-collapse:collapse;">Accountant</td>
		</tr>
		<tr style="font-size: 10px;">
		<td align="left" style="padding-left: 20px;font-weight:bold;">Note: Fees once deposited will not be refunded under any Circumstances
		</td>
		<td style="border-left:1px solid black;border-collapse:collapse;">Date&nbsp;:&nbsp;${recieptdate}</td>
		</tr>
       
		</TABLE>
		</div>
		<br>
		<br>
		<br>
		<div style="border: 1px solid;border-radius: 15px">
		<table width="100%" style="page-break-inside: avoid;border-collapse: collapse;margin-left: auto;margin-right: auto;">
                        		
			<tr>
				<td rowspan="2" style="border-right:1px solid black;text-align:center;padding:7px;"><img src="/hamidullah/images/hamidullah.jpg" width="80" height="80"/></td>
				<td>
				<label class="addressLine">Quadri Group of Institutions</label><br>
				<label class="dataTextBoldCenter" style="text-transform: uppercase;">${branchname}&nbsp;MANGALGI</label><br>
				<label class="addressLine">Post:${branchaddress},&nbsp;PHONE NO: 8483-278786</label>
				</td>
			</tr>
			   
		</table>
		

<TABLE  width="100%" border="1" style="border-collapse:collapse;">
                <tr>
                    <td colspan="4" ></td>
                </tr>
            </TABLE>

		<table width="100%" align="center" style="padding:0px;border-collapse:collapse;">
		
			<tr>
			<td width="20%"  style="border:1px solid black;text-align:center;margin:0px;">
			FEES RECEIPT </td>
			<td colspan="3" style="border:1px solid black;text-align:center;margin:0px;"> STUDENT COPY</td>
			 <td colspan="2" style="border:1px solid black;text-align:center;margin:0px;">
			 <c:set var="yearParts" value="${fn:split(recieptinfo.academicyear, '/')}" />
			  Academic Year&nbsp;${yearParts[0]}-${yearParts[1]}</td>
			</tr>
			<tr>
			<td style="border:1px solid black;">
			Receipt No:&nbsp;</td>
			<td style="border:1px solid black;"><c:out	value="${recieptinfo.branchreceiptnumber}" />
			</td>
			<td style="border:1px solid black;">
			UID No.&nbsp;</td>
			<td style="border:1px solid black;"><c:out	value="${student.studentexternalid}" />
			</td>
			<td style="border:1px solid black;">
			SATS 
			</td>
			<td style="border:1px solid black;font-size:12px"><c:out value="${student.sts}" /></td>
			</tr>
			<tr>
			<td  style="border:1px solid black;text-align:center;">Student's
					Name:&nbsp;</td>
					<td width="55%" colspan="3" style="border:1px solid black;"><label style="text-transform: capitalize;font-size: 14px;"><c:out value="${student.name}" /></td>
			<td style="border:1px solid black;text-align:center;">Class&nbsp;&nbsp;</td><td style="border:1px solid black;">
			<c:set var="classParts" value="${fn:split(recieptinfo.classsec, '--')}"/>
			<c:out value="${classParts[0]}" /></td>
			<td></td>
			</tr>
			<tr>
			<td style="border:1px solid black;text-align:center;">Father's
					Name:&nbsp;</td><td colspan="3" style="border:1px solid black;"><label style="text-transform: capitalize;"><c:out value="${parents.fathersname}" /></label></td>
			<td style="border:1px solid black;text-align:center;">Section&nbsp;
			</td><td style="border:1px solid black;"><c:out value="${classParts[1]}" /></td>
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

				<td class="headerText" style="font-weight:bold;">Particulars of Fees</td>
				<td class="headerText" style="font-weight:bold;">Fees Amount</td>
				

			</tr>
			
			<tr>
			
			<td><hr width="100%"></td>
			<td><hr width="100%"></td>
			<td><hr width="100%"></td>
			</tr>


			<c:forEach items="${feescatmap}" var="feescatmap">
				<tr>
					<td class="dataText" style="font-weight:bold;"><c:out
							value="${feescatmap.key}" /></td>
					<td class="dataText" style="font-weight:bold;">Rs. <c:out
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
				<td class="headerText" style="padding-left: 220px;font-weight:bold;""><label>Total:</label>&nbsp;&nbsp;&nbsp;Rs.  <c:out value="${recieptinfo.totalamount}" /></td>
			</tr>
			
			</table>
			
		<TABLE width="100%" border="0">
			<tr>
			
			<td><hr width="100%"></td>
			<td><hr width="100%"></td>
			<td><hr width="100%"></td>
			</tr>

			<tr>
				<td class="headerText" style="font-weight:bold;">
					Total Fees: Rs. ${totalfees}
				</td>
				<td class="headerText" style="font-weight:bold;">
					Total fees paid : Rs.  ${sumoffees}
				</td>
				<td class="headerText" style="font-weight:bold;">
				 Total fees Due : Rs.  ${dueamount} 
				</td>
				
			</tr>
			
			<!-- <tr>
			<td><hr width="100%"></td>
			<td><hr width="100%"></td>
			<td><hr width="100%"></td>
			</tr> -->
			</table>
			
			<TABLE width="100%" style="border-top:1px solid black;border-collapse:collapse;">
			<tr>
			<td><br></td>
			<td style="border-left:1px solid black"></td>
		<tr style="font-size: 10px;">
		<td align="left" style="padding-left: 20px;font-weight:bold;">In Words:<label style="text-transform: capitalize;"> Rupees <c:out value="${grandTotal}" /><c:out value="${duplicate}" /></label></td>
		<td style="border-left:1px solid black;border-collapse:collapse;"></td>
		</tr>
		 <tr style="font-size: 10px;">
		<td align="left" style="padding-left: 20px;font-weight:bold;">Payment Mode:&nbsp;${recieptinfo.paymenttype}&nbsp;&nbsp;&nbsp;</label>
		</td>
		<td style="border-left:1px solid black;border-collapse:collapse;"></td>
		</tr>
		<tr style="font-size: 10px;">
		<td align="left" style="padding-left: 20px;font-weight:bold;">Received with thanks.
		</td>
		<td style="border-left:1px solid black;border-collapse:collapse;">Accountant</td>
		</tr>
		<tr style="font-size: 10px;">
		<td align="left" style="padding-left: 20px;font-weight:bold;">Note: Fees once deposited will not be refunded under any Circumstances
		</td>
		<td style="border-left:1px solid black;border-collapse:collapse;">Date&nbsp;:&nbsp;${recieptdate}</td>
		</tr>
       
		</TABLE>
		</div>
	</form>
	
	</div>
</body>
</html>

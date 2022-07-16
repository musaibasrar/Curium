<%-- 
    Document   : ${name}
    Created on : ${date}, ${time}
    Author     : ${user}
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="java.util.*" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
<title>DUPLICATE FEES RECEIPT</title>
<head>
<style type="text/css">
.dataText {
	font-family: Tahoma;
	color: black;
	font-size: 12px;
	letter-spacing: normal;
	text-align: center;
}
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
	font-family: Tahoma;
	color: black;
	font-size: 12px;
	letter-spacing: normal;
	text-align: left;
}

.dataTextBoldCenter {
	font-weight: bold;
	font-family: Tahoma;
	color: black;
	font-size: 28px;
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

.footer{
	font-size: 10px;
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

	

</head>




        @media print {
            .fontsize { font-size: 12px ;
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
                margin-left: 0px ;
                margin-right: 0px;
            }
        }
    </style>




<body style="text-align: center;" class="bodymargin">

<div style="page-break-inside: avoid;border-collapse:collapse;">
	<form method="post" class="bodymargin">
		<div style="border: 1px solid;border-radius: 15px">
		<table width="100%" style="border-collapse: collapse;">
			<tr>
				<td align="left" style="padding-left: 50px;">
				<img src="images/shaheenlogo.png" width="160" height="80"/>
				</td> 
				<td style="width: 100%;" align="left">
				<label class="dataTextBoldCenter">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${branchname}</label><br>
				<label class="addressLine" style="padding-left: 100px;">${branchaddress}<br></label>
				<label class="addressLine" style="padding-left: 70px;">Contact:&nbsp;${branchcontact} </label><br>
				<label class="addressLine" style="padding-left: 110px;">Duplicate Receipt</label>
				</td>
				
			</tr>
</table>

<TABLE  width="100%" border="1" style="border-collapse:collapse;">
                <tr>
                    <td colspan="4" ></td>
                </tr>
            </TABLE>

		<table style="padding-left: 30px;">
		
			<tr>
			<td></td>
			
			</tr>
			<tr >
			
				<td class="dataTextBoldLeft" style="width: 50%">Student
					Name:&nbsp;<c:out value="${student.name}" />
				</td>
			
				<td class="dataTextBoldLeft" style="width: 30%">Admission No:&nbsp;<c:out value="${student.admissionnumber}" />
				</td>
				

				

				<td class="dataTextBoldLeft" >&nbsp;&nbsp;&nbsp;Receipt No:&nbsp;<c:out
						value="${recieptinfo.branchreceiptnumber}" />
				</td>

			</tr>
			<tr>
			<td></td>
			</tr>
			<tr>
			<td class="dataTextBoldLeft" style="width: 50%">Fathers
					Name:&nbsp;<c:out value="${parents.fathersname}" />
				</td>
			
				<td class="dataTextBoldLeft" style="width: 30%">
					Class & Sec:&nbsp;<c:out value="${recieptinfo.classsec}" />
				</td>

			<td class="dataTextBoldLeft" >&nbsp;&nbsp;&nbsp;Date:&nbsp;<c:out
						value="${recieptdate}" />
				</td>
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

				<td class="headerText" style="font-weight: bold">Particulars</td>
				<td class="headerText" style="font-weight: bold">Fees Amount</td>
				

			</tr>
			
			<tr>
			
			<td><hr width="100%"></td>
			<td><hr width="100%"></td>
			<td><hr width="100%"></td>
			</tr>


			<c:forEach items="${feescatmap}" var="feescatmap" varStatus="loop">
				
				<c:if test="${loop.index>0}">
					<tr>
						<td><hr width="100%"></td>
						<td><hr width="100%"></td>
						<td><hr width="100%"></td>
					</tr>
				</c:if>
				
				<tr>
					<td class="dataText"><c:out
							value="${feescatmap.key}" /></td>
					<td class="dataText">Rs. <c:out
							value="${feescatmap.value}" /></td>
					
					<%-- Rs.<td class="dataText">Rs. <c:out value="${unitdispensedose.amount}" /></td> --%>
				</tr>
				
			</c:forEach>
			
			
			<tr>
			
			<td><hr width="100%"></td>
			<td><hr width="100%"></td>
			<td><hr width="100%"></td>
			</tr>
			
			
			<tr>
				<!-- <td>&nbsp;</td> -->
				<td class="headerText" style="font-weight: bold;" >
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				Total</td>
				<td class="headerText">Rs. <c:out
						value="${recieptinfo.totalamount}" /></td>
			</tr>
			

<tr>
			
			<td><hr width="100%"></td>
			<td><hr width="100%"></td>
			<td><hr width="100%"></td>
			</tr>
<tr>
<td >In Words: Rupees <c:out value="${grandTotal}" /><c:out value="${duplicate}" /></td>
<td></td>
</tr>
<tr>
<td align="left" class="footer">Payment Mode&nbsp;:&nbsp;${recieptinfo.paymenttype}<br>Cashier Name&nbsp;&nbsp;:&nbsp;<label style="text-transform: capitalize;">${user.username}</label><br> Date&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;${recieptdate}</td>
</tr>
<tr>
<td align="left" class="footer">Note&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;Fees once deposited will not be refunded under any Circumstances</td>
</tr>
<tr>
<td><br></td>
</tr>
<tr>
<td align="right"><br>Signature of Cashier/Accountant</td>
</tr>
		</TABLE>
	</div>
	<br>
	<div style="border: 1px solid;border-radius: 15px">
		<table width="100%" style="border-collapse: collapse;">
			<tr>
				<td align="left" style="padding-left: 50px;">
				<img src="images/shaheenlogo.png" width="160" height="80"/>
				</td> 
				<td style="width: 100%;" align="left">
				<label class="dataTextBoldCenter">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${branchname}</label><br>
				<label class="addressLine" style="padding-left: 100px;">${branchaddress}<br></label>
				<label class="addressLine" style="padding-left: 70px;">Contact:&nbsp;${branchcontact} </label><br>
				<label class="addressLine" style="padding-left: 110px;">Duplicate Receipt</label>
				</td>
			</tr>
</table>

<TABLE  width="100%" border="1" style="border-collapse:collapse;">
                <tr>
                    <td colspan="4" ></td>
                </tr>
            </TABLE>

		<table style="padding-left: 30px;">
		
			<tr>
			<td></td>
			
			</tr>
			<tr >
			
				<td class="dataTextBoldLeft" style="width: 50%">Student
					Name:&nbsp;<c:out value="${student.name}" />
				</td>
			
				<td class="dataTextBoldLeft" style="width: 30%">Admission No:&nbsp;<c:out value="${student.admissionnumber}" />
				</td>
				

				

				<td class="dataTextBoldLeft" >&nbsp;&nbsp;&nbsp;Receipt No:&nbsp;<c:out
						value="${recieptinfo.branchreceiptnumber}" />
				</td>

			</tr>
			<tr>
			<td></td>
			</tr>
			<tr>
			<td class="dataTextBoldLeft" style="width: 50%">Fathers
					Name:&nbsp;<c:out value="${parents.fathersname}" />
				</td>
			
				<td class="dataTextBoldLeft" style="width: 30%">
					Class & Sec:&nbsp;<c:out value="${recieptinfo.classsec}" />
				</td>

			<td class="dataTextBoldLeft" >&nbsp;&nbsp;&nbsp;Date:&nbsp;<c:out
						value="${recieptdate}" />
				</td>
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

				<td class="headerText" style="font-weight: bold">Particulars</td>
				<td class="headerText" style="font-weight: bold">Fees Amount</td>
				

			</tr>
			
			<tr>
			
			<td><hr width="100%"></td>
			<td><hr width="100%"></td>
			<td><hr width="100%"></td>
			</tr>


			<c:forEach items="${feescatmap}" var="feescatmap" varStatus="loop">
			
				<c:if test="${loop.index>0}">
					<tr>
						<td><hr width="100%"></td>
						<td><hr width="100%"></td>
						<td><hr width="100%"></td>
					</tr>
				</c:if>
				
				<tr>
					<td class="dataText"><c:out
							value="${feescatmap.key}" /></td>
					<td class="dataText">Rs. <c:out
							value="${feescatmap.value}" /></td>
					
					<%-- Rs.<td class="dataText">Rs. <c:out value="${unitdispensedose.amount}" /></td> --%>
				</tr>
			</c:forEach>
			
			
			<tr>
			
			<td><hr width="100%"></td>
			<td><hr width="100%"></td>
			<td><hr width="100%"></td>
			</tr>
			
			
			<tr>
				<!-- <td>&nbsp;</td> -->
				<td class="headerText" style="font-weight: bold;" >
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				Total</td>
				<td class="headerText">Rs. <c:out
						value="${recieptinfo.totalamount}" /></td>
			</tr>
			

<tr>
			
			<td><hr width="100%"></td>
			<td><hr width="100%"></td>
			<td><hr width="100%"></td>
			</tr>
<tr>
<td >In Words: Rupees <c:out value="${grandTotal}" /><c:out value="${duplicate}" /></td>
<td></td>
</tr>
<tr>
<td align="left" class="footer">Payment Mode&nbsp;:&nbsp;${recieptinfo.paymenttype}<br>Cashier Name&nbsp;&nbsp;:&nbsp;<label style="text-transform: capitalize;">${user.username}</label><br> Date&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;${recieptdate}</td>
</tr>
<tr>
<td align="left" class="footer">Note&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;Fees once deposited will not be refunded under any Circumstances</td>
</tr>
<tr>
<td><br></td>
</tr>
<tr>
<td align="right"><br>Signature of Cashier/Accountant</td>
</tr>
		</TABLE>
	</div>
	
	</form>
	</div>
</body>
</html>

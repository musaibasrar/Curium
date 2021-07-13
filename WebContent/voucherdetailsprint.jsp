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
<title>CONTRIBUTION RECIEPT</title>
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
	font-family: Tahoma;
	color: black;
	font-size: 18px;
	letter-spacing: normal;
	text-align: left;
}

.dataTextBoldCenter {
	font-weight: bold;
	font-family: Tahoma;
	color: black;
	font-size: 32px;
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




<body style="text-align: center" class="bodymargin">
<div style="border: 1px solid;border-radius: 15px">
	<form method="post" class="bodymargin">
		<br>
		<table width="100%" style="border-collapse: collapse;">
			<tr>
				<td>
				<img src="images/bill.jpg" width="600" height="100"/>
				</td> 
				
			</tr>
		</table>

		<table>
		
			<tr>
			<td></td>
			
			</tr>
			<tr>
			
				<td class="dataTextBoldLeft" style="padding-left: 60px;">Receipt No:<c:out
						value="${vouchernumber}" />
				</td>
				
				<td class="dataTextBoldLeft" style="padding-left: 350px;">
					&nbsp;&nbsp;&nbsp;Date: <c:out value="${date}" />
				</td>


		</table>
		
		<table>
		
			<tr>
			<td></td>
			
			</tr>
			<tr>
			
				<td class="dataTextBoldLeft" style="padding-left: 50px;">
				<br>
				<label style="font-size: 26px;font-weight: normal;">Received with thanks from&nbsp;</label> 
					<label style="font-size: 26px;border-bottom: 1px dotted #000;text-decoration: none;">
					<c:out value="${membername}" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label><br><br>
					<label style="font-size: 26px;font-weight: normal;">the sum of rupees&nbsp; </label>
					<label style="font-size: 26px;border-bottom: 1px dotted #000;text-decoration: none;font-weight: bold;">
					 <c:out value="${grandTotal}" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label><br>
				</td>
			</tr>
			<tr>
			<td></td>
			</tr>
		</table>


			<TABLE width="100%" border="0" style="page-break-after: always;">

				<tr>
				
					<td align="left" style="padding-left: 60px;"><br><br><label style="border-style: solid;padding:15px;font-size: 22px; ">&nbsp; Rs. <c:out value="${totalamount}" />&nbsp;&nbsp;</label><br><br></td>
					 
					 <td align="left" style="padding-left: 60px;"><br><br><label style="padding-left: 600;font-size: 22px;">Collector</label><br><br></td>
					 
					 <td align="left" style="padding-left: 60px;"><br><br><label style="font-size: 22px;">President/Secretary</label><br><br></td>

				</tr>
			</TABLE>
			
		<%-- <table>
		
			<tr>
			<td></td>
			
			</tr>
			<tr>
			
				<td class="dataTextBoldLeft" style="padding-left: 50px;">Member Name: <c:out value="${membername}" />
				</td>
			
				<td class="dataTextBoldLeft" style="width: 30%">Admission No:<c:out value="${student.admissionnumber}" />
				</td>
				

				

				<td class="dataTextBoldLeft" style="padding-left: 200px;">Receipt No:<c:out
						value="${vouchernumber}" />
				</td>
				
				<td class="dataTextBoldLeft" >&nbsp;&nbsp;&nbsp;Date: <c:out
						value="${date}" />
				</td>

			</tr>
			<tr>
			<td></td>
			</tr>
			<tr>
			<td class="dataTextBoldLeft" style="width: 50%">Fathers
					Name: <c:out value="${parents.fathersname}" />
				</td>
			
				<td class="dataTextBoldLeft" style="width: 30%">Class & Sec:<c:out value="${student.classstudying}" />
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
			style="page-break-after: always; border-collapse: collapse;">


			<tr>

				<td class="headerText" style="font-weight: bold">Particulars</td>
				<td class="headerText" style="font-weight: bold">Amount</td>
				

			</tr>
			
			<tr>
			
			<td><hr width="100%"></td>
			<td><hr width="100%"></td>
			<td><hr width="100%"></td>
			</tr>


				<tr>
					<td class="dataText"><c:out
							value="Donation" /></td>
					<td class="dataText">&#x20B9; <c:out
							value="${totalamount}" /></td>
					
					<td class="dataText">Rs. <c:out value="${unitdispensedose.amount}" /></td>
				</tr>
			<tr>
			<td></td>
			<td></td>
			</tr>
			
			<tr>
			<td></td>
			<td></td>
			</tr>
			<tr>
			<td></td>
			<td></td>
			</tr>
			<tr>
			<td></td>
			<td></td>
			</tr>
			<tr>
			<td></td>
			<td></td>
			</tr>
			<tr>
			<td></td>
			<td></td>
			</tr>
			<tr>
			<td></td>
			<td></td>
			</tr>
			<tr>
			<td></td>
			<td></td>
			</tr>
			<tr>
			<td></td>
			<td></td>
			</tr>
			<tr>
			<td></td>
			<td></td>
			</tr>
			<tr>
			<td></td>
			<td></td>
			</tr>
			<tr>
			<td></td>
			<td></td>
			</tr>
			<tr>
			<td></td>
			<td></td>
			</tr>
			<tr>
			<td></td>
			<td></td>
			</tr>
			
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
				<td class="headerText">&#x20B9; <c:out
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

<!-- <tr>
<td align="left">Note: Fees once deposited will not be refunded under any Circumstances</td>
</tr>
 -->
<tr>
<td><br><br></td>
</tr>
<tr>

<td><br>Accountant</td>

<td style="padding-right: 200px;">Chairman</td>

</tr>
		</TABLE> --%>


	</form>
	</div>
</body>
</html>

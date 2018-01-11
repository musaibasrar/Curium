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

<html moznomarginboxes >
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
	font-size: 12px;
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
	font-weight: normal;
	font-family: ariel;
	color: black;
	font-size: 10px;
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
	<form method="post" class="bodymargin">
		<br>
		<table width="100%" style="border-collapse: collapse;">
			<tr>
				<td class="dataTextBoldCenter" style="width: 100%">
				
				Divine M.A. English Higher Primary & High School </td>
			</tr>
			<tr>
			<td class="addressLine">Astana Road, Nai Kaman, Bidar. Ph.No- +91-8095248270</td>
			</tr>

			<tr>
			<td></td></tr>
			<tr></tr>
</table>

<TABLE  width="100%" border="1" style="border-collapse:collapse;">
                <tr>

                    <td colspan="4" ></td>

                </tr>
            </TABLE>

		<table>
		<tr>
			<td></td>
			
			</tr>
			<tr>
			<td></td>
			
			</tr>
			<tr>
			
				<td class="dataTextBoldLeft" style="width: 40%">Student
					Name: <c:out value="${student.name}" />
				</td>
			
				<td class="dataTextBoldLeft" >Admission No:<c:out value="${student.admissionnumber}" />
				</td>
				

				

				<td class="dataTextBoldLeft" >&nbsp;&nbsp;&nbsp;Receipt No:<c:out
						value="${feesdetails.feesdetailsid}" />
				</td>

			</tr>
			<tr>
			<td></td>
			</tr>
			<tr>
			<td class="dataTextBoldLeft" style="width: 40%">Fathers
					Name: <c:out value="${parents.fathersname}" />
				</td>
			
				<td class="dataTextBoldLeft" >Class & Sec:<c:out value="${student.classstudying}" />
				</td>

			<td class="dataTextBoldLeft" >&nbsp;&nbsp;&nbsp;Date: <c:out
						value="${feesdetails.dateoffees}" />
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
				<td class="headerText" style="font-weight: bold">Month</td>
				<td class="headerText" style="font-weight: bold">Fees Amount</td>
				

			</tr>
			
			<tr>
			
			<td><hr width="100%"></td>
			<td><hr width="100%"></td>
			<td><hr width="100%"></td>
			</tr>


			<c:forEach items="${feescollection}" var="feescollection">
				<tr>
					<td class="dataText"><c:out
							value="${feescollection.feescategory}" /></td>
					<td class="dataText"><c:out value="${feescollection.formonth}" /></td>
					<td class="dataText">&#x20B9; <c:out
							value="${feescollection.feesamount}" /></td>
					
					<%-- <td class="dataText">Rs. <c:out value="${unitdispensedose.amount}" /></td> --%>
				</tr>
			</c:forEach>
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
				<td>&nbsp;</td>
				<td class="headerText">Total</td>
				<td class="headerText">&nbsp;&nbsp;&nbsp;&nbsp;&#x20B9; <c:out
						value="${feesdetails.amountpercat}" /></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td class="headerText">Balance</td>
				<td class="headerText">&nbsp;&nbsp;&#x20B9; <c:out
						value="${feesdetails.balamount}" /></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td class="headerText">Miscellaneous</td>
				<td class="headerText">&nbsp;&nbsp;&#x20B9; <c:out
						value="${feesdetails.miscamount}" /></td>
			</tr>
			
			<tr>
			
			<td><hr width="100%"></td>
			<td><hr width="100%"></td>
			<td><hr width="100%"></td>
			</tr>
			
			<tr>
				<td>&nbsp;</td>
				<td class="headerText">Grand Total</td>
				<td class="headerText">&nbsp;&nbsp;&nbsp;&nbsp;&#x20B9; <c:out
						value="${feesdetails.grandtotal}" /></td>
			</tr>
			

<tr>
			
			<td><hr width="100%"></td>
			<td><hr width="100%"></td>
			<td><hr width="100%"></td>
			</tr>


<tr>

<td >In Words: <c:out value="${grandTotal}" /></td>
<td></td>
</tr>

<tr>
<td align="left">Note: Fees once deposited will not be refunded under any Circumstances</td>
<td align="right">Clerk</td>
</tr>
		</TABLE>


	</form>
</body>
</html>

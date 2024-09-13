<%-- 
    Document   : PrintBatchStock
    Created on : JAN 8 2021, 2:01 PM
    Author     : Musaib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html >
<head>
<title>Fees Report</title>
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
	font-weight: bold;
	font-family: ariel;
	color: black;
	font-size: 22px;
	letter-spacing: normal;
	text-align: center;
}

.dataText {
	border: 1px solid #000000;
	font-family: Tahoma;
	color: black;
	font-size: 12px;
	letter-spacing: normal;
	text-align: center;
	padding: 8px;
}
-->
.datatable {
    font-family: arial, sans-serif;
    border-collapse: collapse;
    width: 100%;
}

.datatd, .datath {
    border: 1px solid #000000;
    text-align: left;
    padding: 8px;
}

.datatdright {
    border: 1px solid #000000;
    text-align: right;
    padding: 8px;
}

.datatdt{
    border: 0px solid #000000;
    text-align: left;
    padding: 8px;
}
.reportheaders{
	font-weight: bold;
	font-size: 12px;

}

</style>

<script>
    window.onload = function(){
        window.print();
    }
    
</script>

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
             size: auto;
        }

        @media screen {
            .fontsize { font-size: 12px;
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
<c:set var="itemTotal" value="${0}" />
	<form method="post" class="bodymargin">
		<table width="100%" style="border-collapse: collapse;">
			<tr>
				<td align="center">
				<img src="/gnyanganga/images/gnyanganga${branchid}.jpg" width="150" height="80"/>
				</td>
				<td class="dataTextBoldCenter" style="width: 100%">
				<label class="dataTextBoldCenter" style="text-transform: uppercase;">${branchname}</label><br>
				<label class="addressLine">${branchaddress}</label><br>
				<label class="addressLine">Contact: ${branchcontact}</label><br>
				<label class="addressLine">Fees Report</label><br>
				</td>
			</tr>
	</table>

			<TABLE  width="100%" border="1" style="border-collapse:collapse;">
                <tr>

                    <td colspan="4">
                    
                    </td>

                </tr>
            </TABLE>
		
            <table class="datatable">
            <thead>
            	
 				 <tr>
 				 
 				 		<th class="datath">Admission Number</th>
						<th class="datath">Name&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
						<th class="datath">Class & Sec&nbsp;</th>
						<th class="datath">Father Name&nbsp;</th>
						<th class="datath">Contact No.&nbsp;</th>
						<!-- <th class="datath">Fees Details(Due Amount/Total Amount)</th> -->
						<th class="datath">Paid Amount&nbsp;</th>
						<th class="datath">Due Amount&nbsp;</th>
						<th class="datath">Fees Summary(Due Amount/Total Amount)</th>
 				 </tr>
 			 </thead>
 		 
			<tbody>
					<c:set var="TotalPaidAmount" value="0" />
					<c:set var="TotalDueAmount" value="0" />
					<c:set var="TotalSum" value="0" />
					
					<c:forEach items="${studentfeesreportlist}" var="studentfeesreportlist">

						<tr class="trClass" style="border-color: #000000" border="1"
							cellpadding="1" cellspacing="1">
							<%-- <td class="dataText"><input type="checkbox"
								id="<c:out value="${studentfeesreportlist.student.sid}"/>" class="chcktbl"
								name="studentIDs"
								value="<c:out value="${studentfeesreportlist.student.sid}"/>" /></td> --%>
							<td class="dataText"><c:out
										value="${studentfeesreportlist.parents.student.admissionnumber}" /></a></td>
							<td class="dataText"><c:out value="${studentfeesreportlist.parents.student.name}" /></td>
							<td class="dataText"><c:out
									value="${studentfeesreportlist.parents.student.classstudying}" /></td>
							<td class="dataText"><c:out value="${studentfeesreportlist.parents.fathersname}" /></td>
							<td class="dataText"><c:out value="${studentfeesreportlist.parents.contactnumber}" /></td>
							<%-- <td class="dataText">
									<c:set var="DueAmount" value="0" />
									<c:set var="TotalAmount" value="0" />
								<c:forEach items="${studentfeesreportlist.studentFeesStructure}" var="studentfeescatagorydetails">
									<table>
										<tr>
											<td style="width: 160px;" align="right">
												${studentfeescatagorydetails.feescategory.feescategoryname}:&nbsp;&nbsp;&nbsp;	
											</td>
											<td align="left">
												${studentfeescatagorydetails.feesamount-studentfeescatagorydetails.feespaid - studentfeescatagorydetails.concession - studentfeescatagorydetails.waiveoff}/${studentfeescatagorydetails.feesamount - studentfeescatagorydetails.concession - studentfeescatagorydetails.waiveoff}
											</td>
										</tr>
									</table>
									<c:set var="DueAmount" value="${DueAmount+studentfeescatagorydetails.feesamount-studentfeescatagorydetails.feespaid - studentfeescatagorydetails.concession - studentfeescatagorydetails.waiveoff}" />
									<c:set var="TotalAmount" value="${TotalAmount+(studentfeescatagorydetails.feesamount - studentfeescatagorydetails.concession - studentfeescatagorydetails.waiveoff)}" />
									<c:set var="PaidAmount" value="${TotalAmount-DueAmount}" />
									<c:set var="TotalPaidAmount" value="${TotalPaidAmount+studentfeescatagorydetails.feespaid}" />
									<c:set var="TotalDueAmount" value="${TotalDueAmount+(studentfeescatagorydetails.feesamount-studentfeescatagorydetails.feespaid - studentfeescatagorydetails.concession - studentfeescatagorydetails.waiveoff)}" />
									<c:set var="TotalSum" value="${TotalSum+(studentfeescatagorydetails.feesamount - studentfeescatagorydetails.concession - studentfeescatagorydetails.waiveoff)}" />
								</c:forEach>
							</td> --%>
							<td class="dataText">
							
									<c:set var="DueAmount" value="0" />
									<c:set var="TotalAmount" value="0" />
									<c:forEach items="${studentfeesreportlist.studentFeesStructure}" var="studentfeescatagorydetails">
									<%-- <table>
										<tr>
											<td style="width: 160px;" align="right">
												${studentfeescatagorydetails.feescategory.feescategoryname}:&nbsp;&nbsp;&nbsp;	
											</td>
											<td align="left">
												${studentfeescatagorydetails.feesamount-studentfeescatagorydetails.feespaid - studentfeescatagorydetails.concession - studentfeescatagorydetails.waiveoff}/${studentfeescatagorydetails.feesamount - studentfeescatagorydetails.concession - studentfeescatagorydetails.waiveoff}
											</td>
										</tr>
									</table> --%>
									<c:set var="DueAmount" value="${DueAmount+studentfeescatagorydetails.feesamount-studentfeescatagorydetails.feespaid - studentfeescatagorydetails.concession - studentfeescatagorydetails.waiveoff}" />
									<c:set var="TotalAmount" value="${TotalAmount+(studentfeescatagorydetails.feesamount - studentfeescatagorydetails.concession - studentfeescatagorydetails.waiveoff)}" />
									<c:set var="PaidAmount" value="${TotalAmount-DueAmount}" />
									<c:set var="TotalPaidAmount" value="${TotalPaidAmount+studentfeescatagorydetails.feespaid}" />
									<c:set var="TotalDueAmount" value="${TotalDueAmount+(studentfeescatagorydetails.feesamount-studentfeescatagorydetails.feespaid - studentfeescatagorydetails.concession - studentfeescatagorydetails.waiveoff)}" />
									<c:set var="TotalSum" value="${TotalSum+(studentfeescatagorydetails.feesamount - studentfeescatagorydetails.concession - studentfeescatagorydetails.waiveoff)}" />
								</c:forEach>
							
							<c:out value="${PaidAmount}" /></td>
							<td class="dataText"><c:out value="${DueAmount}" /></td>
							<td class="dataText">
									<table>
										<tr>
												<td style="width: 160px;" align="right" >
													${DueAmount}/${TotalAmount}&nbsp;&nbsp;&nbsp;
												</td>
										</tr>
									</table>
							</td>
						</tr>
					</c:forEach>
			</tbody>
				</table>
			<br>
			<div style="page-break-inside: avoid;" align="center">
				<table align="center">
						<tr>
							<td>
							<label style="font-weight: bold;">
							Total Amount:
							<fmt:setLocale value="en_IN" scope="session"/>
							<fmt:formatNumber type="currency"  value="${TotalSum}" />
							</label>
							<label style="font-weight: bold;">
							Total Paid Amount:
							<fmt:setLocale value="en_IN" scope="session"/>
							<fmt:formatNumber type="currency"  value="${TotalPaidAmount}" />
							</label>
							<label style="font-weight: bold;">
							Total Due Amount:
							<fmt:setLocale value="en_IN" scope="session"/>
							<fmt:formatNumber type="currency"  value="${TotalDueAmount}" />
							</label>
							</td>
						</tr>
				</table>
				<table>
						<tr>
							<td>
								<p><h4>------------- End of The Report ---------- </h4></p>
							</td>
						</tr>				
				</table>
			</div>
	</form>
</body>
</html>

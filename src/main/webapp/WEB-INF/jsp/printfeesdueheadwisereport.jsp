<%-- 
    Document   : Print General Ledger Report
    Created on : JAN 13 2021, 9:38 PM
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
<title>Print Fees Due Head Wise Report</title>
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

.addressLineTwo{
	font-weight: bold;
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

.datatdright{
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
.dataTextRight {
	border-radius: 3px;
	font-family: Tahoma;
	color: #4b6a84;
	font-size: 16px;
	letter-spacing: normal;
	text-align: right;
	background-color: #E3EFFF;
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
	<form method="post" class="bodymargin">
		<table width="100%" style="border-collapse: collapse;">
			<tr>
				<td align="center">
				<img src="/aysha/images/logo.jpg" width="120" height="100"/>
				</td>
				<td class="dataTextBoldCenter" style="width: 100%">
				${branchname}<br><br>
				<label class="addressLine">Fees Report</label><br>
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
 				 		<th class=datath>Sl.No.</th>
 				 		<th class=datath>UID</th>
						<th class="datath">Admission Number</th>
						<th class="datath">Name</th>
						<th class="datath">Class & Sec.</th>
						<th class="datath">Father Name</th>
						<th class="datath">Contact No.</th>
						<th class="datath">Fees Details</th>
						<th class="datath">Total Due Summary</th>
						<th class="datath">Total Fees Summary</th>
				       	
 				 </tr>
 			 </thead>
 		 
			<tbody>
					<c:forEach items="${studentfeesreportlist}" var="studentFeesReport" varStatus="status">
					
					<c:set var="feesDetails" value="" />
					<c:set var="dueAmount" value="0" />
					<c:set var="totalAmount" value="0" />


						<c:forEach items="${studentFeesReport.studentFeesStructure}"  var="studentFee">
							<c:set var="feesDue" value="${studentFee.feesamount - studentFee.feespaid - studentFee.concession - studentFee.waiveoff}" />
							<c:set var="feesTotal" value="${studentFee.feesamount - studentFee.concession - studentFee.waiveoff}" />
							<c:set var="dueAmount" value="${dueAmount + studentFee.feesamount - studentFee.feespaid - studentFee.concession - studentFee.waiveoff}" />
							<c:set var="totalAmount" value="${totalAmount + studentFee.feesamount - studentFee.concession - studentFee.waiveoff}" />
							<c:set var="feesDetails" value="${feesDetails} ${studentFee.feescategory.feescategoryname} : ${feesDue} /  ${feesTotal}" />
						</c:forEach>					
					<tr class="trClass" style="border-color: #000000" border="1"
							cellpadding="1" cellspacing="1">
							<td class="datatd"><c:out value="${status.index+1}" />
							</td>
							<td class="datatd"><c:out value="${studentFeesReport.parents.student.studentexternalid}" />
							</td>
							<td class="datatd"><c:out value="${studentFeesReport.parents.student.admissionnumber}" /></td>
							<td class="datatd"><c:out value="${studentFeesReport.parents.student.name}" /></td>
							<td class="datatd"><c:out value="${studentFeesReport.parents.student.classstudying}" /></td>
						    <td class="datatd"><c:out value="${studentFeesReport.parents.fathersname}" /></td>
						    <td class="datatd"><c:out value="${studentFeesReport.parents.contactnumber}" /></td>
						    <td class="datatd"><c:out value="${feesDetails}" /></td>
						    <td class="datatd"><c:out value="${dueAmount}" /></td>
						    <td class="datatd"><c:out value="${totalAmount}" /></td>
						</tr>
						
					</c:forEach>
			</tbody>
				</table>
			<br>
			<%-- <table class="datatable">
						<tr>
							<td class="dataText"></td>
							<td class="dataText"></td>
							<td class="dataText"></td>
							<td class="dataText"></td>
							<td class="dataText"></td>
							<td align="right" class="dataTextRight" >
							<label style="font-weight: bold;">
							<fmt:formatNumber type="number"  maxFractionDigits = "2"   value="${drtotal}" />
							</label>
							</td>
							
							<td align="right" style="width: 90px;" class="dataTextRight" >
							<label style="font-weight: bold;">
							<fmt:formatNumber type="number"  maxFractionDigits = "2"   value="${crtotal}" />
							</label>
							</td>
						</tr>
						<tr>
					<td class="dataText"></td>
					<td class="dataText"></td>
					<td class="dataText"></td>
					<td class="dataText"></td>
					<td class="dataText"></td>
							<td class="dataTextRight" >
								<label style="color: #eb6000"><b>
									Balance</b>
							</label> 
							</td>
							
							<td class="dataTextRight">
								<label style="color: #eb6000"><b>
							<c:choose>
                                <c:when test="${drtotal > crtotal}">
									<fmt:formatNumber type="currency"  value="${drtotal-crtotal}" />                                    
                                </c:when>
                                <c:otherwise>
                                   <fmt:formatNumber type="currency"  value="${crtotal-drtotal}" />
                                </c:otherwise>
                            </c:choose>
							</b>
							</label>
							</td>
					</tr>
				</table> --%>
				
			<div style="page-break-inside: avoid;" align="center">
				<table>
						<tr>
							<td>
								<p><h4>------------- End of The Report ----------</h4></p>
							</td>
						</tr>				
				</table>
			</div>
	</form>
</body>
</html>

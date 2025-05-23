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
<title>Print Other Fees Collection Details</title>
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
<fmt:setLocale value="en_IN" scope="session"/>
	<form method="post" class="bodymargin">
		<table width="100%" style="border-collapse: collapse;">
			<tr>
				<td align="center">
				<img src="/cambridge/images/logo.jpg" width="100" height="100"/>
				</td>
				<td class="dataTextBoldCenter" style="width: 100%">
				${branchname}<br><br>
				<label class="addressLine">Other Fees Collection Details Report</label><br>
				<label class="addressLineTwo">${daterangefeescollection}</label><br>
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
 				 		<th class=datath>Admission Number</th>
						<th class="datath">UID</th>
						<th class="datath">Receipt No.</th>
						<th class="datath">Student Name</th>
						<th class="datath">Class</th>
						<th class="datath">Father Name</th>
						<th class="datath">Date of Fees</th>
				       	<th class="datath">Total</th>
 				 </tr>
 			 </thead>
			<tbody>
					<c:forEach items="${feesmap}" var="feesmap" varStatus="status">
					<tr class="trClass" style="border-color: #000000" border="1"
							cellpadding="1" cellspacing="1">
							<td class="datatd"><c:out value="${status.index+1}" />
							</td>
							<td class="datatd"><c:out value="${feesmap.key.student.admissionnumber}" />
							</td>
							<td class="datatd"><c:out value="${feesmap.key.student.studentexternalid}" />
							</td>
							<td class="datatd"><c:out	value="${feesmap.value.branchreceiptnumber}" /></td>
							<td class="datatd"><c:out	value="${feesmap.key.student.name}" /></td>
							<td class="datatd"><c:out	value="${feesmap.key.student.classstudying}" /></td>
							<td class="datatd"><c:out	value="${feesmap.key.fathersname}" /></td>
							<td class="datatd">
							<fmt:formatDate type="date" value="${feesmap.value.date}" pattern="dd/MM/yyyy"/>
							</td>
							<td class="datatd">
								<fmt:formatNumber type="number"  maxFractionDigits = "2"   value="${feesmap.value.totalamount}" />
							</td>
						</tr>
						
					</c:forEach>
					<tr>
					<td class="dataText"></td>
					<td class="dataText"></td>
					<td class="dataText"></td>
					<td class="dataText"></td>
					<td class="dataText"></td>
					<td class="dataText"></td>
					<td class="dataText"></td>
							<td class="dataTextRight" >
								<label style="color: #eb6000"><b>
									Total</b>
							</label> 
							</td>
							
							<td class="dataTextRight">
								<label style="color: #eb6000"><b>
								<fmt:formatNumber type="currency"  value="${sumofdetailsfees}" /> 
							</b>
							</label>
							</td>
					</tr>
			</tbody>
				</table>
			
				<br>
			
			<table class="datatable">
						<tr>
					<td class="dataText"></td>
					<td class="dataText"></td>
					<td class="dataText"></td>
							<td class="dataTextRight" >
								<label style="color: #eb6000"><b>
									Total Fees:</b><label style="color: #eb6000"><b>
								<fmt:formatNumber type="currency"  value="${sumofonlyfee}" /> 
							</b>
							</label>
							</label> 
							</td>
							<td class="dataTextRight" >
								<label style="color: #eb6000"><b>
									Total Misc:</b><label style="color: #eb6000"><b>
								<fmt:formatNumber type="currency"  value="${sumofmisc}" /> 
							</b>
							</label>
							</label> 
							</td>
							<td class="dataTextRight" >
								<label style="color: #eb6000"><b>
									Total Fine:</b><label style="color: #eb6000"><b>
								<fmt:formatNumber type="currency"  value="${sumoffine}" /> 
							</b>
							</label>
							</label> 
							</td>
					</tr>
				</table>
				
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

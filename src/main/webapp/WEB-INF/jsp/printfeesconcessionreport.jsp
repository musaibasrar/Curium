<%-- 
    Document   : Print Fees Concession Report
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
<title>Fees Concession Report</title>
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
	font-size: 22px;
	letter-spacing: normal;
	text-align: center;
}

.addressLine{
	font-weight: bold;
	font-family: ariel;
	color: black;
	font-size: 16px;
	letter-spacing: normal;
	text-align: center;
}

.addressLineTwo{
	font-weight: bold;
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
            .fontsize { font-size: 1px ;
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
            .fontsize { font-size: 1px;
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
				<img src="/shatabdi/images/logo.jpg" width="150" height="50"/>
				</td>
				<td class="dataTextBoldCenter" style="width: 100%">
				
				<label class="dataTextBoldCenter">&nbsp;&nbsp;&nbsp;${branchname}</label><br>
				<label class="addressLine" style="padding-left: 20px;">${branchaddress}</label><br>
				<label class="addressLine" style="padding-left: 30px;">Contact:&nbsp;${branchcontact}</label><br>
				<label class="addressLine" style="text-decoration: underline;">Concession Report</label><br>
				<%-- <label class="addressLineTwo">${transactionfromdateselected}&nbsp;&nbsp;${transactiontodateselected}&nbsp;&nbsp;${issuedtoselected}&nbsp;&nbsp;${purposeselected}&nbsp;&nbsp;${itemselected}&nbsp;&nbsp;
				</label> --%>
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
 				 		<th class=datath>Sl.No</th>
 				 		<th class=datath>Adm. No.</th>
 				 		<th class="datath">Student Name</th>
						<th class="datath">Class</th>
						<th class="datath">Father Name</th>
						<th class="datath">Fees Cat.</th>
						<th class="datath">Fees Amount</th>
						<th class="datath">Inst.</th>
						<th class="datath">Total Fees Amount</th>
						<th class="datath">Concession Amount</th>
						<th class="datath">Concession By</th>
 				 </tr>
 				 
 			 </thead>
 		 
			<tbody>
					<c:forEach items="${studentsfeesstructuredetailsconcession}" var="students" >
						
						<c:forEach items="${students.value}" var="fees" varStatus="status">

							<tr>
									<td class="datatd" style="font-size: 9px;">${status.index+1}</td>
									<td class="datatd" style="font-size: 9px;"><c:out value="${students.key.student.admissionnumber}" /></td>
									<td class="datatd" style="font-size: 9px;"><c:out value="${students.key.student.name}" /></td>
									<td class="datatd" style="font-size: 9px;"><c:out value="${students.key.student.classstudying}" /></td>
									<td class="datatd" style="font-size: 9px;"><c:out value="${students.key.fathersname}" /></td>
									<td class="datatd" style="font-size: 9px;"><c:out value="${fees.feescategory.feescategoryname}" /></td>
									<td class="datatdright" style="font-size: 9px;"><c:out value="${fees.feescategory.amount}" /></td>
									<td class="datatd" style="font-size: 9px;"><c:out value="${fees.totalinstallment}" /></td>
									<td class="datatdright" style="font-size: 9px;"><c:out value="${fees.feesamount}" /></td>
									<td class="datatdright" style="font-size: 9px;">
									<c:set var="itemTotal" value="${itemTotal + fees.concession}" />
										<c:out value="${fees.concession}" /></td>
									<td class="datatdright" style="font-size: 9px;"><c:out value="${fees.concessionby}" /></td>
							</tr>	
						</c:forEach>
					</c:forEach>
			</tbody>
				</table>
			<br>
			<div style="page-break-inside: avoid;" align="center">
				<table align="right">
						<tr>
							<td align="right">
							<label style="font-weight: bold;">
							Grand Total: 
							<fmt:setLocale value="en_IN" scope="session"/>
							<fmt:formatNumber type="currency"  value="${itemTotal}" />
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

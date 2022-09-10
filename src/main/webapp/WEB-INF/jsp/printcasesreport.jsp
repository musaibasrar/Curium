<%-- 
    Document   : Print Cases Report
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
<title>Print Cases Report</title>
<style type="text/css">
<!--
.datath {
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

.datathLeft {
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
				<img src="/sla/images/slalogo.png" width="150" height="80"/>
				</td>
				<td class="dataTextBoldCenter" style="width: 100%">
				<label style="text-transform: uppercase;font-size: 24px;">Suprema Law Associate</label>
				<br>
				<label class="addressLine">Cases Report</label><br>
				<label class="addressLineTwo">${transactionfromdateselected}&nbsp;&nbsp;${transactiontodateselected}&nbsp;&nbsp;${issuedtoselected}&nbsp;&nbsp;${purposeselected}&nbsp;&nbsp;${itemselected}&nbsp;&nbsp;
				</label>
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
            	  			<th class="datath">Sl. No.</th>
            	  			<th class="datath">UID</th>
                            <th class="datath">Case Title</th>
                            <th class="datath">Case No.</th>
                            <th class="datath">File No.</th>
                            <th class="datath">Status</th>
                            <th class="datath">Court</th>
                            <th class="datath">Court Name</th>
                            <th class="datath">Date of Dispose/NOC</th>
                            <th class="datath">File Taken</th>
                            <th class="datath">File Taken By</th>
                            <th class="datath">File Taken By Name</th>
                            <th class="datath">File Taken By Number</th>
                            <th class="datath">Created Date</th>
                            <th class="datath">Updated Date</th>
                        </tr>
 			 </thead>
 		 
			<tbody>
			
						<c:forEach items="${caseslist}" var="cases" varStatus="status">
                            <tr>
                            	<td class="datatd" style="font-size: 9px;">${status.index+1}</td>
                            	<td class="datatd" style="font-size: 9px;"><c:out value="${cases.id}"/></td>
                            	
                            	<c:if test="${cases.status == 'Pending' }">
                                	<td class="datatd" style="font-size: 9px;">
                                	<c:out value="${cases.casetitle}"/>
                                	</td>
                                </c:if>
                                <c:if test="${cases.status == 'Disposed' }">
                                	<td class="datatd" style="font-size: 9px;">
                                		<c:out value="${cases.casetitle}"/>
                                	</td>
                                </c:if>
                                <c:if test="${cases.status == 'NOC' }">
                                	<td class="datatd" style="font-size: 9px;">
                                		  <c:out value="${cases.casetitle}"/>
                                	</td>
                                </c:if>
                                <c:if test="${cases.status == 'Cancelled' }">
                                	<td class="datatd" style="font-size: 9px;">
                                		<c:out value="${cases.casetitle}"/>
                                	</td>
                                </c:if>
                                <td class="datatd" style="font-size: 9px;"><c:out value="${cases.casenumber}"/></td>
                                <td class="datatd" style="font-size: 9px;"><c:out value="${cases.fileno}"/></td>
                                <td class="datatd" style="font-size: 9px;"><c:out value="${cases.status}"/></td>
                                <td class="datatd" style="font-size: 9px;"><c:out value="${cases.court}"/></td>
                                <td class="datatd" style="font-size: 9px;"><c:out value="${cases.courtname}"/></td>
                                <td class="datatd" style="font-size: 9px;"><fmt:formatDate pattern="dd/MM/yyyy"  value="${cases.dateofdispose}"/></td>
                                <td class="datatd" style="font-size: 9px;"><c:out value="${cases.filetaken}"/></td>
                                <td class="datatd" style="font-size: 9px;"><c:out value="${cases.filetakenby}"/></td>
                                <td class="datatd" style="font-size: 9px;"><c:out value="${cases.filetakenbyname}"/></td>
                                <td class="datatd" style="font-size: 9px;"><c:out value="${cases.filetakenbynumber}"/></td>
                                <td class="datatd" style="font-size: 9px;"><fmt:formatDate pattern="dd/MM/yyyy"  value="${cases.createddate}"/></td>
                                <td class="datatd" style="font-size: 9px;"><fmt:formatDate pattern="dd/MM/yyyy" value="${cases.updateddate}"/></td>
                            </tr>
                        </c:forEach>
			</tbody>
				</table>
			<br>
			<div style="page-break-inside: avoid;" align="center">
				
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

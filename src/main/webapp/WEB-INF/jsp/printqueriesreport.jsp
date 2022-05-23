<%-- 
    Document   : Print Queries Report
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
<title>Print Queries Report</title>
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
				<img src="images/slalogo.png" width="150" height="80"/>
				</td>
				<td class="dataTextBoldCenter" style="width: 100%">
				<label style="text-transform: uppercase;font-size: 24px;">Suprema Law Associate</label>
				<br>
				<label class="addressLine">Jobs Report</label><br>
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
                           	<th class="datath">Sl.No</th>
                           	<th class="datath">UID</th>
                           	<th class="datath">Job No.</th>
                            <th class="datath">Created Date</th>
                            <th class="datath">Updated Date</th>
                            <th class="datath">Staff</th>
                            <th class="datath">Client Name</th>
                            <th class="datath">Contact Number</th>
                            <th class="datath">Status</th>
                            <!-- <th class="datath">Feedback</th> -->
                        </tr>
 			 </thead>
 		 
			<tbody>
			
						<c:forEach items="${parentquerylist}" var="query" varStatus="status">
                            <tr>
                                <td class="datatd" style="font-size: 9px;">${status.index+1}</td>
                                <td class="datatd" style="font-size: 9px;"><c:out value="${query.id}"/></td>
                                <td class="datatd" style="font-size: 9px;"><c:out value="${query.externalid}"/></td>
                                <td class="datatd" style="font-size: 9px;"><fmt:formatDate pattern="dd/MM/yyyy" value="${query.createddate}"/></td>
                                <td class="datatd" style="font-size: 9px;"><fmt:formatDate pattern="dd/MM/yyyy" value="${query.updateddate}"/></td>
                                <td class="datatd" style="font-size: 9px;"><c:out value="${query.teacher.teachername}"/></td>
                                <td class="datatd" style="font-size: 9px;"><c:out value="${query.parent.student.name}"/></td>
                                <td class="datatd" style="font-size: 9px;"><c:out value="${query.parent.contactnumber}"/></td>
                                <td class="datatd" style="font-size: 9px;"><c:out  value="${query.status}"/></td>
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

<%-- 
    Document   : Print Question Paper Set
    Created on : JUL 28 2018, 10:39 AM
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
<title>Question Paper Set</title>
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
    /* page-break-after:always; */
    page-break-after:auto;
}

.datatd, .datath {
    border: 1px solid #000000;
    text-align: left;
    padding: 8px;
}

.datathe {
    border: 0px solid #000000;
    text-align: left;
    padding: 8px;
}

.reportheaders{
	font-weight: bold;
	font-size: 15px;

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
            .fontsize { 
                        font-weight: bold;
                        font-family: 'Times New Roman';
                        
                        
            }
            /* .header,.hide {
             visibility: hidden } */
            .bodymargin{
                margin-left: 0px ;
                margin-right: 0px;
            }
            
            #footer {
         display: block;
         position: fixed;
         bottom: 0;
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
            .fontsize { 
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
	<c:forEach items="${noofpapers}" var="noofpapers">
	 <table border="0" width="100%" style="page-break-after: always;"> 
	 <thead>
    <tr>
     <th style="width:100%">
     <table width="100%" style="border-collapse: collapse;">
			<tr>
				<td align="center">
				<img src="images/bielogo.png" width="80" height="110"/>
				</td>
				<td class="dataTextBoldCenter" style="width: 100%">
				BOARD OF ISLAMIC EDUCATION <br>
				KARNATAKA<br><br>
				<label class="addressLine">Question Paper Set &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				Total: ${totalstudentsforprint}<br>
				 </label>
				</td>
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
						<td class="dataTextBoldLeft" style="width: 50%"><label class="reportheaders"><c:out value="${printcentername}" /></label>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td class="dataTextBoldLeft" style="width: 50%"><label class="reportheaders"><c:out value="${printexamlevel}" /></label></td>
					
			</tr>
			
			<tr>
			<td></td>
			</tr>
			<tr>
						<td class="dataTextBoldLeft" style="width: 50%"><label class="reportheaders"><c:out value="${printlanguage}" /></label>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<%-- <td class="dataTextBoldLeft" style="width: 50%"><label class="reportheaders"><c:out value="${printsubjectname}" /></label></td> --%>
						<td class="dataTextBoldLeft" style="width: 50%"><label class="reportheaders"><c:out value="${noofpapers.subjectname}" /></label></td>
			</tr>
		</table>
     
     
     </th>
   </tr>
  </thead> 
  
  <tbody>
  		<tr>
  		<td>
		 <div style="page-break-inside: avoid;"> 
		
            <br>
            
            <table class="datatable">
            <thead>
 				 <tr>
  						<th title="click to sort" class="datath">Sl.No.</th>
						<th title="click to sort" class="datath">Registration No.</th>
						<th title="click to sort" class="datath">Student Name</th>

 				 </tr>
 			 </thead>
 		 
			<tbody>
				  <c:forEach items="${mapstudentreports}" var="Parents" varStatus="status">
				<tr>
					<td class="datatd" style="font-size: 11px;"><c:out value="${(status.index)+1}" /></td>
					<td class="datatd" style="font-size: 11px;"><c:out
							value="${Parents.key.student.admissionnumber}" /></td>
					<td class="datatd" style="font-size: 11px;"><c:out
							value="${Parents.key.student.name}" /></td>
				</tr>
			</c:forEach>
			</tbody>
				</table>
				</div>
			<br><br>
			
  		</td>
  		
  		</tr>
 </tbody> 
  
  
  <tfoot>
  </tfoot>
	 </table>
	 </c:forEach>
	</form>
</body>
</html>

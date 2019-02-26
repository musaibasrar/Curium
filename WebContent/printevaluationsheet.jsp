<%-- 
    Document   : Print Evaluation Sheet
    Created on : AUG 16 2018, 06:09 PM
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
<title>Evaluation Sheet</title>
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
	font-size: 24px;
	letter-spacing: normal;
	text-align: center;
}

.addressLine{
	font-weight: bold;
	font-family: ariel;
	color: black;
	font-size: 24px;
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

.reportheaders{
	font-weight: bold;
	font-size: 20px;

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
	
			<!-- START -->
			
			<c:forEach items="${mapstudentsexam}" var="parentsexam" >
	 <table border="0" width="100%" style="page-break-after: always;"> 
	 <thead>
    <tr>
     <th style="width:100%">
     <table width="100%" style="border-collapse: collapse;">
			<tr>
				<td align="center">
				<img src="images/bielogo.png" width="90" height="130"/>
				</td>
				<td class="dataTextBoldCenter" style="width: 100%">
				BOARD OF ISLAMIC EDUCATION KARNATAKA
				<br><br>
				<label class="addressLine"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;EVALUATION SHEET &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				 </label><br><br>
				 <c:if test="${(printcentername != '')}">
						<c:out value="${printcentercode}" />
					</c:if>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<%-- <c:if test="${(printexamlevel != '')}">
						<label class="reportheaders"><c:out value="${printexamlevel}" /></label>
					</c:if> --%>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					Total: ${totalstudentevaluation}
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
					<c:if test="${(printcentername != '')}">
						<td class="dataTextBoldLeft" style="width: 30%"><label class="reportheaders"><c:out value="${printcentername}" /></label></td>
					</c:if>
						<td></td>
					<c:if test="${(printexamlevel != '')}">
						<td class="dataTextBoldLeft" style="width: 50%"><label class="reportheaders"><c:out value="${printexamlevel}" /></label></td>
					</c:if>

			</tr>
			<tr>
			<td></td>
			</tr>
			<tr>
								
					<c:if test="${(printlanguage != '')}">
						<td class="dataTextBoldLeft" style="width: 50%"><label class="reportheaders"><c:out value="${printlanguage}" /></label></td>
					</c:if>
					<td></td>
					<td class="dataTextBoldLeft" style="width: 50%"><label class="reportheaders">Subject: <c:out value="${parentsexam.key.subjectname}" /></label></td>
			</tr>
			<tr>
			<td></td>
			</tr>
			<tr>
						
			</tr>
		</table>
     </th>
   </tr>
  </thead> 
  
  <tbody>
  		<tr>
  		<td>
		 <div style="page-break-inside: avoid;"> 
		  <table class="datatable" style="page-break-after:auto;">
            <thead>
 				 <tr>
  						<th  class="datath">Admission Number</th>
						<th  class="datath">Student Name</th>
						<th  class="datath">Marks</th>
						<th  class="datath">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Marks In Words&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
 				 </tr>
 			 </thead>
 		 
			<tbody>
				<c:forEach items="${parentsexam.value}" var="Parents" >
				<tr>
					<td class="datatd"><c:out
							value="${Parents.student.admissionnumber}" /></td>
					<td class="datatd"><c:out
							value="${Parents.student.name}" /></td>
					<td class="datatd"></td>
					<td class="datatd"></td>
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
  	
  	<tr>
  	<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Name: </td>
  	</tr>
  	<tr>
  	<td><br></td>
  	</tr>
  	<tr>
  	<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Contact No.: </td>
  	</tr>
  	<tr>
  	<td><br><br></td>
  	</tr>
		  <tr>
				<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Signature of Evaluator &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				Signature of Chief Evaluator</td>	
			</tr>
  </tfoot>
	 </table>
	 </c:forEach>		
	</form>
</body>
</html>
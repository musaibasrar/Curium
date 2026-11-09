<%-- 
    Document   : Print Hall Ticket
    Created on : Apr 04 2018, 04:32 PM
    Author     : Musaib
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
	font-weight: normal;
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
	font-size: 20px;
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

span{
    display:inline-block;
    border-bottom:2px solid black;
    padding-bottom:1px;
    width: 300px;
    font-weight: normal;
}
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
        
        .subjectdetails{
        	border: 1px solid black;
    		text-align: left;
   		    padding: 8px;
        }
        
        .nosubjectdetails{
        	border: 0px;
    		text-align: left;
   		    padding: 8px;
        }
        
         .namedetails{
        	border: 0px solid #dddddd;
    		text-align: left;
   		    padding: 4px;
        }
         .namedetailscenter{
        	border: 0px solid #dddddd;
    		text-align: right;
   		    padding: 8px;
        }
        
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

    </style>
	
	<script type="text/javascript">
                       
		window.onload = function(){
		window.print();
		}
        </script>
        
        <style>
    .clean-table {
        width: 100%;
        border-collapse: collapse;
        border: none; /* Guarantees no lines anywhere */
    }
    .clean-table td {
        padding: 5px 15px 5px 0; /* Replaces empty rows with clean margin spacing */
        border: none; /* Guarantees no lines below text */
        text-align: left; /* Left justified */
        vertical-align: top;
    }
    .label-col {
        white-space: nowrap; /* Ensures label NEVER wraps to a second line */
        width: 1%; /* Magic trick: Forces the column to perfectly hug the longest label */
    }
    .value-col {
        font-weight: bold;
    }
    
    /* Centers the table headings */
th.subjectdetails {
    text-align: center;
}

/* Forces the column to shrink to perfectly fit its content */
.col-fit {
    width: 1%;
    white-space: nowrap;
}

/* Allows text to wrap to multiple lines for the reference books */
.col-wrap {
    white-space: normal;
    word-wrap: break-word;
}
</style>
        
       <title>Hall Ticket</title>
        
</head>
  <%
//allow access only if session exists
String user = null;
if(session.getAttribute("userAuth") == null){
	response.sendRedirect("Controller?process=UserProcess&action=sessionTimeOut");
}else user = (String) session.getAttribute("userAuth");
String userName = null;
String sessionID = null;
Cookie[] cookies = request.getCookies();
if(cookies !=null){
for(Cookie cookie : cookies){
	if(cookie.getName().equals("user")) userName = cookie.getValue();
	if(cookie.getName().equals("JSESSIONID")) sessionID = cookie.getValue();
}
}
%>
<body style="text-align: center" class="bodymargin">
<jsp:useBean id="now" class="java.util.Date" scope="page" />
	<form method="post" class="bodymargin">
		<c:forEach items="${hallticketmap}" var="Parents">
		
            <div style="page-break-inside: avoid;border-style: solid;border-width: thin;">             	
		<table width="100%" style="page-break-inside: avoid;border-collapse: collapse;">
			<tr>
				<td></td>
				<td></td>
				<td align="center">
				<img src="images/bielogo.png" width="40" height="70"/>
				</td>
				<td class="dataTextBoldCenter" style="width: 100%">
				BOARD OF ISLAMIC EDUCATION KARNATAKA
				<br><br>
				<label class="addressLine">HALL TICKET<br>
				 </label>
				</td>
			</tr>
			
	</table>
	
			<TABLE  width="100%" border="1" style="border-collapse:collapse;">
                <tr>

                    <td colspan="4" ></td>

                </tr>
            </TABLE>
			<table class="clean-table">
    <!-- Row 1: Split into 4 columns to keep Admission and Language side-by-side -->
    <tr>   
        <td class="label-col">Admission No :</td>
        <td class="value-col" style="font-size: 18px;"><c:out value="${Parents.key.student.admissionnumber}"/></td>
        
        <td class="label-col" style="padding-left: 20px;">Language Opted. :</td>
        <td class="value-col"><c:out value="${Parents.key.student.languageopted}"/></td>
    </tr>
    
    <!-- All following rows use 2 columns (Label and Value) -->
    <tr>
        <td class="label-col">Candidate Name :</td>
        <td class="value-col" colspan="3"><c:out value="${Parents.key.student.name}"/></td>
    </tr>
     
    <c:if test="${(Parents.key.student.guardiandetails != '')}">
        <tr>
            <td class="label-col">Guardian Name :</td>
            <td class="value-col" colspan="3"><c:out value="${Parents.key.student.guardiandetails}"/></td>
        </tr>
    </c:if>

    <c:if test="${(Parents.key.fathersname != '')}">
        <tr>
            <td class="label-col">Father's Name :</td>
            <td class="value-col" colspan="3"><c:out value="${Parents.key.fathersname}"/></td>
        </tr>
    </c:if>
    
    <c:if test="${(Parents.key.mothersname != '')}">
        <tr>
            <td class="label-col">Husband's Name :</td>
            <td class="value-col" colspan="3"><c:out value="${Parents.key.mothersname}"/></td>
        </tr>
    </c:if>	
     
    <tr>
        <td class="label-col">Center Code/Name :</td>
        <td class="value-col" colspan="3" style="font-size: 18px;"><c:out value="${centercodename}"/></td>
    </tr>
     
    <tr>
        <td class="label-col">Exam Code/Name :</td>
        <td class="value-col" colspan="3"><c:out value="${examcodename}"/></td>
    </tr>
</table>

				<table style="border-collapse: collapse; width: 100%;">
					<thead>
						<tr>
							<th class="subjectdetails col-fit">Date</th>
							<th class="subjectdetails col-wrap">Reference Books</th>
							<th class="subjectdetails col-fit">Time</th>
							<th class="subjectdetails col-fit">Examiner's Sign</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${Parents.value}" var="examschedulelist"
							varStatus="status">
							<tr>
								<td class="subjectdetails col-fit"><fmt:formatDate
										value="${examschedulelist.date}" pattern="dd-MM-yyyy" /></td>
								<td class="subjectdetails col-wrap"><c:out
										value="${examschedulelist.referencebooks}" /></td>
								<td class="subjectdetails col-fit"><c:out
										value="${examschedulelist.starttime}" />&nbsp;-&nbsp;<c:out
										value="${examschedulelist.endtime}" /></td>
								<td class="subjectdetails"></td>
							</tr>
						</c:forEach>

						<c:if test="${totalpapers < 2}">
							<tr>
								<td class="nosubjectdetails col-fit"></td>
								<td class="nosubjectdetails col-wrap"></td>
								<td class="nosubjectdetails col-fit"></td>
								<td class="nosubjectdetails"></td>
							</tr>
							<tr>
								<td class="nosubjectdetails col-fit"></td>
								<td class="nosubjectdetails col-wrap"></td>
								<td class="nosubjectdetails col-fit"></td>
								<td class="nosubjectdetails"></td>
							</tr>
						</c:if>
					</tbody>
				</table>


				<TABLE id="dataTable" width="100%" border="0"
			style="page-break-inside:avoid; border-collapse: collapse;">

		
            <tr>
            <td align="left"></td>	
			<td align="left"></td>	
			<td align="center">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
			<td align="left"></td>
			</tr>
			<tr>
		<td></td>
		<td align="left"></td>	
			<td align="centre"></td>
			<td align="centre"><img alt="Chief Examiner Signature" src="images/cesignature.jpg" width="150" height="50"></td>
			</tr>
			
		<tr>
		<td></td>
		<td align="left">Candidate's Signature</td>	
			<td align="centre">Seal & Sign of Organiser</td>
			<td align="centre">Signature of Chief Examiner</td>
			</tr>
		</TABLE>
		</div>    
			<br><br><br>		           
      </c:forEach>
	</form>
	
	
</body>
</html>

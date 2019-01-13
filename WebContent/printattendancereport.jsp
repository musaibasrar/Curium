<%-- 
    Document   : Print Attendance Report
    Created on : Aug 2 2018, 04:57 PM
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
	font-size: 25px;
	letter-spacing: normal;
	text-align: center;
}

.addressLine{
	font-weight: bold;
	font-family: ariel;
	color: black;
	font-size: 25px;
	letter-spacing: normal;
	text-align: center;
}

.examlabels{
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
		<br>
		<table width="100%" style="border-collapse: collapse;">
			<tr>
				<td align="center">
				<img src="images/bielogo.png" width="80" height="110"/>
				</td>
				<td class="dataTextBoldCenter" style="width: 100%">
				BOARD OF ISLAMIC EDUCATION KARNATAKA<br><br>
				<label class="examlabels">${attendancecentercode}</label><label class="addressLine">&nbsp;&nbsp;&nbsp;ATTENDANCE LIST&nbsp;&nbsp;&nbsp;
				 </label><label class="examlabels">Exam Code: ${attendanexamlevelcode}</label><br>
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
					<c:if test="${(attendancecentername != '')}">
						<td class="dataTextBoldLeft" style="width: 50%"><c:out value="${attendancecentername}" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					</c:if>
				
					<c:if test="${(attendanceexamlevelname != '')}">
						<td class="dataTextBoldLeft" style="width: 50%"><c:out value="${attendanceexamlevelname}" /></td>
					</c:if>

			</tr>
			<tr>
			<td></td>
			</tr>
			<tr>
					<c:if test="${(attendancelanguageopted != '')}">
						<td class="dataTextBoldLeft" style="width: 50%"><c:out value="${attendancelanguageopted}" /></td>
					</c:if>
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
            <br>
            
            <table class="datatable">
            <thead>
 				 <tr>
						<th  class="datath">Sl.No.</th> 				 
  						<th  class="datath">Registration No.</th>
						<th  class="datath">Student Name</th>
						<c:forEach items="${attendancesubexamlevel}" var="subexamlevel">
						<th class="datath">${subexamlevel.subjectname}</th>
						</c:forEach>

 				 </tr>
 			 </thead>
 		 
			<tbody>
			<c:forEach items="${viewAttendancemap}" var="viewAttendancemap" varStatus="status">

						<tr>
							<td class="datatd"><c:out value="${(status.index)+1}" /></td>
							<td class="datatd"><c:out value="${viewAttendancemap.key.admissionnumber}" /></td>
							<td class="datatd"><c:out value="${viewAttendancemap.key.name}" /></td>
							<c:forEach items="${viewAttendancemap.value}" var="subjectdetails">
							<td class="datatd">
							<c:set var="attendanceparts" value="${fn:split(subjectdetails, '%')}" />
							<c:out value="${attendanceparts[1]}" />
							</td>
							</c:forEach>
						</tr>
					</c:forEach>
			</tbody>
				</table>
			<br><br>
	</form>
</body>
</html>

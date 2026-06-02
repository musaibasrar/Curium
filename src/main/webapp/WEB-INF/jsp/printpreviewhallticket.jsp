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
	font-family: "Times New Roman", Times, Tahoma;
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
	font-family: "Times New Roman", Times, Tahoma;
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
	font-family: "Times New Roman", Times, Tahoma;
	color: black;
	font-size: 12px;
	letter-spacing: normal;
	text-align: center;
}

.dataTextBoldLeft {
	font-weight: normal;
	font-family: "Times New Roman", Times, Tahoma;
	color: black;
	font-size: 12px;
	letter-spacing: normal;
	text-align: left;
}

.dataTextBoldCenter {
	font-weight: bold;
	font-family: "Times New Roman", Times, Tahoma;
	color: black;
	font-size: 12px;
	letter-spacing: normal;
	text-align: center;
}

.addressLine{
	font-weight: normal;
	font-family: "Times New Roman", Times;
	color: black;
	font-size: 7px;
	letter-spacing: normal;
	text-align: center;
}

.dataText {
	font-family: "Times New Roman", Times, Tahoma;
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
    size: A4;
    margin: 10mm;
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
    		text-align: center;
   		    padding: 8px;
   		    font-size: 12px;
        }
        
        .nosubjectdetails{
        	border: 0px;
    		text-align: left;
   		    padding: 8px;
   		    font-weight: normal;
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
    font-family: "Times New Roman", Times, sans-serif;
    border-collapse: collapse;
    width: 100%;
    font-size: 8px;
}

.datatd, .datath {
    border: 1px solid #000000;
    text-align: center;
    padding: 8px;
}

.ticket-container {
    display: flex;
    flex-wrap: wrap;
    width: 100%;
    position: relative;
}


.ticket {
    width: 50%;
    height: 50vh;
    box-sizing: border-box;
    padding: 12px;
}

.ticket-inner {
    border: 1px solid black;
    height: 90%;
    padding: 10px;
}

/* PRINT SETTINGS */

@media print {

    body {
        margin: 0;
        padding: 0;
    }

    .ticket {
        width: 50%;
        height: 50vh;
        padding: 10px;
    }

    .page-break {
        page-break-after: always;
    }

}



    </style>
	<script type="text/javascript">
                       
		window.onload = function(){
		window.print();
		}
        </script>
	<title> </title>
        
</head>
  <%
//allow access only if session exists
String user = null;
if(session.getAttribute("userAuth") == null){
	response.sendRedirect("/shaheen/UserProcess/sessionTimeOut");
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
                        <div class="ticket-container">

<c:forEach items="${studentList}" var="Parents" varStatus="status">

    <div class="ticket">
        <div class="ticket-inner">

            <!-- ===== HEADER ===== -->
            <table width="100%" style="border-collapse: collapse;">
                <tr>
                    <td><img src="/shaheen/images/shaheen.jpg" width="44" height="30"/></td>
                    <td align="center">
                        <label class="dataTextBoldCenter" style="text-transform: uppercase;">${branchname}</label><br>
                    </td>
                </tr>
            </table>

            <hr style="margin-top: 2px;margin-bottom: 2px;">

            <!-- ===== TITLE ===== -->
            <table width="100%">
                <tr>
                	<td width="30%"></td>
                    <td class="dataTextBoldCenter">
                        Hall Ticket<br>${examname}
                    </td>
                    <td align="right">
                        <img src="data:image;base64,<c:out value='${Parents.student.studentpic}'/>"
                             width="30" height="30"/>
                    </td>
                </tr>
            </table>

            <!-- ===== STUDENT DETAILS ===== -->
            <table width="100%">
                <tr>
                    <td style="text-align: left;font-size: 10px;">Student Name: <b>${Parents.student.name}</b></td>
                    <td style="text-align: left;font-size: 10px;">
                        Class:
                        <c:forEach var="splt" items="${fn:split(Parents.student.classstudying,'--')}">
                            ${splt}
                        </c:forEach>
                    </td>
                </tr>

                <tr>
                    <td style="text-align: left;font-size: 10px;">Father's Name: ${Parents.fathersname}</td>
                    <td style="text-align: left;font-size: 10px;">Admission No: ${Parents.student.admissionnumber}</td>
                </tr>
            </table>

            <!-- ===== EXAM TABLE ===== -->
            <table width="100%" class="datatable">
                <thead>
                <tr>
                    <th class="datath">Date</th>
                    <th class="datath">Day</th>
                    <th class="datath">Subject</th>
                    <th class="datath">Time</th>
                    <th class="datath">Sign</th>
                </tr>
                </thead>

                <tbody>
                <c:forEach items="${examschedulelist}" var="schedule">
                    <tr>
                        <td class="datatd">
                            <fmt:formatDate value="${schedule.date}" pattern="dd/MM/yyyy"/>
                        </td>
                        <td class="datatd">
                            <fmt:formatDate value="${schedule.date}" pattern="E"/>
                        </td>
                        <td class="datatd">${schedule.subject}</td>
                        <td class="datatd">${schedule.starttime} - ${schedule.endtime}</td>
                        <td class="datatd"></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <br><br>

            <table width="100%">
                <tr>
                    <td style="font-weight: bold;">Accountant</td>
                    <td style="font-weight: bold;align-content: center;">Class Teacher</td>
                    <td style="font-weight: bold;align-content: center;">Principal</td>
                </tr>
            </table>

        </div>
    </div>

    <!-- Page break after every 4 tickets -->
    <c:if test="${(status.index + 1) % 4 == 0}">
        <div class="page-break"></div>
    </c:if>

</c:forEach>

</div>

			
	</form>
	
	
</body>
</html>

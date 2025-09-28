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
	font-weight: normal;
	font-family: Tahoma;
	color: black;
	font-size: 40px;
	letter-spacing: normal;
	text-align: center;
}

.addressLine{
	font-weight: normal;
	font-family: ariel;
	color: black;
	font-size: 12px;
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
     <style>
     .background-div {
    position: relative;
    //width: 400px;
    //height: 300px;
    border: 1px solid #ccc;
    overflow: hidden;
} 

.background-div::before {
    content: "";
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background: url('/meps/images/meps.jpg') no-repeat center;
    background-size: contain;
    opacity: 0.1; /* Adjust opacity as needed */
    z-index: -1; /* Keep it behind the content */
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
	response.sendRedirect("/meps/UserProcess/sessionTimeOut");
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
                        <c:forEach items="${studentList}" var="Parents">
                        
                                                
                        <div class="background-div" style="page-break-inside: avoid;border-style: solid;border-width: thin;">   
                        
                        <table width="100%" style="border-collapse: collapse;">
					
				<tr>
					<td class="dataTextBoldCenter" style="width: 100%;font-size:25px;">Hall Ticket<br>${examname}
					</td>
				</tr>
				<tr>
					<td></td>
				</tr>
				<tr></tr>
			</table>
			<TABLE  width="100%" border="1" style="page-break-inside: avoid;border-collapse:collapse;">
                <tr>

                    <td colspan="4" ></td>

                </tr>
            </TABLE>
         <table style="page-break-inside: avoid; border-collapse: collapse; width: 100%;">
                        		
			<tr>
				<td style="width: 20%; text-align: left; vertical-align: middle;"><img src="/meps/images/meps.jpg" width="132" height="90"/></td>
				<td style="width: 60%; text-align: center; vertical-align: middle;">
				<label style="color:red" class="dataTextBoldCenter" style="text-transform: uppercase;font-size:35px;">${branchname}</label><br>
				<label class="addressLine" style="font-size: 15px;">${branchaddress}</label><br>
				<label class="addressLine">${branchcontact}</label>
				</td>
				<td style="width: 20%; text-align: right; vertical-align: middle;"><img  src="data:image;base64,<c:out value="${Parents.student.studentpic}"/>" alt="Student's Photo" width="90" height="90"/></td>
			</tr>
		</table>

<TABLE  width="100%" border="1" style="page-break-inside: avoid;border-collapse:collapse;">
                <tr>

                    <td colspan="4" ></td>

                </tr>
            </TABLE>

			<table style=" border-collapse: collapse;width: 100%;">
											
                            <tr style="border-color:#000000">
                                <td class="namedetails" style="font-size:20px;"><label>Student Name:&nbsp;&nbsp;&nbsp;</label><label style="text-transform: capitalize;color:red"><c:out value="${Parents.student.name}"/></label></td>
                                <td class="namedetails" style="font-size:20px;"><label>Class:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
                                <label style="color:red;">
                                <c:forEach var="splt" items="${fn:split(Parents.student.classstudying,'--')}">
						    ${splt} 
							</c:forEach></label>
                                </td>
                                
                             </tr>
                             
                             
                             <tr>   
				<td class="namedetails" style="font-size:20px;"><label>Father's Name:&nbsp;&nbsp;&nbsp;</label><label style="text-transform: capitalize;color:red"><c:out value="${Parents.fathersname}"/></label></td>	
                                <td class="namedetails" style="font-size:20px;"><label>Roll. No.:&nbsp;&nbsp;&nbsp;</label><!--<c:out value="${Parents.student.admissionnumber}"/>--></td>
                                <!--<td class="namedetails"><label>Date Of Issue:&nbsp;&nbsp;</label><input
									name="dateofcr" type="text" class="textField" style="border: none;border-color: transparent;"
									 size="10" value="<fmt:formatDate type="date" value="${now}" pattern="dd-MM-yyyy"/>" ></td> -->
                            </tr>
                            
                           <!-- <tr>
                            <td><br></td>
                            </tr> -->
                            
                            </table>
                            
                            <table style=" border-collapse: collapse;width: 100%;">
                            	<thead style="background-color:#ac988d;">
                            	<tr>
                            	<th class="subjectdetails" style="font-size:25px;">Date</th>
                            	<th class="subjectdetails" style="font-size:25px;">Day</th>
                            	<th class="subjectdetails" style="font-size:25px;">Subject</th>
                            	<!-- <th class="subjectdetails" style="font-size:25px;">Time</th> -->
                            	<th class="subjectdetails" style="font-size:17px;">Examiner's<br> Sign</th>
                            	</tr>
                            	</thead>
                            	<tbody>
                            	<c:forEach items="${examschedulelist}" var="examschedulelist">
                             	<tr>
                                <td class="subjectdetails" style="font-size:25px;"><fmt:formatDate value="${examschedulelist.date}" pattern="dd/MM/yyyy"/></td>
                                <td class="subjectdetails" style="font-size:25px;"><fmt:formatDate value="${examschedulelist.date}" pattern="E"/></td>
                                <td class="subjectdetails" style="font-size:25px;"><c:out value="${examschedulelist.subject}"/></td>
                                <%-- <td class="subjectdetails" style="font-size:20px;"><c:out value="${examschedulelist.starttime}"/>&nbsp;-&nbsp;<c:out value="${examschedulelist.endtime}"/></td> --%>
                                <td class="subjectdetails" style="font-size:25px;"></td>
                                </tr>
                                 </c:forEach>
                       
                   				 </tbody>
                            </table>
                            
                            
                            <TABLE id="dataTable" width="100%" border="0"
			style="page-break-inside:avoid; border-collapse: collapse;">

						<tr>
							<td><br><br><br></td>
						</tr>
						<tr>
							<td><br></td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
						<tr>
						<td><br></td>
						</tr>
				<tr>
				<td></td>
				<td align="left" style="font-size:25px;">Class Teacher</td>	
					<td align="centre" style="font-size:25px;">&emsp;</td>
					<td align="centre" style="font-size:25px;">Head Master</td>
					</tr>
                    
		</TABLE>
		 <!-- <table style="text-align:left;font-size:25px;">
                        <tr>
                        <td>Note:</td>
                        </tr>
                         <tr>
                        <td>1) Make your child to memorize the given revision daily. </td>
                        </tr>
                         <tr>
                        <td>2) In case postponed, exam continues as per schedule,</td>
                        </tr>
                         <tr>
                        <td>postponed paper will be after exam</td>
                        </tr>
                         <tr>
                        <td>3)Holidays are from 21/03/2025 March to 31/05/2025 May</td>
                        </tr>
                         <tr>
                        <td>4)Parent-Teacher meeting will be on 5/04/2025 April </td>
                        </tr>
                        
                        </table> -->  
		</div>
		<br>
                               
                        </c:forEach>
                        
                       
			
	</form>
	
	
</body>
</html>
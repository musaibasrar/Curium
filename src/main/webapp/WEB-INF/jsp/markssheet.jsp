<%-- 
    Document   : Marks Sheet
    Created on : Nov 29 2021, 09:22 PM
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
	font-size: 14px;
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
            .fontsize { font-size: 10px ;
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
            .fontsize { font-size: 10px;
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
	<title> </title>
        
</head>
  <%
//allow access only if session exists
String user = null;
if(session.getAttribute("userAuth") == null){
	response.sendRedirect("/abc/UserProcess/sessionTimeOut");
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
                        <c:forEach items="${markssheetlist}" var="Parents">
                        
                        <div style="page-break-inside: avoid;border-style: solid;border-width: thin;">   
                        	<table style="page-break-inside: avoid;border-collapse: collapse;">
			<tr>
				<td style="padding-left: 200px;"><img src="/abc/images/abc.jpg" width="72" height="80"/></td>
				<td>
				<label class="dataTextBoldCenter">${branchname}</label><br>
				<label class="addressLine">${branchaddress}</label>
				</td>
			</tr>
			<tr>
			<td></td></tr>
			<tr></tr>
</table>

			<TABLE  width="100%" border="1" style="page-break-inside: avoid;border-collapse:collapse;">
                <tr>

                    <td colspan="4" ></td>

                </tr>
            </TABLE>

			

			<table style="border-collapse: collapse;width: 70%;float: left">
											
                            <tr style="border-color:#000000">
                                <td class="namedetails"><label>Student Name:&nbsp;&nbsp;&nbsp;</label><label style="font-weight: bold;text-transform: capitalize;"><c:out value="${Parents.parents.student.name}"/></label></td>
                                <td class="namedetails"><label>Class:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
                                <c:forEach var="splt" items="${fn:split(Parents.parents.student.classstudying,'--')}">
						    ${splt} 
							</c:forEach>
                                </td>
                                
                             </tr>
                             
                             
                             <tr>   
				<td class="namedetails"><label>Father's Name:&nbsp;&nbsp;&nbsp;</label><label style="font-weight: bold;text-transform: capitalize;"><c:out value="${Parents.parents.fathersname}"/></label></td>	
                                <td class="namedetails"><label>Exam Reg. No.:&nbsp;&nbsp;&nbsp;</label><c:out value="${Parents.parents.student.admissionnumber}"/></td>
                                <!--<td class="namedetails"><label>Date Of Issue:&nbsp;&nbsp;</label><input
									name="dateofcr" type="text" class="textField" style="border: none;border-color: transparent;"
									 size="10" value="<fmt:formatDate type="date" value="${now}" pattern="dd-MM-yyyy"/>" ></td> -->
                            </tr>
                            
                           <!-- <tr>
                            <td><br></td>
                            </tr> -->
                            
                            </table>
                            
                            <table style=" border-collapse: collapse;width: 30%;float: left">
                            	<tr>
                            		<td>
                            			<img src="data:image;base64,<c:out value="${Parents.parents.student.studentpic}"/>" alt="Student's Photo" style="width: 60px;height: 60px;">
                            		</td>
                            	</tr>
                            
                            </table>
                            
                            <!-- <TABLE  width="100%" border="1" style="page-break-inside: avoid;border-collapse:collapse;">
               					 <tr>
                   					 <td colspan="4" ></td>
                				</tr>
           					 </TABLE> -->
           					 	<c:set var = "beginloop" value = "0"/>
           					 	<c:set var = "endloop" value = "4"/>
           					 	<c:forEach begin="0" end="${endloop}" step="1">
           					 		
           					 			<table style=" border-collapse: collapse;width: 100%;">
                            	<thead>
                            	<tr style="border: 1px solid black;text-align: center;">
                            	<c:forEach items="${Parents.exammarks}" var="exammarks" begin="${beginloop}" end= "${endloop}" step="1">
                            		<th><c:out value="${exammarks.examName}"/></th>
                            	</c:forEach>
                            	</tr>
                            	</thead>
                            	
                            	<tbody>
                            	
                             	<tr>
                             	<c:forEach items="${Parents.exammarks}" var="exammarks" begin="${beginloop}" end= "${endloop}" step="1">
                                <td>
                                	<table style=" border-collapse: collapse;width: 100%;border: 1px solid black;">
											<c:forEach items="${exammarks.subMarks}" var="submarks" >
	                                	<tr style="border: 1px solid black;">
	                                		<c:set var="dateParts" value="${fn:split(submarks.value,'_')}" />
	                                		<td style="border: 1px solid black;text-align: left;">${submarks.key}</td>
	                                		<td style="border: 1px solid black;text-align: left;">${dateParts[0]}(${dateParts[1]})</td>
	                                	</tr>
	                                </c:forEach>
	                                		<tr style="border-top: 1px solid black;">
	                                			<td style="border: 1px solid black;text-align: left;">Total Marks Obtained</td>
	                                			<td style="border: 1px solid black;text-align: left;">${exammarks.totalMarksObtained}</td>
	                                		</tr>
	                                		<tr style="border: 1px solid black;">
                            					<td style="border: 1px solid black;text-align: left;">Total Marks</td>
                            					<td style="border: 1px solid black;text-align: left;">${exammarks.totalMarks}</td>
	                                		</tr>
	                                		<tr style="border: 1px solid black;">
                            					<td style="border: 1px solid black;text-align: left;">Percentage</td>
                            					<td style="border: 1px solid black;text-align: left;">
                            					<fmt:formatNumber type = "number" maxFractionDigits = "1" value = "${exammarks.percentage}" /></td>
	                                		</tr>	
	                                		<tr style="border: 1px solid black;">
                            					<td style="border: 1px solid black;text-align: left;">Remarks</td>
                            					<td style="border: 1px solid black;text-align: left;"></td>
	                                		</tr>                                	
	                                	</table>
                                </td>
                               </c:forEach>
                                </tr>
                                 
                       
                   				 </tbody>
                            </table>
                            		<c:set var = "beginloop" value = "${beginloop+5}"/>
           					 	<c:set var = "endloop" value = "${endloop+5}"/>
           					 	</c:forEach>
                            <%-- <table style=" border-collapse: collapse;width: 100%;border: 1px solid black;">
                            
                            	<tbody>
                            		<c:forEach items="${Parents.exammarks}" var="exammarks">
                            			<tr style="border: 1px solid black;">
	                               			 <td style="border: 1px solid black;text-align: center;"><label style="text-transform: capitalize;font-weight: bold;"><c:out value="${exammarks.examName}"/></label>
	                                	<table style=" border-collapse: collapse;width: 100%;border: 1px solid black;">
											<c:forEach items="${exammarks.subMarks}" var="submarks">
	                                	<tr style="border: 1px solid black;">
	                                		<c:set var="dateParts" value="${fn:split(submarks.value,'_')}" />
	                                		<td style="border: 1px solid black;text-align: left;">${submarks.key}</td>
	                                		<td style="border: 1px solid black;text-align: left;">${dateParts[0]}(${dateParts[1]})</td>
	                                	</tr>
	                                </c:forEach>
	                                		<tr style="border-top: 1px solid black;">
	                                			<td style="border: 1px solid black;text-align: left;">Total Marks Obtained</td>
	                                			<td style="border: 1px solid black;text-align: left;">${exammarks.totalMarksObtained}</td>
	                                		</tr>
	                                		<tr style="border: 1px solid black;">
                            					<td style="border: 1px solid black;text-align: left;">Total Marks</td>
                            					<td style="border: 1px solid black;text-align: left;">${exammarks.totalMarks}</td>
	                                		</tr>
	                                		<tr style="border: 1px solid black;">
                            					<td style="border: 1px solid black;text-align: left;">Percentage</td>
                            					<td style="border: 1px solid black;text-align: left;">
                            					<fmt:formatNumber type = "number" maxFractionDigits = "1" value = "${exammarks.percentage}" /></td>
	                                		</tr>	
	                                		<tr style="border: 1px solid black;">
                            					<td style="border: 1px solid black;text-align: left;">Remarks</td>
                            					<td style="border: 1px solid black;text-align: left;"></td>
	                                		</tr>                                	
	                                	</table>
	                                </td>
	                                </tr>
	                                <c:forEach items="${exammarks.subMarks}" var="submarks">
	                                	<tr>
	                                		<td>${submarks.key}</td>
	                                		<td>${submarks.value}</td>
	                                	</tr>
	                                </c:forEach>
	                                
                            	</c:forEach>
                       
                   				 </tbody>
                            </table> --%>
                            
                            
                            <TABLE id="dataTable" width="100%" border="0"
			style="page-break-inside:avoid; border-collapse: collapse;">

						<tr>
							<td><br></td>
						</tr>
				<tr>
					<td></td>
					<td align="left">Class Teacher</td>	
					<td align="centre">Principal</td>
					<td align="centre">Parent</td>
				</tr>
                    
		</TABLE>
		</div>
		<br><br><br>
                                 
                        </c:forEach>
			
	</form>
	
	
</body>
</html>

<%-- 
    Document   : No Due Print
    Created on : Mar 17 2018, 12:32 PM
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
	font-size: 14px;
	letter-spacing: normal;
	text-align: left;
	border: 1px solid black;
  text-align: left;
  padding: 8px;
}

.dataTextBoldLeftDetails {
	font-weight: normal;
	font-family: Tahoma;
	color: black;
	font-size: 14px;
	letter-spacing: normal;
	text-align: left;
	border: 1px solid black;
  text-align: left;
  padding: 8px;
  width: 300px;
}

.dataTextBoldLeftDate {
	font-weight: normal;
	font-family: Tahoma;
	color: black;
	font-size: 14px;
	letter-spacing: normal;
	text-align: left;
}

.dataTextBoldCenter {
	font-weight: bold;
	font-family: Tahoma;
	color: #121298;
	font-size: 48px;
	letter-spacing: normal;
	text-align: center;
}

.addressLine{
	font-weight: normal;
	font-family: ariel;
	color: #172277;
	font-size: 14px;
	letter-spacing: normal;
	/* text-align: center; */
}

.dataText {
	font-family: Tahoma;
	color: black;
	font-size: 12px;
	letter-spacing: normal;
	text-align: center;
}

span{
    display:inline-block;
    border-bottom:2px solid black;
    padding-bottom:1px;
    width: 250px;
    font-weight: normal;
}

#letterheadfooter {
    position:absolute;
    bottom:0px;
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
            div.divFooter {
   				 position: fixed;
   				 bottom: 0;
  			}
            
        }
        
        @page {
              
             margin-left:  1cm;
             margin-right: 1cm;
             margin-bottom: 0cm;
             margin-top: cm;
             size: auto;
        }

        @media screen {
            .fontsize { font-size: 15px;
                        font-weight: bold;
                        font-family: 'Times New Roman'
            }
            div.divFooter {
    			display: none;
 			 }
            .bodymargin{
                margin-left: 0px ;
                margin-right: 0px;
            }
        }
        
    </style>
        <title>Bonafide Print</title>
        <script type="text/javascript">
             window.onload = function(){
            	 window.print();
             }
        </script>

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
	<c:if test="${(letterheadvalue)}">
		<div id="letterhead">
		<table width="100%" style="border-collapse: collapse;">
			
			<tr>
				
				<td>
					<img src="data:image;base64,<c:out value="${certlogo}"/>" alt="logo" style="width: 150px;height: 150px;">
				</td>
				
				<td class="dataTextBoldCenter" style="width: 100%">
					${certinstitutionname}
					<c:if test="${(certaffiliation != '')}">
					<br>
					<label class="addressLine">${certaffiliation}</label>
					</c:if>
			</tr>
		</table>

			<TABLE  width="100%" border="1" style="border-collapse:collapse;color: #172277;">
                <tr>

                    <td colspan="4" ></td>

                </tr>
            </TABLE>
		</div>
		</c:if>
		
		<c:if test="${(!letterheadvalue)}">
			<br><br><br><br><br><br><br>
		</c:if>
		<table>
			<tr>
			<td class="dataTextBoldLeftDate">
			<br><br>
				Date:&nbsp;&nbsp;
				<input name="dateofcr" type="text" class="textField" style="border: none;border-color: transparent;"
					size="10" value="<fmt:formatDate type="date" value="${now}" pattern="dd/MM/yyyy"/>" ></td>
			
			</tr>
			
			<tr>
				<td colspan="4" style="font-weight: bold;font-family: Tahoma;color: black;font-size: 22px;letter-spacing: normal;text-align: center;padding-left: 200px;">
					<br>
					<u>No Due</u>
					<br><br>
				</td>
			</tr>
			<tr>
			<td></td>
			
			</tr>
			<tr>
			<td></td>
			
			</tr>
			<tr>
				<td>
					<table style="border-collapse: collapse;margin-left: 150px;">
			<tr>
			
				<td class="dataTextBoldLeft">
					Student Name: &nbsp;&nbsp;
				</td>
				<td class="dataTextBoldLeftDetails">
					 <c:out value="${studentdetailsnodue.student.name}" />
				</td>
			</tr>
			<tr>
			
				<td class="dataTextBoldLeft" >
					UIN: &nbsp;&nbsp;
				</td>
				<td class="dataTextBoldLeftDetails">
					<c:out value="${studentdetailsnodue.student.studentexternalid}" />
				</td>
			</tr>
			<tr>
			
				<td class="dataTextBoldLeft" >
					Registration Number: &nbsp;&nbsp;
				</td>
				<td class="dataTextBoldLeftDetails">
					<c:out value="${studentdetailsnodue.student.admissionnumber}" />
				</td>
			</tr>
			<tr>
				<td class="dataTextBoldLeft">
					Father Name: &nbsp;&nbsp;
				</td>
				<td class="dataTextBoldLeftDetails">
					<c:out value="${studentdetailsnodue.fathersname}" />
				</td>
			</tr>
			<tr>
				<td class="dataTextBoldLeft">
					Course: &nbsp;&nbsp;
				</td>
				<td class="dataTextBoldLeftDetails">
					
								
								<c:forEach var="splt" items="${fn:split(studentdetailsnodue.student.classstudying,'--')}">
						    		${splt} 
								</c:forEach>
				</td>
			</tr>
			<tr>
				<td class="dataTextBoldLeft">
					College Fees: &nbsp;&nbsp;
				</td>
				<td class="dataTextBoldLeftDetails">
					
				</td>
			</tr>
			<tr>
				<td class="dataTextBoldLeft">
					Exam Fees: &nbsp;&nbsp;
				</td>
				<td class="dataTextBoldLeftDetails">
					
				</td>
			</tr>
			<tr>
				<td class="dataTextBoldLeft">
					Library Due: &nbsp;&nbsp;
				</td>
				<td class="dataTextBoldLeftDetails">
					
				</td>
			</tr>
			<tr>
				<td class="dataTextBoldLeft">
					Laboratory Due: &nbsp;&nbsp;
				</td>
				<td class="dataTextBoldLeftDetails">
					
				</td>
			</tr>
			</table>
				
				</td>
			</tr>					
			
			<tr>
			<td></td>
			</tr>
			<tr>
			<td></td>
			</tr>
			<tr>
			<td></td>
			</tr>
		</table>
		

		<TABLE id="dataTable" width="100%" border="0"
			style="border-collapse: collapse;">

			<tr>
			<td>
			<br>
			<br><br><br></td>
			</tr>
			<tr>
			<td></td>
			</tr>
			<tr>
			<td></td>
			</tr>
		<tr>
		<td></td>
			<td align="left">Accountant Sign</td>	
			<td align="center">Librarian Sign</td>
			<td align="center">Lab Assistant Sign</td>
		</tr>
		        <tr>
			<td>
			<br>
			<br><br><br></td>
			</tr><tr>
			<td>
			<br>
			<br><br><br></td>
			</tr>
			<tr>
			<td>
			<br>
			<br><br><br></td>
			</tr>
			<tr>
			<td>
			<br>
			<br><br><br></td>
			</tr>
			<tr>
			<td>
			<br>
			<br><br><br></td>
			</tr>
			<tr>
			<td>
			<br>
			<br><br><br></td>
			</tr>
			<tr>
			<td>
			<br>
			<br><br><br></td>
			</tr>
			<tr>
			<td>
			<br>
			<br><br><br></td>
			</tr>
			<tr>
			<td>
			<br>
			<br><br><br></td>
			</tr>
			<tr>
			<td>
			<br>
			</td>
			</tr>
		</TABLE>
		<c:if test="${(letterheadvalue)}">
			<div id="letterheadfooter" style="width: 100%">
			<TABLE  width="100%" border="1" style="border-collapse:collapse;color: #172277;">
                <tr>

                    <td colspan="4" ></td>

                </tr>
            </TABLE>
            <table width="100%" style="page-break-after: always; border-collapse:collapse;">
            	<tr>
            		<td>
            		<c:if test="${(certaddress != '')}">
            		<img src="images/map.svg" width="25" height="25" alt="email" style="vertical-align: bottom;"/>
					<label style="color: #121298;">${certaddress}</label>
					</c:if>
            		</td>
                	<td align="right">
                	<p>
                	<c:if test="${(certcontactnumber != '')}">
                	  <img	src="images/phone.svg" width="25" height="25" alt="phone" style="vertical-align: bottom;"/><label style="color: #121298;">${certcontactnumber}
                	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
                	</c:if>
                	<c:if test="${(certemail != '')}">
                	<img src="images/email.svg" width="25" height="25" alt="email" style="vertical-align: bottom;"/><label style="color: #121298;">${certemail}</label></p>
                	</c:if>
                	</td>
                	<td align="right">
                		<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                	</td>
                </tr>
            </table>
            </div>
            </c:if>
	</form>
	
	
</body>
</html>

<%-- 
    Document   : bonafide certificate
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
	font-size: 18px;
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
    width: 250px;
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
              
             margin-left:  0cm;
             margin-right: 0cm;
             margin-bottom: 0cm;
             margin-top: 0cm;
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
	response.sendRedirect("/sneha/UserProcess/sessionTimeOut");
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
		<br><br>
		<table align="center">
			
			
			<tr>
			<td>
			<img border="0" style="vertical-align: text-bottom;height: 105px;width: 113px;" alt="logo" src="/sneha/images/sneha.png">
			</td>
				<td >
					<br>
					<h2 style="margin-bottom:0px;">${branchname}</h2>
					<h4 style="margin-top:0px;margin-bottom:0px;">${branchaddress}</h4>
					 <h4 style="margin-top:0px;margin-bottom:0px;">Ph: 0824 2244629Email:snehapublicschool@gmail.com</h4>
                     <h4 style="margin-top:0px;margin-bottom:0px;"> Website:www.snehapublicschool.com</h4>
					
					
				</td>
			</tr>
			<%-- ${branchcontact}<br> --%>
			</table>
			
		<table align="center" style="padding-left: 30px;padding-right: 20px;">
			<tr>
			<td class="dataTextBoldLeft">
			<br>
				<%-- Date:&nbsp;&nbsp;
				<input name="dateofcr" type="text" class="textField" style="border: none;border-color: transparent;"
					size="10" value="<fmt:formatDate type="date" value="${now}" pattern="yyyy-MM-dd"/>" > --%></td>
			
			</tr>
			
			<tr><td><br></td></tr>
			<tr><td><br></td></tr>
			<tr><td><br></td></tr>
			<tr><td><br></td></tr>
			
			<tr>
				<td colspan="4" class="dataTextBoldCenter">
					<br>
					TO WHOMSOEVER IT MAY CONCERN
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
			
				<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">&emsp;&emsp;&emsp;&emsp;This is to certify that  &nbsp;&nbsp;
					 <c:out value="${studentdetailsbonafide.student.name}" />
					S/o, D/o &nbsp;&nbsp;<c:out value="${studentdetailsbonafide.fathersname}" />	&nbsp;&nbsp;and	
					</h3>
				</td>
			
			</tr>
			<tr>
			<td></td>
			</tr>
			<tr>
			
				<td class="dataTextBoldLeft"  >
				
				<h3 style="font-weight: normal;" > <c:out value="${studentdetailsbonafide.mothersname}" />	&nbsp;&nbsp;			
					 was a bonafide student of this School.
					 
										 He/She has studied in</h3>
				</td></tr>
				<tr>
			
				<td class="dataTextBoldLeft"  >
				
				<h3 style="font-weight: normal;" >
										 
										  our institution from class &nbsp;&nbsp; <c:forEach var="splt" items="${fn:split(studentdetailsbonafide.student.classadmittedin,'--')}">
						    		${splt} 
								</c:forEach>
								&nbsp;&nbsp;&nbsp;&nbsp;to&nbsp;&nbsp;
								<c:forEach var="splt" items="${fn:split(studentdetailsbonafide.student.classstudying,'--')}">
						    		${splt} 
								</c:forEach>
								&nbsp;&nbsp;&nbsp;&nbsp;
					std
					in the year &nbsp;&nbsp;&nbsp;&nbsp;${studentdetailsbonafide.student.yearofadmission}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;to
					</td></tr>
				<tr>
			
				<td class="dataTextBoldLeft"  >
				
				<h3 style="font-weight: normal;" >
					 ${studentdetailsbonafide.student.promotedyear}&nbsp;&nbsp;&nbsp;&nbsp;</h3>
				</td></tr>
				<tr>
			
				<td class="dataTextBoldLeft"  >
				
				<h3 style="font-weight: normal;" >
				Admission No.&nbsp;&nbsp;${studentdetailsbonafide.student.admissionnumber}&nbsp;&nbsp; </h3>
				</td></tr>
				
								<tr>
			
				<td class="dataTextBoldLeft"  >
				
				<h3 style="font-weight: normal;" >
				STS No.&nbsp;&nbsp;${studentdetailsbonafide.student.sts}&nbsp;&nbsp; </h3>
				</td></tr>
				<tr>
			
			<tr><td><br></td></tr>
			<tr><td><br></td></tr>
			
				<td class="dataTextBoldLeft"  >
				
				<h3 style="font-weight: normal;" >
				&emsp;&emsp;&emsp;&emsp;
				&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;Thanking you</h3>
				</td></tr>
				
				
				<tr><td><br></td></tr>
				<tr><td><br></td></tr>
				<tr><td><br></td></tr>
				<tr><td><br></td></tr>
				
				<tr>
			
				<td class="dataTextBoldLeft"  >
				
				<h3 style="font-weight: normal;" >
				&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
					&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
					&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;Headmistress</h3>
				</td></tr>
			
		</table>
		

			</form>
	
	
</body>
</html>

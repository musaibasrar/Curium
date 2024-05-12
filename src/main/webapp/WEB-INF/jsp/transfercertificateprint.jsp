
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
    width: 200px;
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
             margin-bottom: 0cm;
             margin-top: 0cm;
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
	<script type="text/javascript" src="/alirfan/js/datePicker/jquery-1.7.1.js"></script>
        <script type="text/javascript" src="/alirfan/js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
        <script src="/alirfan/js/print/jquery.printPage.js" type="text/javascript"></script>
        <title>Print Transfer Certificate</title>
        <script type="text/javascript">
             $(function() {

                 $("#print").printPage();
             });
        </script>

</head>
<%
//allow access only if session exists
String user = null;
if(session.getAttribute("userAuth") == null){
	response.sendRedirect("/alirfan/UserProcess/sessionTimeOut");
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
		<br>
		<table width="100%" style="border-collapse: collapse;">
			<tr>
				<td  style="width: 100%">
				
			<h2 style="margin-bottom:0px;">	Al Falah High School </h2></td>
			</tr>
			<tr>
			<td class="addressLine">Shanti Nagar, Lalapet, Secunderabad-500017</td>
			</tr>
			<tr>
			<td class="addressLine">Mobile No.: 8143802598, Email: alfalahhighschool@gmail.com</td>
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

		<table style="margin:20px;">
		<tr>
			
			<td colspan="4">
			
			<h4>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<u>TRANSFER CERTIFICATE</u></h4>
			</td>
			
			</tr>
			<tr>
			<td >
			Date:&nbsp;&nbsp;<input
									name="dateoftc" type="text" class="textField" style="border: none;border-color: transparent;"
									 size="10" value="<fmt:formatDate type="date" value="${tcdetails.dateofissues}" pattern="dd/MM/yyyy"/>" ></td>
			
			</tr>
			
			<tr>
			
				<td class="dataTextBoldLeft">
					<h4 style="font-weight: normal;">&nbsp;&nbsp;&nbsp;&nbsp;Addmission no. &nbsp;&nbsp;
					<span style="font-weight: bold;border-bottom-style: dotted;"> <c:out value="${studentdetails.student.admissionnumber}" /></span>
					Book no. &nbsp;&nbsp;
					<span style="font-weight: bold;border-bottom-style: dotted;"> </span>
					TC no. &nbsp;&nbsp;
					<span style="font-weight: bold;border-bottom-style: dotted;"></span>
					</h4>
				</td>
			
			
			
			</tr>
			<tr>
			
			<td class="dataTextBoldLeft">
					<h4 style="font-weight: normal;">1.&nbsp;&nbsp;Name of the Pupil &nbsp;&nbsp;
					<span style="font-weight: bold;border-bottom-style: dotted;width:600px;"> <c:out value="${studentdetails.student.name}" /></span>
					</h4>
				</td>
				
				
			
			</tr>
			<tr>
			<td class="dataTextBoldLeft">
					<h4 style="font-weight: normal;">2.&nbsp;&nbsp;Mother's Name &nbsp;&nbsp;
					<span style="font-weight: bold;border-bottom-style: dotted;width:640px;"> <c:out value="${studentdetails.mothersname}" /></span>
					</h4>
				</td>
				
			</tr>
			<tr>
			<td class="dataTextBoldLeft">
					<h4 style="font-weight: normal;">3.&nbsp;&nbsp;Father's Name/Guardian's Name &nbsp;&nbsp;
					<span style="font-weight: bold;border-bottom-style: dotted;width:620px;"> <c:out value="${studentdetails.fathersname}" /></span>
					</h4>
				</td>
			</tr>
			
			<tr>
			<td class="dataTextBoldLeft">
					<h4 style="font-weight: normal;">4.&nbsp;&nbsp;Nationality &nbsp;&nbsp;
					<span style="font-weight: bold;border-bottom-style: dotted;width:740px;"> <c:out value="${studentdetails.student.nationality}" /></span>
					</h4>
				</td>
			</tr><tr><td class="dataTextBoldLeft">
					<h4 style="font-weight: normal;">5.&nbsp;&nbsp;Whether the Student Belongs to Shedule Caste or Shedule Tribes &nbsp;&nbsp;
					<span style="font-weight: bold;border-bottom-style: dotted;width:400px;">
					<c:if test="${(studentdetails.student.socialcategory == 'General')}">
							<c:out value="No" />
						</c:if>
						<c:if test="${(studentdetails.student.socialcategory == 'OBC')}">
							<c:out value="No" />
						</c:if>
						<c:if test="${(studentdetails.student.socialcategory == 'SC')}">
							<c:out value="Yes" />
						</c:if>
						<c:if test="${(studentdetails.student.socialcategory == 'ST')}">
							<c:out value="Yes" />
						</c:if>
					
					 </span>
					</h4>
				</td>
			</tr>
			<tr>
			<td class="dataTextBoldLeft">
					<h4 style="font-weight: normal;">6.&nbsp;&nbsp;Date of first Admission in the School &nbsp;&nbsp;
					<span style="font-weight: bold;border-bottom-style: dotted;"> <c:out value="${studentdetails.student.admissiondate}" /></span>
					&nbsp;&nbsp;in Class &nbsp;&nbsp;
					<span style="font-weight: bold;border-bottom-style: dotted;"> 
					<c:set var="splt" value="${fn:split(studentdetails.student.classadmittedin,'--')}"/>
					<c:out value="${splt[0]}" />
					</span>
					</h4>
				</td>
				</tr>
				<tr>
				<td class="dataTextBoldLeft">
					<h4 style="font-weight: normal;">7.&nbsp;&nbsp;Date of Birth as Recorded in Admission Register
					
					</h4>
				</td>
				</tr>
				<tr>
			<td class="dataTextBoldLeft">
					<h4 style="font-weight: normal;">&nbsp;&nbsp;&nbsp;&nbsp;In Figures &nbsp;&nbsp;
					<span style="font-weight: bold;border-bottom-style: dotted;"> <c:out value="${studentdetails.student.dateofbirth}" /></span>
					&nbsp;&nbsp;&nbsp;&nbsp;In Words &nbsp;&nbsp;
					<span style="font-weight: bold;border-bottom-style: dotted;"> <c:out value="${dateinword}" /> </span>
					</h4>
				</td>
			</tr>
			<tr>
				<td class="dataTextBoldLeft">
					<h4 style="font-weight: normal;">8.&nbsp;&nbsp;Class in which Pupil Studied Last:
					
					</h4>
				</td>
				</tr>
				<tr>
			<td class="dataTextBoldLeft">
					<h4 style="font-weight: normal;">&nbsp;&nbsp;&nbsp;&nbsp;In Figures &nbsp;&nbsp;
					<span style="font-weight: bold;border-bottom-style: dotted;"> 
						<c:set var="splt" value="${fn:split(studentdetails.student.classstudying,'--')}"/>
					<c:out value="${splt[0]}" />
					
					</span>
					&nbsp;&nbsp;&nbsp;&nbsp;In Words &nbsp;&nbsp;
					<span style="font-weight: bold;border-bottom-style: dotted;"> </span>
					</h4>
				</td>
			</tr>
			<tr>
				<td class="dataTextBoldLeft">
					<h4 style="font-weight: normal;">9.&nbsp;&nbsp;School/Board Annual Examination Last taken with Result:
					
					</h4>
				</td>
				</tr>
				<tr><td class="dataTextBoldLeft">
				<h4 style="font-weight: normal;">&nbsp;&nbsp;&nbsp;&nbsp;<span style="font-weight: bold;border-bottom-style: dotted;width:800px;"> </span>
					</h4></td>
				</tr>
				<tr>
			<td class="dataTextBoldLeft">
					<h4 style="font-weight: normal;">10.&nbsp;&nbsp;Whether failed if so Once/Twice in the same Class &nbsp;&nbsp;
					<span style="font-weight: bold;border-bottom-style: dotted;width:450px;"></span>
					</h4>
				</td>
			</tr>
			
			<tr>
			<td class="dataTextBoldLeft">
					<h4 style="font-weight: normal;">11.&nbsp;&nbsp;Subject studied: &nbsp;&nbsp;
					</h4>
				</td>
				</tr>
			
			<tr>
				<td class="dataTextBoldLeft">
					<h4 style="font-weight: normal;">1. &nbsp;&nbsp;
					<span style="font-weight: bold;border-bottom-style: dotted;"> </span>
					2. &nbsp;&nbsp;
					<span style="font-weight: bold;border-bottom-style: dotted;"> </span>
					</h4>
				</td>
				</tr>
				<tr>
				<td class="dataTextBoldLeft">
					<h4 style="font-weight: normal;">3. &nbsp;&nbsp;
					<span style="font-weight: bold;border-bottom-style: dotted;"> </span>
					4. &nbsp;&nbsp;
					<span style="font-weight: bold;border-bottom-style: dotted;"> </span>
					</h4>
				</td>
				</tr>
				<tr>
				<td class="dataTextBoldLeft">
					<h4 style="font-weight: normal;">5. &nbsp;&nbsp;
					<span style="font-weight: bold;border-bottom-style: dotted;"></span>
					6. &nbsp;&nbsp;
					<span style="font-weight: bold;border-bottom-style: dotted;"> </span>
					</h4>
				</td>
			</tr>
			<tr>
				<td class="dataTextBoldLeft">
					<h4 style="font-weight: normal;">12.&nbsp;&nbsp;Whether qualified for Promotion to the higher Class
					
					</h4>
				</td>
				</tr>
				<tr>
			<td class="dataTextBoldLeft">
					<h4 style="font-weight: normal;">&nbsp;&nbsp;&nbsp;&nbsp;In Figures &nbsp;&nbsp;
					<span style="font-weight: bold;border-bottom-style: dotted;"></span>
					&nbsp;&nbsp;&nbsp;&nbsp;In Words &nbsp;&nbsp;
					<span style="font-weight: bold;border-bottom-style: dotted;"> </span>
					</h4>
				</td>
			</tr>
			<tr>
			<td class="dataTextBoldLeft">
					<h4 style="font-weight: normal;">13.&nbsp;&nbsp;Month upto which the School Dues paid &nbsp;&nbsp;
					<span style="font-weight: bold;border-bottom-style: dotted;width:500px;"></span>
					</h4>
				</td>
			</tr>
			<tr>
			<td class="dataTextBoldLeft">
					<h4 style="font-weight: normal;">14.&nbsp;&nbsp;Any concession available of,if so,the nature of such concession &nbsp;&nbsp;
					<span style="font-weight: bold;border-bottom-style: dotted;width:400px;"></span>
					</h4>
				</td>
			</tr>
			<tr>
			<td class="dataTextBoldLeft">
					<h4 style="font-weight: normal;">15.&nbsp;&nbsp;Total No. of working days &nbsp;&nbsp;
					<span style="font-weight: bold;border-bottom-style: dotted;width:600px;"> </span>
					</h4>
				</td>
			</tr>
			<tr>
			<td class="dataTextBoldLeft">
					<h4 style="font-weight: normal;">16.&nbsp;&nbsp;Total No. of working days present &nbsp;&nbsp;
					<span style="font-weight: bold;border-bottom-style: dotted;width:580px;"> </span>
					</h4>
				</td>
			</tr>
			<tr>
			<td class="dataTextBoldLeft">
					<h4 style="font-weight: normal;">17.&nbsp;&nbsp;Whether NCC cadet/Boy Scout/Girl Scout (detail may be given) &nbsp;&nbsp;
					<span style="font-weight: bold;border-bottom-style: dotted;width:400px;"> </span>
					</h4>
				</td>
			</tr>
			
			<tr>
			<td class="dataTextBoldLeft">
					<h4 style="font-weight: normal;">18.&nbsp;&nbsp;Games played or extra curricular activities in which the pupil usually took part&nbsp;&nbsp; </td></tr>
					<tr>
			<tr>
			<td class="dataTextBoldLeft">
					<h4 style="font-weight: normal;">&nbsp;&nbsp;&nbsp;&nbsp;(mention achievement level threrein) &nbsp;&nbsp;
					<span style="font-weight: bold;border-bottom-style: dotted;width:550px;"></span>
					</h4>
				</td>
			</tr>
			<tr>
			<td class="dataTextBoldLeft">
					<h4 style="font-weight: normal;">19.&nbsp;&nbsp;Genaral conduct &nbsp;&nbsp;
					<span style="font-weight: bold;border-bottom-style: dotted;width:650px;"></span>
					</h4>
				</td>
			</tr>
			<tr>
			<td class="dataTextBoldLeft">
					<h4 style="font-weight: normal;">20.&nbsp;&nbsp;Date of application for Certificate &nbsp;&nbsp;
					<span style="font-weight: bold;border-bottom-style: dotted;width:580px;"> </span>
					</h4>
				</td>
			</tr>
			<tr>
			<td class="dataTextBoldLeft">
					<h4 style="font-weight: normal;">21.&nbsp;&nbsp;Date of issue for Certificate &nbsp;&nbsp;
					<span style="font-weight: bold;border-bottom-style: dotted;width:600px;"><fmt:formatDate
							value="${studentdetails.student.datetcissued}" pattern="dd/MM/yyyy" /> </span>
					</h4>
				</td>
			</tr>
			<tr>
			<td class="dataTextBoldLeft">
					<h4 style="font-weight: normal;">22.&nbsp;&nbsp;Reason for leaving the school &nbsp;&nbsp;
					<span style="font-weight: bold;border-bottom-style: dotted;width:600px;"><c:out value="${studentdetails.student.reasonleaving}" /></span>
					</h4>
				</td>
			</tr>
			<tr>
			<td class="dataTextBoldLeft">
					<h4 style="font-weight: normal;">23.&nbsp;&nbsp;Any other Remarks &nbsp;&nbsp;
					<span style="font-weight: bold;border-bottom-style: dotted;width:670px;"> </span>
					</h4>
				</td>
			</tr>
		</table>
		

		<TABLE id="dataTable" width="100%" border="0"
			style="page-break-after: always; border-collapse: collapse;">

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
		<td align="left">Checked By</td>	
			<td align="centre">School Seal</td>
			<td align="centre">Principal</td>
			</tr>
			
		<!-- 	<tr>
                        <td align="center"><a id="print" href="/alirfan/DocumentsProcess/PrintTransferCertificate?id=<c:out value="${studentdetails.student.sid}" />">Print</a></td>
                    </tr>-->
		</TABLE>
		
	</form>
	
	
</body>
</html>

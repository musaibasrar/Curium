
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
    height:15px;
    font-weight: normal;
    text-align:center;
}
h3{
margin-top:0px;
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
        .rightside{
        float:right;
        }
    </style>
	<script type="text/javascript" src="/dolphin/js/datePicker/jquery-1.7.1.js"></script>
        <script type="text/javascript" src="/dolphin/js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
        <script src="/dolphin/js/print/jquery.printPage.js" type="text/javascript"></script>
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
	response.sendRedirect("/dolphin/UserProcess/sessionTimeOut");
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

		<table align="center" >
		<tr>
			
			<td colspan="4">
			
			<h3><u>TRANSFER CERTIFICATE </u><br>(DUPLICATE)</h3>
			</td>
			
			</tr>
			<tr>
			<td style="text-align:right;">
			
			Date:&nbsp;&nbsp;<input
									name="dateoftc" type="text" class="textField" style="border: none;border-color: transparent;"
									 size="7" value="<fmt:formatDate type="date" value="${tcdetails.dateofissues}" pattern="yyyy-MM-dd"/>" ></td>
			
			</tr>
			
			<tr>
			
				<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">&nbsp;&nbsp;&nbsp;&nbsp;Addmission no. &nbsp;&nbsp;
					<span style="font-weight: bold;border-bottom-style: dotted;"> <c:out value="${studentdetails.student.admissionnumber}" /></span>
					Book no. &nbsp;&nbsp;
					<span style="font-weight: bold;border-bottom-style: dotted;"><c:out value="${bookno}" /></span>
					TC no. &nbsp;&nbsp;
					<span class="rightside" style="font-weight: bold;border-bottom-style: dotted;width:250px;"><c:out value="${tcno}" /> </span>
					</h3>
				</td>
			
			
			
			</tr>
			<tr>
			
			<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">1.&nbsp;&nbsp;Name of the pupil &nbsp;&nbsp;
					<span class="rightside"style="font-weight: bold;border-bottom-style: dotted;width:750px;"> <c:out value="${studentdetails.student.name}" /></span>
					</h3>
				</td>
				
				
			
			</tr>
			<tr>
			<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">2.&nbsp;&nbsp;Mother's Name &nbsp;&nbsp;
					<span class="rightside"style="font-weight: bold;border-bottom-style: dotted;width:770px;"> <c:out value="${studentdetails.mothersname}" /></span>
					</h3>
				</td>
				
			</tr>
			<tr>
			<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">3.&nbsp;&nbsp;Father's Name/Guardian's Name &nbsp;&nbsp;
					<span class="rightside"style="font-weight: bold;border-bottom-style: dotted;width:670px;"> <c:out value="${studentdetails.fathersname}" /></span>
					</h3>
				</td>
			</tr>
			
			<tr>
			<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">4.&nbsp;&nbsp;Nationality &nbsp;&nbsp;
					<span class="rightside"style="font-weight: bold;border-bottom-style: dotted;width:790px;"> <c:out value="${studentdetails.student.nationality}" /></span>
					</h3>
				</td>
			</tr><tr><td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">5.&nbsp;&nbsp;Whether the Student Belongs to Shedule Caste or Shedule Tribes &nbsp;&nbsp;
					<span class="rightside"style="font-weight: bold;border-bottom-style: dotted;width:450px;"><c:out value="${studentdetails.student.socialcategory}" /></span>
					</h3>
				</td>
			</tr>
			<tr>
			<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">6.&nbsp;&nbsp;Date of first Admission in the School &nbsp;&nbsp;
					<span style="font-weight: bold;border-bottom-style: dotted;"> <c:out value="${studentdetails.student.admissiondate}" /></span>
					&nbsp;&nbsp;in Class &nbsp;&nbsp;
					<span style="font-weight: bold;border-bottom-style: dotted;"> <c:out value="${studentdetails.student.classadmittedin}" /></span>
					</h3>
				</td>
				</tr>
				<tr>
				<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">7.&nbsp;&nbsp;Date of Birth as Recorded in Admission Register
					
					</h3>
				</td>
				</tr>
				<tr>
			<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">&nbsp;&nbsp;&nbsp;&nbsp;In Figures &nbsp;&nbsp;
					<span style="font-weight: bold;border-bottom-style: dotted;"> <c:out value="${studentdetails.student.dateofbirth}" /></span>
					&nbsp;&nbsp;&nbsp;&nbsp;In Words &nbsp;&nbsp;
					<span class="rightside" style="font-weight: bold;border-bottom-style: dotted;width:500px;"> <c:out value="${dateinword}" /></span>
					</h3>
				</td>
			</tr>
			<tr>
				<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">8.&nbsp;&nbsp;Class in which Pupil Studied Last:
					
					</h3>
				</td>
				</tr>
				<tr>
			<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">&nbsp;&nbsp;&nbsp;&nbsp;In Figures &nbsp;&nbsp;
					<span style="font-weight: bold;border-bottom-style: dotted;"> <c:out value="${studentdetails.student.classstudying}" /></span>
					&nbsp;&nbsp;&nbsp;&nbsp;In Words &nbsp;&nbsp;
					<span style="font-weight: bold;border-bottom-style: dotted;"><c:out value="${classinword}" /> </span>
					</h3>
				</td>
			</tr>
			<tr>
				<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">9.&nbsp;&nbsp;School/Board Annual Examination Last taken with Result:
					<span class="rightside"style="font-weight: bold;border-bottom-style: dotted;width:500px;"><c:out value="${lastexam}" /> </span>
					</h3>
				</td>
				</tr>
				
				<tr>
			<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">10.&nbsp;&nbsp;Whether failed if so Once/Twice in the same Class &nbsp;&nbsp;
					<span class="rightside"style="font-weight: bold;border-bottom-style: dotted;width:550px;"><c:out value="${failpass}" /> </span>
					</h3>
				</td>
			</tr>
			
			<tr>
			<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">11.&nbsp;&nbsp;Subject studied: &nbsp;&nbsp;
					</h3>
				</td>
				</tr>
			
			<tr>
				<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">1. &nbsp;&nbsp;
					<span style="font-weight: bold;border-bottom-style: dotted;"><c:out value="${firstsubject}" /></span>
					2. &nbsp;&nbsp;
					<span style="font-weight: bold;border-bottom-style: dotted;"> <c:out value="${secondsubject}" /></span>
					</h3>
				</td>
				</tr>
				<tr>
				<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">3. &nbsp;&nbsp;
					<span style="font-weight: bold;border-bottom-style: dotted;"><c:out value="${thirdsubject}" /> </span>
					4. &nbsp;&nbsp;
					<span style="font-weight: bold;border-bottom-style: dotted;"><c:out value="${Fourthsubject}" /></span>
					</h3>
				</td>
				</tr>
				<tr>
				<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">5. &nbsp;&nbsp;
					<span style="font-weight: bold;border-bottom-style: dotted;"><c:out value="${Fifthsubject}" /> </span>
					6. &nbsp;&nbsp;
					<span style="font-weight: bold;border-bottom-style: dotted;"><c:out value="${sixthsubject}" /> </span>
					</h3>
				</td>
			</tr>
			<tr>
				<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">12.&nbsp;&nbsp;Whether qualified for Promotion to the higher Class
					
					</h3>
				</td>
				</tr>
				<tr>
			<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">&nbsp;&nbsp;&nbsp;&nbsp;In Figures &nbsp;&nbsp;
					<span style="font-weight: bold;border-bottom-style: dotted;"><c:out value="${pinfig}" /> </span>
					&nbsp;&nbsp;&nbsp;&nbsp;In Words &nbsp;&nbsp;
					<span style="font-weight: bold;border-bottom-style: dotted;"><c:out value="${pinword}" /> </span>
					</h3>
				</td>
			</tr>
			<tr>
			<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">13.&nbsp;&nbsp;Month upto which the School Dues paid &nbsp;&nbsp;
					<span class="rightside"style="font-weight: bold;border-bottom-style: dotted;width:600px;"><c:out value="${dues}" /> </span>
					</h3>
				</td>
			</tr>
			<tr>
			<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">14.&nbsp;&nbsp;Any concession available of,if so,the nature of such concession &nbsp;&nbsp;
					<span class="rightside"style="font-weight: bold;border-bottom-style: dotted;width:450px;"> <c:out value="${concession}" /> </span>
					</h3>
				</td>
			</tr>
			<tr>
			<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">15.&nbsp;&nbsp;Total No. of working days &nbsp;&nbsp;
					<span class="rightside"style="font-weight: bold;border-bottom-style: dotted;width:700px;"> <c:out value="${workingdays}" /></span>
					</h3>
				</td>
			</tr>
			<tr>
			<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">16.&nbsp;&nbsp;Total No. of working days present &nbsp;&nbsp;
					<span class="rightside"style="font-weight: bold;border-bottom-style: dotted;width:630px;"><c:out value="${present}" /> </span>
					</h3>
				</td>
			</tr>
			<tr>
			<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">17.&nbsp;&nbsp;Whether NCC cadet/Boy Scout/Girl Scout (detail may be given) &nbsp;&nbsp;
					<span class="rightside"style="font-weight: bold;border-bottom-style: dotted;width:450px;"> <c:out value="${ncc}" /></span>
					</h3>
				</td>
			</tr>
			
			<tr>
			<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">18.&nbsp;&nbsp;Games played or extra curricular activities in which the pupil usually took part&nbsp;&nbsp; </td></tr>
					<tr>
			<tr>
			<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">&nbsp;&nbsp;&nbsp;&nbsp;(mention achievement level threrein) &nbsp;&nbsp;
					<span class="rightside"style="font-weight: bold;border-bottom-style: dotted;width:650px;"><c:out value="${game}" /> </span>
					</h3>
				</td>
			</tr>
			<tr>
			<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">19.&nbsp;&nbsp;Genaral conduct &nbsp;&nbsp;
					 <span class="rightside"style="font-weight: bold;border-bottom-style: dotted;width:740px;"><c:out value="${conduct}" /> </span>
					</h3>
				</td>
			</tr>
			<tr>
			<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">20.&nbsp;&nbsp;Date of application for Certificate &nbsp;&nbsp;
					<span class="rightside"style="font-weight: bold;border-bottom-style: dotted;width:630px;"><c:out value="${datecert}" /> </span>
					</h3>
				</td>
			</tr>
			<tr>
			<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">21.&nbsp;&nbsp;Date of issue for Certificate &nbsp;&nbsp;
					<span class="rightside"style="font-weight: bold;border-bottom-style: dotted;width:700px;padding:1px;"><input
									name="dateoftc" type="text" class="textField" style="border: none;border-color: transparent;"
									 size="10" value="<fmt:formatDate type="date" value="${tcdetails.dateofissues}" pattern="yyyy-MM-dd"/>" > </span>
					</h3>
				</td>
			</tr>
			<tr>
			<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">22.&nbsp;&nbsp;Reason for leaving the school &nbsp;&nbsp;
					<span class="rightside"style="font-weight: bold;border-bottom-style: dotted;width:660px;"><c:out value="${leavingReason}" /> </span>
					</h3>
				</td>
			</tr>
			<tr>
			<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">23.&nbsp;&nbsp;Any other Remarks &nbsp;&nbsp;
					<span class="rightside"style="font-weight: bold;border-bottom-style: dotted;width:720px;"> <c:out value="${Remarks}" /></span>
					</h3>
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
			
			<tr>
                        <td align="center">
                      <a>  <button id="print" type="button" style="background-image: url(/dolphin/images/print.jpg);width: 63px;height: 60px" onclick="window.print();
                                     loading.style.visibility = 'visible'" class="hide"></button> </a>
                      <!--   <a id="print" href="/dolphin/DocumentsProcess/PrintTransferCertificate?id=<c:out value="${studentdetails.student.sid}" />">Print</a>--></td>
                   
                    </tr>
                    
		</TABLE>
		
	</form>
	
	
</body>
</html>

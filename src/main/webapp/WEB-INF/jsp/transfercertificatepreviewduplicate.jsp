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
		<td>
		<br>
		<br>
		<br>
		</td>
		</tr>

</table>

<TABLE  width="100%" border="1" style="border-collapse:collapse;">
<!--                 <tr>

                    <td colspan="4" ></td>

                </tr>
 -->            </TABLE>

		<table style="margin:40px;">
		<tr>
			
			<td colspan="4">
			<h4>AFFILIATED TO CENTRAL BOARD OF SECONDARY EDUCATION<br>
			AFFILIATION NO. 1130056        SCHOOL NO.30032</h4>
			<h1>SCHOOL LEAVING CERTIFICATE/TRANSFER CERTIFICATE </h1>
			</td>
			
			</tr>
			<tr>
			<td align="right">
			Date:&nbsp;&nbsp;<input
									name="dateoftc" type="text" class="textField" style="border: none;border-color: transparent;"
									 size="10" value="<fmt:formatDate type="date" value="${now}" pattern="dd/MM/yyyy"/>" ></td>
			
			</tr>
			
			<tr>
			
				<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">&nbsp;&nbsp;&nbsp;&nbsp;Addmission no. &nbsp;&nbsp;
					<span style="font-weight: bold;border-bottom-style: dotted;"> <c:out value="${studentdetails.student.admissionnumber}" /></span>
					<!-- Sl.No. --> &nbsp;&nbsp;
					<span style="font-weight: bold;border:0px;"><%-- <c:out value="${slno}" /> --%></span>
					TC no. &nbsp;&nbsp;
					<span class="rightside" style="font-weight: bold;border-bottom-style: dotted;width:250px;"><c:out value="${tcno}" /> </span>
					</h3>
				</td>
			
			
			
			</tr>
			<tr>
			
			<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">1.&nbsp;&nbsp;Name of the pupil &nbsp;&nbsp;
					<span class="rightside"style="font-weight: bold;border-bottom-style: dotted;width:700px;"> <c:out value="${studentName}" /></span>
					</h3>
				</td>
				
				
			
			</tr>
			
			<tr>
			<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">2.&nbsp;&nbsp;Father's Name/Guardian's Name &nbsp;&nbsp;
					<span style="font-weight: bold;border-bottom-style: dotted;width:260px;"> <c:out value="${fathername}" /></span>
					&nbsp;&nbsp;Mother's Name &nbsp;&nbsp;
					<span class="rightside"style="font-weight: bold;border-bottom-style: dotted;width:260px;"> <c:out value="${mothername}" /></span>
					</h3>
				</td>
			</tr>
			
			<tr>
			<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">3.&nbsp;&nbsp;Nationality &nbsp;&nbsp;
					<span style="font-weight: bold;border-bottom-style: dotted;"> <c:out value="${nationality}" /></span>
					&nbsp;&nbsp;Mother Tongue &nbsp;&nbsp;
					<span style="font-weight: bold;border-bottom-style: dotted;"> <c:out value="${mothertongue}" /></span>
					</h3>
				</td>
			</tr>
			<tr>
				<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">4.&nbsp;&nbsp;Date of Birth according to Admission  & Withdrawal Register
					
					</h3>
				</td>
				</tr>
				<tr>
			<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">&nbsp;&nbsp;&nbsp;&nbsp;In Figures &nbsp;&nbsp;
					<span style="font-weight: bold;border-bottom-style: dotted;"> <c:out value="${dateofbirth}" /></span>
					&nbsp;&nbsp;&nbsp;&nbsp;In Words &nbsp;&nbsp;
					<span class="rightside" style="font-weight: bold;border-bottom-style: dotted;width:500px;"> <c:out value="${dateofbirthwords}" /></span>
					</h3>
				</td>
			</tr>
			<tr>
			<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">5.Place of birth:(a)City/Village: &nbsp;&nbsp;
					<span style="font-weight: bold;border-bottom-style: dotted;"> <c:out value="${firstsubject}" /></span>
					&nbsp;&nbsp;(b)Taluka &nbsp;&nbsp;
					<span style="font-weight: bold;border-bottom-style: dotted;"> <c:out value="${secondsubject}" /></span>
					</h3>
				</td>
			</tr>
			<tr>
			<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">(c)District &nbsp;&nbsp;
					<span style="font-weight: bold;border-bottom-style: dotted;"> <c:out value="${thirdsubject}" /></span>
					&nbsp;&nbsp;(d)State &nbsp;&nbsp;
					<span style="font-weight: bold;border-bottom-style: dotted;"> <c:out value="${Fourthsubject}" /></span>
					&nbsp;&nbsp;(e)Country &nbsp;&nbsp;
					<span style="font-weight: bold;border-bottom-style: dotted;"> <c:out value="${Fifthsubject}" /></span>
					</h3>
				</td>
			</tr>
			<tr>
			<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">6.&nbsp;&nbsp;Religion &nbsp;&nbsp;
					<span style="font-weight: bold;border-bottom-style: dotted;"> <c:out value="${religion}" /></span>
					&nbsp;&nbsp;Caste &nbsp;&nbsp;
					<span style="font-weight: bold;border-bottom-style: dotted;"> <c:out value="${caste}" /></span>
					&nbsp;&nbsp;SubCaste &nbsp;&nbsp;
					<span style="font-weight: bold;border-bottom-style: dotted;"> <c:out value="${sixthsubject}" /></span>
					</h3>
				</td>
			</tr>
			<tr><td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">7.&nbsp;&nbsp;Last school attended &nbsp;&nbsp;
					<span style="font-weight: bold;border-bottom-style: dotted;width:450px;"><c:out value="${lastschoolattended}" /></span>
					Class &nbsp;&nbsp;
					<span style="font-weight: bold;border-bottom-style: dotted;"> <c:out value="${lastclassattended}" /></span>
					</h3>
					</h3>
				</td>
			</tr>
			</tr>
			<tr><td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">8.&nbsp;&nbsp;Date of admission in the school &nbsp;&nbsp;
					<span style="font-weight: bold;border-bottom-style: dotted;width:200px;"><c:out value="${dateofadmission}" /></span>
					Class of admission &nbsp;&nbsp;
					<span style="font-weight: bold;border-bottom-style: dotted;"> <c:out value="${classadmitted}" /></span>
					</h3>
					</h3>
				</td>
			</tr>
			<tr>
			<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">9.&nbsp;&nbsp;Any fee concession availed of,if so,the nature of such concession &nbsp;&nbsp;
					<span class="rightside"style="font-weight: bold;border-bottom-style: dotted;width:450px;"><c:out value="${concession}" /></span>
					</h3>
				</td>
			</tr>
			<tr>
			<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">10.&nbsp;&nbsp;Total No. of days present &nbsp;&nbsp;
					<span style="font-weight: bold;border-bottom-style: dotted;width:120px;"><c:out value="${present}" /> </span>
					&nbsp;&nbsp;out of &nbsp;&nbsp;
					<span style="font-weight: bold;border-bottom-style: dotted;width:120px;"> <c:out value="${workingdays}" /></span>
					&nbsp;&nbsp;Working days of academic year &nbsp;&nbsp;
					<span class="rightside"style="font-weight: bold;border-bottom-style: dotted;width:150px;"> <c:out value="${acadmicyear}" /></span>
					</h3>
				</td>
			</tr>
			<tr><td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">11.&nbsp;&nbsp;Academic Progress &nbsp;&nbsp;
					<span style="font-weight: bold;border-bottom-style: dotted;width:300px;"> <c:out value="${academicprogress}" /></span>
					Conduct &nbsp;&nbsp;
					<span style="font-weight: bold;border-bottom-style: dotted;"> <c:out value="${conduct}" /></span>
					</h3>
					</h3>
				</td>
			</tr>
			<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">12.&nbsp;&nbsp;Standard in which studying and since when &nbsp;&nbsp;
					<span style="font-weight: bold;border-bottom-style: dotted;width:150px;"><c:out value="${classandsec}" /> </span>
					From &nbsp;&nbsp;
					<span style="font-weight: bold;border-bottom-style: dotted;width:150px;"> <c:out value="${from}" /></span>
					To &nbsp;&nbsp;
					<span class="rightside"style="font-weight: bold;border-bottom-style: dotted;width:150px;"> <c:out value="${To}" /></span>
					</h3>
				</td>
			</tr>
			<tr>
			<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">13.&nbsp;&nbsp;Date of Leaving School &nbsp;&nbsp;
					<span class="rightside"style="font-weight: bold;border-bottom-style: dotted;width:630px;"><c:out value="${dateleaving}" /> </span>
					</h3>
				</td>
			</tr>
			<tr>
			<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">14.&nbsp;&nbsp;Reason for leaving the school &nbsp;&nbsp;
					<span class="rightside"style="font-weight: bold;border-bottom-style: dotted;width:590px;"><c:out value="${reason}" /> </span>
					</h3>
				</td>
			</tr>
			<tr>
			<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">15.&nbsp;&nbsp;Result of annual exam &nbsp;&nbsp;
					<span class="rightside"style="font-weight: bold;border-bottom-style: dotted;width:600px;"><c:out value="${result}" /></span>
					</h3>
				</td>
			</tr>
			<tr>
			<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">16.&nbsp;&nbsp;Any other Remarks &nbsp;&nbsp;
					<span class="rightside"style="font-weight: bold;border-bottom-style: dotted;width:647px;"> <c:out value="${Remarks}" /></span>
					</h3>
				</td>
			</tr>
			<tr>
			<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">17.&nbsp;&nbsp;Date on which pupil name was struck of from the rolls &nbsp;&nbsp;
					<span style="font-weight: bold;border-bottom-style: dotted;width:493px;"> <c:out value="${namestuckoff}" /></span>
					</h3>
				</td>
			</tr>
			<tr><td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">18.&nbsp;&nbsp;Whether the pupil has paid all the dues &nbsp;&nbsp;
					<span style="font-weight: bold;border-bottom-style: dotted;width:150px;"><c:out value="${allduespaid}" /></span>
					Date of issue of certificate &nbsp;&nbsp;
					<span style="font-weight: bold;border-bottom-style: dotted;"><c:out value="${dateissue}" /></span>
					</h3>
					</h3>
				</td>
			</tr>
			<tr>
			<td>
			<hr>
			</td>
			</tr>
			<tr>
			<td style="text-align: center;">
			Certified that the above information is in accordance with the school General register
			</td>
			</tr>
			
			
			
		
			<tr>
			<td>
			<br>
			<br><br><br><br></td>
			</tr>
			
		<tr>
		<td align="left">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		Class Teacher&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		Clerk&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;Principal</td>
			</tr>
			<tr>
			<td style="text-align: center;">
			No change in any entry is to be made except by the authority issueing the certificate
			</td>
			</tr>
			
			<tr>
                        <td align="center">
                      <a>  <button id="print" type="button" style="background-image: url(/alirfan/images/print.jpg);width: 63px;height: 60px" onclick="window.print();
                                     loading.style.visibility = 'visible'" class="hide"></button> </a>
                      <!--   <a id="print" href="/alirfan/DocumentsProcess/PrintTransferCertificate?id=<c:out value="${studentdetails.student.sid}" />">Print</a>--></td>
                   
                    </tr>
                    
		</TABLE>
		
	</form>
	
	
</body>
</html>
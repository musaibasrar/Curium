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

.adhar{
     border:1px solid black;
     border-collapse:collapse;
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
	<script type="text/javascript" src="/shatabdi/js/datePicker/jquery-1.7.1.js"></script>
        <script type="text/javascript" src="/shatabdi/js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
        <script src="/shatabdi/js/print/jquery.printPage.js" type="text/javascript"></script>
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
	response.sendRedirect("/shatabdi/UserProcess/sessionTimeOut");
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

<TABLE align="center" >

<tr>
<td><img src="/noblewisdom/images/noblewisdom.jpg" width="105" height="90"/></td>
<td> <h1>
			
			<label style="">${branchname}</label> </h1></td>
</tr>
<tr>
<td></td>
			
			<td>
			
			<label style="">${branchaddress}</label> 
			</td>
			
			</tr>
		<tr>
			<td></td>
			<td>
			
			<h1 ><label style="background-color:red;border-radius:20px;">SCHOOL LEAVING CERTIFICATE</label> </h1>
			</td>
			
			</tr>
            </TABLE>

		<table style="margin:40px;">
		<tr>
		<td></td>
			
			<td colspan="4">
			</td>
			
			</tr>
		<tr>
		
			<tr>
			<td align="right">
			<%-- Date:&nbsp;&nbsp;<input
									name="dateoftc" type="text" class="textField" style="border: none;border-color: transparent;"
									 size="10" value="<fmt:formatDate type="date" value="${tcdetails.dateofissues}" pattern="yyyy-MM-dd"/>" ></td>
			 --%>
			</tr>
			
			<%-- <tr>
			
				<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">&nbsp;&nbsp;&nbsp;&nbsp;Udise no. &nbsp;&nbsp;
					<span width="auto" style="font-weight: bold;border: 0px solid black;">
					<table class="adhar">
					<tr>
					<td class="adhar" style="height:15px;width:12px;">2</td>
					<td class="adhar" style="height:15px;width:12px;">7</td>
					<td class="adhar" style="height:15px;width:12px;">3</td>
					<td class="adhar" style="height:15px;width:12px;">0</td>
					<td class="adhar" style="height:15px;width:12px;">1</td>
					<td class="adhar" style="height:15px;width:12px;">2</td>
					<td class="adhar" style="height:15px;width:12px;">0</td>
					<td class="adhar" style="height:15px;width:12px;">3</td>
					<td class="adhar" style="height:15px;width:12px;">1</td>
					<td class="adhar" style="height:15px;width:12px;">2</td>
					<td class="adhar" style="height:15px;width:12px;">0</td></tr></table></span>
					 &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;
					<span style="font-weight: bold;border-bottom-style: none;"></span>
					Certificate no. &nbsp;&nbsp;
					<span class="rightside" style="font-weight: bold;border-bottom-style: dotted;width:250px;"><c:out value="${tcno}" /> </span>
					</h3>
				</td>
			
			
			
			</tr>
 --%>			 <tr>
			
				<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">&nbsp;&nbsp;&nbsp;&nbsp;Admission ID No. &nbsp;&nbsp;
					<span style="font-weight: bold;border:none;"><table class="adhar">
					<tr>
					<c:forEach var="name" items="${externalid}" varStatus="loop">
					<td class="adhar" style="height:15px;width:12px;">${name}</td>
					</c:forEach>
					</tr></table></span>
					</h3>
				</td>
			
			
			</tr> 
			
			 <tr>
			
				<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">&nbsp;&nbsp;&nbsp;&nbsp;Aadhar No. &nbsp;&nbsp;
					<span style="font-weight: bold;border:none;"><table class="adhar">
					<tr>
					<c:forEach var="name" items="${aadhar}" varStatus="loop">
					<td class="adhar" style="height:15px;width:12px;">${name}</td>
					</c:forEach>
					</tr></table></span>
					</h3>
				</td>
			
			
			</tr> 
			<tr><td><h3> (PRESCRIBED BY RULE II, CHAPTER I,OF THE GRANT - IN - AID CODE)</h3></td></tr>
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
					<h3 style="font-weight: normal;">4.&nbsp;&nbsp;Race and Caste &nbsp;&nbsp;
					<span style="font-weight: bold;border-bottom-style: dotted;"> <c:out value="${studentdetails.student.religion}" />,&nbsp;&nbsp;<c:out value="${studentdetails.student.caste}" /></span>
					&nbsp;&nbsp;Nationality &nbsp;&nbsp;
					<span style="font-weight: bold;border-bottom-style: dotted;"> <c:out value="${studentdetails.student.nationality}" /></span>
					</h3>
				</td>
			</tr><tr><td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">5.&nbsp;&nbsp;Place of Birth &nbsp;&nbsp;
					<span class="rightside"style="font-weight: bold;border-bottom-style: dotted;width:770px;"><c:out value="${studentdetails.student.socialcategory}" /></span>
					</h3>
				</td>
			</tr>
						<tr>
				<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">6.&nbsp;&nbsp;Date of Birth month and year according to christian era
					
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
					<h3 style="font-weight: normal;">7.&nbsp;&nbsp;Last School attended &nbsp;&nbsp;
					<span class="rightside"style="font-weight: bold;border-bottom-style: dotted;width:700px;"><c:out value="${dues}" /> </span>
					</h3>
				</td>
			</tr>
			<tr>
			<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">8.&nbsp;&nbsp;Date of admission &nbsp;&nbsp;
					<span class="rightside"style="font-weight: bold;border-bottom-style: dotted;width:710px;"> <c:out value="${studentdetails.student.admissiondate}" /> </span>
					</h3>
				</td>
			</tr>
			<tr>
			<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">9.&nbsp;&nbsp;Progress &nbsp;&nbsp;
					<span class="rightside"style="font-weight: bold;border-bottom-style: dotted;width:800px;"> <c:out value="${classinword}" /></span>
					</h3>
				</td>
			</tr>
			<tr>
			<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">10.&nbsp;&nbsp;conduct &nbsp;&nbsp;
					<span class="rightside"style="font-weight: bold;border-bottom-style: dotted;width:800px;"><c:out value="${conduct}" /> </span>
					</h3>
				</td>
			</tr>
			<tr>
			<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">11.&nbsp;&nbsp;Date of leaving School &nbsp;&nbsp;
					<span class="rightside"style="font-weight: bold;border-bottom-style: dotted;width:700px;"> <c:out value="${studentdetails.student.dateleaving}" /></span>
					</h3>
				</td>
			</tr>
			
			<tr>
			<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">12.&nbsp;&nbsp;Standard in which studying & since when&nbsp;&nbsp;
					<span class="rightside"style="font-weight: bold;border-bottom-style: dotted;width:600px;"><c:out value="${studentdetails.student.classstudying}" />,&nbsp;&nbsp;
					<c:out value="${studentdetails.student.yearofadmission}" /> </span> </td></tr>
					</h3></td><tr>
			
			<tr>
			<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">13.&nbsp;&nbsp;Reason for leaving the school &nbsp;&nbsp;
					<span style="font-weight: bold;border-bottom-style: dotted;width:440px;"><c:out value="${leavingReason}" /> </span>
					</h3>
				</td>
			</tr>
			<tr>
			<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">14.&nbsp;&nbsp;Any other Remarks &nbsp;&nbsp;
					<span style="font-weight: bold;border-bottom-style: dotted;width:493px;"> <c:out value="${Remarks}" /></span>
					</h3>
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
	    Date:&nbsp;&nbsp;<input name="dateoftc" type="text" class="textField" style="border: none;border-color: transparent;"
		size="10" value="<fmt:formatDate type="date" value="${tcdetails.dateofissues}" pattern="dd-MM-yyyy"/>" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&emsp;&emsp;&emsp;&emsp;&emsp;Headmaster</td>
			</tr>
			<tr>
			<td><br></td>
			</tr>
			<tr>
			<td><br></td>
			</tr>
			<tr><td style="color:red">
			*No change in the entry is to be made except by the authority issuing the
			</td></tr>
			<tr>
			<td style="color:red">
			Leaving Certificate, infringement of the rule I will be punished with the rustication (by order of the ET)
			</td>
			</tr>
			
			<tr>
                        <td align="center">
                      <a>  <button id="print" type="button"  onclick="window.print();
                                     loading.style.visibility = 'visible'" class="hide">Print</button> </a>
                      <!--   <a id="print" href="/shatabdi/DocumentsProcess/PrintTransferCertificate?id=<c:out value="${studentdetails.student.sid}" />">Print</a>--></td>
                   
                    </tr>
                    
		</TABLE>
		
	</form>
	
	
</body>
</html>
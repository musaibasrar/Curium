
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
	<script type="text/javascript" src="/fathima/js/datePicker/jquery-1.7.1.js"></script>
        <script type="text/javascript" src="/fathima/js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
        <script src="/fathima/js/print/jquery.printPage.js" type="text/javascript"></script>
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
	response.sendRedirect("/fathima/UserProcess/sessionTimeOut");
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
	<div style="border:2px solid black;margin-left:10px;margin-right:10px;margin-top:100px;">
		<br>
		<table width="100%" style="border-collapse: collapse;">
			<tr><td rowspan="3" style="padding-left:200px;"><img src="/fathima/images/fathima.jpg" width="105" height="105"/></td>
				<td  style="width: 100%;text-align:left;">
				
			<h2 style="margin-bottom:0px;">	${branchname} </h2></td>
			</tr>
			<tr>
			<td class="addressLine"  style="text-align:left;">&emsp;&emsp;&emsp;&emsp;${branchaddress}</td>
			</tr>
			<tr>
			<td class="addressLine" style="text-align:left;">&emsp;&emsp;${branchcontact}</td>
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

		<table style="margin:40px;">
		<tr>
			
			<td colspan="4">
			
			<h3><u>TRANSFER CERTIFICATE </u></h3>
			</td>
			
			</tr>
			<tr>
			<td style="text-align:right;">
			Date:&nbsp;&nbsp;<input
									name="dateoftc" type="text" class="textField" style="border: none;border-color: transparent;"
									 size="10" value="<fmt:formatDate type="date" value="${tcdetails.dateofissues}" pattern="dd-MM-yyyy"/>" ></td>
			
			</tr>
			
			<tr>
			
				<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">&nbsp;&nbsp;&nbsp;&nbsp;Sl No. &nbsp;&nbsp;
					<span style="font-weight: bold;border-bottom-style: dotted;"> <c:out value="${tcno}" /></span>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<span style="font-weight: bold;border:0px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&emsp;&emsp;&emsp;Ad No.
					<span class="rightside" style="font-weight: bold;border-bottom-style: dotted;width:200px;"> &nbsp;&nbsp;<c:out value="${studentdetails.student.admissionnumber}" /> </span>
					</h3>
				</td>
			
			
			
			</tr>
			<tr><td><br></td></tr>
			
			<tr>
			
			<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">1.&nbsp;&nbsp;Name of the pupil &nbsp;&nbsp;
					<span class="rightside"style="font-weight: bold;border-bottom-style: dotted;width:600px;"> <c:out value="${studentdetails.student.name}" /></span>
					</h3>
				</td>
				
				
			
			</tr>
			<tr><td><br></td></tr>
			<tr>
			<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">2.&nbsp;&nbsp;EMIS Number &nbsp;&nbsp;
					<span class="rightside"style="font-weight: bold;border-bottom-style: dotted;width:600px;"> <c:out value="${studentdetails.student.sts}" /></span>
					</h3>
				</td>
				
			</tr>
			<tr><td><br></td></tr>
			<tr>
			<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">3.&nbsp;&nbsp;Father's Name/Guardian's Name &nbsp;&nbsp;
					<span class="rightside"style="font-weight: bold;border-bottom-style: dotted;width:600px;"> <c:out value="${studentdetails.fathersname}" /></span>
					</h3>
				</td>
			</tr>
			<tr><td><br></td></tr>
			<tr>
			<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">4.&nbsp;&nbsp;Sex &nbsp;&nbsp;
					<span class="rightside"style="font-weight: bold;border-bottom-style: dotted;width:600px;"> <c:out value="${studentdetails.student.gender}" /></span>
					</h3>
				</td>
			</tr>
			<tr><td><br></td></tr>
			<tr><td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">5.&nbsp;&nbsp;Religion, Caste &nbsp;&nbsp;
					<span class="rightside"style="font-weight: bold;border-bottom-style: dotted;width:600px;"><c:out value="${studentdetails.student.religion}" />,<c:out value="${studentdetails.student.caste}" /></span>
					</h3>
				</td>
			</tr>
			<tr><td><br></td></tr>
			<tr>
			<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">6.&nbsp;&nbsp;Date of Birth &nbsp;&nbsp;
					<span class="rightside"style="font-weight: bold;border-bottom-style: dotted;width:600px;"> <c:out value="${studentdetails.student.dateofbirth}" /></span>
					</h3>
				</td>
			</tr>
			<tr><td><br></td></tr>
				<tr>
			<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">7.&nbsp;&nbsp;Residence Address &nbsp;&nbsp;
					<span class="rightside"style="font-weight: bold;border-bottom-style: dotted;width:600px;"> <c:out value="${studentdetails.addresspermanent}" /></span>
					</h3>
				</td>
			</tr>
			<tr><td><br></td></tr>
			<tr>
			<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">8.&nbsp;&nbsp;Occupation of Parent &nbsp;&nbsp;
					<span class="rightside"style="font-weight: bold;border-bottom-style: dotted;width:600px;"> <c:out value="${studentdetails.profession}" /></span>
					</h3>
				</td>
			</tr>
			<tr><td><br></td></tr>
			<tr>
			<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">9.&nbsp;&nbsp;Weather Promotion to higher class &nbsp;&nbsp;
					<span class="rightside"style="font-weight: bold;border-bottom-style: dotted;width:600px;"> <c:out value="${Remarks}" /></span>
					</h3>
				</td>
			</tr>
			<tr><td><br></td></tr>
			<tr>
			<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">10.&nbsp;&nbsp;Class in which the pupil was studying &nbsp;&nbsp;
					<span class="rightside"style="font-weight: bold;border-bottom-style: dotted;width:600px;"> <c:out value="${studentdetails.student.classstudying}" /></span>
					</h3>
				</td>
			</tr>
			<tr><td><br></td></tr>
			<tr>
			<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">11.&nbsp;&nbsp;Date on which pupil left the school &nbsp;&nbsp;
					<span class="rightside"style="font-weight: bold;border-bottom-style: dotted;width:600px;"> <c:out value="${studentdetails.student.dateleaving}" /></span>
					</h3>
				</td>
			</tr>
			<tr><td><br></td></tr>
			<tr>
			<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">12.&nbsp;&nbsp;The Pupil conduct and character &nbsp;&nbsp;
					<span class="rightside"style="font-weight: bold;border-bottom-style: dotted;width:600px;"> <c:out value="${conduct}" /></span>
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
		<td align="left"><i>School Seal</i></td>	
			<td align="centre">&emsp;&emsp;&emsp;&emsp;</td>
			<td align="centre"><i>Signature of head</i></td>
			</tr>
			
			<tr>
                        <td align="center">
                      <a>  <button id="print" type="button" style="background-image: url(/fathima/images/print.jpg);width: 63px;height: 60px" onclick="window.print();
                                     loading.style.visibility = 'visible'" class="hide"></button> </a>
                      <!--   <a id="print" href="/fathima/DocumentsProcess/PrintTransferCertificate?id=<c:out value="${studentdetails.student.sid}" />">Print</a>--></td>
                   
                    </tr>
                    
		</TABLE>
		</div>
		
	</form>
	
	
</body>
</html>

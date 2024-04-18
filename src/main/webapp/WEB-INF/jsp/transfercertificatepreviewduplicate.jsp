<%-- 
    Document   : Transfer Certificate Preview
    Created on : Mar 21 2018, 09:58 AM
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
	font-size: 28px;
	letter-spacing: normal;
	text-align: center;
}
.addressLine{
	font-weight: normal;
	font-family: ariel;
	color: black;
	font-size: 14px;
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

tr,td,th{
	padding-top: 0px;
	padding-bottom: 0px;
}

-->
span{
    display:inline-block;
    border-bottom:2px solid black;
    padding-bottom:1px;
    width: 200px;
    font-weight: normal;
    text-align:center;
}
h3{
margin-bottom:0px;
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
            .fontsize { font-size: 12px;
                        font-weight: bold;
                        font-family: 'Times New Roman'
            }
            .bodymargin{
                margin-left: 0px ;
                margin-right: 0px;
            }
        }
        .input{
        width:10px;
        text-align:center;
        }
    </style>
	<script type="text/javascript" src="/bsr/js/datePicker/jquery-1.7.1.js"></script>
        <script type="text/javascript" src="/bsr/js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
        <script src="/bsr/js/print/jquery.printPage.js" type="text/javascript"></script>
        <title>Print Transfer Certificate</title>
       

</head>
  <%
//allow access only if session exists
String user = null;
if(session.getAttribute("userAuth") == null){
	response.sendRedirect("/bsr/UserProcess/sessionTimeOut");
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
		<table align="center">
			<tr><td>
			
			<img  src="/bsr/images/bsr.png" alt="Brainy Stars" width="120" height="120"></td>
				<td >
				<label class="dataTextBoldCenter" style="text-transform: uppercase;">${branchname}</label><br>
				<label class="addressLine"><u>A UNIT OF TAMIRE MILLAT EDUCATIONAL SOCIETY RATLAM</u></label><br>
				<label class="addressLine"> ${branchaddress}</label><br>
				<label class="addressLine">Contact: ${branchcontact}</label><br>
				<label class="addressLine">UDISE CODE- 23200535701</label>
				</td></tr>
</table>

<TABLE  width="100%" border="1" style="border-collapse:collapse;">
                <tr>

                    <td colspan="4" ></td>

                </tr>
            </TABLE>
            
            <table align="center">
            
			<tr>

				<td align="center">

			<h3><u>TRANSFER CERTIFICATE</u><br>DUPLICATE</h3>
			</td>

			</tr>
			
			
			</table>

		<table align="center">
		<tr><td>Scholar Number:&nbsp;&nbsp;<span style="font-weight: bold; width: 50px;"><c:out value="${Scholarno}" /></span></td><td></td><td></td>
			<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			Date:&nbsp;&nbsp;<input
									name="dateoftc" type="text" class="textField" style="border: none;border-color: transparent;"
									 size="10"value="<fmt:formatDate type="date" value="${now}" pattern="dd/MM/yyyy"/>" ></td>

			</tr>
			<tr>

				<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">This is to certify that Ms. &nbsp;&nbsp;
					</h3>
				</td>
				<td class="dataTextBoldLeft">
					<span style="font-weight: bold;"> <c:out value="${studentdetails.student.name}" /></span>
				</td>
				<td class="dataTextBoldLeft" >
					<h3 style="font-weight: normal;" >
					Mother's name </h3>
				</td>
				
				<td class="dataTextBoldLeft" >
					<span style="font-weight: bold;"><c:out value="${studentdetails.mothersname}" /></span>
				</td>


			</tr>
			<tr>
				<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">Father's/Guardian's Name &nbsp;&nbsp;
					</h3>
				</td>
				<td class="dataTextBoldLeft">
					<span style="font-weight: bold;"> <c:out value="${studentdetails.fathersname}" /></span>
				</td>
				<td class="dataTextBoldLeft" >
					<h3 style="font-weight: normal;" >

					Religion</h3>
				</td>
				<td class="dataTextBoldLeft" >
					<span style="font-weight: bold;"><c:out value="${studentdetails.student.religion}" /></span>
				</td>

			</tr>
			<tr>
				<td class="dataTextBoldLeft" >
					<h3 style="font-weight: normal;" >

					Nationality &nbsp;&nbsp;</h3>
				</td>
				<td class="dataTextBoldLeft" >
					<span style="font-weight: bold;"><c:out value="${studentdetails.student.nationality}" /></span>
				</td>
				<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">Block
					</h3>
				</td>
				<td class="dataTextBoldLeft">
					<span style="font-weight: bold;"> <c:out value="${dist}" /></span>
				</td>
				</tr>
				<tr>
				<td class="dataTextBoldLeft" >
					<h3 style="font-weight: normal;" >

					District &nbsp;&nbsp;</h3>
				</td>
				<td class="dataTextBoldLeft" >
					<span style="font-weight: bold;"><c:out value="${dist}" /></span>
				</td>
				<td class="dataTextBoldLeft" >
					<h3 style="font-weight: normal;" >

					City</h3>
				</td>
				<td class="dataTextBoldLeft" >
					<span style="font-weight: bold;"><c:out value="${dist}" /></span>
				</td>
			</tr>
			<tr>
			<td></td>
			</tr>
			<tr>
				<td class="dataTextBoldLeft"  >
				<h3 style="font-weight: normal;" >
					was present in school from &nbsp;&nbsp;</h3>
				</td>
				<td class="dataTextBoldLeft"  >
					<span style="font-weight: bold;"><c:out value="${studentdetails.student.yearofadmission}" /></span>
				</td>
				<td class="dataTextBoldLeft"  >
				<h3 style="font-weight: normal;" >
					to</h3>
				</td>
				<td class="dataTextBoldLeft"  >
					<c:choose>
                                <c:when test="${empty studentdetails.student.promotedyear}">
                                    <span style="font-weight: bold;"><c:out value="${studentdetails.student.yearofadmission}" /></span>
                                </c:when>
                                <c:otherwise>
                                   <span style="font-weight: bold;"><c:out value="${studentdetails.student.promotedyear}" /></span>
                                </c:otherwise>
                            </c:choose>
				</td>
				<tr>
				<td class="dataTextBoldLeft"  >
				<h3 style="font-weight: normal;" >
					He/She leaving School on &nbsp;&nbsp;</h3>
				</td>
				<td class="dataTextBoldLeft"  >
					<span style="font-weight: bold;"><c:out value="${leavingdate}" /></span>
				</td>

			</tr>
            <tr>
				<td class="dataTextBoldLeft" >
					<h3 style="font-weight: normal;" >

					Total No. of Working days &nbsp;&nbsp;</h3>
				</td>
				<td class="dataTextBoldLeft" >
					<span style="font-weight: bold;"><c:out value="${Workingdays}" /></span>
				</td>
				<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">Total No. of Working<br> days present
					</h3>
				</td>
				<td class="dataTextBoldLeft">
					<span style="font-weight: bold;"> <c:out value="${Workingdayspresent}" /></span>
				</td>
				</tr>
			<tr>

				<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">DATE OF BIRTH(in numbers) &nbsp;&nbsp;
					</h3>
				</td>
				
				<td class="dataTextBoldLeft">
					<span style="font-weight: bold;"> 
					<fmt:formatDate value="${studentdetails.student.dateofbirth}" pattern="dd/MM/yyyy"/>
					</span>
				</td>

				<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;" >

					DATE OF <br>BIRTH(in words)</h3>
				</td>
				
				<td class="dataTextBoldLeft">
					<span style="font-weight: bold;"><c:out value="${dateinword}" /></span>
				</td>
				


			</tr>
			<tr>
			<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;" >

					Class in which the student <br>last studied &nbsp;&nbsp;</h3>
				</td>
				
				<td class="dataTextBoldLeft">
					<span style="font-weight: bold;">
					
						<c:forEach var="splt" items="${fn:split(studentdetails.student.classstudying,'--')}">
						    	<c:out value="${splt}" />
						</c:forEach>
					</span>
				</td>

				<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;" >
					Medium </h3>
				</td>
				
				<td class="dataTextBoldLeft">
					<span style="font-weight: bold;"><c:out value="${medium}" /></span>
				</td>
				</tr>
				<tr>
				<td class="dataTextBoldLeft" >
					<h3 style="font-weight: normal;" >

					School/Board Annual<br> Examination was<br> taken with Result &nbsp;&nbsp;</h3>
				</td>
				<td class="dataTextBoldLeft" >
					<span style="font-weight: bold;"><c:out value="${withresult}" /></span>
				</td>
				<td class="dataTextBoldLeft" >
					<h3 style="font-weight: normal;" >

					Remarks &nbsp;&nbsp;</h3>
				</td>
				<td class="dataTextBoldLeft" >
					<span style="font-weight: bold;"><c:out value="${remarkstc}" /></span>
				</td>
			</tr>
			
			
			<tr>
			<td></td>
			</tr>
			<tr>
			<td></td>
			</tr>
		</table>
		<TABLE align="center">

		<tr>
		<td>
					She/He was <b>Excellent/ Very Good/ Good</b></td>	
	    </tr>
		</TABLE>

		<TABLE align="center" id="dataTable" >
        <tr><td>
        <br><br><br>
        </td></tr>
			
		<tr>
		<td>Class Teacher&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
		<td>Checked By&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
			<td>HM/Principal</td>

			</tr>
        <tr>
         <td>
                            <button id="print" type="button" style="background-image: url(/bsr/images/print.jpg);width: 63px;height: 60px" onclick="window.print();
                                    this.style.visibility = 'hidden', loading.style.visibility = 'visible'" class="hide"></button>     
                        </td>
        </tr>
			<%-- <tr>
               <td align="center"><a id="print" href="/bsr/DocumentsProcess/PrintTransferCertificate?id=<c:out value="${studentdetails.student.sid}" />">Print</a></td>
             </tr> --%>
		</TABLE>

		<%-- <a id="print" href="/bsr/Controller?process=StudentProcess&action=GenerateBonafide&id=<c:out value="${studentdetails.student.sid}" />">Print</a> --%>
	</form>


</body>
</html>
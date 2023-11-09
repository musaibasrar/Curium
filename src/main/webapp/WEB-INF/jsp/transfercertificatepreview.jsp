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
        .input{
        width:10px;
        text-align:center;
        }
    </style>
	<script type="text/javascript" src="/bsr/js/datePicker/jquery-1.7.1.js"></script>
        <script type="text/javascript" src="/bsr/js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
        <script src="/bsr/js/print/jquery.printPage.js" type="text/javascript"></script>
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
		<table width="100%" style="border-collapse: collapse;">
			<tr>
				<td class="dataTextBoldCenter" style="width: 100%">

				THE BRAINY STARS ACADEMY </td>
			</tr>
			<tr>
			<td class="addressLine"><u>A UNIT OF TAMIRE MILLAT EDUCATIONAL SOCITY RATLAM</u></td>
			</tr>
			<tr>
			<td class="addressLine"><u>SECTOR NO. ARIHANT PARISAR RATLAM MP 457001</u></td>
			</tr>
			<tr>
			<td class="addressLine"><u>PHONE 07412-455722</u></td>
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

		<table>
		<tr>

			<td colspan="2">

			<h3><u>TRANSFER CERTIFICATE</u></h3>
			</td>

			</tr>
			<tr>
			<td></td>

			</tr>
			<tr>
			<td></td>

			<td style="align-content: right">
			Date:&nbsp;&nbsp;<input
									name="dateoftc" type="text" class="textField" style="border: none;border-color: transparent;"
									 size="10" value="<fmt:formatDate type="date" value="${tcdetails.dateofissues}" pattern="yyyy-MM-dd"/>" ></td>

			</tr>
			<tr>

				<td class="dataTextBoldLeft"style="width: 40%;">
				&nbsp;&nbsp;
					<h3 style="font-weight: normal;">This is to certify that Ms. &nbsp;&nbsp;
					<span style="font-weight: bold;"> <c:out value="${studentdetails.student.name}" /></span>
					</h3>
				</td>
				<!-- <td>
				
				</td> -->

				<td class="dataTextBoldLeft" style="width: 40%;">
					 &nbsp;&nbsp;
					<h3 style="font-weight: normal;" >

					Mother's name &nbsp;&nbsp;<span style="font-weight: bold;"><c:out value="${studentdetails.mothersname}" /></span></h3>
				</td>


			</tr>
			<tr>
			<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">Father's/Guardian's Name &nbsp;&nbsp;
					<span style="font-weight: bold;"> <c:out value="${studentdetails.fathersname}" /></span>
					</h3>
				</td>
				<td class="dataTextBoldLeft" style="width: 40%;">
					 &nbsp;&nbsp;
					<h3 style="font-weight: normal;" >

					Religion &nbsp;&nbsp;<span style="font-weight: bold;"><c:out value="${studentdetails.student.religion}" /></span></h3>
				</td>

			</tr>
			<tr>
			<td class="dataTextBoldLeft" style="width: 40%;">
					 &nbsp;&nbsp;
					<h3 style="font-weight: normal;" >

					Caste &nbsp;&nbsp;<span style="font-weight: bold;"><c:out value="${studentdetails.student.studentscaste}" /></span></h3>
				</td>
			<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">Block &nbsp;&nbsp;
					<span style="font-weight: bold;"> <c:out value="${dist}" /></span>
					</h3>
				</td>
				</tr>
				<tr>
			<td class="dataTextBoldLeft" style="width: 40%;">
					 &nbsp;&nbsp;
					<h3 style="font-weight: normal;" >

					District &nbsp;&nbsp;<span style="font-weight: bold;"><c:out value="${dist}" /></span></h3>
				</td>
			<td class="dataTextBoldLeft" style="width: 40%;">
					 &nbsp;&nbsp;
					<h3 style="font-weight: normal;" >

					City &nbsp;&nbsp;<span style="font-weight: bold;"><c:out value="${dist}" /></span></h3>
				</td>
			</tr>
			<tr>
			<td></td>
			</tr>
			<tr>
			<%-- <td class="dataTextBoldLeft" style="width: 40%;">
					 &nbsp;&nbsp;
					<h3 style="font-weight: normal;" >
					
					Son of &nbsp;&nbsp;<span style="font-weight: bold;"><c:out value="${studentdetails.fathersname}" /></span></h3>
				</td> --%>

				<td class="dataTextBoldLeft"  >

				<h3 style="font-weight: normal;" >

					was present in school from &nbsp;&nbsp; <span style="font-weight: bold;"><c:out value="${studentdetails.student.admissiondate}" /></span></h3>


				</td>
				<td class="dataTextBoldLeft"  >

				<h3 style="font-weight: normal;" >

					to &nbsp;&nbsp; <span style="font-weight: bold;"><c:out value="${studentdetails.student.dateleaving}" /></span></h3>


				</td>
				<tr>
				<td class="dataTextBoldLeft"  >

				<h3 style="font-weight: normal;" >

					He/She leaving School on &nbsp;&nbsp; <span style="font-weight: bold;"><c:out value="${studentdetails.student.dateleaving}     " /></span></h3>


				</td>

			</tr>
             <tr>

				<td class="dataTextBoldLeft"style="width: 40%;">
				&nbsp;&nbsp;
					<h3 style="font-weight: normal;">Pupil's SSSMID &nbsp;&nbsp;
				<!-- 	<span style="font-weight: bold;">--> <input class="input" type="text">
				<input class="input" type="text">
				<input class="input" type="text">
				<input class="input" type="text">
				<input class="input" type="text">
				<input class="input" type="text">
				<input class="input" type="text">
				<input class="input" type="text">
				<input class="input" type="text">


				<!-- </span>-->
					</h3>
				</td>
				<!-- <td>
				
				</td> -->

				<td class="dataTextBoldLeft" style="width: 40%;">
					 &nbsp;&nbsp;
					<h3 style="font-weight: normal;" >

					Family ID &nbsp;&nbsp;<input class="input" type="text">
				<input class="input" type="text">
				<input class="input" type="text">
				<input class="input" type="text">
				<input class="input" type="text">
				<input class="input" type="text">
				<input class="input" type="text">
				<input class="input" type="text"></h3>
				</td>


			</tr>
			<tr>

				<td class="dataTextBoldLeft"style="width: 40%;">
				&nbsp;&nbsp;
					<h3 style="font-weight: normal;">ADHAR NO. &nbsp;&nbsp;
					<input class="input" type="text"  value="${arr[0]}">
				<input class="input" type="text" value="${arr[1]}">
				<input class="input" type="text" value="${arr[2]}">
				<input class="input" type="text" value="${arr[3]}">
				<input class="input" type="text" value="${arr[4]}">
				<input class="input" type="text" value="${arr[5]}">
				<input class="input" type="text" value="${arr[6]}">
				<input class="input" type="text" value="${arr[7]}">
				<input class="input" type="text" value="${arr[8]}">
				<input class="input" type="text" value="${arr[9]}">
				<input class="input" type="text" value="${arr[10]}">
				<input class="input" type="text" value="${arr[11]}">

					</h3>
				</td>



			</tr>
			<tr>

				<td class="dataTextBoldLeft"style="width: 40%;">
				&nbsp;&nbsp;
					<h3 style="font-weight: normal;">DATE OF BIRTH(in numbers) &nbsp;&nbsp;
					<span style="font-weight: bold;"> <c:out value="${studentdetails.student.dateofbirth}" /></span>
					</h3>
				</td>
				<!-- <td>
				
				</td> -->

				<td class="dataTextBoldLeft" style="width: 40%;">
					 &nbsp;&nbsp;
					<h3 style="font-weight: normal;" >

					DATE OF BIRTH(in words) &nbsp;&nbsp;<span style="font-weight: bold;"><c:out value="${dateinword}" /></span></h3>
				</td>


			</tr>
			<tr>
			<td class="dataTextBoldLeft" style="width: 40%;">
					 &nbsp;&nbsp;
					<h3 style="font-weight: normal;" >

					Class last student studied &nbsp;&nbsp;<span style="font-weight: bold;"><c:out value="${studentdetails.student.classstudying}" /></span></h3>
				</td>

				<td class="dataTextBoldLeft" style="width: 40%;">
					 &nbsp;&nbsp;
					<h3 style="font-weight: normal;" >

					Medium &nbsp;&nbsp;<span style="font-weight: bold;"><c:out value="${medium}" /></span></h3>
				</td>
				</tr>
				<tr>
				<td class="dataTextBoldLeft" style="width: 40%;">
					 &nbsp;&nbsp;
					<h3 style="font-weight: normal;" >

					Year &nbsp;&nbsp;<span style="font-weight: bold;"><c:out value="${currentacadmicyear}" /></span></h3>
				</td>
			</tr>
			<tr>
			<td></td>
			</tr>
			<tr>
			<td></td>
			</tr>
		</table>
		<TABLE width="100%" border="0"
			style="page-break-after: always; border-collapse: collapse;">

		<tr>
		<td><br></td>
		</tr>
		<tr>
		<td class="dataTextBoldLeft" >
					She/He was <b>Excellent/ Very Good/ Good</b></td>	
	    </tr>
		</TABLE>

		<TABLE id="dataTable" width="100%" border="0"
			style="page-break-after: always; border-collapse: collapse;">

			<tr>
			<td>
			<br>
			<br></td>
			</tr>
			<tr>
			<td></td>
			</tr>
			<tr>
			<td></td>
			</tr>
		<tr>
		<td></td>
		<td align="left">Class Teacher</td>	
		<td align="centre">Checked By</td>
			<td align="centre">School Seal</td>

			</tr>

			<tr>
               <td align="center"><a id="print" href="/bsr/DocumentsProcess/PrintTransferCertificate?studentId=<c:out value="${studentdetails.student.sid}"/>&tcdate=<c:out value="${tcdate}"/>">Print</a></td>
             </tr>
		</TABLE>

		<%-- <a id="print" href="/bsr/Controller?process=StudentProcess&action=GenerateBonafide&id=<c:out value="${studentdetails.student.sid}" />">Print</a> --%>
	</form>


</body>
</html>
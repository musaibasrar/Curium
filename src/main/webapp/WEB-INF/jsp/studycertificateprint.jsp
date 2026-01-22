<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta charset="UTF-8">
<title>Study Certificate</title>

<style>
    body{
        font-family: "Times New Roman", serif;
        background:#fff;
    }

    .certificate{
        width: 800px;
        margin: auto;
        padding: 40px;
        border: 1px solid #000;
    }

    .center{
        text-align: center;
    }

    .title{
        font-size: 26px;
        font-weight: bold;
        margin-bottom: 10px;
    }

    .sub-title{
        font-size: 14px;
        margin-bottom: 30px;
    }

    table{
        width: 100%;
        border-collapse: collapse;
        font-size: 16px;
    }

    td{
        padding: 8px;
        vertical-align: top;
    }

    .line{
        border-bottom: 1px dotted #000;
        display: inline-block;
        min-width: 250px;
        text-align:center;
        font-weight:bold;
    }

    .date{
        text-align: right;
        margin-bottom: 20px;
    }

    .seal{
        width: 200px;
        height: 80px;
        border: 1px solid #000;
        border-radius: 50%;
        text-align: center;
        line-height: 80px;
        margin-top: 30px;
    }

    .signature{
        text-align: right;
        margin-top: 40px;
    }

    .footer{
        margin-top: 40px;
        border-top: 1px solid #000;
        padding-top: 20px;
        text-align: center;
    }
    
      @media print{
        .print-btn{
            display: none;
        }
        body{
            margin: 0;
        }
    }
    
</style>
</head>
<%
//allow access only if session exists
String user = null;
if(session.getAttribute("userAuth") == null){
	response.sendRedirect("/alfarooq/UserProcess/sessionTimeOut");
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

<body>
<jsp:useBean id="now" class="java.util.Date" scope="page" />
<div class="certificate">

    <div class="center">
        <div class="title">STUDY CERTIFICATE</div>
        <div class="sub-title">
            NAME, FULL POSTAL ADDRESS & TELEPHONE<br>
            NUMBER OF THE INSTITUTION
        </div>
    </div>

    <div class="date">
        Date: <span class="line">&nbsp;<input name="dateofcr" type="text" class="textField" style="border: none;border-color: transparent;"
					size="10" value="<fmt:formatDate type="date" value="${now}" pattern="dd/MM/yyyy"/>" ></span>
    </div>

    <table>
        <tr>
            <td colspan="2">
                This is to certify that Sri./Kum.
                <span class="line">&nbsp;&nbsp;<c:out value="${studentdetailsbonafide.student.name}" />&nbsp;&nbsp;</span>
            </td>
        </tr>

        <tr>
            <td colspan="2">
                S/o / D/o
                <span class="line">&nbsp;&nbsp; <c:out value="${studentdetailsbonafide.fathersname}" />&nbsp;&nbsp;</span>
                has studied from
                <span class="line">&nbsp;<c:out value="${studentdetailsbonafide.student.classadmittedin}" /></span>
                standard to
                 </td>
        </tr>

        <tr>
            <td colspan="2">
                <span class="line">&nbsp; <c:out value="${studentdetailsbonafide.student.classstudying}" /></span>
                standard
            </td>
        </tr>

        <tr>
            <td colspan="2">
                In our institution from
                <span class="line">&nbsp;<c:out value="${studentdetailsbonafide.student.yearofadmission}" /></span>
                to
                <span class="line">&nbsp;<c:out value="${studentdetailsbonafide.student.promotedyear}" /></span>
                Academic years.
            </td>
        </tr>

        <tr><td colspan="2">&nbsp;</td></tr>

        <tr>
            <td colspan="2">
                The Mother tongue of the candidate is
                <span class="line">&nbsp;<c:out value="${studentdetailsbonafide.student.mothertongue}" /></span>
            </td>
        </tr>

        <tr>
            <td colspan="2">
                As per the admission register of the institution.
            </td>
        </tr>

        <tr>
            <td colspan="2">
                The above details are true and correct to her best of my knowledge.
            </td>
        </tr>
    </table>

    <table>
        <tr>
            <td>
                <div class="seal">Institution Seal</div>
            </td>

            <td class="signature">
                Signature of<br>
                <b>Head of the Institution</b><br><br>
                (Name in Block Letters)
                <span class="line">&nbsp;</span>
            </td>
        </tr>
    </table>

    <div class="footer">
        <b>COUNTER SIGNED BY ME</b><br><br>
        Address, seal & office Telephone Number<br>
        of the Block Educational officer / DDPI.
    </div>

</div>
<div class="print-btn" style="text-align:center;">
    <button onclick="window.print()">Print</button>
</div>
=======
<body style="text-align: center" class="bodymargin">
	<jsp:useBean id="now" class="java.util.Date" scope="page" />
	<form method="post" class="bodymargin">
		<br>
		<table align="center">
			
			
			<tr>
			<td>
			<img border="0" style="vertical-align: text-bottom;height: 113px;width: 100px;" alt="logo" src="/littleflower/images/littleflower.png">
			</td>
				<td >
					<br>
					<h2 style="margin-bottom:0px;"><img src="/littleflower/images/littleflowerschoolname.png" width="391" height="25" /></h2>
					<h3 style="margin-top:0px;">${branchaddress}<br>${branchcontact}</h3>
					
				</td>
			</tr>
			<tr><td></td><td>
					<h2><u>STUDY CERTIFICATE</u></h2></td></tr>
			</table>
			<table align="center">
			<tr>
			<td></td>
			
			</tr>
			<tr>
			<td></td>
			
			</tr>
			<tr>
			
				<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">Admission No. &nbsp;&nbsp;
					<span style="font-weight: bold;text-transform: capitalize;width:120px;"> <c:out value="${studentdetailsbonafide.student.admissionnumber}" /></span>
					</h3>
				</td>
			</tr>
			
			<tr>
			
				<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;This is to certify that Mr./Ms. &nbsp;&nbsp;
					<span class="rightside" style="font-weight: bold;text-transform: capitalize;width:540px;"> <c:out value="${studentdetailsbonafide.student.name}" /></span>
					</h3>
				</td>
			</tr>
			<tr>
			
				<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">Son/Daughter of &nbsp;&nbsp;
					<span  style="font-weight: bold;text-transform: capitalize;width:540px;"> <c:out value="${studentdetailsbonafide.fathersname}" /></span>
					&nbsp;&nbsp;Was a bonafide
					</h3>
				</td>
			</tr>
			<tr>
			
				<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;"> student of this institute/college during the year from &nbsp;&nbsp;
					<span style="font-weight: bold;text-transform: capitalize;width:120px;"> <c:out value="${studentdetailsbonafide.student.yearofadmission}" /></span>&nbsp;&nbsp;&nbsp;&nbsp;to&nbsp;&nbsp;&nbsp;&nbsp;
					<span style="font-weight: bold;text-transform: capitalize;width:130px;"> <c:out value="${studentdetailsbonafide.student.promotedyear}" /></span>&nbsp;&nbsp;&nbsp;&nbsp;
					studying</h3>
				</td>
			</tr>
			<tr>
			
				<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">
					 from<span style="font-weight: bold;text-transform: capitalize;width:120px;"> <c:out value="${studentdetailsbonafide.student.classadmittedin}" /></span>&nbsp;&nbsp;&nbsp;&nbsp;to&nbsp;&nbsp;&nbsp;&nbsp;
					<span style="font-weight: bold;text-transform: capitalize;width:120px;"> <c:out value="${studentdetailsbonafide.student.classstudying}" /></span>
					</h3>
				</td>
			</tr>
			<tr>
			
				<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">During the period his character is found to be Good/Satisfactory. 
					</h3>
				</td>
			</tr>
			<tr>
			
				<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;His Date of Birth is &nbsp;&nbsp;
					<span style="font-weight: bold;text-transform: capitalize;"> <c:out value="${studentdetailsbonafide.student.dateofbirth}" /></span>
					and Religion is<span class="rightside" style="font-weight: bold;text-transform: capitalize;width:200px;"> <c:out value="${studentdetailsbonafide.student.religion}" /></span></h3>
				</td>
			</tr>
			<tr>
			
				<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;"> 
					caste is <span style="font-weight: bold;text-transform: capitalize;"> <c:out value="${studentdetailsbonafide.student.caste}" /></span>
					as per his/her admission Register No. <span style="font-weight: bold;text-transform: capitalize;width:190px;"></span></h3>
				</td>
			</tr>
			
			<tr>
			<td class="dataTextBoldLeft">
			<br><br>
				Date:&nbsp;&nbsp;
				<input name="dateofcr" type="text" class="textField" style="border: none;border-color: transparent;"
					size="10" value="<fmt:formatDate type="date" value="${now}" pattern="dd/MM/yyyy"/>" ></td>
			
			</tr>
			<tr>
			
				<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">place &nbsp;&nbsp;
					<span style="font-weight: bold;text-transform: capitalize;width:120px">&nbsp;&nbsp;&nbsp;&nbsp;</span>
					<span style="font-weight: bold;text-transform: capitalize;width:400px;border-bottom:0px solid black;">&nbsp;&nbsp;&nbsp;&nbsp;</span>Headmaster/principal
					</h3>
				</td>
			</tr>
			
		</table>
		

		<TABLE id="dataTable" width="100%" border="0"
			style="page-break-after: always; border-collapse: collapse;">

			
			<tr>
              <td align="center"><a id="print" href="/littleflower/DocumentsProcess/printStudyCertificate">Print</a></td>
            </tr>
		</TABLE>
	</form>
</body>
</html>

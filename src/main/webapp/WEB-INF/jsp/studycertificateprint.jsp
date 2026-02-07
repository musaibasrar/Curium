<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Study Certificate</title>

<style>
body{
    font-family: "Times New Roman", serif;
    background:#f5f5f5;
}

.certificate{
    width: 800px;
    margin: 20px auto;
    background: #fff;
    padding: 30px;
    border: 2px solid #000;
}

.header{
    text-align: center;
}

.header h2{
    margin: 5px 0;
    font-size: 22px;
    letter-spacing: 1px;
}

.header h3{
    margin: 5px 0;
    font-size: 18px;
}

.header p{
    margin: 2px 0;
    font-size: 14px;
}

.top-right{
    text-align: right;
    font-size: 14px;
}

.title{
    text-align: center;
    margin: 20px 0;
    font-size: 22px;
    font-weight: bold;
    text-decoration: underline;
}

.content{
    font-size: 16px;
    line-height: 30px;
}

.line{
    display: inline-block;
    border-bottom: 1px solid #000;
    min-width: 200px;
    text-align: center;
    font-weight: bold;
}

.table-section{
    width: 100%;
    margin-top: 15px;
}

.table-section td{
    padding: 6px;
    font-size: 16px;
}

.footer{
    margin-top: 60px;
    width: 100%;
}

.footer td{
    font-size: 16px;
    vertical-align: bottom;
}

.signature{
    text-align: right;
}

.print-btn{
    text-align:center;
    margin:20px;
}

@media print{
    .print-btn{
        display:none;
    }
}
</style>
<script type="text/javascript" src="/patriswamy/js/datePicker/jquery-1.7.1.js"></script>
        <script type="text/javascript" src="/patriswamy/js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
        
        <title>Study Certificate</title>
 <script>
 $(function() {

		
		$(".printStudyCertificate").button().click(function() {
			printStudyCertificate();

		});

		
					
	});
 function printStudyCertificate(){
     	var form1 = document.getElementById("form1");
 		form1.action = "/patriswamy/DocumentsProcess/printStudyCertificate";
 		form1.method = "POST";
 		form1.submit();
     }
 </script>
</head>

<body>
<jsp:useBean id="now" class="java.util.Date" scope="page" />
	<form id="form1" method="post" class="bodymargin">

<div class="certificate">

    <div class="header">
        <h3>ARUNODAYA VIDHYA VIKAS TRUST</h3>
        <h2><select name="collegename"
									 id="collegename"
									style="width: 500px;border-radius: 4px;background: white;height: 28px;">
										<option selected>Select College Name</option>
										<option>PATRISWAMY SCIENCE PU COLLEGE, AURAD (B) - 585326</option>
										<option>SRI SATHYAM PU COLLEGE AURAD (B)-585326</option>
										<option>NALANDA COMPOSITE PRE-UNIVERSITY <br>COLLEGE AURAD (B)-585326</option>
								</select></h2>
        <p>(Recognized by the PUE Board, Bangalore)</p>
        <p>College Code: FF0207 &nbsp;&nbsp; Phone No: 8884106686 / 9739819534</p>
    </div>

    <div class="top-right">
        Date: <strong><input name="dateofcr" type="text" class="textField" style="border: none;border-color: transparent;"
					size="10" value="<fmt:formatDate type="date" value="${now}" pattern="dd/MM/yyyy"/>" ></strong>
    </div>

    <div class="title">
        STUDY CERTIFICATE
    </div>

    <div class="content">
        This is to certify that Sri/Kum. 
        <span class="line"><c:out value="${studentdetailsbonafide.student.name}" /></span><br>

        S/O 
        <span class="line"> <c:out value="${studentdetailsbonafide.fathersname}" /></span> 
        has studied from 
        <span class="line"><c:out value="${studentdetailsbonafide.student.classadmittedin}" />1<sup>st</sup> YEAR</span><br>

        standard to 
        <span class="line"><c:out value="${studentdetailsbonafide.student.classstudying}" /> 2<sup>nd</sup> YEAR</span> 
        standard in our institute,<br>

        from 
        <span class="line"> <c:out value="${studentdetailsbonafide.student.yearofadmission}" /></span> 
        to 
        <span class="line"><c:out value="${studentdetailsbonafide.student.promotedyear}" /></span> 
        academic years.
        <br><br>

        As per the admission register of the institute.  
        The above details are true and correct to the best of my knowledge.
    </div>

    <table class="footer">
        <tr>
            <td>
                Institution Seal
            </td>
            <td class="signature">
                Signature of Principal<br><br>
                Name: <strong>AKHIL</strong>
            </td>
        </tr>
    </table>

    <div style="margin-top:40px;font-size:15px;">
        COUNTER SIGNED BY ME<br>
        Address, Seal & Office Telephone Number<br>
        Of the Block Educational Office / DDPI<br>
        Mobile Number
    </div>

</div>

<TABLE id="dataTable" width="100%" border="0"
			style="page-break-after: always; border-collapse: collapse;">

			
			<tr>
              <td align="center"><button class="printStudyCertificate"  >Print</button></td>
            </tr>
		</TABLE>
		</form>
</body>
</html>

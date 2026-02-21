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
/* Reset */
*{
    box-sizing: border-box;
}

body{
    font-family: "Times New Roman", serif;
    margin: 0;
    padding: 0;
    background: #fff;
}

/* Force EXACT A4 */
@page{
    size: A4;
    margin: 15mm;
}

/* Certificate wrapper */
.certificate{
    width: 100%;
    height: 267mm; /* 297mm - (15mm top + 15mm bottom) */
    padding: 10mm 12mm;
    border: 2px solid #000;
}

/* Header */
.header{
    text-align: center;
}

.header h3{
    font-size: 18px;
    margin: 3px 0;
}

.header h2{
    font-size: 22px;
    margin: 4px 0;
}

.header p{
    font-size: 14px;
    margin: 2px 0;
}

/* Date */
.top-right{
    text-align: right;
    font-size: 14px;
    margin-top: 6px;
}

/* Title */
.title{
    text-align: center;
    font-size: 22px;
    font-weight: bold;
    text-decoration: underline;
    margin: 20px 0;
}

/* Main content */
.content{
    font-size: 16px;
    line-height: 30px;
    text-align: justify;
}

/* Underline values */
.line{
    display: inline-block;
    min-width: 220px;
    border-bottom: 1px solid #000;
    text-align: center;
    font-weight: bold;
}

/* Footer table */
.footer{
    width: 100%;
    margin-top: 60px;
}

.footer td{
    font-size: 16px;
    vertical-align: bottom;
}

/* Principal signature */
.signature{
    text-align: right;
}

/* Counter signed section */
.counter{
    margin-top: 35px;
    font-size: 15px;
}

/* Hide buttons while printing */
@media print{
    button, .print-btn{
        display: none !important;
    }

    body{
        margin: 0;
    }
}


</style>
<script type="text/javascript" src="/patriswamy/js/datePicker/jquery-1.7.1.js"></script>
        <script type="text/javascript" src="/patriswamy/js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
        
        <title>Study Certificate</title>
 
 <script type="text/javascript" src="/patriswamy/js/datePicker/jquery-1.7.1.js"></script>
 <script type="text/javascript" src="/patriswamy/js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
 <title>Study Certificate</title>
  <script type="text/javascript">
      window.onload = function(){
     	 window.print();
      }
 </script>
 
</head>

<body>
<jsp:useBean id="now" class="java.util.Date" scope="page" />
	<form id="form1" method="post" class="bodymargin">

<div class="certificate">
<br>
<br>
<br>


    <div class="header">
       
        <h2>${collegename}</h2>
        
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
        <span class="line"><c:out value="${studentname}" /></span><br>

        S/O 
        <span class="line"> <c:out value="${fathersname}" /></span> 
        has studied from 
        <span class="line"><c:out value="${studiedfrom}" /> </span><br>

        standard to 
        <span class="line"><c:out value="${studiedto}" />  </span> 
        standard in our institute,<br>

        from 
        <span class="line"> <c:out value="${fromyear}" /></span> 
        to 
        <span class="line"><c:out value="${toyear}" /></span> 
        academic years.
        <br><br>

        As per the admission register of the institute.  
        The above details are true and correct to the best of my knowledge.
    </div>
    <br>
<br>
<br>
    <table class="footer">
        <tr>
            <td>
                Institution Seal
            </td>
            <td class="signature">
                Signature of Principal<br><br>
                Name: <strong><c:out value="${nameofprincipal}" /></strong>
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


		
		</form>
</body>
</html>

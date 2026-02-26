<%-- 
    Document   : Transfer Certificate
    Created on : MAR 20, 2018, 01:20:35 PM
    Author     : Musaib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Transfer Certificate</title>
        <link rel="stylesheet" href="/patriswamy/css/datePicker/jquery-ui-1.8.18.custom.css">
        <link rel="stylesheet" href="/patriswamy/css/graph/jquery.jqplot.css">

        <link rel="stylesheet" href="/patriswamy/css/datePicker/demos.css">
        <script type="text/javascript" src="/patriswamy/js/datePicker/jquery-1.7.1.js"></script>
        <script type="text/javascript" src="/patriswamy/js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
        <script type="text/javascript" src="/patriswamy/js/datePicker/ui/jquery.ui.dialog.js"></script>
        <script type="text/javascript" src="/patriswamy/js/datePicker/ui/jquery.ui.autocomplete.js"></script>
        <script type="text/javascript" src="/patriswamy/js/datePicker/ui/jquery.ui.core.js"></script>
        <script type="text/javascript" src="/patriswamy/js/datePicker/ui/jquery.ui.widget.js"></script>
        <script type="text/javascript" src="/patriswamy/js/datePicker/ui/jquery.ui.datepicker.js"></script>
        <script type="text/javascript" src="/patriswamy/js/datePicker/ui/jquery.ui.accordion.js"></script>
        <script type="text/javascript" src="/patriswamy/js/datePicker/ui/sliderAccess.js"></script>
        <script type="text/javascript" src="/patriswamy/js/datePicker/ui/jquery-ui-timepicker-addon.js"></script>
        <script  type="text/javascript" src="/patriswamy/js/datePicker/ui/jquery.ui.position.js"></script>
        <script type="text/javascript" src="/patriswamy/js/datePicker/ui/jquery.ui.mouse.js"></script>
        <script type="text/javascript" src="/patriswamy/js/datePicker/ui/jquery.ui.draggable.js"></script>
        <script type="text/javascript" src="/patriswamy/js/datePicker/ui/jquery.ui.resizable.js"></script>

        <script type="text/javascript" src="/patriswamy/js/datePicker/ui/jquery.effects.slide.js"></script>
        <script type="text/javascript" src="/patriswamy/js/datePicker/ui/jquery.effects.bounce.js"></script>
        <script type="text/javascript" src="/patriswamy/js/datePicker/ui/jquery.effects.clip.js"></script>
        <script type="text/javascript" src="/patriswamy/js/datePicker/ui/jquery.effects.transfer.js"></script>
        <script type="text/javascript" src="/patriswamy/js/datePicker/ui/jquery.effects.blind.js"></script>
        <style type="text/css">
            <!--
            .labelCss {
                font-family: Tahoma;
                font-size: 11px;
                font-weight: bold;
            }
            .dataTextInActive {
                border-radius:1px;
                font-family: Tahoma;
                color: #4b6a84;
                font-size: 12px;
                font-weight: bold;
                letter-spacing: normal;
                text-align: center;
                vertical-align: top;
                text-decoration:none;
            }
            .headerText {
                border-radius:3px;
                font-family: Tahoma;
                font-size: 12px;
                background-color: #4b6a84;
                color: #FFFFFF;
                font-weight: normal;
                width: auto ;
                height: 22px;
                vertical-align: middle;
                text-align: center;
                background-image: url("/images/ui-bg_diagonals-small_50_466580_40x40.png");
            }
            .headerTD{
                background-color:#4b6a84;
                color: #FFFFFF;
                font-family: Tahoma;
                font-size: 13px;
                text-transform: uppercase;
                text-align: center;
                font-weight: bold;
            }
            .smallheaderTD{
                color: #4b6a84;
                font-family: Tahoma;
                font-size: 11px;
                text-transform: uppercase;
                text-align: left;
                font-weight: bold;
            }
            .textFieldFixedWidth{
                width: 57px;
            }
            .subHeaderTD{
                color: #325F6D;
                font-family: Tahoma;
                font-size: 11px;
                text-transform: uppercase;
                text-align: left;
                font-weight: bold;
            }
            .divCSS{
                overflow:  scroll;
                height: 100%;
                width: 100%;
            }

            .fiedlSet {
                border-top-width: 1px;
                border-right-width: 1px;
                border-bottom-width: 1px;
                border-left-width: 1px;
                border-top-style: solid;
                border-right-style: solid;
                border-bottom-style: solid;
                border-left-style: solid;
                border-width: 1;
                width: 100%;
                color: #000000;
                font-size: 16px;
                font-weight: bold;
                font-variant: normal;
                font-stretch: wider;
                background-color: #e2ebf3;
                border-top-color: #5d7e9b;
                border-right-color: #5d7e9b;
                border-bottom-color: #5d7e9b;
                border-left-color: #5d7e9b;
            }
            .legendCSS {
                color: #666666;
            }
            .tableCSS {
                width: 100%;
                height: 100%;
                position: absolute;
                left: 0px;
                top: 0px;
            }
            .textAreaCSS {
                height: auto;
                width: auto;
            }
            .textField {
                border-top-style: solid;
                border-right-style: solid;
                border-bottom-style: solid;
                border-left-style: solid;
                border-top-color: #5d7e9b;
                border-right-color: #5d7e9b;
                border-bottom-color: #5d7e9b;
                border-left-color: #5d7e9b;
                border-top-width: 1px;
                border-right-width: 1px;
                border-bottom-width: 1px;
                border-left-width: 1px;
                width: auto;
                height: auto;
            }
            .alignRight {
                font-family: Tahoma;
                font-size: 11px;
                font-style: normal;
                text-transform: capitalize;
                color: #325F6D;
                text-align: right;
                vertical-align: middle;
                font-weight: bold;
            }
            .alignLeft {
                font-family: Tahoma;
                font-size: 11px;
                font-style: normal;
                text-transform: capitalize;
                color: #325F6D;
                text-align: left;
                vertical-align: middle;
                font-weight: bold;
            }
            .alignRightMultiple {
                font-family: Tahoma;
                font-size: 11px;
                font-weight: bolder;
                text-align: right;
                vertical-align: middle;
                font-style: normal;
                color: #325F6D;
            }
            .alignCentreMultiple {
                font-family: Arial, Helvetica, sans-serif;
                font-size: 12px;
                font-weight: bolder;
                text-align: center;
                vertical-align: middle;
                font-style: normal;
                color: #000000;
            }
            .autoAdjust {
                height: auto;
                width: auto;
            }
            .radioSpanCSS {
                font-size: 12px;
                font-family: Arial, Helvetica, sans-serif;
                text-align: left;
                vertical-align: middle;
            }
            .radioCSS {
                background-position: left center;
            }
            .spanText {
                font-family: Arial, Helvetica, sans-serif;
                font-size: 12px;
                font-weight: bold;
                color: #000000;
            }
            .emptyFieldSet {
                border-top-color: #FA7676;
                border-right-color: #FA7676;
                border-bottom-color: #FA7676;
                border-left-color: #FA7676;
                border-top-style: solid;
                border-right-style: solid;
                border-bottom-style: solid;
                border-left-style: solid;
                border-top-width: thin;
                border-right-width: thin;
                border-bottom-width: thin;
                border-left-width: thin;
                background-image: url(images/close.JPG);
                background-repeat: repeat-y;
                background-attachment: scroll;
                background-position: right;
                width: auto;
                height: auto;
                display: inline;
            }
            .style1 {
                font-family: Tahoma;
                font-size: 14px;
            }
            .style2 {
                color: #666666;
                font-family: Tahoma;
                font-size: 14px;
            }
            .style4 {
                font-size: 12px;
                font-family: Tahoma;
                text-align: left;
                vertical-align: middle;
                color: #325f6d;
            }
            -->
            td{
            	font-weight: normal;
            	font-size: 12px;
            }
            span{
    display:inline-block;
    border-bottom:2px solid #4B6A84;
    padding-bottom:1px;
    width: 300px;
    font-weight: normal;
}
        </style>
        <script type="text/javascript">
            var students = [
            <c:forEach varStatus="status" items="${studentListtc}" var="parents">{
                value:'<c:out default="0" value="${parents.student.name}" />',
                admissiondate:'<c:out default="0" value="${parents.student.admissiondate}" />',
                admissionnumber:'<c:out default="0" value="${parents.student.admissionnumber}" />',
                fathername:'<c:out default="0" value="${parents.fathersname}" />',
                mothername:'<c:out default="0" value="${parents.mothersname}" />',
                nationality:'<c:out default="0" value="${parents.student.nationality}" />',
                gender:'<c:out default="0" value="${parents.student.gender}" />',
                religion:'<c:out default="0" value="${parents.student.religion}" />',
                caste:'<c:out default="0" value="${parents.student.caste}" />',
                dateofbirth:'<c:out default="0" value="${parents.student.dateofbirth}" />',
                classandsec:'<c:out default="0" value="${parents.student.classstudying}" />',
                classadmittedin:'<c:out default="0" value="${parents.student.classadmittedin}" />',
                id:'<c:out default="0" value="${parents.student.sid}" />',
                studentname:'<c:out default="0" value="${parents.student.name}" />',
                sts:'<c:out default="0" value="${parents.student.sts}" />',
                collegecode:'<c:out default="0" value="${parents.student.schoollastattended}" />',
                category:'<c:out default="0" value="${parents.student.socialcategory}" />',
                dateofleaving:'<c:out default="0" value="${parents.student.dateleaving}" />',
                
            }<c:if test="${!status.last}">,</c:if>
            </c:forEach>
        ];
        $(function() {
            $( "#admno").autocomplete({
                source: students,
                minLength: 1,
                change:function(event,ui){
                    $( "#studentId").val( ui.item.id );
                },
                focus: function( event, ui ) {
                    $( "#studentId").val( ui.item.id );
                    return true;
                },
                select: function( event, ui ) {
                    $( "#studentId").val( ui.item.id );
                    $( "#dateofadmission").val( ui.item.admissiondate );
       			  $( "#admno").val( ui.item.admissionnumber );
       			$( "#fathername").val( ui.item.fathername );
       			$( "#mothername").val( ui.item.mothername );
       			$( "#nationality").val( ui.item.nationality );
       			$( "#gender").val( ui.item.gender );
       			$( "#religion").val( ui.item.religion );
       			$( "#caste").val( ui.item.caste );
       			$( "#dateofbirth").val( ui.item.dateofbirth );
       			$( "#classandsec").val( ui.item.classandsec );
       			$( "#classadmitted").val( ui.item.classandsec );
       			$( "#studentname").val( ui.item.studentname );
       			$( "#studentname1").val( ui.item.studentname );
       			$( "#sts").val( ui.item.sts );
       			$( "#collegecode").val( ui.item.collegecode );
       			$( "#fathername1").val( ui.item.fathername );
       			$( "#mothername1").val( ui.item.mothername );
       			$( "#category").val( ui.item.category );
       			$( "#dateofleaving").val( ui.item.dateofleaving );
                    /* $("#classandsec"+rowCount).val( ui.item.classandsec ); */
                    return true;
                }
            }).data( "autocomplete" )._renderItem = function( ul, item ) {
                return $( "<li></li>" )
                .data( "item.autocomplete", item )
                .append( "<a><b> " + item.value +"/"+item.fathername+" </b> </a>" )
                .appendTo( ul );
            };
            var addFeesButtonID="#addFees";
            var removeDossageButtonID="#removeDossage";
            $( addFeesButtonID )
            .button({
                icons: {
                    primary: "ui-icon-plus"
                }
            })
            .click(function() {
            	 getstampfees();
                 //addRow();
                return false;
            });
            $(removeDossageButtonID)
            .button({
                icons: {
                    primary: "ui-icon-minus"
                }
            })
            .click(function() {
                deleteRow('dataTable');
                return false;
            });            

        });
        $('#selectAll').click(function () {
            var length = $('.chcktbl:checked').length;
            var trLength=$('.trClass').length;
            if(length>0){
                $('.chcktbl:checked').attr('checked', false);
                this.checked=false;
            }
            else{
                if (this.checked == false) {
                    $('.chcktbl:checked').attr('checked', false);
                }
                else {
                    $('.chcktbl:not(:checked)').attr('checked', true);
                }
            }
        });
        </script>
        <script type="text/javascript">
    $(function() {
        // run the currently selected effect
        function runEffect() {
            var clipEffect='blind';
            var options = {};
            $( "#effect" ).toggle( clipEffect, options, 1000 );
        };
        // set effect from select menu value
        $( "#add" ).button().click(function() {
            runEffect();
            return false;
        });
    });
        </script> 
        <script type="text/javascript">
    $(function() {
        $( "#add" )
        .button()
        .click(function() {
            runEffect();

        });
        $( "#submit" ).button();
        $( "#effect" ).hide();
    });
            
        </script>
                <script type="text/javascript">
            $(function() {
                $( "#tabs" ).tabs();
                
                $("#amountpaying").keypress(function (e) {
       		     //if the letter is not digit then display error and don't type anything
       		     if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
       		               return false;
       		    }
       		   });
                
                
                $("#fine").keypress(function (e) {
       		     //if the letter is not digit then display error and don't type anything
       		     if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
       		               return false;
       		    }
       		   });
            });

            function getCurrentDate(){
                var today = new Date();
                var day=today.getDate();
                var month=today.getMonth()+1;
                var year=today.getFullYear();
                if(month <10){
                    month="0"+month;

                }
                else {
                    month=month;
                }
                if(day <10){
                    day="0"+day;

                }
                else {
                    day=day;
                }
                return month+"/"+day+"/"+year;

            }
            
            
            function getstampfees(){
            	var form1 = document.getElementById("form1");
        		form1.action = "/patriswamy/FeesCollection/StampFees";
        		form1.method = "POST";
        		form1.submit();
            }
            
            function trimNumber(s) {
            	  while (s.substr(0,1) == '0' && s.length>1) { s = s.substr(1,9999); }
            	  return s;
            	}
            
            function datetowords(){
            	
            	var wDays = ['first', 'second', 'third', 'fourth', 'fifth', 'sixth', '	seventh', 'eighth', 'ninth', 'tenth', 'eleventh', 'twelth', 'thirteenth', 'fourteenth', 'fifteenth', 'sixteenth', 'seventeenth', 'eighteenth', 'nineteenth', 'twentieth', 'twenty-first', 'twenty-second', 'twenty-third', 'twenty-fourth', 'twenty-fifth', 'twenty-sixth', 'twenty-seventh', 'twenty-eighth', 'twenty-ninth', 'thirtieth', 'thirty-first']

            	var wMonths = ['','January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December']
            	var wNumbers = ['zero','one', 'two', 'three', 'four', 'five', 'six', 'seven', 'eight', 'nine', 'ten', 'eleven', 'twelve', 'thirteen', 'fourteen', 'fifteen', 'sixteen', 'seventeen', 'eighteen', 'nineteen', 'twenty', 'twentyone']

            	var date = document.getElementById('dateofbirth').value.split("-");
            	var day = date[2] - 1;
//             	var months = trimNumber(date[1]);
            	var month = trimNumber(date[1]);
            	var year = date[0];

            	var x = year.charAt(0)
            	var xx = year.charAt(1)
            	var xxx = year.charAt(2)
            	var xxxx = year.charAt(3)


            	var a = parseInt(x + xx)
            	var b = parseInt(xxx)
            	var c = parseInt(xxxx)
            	console.log(wDays[day] + ' ' + wMonths[month] + ' ' + wNumbers[a] + ' ' + wNumbers[b] + ' ' + wNumbers[c]);
            	var dateinwords = wDays[day] + ' ' + wMonths[month] + ' ' + wNumbers[a] + ' ' + wNumbers[b] + ' ' + wNumbers[c];
            	document.getElementById('dateofbirthwords').value = dateinwords;
            	
            }
            
            function validateForm() {
    		    const graduated = document.getElementById('yes:studentstatus').checked;
    		    const leftout = document.getElementById('no:studentstatus').checked;

    		    if (!graduated && !leftout) {
    		      alert("Please select either 'Graduated' or 'Left Out'.");
    		      return false;
    		    }
    		    return true;
    		  }
            
        </script>
    </head>
      <%
//allow access only if session exists
String user = null;
if(session.getAttribute("userAuth") == null){
	response.sendRedirect("/patriswamy/UserProcess/sessionTimeOut");
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
        <form id="form1" action="/patriswamy/DocumentsProcess/generateTransferCertificate" method="post" onkeypress="if (event.keyCode == 92) datetowords();">
            <table  width="100%">
                <thead>
                    <tr>
                        <th colspan="3" class="headerTD" style="font-size: 15px;">Transfer Certificate Application</th>

                    </tr>
                </thead>
                <tbody>
                <tr>
                    <td style="width: 45%;font-weight: bold;font-size: 15px;color: #4B6A84">&nbsp;&nbsp;&nbsp;&nbsp; </td>
                    </tr>
                    <tr>
                    <td style="width: 45%;">&nbsp;&nbsp;&nbsp;&nbsp;<span style="width: 200%;font-weight: bold;font-size: 15px;color: #4B6A84"> GENERAL INFORMATION</span> </td>
                    </tr>
	                <tr>
                    <td style="width: 45%;font-weight: bold;font-size: 15px;color: #4B6A84">&nbsp;&nbsp;&nbsp;&nbsp; </td>
                    </tr>
                    <tr>
                    <td style="width: 45%">Select Student: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input  type="text" name="admno" id="admno" style="width: 200px" onfocusout="datetowords()"/> <input name="studentId" type="hidden" id="studentId" value="" /> </td>
                    <td > Student Name :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input  type="text" name="studentname" id="studentname" style="width: 200px" /></td>    
                    </tr>
                    
                     <tr>
                    <td style="width: 45%;font-weight: bold;font-size: 15px;color: #4B6A84">&nbsp;&nbsp;&nbsp;&nbsp; </td>
                    </tr>
                    <tr>
                    <td style="width: 45%;">&nbsp;&nbsp;&nbsp;&nbsp;<span style="width: 200%;font-weight: bold;font-size: 15px;color: #4B6A84"> PARENTS / GUARDIAN INFORMATION</span> </td>
                    </tr>
	                <tr>
                    <td style="width: 45%;font-weight: bold;font-size: 15px;color: #4B6A84">&nbsp;&nbsp;&nbsp;&nbsp; </td>
                    </tr>
                    <tr>
                    <td style="width: 45%">Father Name: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input  type="text" name="fathername" id="fathername" style="width: 200px" /></td>
                    <td >Mother Name:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input  type="text" name="mothername" id="mothername" style="width: 200px" readonly/></td>    
                    </tr>
                    
                    
                    <tr>
                    <td style="width: 45%;font-weight: bold;font-size: 15px;color: #4B6A84">&nbsp;&nbsp;&nbsp;&nbsp; </td>
                    </tr>
                    <tr>
                    <td style="width: 45%;">&nbsp;&nbsp;&nbsp;&nbsp;<span style="width: 200%;font-weight: bold;font-size: 15px;color: #4B6A84"> STUDENT PERSONAL INFORMATION</span> </td>
                    </tr>
                    </tbody>
            </table>
            <table>
	                <tr>
                    <td style="font-weight: bold;font-size: 10px;">&nbsp;&nbsp;&nbsp;&nbsp; </td>
                    </tr>
                    <tr>
                     <td >1.College Name:&nbsp;&nbsp;&nbsp;&nbsp;</td><td style="color:green;">  <select name="collegename"
									 id="collegename"
									style="width: 200px;border-radius: 4px;background: white;height: 28px;">
										<option selected></option>
										<option>PATRISWAMY SCIENCE PU COLLEGE, AURAD (B) - 585326</option>
										<option>SRI SATHYAM PU COLLEGE AURAD (B)-585326</option>
										<option>NALANDA COMPOSITE PRE-UNIVERSITY <br>COLLEGE AURAD (B)-585326</option>
								</select></td>
				  <td >2.College Code:&nbsp; </td><td><input  type="text" name="collegecode" id="collegecode" style="width: 200px"/></td>    
                   		</tr>
                   		 <tr>
                    <td><br></td>
                    </tr>
                   		<tr>
                   		 <td >3.Admission No: 
                    				</td><td>		<input  type="text" name="Admissionno" id="Admissionno" style="width: 200px" /></td>
                    <td >4.Date of Admission:</td><td> <input  type="text" name="dateofadmission" id="dateofadmission" style="width: 200px" /></td>
                   		</tr>
                   		 <tr>
                    <td><br></td>
                    </tr>
                   		 <tr>
                    <td >5.Student SATS No:</td><td> <input  type="text" name="sts" id="sts" style="width: 200px" /></td>
                    <td >6.Student Name:</td><td><input  type="text" name="studentname1" id="studentname1" style="width: 200px" /></td>        
                    </tr>
                     <tr>
                    <td><br></td>
                    </tr>
                   		<tr>	
                    <td >7.Sex: </td><td>		<input  type="text" name="gender" id="gender" style="width: 200px" /></td>
                    <td >8.Name of the Father:</td><td>  <input  type="text" name="fathername1" id="fathername1" style="width: 200px" /></td>
                    </tr>
                     <tr>
                    <td><br></td>
                    </tr>
                    <tr>
                    <td >9.Name of the Mother:</td><td><input  type="text" name="mothername1" id="mothername1" style="width: 200px"/></td>    
                    <td >10.Nationality:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td> <input  type="text" name="nationality" id="nationality" style="width: 200px"/></td>    
                    </tr>
                    <tr>
                    <td><br></td>
                    </tr>
                    <tr>
                    <td >11.Religion:</td><td> <input  type="text" name="religion" id="religion" style="width: 200px" /></td>
                    <td >12.Caste:&nbsp; </td><td><input  type="text" name="caste" id="caste" style="width: 200px" /></td>    
                    </tr>
                    <tr>
                    <td><br></td>
                    </tr>
                    
                    <tr>
                    <td>13.Category:&nbsp; </td><td><input  type="text" name="category" id="category" style="width: 200px" /></td>    
                    <td >14.Whether the student belongs to Schedule Cast,<br>
                     Scheduled Tribe, Nomadic Tribe or Semi Nomadic Tribe:&nbsp; </td><td><input  type="text" name="yesno" id="yesno" style="width: 200px;color:green;" /></td>    
                    </tr>
                     <tr>
                    <td><br></td>
                    </tr>
                    <tr>
                    <td >15(a).Date of Birth: </td><td> <input  type="text" name="dateofbirth" id="dateofbirth" style="width: 200px" /></td>
                    <td >15(b).Date of Birth (words):</td><td> <input  type="text" name="dateofbirthwords" id="dateofbirthwords" style="width: 200px"/></td>    
                    </tr>
                     <tr>
                    <td><br></td>
                    </tr>
                    <tr>
                    <td>16.Class in which the student was studied at <br>the time of 
                     leaving the institution (In words) </td>
                     <td><input  type="text" name="leavingclass" id="leavingclass" style="width: 200px" /></td>
                    </tr>
                     <tr>
                    <td><br></td>
                    </tr>
                     <tr>
                    <td >17(a)EXAMINATION: </td><td><input  type="text" name="firstsubject" id="firstsubject" style="width: 200px;color:green;" /></td>
                    <td >17(b)MONTH & YEAR:</td><td> <input  type="text" name="secondsubject" id="secondsubject" style="width: 200px;color:green;" /></td>    
                    </tr>
                     <tr>
                    <td><br></td>
                    </tr>
                    <tr>
                    <td >17(c)REG. NO: &nbsp;&nbsp;&nbsp;&nbsp;</td><td> <input  type="text" name="thirdsubject" id="thirdsubject" style="width: 200px;color:green;" /></td>
                    <td >17(d)RESULT:&nbsp;&nbsp;&nbsp;&nbsp; </td><td><input  type="text" name="Fourthsubject" id="Fourthsubject" style="width: 200px;color:green;" /></td>    
                    </tr>
                     <tr>
                    <td><br></td>
                    </tr>
                     <tr>
                    <td >18(a) Language Offered in Part-I: </td><td><input  type="text" name="partone" id="partone" style="width: 200px;color:green;" /></td>
                    <td >18(b) Optional Subjects Offered in Part-II:</td><td> <input  type="text" name="parttwo" id="parttwo" style="width: 200px;color:green;" /></td>    
                    </tr>
                     <tr>
                    <td><br></td>
                    </tr>
                    
                     <tr>
                    <td >19(a)Total No. of working days: &nbsp;&nbsp;&nbsp;&nbsp; </td><td><input  type="text" name="workingdays" id="workingdays" style="width: 200px;color:green;" /></td>
                    <td >19(b)No of days he/she was present:&nbsp;&nbsp;&nbsp;&nbsp;</td><td> <input  type="text" name="present" id="present" style="width: 200px;color:green;" /></td>    
                    </tr>
                     <tr>
                    <td><br></td>
                    </tr>
                    <tr>
                    <td >20.Last date of student attendance in the institution: </td><td><input  type="text" name="dateofleaving" id="dateofleaving" style="width: 200px" /></td>
                     <td >21.Date of Application for Certificate:&nbsp;&nbsp;&nbsp;&nbsp; </td><td><input  type="text" name="datecert" id="datecert" style="width: 200px;color:green;" /></td>    
                     </tr>
                    <tr>
                    <td><br></td>
                  
                    <tr>
                     <td > 22. Date of issue of the transfer certificate: </td><td><input  style="width: 200px" type="text" name="dateoftc" id="dateoftc"  value="<fmt:formatDate type="date" value="${now}" pattern="dd/MM/yyyy"/>" /></td>    
                   <!--  <td >Class in Which Pupil Studied Last In Words:</td><td> <input  type="text" name="classinword" id="classinword" style="width: 200px" /></td>     -->
                    <td >Reason for Leaving the School:&nbsp;&nbsp;&nbsp;&nbsp;</td><td> <input  type="text" name="reason" id="reason" style="width: 200px" /></td>    
                    </tr>
                     <tr>
                     <td >  23. Character & Conduct: </td><td><input  style="width: 200px" type="text" name="conduct" id="conduct"  /></td>    
                   <!--  <td >Class in Which Pupil Studied Last In Words:</td><td> <input  type="text" name="classinword" id="classinword" style="width: 200px" /></td>     -->
                    <td >Trust Name:&nbsp;&nbsp;&nbsp;&nbsp;</td><td style="color:green;">  <select name="trustname"
									 id="trustname"
									style="width: 200px;border-radius: 4px;background: white;height: 28px;">
										<option selected></option>
										<option>AVV TRUST®</option>
										<option>POONAM EDUCATIONAL INSTITUTION MUNGNAL®</option>
								</select></td>    
                    </tr>
                    <tr>
                    <td><br></td>
                    </tr>
                    
                     <tr>
                    <td><br></td>
                    </tr>
                    <tr>
                    	<td>Student Status: &nbsp;&nbsp;&nbsp;&nbsp;</td>
                    	
                    	<td  height="30">&nbsp;Graduated<input
								type="checkbox" value="passedout" name="studentadmissionstatus" id="yes:studentstatus"
								onclick="yesCheck(this.id);" />&nbsp; &nbsp;Left Out<input
								type="checkbox" value="leftout" name="studentadmissionstatus" id="no:studentstatus"
								onclick="noCheck(this.id)" />

							</td>
							</tr>
                    </table>
                    <tr>
                    <td><br></td>
                    </tr>
                    <tr>
						<td><br></td>
                    </tr>
                    <tr>
						<td><br></td>
                    </tr>
                    
                    <tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						
						<input type="submit" value="Generate Transfer Certificate" id="submit"/></td>
						<td></td>
                    </tr>
                     <tr>
						<td><br></td>
                    </tr>
                
            
            
            
            
            
        </form>

    </body>
</html>

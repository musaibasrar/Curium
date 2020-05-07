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
        <link rel="stylesheet" href="css/datePicker/jquery-ui-1.8.18.custom.css">
        <link rel="stylesheet" href="css/graph/jquery.jqplot.css">

        <link rel="stylesheet" href="css/datePicker/demos.css">
        <script type="text/javascript" src="js/datePicker/jquery-1.7.1.js"></script>
        <script type="text/javascript" src="js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
        <script type="text/javascript" src="js/datePicker/ui/jquery.ui.dialog.js"></script>
        <script type="text/javascript" src="js/datePicker/ui/jquery.ui.autocomplete.js"></script>
        <script type="text/javascript" src="js/datePicker/ui/jquery.ui.core.js"></script>
        <script type="text/javascript" src="js/datePicker/ui/jquery.ui.widget.js"></script>
        <script type="text/javascript" src="js/datePicker/ui/jquery.ui.datepicker.js"></script>
        <script type="text/javascript" src="js/datePicker/ui/jquery.ui.accordion.js"></script>
        <script type="text/javascript" src="js/datePicker/ui/sliderAccess.js"></script>
        <script type="text/javascript" src="js/datePicker/ui/jquery-ui-timepicker-addon.js"></script>
        <script  type="text/javascript" src="js/datePicker/ui/jquery.ui.position.js"></script>
        <script type="text/javascript" src="js/datePicker/ui/jquery.ui.mouse.js"></script>
        <script type="text/javascript" src="js/datePicker/ui/jquery.ui.draggable.js"></script>
        <script type="text/javascript" src="js/datePicker/ui/jquery.ui.resizable.js"></script>

        <script type="text/javascript" src="js/datePicker/ui/jquery.effects.slide.js"></script>
        <script type="text/javascript" src="js/datePicker/ui/jquery.effects.bounce.js"></script>
        <script type="text/javascript" src="js/datePicker/ui/jquery.effects.clip.js"></script>
        <script type="text/javascript" src="js/datePicker/ui/jquery.effects.transfer.js"></script>
        <script type="text/javascript" src="js/datePicker/ui/jquery.effects.blind.js"></script>
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
                background-image: url("images/ui-bg_diagonals-small_50_466580_40x40.png");
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
                value:'<c:out default="0" value="${parents.student.admissionnumber}" />',
                admissiondate:'<c:out default="0" value="${parents.student.admissiondate}" />',
                name:'<c:out default="0" value="${parents.student.name}" />',
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
       			  $( "#studentName").val( ui.item.name );
       			$( "#fathername").val( ui.item.fathername );
       			$( "#mothername").val( ui.item.mothername );
       			$( "#nationality").val( ui.item.nationality );
       			$( "#gender").val( ui.item.gender );
       			$( "#religion").val( ui.item.religion );
       			$( "#caste").val( ui.item.caste );
       			$( "#dateofbirth").val( ui.item.dateofbirth );
       			$( "#classandsec").val( ui.item.classandsec );
       			$( "#classadmitted").val( ui.item.classandsec );
                    /* $("#classandsec"+rowCount).val( ui.item.classandsec ); */
                    return true;
                }
            }).data( "autocomplete" )._renderItem = function( ul, item ) {
                return $( "<li></li>" )
                .data( "item.autocomplete", item )
                .append( "<a><b> " + item.value +" </b> </a>" )
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
        		form1.action = "Controller?process=FeesCollection&action=StampFees";
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
            
        </script>
    </head>
      <%
//allow access only if session exists
String user = null;
if(session.getAttribute("userAuth") == null){
	response.sendRedirect("Controller?process=UserProcess&action=sessionTimeOut");
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
        <form id="form1" action="Controller?process=DocumentsProcess&action=generateTransferCertificate" method="post" onkeypress="if (event.keyCode == 92) datetowords();">
            <div style="height: 28px">
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
                    <td style="width: 45%">Admission No: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input  type="text" name="admno" id="admno" style="width: 200px" onfocusout="datetowords()"/> <input name="studentId" type="hidden" id="studentId" value="" /> </td>
                    <td >Student Name:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input  type="text" name="studentName" id="studentName" style="width: 200px" readonly/></td>    
                        
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
	                <tr>
                    <td style="width: 45%;font-weight: bold;font-size: 15px;color: #4B6A84">&nbsp;&nbsp;&nbsp;&nbsp; </td>
                    </tr>
                    <tr>
                    <td style="width: 45%">Gender: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    						<input  type="text" name="gender" id="gender" style="width: 200px" /></td>
                    <td >Nationality:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input  type="text" name="nationality" id="nationality" style="width: 200px" readonly/></td>    
                    </tr>
                    <tr>
                    <td><br></td>
                    </tr>
                    <tr>
                    <td style="width: 45%">Religion:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input  type="text" name="religion" id="religion" style="width: 200px" /></td>
                    <td >Caste:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input  type="text" name="caste" id="caste" style="width: 200px" readonly/></td>    
                    </tr>
                    <tr>
                    <td><br></td>
                    </tr>
                    <tr>
                    <td style="width: 45%">Date of Birth: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input  type="text" name="dateofbirth" id="dateofbirth" style="width: 200px" /></td>
                    <td >Date of Birth (words):&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input  type="text" name="dateofbirthwords" id="dateofbirthwords" style="width: 200px" readonly/></td>    
                    </tr>
                    <tr>
                    <td><br></td>
                    </tr>
                    <tr>
                    <td style="width: 45%">Date of Admission: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input  type="text" name="dateofadmission" id="dateofadmission" style="width: 200px" /></td>
                    <td >Class Admitted In:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input  type="text" name="classadmitted" id="classadmitted" style="width: 200px" readonly/></td>    
                    </tr>
                    <tr>
                    <td><br></td>
                    </tr>
                    <tr>
                    <td style="width: 45%">Class Last Studied: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input  type="text" name="classandsec" id="classandsec" style="width: 200px" /></td>
                    <td >Date of issue of TC:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input  style="width: 200px" type="text" name="dateoftc" id="dateoftc"  value="<fmt:formatDate type="date" value="${now}" pattern="yyyy-MM-dd"/>" /></td>    
                    </tr>
                    <tr>
                    <td><br></td>
                    </tr>
                    <tr>
                    <td style="width: 45%">Reason For Leaving the school: &nbsp;&nbsp;&nbsp;&nbsp; <input  type="text" name="reason" id="reason" style="width: 200px" /></td>
                    <td >Passed Examination Details:&nbsp;&nbsp;&nbsp;&nbsp; <input  type="text" name="passedexam" id="passedexam" style="width: 200px" readonly/></td>    
                    </tr>
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
                </tbody>
            </table>
            
            
            
            
            
        </form>

    </body>
</html>

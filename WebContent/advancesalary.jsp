<%-- 
    Document   : Advance Salary
    Created on : Jul 24, 2012, 4:07:26 PM
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
        <title>Advance Salary</title>
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
                font-size: 16px;
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
            .labels {
					font-family: Tahoma;
					font-size: 15px;
					font-style: normal;
					text-transform: capitalize;
					color: #325F6D;
					text-align: left;
					vertical-align: middle;
					font-weight: bold;
				}
        </style>
        <script type="text/javascript">
            
            function selectAllRow(tableID){
                var table = document.getElementById(tableID);
                var rowCount = table.rows.length;
                if(rowCount==1){
                    var row = table.rows[0];
                    var chkbox = row.cells[0].childNodes[0];
                    chkbox.checked=false;
                    alert('No records to select');
                }
                for(var i=1; i<rowCount; i++) {
                    var row = table.rows[i];
                    var chkbox = row.cells[0].childNodes[0];
                    chkbox.checked=true;
                }
            }
           
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
           
            var staff = [
                            <c:forEach varStatus="status" items="${employeeList}" var="employeelist">{
                                value:'<c:out default="0" value="${employeelist.teachername}" />',
                                department:'<c:out default="0" value="${employeelist.department}" />',
                                designation:'<c:out default="0" value="${employeelist.designation}" />',
                                id:'<c:out default="0" value="${employeelist.tid}" />',
                                
                            }<c:if test="${!status.last}">,</c:if>
                            </c:forEach>
                        ];
            
        $(function() {
            $( "#staffname").autocomplete({
                source: staff,
                minLength: 1,
                change:function(event,ui){
                    $( "#staffid").val( ui.item.id );
                },
                focus: function( event, ui ) {
                    $( "#staffid").val( ui.item.id );
                    return true;
                },
                select: function( event, ui ) {
                    $( "#staffid").val( ui.item.id );
       			$( "#designation").val( ui.item.designation );
       			$( "#department").val( ui.item.department );
                    return true;
                }
            }).data( "autocomplete" )._renderItem = function( ul, item ) {
                return $( "<li></li>" )
                .data( "item.autocomplete", item )
                .append( "<a><b> " + item.value +" </b> </a>" )
                .appendTo( ul );
            };
            
            $("#dateadvance").datepicker({
    			changeYear : true,
    			changeMonth : true,
    			dateFormat: 'yy-mm-dd',
    			yearRange: "-50:+0"
    		});
    		$("#anim").change(function() {
    			$("#dateadvance").datepicker("option", "showAnim", $(this).val());
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
        /**
         *
         */
         
         $(function(){
            
             $('#chckHead').click(function () {
                 var length = $('.chcktb2:checked').length;
                 var trLength=$('.trClass').length;
                 if(length>0){
                     $('.chcktb2:checked').attr('checked', false);
                     this.checked=false;

                 }
                 else{
                     if (this.checked == false) {
                         $('.chcktb2:checked').attr('checked', false);
                     }
                     else {
                         $('.chcktb2:not(:checked)').attr('checked', true);
                     }

                 }

             });
             $('.chcktb2').click(function () {
                 var length = $('.chcktb2:checked').length;
                 var trLength=$('.trClass').length;
                 alert(tdLength);
                 if (length > trLength) {

                     $('.chcktb2:not(:checked)').attr('disabled', true);
                 }
                 else {
                     $('.chcktb2:not(:checked)').attr('disabled', false);
                 }
             });

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
        $( "#save" ).button() .click(function() {
            saveAdvanceSalary();

        });;
        $( "#effect" ).hide();
    });
            
        </script>
                <script type="text/javascript">
            $(function() {
                $( "#tabs" ).tabs();
                
                $("#salaryforday").keypress(function (e) {
       		     //if the letter is not digit then display error and don't type anything
       		     if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
       		               return false;
       		    }
       		   });
                
                $("#deductionpermonth").keypress(function (e) {
          		     //if the letter is not digit then display error and don't type anything
          		     if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
          		               return false;
          		    }
          		   });
                
                
                $("#amount").keypress(function (e) {
       		     //if the letter is not digit then display error and don't type anything
       		     if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
       		               return false;
       		    }
       		   });
            });
            
            function saveAdvanceSalary(){
            	var form1 = document.getElementById("form1");
        		form1.action = "Controller?process=HrProcess&action=saveAdvanceSalary";
        		form1.method = "POST";
        		form1.submit();
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
    <body background="images/bg.jpg">
    <jsp:useBean id="now" class="java.util.Date" scope="page" />
        <form id="form1" method="post">
            <div style="height: 28px">
                <!--<a href="#" id="button" class="ui-state-default ui-corner-all">Add Medicine</a>-->

               
            <table  width="100%">
                <thead>
                    <tr>
                        <th colspan="3" class="headerTD"> Advance Salary</th>

                    </tr>
                </thead>
                <tbody>
                	<tr>
						<td><br></td>
                    </tr>
                    <tr>
						<td><br></td>
                    </tr>
                    <tr>
                    <td style="width: 45%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label class="labels">Staff Name:</label>&nbsp;&nbsp;&nbsp;&nbsp;<input  type="text" name="staffname" id="staffname" style="width: 200px" /> <input name="staffid" type="hidden" id="staffid" value="" /> </td>
                        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        
                        <label class="labels">Date:</label> &nbsp;&nbsp;<input
									name="dateadvance" type="text" class="textField"
									id="dateadvance" size="25" value="<fmt:formatDate type="date" value="${now}" pattern="yyyy-MM-dd"/>" data-validate="validate(required)"/></td>
                    </tr>
                    <tr>
						<td><br></td>
                    </tr>
                    <tr>
                        <td style="width: 45%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label class="labels">Department:</label>&nbsp;&nbsp;&nbsp;&nbsp;<input  type="text" name="department" id="department" style="width: 200px" readonly/></td>
                        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label class="labels">Designation: </label>&nbsp;&nbsp;<input type="text" style="width: 200px" name="designation" id="designation" readonly/></td>
                    </tr>
                    <tr>
						<td><br></td>
                    </tr>
                    <tr>
                    <td style="width: 45%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label class="labels">Year:</label>&nbsp;&nbsp;&nbsp;&nbsp; 
                    <select name="year" id="year"
									style="width: 200px">
										<option selected></option>
										<option>2018</option>
										<option>2019</option>
										<option>2020</option>
										<option>2021</option>
								</select>
					</td>
                        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <label class="labels">Month:</label>&nbsp;&nbsp;&nbsp; 
                        
                        	<select name="month" id="month"
									style="width: 200px">
										<option selected></option>
										<option>January</option>
										<option>February</option>
										<option>March</option>
										<option>April</option>
										<option>May</option>
										<option>June</option>
										<option>July</option>
										<option>August</option>
										<option>September</option>
										<option>October</option>
										<option>November</option>
										<option>December</option>
								</select>
                        
                        </td>
                    </tr>
                    <tr>
						<td><br></td>
                    </tr>
                    <tr>
                        <td style="width: 45%">&nbsp;&nbsp;<label class="labels">Salary for the days:</label>&nbsp;&nbsp;&nbsp;&nbsp; <input  type="text" name="salaryforday" id="salaryforday" style="width: 200px"/></td>
                        <td>&nbsp;&nbsp;&nbsp;&nbsp;<label class="labels">Deduction Per Month: </label>&nbsp;&nbsp;&nbsp;<input type="text" style="width: 200px" name="deductionpermonth" id="deductionpermonth"/></td>
                    </tr>
                    <tr>
						<td><br></td>
                    </tr>
                    <tr>
                        <td style="width: 45%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <label class="labels">Amount:</label>&nbsp;&nbsp;&nbsp;&nbsp; <input  type="text" name="amount" id="amount" style="width: 200px"/></td>
                        <td><label class="labels">Deduction Start Month:</label> &nbsp;&nbsp;&nbsp;
							<select name="deductionmonth" id="deductionmonth"
									style="width: 200px">
										<option selected></option>
										<option>January</option>
										<option>February</option>
										<option>March</option>
										<option>April</option>
										<option>May</option>
										<option>June</option>
										<option>July</option>
										<option>August</option>
										<option>September</option>
										<option>October</option>
										<option>November</option>
										<option>December</option>
								</select>

						</td>
                    </tr>
                    <tr>
						<td><br></td>
                    </tr>
                    <tr>
                        <td style="width: 45%"><label class="labels">Deduction Start Year:</label>&nbsp;&nbsp;&nbsp;&nbsp; 
                        <select name="deductionyear" id="deductionyear"
									style="width: 200px">
										<option selected></option>
										<option>2018</option>
										<option>2019</option>
										<option>2020</option>
										<option>2021</option>
								</select>
                        
                        
                        </td>
                    </tr>
                    <tr>
						<td><br></td>
                    </tr>
                    <tr>
						<td><br></td>
                    </tr>
                </tbody>
            </table>
            
            
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="button" value="save" id="save"/>
            
        </form>

    </body>
</html>

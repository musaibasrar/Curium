<%--
    Document   : Generate Hall Ticket
    Created on : MAR 31, 2018, 7:07:28 PM
    Author     : Musaib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Generate Hall Ticket</title>
<link rel="stylesheet" href="css/datePicker/jquery-ui-1.8.18.custom.css">
<link rel="stylesheet" href="css/datePicker/demos.css">
<style type="text/css">
<!--
.divCSS {
	overflow: scroll;
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
.footerTD{
                border-radius:6px;
                background-color:#4b6a84;


                text-align: left;


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

<!--
.header {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	background-color: #4b6a84;
}

.table {
	background-color: #3399CC;
	text-align: center;
	width: auto;
}

.headerText {
	border-radius: 3px;
	width: 10px;
	font-family: Tahoma;
	font-size: 12px;
	background-color: #4b6a84;
	color: #FFFFFF;
	font-weight: normal;
	width: auto;
	height: 27px;
	vertical-align: text-top;
	text-align: center;
	background-image:
		url("images/ui-bg_diagonals-small_50_466580_40x40.png");
}

.dataText {
	border-radius: 3px;
	font-family: Tahoma;
	color: #4b6a84;
	font-size: 13px;
	letter-spacing: normal;
	text-align: center;
	background-color: #E3EFFF;
}

.dataTextInActive {
	border-radius: 3px;
	font-family: Tahoma;
	color: #4b6a84;
	font-size: 12px;
	font-weight: bold;
	letter-spacing: normal;
	text-align: center;
	background-color: #E3EFFF;
	text-decoration: none;
}

.dataTextActive {
	border-radius: 3px;
	font-family: Tahoma;
	color: #4b6a84;
	font-size: 12px;
	font-weight: bold;
	letter-spacing: normal;
	text-align: center;
	background-color: #E3EFFF;
	text-decoration: underline;
	cursor: pointer;
}

.dataTextHidden {
	font-family: Tahoma;
	color: #4b6a84;
	font-size: 13px;
	letter-spacing: normal;
	text-align: center;
	background-color: #E3EFFF;
}

.headerTD {
	border-radius: 6px;
	background-color: #4b6a84;
	background-image:
		url("images/ui-bg_diagonals-small_50_466580_40x40.png");
	color: #FFFFFF;
	font-family: Tahoma;
	font-size: 13px;
	text-transform: uppercase;
	text-align: center;
	font-weight: bold;
	height: 22px;
}
.transparent{
background-color:rgba(0, 0, 0, 0);
    color:#4B6A84;
    border: none;
    outline:none;
    height:30px;
    transition:height 1s;
    -webkit-transition:height 1s;
}
</style>
<style>
#button {
	
}
</style>
<link rel="stylesheet" href="css/validation/jquery.ketchup.css">
<script type="text/javascript" src="js/datePicker/jquery-1.7.1.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
<script type="text/javascript" language="javascript"
	src="js/dataTable/jquery.dataTables.js"></script>
<script type="text/javascript" src="js/datePicker/ui/jquery.ui.core.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/jquery.ui.widget.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="js/datePicker/ui/jquery.ui.tabs.js"></script>
<script type="text/javascript" src="js/datePicker/ui/sliderAccess.js"></script>

<script type="text/javascript"
	src="js/validation/jquery.ketchup.all.min.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/jquery.ui.button.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/jquery.ui.accordion.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/jquery.effects.core.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/jquery.ui.accordion.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/jquery.effects.slide.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/jquery.effects.bounce.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/jquery.effects.clip.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/jquery.effects.transfer.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/jquery.effects.blind.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/ScrollableGridPlugin.js"></script>
<script type="text/javascript" charset="utf-8">
	$(document).ready(function() {
		$('#myTable').dataTable({
			"sScrollY" : "380px",
			"bPaginate" : false,
			"bLengthChange" : false,
			"bFilter" : true,
			"bSort" : true,
			"bInfo" : false,
			"bAutoWidth" : false
		});
	});
</script>

<script type="text/javascript" src="js/datetimepicker_css.js"></script>
<script type="text/javascript">
	
	function search() {
		var form1 = document.getElementById("form1");
		form1.action = "Controller?process=ExamDetailsProcess&action=searchHallTicketDetails";
		form1.method = "POST";
		form1.submit();

	}
	
	function printPreview() {
		var form1 = document.getElementById("form1");
		form1.action = "Controller?process=ExamDetailsProcess&action=printPreviewHallTicket";
		form1.method = "POST";
		form1.submit();

	}
	
	$(function() {

		$("#tabs").tabs();
		$("#search").button().click(function() {
				search();	
		});
		
		$("#addSchedule").button({
	        icons: {
	            primary: "ui-icon-plus"
	        }
	    }).click(function() {
	    	addRow();
	    	 return false;
		});
		
		$("#effect").show();
		
	        $('#chckHead').click(function () {
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
	        $('.chcktbl').click(function () {
	            var length = $('.chcktbl:checked').length;
	            var trLength=$('.trClass').length;
	            alert(tdLength);
	            if (length > trLength) {

	                $('.chcktbl:not(:checked)').attr('disabled', true);
	            }
	            else {
	                $('.chcktbl:not(:checked)').attr('disabled', false);
	            }
	        });
	        

	});
	
	 $(function(){
         $("#print").button({
             icons:{
                 primary: "ui-icon-mail-open"
             }
         }).click(function(){
        	 if(confirm('Are you sure,you want to print?')){
        		 printPreview();	
         	}
             
             return false;
         });
     });
	 
	 
	 var students = [
		                <c:forEach varStatus="status" items="${studentList}" var="student">{
		                    value:'<c:out default="0" value="${student.admissionnumber}" />',
		                    name:'<c:out default="0" value="${student.name}" />',
		                    classandsec:'<c:out default="0" value="${student.classstudying}" />',
		                    id:'<c:out default="0" value="${student.sid}" />',
		                    
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
		           			  $( "#studentName").val( ui.item.name );
		           			$( "#classandsec").val( ui.item.classandsec );
		                        /* $("#classandsec"+rowCount).val( ui.item.classandsec ); */
		                        return true;
		                    }
		                }).data( "autocomplete" )._renderItem = function( ul, item ) {
		                    return $( "<li></li>" )
		                    .data( "item.autocomplete", item )
		                    .append( "<a><b> " + item.value +" </b> </a>" )
		                    .appendTo( ul );
		                };
		            });
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
	<form id="form1">
			

		<div id="effect" class="ui-widget-content ui-corner-all">
			
				
				<div id="tabs">
				<ul>
					<li><a href="#tabs-1">Hall Ticket</a></li>
				</ul>
				<div id="tabs-1">
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0" id="table1" style="display: block">

						<tr>
							<td><br /></td>

						</tr>	
						<tr>
							<td><br /></td>

						</tr>		

<tr>
<td><br/></td>
</tr>

	<tr>
							<td><br /></td>

						</tr>
                            <tr>

                                <td class="alignLeft" >
                                    
                                    Academic Year*&nbsp;&nbsp;&nbsp;&nbsp; 

                                
                                    <label>
                                        <label> <select name="academicyear" id="academicyear"
									style="width: 180px">
										<option selected value="${currentYear}">${currentYear}(Current Year)</option>
										<option value="2015/16">2015/16</option>
										<option value="2016/17">2016/17</option>
										<option value="2017/18">2017/18</option>
										<option value="2018/19">2018/19</option>
										<option value="2019/20">2019/20</option>
										
								</select>

							</label> 
                                    </label>

                                </td>


                            </tr>
                            
                            <tr>
							<td><br /></td>

						</tr>
						<tr>
							<td><br /></td>

						</tr>
									
						
						<tr>
						
						<td width="16%" class="alignLeft">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						Class &nbsp;&nbsp;&nbsp;&nbsp;
							 <label> <select name="class" id="class"
									style="width: 180px">
										<option selected>${selectedclass}</option>
										<c:forEach items="${classdetailslist}" var="classdetailslist">
										<c:if test="${(classdetailslist.classdetails != '')}">
											<option value="${classdetailslist.classdetails}" >
												<c:out value="${classdetailslist.classdetails}" />
											</option>
										</c:if>	
										</c:forEach>
								</select> 
							</label>
							&nbsp;&nbsp;&nbsp;&nbsp;
							OR
							&nbsp;&nbsp;&nbsp;&nbsp;
							
							Admission No: &nbsp;&nbsp;&nbsp;&nbsp;
							<label><input type="text" name="admno" id="admno" value="${selectedadmissionno}" style="width: 180px">
							<input type="hidden" name="classandsec" id="classandsec" value="${selectedclassandsec}" style="width: 180px">
							<input type="hidden" name="studentName" id="studentName" value="${selectedstudentname}" style="width: 180px">
							</label>
							
							</td>
						</tr>
						
						<tr>
							<td><br /></td>

						</tr>
						
						<tr>
							<td><br /></td>

						</tr>
								
								<tr>
						<td width="80%" class="alignLeft">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						Exam &nbsp;&nbsp;&nbsp;&nbsp;
							<label>
									<select name="exam" id="exam"
									style="width: 180px">
										<option selected>${selectedexam}</option>

										<c:forEach items="${examdetails}" var="listExam">

											<option value="${listExam.examname}">
												<c:out value="${listExam.examname}" />
											</option>


										</c:forEach>

								</select></td>
						</tr>
										
					

						<tr>
							<td><br /></td>

						</tr>
						<tr>
							<td><br /></td>
						</tr><tr>
							<td><br /></td>
						</tr>
						<tr>
							<td width="60%" class="alignLeft">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<button id="search">Search</button>
							</td>
						</tr>
						
					</table>
				</div>
			</div>
			</div>
		</div>

		<div style="overflow: scroll; height: 600px">
			<table width="100%">
				<tr>
					<td class="headerTD">Search Result </td>
				</tr>
			</table>
			<table width="100%" border="0" style="border-color: #4b6a84;"
				id="myTable">

				<thead>
					<tr>
						<!-- <th class="headerText"><input type="checkbox" id="chckHead" /></th> -->
						<th title="click to sort" class="headerText">Exam<img
							alt=" " style="position: relative; top: 4px;"
							src="css/dataTable/images/sort_both.png" /></th>
						<th title="click to sort" class="headerText">Class<img
							alt=" " style="position: relative; top: 4px;"
							src="css/dataTable/images/sort_both.png" /></th>
						<th title="click to sort" class="headerText">Subject<img
							alt=" " style="position: relative; top: 4px;"
							src="css/dataTable/images/sort_both.png" /></th>
							<th title="click to sort" class="headerText">Date<img
							alt=" " style="position: relative; top: 4px;"
							src="css/dataTable/images/sort_both.png" /></th>
						<th title="click to sort" class="headerText">Start Time<img
							alt=" " style="position: relative; top: 4px;"
							src="css/dataTable/images/sort_both.png" /></th>
						<th title="click to sort" class="headerText">End Time<img
							alt=" " style="position: relative; top: 4px;"
							src="css/dataTable/images/sort_both.png" />&nbsp;&nbsp;
						</th>

					</tr>
				</thead>

				<tbody>

					<c:forEach items="${examschedules}" var="examschedule">
						<tr style="border-color: #000000" border="1" cellpadding="1"
							cellspacing="1">
							<%-- <td class="dataText"><input type="checkbox" id="<c:out value="${examschedule.idexamschedule}"/>" class="chcktbl" name="idexamschedule" value="<c:out value="${examschedule.idexamschedule}"/>" /></td> --%>
							<td class="dataText"><input type="text" name="examname" class="transparent" value="<c:out value="${examschedule.examname}"/>" /></td>
							<td class="dataText"><input type="text" name="classes" class="transparent" value="<c:out value="${examschedule.classes}"/>" /></td>
							<td class="dataText"><input type="text" name="subject" class="transparent" value="<c:out value="${examschedule.subject}" />" /></td>
							<td class="dataText"><input type="text" name="date" class="transparent" value="<c:out value="${examschedule.date}" />" /></td>
							<td class="dataText"><input type="text" name="starttime" class="transparent" value="<c:out value="${examschedule.starttime}" />" /></td>
							<td class="dataText"><input type="text" name="endtime" class="transparent" value="<c:out value="${examschedule.endtime}" />" /></td>
						</tr>
					</c:forEach>




				</tbody>
				<tfoot><tr>
                            <td  class="footerTD" colspan="2" ><button id="print">Print Preview</button> 
                        </tr></tfoot>
			</table>

		</div>

	</form>

</body>
</html>

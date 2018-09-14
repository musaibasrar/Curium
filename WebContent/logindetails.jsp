<%--
    Document   : Login Details
    Created on : JUL 25, 2018, 5:52:28 PM
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
<title>Login Details</title>
<link rel="stylesheet" href="css/datePicker/jquery-ui-1.8.18.custom.css">
<link rel="stylesheet" href="css/datePicker/demos.css">
<link rel="stylesheet" href="css/font-awesome.css">
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
.alert-box {
	padding: 15px;
    margin-bottom: 20px;
    border: 1px solid transparent;
    border-radius: 4px;  
}

.success {
    color: #3c763d;
    background-color: #dff0d8;
    border-color: #d6e9c6;
    display: none;
}

.failure {
    color: #a94442;
    background-color: #f2dede;
    border-color: #ebccd1;
    display: none;
}

.update {
    color: #3c763d;
    background-color: #dff0d8;
    border-color: #d6e9c6;
    display: none;
}

.updatefailure {
    color: #a94442;
    background-color: #f2dede;
    border-color: #ebccd1;
    display: none;
}

.delete {
    color: #3c763d;
    background-color: #dff0d8;
    border-color: #d6e9c6;
    display: none;
}

.deletefailure {
    color: #a94442;
    background-color: #f2dede;
    border-color: #ebccd1;
    display: none;
}

.userexist{
    color: #a94442;
    background-color: #f2dede;
    border-color: #ebccd1;
    display: none;
}

.usercheck{
    color: #a94442;
    background-color: #f2dede;
    border-color: #ebccd1;
    display: none;
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
	src="js/datePicker/ui/jquery.ui.button.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/jquery.ui.accordion.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/jquery.effects.core.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/jquery.ui.accordion.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/jquery.effects.slide.js"></script>

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
<script type="text/javascript">
	function select(id, name) {
		var clipEffect = 'blind';
		var options = {};

		$("#effect").show();
	}
	
</script>
<script type="text/javascript">
	$(function() {
		// run the currently selected effect
		function runEffect() {

			var clipEffect = 'blind';
			var options = {};
			$("#effect").toggle(clipEffect, options, 1000);
		}
		;
		// set effect from select menu value
		$("#add").button().click(function() {
			runEffect();
			return false;
		});
	});
	
</script>
<script type="text/javascript">
	function addUsers() {
		var form1 = document.getElementById("form1");
		form1.action = "Controller?process=UserProcess&action=addUser";
		form1.method = "POST";
		form1.submit();

	}
	
	function deleteRecords() {
		var form1 = document.getElementById("form1");
		form1.action = "Controller?process=UserProcess&action=deleteMultipleUsers";
		form1.method = "POST";
		form1.submit();

	}
	
	function updateRecords() {
		var form1 = document.getElementById("form1");
		form1.action = "Controller?process=UserProcess&action=updateMultipleUsers";
		form1.method = "POST";
		form1.submit();

	}
	
	function pauseRecords() {
		var form1 = document.getElementById("form1");
		form1.action = "Controller?process=UserProcess&action=pauseAllUsers";
		form1.method = "POST";
		form1.submit();

	}
	
	function resumeRecords() {
		var form1 = document.getElementById("form1");
		form1.action = "Controller?process=UserProcess&action=resumeAllUsers";
		form1.method = "POST";
		form1.submit();

	}
	
	$(function() {

		$("#tabs").tabs();
		$("#save").button().click(function() {
			addUsers();
		});
		$("#effect").hide();

	});
	
	 $(function(){
         $("#delete").button({
             icons:{
                 primary: "ui-icon-trash"
             }
         }).click(function(){
             deleteRecords();
             return false;

         });
         $("#bulkstop").button({
             icons:{
                 primary: "ui-icon-stop"
             }
         }).click(function(){
             pauseRecords();
             return false;

         });
         $("#bulkstart").button({
             icons:{
                 primary: "ui-icon-play"
             }
         }).click(function(){
             resumeRecords();
             return false;

         });
         $("#update").button({
             icons:{
                 primary: "ui-icon-note"
             }
         }).click(function(){
             updateRecords();
             return false;

         });
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
</script>

		<script type="text/javascript">
					
					var usersave='<c:out default="" value="${usersave}"/>';
		            var userupdate='<c:out default="" value="${userupdate}"/>';
		            var userdelete='<c:out default="" value="${userdelete}"/>';
		            var userexist='<c:out default="" value="${userexist}"/>';
		            var usercheck ='<c:out default="" value="${usercheck}"/>';
		            
		            if(usersave == "true"){
		            	 $(function(){
		            		 $( "div.success" ).fadeIn( 800 ).delay( 4000 ).fadeOut( 1400 );
		            	 });
		            	 }else if(usersave == "false"){
		            	  $(function(){
		            		 $( "div.failure" ).fadeIn( 800 ).delay( 4000 ).fadeOut( 1400 );
		            		 });
		            	 }else if(userupdate == "true"){
		                   	 $(function(){
		                   		 $( "div.update" ).fadeIn( 800 ).delay( 4000 ).fadeOut( 1400 );
		                   	 });
		                   	 }else if(userupdate == "false"){
		                   	  $(function(){
		                   		 $( "div.updatefailure" ).fadeIn( 800 ).delay( 4000 ).fadeOut( 1400 );
		                   		 });
		                   	 }else if(userdelete == "true"){
			                   	 $(function(){
			                   		 $( "div.delete" ).fadeIn( 800 ).delay( 4000 ).fadeOut( 1400 );
			                   	 });
			                   	 }else if(userdelete == "false"){
			                   	  $(function(){
			                   		 $( "div.deletefailure" ).fadeIn( 800 ).delay( 4000 ).fadeOut( 1400 );
			                   		 });
			                   	 }else if(userexist == "true"){
				                   	  $(function(){
					                   		 $( "div.userexist" ).fadeIn( 800 ).delay( 4000 ).fadeOut( 1400 );
					                   		 });
					                   	 }else if(usercheck == "true"){
					                   		 $(function(){
						                   		 $( "div.usercheck" ).fadeIn( 800 ).delay( 4000 ).fadeOut( 1400 );
						                   		 });
					                   	 }
		            function viewPassword(rowNum)
		            {
		              var passwordInput = document.getElementById('updatepassword'+rowNum);
		              var passStatus = document.getElementById('pass-status'+rowNum);
		             
		              if (passwordInput.type == 'password'){
		                passwordInput.type='text';
		                passStatus.className='fa fa-eye-slash';
		                
		              }
		              else{
		                passwordInput.type='password';
		                passStatus.className='fa fa-eye';
		              }
		            }
            
		            function ValidatePassword() {
		                var password = document.getElementById("passwd").value;
		                var confirmPassword = document.getElementById("confirmpassword").value;
		                if (password != confirmPassword) {
		                    alert("Passwords do not match.");
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
	<form id="form1"
		action="Controller?process=DepartmentProcess&action=deleteMultiple" method="POST">
		<%
			java.text.DateFormat df = new java.text.SimpleDateFormat(
					"MM/dd/yyyy");
		%>

		<div class="alert-box success">User has been added successfully!!!</div>
		<div class="alert-box failure">Saving Failed, Unable to create new User!!!</div>
		
		<div class="alert-box update">User has been updated successfully!!!</div>
		<div class="alert-box updatefailure">Update Failed, Unable to update User!!!</div>
		
		<div class="alert-box delete">User has been deleted successfully!!!</div>
		<div class="alert-box deletefailure">Deletion Failed, Unable to delete User!!!</div>
		
		<div class="alert-box userexist">User already exists!!!</div>
		
		<div class="alert-box usercheck">Deletion Failed, User is mapped with student(s)!!!</div>
		
		
		<div style="height: 28px">
			<button id="add">Add Users</button>
			<br />
		</div>

		<div id="effect" class="ui-widget-content ui-corner-all">
			<div id="tabs">
				<ul>
					<li><a href="#tabs-1">User Details</a></li>

				</ul>
				<div id="tabs-1">
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0" id="table1" style="display: block">
						<tr>
							<td width="10%" class="alignRight">Center Code &nbsp;</td>
							<td width="70%">
								<label> 
							<select name="centercode" id="centercode" required
									style="width: 200px;">
										<option selected></option>
										<c:forEach items="${branchList}" var="branchlist">
											<option value="${branchlist.centercode}" >
												<c:out value="${branchlist.centercode} -- ${branchlist.centername}" />
											</option>
										</c:forEach>
								</select>
							</label> 		
							</td>
						</tr>
						

						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td width="10%" class="alignRight">User Name &nbsp;</td>
							<td width="70%"><label> <input id="username"
									name="username" type="text" class="textField" required size="30">

							</label></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td width="10%" class="alignRight">Password &nbsp;</td>
							<td width="70%"><label> <input id="passwd" 
									name="passwd" type="password" class="textField" required size="30">

							</label></td>
							
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td width="10%" class="alignRight">Confirm Password &nbsp;</td>
							<td width="70%"><label> <input id="confirmpassword" 
									name="confirmpassword" type="password" class="textField" required size="30"
									required>

							</label></td>
							
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td width="10%" class="alignRight">User Type &nbsp;</td>
							<td width="70%"><label> <select name="usertype" id="usertype" required>
										<option selected></option>
										<option value="admin">Admin</option>
										<option value="centerorganizer">Center Organizer</option>
								</select>

							</label></td>
							
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td width="10%" class="alignRight">Admission Process &nbsp;</td>
							<td width="70%"><label> 
							<select name="admissionprocess" id="admissionprocess" required
									>
										<option selected></option>
										<option value="1">Start</option>
										<option value="0">Stop</option>
							</select>
							</label></td>
							
						</tr>
						<tr>
							<td><br /></td>
						</tr>
					</table>
					<table id="table2" width="100%" border="0" align="center">
						<tr>
							<td align="center">
								<button id="save" onfocus="ValidatePassword()" onmouseover="ValidatePassword()">Save</button>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>

		<div style="overflow: scroll; height: 600px">
			<table width="100%">
				<tr>
					<td class="headerTD">Login Credentials</td>
				</tr>
			</table>
			<table width="100%" border="0" style="border-color: #4b6a84;"
				id="myTable">

				<thead>
					<tr>
						<th class="headerText"><input type="checkbox" id="chckHead" /></th>
						<th title="click to sort" class="headerText">Center Code<img
							alt=" " style="position: relative; top: 4px;"
							src="images/sort_both.png" /></th>
							<th title="click to sort" class="headerText">User Name&nbsp;<img
							alt=" " style="position: relative; top: 4px;"
							src="images/sort_both.png" /></th>
							<th title="click to sort" class="headerText">Password&nbsp;<img
							alt=" " style="position: relative; top: 4px;"
							src="images/sort_both.png" /></th>
							<th title="click to sort" class="headerText">User Type&nbsp;<img
							alt=" " style="position: relative; top: 4px;"
							src="images/sort_both.png" /></th>
							<th title="click to sort" class="headerText">Admission Process Status&nbsp;<img
							alt=" " style="position: relative; top: 4px;"
							src="images/sort_both.png" /></th>
							<!-- <th title="click to sort" class="headerText">Last Modified By&nbsp;<img
							alt=" " style="position: relative; top: 4px;"
							src="images/sort_both.png" /></th> -->
					</tr>
				</thead>

				<tbody>

					<c:forEach items="${loginlist}" var="loginlist" varStatus="status">

						<tr style="border-color: #000000" border="1" cellpadding="1" cellspacing="1">
                          <td class="dataText"><input type="checkbox" id = "<c:out value="${loginlist.lid}"/>" class = "chcktbl"  name="loginids"  value="<c:out value="${loginlist.lid}:${status.index}"/>"/></td>
						  <td class="dataText">
						  <select name="updatecentercode" id="updatecentercode"
									style="width: 180px;">
										<option selected value="${loginlist.branch.idbranch}">${loginlist.branch.centercode}</option>
										<c:forEach items="${branchList}" var="branchlist">
											<option value="${branchlist.idbranch}" >
												<c:out value="${branchlist.centercode} -- ${branchlist.centername}" />
											</option>
										</c:forEach>
								</select>
						  </td>	
						  <td class="dataText"><input type="text" style="background-color: #E3EFFF;border-style: none;color: #4B6A84;" value="<c:out value="${loginlist.username}" />" id="updateusername" name="updateusername"></td>
						  <td class="dataText"><input type="password" style="background-color: #E3EFFF;border-style: none;color: #4B6A84;" value="<c:out value="${loginlist.password}" />" id="updatepassword${status.index}" name="updatepassword"><i id="pass-status${status.index}" class="fa fa-eye" aria-hidden="true" onClick="viewPassword(${status.index})"></i></td>
						  <td class="dataText">
						  <select name="updateusertype" id="updateusertype"
									style="width: 180px;">
										<option selected value="${loginlist.usertype}">${loginlist.usertype}</option>
										<option value="admin">Admin</option>
										<option value="centerorganizer">Center Organizer</option>
								</select>
						  </td>	
						  <td class="dataText">
						  <select name="updateadmissionprocess" id="updateadmissionprocess"
									style="width: 180px;">
										<option selected value="${loginlist.addstudentflag}">
										<c:if test="${(loginlist.addstudentflag == '0')}">
											<c:out value="Stopped" />
										</c:if>
										<c:if test="${(loginlist.addstudentflag == '1')}">
											<c:out value="Started" />
										</c:if>
										</option>
										<option value="0">Stop</option>
										<option value="1">Start</option>
							</select>
						  </td>	
						  <%-- <td class="dataText"><input type="text" style="background-color: #E3EFFF;border-style: none;color: #4B6A84;" value="<c:out value="${loginlist.lastmodifiedby}" />" id="updatelasmodified" name="updatelasmodified" readonly></td> --%>
						  <%-- <input type="text" style="background-color: #E3EFFF;border-style: none;color: #4B6A84;" value="<c:out value="${loginlist.branch.centercode}" />" id="updatecentercode" name="updatecentercode"></td> --%>
						  
						</tr>
					</c:forEach>




				</tbody>
				<tfoot><tr>
                            <td  class="footerTD" colspan="2" ><button id="update">Update</button> 
                    		&nbsp;&nbsp;&nbsp;&nbsp;
                            <button id="delete">Delete</button>
                            &nbsp;&nbsp;&nbsp;&nbsp;
                            <button id="bulkstop">Bulk Stop</button>
                            &nbsp;&nbsp;&nbsp;&nbsp;
                            <button id="bulkstart">Bulk Start</button>
                            </td> 
                        </tr></tfoot>
			</table>

		</div>


	</form>

</body>
</html>

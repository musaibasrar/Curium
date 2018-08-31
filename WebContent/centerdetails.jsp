<%--
    Document   : Center Details
    Created on : JUL 17, 2018, 5:52:28 PM
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
<title>Center Details</title>
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

.centerduplicate {
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
	function addCenters() {
		var form1 = document.getElementById("form1");
		form1.action = "Controller?process=BranchProcess&action=addBranches";
		form1.method = "POST";
		form1.submit();

	}
	
	function deleteRecords() {
		var form1 = document.getElementById("form1");
		form1.action = "Controller?process=BranchProcess&action=deleteMultipleCenters";
		form1.method = "POST";
		form1.submit();

	}
	
	function updateRecords() {
		var form1 = document.getElementById("form1");
		form1.action = "Controller?process=BranchProcess&action=updateMultipleCenters";
		form1.method = "POST";
		form1.submit();

	}
	
	$(function() {

		$("#tabs").tabs();
		$("#save").button().click(function() {
			addCenters();
		});
		$("#effect").hide();

	});
	
	 $(function(){
         $("#delete").button({
             icons:{
                 primary: "ui-icon-trash"
             }
         }).click(function(){
        	 
        	 if(confirm('Are you sure, you want to delete the center?')){
        		 deleteRecords();
        	 }
             
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
         
         $("#centercode").keypress(function (e) {
		     //if the letter is not digit then display error and don't type anything
		     if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
		               return false;
		    }
		   });
               

     });
</script>

		<script type="text/javascript">
					
					var centersave='<c:out default="" value="${centersave}"/>';
		            var centerupdate='<c:out default="" value="${centerupdate}"/>';
		            var centerdelete='<c:out default="" value="${centerdelete}"/>';
		            var centerduplicate='<c:out default="" value="${centerduplicate}"/>';
		            
		            if(centersave == "true"){
		            	 $(function(){
		            		 $( "div.success" ).fadeIn( 800 ).delay( 2000 ).fadeOut( 1400 );
		            	 });
		            	 }else if(centersave == "false"){
		            	  $(function(){
		            		 $( "div.failure" ).fadeIn( 800 ).delay( 2000 ).fadeOut( 1400 );
		            		 });
		            	 }else if(centerupdate == "true"){
		                   	 $(function(){
		                   		 $( "div.update" ).fadeIn( 800 ).delay( 2000 ).fadeOut( 1400 );
		                   	 });
		                   	 }else if(centerupdate == "false"){
		                   	  $(function(){
		                   		 $( "div.updatefailure" ).fadeIn( 800 ).delay( 2000 ).fadeOut( 1400 );
		                   		 });
		                   	 }else if(centerdelete == "true"){
			                   	 $(function(){
			                   		 $( "div.delete" ).fadeIn( 800 ).delay( 2000 ).fadeOut( 1400 );
			                   	 });
			                   	 }else if(centerdelete == "false"){
			                   	  $(function(){
			                   		 $( "div.deletefailure" ).fadeIn( 800 ).delay( 2000 ).fadeOut( 1400 );
			                   		 });
			                   	 }
			                   	else if(centerduplicate == "true"){
				                   	  $(function(){
				                   		 $( "div.centerduplicate" ).fadeIn( 800 ).delay( 2000 ).fadeOut( 1400 );
				                   		 });
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

		<div class="alert-box success">Center has been added successfully!!!</div>
		<div class="alert-box failure">Saving Failed, Unable to create new center!!!</div>
		
		<div class="alert-box update">Center has been updated successfully!!!</div>
		<div class="alert-box updatefailure">Update Failed, Unable to update center!!!</div>
		
		<div class="alert-box delete">Center has been deleted successfully!!!</div>
		<div class="alert-box deletefailure">Deletion Failed, Unable to delete center!!!</div>
		
		<div class="alert-box centerduplicate">Saving Failed, Center Code/Name already exits!!!</div>
		
		
		<div style="height: 28px">
			<button id="add">Add Centers</button>
			<br />
		</div>

		<div id="effect" class="ui-widget-content ui-corner-all">
			<div id="tabs">
				<ul>
					<li><a href="#tabs-1">Center Details</a></li>

				</ul>
				<div id="tabs-1">
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0" id="table1" style="display: block">
						<tr>
							<td width="10%" class="alignRight">Center Code &nbsp;</td>
							<td width="70%"><label> <input id="centercode"
									name="centercode" type="text" class="textField" required size="30">

							</label></td>
						</tr>
						

						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td width="10%" class="alignRight">Center Name &nbsp;</td>
							<td width="70%"><label> <input id="centername"
									name="centername" type="text" class="textField" required size="30">

							</label></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td width="10%" class="alignRight">District Code &nbsp;</td>
							<td width="70%"><label> 
									<select name="districtcode" id="districtcode" required
									style="width: 200px;">
										<option selected></option>
										<c:forEach items="${districtsList}" var="districtslist">
										<c:if test="${(districtslist.districtcode != '')}">
											<option value="${districtslist.districtcode}" >
												<c:out value="${districtslist.districtcode} - ${districtslist.districtname}" />
											</option>
										</c:if>	
										</c:forEach>
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
								<button id="save">Save</button>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>

		<div style="overflow: scroll; height: 600px">
			<table width="100%">
				<tr>
					<td class="headerTD">All Centers</td>
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
							<th title="click to sort" class="headerText">Center Name&nbsp;<img
							alt=" " style="position: relative; top: 4px;"
							src="images/sort_both.png" /></th>
							<th title="click to sort" class="headerText">District Code&nbsp;<img
							alt=" " style="position: relative; top: 4px;"
							src="images/sort_both.png" /></th>
					</tr>
				</thead>

				<tbody>

					<c:forEach items="${branchList}" var="branchlist" varStatus="status">

						<tr style="border-color: #000000" border="1" cellpadding="1" cellspacing="1">
                          <td class="dataText"><input type="checkbox" id = "<c:out value="${branchlist.idbranch}"/>" class = "chcktbl"  name="branchids"  value="<c:out value="${branchlist.idbranch}:${status.index}"/>"/></td>
						  <td class="dataText"><label style="display: none;"><c:out value="${branchlist.centercode}" /></label><input type="text" style="background-color: #E3EFFF;border-style: none;color: #4B6A84;" value="<c:out value="${branchlist.centercode}" />" id="updatecentercode" name="updatecentercode"></td>
						  <td class="dataText"><label style="display: none;"><c:out value="${branchlist.centername}" /></label><input type="text" style="background-color: #E3EFFF;border-style: none;color: #4B6A84;" value="<c:out value="${branchlist.centername}" />" id="updatecentername" name="updatecentername"></td>
						  <td class="dataText">
						  <input type="text" style="background-color: #E3EFFF;border-style: none;color: #4B6A84;" value="<c:out value="${branchlist.districtcode}" />" id="updatedistcode" name="updatedistcode" readonly="readonly">
						</tr>
					</c:forEach>




				</tbody>
				<tfoot><tr>
                            <td  class="footerTD" colspan="2" ><button id="update">Update</button> 
                    		&nbsp;&nbsp;&nbsp;&nbsp;
                            <button id="delete">Delete</button>
                            </td> 
                        </tr></tfoot>
			</table>

		</div>


	</form>

</body>
</html>

<%--
    Document   : Add Suppliers
    Created on : Nov 23, 2020, 10:26:28 AM
    Author     : Musaib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Suppliers</title>
<link rel="stylesheet" href="css/datePicker/jquery-ui-1.8.18.custom.css">
<link rel="stylesheet" href="css/datePicker/demos.css">

<style type="text/css">

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
	font-size: 14px;
	font-style: normal;
	text-transform: capitalize;
	color: #325F6D;
	text-align: right;
	vertical-align: middle;
	font-weight: bold;
}

.alignRightFields {
	font-family: Tahoma;
	font-size: 11px;
	font-style: normal;
	text-transform: capitalize;
	color: #325F6D;
	text-align: left;
	vertical-align: middle;
	font-weight: bold;
}

.alignSearch {
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

.footerTD {
	border-radius: 6px;
	background-color: #4b6a84;
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

.button {
  background-color: #4CAF50; /* Green */
  border: none;
  color: white;
  padding: 8px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 12px;
  margin: 4px 2px;
  cursor: pointer;
  border-radius: 12px;
}

.buttonred {
  background-color: red; /* Green */
  border: none;
  color: white;
  padding: 8px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 12px;
  margin: 4px 2px;
  cursor: pointer;
  border-radius: 12px;
}


.dropdownlist{
	width: 220px;
	height:27px;
	border-radius: 5px;
	background-color: white;
}

.textfieldvalues{

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
	width: 220px;
	height: 25px;
	border-radius: 5px;
	background-color: white;
	 
}


</style>

<script type="text/javascript" src="js/datePicker/jquery-1.7.1.js"></script>
<script type="text/javascript" src="js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
<script type="text/javascript" src="js/dataTable/jquery.dataTables.js"></script>
<script type="text/javascript" src="js/datePicker/ui/jquery.ui.datepicker.js"></script>

<script type="text/javascript" charset="utf-8">
	$(document).ready(function() {
		$('#myTable').dataTable({
            "sScrollY": "380px",
            "bPaginate": true,
            "bLengthChange": false,
            "bFilter": true,
            "bSort": true,
            "bInfo": true,
            "bStateSave": false,
            "bProcessing": false,
            "bServerSide": false,
            "bAutoWidth": false,
            "iDisplayLength": 20000,
            "aoColumnDefs":[
                { 'bSortable': false, 'aTargets': [ 0 ] }
            ]
            
        });
	});
</script>

<script type="text/javascript" src="js/datetimepicker_css.js"></script>
<script type="text/javascript">


	$(function() {

		$("#tabs").tabs();

		$("#addSuppliers").button().click(function() {
			addSuppliers();
		});
		$("#effect").hide();
		
		$("#deletesuppliers").button({
            icons:{
                primary: "ui-icon-trash"
            }
        }).click(function(){
            deleteSuppliers();
            return false;

        });
        $("#updatesuppliers").button({
            icons:{
                primary: "ui-icon-note"
            }
        }).click(function(){
            updateSuppliers();
            return false;

        });
        
	});
	
	
	function addSuppliers(){
		var form1 = document.getElementById("form1");
		if(form1.checkValidity()) {
			form1.action = "Controller?process=MessSuppliersProcess&action=addSuppliers";
			form1.method = "POST";
			form1.submit();
		}
		
	}
	
	function updateSuppliers(){
		var form1 = document.getElementById("form1");
			form1.action = "Controller?process=MessSuppliersProcess&action=updateSuppliers";
			form1.method = "POST";
			form1.submit();	
	}
	
	function deleteSuppliers(){
			form1.action = "Controller?process=MessSuppliersProcess&action=deleteSuppliers";
			form1.method = "POST";
			form1.submit();	
	}
	
	$(function() {
		// run the currently selected effect
		function runEffect() {

			var clipEffect = 'blind';
			var options = {};
			$("#effect").toggle(clipEffect, options, 1000);
		}
		;
		// set effect from select menu value
		$("#add").button({
			icons : {
				primary : "ui-icon-arrowthick-1-s"
			}
		}).click(function() {
			runEffect();
			return false;
		});
		
	
	});
	
	
	function closediv(divid){
		var x = document.getElementById("div"+divid);
		  if (x.style.display === "none") {
		    x.style.display = "block";
		  } else {
		    x.style.display = "none";
		  }
		
	}

</script>


<script type="text/javascript">
					
					var suppliersave='<c:out default="" value="${suppliersave}"/>';
		            var suppliersupdate='<c:out default="" value="${suppliersupdate}"/>';
		            var suppliersdelete='<c:out default="" value="${suppliersdelete}"/>';
		            
		            if(suppliersave == "true"){
		            	 $(function(){
		            		 $( "div.success" ).fadeIn( 800 ).delay( 2000 );
		            	 });
		            	 }else if(suppliersave == "false"){
		            	  $(function(){
		            		 $( "div.failure" ).fadeIn( 800 ).delay( 2000 );
		            		 });
		            	 }else if(suppliersupdate == "true"){
		                   	 $(function(){
		                   		 $( "div.update" ).fadeIn( 800 ).delay( 2000 );
		                   	 });
		                   	 }else if(suppliersupdate == "false"){
		                   	  $(function(){
		                   		 $( "div.updatefailure" ).fadeIn( 800 ).delay( 2000 );
		                   		 });
		                   	 }else if(suppliersdelete == "true"){
			                   	 $(function(){
			                   		 $( "div.delete" ).fadeIn( 800 ).delay( 2000 );
			                   	 });
			                   	 }else if(suppliersdelete == "false"){
			                   	  $(function(){
			                   		 $( "div.deletefailure" ).fadeIn( 800 ).delay( 2000 );
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
	<form id="form1" method="post">
	
		<div class="alert-box success" id="div1">${suppliername} has been added successfully!!!&nbsp;&nbsp;&nbsp;<button class="button" id="1" onclick="closediv(this.id);">OK</button></div>
		<div class="alert-box failure" id="div2">Saving Failed, Unable to create new Supplier(s)!!!&nbsp;&nbsp;&nbsp;<button class="buttonred" id="2" onclick="closediv(this.id);">OK</button></div>
		
		<div class="alert-box update" id="div3">Supplier(s) has been updated successfully!!!&nbsp;&nbsp;&nbsp;<button  class="button"  id="3" onclick="closediv(this.id);">OK</button></div>
		<div class="alert-box updatefailure" id="div4">Update Failed, Unable to Update Supplier(s)!!!&nbsp;&nbsp;&nbsp;<button class="buttonred" id="4" onclick="closediv(this.id);">OK</button></div>
		
		<div class="alert-box delete" id="div5">Supplier(s) has been deleted successfully!!!&nbsp;&nbsp;&nbsp;<button class="button" id="5" onclick="closediv(this.id);">OK</button></div>
		<div class="alert-box deletefailure" id="div6">Deletion Failed, Unable to delete Supplier(s)!!!&nbsp;&nbsp;&nbsp;<button class="buttonred" id="6" onclick="closediv(this.id);">OK</button></div>
	
	<div style="height: 28px">
			<button id="add">Add Supplier</button>
			<br />
		</div>

		<div id="effect" class="ui-widget-content ui-corner-all">
			<div id="tabs">
				<ul>
					<li><a href="#tabs-1">Supplier Details</a></li>

				</ul>
				<div id="tabs-1">
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0" id="table1" style="display: block">
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td width="20%" class="alignRight">Name* &nbsp;</td>
							<td width="28%"><label> <input name="suppliername" required
									type="text" class="textfieldvalues" id="suppliername" style="font-size: 14px;">

							</label></td>
							<td width="20%" class="alignRight">Contact Number &nbsp;</td>
							<td width="28%"><label> <input name="contactnumber" 
									type="text" class="textfieldvalues" id="contactnumber" style="font-size: 14px;">

							</label></td>
						</tr>
							<tr>
							<td><br /></td>
							</tr>
							<tr>
							<td><br /></td>
							</tr>
							<tr>
							<td width="20%" class="alignRight">Pay To &nbsp;</td>
							<td width="28%"><label> <input name="payto" 
									type="text" class="textfieldvalues" id="payto" style="font-size: 14px;">

							</label></td>
							<td width="20%" class="alignRight">Address &nbsp;</td>
							<td width="28%"><label> <input name="address" 
									type="text" class="textfieldvalues" id="address" style="font-size: 14px;">

							</label></td>
							
						</tr>
							<tr>
							<td><br /></td>
							</tr>
							<tr>
							<td><br /></td>
							</tr>
							<tr>
								<td width="20%" class="alignRight">Bank Acc/no&nbsp;</td>
								<td width="28%"><label> <input name="bankaccountno" 
										type="text" class="textfieldvalues" id="bankaccountno" style="font-size: 14px;">
	
								</label></td>
								<td width="20%" class="alignRight">IFSC Code &nbsp;</td>
								<td width="28%"><label> <input name="ifsccode" 
										type="text" class="textfieldvalues" id="ifsccode" style="font-size: 14px;">
	
								</label></td>
						</tr>
							<tr>
							<td><br /></td>
							</tr>
							<tr>
							<td><br /></td>
							</tr>
					</table>
					<table id="table2" width="100%" border="0" align="center">
						<tr>
							<td align="center">
								<button id="addSuppliers">Add</button>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>

		<div style="overflow: scroll; height: 600px">
		<table width="100%">
                    <tr>
                        <td  class="headerTD">Suppliers List</td>
                    </tr>
                </table>
			<table   width="100%"  border="0" style="border-color:#4b6a84;"  id="myTable">

                    <thead>
                        <tr>
                            <th class="headerText"><input  type="checkbox" id = "chckHead" /></th>
                            <th title="click to sort" class="headerText">Name</th>
                            <th title="click to sort" class="headerText">Contact #</th>
                            <th title="click to sort" class="headerText">Pay To</th>
                            <th title="click to sort" class="headerText">Bank Acc/no</th>
                            <th title="click to sort" class="headerText">Ifsc Code</th>
                            <th title="click to sort" class="headerText">Address</th>
                        </tr>
                    </thead>

                    <tbody>
                        <c:forEach items="${messsupplierslist}" var="messsuppliers" varStatus="status">
											
                            <tr class="trClass" style="border-color:#000000" border="1"  cellpadding="1"  cellspacing="1" >
                                <td class="dataText"><input type="checkbox" id = "<c:out value="${messsuppliers.id}"/>" class = "chcktbl"  name="messsuppliersids"  value="<c:out value="${messsuppliers.id}"/>"/>
                                <input type="hidden" style="background-color: #E3EFFF;border-style: none;color: #4B6A84;" value="<c:out value="${messsuppliers.linkedledgerid}"/>" id="linkedledgerid" name="linkedledgerid_<c:out value="${messsuppliers.id}"/>">
                                </td>
                                <td class="dataText" style="text-transform:uppercase" >
                                <input type="text" style="background-color: #E3EFFF;border-style: none;color: #4B6A84;width:180px;" value="<c:out value="${messsuppliers.name}"/>" id="updatesuppliersname" name="updatesuppliersname_<c:out value="${messsuppliers.id}"/>"><label style="display: none;"><c:out value="${messsuppliers.name}"/></label>
                                </td>
                                <td class="dataText" style="text-transform:uppercase">
                                <input type="text" style="background-color: #E3EFFF;border-style: none;color: #4B6A84;width:90px;" value="<c:out value="${messsuppliers.contactnumber}"/>" id="updatesupplierscontactnumber" name="updatesupplierscontactnumber_<c:out value="${messsuppliers.id}"/>"><label style="display: none;"><c:out value="${messsuppliers.contactnumber}"/></label>
                                </td>
                                <td class="dataText" style="text-transform:uppercase">
                                <input type="text" style="background-color: #E3EFFF;border-style: none;color: #4B6A84;width:180px;" value="<c:out value="${messsuppliers.payto}"/>" id="updatepayto" name="updatepayto_<c:out value="${messsuppliers.id}"/>"><label style="display: none;"><c:out value="${messsuppliers.payto}"/></label>
                                </td>
                                <td class="dataText" style="text-transform:uppercase">
                                <input type="text" style="background-color: #E3EFFF;border-style: none;color: #4B6A84;width:120px;" value="<c:out value="${messsuppliers.bankaccountno}"/>" id="updatesuppliersbankaccountno" name="updatesuppliersbankaccountno_<c:out value="${messsuppliers.id}"/>"><label style="display: none;"><c:out value="${messsuppliers.bankaccountno}"/></label>
                                </td>
                                <td class="dataText" style="text-transform:uppercase">
                                <input type="text" style="background-color: #E3EFFF;border-style: none;color: #4B6A84;width:90px;" value="<c:out value="${messsuppliers.ifsccode}"/>" id="updatesuppliersifsccode" name="updatesuppliersifsccode_<c:out value="${messsuppliers.id}"/>"><label style="display: none;"><c:out value="${messsuppliers.ifsccode}"/></label>
                                </td>
								<td class="dataText" style="text-transform:uppercase">
                                <textarea cols="20" rows="4" style="background-color: #E3EFFF;border-style: none;color: #4B6A84;" value="<c:out value="${messsuppliers.address}"/>" id="updatesuppliersaddress" name="updatesuppliersaddress_<c:out value="${messsuppliers.id}"/>"><c:out value="${messsuppliers.address}"/></textarea>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                    <tfoot><tr>
                            <td  class="footerTD" colspan="2" >
                            		<button id="updatesuppliers">Update</button>&nbsp;&nbsp;&nbsp;&nbsp;
                           			<button id="deletesuppliers">Delete</button>
                           </td>
                        </tr>
                    </tfoot>
                </table>
			

		</div>


	</form>

</body>
</html>

<%--
    Document   : Books Details
    Created on : AUG 14, 2018, 5:52:28 PM
    Author     : Musaib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Books Details</title>
<style type="text/css" title="currentStyle">
            @import "css/dataTable/css/demo_page.css";
            @import "css/dataTable/css/jquery.dataTables.css";
        </style>
<link rel="stylesheet" href="css/datePicker/jquery-ui-1.8.18.custom.css">
<link rel="stylesheet" href="css/datePicker/demos.css">
<link rel="stylesheet" href="css/font-awesome.css">
<link rel="stylesheet" href="css/style.css">
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
	font-size: 16px;
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
                border-radius:3px;
                width: 10px;
                font-family: Tahoma;
                font-size: 12px;
                background-color: #4b6a84;
                color: #FFFFFF;
                font-weight: normal;
                width: auto ;
                height: 27px;
                vertical-align: middle;
                text-align: center;
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

.dataTextRight {
	border-radius: 3px;
	font-family: Tahoma;
	color: #4b6a84;
	font-size: 13px;
	letter-spacing: normal;
	text-align: right;
	background-color: white;
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


</style>
<style>
#button {
	
}
</style>

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
            "iDisplayLength": 2000,
            "aoColumnDefs":[
                { 'bSortable': false, 'aTargets': [ 0 ] }
            ]
            
        });
	});
</script>
<script type="text/javascript">
	function select(id, name) {
		var clipEffect = 'blind';
		var options = {};

		$("#effect").show();
	}
	
	
	function calculatediscount(){
		
		var discount = document.getElementById("discount").value;
		var totalbeforediscount = document.getElementById("price").value;
		
		var keyPressed = event.keyCode || event.charCode;
		
		document.getElementById("discount").onkeydown = function() {
		    var key = event.keyCode || event.charCode;
		    keyPressed = key;
		    if( key == 8 ){
		    	document.getElementById("price").value = totalbeforediscount;
		    	document.getElementById("discount").value = 0;
		    }
		};
		
		if ((keyPressed < 48 || keyPressed > 57) && (keyPressed < 96 || keyPressed > 105) ) {
			document.getElementById("priceafterdiscount").value = totalbeforediscount;
			document.getElementById("discount").value = '';
 		}else if ((keyPressed >= 48 && keyPressed <= 57) || (keyPressed >= 96 && keyPressed <= 105)){
				if(discount!='' && discount>0){
					var totalafterdiscount = totalbeforediscount - (totalbeforediscount*discount)/100;
					document.getElementById("priceafterdiscount").value = totalafterdiscount;	
				}else if(discount==0 || discount == ''){
					document.getElementById("priceafterdiscount").value = totalbeforediscount*100/(100-discount);
				}
 		}
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
		$("#add").button({
            icons:{
                primary: " ui-icon-arrowthick-1-s"
            }
        }).click(function() {
			runEffect();
			return false;
		});
		
		 $("#printbooks").button({
				icons : {
					primary : "ui-icon-print"
				}
			}).click(function() {
				printBooks();
			});
			
	});
	
</script>
<script type="text/javascript">

	function addBooks() {
		var form1 = document.getElementById("form1");
		form1.action = "Controller?process=OrderProcess&action=addBooks";
		form1.method = "POST";
		form1.submit();

	}
	
	function deleteRecords() {
		var form1 = document.getElementById("form1");
		form1.action = "Controller?process=OrderProcess&action=deleteMultipleBooks";
		form1.method = "POST";
		form1.submit();

	}
	
	function updateRecords() {
		var form1 = document.getElementById("form1");
		form1.action = "Controller?process=OrderProcess&action=updateBooks";
		form1.method = "POST";
		form1.submit();

	}
	
	function printBooks() {
		var form1 = document.getElementById("form1");
		form1.action = "Controller?process=OrderProcess&action=printbooks";
		form1.method = "POST";
		form1.submit();
	}
	
	$(function() {

		$("#tabs").tabs();
		$("#save").button({
            icons:{
                primary: "ui-icon-contact"
            }
        }).click(function() {
			addBooks();
		});
		$("#effect").hide();

	});
	
	 $(function(){
         $("#deletebooks").button({
             icons:{
                 primary: "ui-icon-trash"
             }
         }).click(function(){
             deleteRecords();
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
         
         $("#purchaseddate").datepicker({
 			changeYear : true,
 			changeMonth : true,
 			dateFormat: 'dd/mm/yy',
 			yearRange: "0:+2"
 		});
 		$("#anim").change(
 				function() {
 					$("#purchaseddate").datepicker("option", "showAnim",
 							$(this).val());
 				});
         
     });
	 
	 var xmlHttp;
	    var count;
	    function getAuthor() {

			var selected=document.getElementById('title').value;

			 if (typeof XMLHttpRequest != "undefined") {
				 xmlHttp = new XMLHttpRequest();
	            
	         } else if (window.ActiveXObject) {
	        	 xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	             
	         }
			xmlHttp.onreadystatechange = stateChanged;
			xmlHttp.open("GET", "AjaxController?process=BooksInfoProcess&action=getAuthor&title="+selected,true);
			xmlHttp.send(null);
		}
	    
		function stateChanged() {

			if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete") {
				document.getElementById("authorslist").innerHTML = xmlHttp.responseText;
	            document.getElementById("authorslist").style.display = '';
			}
		}
		function GetXmlHttpObject() {
			var xmlHttp = null;
			try {
				xmlHttp = new XMLHttpRequest();
			} catch (e) {
				try {
					xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
				} catch (e) {
					xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
				}
			}
			return xmlHttp;
		}
		
</script>

		<script type="text/javascript">
					
					var bookssave='<c:out default="" value="${bookssave}"/>';
		            var booksupdate='<c:out default="" value="${booksupdate}"/>';
		            var booksdelete='<c:out default="" value="${booksdelete}"/>';
		            
		            if(bookssave == "true"){
		            	 $(function(){
		            		 $( "div.success" ).fadeIn( 800 ).delay( 2000 ).fadeOut( 1400 );
		            	 });
		            	 }else if(bookssave == "false"){
		            	  $(function(){
		            		 $( "div.failure" ).fadeIn( 800 ).delay( 2000 ).fadeOut( 1400 );
		            		 });
		            	 }else if(booksupdate == "true"){
		                   	 $(function(){
		                   		 $( "div.update" ).fadeIn( 800 ).delay( 2000 ).fadeOut( 1400 );
		                   	 });
		                   	 }else if(booksupdate == "false"){
		                   	  $(function(){
		                   		 $( "div.updatefailure" ).fadeIn( 800 ).delay( 2000 ).fadeOut( 1400 );
		                   		 });
		                   	 }else if(booksdelete == "true"){
			                   	 $(function(){
			                   		 $( "div.delete" ).fadeIn( 800 ).delay( 2000 ).fadeOut( 1400 );
			                   	 });
			                   	 }else if(booksdelete == "false"){
			                   	  $(function(){
			                   		 $( "div.deletefailure" ).fadeIn( 800 ).delay( 2000 ).fadeOut( 1400 );
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

	<form id="form1" method="POST">
		<%
			java.text.DateFormat df = new java.text.SimpleDateFormat(
					"MM/dd/yyyy");
		%>
		<c:set var="itemTotal" value="${0}" />
		
		<div class="alert-box success">Book(s) has been added successfully!!!</div>
		<div class="alert-box failure">Saving Failed, Unable to create new Book(s)!!!</div>
		
		<div class="alert-box update">Book(s) has been updated successfully!!!</div>
		<div class="alert-box updatefailure">Update Failed, Unable to update Book(s)!!!</div>
		
		<div class="alert-box delete">Book(s) has been deleted successfully!!!</div>
		<div class="alert-box deletefailure">Deletion Failed, Unable to delete Book(s)!!!</div>
		
		
		<div style="height: 28px">
			<button id="add">Add Books</button>
			<br />
		</div>

		<div id="effect" class="ui-widget-content ui-corner-all">
			<div id="tabs">
				<ul>
					<li><a href="#tabs-1">Books Details</a></li>

				</ul>
				<div id="tabs-1">
					<table border="0" cellpadding="0" align="center"
						cellspacing="0" id="table1" style="width: auto;height: auto;">
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td class="alignRight">Book Title &nbsp;</td>
							<td><label> 
							
							<select name="title" id="title" onchange="getAuthor()" class="textfieldvalues"
									style="width: 225px;" required>
										<option selected></option>
										<c:forEach items="${booksinfolist}" var="booksinfo">
											<option value="${booksinfo.title}">
												<c:out value="${booksinfo.title}" />
											</option>
										</c:forEach>
								</select>
							</label></td>
							<td class="alignRight">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Author &nbsp;</td>
								<td id="authorslist"><label> <select name="author" id="author" class="textfieldvalues"
									style="width: 225px" >
									<option ></option>
								</select>
							</label>
							
							</td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						
						<tr>
							<td class="alignRight">Language&nbsp;</td>
							<td ><label> 
							
							 <select name="language" id="language"  class="textfieldvalues"
									style="width: 225px;" required>
										<option selected value="Urdu" >Urdu</option>
										<option value="English">English</option>
										<option value="Kannada">Kannada</option>
										<option value="Hindi">Hindi</option>
										<option value="Tamil">Tamil</option>
										<option value="Telegu">Telegu</option>
										<option value="Marathi">Marathi</option>
								</select>

							</label></td>
							
							<td class="alignRight">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Edition &nbsp;</td>
							<td ><label> <input id="edition"  class="textfieldvalues"
									name="edition" type="text" class="textField" required size="30">

							</label></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td class="alignRight">Price &nbsp;</td>
							<td ><label> <input id="price" class="textfieldvalues" onkeypress="return event.charCode >= 00 && event.charCode <=57"
									name="price" type="text" class="textField" required size="30"
									required>

							</label></td>
							
							<td class="alignRight">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Discount &nbsp;</td>
							<td><label> <input id="discount" class="textfieldvalues" onkeyup="calculatediscount()"
									name="discount" type="text"  required size="30">

							</label></td>
							
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						
						<tr>
						
						<td class="alignRight">Price after discount &nbsp;</td>
							<td ><label> <input id="priceafterdiscount" class="textfieldvalues" onkeypress="return event.charCode >= 00 && event.charCode <=57"
									name="priceafterdiscount" type="text" class="textField" required size="30"
									required>

							</label></td>
							
						<td class="alignRight">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Quantity &nbsp;</td>
							<td><label> <input id="quantity" class="textfieldvalues" onkeypress="return event.charCode >= 00 && event.charCode <=57"
									name="quantity" type="text" class="textField" required size="30">

							</label></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td class="alignRight">Purchased Date &nbsp;</td>
							<td ><label> <input
									name="purchaseddate" class="textfieldvalues" type="text" class="textField" 
									id="purchaseddate" size="30" value="<fmt:formatDate type="date" value="${now}" pattern="dd/MM/yyyy"/>" data-validate="validate(required)">

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
					<td class="headerTD">Books Details</td>
				</tr>
			</table>
			
			<table   width="100%"  border="0" style="border-color:#4b6a84;"  id="myTable">

                    <thead>
                        <tr>
                            <th class="headerText"><input  type="checkbox" id = "chckHead" /></th>
                            <th title="click to sort" class="headerText">Book Title</th>
                            <th title="click to sort" class="headerText">Language</th>
                            <th title="click to sort" class="headerText">Author&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                            <th title="click to sort" class="headerText">Quantity</th>
                            <th title="click to sort" class="headerText">Price/Book&nbsp;</th>
                            <th title="click to sort" class="headerText">Discount&nbsp;</th>
                            <th title="click to sort" class="headerText">Total Price&nbsp;</th>
                        </tr>
                    </thead>

                    <tbody>
                    
                        <c:forEach items="${bookslist}" var="books" varStatus="status">
											
                            <tr class="trClass" style="border-color:#000000" border="1"  cellpadding="1"  cellspacing="1" >
                                <td class="dataText"><input type="checkbox" id = "<c:out value="${books.id}"/>" class = "chcktbl"  name="booksids"  value="<c:out value="${books.id}:${status.index}"/>"/></td>
                                <td class="dataText"><input type="text" style="background-color: #E3EFFF;border-style: none;color: #4B6A84;" value="<c:out value="${books.title}"/>" id="updatetitle" name="updatetitle"><label style="display: none;"><c:out value="${books.title}"/></label></td>
                                <td class="dataText"><input type="text" style="background-color: #E3EFFF;border-style: none;color: #4B6A84;" value="<c:out value="${books.language}"/>" id="updatelanguage" name="updatelanguage"><label style="display: none;"><c:out value="${books.language}"/></label></td>
                                <td class="dataText"><input type="text" style="background-color: #E3EFFF;border-style: none;color: #4B6A84;" value="<c:out value="${books.author}"/>" id="updateauthor" name="updateauthor"><label style="display: none;"><c:out value="${books.author}"/></label></td>
                                <td class="dataText"><input type="text" style="background-color: #E3EFFF;border-style: none;color: #4B6A84;" value="<c:out value="${books.quantity}"/>" id="updatequantity" name="updatequantity" onkeypress="return event.charCode >= 00 && event.charCode <=57"><label style="display: none;"><c:out value="${books.quantity}"/></label></td>
                                <td class="dataText"><input type="text" style="background-color: #E3EFFF;border-style: none;color: #4B6A84;" value="<c:out value="${books.price}"/>" id="updateprice" name="updateprice" onkeypress="return event.charCode >= 00 && event.charCode <=57"><label style="display: none;"><c:out value="${books.price}"/></label></td>
                                <td class="dataText"><input type="text" style="background-color: #E3EFFF;border-style: none;color: #4B6A84;" value="<c:out value="${books.discount}"/>" id="updatediscount" name="updatediscount" onkeypress="return event.charCode >= 00 && event.charCode <=57"><label style="display: none;"><c:out value="${books.discount}"/></label></td>
                                <td class="dataText" style="text-align: right">
                                	<c:set var="itemTotal" value="${itemTotal + ((books.quantity * books.price) - (books.quantity * books.price)*books.discount/100)}" />
                                	<fmt:setLocale value="en_IN" scope="request"/>
									<fmt:formatNumber type="currency"  value="${(books.quantity * books.price) - (books.quantity * books.price)*books.discount/100}" />
                               </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                    <tfoot>
                    	<tr>
							<td class="dataTextRight" >
								<label style="color: #eb6000"><b>Grand Total:
							<fmt:setLocale value="en_IN" scope="request"/>
							<fmt:formatNumber type="currency"  value="${itemTotal}" /></b>
							</label> 
							</td>
						</tr>
                    
                    <tr>
                     		<td  class="footerTD" colspan="2" ><button id="update">Update</button> 
                    		&nbsp;&nbsp;&nbsp;&nbsp;
                           <button id="deletebooks">Delete</button>
                           &nbsp;&nbsp;&nbsp;&nbsp;
                           <button id="printbooks">Print</button> 
                           </td>
                    
                        </tr></tfoot>
                </table>
		</div>


	</form>

</body>
</html>

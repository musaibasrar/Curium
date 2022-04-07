<%--
    Document   : Place Order
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
<title>Place Order</title>
<style type="text/css" title="currentStyle">
            @import "css/dataTable/css/demo_page.css";
            @import "css/dataTable/css/jquery.dataTables.css";
        </style>
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
            "bSort": false,
            "bInfo": true,
            "bStateSave": false,
            "bProcessing": false,
            "bServerSide": false,
            "bAutoWidth": true,
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
	
	function confirmOrder() {
		var form1 = document.getElementById("form1");
		form1.action = "Controller?process=OrderProcess&action=confirmOrder";
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
		$("#orderdate").datepicker({
			changeYear : true,
			changeMonth : true,
			dateFormat: 'dd/mm/yy',
			yearRange: "-50:+0"
		});
		$("#anim").change(function() {
			$("#orderdate").datepicker("option", "showAnim", $(this).val());
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
         $("#placeorder").button({
             icons:{
                 primary: "ui-icon-note"
             }
         }).click(function(){
        	 
        	 $('#myTable').dataTable().fnFilter('');
        	 

        	 var chkds = $("input[name='booksids']:checkbox");
        	 
        	       		 
        	 if (chkds.is(":checked"))  {
        		 
        		 for (index = 0, len = chkds.length; index < len; ++index) {
         		 	var test = chkds[index].value;
         		 	var splits = test.split(":");
         		 	var ind = splits[1];
         		 	
         		 	if(chkds[index].checked){
         		 		var quantity = document.getElementById('quantity_'+ind).value;
         		 		if(quantity == 0){
         		 			alert('Quantity should be greater than 0');
         		 			return false;
         		 		}
         		 	}
 	        	}
        		 
            	 if(confirm('Are you sure,you want to place the order?')){
            		 confirmOrder();	
             	}
                 //return false;
        	 } else {
        		 alert('Select the book(s) to place the order');
        		 return false;
        	 }
        	 
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

	 
	 function calculatePrice(value){
		 var price = document.getElementById("price_"+value).value;
		 var quantity = document.getElementById("quantity_"+value).value;
		 var totalAmount = price*quantity;
		 document.getElementById("totalprice_"+value).value = totalAmount;
	 }
	 
	 
	 function checkboxcheck(value){
		 var quantity = document.getElementById("quantity_"+value).value;
	               
	        if(parseFloat(quantity)>0){
	      	  document.getElementById("booksid_"+value).checked = true;  
	        }else{
	      	  document.getElementById("booksid_"+value).checked = false;
	        }
			
	 }
	 
</script>

		<script type="text/javascript">
					
					var ordersave='<c:out default="" value="${ordersave}"/>';
		            var booksupdate='<c:out default="" value="${booksupdate}"/>';
		            var booksdelete='<c:out default="" value="${booksdelete}"/>';
		            
		            if(ordersave == "true"){
		            	 $(function(){
		            		 $( "div.success" ).fadeIn( 800 ).delay( 2000 ).fadeOut( 1400 );
		            	 });
		            	 }else if(ordersave == "false"){
		            	  $(function(){
		            		 $( "div.failure" ).fadeIn( 800 ).delay( 2000 ).fadeOut( 1400 );
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
		<jsp:useBean id="now" class="java.util.Date" scope="page" />
		<div class="alert-box success">Book(s) has been ordered successfully!!!</div>
		<div class="alert-box failure">Order Failed, Unable to order Book(s)!!!</div>
		
		<div style="overflow: scroll; height: 600px">
			<table width="100%">
				<tr>
					<td class="headerTD">Place Order</td>
				</tr>
			</table>
			
			<table   width="100%"  border="0" style="border-color:#4b6a84;"  id="myTable">

                    <thead>
                        <tr>
                            <th class="headerText" style="width: 10%;"><input  type="checkbox" id = "chckHead" /></th>
                            <th title="click to sort" class="headerText" style="width: 30%;">Book Title</th>
                            <th title="click to sort" class="headerText">Language&nbsp;&nbsp;</th>
                            <th title="click to sort" class="headerText" style="width: 20%;">Author</th>
                            <th title="click to sort" class="headerText">Price Per Book</th>
                            <th title="click to sort" class="headerText" style="width: 20%;">Quantity</th>
                            <th title="click to sort" class="headerText" style="width: 20%;">Total Price</th>
                        </tr>
                    </thead>

                    <tbody>
                        <c:forEach items="${bookslist}" var="books" varStatus="status">
											
                            <tr class="trClass" style="border-color:#000000" border="1"  cellpadding="1"  cellspacing="1" >
                                <td class="dataText" style="width: 10%;"><input type="checkbox" id = "booksid_${status.index}" class = "chcktbl"  name="booksids"  value="<c:out value="${books.id}:${status.index}"/>"/></td>
                                <td class="dataText" style="width: 30%;"><c:out value="${books.title}"/></td>
                                <td class="dataText"><c:out value="${books.language}"/></td>
                                <td class="dataText" style="width: 20%;"><c:out value="${books.author}"/></td>
                                <td class="dataText"><input type="hidden" style="background-color: #E3EFFF;border-style: none;color: #4B6A84;" value="<c:out value="${books.price}"/>" class="price" id="price_${status.index}" name="price" readonly="readonly"><c:out value="${books.price}"/></td>
                                <td class="dataText"><input type="text" style="background-color: #E3EFFF;border-style: none;color: #4B6A84;width: 20%;" value="0" class="quantity" id="quantity_${status.index}" name="quantity" onkeyup="calculatePrice(${status.index});checkboxcheck(${status.index});"></td>
                                <td class="dataText" style="text-transform:uppercase">
                                <input type="text" style="background-color: #E3EFFF;border-style: none;color: #4B6A84;width: 40%;" id="totalprice_${status.index}" name="totalprice">
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                    <tfoot>
                    <tr>
                     		<td  class="footerTD" colspan="2" >
                     		<label> <select name="centercode" id="centercode"
									style="width: 240px;" required>
										<c:forEach items="${branchList}" var="branchlist">
											<option value="${branchlist.centercode}:${branchlist.centername}" >
												<c:out value="${branchlist.centercode} -- ${branchlist.centername}" />
											</option>
										</c:forEach>
								</select>
							</label>
                     		&nbsp;&nbsp;&nbsp;&nbsp;
                     		<label> <input
									name="orderdate" type="text" class="textField" style="width: 200px;" 
									value="<fmt:formatDate type="date" value="${now}" pattern="dd/MM/yyyy"/>"
									id="orderdate" size="25"  data-validate="validate(required)"/>
							</label>
                     		&nbsp;&nbsp;&nbsp;&nbsp;
                     		<button id="placeorder">Place Order</button> 
                        </tr></tfoot>
                </table>
		</div>


	</form>

</body>
</html>
<%--
    Document   : View Diary
    Created on : Mar 09, 2018, 3:05:28 PM
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
<title>Dues List</title>
<link rel="stylesheet" href="/sac/css/datePicker/jquery-ui-1.8.18.custom.css">
<link rel="stylesheet" href="/sac/css/datePicker/demos.css">
<style type="text/css">
<!--
.divCSS {
	overflow: scroll;
	height: 100%;
	width: 100%;
}

.textfieldvaluesshorts{

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
	width: 80px;
	height: 25px;
	border-radius: 5px;
	background-color: white;
	 
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
		url("/images/ui-bg_diagonals-small_50_466580_40x40.png");
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
		url("/images/ui-bg_diagonals-small_50_466580_40x40.png");
	color: #FFFFFF;
	font-family: Tahoma;
	font-size: 13px;
	text-transform: uppercase;
	text-align: center;
	font-weight: bold;
	height: 22px;
}
</style>
<style>
#button {
	
}
</style>
<link rel="stylesheet" href="/sac/css/validation/jquery.ketchup.css">
<script type="text/javascript" src="/sac/js/datePicker/jquery-1.7.1.js"></script>
<script type="text/javascript"
	src="/sac/js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
<script type="text/javascript" language="javascript"
	src="/sac/js/dataTable/jquery.dataTables.js"></script>
<script type="text/javascript" src="/sac/js/datePicker/ui/jquery.ui.core.js"></script>
<script type="text/javascript"
	src="/sac/js/datePicker/ui/jquery.ui.widget.js"></script>
<script type="text/javascript"
	src="/sac/js/datePicker/ui/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="/sac/js/datePicker/ui/jquery.ui.tabs.js"></script>
<script type="text/javascript" src="/sac/js/datePicker/ui/sliderAccess.js"></script>

<script type="text/javascript"
	src="/sac/js/validation/jquery.ketchup.all.min.js"></script>
<script type="text/javascript"
	src="/sac/js/datePicker/ui/jquery.ui.button.js"></script>
<script type="text/javascript"
	src="/sac/js/datePicker/ui/jquery.ui.accordion.js"></script>
<script type="text/javascript"
	src="/sac/js/datePicker/ui/jquery.effects.core.js"></script>
<script type="text/javascript"
	src="/sac/js/datePicker/ui/jquery.ui.accordion.js"></script>
<script type="text/javascript"
	src="/sac/js/datePicker/ui/jquery.effects.slide.js"></script>
<script type="text/javascript"
	src="/sac/js/datePicker/ui/jquery.effects.bounce.js"></script>
<script type="text/javascript"
	src="/sac/js/datePicker/ui/jquery.effects.clip.js"></script>
<script type="text/javascript"
	src="/sac/js/datePicker/ui/jquery.effects.transfer.js"></script>
<script type="text/javascript"
	src="/sac/js/datePicker/ui/jquery.effects.blind.js"></script>
<script type="text/javascript"
	src="/sac/js/datePicker/ui/ScrollableGridPlugin.js"></script>
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
<script src="/sac/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/sac/js/datetimepicker_css.js"></script>
<link href="/sac/css/select2.min.css" rel="stylesheet" />
<script src="/sac/js/select2.min.js"></script>
<script type="text/javascript">

function generateDueReport() {
	var form1 = document.getElementById("form1");
	form1.action = "/sac/MessItemsMoveProcess/generateDueReport";
	form1.method = "POST";
	form1.submit();
}


$(function(){
 
 $("#generatereport").button({
     icons:{
         primary: "ui-icon-document"
     }
 }).click(function(){
	 generateDueReport();
     return false;

 });
 
 $("#print").button({
     icons:{
         primary: "ui-icon-print"
     }
 }).click(function(){
     printRecords();
     return false;

 });
 
 
});

$(function(){
	//chechbox
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

            function openPopup(id,date,studentName,branchreceiptnumber,due){
            	
        			   if (typeof XMLHttpRequest != "undefined") {
        				 xmlHttp = new XMLHttpRequest();
        	            
        	         } else if (window.ActiveXObject) {
        	        	 xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
        	             
        	         }
        			xmlHttp.onreadystatechange = stateChangedSSGroup;
        			xmlHttp.open("GET", "/sac/stockentry/dueMrvDetails?date="+date+"&studentName="+studentName+"&branchreceiptnumber="+branchreceiptnumber+"&due="+due+"",true);
        			xmlHttp.send(null);  
        			 document.getElementById("itemsGrandTotalAmount").value = due;
        			 document.getElementById("dueid").value = id;
        		
                $( "#dialog" ).dialog( "open" );
                
                //from here
            }
        </script>
                <script type="text/javascript">

          /*   $(function() {
                $( "#dialog" ).dialog({
                    autoOpen: false,
                    height: 400,
                    width: 600,
                    modal: true,
                    buttons: {
                        Cancel: function() {
                            $( this ).dialog( "close" );
                        }

                    }
                });
            }); */


//fom here
        </script>
     <script type="text/javascript">

          
            
            function stateChangedSSGroup() {

        		if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete") {
        			document.getElementById("dueMrvDetails").innerHTML = xmlHttp.responseText;
        		}
        	}
            
            function selectVendor(name,id){
                var vendorName=document.getElementById('vendorName');
                var vendorID=document.getElementById("vendorID");
                vendorName.value=name;
                vendorID.value=id;
                $( "#dialog" ).dialog( "close" );

            }
        </script>


        <script type="text/javascript">

            $(function() {
                $( "#dialog" ).dialog({
                    autoOpen: false,
                    height: 400,
                    width: 600,
                    modal: true,
                    buttons: {
                        Pay: function() {
                         	
                   		 generatebill(document.getElementById("itemsGrandNetDueAmount"),document.getElementById("dueid"));
                        		$( this ).dialog( "close" );
                 		   }
                }
            });
        });

              function generatebill(itemsGrandNetDueAmount,dueid){
            	  var dueAmountValue = '';
            	  var dueidValue = '';
            	  dueAmountValue = itemsGrandNetDueAmount.value;
            	  dueidValue = dueid.value;
            	var form1 = document.getElementById("form1");
        		form1.action="/sac/MessItemsMoveProcess/paydue?itemsGrandNetDueAmount="+dueAmountValue+"&dueid="+dueidValue+"";
        		form1.method = "POST";
        		form1.submit();
        		
            		}
        		
            

        </script>
        
        <script type="text/javascript">
	$(function() {
		$("#tabs").tabs();
		$("#effect").hide();
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
	$(function() {
		$("#transactiondatefrom").datepicker({
			changeYear : true,
			changeMonth : true,
			dateFormat: 'dd/mm/yy',
			yearRange: "-50:+0"
		});
		$("#anim").change(function() {
			$("#transactiondatefrom").datepicker("option", "showAnim", $(this).val());
		});
		$("#transactiondateto").datepicker({
			changeYear : true,
			changeMonth : true,
			dateFormat: 'dd/mm/yy',
			yearRange: "-50:+0"
		});
		$("#anim").change(function() {
			$("#transactiondateto").datepicker("option", "showAnim", $(this).val());
		});
	});
</script>
 
<script type="text/javascript">
$(function(){
    $("#save").button({
        icons:{
            primary: "ui-icon-trash"
        }
    }).click(function(){
        payDue();
        return false;

    });
});
    function payDue(){
        
        var form1=document.getElementById("form1");
        form1.action="/sac/MessItemsMoveProcess/paydue";
        form1.method = "POST";
       form1.submit();
        
    }

    </script>

</head>
  <%
//allow access only if session exists
String user = null;
if(session.getAttribute("userAuth") == null){
	response.sendRedirect("/sac/UserProcess/sessionTimeOut");
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
	
	<div style="height: 28px">
			<button id="add">Parameters</button>
			<br />
		</div>

		<div id="effect" class="ui-widget-content ui-corner-all">
			<div id="tabs">
				<ul>
					<li><a href="#tabs-1">Filter</a></li>

				</ul>
				<div id="tabs-1">
				
					<table style="margin-left: auto;margin-right: auto;">
					
						<tr>
							<td><br><br></td>
						</tr>
						
						<tr>
						<td class="alignRight">From Date&nbsp;</td>
							<td><label> <input type="text"  name="transactiondatefrom"
									class="textField" style="font-size: 14px; border-radius: 5px;"
									value="<fmt:formatDate type="date" value="${now}" pattern="dd/MM/yyyy"/>" 
									id="transactiondatefrom" autocomplete="false" required
									data-validate="validate(required)">
							</label></td>
							
							<td class="alignRight">To Date&nbsp;</td>
							<td><label> <input type="text"  name="transactiondateto"
									class="textField" style="font-size: 14px;border-radius: 5px;"
									value="<fmt:formatDate type="date" value="${now}" pattern="dd/MM/yyyy"/>" 
									id="transactiondateto" autocomplete="false" required
									data-validate="validate(required)">
							</label></td>
							
							</tr>
							<tr>
							<td><br /></td>
	
							</tr>
						
						<tr>
						
						<tr>
							<td class="alignRight">Student Name&nbsp;</td>
							<td ><label>
									<select name="issuedto"	id="issuedto" style="font-size: 24px;width: 190px;"  class="form-control select2" required>
										        	<option></option>
										        	<c:forEach items="${studentList}" var="student">
										        	
										        		<option value="${student.student.name}_${student.student.classstudying}_${student.fathersname}">${student.student.name}/${student.student.classstudying}/${student.fathersname}</option>
										        	</c:forEach>
										        </select>
							
							</label></td>
							
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
					</table>
					
						<div align="center">
						<p>
						<label><button id="generatereport">Generate Report</button></label></p>
						
									
					</div>
					
					</div>
				</div>
				
				
			</div>

	

		<div style="overflow: hidden">
			<table width="100%">
				<tr>
					<td class="headerTD">Unpaid Report</td>
				</tr>
			</table>
			<table width="100%" border="0" style="border-color: #4b6a84;"
				id="myTable">

				<thead>
					<tr>
						<th class="headerText"><input type="checkbox" id="chckHead" /></th>
						<th class="headerText">Sl No</th>
						<th title="click to sort" class="headerText">Date</th>
						<th title="click to sort" class="headerText">Student Name</th>
						<th title="click to sort" class="headerText">Branch Receipt Number&nbsp;</th>
						<th title="click to sort" class="headerText">Academic Year</th>
						<th title="click to sort" class="headerText">Due Amount&nbsp;</th>
						<th title="click to sort" class="headerText">Pay Due</th>
						
					</tr>
				</thead>

				<tbody>

					<c:forEach items="${dueslist}" var="dueslist" varStatus="status">

						<tr style="border-color: #000000" border="1" cellpadding="1"
							cellspacing="1">
							<td class="dataText"><input type="checkbox"
								id="<c:out value="${diary.id}"/>" class="chcktbl"
								name="id"
								value="<c:out value="${diary.id}"/>" /></td>
							<td class="dataText" align="center">${status.index + 1}</td>	
							<td class="dataText"><c:out value="${dueslist.date}" /></td>
							<td class="dataText"><c:out value="${dueslist.studentName}" /></td>
							<td class="dataText"><c:out value="${dueslist.branchreceiptnumber}" /></td>
							<td class="dataText"><c:out value="${dueslist.academicyear}" /></td>
							<td class="dataText"><c:out value="${dueslist.due}" /></td>
							<td class="dataText"><a href="#" onclick="openPopup('<c:out value="${dueslist.receiptnumber}"/>','<c:out value="${dueslist.date}"/>','<c:out value="${dueslist.studentName}"/>','<c:out value="${dueslist.branchreceiptnumber}"/>','<c:out value="${dueslist.due}"/>')">Pay Due</a></td>
						</tr>
					</c:forEach>




				</tbody>
				<tfoot>
					<tr>
						<td class="footerTD" colspan="2">
							</td>
							

					</tr>
				</tfoot>
			</table>

		</div>
                    
	</form>
<script>
    $('.select2').select2();
</script>
</body>
</html>

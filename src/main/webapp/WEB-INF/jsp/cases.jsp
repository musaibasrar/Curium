<%--
    Document   : cases
    Created on : Aug 16, 2022, 11:08:28 AM
    Author     : Musaib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cases</title>
<link rel="stylesheet" href="/sla/css/datePicker/jquery-ui-1.8.18.custom.css">
<link rel="stylesheet" href="/sla/css/datePicker/demos.css">
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
	background-image: url(/sla/images/close.JPG);
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
.formfield * {
  vertical-align: middle;
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
		url("/sla/images/ui-bg_diagonals-small_50_466580_40x40.png");
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
		url("/sla/images/ui-bg_diagonals-small_50_466580_40x40.png");
	color: #FFFFFF;
	font-family: Tahoma;
	font-size: 13px;
	text-transform: uppercase;
	text-align: center;
	font-weight: bold;
	height: 22px;
}
.footerTD{
                border-radius:6px;
                background-color:#4b6a84;


                text-align: left;


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
 .alignLeft {
				font-family: Tahoma;
				font-size: 14px;
				font-style: normal;
				text-transform: capitalize;
				color: #325F6D;
				text-align: left;
				vertical-align: middle;
				font-weight: bold;
			}           
</style>
<style>
#button {
	
}
</style>
<link rel="stylesheet" href="/sla/css/validation/jquery.ketchup.css">
<script type="text/javascript" src="/sla/js/datePicker/jquery-1.7.1.js"></script>
<script type="text/javascript"
	src="/sla/js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
<script type="text/javascript" language="javascript"
	src="/sla/js/dataTable/jquery.dataTables.js"></script>
<script type="text/javascript" src="/sla/js/datePicker/ui/jquery.ui.core.js"></script>
<script type="text/javascript"
	src="/sla/js/datePicker/ui/jquery.ui.widget.js"></script>
<script type="text/javascript"
	src="/sla/js/datePicker/ui/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="/sla/js/datePicker/ui/jquery.ui.tabs.js"></script>
<script type="text/javascript" src="/sla/js/datePicker/ui/sliderAccess.js"></script>

<script type="text/javascript"
	src="/sla/js/validation/jquery.ketchup.all.min.js"></script>
<script type="text/javascript"
	src="/sla/js/datePicker/ui/jquery.ui.button.js"></script>
<script type="text/javascript"
	src="/sla/js/datePicker/ui/jquery.ui.accordion.js"></script>
<script type="text/javascript"
	src="/sla/js/datePicker/ui/jquery.effects.core.js"></script>
<script type="text/javascript"
	src="/sla/js/datePicker/ui/jquery.ui.accordion.js"></script>
<script type="text/javascript"
	src="/sla/js/datePicker/ui/jquery.effects.slide.js"></script>
<script type="text/javascript"
	src="/sla/js/datePicker/ui/jquery.effects.bounce.js"></script>
<script type="text/javascript"
	src="/sla/js/datePicker/ui/jquery.effects.clip.js"></script>
<script type="text/javascript"
	src="/sla/js/datePicker/ui/jquery.effects.transfer.js"></script>
<script type="text/javascript"
	src="/sla/js/datePicker/ui/jquery.effects.blind.js"></script>
<script type="text/javascript"
	src="/sla/js/datePicker/ui/ScrollableGridPlugin.js"></script>
<script type="text/javascript" charset="utf-8">
	$(document).ready(function() {
		$('#myTable').dataTable({
			"sScrollY" : "380px",
			"bPaginate" : false,
			"bLengthChange" : false,
			"bFilter" : true,
			"bSort" : true,
			"bInfo" : true,
			"bAutoWidth" : false
		});
	});
</script>
<script type="text/javascript" src="/sla/js/datetimepicker_css.js"></script>


	<script type="text/javascript">
		function pendingCases() {
			var form1 = document.getElementById("form1");
			form1.action = "/sla/CasesProcess/pendingCases";
			form1.method = "POST";
			form1.submit();
		}
		
		function cancelCases() {
			var form1 = document.getElementById("form1");
			form1.action = "/sla/CasesProcess/cancelCases";
			form1.method = "POST";
			form1.submit();
		}
		
	</script>
	
	 <script type="text/javascript">
					
					var casesstatus = '<c:out default="" value="${casesstatus}"/>';
		            
		            if(casesstatus == "true"){
		            	 $(function(){
		            		 $( "div.success" ).fadeIn( 800 ).delay( 2000 );
		            		 $( "div.success" ).fadeOut("slow");
		            	 });
		            	 }else if(casesstatus == "false"){
		            	  $(function(){
		            		 $( "div.failure" ).fadeIn( 800 ).delay( 2000 );
		            		 $( "div.success" ).fadeOut("slow");
		            		 });
		            	 }
		            
		        	function closediv(divid){
		        		var x = document.getElementById("div"+divid);
		        		  if (x.style.display === "none") {
		        		    x.style.display = "block";
		        		    return false;
		        		  } else {
		        		    x.style.display = "none";
		        		    var form1 = document.getElementById("form1");
		        			form1.action = "/sla/CasesProcess/viewAllCases";
		        			form1.method = "POST";
		        			form1.submit();
		        		  }
		        	}
		        	
        </script>
        
<script type="text/javascript">
	
	$(function() {
		$("#tabs").tabs();
	});
	
	 $(function(){
         $("#pending").button({
             icons:{
                 primary: "ui-icon-circle-check"
             }
         }).click(function(){
        	 pendingCases();
         });
         
         $("#cancel").button({
             icons:{
                 primary: "ui-icon-cancel"
             }
         }).click(function(){
             cancelCases();

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
             if (length > trLength) {

                 $('.chcktbl:not(:checked)').attr('disabled', true);
             }
             else {
                 $('.chcktbl:not(:checked)').attr('disabled', false);
             }
         });
              
         
         $(".dispose").button({
             icons:{
                 primary: " ui-icon-calendar"
             }
         }).click(function(){
         	$( "#dialogdispose" ).dialog( "open" );
             return false;
         	});
         
           $(function() {
               $( "#dialogdispose" ).dialog({
                   autoOpen: false,
                   height: 300,
                   width: 300,
                   modal: true,
                   buttons: {
                       OK: function() {                              	
                    	   dispose(document.getElementById("disposedate"),document.getElementsByName("disposefiletaken"),document.getElementsByName("disposefiletakenby"),document.getElementById("disposefiletakenbyname"),document.getElementById("disposefiletakenbycontact"));
                           		$( this ).dialog( "close" );
                    		   }
                   }
               });
           });
           
           $(".noc").button({
               icons:{
                   primary: " ui-icon-locked"
               }
           }).click(function(){
           	$( "#dialognoc" ).dialog( "open" );
               return false;
           	});
           
           $(function() {
               $( "#dialognoc" ).dialog({
                   autoOpen: false,
                   height: 300,
                   width: 300,
                   modal: true,
                   buttons: {
                       OK: function() {                              	
                    	   noc(document.getElementById("nocdate"),document.getElementsByName("nocfiletaken"),document.getElementsByName("nocfiletakenby"),document.getElementById("nocfiletakenbyname"),document.getElementById("nocfiletakenbycontact"));
                           		$( this ).dialog( "close" );
                    		   }
                   }
               });
           });

     });
		
	 
	 
</script>
       

 <script type="text/javascript">

        
            function viewStudentDetails(sid,branchid){
                var form1=document.getElementById("form1");
               form1.action="/sla/StudentProcess/ViewDetails?id="+sid+"&urlbranchid="+branchid+"";
               form1.submit();
               
               //window.location.reload();
           }
            
        	function dispose(disposedate,disposefiletaken,disposefiletakenby,disposefiletakenbyname,disposefiletakenbycontact){
        		
          	  	var ddate = disposedate.value;
          	  	var fileTaken;
	          	  for (var checkbox of disposefiletaken)
	              {
	                  if (checkbox.checked) {
	                	  fileTaken=checkbox.value;
	                  }
	              }
          	    var fileTakeBy;
	          	  for (var checkbox of disposefiletakenby)
	              {
	                  if (checkbox.checked) {
	                	  fileTakeBy=checkbox.value;
	                  }
	              }
          	    var fileTakenByName = disposefiletakenbyname.value;
          	    var fileTakenByContact = disposefiletakenbycontact.value;
            	var form1 = document.getElementById("form1");
        		form1.action = "/sla/CasesProcess/addDispose?disposedate="+ddate+"&disposefiletaken="+fileTaken+"&disposefiletakenby="+fileTakeBy+"&disposefiletakenbyname="+fileTakenByName+"&disposefiletakenbycontact="+fileTakenByContact+"";
        		form1.method = "POST";
        		form1.submit();
            }
        	
			function noc(nocdate,nocfiletaken,nocfiletakenby,nocfiletakenbyname,nocfiletakenbycontact){
        		
          	  	var ddate = nocdate.value;
          	  	var fileTaken = nocfiletaken;
	          	  for (var checkbox of nocfiletaken)
	              {
	                  if (checkbox.checked) {
	                	  fileTaken=checkbox.value;
	                  }
	              }
          	    var fileTakeBy = nocfiletakenby;
	          	  for (var checkbox of nocfiletakenby)
	              {
	                  if (checkbox.checked) {
	                	  fileTakeBy=checkbox.value;
	                  }
	              }
          	    var fileTakenByName = nocfiletakenbyname.value;
          	    var fileTakenByContact = nocfiletakenbycontact.value;
            	var form1 = document.getElementById("form1");
        		form1.action = "/sla/CasesProcess/addNOC?nocdate="+ddate+"&nocfiletaken="+fileTaken+"&nocfiletakenby="+fileTakeBy+"&nocfiletakenbyname="+fileTakenByName+"&nocfiletakenbycontact="+fileTakenByContact+"";
        		form1.method = "POST";
        		form1.submit();
            }
        	
        	
            function check(studentid){
            	
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
                }
      	      	  document.getElementById(studentid).checked = true;  
            }
            
            function yesCheck(id) {

        		if (document.getElementById(id).checked == true) {
        			var splitId = id.split(':');
        			document.getElementById('no:'+splitId[1]).checked = false;
        			document.getElementById("otherfiletaken").style.display = '';
        		}

        	}
        	function noCheck(id) {

        		if (document.getElementById(id).checked == true) {
        			var splitId = id.split(':');
        			document.getElementById('yes:'+splitId[1]).checked = false;
        			document.getElementById("otherfiletaken").style.display = "none";
        			document.getElementById("otherfilename").style.display = "none";
         			document.getElementById("otherfilecontact").style.display = "none";
        		}

        	}
        	
        	 function yesChecktf(id) {

         		if (document.getElementById(id).checked == true) {
         			var splitId = id.split(':');
         			document.getElementById('no:'+splitId[1]).checked = false;
         			document.getElementById("otherfilename").style.display = "none";
         			document.getElementById("otherfilecontact").style.display = "none";
         		}
         		
         	}
        	 
         	function noChecktf(id) {

         		if (document.getElementById(id).checked == true) {
         			var splitId = id.split(':');
         			document.getElementById('yes:'+splitId[1]).checked = false;
         			document.getElementById("otherfilename").style.display = '';
         			document.getElementById("otherfilecontact").style.display = '';
         		}

         	}
         	
         	function yesChecknoc(id) {

        		if (document.getElementById(id).checked == true) {
        			var splitId = id.split(':');
        			document.getElementById('no:'+splitId[1]).checked = false;
        			document.getElementById("nocotherfiletaken").style.display = '';
        		}

        	}
        	function noChecknoc(id) {

        		if (document.getElementById(id).checked == true) {
        			var splitId = id.split(':');
        			document.getElementById('yes:'+splitId[1]).checked = false;
        			document.getElementById("nocotherfiletaken").style.display = "none";
        			document.getElementById("nocotherfilename").style.display = "none";
         			document.getElementById("nocotherfilecontact").style.display = "none";
        		}

        	}
        	
        	 function yesChecktfnoc(id) {

         		if (document.getElementById(id).checked == true) {
         			var splitId = id.split(':');
         			document.getElementById('no:'+splitId[1]).checked = false;
         			document.getElementById("nocotherfilename").style.display = "none";
         			document.getElementById("nocotherfilecontact").style.display = "none";
         		}
         		
         	}
        	 
         	function noChecktfnoc(id) {

         		if (document.getElementById(id).checked == true) {
         			var splitId = id.split(':');
         			document.getElementById('yes:'+splitId[1]).checked = false;
         			document.getElementById("nocotherfilename").style.display = '';
         			document.getElementById("nocotherfilecontact").style.display = '';
         		}

         	}
        </script>

		<script type="text/javascript">

            function openPopup(referredby){
            	
        			 if (typeof XMLHttpRequest != "undefined") {
        				 xmlHttp = new XMLHttpRequest();
        	            
        	         } else if (window.ActiveXObject) {
        	        	 xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
        	             
        	         }
        			xmlHttp.onreadystatechange = stateChangedSSGroup;
        			xmlHttp.open("GET", "/sla/CasesProcess/viewReferredby?referredby="+referredby+"",true);;
        			xmlHttp.send(null);

        		
                $( "#dialogreferredbydetails" ).dialog( "open" );
            }
            
            function stateChangedSSGroup() {

        		if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete") {
        			document.getElementById("referredbydetails").innerHTML = xmlHttp.responseText;
        		}
        	}
        </script>
        
         <script type="text/javascript">

            $(function() {
                $( "#dialogreferredbydetails" ).dialog({
                    autoOpen: false,
                    height: 280,
                    width: 280,
                    modal: true,
                    buttons: {
                        Cancel: function() {
                            $( this ).dialog( "close" );
                        }

                    }
                });
            });



        </script>
        
</head>
  <%
//allow access only if session exists
String user = null;
if(session.getAttribute("userAuth") == null){
	response.sendRedirect("/sla/UserProcess/sessionTimeOut");
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


		<div class="alert-box success" id="div1">Case updated successfully!!!&nbsp;&nbsp;&nbsp;<button class="button" id="1" onclick="closediv(this.id);">OK</button></div>
		<div class="alert-box failure" id="div2">Case update failed, please try again!!!&nbsp;&nbsp;&nbsp;<button class="buttonred" id="2" onclick="closediv(this.id);">OK</button></div>
		
		<div style="overflow: scroll; height: 600px">
			<table width="100%">
				<tr>
					<td class="headerTD">Cases</td>
					
				</tr>
			</table>
			<table   width="100%"  border="0" style="border-color:#4b6a84;"  id="myTable">

                    <thead>
                        <tr  >
                            <th class="headerText"><input  type="checkbox" id = "chckHead" /></th>
                            <th title="click to sort" class="headerText">UID</th>
                            <th title="click to sort" class="headerText">Case Title</th>
                            <th title="click to sort" class="headerText">Case No</th>
                            <th title="click to sort" class="headerText">File No</th>
                            <th title="click to sort" class="headerText">Status</th>
                            <th title="click to sort" class="headerText">Court</th>
                            <th title="click to sort" class="headerText">Referred By</th>
                        </tr>
                    </thead>

                    <tbody>
                        <c:forEach items="${caseslist}" var="cases">
                            <tr class="trClass" style="border-color:#000000" border="1"  cellpadding="1"  cellspacing="1" >
                            	
                            	<td class="dataText"><input type="checkbox" id = "<c:out value="${cases.id}"/>" class = "chcktbl"  name="casesids"  value="<c:out value="${cases.id}"/>"/></td>
                            	<td class="dataText"><c:out value="${cases.id}"/><input type="hidden" id="username" name="username" value="${username}"> </td></td>
                            	<c:if test="${cases.status == 'Pending' }">
                                	<td class="dataText" style="color: #0001ff;font-weight: bold;text-transform: capitalize;">
                                	<a class="dataTextInActive" style="color: #0001ff;font-weight: bold;cursor: pointer;" onclick="viewStudentDetails(${cases.sid},${cases.branchid})">${cases.casetitle}</a>
                                	</td>
                                </c:if>
                                <c:if test="${cases.status == 'Disposed' }">
                                	<td class="dataText" style="color: green;font-weight: bold;text-transform: capitalize;">
                                		<a class="dataTextInActive" style="color: green;font-weight: bold;cursor: pointer;" onclick="viewStudentDetails(${cases.sid},${cases.branchid})">${cases.casetitle}</a>
                                	</td>
                                </c:if>
                                <c:if test="${cases.status == 'NOC' }">
                                	<td class="dataText" style="color: red;font-weight: bold;text-transform: capitalize;">
                                		<a class="dataTextInActive" style="color: red;font-weight: bold;cursor: pointer;" onclick="viewStudentDetails(${cases.sid},${cases.branchid})">${cases.casetitle}</a>
                                	</td>
                                </c:if>
                                <c:if test="${cases.status == 'Cancelled' }">
                                	<td class="dataText" style="color: grey;font-weight: bold;text-transform: capitalize;">
                                		<a class="dataTextInActive" style="color: grey;font-weight: bold;cursor: pointer;" onclick="viewStudentDetails(${cases.sid},${cases.branchid})">${cases.casetitle}</a>
                                	</td>
                                </c:if>
                               	<td class="dataText"><c:out value="${cases.casenumber}"/></td>
                               	<td class="dataText"><c:out value="${cases.fileno}"/></td>
                              	<td class="dataText"><c:out  value="${cases.status}"/></td>
                                <c:choose>
									<c:when test="${cases.court == 'DC'}">
										<td class="dataText"><c:out  value="${cases.courtname}"/></td>
									</c:when>
									<c:when test="${cases.court == 'RC'}">
										<td class="dataText"><c:out  value="${cases.courtname}"/></td>
									</c:when>
									<c:otherwise>
										<td class="dataText"><c:out  value="${cases.court}"/></td>
									</c:otherwise>
								</c:choose>
                              	<td class="dataText"><a href="#" onclick="openPopup('<c:out value="${cases.referredby}"/>')" style="color:#eb6000;">View Details</a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                    
                    <tfoot><tr>
                    
                    <td  class="footerTD" colspan="2" >
                            </td>
                   </tr></tfoot>
                </table>
                
                <table>
                	<tr>
                    	<td  colspan="2" ><button value="pending" id="pending">Pending</button>
                            &nbsp;&nbsp;&nbsp;&nbsp;<button class="dispose">Disposed</button>
                            &nbsp;&nbsp;&nbsp;&nbsp;<button class="noc">NOC</button> 
                            &nbsp;&nbsp;&nbsp;&nbsp;<button id="cancel">Cancel</button> 
                    	</td>
                   </tr>
                </table>
                
                <div align="center">
		             <%--For displaying Previous link except for the 1st page --%>
		                <c:if test="${currentPage != 1}">
		                    <td><a style="color: #4B6A84;font-size: 12px" href="/sla/CasesProcess/viewAllCases&page=${currentPage - 1}">Previous</a></td>
		                </c:if>
		
		                <%--For displaying Page numbers.
		                The when condition does not display a link for the current page--%>
		                <table border="0" cellpadding="2" cellspacing="2">
		                	<c:forEach begin="1" end="${noOfPages}" var="i" varStatus="status">
 								   
 								   <c:if test="${status.index % 30 == 1}">
 								   	 <tr>
 								   </c:if>
		                            <c:choose>
		                                <c:when test="${currentPage eq i}">
		                                    <td style="color: #1D599B;font-weight:bolder;font-size: 20px ">${i}</td>
		                                </c:when>
		                                <c:otherwise>
		                                    <td style="color: black;font-weight:bold;font-size: 15px "><a style="color: #4B6A84" href="/sla/CasesProcess/viewAllCases&page=${i}">${i}</a></td>
		                                </c:otherwise>
		                            </c:choose>
		                            
		                             <c:if test="${status.index % 30 == 0 || status.index == noOfPages}">
 								   	 </tr>
 								   </c:if>
		                            
		                    </c:forEach>
		                </table>
		
		                <%--For displaying Next link --%>
		                <c:if test="${currentPage lt noOfPages}">
		                    <td ><a style="color: #4B6A84;font-size: 12px" href="/sla/CasesProcess/viewAllCases&page=${currentPage + 1}">Next</a></td>
		                </c:if>
                </div>
		</div>
		
		<div id="dialogdispose" title="Dispose">
				
           		 
		           		<table style="width: auto;height: auto;">
								
								<tr>
									<td>
										<label style="font-size: 14px;padding-left: 10px;"> Date :</label>
										<input type="date" name="disposedate" id="disposedate"/>
									</td>
								</tr>
								
								<tr>
									<td><br></td>
								</tr>
								
								<tr>
									<td>
										<label style="font-size: 14px;padding-left: 10px;">Whether client has taken file:</label>
													Yes<input
													type="checkbox" value="yes" name="disposefiletaken" id="yes:ft"
													onclick="yesCheck(this.id);" />&nbsp; &nbsp;No<input
													type="checkbox" value="no" name="disposefiletaken" id="no:ft"
														onclick="noCheck(this.id)" />
									</td>
								</tr>
								
								<tr>
									<td><br></td>
								</tr>
								<tr style="display: none;" id="otherfiletaken">
									<td>
										<label style="font-size: 14px;padding-left: 10px;">Who has taken the file:</label>
										
													Client<input
													type="checkbox" value="client" name="disposefiletakenby" id="yes:tf"
													onclick="yesChecktf(this.id);" />&nbsp; &nbsp;Other<input
													type="checkbox" value="other" name="disposefiletakenby" id="no:tf"
														onclick="noChecktf(this.id)" />
									</td>
								</tr>
								
								<tr>
									<td><br></td>
								</tr>
								
								<tr style="display: none;" id="otherfilename">
									<td>
										<label style="font-size: 14px;padding-left: 10px;"> Name :</label>
										<input type="text" name="disposefiletakenbyname" id="disposefiletakenbyname"/>
									</td>
								</tr>
								
								<tr>
									<td><br></td>
								</tr>
								
								<tr style="display: none;" id="otherfilecontact">
									<td>
										<label style="font-size: 14px;padding-left: 10px;">Contact:</label>
										<input type="text" name="disposefiletakenbycontact" id="disposefiletakenbycontact"/>
									</td>
								</tr>
								
								<tr>
									<td><br></td>
								</tr>
						</table>
						
					</div>
					
					<div id="dialognoc" title="NOC">
				
           		 
		           		<table style="width: auto;height: auto;">
								
								<tr>
									<td>
										<label style="font-size: 14px;padding-left: 10px;"> Date :</label>
										&nbsp;&nbsp;&nbsp;<input type="date" name="nocdate" id="nocdate"/>
									</td>
								</tr>
								
								<tr>
									<td><br></td>
								</tr>
								
								<tr>
									<td>
										<label style="font-size: 14px;padding-left: 10px;">Whether client has taken file:</label>&nbsp;&nbsp;&nbsp;
													Yes<input
													type="checkbox" value="yes" name="nocfiletaken" id="yes:ftnoc"
													onclick="yesChecknoc(this.id);" />&nbsp; &nbsp;No<input
													type="checkbox" value="no" name="nocfiletaken" id="no:ftnoc"
														onclick="noChecknoc(this.id)" />
									</td>
								</tr>
								
								<tr>
									<td><br></td>
								</tr>
								<tr style="display: none;" id="nocotherfiletaken">
									<td>
										<label style="font-size: 14px;padding-left: 10px;">Who has taken the file:</label>&nbsp;&nbsp;&nbsp;
										
													Client<input
													type="checkbox" value="client" name="nocfiletakenby" id="yes:tfnoc"
													onclick="yesChecktfnoc(this.id);" />&nbsp; &nbsp;Other<input
													type="checkbox" value="other" name="nocfiletakenby" id="no:tfnoc"
														onclick="noChecktfnoc(this.id)" />
									</td>
								</tr>
								
								<tr>
									<td><br></td>
								</tr>
								
								<tr style="display: none;" id="nocotherfilename">
									<td>
										<label style="font-size: 14px;padding-left: 10px;"> Name :</label>&nbsp;&nbsp;&nbsp;
										<input type="text" name="nocfiletakenbyname" id="nocfiletakenbyname"/>
									</td>
								</tr>
								
								<tr>
									<td><br></td>
								</tr>
								
								<tr style="display: none;" id="nocotherfilecontact">
									<td>
										<label style="font-size: 14px;padding-left: 10px;">Contact:</label>&nbsp;&nbsp;&nbsp;
										<input type="text" name="nocfiletakenbycontact" id="nocfiletakenbycontact"/>
									</td>
								</tr>
								
								<tr>
									<td><br></td>
								</tr>
						</table>
						
					</div>
					
					<div id="dialogreferredbydetails" title="Referred By Details">
		             	 <div id="referredbydetails">
		           		 </div>
			</div>
					
	</form>
</body>
</html>

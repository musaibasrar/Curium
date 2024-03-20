<%--
    Document   : queries
    Created on : Dec 19, 2021, 07:08:28 AM
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
<title>Enquiries</title>
<link rel="stylesheet" href="/abc/css/datePicker/jquery-ui-1.8.18.custom.css">
<link rel="stylesheet" href="/abc/css/datePicker/demos.css">
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
	background-image: url(/abc/images/close.JPG);
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
		url("/abc/images/ui-bg_diagonals-small_50_466580_40x40.png");
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
		url("/abc/images/ui-bg_diagonals-small_50_466580_40x40.png");
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
<link rel="stylesheet" href="/abc/css/validation/jquery.ketchup.css">
<script type="text/javascript" src="/abc/js/datePicker/jquery-1.7.1.js"></script>
<script type="text/javascript"
	src="/abc/js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
<script type="text/javascript" language="javascript"
	src="/abc/js/dataTable/jquery.dataTables.js"></script>
<script type="text/javascript" src="/abc/js/datePicker/ui/jquery.ui.core.js"></script>
<script type="text/javascript"
	src="/abc/js/datePicker/ui/jquery.ui.widget.js"></script>
<script type="text/javascript"
	src="/abc/js/datePicker/ui/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="/abc/js/datePicker/ui/jquery.ui.tabs.js"></script>
<script type="text/javascript" src="/abc/js/datePicker/ui/sliderAccess.js"></script>

<script type="text/javascript"
	src="/abc/js/validation/jquery.ketchup.all.min.js"></script>
<script type="text/javascript"
	src="/abc/js/datePicker/ui/jquery.ui.button.js"></script>
<script type="text/javascript"
	src="/abc/js/datePicker/ui/jquery.ui.accordion.js"></script>
<script type="text/javascript"
	src="/abc/js/datePicker/ui/jquery.effects.core.js"></script>
<script type="text/javascript"
	src="/abc/js/datePicker/ui/jquery.ui.accordion.js"></script>
<script type="text/javascript"
	src="/abc/js/datePicker/ui/jquery.effects.slide.js"></script>
<script type="text/javascript"
	src="/abc/js/datePicker/ui/jquery.effects.bounce.js"></script>
<script type="text/javascript"
	src="/abc/js/datePicker/ui/jquery.effects.clip.js"></script>
<script type="text/javascript"
	src="/abc/js/datePicker/ui/jquery.effects.transfer.js"></script>
<script type="text/javascript"
	src="/abc/js/datePicker/ui/jquery.effects.blind.js"></script>
<script type="text/javascript"
	src="/abc/js/datePicker/ui/ScrollableGridPlugin.js"></script>
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
<script type="text/javascript" src="/abc/js/datetimepicker_css.js"></script>


	<script type="text/javascript">
		function approved() {
			var form1 = document.getElementById("form1");
			form1.action = "/abc/EnquiryProcess/approved?display=viewall";
			form1.method = "POST";
			form1.submit();
		}
		
		function rejected() {
			var form1 = document.getElementById("form1");
			form1.action = "/abc/EnquiryProcess/rejected?display=viewall";
			form1.method = "POST";
			form1.submit();
		}
		
		function processing() {
			var form1 = document.getElementById("form1");
			form1.action = "/abc/EnquiryProcess/processing?display=viewall";
			form1.method = "POST";
			form1.submit();
		}
		
		function onHold() {
			var form1 = document.getElementById("form1");
			form1.action = "/abc/EnquiryProcess/onhold?display=viewall";
			form1.method = "POST";
			form1.submit();
		}
		
	</script>
	
	 <script type="text/javascript">
					
					var querystatus = '<c:out default="" value="${querystatus}"/>';
		            
		            if(querystatus == "true"){
		            	 $(function(){
		            		 $( "div.success" ).fadeIn( 800 ).delay( 2000 );
		            		 $( "div.success" ).fadeOut("slow");
		            	 });
		            	 }else if(querystatus == "false"){
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
		        			form1.action = "/abc/JobProcess/ViewTaskDetails?jobid="+jobId;
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
         $("#approved").button({
             icons:{
                 primary: "ui-icon-check"
             }
         }).click(function(){
        	 approved();
         });
         
         
         $("#rejected").button({
             icons:{
                 primary: "ui-icon-cancel"
             }
         }).click(function(){
             rejected();

         });
         
         $("#processing").button({
             icons:{
                 primary: "ui-icon-circle-check"
             }
         }).click(function(){
             processing();

         });
         
         $("#onhold").button({
             icons:{
                 primary: "ui-icon-circle-plus"
             }
         }).click(function(){
             onHold();

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
              

     });
		
</script>
	


</head>
  <%
//allow access only if session exists
String user = null;
if(session.getAttribute("userAuth") == null){
	response.sendRedirect("/abc/UserProcess/sessionTimeOut");
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


		<div class="alert-box success" id="div1">Enquiry updated successfully!!!&nbsp;&nbsp;&nbsp;<button class="button" id="1" onclick="closediv(this.id);">OK</button></div>
		<div class="alert-box failure" id="div2">Enquiry update failed, please try again!!!&nbsp;&nbsp;&nbsp;<button class="buttonred" id="2" onclick="closediv(this.id);">OK</button></div>
		
		<div style="overflow: hidden">
			<table width="100%">
				<tr>
					<td class="headerTD">Enquiries</td>
					
				</tr>
			</table>
			<table   width="100%"  border="0" style="border-color:#4b6a84;"  id="myTable">

                    <thead>
                        <tr>
                            <th class="headerText"><input  type="checkbox" id = "chckHead" /></th>
                            <th class="headerText">Student Name</th>
                            <th class="headerText">Contact No.</th>
                            <th class="headerText">Father Name</th>
                            <th class="headerText">Mother Name</th>
                            <th class="headerText">Admission Class</th>
                            <th class="headerText">Address</th>
                            <th class="headerText">Siblings</th>
                            <th class="headerText">Created Date</th>
                            <th class="headerText">Academic Year</th>
                            <th class="headerText">Status</th>
                        </tr>
                    </thead>

                    <tbody>
                        <c:forEach items="${enquirylist}" var="enquiry" varStatus="status">
                            <tr class="trClass" style="border-color:#000000" border="1">
                            	<%-- <c:choose>
                                <c:when test="${enquiry.status == 'Approved'}">
                                    <td class="dataText"><input type="checkbox" id = "<c:out value="${enquiry.id}"/>" class = "chcktbl"  name="enquiryids"  value="<c:out value="${enquiry.id}"/>" disabled="disabled"/></td>
                                </c:when>
                                <c:otherwise>
                                    <td class="dataText"><input type="checkbox" id = "<c:out value="${enquiry.id}"/>" class = "chcktbl"  name="enquiryids"  value="<c:out value="${enquiry.id}"/>"/></td>
                                </c:otherwise>
                           		 </c:choose> --%>
                            	<td class="dataText"><input type="checkbox" id = "<c:out value="${enquiry.id}"/>" class = "chcktbl"  name="enquiryids"  value="<c:out value="${enquiry.id}"/>"/></td>
                            	<td class="dataText"><c:out value="${enquiry.name}"/></td>
                            	<td class="dataText"><c:out value="${enquiry.mobileno}"/></td>
                            	<td class="dataText"><c:out value="${enquiry.fathername}"/></td>
                            	<td class="dataText"><c:out value="${enquiry.mothername}"/></td>
                            	<td class="dataText"><c:out value="${enquiry.admissionclass}"/></td>
                            	<td class="dataText"><c:out value="${enquiry.address}"/></td>
                            	<td class="dataText"><c:out value="${enquiry.siblings}"/></td>
                            	<td class="dataText" style="text-align: left;"><fmt:formatDate pattern="dd/MM/yyyy" value="${enquiry.createddate}"/></td>
                            	<td class="dataText"><c:out value="${enquiry.academicyear}"/></td>
                            	<td class="dataText"><c:out value="${enquiry.status}"/></td>
                            	<%-- <c:if test="${enquiry.status == 'Approved' }">
                                	<td class="dataText" style="color: #cb1b09;font-weight: bold;">
                                		<a class="dataTextInActive" style="color: #cb1b09;font-weight: bold;cursor: pointer;"><c:out value="${enquiry.status}"/></a>
                                	</td>
                                </c:if>
                                <c:if test="${enquiry.status == 'Rejected' }">
                                	<td class="dataText" style="color: #0001ff;font-weight: bold;">
                                		<a class="dataTextInActive" style="color: #0001ff;font-weight: bold;cursor: pointer;"><c:out value="${enquiry.status}"/></a>
                                	</td>
                                </c:if>
                                <c:if test="${enquiry.status == 'Processing' }">
                                	<td class="dataText" style="color: #65a358;font-weight: bold;">
                                		<a class="dataTextInActive" style="color: #65a358;font-weight: bold;cursor: pointer;" ><c:out value="${enquiry.status}"/></a>
                                	</td>
                                </c:if>
                                <c:if test="${enquiry.status == 'On Hold' }">
                                	<td class="dataText" style="color: grey;font-weight: bold;">
                                		<a class="dataTextInActive" style="color: grey;font-weight: bold;cursor: pointer;"><c:out value="${enquiry.status}"/></a>
                                	</td>
                                </c:if> --%>
                                
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
                    	<td  colspan="2" ><button id="approved">Approved</button>
                            &nbsp;&nbsp;&nbsp;&nbsp;<button id="rejected">Rejected</button>
                            &nbsp;&nbsp;&nbsp;&nbsp;<button id="processing">Processing</button> 
                            &nbsp;&nbsp;&nbsp;&nbsp;<button id="onhold">On Hold</button> 
                    	</td>
                   </tr>
                </table>
                
                <div align="center">
		             <%--For displaying Previous link except for the 1st page --%>
		                <c:if test="${currentPage != 1}">
		                    <td><a style="color: #4B6A84;font-size: 12px" href="/abc/EnquiryProcess/viewAllEnquiries&page=${currentPage - 1}">Previous</a></td>
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
		                                    <td style="color: black;font-weight:bold;font-size: 15px "><a style="color: #4B6A84" href="/abc/EnquiryProcess/viewAllEnquiries&page=${i}">${i}</a></td>
		                                </c:otherwise>
		                            </c:choose>
		                            
		                             <c:if test="${status.index % 30 == 0 || status.index == noOfPages}">
 								   	 </tr>
 								   </c:if>
		                            
		                    </c:forEach>
		                </table>
		
		                <%--For displaying Next link --%>
		                <c:if test="${currentPage lt noOfPages}">
		                    <td ><a style="color: #4B6A84;font-size: 12px" href="/abc/EnquiryProcess/viewAllEnquiries&page=${currentPage + 1}">Next</a></td>
		                </c:if>
                </div>
                
		</div>
	</form>
</body>
</html>

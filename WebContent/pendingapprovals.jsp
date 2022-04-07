<%-- 
    Document   : Pending Approvals
    Created on : JUL 25, 2019, 10:34:17 PM
    Author     : Musaib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html >
    <head >
       <%--  <%
            response.setHeader("Cache-Control", "no-cache"); //Forces caches to obtain a new copy of the page from the origin server
            response.setHeader("Cache-Control", "no-store"); //Directs caches not to store the page under any circumstance
            response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
            response.setHeader("Pragma", "no-cache"); //HTTP 1.0 backward compatibility
        %> --%>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pending Approvals</title>
        
        	<link rel="stylesheet" href="css/datePicker/jquery-ui-1.8.18.custom.css">
			<link rel="stylesheet" href="css/datePicker/demos.css">
			<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
			<script type="text/javascript" src="js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
			<script type="text/javascript" src="js/dataTable/jquery.dataTables.js"></script>
			
			
        
        <style type="text/css" >
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
                border-radius:3px;
                font-family: Tahoma;
                color: #4b6a84;
                font-size: 13px;
                letter-spacing: normal;
                text-align: center;
                background-color: #E3EFFF;

            }
            .dataTextInActive {
                border-radius:3px;
                font-family: Tahoma;
                color: #4b6a84;
                font-size: 12px;
                font-weight: bold;
                letter-spacing: normal;
                text-align: center;
                background-color: #E3EFFF;
                text-decoration:none;
            }
            .dataTextActive {
                border-radius:3px;
                font-family: Tahoma;
                color: #4b6a84;
                font-size: 12px;
                font-weight: bold;
                letter-spacing: normal;
                text-align: center;
                background-color: #E3EFFF;
                text-decoration: underline;
            }
            .dataTextHidden {
                font-family: Tahoma;
                color: #4b6a84;
                font-size: 13px;
                letter-spacing: normal;
                text-align: center;
                background-color: #E3EFFF;

            }
            .headerTD{
                border-radius:6px;
                background-color:#4b6a84;
                background-image: url("images/ui-bg_diagonals-small_50_466580_40x40.png");
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
            -->
            
            .alignRightFields {
	font-family: Tahoma;
	font-size: 11px;
	font-style: normal;
	text-transform: capitalize;
	color: #325F6D;
	text-align: right;
	vertical-align: middle;
	font-weight: bold;
}

.alignParallelFields {
	font-family: Tahoma;
	font-weight: bold;
	color: #325F6D;
}


         .alignCenterFields {
	font-family: Tahoma;
	font-size: 14px;
	font-style: normal;
	text-transform: capitalize;
	color: #325F6D;
	text-align: center;
	vertical-align: middle;
	font-weight: bold;
}

        </style>
        
        <script type="text/javascript" charset="utf-8">
            $(document).ready(function() {
                $('#myTable').dataTable( {
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
                    
                } );
            } );
        </script>
        <script type="text/javascript">
        function approveRecords(){
            var form1=document.getElementById("form1");
            form1.action="Controller?process=StudentProcess&action=approveRecords";
           	form1.submit();
            
        }
        
        function rejectRecords(){
            var form1=document.getElementById("form1");
            form1.action="Controller?process=StudentProcess&action=rejectRecords";
           	form1.submit();
            
        }
        </script>
        
        <script type="text/javascript">
    	$(function() {

    		$("#tabs").tabs();
    		
    		 $("#effect").hide();
    		 
    		 $("#search").button().click(function() {
    				searchForStudents();
    			});
    	});
    	
    	
    	function searchForStudents() {
        			var form1 = document.getElementById("form1");
        			form1.action = "Controller?process=StudentProcess&action=pendingapprovals";
        			form1.method = "POST";
        			form1.submit();
    		}
    	
            $(function(){
                $("#approve").button({
                    icons:{
                        primary: "ui-icon-trash"
                    }
                }).click(function(){
                	if(confirm('are you sure, you want to approve?')){
                		 approveRecords();
                	}
                    return false;

                });
                
                $("#reject").button({
                    icons:{
                        primary: "ui-icon-trash"
                    }
                }).click(function(){
                	if(confirm('are you sure, you want to reject?')){
               		 rejectRecords();
               	}
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

        <form name="form1" id="form1"action="Controller?process=StudentProcess&action=archiveMultiple" method="post">
            <div style="height: 28px">
			<button id="add">Search</button>
			<br />
		</div>

		<div id="effect" class="ui-widget-content ui-corner-all">
			<div id="tabs">
				<ul>
					<li><a href="#tabs-1">Apply Filters</a></li>

				</ul>
				<div id="tabs-1">
				
					<table width="100%" border="0" style="border-color: #4b6a84;float: left;margin-bottom:20px;" >
					<tr>
						<tr>
							<td class="alignRightFields" >Center&nbsp;&nbsp;&nbsp;<input name="branchid" type="hidden" id="branchid" value="${branchid}" /></td>
							<td><label> <select name="centercode" id="centercode"
									style="width: 240px;">
										<option selected>${studentviewallcenter}</option>
										<option></option>
										<c:forEach items="${branchList}" var="branchlist">
											<option value="${branchlist.centercode}:${branchlist.centername}" >
												<c:out value="${branchlist.centercode} -- ${branchlist.centername}" />
											</option>
										</c:forEach>
								</select>
							</label>
							</td>
							
						</tr>
					</table>
					
					<table >
					<tr>
							<td width="80%"></td>
							<td >
								<button id="search">Search</button>
							</td>
						</tr>
					
					</table>
					

				</div>
			</div>
		</div>
            <div style="overflow: hidden">
                <table width="100%">
                    <tr>
                        <td  class="headerTD">List Of Pending Approvals</td>
                    </tr>

                    

                </table>
                <table   width="100%"  border="0" style="border-color:#4b6a84;"  id="myTable">

                    <thead>
                        <tr>
                            <th class="headerText"><input  type="checkbox" id = "chckHead" /></th>
                            <th title="click to sort" class="headerText">Admission Number</th>
                            <th title="click to sort" class="headerText">Name&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                            <th title="click to sort" class="headerText">Father/Guardian/Husband Name</th>
                            <th title="click to sort" class="headerText">Language</th>
                            <th title="click to sort" class="headerText">Exam Level</th>
                            <th title="click to sort" class="headerText">District Code&nbsp;</th>
                            <th title="click to sort" class="headerText">Center Code&nbsp;</th>
                        </tr>
                    </thead>

                    <tbody>
                        <c:forEach items="${studentListPendingApproval}" var="Parents">
											
                            <tr class="trClass" style="border-color:#000000" border="1"  cellpadding="1"  cellspacing="1" >
                                <td class="dataText"><input type="checkbox" id = "<c:out value="${Parents.student.sid}"/>" class = "chcktbl"  name="studentIDs"  value="<c:out value="${Parents.student.sid}"/>"/></td>
                                <td  class="dataTextInActive"><a class="dataTextInActive" href="Controller?process=StudentProcess&action=ViewDetails&id=<c:out value='${Parents.student.sid}'/>&urlbranchid=<c:out value='${Parents.student.branchid}'/>"><c:out value="${Parents.student.admissionnumber}"/></a>
                                <input type="hidden" name="admissionno_${Parents.student.sid}"  value="<c:out value="${Parents.student.admissionnumber}"/>"/>
                                </td>
                                <td class="dataText" style="text-transform:uppercase"><c:out value="${Parents.student.name}"/></td>
                                <td class="dataText">
								<c:if test="${(Parents.mothersname != '')}">
								<c:out value="${Parents.mothersname}" />
								</c:if>
								<c:if test="${(Parents.fathersname != '')}">
								<c:out value="${Parents.fathersname}" />
								</c:if>
								<c:if test="${(Parents.student.guardiandetails != '')}">
								<c:out value="${Parents.student.guardiandetails}" />
								</c:if>
								</td>
								<td class="dataText" style="text-transform:uppercase"><c:out value="${Parents.student.languageopted}"/></td>
                                <td class="dataText" style="text-transform:uppercase"><c:out value="${Parents.student.examlevel}"/></td>
                                <td class="dataText" style="text-transform:uppercase"><c:out value="${Parents.student.districtcode}"/></td>
                                <td class="dataText" style="text-transform:uppercase"><c:out value="${Parents.student.centercode}"/></td>
                                <!-- <fmt:formatDate value="${Parents.student.admissiondate}" pattern="yyyy-MM-dd"/>  -->
                                <!-- <td class="dataText"><fmt:formatDate value="${Parents.student.admissiondate}" pattern="yyyy-MM-dd"/></td> -->
                                 

                            </tr>
                        </c:forEach>
                    </tbody>
                    <tfoot><tr>
                            <td  class="footerTD" colspan="2" >
                            <input value="Approve" type="submit" id="approve"/> &nbsp;&nbsp;&nbsp;
                            <input value="Reject" type="submit" id="reject"/> </td>
                    
                        </tr></tfoot>
                </table>

            </div>
            
            <div align="center">
            			<p class="alignCenterFields">Total Number Of Students: ${totalstudents}</p>
             <%--For displaying Previous link except for the 1st page --%>
                <c:if test="${currentPage != 1}">
                    <td><a style="color: #4B6A84;font-size: 12px" href="Controller?process=StudentProcess&action=viewAll&page=${currentPage - 1}">Previous</a></td>
                </c:if>
                <%--For displaying Next link --%>
                <c:if test="${currentPage lt noOfPages}">
                    <td ><a style="color: #4B6A84;font-size: 12px" href="Controller?process=StudentProcess&action=viewAll&page=${currentPage + 1}">Next</a></td>
                </c:if>
                    </div>
            
            
        </form>
    </body>
</html>
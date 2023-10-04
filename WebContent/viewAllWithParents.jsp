<%-- 
    Document   : viewAll
    Created on : Dec 29, 2012, 1:57:17 PM
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
        <title>View All</title>
        
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
        function deleteRecords(){
            var form1=document.getElementById("form1");
            form1.action="Controller?process=StudentProcess&action=deleteMultiple";
           form1.submit();
            
        }
        
        function archiveRecords(){
            var form1=document.getElementById("form1");
            form1.action="Controller?process=StudentProcess&action=archiveMultiple";
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
    		var centerCode = document.getElementById("centercode").value;
    		var districtCode = document.getElementById("districtcode").value;
    		var examLevel = document.getElementById("examlevel").value;
    		var language = document.getElementById("languageopted").value;
    		var qualification = document.getElementById("qualification").value;
    		var religion = document.getElementById("religion").value;
    		var academicyear = document.getElementById("academicyear").value;
    		var admissionnumber = document.getElementById("admissionnumber").value;
    		var studentname = document.getElementById("studentname").value;
    		var fhgname = document.getElementById("fhgname").value;
    		var fromage = document.getElementById("fromage").value;
    		var toage = document.getElementById("toage").value;
    		var branchid = document.getElementById("branchid").value;
    		
    		if(branchid == 1){
    			if(centerCode!="" || districtCode!="" || examLevel!="" || language!="" || qualification!="" ||
        				religion!="" || academicyear!="" || admissionnumber!="" || studentname!="" || fhgname!="" || fromage!="" || toage!="" || document.getElementById('male').checked == true || document.getElementById('female').checked == true){
        			var form1 = document.getElementById("form1");
        			form1.action = "Controller?process=StudentProcess&action=searchStudentsviewAll";
        			form1.method = "POST";
        			form1.submit();
        		}else{
        			alert('Enter atleast one filter criteria');
        			var form1 = document.getElementById("form1");
        			form1.action = "Controller?process=StudentProcess&action=viewAll";
        			form1.method = "POST";
        			form1.submit();
        		}
    		}else{
    			if(centerCode!=""){
        			var form1 = document.getElementById("form1");
        			form1.action = "Controller?process=StudentProcess&action=searchStudentsviewAll";
        			form1.method = "POST";
        			form1.submit();
        		}else{
        			alert('Please select the center');
        			var form1 = document.getElementById("form1");
        			form1.action = "Controller?process=StudentProcess&action=viewAllStudentsCenter";
        			form1.method = "POST";
        			form1.submit();
        		}
    		}
    		
    		
    	}
    	
            $(function(){
                $("#delete").button({
                    icons:{
                        primary: "ui-icon-trash"
                    }
                }).click(function(){
                	if(confirm('are you sure, you want to delete?')){
                		 deleteRecords();
                	}
                    return false;

                });
                
                $("#archive").button({
                    icons:{
                        primary: "ui-icon-trash"
                    }
                }).click(function(){
                	if(confirm('are you sure, you want to archive?')){
               		 archiveRecords();
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
            
            function redirect(){
                 var form1=document.getElementById("form1");
                    form1.action="Controller?process=PersonalProcess&action=redirect";
                    form1.submit();
                
                //window.location.reload();
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
        		$("#add").button().click(function() {
        			runEffect();
        			return false;
        		});
        	});
        </script>
        
        <script type="text/javascript">

	function maleCheck() {

		if (document.getElementById('male').checked == true) {
			document.getElementById('female').checked = false;

		}

	}

	function femaleCheck() {

		if (document.getElementById('female').checked == true) {
			document.getElementById('male').checked = false;

		}

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

        <form name="form1" id="form1"action="Controller?process=StudentProcess&action=archiveMultiple" method="post">
            <div style="height: 28px">
			<button id="add">Search Students</button>
			<br />
		</div>

		<div id="effect" class="ui-widget-content ui-corner-all">
			<div id="tabs">
				<ul>
					<li><a href="#tabs-1">Apply Filters</a></li>

				</ul>
				<div id="tabs-1">
				
					<table width="30%" border="0" style="border-color: #4b6a84;float: left;margin-bottom:50px;" >
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

						<tr>
							<td><br /></td>

						</tr>
						
						<tr>
							<td class="alignRightFields">Exam Level &nbsp;&nbsp;&nbsp;</td>
							<td><label> 
										<select name="examlevel" id="examlevel"
									style="width: 240px;">
										<option selected>${studentviewallexamlevel}</option>
										<option></option>
										<c:forEach items="${examleveldetails}" var="examleveldetails">
											<option value="${examleveldetails.levelcode}" >
												<c:out value="${examleveldetails.levelcode} -- ${examleveldetails.levelname}" />
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
							<td class="alignRightFields">Qualification &nbsp;&nbsp;&nbsp;</td>
							<td ><label> 
										<select name="qualification" id="qualification"
									style="width: 240px;">
										<option selected>${studentviewallqualification}</option>
										<option></option>
										<c:forEach items="${qualificationlist}" var="qualificationlist">
											<option value="${qualificationlist.qualification}" >
												<c:out value="${qualificationlist.qualification}" />
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
							<td class="alignRightFields">Batch &nbsp;&nbsp;&nbsp;</td>
							<td><label> 
										<select name="academicyear" id="academicyear"
									style="width: 240px;">
										<option selected value="${studentviewallacademic}">${studentviewallacademic}</option>
											<option></option>
											<option value="${currentAcademicYear}">${currentAcademicYear} {Current Academic Year}</option>
											<option value="2023/24" >2023/24</option>
											<option value="2022/23" >2022/23</option>
											<option value="2021/22" >2021/22</option>
											<option value="2020/21" >2020/21</option>
											<option value="2019/20" >2019/20</option>
											<option value="2018/19" >2018/19</option>
								</select>
							</label> 
							</td>
						</tr>
						
						<tr>
							<td><br /></td>

						</tr>
						
						<tr>
							<td class="alignRightFields">Student Name &nbsp;&nbsp;&nbsp;</td>
							<td><label> <input
									name="studentname" type="text" id="studentname" value="${studentviewallstudentname}" size="36" style="text-transform:uppercase;font-weight: bold;width: 236px;"
									>
							</label> 
						</tr>
						
						<tr>
							<td><br /></td>

						</tr>
						
						<tr>
							<td class="alignRightFields">Age &nbsp;&nbsp;&nbsp;</td>
							<td><label> From Age:&nbsp;<input
									name="fromage" type="text" id="fromage" size="15" style="text-transform:uppercase;font-weight: bold;width: 36px;"
									>
							</label>
							<label> To Age:&nbsp;<input
									name="toage" type="text" id="toage" size="15" style="text-transform:uppercase;font-weight: bold;width: 36px;"
									>
							</label> 
						</tr>
						
						<tr>
							<td><br /></td>

						</tr>
						
					</table>
					
					
					<table width="50%" border="0" style="border-color: #4b6a84;margin-bottom:50px;" >

						<tr>
							<td class="alignRightFields">District &nbsp;&nbsp;&nbsp;</td>
							<td><label> 
										<select name="districtcode" id="districtcode"
									style="width: 240px;" onchange="getAdmNo()">
										<option selected>${studentviewalldistrict}</option>
										<option></option>
										<c:forEach items="${districtsList}" var="districtsList">
											<option value="${districtsList.districtcode}" >
												<c:out value="${districtsList.districtcode} -- ${districtsList.districtname}" />
											</option>
										</c:forEach>
								</select>
							</label> 
						</tr>

						<tr>
							<td><br /></td>

						</tr>
						
						<tr>
							<td class="alignRightFields">Language &nbsp;&nbsp;&nbsp;</td>
							<td><label> 
										<select name="languageopted" id="languageopted"
									style="width: 240px;">
										<option selected>${studentviewalllanguage}</option>
										<option></option>
										<c:forEach items="${languageslist}" var="languageslist">
											<option value="${languageslist.language}" >
												<c:out value="${languageslist.language}" />
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
							<td class="alignRightFields">Religion &nbsp;&nbsp;&nbsp;</td>
							<td><label> 
										<select name="religion" id="religion"
									style="width: 240px;">
										<option selected>${studentviewallreligion}</option>
										<option></option>
											<option value="ISLAM" >Islam</option>
											<option value="OTHERS" >Others</option>
								</select>
							</label> 
						</tr>

						<tr>
							<td><br /></td>

						</tr>
						
						<tr>
							<td class="alignRightFields">Admission Number&nbsp;&nbsp;&nbsp;</td>
							<td><label> 
										<input
									name="admissionnumber" type="text" id="admissionnumber" size="36" style="text-transform:uppercase;font-weight: bold;width: 236px;"
									>
							</label> 
						</tr>
						
						<tr>
							<td><br /></td>

						</tr>
						
						<tr>
							<td class="alignRightFields">F/H/G Name &nbsp;&nbsp;&nbsp;</td>
							<td>
								<label>
								 <input name="fhgname" type="text" id="fhgname" size="36" style="text-transform:uppercase;font-weight: bold;width: 236px;">
								</label> 
						</tr>
						
						
						<tr>
							<td><br /></td>

						</tr>
						
						<tr>
								<td class="alignRightFields">Gender &nbsp;</td>
								<td>&nbsp;Male<input type="checkbox" value="Male" name="gender" id="male" ${studentviewallgender == 'Male' ? 'checked' : ''} 
								onclick="maleCheck();" />&nbsp; &nbsp;Female<input
								type="checkbox" value="Female" name="gender" id="female" ${studentviewallgender == 'Female' ? 'checked' : ''} 
								onclick="femaleCheck()" />

								</td>
							</tr>


					</table>
					
					<table >
					<tr>
							<td width="50%"></td>
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
                        <td  class="headerTD">View All Students</td>
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
                        <c:forEach items="${studentList}" var="Parents">
											
                            <tr class="trClass" style="border-color:#000000" border="1"  cellpadding="1"  cellspacing="1" >
                                <td class="dataText"><input type="checkbox" id = "<c:out value="${Parents.student.sid}"/>" class = "chcktbl"  name="studentIDs"  value="<c:out value="${Parents.student.sid}"/>"/></td>
                                <td  class="dataTextInActive"><a class="dataTextInActive" href="Controller?process=StudentProcess&action=ViewDetails&id=<c:out value='${Parents.student.sid}'/>&urlbranchid=<c:out value='${Parents.student.branchid}'/>"><c:out value="${Parents.student.admissionnumber}"/></a></td>
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
                            <input value="Delete" type="submit" id="delete"/> &nbsp;&nbsp;&nbsp;
                            <input value="Archive" type="submit" id="archive"/> </td>
                    
                        </tr></tfoot>
                </table>

            </div>
            
            <div align="center">
            			<p class="alignCenterFields">Total Number Of Students: ${totalstudents}</p>
             <%--For displaying Previous link except for the 1st page --%>
                <c:if test="${currentPage != 1}">
                    <td><a style="color: #4B6A84;font-size: 12px" href="Controller?process=StudentProcess&action=viewAll&page=${currentPage - 1}">Previous</a></td>
                </c:if>

                <%--For displaying Page numbers.
                The when condition does not display a link for the current page--%>
                <%-- <table border="0" cellpadding="5" cellspacing="5" >
                    <tr style="width: 400px;">
                        <c:forEach begin="1" end="${noOfPages}" var="i">
                            <c:choose>
                                <c:when test="${currentPage eq i}">
                                    <td style="color: #1D599B;font-weight:bolder;font-size: 20px ">${i}</td>
                                </c:when>
                                <c:otherwise>
                                    <td style="color: black;font-weight:bold;font-size: 15px "><a style="color: #4B6A84" href="Controller?process=StudentProcess&action=viewAll&page=${i}">${i}</a></td>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </tr>
                </table> --%>

                <%--For displaying Next link --%>
                <c:if test="${currentPage lt noOfPages}">
                    <td ><a style="color: #4B6A84;font-size: 12px" href="Controller?process=StudentProcess&action=viewAll&page=${currentPage + 1}">Next</a></td>
                </c:if>
                    </div>
            
            
        </form>
    </body>
</html>


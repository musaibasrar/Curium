<%-- 
    Document   : Student Details Fees Structure
    Created on : Jan 4, 2013, 4:39:24 PM
    Author     : Musaib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Student Details Fees Structure</title>

        <script type="text/javascript" language="JavaScript" src="js/motionpack.js"></script>
        <link rel="stylesheet" href="css/datePicker/jquery-ui-1.8.18.custom.css">
        <link rel="stylesheet" href="css/graph/jquery.jqplot.css">

        <link rel="stylesheet" href="css/datePicker/demos.css">
        <script type="text/javascript" src="js/datePicker/jquery-1.7.1.js"></script>
        <script type="text/javascript" src="js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
        <script type="text/javascript" src="js/datePicker/ui/jquery.ui.dialog.js"></script>
        <script type="text/javascript" src="js/datePicker/ui/jquery.ui.autocomplete.js"></script>
        <script type="text/javascript" src="js/datePicker/ui/jquery.ui.core.js"></script>
        <script type="text/javascript" src="js/datePicker/ui/jquery.ui.widget.js"></script>
        <script type="text/javascript" src="js/datePicker/ui/jquery.ui.datepicker.js"></script>
        <script type="text/javascript" src="js/datePicker/ui/jquery.ui.accordion.js"></script>
        <script type="text/javascript" src="js/datePicker/ui/sliderAccess.js"></script>
        <script type="text/javascript" src="js/datePicker/ui/jquery-ui-timepicker-addon.js"></script>

        <script  type="text/javascript" src="js/datePicker/ui/jquery.ui.position.js"></script>
        <script type="text/javascript" src="js/datePicker/ui/jquery.ui.mouse.js"></script>
        <script type="text/javascript" src="js/datePicker/ui/jquery.ui.draggable.js"></script>
        <script type="text/javascript" src="js/datePicker/ui/jquery.ui.resizable.js"></script>
        <script type="text/javascript" src="js/graph/jquery.jqplot.js"></script>
        <script  type="text/javascript" src="js/graph/plugins/jqplot.dateAxisRenderer.min.js"></script>
        <script type="text/javascript" src="js/graph/plugins/jqplot.barRenderer.min.js"></script>
        <script type="text/javascript" src="js/graph/plugins/jqplot.categoryAxisRenderer.min.js"></script>
        <script type="text/javascript" src="js/graph/plugins/jqplot.cursor.min.js"></script>
        <script type="text/javascript" src="js/graph/plugins/jqplot.highlighter.min.js"></script>
        <script type="text/javascript" src="js/graph/plugins/jqplot.dragable.min.js"></script>
        <script type="text/javascript" src="js/graph/plugins/jqplot.trendline.min.js"></script>

        <style type="text/css">
        .dataText {
                border-radius:3px;
                font-family: Tahoma;
                color: #4b6a84;
                font-size: 13px;
                letter-spacing: normal;
                text-align: center;
                background-color: #E3EFFF;

            }
            .dataTextFees {
                border-radius:3px;
                font-family: ariel;
                font-weight: bold;
                color: #4b6a84;
                font-size: 16px;
                letter-spacing: normal;
                text-align: center;
                background-color: #E3EFFF;

            }
       		.dataTextDueFees{
				                border-radius:3px;
                font-family: ariel;
                font-weight: bold;
                color: red;
                font-size: 16px;
                letter-spacing: normal;
                text-align: center;
                background-color: #E3EFFF;

            }       		
       			 
            <!--
            .dataTextInActive {
                border-radius:1px;
                font-family: Tahoma;
                color: #4b6a84;
                background-color: #E3EFFF;
               	font-size: 12px;
                font-weight: bold;
                letter-spacing: normal;
                text-align: center;
                vertical-align: top;
                text-decoration:none;
            }
            .headerText {
                border-radius:3px;
                font-family: Tahoma;
                font-size: 12px;
                background-color: #4b6a84;
                color: #FFFFFF;
                font-weight: normal;
                width: auto ;
                height: 22px;
                vertical-align: middle;
                text-align: center;
                background-image: url("images/ui-bg_diagonals-small_50_466580_40x40.png");
            }
            .headerTD{
                background-color:#4b6a84;
                color: #FFFFFF;
                font-family: Tahoma;
                font-size: 13px;
                text-transform: uppercase;
                text-align: center;
                font-weight: bold;
            }
            .smallheaderTD{
                color: #4b6a84;
                font-family: Tahoma;
                font-size: 11px;
                text-transform: uppercase;
                text-align: left;
                font-weight: bold;
            }
            .textFieldFixedWidth{
                width: 57px;
            }
            .subHeaderTD{
                color: #325F6D;
                font-family: Tahoma;
                font-size: 11px;
                text-transform: uppercase;
                text-align: left;
                font-weight: bold;
            }
            .divCSS{
                overflow:  scroll;
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
            .alignLeft {
                font-family: Tahoma;
                font-size: 13px;
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
            .tablerows{
                font-size: 12px;
                font-family: Tahoma;
                text-align: left;
                font-weight: bold;

            }
            -->
            .mandatoryClass {
    font-family: Tahoma;
    font-size: 11px;
    color: red;
    font-style: normal;
    text-transform: capitalize;
    
    text-align: right;
    vertical-align: middle;
    font-weight: bold;
}
        </style>
        
        

        <script type="text/javascript">
            $(function() {

                $("#accordion").accordion();
                
                $("#search")
                .button()
                .click(function() {
                	searchLeaveDetails();
			        });
                $( "#cancel" )
                .button()
                ;
                
                $("#delete").button({
        	        icons: {
        	            primary: "ui-icon-trash"
        	        }
        	    }).click(function() {
        	    	if (confirm('Are you sure you want to delete the fees category?')) {
        	    		deleteRecord();
        				}
        	    	 return false;
        		});
                
            });
            
            $(function(){
                
                $('#chckHead').click(function () {
                    var length = $('.chcktb2:checked').length;
                    var trLength=$('.trClass').length;
                    if(length>0){
                        $('.chcktb2:checked').attr('checked', false);
                        this.checked=false;

                    }
                    else{
                        if (this.checked == false) {
                            $('.chcktb2:checked').attr('checked', false);
                        }
                        else {
                            $('.chcktb2:not(:checked)').attr('checked', true);
                        }

                    }

                });
                $('.chcktb2').click(function () {
                    var length = $('.chcktb2:checked').length;
                    var trLength=$('.trClass').length;
                    alert(tdLength);
                    if (length > trLength) {

                        $('.chcktb2:not(:checked)').attr('disabled', true);
                    }
                    else {
                        $('.chcktb2:not(:checked)').attr('disabled', false);
                    }
                });

            });
                       
            
            function searchLeaveDetails() {

                var form1 = document.getElementById("form1");
                form1.action = "Controller?process=HrProcess&action=leaveDetailsPerYear";
                form1.submit();

            }
            
            function deleteRecord() {

                var form1 = document.getElementById("form1");
                form1.action = "Controller?process=FeesProcess&action=deleteFeesCategory";
                form1.submit();

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
    <body background="images/bg.jpg">
        <jsp:useBean id="now" class="java.util.Date" />
        <fmt:formatDate var="today" type="date" value="${now}" />
        <form  method="post" id="form1">


            <table width="100%">
                <tr>
                    <td  class="headerTD">NAME: &nbsp;<c:out value="${teachername}" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                      

                    </td>
                </tr>

            </table>
            <div id="accordion" style="width: 100%;height: 100%">

                <h3><a href="#">Staff Leave Details</a></h3>
                
                <div>
                
                <table width="100%" border="0" align="center"  id="table1">
						<tr>
		               <td width="26%"  class="alignRight" >
                                    
                                    Academic Year*&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  </td>

                                <td width="28%"  >
                                    <label>
                                        <label> <select name="academicyear" id="academicyear" required
									style="width: 140px">
										<option selected value="${currentAcademicYear}"> ${currentAcademicYear}(Current Year)</option>
										<option>2015/16</option>
										<option>2016/17</option>
										<option>2017/18</option>
										<option>2018/19</option>
										<option>2019/20</option>
										
								</select>

							</label> 
                                    </label>

                                </td>


                            </tr>

                            
						<td>
						<input type="hidden" id="leavedetailsteachersid" name="leavedetailsteachersid" value="${leavedetailsteachersid}"  />
						</td>

              
                          </table>  
                                <table width="100%" >
                                    <tr>

                                        <td>
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="center">

                                            <button id="search" >Search</button>

                                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                            <!-- <button id="update" >Update</button> -->

                                        </td>

                                    </tr>
                                    
                                         <tr>

                                        <td>
                                            <br/>
                                        </td>
                                    </tr>
                                    
                                         <tr>

                                        <td>
                                            <br/>
                                        </td>
                                    </tr>
                                </table>
					<div align="center">
				<h class="dataTextFees">Academic Year : ${academicPerYear}</h>&nbsp;&nbsp;&nbsp;
				
                </div>
                    <table   width="100%"  border="0" style="border-color:#4b6a84;"  id="myTable">

                    <thead>
                        <tr  >
                            
                            <th title="click to sort" class="headerText"></th>
                            <th title="click to sort" class="headerText"></th>
                            <th title="click to sort" class="headerText"><input type="checkbox" id="chckHead" /></th>
                            <th title="click to sort" class="headerText">Leave Type Name</th>
                            <th title="click to sort" class="headerText">Number Of Leaves&nbsp;</th>
                            <th title="click to sort" class="headerText"></th>
                            <th title="click to sort" class="headerText"></th>
                            <th title="click to sort" class="headerText"></th>
                             


                        </tr>
                    </thead>

                    <tbody>
                        <c:forEach items="${leavedetailslist}" var="leavedetailslist">

                            <tr class="trClass" style="border-color:#000000" border="1"  cellpadding="1"  cellspacing="1" >
                              	<td class="dataText"></td>
                               	<td class="dataText"></td>
                                <td class="dataText"><input type="checkbox"  class = "chcktb2"
								id="<c:out value="${leavedetailslist.idleavedetails}"/>" 
								name="idleavedetails" 
								value="<c:out value="${leavedetailslist.idleavedetails}"/>"></td>
                                <td class="dataText"><c:out value="${leavedetailslist.leaveTypeMaster.leavetypename}"/></td>
                                <td class="dataText"><c:out value="${leavedetailslist.numberofleaves}"/></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                   
                </table>
				
				<table width="100%" >
                                    <tr>

                                        <td>
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="left">
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                            <button id="delete" >
                                            Delete</button>

                                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

                                        </td>

                                    </tr>
                                    
                                         <tr>

                                        <td>
                                            <br/>
                                        </td>
                                    </tr>
                                    
                                         <tr>

                                        <td>
                                            <br/>
                                        </td>
                                    </tr>
                                </table>
                    

                </div>






            </div>
            
            <table  width="70%"  id="table11" align="center">
                        <tr>
                            <td width="30%"> 

                            </td>
                            <td>
                                
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                
                            </td>

                        </tr>

                    </table>
            <script>
                typeofrelation();
            </script>

        </form>
        <script>
        
            function Cancel(){
                var form1=document.getElementById("form1");
                form1.action="Controller?process=StudentProcess&action=ViewAll";
                form1.submit();
            }
        </script>
    </body>
</html>

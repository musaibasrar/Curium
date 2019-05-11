<%-- 
    Document   : member_details
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
        <title>JSP Page</title>

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

        <style type="text/css">
            <!--
            .dataTextInActive {
                border-radius:1px;
                font-family: Tahoma;
                color: #4b6a84;
                font-size: 12px;
                font-weight: bold;
                letter-spacing: normal;
                text-align: left;
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
        </style>
        <script type="text/javascript">
            
            function updateStaff(){
                var form1=document.getElementById("form1");
                form1.action="Controller?process=EmployeeProcess&action=updateEmployeeDetails&id=<c:out value='${employee.tid}'/>";
                form1.submit();
            }
            
        </script>

        <script type="text/javascript">
            $(function() {

                $(".accordion").accordion({
                    collapsible: true,
                    navigation: true,
                    active: false,
                    autoHeight: false});
                $("#accordion1").accordion({
                    collapsible: true,
                    navigation: true,
                    active: false,
                    autoHeight: false});
                /*$("#set")
                .button()
                .click(function() {
                    updateVisit();
                });  */
                $( "#cancel" )
                .button()
                .click(function() {
                    Cancel();


                });
            });
        </script>

                            <script type="text/javascript">
                                $(function() {
                                    $( "#set" )
                                    .button()
                                    .click(function() {
                                        setAppointment();

                                    });
                                    $( "#modify" )
                                    .button()
                                    .click(function() {
                                        updateStaff();

                                    });
                                    $( "#fullSummary" )
                                    .button()
                                    .click(function() {
                                        fullSummary();

                                    });
                                });
                                
                                function calIssues(){
                                    var fromIss = document.getElementById("").value;
                                }
                                
                                function Cancel(){
                                    var form1=document.getElementById("form1");
                                    form1.action="Controller?process=EmployeeProcess&action=viewAllEmployee";
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
        <form  method="post" id="form1">

            <table width="100%">
                <tr>
                    <td  class="headerTD">NAME: &nbsp;<c:out value="${employee.teachername}" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                      

                    </td>
                </tr>

            </table>
            <div class="accordion" style="width: 100%;height: 100%">

                <h3><a href="#">Personal Details</a></h3>
                <div>

                    <table  border="0px" width="100%"  id="table1" align="center">

                        <tr>

                            <td width="25%"  class="alignLeft" height="50">Name</td>
                            <td width="25%" class="tablerows"  style="text-transform:uppercase">
                                <c:out default="" value="${employee.teachername}" />
                            </td>
                            <td width="25%"  class="alignLeft" height="50" >Gender
                            </td>
                            <td width="25%" class="tablerows" >
                                <c:out default="" value="${employee.gender}" />
                            </td>
                        </tr>
                        
                          <tr>
                        <td  width="25%"  class="alignLeft" height="50">Address
                            </td>
                            <td width="25%" class="tablerows">
                                <c:out default="" value="${employee.address}" />
                            </td>
                        
                            <td  width="25%"  class="alignLeft" height="50" >Contact Number
                            </td>
                            <td width="25%" class="tablerows" >
                                <c:out default="" value="${employee.contactnumber}" />
                            </td>

                            
                        </tr>
                        
                        <tr>
                        <td width="25%"  class="alignLeft" height="50" >Email
                            </td>
                            <td width="25%"  class="tablerows">
                                <c:out default="" value="${employee.email}" />
                            </td>
                            <td width="25%"  class="alignLeft" height="50">Date Of Joining</td>
                            <td width="25%" class="tablerows" >
                                <fmt:formatDate pattern="dd-MM-yyyy" value="${employee.dateofjoining}"/>
                            </td>
                            
                        </tr>
                        
                        <tr>
                            <td width="25%"  class="alignLeft" height="50" >Total Experience</td>
                            <td  width="25%"  class="tablerows">
                                <c:out default="" value="${employee.totalexperience}" />
                            </td>
                            <td width="25%"  class="alignLeft" height="50">Qualification
                            </td>
                            <td width="25%" class="tablerows">
                                <c:out default="" value="${employee.qualification}" />
                            </td>
                        </tr>
                        
                        
                        <tr>
                        <td width="25%"   class="alignLeft" height="50" >Department</td>
                            <td width="25%" class="tablerows" >
                                <c:out default="" value="${employee.department}" />
                            </td>
                            
                            
                            <td width="25%"   class="alignLeft" height="50" >Designation</td>
                            <td width="25%" class="tablerows" >
                                <c:out default="" value="${employee.designation}" />
                            </td>
                           
                           
                        
                        </tr>
                        
                        <tr>
                         
                            <td  width="25%"  class="alignLeft" height="50">Current Employee</td>
                            
                            <td width="25%" class="tablerows">
                            	<c:if test="${(employee.currentemployee ==1)}">    
           						  <c:out default="" value="Yes" />
           						 </c:if>
                                <c:if test="${(employee.currentemployee ==0)}">    
           						  <c:out default="" value="No" />
           						 </c:if>
                            </td>
                        
                            <td width="25%" class="alignLeft" height="50" >Remarks</td>
                            <td width="25%" class="tablerows"  >
                                 <c:out default="" value="${employee.remarks}" />
                            </td>
                            
                        </tr>
                        
                        
                        <tr>
                        
                         
                            <td  width="25%"  class="alignLeft" height="50">Staff User ID
                            </td>
                            <td width="25%" class="tablerows">
                                <c:out default="" value="${stafflogin.username}" />
                            </td>
                            
                        
                            <td width="25%"   class="alignLeft" height="50" >Password</td>
                            <td width="25%" class="tablerows"  >
                                 <c:out default="" value="${stafflogin.password}" />
                                
                            </td>
                            

                        </tr>
                        
                        <tr>
                            <td  width="25%"  class="alignLeft" height="50">
                            </td>
                            <td width="25%" class="tablerows" >

                            </td>
                            <td  width="25%"  class="alignLeft" height="50">
                            </td>
                            <td width="25%" class="tablerows">

                            </td>
                        </tr>
                    </table>
                </div>
            </div>
            
            <div class="accordion" style="width: 100%;height: 100%">

                <h3><a href="#">Bank Details</a></h3>
                <div>

                    <table  border="0px" width="100%"  id="table1" align="center">

                        <tr>

                            <td width="25%"  class="alignLeft" height="50">Bank Name</td>
                            <td width="25%" class="tablerows"  style="text-transform:uppercase">
                                <c:out default="" value="${employee.bankname}" />
                            </td>
                            <td width="25%"  class="alignLeft" height="50" >Bank IFSC
                            </td>
                            <td width="25%" class="tablerows" >
                                <c:out default="" value="${employee.bankifsc}" />
                            </td>
                        </tr>
                        
                          <tr>
                        <td  width="25%"  class="alignLeft" height="50">Account Number
                            </td>
                            <td width="25%" class="tablerows">
                                <c:out default="" value="${employee.accno}" />
                            </td>
                                                    
                        </tr>
                        
                        <tr>
                            <td  width="25%"  class="alignLeft" height="50">
                            </td>
                            <td width="25%" class="tablerows" >

                            </td>
                            <td  width="25%"  class="alignLeft" height="50">
                            </td>
                            <td width="25%" class="tablerows">

                            </td>
                        </tr>
                    </table>
                </div>
            </div>
            
            <div class="accordion" style="width: 100%;height: 100%">

                <h3><a href="#">Additional Details</a></h3>
                <div>

                    <table border="0px" width="100%"  id="table1" align="center">

                        <tr>
                            <td width="25%"  class="alignLeft" height="50">Date of Leaving&nbsp;</td>
                            <td width="25%" class="tablerows"  style="text-transform:uppercase">
                            <fmt:formatDate pattern="dd-MM-yyyy" value="${employee.leavingdate}"/>
                            </td>
                        </tr>
                        
                        <tr>
                            <td  width="25%"  class="alignLeft" height="50">
                            </td>
                            <td width="25%" class="tablerows" >

                            </td>
                            <td  width="25%"  class="alignLeft" height="50">
                            </td>
                            <td width="25%" class="tablerows">

                            </td>
                        </tr>
                    </table>
                </div>
            </div>
            
            <table  width="70%"  id="table11" align="center">
                        <tr>
                            <td width="30%"></td>
                            <td>
                                <button id="modify" type="submit">Modify</button>
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <button id="cancel" >Cancel</button>
                            </td>
                        </tr>
          </table>
          
        </form>
    </body>
</html>

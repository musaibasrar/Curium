<%-- 
    Document   : addcontact
    Created on : Jun 17, 2013, 4:17:40 PM
    Author     : CPEDUR1P5
--%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession" %>

<%@page import="java.util.*" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Advance Search for print</title>
        <link rel="stylesheet" href="css/datePicker/jquery-ui-1.8.18.custom.css">
        <link rel="stylesheet" href="css/validation/jquery.ketchup.css">

        <script type="text/javascript" src="js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
        <script src="js/datePicker/jquery-1.7.1.js"></script>
        <script src="js/datePicker/ui/jquery.ui.core.js"></script>
        <script src="js/datePicker/ui/jquery.ui.widget.js"></script>
        <script src="js/datePicker/ui/jquery.ui.datepicker.js"></script>
        <script src="js/datePicker/ui/jquery.ui.tabs.js"></script>
        <script src="js/datePicker/ui/sliderAccess.js"></script>
        <script src="js/datePicker/ui/jquery-ui-timepicker-addon.js"></script>
        <script src="js/validation/jquery.ketchup.all.min.js"></script>
        <script type="text/javascript" src="js/datePicker/ui/jquery.ui.button.js"></script>
        <link rel="stylesheet" href="css/datePicker/demos.css">





        <style type="text/css">

            .headerSearch{
                font-size: 18px;
                font-weight: bold;

            }

            .myclass{
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
                color: black;
                text-transform:capitalize;
            }
            <!--
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


            }
            .alignRight {
                font-family: Tahoma;
                font-size: 12px;
                font-style: normal;
                text-transform: capitalize;
                color: #325F6D;
                text-align: right;
                vertical-align: middle;
                font-weight: bold;
            }

            .alignRightHead {
                font-family: Tahoma;
                font-size: 12px;
                font-style: normal;
                text-transform: capitalize;
                color: #325F6D;


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



            -->

            .alignRight1 {
                font-family: Tahoma;
                font-size: 15px;
                font-style: normal;
                text-transform: capitalize;
                color: #325F6D;
                text-align: right;
                vertical-align: middle;
                font-weight: bold;
            }
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


        <script type="text/javascript" src="js/datetimepicker_css.js"></script>
        <script src="JavaScript/actb.js"></script>
        <script src="JavaScript/common.js"></script>

        <script>

        function validatePassword()
            {
                if (document.getElementById("currentpassword").value.length === 0 || document.getElementById("newpassword").value.length === 0 || document.getElementById("confirmpassword").value.length === 0)

                {
                    document.getElementById("mandatory").style.display = "";
                    
                    
                }


            }

            
            function hide(){
                
                document.getElementById("mandatory").style.display = "none";
                
            }



        </script>



        <script type="text/javascript">
            $(function() {


                $("#save")
                        .button()
                        .click(function() {
                    changePassword();


                });
                

                $("#cancel")
                        .button()
                        .click(function() {
                    Cancel();


                });
            });


        </script>
        <script>
            $(function() {
                $("#tabs").tabs();

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
    <body onload="hide()"><form id="form1"  method="post" >
            

            <div >
                <div id="tabs">
                    <ul>
                        <li><a href="#tabs-1">Change Password</a></li>
                        
                    </ul>



                    <div id="tabs-1">
                        <table width="100%" border="0" align="center"  id="table1">

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

                            <tr>
                                <td>
                                    <br/>
                                </td>
                            </tr>

                            <tr>


                                <td width="16%"  class="alignRight" >
                                    
                                    Current Password*&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  </td>

                                <td width="28%"  >
                                    <label>
                                        <input name="currentpassword" type="password"  class="textField" id="currentpassword" required size="36"  >
                                    </label>

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

                            <tr>

                                <td  class="alignRight" > 
                                    New Password* &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                </td>
                                <td  >
                                    <label>
                                        <input name="newpassword" type="password"  class="textField" id="newpassword" required size="36"  >
                                    </label>
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
                            <tr>


                                <td width="16%"  class="alignRight" >
                                    Confirm new password* &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </td>

                                <td>
                                    <label>
                                        <input name="confirmpassword" type="password"  class="textField" id="confirmpassword" required size="36"  >
                                    </label>
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
                            
                            <tr>
                                <td>
                                    <br/>
                                </td>
                            </tr>


                            
                            <tr id="mandatory">
                                
                                <td width="16%"  class="mandatoryClass"  >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; * All fields are mandatory</td>
                            </tr>
                            
                                <table width="100%" >
                                    <tr>

                                        <td>
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="center">

                                            <button id="save" onmouseover="validatePassword();" >Confirm</button>

                                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                            <button id="cancel" >Cancel</button>

                                        </td>


                                    </tr>
                                </table>

                            </div>
                            
                         



                            
                            
                    </div>
                </div>


        </form>
        <script type="text/javascript">

            function changePassword() {

                var form1 = document.getElementById("form1");
                form1.action = "Controller?process=UserProcess&action=changePassword";
                form1.submit();

            }
         
            function Cancel() {
                var form1 = document.getElementById("form1");
                form1.action = "Controller?process=StudentProcess&action=viewAll";
                form1.submit();
            }

            function validateEmptyField(elementName) {

                var txtBox = document.getElementById(elementName);


                if (txtBox.value == "") {

                    txtBox.className = "emptyFieldSet";


                }
                else if (txtBox.value != "") {
                    txtBox.className = "textField";

                }


            }
            function notEmptyField(elementName) {
                alert(elementName);
                var txtBox = document.getElementById(elementName);
                if (txtBox.value != "") {
                    txtBox.className = "textField";
                }
                else if (txtBox.value == "") {
                    txtBox.className = "emptyFieldSet";
                }
            }
        </script>
    </body>
</html>



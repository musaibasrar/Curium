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
        <meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Change Password</title>
        <link rel="stylesheet" href="/greatindiaacademy/css/datePicker/jquery-ui-1.8.18.custom.css">
        <link rel="stylesheet" href="/greatindiaacademy/css/validation/jquery.ketchup.css">

        <script type="text/javascript" src="/greatindiaacademy/js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
        <script src="/greatindiaacademy/js/datePicker/jquery-1.7.1.js"></script>
        <script src="/greatindiaacademy/js/datePicker/ui/jquery.ui.core.js"></script>
        <script src="/greatindiaacademy/js/datePicker/ui/jquery.ui.widget.js"></script>
        <script src="/greatindiaacademy/js/datePicker/ui/jquery.ui.datepicker.js"></script>
        <script src="/greatindiaacademy/js/datePicker/ui/jquery.ui.tabs.js"></script>
        <script src="/greatindiaacademy/js/datePicker/ui/sliderAccess.js"></script>
        <script src="/greatindiaacademy/js/datePicker/ui/jquery-ui-timepicker-addon.js"></script>
        <script src="/greatindiaacademy/js/validation/jquery.ketchup.all.min.js"></script>
        <script type="text/javascript" src="/greatindiaacademy/js/datePicker/ui/jquery.ui.button.js"></script>
        <link rel="stylesheet" href="/greatindiaacademy/css/datePicker/demos.css">





      
 <style type="text/css">

    /* ==============================
       GLOBAL RESET
       ============================== */

    * {
        box-sizing: border-box;
    }

    html,
    body {
        margin: 0;
        padding: 0;
        width: 100%;
        min-height: 100%;
        font-family: Arial, Helvetica, sans-serif;
        background: #f5f7fa;
    }

    body {
        overflow-x: hidden;
    }

    form {
        width: 100%;
        margin: 0;
        padding: 0;
    }

    /* ==============================
       MAIN TABS CONTAINER
       ============================== */

    #tabs {
        width: 100%;
        max-width: 900px;
        margin: 20px auto;
        padding: 0 15px;
    }

    #tabs-1 {
        width: 100%;
        background: #ffffff;
        border: 1px solid #d5dce3;
        border-radius: 8px;
        padding: 25px;
        box-shadow: 0 2px 8px rgba(0,0,0,0.08);
    }

    /* ==============================
       TAB HEADER
       ============================== */

    #tabs > ul {
        margin: 0;
        padding: 0;
        list-style: none;
    }

    #tabs > ul li {
        display: inline-block;
    }

    #tabs > ul li a {
        display: block;
        padding: 10px 18px;
        text-decoration: none;
    }

    /* ==============================
       FORM TABLE
       ============================== */

    #table1 {
        width: 100%;
        border-collapse: collapse;
        table-layout: fixed;
    }

    #table1 td {
        padding: 8px;
        vertical-align: middle;
    }

    /* Label column */
    #table1 td.alignRight {
        width: 30%;
        text-align: right;
        padding-right: 15px;
        white-space: normal;
    }

    /* Input column */
    #table1 td:nth-child(2) {
        width: 70%;
    }

    /* ==============================
       LABELS
       ============================== */

    .alignRight {
        font-family: Tahoma, Arial, sans-serif;
        font-size: 13px;
        color: #325F6D;
        text-align: right;
        vertical-align: middle;
        font-weight: bold;
    }

    .alignRight1 {
        font-family: Tahoma, Arial, sans-serif;
        font-size: 15px;
        color: #325F6D;
        text-align: right;
        vertical-align: middle;
        font-weight: bold;
    }

    .mandatoryClass {
        font-family: Tahoma, Arial, sans-serif;
        font-size: 11px;
        color: red;
        font-weight: bold;
        text-align: left;
    }

    /* ==============================
       INPUT FIELDS
       ============================== */

    .textField {
        width: 100%;
        max-width: 450px;
        min-height: 38px;
        padding: 8px 10px;

        border: 1px solid #5d7e9b;
        border-radius: 4px;

        font-size: 14px;
        font-family: Arial, Helvetica, sans-serif;

        background: #fff;

        outline: none;
        transition: border-color 0.2s, box-shadow 0.2s;
    }

    .textField:focus {
        border-color: #2878b5;
        box-shadow: 0 0 0 3px rgba(40,120,181,0.12);
    }

    .emptyFieldSet {
        width: 100%;
        max-width: 450px;
        min-height: 38px;

        padding: 8px 10px;

        border: 1px solid #FA7676;
        border-radius: 4px;

        background-color: #fff;
    }

    /* ==============================
       BUTTONS
       ============================== */

    #save,
    #cancel {
        min-width: 100px;
        min-height: 38px;
        padding: 8px 20px;

        border-radius: 5px;
        cursor: pointer;
        font-size: 14px;
    }

    /* ==============================
       INNER BUTTON TABLE
       ============================== */

    #tabs-1 table {
        width: 100%;
        border-collapse: collapse;
    }

    /* ==============================
       REMOVE FIXED/OLD STYLES
       ============================== */

    .tableCSS {
        width: 100%;
        height: auto;
        position: static;
    }

    .textAreaCSS {
        width: 100%;
        height: auto;
    }

    .autoAdjust {
        width: 100%;
        height: auto;
    }

    .myclass {
        width: 100%;
        height: auto;
        color: black;
        text-transform: capitalize;
        border: 1px solid #5d7e9b;
    }

    /* ==============================
       TABLET
       ============================== */

    @media screen and (max-width: 768px) {

        #tabs {
            margin: 10px auto;
            padding: 0 10px;
        }

        #tabs-1 {
            padding: 20px 15px;
        }

        #table1 {
            table-layout: auto;
        }

        #table1 td.alignRight {
            width: 35%;
            font-size: 12px;
        }

        #table1 td:nth-child(2) {
            width: 65%;
        }

        .textField,
        .emptyFieldSet {
            max-width: 100%;
            width: 100%;
        }
    }

    /* ==============================
       MOBILE
       ============================== */

    @media screen and (max-width: 600px) {

        #tabs {
            width: 100%;
            margin: 0;
            padding: 8px;
        }

        #tabs-1 {
            padding: 15px 10px;
            border-radius: 6px;
        }

        #tabs > ul li {
            width: 100%;
        }

        #tabs > ul li a {
            text-align: center;
        }

        /* Convert form table into stacked layout */
        #table1,
        #table1 tbody,
        #table1 tr,
        #table1 td {
            display: block;
            width: 100% !important;
        }

        #table1 tr {
            margin-bottom: 10px;
        }

        #table1 td {
            padding: 5px 0;
        }

        #table1 td.alignRight {
            text-align: left;
            padding: 5px 0;
            font-size: 13px;
        }

        #table1 td:nth-child(2) {
            padding-bottom: 10px;
        }

        .textField,
        .emptyFieldSet {
            width: 100%;
            max-width: none;
            min-height: 40px;
            font-size: 16px;
        }

        .mandatoryClass {
            font-size: 11px;
            text-align: left;
            padding: 5px 0;
        }

        /* Button area */
        #tabs-1 table {
            width: 100%;
        }

        #tabs-1 table td {
            text-align: center;
        }

        #save,
        #cancel {
            width: 100%;
            max-width: 250px;
            margin: 5px auto;
            display: block;
        }
    }

    /* ==============================
       VERY SMALL MOBILE
       ============================== */

    @media screen and (max-width: 380px) {

        #tabs {
            padding: 5px;
        }

        #tabs-1 {
            padding: 12px 8px;
        }

        .alignRight {
            font-size: 12px;
        }

        .textField,
        .emptyFieldSet {
            font-size: 16px;
            min-height: 42px;
        }

        #save,
        #cancel {
            max-width: 100%;
        }
    }

</style>
        <script type="text/javascript" src="/greatindiaacademy/js/datetimepicker_css.js"></script>
        <script src="/greatindiaacademy/JavaScript/actb.js"></script>
        <script src="/greatindiaacademy/JavaScript/common.js"></script>

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
	response.sendRedirect("/greatindiaacademy/UserProcess/sessionTimeOut");
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
                                    
                                    Current Password*  </td>

                                <td width="28%"  >
                                    <label>
                                        <input name="currentpassword" type="password"  class="textField" id="currentpassword" required   >
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
                                    New Password* 
                                </td>
                                <td  >
                                    <label>
                                        <input name="newpassword" type="password"  class="textField" id="newpassword" required   >
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
                                    Confirm new password*  </td>

                                <td>
                                    <label>
                                        <input name="confirmpassword" type="password"  class="textField" id="confirmpassword" required   >
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
                form1.action = "/greatindiaacademy/UserProcess/changePassword";
                form1.submit();

            }
         
            function Cancel() {
                var form1 = document.getElementById("form1");
                form1.action = "/greatindiaacademy/StudentProcess/viewAll";
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



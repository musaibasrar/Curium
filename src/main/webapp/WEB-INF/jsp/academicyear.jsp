<%-- 
    Document   : academic year
    Created on : Jun 17, 2015, 4:17:40 PM
    Author     : Musaib
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
        <title>Academic Year</title>
        <link rel="stylesheet" href="/riyan/css/datePicker/jquery-ui-1.8.18.custom.css">
        <link rel="stylesheet" href="/riyan/css/validation/jquery.ketchup.css">

        <script type="text/javascript" src="/riyan/js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
        <script src="/riyan/js/datePicker/jquery-1.7.1.js"></script>
        <script src="/riyan/js/datePicker/ui/jquery.ui.core.js"></script>
        <script src="/riyan/js/datePicker/ui/jquery.ui.widget.js"></script>
        <script src="/riyan/js/datePicker/ui/jquery.ui.datepicker.js"></script>
        <script src="/riyan/js/datePicker/ui/jquery.ui.tabs.js"></script>
        <script src="/riyan/js/datePicker/ui/sliderAccess.js"></script>
        <script src="/riyan/js/datePicker/ui/jquery-ui-timepicker-addon.js"></script>
        <script type="text/javascript" src="/riyan/js/datePicker/ui/jquery.ui.button.js"></script>
        <link rel="stylesheet" href="/riyan/css/datePicker/demos.css">





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
            
            .alignRightYear {
                font-family: ariel;
                font-size: 14px;
                font-style: normal;
                text-transform: capitalize;
                color: #325F6D;
                text-align: right;
                vertical-align: middle;
                font-weight: bold;
            }
            .alignRightHeader {
                font-family: ariel;
                font-size: 18px;
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
        <script type="text/javascript" src="/riyan/js/datetimepicker_css.js"></script>
        <script>
            $(function() {
                $("#datepicker").datepicker({changeYear: true, changeMonth: true});
                $("#anim").change(function() {
                    $("#datepicker").datepicker("option", "showAnim", $(this).val());
                });
                 $("#datepickercredits").datepicker({changeYear: true, changeMonth: true});
                $("#anim").change(function() {
                    $("#datepickercredits").datepicker("option", "showAnim", $(this).val());
                });
                 $("#datepickercredittodate").datepicker({changeYear: true, changeMonth: true});
                $("#anim").change(function() {
                    $("#datepickercredittodate").datepicker("option", "showAnim", $(this).val());
                });
                $("#datepickercompls").datepicker({changeYear: true, changeMonth: true});
                $("#anim").change(function() {
                    $("#datepickercompls").datepicker("option", "showAnim", $(this).val());
                });
                 $("#datepickercomplstodate").datepicker({changeYear: true, changeMonth: true});
                $("#anim").change(function() {
                    $("#datepickercomplstodate").datepicker("option", "showAnim", $(this).val());
                });
            });

        </script>



        <script>

        function validateYear()
            {
                if (document.getElementById("academicyear").value.length === 0)

                {
                    document.getElementById("mandatory").style.display = "";
                    
                    
                }


            }

            
            function hide(){
                
                document.getElementById("mandatory").style.display = "none";
                
            }



        </script>


<script type="text/javascript">

           
            function Update() {
                var form1 = document.getElementById("form1");
                form1.action = "/riyan/UserProcess/updateYear";
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
        <script type="text/javascript">
        
            $(function() {


                $("#save")
                        .button()
                        .click(function() {
                        	if($("#academicyear").val().length===0){
                        		return false;
                        	}
                        	if(confirm('Are you sure,you want to change the current academic year?')){
                        		changeYear();	
                        	}
                    


                });
                

                $("#update")
                        .button()
                        .click(function() {
                    Update();


                });
            });

            function changeYear() {

                var form1 = document.getElementById("form1");
                form1.action = "/riyan/YearProcess/saveYear";
                form1.submit();

            }
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
	response.sendRedirect("/riyan/login");
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
                        <li><a href="#tabs-1">Academic Year</a></li>
                        
                    </ul>



                    <div id="tabs-1">
                    
                    <div align="center">
                    
                    <h class="alignRightHeader">Enter the academic year</h>
                    
                    
                    </div>
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
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
							<td width="26%"  class="alignRightYear">
                             Current academic year is: ${currentyear}
                            </td>
							</tr>

<tr>
<td><br/></td>
</tr>
                            <tr>
                            
                            


                                <td width="26%"  class="alignRight" >
                                    
                                    Current Academic Year*&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  </td>

                                <td width="28%"  >
                                    <label>
                                        <label> <select name="academicyear" id="academicyear"
									style="width: 120px">
										<option selected></option>
										<option>2015/16</option>
										<option>2016/17</option>
										<option>2017/18</option>
										<option>2018/19</option>
										<option>2019/20</option>
										<option>2020/21</option>
										<option>2021/22</option>
										<option>2022/23</option>
										<option>2023/24</option>
										<option>2024/25</option>
								</select>

							</label> 
                                    </label>

                                </td>


                            </tr>

                            
                            <tr>
                                <td>
                                    <br/>
                                </td>
                            </tr>
                            


                            
                            <tr id="mandatory">
                                
                                <td width="16%"  class="mandatoryClass"  >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; * Please enter the current academic year</td>
                            </tr>
                            
                                <table width="100%" >
                                    <tr>

                                        <td>
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="center">

                                            <button id="save" onmouseover="validateYear();" >Confirm</button>

                                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                            <!-- <button id="update" >Update</button> -->

                                        </td>


                                    </tr>
                                </table>
                                
                                
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

                            </div>
                            
                    </div>
                </div>


        </form>
        
    </body>
</html>



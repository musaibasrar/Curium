<%-- 
    Document   : Enquiry Form
    Created on : Apr 12, 2025, 10:25:40 AM
    Author     : Musaib
--%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession"%>

<%@page import="java.util.*"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
    <head>
    <title>Enquiry Form - Online Application</title>

<!-- Modernized styles for an "online application" look. Kept input classes & IDs unchanged so existing JS/server logic continues to work. -->
<style>
    :root{
        --primary:#2E6E7F;
        --accent:#4FB0C6;
        --muted:#657e86;
        --card-bg: #ffffff;
        --page-bg: linear-gradient(180deg, #e9f3f6 0%, #ffffff 100%);
        --radius:10px;
    }

    html,body{
        height:100%;
        margin:0;
        padding:0;
        font-family: "Segoe UI", Roboto, Tahoma, Arial, sans-serif;
        background: var(--page-bg);
        color:var(--muted);
        -webkit-font-smoothing:antialiased;
        -moz-osx-font-smoothing:grayscale;
    }

    /* Centered container */
    .page-wrap{
        min-height:100vh;
        display:flex;
        align-items:center;
        justify-content:center;
        padding:40px 20px;
        box-sizing:border-box;
    }

    .application-card{
        width: 100%;
        max-width: 980px;
        background: var(--card-bg);
        border-radius: var(--radius);
        box-shadow: 0 10px 30px rgba(46,110,127,0.12);
        padding: 22px;
        box-sizing:border-box;
        border: 1px solid rgba(46,110,127,0.06);
    }

    .card-header{
        /* center the header content (logo & title) */
        display:flex;
        flex-direction:column;
        align-items:center;
        justify-content:center;
        margin-bottom:18px;
        gap:8px;
    }

    .card-logo img{
        vertical-align: text-bottom;
        height: 80px;
        width: 140px;
        display:block;
    }

    .card-title{
        font-size:20px;
        font-weight:700;
        color: var(--primary);
        letter-spacing:0.2px;
        text-align:center; /* ensure centered */
        width:100%;
    }

    .card-sub{
        font-size:13px;
        color:#7d9aa2;
        text-align:center;
    }

    /* Make table look like a form grid */
    #table1{
        width:100%;
        border-collapse:collapse;
        table-layout:fixed;
    }

    #table1 td{
        vertical-align: middle;
        padding:10px 8px;
        box-sizing:border-box;
    }

    /* Left columns for labels */
    #table1 td.alignLeft{
        width: 210px;
        font-weight:700;
        color:var(--primary);
        font-size:14px;
        text-transform:none;
        padding-right:12px;
    }

    /* Inputs: override myclass for nicer look but keep the class name */
    .myclass{
        display:inline-block;
        width: 100%;
        max-width: 100%;
        padding:7px 10px;
        font-size:14px;
        border-radius:6px;
        border:1px solid #d0dde0;
        background: #fff;
        box-shadow: inset 0 1px 0 rgba(0,0,0,0.02);
        box-sizing:border-box;
        outline: none;
        color:#123;
        height:36px;
    }

    textarea.myclass{
        min-height:80px;
        resize:vertical;
        padding:10px;
    }

    select.myclass{
        height:36px;
        padding:6px 8px;
    }

    /* Align gender checkboxes nicely */
    .gender-group{
        display:flex;
        gap:14px;
        align-items:center;
        font-weight:600;
        color:var(--muted);
    }
    .gender-group input{
        transform:scale(1.04);
        margin-left:6px;
    }

    /* Datepicker input */
    #datepicker{
        cursor:pointer;
        background-image: linear-gradient(180deg,#fff,#fbfeff);
    }

    /* Save button - smaller and centered */
    #saveenquiryform{
        width: 100px;                 /* decreased width */
        display: block;               /* center via margin */
        margin: 12px auto 0 auto;     /* center horizontally */
        background: linear-gradient(90deg,var(--primary),var(--accent));
        color:white;
        border: none;
        padding:8px 10px;             /* slightly smaller padding */
        border-radius:8px;
        font-size:14px;
        cursor:pointer;
        box-shadow: 0 6px 14px rgba(79,176,198,0.18);
        transition: transform .08s ease, box-shadow .12s ease, opacity .12s;
    }
    #saveenquiryform:hover{ transform: translateY(-1px); }
    #saveenquiryform:active{ transform: translateY(0); opacity:0.95; }

    /* Small helper spacing rows removed: hide empty BR rows visually */
    #table1 tr td br { display:none; }

    /* Responsive tweaks */
    @media (max-width:860px){
        #table1 td.alignLeft{ width:120px; font-size:13px; }
    }
    @media (max-width:620px){
        .application-card{ padding:16px; }
        #table1 td.alignLeft{ display:block; width:100%; padding-bottom:6px; }
        #table1 td{ display:block; width:100%; padding:6px 0; }
        #table1 tr{ display:block; margin-bottom:8px; border-bottom:1px dashed #eef6f7; padding-bottom:8px;}
        .card-header{ flex-direction:column; align-items:center; gap:6px; }
    }

    /* subtle label style for small helper classes preserved */
    .alignRight, .alignRightHead, .alignRightMultiple { color:var(--muted); font-weight:600; }

</style>

<link rel="stylesheet" href="/sky/css/datePicker/jquery-ui-1.8.18.custom.css">
<link rel="stylesheet" href="/sky/css/validation/jquery.ketchup.css">

<script type="text/javascript"
    src="/sky/js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
<script src="/sky/js/datePicker/jquery-1.7.1.js"></script>
<script src="/sky/js/datePicker/ui/jquery.ui.core.js"></script>
<script src="/sky/js/datePicker/ui/jquery.ui.widget.js"></script>
<script src="/sky/js/datePicker/ui/jquery.ui.datepicker.js"></script>
<script src="/sky/js/datePicker/ui/jquery.ui.tabs.js"></script>
<script src="/sky/js/datePicker/ui/sliderAccess.js"></script>
<script src="/sky/js/datePicker/ui/jquery-ui-timepicker-addon.js"></script>
<script src="/sky/js/validation/jquery.ketchup.all.min.js"></script>
<script type="text/javascript"
    src="/sky/js/datePicker/ui/jquery.ui.button.js"></script>
<link rel="stylesheet" href="/sky/css/datePicker/demos.css">

<style type="text/css">
.myclass {
    /* Keep original myclass present (this file overrides it above) */
    font-size: 1.3em;
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
    height: 28px;
    color: black;
    text-transform: capitalize;
    border-radius: 4px;
}
</style>
<script type="text/javascript">
    function saveEnquiryForm() {
        var form1 = document.getElementById("form1");
        if(form1.checkValidity()) {
        form1.action = "/sky/EnquiryProcess/saveEnquiryFormOnline";
        form1.method = "POST";
        form1.submit();
        }
    }
    $(function() {
        $("#tabs").tabs();
        $("#saveenquiryform").button().click(function() {
            saveEnquiryForm();
        });
    });
    function yesCheck(id) {
        if (id == "yes:male") {
            document.getElementById("no:male").checked = false;
        }
    }
    function noCheck(id) {
        if (id == "no:male") {
            document.getElementById("yes:male").checked = false;
        }
    }
</script>

    <script type="text/javascript" src="/sky/js/datetimepicker_css.js"></script>

    <script src="/sky/JavaScript/actb.js"></script>
    <script src="/sky/JavaScript/common.js"></script>




    <script>
        $(function() {
            $("#datepicker").datepicker({
                changeYear : true,
                changeMonth : true,
                dateFormat: 'dd/mm/yy',
                yearRange: "-50:+0"
            });
            $("#anim").change(function() {
                $("#datepicker").datepicker("option", "showAnim", $(this).val());
            });
        });
        
    </script>


    
    <script>
        $(function() {
            $("#tabs").tabs();
            $(".nexttab").click(function() {
                var selected = $("#tabs").tabs("option", "selected");
                $("#tabs").tabs("option", "selected", selected + 1);
            });
            $(".prevtab").click(function() {
                var selected = $("#tabs").tabs("option", "selected");
                $("#tabs").tabs("option", "selected", selected - 1);
            });
            
             $("#parentsannualincome").keypress(function (e) {
                 //if the letter is not digit then display error and don't type anything
                 if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
                           return false;
                }
               });
             
             $("#contactnumber").keypress(function (e) {
                 //if the letter is not digit then display error and don't type anything
                 if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
                           return false;
                }
               });
             
             $("#cocontactnumber").keypress(function (e) {
                 //if the letter is not digit then display error and don't type anything
                 if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
                           return false;
                }
               });
             
             $("#sts").keypress(function (e) {
                 //if the letter is not digit then display error and don't type anything
                 if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
                           return false;
                }
               });
             
             $("#noofdependents").keypress(function (e) {
                 //if the letter is not digit then display error and don't type anything
                 if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
                           return false;
                }
               });
        });


    </script>

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
    </head>
   <body>
        <form method="post"  id="form1">
        <div class="page-wrap">
            <div class="application-card">
                <div id="tabs">
                    <div class="card-header">
                        <div class="card-logo">
                            <img border="0" style="vertical-align: text-bottom;height: 80px;width: 71px;" alt="ideoholic" src="/sky/images/sky.png">
                        </div>
                        <div class="card-title">Admission Enquiry Form</div>
                    </div>

                    <ul style="display:none;">
                        <li><a href="#fragment-1">Admission Enquiry Form</a></li>
                    </ul>
                    
                    <div id="fragment-1">
                        <table style="width: 100%;height: auto;" border="0" align="center" id="table1">
                            <tr>
                                <td class="alignLeft">Name of the child</td>
                                <td><input type="text" name="name" class="myclass" required/></td>
                                <td class="alignLeft" style="padding-left: 20px;">Gender</td>
                                <td>
                                    <div class="gender-group">
                                        Male<input type="checkbox" value="Male" name="gender" id="yes:male" onclick="yesCheck(this.id);" /> 
                                        Female<input type="checkbox" value="Female" name="gender" id="no:male" onclick="noCheck(this.id)" />
                                    </div>
                                </td>
                            </tr>

                            <tr>
                                <td class="alignLeft">Previous Class Passed</td>
                                <td>
                                    <label> 
                                        <select name="previousclasspass" required
                                            id="previousclasspass" class="myclass" style="width: 100%;border-radius: 6px;background: white;height: 36px;width: 165px;">
                                                <option selected></option>
                                                <option value="I">I</option>
												<option value="II">II</option>
												<option value="III">III</option>
												<option value="IV">IV</option>
												<option value="V">V</option>
												<option value="VI">VI</option>
												<option value="VII">VII</option>
												<option value="VIII">VIII</option>
												<option value="IX">IX</option>
												<option value="X">X</option>
												<option value="XI">XI</option>
                                        </select>
                                    </label>
                                </td>
                                <td class="alignLeft" style="padding-left: 20px;">Previous school Name</td>
                                <td><input type="text" name="previousschoolname" class="myclass" required/></td>
                            </tr>

                            <tr>
                                <td class="alignLeft">Class to be Admitted</td>
                                <td>
                                    <label> 
                                        <select name="classadmittedin"  required
                                            id="classadmittedin" class="myclass" style="width: 100%;border-radius: 6px;background: white;height: 36px;width: 165px;">
                                                <option selected></option>
                                                <option value="I">I</option>
												<option value="II">II</option>
												<option value="III">III</option>
												<option value="IV">IV</option>
												<option value="V">V</option>
												<option value="VI">VI</option>
												<option value="VII">VII</option>
												<option value="VIII">VIII</option>
												<option value="IX">IX</option>
												<option value="X">X</option>
												<option value="XI">XI</option>
                                        </select>
                                    </label>
                                </td>
                                <td class="alignLeft" style="padding-left: 20px;">Academic Year</td>
                                <td>
                                    <select name="academicyear" id="academicyear" required class="myclass" style="width: 100%;border-radius: 6px;background: white;height: 36px;width: 165px;">
                                        <option selected></option>
                                        <option>2025/26</option>
                                        <option>2026/27</option>
                                        <option>2027/28</option>
                                        <option>2028/29</option>
                                        <option>2029/30</option>
                                        <option>2030/31</option>                                        
                                    </select>
                                </td>
                            </tr>

                            <tr>
                                <td class="alignLeft">Father's Name</td>
                                <td><input type="text" name="fathername" class="myclass"/></td>
                                 <td class="alignLeft"  style="padding-left: 20px;">Contact No</td>
                                <td><input type="text" name="contactno" class="myclass"/></td>
                                <td style="display: none;">
                                    <select name="branchid" id="branchid" required class="myclass" style="width: 100%;border-radius: 6px;background: white;height: 36px;width: 165px;display:none;">
                                        <option selected value="2">Brainy & Bright Academy</option>
                                    </select>
                                </td>
                            </tr>
                            
                            <!--  <tr>
                                <td class="alignLeft">Contact No</td>
                                <td><input type="text" name="contactno" class="myclass"/></td>
                                <td class="alignLeft" style="padding-left: 20px;">Parent Sig</td>
                                <td><input type="text" name="parentsign" class="myclass"/></td>
                            </tr>

                             <tr>
                                <td class="alignLeft">Mother's Name</td>
                                <td><input type="text" name="mothername" class="myclass"/></td>
                                <td class="alignLeft" style="padding-left: 20px;">Mother Qualification</td>
                                <td><input type="text" name="motherqualification" class="myclass"/></td>
                                <td class="alignLeft" style="padding-left: 20px;">Religion</td>
                                <td><input type="text" name="religion" class="myclass"/></td>
                            </tr>

                            <tr>
                                <td class="alignLeft">Elder/Younger<br> brother Education</td>
                                <td><input type="text" name="brothereducation" class="myclass"/></td>
                                <td class="alignLeft" style="padding-left: 20px;">Elder/Younger<br> Sister Education</td>
                                <td><input type="text" name="sistereducation" class="myclass"/></td>
                            </tr>

                            <tr>
                                <td class="alignLeft">Nature of <br>Profession/occupation</td>
                                <td><input type="text" name="occupation" class="myclass"/></td>
                                <td class="alignLeft" style="padding-left: 20px;">Father Qualification</td>
                                <td><input type="text" name="fatherqualification" class="myclass"/></td>
                            </tr>

                           <tr>
                                <td class="alignLeft">Permanent Address</td>
                                <td><textarea name="address" class="myclass"></textarea></td>
                                <td class="alignLeft" style="padding-left: 20px;">Notes</td>
                                <td><textarea name="notes" class="myclass"></textarea></td>
                            </tr>
                            
                            <tr>
                                <td class="alignLeft">Date of Birth</td>
                                <td><input name="dateofbirth" type="text" class="myclass" id="datepicker" size="20" autocomplete="false" onchange="CalculateAge(this)" data-validate="validate(required)"></td>
                                <td class="alignLeft" style="padding-left: 20px;">Caste</td>
                                <td><input type="text" name="caste" class="myclass"/></td>
                            </tr>

                            <tr>
                                <td class="alignLeft">Place of Birth</td>
                                <td><input type="text" name="placeofbirth" class="myclass"/></td>
                                <td class="alignLeft" style="padding-left: 20px;">Surname</td>
                                <td><input type="text" name="surname" class="myclass"/></td>
                            </tr> -->
                            <tr align="center">
                                <td class="alignRight">&nbsp;</td>
                                <td class="alignRight">&nbsp;</td>
                                <td align="center">
                                    <button id="saveenquiryform" class="save">Save</button>
                                </td>
                                <td colspan="2">&nbsp;</td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </form>
    </body>
    </html>
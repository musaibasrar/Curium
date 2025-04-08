<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
    <title>Enquiry Form</title>
<style>
   td{
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
<link rel="stylesheet" href="/roshan/css/datePicker/jquery-ui-1.8.18.custom.css">
<link rel="stylesheet" href="/roshan/css/validation/jquery.ketchup.css">
<script type="text/javascript"
	src="/roshan/js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
<script src="/roshan/js/datePicker/jquery-1.7.1.js"></script>
<script src="/roshan/js/datePicker/ui/jquery.ui.core.js"></script>
<script src="/roshan/js/datePicker/ui/jquery.ui.widget.js"></script>
<script src="/roshan/js/datePicker/ui/jquery.ui.datepicker.js"></script>
<script src="/roshan/js/datePicker/ui/jquery.ui.tabs.js"></script>
<script src="/roshan/js/datePicker/ui/sliderAccess.js"></script>
<script src="/roshan/js/datePicker/ui/jquery-ui-timepicker-addon.js"></script>
<script src="/roshan/js/validation/jquery.ketchup.all.min.js"></script>
<script type="text/javascript"
	src="/roshan/js/datePicker/ui/jquery.ui.button.js"></script>
<script type="text/javascript">
	function addadmissionform() {
		var form1 = document.getElementById("form1");
		form1.action = "/roshan/EnquiryProcess/addEnquiryForm";
		form1.method = "POST";
		form1.submit();

	}
	$(function() {

		$("#tabs").tabs();
		$("#saveadmissionform").button().click(function() {
			addadmissionform();
		});
		//$("#effect").hide();

	});
	
	</script>
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
    </head>
   <body>
        <form method="post"  id="form1">
        <table  align="center"  style="text-align: center;">
            <tr>
                <td style="font-weight: bold;font-size: 30px;">
                    Roshan Group of Institutes Kamthana
                </td>
            </tr>
            <tr>
                <td style="font-weight: bold;font-size: 30px;">
                    Admission Enquiry form - 2025/26
                </td>
            </tr>
        </table>
        <table align="center" >
            <tr>
                <td><br></td>
            </tr>
            <tr>
                <td><br></td>
            </tr>
            <tr>
                <td>Name of the child:</td><td><input type = "text" name= "name"/></td><td>Gender:</td>
                <td>Male<input
                    type="checkbox" value="Male" name="gender" id="yes:male"
                    onclick="yesCheck(this.id);" />&nbsp; &nbsp;Female<input
                    type="checkbox" value="Female" name="gender" id="no:male"
                    onclick="noCheck(this.id)" /></td>
            </tr>
            <tr>
                <td>Date of Birth</td><td><input name="dateofbirth"
									type="text" class="myclass" id="datepicker" size="20" autocomplete="false"
									onchange="CalculateAge(this)"
									data-validate="validate(required)"></td><td>Caste</td><td><input type="text" name = "caste"/></td>

            </tr>
            <tr>
                <td>Place of Birth:</td><td><input type="text" name="placeofbirth"/></td><td>Surname:</td><td><input type="text" name="surname"/></td>
            </tr>
            <tr>
                <td>Previous class pass:</td><td><input type="text" name="previousclasspass"/></td><td>Previous school Name:</td><td><input type="text" name="previousschoolname"/></td>
            </tr>
            <tr>
                <td>Class to be admitted:</td><td><input type="text" name="classadmittedin" /></td><td>Religion:</td><td><input type="text" name="religion"/></td>
            </tr>
            <tr>
                <td>Name of the Father:</td><td><input type="text" name="fathername" /></td><td>Father Qualification:</td><td><input type="text" name="fatherqualification" /></td>
            </tr>
            <tr>
                <td>Mother Name:</td><td><input type="text" name="mothername" /></td><td>Mother Qualification:</td><td><input type="text" name="motherqualification" /></td>
            </tr>
            <tr>
                <td>Elder/Younger brother Education:</td><td><input type="text" name="brothereducation" /></td><td>Elder/Younger Sister Education:</td><td><input type="text" name="sistereducation" /></td>
            </tr>
            <tr>
                <td>Nature of Profession/occupation:</td><td colspan="3"><input type="text" name="occupation" /></td>
            </tr>
            <tr>
                <td>Permanent Address:</td><td colspan="3"><textarea name="address"></textarea></td>
            </tr>
            <tr>
                <td>Contact No:</td><td><input type="text" name="contactno" /></td><td>Parent Sig:</td><td><input type="text" name="parentsign" /></td>
            </tr>
        </table>
        <div>
            <table align="center" >
                <tr>

                    <td><br /></td>
                </tr>
                <tr>
                    <td align="center">

                        <button id="saveadmissionform">Save</button>

                        
                    </td>


                </tr>
            </table>

        </div>
    </form>
    </body>
    </html>
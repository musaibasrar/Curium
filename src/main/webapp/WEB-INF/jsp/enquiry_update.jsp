<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
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
    
    @media print {
  button {
    display: none;
  }
}
    
</style>

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
	
	                            <script type="text/javascript">
                                $(function() {
                                   
                                    $( "#modify" )
                                    .button()
                                    .click(function() {
                                        updateEnquiryForm();

                                    });
                                   
                                });
                            </script>
	        <script type="text/javascript">
            
            function updateEnquiryForm(){
               
                var form1=document.getElementById("form1");
                form1.action="/roshan/EnquiryProcess/updateEnquiryFormDetails?id=<c:out value='${admissionEnquiry.id}'/>";
                form1.submit();
            }
            
            function typeofrelation(){
                var listitem = document.getElementById("subscriber");
                var listitemtext = listitem.options[listitem.selectedIndex].text;
                alert(listitemtext);
                
                if(listitemtext=="1"){
                    document.getElementById("typeofrelation").value="subscriber";
                    
                }
            }
            
      
        </script>
        
        <script type="text/javascript">
														
							
							function yesCheck(id) {

								if (document.getElementById(id).checked == true) {
									var splitId = id.split(':');
									document.getElementById('no:'+splitId[1]).checked = false;
								}

							}
							function noCheck(id) {

								if (document.getElementById(id).checked == true) {
									var splitId = id.split(':');
									document.getElementById('yes:'+splitId[1]).checked = false;
								}

							}

						</script>

	
	<title>Print Enquiry Form</title>
    </head>
   <body>
   <form  method="post" id="form1">
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
                <td>Name of the child:</td><td><input type = "text" name= "name" value="${admissionEnquiry.name}"/></td><td>Gender:</td>
                <td>Male<input
                    type="checkbox" value="Male" name="gender" id="yes:male"
                    onclick="yesCheck();"
					${admissionEnquiry.gender == 'Male' ? 'checked' : ''}/> &nbsp; &nbsp;Female<input
                    type="checkbox" value="Female" name="gender" id="no:male"
                    onclick="noCheck()"
					${admissionEnquiry.gender == 'Female' ? 'checked' : ''}/></td>
            </tr>
            <tr>
                <td>Date of Birth</td><td><input name="dateofbirth"
									type="text" class="myclass" id="datepicker" size="20" autocomplete="false"
									onchange="CalculateAge(this)"
									data-validate="validate(required)"  value="${admissionEnquiry.dateofbirth}"></td><td>Caste</td><td><input type="text" name = "caste" value="${admissionEnquiry.caste}"/></td>

            </tr>
            <tr>
                <td>Place of Birth:</td><td><input type="text" name="placeofbirth" value="${admissionEnquiry.placeOfBirth}"/></td><td>Surname:</td><td><input type="text" name="surname" value="${admissionEnquiry.surName}"/></td>
            </tr>
            <tr>
                <td>Previous class pass:</td><td><input type="text" name="previousclasspass" value="${admissionEnquiry.previousClassPassed}"/></td><td>Previous school Name:</td><td><input type="text" name="previousschoolname" value="${admissionEnquiry.previousSchoolName}"/></td>
            </tr>
            <tr>
                <td>Class to be admitted:</td><td><input type="text" name="classadmittedin" value="${admissionEnquiry.admissionclass}" /></td><td>Religion:</td><td><input type="text" name="religion" value="${admissionEnquiry.religion}"/></td>
            </tr>
            <tr>
                <td>Name of the Father:</td><td><input type="text" name="fathername" value="${admissionEnquiry.fathername}" /></td><td>Father Qualification:</td><td><input type="text" name="fatherqualification" value="${admissionEnquiry.fatherQualification}" /></td>
            </tr>
            <tr>
                <td>Mother Name:</td><td><input type="text" name="mothername" value="${admissionEnquiry.mothername}" /></td><td>Mother Qualification:</td><td><input type="text" name="motherqualification" value="${admissionEnquiry.motherQualification}" /></td>
            </tr>
            <tr>
                <td>Elder/Younger brother Education:</td><td><input type="text" name="brothereducation" value="${admissionEnquiry.brothereducation}" /></td><td>Elder/Younger Sister Education:</td><td><input type="text" name="sistereducation" value="${admissionEnquiry.sistereducation}" /></td>
            </tr>
            <tr>
                <td>Nature of Profession/occupation:</td><td><input type="text" name="occupation" value="${admissionEnquiry.occupation}" /></td><td>Academic Year:</td><td><input type="text" name="academicyear" value="${admissionEnquiry.academicYear}" /></td>
            </tr>
            <tr>
                <td>Permanent Address:</td><td><textarea name="address" >${admissionEnquiry.address}</textarea></td><td>Notes:</td><td><textarea name="notes">${admissionEnquiry.notes}</textarea></td>
            </tr>
            <tr>
                <td>Contact No:</td><td><input type="text" name="contactno" value="${admissionEnquiry.mobileno}" /></td><td>Parent Sig:</td><td><input type="text" name="parentsign" /></td>
            </tr>
        </table>
        <div>
            <table align="center" >
                <tr>

                    <td><br /></td>
                </tr>
                <tr>
                    <td align="center">

                        <button id="modify" >Update</button> 

                        
                    </td>


                </tr>
            </table>

        </div>
        </form>
    </body>
    </html>
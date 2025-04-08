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
	<title>Print Enquiry Form</title>
    </head>
   <body>
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
                <td>Name of the child:</td><td>${admissionEnquiry.name}</td><td>Gender:</td>
                <td>${admissionEnquiry.gender}</td>
            </tr>
            <tr>
                <td><br></td>
            </tr>
            
            <tr>
                <td>Date of Birth</td><td>${admissionEnquiry.dateofbirth}</td><td>Caste</td><td>${admissionEnquiry.caste}</td>

            </tr>
            <tr>
                <td><br></td>
            </tr>
            
            <tr>
                <td>Place of Birth:</td><td>${admissionEnquiry.placeOfBirth}</td><td>Surname:</td><td>${admissionEnquiry.surName}</td>
            </tr>
            <tr>
                <td><br></td>
            </tr>
            
            <tr>
                <td>Previous class pass:</td><td>${admissionEnquiry.previousClassPassed}</td><td>Previous school Name:</td><td>${admissionEnquiry.previousSchoolName}</td>
            </tr>
            <tr>
                <td><br></td>
            </tr>
            
            <tr>
                <td>Class to be admitted:</td><td>${admissionEnquiry.admissionclass}</td><td>Religion:</td><td>${admissionEnquiry.religion}</td>
            </tr>
            <tr>
                <td><br></td>
            </tr>
            
            <tr>
                <td>Name of the Father:</td><td>${admissionEnquiry.fathername}</td><td>Father Qualification:</td><td>${admissionEnquiry.fatherQualification}</td>
            </tr>
            <tr>
                <td><br></td>
            </tr>
            
            <tr>
                <td>Mother Name:</td><td>${admissionEnquiry.mothername}</td><td>Mother Qualification:</td><td>${admissionEnquiry.motherQualification}</td>
            </tr>
            <tr>
                <td><br></td>
            </tr>
            
            <tr>
                <td>Elder/Younger brother Education:</td><td>${admissionEnquiry.brothereducation}</td><td>Elder/Younger Sister Education:</td><td>${admissionEnquiry.sistereducation}</td>
            </tr>
            <tr>
                <td><br></td>
            </tr>
            
            <tr>
                <td>Nature of Profession/occupation:</td><td colspan="3">${admissionEnquiry.occupation}</td>
            </tr>
            <tr>
                <td><br></td>
            </tr>
            
            <tr>
                <td>Permanent Address:</td><td colspan="3">${admissionEnquiry.address}</td>
            </tr>
            <tr>
                <td><br></td>
            </tr>
            
            <tr>
                <td>Contact No:</td><td>${mobileno}</td><td>Parent Sig:</td><td>${placeOfBirth}</td>
            </tr>
        </table>
        <div>
            <table align="center" >
                <tr>

                    <td><br /></td>
                </tr>
                <tr>
                    <td align="center">

                        <button onclick="window.print()">Print</button>

                        
                    </td>


                </tr>
            </table>

        </div>
    </body>
    </html>
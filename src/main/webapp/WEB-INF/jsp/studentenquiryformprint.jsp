<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<title>Admission Enquiry Form</title>

<style>
body {
    font-family: Arial, sans-serif;
}

.form-container {
    width: 800px;
    margin: auto;
    border: 2px solid #000;
}

.form-header {
    text-align: center;
    border-bottom: 2px solid #000;
    padding: 10px;
}

.form-header img {
    float: left;
}

.form-title {
    font-size: 22px;
    font-weight: bold;
}

.form-subtitle {
    font-size: 16px;
    font-weight: bold;
    margin-top: 5px;
}

.form-row {
    display: flex;
    border-bottom: 1px solid #000;
}

.form-label {
    width: 40%;
    padding: 8px;
    border-right: 1px solid #000;
    font-weight: bold;
}

.form-value {
    width: 60%;
    padding: 8px;
}

.section-title {
    background: #f2f2f2;
    padding: 8px;
    font-weight: bold;
    border-top: 2px solid #000;
    border-bottom: 1px solid #000;
}

.buttons {
    text-align: center;
    padding: 15px;
}

@media print {
    .buttons {
        display: none;
    }
}
</style>

<script>
function updateEnquiryForm(){
    var form1=document.getElementById("form1");
    form1.method = "POST";
    form1.action="/littleflower/EnquiryProcess/updateEnquiryDetails?id=<c:out value='${admissionEnquiry.id}'/>";
    form1.submit();
}
</script>

</head>

<body>

<form id="form1">

<div class="form-container">

    <!-- HEADER -->
    <div class="form-header">
        <img src="/littleflower/images/littleflower.jpg" width="70"/>
        <div class="form-title">LITTLE FLOWER PUBLIC SCHOOL</div>
        <div>${branchaddress}</div>
        <div>${branchcontact}</div>
        <div class="form-subtitle">
            ENQUIRY FORM - ${admissionEnquiry.academicYear}
        </div>
    </div>
    
    <div class="section-title">Class to which admission is being sought: ${admissionEnquiry.admissionclass}</div>

    <!-- STUDENT DETAILS -->
    <div class="section-title">Student Details</div>

    <div class="form-row">
        <div class="form-label">Student Name</div>
        <div class="form-value">${admissionEnquiry.name}</div>
    </div>

    <div class="form-row">
        <div class="form-label">Date of Birth</div>
        <div class="form-value"><fmt:formatDate value="${admissionEnquiry.dateofbirth}" pattern="dd/MM/yyyy"/></div>
    </div>

    <div class="form-row">
        <div class="form-label">Gender</div>
        <div class="form-value">${admissionEnquiry.gender}</div>
    </div>
    
     <div class="form-row">
        <div class="form-label">Age</div>
        <div class="form-value">${admissionEnquiry.occupation}</div>
    </div>

    <!-- PARENT DETAILS -->
    <div class="section-title">Parent Details</div>

    <div class="form-row">
        <div class="form-label">Father's Name</div>
        <div class="form-value">${admissionEnquiry.fathername}</div>
    </div>

    <div class="form-row">
        <div class="form-label">Father Qualification</div>
        <div class="form-value">${admissionEnquiry.fatherQualification}</div>
    </div>

    <div class="form-row">
        <div class="form-label">Father Occupation</div>
        <div class="form-value">${admissionEnquiry.placeOfBirth}</div>
    </div>

    <div class="form-row">
        <div class="form-label">Mother's Name</div>
        <div class="form-value">${admissionEnquiry.mothername}</div>
    </div>

    <div class="form-row">
        <div class="form-label">Mother Qualification</div>
        <div class="form-value">${admissionEnquiry.motherQualification}</div>
    </div>

    <div class="form-row">
        <div class="form-label">Mother Occupation</div>
        <div class="form-value">${admissionEnquiry.surName}</div>
    </div>

    <!-- OTHER DETAILS -->
    <div class="section-title">Other Details</div>

    <div class="form-row">
        <div class="form-label">Religion</div>
        <div class="form-value">${admissionEnquiry.religion}</div>
    </div>

    <div class="form-row">
        <div class="form-label">Caste</div>
        <div class="form-value">${admissionEnquiry.caste}</div>
    </div>

    <div class="form-row">
        <div class="form-label">Previous Class Passed</div>
        <div class="form-value">${admissionEnquiry.previousClassPassed}</div>
    </div>

    <div class="form-row">
        <div class="form-label">Previous School Name</div>
        <div class="form-value">${admissionEnquiry.previousSchoolName}</div>
    </div>

    <div class="form-row">
        <div class="form-label">Address</div>
        <div class="form-value">${admissionEnquiry.address}</div>
    </div>

    <div class="form-row">
        <div class="form-label">Contact Number</div>
        <div class="form-value">${admissionEnquiry.mobileno}</div>
    </div>

    <div class="form-row">
        <div class="form-label">Sibling Details</div>
        <div class="form-value">
            Brother: ${admissionEnquiry.brothereducation} <br/>
            Sister: ${admissionEnquiry.sistereducation}
        </div>
    </div>

    <%-- <div class="form-row">
        <div class="form-label">Nature of Profession</div>
        <div class="form-value">${admissionEnquiry.occupation}</div>
    </div> --%>

    <!-- BUTTONS -->
    <div class="buttons">
        <button type="button" onclick="window.print()">Print</button>
        <button type="button" onclick="updateEnquiryForm()">Modify</button>
    </div>

</div>

</form>

</body>
</html>
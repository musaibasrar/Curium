<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<meta charset="UTF-8">
<title>Admission Form</title>

<style>

* {
    box-sizing: border-box;
}

body {
    margin: 0;
    padding: 20px;
    font-family: Arial, Helvetica, sans-serif;
    background: #f2f2f2;
}

.admission-form {
    width: 800px;
    margin: auto;
    background: #ffffff;
    padding: 10px;
    border: 2px solid #333;
    border-radius: 15px;
}

/* ================= HEADER ================= */

.school-header {
    border: 2px solid #333;
    border-radius: 12px;
    padding: 10px;
    text-align: center;
    position: relative;
    margin-bottom: 5px;
}

.logo {
    position: absolute;
    left: 20px;
    top: 10px;
    width: 110px;
    height: 100px;
    border: 1px solid #555;
    border-radius: 50%;
    display: flex;
    align-items: center;
    justify-content: center;
    font-weight: bold;
    color: #444;
}

.school-name {
    font-family: "Times New Roman", serif;
    font-size: 38px;
    font-weight: bold;
    letter-spacing: 2px;
}

.school-subtitle {
    font-size: 20px;
    font-weight: bold;
    margin-top: 5px;
}

.school-address {
    font-size: 16px;
    margin-top: 4px;
}

.school-phone {
    font-size: 16px;
    font-weight: bold;
}

/* ================= TITLE ================= */

.form-title {
    width: 260px;
    margin: 5px auto 10px auto;
    background: #3d477c;
    color: white;
    text-align: center;
    padding: 5px;
    font-size: 24px;
    font-weight: bold;
    border-radius: 5px;
}

/* ================= TOP SECTION ================= */

.top-section {
    width: 100%;
}

.top-section td {
    vertical-align: top;
}

.form-no-table {
    width: 100%;
    border-collapse: collapse;
}

.form-no-table td {
    padding: 6px;
}

.input-box {
    border: 1px solid #444;
    height: 28px;
    width: 100%;
}

.small-input {
    width: 120px;
}

.photo-box {
    width: 120px;
    height: 145px;
    border: 1px solid #333;
    text-align: center;
    vertical-align: middle;
    //padding-top: 45px;
    font-size: 14px;
}

/* ================= NAME BOX ================= */

.name-table {
    width: 100%;
    border-collapse: collapse;
    margin-top: 5px;
}

.name-table td {
    padding: 3px;
}

.name-label {
    width: 130px;
    font-size: 15px;
}

.letter-box-container {
    width: 100%;
}

.letter-box {
    width: 100%;
    border-collapse: collapse;
}

.letter-box td {
    border: 1px solid #444;
    height: 24px;
    width: 4%;
}

/* ================= DETAILS ================= */

.details-table {
    width: 100%;
    border-collapse: collapse;
    margin-top: 5px;
}

.details-table td {
    padding: 3px 4px;
    vertical-align: middle;
}

.label {
    width: 140px;
    font-size: 15px;
}

.text-field {
    border: 1px solid #333;
    height: 27px;
    width: 100%;
}

.checkbox-box {
    width: 100px;
    height: 27px;
    border: 1px solid #333;
    display: inline-block;
}

.note-text {
    font-size: 12px;
    padding-left: 10px;
}

/* ================= FULL WIDTH ROWS ================= */

.full-table {
    width: 100%;
    border-collapse: collapse;
}

.full-table td {
    padding: 3px;
}

.full-label {
    width: 185px;
    font-size: 15px;
}

.full-input {
    width: 100%;
    height: 27px;
    border: 1px solid #333;
}

/* ================= ADDRESS ================= */

.address-table {
    width: 100%;
    border-collapse: collapse;
}

.address-table td {
    padding: 4px;
}

.address-label {
    width: 130px;
    vertical-align: top;
    font-size: 15px;
}

.address-box {
    width: 100%;
    height: 45px;
    border: 1px solid #333;
    padding: 5px;
}

/* ================= MOBILE ================= */

.mobile-table {
    width: 100%;
    border-collapse: collapse;
}

.mobile-table td {
    padding: 4px;
}

/* ================= SCHOOL ================= */

.school-table {
    width: 100%;
    border-collapse: collapse;
    margin-top: 5px;
}

.school-table td {
    padding: 4px;
}

.school-label {
    width: 135px;
    vertical-align: top;
    font-size: 14px;
}

/* ================= BOTTOM ================= */

.bottom-section {
    width: 100%;
    margin-top: 15px;
}

.bottom-section td {
    vertical-align: top;
    padding: 5px;
}

.signature-section {
    margin-top: 15px;
}

.signature-section p {
    margin: 10px 0;
}

/* ================= PRINT ================= */

@media print {

    body {
        background: white;
        padding: 0;
    }

    .admission-form {
        width: 100%;
        border: none;
        margin: 0;
        padding: 5px;
    }

}

</style>

</head>

<body>

<div class="admission-form">

    <!-- SCHOOL HEADER -->

    <div class="school-header">

        <div class="logo">
            <img alt="Govt Of Karnataka" src="/hira/images/hira.png"  width="120" height="119" >
        </div>

        <div class="school-name">
            HIRA PUBLIC SCHOOL
        </div>

        <div class="school-subtitle">
            (Run by Hira Welfare Trust®)
        </div>

        <div class="school-address">
            Tamsa Masjid Road, Areahalli - 573 101, Hassan Dist.
        </div>

        <div class="school-phone">
            Phone No.: 08177 - 221658
        </div>

    </div>


    <!-- FORM TITLE -->

    <div class="form-title">
        ADMISSION FORM
    </div>


    <!-- FORM NUMBER SECTION -->

    <table class="top-section">

        <tr>

            <td style="width:75%;">

                <table class="form-no-table">

                    <tr>
                        <td style="width:130px;">Form No.</td>
                        <td>
                            <div class="input-box small-input"></div>
                        </td>
                    </tr>

                    <tr>
                        <td>Admission No.</td>
                        <td>
                            <div class="input-box small-input"><c:out value="${parents.student.admissionnumber}"/></div>
                        </td>
                    </tr>

                    <tr>
                        <td>Admission To Class</td>
                        <td>
                            <div class="input-box small-input"><c:out value="${parents.student.classstudying}"/></div>
                        </td>

                        <td>SATS No.</td>

                        <td>
                            <div class="input-box"><c:out value="${parents.student.sts}"/></div>
                        </td>
                    </tr>

                </table>

            </td>


            <td style="width:25%; text-align:center;">

                <div class="photo-box">
                  <img src="data:image;base64,<c:out value="${parents.student.studentpic}"/>" alt="Student's Photo" style="width: 120px;height: 145px;">
                </div>

            </td>

        </tr>

    </table>


    <div class="note-text">
        Note : Incomplete application will not be considered.
    </div>


    <!-- NAME -->

    <table class="name-table">

        <tr>

            <td class="name-label">
                Name :<br>
                (in block letters)
            </td>

            <td>

               <c:set var="studentName" value="${parents.student.name}" />

<table class="letter-box">
    <tr>

        <c:forEach begin="0" end="19" var="i">

            <td style="text-align:center">
                <c:if test="${i < fn:length(studentName)}">
                    ${fn:substring(studentName, i, i + 1)}
                </c:if>
            </td>

        </c:forEach>

    </tr>
</table>

            </td>

        </tr>


        <tr>

            <td class="name-label">
                Father's Name :
            </td>

            <td>

                <c:set var="fatherName" value="${parents.fathersname}" />

<table class="letter-box">
    <tr>

        <c:forEach begin="0" end="19" var="i">

            <td style="text-align:center">
                <c:if test="${i < fn:length(fatherName)}">
                    ${fn:substring(fatherName, i, i + 1)}
                </c:if>
            </td>

        </c:forEach>

    </tr>
</table>

            </td>

        </tr>


        <tr>

            <td class="name-label">
                Mother's Name :
            </td>

            <td>

                <c:set var="motherName" value="${parents.mothersname}" />

<table class="letter-box">
    <tr>

        <c:forEach begin="0" end="19" var="i">

            <td style="text-align:center">
                <c:if test="${i < fn:length(motherName)}">
                    ${fn:substring(motherName, i, i + 1)}
                </c:if>
            </td>

        </c:forEach>

    </tr>
</table>

            </td>

        </tr>

    </table>


    <!-- SEX AND DOB -->

    <table class="details-table">

        <tr>

            <td class="label">Sex :</td>

            <td style="width:50px;"></td>

            <td style="width:100px;">
                <div class="checkbox-box"><c:out value="${parents.student.gender}" /></div>
            </td>

            <td style="width:65px;"></td>

            <td style="width:50px;">
               <!--  <div class="checkbox-box"></div> -->
            </td>

            <td style="width:120px;">
                Date of Birth
            </td>

            <td>
                <div class="text-field"><c:out value="${parents.student.dateofbirth}" /></div>
            </td>

        </tr>

    </table>


    <!-- DETAILS -->

    <table class="details-table">

        <tr>

            <td class="label">Nationality :</td>

            <td>
                <div class="text-field"><c:out value="${parents.student.nationality}" /></div>
            </td>

            <td class="label">Place of Birth :</td>

            <td>
                <div class="text-field"><c:out value="${parents.student.placeofbirth}" /></div>
            </td>

        </tr>


        <tr>

            <td class="label">Religion :</td>

            <td>
                <div class="text-field"><c:out value="${parents.student.religion}" /></div>
            </td>

            <td class="label">Mother Tongue :</td>

            <td>
                <div class="text-field"><c:out value="${parents.student.mothertongue}" /></div>
            </td>

        </tr>


        <tr>

            <td class="label">Caste :</td>

            <td>
                <div class="text-field"><c:out value="${parents.student.caste}" /></div>
            </td>

            <td class="label">Languages :</td>

            <td>
                <div class="text-field"><c:out value="${parents.student.languagesstudied}" /></div>
            </td>

        </tr>


        <tr>

            <td class="label">Sub Caste :</td>

            <td>
                <div class="text-field"><c:out value="${parents.student.studentscaste}" /></div>
            </td>

            <td></td>

            <td>
                <div class="text-field"></div>
            </td>

        </tr>


        <tr>

            <td class="label">Category :</td>

            <td>
                <div class="text-field"><c:out value="${parents.student.socialcategory}" /></div>
            </td>

            <td></td>

            <td>
                <div class="text-field"></div>
            </td>

        </tr>


        <tr>

            <td class="label">Blood Group :</td>

            <td>
                <div class="text-field"><c:out value="${parents.student.bloodgroup}" /></div>
            </td>

            <td class="label">Aadhar Card No. :</td>

            <td>
                <div class="text-field"><c:out value="${parents.student.disabilitychild}" /></div>
            </td>

        </tr>

    </table>


    <!-- PARENT QUALIFICATION -->

    <table class="full-table">

        <tr>

            <td class="full-label">
                Father's / Guardian's Qualification & Occupation :
            </td>

            <td>
                <div class="full-input"><c:out value="${parents.fathersqualification}" /></div>
            </td>

        </tr>


        <tr>

            <td class="full-label">
                Mother's Qualification & Occupation :
            </td>

            <td>
                <div class="full-input"><c:out value="${parents.mothersqualification}" /></div>
            </td>

        </tr>

    </table>


    <!-- ADDRESS -->

    <table class="address-table">

        <tr>

            <td class="address-label">
                Address of the<br>
                Parent / Guardian :
            </td>

            <td>

                <div class="address-box">
                    Current Address :<c:out value="${parents.addresstemporary}" />
                </div>

                <div class="address-box" style="margin-top:5px;">
                    Permanent Address :<c:out value="${parents.addresspermanent}" />
                </div>

            </td>

        </tr>

    </table>


    <!-- MOBILE -->

    <table class="mobile-table">

        <tr>

            <td style="width:140px;">
                Mobile Number :
            </td>

            <td>
                Father
                <div class="text-field" style="width:120px; display:inline-block;"><c:out value="${parents.contactnumber}" /></div>
            </td>

            <td>
                Mother
                <div class="text-field" style="width:110px; display:inline-block;"><c:out value="${parents.cocontactnumber}" /></div>
            </td>

            <td>
                Whatsapp
                <div class="text-field" style="width:110px; display:inline-block;"><c:out value="${parents.contactnumber}" /></div>
            </td>

        </tr>

    </table>


    <!-- IDENTIFICATION MARK -->

    <table class="full-table">

        <tr>

            <td class="full-label">
                Identification Mark :
            </td>

            <td>
                <div class="full-input"></div>
            </td>

        </tr>

    </table>


    <!-- PREVIOUS SCHOOL -->

    <table class="school-table">

        <tr>

            <td class="school-label" rowspan="5">
                School Previous attended:
            </td>

            <td>
                <div class="full-input">
                    Name : <c:out value="${parents.student.schoollastattended}" />
                </div>
            </td>

        </tr>


        <tr>

            <td>
                <div class="full-input">
                    Address : <c:out value="${parents.student.previouschooladdress}" />
                </div>
            </td>

        </tr>


        <tr>

            <td>
                <div class="full-input">
                    Standard in which he/she was studied :<c:out value="${parents.student.stdlaststudied}" />
                </div>
            </td>

        </tr>


        <tr>

            <td>
                <div class="full-input">
                    Examination passed/failed :
                </div>
            </td>

        </tr>


        <tr>

            <td>
                <div class="full-input">
                    T.C. & Marks Card on Last to be enclosed
                </div>
            </td>

        </tr>


        <tr>

            <td></td>

            <td>
                <div class="full-input">
                    Reason for leaving previous school:
                </div>
            </td>

        </tr>

    </table>


    <!-- BOTTOM -->

    <table class="bottom-section">

        <tr>

            <td style="width:60%;">

                Other brothers & sister<br>
                studying in the school :

            </td>


            <td style="width:40%;">

                <div class="signature-section">

                    <strong>
                        Signature of the Parent / guardian
                    </strong>

                    <p>Name : __________________________</p>

                    <p>Relation : ________________________</p>

                    <p>Contact No. : _____________________</p>

                </div>

            </td>

        </tr>


        <tr>

            <td>
                Date : ____________________
            </td>

            <td></td>

        </tr>

    </table>

</div>

</body>
</html>
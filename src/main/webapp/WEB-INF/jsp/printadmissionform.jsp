<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<title>Admission Form</title>

<style>
body{
    font-family: Arial, Helvetica, sans-serif;
    
}

.container{
    width:800px;   /* instead of 900px */
    margin:auto;
    background-color: #f2dea7;
    padding:15px;
    box-sizing:border-box;
}

.header{
    display:flex;
    justify-content:space-between;
    align-items:center;
}

.logo{
    font-size:24px;
    font-weight:bold;
    color:#2c5aa0;
}

.partner{
    text-align:right;
    font-size:14px;
}

.title{
    text-align:center;
    font-size:20px;
    font-weight:bold;
    margin:15px 0;
}

.photo-box{
    border:1px solid #000;
    width: 100px;
    height: 120px;
    text-align:center;
    font-size:12px;
    float:right;
    padding:5px;
}
.courses{
 float:left;
}

table{
    width:100%;
    border-collapse:collapse;
    margin-top:10px;
}

td{
    border:1px solid #ccc;
    padding:5px;
    font-size:12px;
}

.label{
    width:200px;
    font-weight:bold;
    background:#f9f9f9;
}

.section-title{
    background:#e6e6e6;
    font-weight:bold;
    text-align:left;
}

input{
   // width:100%;
    border:none;
    outline:none;
}

.footer{
    margin-top:20px;
    font-size:12px;
}

.signature{
    margin-top:30px;
    text-align:right;
}
.print-btn{
    text-align:center;
    margin-top:20px;
}

.print-btn button{
    padding:10px 20px;
    font-size:14px;
    background:#2c5aa0;
    color:white;
    border:none;
    cursor:pointer;
    border-radius:5px;
}

/* Hide button while printing */
@page {
    size: A4;
    margin: 10mm;
}
@media print {
    .print-btn {
        display:none;
    }

    body{
        background:white;
        margin: 0;
        padding: 0;
    }

    .container{
        border:1px solid black;
        width:100%;
        //padding: 10px;
        box-sizing: border-box;
        // background-color: #B6BAA4;
        
    }
}
</style>
<script>
function printForm() {
    window.print();
}
</script>
</head>

<body>

<div class="container">

    <div class="header" >
        <div class="logo" style="width:85%">
           <img alt="school logo" src="/bba/images/logo.png" style="width: 175px;height: 100px;">
          BRAINY AND BRIGHT ACADEMY
        </div>
        <div class="partner" style="width:15%">
            <img alt="gravity logo" src="/bba/images/gravity.jpg" style="width: 120px;height: 80px;">
        </div>
    </div>

    <div class="title">ADMISSION FORM</div>
 <div class="courses">
 <table>
 <tr>
 <td style="border:0px solid #ccc;">
 <br>
 </td>
 </tr>
 <tr>
 <td style="border:0px solid #ccc;">
 <br>
 </td>
 </tr>
  <tr>
 <td style="border:0px solid #ccc;">
 <br>
 </td>
 </tr>
 </table>
  <c:forEach var="splt" items="${fn:split(student.classstudying,'--')}">
						   &emsp; ${splt}
							</c:forEach>
    </div>
    <div class="photo-box">
        <img src="data:image;base64,<c:out value="${student.studentpic}"/>" alt="Student's Photo" style="width: 100px;height: 120px;">
    </div>

    <table>
        <tr>
            <td class="section-title" colspan="4">Personal Details</td>
        </tr>

        <tr>
            <td class="label">Student Name</td>
            <td colspan="3"><c:out value="${parents.student.name}" /></td>
        </tr>

        <tr>
            <td class="label">Aadhar No</td>
            <td colspan="3">1234 5678 9876</td>
        </tr>

        <tr>
            <td class="label">Father's Name</td>
            <td colspan="3"> <c:out value="${parents.fathersname}" /></td>
        </tr>

        <tr>
            <td class="label">Mother's Name</td>
            <td colspan="3"><c:out value="${parents.mothersname}" /></td>
        </tr>

        <tr>
            <td class="label">Occupation (Father)</td>
            <td><c:out value="${parents.fatherscastecertno}" /></td>
            <td class="label">Occupation (Mother)</td>
            <td><c:out value="${parents.motherscastecertno}" /></td>
        </tr>

        <tr>
            <td class="label">Date of Birth</td>
            <td><fmt:formatDate value="${parents.student.dateofbirth}" pattern="dd/MM/yyyy"/></td>
            <td class="label">Gender</td>
            <td><c:out value="${parents.student.gender}" /></td>
        </tr>

        <tr>
            <td class="label">Blood Group</td>
            <td><c:out value="${parents.student.bloodgroup}" /></td>
            <td class="label">Category</td>
            <td><c:out value="${parents.student.caste}" /></td>
        </tr>

        <tr>
            <td class="label">Nationality</td>
            <td colspan="3"><c:out value="${parents.student.nationality}" /></td>
        </tr>

        <tr>
            <td class="section-title" colspan="4">Address</td>
        </tr>

        <tr>
            <td class="label">Permanent Address</td>
            <td colspan="3"><c:out value="${parents.addresspermanent}" /></td>
        </tr>

      

        <tr>
            <td class="label">Email</td>
            <td><c:out value="${parents.email}" /></td>
            <td class="label">Mobile/what's app No.</td>
            <td><c:out value="${parents.contactnumber}" /></td>
        </tr>

      
         <tr>
            <td class="label">Correspondence Address</td>
            <td colspan="3"><c:out value="${parents.addresstemporary}" /></td>
        </tr>

      

        <tr>
            <td class="label">Email</td>
            <td><c:out value="${parents.email}" /></td>
            <td class="label">Mobile/what's app No.</td>
            <td><c:out value="${parents.contactnumber}" /></td>
        </tr>

             <tr>
            <td class="section-title" colspan="4">Last Class Details</td>
        </tr>

        <tr>
            <td class="label">School/College Name</td>
            <td colspan="3"><c:out value="${parents.student.schoollastattended}" /></td>
        </tr>

        <tr>
            <td class="label">Class</td>
            <td><c:out value="${parents.student.stdlaststudied}" /></td>
            <td class="label">Marks (%)</td>
            <td><c:out value="${parents.student.languagesstudied}" /></td>
        </tr>

        <tr>
            <td class="label">Exam Board</td>
            <td colspan="3"><c:out value="${parents.student.subsequentprogress}" /></td>
        </tr>

         <tr>
            <td class="section-title" colspan="4">How did you know?</td>
            
        </tr>
        <tr>
           
            <!-- <td > Newspaper<input type="checkbox"> </td>
            <td > Teacher<input type="checkbox"> </td>
            <td > Friends <input type="checkbox"> </td>
            <td > Website <input type="checkbox"></td> -->
            
            <td>Newspaper<input type="checkbox"
		value="Newspaper" name="remarks" id="yes:Newspaper"
		${parents.student.remarks == 'Newspaper' ? 'checked' : ''} /></td><td>&nbsp;
		&nbsp;Teacher<input type="checkbox" value="Teacher" name="remarks"
		id="no:Teacher" onclick="noCheck(this.id);"
		${parents.student.remarks == 'Teacher' ? 'checked' : ''} /></td><td>
		Friends<input type="checkbox"
		value="Friends" name="remarks" id="yes:Friends"
		${parents.student.remarks == 'Friends' ? 'checked' : ''} /></td><td>&nbsp;
		&nbsp;Website<input type="checkbox" value="Website" name="remarks"
		id="no:Website" onclick="noCheck(this.id);"
		${parents.student.remarks == 'Website' ? 'checked' : ''} /></td>
		
        </tr>
<tr><td><br></td></tr>
<tr><td><br></td></tr>
<tr><td><br></td></tr>

    </table>

    <div class="signature">
        Signature of Student
    </div>

    <div class="footer">
        Address: Sherwani Nagar, Sitapur Road, Lucknow<br>
        Phone: 9161399292 | Email: brainyacademy@gmail.com
    </div>

    <div class="print-btn">
    <button onclick="printForm()"> Print Form</button>
</div> 

</div>

</body>
</html>
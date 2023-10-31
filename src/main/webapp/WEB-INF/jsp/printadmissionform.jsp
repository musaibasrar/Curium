<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admission Form</title>

<style>

h3{
	margin-left: 400px;
	margin-right: 30px;
	margin-top: 10px;
}

h2{
	margin-left: 400px;
}

h4{
	margin-left: 400px;
}

table {
  border-collapse: collapse;
  width: 100%;
}

td, th {
  border: 1px solid black;
  text-align: left;
  padding: 8px;
}

input {
  border: 0;
  outline: 0;
  border-bottom: solid;
        
}




</style>


</head>
<body>

<form method="post"  enctype="multipart/form-data">

		<table style="border-collapse: collapse;margin-left: auto;margin-right: auto;">
                        		
			<tr>
				<td><img src="/shatabdi/images/shatabdi.jpg" width="67" height="80"/></td>
				<td>
				<label class="dataTextBoldCenter" style="text-transform: uppercase;">${branchname}</label><br>
				<label class="addressLine">A Senior Secondary School<br></label>
				<label class="addressLine">Affiliated to CBSE., Delhi, Affiliation No-330113<br></label>
				<label class="addressLine">${branchaddress}</label><br>
				<label class="addressLine">Contact: ${branchcontact}</label>
				</td>
				<td><img  src="data:image;base64,<c:out value="${Parents.student.studentpic}"/>" alt="Student's Photo" width="140" height="70"/></td>
			</tr>
		</table>
		

	<div align="center">
		<img alt="Shatabdi logo" src="/shatabdi/images/shatabdi.jpg" width="67" height="80">
	</div>
	
<h2>SHATABDI PUBLIC SCHOOL, GAYA</h2>
<h4>A SENIOR SECONDRY SCHOOL, AFFILIATED TO CBSE, DELHI)</h4>
<h4>REGISTRATION CUM ADMISSION FORM</h4>
<h4>SESSION</h4>
<div class="schoolname">
<label for="schoolname">School Name and Location:</label>
 <input type="text" name="schoolname" style="width: 900px;">
 </div>
<h3>Student Detail </h3>
<table>	
  <tr>
    <td>1</td>
    <td>Pupil's Name</td> 
    <td style="width:70%">
    	&nbsp;<c:out value="${parents.student.name}" /></td>
  </tr>
  <tr>
    <td>2</td>
    <td>Mother's Name</td>
    <td><c:out value="${parents.mothersname}" /></td>
  </tr>
  <tr>
    <td>3</td>
    <td>Father's Name</td>
    <td> <c:out value="${parents.fathersname}" /></td>
  </tr>
  <tr>
    <td>4</td>
    <td>Category</td>
    <td> General<input type="checkbox"
		value="General" name="socialcategory" id="yes:General"
		${parents.student.socialcategory == 'General' ? 'checked' : ''} />&nbsp;
		&nbsp;OBC<input type="checkbox" value="OBC" name="socialcategory"
		id="no:OBC" onclick="noCheck(this.id);"
		${parents.student.socialcategory == 'OBC' ? 'checked' : ''} />
		SC<input type="checkbox"
		value="SC" name="socialcategory" id="yes:SC"
		${parents.student.socialcategory == 'SC' ? 'checked' : ''} />&nbsp;
		&nbsp;ST<input type="checkbox" value="ST" name="socialcategory"
		id="no:ST" onclick="noCheck(this.id);"
		${parents.student.socialcategory == 'ST' ? 'checked' : ''} /></td>
  </tr>
 

  <tr>
    <td>5</td>
    <td>Class to which admission is sought</td>
    <td style="width:70%"><c:out value="${parents.student.classadmittedin}" /></td>
  </tr>
  <tr>
    <td>6</td>
    <td>Date of Birth</td>
    <td style="width:70%"><fmt:formatDate value="${parents.student.dateofbirth}" pattern="dd/MM/yyyy"/></td>
  </tr>
  <tr>
    <td>7</td>
    <td>Blood Group</td>
    <td><c:out value="${parents.student.bloodgroup}" /></td>
  </tr>
  <tr>
    <td>8</td>
    <td>Weight</td>
    <td><c:out value="${parents.student.bhagyalakshmibondnumber}" /></td>
  </tr>
  <tr>
    <td>9</td>
    <td>Height</td>
    <td><c:out value="${parents.student.bplcardno}" /></td>
  </tr>
  <tr>
    <td>10</td>
    <td>Mark of Identification</td>
    <td></td>
  </tr>
  <tr>
    <td>11</td>
    <td>Nationality</td>
    <td><c:out value="${parents.student.nationality}" /></td>
  </tr>
  <tr>
    <td>12</td>
    <td>Mother Tongue</td>
    <td><c:out value="${parents.student.mothertongue}" /></td>
  </tr>
  <tr>
    <td>13</td>
    <td>Address</td>
    <td><c:out value="${parents.addresspermanent}" /></td>
  </tr>
  <tr>
    <td>14</td>
    <td>Email</td>
    <td><c:out value="${parents.email}" /></td>
  </tr>
 

  <tr>
    <td>15</td>
    <td>Adhar</td>
    <td style="width:70%">&nbsp;<c:out value="${parents.student.disabilitychild}" /></td>
  </tr>
  <tr>
    <td>16</td>
    <td>Mobile</td>
    <td><c:out value="${parents.contactnumber}" /></td>
  </tr>
  <tr>
    <td>17</td>
    <td>Qualification of Father</td>
    <td> <c:out value="${parents.fathersqualification}" /></td>
  </tr>
  <tr>
    <td>18</td>
    <td>Qualification of Mother</td>
    <td><fmt:formatDate value="${parents.mothersqualification}" pattern="dd/MM/yyyy"/></td>
  </tr>
  <tr>
    <td>19</td>
    <td>Occupation of Father</td>
    <td>
    	<c:out value="${parents.fatherscastecertno}" />
    </td>
  </tr>
  <tr>
    <td>20</td>
    <td>Occupation of Mother</td>
    <td> <c:out default="" value="${parents.motherscastecertno}" /></td>
  </tr>
  <tr>
    <td>21</td>
    <td>Annual Income</td>
    <td> <c:out default="" value="${parents.parentsannualincome}" /></td>
  </tr>
  <tr>
    <td>22</td>
    <td>Local Guardian's Name(with Relation)</td>
    <td></td>
  </tr>
  <tr>
    <td>23</td>
    <td>Occupation and Address</td>
    <td></td>
  </tr>
  <tr>
    <td>24</td>
    <td>Name and Address of previous school</td>
    <td> <c:out default="" value="${student.schoollastattended}" /></td>
  </tr>
 
  <tr>
    <td>25</td>
    <td>Previous Class</td>
    <td style="width:70%"><c:out default="" value="${student.stdlaststudied}" /></td>
  </tr>
  <tr>
    <td>26</td>
    <td>Percentage of Mark</td>
    <td></td>
  </tr>
   </table>
  <h2>DECLARATION</h2>
  <table>
   <tr>
    <td>27</td>
    <td>Declaration</td>
    <td>I solemnly declare that the above Information is correct to the best of my knowledge and I will abide by
    the rules and regulation of the school.</td>
  </tr>
  <tr>
    <td>28</td>
    <td>Place</td>
    <td></td>
  </tr>
  <tr>
    <td>29</td>
    <td>Date</td>
    <td><fmt:formatDate value="${student.createddate}" pattern="dd/MM/yyyy"/></td>
  </tr>
   <tr>
    <td>30</td>
    <td>Signature of Father/Guardian</td>
    <td></td>
  </tr>
  
   
</table>
  		

		<p>Note: Fill all the fields in capital letter.</p>

	<h3>For Office Use Only</h3>
		<label for="enrollmentno">Class in which admitted</label>
        <input type="text" name="enrollmentno"value="${parents.student.classadmittedin}">&nbsp;&nbsp;&nbsp;&nbsp;
        <label for="admissiondate"> Date:</label>
        <input type="date" name="admissiondate">&nbsp;&nbsp;&nbsp;&nbsp;
        <label for="U-Dise">Principal Signature</label>
        <input type="text" name="U-Dise"></br>
<hr>
<table  >
<tr><td></td><td><h3>Application Receipt</h3></td></tr>
<tr><td>Registration No. </td><td></td></tr>
<tr><td>Form No. </td><td></td></tr>
<tr><td>Name </td><td><c:out value="${parents.student.name}" /></td></tr>
<tr><td>Class </td><td><c:out value="${parents.student.classadmittedin}" /></td></tr>
<tr><td>Date of Entrance Test </td><td></td></tr>
<tr><td>Time </td><td></td></tr>
<tr><td>Publication of Result </td><td></td></tr>
<tr><td>Admission I/C </td><td></td></tr>
</table>       

 <button onclick="window.print()">Print</button>
 </div>
 </form>
</body>
</html>
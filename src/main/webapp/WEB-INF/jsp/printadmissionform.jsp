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

	<div align="center">
		<img alt="Govt Of Karnataka" src="/presidency/images/Karnatakalogo.png">
	</div>

<h2>GOVERNMENT OF KARNATAKA</h2>
<h4>STUDENT ENROLLMENT FORM FOR YEAR 2021-22</h4>
<div class="schoolname">
<label for="schoolname">School Name and Location:</label>
 <input type="text" name="schoolname" style="width: 900px;">
 </div>
<h3>Admission Detail For Higher Class</h3>
<table>	
  <tr>
    <td>1</td>
    <td>Admission To Class</td> 
    <td style="width:70%">
    	<c:set var="classonly" value="${fn:split(parents.student.classstudying, '--')}" />
    	1<input type="checkbox"
		value="1" name="admissiontoclass" id="yes:1"
		${classonly[0] == '1ST STD' ? 'checked' : ''} />&nbsp;
		&nbsp;2<input type="checkbox" value="2" name="admissiontoclass"
		id="no:2" onclick="noCheck(this.id);"
		${classonly[0] == 'IIND STD' ? 'checked' : ''} />
		3<input type="checkbox"
		value="3" name="admissiontoclass" id="yes:3"
		${classonly[0] == 'IIIRD STD' ? 'checked' : ''} />&nbsp;
		&nbsp;4<input type="checkbox" value="4" name="admissiontoclass"
		id="no:4" onclick="noCheck(this.id);"
		${classonly[0] == 'IVTH STD' ? 'checked' : ''} />
		5<input type="checkbox"
		value="5" name="admissiontoclass" id="yes:5"
		${classonly[0] == 'VTH STD' ? 'checked' : ''} />&nbsp;
		&nbsp;6<input type="checkbox" value="6" name="admissiontoclass"
		id="no:6" onclick="noCheck(this.id);"
		${classonly[0] == 'VITH STD' ? 'checked' : ''} />
		7<input type="checkbox"
		value="7" name="admissiontoclass" id="yes:7"
		${classonly[0] == 'VIITH STD' ? 'checked' : ''} />&nbsp;
		&nbsp;8<input type="checkbox" value="8" name="admissiontoclass"
		id="no:8" onclick="noCheck(this.id);"
		${classonly[0] == 'VIII STD' ? 'checked' : ''} />
		&nbsp;
		&nbsp;9<input type="checkbox" value="9" name="admissiontoclass"
		id="no:9" onclick="noCheck(this.id);"
		${classonly[0] == 'IX STD' ? 'checked' : ''} />
		&nbsp;
		&nbsp;10<input type="checkbox" value="10" name="admissiontoclass"
		id="no:10" onclick="noCheck(this.id);"
		${classonly[0] == 'X STD' ? 'checked' : ''} /></td>
  </tr>
  <tr>
    <td>2</td>
    <td>Stream For 11 and 12</td>
    <td></td>
  </tr>
  <tr>
    <td>3</td>
    <td>Medium For Instruction</td>
    <td>Kannada<input type="checkbox"
		value="Kannada" name="mediumofinstruction" id="yes:Kannada"
		${parents.student.mediumofinstruction == 'Kannada' ? 'checked' : ''} />&nbsp;
		&nbsp;Hindi<input type="checkbox" value="Hindi" name="mediumofinstruction"
		id="no:Hindi" onclick="noCheck(this.id);"
		${parents.student.mediumofinstruction == 'Hindi' ? 'checked' : ''} />
		Urdu<input type="checkbox"
		value="Urdu" name="mediumofinstruction" id="yes:Urdu"
		${parents.student.mediumofinstruction == 'Urdu' ? 'checked' : ''} />&nbsp;
		&nbsp;English<input type="checkbox" value="English" name="mediumofinstruction"
		id="no:English" onclick="noCheck(this.id);"
		${parents.student.mediumofinstruction == 'English' ? 'checked' : ''} />
		Marathi<input type="checkbox"
		value="Marathi" name="mediumofinstruction" id="yes:Marathi"
		${parents.student.mediumofinstruction == 'Marathi' ? 'checked' : ''} />&nbsp;
		&nbsp;Tamil<input type="checkbox" value="Tamil" name="mediumofinstruction"
		id="no:Tamil" onclick="noCheck(this.id);"
		${parents.student.mediumofinstruction == 'Tamil' ? 'checked' : ''} />
		Telgu<input type="checkbox"
		value="Telgu" name="mediumofinstruction" id="yes:Telgu"
		${parents.student.mediumofinstruction == 'Telgu' ? 'checked' : ''} /></td>
  </tr>
  <tr>
    <td>4</td>
    <td>Mother Tongue</td>
    <td>Kannada<input type="checkbox"
		value="Kannada" name="mothertongue" id="yes:Kannada"
		${parents.student.mothertongue == 'Kannada' ? 'checked' : ''} />&nbsp;
		&nbsp;Hindi<input type="checkbox" value="Hindi" name="mothertongue"
		id="no:Hindi" onclick="noCheck(this.id);"
		${parents.student.mothertongue == 'Hindi' ? 'checked' : ''} />
		Urdu<input type="checkbox"
		value="Urdu" name="mothertongue" id="yes:Urdu"
		${parents.student.mothertongue == 'Urdu' ? 'checked' : ''} />&nbsp;
		&nbsp;English<input type="checkbox" value="English" name="mothertongue"
		id="no:English" onclick="noCheck(this.id);"
		${parents.student.mothertongue == 'English' ? 'checked' : ''} />
		Marathi<input type="checkbox"
		value="Marathi" name="mothertongue" id="yes:Marathi"
		${parents.student.mothertongue == 'Marathi' ? 'checked' : ''} />&nbsp;
		&nbsp;Tamil<input type="checkbox" value="Tamil" name="mothertongue"
		id="no:Tamil" onclick="noCheck(this.id);"
		${parents.student.mothertongue == 'Tamil' ? 'checked' : ''} />
		Telgu<input type="checkbox"
		value="Telgu" name="mothertongue" id="yes:Telgu"
		${parents.student.mothertongue == 'Telgu' ? 'checked' : ''} /></td>
  </tr>
 
</table>

<h3>Previous School Details</h3>
<table>	
  <tr>
    <td>5</td>
    <td>Previous School Affiliation</td>
    <td style="width:70%"></td>
  </tr>
  <tr>
    <td>6</td>
    <td>Transfer Certificate No.</td>
    <td style="width:70%"><c:out default="" value="${parents.student.nooftc}" /></td>
  </tr>
  <tr>
    <td>7</td>
    <td>SATS Child Number</td>
    <td></td>
  </tr>
  <tr>
    <td>8</td>
    <td>Previous School Name</td>
    <td><c:out default="" value="${parents.student.schoollastattended}" /></td>
  </tr>
  <tr>
    <td>9</td>
    <td>Previous School Type</td>
    <td>Government<input type="checkbox"
		value="Government" name="previousschooltype" id="yes:Government"
		${parents.student.previousschooltype == 'Government' ? 'checked' : ''} />&nbsp;
		&nbsp;Private Aided<input type="checkbox" value="Private Aided" name="previousschooltype"
		id="no:Private Aided" onclick="noCheck(this.id);"
		${parents.student.previousschooltype == 'Private Aided' ? 'checked' : ''} />
		Local Bodies<input type="checkbox"
		value="Local Bodies" name="previousschooltype" id="yes:Local Bodies"
		${parents.student.previousschooltype == 'Local Bodies' ? 'checked' : ''} />&nbsp;
		&nbsp;Private Unaided School<input type="checkbox" value="Private Unaided School" name="previousschooltype"
		id="no:Private Unaided School" onclick="noCheck(this.id);"
		${parents.student.previousschooltype == 'Private Unaided School' ? 'checked' : ''} /></td>
  </tr>
  <tr>
    <td>10</td>
    <td>Previous School Address</td>
    <td></td>
  </tr>
  <tr>
    <td>11</td>
    <td>District</td>
    <td></td>
  </tr>
  <tr>
    <td>12</td>
    <td>Taluk</td>
    <td></td>
  </tr>
  <tr>
    <td>13</td>
    <td>City</td>
    <td></td>
  </tr>
  <tr>
    <td>14</td>
    <td>Pin Code</td>
    <td></td>
  </tr>
 
</table>   



        <h3>Student Details</h3>
<table>	
  <tr>
    <td>15</td>
    <td>Student Name</td>
    <td style="width:70%">&nbsp;<c:out value="${parents.student.name}" /></td>
  </tr>
  <tr>
    <td>16</td>
    <td>Mother's Name</td>
    <td><c:out value="${parents.mothersname}" /></td>
  </tr>
  <tr>
    <td>17</td>
    <td>Father's Name</td>
    <td> <c:out value="${parents.fathersname}" /></td>
  </tr>
  <tr>
    <td>18</td>
    <td>Date of birth</td>
    <td><fmt:formatDate value="${parents.student.dateofbirth}" pattern="dd/MM/yyyy"/></td>
  </tr>
  <tr>
    <td>19</td>
    <td>Child Admitted Under RTE Quota</td>
    <td>
    	<c:if test="${(parents.student.rte ==1)}">    
           <c:out default="" value="Yes" />
           </c:if>
          <c:if test="${(parents.student.rte == 0)}">    
           	<c:out default="" value="No" />
           	</c:if>
    </td>
  </tr>
  <tr>
    <td>20</td>
    <td>Gender</td>
    <td>Male<input type="checkbox"
		value="Male" name="gender" id="yes:male"
		${parents.student.gender == 'Male' ? 'checked' : ''} />&nbsp;
		&nbsp;Female<input type="checkbox" value="Female" name="gender"
		id="no:male" onclick="noCheck(this.id);"
		${parents.student.gender == 'Female' ? 'checked' : ''} /></td>
  </tr>
  <tr>
    <td>21</td>
    <td>Student Adhaar No</td>
    <td></td>
  </tr>
  <tr>
    <td>22</td>
    <td>Mother's Adhaar No</td>
    <td></td>
  </tr>
  <tr>
    <td>23</td>
    <td>Father's Adhaar No</td>
    <td></td>
  </tr>
  <tr>
    <td>24</td>
    <td>Social Category</td>
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
  </table>
  <h3>TC not produced at the time of admission please fill the following information</h3>
  <table>
  <tr>
    <td>25</td>
    <td>Student Caste Certificate No</td>
    <td style="width:70%"><c:out default="" value="${parents.student.studentscastecertno}" /></td>
  </tr>
  <tr>
    <td>26</td>
    <td>Mother's Caste Certificate No</td>
    <td><c:out default="" value="${parents.motherscastecertno}" /></td>
  </tr>
   <tr>
    <td>27</td>
    <td>Father's Caste Certificate No</td>
    <td><c:out value="${parents.fatherscastecertno}" /></td>
  </tr>
  <tr>
    <td>28</td>
    <td>Belong To BPL</td>
    <td>&nbsp;Yes<input
		type="checkbox" value="1" name="belongtobpl" id="yes:bpl"
		onclick="yesCheck(this.id);" ${parents.student.belongtobpl == '1' ? 'checked' : ''}/>&nbsp; &nbsp;No<input
		type="checkbox" value="0" name="belongtobpl" id="no:bpl"
		onclick="noCheck(this.id);" ${parents.student.belongtobpl == '0' ? 'checked' : ''}/></td>
  </tr>
  <tr>
    <td>29</td>
    <td>Bhagyalaxmi Bond No</td>
    <td><c:out default="" value="${parents.student.bhagyalakshmibondnumber}" /></td>
  </tr>
   <tr>
    <td>30</td>
    <td>Child with special need</td>
    <td></td>
  </tr>
  <tr>
    <td>31</td>
    <td>Special Category</td>
    <td>None<input type="checkbox"
		value="None" name="specialcategory" id="yes:None"
		${parents.student.specialcategory == 'None' ? 'checked' : ''} />&nbsp;
		&nbsp;Destitute<input type="checkbox" value="Destitute" name="specialcategory"
		id="no:Destitute" onclick="noCheck(this.id);"
		${parents.student.specialcategory == 'Destitute' ? 'checked' : ''} />
		HIV Case<input type="checkbox"
		value="HIV Case" name="specialcategory" id="yes:HIV Case"
		${parents.student.specialcategory == 'HIV Case' ? 'checked' : ''} />&nbsp;
		&nbsp;Orphans<input type="checkbox" value="Orphans" name="specialcategory"
		id="no:Orphans" onclick="noCheck(this.id);"
		${parents.student.specialcategory == 'Orphans' ? 'checked' : ''} />
		Others<input type="checkbox"
		value="Others" name="specialcategory" id="yes:Others"
		${parents.student.specialcategory == 'Others' ? 'checked' : ''} />&nbsp;</td>
  </tr>
  <tr>
    <td>32</td>
    <td>Pin Code</td>
    <td></td>
  </tr>
   <tr>
    <td>33</td>
    <td>City/Village/Town</td>
    <td></td>
  </tr>
  <tr>
    <td>34</td>
    <td>Address</td>
    <td><c:out default="" value="${parents.addresspermanent}" /></td>
  </tr>
  <tr>
    <td>35</td>
    <td>Mobile Number</td>
    <td><c:out default="" value="${parents.contactnumber}" /></td>
  </tr>
   <tr>
    <td>36</td>
    <td>BMTC Bus pass</td>
    <td></td>
  </tr>
   
</table>
  		

		<p>Note: Fill all the fields in capital letter.</p>

	<h3>For Office Use Only</h3>
		<label for="enrollmentno">Student Enrollment Number:</label>
        <input type="text" name="enrollmentno">
        <label for="admissiondate">Admission Date:</label>
        <input type="date" name="admissiondate">
        <label for="U-Dise">U-Dise Code:</label>
        <input type="text" name="U-Dise"></br>
        <label for="bankaccount">Student/Parent Bank Account Number:</label>
        <input type="text" name="bankaccount" size="172"></br>
        <label for="IFSC">Bank IFSC Code:</label>
        <input type="text" name="IFSC" size="172"></br>

 <button onclick="window.print()">Print</button>
 </div>
 </form>
</body>
</html>
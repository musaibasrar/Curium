<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admission Form</title>

<script type="text/javascript">
function validateForm() {
  var x = document.forms["myForm"]["fname"].value;
  if (x == "" || x == null) {
    alert("First Name must be filled out");
    return false;
  }
}

</script>
</head>
<body>

<form method="post"  enctype="multipart/form-data">

<h2>GOVERNMENT OF KARNATAKA</h2>
<h4>STUDENT ENROLLMENT FORN FOR YEAR 2021-22</h4>
<form name="myForm">
	<div class="admission">
	<div class="group1">
	<form>
		<label for="schoolnamelocation">School Name and Location:</label>
        <input type="text" name="schoolnamelocation" size="172"></br>
    </form>
</div>
<h3>Admission Detail For Higher Class</h3>


	<form>
        <label for="admissiontoclass">Admission To Class:</label>
   	    <input type="checkbox" name="admissiontoclass" value="2">2
        <input type="checkbox" name="admissiontoclass" value="3">3
        <input type="checkbox" name="admissiontoclass" value="4">4
        <input type="checkbox" name="admissiontoclass" value="5">5
        <input type="checkbox" name="admissiontoclass" value="6">6
        <input type="checkbox" name="admissiontoclass" value="7">7
        <input type="checkbox" name="admissiontoclass" value="8">8
        <input type="checkbox" name="admissiontoclass" value="9">9
        <input type="checkbox" name="admissiontoclass" value="10">10
        <input type="checkbox" name="admissiontoclass" value="11">11
         <input type="checkbox" name="admissiontoclass" value="12">12
    </form></br>

    <form>
        <label for="stream">Stream For 11 and !2:</label>
   	    <input type="checkbox" name="stream" value="na">NA
        <input type="checkbox" name="stream" value="commerce">COMMERCE
        <input type="checkbox" name="stream" value="vocational">VOCATIONAL
        <input type="checkbox" name="stream" value="sceince">SCEINCE
        <input type="checkbox" name="stream" value="arts">ARTS
    </form></br>

	<form>
        <label for="mediumfirinstruction" >Medium For Instruction:</label>
   	    <input type="checkbox" name="mediumfirinstruction" value="Kannada">Kannada
        <input type="checkbox" name="mediumfirinstruction" value="Hindi">Hindi
        <input type="checkbox" name="mediumfirinstruction" value="Urdu">Urdu
        <input type="checkbox" name="mediumfirinstruction" value="English">English
        <input type="checkbox" name="mediumfirinstruction" value="Marathi">Marathi
        <input type="checkbox" name="mediumfirinstruction" value="Tamil">Tamil
        <input type="checkbox" name="mediumfirinstruction" value="Telugu">Telugu
    </form></br>

    <form>
        <label for="mothertongue" >Mother Tongue:</label>
   	    <input type="checkbox" name="mothertongue" value="Kannada">Kannada
        <input type="checkbox" name="mothertongue" value="Hindi">Hindi
        <input type="checkbox" name="mothertongue" value="Urdu">Urdu
        <input type="checkbox" name="mothertongue" value="English">English
        <input type="checkbox" name="mothertongue" value="Marathi">Marathi
        <input type="checkbox" name="mothertongue" value="Tamil">Tamil
        <input type="checkbox" name="mothertongue" value="Telugu">Telugu
        <input type="checkbox" name="mothertongue" value="Other">Other
    </form></br>

    <h3>Previous School Details</h3>

    <form>
        <label for="Affiliation" >Previous School Affiliation:</label>
   	    <input type="checkbox" name="Affiliation" value="State">State
        <input type="checkbox" name="Affiliation" value="CBSE">CBSE
        <input type="checkbox" name="Affiliation" value="ICSE">ICSE
        <input type="checkbox" name="Affiliation" value="Other">Other
    </form></br>

		<label for="transfercertificateno">Transfer Certificate No.:</label>
        <input type="text" name="transfercertificateno">
        <label for="tcdate">TC Date:</label>
        <input type="date" name="tcdate"></br>
        <label for="childnumber">SATS Child Number:</label>
        <input type="text" name="childnumber" size="172"></br>
        <label for="previousschool">Previous School Name:</label>
        <input type="text" name="previousschool"  required>
        <label for="disecode">U-dise Code:</label>
        <input type="text" name="disecode"></br>

    <form>
        <label for="schooltype" >Previous School Type:</label>
   	    <input type="checkbox" name="schooltype" value="Government">Government School
        <input type="checkbox" name="schooltype" value="Privateaided">Private Aided School
        <input type="checkbox" name="schooltype" value="Local">Local Bodies
        <input type="checkbox" name="schooltype" value="Privateunaided">Private Unaided School
    </form></br>
    	<label for="previousschooladdress">Previous School Address:</label>
        <input type="text" name="previousschooladdress" size="172" required></br>
        <label for="District">District:</label>
        <input type="text" name="District" required>
        <label for="Taluk">Taluk:</label>
        <input type="text" name="Taluk" required>
        <label for="City">City:</label>
        <input type="text" name="City" required></br>
        <label for="Pincode">Pin Code:</label></br>
        <input type="text" name="Pincode" size="172" required></br>

        <h3>Student Details</h3>

        <table class="table">
    <thead>
      <tr>
      	<th></th>
        <th>First Name</th>
        <th>Middle Name</th>
        <th>Last Name</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td><label for="studentname">Student Name:</label></td>
        <td><input type="text" name="fname" required></td>
        <td><input type="text" name="studentname"></td>
        <td><input type="text" name="studentname"></td>
      </tr>
       <tr>
        <td><label for="mothername">Mother's Name:</label></td>
        <td><input type="text" name="mothername" ></td>
        <td><input type="text" name="mothername"></td>
        <td><input type="text" name="mothername"></td>
      </tr>
       <tr>
        <td><label for="fathername">Father's Name:</label></td>
        <td><input type="text" name="fathername" ></td>
        <td><input type="text" name="fathername"></td>
        <td><input type="text" name="fathername"></td>
      </tr>
     
    </tbody>
  </table>
  		<label for="dob">Date of birth:</label>
        <input type="date" name="dob">
        <label for="inwords">In Words</label>
        <input type="text" name="inwords"></br>
        <label for="rte">Child Admitted Under RTE Quota</label>
        Yes<input type="radio" name="rte" value="Yes">
        No<input type="radio" name="rte" value="No"></br>
        <label for="Gender">Gender</label>
        Male<input type="radio" name="Gender" value="Male">
        Female<input type="radio" name="Gender" value="Female">
        Transgender<input type="radio" name="Gender" value="Transgender">
        Other<input type="radio" name="Gender" value="Other"></br>

        <table class="table1">
    <thead>
      <tr>
      	<th></th>
        <th>Child</th>
        <th>Father's</th>
        <th>Mother's</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td><label for="aadharno">Aadhar UID No.</label></td>
        <td><input type="text" name="aadharno" ></td>
        <td><input type="text" name="aadharno"></td>
        <td><input type="text" name="aadharno"></td>
      </tr>
  </tbody>
</table>
  		<label for="Category">Social Category</label>
        General<input type="radio" name="Category" value="General">
        OBC<input type="radio" name="Category" value="OBC">
        SC<input type="radio" name="Category" value="SC">
        ST<input type="radio" name="Category" value="ST"></br>
 
 <h3>TC not produced at the time of admission please fill the following information</h3>
 		<label for="scastenumber">Student Caste Certificate Number:</label>
        <input type="text" name="scastenumber">
        <label for="SCaste">Caste:</label>
        <input type="text" name="SCaste">
        <label for="SSubcaste">Sub Caste</label>
        <input type="text" name="SSubcaste"></br>

        <label for="fcastenumber">Father's Caste Certificate Number:</label>
        <input type="text" name="fcastenumber">
        <label for="FCaste">Caste:</label>
        <input type="text" name="FCaste">
        <label for="FSubcaste">Sub Caste</label>
        <input type="text" name="FSubcaste"></br>

        <label for="mcastenumber">Student Caste Certificate Number:</label>
        <input type="text" name="mcastenumber">
        <label for="MCaste">Caste:</label>
        <input type="text" name="MCaste">
        <label for="MSubcaste">Sub Caste</label>
        <input type="text" name="MSubcaste"></br>

        <label for="BPL">Belong To BPL</label>
        Yes<input type="radio" name="BPL" value="Yes">
        No<input type="radio" name="BPL" value="No">
        <label for="bplcardno">BPL Card Number</label>
        <input type="text" name="bplcardno"></br>

        <label for="specialcategory" >Special Catagory:</label>
   	    <input type="checkbox" name="specialcategory" value="None">None
        <input type="checkbox" name="specialcategory" value="Destitude">Destitude
        <input type="checkbox" name="specialcategory" value="HIV">HIV Case
        <input type="checkbox" name="specialcategory" value="Orphan">Orphan
        <input type="checkbox" name="specialcategory" value="Other">Other</br>

        <label for="Pincode">Pin Code:</label>
        <input type="text" name="Pincode">
        <label for="District">District:</label>
        <input type="text" name="District">
        <label for="Taluk">Taluk:</label>
        <input type="text" name="Taluk"></br>
        <label for="cvt">City/Village/Town:</label>
        <input type="text" name="cvt">
        <label for="Locality">Locality:</label>
        <input type="text" name="Locality"></br>
        <label for="Address">Address:</label>
        <input type="text" name="Address" size="172"></br>
        <label for="mobileno">Mobile Number:</label>
		<input type="tel" id="mobileno" name="mobileno" pattern="[0-9]{3}-[0-9]{2}-[0-9]{3}"></br>
		<label for="email">Email:</label>
		<input type="email" id="email" name="email"></br>

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

 <button type="button" onclick="validateForm();">Submit</button>
 <button onclick="window.print()">Print</button>
 </div>
 </form>
</body>
</html>
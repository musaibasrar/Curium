<!DOCTYPE html>
<html lang="en">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student Profile</title>
    <link rel="stylesheet" href="/stanley/css/bootstrap.min.css">
    <script src="/stanley/js/jquery.min.js"></script>
    <script src="/stanley/js/bootstrap.min.js"></script>
    <script src="/stanley/js/popper.min.js"></script>
        
<style>
 .headerText {
		background-image: url("/images/ui-bg_diagonals-small_50_466580_40x40.png");
                border-radius:3px;
                font-family: Tahoma;
                font-size: 12px;
                background-color: #4b6a84;
                color: #FFFFFF;
                font-weight: normal;
                width: auto ;
                height: 22px;
                vertical-align: middle;
                text-align: center;
	     }
.dataText {
                border-radius:3px;
                font-family: Tahoma;
                color: #4b6a84;
                font-size: 13px;
                letter-spacing: normal;
                text-align: center;
                background-color: #E3EFFF;

            }
            
.mb-0{
	margin-bottom: 0!important;
	/* font-size:38px; */
}
.studenthr{
		border:1px solid rgb(0 0 0);
}
.card-body{
padding:1 rem;
}
</style>
</head>
<body>
   

<!-- profile pic start-->
     <div class="d-flex justify-content-center mb-2" style="width: 100%; padding: 0.25rem;">
  <div class="card" style="width: 100%; max-width: 360px;">
    <div class="card-body text-center">
      <img class="rounded-circle mx-auto" src="data:image;base64,<c:out value="${student.studentpic}"/>" alt="Student's Photo" style="width: 200px; height: 200px;">
      <h5 class="my-3"><c:out value="${student.name}" /></h5>
      <p class="text-muted mb-1"><c:forEach var="splt" items="${fn:split(student.classstudying,'--')}">
        ${splt}
      </c:forEach></p>
      <h5 class="my-3" style="color: #0f304f; font-weight: bold;">UID: &nbsp;<c:out value="${student.studentexternalid}" /></h5>
    </div>
  </div>
</div>

<!-- profile pic end-->
 <div class="card-body text-left" style="width: 100%; padding: 0.5rem;">
  <hr class="mt-0 mb-2">
  <h5 align="left" style="color: #FF914D;font-weight:bold;">Student Details</h5>
  <div class="card-body" style="border: 2px solid #000;">
    <div class="row mb-2">
      <div class="col-6">
        <p class="mb-0" style="font-weight: normal; color: #FF5733;">Full Name</p>
      </div>
      <div class="col-6">
        <p class="mb-0" style="font-weight: bold;color: #0f304f;"><c:out value="${student.name}" /></p>
      </div>
    </div>
    <hr class="studenthr">
    <div class="row mb-2">
      <div class="col-6">
        <p class="mb-0" style="font-weight: normal; color: #FF5733;">Gender</p>
      </div>
      <div class="col-6">
        <p class="mb-0" style="font-weight: bold;color: #0f304f;"><c:out default="" value="${student.gender}" /></p>
      </div>
    </div>
    <hr class="studenthr">
    <div class="row mb-2">
      <div class="col-6">
        <p class="mb-0" style="font-weight: normal; color: #FF5733;">DOB</p>
      </div>
      <div class="col-6">
        <p class="mb-0" style="font-weight: bold;color: #0f304f;"><fmt:formatDate value="${student.dateofbirth}" pattern="dd/MM/yyyy"/></p>
      </div>
    </div>
    <hr class="studenthr">
    <div class="row mb-2">
      <div class="col-6">
        <p class="mb-0" style="font-weight: normal; color: #FF5733;">Age</p>
      </div>
      <div class="col-6">
        <p class="mb-0" style="font-weight: bold;color: #0f304f;"><c:out default="" value="${student.age}" /></p>
      </div>
    </div>
    <hr class="studenthr">
    <div class="row mb-2">
      <div class="col-6">
        <p class="mb-0" style="font-weight: normal; color: #FF5733;">Place of Birth</p>
      </div>
      <div class="col-6">
        <p class="mb-0" style="font-weight: bold;color: #0f304f;"><c:out default="" value="${student.placeofbirth}" /></p>
      </div>
    </div>
    <hr class="studenthr">
    <div class="row mb-2">
      <div class="col-6">
        <p class="mb-0" style="font-weight: normal; color: #FF5733;">Date of Admission</p>
      </div>
      <div class="col-6">
        <p class="mb-0" style="font-weight: bold;color: #0f304f;"><fmt:formatDate value="${student.admissiondate}" pattern="dd/MM/yyyy"/></p>
      </div>
    </div>
    <hr class="studenthr">
    <div class="row mb-2">
      <div class="col-6">
        <p class="mb-0" style="font-weight: normal; color: #FF5733;">Studying in Class</p>
      </div>
      <div class="col-6">
        <p class="mb-0" style="font-weight: bold;color: #0f304f;"><c:forEach var="splt" items="${fn:split(student.classstudying,'--')}">
          ${splt}
        </c:forEach></p>
      </div>
    </div>
    <hr class="studenthr">
    <div class="row mb-2">
      <div class="col-6">
        <p class="mb-0" style="font-weight: normal; color: #FF5733;">Admitted in Class</p>
      </div>
      <div class="col-6">
        <p class="mb-0" style="font-weight: bold;color: #0f304f;"><c:forEach var="splt" items="${fn:split(student.classadmittedin,'--')}">
          ${splt}
        </c:forEach></p>
      </div>
    </div>
    <hr class="studenthr">
    <div class="row mb-2">
      <div class="col-6">
        <p class="mb-0" style="font-weight: normal; color: #FF5733;">Blood Group</p>
      </div>
      <div class="col-6">
        <p class="mb-0" style="font-weight: bold;color: #0f304f;"><c:out default="" value="${student.bloodgroup}" /></p>
      </div>
    </div>
    <hr class="studenthr">
    <div class="row mb-2">
      <div class="col-6">
        <p class="mb-0" style="font-weight: normal; color: #FF5733;">Nationality</p>
      </div>
      <div class="col-6">
        <p class="mb-0" style="font-weight: bold;color: #0f304f;"><c:out default="" value="${student.nationality}" /></p>
      </div>
    </div>
    <hr class="studenthr">
    <div class="row mb-2">
      <div class="col-6">
        <p class="mb-0" style="font-weight: normal; color: #FF5733;">Religion</p>
      </div>
      <div class="col-6">
        <p class="mb-0" style="font-weight: bold;color: #0f304f;"><c:out default="" value="${student.religion}" /></p>
      </div>
    </div>
    <hr class="studenthr">
    <div class="row mb-2">
      <div class="col-6">
        <p class="mb-0" style="font-weight: normal; color: #FF5733;">Caste Certificate No.</p>
      </div>
      <div class="col-6">
        <p class="mb-0" style="font-weight: bold;color: #0f304f;"><c:out default="" value="${student.studentscastecertno}" /></p>
      </div>
    </div>
    <hr class="studenthr">
    <div class="row mb-2">
      <div class="col-6">
        <p class="mb-0" style="font-weight: normal; color: #FF5733;">Student Caste</p>
      </div>
      <div class="col-6">
        <p class="mb-0" style="font-weight: bold;color: #0f304f;"><c:out default="" value="${student.studentscaste}" /></p>
      </div>
    </div>
    <hr class="studenthr">
    <div class="row mb-2">
      <div class="col-6">
        <p class="mb-0" style="font-weight: normal; color: #FF5733;">Social Category</p>
      </div>
      <div class="col-6">
        <p class="mb-0" style="font-weight: bold;color: #0f304f;"><c:out default="" value="${student.socialcategory}" /></p>
      </div>
    </div>
    <hr class="studenthr">
    <div class="row mb-2">
      <div class="col-6">
        <p class="mb-0" style="font-weight: normal; color: #FF5733;">Belong to BPL</p>
      </div>
      <div class="col-6">
        <p class="mb-0" style="font-weight: bold;color: #0f304f;"><c:if test="${(student.belongtobpl == 0)}">    
          <c:out default="" value="No" />
        </c:if>
        <c:if test="${(student.belongtobpl == 1)}">    
          <c:out default="" value="Yes" />
        </c:if></p>
      </div>
    </div>
    <hr class="studenthr">
    <div class="row mb-2">
      <div class="col-6">
        <p class="mb-0" style="font-weight: normal; color: #FF5733;">BPL Card No.</p>
      </div>
      <div class="col-6">
        <p class="mb-0" style="font-weight: bold;color: #0f304f;"><c:out default="" value="${student.bplcardno}" /></p>
      </div>
    </div>
      
           <hr class="studenthr">
              <div class="row mb-2">
              <div class="col-6">
                <p class="mb-0" style="font-weight:normal;color:#FF5733;">Bhagyalakshmi Bond No.</p>
              </div>
              <div class="col-6">
                <p class="mb-0" style="font-weight: bold;color: #0f304f;"><c:out default="" value="${student.bhagyalakshmibondnumber}" /></p>
              </div> 
            </div>
      
       <hr class="studenthr">
              <div class="row mb-2">
              <div class="col-6">
                <p class="mb-0" style="font-weight:normal;color:#FF5733;">Student's Aadhar Card No.</p>
              </div>
              <div class="col-6">
                <p class="mb-0" style="font-weight: bold;color: #0f304f;"><c:out default="" value="${student.disabilitychild}" /></p>
              </div> 
            </div>
            
            
            <hr class="studenthr">
              <div class="row mb-2">
              <div class="col-6">
                <p class="mb-0"style="font-weight:normal;color:#FF5733;">Special Category </p>
              </div>
              <div class="col-6">
                <p class="mb-0" style="font-weight: bold;color: #0f304f;"><c:out default="" value="${student.specialcategory}" /></p>
              </div>
              </div>
              
              <hr class="studenthr">
              <div class="row mb-2">
              <div class="col-6">
                <p class="mb-0"style="font-weight:normal;color:#FF5733;">Mother Tongue</p>
              </div>
              <div class="col-6">
                <p class="mb-0" style="font-weight: bold;color: #0f304f;"><c:out default="" value="${student.mothertongue}" /></p>
              </div> 
            </div>
            
             <hr class="studenthr">
              <div class="row mb-2">
              <div class="col-6">
                <p class="mb-0"style="font-weight:normal;color:#FF5733;">Created Date </p>
              </div>
              <div class="col-6">
                <p class="mb-0" style="font-weight: bold;color: #0f304f;"><fmt:formatDate value="${student.createddate}" pattern="dd/MM/yyyy"/></p>
              </div>
              </div>
              
              <hr class="studenthr">
              <div class="row mb-2">
              <div class="col-6">
                <p class="mb-0"style="font-weight:normal;color:#FF5733;">Admission Year</p>
              </div>
              <div class="col-6">
                <p class="mb-0" style="font-weight: bold;color: #0f304f;"><c:out default="" value="${student.yearofadmission}" /></p>
              </div> 
            </div>
            
	     <hr class="studenthr">
              <div class="row mb-2">
              <div class="col-6">
                <p class="mb-0"style="font-weight:normal;color:#FF5733;">Class Of Leaving </p>
              </div>
              <div class="col-6">
                <p class="mb-0" style="font-weight: bold;color: #0f304f;"><c:out default="" value="${student.classonleaving}" /></p>
              </div>
              </div>
              
              <hr class="studenthr">
              <div class="row mb-2">
              <div class="col-6">
                <p class="mb-0"style="font-weight:normal;color:#FF5733;">Date Of Leaving The School</p>
              </div>
              <div class="col-6">
                <p class="mb-0" style="font-weight: bold;color: #0f304f;"> <fmt:formatDate value="${student.dateleaving}" pattern="dd/MM/yyyy"/></p>
              </div> 
            </div>
            
             <hr class="studenthr">
              <div class="row mb-2">
              <div class="col-6">
                <p class="mb-0"style="font-weight:normal;color:#FF5733;">RTE </p>
              </div>
              <div class="col-6">
                <p class="mb-0" style="font-weight: bold;color: #0f304f;"><c:if test="${(student.rte ==1)}">    
           						  <c:out default="" value="Yes" />
           						 </c:if>
                                <c:if test="${(student.rte == 0)}">    
           						  <c:out default="" value="No" />
           						 </c:if></p>
              </div>
              </div>
              
              <hr class="studenthr">
              <div class="row mb-2">
              <div class="col-6">
                <p class="mb-0"style="font-weight:normal;color:#FF5733;">Remarks</p>
              </div>
              <div class="col-6">
                <p class="mb-0" style="font-weight: bold;color: #0f304f;"><c:out default="" value="${student.remarks}" /></p>
              </div> 
            </div>
            
             <hr class="studenthr">
              <div class="row mb-2">
              <div class="col-6">
                <p class="mb-0"style="font-weight:normal;color:#FF5733;">Reason For Leaving</p>
              </div>
              <div class="col-6">
                <p class="mb-0" style="font-weight: bold;color: #0f304f;"><c:out default="" value="${student.reasonleaving}" /></p>
              </div>
              </div>
              
              <hr class="studenthr">
              <div class="row mb-2">
              <div class="col-6">
                <p class="mb-0"style="font-weight:normal;color:#FF5733;">No. & Date Of Transfer Certificate Issued</p>
              </div>
              <div class="col-6">
                <p class="mb-0" style="font-weight: bold;color: #0f304f;"> <c:out default="" value="${student.notcissued}" /> :
                                <fmt:formatDate value="${student.datetcissued}" pattern="dd/MM/yyyy"/></p>
              </div> 
            </div>
            </div>
<!-- end student detail-->
        <br>
       <h5 align="left" style="color: #FF914D;font-weight:bold;">Parent Details</h5>
                <hr class="mt-0 mb-2">

          <div class="card-body" style="border: 2px solid rgb(0 0 0);">
              <div class="row mb-2">
              <div class="col-6">
                <p class="mb-0"style="font-weight:normal;color:#FF5733;">Father's Name</p>
              </div>
              <div class="col-6">
                <p class="mb-0" style="font-weight: bold;color: #0f304f;"><c:out default="" value="${parents.fathersname}" /></p>
              </div>
              </div>
              
              <hr class="studenthr">
              <div class="row mb-2">
              <div class="col-6">
                <p class="text-muted mb-0"style="font-weight:normal;color:#FF5733;">Mother's Name</p>
              </div>
              <div class="col-6">
                <p class="mb-0" style="font-weight: bold;color: #0f304f;"><c:out default="" value="${parents.mothersname}" /></p>
              </div>
            </div>
            
            <hr class="studenthr">
              <div class="row mb-2">
              <div class="col-6">
                <p class="mb-0"style="font-weight:normal;color:#FF5733;">Father's Qualification</p>
              </div>
              <div class="col-6">
                <p class="mb-0" style="font-weight: bold;color: #0f304f;"><c:out default="" value="${parents.fathersqualification}" /></p>
              </div>
              </div>
              
              <hr class="studenthr">
              <div class="row mb-2">
              <div class="col-6">
                <p class="mb-0"style="font-weight:normal;color:#FF5733;">Mother's Qualification</p>
              </div>
              <div class="col-6">
                <p class="mb-0" style="font-weight: bold;color: #0f304f;"><c:out default="" value="${parents.mothersqualification}" /></p>
              </div>  
            </div>
            
            <hr class="studenthr">
              <div class="row mb-2">
              <div class="col-6">
                <p class="mb-0"style="font-weight:normal;color:#FF5733;">Father's Caste Certificate No.</p>
              </div>
              <div class="col-6">
                <p class="mb-0" style="font-weight: bold;color: #0f304f;"><c:out value="${parents.fatherscastecertno}" /></p>
              </div>
              </div>
              
              <hr class="studenthr">
              <div class="row mb-2">
              <div class="col-6">
                <p class="mb-0"style="font-weight:normal;color:#FF5733;">Mother's Caste</p>
              </div>
              <div class="col-6">
                <p class="mb-0" style="font-weight: bold;color: #0f304f;"><c:out default="" value="${parents.motherscastecertno}" /></p>
              </div> 
            </div>
            
            <hr class="studenthr">
              <div class="row mb-2">
              <div class="col-6">
                <p class="mb-0"style="font-weight:normal;color:#FF5733;">Guardian's Name & Address </p>
              </div>
              <div class="col-6">
                <p class="mb-0" style="font-weight: bold;color: #0f304f;"><c:out value="${student.guardiandetails}" /></p>
              </div>
              </div>
              
              <hr class="studenthr">
              <div class="row mb-2">
              <div class="col-6">
                <p class="mb-0"style="font-weight:normal;color:#FF5733;">Annual Income</p>
              </div>
              <div class="col-6">
                <p class="mb-0" style="font-weight: bold;color: #0f304f;"><c:out default="" value="${parents.parentsannualincome}" /></p>
              </div> 
            </div>
            
            <hr class="studenthr">
              <div class="row mb-2">
              <div class="col-6">
                <p class="mb-0"style="font-weight:normal;color:#FF5733;">Contact Number </p>
              </div>
              <div class="col-6">
                <p class="mb-0" style="font-weight: bold;color: #0f304f;"><c:out default="" value="${parents.contactnumber}" /></p>
              </div>
              </div>
              
              <hr class="studenthr">
              <div class="row mb-2">
              <div class="col-6">
                <p class="mb-0"style="font-weight:normal;color:#FF5733;">Co-Contact Number</p>
              </div>
              <div class="col-6">
                <p class="mb-0" style="font-weight: bold;color: #0f304f;"><c:out default="" value="${parents.cocontactnumber}" /></p>
              </div> 
            </div>
            
             <hr class="studenthr">
              <div class="row mb-2">
              <div class="col-6">
                <p class="mb-0"style="font-weight:normal;color:#FF5733;">Email </p>
              </div>
              <div class="col-6">
                <p class="mb-0" style="font-weight: bold;color: #0f304f;"><c:out default=""
								value="${parents.email}" /></p>
              </div>
              </div>
              
              <hr class="studenthr">
              <div class="row mb-2">
              <div class="col-6">
                <p class="mb-0"style="font-weight:normal;color:#FF5733;">Number of Dependents</p>
              </div>
              <div class="col-6">
                <p class="mb-0" style="font-weight: bold;color: #0f304f;"><c:out default=""
								value="${parents.noofdependents}" /></p>
              </div> 
            </div>
            
             <hr class="studenthr">
              <div class="row mb-2">
              <div class="col-6">
                <p class="mb-0"style="font-weight:normal;color:#FF5733;">Permanent Address </p>
              </div>
              <div class="col-6">
                <p class="mb-0" style="font-weight: bold;color: #0f304f;"><c:out default="" value="${parents.addresspermanent}" /></p>
              </div>
              </div>
              
              <hr class="studenthr">
              <div class="row mb-2">
              <div class="col-6">
                <p class="mb-0"style="font-weight:normal;color:#FF5733;">Temporary Address</p>
              </div>
              <div class="col-6">
                <p class="mb-0" style="font-weight: bold;color: #0f304f;"><c:out default="" value="${parents.addresstemporary}" /></p>
              </div> 
            </div>
            
            <hr class="studenthr">
              <div class="row mb-2">
              <div class="col-6">
                <p class="mb-0"style="font-weight:normal;color:#FF5733;">Number of Dependents </p>
              </div>
              <div class="col-6">
                <p class="mb-0" style="font-weight: bold;color: #0f304f;"> <c:out default="" value="${parents.noofdependents}" /></p>
              </div>
              </div>
              
              <hr class="studenthr">
              <div class="row mb-2">
              <div class="col-6">
                <p class="mb-0"style="font-weight:normal;color:#FF5733;">Note</p>
              </div>
              <div class="col-6">
                <p class="mb-0" style="font-weight: bold;color: #0f304f;"><c:out default="" value="${parents.remarks}" /></p>
              </div> 
            </div>
          </div>
<!-- end student detail -->
<!--previous school detai-->
	<%-- <h1 align="center" style="color: #FF914D;font-weight:bold;">Previous School Detail</h1>
                <hr class="mt-0 mb-4">
         <div class="card mb-4">
          <div class="card-body" style="border: 2px solid rgb(0 0 0);">
            <div class="row mb-2">
            	
              <div class="col-sm-3">
                <p class="mb-0"style="font-weight:normal;color:#FF5733;">Transfer Certificate No.</p>
              </div>
              <div class="col-sm-3">
                <p class="mb-0" style="font-weight: bold;color: #0f304f;"> <c:out default="" value="${student.nooftc}" /></p>
              </div>
              <div class="col-sm-3">
                <p class="text-muted mb-0"style="font-weight:normal;color:#FF5733;">Date Of Transfer Certificate</p>
              </div>
              <div class="col-sm-3">
                <p class="mb-0" style="font-weight: bold;color: #0f304f;"><fmt:formatDate value="${student.dateoftc}" pattern="dd/MM/yyyy"/></p>
              </div>
            </div>
            
            <hr class="studenthr">
            <div class="row mb-2">
              <div class="col-sm-3">
                <p class="mb-0"style="font-weight:normal;color:#FF5733;">Previous Class Studied</p>
              </div>
              <div class="col-sm-3">
                <p class="mb-0" style="font-weight: bold;color: #0f304f;"> <c:out default="" value="${student.stdlaststudied}" /></p>
              </div>
              <div class="col-sm-3">
                <p class="mb-0"style="font-weight:normal;color:#FF5733;">Previous School Name</p>
              </div>
              <div class="col-sm-3">
                <p class="mb-0" style="font-weight: bold;color: #0f304f;"><c:out default="" value="${student.schoollastattended}" /></p>
              </div> 
            </div>
            <hr class="studenthr">
            <div class="row mb-2">
              <div class="col-sm-3">
                <p class="mb-0"style="font-weight:normal;color:#FF5733;">Languages Studied </p>
              </div>
              <div class="col-sm-3">
                <p class="mb-0" style="font-weight: bold;color: #0f304f;"><c:out default="" value="${student.languagesstudied}" /></p>
              </div>
              <div class="col-sm-3">
                <p class="mb-0"style="font-weight:normal;color:#FF5733;">Core Subjects Studied</p>
              </div>
              <div class="col-sm-3">
                <p class="mb-0" style="font-weight: bold;color: #0f304f;"><c:out default="" value="${student.subsequentprogress}" /></p>
              </div> 
            </div>
            <hr class="studenthr">
            <div class="row mb-2">
              <div class="col-sm-3">
                <p class="mb-0"style="font-weight:normal;color:#FF5733;">Previous School Medium Of Instruction </p>
              </div>
              <div class="col-sm-3">
                <p class="mb-0" style="font-weight: bold;color: #0f304f;"><c:out default="" value="${student.mediumofinstruction}" /></p>
              </div>
              <div class="col-sm-3">
                <p class="mb-0"style="font-weight:normal;color:#FF5733;">Previous School Type</p>
              </div>
              <div class="col-sm-3">
                <p class="mb-0" style="font-weight: bold;color: #0f304f;"> <c:out default="" value="${student.previousschooltype}" /></p>
              </div> 
            </div>
            
            
          </div>
        </div>   --%>  
      
      </div>
   
</body>
</html>
<!DOCTYPE html>
<html lang="en">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student Profile</title>
    <link rel="stylesheet" href="/abc/css/bootstrap.min.css">
    <script src="/abc/js/jquery.min.js"></script>
    <script src="/abc/js/bootstrap.min.js"></script>
    <script src="/abc/js/popper.min.js"></script>
        
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
	font-size:38px;
}
.studenthr{
		border:1px solid rgb(0 0 0);
}
</style>
</head>
<body>
   <section style="background-color: #ffff;padding-left: 10px;">

<!-- profile pic start-->
      <div class="col-lg-4">
        <div class="card mb-4">
          <div class="card-body text-center">
             <img class="rounded-circle" src="data:image;base64,<c:out value="${student.studentpic}"/>" alt="Student's Photo" style="width: 200px;height: 200px;">
            <h5 class="my-3"><c:out value="${student.name}" /></h5>
            <p class="text-muted mb-1"><c:forEach var="splt" items="${fn:split(student.classstudying,'--')}">
						    ${splt} 
							</c:forEach></p>
            
            
          </div>
        </div>
<!-- profile pic end-->




<!--End Parent Detail-->
      </div>
      <div class="col-lg-15">
       <h1 align="center" style="color: #FF914D;font-weight:bold;">Student Detail &nbsp;&nbsp;&nbsp;UID: &nbsp;<c:out value="${student.studentexternalid}" /></h1>
                <hr class="mt-0 mb-4">
        <div class="card mb-4">
          <div class="card-body" style="border: 2px solid rgb(0 0 0);">
            <div class="row">
            	
              <div class="col-sm-3">
                <p class="mb-0"style="font-weight:bold;">Full Name</p>
              </div>
              <div class="col-sm-3">
                <p class="text-muted mb-0"style="font-weight:bold;"><c:out value="${student.name}" /></p>
              </div>
              <div class="col-sm-3">
                <p class="text-muted mb-0"style="font-weight:bold;">Gender</p>
              </div>
              <div class="col-sm-3">
                <p class="text-muted mb-0"style="font-weight:bold;"><c:out default="" value="${student.gender}" /></p>
              </div>
            </div>
            <hr class="studenthr">
            <div class="row">
              <div class="col-sm-3">
                <p class="mb-0"style="font-weight:bold;">DOB</p>
              </div>
              <div class="col-sm-3">
                <p class="text-muted mb-0"style="font-weight:bold;"> <fmt:formatDate value="${student.dateofbirth}" pattern="dd/MM/yyyy"/></p>
              </div>
              <div class="col-sm-3">
                <p class="mb-0"style="font-weight:bold;">age </p>
              </div>
              <div class="col-sm-3">
                <p class="text-muted mb-0"style="font-weight:bold;"><c:out default="" value="${student.age}" /></p>
              </div>  
            </div>
            <hr class="studenthr">
            <div class="row">
              <div class="col-sm-3">
                <p class="mb-0"style="font-weight:bold;">Place of birth</p>
              </div>
              <div class="col-sm-3">
                <p class="text-muted mb-0"style="font-weight:bold;"><c:out default="" value="${student.placeofbirth}" /></p>
              </div>
              <div class="col-sm-3">
                <p class="mb-0"style="font-weight:bold;">Date of admission</p>
              </div>
              <div class="col-sm-3">
                <p class="text-muted mb-0"style="font-weight:bold;"><fmt:formatDate value="${student.admissiondate}" pattern="dd/MM/yyyy"/></p>
              </div> 
            </div>
            <hr class="studenthr">
            <div class="row">
              <div class="col-sm-3">
                <p class="mb-0"style="font-weight:bold;">Studying in class </p>
              </div>
              <div class="col-sm-3">
                <p class="text-muted mb-0"style="font-weight:bold;"><c:forEach var="splt" items="${fn:split(student.classstudying,'--')}">
						    ${splt} 
							</c:forEach></p>
              </div>
              <div class="col-sm-3">
                <p class="mb-0"style="font-weight:bold;">Admitted in Class</p>
              </div>
              <div class="col-sm-3">
                <p class="text-muted mb-0"style="font-weight:bold;"><c:forEach var="splt" items="${fn:split(student.classadmittedin,'--')}">
								    ${splt} 
									</c:forEach></p>
              </div> 
            </div>
            <hr class="studenthr">
            <div class="row">
              <div class="col-sm-3">
                <p class="mb-0"style="font-weight:bold;">Blood Group </p>
              </div>
              <div class="col-sm-3">
                <p class="text-muted mb-0"style="font-weight:bold;"><c:out default="" value="${student.bloodgroup}" /></p>
              </div>
              <div class="col-sm-3">
                <p class="mb-0"style="font-weight:bold;">Nationality</p>
              </div>
              <div class="col-sm-3">
                <p class="text-muted mb-0"style="font-weight:bold;"><c:out default="" value="${student.nationality}" /></p>
              </div> 
            </div>
             <hr class="studenthr">
            <div class="row">
              <div class="col-sm-3">
                <p class="mb-0"style="font-weight:bold;">Religion </p>
              </div>
              <div class="col-sm-3">
                <p class="text-muted mb-0"style="font-weight:bold;"><c:out default="" value="${student.religion}" /></p>
              </div>
              <div class="col-sm-3">
                <p class="mb-0"style="font-weight:bold;">Caste Certificate No.</p>
              </div>
              <div class="col-sm-3">
                <p class="text-muted mb-0"style="font-weight:bold;"><c:out default="" value="${student.studentscastecertno}" /></p>
              </div> 
            </div>
             <hr class="studenthr">
            <div class="row">
              <div class="col-sm-3">
                <p class="mb-0"style="font-weight:bold;">Student Caste </p>
              </div>
              <div class="col-sm-3">
                <p class="text-muted mb-0"style="font-weight:bold;"><c:out default="" value="${student.studentscaste}" /></p>
              </div>
              <div class="col-sm-3">
                <p class="mb-0"style="font-weight:bold;">SocialCategory</p>
              </div>
              <div class="col-sm-3">
                <p class="text-muted mb-0"style="font-weight:bold;"><c:out default="" value="${student.socialcategory}" /></p>
              </div> 
            </div>
             <hr class="studenthr">
            <div class="row">
              <div class="col-sm-3">
                <p class="mb-0"style="font-weight:bold;">Belong to BPL </p>
              </div>
              <div class="col-sm-3">
                <p class="text-muted mb-0"style="font-weight:bold;"><c:if test="${(student.belongtobpl ==0)}">    
           						  <c:out default="" value="No" />
           						 </c:if>
                                <c:if test="${(student.belongtobpl ==1)}">    
           						  <c:out default="" value="Yes" />
           						 </c:if></p>
              </div>
              <div class="col-sm-3">
                <p class="mb-0"style="font-weight:bold;">BPL Card No.</p>
              </div>
              <div class="col-sm-3">
                <p class="text-muted mb-0"style="font-weight:bold;"> <c:out default="" value="${student.bplcardno}" /></p>
              </div> 
            </div>
             <hr class="studenthr">
            <div class="row">
              <div class="col-sm-3">
                <p class="mb-0"style="font-weight:bold;">Bhagyalakshmi Bond No. </p>
              </div>
              <div class="col-sm-3">
                <p class="text-muted mb-0"style="font-weight:bold;"><c:out default="" value="${student.bhagyalakshmibondnumber}" /></p>
              </div>
              <div class="col-sm-3">
                <p class="mb-0"style="font-weight:bold;">Student's Aadhar Card No.</p>
              </div>
              <div class="col-sm-3">
                <p class="text-muted mb-0"style="font-weight:bold;"><c:out default="" value="${student.disabilitychild}" /></p>
              </div> 
            </div>
	     <hr class="studenthr">
            <div class="row">
              <div class="col-sm-3">
                <p class="mb-0"style="font-weight:bold;">Special Category </p>
              </div>
              <div class="col-sm-3">
                <p class="text-muted mb-0"style="font-weight:bold;"><c:out default="" value="${student.specialcategory}" /></p>
              </div>
              <div class="col-sm-3">
                <p class="mb-0"style="font-weight:bold;">Mother Tongue</p>
              </div>
              <div class="col-sm-3">
                <p class="text-muted mb-0"style="font-weight:bold;"><c:out default="" value="${student.mothertongue}" /></p>
              </div> 
            </div>
             <hr class="studenthr">
            <div class="row">
              <div class="col-sm-3">
                <p class="mb-0"style="font-weight:bold;">Created Date </p>
              </div>
              <div class="col-sm-3">
                <p class="text-muted mb-0"style="font-weight:bold;"><fmt:formatDate value="${student.createddate}" pattern="dd/MM/yyyy"/></p>
              </div>
              <div class="col-sm-3">
                <p class="mb-0"style="font-weight:bold;">Admission Year</p>
              </div>
              <div class="col-sm-3">
                <p class="text-muted mb-0"style="font-weight:bold;"><c:out default="" value="${student.yearofadmission}" /></p>
              </div> 
            </div>
	     <hr class="studenthr">
            <div class="row">
              <div class="col-sm-3">
                <p class="mb-0"style="font-weight:bold;">Class Of Leaving </p>
              </div>
              <div class="col-sm-3">
                <p class="text-muted mb-0"style="font-weight:bold;"><c:out default="" value="${student.classonleaving}" /></p>
              </div>
              <div class="col-sm-3">
                <p class="mb-0"style="font-weight:bold;">Date Of Leaving The School</p>
              </div>
              <div class="col-sm-3">
                <p class="text-muted mb-0"style="font-weight:bold;"> <fmt:formatDate value="${student.dateleaving}" pattern="dd/MM/yyyy"/></p>
              </div> 
            </div>
             <hr class="studenthr">
            <div class="row">
              <div class="col-sm-3">
                <p class="mb-0"style="font-weight:bold;">RTE </p>
              </div>
              <div class="col-sm-3">
                <p class="text-muted mb-0"style="font-weight:bold;"><c:if test="${(student.rte ==1)}">    
           						  <c:out default="" value="Yes" />
           						 </c:if>
                                <c:if test="${(student.rte == 0)}">    
           						  <c:out default="" value="No" />
           						 </c:if></p>
              </div>
              <div class="col-sm-3">
                <p class="mb-0"style="font-weight:bold;">Remarks</p>
              </div>
              <div class="col-sm-3">
                <p class="text-muted mb-0"style="font-weight:bold;"><c:out default="" value="${student.remarks}" /></p>
              </div> 
            </div>
             <hr class="studenthr">
            <div class="row">
              <div class="col-sm-3">
                <p class="mb-0"style="font-weight:bold;">Reason For Leaving</p>
              </div>
              <div class="col-sm-3">
                <p class="text-muted mb-0"style="font-weight:bold;"><c:out default="" value="${student.reasonleaving}" /></p>
              </div>
              <div class="col-sm-3">
                <p class="mb-0"style="font-weight:bold;">No. & Date Of Transfer Certificate Issued</p>
              </div>
              <div class="col-sm-3">
                <p class="text-muted mb-0"style="font-weight:bold;"> <c:out default="" value="${student.notcissued}" /> :
                                <fmt:formatDate value="${student.datetcissued}" pattern="dd/MM/yyyy"/></p>
              </div> 
            </div>
          </div>
</div>
<!-- end student detail-->
        
       <h1 align="center" style="color: #FF914D;font-weight:bold;">Parent Detail</h1>
                <hr class="mt-0 mb-4">

        <div class="card mb-4">
          <div class="card-body" style="border: 2px solid rgb(0 0 0);">
            <div class="row">
            	
              <div class="col-sm-3">
                <p class="mb-0"style="font-weight:bold;">Father's Name</p>
              </div>
              <div class="col-sm-3">
                <p class="text-muted mb-0"style="font-weight:bold;"><c:out default="" value="${parents.fathersname}" /></p>
              </div>
              <div class="col-sm-3">
                <p class="text-muted mb-0"style="font-weight:bold;">Mother's Name</p>
              </div>
              <div class="col-sm-3">
                <p class="text-muted mb-0"style="font-weight:bold;"><c:out default="" value="${parents.mothersname}" /></p>
              </div>
            </div>
            <hr class="studenthr">
            <div class="row">
              <div class="col-sm-3">
                <p class="mb-0"style="font-weight:bold;">Father's Qualification</p>
              </div>
              <div class="col-sm-3">
                <p class="text-muted mb-0"style="font-weight:bold;"><c:out default="" value="${parents.fathersqualification}" /></p>
              </div>
              <div class="col-sm-3">
                <p class="mb-0"style="font-weight:bold;">Mother's Qualification</p>
              </div>
              <div class="col-sm-3">
                <p class="text-muted mb-0"style="font-weight:bold;"><c:out default="" value="${parents.mothersqualification}" /></p>
              </div>  
            </div>
            <hr class="studenthr">
            <div class="row">
              <div class="col-sm-3">
                <p class="mb-0"style="font-weight:bold;">Father's Caste Certificate No.</p>
              </div>
              <div class="col-sm-3">
                <p class="text-muted mb-0"style="font-weight:bold;"><c:out value="${parents.fatherscastecertno}" /></p>
              </div>
              <div class="col-sm-3">
                <p class="mb-0"style="font-weight:bold;">Mother's Caste</p>
              </div>
              <div class="col-sm-3">
                <p class="text-muted mb-0"style="font-weight:bold;"><c:out default="" value="${parents.motherscastecertno}" /></p>
              </div> 
            </div>
            <hr class="studenthr">
            <div class="row">
              <div class="col-sm-3">
                <p class="mb-0"style="font-weight:bold;">Guardian's Name & Address </p>
              </div>
              <div class="col-sm-3">
                <p class="text-muted mb-0"style="font-weight:bold;"><c:out value="${student.guardiandetails}" /></p>
              </div>
              <div class="col-sm-3">
                <p class="mb-0"style="font-weight:bold;">Annual Income</p>
              </div>
              <div class="col-sm-3">
                <p class="text-muted mb-0"style="font-weight:bold;"><c:out default="" value="${parents.parentsannualincome}" /></p>
              </div> 
            </div>
            <hr class="studenthr">
            <div class="row">
              <div class="col-sm-3">
                <p class="mb-0"style="font-weight:bold;">Contact Number </p>
              </div>
              <div class="col-sm-3">
                <p class="text-muted mb-0"style="font-weight:bold;"><c:out default="" value="${parents.contactnumber}" /></p>
              </div>
              <div class="col-sm-3">
                <p class="mb-0"style="font-weight:bold;">Co-Contact Number</p>
              </div>
              <div class="col-sm-3">
                <p class="text-muted mb-0"style="font-weight:bold;"><c:out default="" value="${parents.cocontactnumber}" /></p>
              </div> 
            </div>
             <hr class="studenthr">
            <div class="row">
              <div class="col-sm-3">
                <p class="mb-0"style="font-weight:bold;">Email </p>
              </div>
              <div class="col-sm-3">
                <p class="text-muted mb-0"style="font-weight:bold;"><c:out default=""
								value="${parents.email}" /></p>
              </div>
              <div class="col-sm-3">
                <p class="mb-0"style="font-weight:bold;">Number of Dependents</p>
              </div>
              <div class="col-sm-3">
                <p class="text-muted mb-0"style="font-weight:bold;"><c:out default=""
								value="${parents.noofdependents}" /></p>
              </div> 
            </div>
             <hr class="studenthr">
            <div class="row">
              <div class="col-sm-3">
                <p class="mb-0"style="font-weight:bold;">Permanent Address </p>
              </div>
              <div class="col-sm-3">
                <p class="text-muted mb-0"style="font-weight:bold;"><c:out default="" value="${parents.addresspermanent}" /></p>
              </div>
              <div class="col-sm-3">
                <p class="mb-0"style="font-weight:bold;">Temporary Address</p>
              </div>
              <div class="col-sm-3">
                <p class="text-muted mb-0"style="font-weight:bold;"><c:out default="" value="${parents.addresstemporary}" /></p>
              </div> 
            </div>
            <hr class="studenthr">
            <div class="row">
              <div class="col-sm-3">
                <p class="mb-0"style="font-weight:bold;">Number of Dependents </p>
              </div>
              <div class="col-sm-3">
                <p class="text-muted mb-0"style="font-weight:bold;"> <c:out default="" value="${parents.noofdependents}" /></p>
              </div>
              <div class="col-sm-3">
                <p class="mb-0"style="font-weight:bold;">Note</p>
              </div>
              <div class="col-sm-3">
                <p class="text-muted mb-0"style="font-weight:bold;"><c:out default="" value="${parents.remarks}" /></p>
              </div> 
            </div>
          </div>
        </div>

<!-- end student detail -->
<!--previous school detai-->
	<h1 align="center" style="color: #FF914D;font-weight:bold;">Previous School Detail</h1>
                <hr class="mt-0 mb-4">
        <div class="card mb-4">
          <div class="card-body" style="border: 2px solid rgb(0 0 0);">
            <div class="row">
            	
              <div class="col-sm-3">
                <p class="mb-0"style="font-weight:bold;">Transfer Certificate No.</p>
              </div>
              <div class="col-sm-3">
                <p class="text-muted mb-0"style="font-weight:bold;"> <c:out default="" value="${student.nooftc}" /></p>
              </div>
              <div class="col-sm-3">
                <p class="text-muted mb-0"style="font-weight:bold;">Date Of Transfer Certificate</p>
              </div>
              <div class="col-sm-3">
                <p class="text-muted mb-0"style="font-weight:bold;"><fmt:formatDate value="${student.dateoftc}" pattern="dd/MM/yyyy"/></p>
              </div>
            </div>
            
            <hr class="studenthr">
            <div class="row">
              <div class="col-sm-3">
                <p class="mb-0"style="font-weight:bold;">Previous Class Studied</p>
              </div>
              <div class="col-sm-3">
                <p class="text-muted mb-0"style="font-weight:bold;"> <c:out default="" value="${student.stdlaststudied}" /></p>
              </div>
              <div class="col-sm-3">
                <p class="mb-0"style="font-weight:bold;">Previous School Name</p>
              </div>
              <div class="col-sm-3">
                <p class="text-muted mb-0"style="font-weight:bold;"><c:out default="" value="${student.schoollastattended}" /></p>
              </div> 
            </div>
            <hr class="studenthr">
            <div class="row">
              <div class="col-sm-3">
                <p class="mb-0"style="font-weight:bold;">Languages Studied </p>
              </div>
              <div class="col-sm-3">
                <p class="text-muted mb-0"style="font-weight:bold;"><c:out default="" value="${student.languagesstudied}" /></p>
              </div>
              <div class="col-sm-3">
                <p class="mb-0"style="font-weight:bold;">Core Subjects Studied</p>
              </div>
              <div class="col-sm-3">
                <p class="text-muted mb-0"style="font-weight:bold;"><c:out default="" value="${student.subsequentprogress}" /></p>
              </div> 
            </div>
            <hr class="studenthr">
            <div class="row">
              <div class="col-sm-3">
                <p class="mb-0"style="font-weight:bold;">Previous School Medium Of Instruction </p>
              </div>
              <div class="col-sm-3">
                <p class="text-muted mb-0"style="font-weight:bold;"><c:out default="" value="${student.mediumofinstruction}" /></p>
              </div>
              <div class="col-sm-3">
                <p class="mb-0"style="font-weight:bold;">Previous School Type</p>
              </div>
              <div class="col-sm-3">
                <p class="text-muted mb-0"style="font-weight:bold;"> <c:out default="" value="${student.previousschooltype}" /></p>
              </div> 
            </div>
            
            
          </div>
        </div>
      </div>
</section>
</body>
</html>
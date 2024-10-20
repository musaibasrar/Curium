<!DOCTYPE html>
<html lang="en">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student Profile</title>
    <link rel="stylesheet" href="/greatindiaacademy/css/bootstrap.min.css">
    <script src="/greatindiaacademy/js/jquery.min.js"></script>
    <script src="/greatindiaacademy/js/bootstrap.min.js"></script>
    <script src="/greatindiaacademy/js/popper.min.js"></script>
        
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
        <div class="card mb-2" style="width: 360px;padding:0.25rem;">
          <div class="card-body text-left">
             <img class="rounded-circle" src="data:image;base64,<c:out value="${student.studentpic}"/>" alt="Student's Photo" style="width: 200px;height: 200px;">
            <h5 class="my-3"><c:out value="${student.name}" /></h5>
            <p class="text-muted mb-1"><c:forEach var="splt" items="${fn:split(student.classstudying,'--')}">
						    ${splt} 
							</c:forEach></p>
            
            
          </div>
        </div>
<!-- profile pic end-->
 <div class="card-body text-left" style="width: 360px;padding:0.25rem;">
       <h5 align="left" style="color: #FF914D;font-weight:bold;">Student Details<br>UID: &nbsp;<c:out value="${student.studentexternalid}" /></h5>
                <hr class="mt-0 mb-2">
                <div class="card-body" style="border: 2px solid rgb(0 0 0);">
            <div class="row">
            	
              <div class="col-sm-6">
                <p class="mb-0"style="font-weight:normal;color:#90ccb8;">Full Name</p>
              </div>
              <div class="col-sm-6">
                <p class="text-muted mb-0"style="font-weight:bold;"><c:out value="${student.name}" /></p>
              </div>
              </div>
              <hr class="studenthr">
              <div class="row">
              <div class="col-sm-6">
                <p class="text-muted mb-0"style="font-weight:normal;color:#90ccb8;">Gender</p>
              </div>
              <div class="col-sm-6">
                <p class="text-muted mb-0"style="font-weight:bold;"><c:out default="" value="${student.gender}" /></p>
              </div>
            </div>
            <hr class="studenthr">
            <div class="row">
              <div class="col-sm-6">
                <p class="mb-0"style="font-weight:normal;color:#90ccb8;">DOB</p>
              </div>
              <div class="col-sm-6">
                <p class="text-muted mb-0"style="font-weight:bold;"> <fmt:formatDate value="${student.dateofbirth}" pattern="dd/MM/yyyy"/></p>
              </div>
              </div>
               <hr class="studenthr">
              <div class="row">
              <div class="col-sm-6">
                <p class="mb-0"style="font-weight:normal;color:#90ccb8;">Age </p>
              </div>
              <div class="col-sm-6">
                <p class="text-muted mb-0"style="font-weight:bold;"><c:out default="" value="${student.age}" /></p>
              </div>  
            </div>
            <hr class="studenthr">
            <div class="row">
              <div class="col-sm-6">
                <p class="mb-0"style="font-weight:normal;color:#90ccb8;">Place of Birth</p>
              </div>
              <div class="col-sm-6">
                <p class="text-muted mb-0"style="font-weight:bold;"><c:out default="" value="${student.placeofbirth}" /></p>
              </div>
              </div>
               <hr class="studenthr">
              <div class="row">
              <div class="col-sm-6">
                <p class="mb-0"style="font-weight:normal;color:#90ccb8;">Date of Admission</p>
              </div>
              <div class="col-sm-6">
                <p class="text-muted mb-0"style="font-weight:bold;"><fmt:formatDate value="${student.admissiondate}" pattern="dd/MM/yyyy"/></p>
              </div> 
            </div>
            <hr class="studenthr">
            <div class="row">
              <div class="col-sm-6">
                <p class="mb-0"style="font-weight:normal;color:#90ccb8;">Studying in Class </p>
              </div>
              <div class="col-sm-6">
                <p class="text-muted mb-0"style="font-weight:bold;"><c:forEach var="splt" items="${fn:split(student.classstudying,'--')}">
						    ${splt} 
							</c:forEach></p>
              </div>
               </div>
               <hr class="studenthr">
              <div class="row">
              <div class="col-sm-6">
                <p class="mb-0"style="font-weight:normal;color:#90ccb8;">Admitted in Class</p>
              </div>
              <div class="col-sm-6">
                <p class="text-muted mb-0"style="font-weight:bold;"><c:forEach var="splt" items="${fn:split(student.classadmittedin,'--')}">
								    ${splt} 
									</c:forEach></p>
              </div> 
            </div>
            Here
			 <hr class="studenthr">
              <div class="row">
              <div class="col-sm-6">
                <p class="mb-0"style="font-weight:normal;color:#90ccb8;">Blood Group </p>
              </div>
              <div class="col-sm-6">
                <p class="text-muted mb-0"style="font-weight:bold;"><c:out default="" value="${student.bloodgroup}" /></p>
              </div>
              </div>
              
              <hr class="studenthr">
              <div class="row">
              <div class="col-sm-6">
                <p class="mb-0"style="font-weight:normal;color:#90ccb8;">Nationality</p>
              </div>
              <div class="col-sm-6">
                <p class="text-muted mb-0"style="font-weight:bold;"><c:out default="" value="${student.nationality}" /></p>
              </div> 
            </div>
            
             <hr class="studenthr">
              <div class="row">
              <div class="col-sm-6">
                <p class="mb-0"style="font-weight:normal;color:#90ccb8;">Religion </p>
              </div>
              <div class="col-sm-6">
                <p class="text-muted mb-0"style="font-weight:bold;"><c:out default="" value="${student.religion}" /></p>
              </div>
              </div>
              
              <hr class="studenthr">
              <div class="row">
              <div class="col-sm-6">
                <p class="mb-0"style="font-weight:normal;color:#90ccb8;">Caste Certificate No.</p>
              </div>
              <div class="col-sm-6">
                <p class="text-muted mb-0"style="font-weight:bold;"><c:out default="" value="${student.studentscastecertno}" /></p>
              </div> 
            </div>
            
             <hr class="studenthr">
              <div class="row">
              <div class="col-sm-6">
                <p class="mb-0"style="font-weight:normal;color:#90ccb8;">Student Caste </p>
              </div>
              <div class="col-sm-6">
                <p class="text-muted mb-0"style="font-weight:bold;"><c:out default="" value="${student.studentscaste}" /></p>
              </div>
              </div>
              
              <hr class="studenthr">
              <div class="row">
              <div class="col-sm-6">
                <p class="mb-0"style="font-weight:normal;color:#90ccb8;">Social Category</p>
              </div>
              <div class="col-sm-6">
                <p class="text-muted mb-0"style="font-weight:bold;"><c:out default="" value="${student.socialcategory}" /></p>
              </div> 
            </div>
            
             <hr class="studenthr">
              <div class="row">
              <div class="col-sm-6">
                <p class="mb-0"style="font-weight:normal;color:#90ccb8;">Belong to BPL </p>
              </div>
              <div class="col-sm-6">
                <p class="text-muted mb-0"style="font-weight:bold;"><c:if test="${(student.belongtobpl ==0)}">    
           						  <c:out default="" value="No" />
           						 </c:if>
                                <c:if test="${(student.belongtobpl ==1)}">    
           						  <c:out default="" value="Yes" />
           						 </c:if></p>
              </div>
              </div>
              
              <hr class="studenthr">
              <div class="row">
              <div class="col-sm-6">
                <p class="mb-0"style="font-weight:normal;color:#90ccb8;">BPL Card No.</p>
              </div>
              <div class="col-sm-6">
                <p class="text-muted mb-0"style="font-weight:bold;"> <c:out default="" value="${student.bplcardno}" /></p>
              </div> 
            </div>
            
             <hr class="studenthr">
              <div class="row">
              <div class="col-sm-6">
                <p class="mb-0"style="font-weight:normal;color:#90ccb8;">Bhagyalakshmi Bond No. </p>
              </div>
              <div class="col-sm-6">
                <p class="text-muted mb-0"style="font-weight:bold;"><c:out default="" value="${student.bhagyalakshmibondnumber}" /></p>
              </div>
              </div>
              
              <hr class="studenthr">
              <div class="row">
              <div class="col-sm-6">
                <p class="mb-0"style="font-weight:normal;color:#90ccb8;">Student's Aadhar Card No.</p>
              </div>
              <div class="col-sm-6">
                <p class="text-muted mb-0"style="font-weight:bold;"><c:out default="" value="${student.disabilitychild}" /></p>
              </div> 
            </div>
            
	     <hr class="studenthr">
              <div class="row">
              <div class="col-sm-6">
                <p class="mb-0"style="font-weight:normal;color:#90ccb8;">Special Category </p>
              </div>
              <div class="col-sm-6">
                <p class="text-muted mb-0"style="font-weight:bold;"><c:out default="" value="${student.specialcategory}" /></p>
              </div>
              </div>
              
              <hr class="studenthr">
              <div class="row">
              <div class="col-sm-6">
                <p class="mb-0"style="font-weight:normal;color:#90ccb8;">Mother Tongue</p>
              </div>
              <div class="col-sm-6">
                <p class="text-muted mb-0"style="font-weight:bold;"><c:out default="" value="${student.mothertongue}" /></p>
              </div> 
            </div>
            
             <hr class="studenthr">
              <div class="row">
              <div class="col-sm-6">
                <p class="mb-0"style="font-weight:normal;color:#90ccb8;">Created Date </p>
              </div>
              <div class="col-sm-6">
                <p class="text-muted mb-0"style="font-weight:bold;"><fmt:formatDate value="${student.createddate}" pattern="dd/MM/yyyy"/></p>
              </div>
              </div>
              
              <hr class="studenthr">
              <div class="row">
              <div class="col-sm-6">
                <p class="mb-0"style="font-weight:normal;color:#90ccb8;">Admission Year</p>
              </div>
              <div class="col-sm-6">
                <p class="text-muted mb-0"style="font-weight:bold;"><c:out default="" value="${student.yearofadmission}" /></p>
              </div> 
            </div>
            
	     <hr class="studenthr">
              <div class="row">
              <div class="col-sm-6">
                <p class="mb-0"style="font-weight:normal;color:#90ccb8;">Class Of Leaving </p>
              </div>
              <div class="col-sm-6">
                <p class="text-muted mb-0"style="font-weight:bold;"><c:out default="" value="${student.classonleaving}" /></p>
              </div>
              </div>
              
              <hr class="studenthr">
              <div class="row">
              <div class="col-sm-6">
                <p class="mb-0"style="font-weight:normal;color:#90ccb8;">Date Of Leaving The School</p>
              </div>
              <div class="col-sm-6">
                <p class="text-muted mb-0"style="font-weight:bold;"> <fmt:formatDate value="${student.dateleaving}" pattern="dd/MM/yyyy"/></p>
              </div> 
            </div>
            
             <hr class="studenthr">
              <div class="row">
              <div class="col-sm-6">
                <p class="mb-0"style="font-weight:normal;color:#90ccb8;">RTE </p>
              </div>
              <div class="col-sm-6">
                <p class="text-muted mb-0"style="font-weight:bold;"><c:if test="${(student.rte ==1)}">    
           						  <c:out default="" value="Yes" />
           						 </c:if>
                                <c:if test="${(student.rte == 0)}">    
           						  <c:out default="" value="No" />
           						 </c:if></p>
              </div>
              </div>
              
              <hr class="studenthr">
              <div class="row">
              <div class="col-sm-6">
                <p class="mb-0"style="font-weight:normal;color:#90ccb8;">Remarks</p>
              </div>
              <div class="col-sm-6">
                <p class="text-muted mb-0"style="font-weight:bold;"><c:out default="" value="${student.remarks}" /></p>
              </div> 
            </div>
            
             <hr class="studenthr">
              <div class="row">
              <div class="col-sm-6">
                <p class="mb-0"style="font-weight:normal;color:#90ccb8;">Reason For Leaving</p>
              </div>
              <div class="col-sm-6">
                <p class="text-muted mb-0"style="font-weight:bold;"><c:out default="" value="${student.reasonleaving}" /></p>
              </div>
              </div>
              
              <hr class="studenthr">
              <div class="row">
              <div class="col-sm-6">
                <p class="mb-0"style="font-weight:normal;color:#90ccb8;">No. & Date Of Transfer Certificate Issued</p>
              </div>
              <div class="col-sm-6">
                <p class="text-muted mb-0"style="font-weight:bold;"> <c:out default="" value="${student.notcissued}" /> :
                                <fmt:formatDate value="${student.datetcissued}" pattern="dd/MM/yyyy"/></p>
              </div> 
            </div>
            </div>
<!-- end student detail-->
        <br>
       <h5 align="left" style="color: #FF914D;font-weight:bold;">Parent Details</h5>
                <hr class="mt-0 mb-2">

          <div class="card-body" style="border: 2px solid rgb(0 0 0);">
              <div class="row">
              <div class="col-sm-6">
                <p class="mb-0"style="font-weight:normal;color:#90ccb8;">Father's Name</p>
              </div>
              <div class="col-sm-6">
                <p class="text-muted mb-0"style="font-weight:bold;"><c:out default="" value="${parents.fathersname}" /></p>
              </div>
              </div>
              
              <hr class="studenthr">
              <div class="row">
              <div class="col-sm-6">
                <p class="text-muted mb-0"style="font-weight:normal;color:#90ccb8;">Mother's Name</p>
              </div>
              <div class="col-sm-6">
                <p class="text-muted mb-0"style="font-weight:bold;"><c:out default="" value="${parents.mothersname}" /></p>
              </div>
            </div>
            
            <hr class="studenthr">
              <div class="row">
              <div class="col-sm-6">
                <p class="mb-0"style="font-weight:normal;color:#90ccb8;">Father's Qualification</p>
              </div>
              <div class="col-sm-6">
                <p class="text-muted mb-0"style="font-weight:bold;"><c:out default="" value="${parents.fathersqualification}" /></p>
              </div>
              </div>
              
              <hr class="studenthr">
              <div class="row">
              <div class="col-sm-6">
                <p class="mb-0"style="font-weight:normal;color:#90ccb8;">Mother's Qualification</p>
              </div>
              <div class="col-sm-6">
                <p class="text-muted mb-0"style="font-weight:bold;"><c:out default="" value="${parents.mothersqualification}" /></p>
              </div>  
            </div>
            
            <hr class="studenthr">
              <div class="row">
              <div class="col-sm-6">
                <p class="mb-0"style="font-weight:normal;color:#90ccb8;">Father's Caste Certificate No.</p>
              </div>
              <div class="col-sm-6">
                <p class="text-muted mb-0"style="font-weight:bold;"><c:out value="${parents.fatherscastecertno}" /></p>
              </div>
              </div>
              
              <hr class="studenthr">
              <div class="row">
              <div class="col-sm-6">
                <p class="mb-0"style="font-weight:normal;color:#90ccb8;">Mother's Caste</p>
              </div>
              <div class="col-sm-6">
                <p class="text-muted mb-0"style="font-weight:bold;"><c:out default="" value="${parents.motherscastecertno}" /></p>
              </div> 
            </div>
            
            <hr class="studenthr">
              <div class="row">
              <div class="col-sm-6">
                <p class="mb-0"style="font-weight:normal;color:#90ccb8;">Guardian's Name & Address </p>
              </div>
              <div class="col-sm-6">
                <p class="text-muted mb-0"style="font-weight:bold;"><c:out value="${student.guardiandetails}" /></p>
              </div>
              </div>
              
              <hr class="studenthr">
              <div class="row">
              <div class="col-sm-6">
                <p class="mb-0"style="font-weight:normal;color:#90ccb8;">Annual Income</p>
              </div>
              <div class="col-sm-6">
                <p class="text-muted mb-0"style="font-weight:bold;"><c:out default="" value="${parents.parentsannualincome}" /></p>
              </div> 
            </div>
            
            <hr class="studenthr">
              <div class="row">
              <div class="col-sm-6">
                <p class="mb-0"style="font-weight:normal;color:#90ccb8;">Contact Number </p>
              </div>
              <div class="col-sm-6">
                <p class="text-muted mb-0"style="font-weight:bold;"><c:out default="" value="${parents.contactnumber}" /></p>
              </div>
              </div>
              
              <hr class="studenthr">
              <div class="row">
              <div class="col-sm-6">
                <p class="mb-0"style="font-weight:normal;color:#90ccb8;">Co-Contact Number</p>
              </div>
              <div class="col-sm-6">
                <p class="text-muted mb-0"style="font-weight:bold;"><c:out default="" value="${parents.cocontactnumber}" /></p>
              </div> 
            </div>
            
             <hr class="studenthr">
              <div class="row">
              <div class="col-sm-6">
                <p class="mb-0"style="font-weight:normal;color:#90ccb8;">Email </p>
              </div>
              <div class="col-sm-6">
                <p class="text-muted mb-0"style="font-weight:bold;"><c:out default=""
								value="${parents.email}" /></p>
              </div>
              </div>
              
              <hr class="studenthr">
              <div class="row">
              <div class="col-sm-6">
                <p class="mb-0"style="font-weight:normal;color:#90ccb8;">Number of Dependents</p>
              </div>
              <div class="col-sm-6">
                <p class="text-muted mb-0"style="font-weight:bold;"><c:out default=""
								value="${parents.noofdependents}" /></p>
              </div> 
            </div>
            
             <hr class="studenthr">
              <div class="row">
              <div class="col-sm-6">
                <p class="mb-0"style="font-weight:normal;color:#90ccb8;">Permanent Address </p>
              </div>
              <div class="col-sm-6">
                <p class="text-muted mb-0"style="font-weight:bold;"><c:out default="" value="${parents.addresspermanent}" /></p>
              </div>
              </div>
              
              <hr class="studenthr">
              <div class="row">
              <div class="col-sm-6">
                <p class="mb-0"style="font-weight:normal;color:#90ccb8;">Temporary Address</p>
              </div>
              <div class="col-sm-6">
                <p class="text-muted mb-0"style="font-weight:bold;"><c:out default="" value="${parents.addresstemporary}" /></p>
              </div> 
            </div>
            
            <hr class="studenthr">
              <div class="row">
              <div class="col-sm-6">
                <p class="mb-0"style="font-weight:normal;color:#90ccb8;">Number of Dependents </p>
              </div>
              <div class="col-sm-6">
                <p class="text-muted mb-0"style="font-weight:bold;"> <c:out default="" value="${parents.noofdependents}" /></p>
              </div>
              </div>
              
              <hr class="studenthr">
              <div class="row">
              <div class="col-sm-6">
                <p class="mb-0"style="font-weight:normal;color:#90ccb8;">Note</p>
              </div>
              <div class="col-sm-6">
                <p class="text-muted mb-0"style="font-weight:bold;"><c:out default="" value="${parents.remarks}" /></p>
              </div> 
            </div>
          </div>
<!-- end student detail -->
<!--previous school detai-->
	<%-- <h1 align="center" style="color: #FF914D;font-weight:bold;">Previous School Detail</h1>
                <hr class="mt-0 mb-4">
         <div class="card mb-4">
          <div class="card-body" style="border: 2px solid rgb(0 0 0);">
            <div class="row">
            	
              <div class="col-sm-3">
                <p class="mb-0"style="font-weight:normal;color:#90ccb8;">Transfer Certificate No.</p>
              </div>
              <div class="col-sm-3">
                <p class="text-muted mb-0"style="font-weight:bold;"> <c:out default="" value="${student.nooftc}" /></p>
              </div>
              <div class="col-sm-3">
                <p class="text-muted mb-0"style="font-weight:normal;color:#90ccb8;">Date Of Transfer Certificate</p>
              </div>
              <div class="col-sm-3">
                <p class="text-muted mb-0"style="font-weight:bold;"><fmt:formatDate value="${student.dateoftc}" pattern="dd/MM/yyyy"/></p>
              </div>
            </div>
            
            <hr class="studenthr">
            <div class="row">
              <div class="col-sm-3">
                <p class="mb-0"style="font-weight:normal;color:#90ccb8;">Previous Class Studied</p>
              </div>
              <div class="col-sm-3">
                <p class="text-muted mb-0"style="font-weight:bold;"> <c:out default="" value="${student.stdlaststudied}" /></p>
              </div>
              <div class="col-sm-3">
                <p class="mb-0"style="font-weight:normal;color:#90ccb8;">Previous School Name</p>
              </div>
              <div class="col-sm-3">
                <p class="text-muted mb-0"style="font-weight:bold;"><c:out default="" value="${student.schoollastattended}" /></p>
              </div> 
            </div>
            <hr class="studenthr">
            <div class="row">
              <div class="col-sm-3">
                <p class="mb-0"style="font-weight:normal;color:#90ccb8;">Languages Studied </p>
              </div>
              <div class="col-sm-3">
                <p class="text-muted mb-0"style="font-weight:bold;"><c:out default="" value="${student.languagesstudied}" /></p>
              </div>
              <div class="col-sm-3">
                <p class="mb-0"style="font-weight:normal;color:#90ccb8;">Core Subjects Studied</p>
              </div>
              <div class="col-sm-3">
                <p class="text-muted mb-0"style="font-weight:bold;"><c:out default="" value="${student.subsequentprogress}" /></p>
              </div> 
            </div>
            <hr class="studenthr">
            <div class="row">
              <div class="col-sm-3">
                <p class="mb-0"style="font-weight:normal;color:#90ccb8;">Previous School Medium Of Instruction </p>
              </div>
              <div class="col-sm-3">
                <p class="text-muted mb-0"style="font-weight:bold;"><c:out default="" value="${student.mediumofinstruction}" /></p>
              </div>
              <div class="col-sm-3">
                <p class="mb-0"style="font-weight:normal;color:#90ccb8;">Previous School Type</p>
              </div>
              <div class="col-sm-3">
                <p class="text-muted mb-0"style="font-weight:bold;"> <c:out default="" value="${student.previousschooltype}" /></p>
              </div> 
            </div>
            
            
          </div>
        </div>   --%>       
        </div>   
</body>
</html>
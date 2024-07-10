<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPEhtml>
<html>
<head>
<title>bootstrap practice</title>
<link rel="stylesheet" href="bootstrap1.css"/>

<link 

href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min

.css" rel="stylesheet" integrity="sha384-

EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" 

crossorigin="anonymous">
<style>
.container{
border: 0px solid red;
text-align:center;
background-color:white;
}
.row{
border: 0px solid green;
text-align:center;
}
.col-4{
border: 0px solid blue;
text-align:center;
}
#f1{
background-color:lightblue;
}
#f2{
background-color:red;
}
#f3{
background-color:yellow;
}
#f4{
background-color:red;
}
#f5{
background-color:lightgreen;
}
#f6{
background-color:purple;
}
#f7{
background-color:grey;
}
#f8{
background-color:purple;
}
#f9{
background-color:green;
}
.fa-graduation-cap{
font-size:50px;
margin-top:20px;
}
.fa-bell{
font-size:70px;
}
.fa-book{
font-size:50px;
margin-top:20px;
}
.fa-money-check-alt{
font-size:50px;
margin-top:20px;
}
.fa-check-circle{
font-size:50px;
margin-top:20px;
}
</style>
<link rel="stylesheet" 

href="https://cdnjs.cloudflare.com/ajax/libs/font-

awesome/6.4.2/css/all.min.css" integrity="sha512-

z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn

+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" 

crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>

<!--coding area-->
 <div class="container">

  <div class="row">
    
    <div class="col-6" id="f1">
     <i class="fa-solid fa-graduation-cap"></i>
     <a target="mainFrame" href="/madina/StudentProcess/ViewDetailsbyexternalid?id=${username}&urlbranchid=${Parents.student.branchid}" style="font-size: 12px;"><h5>Student Profile</h5></a>
     
    </div> 
    
     
    <div class="col-6" id="f3">
    <i class="fa-solid fa-book"></i>
     <a target="mainFrame" href="/madina/DiaryProcess/viewDiaryStudentParent?id=${username}&urlbranchid=${Parents.student.branchid}" style="font-size: 12px;"><h5>Student Diary</h5></a>
    </div>
    
  </div> 
  <div class="row">
    <div class="col-6" id="f4">
      <i class="fas fa-money-check-alt"></i>
     <a target="mainFrame" href="/madina/StudentProcess/ViewFeesDetailsbyexternalid?id=${username}&urlbranchid=${Parents.student.branchid}" style="font-size: 12px;"><h5>Fees</h5></a>
    </div> 
   
    <div class="col-6" id="f6">
    <i class="fas fa-check-circle"></i>
     <a target="mainFrame" href="/madina/MarksDetailsProcess/generateReportParent?id=${username}" style="font-size: 12px;">
     <h5>Progress Report</h5></a>
    </div>
  </div>
 
  
  <div class="footer"id="foot">
  <h5>c schoolshare</h5>
  </div>  
</div>
<!--end of coding area-->
</body>
<script>
//alert("hii");
</script>
</html>

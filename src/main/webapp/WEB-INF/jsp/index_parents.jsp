<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta charset="UTF-8">
<title>index_parents</title>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/greatindiaacademy/css/bootstrap.min.css">
        <script src="/greatindiaacademy/js/jquery.min.js"></script>
        <script src="/greatindiaacademy/js/bootstrap.min.js"></script>
        <script src="/greatindiaacademy/js/popper.min.js"></script> 
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
font-size:70px;
}
.fa-bell{
font-size:70px;
}
.fa-book{
font-size:70px;
}
.fa-money-check-alt{
font-size:70px;
}
.fa-check-circle{
font-size:70px;
}
 
 </style>
        <script type="text/javascript">

            /***********************************************
             * Collapsible Frames script- © Dynamic Drive (www.dynamicdrive.com)
             * This notice must stay intact for use
             * Visit http://www.dynamicdrive.com/ for full source code
             ***********************************************/

            var columntype=""
            var defaultsetting=""

            function getCurrentSetting(){
                if (document.body)
                    return (document.body.cols)? document.body.cols : document.body.rows
            }

            function setframevalue(coltype, settingvalue){
                if (coltype=="rows")
                    document.body.rows=settingvalue
                else if (coltype=="cols")
                    document.body.cols=settingvalue
            }

            function resizeFrame(contractsetting){
                if (getCurrentSetting()!=defaultsetting)
                    setframevalue(columntype, defaultsetting)
                else
                    setframevalue(columntype, contractsetting)
            }

            function init(){
                if (!document.all && !document.getElementById) return
                if (document.body!=null){
                    columntype=(document.body.cols)? "cols" : "rows"
                    defaultsetting=(document.body.cols)? document.body.cols : document.body.rows
                }
                else
                    setTimeout("init()",100)
            }

            setTimeout("init()",100)

        </script>
</head>
<%
//allow access only if session exists
String user = null;
if(session.getAttribute("userAuth") == null){
	response.sendRedirect("/greatindiaacademy/UserProcess/sessionTimeOut");
}else user = (String) session.getAttribute("userAuth");
String userName = null;
String sessionID = null;
Cookie[] cookies = request.getCookies();
if(cookies !=null){
for(Cookie cookie : cookies){
	if(cookie.getName().equals("user")) userName = cookie.getValue();
	if(cookie.getName().equals("JSESSIONID")) sessionID = cookie.getValue();
}
}
%>
    <frameset    rows="95,*"frameborder="0" border="0" framespacing="0"  >
    
    
		   <c:if test="${(userType == 'parents')}">
			    <frame   src="/greatindiaacademy/header_parent"   name="topFrame" scrolling="NO" noresize frameborder="0">
		   </c:if>
			<frame src="/greatindiaacademy/welcomeparent" name="mainFrame" scrolling="yes" />
           <!--  <frameset  cols="195,*" frameborder="0" border="0" framespacing="0">
                <frame  src="/greatindiaacademy/left_parent" name="leftFrame" scrolling="yes"  frameborder="1"   />
                <frame src="/greatindiaacademy/welcomeparent" name="mainFrame" scrolling="yes" />
            </frameset> -->

   </frameset>




    <noframes>
  <!--   <div class="container">
<h1>SCHOOL NAME</h1>
<h1>SCHOOL</h1>
<h1>NAME</h1>
  <div class="row">
    
    <div class="col-6" id="f1">
     <i class="fa-solid fa-graduation-cap"></i>
     <a target="mainFrame" href="/greatindiaacademy/StudentProcess/ViewDetailsbyexternalid?id=<c:out value='${username}'/>&urlbranchid=<c:out value='${Parents.student.branchid}'/>" style="font-size: 12px;"><h5>Student Profile</h5></a>
     
    </div> 
    
     
    <div class="col-6" id="f3">
    <i class="fa-solid fa-book"></i>
     <a target="mainFrame" href="/greatindiaacademy/DiaryProcess/viewDiaryStudentParent?id=<c:out value='${username}'/>&urlbranchid=<c:out value='${Parents.student.branchid}'/>" style="font-size: 12px;"><h5>Student Diary</h5></a>
    </div>
    
  </div> 
  <div class="row">
    <div class="col-6" id="f4">
      <i class="fas fa-money-check-alt"></i>
     <h5>Fees</h5>
    </div> 
   
    <div class="col-6" id="f6">
    <i class="fas fa-check-circle"></i>
     <h5>Progress Report</h5>
    </div>
  </div>
 
  
  <div class="footer"id="foot">
  <h5>c schoolshare</h5>
  </div>  
</div>-->
<body>

</body>
</noframes>
</html>
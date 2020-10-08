<%-- 
    Document   : student_details
    Created on : Jan 4, 2013, 4:39:24 PM
    Author     : Musaib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Student Details</title>

        <script type="text/javascript" language="JavaScript" src="js/motionpack.js"></script>
        <link rel="stylesheet" href="css/datePicker/jquery-ui-1.8.18.custom.css">
        <link rel="stylesheet" href="css/graph/jquery.jqplot.css">

        <link rel="stylesheet" href="css/datePicker/demos.css">
        <script type="text/javascript" src="js/datePicker/jquery-1.7.1.js"></script>
        <script type="text/javascript" src="js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
        <script type="text/javascript" src="js/datePicker/ui/jquery.ui.dialog.js"></script>
        <script type="text/javascript" src="js/datePicker/ui/jquery.ui.autocomplete.js"></script>
        <script type="text/javascript" src="js/datePicker/ui/jquery.ui.core.js"></script>
        <script type="text/javascript" src="js/datePicker/ui/jquery.ui.widget.js"></script>
        <script type="text/javascript" src="js/datePicker/ui/jquery.ui.datepicker.js"></script>
        <script type="text/javascript" src="js/datePicker/ui/jquery.ui.accordion.js"></script>
        <script type="text/javascript" src="js/datePicker/ui/sliderAccess.js"></script>
        <script type="text/javascript" src="js/datePicker/ui/jquery-ui-timepicker-addon.js"></script>

        <script  type="text/javascript" src="js/datePicker/ui/jquery.ui.position.js"></script>
        <script type="text/javascript" src="js/datePicker/ui/jquery.ui.mouse.js"></script>
        <script type="text/javascript" src="js/datePicker/ui/jquery.ui.draggable.js"></script>
        <script type="text/javascript" src="js/datePicker/ui/jquery.ui.resizable.js"></script>
        <script type="text/javascript" src="js/graph/jquery.jqplot.js"></script>
        <script  type="text/javascript" src="js/graph/plugins/jqplot.dateAxisRenderer.min.js"></script>
        <script type="text/javascript" src="js/graph/plugins/jqplot.barRenderer.min.js"></script>
        <script type="text/javascript" src="js/graph/plugins/jqplot.categoryAxisRenderer.min.js"></script>
        <script type="text/javascript" src="js/graph/plugins/jqplot.cursor.min.js"></script>
        <script type="text/javascript" src="js/graph/plugins/jqplot.highlighter.min.js"></script>
        <script type="text/javascript" src="js/graph/plugins/jqplot.dragable.min.js"></script>
        <script type="text/javascript" src="js/graph/plugins/jqplot.trendline.min.js"></script>

        <style type="text/css">
        .dataText {
                border-radius:3px;
                font-family: Tahoma;
                color: #4b6a84;
                font-size: 13px;
                letter-spacing: normal;
                text-align: center;
                background-color: #E3EFFF;

            }
            .dataTextFees {
                border-radius:3px;
                font-family: ariel;
                font-weight: bold;
                color: #4b6a84;
                font-size: 16px;
                letter-spacing: normal;
                text-align: center;
                background-color: #E3EFFF;

            }
       		.dataTextDueFees{
				                border-radius:3px;
                font-family: ariel;
                font-weight: bold;
                color: red;
                font-size: 16px;
                letter-spacing: normal;
                text-align: center;
                background-color: #E3EFFF;

            }       		
       			 
           
            .dataTextInActive {
                border-radius:1px;
                font-family: Tahoma;
                color: #4b6a84;
                background-color: #E3EFFF;
               	font-size: 12px;
                font-weight: bold;
                letter-spacing: normal;
                text-align: center;
                vertical-align: top;
                text-decoration:none;
            }
            .headerText {
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
                background-image: url("images/ui-bg_diagonals-small_50_466580_40x40.png");
            }
            .headerTD{
                background-color:#4b6a84;
                color: #FFFFFF;
                font-family: Tahoma;
                font-size: 13px;
                text-transform: uppercase;
                text-align: center;
                font-weight: bold;
            }
            .smallheaderTD{
                color: #4b6a84;
                font-family: Tahoma;
                font-size: 11px;
                text-transform: uppercase;
                text-align: left;
                font-weight: bold;
            }
            .textFieldFixedWidth{
                width: 57px;
            }
            .subHeaderTD{
                color: #325F6D;
                font-family: Tahoma;
                font-size: 11px;
                text-transform: uppercase;
                text-align: left;
                font-weight: bold;
            }
            .divCSS{
                overflow:  scroll;
                height: 100%;
                width: 100%;
            }

            .fiedlSet {
                border-top-width: 1px;
                border-right-width: 1px;
                border-bottom-width: 1px;
                border-left-width: 1px;
                border-top-style: solid;
                border-right-style: solid;
                border-bottom-style: solid;
                border-left-style: solid;
                border-width: 1;
                width: 100%;
                color: #000000;
                font-size: 16px;
                font-weight: bold;
                font-variant: normal;
                font-stretch: wider;
                background-color: #e2ebf3;
                border-top-color: #5d7e9b;
                border-right-color: #5d7e9b;
                border-bottom-color: #5d7e9b;
                border-left-color: #5d7e9b;
            }
            .legendCSS {
                color: #666666;
            }
            .tableCSS {
                width: 100%;
                height: 100%;
                position: absolute;
                left: 0px;
                top: 0px;
            }
            .textAreaCSS {
                height: auto;
                width: auto;
            }
            .textField {
                border-top-style: solid;
                border-right-style: solid;
                border-bottom-style: solid;
                border-left-style: solid;
                border-top-color: #5d7e9b;
                border-right-color: #5d7e9b;
                border-bottom-color: #5d7e9b;
                border-left-color: #5d7e9b;
                border-top-width: 1px;
                border-right-width: 1px;
                border-bottom-width: 1px;
                border-left-width: 1px;
                width: auto;
                height: auto;
            }
           
            .alignRight {
                font-family: Tahoma;
                font-size: 14px;
                font-style: normal;
                text-transform: capitalize;
                color: #325F6D;
                text-align: right;
                vertical-align: middle;
                font-weight: bold;
                width: 200px;
            }
            .alignRightMultiple {
                font-family: Tahoma;
                font-size: 11px;
                font-weight: bolder;
                text-align: right;
                vertical-align: middle;
                font-style: normal;
                color: #325F6D;
            }
            .alignCentreMultiple {
                font-family: Arial, Helvetica, sans-serif;
                font-size: 12px;
                font-weight: bolder;
                text-align: center;
                vertical-align: middle;
                font-style: normal;
                color: #000000;
            }
            .autoAdjust {
                height: auto;
                width: auto;
            }
            .radioSpanCSS {
                font-size: 12px;
                font-family: Arial, Helvetica, sans-serif;
                text-align: left;
                vertical-align: middle;
            }
            .radioCSS {
                background-position: left center;
            }
            .spanText {
                font-family: Arial, Helvetica, sans-serif;
                font-size: 12px;
                font-weight: bold;
                color: #000000;
            }
            .emptyFieldSet {
                border-top-color: #FA7676;
                border-right-color: #FA7676;
                border-bottom-color: #FA7676;
                border-left-color: #FA7676;
                border-top-style: solid;
                border-right-style: solid;
                border-bottom-style: solid;
                border-left-style: solid;
                border-top-width: thin;
                border-right-width: thin;
                border-bottom-width: thin;
                border-left-width: thin;
                background-image: url(images/close.JPG);
                background-repeat: repeat-y;
                background-attachment: scroll;
                background-position: right;
                width: auto;
                height: auto;
                display: inline;
            }
            .style1 {
                font-family: Tahoma;
                font-size: 14px;
            }
            .style2 {
                color: #666666;
                font-family: Tahoma;
                font-size: 14px;
            }
            .style4 {
                font-size: 12px;
                font-family: Tahoma;
                text-align: left;
                vertical-align: middle;
                color: #325f6d;
            }
            .tablerows{
                font-size: 14px;
                font-family: Tahoma;
                text-align: left;
                font-weight: bold;
                padding-top: 0px;
                padding-left: 20px;
                padding-right: 50px;
                width: 200px;

            }

			
.educationtable {
font-family: arial, sans-serif;
  border-collapse: collapse;
  width: 100%;
	
}

.educationtableheaderdescription {
  border: 1px solid #5d7e9b;
  text-align: center;
  padding: 8px;
  color: #0a3b4a;
  font-size: 14px;
}

.educationtabledetaildescription {
  border: 1px solid #5d7e9b;
  text-align: center;
  padding: 8px;
  font-size: 11px;
}


.certificatetable {
font-family: arial, sans-serif;
  border-collapse: collapse;
  width: 50%;
  float: left;	
}

.photocopy {
  text-align: center;
  font-size: 16px; 
  font-weight: bold;
}
        </style>
        
        <script type="text/javascript">
            
            function updateStudent(){
               
                var form1=document.getElementById("form1");
                form1.action="Controller?process=StudentProcess&action=updateStudentDetails&id=<c:out value='${student.sid}'/>&urlbranchid=<c:out value='${student.branchid}'/>";
                form1.submit();
            }
            
        </script>

        <script type="text/javascript">
            $(function() {

                $(".accordion").accordion({
                    collapsible: true,
                    navigation: true,
                    active: false,
                    autoHeight: false});
                $("#accordion1").accordion({
                    collapsible: true,
                    navigation: true,
                    active: false,
                    autoHeight: false});
                $("#accordion2").accordion({
                    collapsible: true,
                    navigation: true,
                    active: false,
                    autoHeight: false});
                $("#accordion3").accordion({
                    collapsible: true,
                    navigation: true,
                    active: false,
                    autoHeight: false});
                /*$("#set")
                .button()
                .click(function() {
                    updateVisit();
                });  */
                $( "#cancel" )
                .button()
                .click(function() {
                    Cancel();


                });
                
                $( "#modify" )
                .button()
                .click(function() {
                    updateStudent();

                });
                
            });
            var type='<c:out default="" value="${typeOfUser}"/>';
            function checkUsertype(){
            	
            	if(type == 'reception'){
            		document.getElementById('modify').style.visibility = 'hidden';
            	}
            }
        </script>

    </head>
      <%
//allow access only if session exists
String user = null;
if(session.getAttribute("userAuth") == null){
	response.sendRedirect("Controller?process=UserProcess&action=sessionTimeOut");
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
    <body background="images/bg.jpg" onload="checkUsertype();">
        <jsp:useBean id="now" class="java.util.Date" />
        <fmt:formatDate var="today" type="date" value="${now}" />
        <form  method="post" id="form1">

            <table width="100%">
                <tr>
                    <td  class="headerTD">NAME: &nbsp;<c:out value="${student.name}" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                      

                    </td>
                </tr>

            </table>
            <div class="accordion" style="width: 100%;height: 100%">
		
                <h3><a href="#">Student Details</a></h3>
                <div>
                
                <table id="table1" align="center">
                    
                    
                    <tr>
                    	<!-- <td></td> -->
                    <td class="alignRight">
                    <img src="data:image;base64,<c:out value="${student.studentpic}"/>" alt="Student's Photo" style="width: 150px;height: 150px;">
                    </td>
                    </tr>
                    </table>

                    <table id="table1" align="center">
                    
                    <tr class="alignRight" height="30">
                    	<td class="tablerows"></td>
                    </tr>
                    
                    	<tr>
                         
                            <td   class="alignRight" height="30">Admission Number
                            </td>
                            <td class="tablerows">
                                <c:out default="" value="${student.admissionnumber}" />
                            </td>
                            
                            <td   class="alignRight" height="30">UIN
                            </td>
                            <td class="tablerows">
                                <c:out default="" value="${student.studentexternalid}" />
                            </td>
                            
                        </tr>

					<tr>
                            <td  class="alignRight" height="30">Student Name</td>
                            <td class="tablerows" style="text-transform:uppercase">
                                <c:out default="" value="${student.name}" />
                            </td>
                            <td  class="alignRight" height="30" >Gender
                            </td>
                            <td class="tablerows" >
                                <c:out default="" value="${student.gender}" />
                            </td>
                        </tr>
                        <tr>
                            <td  class="alignRight" height="30">Date Of Birth</td>
                            <td class="tablerows" >
                                <%-- <c:out value="${student.dateofbirth}" /> --%>
                                <fmt:formatDate value="${student.dateofbirth}" pattern="dd/MM/yyyy"/>
                                
                            </td>
                            <td  class="alignRight" height="30" >Age
                            </td>
                            <td  class="tablerows">
                                <c:out default="" value="${student.age}" />
                            </td>
                        </tr>
                        <%-- <tr>
                            <td  class="alignRight" height="30" >Place of birth, Tq, Dist.</td>
                            <td  class="tablerows" >
                              <c:out default="" value="${student.placeofbirth}" />
                            </td>
                           
                        </tr>   --%>
                        
                          
                        <tr>
                            <td  class="alignRight" height="30" >Class Studying</td>
                            <td   class="tablerows">
                            <c:forEach var="splt" items="${fn:split(student.classstudying,'--')}">
						    ${splt} 
							</c:forEach>
                               <%--  <c:out default="" value="${student.classstudying}" /> --%>
                            </td>
                            
                             <td   class="alignRight" height="30" >Mother Tongue
                            </td>
                            <td class="tablerows" >
                                <c:out default="" value="${student.mothertongue}" />
                            </td>   
                            
                            <%-- <td  class="alignRight" height="30">Admitted in class
                            </td>
                            <td class="tablerows">
		                            <c:forEach var="splt" items="${fn:split(student.classadmittedin,'--')}">
								    ${splt} 
									</c:forEach>
                            </td> --%>
                        </tr>
                        
                        
                        
                         <tr>
                            <td   class="alignRight" height="30">Blood Group
                            </td>
                            <td class="tablerows">
                                <c:out default="" value="${student.bloodgroup}" />
                            </td>
                                                        
                            <td   class="alignRight" height="30" >Nationality
                            </td>
                            <td class="tablerows" >
                                <c:out default="" value="${student.nationality}" />
                            </td>
                        </tr>
                        
                        <tr>
                        <td   class="alignRight" height="30" >Religion
                            </td>
                            <td class="tablerows" >
                                <c:out default="" value="${student.religion}" />
                            </td>
                  <%--           
                            <td   class="alignRight" height="30" >Caste
                            </td>
                            <td class="tablerows" >
                                <c:out default="" value="${student.caste}" />
                            </td>
 --%>
                                      
                            <td   class="alignRight" height="30" >Caste Certificate No.
                            </td>
                            <td class="tablerows" >
                                <c:out default="" value="${student.studentscastecertno}" />
                            </td>
                         
                        </tr>
                        <tr>
                         <td   class="alignRight" height="30" >Caste
                            </td>
                            <td class="tablerows" >
                                <c:out default="" value="${student.caste}" />
                            </td>
                            
                            <td   class="alignRight" height="30" >Date of admission
                            </td>
                            <td class="tablerows" >
                            	<fmt:formatDate value="${student.admissiondate}" pattern="dd/MM/yyyy"/>
                            </td>
                            <%-- <td   class="alignRight" height="30" >Social Category
                            </td>
                            <td class="tablerows" >
                                <c:out default="" value="${student.socialcategory}" />
                            </td>  --%>                       
                        
                        </tr>
                        
                        <%-- <tr>
                         <td   class="alignRight" height="30" >Was in receipt of any scholarship
                            </td>
                            <td class="tablerows" >
                               
                                <c:if test="${(student.belongtobpl ==0)}">    
           						  <c:out default="" value="No" />
           						 </c:if>
                                <c:if test="${(student.belongtobpl ==1)}">    
           						  <c:out default="" value="Yes" />
           						 </c:if>
           						 
                            </td>
                            <td   class="alignRight" height="30" >Adhar Card No.
                            </td>
                            <td class="tablerows" >
                                <c:out default="" value="${student.bplcardno}" />
                            </td>                        
                        
                        </tr>
                        
                        <tr>
                         <td   class="alignRight" height="30" >Whether Vaccinated
                            </td>
                            <td class="tablerows" >
                               
                                <c:if test="${(student.bhagyalakshmibondnumber ==0)}">    
           						  <c:out default="" value="No" />
           						 </c:if>
                                <c:if test="${(student.bhagyalakshmibondnumber ==1)}">    
           						  <c:out default="" value="Yes" />
           						 </c:if>
           						 
                            </td>
                            
                            <td   class="alignRight" height="30" >Marks of Identification on Pupil's body
                            </td>
                            <td class="tablerows" >
                                <c:out default="" value="${student.disabilitychild}" />
                            </td>                        
                        </tr>
                        <tr>
                        <td   class="alignRight" height="30" >Special Category
                            </td>
                            <td class="tablerows" >
                                <c:out default="" value="${student.specialcategory}" />
                            </td>
                          <td   class="alignRight" height="30" >Mother Tongue
                            </td>
                            <td class="tablerows" >
                                <c:out default="" value="${student.mothertongue}" />
                            </td>                           
                        </tr> --%>
                        
                       <%--  <tr>
                        
                         <td   class="alignRight" height="30" >RTE
                            </td>
                            <td class="tablerows" >
                               
                                <c:if test="${(student.rte ==1)}">    
           						  <c:out default="" value="Yes" />
           						 </c:if>
                                <c:if test="${(student.rte == 0)}">    
           						  <c:out default="" value="No" />
           						 </c:if>
           						 
                            </td>
   
                            
                        </tr>
                         --%>
                        <tr>
                        
                        <td class="alignRight" height="30">Contact Number</td>
								<td class="tablerows"><c:out default=""
								value="${student.contactnumber}" /></td>
                        
                        	<td class="alignRight" height="30">Email</td>
								<td class="tablerows"><c:out default=""
								value="${student.email}" /></td>
								
                        
                        	
                        </tr>
                             
                        <tr>
                        
                       		<td   class="alignRight" height="30">Remarks
                            </td>
                            <td class="tablerows" >
                                <c:out default="" value="${student.remarks}" />
                                
                            </td>
                            
                             <td   class="alignRight" height="30">Created Date
                            </td>
                            <td class="tablerows">
                                <%-- <c:out default="" value="${student.createddate}" /> --%>
                                <fmt:formatDate value="${student.createddate}" pattern="dd/MM/yyyy"/>
                            </td>
   
                        </tr>           
                                                
                        <tr>
                            <td   class="alignRight" height="30" >Class of leaving
                            </td>
                            <td class="tablerows" >
                                <c:out default="" value="${student.classonleaving}" />
                            </td>
                            
                             <td   class="alignRight" height="30">Date of Leaving the College
                            </td>
                            <td class="tablerows">
                                <fmt:formatDate value="${student.dateleaving}" pattern="dd/MM/yyyy"/>
                            </td>
                          
                           
                        </tr>
                        
                        <tr>
                            <td   class="alignRight" height="30" >Reason for leaving
                            </td>
                            <td class="tablerows" >
                                <c:out default="" value="${student.reasonleaving}" />
                            </td>
                            
                             <td   class="alignRight" height="30">No. & date of TC issued
                            </td>
                            <td class="tablerows">
                            <c:out default="" value="${student.notcissued}" /> :
                                <fmt:formatDate value="${student.datetcissued}" pattern="dd/MM/yyyy"/>
                            </td>
                          
                           
                        </tr>
                        
           

                        <tr>
                            <td   class="alignRight" height="30">
                            </td>
                            <td class="tablerows" >

                            </td>
                            <td   class="alignRight" height="30">
                            </td>
                            <td class="tablerows">

                            </td>
                        </tr>
                    </table>
                </div>
    </div>
    
    <div class="accordion" style="width: 100%;height: 100%">
		
                <h3><a href="#">Educational Qualification</a></h3>
                
               
                <div>

                    <div align="center">
								<table class="educationtable">
									<thead class="educationtableheaderdescription">
										<tr class="educationtableheaderdescription">
											<th class="educationtableheaderdescription">Educational Qualification</th>
											<th class="educationtableheaderdescription">Name of the Board</th>
											<th class="educationtableheaderdescription">Year of Passing</th>
											<!-- <th class="educationtableheaderdescription">No. of Attempts</th> -->
											<th class="educationtableheaderdescription">Total Marks Scored</th>
											<th class="educationtableheaderdescription">% Secured</th>
										
										</tr>
									</thead>
									
									<tbody>
										<tr>
											<td  class="educationtableheaderdescription" style="font-weight: bold;">
												SSLC / 10<sup>th</sup> 
											</td>
											<td  class="educationtabledetaildescription" style="font-weight: bold;">
					                                <c:out default="" value="${student.tenthboard}" />
											</td>
											<td  class="educationtabledetaildescription" style="font-weight: bold;">
											<c:out default="" value="${student.tenthyearofpassing}" />
											</td>
											<%-- <td  class="educationtabledetaildescription" style="font-weight: bold;">
												<c:out default="" value="${student.tenthnoofattempts}" />
											</td> --%>
											<td  class="educationtabledetaildescription" style="font-weight: bold;">
												<c:out default="" value="${student.tenthtotalmarkssecured}" />
											</td>
											<td  class="educationtabledetaildescription" style="font-weight: bold;">
												<c:out default="" value="${student.tenthpercentage}" />
											</td>
										</tr>
										<tr>
											<td  class="educationtableheaderdescription" style="font-weight: bold;">
												 P.U.C / 12<sup>th</sup>  / Inter 
											</td>
											<td  class="educationtabledetaildescription" style="font-weight: bold;">
												<c:out default="" value="${student.twelthboard}" />
											</td>
											<td  class="educationtabledetaildescription" style="font-weight: bold;">
												<c:out default="" value="${student.twelthyearofpassing}" />
											</td>
											<%-- <td  class="educationtabledetaildescription" style="font-weight: bold;">
												<c:out default="" value="${student.twelthnoofattempts}" />
											</td> --%>
											<td  class="educationtabledetaildescription" style="font-weight: bold;">
												<c:out default="" value="${student.twelthtotalmarkssecured}" />
											</td>
											<td  class="educationtabledetaildescription" style="font-weight: bold;">
												<c:out default="" value="${student.twelthpercentage}" />
											</td>
										</tr>
									</tbody>
								
								</table>
								
								<div><p class="photocopy">Photocopies of the Certificate Attached</p></div>
								<table class="certificatetable">
									<thead class="educationtableheaderdescription">
										<tr class="educationtableheaderdescription">
											<th class="educationtableheaderdescription">Sr.No.</th>
											<th class="educationtableheaderdescription">Certificate</th>
											<th class="educationtableheaderdescription">Yes/No</th>
										</tr>
									</thead>
									
									<tbody>
										<tr>
											<td  class="educationtabledetaildescription" style="font-weight: bold;">
												<p>1.</p>
											</td>
											<td  class="educationtabledetaildescription" style="font-weight: bold;">
												<p>10<sup>th</sup> Marks Card</p>
											</td>
											<td  class="educationtableheaderdescription" style="font-weight: bold;">
											
													<input type="checkbox" value="yes" name="markscard10" ${student.markscard10 == 'yes' ? 'checked' : ''}  />
												
											</td>
											
										</tr>
										<tr>
											<td  class="educationtabledetaildescription" style="font-weight: bold;">
												<p>2.</p>
											</td>
											<td  class="educationtabledetaildescription" style="font-weight: bold;">
												<p>PUC / 12<sup>th</sup> Marks Card</p>
											</td>
											<td  class="educationtableheaderdescription" style="font-weight: bold;">
												<input type="checkbox" value="yes" name="markscard12" ${student.markscard12 == 'yes' ? 'checked' : ''} />
											</td>
										</tr>
										<tr>
											<td  class="educationtabledetaildescription" style="font-weight: bold;">
												<p>3.</p>
											</td>
												<td  class="educationtabledetaildescription" style="font-weight: bold;">
												<p>Migration Certificate</p>
											</td>
											<td  class="educationtableheaderdescription" style="font-weight: bold;">
													<input type="checkbox" value="yes" name="certificatemigration" ${student.certificatemigration == 'yes' ? 'checked' : ''} />
											</td>
										</tr>
										<tr>
											<td  class="educationtabledetaildescription" style="font-weight: bold;">
												<p>4.</p>
											</td>	
											<td  class="educationtabledetaildescription" style="font-weight: bold;">
												<p>Eligibility Certificate</p>
											</td>
											<td  class="educationtableheaderdescription" style="font-weight: bold;">
												<input type="checkbox" value="yes" name="eligibilitycertificate" ${student.eligibilitycertificate == 'yes' ? 'checked' : ''} />
											</td>
										</tr>
									
									</tbody>
									
									</table>
									
									<table class="certificatetable">
									<thead class="educationtableheaderdescription">
										<tr class="educationtableheaderdescription">
											<th class="educationtableheaderdescription">Sr.No.</th>
											<th class="educationtableheaderdescription">Certificate</th>
											<th class="educationtableheaderdescription">Yes/No</th>
										</tr>
									</thead>
									
									<tbody>
										<tr>
											<td  class="educationtabledetaildescription" style="font-weight: bold;">
												<p>5.</p>
											</td>
											<td  class="educationtabledetaildescription" style="font-weight: bold;padding-top: 10px;padding-bottom: 8px;">
												<p>Transfer Certificate</p>
											</td>
											<td  class="educationtableheaderdescription" style="font-weight: bold;">
													<input type="checkbox" value="yes" name="transfercertificate" ${student.transfercertificate == 'yes' ? 'checked' : ''} />
											</td>
											
										</tr>
										<tr>
											<td  class="educationtabledetaildescription" style="font-weight: bold;">
												<p>6.</p>
											</td>
											<td  class="educationtabledetaildescription" style="font-weight: bold;padding-top: 10px;padding-bottom: 8px;">
												<p>Conduct Certificate</p>
											</td>
											<td  class="educationtableheaderdescription" style="font-weight: bold;">
													<input type="checkbox" value="yes" name="conductcertificate" ${student.conductcertificate == 'yes' ? 'checked' : ''} />
											</td>
										</tr>
										<tr>
											<td  class="educationtabledetaildescription" style="font-weight: bold;">
												<p>7.</p>
											</td>
											<td  class="educationtabledetaildescription" style="font-weight: bold;">
												<p>Aadhaar Card</p>
											</td>
											<td  class="educationtableheaderdescription" style="font-weight: bold;">
											<input type="checkbox" value="yes" name="adharcard" ${student.adharcard == 'yes' ? 'checked' : ''} />
											</td>
										</tr>
										<tr>
											<td  class="educationtabledetaildescription" style="font-weight: bold;">
												<p>8.</p>
											</td>
											<td  class="educationtabledetaildescription" style="font-weight: bold;">
												<p>Passport Size Photos-6 nos.</p>
											</td>
											<td  class="educationtableheaderdescription" style="font-weight: bold;">
													<input type="checkbox" value="yes" name="passportsizephotos" ${student.passportsizephotos == 'yes' ? 'checked' : ''} />
											</td>
										</tr>
									
									</tbody>
									
									</table>
							</div>
                </div>
    </div>
    
    <div class="accordion" style="width: 100%;height: 100%">
		
                <h3><a href="#">Documents</a></h3>
                <div>

                    <table id="table1" align="center">
                    
                        <tr>
                    <td class="alignRight" height="30">
                    <img src="data:image;base64,<c:out value="${student.studentdoc1}"/>" alt="Student's Doc 1" style="width: 200px;height: 200px;">
                    </td>
                    <td class="tablerows" >
                    <a download="studentdoc1.jpg" href="data:image/jpg;base64,<c:out value="${student.studentdoc1}"/>">Download</a>
                    </td>
                    <td class="alignRight" height="30">
                    <img src="data:image;base64,<c:out value="${student.studentdoc5}"/>" alt="Student's Doc 5" style="width: 200px;height: 200px;">
                    </td>
                    <td class="tablerows" >
                    <a download="studentdoc6.jpg" href="data:image/jpg;base64,<c:out value="${student.studentdoc5}"/>">Download</a>
                    </td>
                    </tr>
                    
                    <tr>
                    <td class="alignRight" height="30">
                    <img src="data:image;base64,<c:out value="${student.studentdoc2}"/>" alt="Student's Doc 2" style="width: 200px;height: 200px;">
                    </td>
                    <td class="tablerows" >
                    <a download="studentdoc2.jpg" href="data:image/jpg;base64,<c:out value="${student.studentdoc2}"/>">Download</a>
                    </td>
                     <td class="alignRight" height="30">
                    <img src="data:image;base64,<c:out value="${student.studentdoc6}"/>" alt="Student's Doc 6" style="width: 200px;height: 200px;">
                    </td>
                    <td class="tablerows" >
                    <a download="studentdoc7.jpg" href="data:image/jpg;base64,<c:out value="${student.studentdoc6}"/>">Download</a>
                    </td>
                    </tr>
                    
                    <tr>
                    <td class="alignRight" height="30">
                    <img src="data:image;base64,<c:out value="${student.studentdoc3}"/>" alt="Student's Doc 3" style="width: 200px;height: 200px;">
                    </td>
                    <td class="tablerows" >
                    <a download="studentdoc3.jpg" href="data:image/jpg;base64,<c:out value="${student.studentdoc3}"/>">Download</a>
                    </td>
                    <td class="alignRight" height="30">
                    <img src="data:image;base64,<c:out value="${student.studentdoc7}"/>" alt="Student's Doc 7" style="width: 200px;height: 200px;">
                    </td>
                    <td class="tablerows" >
                    <a download="studentdoc8.jpg" href="data:image/jpg;base64,<c:out value="${student.studentdoc7}"/>">Download</a>
                    </td>
                    </tr>
                    
                    <tr>
                    <td class="alignRight" height="30">
                    <img src="data:image;base64,<c:out value="${student.studentdoc4}"/>" alt="Student's Doc 4" style="width: 200px;height: 200px;">
                    </td>
                    <td class="tablerows" >
                    <a download="studentdoc4.jpg" href="data:image/jpg;base64,<c:out value="${student.studentdoc4}"/>">Download</a>
                    </td>
                    <td class="alignRight" height="30">
                    <img src="data:image;base64,<c:out value="${student.studentdoc8}"/>" alt="Student's Doc 8" style="width: 200px;height: 200px;">
                    </td>
                    <td class="tablerows" >
                    <a download="studentdoc9.jpg" href="data:image/jpg;base64,<c:out value="${student.studentdoc8}"/>">Download</a>
                    </td>
                    </tr>
                     
                    </table>
                </div>
    </div>
    
    	<div class="accordion" style="width: 100%;height: 100%">
		
                <h3><a href="#">Previous School Details</a></h3>
                
               
                <div>

                    <table id="table1" align="center">
                    
                        <tr>
                            <td  class="alignRight" height="30">Transfer Certificate No.
                            </td>
                            <td  class="tablerows" align="left">
									<c:out value="${student.nooftc}" />
                            </td>
                             <td  class="alignRight" height="30">Date of Transfer Certificate
                            </td>
                            <td  class="tablerows" >
                                   <fmt:formatDate value="${student.dateoftc}" pattern="dd/MM/yyyy"/>
                            </td>
                        </tr>
                        
                         <tr>
                        <%-- <td   class="alignRight" height="30" >Previous Class Studied</td>
                            <td class="tablerows" >
                                <c:out default="" value="${student.stdlaststudied}" />
                            </td> --%>
                            
                            
                            <td   class="alignRight" height="30" >Previous School Name</td>
                            <td class="tablerows" >
                                  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<c:out default="" value="${student.schoollastattended}" />
                            </td>
                        </tr>
                        
                         <%-- <tr>
                        <td   class="alignRight" height="30" >Languages Studied</td>
                            <td class="tablerows" >
                                <c:out default="" value="${student.languagesstudied}" />
                            </td>
                            
                            
                            <td   class="alignRight" height="30" >Core Subjects Studied</td>
                            <td class="tablerows" >
                                <c:out default="" value="${student.subsequentprogress}" />
                            </td>
                        </tr> 
                        
                        <tr>
                       <td class="alignRight" height="30">Previous School Medium of
								Instruction</td>
                            <td class="tablerows">
                                <c:out default="" value="${student.mediumofinstruction}" />
                            </td>
                            
                            
                            <td class="alignRight" height="30">Previous School
								Type </td>
                            <td class="tablerows">
                                <c:out default="" value="${student.previousschooltype}" />
                            </td>
                        </tr>--%>

                        <tr>
                            <td   class="alignRight" height="30">
                            </td>
                            <td class="tablerows" >

                            </td>
                            <td   class="alignRight" height="30">
                            </td>
                            <td class="tablerows">

                            </td>
                        </tr>
                    </table>
                </div>
    </div>
    
    <%-- <div class="accordion" style="width: 100%;height: 100%">
		
                <h3><a href="#">Bank Details</a></h3>
                <div>

                    <table  border="0px" width="100%"  id="table1" align="center">
                         <tr>
                            <td   class="alignRight" height="30" >Bank Name
                           
                            </td>
                            <td class="tablerows" >
                              ${student.bankname}
                            </td>
                            
                             <td   class="alignRight" height="30">Bank IFSC Code
                            </td>
                            <td class="tablerows">
                                ${student.bankifsc}
                            </td>
                        </tr>
                        
                        <tr>
                            <td   class="alignRight" height="30" >Account No.
                            </td>
                            <td class="tablerows" >
                               ${student.accno}
                            </td>
                        </tr>
                        
                        <tr>
                            <td   class="alignRight" height="30">
                            </td>
                            <td class="tablerows" >

                            </td>
                            <td   class="alignRight" height="30">
                            </td>
                            <td class="tablerows">

                            </td>
                        </tr>
                        
                    </table>
                </div>
    </div> --%>
    
            <div id="accordion1" style="width: 100%;height: 100%">

                <h3><a href="#">Parent's Details</a></h3>
                <div>

                    <table  width="80%"  id="table1" align="center">

                        <tr>

                            <td  class="alignRight" height="30">Father's Name</td>
                            <td class="tablerows" style="text-transform:uppercase">
                                <c:out default="" value="${parents.fathersname}" />
                            </td>
                            <td  class="alignRight" height="30" >Mother's Name
                            </td>
                            <td class="tablerows" style="text-transform:uppercase">
                                <c:out default="" value="${parents.mothersname}" />
                            </td>
                        </tr>
                        
                         <tr>
                            <td  class="alignRight" height="30" >Contact Number</td>
                            <td   class="tablerows">
                                <c:out default="" value="${parents.contactnumber}" />
                            </td>
                            <td  class="alignRight" height="30">Co-Contact Number
                            </td>
                            <td class="tablerows">
                                <c:out default="" value="${parents.cocontactnumber}" />
                            </td>
                        </tr>
                        
                        <tr>
                            <td   class="alignRight" height="30" >Parmanent Address</td>
                            <td class="tablerows" >
                                <c:out default="" value="${parents.addresspermanent}" />
                            </td>
                           
                            
                            <td   class="alignRight" height="30">Temporary Address
                            </td>
                            <td class="tablerows">
                                <c:out default="" value="${parents.addresstemporary}" />
                            </td>
                        
                        </tr>
                        
                        <tr>
                            <td  class="alignRight" height="30">Guardian's name & address</td>
                            <td class="tablerows" >
                                 <c:out value="${student.guardiandetails}" />
                                
                            </td>
                            <td  class="alignRight" height="30" >Annual Income
                            </td>
                            <td  class="tablerows">
                                <c:out default="" value="${parents.parentsannualincome}" />
                            </td>
                        </tr>
                        
                       <%-- <tr>
                            <td  class="alignRight" height="30">Fathers Occupation</td>
                            <td class="tablerows" >
                                 <c:out value="${parents.fatherscastecertno}" />
                                
                            </td>
                            <td  class="alignRight" height="30" >Mothers Occupation
                            </td>
                            <td  class="tablerows">
                                <c:out default="" value="${parents.motherscastecertno}" />
                            </td>
                        </tr>
                        
                        
                        <tr>

                            <td  class="alignRight" height="30">Father's Qualification</td>
                            <td class="tablerows" style="text-transform:uppercase">
                                <c:out default="" value="${parents.fathersqualification}" />
                            </td>
                            <td  class="alignRight" height="30" >Mother's Qualification
                            </td>
                            <td class="tablerows" style="text-transform:uppercase">
                                <c:out default="" value="${parents.mothersqualification}" />
                            </td>
                        </tr> --%>
                        
                       
					<tr>
								
								<td   class="alignRight" height="30">Notes
                            </td>
                            <td class="tablerows" >
                                <c:out default="" value="${parents.remarks}" />
							</td>	
						<%-- <td class="alignRight" height="30">Number Of
							Dependents</td>
						<td class="tablerows"><c:out default=""
								value="${parents.noofdependents}" /></td> --%>
					</tr>
					
					
                        <%-- <tr>
                            <td   class="alignRight" height="30" >Number Of Dependents</td>
                            <td class="tablerows"  >
                                 <c:out default="" value="${parents.noofdependents}" />
                                
                            </td>
                            <td   class="alignRight" height="30">Notes
                            </td>
                            <td class="tablerows" >
                                <c:out default="" value="${parents.remarks}" />
                                
                            </td>


                        </tr> --%>
                        


                        <tr>
                            <td   class="alignRight" height="30">
                            </td>
                            <td class="tablerows" >

                            </td>
                            <td   class="alignRight" height="30">
                            </td>
                            <td class="tablerows">

                            </td>
                        </tr>
                    </table>

                    

                </div>






            </div>
              <div id="accordion2" style="width: 100%;height: 100%">

                <h3><a href="#">Fees Details</a></h3>
                
                <div>
					<div align="center">
					<h class="dataTextFees">Total fees : &#x20B9; ${totalfees}</h>&nbsp;&nbsp;&nbsp;
                <h class="dataTextFees">Total fees paid : &#x20B9; ${sumoffees}</h>&nbsp;&nbsp;&nbsp;
                <h class="dataTextFees">Due Amount : </h>
                <h class="dataTextDueFees"> &#x20B9; ${dueamount}</h>
                </div>
                    <table   width="100%"  border="0" style="border-color:#4b6a84;"  id="myTable">

                    <thead>
                        <tr  >
                            
                            <th title="click to sort" class="headerText">Date of fees</th>
                            <th title="click to sort" class="headerText">Reference Number</th>
                            <th title="click to sort" class="headerText">Total Amount</th>
                            <th title="click to sort" class="headerText">View Details</th>
                             


                        </tr>
                    </thead>

                    <tbody>
                        <c:forEach items="${receiptinfo}" var="receiptinfo">

                            <tr class="trClass" style="border-color:#000000" border="1"  cellpadding="1"  cellspacing="1" >
                                
                                <td  class="dataText"><c:out value="${receiptinfo.date}"/></a></td>
                                <td  class="dataText"><c:out value="${receiptinfo.receiptnumber}"/></a></td>
                                <td class="dataText"><c:out value="${receiptinfo.totalamount}"/></td>
                                <td  class="dataTextInActive"><a class="dataTextInActive" target="_blank" href="Controller?process=FeesCollection&action=ViewDetails&id=<c:out value='${receiptinfo.receiptnumber}'/>&sid=<c:out value='${student.sid}'/>">View Details</a></td>
                                 

                            </tr>
                        </c:forEach>
                        
                        
                    </tbody>
                   
                </table>
				
                    

                </div>






            </div>
            <div id="accordion3" style="width: 100%;height: 100%">

                <h3><a href="#">Fees Structure</a></h3>
                
                <div>
					<div align="center">
				<h class="dataTextFees">Academic Year : ${currentAcademicYear}</h>&nbsp;&nbsp;&nbsp;
				<h class="dataTextFees">Total fees : &#x20B9; ${totalfees}</h>&nbsp;&nbsp;&nbsp;
                <h class="dataTextFees">Total fees paid : &#x20B9; ${sumoffees}</h>&nbsp;&nbsp;&nbsp;
                <h class="dataTextFees">Due Amount : </h>
                <h class="dataTextDueFees"> &#x20B9; ${dueamount}</h>
                </div>
                    <table   width="100%"  border="0" style="border-color:#4b6a84;"  id="myTable">

                    <thead>
                        <tr  >
                            
                            <th title="click to sort" class="headerText"></th>
                            <th title="click to sort" class="headerText"></th>
                            <th title="click to sort" class="headerText"></th>
                            <th title="click to sort" class="headerText">Fees Category</th>
                            <th title="click to sort" class="headerText">Fees Amount&nbsp;</th>
                            <th title="click to sort" class="headerText">Fees Paid&nbsp;</th>
                            <th title="click to sort" class="headerText">Due Amount&nbsp;</th>
                            <th title="click to sort" class="headerText">Concession&nbsp;</th>
                            <th title="click to sort" class="headerText"></th>
                            <th title="click to sort" class="headerText"></th>
                            <th title="click to sort" class="headerText"></th>
                             


                        </tr>
                    </thead>

                    <tbody>
                        <c:forEach items="${feesstructure}" var="feesstructure">

                            <tr class="trClass" style="border-color:#000000" border="1"  cellpadding="1"  cellspacing="1" >
                                
                              	<td class="dataText"></td>
                               	<td class="dataText"></td>
                                <td class="dataText"></td>
                                <td class="dataText"><c:out value="${feesstructure.feescategory.feescategoryname}"/></td>
                                <td class="dataText"><c:out value="${feesstructure.feesamount}"/></td>
                                <td class="dataText"><c:out value="${feesstructure.feespaid}"/></td>
                                <td class="dataText"><c:out value="${feesstructure.feesamount-feesstructure.feespaid}"/></td>
                                <td class="dataText"><c:out value="${feesstructure.concession}"/></td>
                                
                                 

                            </tr>
                        </c:forEach>
                        
                        
                    </tbody>
                   
                </table>
				
                    

                </div>






            </div>
            <table  width="70%"  id="table11" align="center">
                        <tr>
                            <td width="30%"> 

                            </td>
                            <td>
                                <button id="modify" type="submit">Modify</button>
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <button id="cancel" >Cancel</button>
                            </td>

                        </tr>

                    </table>
            <script>
                typeofrelation();
            </script>

        </form>
        <script>
        
            function Cancel(){
                var form1=document.getElementById("form1");
                form1.action="Controller?process=StudentProcess&action=ViewAll";
                form1.submit();
            }
        </script>
    </body>
</html>

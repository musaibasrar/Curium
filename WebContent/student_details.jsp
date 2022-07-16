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
       			 
            <!--
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
                font-size: 11px;
                font-style: normal;
                text-transform: capitalize;
                color: #325F6D;
                text-align: right;
                vertical-align: middle;
                font-weight: bold;
            }
            .alignLeft {
                font-family: Tahoma;
                font-size: 16px;
                font-style: normal;
                text-transform: capitalize;
                color: #325F6D;
                text-align: left;
                vertical-align: middle;
                font-weight: bold;
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
                font-size: 16px;
                font-family: Tahoma;
                text-align: left;
                font-weight: bold;

            }
            -->
            table {
  font-family: arial, sans-serif;
  border-collapse: collapse;
  width: 100%;
}

td, th {
  border: 1px solid #b6cfe2;
  text-align: left;
  padding: 8px;
}
        </style>
        
        <script type="text/javascript">
            
            function updateStudent(){
               
                var form1=document.getElementById("form1");
                form1.action="Controller?process=StudentProcess&action=updateStudentDetails&id=<c:out value='${student.sid}'/>&urlbranchid=<c:out value='${student.branchid}'/>";
                form1.submit();
            }
            
            function typeofrelation(){
                var listitem = document.getElementById("subscriber");
                var listitemtext = listitem.options[listitem.selectedIndex].text;
                alert(listitemtext);
                
                if(listitemtext=="1"){
                    document.getElementById("typeofrelation").value="subscriber";
                    
                }
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
            });
            var type='<c:out default="" value="${typeOfUser}"/>';
            function checkUsertype(){
            	
            	if(type == 'reception'){
            		document.getElementById('modify').style.visibility = 'hidden';
            	}
            }
        </script>
 <script type="text/javascript">
                                $(function() {
                                    $( "#modify" )
                                    .button()
                                    .click(function() {
                                        updateStudent();

                                    });
                                });
                                
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
    <body onload="checkUsertype();">
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

                    <table  border="0px" style="width: auto;height: auto;"  id="table1" align="center">
                    
                    
                    <tr>
                    <td style="border: 0px;"></td>
                    <td style="border: 0px;">
                    <img src="data:image;base64,<c:out value="${student.studentpic}"/>" alt="Student's Photo" style="width: 200px;height: 200px;">
                    </td>
                    <td style="border: 0px;"></td>
                    <td style="width: 200px;border: 0px;"></td>
                    </tr>
                    
                    	<tr>
                         
                            <td   class="alignLeft" height="50">Admission Number
                            </td>
                            <td class="tablerows">
                                <c:out default="" value="${student.admissionnumber}" />
                            </td>
                            
                        <td class="alignLeft" height="50">Registration No.
								</td>
                            <td class="tablerows">
                                <c:out default="" value="${student.registrationnumber}" />&nbsp;&nbsp;&nbsp;&nbsp;
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            </td>
                            
                        </tr>

					<tr>
                            <td  class="alignLeft" height="50">Name</td>
                            <td class="tablerows" style="text-transform:uppercase">
                                <c:out default="" value="${student.name}" />
                            </td>
                            <td  class="alignLeft" height="50" >Gender
                            </td>
                            <td class="tablerows" >
                                <c:out default="" value="${student.gender}" />
                            </td>
                        </tr>
                        <tr>
                            <td  class="alignLeft" height="50">Date Of Birth</td>
                            <td class="tablerows" >
                                <%-- <c:out value="${student.dateofbirth}" /> --%>
                                <fmt:formatDate value="${student.dateofbirth}" pattern="dd/MM/yyyy"/>
                                
                            </td>
                            <td  class="alignLeft" height="50" >Age
                            </td>
                            <td  class="tablerows">
                                <c:out default="" value="${student.age}" />
                            </td>
                        </tr>
                        <tr>
                            <td  class="alignLeft" height="50" >Place of birth</td>
                            <td  class="tablerows" >
                              <c:out default="" value="${student.placeofbirth}" />
                            </td>
                           <td   class="alignLeft" height="50" >Date of admission
                            </td>
                            <td class="tablerows" >
                            	<fmt:formatDate value="${student.admissiondate}" pattern="dd/MM/yyyy"/>
                            </td>
                        </tr>  
                        
                          
                        <tr>
                            <td  class="alignLeft" height="50" >Course</td>
                            <td   class="tablerows">
                            <c:forEach var="splt" items="${fn:split(student.classstudying,'--')}">
						    ${splt} 
							</c:forEach>
                               <%--  <c:out default="" value="${student.classstudying}" /> --%>
                            </td>
                            <td   class="alignLeft" height="50">Blood Group
                            </td>
                            <td class="tablerows">
                                <c:out default="" value="${student.bloodgroup}" />
                            </td>
                        </tr>
                        
                        <tr>
                          <td   class="alignLeft" height="50" >Mother Tongue
                            </td>
                            <td class="tablerows" >
                                <c:out default="" value="${student.mothertongue}" />
                            </td>          
                            
                            <td   class="alignLeft" height="50" >Religion
                            </td>
                            <td class="tablerows" >
                                <c:out default="" value="${student.religion}" />
                            </td>
                                             
                        </tr>
                        
                         <tr>
                            
                            <td   class="alignLeft" height="50" >Caste/Category
                            </td>
                            <td class="tablerows" >
                                <c:out default="" value="${student.studentscaste}" />
                            </td>
                                                        
                            <td   class="alignLeft" height="50" >Nationality
                            </td>
                            <td class="tablerows" >
                                <c:out default="" value="${student.nationality}" />
                            </td>
                        </tr>
                        
                        <tr>
                        
                      
                            <td   class="alignLeft" height="50" >Aadhaar Card
                            </td>
                            <td class="tablerows" >
                                <c:out default="" value="${student.studentscastecertno}" />
                            </td>
                            
                             <td   class="alignLeft" height="50" >Second Language
                            </td>
                            <td class="tablerows" >
                                <c:out default="" value="${student.secondlanguage}" />
                            </td> 
                         
                        </tr>
                                            
                        <tr>
                        
                             <td   class="alignLeft" height="50">Created Date
                            </td>
                            <td class="tablerows">
                                <%-- <c:out default="" value="${student.createddate}" /> --%>
                                <fmt:formatDate value="${student.createddate}" pattern="dd/MM/yyyy"/>
                            </td>
                            
                            <td  width="25%"  class="alignLeft" height="50">Admission Year
                            </td>
                            <td width="25%" class="tablerows" >
                                <c:out default="" value="${student.yearofadmission}" />
                            </td>
   
                        </tr>
                                                
                                                
                         <tr>
								<td style="border: 0px;">
									<div align="left"><h3 style="text-decoration: underline;color: #eb6000;margin-left: 4px;">Additional Details</h3></div>
								</td>
						</tr>
                        <tr>
                            <td   class="alignLeft" height="50" >Class of leaving
                            </td>
                            <td class="tablerows" >
                                <c:out default="" value="${student.classonleaving}" />
                            </td>
                            
                             <td   class="alignLeft" height="50">Date of leaving<br> the school
                            </td>
                            <td class="tablerows">
                                <fmt:formatDate value="${student.dateleaving}" pattern="dd/MM/yyyy"/>
                            </td>
                          
                           
                        </tr>
                        
                        <tr>
                            <td   class="alignLeft" height="50" >Reason for leaving
                            </td>
                            <td class="tablerows" >
                                <c:out default="" value="${student.reasonleaving}" />
                            </td>
                            
                             <td   class="alignLeft" height="50">No. & date of <br>transfer certificate<br> issued
                            </td>
                            <td class="tablerows">
                            <c:out default="" value="${student.notcissued}" /> :
                                <fmt:formatDate value="${student.datetcissued}" pattern="dd/MM/yyyy"/>
                            </td>
                          
                           
                        </tr>
                        
                    </table>
                </div>
    </div>
    
    	<div class="accordion" style="width: 100%;height: 100%">
		
                <h3><a href="#">Previous School Details</a></h3>
                <div>
					<br>
                    <table  border="0px" style="width: auto;height: auto;"  id="table1" align="center">
                    
                    
                    	<tr>
                    	
                    	<td   class="alignLeft" height="50" >Previous School Name</td>
                            <td class="tablerows" width="200px;">
                                <c:out default="" value="${student.schoollastattended}" />
                            </td>
                            
                            <td  class="alignLeft" height="50">Address
                            </td>
                            <td  class="tablerows"  width="200px;">
                               <c:out default="" value="${student.lastschooladdress}" />
                            </td>
                            
                        </tr>
                        
                        <tr>
                            
                             <td   class="alignLeft" height="50" >Total Marks</td>
                            <td class="tablerows" >
                                <c:out default="" value="${student.totalmarks}" />
                            </td>
                            
                            <td   class="alignLeft" height="50" >GPA/Percentage</td>
                            <td class="tablerows" >
                                <c:out default="" value="${student.percentage}" />
                            </td>
                        </tr>
                        
                      <tr>
                             <td   class="alignLeft" height="50" >Admission In Class</td>
                            <td class="tablerows" >
                                <c:out default="" value="${student.stdlaststudied}" />
                            </td>
                            
                            <td   class="alignLeft" height="50" >Course</td>
                            <td class="tablerows" >
                                <c:out default="" value="${student.lastcourse}" />
                            </td>
                        </tr>
                        
                         <tr>
                        	<td   class="alignLeft" height="50" >First Language</td>
                            <td class="tablerows" >
                                <c:out default="" value="${student.lastfirstlanguage}" />
                            </td>
                            
                            
                            <td   class="alignLeft" height="50" >Second Language</td>
                            <td class="tablerows" >
                                <c:out default="" value="${student.lastsecondlanguage}" />
                            </td>
                        </tr>

                    </table>
                    <br>
                    <br>
                </div>
    </div>
    
        
            <div id="accordion1" style="width: 100%;height: 100%">

                <h3><a href="#">Parent's Details</a></h3>
                <div>
					
                    <table  border="0px" style="width: auto;height: auto;" id="table1" align="center">
						
						
							<tr >
								<td style="border: 0px;">
									<div align="left"><h3 style="text-decoration: underline;color: #eb6000;margin-left: 4px;">Father's / Guardian's Details</h3></div>
								</td>
							</tr>
                        <tr>

                            <td  class="alignLeft" height="50">Father Name</td>
                            <td class="tablerows" style="text-transform:uppercase;width: 200px;">
                                <c:out default="" value="${parents.fathersname}" />
                            </td>
                            <td  class="alignLeft" height="50">Occupation</td>
                            <td class="tablerows" width="200px;">
                                 <c:out value="${parents.fatheroccupation}" />
                                
                            </td>

                        </tr>
                        
                        <tr>

                            <td  class="alignLeft" height="50">Qualification</td>
                            <td class="tablerows" style="text-transform:uppercase">
                                <c:out default="" value="${parents.fathersqualification}" />
                            </td>
                            <td  class="alignLeft" height="50" >Contact Number</td>
                            <td   class="tablerows">
                                <c:out default="" value="${parents.contactnumber}" />
                            </td>
                            
                        </tr>
                        
                        <tr>
                            <td  class="alignLeft" height="50" >Annual Income
                            </td>
                            <td  class="tablerows">
                                <c:out default="" value="${parents.parentsannualincome}" />
                            </td>
                            
                           <td  class="alignLeft" height="50">Emergency Contact Number
                            </td>
                            <td class="tablerows">
                                <c:out default="" value="${parents.emergencycontactno}" />
                            </td>
                        </tr>
                        
                        <tr>
                            <td   class="alignLeft" height="50" >Parmanent Address</td>
                            <td class="tablerows" >
                                <c:out default="" value="${parents.addresspermanent}" />
                            </td>
                           
                            
                            <td   class="alignLeft" height="50">Temporary Address
                            </td>
                            <td class="tablerows">
                                <c:out default="" value="${parents.addresstemporary}" />
                            </td>
                        
                        </tr>
                        
                        
                        <tr>
                             <td  class="alignLeft" height="50">Guardian's name & address</td>
                            <td class="tablerows" >
                                 <c:out value="${student.guardiandetails}" />
                                
                            </td>
                            <td   class="alignLeft" height="50">Notes
                            </td>
                            <td class="tablerows" >
                                <c:out default="" value="${parents.remarks}" />
                                
                            </td>


                        </tr>
                        
                        <tr>
								<td style="border: 0px;">
									<div align="left"><h3 style="text-decoration: underline;color: #eb6000;margin-left: 4px;">Mother's Details</h3></div>
								</td>
							</tr>
                        
                        
                         <tr>

                            <td  class="alignLeft" height="50">Mother Name</td>
                            <td class="tablerows" style="text-transform:uppercase">
                                <c:out default="" value="${parents.mothersname}" />
                            </td>
                            <td  class="alignLeft" height="50">Occupation</td>
                            <td class="tablerows" >
                                 <c:out value="${parents.profession}" />
                                
                            </td>

                        </tr>
                        
                        <tr>

                            <td  class="alignLeft" height="50">Qualification</td>
                            <td class="tablerows" style="text-transform:uppercase">
                                <c:out default="" value="${parents.mothersqualification}" />
                            </td>
                            <td  class="alignLeft" height="50">Co-Contact Number
                            </td>
                            <td class="tablerows">
                                <c:out default="" value="${parents.cocontactnumber}" />
                            </td>
                        </tr>

                    </table>
<br>
                    

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
                                <td  class="dataText"><c:out value="${receiptinfo.branchreceiptnumber}"/></a></td>
                                <td class="dataText"><c:out value="${receiptinfo.totalamount}"/></td>
                                <td  class="dataTextInActive"><a class="dataTextInActive" target="_blank" href="Controller?process=FeesCollection&action=ViewFeesDetails&id=<c:out value='${receiptinfo.receiptnumber}'/>&sid=<c:out value='${student.sid}'/>">View Details</a></td>
                                 

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
                            <th title="click to sort" class="headerText">Total Installments&nbsp;</th>
                            <th title="click to sort" class="headerText">Total Fees Amount&nbsp;</th>
                            <th title="click to sort" class="headerText">Fees Paid&nbsp;</th>
                            <th title="click to sort" class="headerText">Due Amount&nbsp;</th>
                            <th title="click to sort" class="headerText">Concession&nbsp;</th>
                            <th title="click to sort" class="headerText">Waive Off&nbsp;</th>
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
                                <td class="dataText"><c:out value="${feesstructure.feescategory.amount}"/></td>
                                <td class="dataText"><c:out value="${feesstructure.totalinstallment}"/></td>
                                <td class="dataText"><c:out value="${feesstructure.feesamount}"/></td>
                                <td class="dataText"><c:out value="${feesstructure.feespaid}"/></td>
                                <td class="dataText"><c:out value="${feesstructure.feesamount-feesstructure.feespaid-feesstructure.concession-feesstructure.waiveoff}"/></td>
                                <td class="dataText"><c:out value="${feesstructure.concession}"/></td>
                                <td class="dataText"><c:out value="${feesstructure.waiveoff}"/></td>
                            </tr>
                        </c:forEach>
                        
                        
                    </tbody>
                   
                </table>
				
                    

                </div>






            </div>
            <table  width="70%"  id="table11" align="center">
                        <tr>
                            <td width="30%" style="border: 0px;"> 

                            </td>
                            <td style="border: 0px;">
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

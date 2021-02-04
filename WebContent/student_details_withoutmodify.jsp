<%-- 
    Document   : student_details_withoutmodify
    Created on : Jan 4, 2013, 4:39:24 PM
    Author     : Musaib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>student_details</title>

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
                font-size: 13px;
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
                font-size: 12px;
                font-family: Tahoma;
                text-align: left;
                font-weight: bold;

            }
            -->
        </style>
        
        <script type="text/javascript">
            
            function updateStudent(){
               
                var form1=document.getElementById("form1");
                form1.action="Controller?process=StudentProcess&action=updateStudentDetails&id=<c:out value='${student.sid}'/>";
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

                $("#accordion").accordion({
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

            <div>
                <table id="table3" width="100%" border="0" cellpadding="1" cellspacing="1" align="center"  >
                    <tr><td >


                            <%--<button id="fullSummary">Full Summary</button>--%>
                            <script type="text/javascript">
                                $(function() {
                                    $( "#set" )
                                    .button()
                                    .click(function() {
                                        setAppointment();

                                    });
                                    $( "#modify" )
                                    .button()
                                    .click(function() {
                                        updateStudent();

                                    });
                                    $( "#fullSummary" )
                                    .button()
                                    .click(function() {
                                        fullSummary();

                                    });
                                });
                                
                                function calIssues(){
                                    var fromIss = document.getElementById("").value;
                                }
                            </script>
                        </td></tr>

                </table>
            </div>

            <table width="100%">
                <tr>
                    <td  class="headerTD">NAME: &nbsp;<c:out value="${student.name}" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                      

                    </td>
                </tr>

            </table>
            <div id="accordion" style="width: 100%;height: 100%">
		
                <h3><a href="#">Student Details</a></h3>
                <div>

                    <table  border="0px" width="100%"  id="table1" align="center">
                    
                    
                    <tr>
                    <td>
                    <img src="data:image;base64,<c:out value="${student.studentpic}"/>" alt="Student's Photo">
                    </td>
                    </tr>
                    
                    	<tr>
                        
                         
                            <td  width="25%"  class="alignLeft" height="50">Admission Number
                            </td>
                            <td width="25%" class="tablerows">
                                <c:out default="" value="${student.admissionnumber}" />
                            </td>
                            
                        
                            <td width="25%"   class="alignLeft" height="50" >Cumulative record no. with date of opening</td>
                             <td width="25%" class="tablerows">
                                <c:out default="" value="${student.crecord}" /> : 
                                <fmt:formatDate value="${student.crecorddate}" pattern="yyyy-MM-dd"/>
                            </td>
                        </tr>

                        <tr>

                            <td width="25%"  class="alignLeft" height="50">Name</td>
                            <td width="25%" class="tablerows" style="text-transform:uppercase">
                                <c:out default="" value="${student.name}" />
                            </td>
                            <td width="25%"  class="alignLeft" height="50" >Gender
                            </td>
                            <td width="25%" class="tablerows" >
                                <c:out default="" value="${student.gender}" />
                            </td>
                        </tr>
                        <tr>
                            <td width="25%"  class="alignLeft" height="50">Date Of Birth</td>
                            <td width="25%" class="tablerows" >
                                <%-- <c:out value="${student.dateofbirth}" /> --%>
                                <fmt:formatDate value="${student.dateofbirth}" pattern="yyyy-MM-dd"/>
                                
                            </td>
                            <td width="25%"  class="alignLeft" height="50" >Age
                            </td>
                            <td width="25%"  class="tablerows">
                                <c:out default="" value="${student.age}" />
                            </td>
                        </tr>
                       
                        <tr>
                            <td width="25%"  class="alignLeft" height="50" >Studying in class</td>
                            <td  width="25%"  class="tablerows">
                                <c:out default="" value="${student.classstudying}" />
                            </td>
                            <td width="25%"  class="alignLeft" height="50">Admitted in class
                            </td>
                            <td width="25%" class="tablerows">
                                <c:out default="" value="${student.classadmittedin}" />
                            </td>
                        </tr>
                        


                        <tr>
                            <td  width="25%"  class="alignLeft" height="50">
                            </td>
                            <td width="25%" class="tablerows" >

                            </td>
                            <td  width="25%"  class="alignLeft" height="50">
                            </td>
                            <td width="25%" class="tablerows">

                            </td>
                        </tr>
                    </table>

                   
                </div>






            </div>
            <div id="accordion1" style="width: 100%;height: 100%">

                <h3><a href="#">Parent's Details</a></h3>
                <div>

                    <table  border="0px" width="100%"  id="table1" align="center">

                        <tr>

                            <td width="25%"  class="alignLeft" height="50">Father's Name</td>
                            <td width="25%" class="tablerows" style="text-transform:uppercase">
                                <c:out default="" value="${parents.fathersname}" />
                            </td>
                            <td width="25%"  class="alignLeft" height="50" >Mother's Name
                            </td>
                            <td width="25%" class="tablerows" style="text-transform:uppercase">
                                <c:out default="" value="${parents.mothersname}" />
                            </td>
                        </tr>
                        <tr>
                            <td width="25%"  class="alignLeft" height="50">Guardian's name & address</td>
                            <td width="25%" class="tablerows" >
                                 <c:out value="${student.guardiandetails}" />
                                 
                                 <td width="25%" class="alignLeft" height="50">Email</td>
						<td width="25%" class="tablerows"><c:out default=""
								value="${parents.email}" /></td>
                        </tr>
                        
                        <tr>
                            <td width="25%"  class="alignLeft" height="50" >Contact Number</td>
                            <td  width="25%"  class="tablerows">
                                <c:out default="" value="${parents.contactnumber}" />
                            </td>
                            <td width="25%"  class="alignLeft" height="50">Co-Contact Number
                            </td>
                            <td width="25%" class="tablerows">
                                <c:out default="" value="${parents.cocontactnumber}" />
                            </td>
                        </tr>
										
					<tr>
                            <td width="25%"   class="alignLeft" height="50" >Parmanent Address</td>
                            <td width="25%" class="tablerows" >
                                <c:out default="" value="${parents.addresspermanent}" />
                            </td>
                           
                            
                            <td  width="25%"  class="alignLeft" height="50">Temporary Address
                            </td>
                            <td width="25%" class="tablerows">
                                <c:out default="" value="${parents.addresstemporary}" />
                            </td>
                        
                        </tr>

                        <tr>
                            <td  width="25%"  class="alignLeft" height="50">
                            </td>
                            <td width="25%" class="tablerows" >

                            </td>
                            <td  width="25%"  class="alignLeft" height="50">
                            </td>
                            <td width="25%" class="tablerows">

                            </td>
                        </tr>
                    </table>

                    

                </div>






            </div>
              <div id="accordion2" style="width: 100%;height: 100%">

                <h3><a href="#">Fees Details</a></h3>
                
                <div>
					<div align="center">
					<h class="dataTextFees">Total fees : Rs. ${totalfees}</h>&nbsp;&nbsp;&nbsp;
                <h class="dataTextFees">Total fees paid : Rs. ${sumoffees}</h>&nbsp;&nbsp;&nbsp;
                <h class="dataTextFees">Due Amount : </h>
                <h class="dataTextDueFees"> Rs. ${dueamount}</h>
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
				<h class="dataTextFees">Total fees : Rs. ${totalfees}</h>&nbsp;&nbsp;&nbsp;
                <h class="dataTextFees">Total fees paid : Rs. ${sumoffees}</h>&nbsp;&nbsp;&nbsp;
                <h class="dataTextFees">Due Amount : </h>
                <h class="dataTextDueFees"> Rs. ${dueamount}</h>
                </div>
                    <table   width="100%"  border="0" style="border-color:#4b6a84;"  id="myTable">

                    <thead>
                        <tr  >
                            
                            <th title="click to sort" class="headerText"></th>
                            <th title="click to sort" class="headerText"></th>
                            <th title="click to sort" class="headerText"></th>
                            <th title="click to sort" class="headerText">Fees Category</th>
                            <th title="click to sort" class="headerText">Fees Amount&nbsp;</th>
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
                form1.action="Controller?process=StudentProcess&action=viewAllSuperAdmin";
                form1.submit();
            }
        </script>
    </body>
</html>

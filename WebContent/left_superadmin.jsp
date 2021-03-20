<%-- 
Document   : left
Created on : Jan 4, 2012, 3:41:11 PM
Author     : Musaib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Left</title>
        <link rel="stylesheet" href="css/datePicker/jquery-ui-1.8.18.custom.css">
        <script type="text/javascript" src="js/datePicker/jquery-1.7.1.js"></script>
        <script src="js/datePicker/ui/jquery.ui.widget.js"></script>
        <script src="js/datePicker/ui/jquery.ui.accordion.js"></script>
        <style>

            .noti_Container {
                position:relative;
                border:1px solid blue; /* This is just to show you where the container ends */
                width:16px;
                height:16px;
                cursor: pointer;
            }
            .noti_bubble {
                position:absolute;
                font-size: 12px;
                top: 450px;
                left: 0px;
                padding-right:2px;
                background-color:transparent;
                color:black;
                font-weight:bold;

                z-index: 2;
                width: 100%;
                height: 16px;
            }
        </style>
        <style>
            body{
                margin:0;
                padding:0;
                background:#FFFFFF;
                font:80% Tahoma;
                color:#555;
                line-height:150%;
                text-align:left;

            }
            /*body{
                margin:0;
                padding:0;
                background:#E6EEF4;
                font:80% Tahoma;
                color:#555;
                line-height:150%;
                text-align:left;

            }*/
            a{
                text-decoration:none;

                color:#cfe0ea;;
            }
            a:hover{
                text-decoration:underline;
                color:#EB6000;
            }
            h1{
                font-size:140%;
                margin:0 20px;
                line-height:80px;	
            }

            #content{margin:0 0px;}
            p{	
                margin:0 auto;
                width:700px;
                padding:1em 0;
            }


            .noti_Container {
                position:relative;
                border:1px solid blue; /* This is just to show you where the container ends */
                width:16px;
                height:16px;
                cursor: pointer;
            }
            .headerTD{
                border-radius:1px;
                background-color:#4b6a84;
                background-image: url("images/ui-bg_diagonals-small_50_466580_40x40.png");
                color: #FFFFFF;
                font-family: Tahoma;
                font-size: 13px;
                text-transform: uppercase;
                text-align: center;
                font-weight: bold;
                height: 22px;
            }


.ui-state-default, .ui-widget-content .ui-state-default, .ui-widget-header .ui-state-default { border: 1px solid #cccccc; background: #ffffff url(images/ui-bg_glass_100_f6f6f6_1x400.png) 50% 50% repeat-x; font-weight: bold; color: #1c94c4; }
.ui-widget-content {
    border: 1px solid #b6cfe2;
    background: #ffffff;
    color: #222222;
}

.sideaccordian{
		
		font-size: 12px;
		/* border: 0px; */
		border-radius: 5px;
		/* border-bottom:  1px solid #010d1c !important; */
}

        </style>


        <script>
            $(function() {


                $("#container").accordion({
                    collapsible: true,
                    active: false,
                    autoHeight: false,
                    navigation: true
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
    <body onload="StartClock()" onunload="KillClock()" >
        <%--  <div class="headerTD">Welcome <c:out default="" value="${userAuth}"/> </div> --%>

        <div id="container" style="width: 95%;">
        			<!-- border:none;border-bottom: 1px solid #010d1c !important; border-right: 1px solid #010d1c !important;-->
        	<h5 class="sideaccordian" ><a href="#" >Stock Management</a></h5>
            <div >
                <a target="mainFrame" href="Controller?process=MessItemsMoveProcess&action=issueItems" style="font-size: 12px;">Issue</a><br/>
                <a target="mainFrame" href="Controller?process=MessItemsProcess&action=purchaseItems" style="font-size: 12px;">Receive</a><br/>
                <a target="mainFrame" href="Controller?process=MessItemsProcess&action=viewItems" style="font-size: 12px;">View/Add Items</a><br/>
            </div>
            
            <h5 class="sideaccordian" ><a href="#" >Stock Reports</a></h5>
            <div >
                <a target="mainFrame" href="Controller?process=MessItemsProcess&action=currentStock" style="font-size: 12px;">Stock Quantity Report</a><br/>
                <a target="mainFrame" href="Controller?process=MessItemsProcess&action=batchStock" style="font-size: 12px;">Stock Price Report</a><br/>
                <a target="mainFrame" href="Controller?process=MessItemsProcess&action=issuanceStock" style="font-size: 12px;">Stock Issuance Report</a><br/>
                <a target="mainFrame" href="Controller?process=MessItemsProcess&action=receiveStock" style="font-size: 12px;">Stock Received Report</a><br/>
            </div>
            
              <h5 class="sideaccordian"><a href="#" >Suppliers</a></h5>
            <div >
            	<a target="mainFrame" href="Controller?process=MessSuppliersProcess&action=paymentSuppliers" style="font-size: 12px;">Make Payment</a><br/>
            	<a target="mainFrame" href="Controller?process=MessSuppliersProcess&action=balanceSuppliers" style="font-size: 12px;">Balance Report</a><br/>
            	<a target="mainFrame" href="Controller?process=MessSuppliersProcess&action=supplierPaymentReport" style="font-size: 12px;">Payment & Receipt Report</a><br/>
                <a target="mainFrame" href="Controller?process=MessSuppliersProcess&action=viewSuppliers" style="font-size: 12px;">View/Add Suppliers</a><br/>
            </div>
            
             <h5 class="sideaccordian"><a href="#" >Accounts</a></h5>
            <div >
                <a target="mainFrame" href="Controller?process=AccountProcess&action=createAccount" style="font-size: 12px;">Ledger Account</a><br/>
                <a target="mainFrame" href="Controller?process=AccountProcess&action=createVoucher" style="font-size: 12px;">Create Voucher</a><br/>
                <a target="mainFrame" href="Controller?process=AccountProcess&action=viewVoucherReceipt" style="font-size: 12px;">View/Cancel Voucher</a><br/>
                <a target="mainFrame" href="Controller?process=AccountProcess&action=viewCancelledVouchers" style="font-size: 12px;">Cancelled Vouchers</a><br/>
                <a target="mainFrame" href="Controller?process=AccountProcess&action=generalLedgerReport" style="font-size: 12px;">General Ledger Report</a><br/>
                <a target="mainFrame" href="incomestatement.jsp" style="font-size: 12px;">Income Statement</a><br/>
                <a target="mainFrame" href="trialbalance.jsp" style="font-size: 12px;">Trial Balance</a><br/>
                <a target="mainFrame" href="Controller?process=AccountProcess&action=balanceSheet" style="font-size: 12px;">Balance Sheet</a><br/>
            </div>
            
            <h5 class="sideaccordian"><a href="#">Students</a></h5>
            <div>
                <a target="mainFrame" href="Controller?process=StudentProcess&action=viewAll" style="font-size: 12px;">View All</a><br/>
                <a target="mainFrame" href="Controller?process=StudentProcess&action=addNew" style="font-size: 12px;">Add New</a><br/>
            </div>
            
           <!--  <h5 class="sideaccordian"><a href="#">Attendance</a></h5>
            <div>
                <a target="mainFrame" href="Controller?process=AttendanceProcess&action=viewAttendance" style="font-size: 12px;">View Attendance</a><br/>
                <a target="mainFrame" href="Controller?process=AttendanceProcess&action=markAttendance" style="font-size: 12px;">Mark Attendance</a><br/>
                <a target="mainFrame" href="Controller?process=AttendanceProcess&action=attendanceExport" style="font-size: 12px;">Export Attendance</a><br/>
            </div> -->
            
           <!--  <h5 class="sideaccordian"><a href="#">Staff</a></h5>
            <div>
                <a target="mainFrame" href="Controller?process=EmployeeProcess&action=viewAllEmployee" style="font-size: 12px;">View All</a><br/>
                <a target="mainFrame" href="Controller?process=EmployeeProcess&action=addEmployeePage" style="font-size: 12px;">Add Staff</a><br/>
				<a target="mainFrame" href="Controller?process=AttendanceProcess&action=viewAttendanceStaff" style="font-size: 12px;">View Attendance</a><br/>
				<a target="mainFrame" href="Controller?process=AttendanceProcess&action=attendanceMarkStaff" style="font-size: 12px;">Mark Attendance</a><br/>
				<a target="mainFrame" href="Controller?process=AttendanceProcess&action=attendanceExportViewStaff" style="font-size: 12px;">Export Attendance</a><br/>
            </div> -->

            <h5 class="sideaccordian"><a href="#">Fees</a></h5>
            
            <div>
				<a target="mainFrame" href="Controller?process=FeesProcess&action=feesCollect" style="font-size: 12px;">Fees Collection</a><br/>                
				<a target="mainFrame" href="Controller?process=FeesProcess&action=feesStructure" style="font-size: 12px;">Fees Structure</a><br/>                
				<a target="mainFrame" href="feesCollectionDetails.jsp" style="font-size: 12px;">Fees Collection Details</a><br/>
				<a target="mainFrame" href="feescancelledreceipts.jsp" style="font-size: 12px;">Cancelled Fees Receipts</a><br/>
				<a target="mainFrame" href="Controller?process=FeesProcess&action=feesReport" style="font-size: 12px;">Fees Report</a><br/>
            </div> 
            
           <!--  <h5 style="font-size: 12px"><a href="#">Exams</a></h5>
            
            <div>
                <a target="mainFrame" href="Controller?process=MarksDetailsProcess&action=marksEntry" style="font-size: 12px;">Enter Marks</a><br/>
                <a target="mainFrame" href="Controller?process=MarksDetailsProcess&action=getSubjectsExams" style="font-size: 12px;">View Marks</a><br/>
            	<a target="mainFrame" href="Controller?process=MarksDetailsProcess&action=progressReport" style="font-size: 12px;">Generate Report</a><br/>
            	<a target="mainFrame" href="Controller?process=MarksDetailsProcess&action=getGraphicalReportData" style="font-size: 12px;">Graphical Report</a><br/>
            </div>  -->
            
           <!--  <h5 style="font-size: 12px"><a href="#" >Advance Search</a></h5>
            <div>
                <a target="mainFrame" href="Controller?process=StudentProcess&action=advanceSearchStudents" style="font-size: 12px;">Search</a><br/>


            </div> -->

            <!-- <h5 style="font-size: 12px"><a href="#" >Extras</a></h5>
            <div >
                <a target="mainFrame" href="Backup&Restore.jsp" style="font-size: 12px;">Backup</a><br/>
                <a target="mainFrame" href="changePassword.jsp" style="font-size: 12px;">Change Password</a><br/>
                <a target="mainFrame" href="uploadattendance.jsp" style="font-size: 12px;">Upload Attendance File</a><br/>
                <a target="mainFrame" href="Controller?process=StudentProcess&action=archiveViewAll" style="font-size: 12px;">Archive Students</a><br/>
            </div> -->
            
            
            <h5 class="sideaccordian"><a href="#" >Mess Cards</a></h5>
            <div >
                <a target="mainFrame" href="Controller?process=printids&action=generateIds" style="font-size: 12px;">Generate IDs</a><br/>
                <a target="mainFrame" href="Controller?process=printids&action=cardValidity" style="font-size: 12px;">Card Validity</a><br/>
            </div>
            
            <h5 class="sideaccordian"><a href="#" >Documents</a></h5>
            <div >
                <a target="mainFrame" href="Controller?process=DocumentsProcess&action=studentsDetailsReports" style="font-size: 12px;">Student Details</a><br/>
                <!-- <a target="mainFrame" href="Controller?process=DocumentsProcess&action=admissionAbstract" style="font-size: 12px;">Admission Abstract</a><br/>
                <a target="mainFrame" href="Controller?process=DocumentsProcess&action=studentsDetailsBonafide" style="font-size: 12px;">Bonafide Certificate</a><br/>
                <a target="mainFrame" href="Controller?process=DocumentsProcess&action=transferCertificate" style="font-size: 12px;">Transfer Certificate</a><br/>
                <a target="mainFrame" href="Controller?process=ExamDetailsProcess&action=generateHallTicket" style="font-size: 12px;">Hall Ticket</a><br/>
                <a target="mainFrame" href="Controller?process=PeriodProcess&action=generateTimeTable" style="font-size: 12px;">Class Time Table</a><br/>
                <a target="mainFrame" href="Controller?process=PeriodProcess&action=generateTeacherTimeTable" style="font-size: 12px;">Teacher Time Table</a><br/> -->
            </div> 
            
            <h5 class="sideaccordian"><a href="#" >Expenses</a></h5>
            <div>
                <a target="mainFrame" href="Controller?process=AdminProcess&action=viewAllExpenses" style="font-size: 12px;">Expenses</a><br/>


            </div>
            
            <h5 class="sideaccordian"><a href="#" >Send Notifications</a></h5>
            <div >
                <a target="mainFrame" href="Controller?process=SMSProcess&action=sendSMS" style="font-size: 12px;">SMS</a><br/>
                <a target="mainFrame" href="sendemail.jsp" style="font-size: 12px;">Email</a><br/>
            </div>
            
             <!-- <h5 style="font-size: 12px"><a href="#" >H.R.</a></h5>
            <div >
                <a target="mainFrame" href="Controller?process=HrProcess&action=advanceSalary" style="font-size: 12px;">Advance Salary</a><br/>
                <a target="mainFrame" href="Controller?process=HrProcess&action=salaryApproval" style="font-size: 12px;">Advance Salary Approval</a><br/>
                <a target="mainFrame" href="Controller?process=HrProcess&action=salaryIssue" style="font-size: 12px;">Advance Salary Status</a><br/>
                <a target="mainFrame" href="Controller?process=HrProcess&action=processSalary" style="font-size: 12px;">Process Salary</a><br/>
                <a target="mainFrame" href="Controller?process=HrProcess&action=issueStaffSalary" style="font-size: 12px;">Issue Staff Salary</a><br/>
            </div> -->
            
            <!-- <h5 style="font-size: 12px"><a href="#" >Leave Management</a></h5>
            <div >
                <a target="mainFrame" href="Controller?process=HrProcess&action=leaveApplication" style="font-size: 12px;">Leave Application</a><br/>
                <a target="mainFrame" href="Controller?process=HrProcess&action=leaveApprovals" style="font-size: 12px;">Leave Approvals</a><br/>
            </div> -->
            
          <!--   <h5 style="font-size: 12px"><a href="#" >Promotion</a></h5>
            <div>
                <a target="mainFrame" href="Controller?process=ClassProcess&action=promoteClass" style="font-size: 12px;">Promotions</a><br/>
				
            </div> -->
            
            </div>
                   
            
            <!-- END -->
           
       
        


        </body>

</html>

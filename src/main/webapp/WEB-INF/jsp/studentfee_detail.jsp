<!DOCTYPE html>
<html lang="en">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student Profile</title>
    <link rel="stylesheet" href="/school/css/bootstrap.min.css">
        <script src="/school/js/jquery.min.js"></script>
        <script src="/school/js/bootstrap.min.js"></script>
        <script src="/school/js/popper.min.js"></script>

<style>
 .headerText {
                font-family: Tahoma, sans-serif;
            font-size: 14px;
            background-color: #4b6a84;
            color: #FFFFFF;
            font-weight: bold;
            text-align: center;
	     }
.dataText {
               font-family: Tahoma, sans-serif;
            color: #4b6a84;
            font-size: 14px;
            background-color: #E3EFFF;
            text-align: center;

            }
            
            .fees-label {
            font-size: 14px;
            font-weight: bold;
        }
            
 .card {
            margin-bottom: 1rem;
        }

        @media (max-width: 576px) {
            .fees-label {
                font-size: 12px;
            }

            .headerText,
            .dataText {
                font-size: 12px;
            }

            h4 {
                font-size: 1.25rem;
            }

            .card-body {
                padding: 0.5rem;
            }
        }
</style>
</head>
<%
//allow access only if session exists
String user = null;
if(session.getAttribute("userAuth") == null){
	response.sendRedirect("/school/UserProcess/sessionTimeOut");
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
<body>
 <div class="container-fluid p-3">
        <!-- Fees Structure -->
        <h4 class="text-center mb-3">Fees Structure</h4>
    <div class="card">
        <div class="card-body">
            <div class="row text-center">
                <div class="col-6 mb-2">
                        <label class="fees-label text-warning">Academic Year: ${currentAcademicYear}</label>
                    </div>
                    <div class="col-6 mb-2">
                        <label class="fees-label text-primary">Total Fees: Rs. ${totalfees}</label>
                    </div>
                    <div class="col-6 mb-2">
                        <label class="fees-label text-success">Fees Paid: Rs. ${sumoffees}</label>
                    </div>
                    <div class="col-6 mb-2">
                        <label class="fees-label text-danger">Due Amount: Rs. ${dueamount}</label>
                    </div>
            </div>
            <div class="table-responsive">
            
            		<table class="table table-bordered" id="myTable">
                        <thead class="thead-light">
                            <tr>
                                <th class="headerText">Fees Category</th>
                                <th class="headerText">Fees Amount</th>
                                <th class="headerText">Fees Paid</th>
                                <th class="headerText">Due Amount</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${feesstructure}" var="feesstructure">
                                <tr>
                                    <td class="dataText">
                                        <c:out value="${feesstructure.feescategory.feescategoryname}" />
                                    </td>
                                    <td class="dataText">
                                        <c:out value="${feesstructure.feesamount}" />
                                    </td>
                                    <td class="dataText">
                                        <c:out value="${feesstructure.feespaid}" />
                                    </td>
                                    <td class="dataText">
                                        <c:out value="${feesstructure.feesamount - feesstructure.feespaid - feesstructure.concession - feesstructure.waiveoff}" />
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
            </div>
        </div>
    </div>
    <!--End Fees Structure--> 

    <!--Fees Detail-->
    
    <h4 class="text-center mb-3">Fees Detail</h4>
        <div class="card">
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-bordered" id="myTable2">
                        <thead class="thead-light">
                            <tr>
                                <th class="headerText">Date of Fees</th>
                                <th class="headerText">Receipt Number</th>
                                <th class="headerText">Total Amount</th>
                                <th class="headerText">View Details</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${receiptinfo}" var="receiptinfo">
                                <tr>
                                    <td class="dataText"><c:out value="${receiptinfo.date}" /></td>
                                    <td class="dataText"><c:out value="${receiptinfo.branchreceiptnumber}" /></td>
                                    <td class="dataText"><c:out value="${receiptinfo.totalamount}" /></td>
                                    <td class="dataText">
                                        <a class="btn btn-primary btn-sm" target="_blank" href="/school/FeesCollection/ViewDetails?id=<c:out value='${receiptinfo.receiptnumber}' />&sid=<c:out value='${student.sid}' />">View</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <!-- Bus Fees -->
    
     <h4 class="text-center mb-3">Bus Fees Structure</h4>
    <div class="card">
        <div class="card-body">
            <div class="row text-center">
                <div class="col-6 mb-2">
                        <label class="fees-label text-warning">Academic Year: ${currentAcademicYear}</label>
                    </div>
                    <div class="col-6 mb-2">
                        <label class="fees-label text-primary">Total Fees: Rs. ${othertotalfees}</label>
                    </div>
                    <div class="col-6 mb-2">
                        <label class="fees-label text-success">Fees Paid: Rs. ${othersumoffees}</label>
                    </div>
                    <div class="col-6 mb-2">
                        <label class="fees-label text-danger">Due Amount: Rs. ${otherdueamount}</label>
                    </div>
            </div>
            <div class="table-responsive">
            
            		<table class="table table-bordered" id="myTable">
                        <thead class="thead-light">
                            <tr>
                                <th class="headerText">Fees Category</th>
                                <!-- <th class="headerText">Fees Amount</th>
                                <th class="headerText">Fees Paid</th>
                                <th class="headerText">Due Amount</th> -->
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${otherfeesstructure}" var="feesstructure">
                                <tr>
                                    <td class="dataText">
                                        <c:out value="${feesstructure.otherfeescategory.feescategoryname}" />
                                    </td>
                                    <%-- <td class="dataText">
                                        <c:out value="${feesstructure.amount}" />
                                    </td>
                                    <td class="dataText">
                                        <c:out value="${feesstructure.feespaid}" />
                                    </td>
                                    <td class="dataText">
                                        <c:out value="${feesstructure.feesamount - feesstructure.feespaid - feesstructure.concession - feesstructure.waiveoff}" />
                                    </td> --%>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
            </div>
        </div>
    </div>
    
    <!--Fees Detail-->
    
     <h4 class="text-center mb-3">Fees Detail</h4>
        <div class="card">
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-bordered" id="myTable2">
                        <thead class="thead-light">
                            <tr>
                                <th class="headerText">Date of Fees</th>
                                <th class="headerText">Receipt Number</th>
                                <th class="headerText">Total Amount</th>
                                <th class="headerText">View Details</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${otherreceiptinfo}" var="receiptinfo">
                                <tr>
                                    <td class="dataText"><c:out value="${receiptinfo.date}" /></td>
                                    <td class="dataText"><c:out value="${receiptinfo.branchreceiptnumber}" /></td>
                                    <td class="dataText"><c:out value="${receiptinfo.totalamount}" /></td>
                                    <td class="dataText">
                                        <a class="btn btn-primary btn-sm" target="_blank" href="/school/FeesCollection/viewOtherFeesDetails?id=<c:out value='${receiptinfo.receiptnumber}' />&sid=<c:out value='${student.sid}' />">View</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
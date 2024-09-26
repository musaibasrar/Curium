<!DOCTYPE html>
<html lang="en">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student Profile</title>
    <link rel="stylesheet" href="/cambridge/css/bootstrap.min.css">
        <script src="/cambridge/js/jquery.min.js"></script>
        <script src="/cambridge/js/bootstrap.min.js"></script>
        <script src="/cambridge/js/popper.min.js"></script>

<style>
 .headerText {
                border-radius:3px;
                font-family: Tahoma;
                font-size: 16px;
                background-color: #4b6a84;
                color: #FFFFFF;
                font-weight: bold;
                width: auto ;
                height: 18px;
                vertical-align: middle;
                text-align: center;
	     }
.dataText {
                border-radius:3px;
                font-family: Tahoma;
                color: #4b6a84;
                font-size: 16px;
                letter-spacing: normal;
                text-align: center;
                background-color: #E3EFFF;

            }
            
            .fees-label {
            font-size: 16px;
        }
            
#myTable tr:nth-child(even) td {
  background-color: #e5e5e5; 
  border : 2px solid;
}

#myTable tr:nth-child(odd) td {
  background-color: #ffffff;
  border : 2px solid;
}

#myTable2 tr:nth-child(even) td {
  background-color: #e5e5e5; 
  border : 2px solid;
}

#myTable2 tr:nth-child(odd) td {
  background-color: #ffffff;
  border : 2px solid;
}

.card-body {
    -ms-flex: 1 1 auto;
    flex: 1 1 auto;
    min-height: 1px;
    padding: 0rem;
}
</style>
</head>
<body>
 <div class="container" style="margin-left: 0px;margin-right: 0px;">       
    <!--Fees Structure-->
    <h4 class="text-left">Fees Structure<%-- &nbsp;UID: <c:out value="${student.studentexternalid}" /> --%></h4>
    <div class="card mb-2" style="width: 360px;padding:0.25rem;">
        <div class="card-body">
            <div class="row text-center">
                <div class="col-lg-3 col-md-6 col-sm-12 mb-2">
                    <label class="fees-label" style="color: #ff914d">Academic Year: ${currentAcademicYear}</label>
                </div>
                <div class="col-lg-3 col-md-6 col-sm-12 mb-2">
                    <label class="fees-label" style="color: #0cc0df">Total fees: Rs. ${totalfees}</label>
                </div>
                <div class="col-lg-3 col-md-6 col-sm-12 mb-2">
                    <label class="fees-label" style="color: #00BF63">Total fees paid: Rs. ${sumoffees}</label>
                </div>
                <div class="col-lg-3 col-md-6 col-sm-12 mb-2">
                    <label class="fees-label" style="color: #ff3131">Due Amount: Rs. ${dueamount}</label>
                </div>
            </div>
            <div class="row">
                <div class="col-12">
                    <table class="table table-bordered" id="myTable">
                        <thead class="thead-light">
                            <tr>
                                <th class="headerText">Fees Category</th>
                                <!-- <th class="headerText">Fees Amount</th>
                                <th class="headerText">Total Installments</th> -->
                                <th class="headerText">Fees Amount</th>
                                <th class="headerText">Fees Paid</th>
                                <th class="headerText">Due Amount</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${feesstructure}" var="feesstructure">  
                                <tr>
                                    <td class="dataText"><c:out value="${feesstructure.feescategory.feescategoryname}"/></td>
                                   <%--  <td class="dataText"><c:out value="${feesstructure.feescategory.amount}"/></td>
                                    <td class="dataText"><c:out value="${feesstructure.totalinstallment}"/></td> --%>
                                    <td class="dataText"><c:out value="${feesstructure.feesamount}"/></td>
                                    <td class="dataText"><c:out value="${feesstructure.feespaid}"/></td>
                                    <td class="dataText"><c:out value="${feesstructure.feesamount - feesstructure.feespaid - feesstructure.concession - feesstructure.waiveoff}"/></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <!--End Fees Structure--> 

    <!--Fees Detail-->
    <h4 class="text-left">Fees Detail</h4>
    <div class="card mb-2" style="width: 360px;padding:0.25rem;">
        <div class="card-body">
            
            <div class="row">
                <div class="col-12">
                    <table class="table table-bordered" id="myTable2">
                        <thead class="thead-light">
                            <tr>
                                <th class="headerText">Date of fees</th>
                                <th class="headerText">Receipt Number</th>
                                <th class="headerText">Total Amount</th>
                                <th class="headerText">View Details</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${receiptinfo}" var="receiptinfo">
                                <tr>
                                    <td class="dataText"><c:out value="${receiptinfo.date}"/></td>
                                    <td class="dataText"><c:out value="${receiptinfo.branchreceiptnumber}"/></td>
                                    <td class="dataText"><c:out value="${receiptinfo.totalamount}"/></td>
                                    <td class="dataText"><a class="dataTextInActive" target="_blank" href="/cambridge/FeesCollection/ViewDetails?id=<c:out value='${receiptinfo.receiptnumber}'/>&sid=<c:out value='${student.sid}'/>">View Details</a></td>
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
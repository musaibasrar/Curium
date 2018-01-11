<%-- 
    Document   : ${name}
    Created on : ${date}, ${time}
    Author     : ${user}
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <style type="text/css" >
            <!--
            .header {
                font-family: Arial, Helvetica, sans-serif;
                font-size: 12px;
                background-color: #4b6a84;
            }
            .table {
                background-color: #3399CC;
                text-align: center;
                width: auto;


            }
            .headerText {
                border-radius:3px;
                width: 10px;
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
            .dataText {
                border-radius:3px;
                font-family: Tahoma;
                color: #4b6a84;
                font-size: 13px;
                letter-spacing: normal;
                text-align: center;
                background-color: #E3EFFF;

            }
            .dataTextInActive {
                border-radius:3px;
                font-family: Tahoma;
                color: #4b6a84;
                font-size: 12px;
                font-weight: bold;
                letter-spacing: normal;
                text-align: center;
                background-color: #E3EFFF;
                text-decoration:none;
            }
            .dataTextActive {
                border-radius:3px;
                font-family: Tahoma;
                color: #4b6a84;
                font-size: 12px;
                font-weight: bold;
                letter-spacing: normal;
                text-align: center;
                background-color: #E3EFFF;
                text-decoration: underline;
            }
            .dataTextHidden {
                font-family: Tahoma;
                color: #4b6a84;
                font-size: 13px;
                letter-spacing: normal;
                text-align: center;
                background-color: #E3EFFF;

            }
            .headerTD{
                border-radius:6px;


                color: #000000;
                font-family: Tahoma;
                font-size: 26px;
                text-transform: uppercase;
                text-align: right;
                font-weight: bold;
                height: 22px;
            }
            .headerTD1{
                border-radius:6px;


                color: #000000;
                font-family: Tahoma;
                font-size: 11px;
                text-transform: uppercase;
                text-align: right;
                font-weight: bold;
                height: 22px;
            }
            .headerTD2{
                border-radius:6px;
                font-style:  italic;

                color: #000000;
                font-family: Tahoma;
                font-size: 8px;
                text-transform: uppercase;
                text-align: right;
                font-weight: bold;
                height: 22px;
            }
            .alignLeft {
                font-family: Tahoma;
                font-size: 11px;
                font-style: normal;
                text-transform: capitalize;
                color: #000000;
                text-align: left;
                vertical-align: middle;
                font-weight: bold;
            }
            -->
        </style>
        <script type="text/javascript" src="js/datePicker/jquery-1.7.1.js"></script>
        <script type="text/javascript" src="js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
        <script src="js/print/jquery.printPage.js" type="text/javascript"></script>
        <title>JSP Page</title>
        <script type="text/javascript">
             $(function() {

                 $("#print").printPage();
             });
        </script>
    </head>
    <body>
        <form action="Controller?process=DispenseProcess&action=add" method="post" onsubmit="return validateMedicine()">
            <table  width="100%">
                <thead>
                    <tr  >

                        <td colspan="4" class="headerText">Fees Details</td>


                    </tr>

                </thead>
                <tbody>
                    <tr>
                        <td style="width: 35%">Student Name: <c:out value="${student.name}" /></td>
                        <td style="width: 20%">Admission No:  <c:out value="${student.admissionnumber}" /></td>
                        <td style="width: 20%">Date:  <c:out value="${feesdetails.dateoffees}" /></td>
                        <td style="width: 20%">Receipt No:  <c:out value="${feesdetails.feesdetailsid}" /></td>

                    </tr>


                </tbody>
            </table>
            <table width="100%">
               <thead>
                    <tr>

                        <td  class="headerText">Fees Details</td>


                    </tr>

                </thead>

            </table>
            <TABLE id="dataTable" width="100%" border="1" >
                <thead>
                    <tr >

                        <td class="headerText">Fees Category</td>
                        <td class="headerText">Fees Amount</td>
                        <td class="headerText">For The Month</td>
                        <!-- <td class="headerText">Amount</td> -->

                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${feescollection}" var="feescollection">
                        <tr>
                            <td align="center"><c:out value="${feescollection.feescategory}" /></td>
                            <td align="center"><c:out value="${feescollection.feesamount}" /></td>
                            <td align="center"><c:out value="${feescollection.formonth}" /></td>
                            <%-- <td align="center"><c:out value="${feescollection.amount}" /></td> --%>
                        </tr>
                    </c:forEach>

                </tbody>
                <tfoot>
                    <%-- <c:forEach end="1" items="${dispense.dispensedmedicinebills}" var="dispensedmedicinebill"> --%>
                        <tr>

                            <td colspan="3" align="right">Total</td>
                            <td align="center"><c:out value="${feesdetails.amountpercat}" /></td>
                        </tr>
                        <tr>

                            <td colspan="3" align="right">Balance</td>
                            <td align="center"><c:out value="${feesdetails.balamount}" /></td>
                        </tr>
                        <tr>

                            <td colspan="3" align="right">Miscellaneous</td>
                            <td align="center"><c:out value="${feesdetails.miscamount}" /></td>
                        </tr>
                        <tr>

                            <td colspan="3" align="right">Grand Total</td>
                            <td align="center"><c:out value="${feesdetails.grandtotal}" /></td>
                        </tr>
                    <%-- </c:forEach> --%>
                    <tr>


                        <td align="center"><a id="print" href="Controller?process=FeesCollection&action=printReceipt&id=<c:out value="${feesdetails.feesdetailsid}"/>&sid=<c:out value="${student.sid}"/>">Print</a></td>
                    </tr>
                </tfoot>
            </TABLE>

        </form>
    </body>
</html>

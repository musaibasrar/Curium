<%-- 
    Document   : ${name}
    Created on : ${date}, ${time}
    Author     : ${user}
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE HTML>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <style type="text/css">
            .header {
                font-family: Arial, Helvetica, sans-serif;
                font-size: 12px;
                background-color: #4b6a84;
            }
            .table {
                background-color: #FFFFFF;
                text-align: center;
                width: 100%;
                table-layout: fixed;
            }
            .headerText {
                border-radius: 3px;
                width: 100%;
                font-family: Tahoma;
                font-size: 12px;
                background-color: #4b6a84;
                color: #FFFFFF;
                font-weight: normal;
                height: 22px;
                vertical-align: middle;
                text-align: center;
                background-image: url("images/ui-bg_diagonals-small_50_466580_40x40.png");
            }
            .dataText {
                border-radius: 3px;
                font-family: Tahoma;
                color: #4b6a84;
                font-size: 13px;
                letter-spacing: normal;
                text-align: center;
                background-color: #E3EFFF;
            }
            .dataTextInActive, .dataTextActive {
                border-radius: 3px;
                font-family: Tahoma;
                color: #4b6a84;
                font-size: 12px;
                font-weight: bold;
                letter-spacing: normal;
                text-align: center;
                background-color: #E3EFFF;
                text-decoration: none;
            }
            .dataTextActive {
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
            .headerTD {
                border-radius: 6px;
                color: #000000;
                font-family: Tahoma;
                font-size: 26px;
                text-transform: uppercase;
                text-align: right;
                font-weight: bold;
                height: 22px;
            }
            .alignLeft {
                font-family: Tahoma;
                font-size: 11px;
                text-transform: capitalize;
                color: #000000;
                text-align: left;
                vertical-align: middle;
                font-weight: bold;
            }
            .totalAmount {
                color: #FF0000;
                font-weight: bold;
            }
            @media screen and (max-width: 600px) {
                .table, .header, .headerText, .dataText {
                    width: 100%;
                    font-size: 16px;
                }
                .headerTD {
                    font-size: 20px;
                }
            }
        </style>
        <script type="text/javascript" src="/school/js/datePicker/jquery-1.7.1.js"></script>
        <script type="text/javascript" src="/school/js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
        <script src="/school/js/print/jquery.printPage.js" type="text/javascript"></script>
        <title>Print Receipt</title>
        <script type="text/javascript">
             $(function() {
                 $("#print").printPage();
             });
             
             function printReceipt(){
                 document.getElementById("print").click();
             }
        </script>
    </head>
    <%
//allow access only if session exists
String user = null;
if(session.getAttribute("userAuth") == null){
    response.sendRedirect("/school/UserProcess/sessionTimeOut");
} else {
    user = (String) session.getAttribute("userAuth");
}
String userName = null;
String sessionID = null;
Cookie[] cookies = request.getCookies();
if (cookies != null) {
    for (Cookie cookie : cookies) {
        if (cookie.getName().equals("user")) userName = cookie.getValue();
        if (cookie.getName().equals("JSESSIONID")) sessionID = cookie.getValue();
    }
}
%>
    <body onload="printReceipt()">
        <form id="form">
        <input type="hidden" value="${duplicate}" id="duplicate" name="duplicate">
            <table class="table">
                <thead>
                    <tr>
                        <td colspan="4" class="headerText">Fees Receipt</td>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Student Name: <c:out value="${student.name}" /></td>
                        <td>Admission No:  <c:out value="${student.admissionnumber}" /></td>
                        <td>Date:  <c:out value="${recieptdate}" /></td>
                        <td>Receipt No:  <c:out value="${recieptinfo.branchreceiptnumber}" /></td>
                    </tr>
                </tbody>
            </table>
            <table class="table">
                <thead>
                    <tr>
                        <td class="headerText">Particulars</td>
                    </tr>
                </thead>
            </table>
            <table class="table" border="1">
                <thead>
                    <tr>
                        <td class="headerText">Fees Category</td>
                        <td class="headerText">Fees Amount</td>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${feescatmap}" var="feescatmap">
                        <tr>
                            <td><c:out value="${feescatmap.key}" /></td>
                            <td><c:out value="${feescatmap.value}" /></td>
                        </tr>
                    </c:forEach>
                </tbody>
                <tfoot>
                    <tr>
                        <td>Total Amount</td>
                        <td class="totalAmount"><c:out value="${recieptinfo.totalamount}" /></td>
                    </tr>
                    <tr>
                        <td colspan="3"><a id="print" href="/school/FeesCollection/printFeesReceipt?id=<c:out value="${recieptinfo.receiptnumber}" />&sid=<c:out value="${student.sid}"/>">Print</a></td>
                    </tr>
                </tfoot>
            </table>
        </form>
    </body>
</html>
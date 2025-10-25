<%-- 
    Document   : Print Student details fees structure Report
    Created on : JAN 8 2021, 2:01 PM
    Author     : Musaib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html >
<head>
<title>Students Details Fees Structure Report</title>
<style type="text/css">
<!--
.headerText {
	width: 10px;
	font-family: Tahoma;
	font-size: 12px;
	color: black;
	font-weight: normal;
	width: auto;
	height: 22px;
	vertical-align: middle;
	text-align: center;
	border: 1px solid #000000;
}

.headerTextLeft {
	width: 10px;
	font-family: Tahoma;
	font-size: 12px;
	color: #FFFFFF;
	font-weight: normal;
	width: auto;
	height: 22px;
	vertical-align: middle;
	text-align: left;
}

.dataTextBold {
	font-weight: bold;
	font-family: Tahoma;
	color: black;
	font-size: 12px;
	letter-spacing: normal;
	text-align: center;
}

.dataTextBoldLeft {
	font-weight: bold;
	font-family: Tahoma;
	color: black;
	font-size: 12px;
	letter-spacing: normal;
	text-align: left;
}

.dataTextBoldCenter {
	font-weight: bold;
	font-family: Tahoma;
	color: black;
	font-size: 22px;
	letter-spacing: normal;
	text-align: center;
}

.addressLine{
	font-weight: bold;
	font-family: ariel;
	color: black;
	font-size: 16px;
	letter-spacing: normal;
	text-align: center;
}

.addressLineTwo{
	font-weight: bold;
	font-family: ariel;
	color: black;
	font-size: 10px;
	letter-spacing: normal;
	text-align: center;
}

.dataText {
	font-family: Tahoma;
	color: black;
	font-size: 12px;
	letter-spacing: normal;
	text-align: center;
	border: 1px solid #000000;
}


-->
.datatable {
    font-family: arial, sans-serif;
    border-collapse: collapse;
    width: 100%;
}

.datatd, .datath {
    border: 1px solid #000000;
    text-align: left;
    padding: 8px;
}

.datatdright {
    border: 1px solid #000000;
    text-align: right;
    padding: 8px;
}

.datatdt{
    border: 0px solid #000000;
    text-align: left;
    padding: 8px;
}
.reportheaders{
	font-weight: bold;
	font-size: 12px;

}

</style>

<script>
    window.onload = function(){
        window.print();
    }
    
</script>

<!-- <style type="text/css">

        @media print {
            .fontsize { font-size: 15px ;
                        font-weight: bold;
                        font-family: 'Times New Roman';
                        
                        
            }
            .header,.hide { visibility: hidden }
            .bodymargin{
            	margin-top: 0px;
                margin-left: 0px ;
                margin-right: 0px;
            }
            
        }
        
        @page {
              size: auto;   /* auto is the current printer page size */
           	  margin: 0mm;  /* this affects the margin in the printer settings */ 
            
        }

        @media screen {
            .fontsize { font-size: 15px;
                        font-weight: bold;
                        font-family: 'Times New Roman'
            }
            .bodymargin{
                margin-left: 0px ;
                margin-right: 0px;
            }
        }
    </style> -->
    
    <style type="text/css">

	

</head>




        @media print {
            .fontsize { font-size: 1px ;
                        font-weight: bold;
                        font-family: 'Times New Roman';
                        
                        
            }
            .header,.hide { visibility: hidden }
            .bodymargin{
                margin-left: 0px ;
                margin-right: 0px;
            }
            
        }
        
        @page {
              
            margin-left:  1cm;
             margin-right: 1cm;
             margin-bottom: 1cm;
             margin-top: 1cm;
             size: auto;
        }

        @media screen {
            .fontsize { font-size: 1px;
                        font-weight: bold;
                        font-family: 'Times New Roman'
            }
            .bodymargin{
                margin-left: 0px ;
                margin-right: 0px;
            }
        }
    </style>




<body>
        <jsp:useBean id="now" class="java.util.Date" />
        <fmt:formatDate var="today" type="date" value="${now}" />
        <form  method="post" id="form1">
		<table width="100%" style="border-collapse: collapse;">
			<tr>
				<td align="center">
				<img src="/abc/images/abc.png" width="90" height="60"/>
				</td>
				<td class="dataTextBoldCenter" style="width: 100%">
				
				<label class="dataTextBoldCenter">&nbsp;&nbsp;&nbsp;${branchname}</label><br>
				<label class="addressLine" style="padding-left: 20px;">${branchaddress}</label><br>
				<label class="addressLine" style="padding-left: 30px;">Contact:&nbsp;${branchcontact}</label><br>
				<label class="addressLine" style="text-decoration: underline;">Fees Card</label><br>
				<%-- <label class="addressLineTwo">${transactionfromdateselected}&nbsp;&nbsp;${transactiontodateselected}&nbsp;&nbsp;${issuedtoselected}&nbsp;&nbsp;${purposeselected}&nbsp;&nbsp;${itemselected}&nbsp;&nbsp;
				</label> --%>
				</td>
			</tr>
		</table>

			<TABLE  width="100%" border="1" style="border-collapse:collapse;">
                <tr>

                    <td colspan="4">
                    
                    </td>

                </tr>
            </TABLE>

            <table width="100%">
                <tr>
                    <td  class="headerTD">Name: &nbsp;<c:out value="${student.name}" /></td>
                    <td  class="headerTD">Class: &nbsp;<c:out value="${student.classstudying}" /></td>
                    <td  class="headerTD">Academic Year: &nbsp;<c:out value="${academicPerYear}" /></td>
                    <td  class="headerTD">Date: &nbsp;<fmt:formatDate type="date" value="${now}" pattern="dd/MM/yyyy"/></td>
                </tr>
                
            </table>
            
            <TABLE  width="100%" border="1" style="page-break-inside: avoid;border-collapse:collapse;">
                <tr>

                    <td colspan="4" ></td>

                </tr>
            </TABLE>
            <div id="accordion" style="width: 100%;height: 100%">

                <div>
                
					<div align="center">
				<h class="dataTextFees">Academic Year : ${academicPerYear}</h>&nbsp;&nbsp;&nbsp;
				<%-- <h class="dataTextFees">Total fees : Rs.  ${totalfees}</h>&nbsp;&nbsp;&nbsp;
                <h class="dataTextFees">Total fees paid : Rs.  ${sumoffees}</h>&nbsp;&nbsp;&nbsp;
                <h class="dataTextFees">Due Amount : </h>
                <h class="dataTextDueFees"> Rs.  ${dueamount}</h> --%>
                </div>
                
                <TABLE  width="100%" border="1" style="page-break-inside: avoid;border-collapse:collapse;">
                <tr>

                    <td colspan="4" ></td>

                </tr>
            </TABLE>
                    <table class="datatable">

                    <thead>
                        <tr>
                        	<th class="datath" style="text-align: center;">Sl.No.</th>
                            <th class="datath" style="text-align: center;">Items</th>
                            <!-- <th class="datath" style="text-align: center;">Fees Amount&nbsp;</th> -->
                            <th class="datath" style="text-align: center;">No. of Items&nbsp;</th>
                            <!-- <th class="datath" style="text-align: center;">Total Fees Amount&nbsp;</th>
                            <th class="datath" style="text-align: center;">Fees Paid&nbsp;</th>
                            <th class="datath" style="text-align: center;">Fees Due&nbsp;</th> -->
                            <th class="datath" style="text-align: center;">Delivery Status&nbsp;</th>
                           <!--  <th class="datath" style="text-align: center;">Waive Off Amount&nbsp;</th> -->
                        </tr>
                    </thead>

                    <tbody>
                        <c:forEach items="${feesstructure}" var="feesstructure" varStatus="status">
                            <tr>
                            	<td class="datatd" style="text-align: right">${status.index+1}</td>
                                <td class="datatd" style="text-align: left">
                                <c:set var="feeCategories" value="${fn:split(feesstructure.otherfeescategory.feescategoryname, '--')}" />
                                ${feeCategories[0]}</td>
                                <%-- <td class="datatd" style="text-align: right">${feesstructure.feescategory.amount}</td> --%>
                                <td class="datatd" style="text-align: center;">${feesstructure.totalinstallment}</td>
                                <%--<td class="datatd" style="text-align: right">${feesstructure.feesamount}</td>
                                 <td class="datatd" style="text-align: right">${feesstructure.feespaid}</td> 
                                <td class="datatd" style="text-align: right">${feesstructure.feesamount-feesstructure.feespaid-feesstructure.concession-feesstructure.waiveoff}</td> --%>
                                <td class="datatd" style="text-align: right">${feesstructure.concessionnotes}</td>
                                <%-- <td class="datatd" style="text-align: right">${feesstructure.waiveoff}</td> --%>
                            </tr>
                        </c:forEach>
                    </tbody>
                    
                    <%-- <tfoot>
                    	<tr>
                    			 <!-- colspan="2" -->
                    			<td class="datatd" style="text-align: right;font-weight: bold;">Total</td>
                    			<td class="datatd" style="text-align: right;font-weight: bold;">${grandtotalfeesamount}</td>
                                <td class="datatd" style="text-align: right;font-weight: bold;">${totalfees}</td>
                                <td class="datatd" style="text-align: right;font-weight: bold;">${sumoffees}</td>
                                <td class="datatd" style="text-align: right;font-weight: bold;">${dueamount}</td>
                    	</tr>
                    </tfoot> --%>
                   
                </table>
                
                <TABLE width="100%" border="0">
			<tr>
				<td class="headerText">
					Total Fees: Rs. ${totalfees}
				</td>
				<td class="headerText">
					Total fees paid : Rs.  ${sumoffees}
				</td>
				<td class="headerText">
				 Total fees Due : Rs.  ${dueamount} 
				</td>
				
			</tr>
			
			</table>
                
                <TABLE id="dataTable" width="100%" border="0"
			style="page-break-after: always; border-collapse: collapse;">

			<tr>
			<td>
			<br>
			<br><br><br></td>
			</tr>
			<tr>
			<td></td>
			</tr>
			<tr>
			<td></td>
			</tr>
		
			<tr>
		<tr>
		<td></td>
		<td></td>
			<td align="left">Parent Signature</td>	
			<td align="center">Satff Signature</td>
		</tr>
		
			<tr>
                                      <td>
                        </td>

            </tr>
		</TABLE>
                                <%-- <td class="datatd" style="text-align: right;font-weight: bold;">${totalfees}</td> --%>
                                <td class="datatd" style="text-align: right;font-weight: bold;">${sumoffees}</td>
                                <td class="datatd" style="text-align: right;font-weight: bold;">${totalfeesconcession}</td>
                                <td class="datatd" style="text-align: right;font-weight: bold;">${dueamount}</td>
                    	</tr>
                    
                    </tfoot>
                   
                </table>
                </div>
            </div>
        </form>
    </body>
</html>

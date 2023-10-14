<%-- 
    Document   : ${name}
    Created on : ${date}, ${time}
    Author     : ${user}
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
<title>Payment Report</title>
<head>
<style type="text/css">
<!--
.headerText {
	width: 10px;
	font-family: Tahoma;
	font-size: 12px;
	color: #000000;
	font-weight: normal;
	width: auto;
	height: auto;
	vertical-align: middle;
	text-align: center;
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
	font-size: 16px;
	letter-spacing: normal;
	text-align: left;
}

.dataTextBoldRight {
	font-weight: bold;
	font-family: Tahoma;
	color: black;
	font-size: 12px;
	letter-spacing: normal;
	text-align: right;
}

.dataTextBoldCenter {
	font-weight: bold;
	font-family: Tahoma;
	color: black;
	font-size: 32px;
	letter-spacing: normal;
	text-align: center;
}

.addressLine{
	font-weight: normal;
	font-family: ariel;
	color: black;
	font-size: 18px;
	letter-spacing: normal;
	text-align: center;
}

.dataText {
	font-family: Tahoma;
	color: black;
	font-size: 12px;
	letter-spacing: normal;
	text-align: center;
}

.dataTextInActive {
	font-family: Tahoma;
	font-size: 12px;
	font-weight: bold;
	letter-spacing: normal;
}
-->
.headerTD {
	width: 10px;
	font-family: Tahoma;
	font-size: 18px;
	color: #000000;
	font-weight: normal;
	width: auto;
	height: 18px;
	vertical-align: middle;
	text-align: center;
}

.incomeexpense {
  font-family: Arial, Helvetica, sans-serif;
  border-collapse: collapse;
}

.incomeexpense td, #incomeexpense th {
  border: 1px solid #000000;
  padding: 8px;
}

.incomeexpense th {
  padding-top: 12px;
  padding-bottom: 12px;
  text-align: center;
  background-color: #FFFFFF;
  color: black;
  font-size: 14px;
}

</style>


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

	
        @media print {
            .fontsize { font-size: 15px ;
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
             margin-bottom: 0cm;
             margin-top: 0cm;
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
    </style>


<script>
    window.onload = function(){
        window.print();
    }
    
</script>
</head>


<body style="text-align: center" class="bodymargin">
	<form method="post" class="bodymargin">
	
		<table style="page-break-inside: avoid;border-collapse: collapse;margin-left: auto;margin-right: auto;">
                        		
			<tr>
				<td><img src="/jihtel/images/jihtel.png" width="80" height="80"/></td>
				<td>
				<label class="dataTextBoldCenter" style="text-transform: uppercase;">${branchname}</label><br>
				<label class="addressLine">${branchaddress}</label><br>
				<label class="addressLine">Report</label><br>
				</td>
			</tr>
		</table>
		
			<TABLE  width="100%" border="1" style="border-collapse:collapse;">
                <tr>
                    <td colspan="4" ></td>
                </tr>
            </TABLE>

		<div>
				
                
			<table width="100%" border="1" class="incomeexpense" style="border-color: #4b6a84;"	>

				<thead>
					
					<tr>
						<th title="click to sort" class="headerText" style="font-weight: bold;">Sl.No.</th>
						<th title="click to sort" class="headerText" style="font-weight: bold;">Name</th>
						<th title="click to sort" class="headerText" style="font-weight: bold;">Contact No.</th>
						<th title="click to sort" class="headerText" style="font-weight: bold;">Last Payment Date</th>
						<th title="click to sort" class="headerText" style="font-weight: bold;">Last Payment Days</th>
					</tr>
				</thead>

				<tbody>
					
					<c:forEach items="${lastpaymentreport}" var="lastpaymentreport" varStatus="status">

						<tr class="trClass" style="border-color: #000000" border="1"
							cellpadding="1" cellspacing="1">
							<td class="dataText" style="text-align: center"><c:out value="${status.index+1}" /></td>
							<td class="dataText" style="text-align: left"><c:out value="${lastpaymentreport.name}" /></td>
							<td class="dataText" style="text-align: left"><c:out value="${lastpaymentreport.classstudying}" /></td>
							<td class="dataText" style="text-align: left"><c:out value="${lastpaymentreport.bankname}" /></td>
							<td class="dataText" style="text-align: left"><c:out value="${lastpaymentreport.age}" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</form>
</body>
</html>

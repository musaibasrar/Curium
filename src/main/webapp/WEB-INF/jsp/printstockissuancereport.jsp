<%-- 
    Document   : PrintStockIssuanceReport
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
<title>Stock Issuance Report</title>
<style type="text/css">
.datath {
	width: 10px;
	font-family: Tahoma;
	font-size: 12px;
	color: black;
	font-weight: normal;
	width: auto;
	height: 22px;
	vertical-align: middle;
	text-align: center;
}
<!--
.datathLeft {
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

.datatdBold {
	font-weight: bold;
	font-family: Tahoma;
	color: black;
	font-size: 12px;
	letter-spacing: normal;
	text-align: center;
}

.datatdBoldLeft {
	font-weight: bold;
	font-family: Tahoma;
	color: black;
	font-size: 12px;
	letter-spacing: normal;
	text-align: left;
}

.datatdBoldCenter {
	font-weight: bold;
	font-family: Tahoma;
	color: black;
	font-size: 18px;
	letter-spacing: normal;
	text-align: center;
}

.addressLine{
	font-weight: bold;
	font-family: ariel;
	color: black;
	font-size: 22px;
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

.datatd {
	font-family: Tahoma;
	color: black;
	font-size: 12px;
	letter-spacing: normal;
	text-align: center;
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

.dataTextRight {
	border-radius: 3px;
	font-family: Tahoma;
	color: #4b6a84;
	font-size: 13px;
	letter-spacing: normal;
	text-align: right;
	background-color: #E3EFFF;
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




<body style="text-align: center" class="bodymargin">
<c:set var="itemTotal" value="${0}" />
	<form method="post" class="bodymargin">
		<table width="100%" style="border-collapse: collapse;">
			<tr>
				<td align="center">
				<img src="/cambridge/images/cambridge.jpg" width="150" height="80"/>
				</td>
				<td class="datatdBoldCenter" style="width: 100%">
				${branchname}<br><br>
				<label class="addressLine">Stock Issuance Report</label><br>
				<label class="addressLineTwo">${transactionfromdateselected}&nbsp;&nbsp;${transactiontodateselected}&nbsp;&nbsp;${issuedtoselected}&nbsp;&nbsp;${purposeselected}&nbsp;&nbsp;${itemselected}&nbsp;&nbsp;
				</label>
				</td>
			</tr>
	</table>

			<TABLE  width="100%" border="1" style="border-collapse:collapse;">
                <tr>

                    <td colspan="4">
                    
                    </td>

                </tr>
            </TABLE>
		
            <table class="datatable">
            
            <thead>
					<tr>
						<th class="datath">Sl.No.</th>
						<th class="datath">Date</th>
						<th class="datath">Student Name</th>
						<th class="datath">Class</th>
						<th class="datath">Father Name</th>
						<th class="datath">Item Name</th>
						<th class="datath">Quantity</th>
						<th class="datath">Unit of Measure</th>
						<th class="datath">Purchase Price</th>
						<th class="datath">Sales Price</th>
						<th class="datath">Purchase Total Price</th>
						<th class="datath">Sales Total Price</th>
						
					</tr>
				</thead>
			<tbody>
				<c:forEach items="${stockissuancelist}" var="stockissuancelist" varStatus="status">
						<tr>
							<td class="datatd"><c:out value="${status.index+1}" /></td>
						  <td class="datatd"><input type="text"  style="border-style: none;color: #4B6A84;text-align: center;"  value="<fmt:formatDate value="${stockissuancelist.transactiondate}" pattern="dd/MM/yyyy"/>" readonly></td>
						    <c:set var="nameparts" value="${fn:split(stockissuancelist.issuedto, '_')}" />
						 	<td class="datatd"><c:out value="${nameparts[0]}" /></td>
						 	<td class="datatd"><c:out value="${nameparts[1]}" /></td>
						 	<td class="datatd"><c:out value="${nameparts[2]}" /></td>
						  <%-- <td class="datatd"><c:out value="${stockissuancelist.purpose}" /></td> --%>
						  <td class="datatd"><c:out value="${stockissuancelist.itemname}" /></td>
						  <td class="datatd"><c:out value="${stockissuancelist.quantity}" /></td>
						  <td class="datatd"><c:out value="${stockissuancelist.unitofmeasure}" /></td>
						  <td class="datatd"><c:out value="${stockissuancelist.itemunitprice}" /></td>
						  <td class="datatd"><c:out value="${stockissuancelist.purpose}" /></td>
						  <td class="datatd">
						  <c:set var="itemTotal" value="${itemTotal + stockissuancelist.itemunitprice * stockissuancelist.quantity}" />
						  <fmt:setLocale value="en_IN" scope="session"/>
							<fmt:formatNumber type="currency"  value="${stockissuancelist.itemunitprice * stockissuancelist.quantity}" />
						  </td>
						  <td class="datatd">
						  <c:set var="itemSalesPrice" value="${stockissuancelist.purpose}" />
						  <c:set var="itemTotalSales" value="${itemTotalSales + itemSalesPrice * stockissuancelist.quantity}" />
						  <fmt:setLocale value="en_IN" scope="session"/>
							<fmt:formatNumber type="currency"  value="${itemSalesPrice * stockissuancelist.quantity}" />
						  </td>
						</tr>
					</c:forEach>
			</tbody>
				</table>
			<br>
			<div style="page-break-inside: avoid;" align="center">
				<table align="right">
						<tr>
							<td align="right">
							<label style="color: #eb6000"><b>Purchase Total:
							<fmt:setLocale value="en_IN" scope="session"/>
							<fmt:formatNumber type="currency"  value="${itemTotal}" />&nbsp;&nbsp;&nbsp;</b>
							</label> 
							<label style="color: #eb6000"><b>Sales Total:
							<fmt:setLocale value="en_IN" scope="session"/>
							<fmt:formatNumber type="currency"  value="${itemTotalSales}" /></b>
							</label> 
							</td>
						</tr>
				</table>
			</div>
			<br>
			<div style="page-break-inside: avoid;" align="center">
				<table align="right">
						<tr>
							<td align="right" >
								<label style="color: #eb6000"><b>Profit:
							<fmt:setLocale value="en_IN" scope="session"/>
							<fmt:formatNumber type="currency"  value="${itemTotalSales-itemTotal}" /></b>
							</label> 
							</td>
						</tr>
				</table>
				<table>
						<tr>
							<td>
								<p><h4>------------- End of The Report ---------- </h4></p>
							</td>
						</tr>				
				</table>
			</div>
	</form>
</body>
</html>

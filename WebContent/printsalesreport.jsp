<%-- 
    Document   : PrintSalesReport
    Created on : Mar 6 1 2021, 04:44 PM
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
<title>Print Sales Report</title>
<style type="text/css">
<!--
.headerText {
	width: 10px;
	font-family: Tahoma;
	font-size: 12px;
	color: #FFFFFF;
	font-weight: normal;
	width: auto;
	height: 22px;
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
	font-size: 22px;
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
	font-size: 25px;
	letter-spacing: normal;
	text-align: center;
	text-decoration: underline;
}

.selectedparameters{
	font-weight: bold;
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
             margin-bottom: 1cm;
             margin-top: 1cm;
             size: auto;
             
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




<body style="text-align: center" class="bodymargin">
	<form method="post" class="bodymargin">
	<c:set var="itemTotal" value="${0}" />
	<c:set var="totalQuantity" value="${0}" />
		<br>
		<table width="100%" style="border-collapse: collapse;">
			<tr>
				<td align="center">
				<img src="images/bielogo.png" width="50" height="80"/>
				</td>
				<td class="dataTextBoldCenter" style="width: 100%">
				BOARD OF ISLAMIC EDUCATION KARNATAKA<br>
				<label class="addressLine">Books List</label><br>
				<label class="selectedparameters">${fromdateselected}&nbsp;&nbsp;${todateselected}&nbsp;&nbsp;${titleselected}&nbsp;&nbsp;${authorselected}&nbsp;&nbsp;${languageselected}&nbsp;&nbsp;${centercodeselected}
				
				<br>
				 </label>
				</td>
			</tr>
			
			<tr></tr>
	</table>

<TABLE  width="100%" border="1" style="border-collapse:collapse;">
                <tr>

                    <td colspan="4" ></td>

                </tr>
            </TABLE>

		<table>
		</table>
		<TABLE  width="100%" border="1" style="border-collapse:collapse;">
                <tr>

                    <td colspan="4" ></td>

                </tr>
            </TABLE>
            <br>
            
            <table class="datatable">
            
            <thead>
					<tr>
						<th title="click to sort" class="datath">Order Date</th>
						<th title="click to sort" class="datath">Center</th>
						<th title="click to sort" class="datath">Status</th>
						<th title="click to sort" class="datath">Title</th>
						<th title="click to sort" class="datath">Author</th>
						<th title="click to sort" class="datath">Language</th>
						<th title="click to sort" class="datath">Qty</th>
                        <th title="click to sort" class="datath">Price&nbsp;</th>
						<th title="click to sort" class="datath">Total Price</th>
					</tr>
				</thead>
			<tbody>
			
			<c:forEach items="${bookslist}" var="resultlist" varStatus="status">

						<tr>
							<td class="datatd"><c:out value="${resultlist.orderdate}" /></td>
							<td class="datatd"><c:out value="${resultlist.centercode}" /></td>
							<td class="datatd"><c:out value="${resultlist.narration}" /></td>
							<td class="datatd"><c:out value="${resultlist.title}" /></td>
							<td class="datatd"><c:out value="${resultlist.author}" /></td>
							<td class="datatd"><c:out value="${resultlist.language}" /></td>
							<td class="datatd"><c:out value="${resultlist.quantity}" /></td>
							<td class="datatd"><c:out value="${resultlist.price}" /></td>
							<td class="datatdright">
							<c:set var="totalQuantity" value="${totalQuantity + resultlist.quantity}" />
							 <c:set var="itemTotal" value="${itemTotal + resultlist.quantity * resultlist.price}" />
                                <fmt:setLocale value="en_IN" scope="request"/>
								<fmt:formatNumber type="currency"  value="${resultlist.quantity * resultlist.price}" />
							</td>							
						</tr>
					</c:forEach>
			</tbody>
				</table>
			<br><br>
			<div style="page-break-inside: avoid;" align="center">
				<table align="right">
						<tr>
							<td align="right">
							<label style="font-weight: bold;">Total Quantity:
									<c:out value="${totalQuantity}" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</label> 
							<label style="font-weight: bold;">
							Grand Total: 
							<fmt:setLocale value="en_IN" scope="request"/>
							<fmt:formatNumber type="currency"  value="${itemTotal}" />
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

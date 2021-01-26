<%-- 
    Document   : Print Supplier Payment
    Created on : JAN 18 2021, 4:01 PM
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
<title>Print Supplier Payment</title>
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
	font-size: 12px;
	letter-spacing: normal;
	text-align: left;
}

.dataTextBoldCenter {
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
.datatdt{
    border: 0px solid #000000;
    text-align: left;
    padding: 8px;
}

.datatdright {
    border: 1px solid #000000;
    text-align: right;
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
            .fontsize { font-size: 12px;
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
		<table width="100%" style="border-collapse: collapse;">
			<tr>
				<td align="center">
				<img src="images/shaheenlogo.png" width="150" height="80"/>
				</td>
				<td class="dataTextBoldCenter" style="width: 100%">
				Zaiqa Enterprises<br><br>
				<label class="addressLine">Supplier Payment Report</label><br>
				</td>
			</tr>
	</table>

<TABLE  width="100%" border="1" style="border-collapse:collapse;">
                <tr>

                    <td colspan="4" ></td>

                </tr>
            </TABLE>
		
            <table class="datatable">
            <thead>
 				 <tr>
 				 		<th class=datath>Sl.No</th>
						<th class="datath">Issue Date</th>
						<th class="datath">Status</th>
						<th class="datath">Supplier Name</th>
						<th class="datath">Cheque #</th>
						<th class="datath">Amount</th>
 				 </tr>
 			 </thead>
 		 
			<tbody>
					<c:forEach items="${supplierpaymentlist}" var="supplierpaymentlist" varStatus="status">

						<tr>
							<td class="datatd" style="font-size: 12px;">${status.index+1}</td>
	        				<td class="datatd" style="font-size: 12px;"><fmt:formatDate value="${supplierpaymentlist.issuedate}" pattern="dd/MM/yyyy"/></td>
						    <td class="datatd" style="font-size: 12px;">${supplierpaymentlist.status}</td>
						    <td class="datatd" style="font-size: 12px;">
						      <c:set var="nameparts" value="${fn:split(supplierpaymentlist.externalid, '_')}" />
						         ${nameparts[0]}
						    </td>
						    <td class="datatd" style="font-size: 12px;">${supplierpaymentlist.chequeno}</td>
						    <td class="datatdright">
						  	  <fmt:formatNumber type="number"  maxFractionDigits = "2"  value="${supplierpaymentlist.amount}" />
						    </td>
						</tr>
					</c:forEach>
			</tbody>
				</table>
			<br>
			<div style="page-break-inside: avoid;" align="center">
				<table>
						<tr>
							<td>
								<p><h4>------------- End of The Report ----------</h4></p>
							</td>
						</tr>				
				</table>
			</div>
	</form>
</body>
</html>

<%-- 
    Document   : Print Language Report
    Created on : JUL 30 2018, 12:01 PM
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
<title>Print Language Report</title>
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
		<br>
		<table width="100%" style="border-collapse: collapse;">
			<tr>
				<td align="center">
				<img src="images/bielogo.png" width="80" height="110"/>
				</td>
				<td class="dataTextBoldCenter" style="width: 100%">
				BOARD OF ISLAMIC EDUCATION <br>
				KARNATAKA<br><br>
				<label class="addressLine">Language Report</label><br>
				<label class="reportheaders"><c:out value="${printcentername}" /></label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<label class="reportheaders"><c:out value="${printexamlevel}" /></label>
				 
				</td>
			</tr>
			<tr>
			<td></td></tr>
			<tr></tr>
	</table>

<TABLE  width="100%" border="1" style="border-collapse:collapse;">
                <tr>

                    <td colspan="4" ></td>

                </tr>
            </TABLE>
		
            <table class="datatable">
            <thead>
 				 <tr>
 				 		<th class="datath">CODE</th>
						<th class="datath">CENTER NAME</th>
						<th class="datath">ENGLISH</th>
						<th class="datath">URDU</th>
						<th class="datath">HINDI</th>
						<th class="datath">KANNADA</th>
						<th class="datath">TOTAL</th>
 				 </tr>
 			 </thead>
 		 
			<tbody>
					<c:forEach items="${languagereports}" var="languagereports">

						<tr>
							<td class="datatd" style="font-size: 12px;"><c:out value="${languagereports.key.get(0)}" /></td>
							<td class="datatd" style="font-size: 12px;"><c:out value="${languagereports.key.get(1)}" /></td>
							<td class="datatd" style="font-size: 12px;"><c:out value="${languagereports.value.get(0)}" /></td>
							<td class="datatd" style="font-size: 12px;"><c:out value="${languagereports.value.get(1)}" /></td>
							<td class="datatd" style="font-size: 12px;"><c:out value="${languagereports.value.get(2)}" /></td>
							<td class="datatd" style="font-size: 12px;"><c:out value="${languagereports.value.get(3)}" /></td>
							<td class="datatd" style="font-size: 12px;"><c:out value="${languagereports.value.get(4)}" /></td>	
						</tr>
					</c:forEach>
					<tr>
							<td class="datatdt"></td>
							<td class="datatdt">Total</td>
							<td class="datatdt"><c:out value="${englishcounttotal}" /></td>
							<td class="datatdt"><c:out value="${urducounttotal}" /></td>
							<td class="datatdt"><c:out value="${hindicounttotal}" /></td>
							<td class="datatdt"><c:out value="${kannadacounttotal}" /></td>
							<td class="datatdt"><c:out value="${totalcount}" /></td>
						</tr>
			</tbody>
				</table>
				
			<br><br>
	</form>
</body>
</html>

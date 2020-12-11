<%-- 
    Document   : Print Result List
    Created on : Aug 09 2018, 01:14 PM
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
<title>Result List Report</title>
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
	font-size: 28px;
	letter-spacing: normal;
	text-align: center;
}

.addressLine{
		font-weight: bold;
	font-family: ariel;
	color: black;
	font-size: 30px;
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
		<br>
		<table width="100%" style="border-collapse: collapse;">
			<tr>
				<td align="center">
				<img src="images/bielogo.png" width="100" height="150"/>
				</td>
				<td class="dataTextBoldCenter" style="width: 100%">
				BOARD OF ISLAMIC EDUCATION KARNATAKA<br><br>
				<label class="addressLine">Result List<br><br>
				 </label>
				  <c:out value="${resultcentercode}"/>
				 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				 <c:out value="${resultexamlevel}"/>
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
		<tr>
			<td></td>
			
			</tr>
			<tr>
			<td></td>
			
			</tr>
			<tr>
						<td class="dataTextBoldLeft" style="width: 50%"><c:out value="${resultcentername}" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<c:out value="${resultlanguage}" /></td>
			</tr>
			
			<tr>
			<td></td>
			</tr>
			<tr>
						<td class="dataTextBoldLeft" style="width: 50%"><c:out value="${resultexamlevel}" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<c:out value="${resultqualification}" /></td>
			</tr>
			<tr>
			<td></td>
			</tr>
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
						<th title="click to sort" class="datath">Admission No.</th>
						<th title="click to sort" class="datath">Student Name&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
						<th title="click to sort" class="datath">Language</th>
						<%-- <c:forEach items="${resultsubexamlevel}" var="subexamlevel">
						<th title="click to sort" class="datath">${subexamlevel.subjectname}&nbsp;</th>
						</c:forEach> --%>
						<th title="click to sort" class="datath">Paper 1&nbsp;</th>
						<th title="click to sort" class="datath">Paper 2&nbsp;</th>
						<th title="click to sort" class="datath">%&nbsp;</th>
						<th title="click to sort" class="datath">Result&nbsp;</th>
					</tr>
				</thead>
			<tbody>
			
			<c:forEach items="${resultlistadmno}" var="resultlist" varStatus="status">

						<tr>
							<td class="datatd"><a class="dataTextInActive"><c:out	value="${resultlist.student.admissionnumber}" /></a></td>
							<td class="datatd"><c:out value="${resultlist.student.name}" /></td>
							<td class="datatd"><c:out value="${resultlist.student.languageopted}" /></td>
							<%-- <c:forEach items="${resultlist.marksList}" var="markslist">
							<td class="datatd"><c:out value="${markslist}" /></td>
							</c:forEach> --%>
							<c:forEach items="${resultlist.marksList}" var="markslist">
							<c:if test="${(markslist == 999)}">
								<td class="datatd"></td>
							</c:if>
							<c:if test="${(markslist != 999)}">
								<td class="datatd"><c:out value="${markslist}" /></td>
							</c:if>
							</c:forEach>
							<td class="datatd"><c:out value="${resultlist.percentage}" /></td>
							<td class="datatd"><c:out value="${resultlist.resultclass}" /></td>
						</tr>
					</c:forEach>
			</tbody>
				</table>
			<br><br>
			<a style="font-weight: bold;color: black;font-size: 20px;">&nbsp;&nbsp;Total: ${totalstudentresult}&nbsp;&nbsp;Distinction: ${distinctioncount}&nbsp;&nbsp;First Class: ${firstcount}
						  &nbsp;&nbsp;Second Class: ${secondcount}&nbsp;&nbsp;Pass: ${passcount}&nbsp;&nbsp;Fail: ${failcount}</a>
	</form>
</body>
</html>

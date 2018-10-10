<%-- 
    Document   : Print Marks Sheet
    Created on : Aug 11 2018, 03:20 PM
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
	font-size: 28px;
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
    width: 60%;
}

.datatd, .datath {
    border: 1px solid #000000;
    text-align: left;
    padding: 5px;
    width: 10%;
}
.markssheet{
	font-weight: bold;
	font-size: 20px;
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
		<c:forEach items="${markssheetlist}" var="mlist" >
		<table width="100%" style="border-collapse: collapse;">
			<tr>
				<td align="center">
				<br>
				</td>
				<td class="dataTextBoldCenter" style="width: 100%">
				<br><br><br><br><br>
				</td>
			</tr>
			<tr>
			<td></td></tr>
			<tr></tr>
	</table>

		<table>
		<tr>
			<td></td>
			
			</tr>
			<tr>
			<td></td>
			
			</tr>
			<tr>
						<td>&nbsp;&nbsp;&nbsp;<label class="markssheet">Examination Year:&nbsp;</label>
						<c:out value="${markssheetyear}" /></td>
						<td></td>
						<td align="left"><label class="markssheet">Examination:&nbsp;</label><c:out value="${markssheetexamlevel}" /></td>
			</tr>
			<tr>
			<td></td>
			</tr>
			<tr>
						<td>&nbsp;&nbsp;&nbsp;<label class="markssheet">Center Code:&nbsp;</label>
						<c:out value="${markssheetcentercode}" />
						</td>	
						<td></td>
						<td align="left"><label class="markssheet">Language:&nbsp;</label><c:out value="${markssheetlanguage}" /></td>
			</tr>
			<tr>
			<td></td>
			</tr>
			<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<label class="markssheet">Center Name:&nbsp;</label>
						<c:out value="${markssheetcentername}" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</td>
						<td></td>
						<td align="left"><label class="markssheet">Reg.No:&nbsp;</label><c:out value="${mlist.parents.student.admissionnumber}" /></td>
			</tr>
			<tr>
			<td></td>
			</tr>
			<tr>
						<td>&nbsp;&nbsp;&nbsp;<label class="markssheet">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						
						Candidate Name:&nbsp;</label>
						<c:out value="${mlist.parents.student.name}" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</td>
			</tr>
			<tr>
			<td></td>
			</tr>
			      <tr>
			      
							<c:if test="${(mlist.parents.mothersname != '')}">
                                <td><label style="font-weight: bold;" class="markssheet">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                W/O:&nbsp;&nbsp;&nbsp;</label><label ><c:out value="${mlist.parents.mothersname}"/></label></td>
							</c:if>	

								<c:if test="${(mlist.parents.fathersname != '')}">
                                <td><label style="font-weight: bold;" class="markssheet">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;S/D/O:&nbsp;&nbsp;&nbsp;</label><label ><c:out value="${mlist.parents.fathersname}"/></label></td>
							</c:if>
							

                             	<c:if test="${(mlist.parents.student.guardiandetails != '')}">
                                <td><label style="font-weight: bold;" class="markssheet">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Guardian:&nbsp;&nbsp;&nbsp;</label><label ><c:out value="${mlist.parents.student.guardiandetails}"/></label></td>
                                </c:if>
                </tr>
                <tr>
			<td></td>
			</tr>  
                       
		</table>
		
            <br>
            
            <table class="datatable" align="center">
            
            <thead>
					<tr>
						<c:forEach items="${mlist.subjectList}" var="subexamlevel">
						<th class="datath">${subexamlevel}</th>
						</c:forEach>
						<th class="datath">Total</th>
						<th class="datath">Result</th>
					</tr>
				</thead>
			<tbody>
			

						<tr>
							<c:forEach items="${mlist.marksList}" var="markslist">
							<td class="datatd"><c:out value="${markslist}" /></td>
							</c:forEach>
							<td class="datatd"><c:out value="${mlist.totalMarksObtained}" /></td>
							<td class="datatd"><c:out value="${mlist.resultclass}" /></td>
						</tr>
						
							<tr><td><br><br><br></td></tr>

			</tbody>
				</table>
				
				<table align="center">
												
							<tr>
							<td></td>
							<td style="font-weight: bold;">Reference Books:</td>
							<c:forEach items="${mlist.referenceBooksList}" var="referencebooks">
							<tr align="center" >
							<td></td>
							<td></td>
							<td align="left" style="font-weight: bold;"><c:out value="${referencebooks}" /></td>
							</tr>
						</c:forEach>
							</tr>
							<tr><td><br><br><br></td></tr>
							<tr><td><br><br><br></td></tr>
				</table>

				<table style="page-break-after:always;">
				<tr>
		<td></td>
		<td align="left"></td>	
			<td align="centre" style="padding-left: 300px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
			<td align="right" style="padding-left: 300px;"><img alt="Chief Examiner Signature" src="images/cesignature.png" width="200" height="70"></td>
			</tr>
				<tr>
		<td></td>
		<td align="left">Candidate's Signature</td>	
			<td align="centre" style="padding-left: 300px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Seal & Sign of Organiser</td>
			<td align="right" style="padding-left: 300px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Signature of Chief Examiner</td>
			</tr>
			
			<tr>
                        <td align="center"><br><br><br><br><br></td>
                    </tr>
				</table>
			<br><br>
			</c:forEach>
	</form>
</body>
</html>

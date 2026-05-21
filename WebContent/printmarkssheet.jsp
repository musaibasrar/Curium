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
<title>Marks Sheet</title>
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
    width: 90%;
    font-size: 18px;
}

.datatd, .datath {
    border: 1px solid #000000;
    text-align: center;
    padding: 5px;
    width: 10%;
}
.markssheetlabel{
	font-weight: bold;
	font-size: 20px;
	text-align: left;
}
.markssheetvalue{
	font-size: 20px;
	text-align: left;
}
#footer {
	font-family: arial, sans-serif;
	font-size: 15px;
    position: fixed;
    right: 0;
    bottom: 0;
    left: 0;
    padding: 1rem;
    text-align: center;
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
              
           margin-left:  1.5cm;
             margin-right: 1cm;
             margin-bottom: 0cm;
             margin-top: 0cm;
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
		<c:forEach items="${markssheetlist}" var="mlist" >
		<div style="page-break-inside: avoid;">
		<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
		<table width="100%" border="0"
	style="border-color: #4b6a84; float: left; text-align: left; table-layout: fixed;">

	<tr>
		<td width="5%">&nbsp;</td>

		<td width="47%" style="vertical-align: top;">
			<label class="markssheetlabel">Examination Year:</label>
			&nbsp;&nbsp;
			<label class="markssheetvalue">${markssheetyear}</label>
		</td>

		<td width="48%" style="vertical-align: top;">
			<label class="markssheetlabel">Center Code:</label>
			&nbsp;&nbsp;
			<label class="markssheetvalue">${markssheetcentercode}</label>
		</td>
	</tr>

	<tr>
		<td>&nbsp;</td>

		<td style="vertical-align: top;">
			<label class="markssheetlabel">Language:</label>
			&nbsp;&nbsp;
			<label class="markssheetvalue">${mlist.parents.student.languageopted}</label>
		</td>

		<td style="vertical-align: top;">
			<label class="markssheetlabel">Reg. No:</label>
			&nbsp;&nbsp;
			<label class="markssheetvalue">${mlist.parents.student.admissionnumber}</label>
		</td>
	</tr>

</table>


<table width="100%" border="0"
	style="border-color: #4b6a84; float: left; text-align: left; table-layout: fixed; margin-top: 5px;">

	<tr>
		<td width="5%">&nbsp;</td>

		<td>
			<label class="markssheetlabel">Examination:</label>
			&nbsp;&nbsp;
			<label class="markssheetvalue">${markssheetexamlevel}</label>
		</td>
	</tr>

</table>


<table width="100%" border="0"
	style="text-align: left; table-layout: fixed; margin-top: 8px;">

	<tr>
		<td width="5%">&nbsp;</td>

		<td style="vertical-align: top; word-wrap: break-word; white-space: normal;">

			<label class="markssheetlabel">Center Name:</label>
			&nbsp;&nbsp;

			<label class="markssheetvalue"
				style="display: inline-block;
					   width: 75%;
					   vertical-align: top;
					   word-break: break-word;
					   white-space: normal;
					   line-height: 20px;">

				${markssheetcentername}

			</label>

		</td>
	</tr>


	<tr>
		<td>&nbsp;</td>

		<td style="padding-top: 15px; vertical-align: top;">

			<label class="markssheetlabel">Candidate Name:</label>
			&nbsp;&nbsp;

			<label class="markssheetvalue"
				style="font-weight: bold;
					   display: inline-block;
					   width: 70%;
					   word-break: break-word;
					   white-space: normal;">

				${mlist.parents.student.name}

			</label>

		</td>
	</tr>


	<tr>
		<td>&nbsp;</td>

		<td style="padding-top: 10px; vertical-align: top;">

			<c:if test="${(mlist.parents.mothersname != '')}">
				<div style="margin-bottom: 5px;">

					<label class="markssheetlabel"
						style="display: inline-block; width: 120px;">
						W/O:
					</label>

					<label class="markssheetvalue"
						style="font-weight: bold;
							   display: inline-block;
							   width: 70%;
							   word-break: break-word;
							   white-space: normal;">

						${mlist.parents.mothersname}

					</label>

				</div>
			</c:if>


			<c:if test="${(mlist.parents.fathersname != '')}">
				<div style="margin-bottom: 5px;">

					<label class="markssheetlabel"
						style="display: inline-block; width: 120px;">
						S/D/O:
					</label>

					<label class="markssheetvalue"
						style="font-weight: bold;
							   display: inline-block;
							   width: 70%;
							   word-break: break-word;
							   white-space: normal;">

						${mlist.parents.fathersname}

					</label>

				</div>
			</c:if>


			<c:if test="${(mlist.parents.student.guardiandetails != '')}">
				<div>

					<label class="markssheetlabel"
						style="display: inline-block; width: 120px;">
						Guardian:
					</label>

					<label class="markssheetvalue"
						style="font-weight: bold;
							   display: inline-block;
							   width: 70%;
							   word-break: break-word;
							   white-space: normal;">

						${mlist.parents.student.guardiandetails}

					</label>

				</div>
			</c:if>

		</td>
	</tr>

</table>
            <br>
            
            <table class="datatable" align="center" style="width:85%">
            
            <thead>
					<tr>
						<c:forEach items="${mlist.subjectList}" var="subexamlevel">
						<c:if test="${(subexamlevel != 'PAPER ')}">
						<th class="datath">${subexamlevel}</th>
						</c:if>
						<c:set var = "subjectname" value = "${subexamlevel}"/>
						</c:forEach>
						<c:if test="${(subjectname == 'PAPER ')}">
						<th class="datath">Marks Obtained</th>
						</c:if>
						<c:if test="${(subjectname != 'PAPER ')}">
						<th class="datath">Marks Obtained</th>
						</c:if>
						<th class="datath">Total Marks</th>
						<th class="datath">Percentage</th>
						<th class="datath">Result</th>
					</tr>
				</thead>
			<tbody>
			

						<tr>
							<c:forEach items="${mlist.marksList}" var="markslist">
							<c:if test="${(subjectname != 'PAPER ')}">
							<td class="datatd"><c:out value="${markslist}" /></td>
							</c:if>
							</c:forEach>
							<td class="datatd"><c:out value="${mlist.totalMarksObtained}" /></td>
							<td class="datatd"><c:out value="${mlist.totalMarks}" /></td>
							<td class="datatd">
							<fmt:formatNumber type = "number" 
         					pattern = "0.#" value = "${mlist.percentage}" />%</td>
							<td class="datatd"><c:out value="${mlist.resultclass}" /></td>
						</tr>
						
							<tr><td><br></td></tr>

			</tbody>
				</table>
				
				<table align="center">
												
							<tr>
							
							<td style="font-weight: bold;">Reference Books:</td>
							<c:forEach items="${mlist.referenceBooksList}" var="referencebooks" varStatus="status">
							<tr align="center" >
							<td></td>
							<td align="left" style="font-weight: bold;font-size:20px;">${status.index+1}.&nbsp;<c:out value="${referencebooks}" /><br></td>
							</tr>
						</c:forEach>
							</tr>
				</table>
<!-- <div id="footer">

		<table>
				<tr>
					<td align="right">
					<img alt="Chief Examiner Signature" src="images/cesignature.jpg" width="200" height="70">
					</td>
				</tr>
				
				<tr>
					<td><br><br><br><br></td>
				</tr>
			
	    </table>
	  
			</div>
 -->			</div>
			</c:forEach>
	</form>
</body>
</html>

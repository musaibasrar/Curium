<%-- 
    Document   : Print Hall Ticket
    Created on : Apr 04 2018, 04:32 PM
    Author     : Musaib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html moznomarginboxes>
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
	font-weight: normal;
	font-family: Tahoma;
	color: black;
	font-size: 12px;
	letter-spacing: normal;
	text-align: left;
}

.dataTextBoldCenter {
	font-weight: normal;
	font-family: Tahoma;
	color: black;
	font-size: 18px;
	letter-spacing: normal;
	text-align: center;
}

.addressLine {
	font-weight: normal;
	font-family: ariel;
	color: black;
	font-size: 12px;
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
span {
	display: inline-block;
	border-bottom: 2px solid black;
	padding-bottom: 1px;
	width: 300px;
	font-weight: normal;
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
	.fontsize {
		font-size: 15px;
		font-weight: bold;
		font-family: 'Times New Roman';
	}
	.header, .hide {
		visibility: hidden
	}
	.bodymargin {
		margin-left: 0px;
		margin-right: 0px;
	}
}

@page {
	margin-left: 1cm;
	margin-right: 1cm;
	margin-bottom: 1cm;
	margin-top: 1cm;
	size: auto;
}

@media screen {
	.fontsize {
		font-size: 15px;
		font-weight: bold;
		font-family: 'Times New Roman'
	}
	.bodymargin {
		margin-left: 0px;
		margin-right: 0px;
	}
}

.subjectdetails {
	border: 1px solid black;
	text-align: center;
	padding: 8px;
	font-size: 12px;
}

.nosubjectdetails {
	border: 0px;
	text-align: left;
	padding: 8px;
	font-weight: normal;
}

.namedetails {
	border: 0px solid #dddddd;
	text-align: left;
	padding: 4px;
}

.namedetailscenter {
	border: 0px solid #dddddd;
	text-align: right;
	padding: 8px;
}

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

h3{
	text-align: center;
    margin-top: 0px;
    margin-bottom: 0px;
}

h2{
	text-align: center;
}

h4{
	text-align: center;
}

table {
  border-collapse: collapse;
  width: 100%;
}

td, th {
  border: 1px solid black;
  text-align: left;
  padding: 8px;
}

input {
  border: 0;
  outline: 0;
  border-bottom: solid;
        
}
</style>
<script type="text/javascript">
                       
		window.onload = function(){
		window.print();
		}
        </script>
<title></title>

</head>
<%
//allow access only if session exists
String user = null;
if(session.getAttribute("userAuth") == null){
	response.sendRedirect("/abc/UserProcess/sessionTimeOut");
}else user = (String) session.getAttribute("userAuth");
String userName = null;
String sessionID = null;
Cookie[] cookies = request.getCookies();
if(cookies !=null){
for(Cookie cookie : cookies){
	if(cookie.getName().equals("user")) userName = cookie.getValue();
	if(cookie.getName().equals("JSESSIONID")) sessionID = cookie.getValue();
}
}
%>
<body style="text-align: center" class="bodymargin">
	<jsp:useBean id="now" class="java.util.Date" scope="page" />
	<form method="post" class="bodymargin">

		<div
			style="page-break-inside: avoid; border-style: solid; border-width: thin;">

			<table
				style="page-break-inside: avoid; border-collapse: collapse; margin-left: auto; margin-right: auto;">

				<tr style="border: 0px solid black;">
					<td style="text-align: center; border: 0px solid black;"><img src="/abc/images/abc.jpg" width="72"
						height="80" /><br/><label>Regn. No.</label></td>
					<td style="text-align: center;border: 0px solid black;"><label class="dataTextBoldCenter"
						style="text-transform: uppercase;">${branchname}</label><br>
						 <label class="addressLine">${branchaddress}</label><br> 
						 <label	class="addressLine">Contact: ${branchcontact}<br/></label>
						 <label class="addressLine" style="font-weight: bold;">
							REGISTRATION CUM ADMISSION FORM : SESSION ${parents.student.yearofadmission}
					</label>
					
						 </td>
					<td style="text-align: center;border: 0px solid black;"><img
						src="data:image;base64,<c:out value="${Parents.student.studentpic}"/>"
						alt="Student's Photo" width="140" height="70" /></td>
				</tr>
			</table>
			<table>
				<tr>
					<td>1</td>
					<td>Pupil's Name</td>
					<td style="width: 70%">&nbsp;<c:out
							value="${parents.student.name}" /></td>
				</tr>
				<tr>
					<td>2</td>
					<td>Mother's Name</td>
					<td><c:out value="${parents.mothersname}" /></td>
				</tr>
				<tr>
					<td>3</td>
					<td>Father's Name</td>
					<td><c:out value="${parents.fathersname}" /></td>
				</tr>
				<tr>
					<td>4</td>
					<td>Category</td>
					<td>General<input type="checkbox" value="General"
						name="socialcategory" id="yes:General"
						${parents.student.socialcategory == 'General' ? 'checked' : ''} />&nbsp;
						&nbsp;OBC<input type="checkbox" value="OBC" name="socialcategory"
						id="no:OBC" onclick="noCheck(this.id);"
						${parents.student.socialcategory == 'OBC' ? 'checked' : ''} /> SC<input
						type="checkbox" value="SC" name="socialcategory" id="yes:SC"
						${parents.student.socialcategory == 'SC' ? 'checked' : ''} />&nbsp;
						&nbsp;ST<input type="checkbox" value="ST" name="socialcategory"
						id="no:ST" onclick="noCheck(this.id);"
						${parents.student.socialcategory == 'ST' ? 'checked' : ''} /></td>
				</tr>


				<tr>
					<td>5</td>
					<td>Class to which admission is sought</td>
					<td style="width: 70%">
							<c:forEach var="splt" items="${fn:split(parents.student.classadmittedin,'--')}">
						    	<c:out
							value="${splt}" />							
								</c:forEach></td>
				</tr>
				<tr>
					<td>6</td>
					<td>Date of Birth</td>
					<td style="width: 70%"><fmt:formatDate
							value="${parents.student.dateofbirth}" pattern="dd/MM/yyyy" /></td>
				</tr>
				<tr>
					<td>7</td>
					<td>Blood Group</td>
					<td><c:out value="${parents.student.bloodgroup}" /></td>
				</tr>
				<tr>
					<td>8</td>
					<td>Weight</td>
					<td><c:out value="${parents.student.bhagyalakshmibondnumber}" /></td>
				</tr>
				<tr>
					<td>9</td>
					<td>Height</td>
					<td><c:out value="${parents.student.bplcardno}" /></td>
				</tr>
				<tr>
					<td>10</td>
					<td>Mark of Identification</td>
					<td><c:out value="${parents.student.remarks}" /></td>
				</tr>
				<tr>
					<td>11</td>
					<td>Nationality</td>
					<td><c:out value="${parents.student.nationality}" /></td>
				</tr>
				<tr>
					<td>12</td>
					<td>Mother Tongue</td>
					<td><c:out value="${parents.student.mothertongue}" /></td>
				</tr>
				<tr>
					<td>13</td>
					<td>Address</td>
					<td><c:out value="${parents.addresspermanent}" /></td>
				</tr>
				<tr>
					<td>14</td>
					<td>Email</td>
					<td><c:out value="${parents.email}" /></td>
				</tr>


				<tr>
					<td>15</td>
					<td>Adhar</td>
					<td style="width: 70%">&nbsp;<c:out
							value="${parents.student.disabilitychild}" /></td>
				</tr>
				<tr>
					<td>16</td>
					<td>Mobile</td>
					<td><c:out value="${parents.contactnumber}" /></td>
				</tr>
				<tr>
					<td>17</td>
					<td>Qualification of Father</td>
					<td><c:out value="${parents.fathersqualification}" /></td>
				</tr>
				<tr>
					<td>18</td>
					<td>Qualification of Mother</td>
					<td><c:out value="${parents.mothersqualification}" /></td>
				</tr>
				<tr>
					<td>19</td>
					<td>Occupation of Father</td>
					<td><c:out value="${parents.fatherscastecertno}" /></td>
				</tr>
				<tr>
					<td>20</td>
					<td>Occupation of Mother</td>
					<td><c:out default="" value="${parents.motherscastecertno}" /></td>
				</tr>
				<tr>
					<td>21</td>
					<td>Annual Income</td>
					<td><c:out default="" value="${parents.parentsannualincome}" /></td>
				</tr>
				<tr>
					<td>22</td>
					<td>Local Guardian's Name(with Relation) & Address</td>
					<td><c:out default="" value="${parents.student.guardiandetails}" /></td>
				</tr>
				<tr>
					<td>23</td>
					<td>Name and Address of previous school</td>
					<td><c:out default="" value="${parents.student.schoollastattended}" /></td>
				</tr>

				<tr>
					<td>24</td>
					<td>Previous Class</td>
					<td style="width: 70%"><c:out default=""
							value="${parents.student.stdlaststudied}" /></td>
				</tr>
				<tr>
					<td>25</td>
					<td>Percentage of Mark</td>
					<td><c:out default="" value="${parents.student.subsequentprogress}" /></td>
				</tr>
			</table>
			<h2>DECLARATION</h2>
			<table>
				<tr>
					<td>26</td>
					<td>Declaration</td>
					<td>I solemnly declare that the above Information is correct
						to the best of my knowledge and I will abide by the rules and
						regulation of the school.</td>
				</tr>
				<tr>
					<td>27</td>
					<td>Place</td>
					<td></td>
				</tr>
				<tr>
					<td>28</td>
					<td>Date</td>
					<td><fmt:formatDate value="${parents.student.createddate}"
							pattern="dd/MM/yyyy" /></td>
				</tr>
				<tr>
					<td>29</td>
					<td>Signature of Father/Guardian</td>
					<td></td>
				</tr>


			</table>


			<p>Note: Fill all the fields in capital letter.</p>

			<h3>For Office Use Only</h3>
			<table>
			<tr>
			<td>
			<label for="enrollmentno">Class in which admitted</label>
			<c:forEach var="splt" items="${fn:split(parents.student.classstudying,'--')}">
						    	<input style="width:50px;"
				type="text" name="enrollmentno"
				value="${splt}">	 
								</c:forEach>
			<label for="admissiondate"> Date:</label> <input type="text" name="admissiondate" style="width:100px;"> <label
				for="U-Dise">Principal's Signature</label> <input type="text"
				name="U-Dise"><br/>
				
				</td>
			</tr>
			</table>
			
			
			<h3>Application Receipt</h3>
			<table>
				<tr>
					<td>Registration No.</td>
					<td></td>
				</tr>
				<tr>
					<td>Form No.</td>
					<td></td>
				</tr>
				<tr>
					<td>Name</td>
					<td><c:out value="${parents.student.name}" /></td>
				</tr>
				<tr>
					<td>Class</td>
					<td><c:out value="${parents.student.classadmittedin}" /></td>
				</tr>
				<tr>
					<td>Date of Entrance Test</td>
					<td></td>
				</tr>
				<tr>
					<td>Time</td>
					<td></td>
				</tr>
				<tr>
					<td>Publication of Result</td>
					<td></td>
				</tr>
				<tr>
					<td>Admission I/C</td>
					<td></td>
				</tr>
			</table>
		</div>
			<button onclick="window.print()">Print</button>
	</form>


</body>
</html>
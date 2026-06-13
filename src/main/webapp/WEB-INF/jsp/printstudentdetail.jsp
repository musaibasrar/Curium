<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%--
    Document   : index
    Created on : Dec 23, 2011, 5:52:28 PM
    Author     : Musaib
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/roshan/css/validation/jquery.ketchup.css">
<script type="text/javascript" src="/roshan/js/datePicker/jquery-1.7.1.js"></script>
<script type="text/javascript"
	src="/roshan/js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
<script type="text/javascript" language="javascript"
	src="/roshan/js/dataTable/jquery.dataTables.js"></script>
<script type="text/javascript" src="/roshan/js/datePicker/ui/jquery.ui.core.js"></script>
<script type="text/javascript"
	src="/roshan/js/datePicker/ui/jquery.ui.widget.js"></script>
<script type="text/javascript"
	src="/roshan/js/datePicker/ui/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="/roshan/js/datePicker/ui/jquery.ui.tabs.js"></script>
<script type="text/javascript" src="/roshan/js/datePicker/ui/sliderAccess.js"></script>

<script type="text/javascript"
	src="/roshan/js/validation/jquery.ketchup.all.min.js"></script>
<script type="text/javascript"
	src="/roshan/js/datePicker/ui/jquery.ui.button.js"></script>
<script type="text/javascript"
	src="/roshan/js/datePicker/ui/jquery.ui.accordion.js"></script>
<script type="text/javascript"
	src="/roshan/js/datePicker/ui/jquery.effects.core.js"></script>
<script type="text/javascript"
	src="/roshan/js/datePicker/ui/jquery.ui.accordion.js"></script>
<script type="text/javascript"
	src="/roshan/js/datePicker/ui/jquery.effects.slide.js"></script>
<script type="text/javascript"
	src="/roshan/js/datePicker/ui/jquery.effects.bounce.js"></script>
<script type="text/javascript"
	src="/roshan/js/datePicker/ui/jquery.effects.clip.js"></script>
<script type="text/javascript"
	src="/roshan/js/datePicker/ui/jquery.effects.transfer.js"></script>
<script type="text/javascript"
	src="/roshan/js/datePicker/ui/jquery.effects.blind.js"></script>
<script type="text/javascript"
	src="/roshan/js/datePicker/ui/ScrollableGridPlugin.js"></script>
<title>print student detail</title>
<style>
table, th, td{
text-align:center;
border:1px solid black;
border-collapse:collapse;
}
</style>
<script type="text/javascript">
                       
		window.onload = function(){
		window.print();
		}
        </script>
</head>
<body>
			
			<table width="100%" border="0" style="border-color: #4b6a84;"
				id="myTable">

				<thead>
					<tr>
						<th class="headerText">Sl.No</th>
						<th title="click to sort" class="headerText">Admission Number</th>
						<th title="click to sort" class="headerText">Name</th>
						<th title="click to sort" class="headerText">Class &
							Sec&nbsp;</th>
						<th title="click to sort" class="headerText">Father Name</th>
						<th title="click to sort" class="headerText">Contact Number</th>
						
						<th title="click to sort" class="headerText">pen</th>
						<th title="click to sort" class="headerText">apaarid</th>



					</tr>
				</thead>

				<tbody>
					<c:forEach items="${searchStudentList}" var="Parents" varStatus="status">

						<tr class="trClass" style="border-color: #000000" border="1"
							cellpadding="1" cellspacing="1">
								<td class="dataText">${status.index+1}</td>
							<td class="dataText"><c:out value='${Parents.student.sid}'/><c:out
										value="${Parents.student.admissionnumber}" /></td>
							<td class="dataText"><c:out value="${Parents.student.name}" /></td>
							<td class="dataText"><c:out
									value="${Parents.student.classstudying}" /></td>
							<td class="dataText"><c:out value="${Parents.fathersname}" /></td>
							<td class="dataText"><c:out value="${Parents.contactnumber}" /></td>
							<td class="dataText"><c:out
									value="${Parents.student.accno}" /></td>
									<td class="dataText"><c:out
									value="${Parents.student.bankifsc}" /></td>
									
						</tr>
					</c:forEach>
				</tbody>
				
			</table>


</body>
</html>
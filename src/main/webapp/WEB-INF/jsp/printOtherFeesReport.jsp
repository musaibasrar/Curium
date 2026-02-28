<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/shatabdi/css/validation/jquery.ketchup.css">
<script type="text/javascript" src="/shatabdi/js/datePicker/jquery-1.7.1.js"></script>
<script type="text/javascript"
	src="/shatabdi/js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
<script type="text/javascript" language="javascript"
	src="/shatabdi/js/dataTable/jquery.dataTables.js"></script>
<script type="text/javascript" src="/shatabdi/js/datePicker/ui/jquery.ui.core.js"></script>
<script type="text/javascript"
	src="/shatabdi/js/datePicker/ui/jquery.ui.widget.js"></script>
<script type="text/javascript"
	src="/shatabdi/js/datePicker/ui/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="/shatabdi/js/datePicker/ui/jquery.ui.tabs.js"></script>


<script type="text/javascript"
	src="/shatabdi/js/datePicker/ui/jquery.ui.button.js"></script>
<script type="text/javascript"
	src="/shatabdi/js/datePicker/ui/jquery.ui.accordion.js"></script>
<script type="text/javascript"
	src="/shatabdi/js/datePicker/ui/jquery.effects.core.js"></script>
<script type="text/javascript"
	src="/shatabdi/js/datePicker/ui/jquery.ui.accordion.js"></script>
<script type="text/javascript"
	src="/shatabdi/js/datePicker/ui/jquery.effects.slide.js"></script>
<script type="text/javascript"
	src="/shatabdi/js/datePicker/ui/jquery.effects.bounce.js"></script>
<script type="text/javascript"
	src="/shatabdi/js/datePicker/ui/jquery.effects.clip.js"></script>
<script type="text/javascript"
	src="/shatabdi/js/datePicker/ui/jquery.effects.transfer.js"></script>
<script type="text/javascript"
	src="/shatabdi/js/datePicker/ui/jquery.effects.blind.js"></script>
<style>
th,td,tr,table{
border:1px solid black;
border-collapse:collapse;
}

</style>
</head>
<body onload="window.print();">
			<table width="100%" style="border:0px;">
				<tr style="border:0px;">
					<td align="center" style="border:0px;"><img src="/shatabdi/images/shatabdi.png" width="60" height="72"/></td><td class="headerTD" align="center" style="border:0px;font-size:40px;font-weight:bold;">${branchname}</td>
				</tr>
				<tr style="border:0px;">
					<td colspan="2" class="headerTD" align="center" style="border:0px;">Other Fees Report</td>
				</tr>
			</table>
			<table width="100%" border="0" style="border-color: #4b6a84;"
				id="myTable">

				<thead>
					<tr>
						<!-- <th class="headerText"><input type="checkbox" id="chckHead" /></th> -->
						<th title="click to sort" class="headerText">Admission Number</th>
						<th title="click to sort" class="headerText">Name&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
						<th title="click to sort" class="headerText">Class & Sec&nbsp;</th>
						<th title="click to sort" class="headerText">Father Name&nbsp;</th>
						<th title="click to sort" class="headerText">Contact No.&nbsp;</th>
						<th title="click to sort" class="headerText">Fees due</th>
						<th title="click to sort" class="headerText">Fees paid</th>
						<th title="click to sort" class="headerText">Fees Total</th>
					</tr>
				</thead>

				<tbody>
					<c:set var="TotalPaidAmount" value="0" />
					<c:set var="TotalDueAmount" value="0" />
					<c:set var="TotalSum" value="0" />

					<c:forEach items="${studentotherfeesreportlist}" var="studentfeesreportlist">

						<tr class="trClass" style="border-color: #000000" border="1"
							cellpadding="1" cellspacing="1">
							<%-- <td class="dataText"><input type="checkbox"
								id="<c:out value="${studentfeesreportlist.student.sid}"/>" class="chcktbl"
								name="studentIDs"
								value="<c:out value="${studentfeesreportlist.student.sid}"/>" /></td> --%>
							<td class="dataText"><c:out
										value="${studentfeesreportlist.parents.student.admissionnumber}" /></a></td>
							<td class="dataText"><c:out value="${studentfeesreportlist.parents.student.name}" /></td>
							<td class="dataText"><c:out
									value="${studentfeesreportlist.parents.student.classstudying}" /></td>
							<td class="dataText"><c:out value="${studentfeesreportlist.parents.fathersname}" /></td>
							<td class="dataText"><c:out value="${studentfeesreportlist.parents.contactnumber}" /></td>
							<td class="dataText" align="center">
									<c:set var="DueAmount" value="0" />
									<c:set var="TotalAmount" value="0" />
								<c:forEach items="${studentfeesreportlist.studentFeesStructure}" var="studentfeescatagorydetails">
									<%-- <table>
										<tr>
											<td style="width: 160px;" align="right">
												${studentfeescatagorydetails.otherfeescategory.feescategoryname}:&nbsp;&nbsp;&nbsp;	
											</td>
											<td align="left">
												${studentfeescatagorydetails.feesamount-studentfeescatagorydetails.feespaid - studentfeescatagorydetails.concession - studentfeescatagorydetails.waiveoff}/${studentfeescatagorydetails.feesamount - studentfeescatagorydetails.concession - studentfeescatagorydetails.waiveoff}
											</td>
											<td style="width: 160px;" align="right">
												[Total Installments${studentfeescatagorydetails.totalinstallment}]&nbsp;&nbsp;&nbsp;	
											</td>
										</tr>
									</table> --%>
									<c:set var="DueAmount" value="${DueAmount+studentfeescatagorydetails.feesamount-studentfeescatagorydetails.feespaid - studentfeescatagorydetails.concession - studentfeescatagorydetails.waiveoff}" />
									<c:set var="TotalAmount" value="${TotalAmount+(studentfeescatagorydetails.feesamount - studentfeescatagorydetails.concession - studentfeescatagorydetails.waiveoff)}" />

									<c:set var="TotalPaidAmount" value="${TotalPaidAmount+studentfeescatagorydetails.feespaid}" />
									<c:set var="TotalDueAmount" value="${TotalDueAmount+(studentfeescatagorydetails.feesamount-studentfeescatagorydetails.feespaid - studentfeescatagorydetails.concession - studentfeescatagorydetails.waiveoff)}" />
									<c:set var="TotalSum" value="${TotalSum+(studentfeescatagorydetails.feesamount - studentfeescatagorydetails.concession - studentfeescatagorydetails.waiveoff)}" />
								</c:forEach>
								${DueAmount}
							</td>
							<td class="dataText">
							${TotalPaidAmount}
									<%-- <table>
										<tr>
											<td style="width: 160px;" align="right">
												${DueAmount}/${TotalAmount}&nbsp;&nbsp;&nbsp;	
											</td>
										</tr>
									</table> --%>
							</td>
							<td class="dataText">
							${TotalAmount}
							</td>
						</tr>
					</c:forEach>
				</tbody>
				<tfoot>
					<tr>

					

						<td class="footerTD" colspan="9" >

						 Total Amount: ${TotalSum}
						 &nbsp;&nbsp;&nbsp;
						 Total Paid Amount : ${TotalPaidAmount} &nbsp;&nbsp;&nbsp; Total Due Amount: ${TotalDueAmount }

						</td>



					</tr>
				</tfoot>
			</table>


</body>
</html>
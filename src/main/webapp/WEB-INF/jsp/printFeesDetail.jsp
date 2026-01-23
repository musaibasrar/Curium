<%-- 
    Document   : ${name}
    Created on : ${date}, ${time}
    Author     : ${user}
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>FEE RECEIPT / استلام الرسوم</title>
<link href="https://fonts.googleapis.com/css2?family=Noto+Naskh+Arabic:wght@400;500;600;700&display=swap" rel="stylesheet">
<style type="text/css">
    body {
        font-family: Arial, sans-serif;
        margin: 20px;
        color: #000;
    }
    
    /* Add Arabic text class */
    .arabic {
        font-family: 'Noto Naskh Arabic', Arial, sans-serif;
        direction: rtl;
        margin-left: 4px;
    }
    
    .receipt-container {
        width: 100%;
        max-width: 800px;
        margin: 0 auto;
        padding: 10px;
    }
    
    .header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-bottom: 10px;
    }
    
    .logo {
        width: 110px;
        height: 110px;
    }
    
    .qr-code {
        width: 100px;
        height: 100px;
    }
    
    .school-info {
	text-align: center;
}

    .school-name {
        font-size: 20px;
        font-weight: bold;
        margin-bottom: 5px;
    }
    
    .school-address {
        font-size: 12px;
        margin-bottom: 3px;
    }
    
    .receipt-title {
        text-align: center;
        font-size: 16px;
        font-weight: bold;
        margin: 10px 0;
    }
    
    .student-info {
        display: grid;
        grid-template-columns: repeat(2, 1fr);
        gap: 10px;
        margin-bottom: 10px;
    }
    
    .info-row {
        display: flex;
        flex-wrap: wrap;
        font-size: 14px;
        margin-bottom: 6px;
    }
    
    .fees-table {
        width: 98%;
        border-collapse: collapse;
        margin: 10px 0;
    }
    
    .fees-table th, .fees-table td {
        border: 1px solid #000;
        padding: 8px;
        text-align: center;
        font-size: 10px;
    }
    
    .fees-table th {
        background-color: #f5f5f5;
    }
    
    .amount-in-words {
        font-size: 12px;
        margin: 10px 0;
    }
    
    .footer {
        text-align: left;
        margin-top: 0px;
        font-size: 10px;
    }
    
    .arabic-text {
        font-family: 'Noto Naskh Arabic', Arial, sans-serif;
        direction: rtl;
    }

	#table1 tr,
  	#table1 td {
    	font-size: 12px; /* You can adjust to 10px or 11px if needed */
    	vertical-align: top;
    	padding: 0px;
  }
        @media print {
        body {
            margin: 0;
            padding: 10px;
        }
        
        @page {
            size: A4;
            margin: 1cm;
            }
        }
    </style>
    <style>
    .info-label {
        font-weight: bold;
        margin-right: 6px;
    }
    
    .info-block {
        width: 100%;
        margin-bottom: 4px;
    }
    .info-inline {
        display: inline-block;
        min-width: 160px;
        margin-right: 25px;
    }
    
    .headerText {
	width: 10px;
	font-family: Tahoma;
	font-size: 12px;
	color: black;
	font-weight: normal;
	width: auto;
	height: 12px;
	vertical-align: middle;
	text-align: center;
}
</style>
</head>
<body>
    <div class="receipt-container">
        <!-- Header -->
        <div class="header">
            <img src="/daralmajd/images/daralmajd.jpg" alt="School Logo" class="logo">
            <div class="school-info">
                <div class="school-name"><label style="text-transform: uppercase;">${branchname},Jeddah</label></div>
                <div class="school-address">${branchaddress}</div>
                <div class="school-address">Phone:012 6332334 Mobile: 0500457799/0500457744 <br>email: info@darmajischool.com Website:
www.daralmajdschool.com</div>
                <div class="school-address">C.B.S.E No. 6630280, License No. 520-0867</div>
                <div class="school-address">Tax Identification No.: 310141531500003 <span class="arabic">رقم الضريبة</span></div>
            </div>
            <img src="data:image;base64,<c:out value="${qrcode}"/>" alt="QR Code" class="qr-code">
        </div>

        <!-- Receipt Title -->
        <div class="receipt-title">FEE RECEIPT / <span class="arabic">استلام الرسوم</span></div>

        <!-- Receipt Details -->


		<table style="width: auto; height: auto;" border="0" align="left"
			id="table1">
			<tr>
				<td><span class="info-label">Receipt
						No. <span class="arabic">رقم الفاتورة</span>
				</span></td>
				<td>:&nbsp;&nbsp;<span>${receiptinfo.branchreceiptnumber}</span>
				
				<label class="alignLeft" style="padding-left: 120px;">
 					 <span class="info-label">Adm No.</span>&nbsp;
 					 <span class="info-label arabic" dir="rtl" style="display: inline-block;">رقم الطالب</span>&nbsp;:
 					 &nbsp;<span>${student.admissionnumber}</span>
				</label>
				
				
				<label class="alignLeft" style="padding-left: 120px;">
					  <span class="info-label">Date</span>&nbsp;
  					  <span class="info-label arabic" dir="rtl" style="display: inline-block;">التاريخ</span>&nbsp;:
  					  &nbsp;<span>${receiptdate}</span>
				</label>
				</td>
			</tr>
			
			
			<tr>
				<td><span class="info-label"><span class="info-label">Student's Name <span class="arabic">اسم الطالب</span></span>
				</span>&nbsp;</td>
				<td>:&nbsp;&nbsp;<span>${student.name}</span></td>
			</tr>
			<tr>
				<td class="alignLeft"><span class="info-label"><span class="info-label">Father's Name <span class="arabic">اسم الأب</span></span>
				</span>&nbsp;</td>
				<td>:&nbsp;&nbsp;<span>${parents.fathersname}</span></td>
			</tr>
			<tr>
				<td class="alignLeft"><span class="info-label"><span class="info-label">Mother's Name <span class="arabic">اسم الأم</span></span></td>
				<td><span>:&nbsp;&nbsp;${parents.mothersname}</span>
				
				<label class="alignLeft" style="padding-left: 200px;">
					  <span class="info-label">Iqama No.</span>&nbsp;
  					  <span class="info-label arabic" dir="rtl" style="display: inline-block;">رقم الإقامة</span>&nbsp;:
  					  &nbsp;<span>${student.bhagyalakshmibondnumber}</span>
				</label>
				</td>
			</tr>
			<tr>
				<td class="alignLeft"><span class="info-label"><span class="info-label">Class<span class="arabic">الصف</span></span></td>
				<td>:&nbsp;&nbsp;<span>${student.classstudying}</span>
				<label class="alignLeft" style="padding-left: 250px;font-weight: bold;">Payment Mode<span class="arabic" style="font-weight: bold;">طريقة الدفع<span></span><span>:&nbsp;&nbsp;${receiptinfo.paymenttype}</span>
				</label>
				</td>
			</tr>
			<tr>
				<td><span class="info-label"><span class="info-label">Fees For Term(s) <!-- <span class="arabic">اسم الطالب</span></span> -->
				</span></td>
				<td>:&nbsp;&nbsp;<span>
				<c:forEach items="${feesMonth}" var="feemonth" varStatus="status">
					${feemonth}<c:if test="${!status.last}">, </c:if>
				</c:forEach>
				</span></td>
			</tr>

		</table>
      
        <!-- Fees Table -->
        <table class="fees-table">
            <thead>
                <tr>
                    <th>Particulars <span class="arabic"><br>البيان</span></th>
                    <th>Fee Paid <span class="arabic">قيمة الرسوم<br></span></th>
                    <!-- <th>Discount Amount <span class="arabic">مبلغ الخصم<br></span></th>
                    <th>Amount <br>(After Discount) <span class="arabic"><br>المبلغ بعد الخصم</span></th> -->
                    <th>VAT (15%) <span class="arabic"><br>نسبة الضريبة</span></th>
                    <th>Net Amount<br>(Incl. VAT) <span class="arabic"><br>المجموع شامل الضريبة</span></th>
                </tr>
            </thead>
            <tbody>
            <c:set var="itemTotal" value="${0}" />
            <c:set var="itemTotalDisc" value="${0}" />
            <c:set var="itemTotalAfterDisc" value="${0}" />
            <c:set var="itemVatTotal" value="${0}" />
            <c:set var="itemTotalNet" value="${0}" />
            	<c:forEach items="${feescatmap}" var="feescatmap">
            	<tr>
                    <td>
	                    <c:set var="fullName" value="${feescatmap.key.feescategory.feescategoryname}" />

							<c:choose>
								<c:when test="${fn:contains(fullName, '/')}">
									<c:set var="arabicText"
										value="${fn:substringAfter(fullName, '/')}" />
									<c:set var="englishText"
										value="${fn:substringBefore(fullName, '/')}" />
											<%-- <c:if test="${fn:contains(englishText, 'Tuition')}">
    											<c:set var="englishText" value="Tuition Fee" />
											</c:if> --%>
									<span>${englishText}</span> /
     										   <span style="font-family: 'Amiri', 'Noto Naskh Arabic', serif;">
										${arabicText} </span>
								</c:when>
								<c:otherwise>
									<span>${fullName}</span>
								</c:otherwise>
							</c:choose>

						</td>
                    <td>${feescatmap.value}
                    <c:set var="itemTotal" value="${itemTotal + feescatmap.value}" />
                    </td>
                    <%-- <td>${feescatmap.key.concession}
                    <c:set var="itemTotalDisc" value="${itemTotalDisc + feescatmap.key.concession}" />
                    </td>
                    <td>${feescatmap.key.feesamount-feescatmap.key.concession}
                    <c:set var="itemTotalAfterDisc" value="${itemTotalAfterDisc + (feescatmap.key.feesamount-feescatmap.key.concession)}" />
                    </td> --%>
                    <td>
                    	
                    <c:if test="${applyVAT==1}">

								<c:choose>
									<c:when	test="${fn:containsIgnoreCase(feescatmap.key.feescategory.feescategoryname, 'tuition fee')}">
										<fmt:formatNumber
											value="${(feescatmap.value) * 0.15}"
											type="number" maxFractionDigits="2" minFractionDigits="2" />
										<c:set var="itemVatTotal"
											value="${itemVatTotal + ((feescatmap.value) * 0.15)}" />
									</c:when>
									<c:otherwise>
           								0.00
        							</c:otherwise>
								</c:choose>

								
                    </c:if>
                    
                    <c:if test="${applyVAT==0}">
                    <c:set var="itemVatTotal" value="${0}" />
                    </c:if>
                    
                    </td>
                    <td>
                    	<c:if test="${applyVAT==1}">
                    	
                    		<c:choose>
									<c:when
										test="${fn:containsIgnoreCase(feescatmap.key.feescategory.feescategoryname, 'tuition fee')}">
										<fmt:formatNumber value="${((feescatmap.value - feescatmap.key.concession) * 0.15) + (feescatmap.value - feescatmap.key.concession)}" type="number" maxFractionDigits="2" minFractionDigits="2" />
                    		<c:set var="itemTotalNet" value="${itemTotalNet + ((feescatmap.value - feescatmap.key.concession) * 0.15) + (feescatmap.value - feescatmap.key.concession)}" />
									</c:when>
									<c:otherwise>
           								<fmt:formatNumber value="${feescatmap.value - feescatmap.key.concession}" type="number" maxFractionDigits="2" minFractionDigits="2" />
                    		<c:set var="itemTotalNet" value="${itemTotalNet + (feescatmap.value - feescatmap.key.concession)}" />
        							</c:otherwise>
								</c:choose>
                    	</c:if>	
                    	
                    	<c:if test="${applyVAT==0}">
                    		<fmt:formatNumber value="${feescatmap.value - feescatmap.key.concession}" type="number" maxFractionDigits="2" minFractionDigits="2" />
                    		<c:set var="itemTotalNet" value="${itemTotalNet + (feescatmap.value - feescatmap.key.concession)}" />
                    	</c:if>
					</td>
                </tr>
            	
            	</c:forEach>
                <c:if test="${receiptinfo.fine > 0}">
				<tr>
					<td class="dataText">Late Fee (Fine)<span class="arabic">غرامة التأخير</span></td>
					<td class="dataText"> <c:out value="${receiptinfo.fine}" />
					<c:set var="itemTotal" value="${itemTotal + receiptinfo.fine}" />
					</td>
					<td>0.00</td>
					<td> <c:out value="${receiptinfo.fine}" />
					 <c:set var="itemTotalAfterDisc" value="${itemTotalAfterDisc + (receiptinfo.fine)}" />
					</td>
					<td>
					0.00
					<%-- <fmt:formatNumber value="${receiptinfo.fine * 0.15}" type="number" maxFractionDigits="2" minFractionDigits="2" />
					<c:set var="itemVatTotal" value="${itemVatTotal + (receiptinfo.fine * 0.15)}" /> --%>
					</td>
					<td>${receiptinfo.fine}
					<c:set var="itemTotalNet" value="${itemTotalNet + receiptinfo.fine}" />
					</td>
				</tr>
			</c:if>
			<c:if test="${receiptinfo.fine == 0}">
                <tr>
                    <td>Late Fee (Fine)<span class="arabic">غرامة التأخير</span></td>
                    <td>0.00</td>
                    <td>0.00</td>
                    <td>0.00</td>
                </tr>
                </c:if>
                <tr>
                    <td>TOTAL<span class="arabic">الإجمالي</span></td>
                    <td><fmt:formatNumber value="${itemTotal}" type="number" maxFractionDigits="2" minFractionDigits="2" /></td>
                    <%-- <td><fmt:formatNumber value="${itemTotalDisc}" type="number" maxFractionDigits="2" minFractionDigits="2" /></td>
                    <td><fmt:formatNumber value="${itemTotalAfterDisc}" type="number" maxFractionDigits="2" minFractionDigits="2" /></td> --%>
                    <td><fmt:formatNumber value="${itemVatTotal}" type="number" maxFractionDigits="2" minFractionDigits="2" /></td>
                    <td><fmt:formatNumber value="${itemTotalNet}" type="number" maxFractionDigits="2" minFractionDigits="2" /></td>
                </tr>
            </tbody>
			</table>
			
        <!-- Amount in Words -->
        <div class="amount-in-words">
            Net Amount Payable in words: SAR <label style="text-transform: capitalize;">${grandTotal}</label>
        </div>
        
        <TABLE width="100%" border="0" cellspacing="0" cellpadding="0" style="border-collapse: collapse;">
			 <tr style="line-height: 1;">
			
			<td><hr width="100%"></td>
			<td><hr width="100%"></td>
			<td><hr width="100%"></td>
			</tr>

			<tr style="line-height: 1;">
				<td class="headerText">
					Total Fees: SR. <fmt:formatNumber value="${totalfees}" type="number" minFractionDigits="2" maxFractionDigits="2"/>
				</td>
				<td class="headerText">
					Total fees paid : SR.  <fmt:formatNumber value="${sumoffees}" type="number" minFractionDigits="2" maxFractionDigits="2"/>
				</td>
				<td class="headerText">
				 Total fees Due : SR.  <fmt:formatNumber value="${dueamount}" type="number" minFractionDigits="2" maxFractionDigits="2"/> 
				</td>
				
			</tr style="line-height: 1;"> 
			
			<tr>
			<td><hr width="100%"></td>
			<td><hr width="100%"></td>
			<td><hr width="100%"></td>
			</tr>
			</table>

        <!-- Footer -->
        <div class="footer">
        	<label class="alignLeft">
 					 <span class="info-label" style="font-weight: normal;">Remarks</span>&nbsp;
 					 <span class="info-label arabic" dir="rtl" style="display: inline-block;">ملاحظات</span>&nbsp;:
 					 &nbsp;
				</label><br>
            Receipt generated by ${username}
		</div>
	</div><br><br>
	    <div class="receipt-container">
        <!-- Header -->
        <div class="header">
            <img src="/daralmajd/images/daralmajd.jpg" alt="School Logo" class="logo">
            <div class="school-info">
                <div class="school-name"><label style="text-transform: uppercase;">${branchname},Jeddah</label></div>
                <div class="school-address">${branchaddress}</div>
                <div class="school-address">Phone:012 6332334 Mobile: 0500457799/0500457744 <br>email: info@darmajischool.com Website:
www.daralmajdschool.com</div>
                <div class="school-address">C.B.S.E No. 6630280, License No. 520-0867</div>
                <div class="school-address">Tax Identification No.: 310141531500003 <span class="arabic">رقم الضريبة</span></div>
            </div>
            <img src="data:image;base64,<c:out value="${qrcode}"/>" alt="QR Code" class="qr-code">
        </div>

        <!-- Receipt Title -->
        <div class="receipt-title">FEE RECEIPT / <span class="arabic">استلام الرسوم</span></div>

        <!-- Receipt Details -->


		<table style="width: auto; height: auto;" border="0" align="left"
			id="table1">
			<tr>
				<td><span class="info-label">Receipt
						No. <span class="arabic">رقم الفاتورة</span>
				</span></td>
				<td>:&nbsp;&nbsp;<span>${receiptinfo.branchreceiptnumber}</span>
				
				<label class="alignLeft" style="padding-left: 120px;">
 					 <span class="info-label">Adm No.</span>&nbsp;
 					 <span class="info-label arabic" dir="rtl" style="display: inline-block;">رقم الطالب</span>&nbsp;:
 					 &nbsp;<span>${student.admissionnumber}</span>
				</label>
				
				
				<label class="alignLeft" style="padding-left: 120px;">
					  <span class="info-label">Date</span>&nbsp;
  					  <span class="info-label arabic" dir="rtl" style="display: inline-block;">التاريخ</span>&nbsp;:
  					  &nbsp;<span>${receiptdate}</span>
				</label>
				</td>
			</tr>
			
			
			<tr>
				<td><span class="info-label"><span class="info-label">Student's Name <span class="arabic">اسم الطالب</span></span>
				</span>&nbsp;</td>
				<td>:&nbsp;&nbsp;<span>${student.name}</span></td>
			</tr>
			<tr>
				<td class="alignLeft"><span class="info-label"><span class="info-label">Father's Name <span class="arabic">اسم الأب</span></span>
				</span>&nbsp;</td>
				<td>:&nbsp;&nbsp;<span>${parents.fathersname}</span></td>
			</tr>
			<tr>
				<td class="alignLeft"><span class="info-label"><span class="info-label">Mother's Name <span class="arabic">اسم الأم</span></span></td>
				<td><span>:&nbsp;&nbsp;${parents.mothersname}</span>
				
				<label class="alignLeft" style="padding-left: 200px;">
					  <span class="info-label">Iqama No.</span>&nbsp;
  					  <span class="info-label arabic" dir="rtl" style="display: inline-block;">رقم الإقامة</span>&nbsp;:
  					  &nbsp;<span>${student.bhagyalakshmibondnumber}</span>
				</label>
				</td>
			</tr>
			<tr>
				<td class="alignLeft"><span class="info-label"><span class="info-label">Class<span class="arabic">الصف</span></span></td>
				<td>:&nbsp;&nbsp;<span>${student.classstudying}</span>
				<label class="alignLeft" style="padding-left: 250px;font-weight: bold;">Payment Mode<span class="arabic" style="font-weight: bold;">طريقة الدفع<span></span><span>:&nbsp;&nbsp;${receiptinfo.paymenttype}</span>
				</label>
				</td>
			</tr>
			<tr>
				<td><span class="info-label"><span class="info-label">Fees For Term(s) <!-- <span class="arabic">اسم الطالب</span></span> -->
				</span></td>
				<td>:&nbsp;&nbsp;<span>
				<c:forEach items="${feesMonth}" var="feemonth" varStatus="status">
					${feemonth}<c:if test="${!status.last}">, </c:if>
				</c:forEach>
				</span></td>
			</tr>

		</table>
      
        <!-- Fees Table -->
        <table class="fees-table">
            <thead>
                <tr>
                    <th>Particulars <span class="arabic"><br>البيان</span></th>
                    <th>Fee Paid <span class="arabic">قيمة الرسوم<br></span></th>
                    <!-- <th>Discount Amount <span class="arabic">مبلغ الخصم<br></span></th>
                    <th>Amount <br>(After Discount) <span class="arabic"><br>المبلغ بعد الخصم</span></th> -->
                    <th>VAT (15%) <span class="arabic"><br>نسبة الضريبة</span></th>
                    <th>Net Amount<br>(Incl. VAT) <span class="arabic"><br>المجموع شامل الضريبة</span></th>
                </tr>
            </thead>
            <tbody>
            <c:set var="itemTotal" value="${0}" />
            <c:set var="itemTotalDisc" value="${0}" />
            <c:set var="itemTotalAfterDisc" value="${0}" />
            <c:set var="itemVatTotal" value="${0}" />
            <c:set var="itemTotalNet" value="${0}" />
            	<c:forEach items="${feescatmap}" var="feescatmap">
            	<tr>
                    <td>
	                    <c:set var="fullName" value="${feescatmap.key.feescategory.feescategoryname}" />

							<c:choose>
								<c:when test="${fn:contains(fullName, '/')}">
									<c:set var="arabicText"
										value="${fn:substringAfter(fullName, '/')}" />
									<c:set var="englishText"
										value="${fn:substringBefore(fullName, '/')}" />
											<%-- <c:if test="${fn:contains(englishText, 'Tuition')}">
    											<c:set var="englishText" value="Tuition Fee" />
											</c:if> --%>
									<span>${englishText}</span> /
     										   <span style="font-family: 'Amiri', 'Noto Naskh Arabic', serif;">
										${arabicText} </span>
								</c:when>
								<c:otherwise>
									<span>${fullName}</span>
								</c:otherwise>
							</c:choose>

						</td>
                    <td>${feescatmap.value}
                    <c:set var="itemTotal" value="${itemTotal + feescatmap.value}" />
                    </td>
                    <%-- <td>${feescatmap.key.concession}
                    <c:set var="itemTotalDisc" value="${itemTotalDisc + feescatmap.key.concession}" />
                    </td>
                    <td>${feescatmap.key.feesamount-feescatmap.key.concession}
                    <c:set var="itemTotalAfterDisc" value="${itemTotalAfterDisc + (feescatmap.key.feesamount-feescatmap.key.concession)}" />
                    </td> --%>
                    <td>
                    	
                    <c:if test="${applyVAT==1}">

								<c:choose>
									<c:when	test="${fn:containsIgnoreCase(feescatmap.key.feescategory.feescategoryname, 'tuition fee')}">
										<fmt:formatNumber
											value="${(feescatmap.value) * 0.15}"
											type="number" maxFractionDigits="2" minFractionDigits="2" />
										<c:set var="itemVatTotal"
											value="${itemVatTotal + ((feescatmap.value) * 0.15)}" />
									</c:when>
									<c:otherwise>
           								0.00
        							</c:otherwise>
								</c:choose>

								
                    </c:if>
                    
                    <c:if test="${applyVAT==0}">
                    <c:set var="itemVatTotal" value="${0}" />
                    </c:if>
                    
                    </td>
                    <td>
                    	<c:if test="${applyVAT==1}">
                    	
                    		<c:choose>
									<c:when
										test="${fn:containsIgnoreCase(feescatmap.key.feescategory.feescategoryname, 'tuition fee')}">
										<fmt:formatNumber value="${((feescatmap.value - feescatmap.key.concession) * 0.15) + (feescatmap.value - feescatmap.key.concession)}" type="number" maxFractionDigits="2" minFractionDigits="2" />
                    		<c:set var="itemTotalNet" value="${itemTotalNet + ((feescatmap.value - feescatmap.key.concession) * 0.15) + (feescatmap.value - feescatmap.key.concession)}" />
									</c:when>
									<c:otherwise>
           								<fmt:formatNumber value="${feescatmap.value - feescatmap.key.concession}" type="number" maxFractionDigits="2" minFractionDigits="2" />
                    		<c:set var="itemTotalNet" value="${itemTotalNet + (feescatmap.value - feescatmap.key.concession)}" />
        							</c:otherwise>
								</c:choose>
                    	</c:if>	
                    	
                    	<c:if test="${applyVAT==0}">
                    		<fmt:formatNumber value="${feescatmap.value - feescatmap.key.concession}" type="number" maxFractionDigits="2" minFractionDigits="2" />
                    		<c:set var="itemTotalNet" value="${itemTotalNet + (feescatmap.value - feescatmap.key.concession)}" />
                    	</c:if>
					</td>
                </tr>
            	
            	</c:forEach>
                <c:if test="${receiptinfo.fine > 0}">
				<tr>
					<td class="dataText">Late Fee (Fine)<span class="arabic">غرامة التأخير</span></td>
					<td class="dataText"> <c:out value="${receiptinfo.fine}" />
					<c:set var="itemTotal" value="${itemTotal + receiptinfo.fine}" />
					</td>
					<td>0.00</td>
					<td> <c:out value="${receiptinfo.fine}" />
					 <c:set var="itemTotalAfterDisc" value="${itemTotalAfterDisc + (receiptinfo.fine)}" />
					</td>
					<td>
					0.00
					<%-- <fmt:formatNumber value="${receiptinfo.fine * 0.15}" type="number" maxFractionDigits="2" minFractionDigits="2" />
					<c:set var="itemVatTotal" value="${itemVatTotal + (receiptinfo.fine * 0.15)}" /> --%>
					</td>
					<td>${receiptinfo.fine}
					<c:set var="itemTotalNet" value="${itemTotalNet + receiptinfo.fine}" />
					</td>
				</tr>
			</c:if>
			<c:if test="${receiptinfo.fine == 0}">
                <tr>
                    <td>Late Fee (Fine)<span class="arabic">غرامة التأخير</span></td>
                    <td>0.00</td>
                    <td>0.00</td>
                    <td>0.00</td>
                </tr>
                </c:if>
                <tr>
                    <td>TOTAL<span class="arabic">الإجمالي</span></td>
                    <td><fmt:formatNumber value="${itemTotal}" type="number" maxFractionDigits="2" minFractionDigits="2" /></td>
                    <%-- <td><fmt:formatNumber value="${itemTotalDisc}" type="number" maxFractionDigits="2" minFractionDigits="2" /></td>
                    <td><fmt:formatNumber value="${itemTotalAfterDisc}" type="number" maxFractionDigits="2" minFractionDigits="2" /></td> --%>
                    <td><fmt:formatNumber value="${itemVatTotal}" type="number" maxFractionDigits="2" minFractionDigits="2" /></td>
                    <td><fmt:formatNumber value="${itemTotalNet}" type="number" maxFractionDigits="2" minFractionDigits="2" /></td>
                </tr>
            </tbody>
			</table>
			
        <!-- Amount in Words -->
        <div class="amount-in-words">
            Net Amount Payable in words: SAR <label style="text-transform: capitalize;">${grandTotal}</label>
        </div>
        
        <TABLE width="100%" border="0" cellspacing="0" cellpadding="0" style="border-collapse: collapse;">
			 <tr style="line-height: 1;">
			
			<td><hr width="100%"></td>
			<td><hr width="100%"></td>
			<td><hr width="100%"></td>
			</tr>

			<tr style="line-height: 1;">
				<td class="headerText">
					Total Fees: SR. ${totalfees}
				</td>
				<td class="headerText">
					Total fees paid : SR.  ${sumoffees}
				</td>
				<td class="headerText">
				 Total fees Due : SR.  ${dueamount} 
				</td>
				
			</tr style="line-height: 1;"> 
			
			<tr>
			<td><hr width="100%"></td>
			<td><hr width="100%"></td>
			<td><hr width="100%"></td>
			</tr>
			</table>

        <!-- Footer -->
        <div class="footer">
        	<label class="alignLeft">
 					 <span class="info-label" style="font-weight: normal;">Remarks</span>&nbsp;
 					 <span class="info-label arabic" dir="rtl" style="display: inline-block;">ملاحظات</span>&nbsp;:
 					 &nbsp;
				</label><br>
            Receipt generated by ${username}
		</div>
	</div>
</body>
</html>

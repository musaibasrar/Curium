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
                <div class="school-address">${branchcontact}</div>
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
				<td>:&nbsp;&nbsp;<span>${recieptinfo.branchreceiptnumber}</span>
				
				<label class="alignLeft" style="padding-left: 10px;">
 					 <span class="info-label">Adm No.</span>&nbsp;
 					 <span class="info-label arabic" dir="rtl" style="display: inline-block;">رقم الطالب</span>&nbsp;:
 					 &nbsp;<span>${student.admissionnumber}</span>
				</label>
				
				
				<label class="alignLeft" style="padding-left: 10px;">
					  <span class="info-label">Date</span>&nbsp;
  					  <span class="info-label arabic" dir="rtl" style="display: inline-block;">التاريخ</span>&nbsp;:
  					  &nbsp;<span>${recieptdate}</span>
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
				<td>:<span>${parents.mothersname}</span></td>
			</tr>
			<tr>
				<td class="alignLeft"><span class="info-label"><span class="info-label">Class<span class="arabic">الصف</span></span></td>
				<td>:&nbsp;&nbsp;<span>${student.classstudying}</span>
				<label class="alignLeft" style="padding-left: 10px;font-weight: bold;">Iqama No.<span class="arabic" style="font-weight: bold;">رقم الإقامة</span></span>:&nbsp;&nbsp;<span>${student.bhagyalakshmibondnumber}</span></label>
				</td>
			</tr>

		</table>
      
        <!-- Fees Table -->
        <table class="fees-table">
            <thead>
                <tr>
                    <th>Particulars <span class="arabic"><br>البيان</span></th>
                    <th>Fee Amount <span class="arabic">قيمة الرسوم<br></span></th>
                    <th>Discount Amount <span class="arabic">مبلغ الخصم<br></span></th>
                    <th>Amount <br>(After Discount) <span class="arabic"><br>المبلغ بعد الخصم</span></th>
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
						<c:set var="arabicText" value="${fn:substringAfter(fullName, '/')}"/>
						<c:set var="englishText" value="${fn:substringBefore(fullName, '/')}" />
						<span>${englishText}</span> /
						<span style="font-family: 'Amiri', 'Noto Naskh Arabic', serif;">
    						${arabicText}
						</span>
                    </td>
                    <td>${feescatmap.key.feesamount}
                    <c:set var="itemTotal" value="${itemTotal + feescatmap.key.feesamount}" />
                    </td>
                    <td>${feescatmap.key.concession}
                    <c:set var="itemTotalDisc" value="${itemTotalDisc + feescatmap.key.concession}" />
                    </td>
                    <td>${feescatmap.key.feesamount-feescatmap.key.concession}
                    <c:set var="itemTotalAfterDisc" value="${itemTotalAfterDisc + (feescatmap.key.feesamount-feescatmap.key.concession)}" />
                    </td>
                    <td>
                    	
                    <c:if test="${applyVAT==1}">	
                    <fmt:formatNumber value="${(feescatmap.key.feesamount-feescatmap.key.concession) * 0.15}" type="number" maxFractionDigits="2" minFractionDigits="2" />
                    <c:set var="itemVatTotal" value="${itemVatTotal + ((feescatmap.key.feesamount - feescatmap.key.concession) * 0.15)}" />
                    </c:if>
                    
                    <c:if test="${applyVAT==0}">
                    <c:set var="itemVatTotal" value="${0}" />
                    </c:if>
                    
                    </td>
                    <td>
                    	<c:if test="${applyVAT==1}">
                    		<fmt:formatNumber value="${((feescatmap.key.feesamount - feescatmap.key.concession) * 0.15) + (feescatmap.key.feesamount - feescatmap.key.concession)}" type="number" maxFractionDigits="2" minFractionDigits="2" />
                    		<c:set var="itemTotalNet" value="${itemTotalNet + ((feescatmap.key.feesamount - feescatmap.key.concession) * 0.15) + (feescatmap.key.feesamount - feescatmap.key.concession)}" />
                    	</c:if>	
                    	
                    	<c:if test="${applyVAT==0}">
                    		<fmt:formatNumber value="${feescatmap.key.feesamount - feescatmap.key.concession}" type="number" maxFractionDigits="2" minFractionDigits="2" />
                    		<c:set var="itemTotalNet" value="${itemTotalNet + (feescatmap.key.feesamount - feescatmap.key.concession)}" />
                    	</c:if>
					</td>
                </tr>
            	
            	</c:forEach>
                <c:if test="${recieptinfo.fine > 0}">
				<tr>
					<td class="dataText">Late Fee (Fine)<span class="arabic">غرامة التأخير</span></td>
					<td class="dataText"> <c:out value="${recieptinfo.fine}" />
					<c:set var="itemTotal" value="${itemTotal + recieptinfo.fine}" />
					</td>
					<td>0.00</td>
					<td> <c:out value="${recieptinfo.fine}" />
					 <c:set var="itemTotalAfterDisc" value="${itemTotalAfterDisc + (recieptinfo.fine)}" />
					</td>
					<td>
					<fmt:formatNumber value="${recieptinfo.fine * 0.15}" type="number" maxFractionDigits="2" minFractionDigits="2" />
					<c:set var="itemVatTotal" value="${itemVatTotal + (recieptinfo.fine * 0.15)}" />
					</td>
					<td>${recieptinfo.fine + (recieptinfo.fine *0.15)}
					<c:set var="itemTotalNet" value="${itemTotalNet + (recieptinfo.fine + (recieptinfo.fine *0.15))}" />
					</td>
				</tr>
			</c:if>
			<c:if test="${recieptinfo.fine == 0}">
                <tr>
                    <td>Late Fee (Fine)<span class="arabic">غرامة التأخير</span></td>
                    <td>0.00</td>
                    <td>0.00</td>
                    <td>0.00</td>
                    <td>0.00</td>
                    <td>0.00</td>
                </tr>
                </c:if>
                <tr>
                    <td>TOTAL<span class="arabic">الإجمالي</span></td>
                    <td><fmt:formatNumber value="${itemTotal}" type="number" maxFractionDigits="2" minFractionDigits="2" /></td>
                    <td><fmt:formatNumber value="${itemTotalDisc}" type="number" maxFractionDigits="2" minFractionDigits="2" /></td>
                    <td><fmt:formatNumber value="${itemTotalAfterDisc}" type="number" maxFractionDigits="2" minFractionDigits="2" /></td>
                    <td><fmt:formatNumber value="${itemVatTotal}" type="number" maxFractionDigits="2" minFractionDigits="2" /></td>
                    <td><fmt:formatNumber value="${itemTotalNet}" type="number" maxFractionDigits="2" minFractionDigits="2" /></td>
                </tr>
            </tbody>
			</table>
			
        <!-- Amount in Words -->
        <div class="amount-in-words">
            Net Amount Payable in words: SAR <label style="text-transform: capitalize;">${grandTotal}</label>
        </div>

        <!-- Footer -->
        <div class="footer">
        	<label class="alignLeft">
 					 <span class="info-label" style="font-weight: normal;">Payment Mode</span>&nbsp;
 					 <span class="info-label arabic" dir="rtl" style="display: inline-block;">طريقة الدفع</span>&nbsp;:
 					 &nbsp;<span>: &nbsp;${recieptinfo.paymenttype}</span>
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
                <div class="school-address">${branchcontact}</div>
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
				<td>:&nbsp;&nbsp;<span>${recieptinfo.branchreceiptnumber}</span>
				
				<label class="alignLeft" style="padding-left: 10px;">
 					 <span class="info-label">Adm No.</span>&nbsp;
 					 <span class="info-label arabic" dir="rtl" style="display: inline-block;">رقم الطالب</span>&nbsp;:
 					 &nbsp;<span>${student.admissionnumber}</span>
				</label>
				
				
				<label class="alignLeft" style="padding-left: 10px;">
					  <span class="info-label">Date</span>&nbsp;
  					  <span class="info-label arabic" dir="rtl" style="display: inline-block;">التاريخ</span>&nbsp;:
  					  &nbsp;<span>${recieptdate}</span>
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
				<td>:<span>${parents.mothersname}</span></td>
			</tr>
			<tr>
				<td class="alignLeft"><span class="info-label"><span class="info-label">Class<span class="arabic">الصف</span></span></td>
				<td>:&nbsp;&nbsp;<span>${student.classstudying}</span>
				<label class="alignLeft" style="padding-left: 10px;font-weight: bold;">Iqama No.<span class="arabic" style="font-weight: bold;">رقم الإقامة</span></span>:&nbsp;&nbsp;<span>${student.bhagyalakshmibondnumber}</span></label>
				</td>
			</tr>

		</table>
      
        <!-- Fees Table -->
        <table class="fees-table">
            <thead>
                <tr>
                    <th>Particulars <span class="arabic"><br>البيان</span></th>
                    <th>Fee Amount <span class="arabic">قيمة الرسوم<br></span></th>
                    <th>Discount Amount <span class="arabic">مبلغ الخصم<br></span></th>
                    <th>Amount <br>(After Discount) <span class="arabic"><br>المبلغ بعد الخصم</span></th>
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
						<c:set var="arabicText" value="${fn:substringAfter(fullName, '/')}"/>
						<c:set var="englishText" value="${fn:substringBefore(fullName, '/')}" />
						<span>${englishText}</span> /
						<span style="font-family: 'Amiri', 'Noto Naskh Arabic', serif;">
    						${arabicText}
						</span>
                    </td>
                    <td>${feescatmap.key.feesamount}
                    <c:set var="itemTotal" value="${itemTotal + feescatmap.key.feesamount}" />
                    </td>
                    <td>${feescatmap.key.concession}
                    <c:set var="itemTotalDisc" value="${itemTotalDisc + feescatmap.key.concession}" />
                    </td>
                    <td>${feescatmap.key.feesamount-feescatmap.key.concession}
                    <c:set var="itemTotalAfterDisc" value="${itemTotalAfterDisc + (feescatmap.key.feesamount-feescatmap.key.concession)}" />
                    </td>
                    <td>
                    <fmt:formatNumber value="${(feescatmap.key.feesamount-feescatmap.key.concession) * 0.15}" type="number" maxFractionDigits="2" minFractionDigits="2" />
                    <c:set var="itemVatTotal" value="${itemVatTotal + ((feescatmap.key.feesamount - feescatmap.key.concession) * 0.15)}" />
                    </td>
                    <td>
                    <fmt:formatNumber value="${((feescatmap.key.feesamount - feescatmap.key.concession) * 0.15) + (feescatmap.key.feesamount - feescatmap.key.concession)}" type="number" maxFractionDigits="2" minFractionDigits="2" />
                    <c:set var="itemTotalNet" value="${itemTotalNet + ((feescatmap.key.feesamount - feescatmap.key.concession) * 0.15) + (feescatmap.key.feesamount - feescatmap.key.concession)}" />
</td>
                </tr>
            	
            	</c:forEach>
                <c:if test="${recieptinfo.fine > 0}">
				<tr>
					<td class="dataText">Late Fee (Fine)<span class="arabic">غرامة التأخير</span></td>
					<td class="dataText"> <c:out value="${recieptinfo.fine}" />
					<c:set var="itemTotal" value="${itemTotal + recieptinfo.fine}" />
					</td>
					<td>0.00</td>
					<td> <c:out value="${recieptinfo.fine}" />
					 <c:set var="itemTotalAfterDisc" value="${itemTotalAfterDisc + (recieptinfo.fine)}" />
					</td>
					<td>
					<fmt:formatNumber value="${recieptinfo.fine * 0.15}" type="number" maxFractionDigits="2" minFractionDigits="2" />
					<c:set var="itemVatTotal" value="${itemVatTotal + (recieptinfo.fine * 0.15)}" />
					</td>
					<td>${recieptinfo.fine + (recieptinfo.fine *0.15)}
					<c:set var="itemTotalNet" value="${itemTotalNet + (recieptinfo.fine + (recieptinfo.fine *0.15))}" />
					</td>
				</tr>
			</c:if>
			<c:if test="${recieptinfo.fine == 0}">
                <tr>
                    <td>Late Fee (Fine)<span class="arabic">غرامة التأخير</span></td>
                    <td>0.00</td>
                    <td>0.00</td>
                    <td>0.00</td>
                    <td>0.00</td>
                    <td>0.00</td>
                </tr>
                </c:if>
                <tr>
                    <td>TOTAL<span class="arabic">الإجمالي</span></td>
                    <td><fmt:formatNumber value="${itemTotal}" type="number" maxFractionDigits="2" minFractionDigits="2" /></td>
                    <td><fmt:formatNumber value="${itemTotalDisc}" type="number" maxFractionDigits="2" minFractionDigits="2" /></td>
                    <td><fmt:formatNumber value="${itemTotalAfterDisc}" type="number" maxFractionDigits="2" minFractionDigits="2" /></td>
                    <td><fmt:formatNumber value="${itemVatTotal}" type="number" maxFractionDigits="2" minFractionDigits="2" /></td>
                    <td><fmt:formatNumber value="${itemTotalNet}" type="number" maxFractionDigits="2" minFractionDigits="2" /></td>
                </tr>
            </tbody>
			</table>
			
        <!-- Amount in Words -->
        <div class="amount-in-words">
            Net Amount Payable in words: SAR <label style="text-transform: capitalize;">${grandTotal}</label>
        </div>

        <!-- Footer -->
        <div class="footer">
        	<label class="alignLeft">
 					 <span class="info-label" style="font-weight: normal;">Payment Mode</span>&nbsp;
 					 <span class="info-label arabic" dir="rtl" style="display: inline-block;">طريقة الدفع</span>&nbsp;:
 					 &nbsp;<span>: &nbsp;${recieptinfo.paymenttype}</span>
				</label><br>
            Receipt generated by ${username}
		</div>
	</div>
</body>
</html>

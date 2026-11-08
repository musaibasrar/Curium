<%-- 
    Document   : Student Details Fees Structure
    Created on : Jan 4, 2013, 4:39:24 PM
    Author     : Musaib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Student Details Fees Structure</title>

        <script type="text/javascript" language="JavaScript" src="/greatindiaacademy/js/motionpack.js"></script>
        <link rel="stylesheet" href="/greatindiaacademy/css/datePicker/jquery-ui-1.8.18.custom.css">
        <link rel="stylesheet" href="/greatindiaacademy/css/graph/jquery.jqplot.css">

        <link rel="stylesheet" href="/greatindiaacademy/css/datePicker/demos.css">
        <script type="text/javascript" src="/greatindiaacademy/js/datePicker/jquery-1.7.1.js"></script>
        <script type="text/javascript" src="/greatindiaacademy/js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
        <script type="text/javascript" src="/greatindiaacademy/js/datePicker/ui/jquery.ui.dialog.js"></script>
        <script type="text/javascript" src="/greatindiaacademy/js/datePicker/ui/jquery.ui.autocomplete.js"></script>
        <script type="text/javascript" src="/greatindiaacademy/js/datePicker/ui/jquery.ui.core.js"></script>
        <script type="text/javascript" src="/greatindiaacademy/js/datePicker/ui/jquery.ui.widget.js"></script>
        <script type="text/javascript" src="/greatindiaacademy/js/datePicker/ui/jquery.ui.datepicker.js"></script>
        <script type="text/javascript" src="/greatindiaacademy/js/datePicker/ui/jquery.ui.accordion.js"></script>
        <script type="text/javascript" src="/greatindiaacademy/js/datePicker/ui/sliderAccess.js"></script>
        <script type="text/javascript" src="/greatindiaacademy/js/datePicker/ui/jquery-ui-timepicker-addon.js"></script>

        <script  type="text/javascript" src="/greatindiaacademy/js/datePicker/ui/jquery.ui.position.js"></script>
        <script type="text/javascript" src="/greatindiaacademy/js/datePicker/ui/jquery.ui.mouse.js"></script>
        <script type="text/javascript" src="/greatindiaacademy/js/datePicker/ui/jquery.ui.draggable.js"></script>
        <script type="text/javascript" src="/greatindiaacademy/js/datePicker/ui/jquery.ui.resizable.js"></script>
        <script type="text/javascript" src="/greatindiaacademy/js/graph/jquery.jqplot.js"></script>
        <script  type="text/javascript" src="/greatindiaacademy/js/graph/plugins/jqplot.dateAxisRenderer.min.js"></script>
        <script type="text/javascript" src="/greatindiaacademy/js/graph/plugins/jqplot.barRenderer.min.js"></script>
        <script type="text/javascript" src="/greatindiaacademy/js/graph/plugins/jqplot.categoryAxisRenderer.min.js"></script>
        <script type="text/javascript" src="/greatindiaacademy/js/graph/plugins/jqplot.cursor.min.js"></script>
        <script type="text/javascript" src="/greatindiaacademy/js/graph/plugins/jqplot.highlighter.min.js"></script>
        <script type="text/javascript" src="/greatindiaacademy/js/graph/plugins/jqplot.dragable.min.js"></script>
        <script type="text/javascript" src="/greatindiaacademy/js/graph/plugins/jqplot.trendline.min.js"></script>

        <style type="text/css">
        .dataText {
                border-radius:3px;
                font-family: Tahoma;
                color: #4b6a84;
                font-size: 13px;
                letter-spacing: normal;
                text-align: center;
                background-color: #E3EFFF;

            }
            .dataTextFees {
                border-radius:3px;
                font-family: ariel;
                font-weight: bold;
                color: #4b6a84;
                font-size: 16px;
                letter-spacing: normal;
                text-align: center;
                background-color: #E3EFFF;

            }
       		.dataTextDueFees{
				                border-radius:3px;
                font-family: ariel;
                font-weight: bold;
                color: red;
                font-size: 16px;
                letter-spacing: normal;
                text-align: center;
                background-color: #E3EFFF;

            }       		
       			 
            <!--
            .dataTextInActive {
                border-radius:1px;
                font-family: Tahoma;
                color: #4b6a84;
                background-color: #E3EFFF;
               	font-size: 12px;
                font-weight: bold;
                letter-spacing: normal;
                text-align: center;
                vertical-align: top;
                text-decoration:none;
            }
            .headerText {
                border-radius:3px;
                font-family: Tahoma;
                font-size: 12px;
                background-color: #4b6a84;
                color: #FFFFFF;
                font-weight: normal;
                width: auto ;
                height: 22px;
                vertical-align: middle;
                text-align: center;
                background-image: url("/images/ui-bg_diagonals-small_50_466580_40x40.png");
            }
            .headerTD{
                background-color:#4b6a84;
                color: #FFFFFF;
                font-family: Tahoma;
                font-size: 13px;
                text-transform: uppercase;
                text-align: center;
                font-weight: bold;
            }
            .smallheaderTD{
                color: #4b6a84;
                font-family: Tahoma;
                font-size: 11px;
                text-transform: uppercase;
                text-align: left;
                font-weight: bold;
            }
            .textFieldFixedWidth{
                width: 57px;
            }
            .subHeaderTD{
                color: #325F6D;
                font-family: Tahoma;
                font-size: 11px;
                text-transform: uppercase;
                text-align: left;
                font-weight: bold;
            }
            .divCSS{
                overflow:  scroll;
                height: 100%;
                width: 100%;
            }

            .fiedlSet {
                border-top-width: 1px;
                border-right-width: 1px;
                border-bottom-width: 1px;
                border-left-width: 1px;
                border-top-style: solid;
                border-right-style: solid;
                border-bottom-style: solid;
                border-left-style: solid;
                border-width: 1;
                width: 100%;
                color: #000000;
                font-size: 16px;
                font-weight: bold;
                font-variant: normal;
                font-stretch: wider;
                background-color: #e2ebf3;
                border-top-color: #5d7e9b;
                border-right-color: #5d7e9b;
                border-bottom-color: #5d7e9b;
                border-left-color: #5d7e9b;
            }
            .legendCSS {
                color: #666666;
            }
            .tableCSS {
                width: 100%;
                height: 100%;
                position: absolute;
                left: 0px;
                top: 0px;
            }
            .textAreaCSS {
                height: auto;
                width: auto;
            }
            .textField {
                border-top-style: solid;
                border-right-style: solid;
                border-bottom-style: solid;
                border-left-style: solid;
                border-top-color: #5d7e9b;
                border-right-color: #5d7e9b;
                border-bottom-color: #5d7e9b;
                border-left-color: #5d7e9b;
                border-top-width: 1px;
                border-right-width: 1px;
                border-bottom-width: 1px;
                border-left-width: 1px;
                width: auto;
                height: auto;
            }
            .alignRight {
                font-family: Tahoma;
                font-size: 11px;
                font-style: normal;
                text-transform: capitalize;
                color: #325F6D;
                text-align: right;
                vertical-align: middle;
                font-weight: bold;
            }
            .alignLeft {
                font-family: Tahoma;
                font-size: 13px;
                font-style: normal;
                text-transform: capitalize;
                color: #325F6D;
                text-align: left;
                vertical-align: middle;
                font-weight: bold;
            }
            .alignRightMultiple {
                font-family: Tahoma;
                font-size: 11px;
                font-weight: bolder;
                text-align: right;
                vertical-align: middle;
                font-style: normal;
                color: #325F6D;
            }
            .alignCentreMultiple {
                font-family: Arial, Helvetica, sans-serif;
                font-size: 12px;
                font-weight: bolder;
                text-align: center;
                vertical-align: middle;
                font-style: normal;
                color: #000000;
            }
            .autoAdjust {
                height: auto;
                width: auto;
            }
            .radioSpanCSS {
                font-size: 12px;
                font-family: Arial, Helvetica, sans-serif;
                text-align: left;
                vertical-align: middle;
            }
            .radioCSS {
                background-position: left center;
            }
            .spanText {
                font-family: Arial, Helvetica, sans-serif;
                font-size: 12px;
                font-weight: bold;
                color: #000000;
            }
            .emptyFieldSet {
                border-top-color: #FA7676;
                border-right-color: #FA7676;
                border-bottom-color: #FA7676;
                border-left-color: #FA7676;
                border-top-style: solid;
                border-right-style: solid;
                border-bottom-style: solid;
                border-left-style: solid;
                border-top-width: thin;
                border-right-width: thin;
                border-bottom-width: thin;
                border-left-width: thin;
                background-image: url(images/close.JPG);
                background-repeat: repeat-y;
                background-attachment: scroll;
                background-position: right;
                width: auto;
                height: auto;
                display: inline;
            }
            .style1 {
                font-family: Tahoma;
                font-size: 14px;
            }
            .style2 {
                color: #666666;
                font-family: Tahoma;
                font-size: 14px;
            }
            .style4 {
                font-size: 12px;
                font-family: Tahoma;
                text-align: left;
                vertical-align: middle;
                color: #325f6d;
            }
            .tablerows{
                font-size: 12px;
                font-family: Tahoma;
                text-align: left;
                font-weight: bold;

            }
            -->
            .mandatoryClass {
    font-family: Tahoma;
    font-size: 11px;
    color: red;
    font-style: normal;
    text-transform: capitalize;
    
    text-align: right;
    vertical-align: middle;
    font-weight: bold;
}
        </style>
        
        

        <script type="text/javascript">
            $(function() {

                $("#accordion").accordion();
                
                $("#search")
                .button()
                .click(function() {
            searchFeesStructure();
			        });
                $( "#cancel" )
                .button()
                ;
                
                $("#delete").button({
        	        icons: {
        	            primary: "ui-icon-trash"
        	        }
        	    }).click(function() {
        	    	if (confirm('Are you sure you want to delete the fees category?')) {
        	    		deleteRecord();
        				}
        	    	 return false;
        		});
                
                
                $("#waiveoff").button({
        	        icons: {
        	            primary: "ui-icon-flag"
        	        }
        	    }).click(function() {
        	    	if (confirm('Are you sure you want to waive off the fees?')) {
        	    		waiveOff();
        				}
        	    	 return false;
        		});
                
                $("#print").button({
        	        icons: {
        	            primary: "ui-icon-print"
        	        }
        	    });
                
                
                $("#applyconcession").button({
        	        icons: {
        	            primary: "ui-icon-flag"
        	        }
        	    }).click(function() {
        	    	if (confirm('Are you sure you want to apply the concession?')) {
        	    		applyConcession();
        				}
        	    	 return false;
        		});
                
                $(".concession").keypress(function (e) {
       		     //if the letter is not digit then display error and don't type anything
       		     if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
       		               return false;
       		    }
       		   });
                
            });
            
            $(function(){
                
                $('#chckHead').click(function () {
                    var length = $('.chcktb2:checked').length;
                    var trLength=$('.trClass').length;
                    if(length>0){
                        $('.chcktb2:checked').attr('checked', false);
                        this.checked=false;

                    }
                    else{
                        if (this.checked == false) {
                            $('.chcktb2:checked').attr('checked', false);
                        }
                        else {
                            $('.chcktb2:not(:checked)').attr('checked', true);
                        }

                    }

                });
            });
                      
            
            function searchFeesStructure() {

                var form1 = document.getElementById("form1");
                var id = document.getElementById("id").value;
                
                form1.action = "/greatindiaacademy/StudentProcess/feesStructurePerYear?id="+id;
                form1.submit();

            }
            
            function deleteRecord() {

                var form1 = document.getElementById("form1");
                form1.action = "/greatindiaacademy/FeesProcess/deleteFeesCategory";
                form1.submit();

            }
            
            function waiveOff() {

                var form1 = document.getElementById("form1");
                form1.action = "/greatindiaacademy/FeesProcess/waiveOffFees";
                form1.submit();

            }
            
            function applyConcession() {

                var form1 = document.getElementById("form1");
                form1.action = "/greatindiaacademy/FeesProcess/applyConcession";
                form1.submit();

            }
            
            function checkConcession(dueAmount,concession,sfsid){
            	var amountField = document.getElementById("concession:" + sfsid);

            	if (!amountField) {
            		return;
            	}

            	if (concession !== "" && parseFloat(concession) > parseFloat(dueAmount)) {
            		amountField.value = "";
            	}

            	updateRowCheckboxState(sfsid);
            }
           
        </script>
        
        <script>

                        function trimValue(value) {

                            if (value == null) {
                                return "";
                            }

                            return String(value).replace(/^\s+|\s+$/g, "");
                        }

                        function normalizeTextValue(value) {

                            if (!value) {
                                return "";
                            }

                            return trimValue(value).replace(/\s+/g, " ").toLowerCase();
                        }

                        function getFeeRows() {

                            if (document.querySelectorAll) {
                                return document.querySelectorAll("#feesStructureTable tbody tr");
                            }

                            return $("#feesStructureTable tbody tr").toArray();
                        }

                        function getRowCheckbox(row) {

                            if (!row) {
                                return null;
                            }

                            if (row.querySelector) {
                                return row.querySelector("input.chcktb2");
                            }

                            return $(row).find("input.chcktb2:first")[0] || null;
                        }

                        function getCellText(row, cellIndex) {

                            if (!row || !row.cells || row.cells.length <= cellIndex) {
                                return "";
                            }

                            var cell = row.cells[cellIndex];

                            if (!cell) {
                                return "";
                            }

                            if (cell.innerText != null) {
                                return trimValue(cell.innerText);
                            }

                            if (cell.textContent != null) {
                                return trimValue(cell.textContent);
                            }

                            return trimValue($(cell).text());
                        }

                        function normalizeFeesCategoryName(categoryName) {

                            return normalizeTextValue(categoryName);
                        }

                        function isTuitionFeeRow(row) {

                        	if (!row || !row.cells || row.cells.length < 2) {
                                return false;
                            }

                            const normalized = normalizeFeesCategoryName(getCellText(row, 1)).toLowerCase();
                            
                            return normalized.includes("tuition fee");
                        }

                        function getFieldValue(fieldId) {

                            var field = document.getElementById(fieldId);

                            if (!field || field.value == null) {
                                return "";
                            }

                            return trimValue(field.value);
                        }

                        function getConcessionPercentByReason(reason) {

                            var normalizedReason = normalizeTextValue(reason);

                            if (normalizedReason === "2nd child") {
                                return "15";
                            }

                            if (normalizedReason === "3rd child") {
                                return "100";
                            }

                            if (normalizedReason === "staff child") {
                                return "100";
                            }

                            return "";
                        }

                        function getConcessionAmountFromPercent(feesAmount, percentValue, dueAmount) {

                            var fees = parseFloat(feesAmount);
                            var percent = parseFloat(percentValue);
                            var due = parseFloat(dueAmount);

                            if (isNaN(fees)) {
                                fees = 0;
                            }

                            if (isNaN(due)) {
                                due = 0;
                            }

                            if (isNaN(percent)) {
                                return "";
                            }

                            if (percent < 0 || percent > 100) {
                                return "";
                            }

                            if (percent == 100) {
                                return due;
                            }

                            var concessionAmount = Math.round((fees * percent) / 100);

                            if (concessionAmount > due) {
                                concessionAmount = due;
                            }

                            return concessionAmount;
                        }

                        function updateRowCheckboxState(sfsid) {

                            var checkbox = document.getElementById(sfsid);

                            if (!checkbox) {
                                return;
                            }

                            var amount = getFieldValue("concession:" + sfsid);
                            var percent = getFieldValue("concessionPercent:" + sfsid);
                            var reason = getFieldValue("concessionnotes:" + sfsid);
                            var approvedBy = getFieldValue("concessionapprovedby:" + sfsid);

                            checkbox.checked =
                                amount !== "" ||
                                percent !== "" ||
                                reason !== "" ||
                                approvedBy !== "";
                        }

                        function setRowConcessionFromPercent(sfsid, feesAmount, dueAmount, percentValue) {

                            var percentField = document.getElementById("concessionPercent:" + sfsid);
                            var amountField = document.getElementById("concession:" + sfsid);

                            if (!percentField || !amountField) {
                                return;
                            }

                            if (percentValue === "") {
                                percentField.value = "";
                                amountField.value = "";
                                updateRowCheckboxState(sfsid);
                                return;
                            }

                            var concessionAmount =
                                getConcessionAmountFromPercent(feesAmount, percentValue, dueAmount);

                            if (concessionAmount === "") {
                                percentField.value = "";
                                amountField.value = "";
                                updateRowCheckboxState(sfsid);
                                return;
                            }

                            percentField.value = parseFloat(percentValue);
                            amountField.value = concessionAmount;
                            updateRowCheckboxState(sfsid);
                        }

                        function rowConcessionReasonChanged(sfsid, feesAmount, dueAmount) {

                            var reason = getFieldValue("concessionnotes:" + sfsid);
                            var percentValue = getConcessionPercentByReason(reason);

                            setRowConcessionFromPercent(sfsid, feesAmount, dueAmount, percentValue);
                            updateRowCheckboxState(sfsid);
                        }

                        function rowConcessionApprovedByChanged(sfsid) {
                            updateRowCheckboxState(sfsid);
                        }

                        function applyCommonConcessionValuesToRow(row, checkbox, amountField, percentField,
                                reasonField, approvedByField, dueField, commonAmount, commonPercent,
                                commonReason, commonConcessionApprovedBy) {

                            var hasCommonPercent = commonPercent !== "";
                            var hasCommonAmount = commonAmount !== "";

                            if (!row || !checkbox || !amountField || !percentField || !reasonField) {
                                return;
                            }

                            var sfsid = checkbox.id;
                            var dueAmount = 0;

                            if (dueField) {
                                dueAmount =
                                    parseFloat(
                                        trimValue(dueField.value)
                                            .replace(/,/g, "")
                                    );
                            }

                            if (isNaN(dueAmount)) {
                                dueAmount = 0;
                            }

                            if (hasCommonPercent) {

                                var totalFeesAmount =
                                    parseFloat(
                                        getCellText(row, 4)
                                            .replace(/,/g, "")
                                    );

                                var concessionAmount =
                                    getConcessionAmountFromPercent(totalFeesAmount, commonPercent, dueAmount);

                                if (concessionAmount !== "") {
                                    percentField.value = parseFloat(commonPercent);
                                    amountField.value = concessionAmount;
                                }

                            } else if (hasCommonAmount) {

                                var amount = parseFloat(commonAmount);

                                if (!isNaN(amount)) {

                                    if (amount > dueAmount) {
                                        amount = dueAmount;
                                    }

                                    amountField.value = amount;
                                }

                                percentField.value = "";
                            }

                            reasonField.value = commonReason;

                            if (approvedByField) {
                                approvedByField.value = commonConcessionApprovedBy;
                            }

                            updateRowCheckboxState(sfsid);
                        }

                        function syncTuitionFeeRowsForCommonConcession() {

                            var commonAmount =
                                getFieldValue("commonConcession");

                            var commonPercent =
                                getFieldValue("commonConcessionPercent");

                            var commonReason =
                                getFieldValue("commonConcessionReason");

                            var commonConcessionApprovedBy =
                                getFieldValue("commonConcessionApprovedBy");

                            var hasCommonSelection =
                                commonReason !== "" || commonConcessionApprovedBy !== "";

                            if (!hasCommonSelection) {
                                return;
                            }

                            var rows = getFeeRows();

                            for (var i = 0; i < rows.length; i++) {

                                var row = rows[i];

                                if (!isTuitionFeeRow(row)) {
                                    continue;
                                }

                                var checkbox = getRowCheckbox(row);

                                if (!checkbox) {
                                    continue;
                                }

                                var sfsid = checkbox.id;
                                var amountField = document.getElementById("concession:" + sfsid);
                                var percentField = document.getElementById("concessionPercent:" + sfsid);
                                var reasonField = document.getElementById("concessionnotes:" + sfsid);
                                var approvedByField = document.getElementById("concessionapprovedby:" + sfsid);
                                var dueField = document.getElementsByName("dueamount:" + sfsid)[0];

                                applyCommonConcessionValuesToRow(row, checkbox, amountField, percentField,
                                    reasonField, approvedByField, dueField, commonAmount, commonPercent,
                                    commonReason, commonConcessionApprovedBy);
                            }
                        }

        function applyConcessionToAll() {

            var applyAll = document.getElementById("applyAllConcession").checked;

            var commonAmount =
                getFieldValue("commonConcession");

            var commonPercent =
                getFieldValue("commonConcessionPercent");

            var commonReason =
                getFieldValue("commonConcessionReason");
            
            var commonConcessionApprovedBy =
                getFieldValue("commonConcessionApprovedBy");

            // Get ALL rows from the fees table
            var rows = getFeeRows();

            for (var i = 0; i < rows.length; i++) {

                var row = rows[i];

                // Get checkbox from this row
                var checkbox = getRowCheckbox(row);

                if (!checkbox) {
                    continue;
                }

                var sfsid = checkbox.id;

                // Get fields belonging to this row
                var amountField =
                    document.getElementById("concession:" + sfsid);

                var percentField =
                    document.getElementById("concessionPercent:" + sfsid);

                var reasonField =
                    document.getElementById("concessionnotes:" + sfsid);

                var approvedByField =
                    document.getElementById("concessionapprovedby:" + sfsid);

                var dueField =
                    document.getElementsByName("dueamount:" + sfsid)[0];

                if (!amountField || !percentField || !reasonField) {
                    continue;
                }

                if (!isTuitionFeeRow(row)) {
                    continue;
                }

                if (!applyAll) {
                    continue;
                }

                applyCommonConcessionValuesToRow(row, checkbox, amountField, percentField,
                    reasonField, approvedByField, dueField, commonAmount, commonPercent,
                    commonReason, commonConcessionApprovedBy);
            }
        }
        
        
        $("#commonConcessionPercent").keypress(function(e) {

            if (e.which == 46) {
                if ($(this).val().indexOf('.') != -1) {
                    return false;
                }
                return true;
            }

            if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
                return false;
            }

        });

</script>
<script>
/*function calculateConcessionPercent(feesAmount, percentValue, sfsid,dueAmount) {
    var percent = parseFloat(percentValue);
    if (!isNaN(percent)) {
        var concessionAmount = Math.round((feesAmount * percent) / 100);
		var due = dueAmount
        if (concessionAmount > due) {
            document.getElementById('concession:' + sfsid).value = "";
            document.getElementById(sfsid+'_concession').checked = false;
            document.getElementById(sfsid).checked = true;
        } else {
            document.getElementById('concession:' + sfsid).value = concessionAmount;
            document.getElementById(sfsid+'_concession').checked = true;
        }
    }
}*/
function calculateConcessionPercent(feesAmount, percentValue, sfsid, dueAmount) {

    setRowConcessionFromPercent(sfsid, feesAmount, dueAmount, percentValue);
}

function commonConcessionReasonChanged() {

    var reason = getFieldValue("commonConcessionReason");
    var percentField = document.getElementById("commonConcessionPercent");

    if (!percentField) {
        return;
    }

    percentField.value = getConcessionPercentByReason(reason);

    syncTuitionFeeRowsForCommonConcession();

    // If Apply to all is already selected,
    // immediately apply the new percentage/reason
    if (document.getElementById("applyAllConcession").checked) {
        applyConcessionToAll();
    }
}

function commonConcessionApprovedByChanged() {

    syncTuitionFeeRowsForCommonConcession();

    if (document.getElementById("applyAllConcession").checked) {
        applyConcessionToAll();
    }
}

</script>

    </head>
      <%
//allow access only if session exists
String user = null;
if(session.getAttribute("userAuth") == null){
	response.sendRedirect("/greatindiaacademy/UserProcess/sessionTimeOut");
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
    <body background="/images/bg.jpg">
        <jsp:useBean id="now" class="java.util.Date" />
        <fmt:formatDate var="today" type="date" value="${now}" />
        <form  method="post" id="form1">


            <table width="100%">
                <tr>
                   <td  class="headerTD">UID: &nbsp;<c:out value="${student.studentexternalid}" />&nbsp;&nbsp; NAME: &nbsp;<c:out value="${student.name}" />&nbsp;&nbsp; Class: &nbsp;<c:out value="${student.classstudying}" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						Father Name:${parents.fathersname}                    

                    </td>
                </tr>

            </table>
            <div id="accordion" style="width: 100%;height: 100%">

                <h3><a href="#">Fees Structure</a></h3>
                
                <div>
                
                <table width="100%" border="0" align="center"  id="table1">
						
											
						<tr>
		               <td width="26%"  class="alignRight" >
                                    
                                    Current Academic Year :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  </td>

                                <td width="28%"  >
                                    <label style="font-weight: bold;color: #325F85">
                                      ${currentAcademicYear}
                                    </label>

                                </td>


                            </tr>
                            
						<tr>
		               <td width="26%"  class="alignRight" >
                                    
                                    Academic Year*&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  </td>

                                <td width="28%"  >
                                    <label>
                                        <label> <select name="academicyear" id="academicyear" required
									style="width: 120px">
										<option selected></option>
										 <c:forEach var="year" items="${previousAcademicYears}">
        										<option value="${year}">${year}</option>
    									</c:forEach>
								</select>

							</label> 
                                    </label>

                                </td>


                            </tr>

                            
						<td>
						<input type="hidden" id="id" name="id" value="${student.sid}"  />
						</td>

              
                          </table>  
                                <table width="100%" >
                                    <tr>

                                        <td>
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="center">

                                            <button id="search" >Search</button>

                                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                            <!-- <button id="update" >Update</button> -->

                                        </td>

                                    </tr>
                                    
                                         <tr>

                                        <td>
                                            <br/>
                                        </td>
                                    </tr>
                                    
                                         <tr>

                                        <td>
                                            <br/>
                                        </td>
                                    </tr>
                                </table>
					<div align="center">
				<h class="dataTextFees">Academic Year : ${academicPerYear}</h>&nbsp;&nbsp;&nbsp;
				<h class="dataTextFees">Grand Total Fees : Rs. ${grandtotalfeesamount}</h>&nbsp;&nbsp;&nbsp;
				<h class="dataTextFees">Total Final Fees : Rs. ${totalfees}</h>&nbsp;&nbsp;&nbsp;
                <h class="dataTextFees">Total Fees paid : Rs.  ${sumoffees}</h>&nbsp;&nbsp;&nbsp;
                <h class="dataTextFees">Total Fees Due : </h>
                <h class="dataTextDueFees"> Rs.  ${dueamount}</h>&nbsp;&nbsp;&nbsp;
                <h class="dataTextFees">Total Fees Concession : Rs.  ${totalfeesconcession}</h>
                </div>
                    <table   width="100%"  border="0" style="border-color:#4b6a84;"  id="feesStructureTable">

                    <thead>
                        <tr>
                            <th title="click to sort" class="headerText"><input type="checkbox" id="chckHead" /></th>
                            <th title="click to sort" class="headerText">Fees Category</th>
                            <th title="click to sort" class="headerText">Fees Amount&nbsp;</th>
                            <th title="click to sort" class="headerText">Installments&nbsp;</th>
                            <th title="click to sort" class="headerText">Total Fees Amount&nbsp;</th>
                            <th title="click to sort" class="headerText">Fees Paid&nbsp;</th>
                            <th title="click to sort" class="headerText">Fees Due&nbsp;</th>
                             <th title="click to sort" class="headerText">Concession %&nbsp;</th>
                            <th title="click to sort" class="headerText">Concession Amount&nbsp;</th>
                            <th title="click to sort" class="headerText">Concession Reason&nbsp;</th>
                            <th title="click to sort" class="headerText">Approved By&nbsp;</th>
                            <th title="click to sort" class="headerText">Waive Off Amount&nbsp;</th>
                        </tr>
                    </thead>

                    <tbody>
                        <c:forEach items="${feesstructure}" var="feesstructure">

                            <tr class="trClass" style="border-color:#000000" border="1"  cellpadding="1"  cellspacing="1" >
                                <td class="dataText"><input type="checkbox"  class = "chcktb2"
								id="<c:out value="${feesstructure.sfsid}"/>" 
								name="sfsid" 
								value="<c:out value="${feesstructure.sfsid}"/>_<c:out value="${feesstructure.feescategory.idfeescategory}"/>" /></td>
                                <td class="dataText"><c:out value="${feesstructure.feescategory.feescategoryname}"/></td>
                                <td class="dataText"><c:out value="${feesstructure.feescategory.amount}"/></td>
                                <td class="dataText"><c:out value="${feesstructure.totalinstallment}"/></td>
                                <td class="dataText"><c:out value="${feesstructure.feesamount}"/></td>
                                <td class="dataText"><c:out value="${feesstructure.feespaid}"/></td>
                                <td class="dataText"><input type="text" style="background: transparent;border: none;color: #4b6a84;font-size: 13px;" name="dueamount:${feesstructure.sfsid}" value="${feesstructure.feesamount-feesstructure.feespaid-feesstructure.concession-feesstructure.waiveoff}" readonly></td>
                                 <!-- here -->
                                 <td class="dataText"><input type="text" 
       id="concessionPercent:${feesstructure.sfsid}" 
       placeholder="%"
       style="width: 50px; margin-left: 5px; font-size: 13px;" 
       onkeyup="calculateConcessionPercent(${feesstructure.feesamount}, this.value, ${feesstructure.sfsid}, ${feesstructure.feesamount-feesstructure.feespaid-feesstructure.concession-feesstructure.waiveoff})"></td>
                             
                                <td class="dataText">
                                <input type="hidden" id="concessionold:${feesstructure.sfsid}" name="concessionold:${feesstructure.sfsid}" value="${feesstructure.concession}">
                                <input type="text" id="concession:${feesstructure.sfsid}" style="background: transparent;border: none;color: #4b6a84;font-size: 13px;" onkeyup="checkConcession(${feesstructure.feesamount-feesstructure.feespaid-feesstructure.concession-feesstructure.waiveoff},this.value,${feesstructure.sfsid})" 
                                name="concession:${feesstructure.sfsid}" class="concession"
                                value="${feesstructure.concession}"></td>
                               
                                <td class="dataText">
								    <c:set var="concessionnotes" value="${fn:split(feesstructure.concessionnotes, '::')}" />
								    <select name="concessionnotes:${feesstructure.sfsid}" 
								            id="concessionnotes:${feesstructure.sfsid}"
								            class="dataText"
								            style="width:180px;
								                   background:transparent;
								                   border:1px solid #4b6a84;
								                   color:#4b6a84;
								                   font-size:13px;
                                   padding:3px;"
                            onchange="rowConcessionReasonChanged(${feesstructure.sfsid}, ${feesstructure.feesamount}, ${feesstructure.feesamount-feesstructure.feespaid-feesstructure.concession-feesstructure.waiveoff})">
								        <option value="">Select Reason</option>
								        <option value="2ND Child" ${concessionnotes[0] eq '2ND Child' ? 'selected' : ''}>2ND Child</option>
								        <option value="3RD Child" ${concessionnotes[0] eq '3RD Child' ? 'selected' : ''}>3RD Child</option>
								        <option value="Staff Child" ${concessionnotes[0] eq 'Staff Child' ? 'selected' : ''}>Staff Child</option>
								        <option value="Gift Scholar" ${concessionnotes[0] eq 'Gift Scholar' ? 'selected' : ''}>Gift Scholar</option>
								        <option value="Sponsored" ${concessionnotes[0] eq 'Sponsored' ? 'selected' : ''}>Sponsored</option>
								    </select>
								</td>
								
								<td class="dataText">
								    <select name="concessionapprovedby:${feesstructure.sfsid}" 
								            id="concessionapprovedby:${feesstructure.sfsid}"
								            class="dataText"
								            style="width:180px;
								                   background:transparent;
								                   border:1px solid #4b6a84;
								                   color:#4b6a84;
								                   font-size:13px;
                                   padding:3px;"
                            onchange="rowConcessionApprovedByChanged(${feesstructure.sfsid})">
								        <option value="">Approved By</option>
								        <option value="Ikram Sir" ${concessionnotes[1] eq 'Ikram Sir' ? 'selected' : ''}>Ikram Sir</option>
								        <option value="Najam Sir" ${concessionnotes[1] eq 'Najam Sir' ? 'selected' : ''}>Najam Sir</option>
								        <option value="Talha Sir" ${concessionnotes[1] eq 'Talha Sir' ? 'selected' : ''}>Talha Sir</option>
								    </select>
								</td>
                                <td class="dataText">
                                
                                <input type="hidden" name="waiveoff:${feesstructure.sfsid}" value="${feesstructure.waiveoff}" >
                                <c:out value="${feesstructure.waiveoff}"/></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                   
                </table>
				
				<table width="100%" >
                                    <tr>

                                        <td>
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="left">
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                            <button id="delete" >
                                            Delete</button>

                                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                             <button id="waiveoff">Waive Off</button>
                                             
                                             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                             <button id="applyconcession">Apply Concession</button>
                                             
                                             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                             <button id="print" onclick="window.location.href='/greatindiaacademy/printstudentdetailsfeesstructure'">Print</button>
                                        </td>

                                    </tr>
                                    
                                         <tr>

                                        <td>
                                            <br/>
                                        </td>
                                    </tr>
                                    
                                         <tr>
                                        <td align="left">
											    <span style="color:#416884;font-size:13px;font-weight:bold;">
												    Common Concession Amount:
												</span>

											<input type="text"
											       id="commonConcession"
											       class="dataText"
											       placeholder="Amount"
											       style="width:100px;
											              background:transparent;
											              border:1px solid #4b6a84;
											              color:#4b6a84;
											              font-size:13px;
											              padding:3px;"
											       onkeyup="if(document.getElementById('applyAllConcession').checked) {
											                    applyConcessionToAll();
											                }">
											
											&nbsp;&nbsp;
											
											<span style="color:#416884;font-size:13px;font-weight:bold;">
											    Common Concession %:
											</span>
											
											<input type="text"
											       id="commonConcessionPercent"
											       class="dataText"
											       placeholder="%"
											       style="width:70px;
											              background:transparent;
											              border:1px solid #4b6a84;
											              color:#4b6a84;
											              font-size:13px;
											              padding:3px;"
											       onkeyup="if(document.getElementById('applyAllConcession').checked) {
											                    applyConcessionToAll();
											                }">
											
											&nbsp;&nbsp;
											
											<span style="color:#416884;font-size:13px;font-weight:bold;">
											    Common Concession:
											</span>
											
											<select id="commonConcessionReason"
											        class="dataText"
											        style="width:180px;
											               background:transparent;
											               border:1px solid #4b6a84;
											               color:#4b6a84;
											               font-size:13px;
											               padding:3px;"
                                    onchange="commonConcessionReasonChanged()">
											
											    <option value="">Select Reason</option>
											    <option value="2ND Child">2ND Child</option>
											    <option value="3RD Child">3RD Child</option>
											    <option value="Staff Child">Staff Child</option>
											    <option value="Gift Scholar">Gift Scholar</option>
											    <option value="Sponsored">Sponsored</option>
											
											</select>
											
											&nbsp;&nbsp;
											
											<select id="commonConcessionApprovedBy"
											        class="dataText"
											        style="width:180px;
											               background:transparent;
											               border:1px solid #4b6a84;
											               color:#4b6a84;
											               font-size:13px;
											               padding:3px;"
                                    onchange="commonConcessionApprovedByChanged()">
											
											    <option value="">Approved By</option>
											    <option value="Ikram Sir">Ikram Sir</option>
											    <option value="Najam Sir">Najam Sir</option>
											    <option value="Talha Sir">Talha Sir</option>
											
											</select>
											
											&nbsp;&nbsp;
											
											<input type="checkbox"
											       id="applyAllConcession"
											       onclick="applyConcessionToAll()"
											       style="vertical-align:middle;cursor:pointer;">
											
											<span style="color:#416884;font-size:13px;font-weight:bold;">
											    Apply to all rows
											</span>

                                        </td>

                                    </tr>
                                    
                                         <tr>

                                        <td>
                                            <br/>
                                        </td>
                                    </tr>
                                    
                                         <tr>

                                        <td>
                                            <br/>
                                        </td>
                                    </tr>
                                    
                                </table>
                                
                                <table   width="100%"  border="0" style="border-color:#4b6a84;"  id="myTable">
									<tr>
		               		<td width="26%"  class="alignRight" style="text-align: center;font-size:20px;">
                                    
                                    Fees Collection Details&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  </td>
                            </tr>
                            </table>
                                
                                <table   width="100%"  border="0" style="border-color:#4b6a84;"  id="myTable">
                    <thead>
                        <tr  >
                            
                            <th title="click to sort" class="headerText">Date of fees</th>
                            <th title="click to sort" class="headerText">Reference Number</th>
                            <th title="click to sort" class="headerText">Total Amount</th>
                            <th title="click to sort" class="headerText">View Details</th>
                             


                        </tr>
                    </thead>

                    <tbody>
                        <c:forEach items="${receiptinfo}" var="receiptinfo">

                            <tr class="trClass" style="border-color:#000000" border="1"  cellpadding="1"  cellspacing="1" >
                                
                                <td  class="dataText"><c:out value="${receiptinfo.date}"/></a></td>
                                <td  class="dataText"><c:out value="${receiptinfo.branchreceiptnumber}"/></a></td>
                                <td class="dataText"><c:out value="${receiptinfo.totalamount}"/></td>
                                <td  class="dataTextInActive"><a class="dataTextInActive" target="_blank" href="/scholar/FeesCollection/ViewDetails?id=<c:out value='${receiptinfo.receiptnumber}'/>&sid=<c:out value='${student.sid}'/>">View Details</a></td>
                                 

                            </tr>
                        </c:forEach>
                        
                        
                    </tbody>
                   
                </table>
                    

                </div>






            </div>
            
            <table  width="70%"  id="table11" align="center">
                        <tr>
                            <td width="30%"> 

                            </td>
                            <td>
                                
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                
                            </td>

                        </tr>

                    </table>
            <script>
                typeofrelation();
            </script>

        </form>
        <script>
        
            function Cancel(){
                var form1=document.getElementById("form1");
                form1.action="/greatindiaacademy/StudentProcess/ViewAll";
                form1.submit();
            }
        </script>
    </body>
</html>
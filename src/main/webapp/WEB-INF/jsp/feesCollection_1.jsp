<%-- 
    Document   : Fees Collect
    Created on : Jul 24, 2012, 4:07:26 PM
    Author     : Musaib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/datePicker/jquery-ui-1.8.18.custom.css">
        <link rel="stylesheet" href="css/graph/jquery.jqplot.css">

        <link rel="stylesheet" href="css/datePicker/demos.css">
        <script type="text/javascript" src="js/datePicker/jquery-1.7.1.js"></script>
        <script type="text/javascript" src="js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
        <script type="text/javascript" src="js/datePicker/ui/jquery.ui.dialog.js"></script>
        <script type="text/javascript" src="js/datePicker/ui/jquery.ui.autocomplete.js"></script>
        <script type="text/javascript" src="js/datePicker/ui/jquery.ui.core.js"></script>
        <script type="text/javascript" src="js/datePicker/ui/jquery.ui.widget.js"></script>
        <script type="text/javascript" src="js/datePicker/ui/jquery.ui.datepicker.js"></script>
        <script type="text/javascript" src="js/datePicker/ui/jquery.ui.accordion.js"></script>
        <script type="text/javascript" src="js/datePicker/ui/sliderAccess.js"></script>
        <script type="text/javascript" src="js/datePicker/ui/jquery-ui-timepicker-addon.js"></script>
        <script  type="text/javascript" src="js/datePicker/ui/jquery.ui.position.js"></script>
        <script type="text/javascript" src="js/datePicker/ui/jquery.ui.mouse.js"></script>
        <script type="text/javascript" src="js/datePicker/ui/jquery.ui.draggable.js"></script>
        <script type="text/javascript" src="js/datePicker/ui/jquery.ui.resizable.js"></script>

        <script type="text/javascript" src="js/datePicker/ui/jquery.effects.slide.js"></script>
        <script type="text/javascript" src="js/datePicker/ui/jquery.effects.bounce.js"></script>
        <script type="text/javascript" src="js/datePicker/ui/jquery.effects.clip.js"></script>
        <script type="text/javascript" src="js/datePicker/ui/jquery.effects.transfer.js"></script>
        <script type="text/javascript" src="js/datePicker/ui/jquery.effects.blind.js"></script>
        <style type="text/css">
            <!--
            .labelCss {
                font-family: Tahoma;
                font-size: 11px;
                font-weight: bold;
            }
            .dataTextInActive {
                border-radius:1px;
                font-family: Tahoma;
                color: #4b6a84;
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
                background-image: url("images/ui-bg_diagonals-small_50_466580_40x40.png");
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
                font-size: 11px;
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
            -->
        </style>
        <script type="text/javascript">
            /**
             * Comment
             */
            function calculateGrandTotal() {
                var sum = 0.0;
                var column2 = $('.feesAmount')
                jQuery.each(column2,function(){
                    sum += parseFloat($(this).val());
                });
                $('#grandTotalAmount').val(sum.toPrecision(6));

            }
            $(document).ready(function() {
                $("#feesSubmitDate").val(getCurrentDate());
                $("#feesSubmitDate").datepicker();
                
                $("#balanceamount").keyup(function(){
                    var consultation=parseFloat($("#balanceamount").val());
                    var miscellanous=parseFloat($("#miscellanousamount").val());
                    var sum = 0.0;
                    var totalSum=0.0;
                    var column2 = $('.feesAmount')
                    jQuery.each(column2,function(){
                        sum += parseFloat($(this).val());
                    });
                    totalSum=sum+miscellanous+consultation;
                    $('#grandTotalAmount').val(totalSum.toPrecision(6));

                });
                $("#miscellanousamount").keyup(function(){
                    var consultation=parseFloat($("#balanceamount").val());
                    var miscellanous=parseFloat($("#miscellanousamount").val());
                    var sum = 0.0;
                    var totalSum=0.0;
                    var column2 = $('.feesAmount')
                    jQuery.each(column2,function(){
                        sum += parseFloat($(this).val());
                    });
                    totalSum=sum+miscellanous+consultation;
                    $('#grandTotalAmount').val(totalSum.toPrecision(6));
                });
                $("#dataTable").keyup(function(){
                    var consultation=parseFloat($("#balanceamount").val());
                    var miscellanous=parseFloat($("#miscellanousamount").val());
                    var sum = 0.0;
                    var totalSum=0.0;
                    var column2 = $('.feesAmount')
                    jQuery.each(column2,function(){
                        sum += parseFloat($(this).val());
                    });
                    totalSum=sum+miscellanous+consultation;
                    $('#feesTotalAmount').val(sum.toPrecision(6));
                    $('#grandTotalAmount').val(totalSum.toPrecision(6));
                });
                $("#dataTable").click(function(){
                    var consultation=parseFloat($("#balanceamount").val());
                    var miscellanous=parseFloat($("#miscellanousamount").val());
                    var sum = 0.0;
                    var totalSum=0.0;
                    var column2 = $('.feesAmount')
                    jQuery.each(column2,function(){
                        sum += parseFloat($(this).val());
                    });
                    totalSum=sum+miscellanous+consultation;
                    $('#feesTotalAmount').val(sum.toPrecision(6));
                    $('#grandTotalAmount').val(totalSum.toPrecision(6));
                });


            });
            /**
             * Comment
             */
            /**
             * Comment
             */
            /**
             * Comment
             */
            function validate() {
                if(validateStudent() & validatefees()){
                    return true;
                }
                else{
                    return false;
                }

            }
            function validateStudent() {
                if($("#studentId").val()==""){
                    alert("Select Student");
                    return false;

                }
                else{
                    return true;
                }

            }
            function validatefees() {
                var count = 0;
                var idColumn = $('.feesStatus')
               
                if(idColumn.length==0){
                    alert("Add fees");
                    return false;
                }else{
                    jQuery.each(idColumn,function(){
                    
                        if($(this).val()=="not set"){
                        
                            count++;
                        }
                    });
                    if(count >0){
                        alert("Add fees");
                        return false;
                    }
                    else{
                        return true;
                    }
                
                }

            }
            
            function selectAllRow(tableID){
                var table = document.getElementById(tableID);
                var rowCount = table.rows.length;
                if(rowCount==1){
                    var row = table.rows[0];
                    var chkbox = row.cells[0].childNodes[0];
                    chkbox.checked=false;
                    alert('No records to select');
                }
                for(var i=1; i<rowCount; i++) {
                    var row = table.rows[i];
                    var chkbox = row.cells[0].childNodes[0];
                    chkbox.checked=true;
                }
            }
            function addPrescription(visitId){
                updateVisitByID(visitId);
                var form1=document.getElementById("form1");
                var rowIdString=document.getElementById('rowIdString');
                var rowfeesIdString=document.getElementById('rowfeesIdString');
                var rowfeesNatureString=document.getElementById('rowfeesNatureString');
                var rowfeesStrengthString=document.getElementById('rowfeesStrengthString');
                var rowfeesDurationString=document.getElementById('rowfeesDurationString');
                var rowfeesDosingString=document.getElementById('rowfeesDosingString');
                var rowfeesRemarkString=document.getElementById('rowfeesRemarkString');
                form1.action="Controller?process=PrescriptionProcess&action=add&visitId="+visitId+"&rowIdString="+rowIdString.value+"&rowfeesIdString="+rowfeesIdString.value+"&rowfeesNatureString="+rowfeesNatureString.value+"&rowfeesStrengthString="+rowfeesStrengthString.value+"&rowfeesDurationString="+rowfeesDurationString.value+"&rowfeesDosingString="+rowfeesDosingString.value+"&rowfeesRemarkString="+rowfeesRemarkString.value;
                form1.submit();
            }
            function getCurrentDate(){
                var today = new Date();
                var day=today.getDate();
                var month=today.getMonth()+1;
                var year=today.getFullYear();
                if(month <10){
                    month="0"+month;

                }
                else {
                    month=month;
                }
                if(day <10){
                    day="0"+day;

                }
                else {
                    day=day;
                }
                return month+"/"+day+"/"+year;

            }
            

                var feescat=[
            <c:forEach varStatus="status" items="${feescategory}" var="fees">{
                    value:'<c:out default="0" value="${fees.feescategory}" />',
                    particularname:'<c:out default="0" value="${fees.particularname}" />',
                    price:'<c:out default="0" value="${fees.amount}" />',
                    id:'<c:out default="0" value="${fees.idfeescategory}" />'
                    }<c:if test="${!status.last}">,</c:if>
            </c:forEach>
            ];
            var students = [
            <c:forEach varStatus="status" items="${studentList}" var="student">{
                value:'<c:out default="0" value="${student.admissionnumber}" />',
                name:'<c:out default="0" value="${student.name}" />',
                classandsec:'<c:out default="0" value="${student.classstudying}" />',
                id:'<c:out default="0" value="${student.sid}" />',
                
            }<c:if test="${!status.last}">,</c:if>
            </c:forEach>
        ];
        $(function() {
            $( "#admno").autocomplete({
                source: students,
                minLength: 1,
                change:function(event,ui){
                    $( "#studentId").val( ui.item.id );
                    
                    
                },
                focus: function( event, ui ) {
                    $( "#studentId").val( ui.item.id );
                    return true;
                },
                select: function( event, ui ) {
                    $( "#studentId").val( ui.item.id );
       			  $( "#studentName").val( ui.item.name );
       			$( "#classandsec").val( ui.item.classandsec );
                    /* $("#classandsec"+rowCount).val( ui.item.classandsec ); */
                    return true;
                }
            }).data( "autocomplete" )._renderItem = function( ul, item ) {
                return $( "<li></li>" )
                .data( "item.autocomplete", item )
                .append( "<a><b> " + item.value +" </b> </a>" )
                .appendTo( ul );
            };
            var addFeesButtonID="#addFees";
            var removeFeesButtonID="#removeFees";
            $( addFeesButtonID )
            .button({
                icons: {
                    primary: "ui-icon-plus"
                }
            })
            .click(function() {
                addRow();
                return false;
            });
            $(removeFeesButtonID)
            .button({
                icons: {
                    primary: "ui-icon-minus"
                }
            })
            .click(function() {
                deleteRow('dataTable');
                return false;
            });            

        });
        $('#selectAll').click(function () {
            var length = $('.chcktbl:checked').length;
            var trLength=$('.trClass').length;
            if(length>0){
                $('.chcktbl:checked').attr('checked', false);
                this.checked=false;
            }
            else{
                if (this.checked == false) {
                    $('.chcktbl:checked').attr('checked', false);
                }
                else {
                    $('.chcktbl:not(:checked)').attr('checked', true);
                }
            }
        });
        /**
         *
         */
        function deleteRow(tableID) {
            try {
                var table = document.getElementById(tableID);
                var rowCount = table.rows.length;
                if(rowCount==1){
                    alert('No records to delete');
                }
                for(var i=1; i<rowCount-1; i++) {
                    var row = table.rows[i];
                    var chkbox = row.cells[0].childNodes[0];
                    if(null != chkbox && true == chkbox.checked) {
                        table.deleteRow(i);
                        rowCount--;
                        i--;
                    }
                }
               
                var consultation=parseFloat($("#balanceamount").val());
                var miscellanous=parseFloat($("#miscellanousamount").val());
                var sum = 0.0;
                var totalSum=0.0;
                var column2 = $('.feesAmount')
                jQuery.each(column2,function(){
                    sum += parseFloat($(this).val());
                });
                totalSum=sum+miscellanous+consultation;
                $('#feesTotalAmount').val(sum.toPrecision(6));
                $('#grandTotalAmount').val(totalSum.toPrecision(6));
                
                calculateGrandTotal();
                //$('#grandTotalAmount').val(0);
            }catch(e) {
                alert(e);
            }
        }
        function calculate(value2) {
            //var val1=value1.value;
            var actualQuantity=document.getElementById("hiddenfees_quantity_"+value2).value;
            var val1=document.getElementById("fees_month_"+value2).value;
            var val2=document.getElementById("hiddenfees_amount_"+value2).value;
            var final1=document.getElementById("fees_amount_"+value2);
            if(parseInt(val1) > parseInt(actualQuantity) ){
                final1.value=0;
                alert("Enter the valid fees amount");
            }
            
            else{
                final1.value=(val2);
            }    
        }

         

        function addRow() {
            var rowCount = document.getElementById('dataTable').rows.length;    
            var col1="<td class='dataTextInActive'><input type='checkbox' class = 'chcktbl' id=fees_"+rowCount+" /><input type='hidden' class='feesStatus' name='feesStatuses' id=fees_status_"+rowCount+" value='not set' /><input type='hidden' class='feesId' name='feesIDS' id=fees_id_"+rowCount+" value='' /></td>";
            var col2="<td class='dataTextInActive'><input class='feesName' type='text' name='feesNames' id=fees_name_"+rowCount+" /></td>";
            var col3="<td class='dataTextInActive'><input type='text' value='0' readonly  id=hiddenfees_amount_"+rowCount+" /></td>";
            /* var col4="<td class='dataTextInActive'><input type='text' value='1' onclick='calculate("+rowCount+")'  onkeyup='calculate("+rowCount+")' name='medicineQuantities' id=medicine_quantity_"+rowCount+" /><input type='hidden'   id=hiddenMedicine_quantity_"+rowCount+" value='' /></td>"; */
            var col4="<td class='dataTextInActive'><select  onchange='calculate("+rowCount+")'  name='feesMonths' id=fees_month_"+rowCount+"><option></option><option>JAN</option><option>Feb</option><option>MAR</option><option>APR</option><option>MAY</option><option>JUN</option><option>JUL</option><option>AUG</option><option>SEP</option><option>OCT</option><option>NOV</option><option>DEC</option></select><input type='hidden'   id=hiddenfees_month_"+rowCount+" value='' /></td>";
            var col5="<td class='dataTextInActive'><input class='feesAmount' type='text' value='0' readonly name='feesAmounts' id=fees_amount_"+rowCount+" /></td>";
            var newRow = $("<tr class='trClass'>"+col1+col2+col3+col4+col5+"</tr>");
            $(function() {
                $("#dataTable").find('tbody').append(newRow);
            });
            $(function() {
                $("#fees_name_"+rowCount).autocomplete({
                    source: feescat,
                    minLength: 1,
                    change:function(event,ui){

                        $("#fees_id_"+rowCount ).val( ui.item.id );
                        $( "#fees_status_"+rowCount ).val("set");
                        $("#hiddenfees_amount_"+rowCount).val( ui.item.price );
                        $("#hiddenfees_month_"+rowCount).val( ui.item.particularname );
                        calculate(rowCount);

                    },
                    focus: function( event, ui ) {
                        $( "#fees_name_"+rowCount).val( ui.item.name );
                        $( "#fees_status_"+rowCount ).val("not set");
                        $( "#fees_id_"+rowCount ).val( ui.item.id );
                        $( "#hiddenfees_amount_"+rowCount).val( ui.item.price );
                        $("#hiddenfees_month_"+rowCount).val( ui.item.particularname );
                        calculate(rowCount);

                        return true;
                    },
                    select: function( event, ui ) {
                        $( "#fees_name_"+rowCount).val( ui.item.value );
                        $( "#fees_id_"+rowCount ).val( ui.item.id );
                        $( "#fees_status_"+rowCount ).val("set");
                        $( "#hiddenfees_amount_"+rowCount).val( ui.item.price );
                        $("#hiddenfees_month_"+rowCount).val( ui.item.particularname );
                        calculate(rowCount);
                        return true;
                    }
                }).data( "autocomplete" )._renderItem = function( ul, item ) {
                    return $( "<li></li>" )
                    .data( "item.autocomplete", item )
                    .append( "<a><b> " + item.value +":-</b> <b> "+item.particularname +"</b></a>" )
                    .appendTo( ul );
                };

            });



        }




        </script>
        <script type="text/javascript">
    $(function() {
        // run the currently selected effect
        function runEffect() {
            var clipEffect='blind';
            var options = {};
            $( "#effect" ).toggle( clipEffect, options, 1000 );
        };
        // set effect from select menu value
        $( "#add" ).button().click(function() {
            runEffect();
            return false;
        });
    });
        </script> 
        
                <script type="text/javascript">
            $(function() {
                $( "#tabs" ).tabs();
            });

            
        </script>
    </head>
    <%
//allow access only if session exists
String user = null;
if(session.getAttribute("userAuth") == null){
	response.sendRedirect("Controller?process=UserProcess&action=sessionTimeOut");
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
    <body>
        <form id="form1" action="Controller?process=FeesCollection&action=feesAdd" method="post" onkeypress="if (event.keyCode == 92) addRow();">
            <div style="height: 28px">
                <!--<a href="#" id="button" class="ui-state-default ui-corner-all">Add Medicine</a>-->

               
            <table  width="100%">
                <thead>
                    <tr>
                        <th colspan="3" class="headerTD"> Fees Collection</th>

                    </tr>
                </thead>
                <tbody>
                    <tr>
                    <td style="width: 45%">Admission No: &nbsp;&nbsp;&nbsp;&nbsp; <input  type="text" name="admno" id="admno" style="width: 200px" /> <input name="studentId" type="hidden" id="studentId" value="" /> </td>
                        
                        <td>Date:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="text" name="feesSubmitDate" id="feesSubmitDate" /></td>
                        <td>Fees Category: <button id="addFees">Add</button>&nbsp;&nbsp;&nbsp;<button id="removeFees">Remove</button></td>
                    </tr>
                    
                    <tr>
                    
                        <td style="width: 45%">Student Name:&nbsp;&nbsp;&nbsp;&nbsp; <input  type="text" name="studentName" id="studentName" style="width: 200px" /></td>
                        <td>Class & SEC : &nbsp;&nbsp;&nbsp;<input type="text" name="classandsec" id="classandsec" /></td>
                        
                    </tr>
                    
                    <tr>

                        <td style="width: 45%">Balance Amount:&nbsp;&nbsp;<input  type="text" name="balanceamount" id="balanceamount" style="width: 200px" value="0" />  </td>
                        <td>Miscellaneous:&nbsp;&nbsp;&nbsp;<input type="text" name="miscellanousamount" id="miscellanousamount" value="0" /></td>

                    </tr>
                    <tr>

                    </tr>

                </tbody>
            </table>
            <table id="dossageTable">
                <tbody></tbody>

            </table>
            <TABLE id="dataTable" width="100%" border="1" >
                <thead>
                    <tr >
                        <td class="headerText"><INPUT type="checkbox" id="selectAll"  name="selectAll" onclick="selectAllRow('dataTable')" /></td>
                        <td class="headerText">Fees Category</td>
                        <td class="headerText">Fees Amount</td>                       
                        <td class="headerText">For the month of</td>
                        <td class="headerText">Amount</td>

                    </tr>
                </thead>
                <tbody>

                </tbody>
                <tfoot>
                    <tr>

                        <td colspan="4" align="right">Total&nbsp;&nbsp;</td>
                        <td align="center"><input type="text" name="feesTotalAmount" id="feesTotalAmount" value="0" readonly /></td>
                    </tr>
                    <tr>

                        <td colspan="4" align="right"><b>Grand Total&nbsp;&nbsp;</b></td>
                        <td align="center"><b><input type="text" name="grandTotalAmount" id="grandTotalAmount" value="0" readonly /></b></td>
                    </tr>
                </tfoot>
            </TABLE>

            
            <input type="submit" value="submit" />
            <input type="hidden" id="customeradd" name="customeradd" value="fromdispense"/>
        </form>

    </body>
</html>

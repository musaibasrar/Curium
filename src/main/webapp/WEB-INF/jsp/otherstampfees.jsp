<%--
    Document   : Stamp Fees
    Created on : Dec 23, 2011, 5:52:28 PM
    Author     : Musaib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>stamp fees</title>
<link rel="stylesheet" href="/abc/css/datePicker/jquery-ui-1.8.18.custom.css">
<link rel="stylesheet" href="/abc/css/datePicker/demos.css">

<style type="text/css">
<!--
.divCSS {
	overflow: scroll;
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
.alignRightFields {
	font-family: Tahoma;
	font-size: 11px;
	font-style: normal;
	text-transform: capitalize;
	color: #325F6D;
	text-align: left;
	vertical-align: middle;
	font-weight: bold;
}
.alignSearch {
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
.footerTD {
	border-radius: 6px;
	background-color: #4b6a84;
	text-align: left;
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
<!--
.header {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	background-color: #4b6a84;
}
.table {
	background-color: #3399CC;
	text-align: center;
	width: auto;
}
.headerText {
	border-radius: 3px;
	width: 10px;
	font-family: Tahoma;
	font-size: 12px;
	background-color: #4b6a84;
	color: #FFFFFF;
	font-weight: normal;
	width: auto;
	height: 27px;
	vertical-align: text-top;
	text-align: center;
	background-image:
		url("/images/ui-bg_diagonals-small_50_466580_40x40.png");
}
.dataText {
	border-radius: 3px;
	font-family: Tahoma;
	color: #4b6a84;
	font-size: 13px;
	letter-spacing: normal;
	text-align: center;
	background-color: #E3EFFF;
}
.dataTextInActive {
	border-radius: 3px;
	font-family: Tahoma;
	color: #4b6a84;
	font-size: 12px;
	font-weight: bold;
	letter-spacing: normal;
	text-align: center;
	background-color: #E3EFFF;
	text-decoration: none;
}
.dataTextActive {
	border-radius: 3px;
	font-family: Tahoma;
	color: #4b6a84;
	font-size: 12px;
	font-weight: bold;
	letter-spacing: normal;
	text-align: center;
	background-color: #E3EFFF;
	text-decoration: underline;
	cursor: pointer;
}
.dataTextHidden {
	font-family: Tahoma;
	color: #4b6a84;
	font-size: 13px;
	letter-spacing: normal;
	text-align: center;
	background-color: #E3EFFF;
}
.headerTD {
	border-radius: 6px;
	background-color: #4b6a84;
	background-image:
		url("/images/ui-bg_diagonals-small_50_466580_40x40.png");
	color: #FFFFFF;
	font-family: Tahoma;
	font-size: 13px;
	text-transform: uppercase;
	text-align: center;
	font-weight: bold;
	height: 22px;
}
</style>
<style>
#button {
	
}
</style>
<link rel="stylesheet" href="/abc/css/validation/jquery.ketchup.css">
<script type="text/javascript" src="/abc/js/datePicker/jquery-1.7.1.js"></script>
<script type="text/javascript"
	src="/abc/js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
<script type="text/javascript" language="javascript"
	src="/abc/js/dataTable/jquery.dataTables.js"></script>
<script type="text/javascript" src="/abc/js/datePicker/ui/jquery.ui.core.js"></script>
<script type="text/javascript"
	src="/abc/js/datePicker/ui/jquery.ui.widget.js"></script>
<script type="text/javascript"
	src="/abc/js/datePicker/ui/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="/abc/js/datePicker/ui/jquery.ui.tabs.js"></script>
<script type="text/javascript" src="/abc/js/datePicker/ui/sliderAccess.js"></script>

<script type="text/javascript" src="/abc/js/globalize.min.js"></script>
<script type="text/javascript" charset="utf-8" src="/abc/js/globalize.cultures.js"></script>

<script type="text/javascript"
	src="/abc/js/validation/jquery.ketchup.all.min.js"></script>
<script type="text/javascript"
	src="/abc/js/datePicker/ui/jquery.ui.button.js"></script>
<script type="text/javascript"
	src="/abc/js/datePicker/ui/jquery.ui.accordion.js"></script>
<script type="text/javascript"
	src="/abc/js/datePicker/ui/jquery.effects.core.js"></script>
<script type="text/javascript"
	src="/abc/js/datePicker/ui/jquery.ui.accordion.js"></script>
<script type="text/javascript"
	src="/abc/js/datePicker/ui/jquery.effects.slide.js"></script>
<script type="text/javascript"
	src="/abc/js/datePicker/ui/jquery.effects.bounce.js"></script>
<script type="text/javascript"
	src="/abc/js/datePicker/ui/jquery.effects.clip.js"></script>
<script type="text/javascript"
	src="/abc/js/datePicker/ui/jquery.effects.transfer.js"></script>
<script type="text/javascript"
	src="/abc/js/datePicker/ui/jquery.effects.blind.js"></script>
<script type="text/javascript"
	src="/abc/js/datePicker/ui/ScrollableGridPlugin.js"></script>
<script type="text/javascript" charset="utf-8">
	$(document).ready(function() {
		$('#myTable').dataTable({
			"sScrollY" : "380px",
			"bPaginate" : false,
			"bLengthChange" : false,
			"bFilter" : true,
			"bSort" : true,
			"bInfo" : false,
			"bAutoWidth" : false
		});
	});
</script>
<script type="text/javascript">
	function select(id, name) {
		var clipEffect = 'blind';
		var options = {};
		$("#effect").show();
		
	}
	
</script>

<script type="text/javascript" src="/abc/js/datetimepicker_css.js"></script>
<script type="text/javascript">
	function searchForFees() {
		var form1 = document.getElementById("form1");
		form1.action = "/abc/StampFeesProcess/othersearch";
		form1.method = "POST";
		form1.submit();
	}
	$(function() {
		$("#search").button().click(function() {
			searchForFees();
		});
		
	});
	$(function() {
		$("#tabs").tabs();
		$("#save").button().click(function() {
			addDepartment();
		});
		/* $("#effect").hide(); */
	});
	
	$(function() {
		$("#delete").button({
			icons : {
				primary : "ui-icon-trash"
			}
		}).click(function() {
			deleteRecords();
			return false;
		});
		$("#deleteStamp").button({
			icons : {
				primary : "ui-icon-trash"
			}
		}).click(function() {
			if (confirm('Are you sure you want to delete the stamp fees?As it can not be revert back.')) {
			deleteFeesStamp();
			}
			return false;
		});
		$('#chckHead').click(function() {
			var length = $('.chcktbl:checked').length;
			var trLength = $('.trClass').length;
			if (length > 0) {
				$('.chcktbl:checked').attr('checked', false);
				this.checked = false;
			} else {
				if (this.checked == false) {
					$('.chcktbl:checked').attr('checked', false);
				} else {
					$('.chcktbl:not(:checked)').attr('checked', true);
				}
			}
		});
		$('.chcktbl').click(function() {
			var length = $('.chcktbl:checked').length;
			var trLength = $('.trClass').length;
			alert(tdLength);
			if (length > trLength) {
				$('.chcktbl:not(:checked)').attr('disabled', true);
			} else {
				$('.chcktbl:not(:checked)').attr('disabled', false);
			}
		});
		$("#go").button()
	});
	
	function deleteFeesStamp(){
		var form1 = document.getElementById("form1");
		form1.action = "/abc/StampFeesProcess/delete";
		form1.method = "POST";
		form1.submit();
	}
</script>


<script type="text/javascript">
        
        function calculateGrandTotal() {
            var sum = 0.0;
            var column2 = $('.feesFullAmount')
            jQuery.each(column2,function(){
                sum += parseFloat($(this).val());
            });
            
            $('#feesTotalAmount').val(sum);
        }
        $(document).ready(function() {
            
            
            $("#dataTable").keyup(function(){
                
                var sum = 0.0;
                var totalSum=0.0;
                var column2 = $('.feesFullAmount')
                jQuery.each(column2,function(){
                    sum += parseFloat($(this).val());
                });
                
                $('#feesTotalAmount').val(sum);
                
            });
            $("#dataTable").click(function(){
                
                var sum = 0.0;
                var totalSum=0.0;
                var column2 = $('.feesFullAmount')
                jQuery.each(column2,function(){
                    sum += parseFloat($(this).val());
                });
                
                $('#feesTotalAmount').val(sum);
               
            });
        });
        
        var feescat=[
                     <c:forEach varStatus="status" items="${otherfeescategory}" var="fees">{
                             value:'<c:out default="0" value="${fees.feescategoryname}" />',
                             particularname:'<c:out default="0" value="${fees.particularname}" />',
                             price:'<c:out default="0" value="${fees.amount}" />',
                             id:'<c:out default="0" value="${fees.idfeescategory}" />',
                             year:'<c:out default="0" value="${fees.academicyear}" />'
                             }<c:if test="${!status.last}">,</c:if>
                     </c:forEach>
                     ];
        
        $(function() {
            
            var addFeesCatButtonID="#addFeesCat";
            var removeFeesCatButtonID="#removeFeesCat";
            $( addFeesCatButtonID )
            .button({
                icons: {
                    primary: "ui-icon-plus"
                }
            })
            .click(function() {
                addRow();
                return false;
            });
            $(removeFeesCatButtonID)
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
        
        function SelectAll(id)
        {
        	
            document.getElementById("feesCount_"+id).focus();
            document.getElementById("feesCount_"+id).select();
        }
        function calculate(value2) {
        	var feesCount=document.getElementById("feesCount_"+value2).value;
        	
        	        	
            var feesCat=document.getElementById("hiddenfees_amount_"+value2).value;
            var feesCount=document.getElementById("feesCount_"+value2).value;
            var feesConcession=document.getElementById("feesConcession_"+value2).value;
            var final1=document.getElementById("hiddenfees_full_amount_"+value2);
            	
            	//var concession = ((feesCat*feesCount)*feesConcession)/100;(% concession)
            	//feesConcession (direct amount)
                final1.value=(feesCat*feesCount)-feesConcession;
           
        }
       
    function addRow() {
        var rowCount = document.getElementById('dataTable').rows.length;    
        var col1="<td class='dataTextInActive'><input type='checkbox' class = 'chcktbl' id=fees_"+rowCount+" /><input type='hidden' class='feesStatus' name='feesStatuses' id=fees_status_"+rowCount+" value='not set' /><input type='hidden' class='feesId' name='feesIDS' id=fees_id_"+rowCount+" value='' /></td>";
        var col2="<td class='dataTextInActive'><input class='feesName'   type='text' name='feesNames' id=fees_name_"+rowCount+" /></td>";
        var col3="<td class='dataTextInActive'><input class='feesYear'   type='text' name='feesYears' id=fees_year_"+rowCount+" readonly/></td>";
        var col4="<td class='dataTextInActive'><input class='feesAmount' type='text' value='0'   name='fessCat'  id=hiddenfees_amount_"+rowCount+" /></td>";
        var col5="<td class='dataTextInActive'><input type='text' value='0' onclick='SelectAll("+rowCount+");calculate("+rowCount+");' onfocus='SelectAll("+rowCount+")' onkeyup='calculate("+rowCount+")' name='feesCount' id=feesCount_"+rowCount+" /><input type='hidden' value='0' name='feesConcession' id=feesConcession_"+rowCount+" /></td>";
        /* var col5="<td class='dataTextInActive'><input type='text' value='0' name='feesConcession' id=feesConcession_"+rowCount+" /></td>"; */
        var col6="<td class='dataTextInActive'><input class='feesFullAmount' type='text' value='0'   name='fessFullCat'  id=hiddenfees_full_amount_"+rowCount+" /></td>";
        /* var col4="<td class='dataTextInActive'><input type='text' value='1' onclick='calculate("+rowCount+")'  onkeyup='calculate("+rowCount+")' name='feesQuantities' id=fees_quantity_"+rowCount+" /><input type='hidden'   id=hiddenfees_quantity_"+rowCount+" value='' /></td>"; */
        /* var col4="<td class='dataTextInActive'><select  onchange='calculate("+rowCount+")'  name='feesQuantities' id=fees_quantity_"+rowCount+"><option></option><option>JAN</option><option>Feb</option><option>MAR</option><option>APR</option><option>MAY</option><option>JUN</option><option>JUL</option><option>AUG</option><option>SEP</option><option>OCT</option><option>NOV</option><option>DEC</option></select><input type='hidden'   id=hiddenfees_quantity_"+rowCount+" value='' /></td>"; */
        /* var col4="<td class='dataTextInActive'><input class='feesAmount' type='text' value='0'      name='feesAmounts' id=fees_amount_"+rowCount+" /></td>"; */
        var newRow = $("<tr class='trClass'>"+col1+col2+col3+col4+col5+col6+"</tr>");
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
                    $("#fees_year_"+rowCount).val( ui.item.year );
                    //$("#hiddenfees_full_amount_"+rowCount).val( ui.item.price );
                   
                },
                focus: function( event, ui ) {
                    $( "#fees_name_"+rowCount).val( ui.item.name );
                    $( "#fees_status_"+rowCount ).val("not set");
                    $( "#fees_id_"+rowCount ).val( ui.item.id );
                    $( "#hiddenfees_amount_"+rowCount).val( ui.item.price );
                    $("#fees_year_"+rowCount).val( ui.item.year );
                    //$( "#hiddenfees_full_amount_"+rowCount).val( ui.item.price );
                   
                    return true;
                },
                select: function( event, ui ) {
                    $( "#fees_name_"+rowCount).val( ui.item.value );
                    $( "#fees_id_"+rowCount ).val( ui.item.id );
                    $( "#fees_status_"+rowCount ).val("set");
                    $( "#hiddenfees_amount_"+rowCount).val( ui.item.price );
                    $("#fees_year_"+rowCount).val( ui.item.year );
                   // $( "#hiddenfees_full_amount_"+rowCount).val( ui.item.price );
                   
                    return true;
                }
            }).data( "autocomplete" )._renderItem = function( ul, item ) {
                return $( "<li></li>" )
                .data( "item.autocomplete", item )
                .append( "<a><b> " + item.value +" of "+item.year+":-</b> <b> "+item.particularname +"</b></a>" )
                .appendTo( ul );
            };
        });
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
           
            
            var sum = 0.0;
            var totalSum=0.0;
            var column2 = $('.feesAmount')
            jQuery.each(column2,function(){
                sum += parseFloat($(this).val());
            });
            totalSum=sum;
            
            $('#feesTotalAmount').val(totalSum);
            
            calculateGrandTotal();
            //$('#grandTotalAmount').val(0);
        }catch(e) {
            alert(e);
        }
    }
        </script>

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
<body>
	<form id="form1" action="/abc/StampFeesProcess/applyotherFees" method="POST">

		<div id="effect" class="ui-widget-content ui-corner-all">
			<div id="tabs">
				<ul>
					<li><a href="#tabs-1">Other Stamp Fees</a></li>

				</ul>
				<div id="tabs-1">
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0" id="table1" style="display: block">

						<tr>
							<td class="alignRightFields">Name &nbsp;</td>
							<td width="12%" align="left"><label> <input
									name="namesearch" type="text" class="myclass" id="namesearch"
									size="36"">
							</label></td>
							
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						
						<tr>
							<td></td>
							
							<td class="alignRightFields">OR</td>
						</tr>
					
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td class="alignRightFields">Class &nbsp;</td>
							<td width="90%"><label> 
								<select name="classsearch" id="classsearch"
									style="width: 120px;">
										<option selected></option>
										<c:forEach items="${classdetailslist}" var="classdetailslist">
										<c:if test="${(classdetailslist.classdetails != '')}">
											<option value="${classdetailslist.classdetails}">
												<c:out value="${classdetailslist.classdetails}" />
											</option>
										</c:if>
										</c:forEach>
								</select>
							</label> <label> 
									<select name="secsearch" id="secsearch"
									style="width: 120px;">
										<option selected></option>
										<c:forEach items="${classdetailslist}" var="classdetailslist">
										<c:if test="${(classdetailslist.section != '')}">
											<option value="${classdetailslist.section}">
												<c:out value="${classdetailslist.section}" />
											</option>
										</c:if>
										</c:forEach>
								</select>
							</label>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td width="30%" class="alignRight"></td>
							<!-- <td width="30%" class="alignRight">&nbsp;</td> -->
							<td width="30%" class="alignRight">&nbsp;&nbsp;&nbsp;&nbsp;
								<button id="search">Search</button>
							</td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
					</table>
					<div class="alignRightFields">
						Fees Category:
						<button id="addFeesCat">Add</button>
						<button id="removeFeesCat">Remove</button>
						<input
									name="currentyear" type="hidden" value="${currentYear}" class="myclass" id="currentyear"
									size="36"">
					</div>
					<TABLE id="dataTable" width="100%" border="0">
						<thead>
							<tr>
								<td class="headerText"><INPUT type="checkbox"
									id="selectAll" name="selectAll"
									onclick="selectAllRow('dataTable')" /></td>
								<td class="headerText">Fees Category</td>
								<td class="headerText">Academic Year</td>
								<td class="headerText">Fees Amount</td>
								<td class="headerText">No.of installments in a Year</td>
								<td class="headerText">Fees Total Amount</td>



							</tr>
						</thead>
						<tbody>

						</tbody>
						<tfoot>
							<tr>

								<td colspan="4" align="right">Total&nbsp;&nbsp;</td>
								<td align="center"><input type="text"
									name="feesTotalAmount" id="feesTotalAmount" value="0" /></td>
							</tr>

						</tfoot>
					</TABLE>

				</div>
			</div>
		</div>

		<div style="overflow: scroll; height: 600px">
			<table width="100%">
				<tr>
					<td class="headerTD">Search result</td>
				</tr>
			</table>
			<table width="100%" border="0" style="border-color: #4b6a84;"
				id="myTable">

				<thead>
					<tr>
						<th class="headerText"><input type="checkbox" id="chckHead" /></th>
						<th title="click to sort" class="headerText">Admission Number</th>
						<th title="click to sort" class="headerText">Name&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
						<th title="click to sort" class="headerText">Class &
							Sec&nbsp;</th>
						<th title="click to sort" class="headerText">Admission Date</th>
						<th title="click to sort" class="headerText">Admission Type</th>
					</tr>
				</thead>

				<tbody>
					<c:forEach items="${searchStudentList}" var="Parents">

						<tr class="trClass" style="border-color: #000000" border="1"
							cellpadding="1" cellspacing="1">
							<td class="dataText"><input type="checkbox"
								id="<c:out value="${Parents.student.sid}"/>" class="chcktbl"
								name="studentIDs"
								value="<c:out value="${Parents.student.sid}"/>" /></td>
							<td class="dataTextInActive"><a class="dataTextInActive"
								href="/abc/StudentProcess/ViewDetails?id=<c:out value='${Parents.student.sid}'/>&urlbranchid=<c:out value='${Parents.student.branchid}'/>"><c:out
										value="${Parents.student.admissionnumber}" /></a></td>
							<td class="dataText"><c:out value="${Parents.student.name}" /></td>
							<td class="dataText">
							 <c:forEach var="splt" items="${fn:split(Parents.student.classstudying,'--')}">
								    ${splt} 
									</c:forEach>
							<td class="dataText"><c:out
									value="${Parents.student.admissiondate}" /></td>
						 	<td class="dataText">
						 		<c:set var="classstudying" value="${fn:split(Parents.student.classstudying, '_')}" />
						 		<c:set var="classadmitted" value="${fn:split(Parents.student.classadmittedin, '_')}" />
						 		 <c:choose>
                                <c:when test="${classstudying[0] == classadmitted[0]}">
                                   <c:out value="New Admission" />
                                </c:when>
                                <c:otherwise>
                                    <c:out value="Old Admission" />
                                </c:otherwise>
                            </c:choose>
						 	</td>


						</tr>
					</c:forEach>
				</tbody>
				<tfoot>
					<tr>
						<td class="footerTD" colspan="2"><input value="Apply"
							type="submit" id="delete" />
							<!-- <input value="Delete Stamp Fees"
							type="submit" id="deleteStamp" /> --></td>


					</tr>
				</tfoot>
			</table>

		</div>


	</form>

</body>
</html>
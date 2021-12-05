<%-- 
    Document   : stampfees
    Created on : Jul 24, 2012, 4:07:26 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP Page</title>
<link rel="stylesheet" href="css/datePicker/jquery-ui-1.8.18.custom.css">


<link rel="stylesheet" href="css/datePicker/demos.css">
<script type="text/javascript" src="js/datePicker/jquery-1.7.1.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/jquery.ui.dialog.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/jquery.ui.autocomplete.js"></script>
<script type="text/javascript" src="js/datePicker/ui/jquery.ui.core.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/jquery.ui.widget.js"></script>

<script type="text/javascript"
	src="js/datePicker/ui/jquery.ui.accordion.js"></script>
<script type="text/javascript" src="js/datePicker/ui/sliderAccess.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/jquery-ui-timepicker-addon.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/jquery.ui.position.js"></script>
<script type="text/javascript" src="js/datePicker/ui/jquery.ui.mouse.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/jquery.ui.draggable.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/jquery.ui.resizable.js"></script>

<script type="text/javascript"
	src="js/datePicker/ui/jquery.effects.slide.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/jquery.effects.bounce.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/jquery.effects.clip.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/jquery.effects.transfer.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/jquery.effects.blind.js"></script>
<style type="text/css">
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

<!--
.labelCss {
	font-family: Tahoma;
	font-size: 11px;
	font-weight: bold;
}

.dataTextInActive {
	border-radius: 1px;
	font-family: Tahoma;
	color: #4b6a84;
	font-size: 12px;
	font-weight: bold;
	letter-spacing: normal;
	text-align: center;
	vertical-align: top;
	text-decoration: none;
}

.headerText {
	border-radius: 3px;
	font-family: Tahoma;
	font-size: 12px;
	background-color: #4b6a84;
	color: #FFFFFF;
	font-weight: normal;
	width: auto;
	height: 22px;
	vertical-align: middle;
	text-align: center;
	background-image:
		url("images/ui-bg_diagonals-small_50_466580_40x40.png");
}

.headerTD {
	background-image:
		url("images/ui-bg_diagonals-small_50_466580_40x40.png");
	color: #FFFFFF;
	font-family: Tahoma;
	font-size: 13px;
	text-transform: uppercase;
	text-align: center;
	font-weight: bold;
}

.smallheaderTD {
	color: #4b6a84;
	font-family: Tahoma;
	font-size: 11px;
	text-transform: uppercase;
	text-align: left;
	font-weight: bold;
}

.textFieldFixedWidth {
	width: 57px;
}

.subHeaderTD {
	color: #325F6D;
	font-family: Tahoma;
	font-size: 11px;
	text-transform: uppercase;
	text-align: left;
	font-weight: bold;
}

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
        
        function calculateGrandTotal() {
            var sum = 0.0;
            var column2 = $('.feesAmount')
            jQuery.each(column2,function(){
                sum += parseFloat($(this).val());
            });
            
            $('#feesTotalAmount').val(sum.toPrecision(6));

        }
        $(document).ready(function() {
            
            
            $("#dataTable").keyup(function(){
                
                var sum = 0.0;
                var totalSum=0.0;
                var column2 = $('.feesAmount')
                jQuery.each(column2,function(){
                    sum += parseFloat($(this).val());
                });
                
                $('#feesTotalAmount').val(sum.toPrecision(6));
                
            });
            $("#dataTable").click(function(){
                
                var sum = 0.0;
                var totalSum=0.0;
                var column2 = $('.feesAmount')
                jQuery.each(column2,function(){
                    sum += parseFloat($(this).val());
                });
                
                $('#feesTotalAmount').val(sum.toPrecision(6));
               
            });


        });
        
        var feescat=[
                     <c:forEach varStatus="status" items="${feescategory}" var="fees">{
                             value:'<c:out default="0" value="${fees.feescategory}" />',
                             particularname:'<c:out default="0" value="${fees.particularname}" />',
                             price:'<c:out default="0" value="${fees.amount}" />',
                             id:'<c:out default="0" value="${fees.idfeescategory}" />'
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

        function calculate(value2) {
            //var val1=value1.value;
           
            var val1=0;
            var val2=document.getElementById("hiddenfees_amount_"+value2).value;
           /*  var final1=document.getElementById("fees_amount_"+value2); */
            if(parseInt(val1) == 0 ){
                /* final1.value=0; */
                
            }
            
            else{
                /* final1.value=(val2); */
            }    
        }
       
    function addRow() {
        var rowCount = document.getElementById('dataTable').rows.length;    
        var col1="<td class='dataTextInActive'><input type='checkbox' class = 'chcktbl' id=fees_"+rowCount+" /><input type='hidden' class='feesStatus' name='feesStatuses' id=fees_status_"+rowCount+" value='not set' /><input type='hidden' class='feesId' name='feesIDS' id=fees_id_"+rowCount+" value='' /></td>";
        var col2="<td class='dataTextInActive'><input class='feesName'  onclick='calculate("+rowCount+")' type='text' name='feesNames' id=fees_name_"+rowCount+" /></td>";
        var col3="<td class='dataTextInActive'><input class='feesAmount' type='text' value='0'   name='fessCat'  id=hiddenfees_amount_"+rowCount+" /></td>";
        /* var col4="<td class='dataTextInActive'><input type='text' value='1' onclick='calculate("+rowCount+")'  onkeyup='calculate("+rowCount+")' name='feesQuantities' id=fees_quantity_"+rowCount+" /><input type='hidden'   id=hiddenfees_quantity_"+rowCount+" value='' /></td>"; */
        /* var col4="<td class='dataTextInActive'><select  onchange='calculate("+rowCount+")'  name='feesQuantities' id=fees_quantity_"+rowCount+"><option></option><option>JAN</option><option>Feb</option><option>MAR</option><option>APR</option><option>MAY</option><option>JUN</option><option>JUL</option><option>AUG</option><option>SEP</option><option>OCT</option><option>NOV</option><option>DEC</option></select><input type='hidden'   id=hiddenfees_quantity_"+rowCount+" value='' /></td>"; */
        /* var col4="<td class='dataTextInActive'><input class='feesAmount' type='text' value='0'      name='feesAmounts' id=fees_amount_"+rowCount+" /></td>"; */
        var newRow = $("<tr class='trClass'>"+col1+col2+col3+"</tr>");
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
                   
                   

                },
                focus: function( event, ui ) {
                    $( "#fees_name_"+rowCount).val( ui.item.name );
                    $( "#fees_status_"+rowCount ).val("not set");
                    $( "#fees_id_"+rowCount ).val( ui.item.id );
                    $( "#hiddenfees_amount_"+rowCount).val( ui.item.price );
                   
                   

                    return true;
                },
                select: function( event, ui ) {
                    $( "#fees_name_"+rowCount).val( ui.item.value );
                    $( "#fees_id_"+rowCount ).val( ui.item.id );
                    $( "#fees_status_"+rowCount ).val("set");
                    $( "#hiddenfees_amount_"+rowCount).val( ui.item.price );
                   
                   
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
            
            $('#feesTotalAmount').val(totalSum.toPrecision(6));
            
            calculateGrandTotal();
            //$('#grandTotalAmount').val(0);
        }catch(e) {
            alert(e);
        }
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
        $( "#add" )
        .button()
        .click(function() {
            runEffect();

        });
        $( "#save" )
        .button()
        .click(function() {
            addPatient();

        });
        $( "#effect" ).hide();
    });
             function addPatient(){
                var form1=document.getElementById("form1");
                form1.action="Controller?process=PatientProcess&action=add";
                form1.submit();
            }
        </script>
<script type="text/javascript">
            $(function() {
                $( "#tabs" ).tabs();
            });

            function searchForFees() {
        		var form1 = document.getElementById("form1");
        		form1.action = "Controller?process=StampFeesProcess&action=search";
        		form1.method = "POST";
        		form1.submit();

        	}

        	$(function() {

        		$("#search").button().click(function() {
        			searchForFees();
        		});
        		

        	});
        </script>
</head>
<body>
	<form id="form1"
		action="Controller?process=FeesCollection&action=feesAdd"
		method="post" onkeypress="if (event.keyCode == 92) addRow();">
		<div style="height: 28px">
			<!--<a href="#" id="button" class="ui-state-default ui-corner-all">Add Medicine</a>-->


			<table width="100%">
				<thead>
					<tr>
						<th colspan="3" class="headerTD">Stamp Fees</th>

					</tr>
				</thead>

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
						<td class="alignRightFields">Class &nbsp;</td>
						<td width="70%"><label> <select name="classsearch"
								id="classsearch" style="width: 150px">
									<option selected></option>
									<option>nursery</option>
									<option>L.K.G</option>
									<option>U.K.G</option>
									<option>I</option>
									<option>II</option>
									<option>III</option>
									<option>IV</option>
									<option>V</option>
									<option>VI</option>
									<option>VII</option>
									<option>VIII</option>
									<option>IX</option>
									<option>X</option>
							</select>

						</label> <label> <select name="secsearch" id="secsearch"
								style="width: 120px">
									<option selected></option>
									<option>A</option>
									<option>B</option>
									<option>C</option>
									<option>D</option>
									<option>E</option>
									<option>F</option>
									<option>G</option>

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
				<tbody>
					<tr>



						<td class="alignRightFields">Fees Category:
							<button id="addFeesCat">Add</button>&nbsp;&nbsp;&nbsp;
							<button id="removeFeesCat">Remove</button>
						</td>
					</tr>


				</tbody>
			</table>
			<table id="dossageTable">
				<tbody></tbody>

			</table>
			<TABLE id="dataTable" width="100%" border="1">
				<thead>
					<tr>
						<td class="headerText"><INPUT type="checkbox" id="selectAll"
							name="selectAll" onclick="selectAllRow('dataTable')" /></td>
						<td class="headerText">Fees Category</td>
						<td class="headerText">Fees Amount</td>



					</tr>
				</thead>
				<tbody>

				</tbody>
				<tfoot>
					<tr>

						<td colspan="2" align="right">Total&nbsp;&nbsp;</td>
						<td align="center"><input type="text" name="feesTotalAmount"
							id="feesTotalAmount" value="0" /></td>
					</tr>

				</tfoot>
			</TABLE>

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
							<th title="click to sort" class="headerText">Admission
								Number</th>
							<th title="click to sort" class="headerText">Name&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
							<th title="click to sort" class="headerText">Class &
								Sec&nbsp;</th>
							<th title="click to sort" class="headerText">Admission Date</th>



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
									href="Controller?process=StudentProcess&action=ViewDetails&id=<c:out value='${Parents.student.sid}'/>"><c:out
											value="${Parents.student.admissionnumber}" /></a></td>
								<td class="dataText"><c:out value="${Parents.student.name}" /></td>
								<td class="dataText"><c:out
										value="${Parents.student.classstudying}" /></td>
								<td class="dataText"><c:out
										value="${Parents.student.admissiondate}" /></td>


							</tr>
						</c:forEach>
					</tbody>
					<tfoot>
						<tr>
							<td class="footerTD" colspan="2"><input value="Apply"
								type="submit" id="delete" /></td>

						</tr>
					</tfoot>
				</table>

			</div>
	</form>

</body>
</html>

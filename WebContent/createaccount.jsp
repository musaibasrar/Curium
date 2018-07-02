<%--
    Document   : create account
    Created on : feb 18, 2018, 12:42:28 PM
    Author     : Musaib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Create Account</title>
<link rel="stylesheet" href="css/datePicker/jquery-ui-1.8.18.custom.css">
<link rel="stylesheet" href="css/datePicker/demos.css">
<style type="text/css">
.footerTD{
                border-radius:6px;
                background-color:#4b6a84;


                text-align: left;


            }
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
		url("images/ui-bg_diagonals-small_50_466580_40x40.png");
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
		url("images/ui-bg_diagonals-small_50_466580_40x40.png");
	color: #FFFFFF;
	font-family: Tahoma;
	font-size: 13px;
	text-transform: uppercase;
	text-align: center;
	font-weight: bold;
	height: 22px;
}
</style>

<link rel="stylesheet" href="css/validation/jquery.ketchup.css">
<script type="text/javascript" src="js/datePicker/jquery-1.7.1.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
<script type="text/javascript" language="javascript"
	src="js/dataTable/jquery.dataTables.js"></script>
<script type="text/javascript" src="js/datePicker/ui/jquery.ui.core.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/jquery.ui.widget.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="js/datePicker/ui/jquery.ui.tabs.js"></script>
<script type="text/javascript" src="js/datePicker/ui/sliderAccess.js"></script>

<script type="text/javascript"
	src="js/validation/jquery.ketchup.all.min.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/jquery.ui.button.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/jquery.ui.accordion.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/jquery.effects.core.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/jquery.ui.accordion.js"></script>
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
<script type="text/javascript"
	src="js/datePicker/ui/ScrollableGridPlugin.js"></script>
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
	$(function() {
		// run the currently selected effect
		function runEffect() {

			var clipEffect = 'blind';
			var options = {};
			$("#effect").toggle(clipEffect, options, 1000);
		}
		;
		// set effect from select menu value
		$("#add").button().click(function() {
			runEffect();
			return false;
		});
	});
</script>
<script type="text/javascript" src="js/datetimepicker_css.js"></script>
<script type="text/javascript">
	function addFeesCategory() {
		var form1 = document.getElementById("form1");
		form1.action = "Controller?process=FeesProcess&action=addFeesParticular";
		form1.method = "POST";
		form1.submit();

	}
	$(function() {

		$("#tabs").tabs();
		$("#save").button().click(function() {
			saveaccount();
		});
		$("#effect").hide();
		
		$("#reset").button().click(function() {
		});
		
		$("#openingbalance").keypress(function (e) {
  		     //if the letter is not digit then display error and don't type anything
  		     if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57) && e.which !=46) {
  		               return false;
  		    }
  		   });
	});

	 $(function(){
         $("#delete").button({
             icons:{
                 primary: "ui-icon-trash"
             }
         }).click(function(){
             deleteRecords();
             return false;

         });
         $('#chckHead').click(function () {
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
         $('.chcktbl').click(function () {
             var length = $('.chcktbl:checked').length;
             var trLength=$('.trClass').length;
             alert(tdLength);
             if (length > trLength) {

                 $('.chcktbl:not(:checked)').attr('disabled', true);
             }
             else {
                 $('.chcktbl:not(:checked)').attr('disabled', false);
             }
         });
         
     });
	 function saveaccount(){

		 var form1=document.getElementById("form1");
         form1.action="Controller?process=AccountProcess&action=saveAccount";
        form1.submit();
         
     }
	 
	function deleteRecords(){
         
		if(confirm('Are you sure, you want to delete the account?')){
			var form1=document.getElementById("form1");
	         form1.action="Controller?process=AccountProcess&action=deleteAccount";
	        form1.submit();
		}
         
         
     }
</script>
<script type="text/javascript">

    function dropdowndist() {
        var distlistitem = document.getElementById("sgname");
        var distlistitemtext = distlistitem.options[distlistitem.selectedIndex].text;

        if (distlistitemtext == "New Sub-Group") {
            document.getElementById("sgname").style.display = "none";
            document.getElementById("subgroupname").style.display = "none";
            document.getElementById("newsubgr").style.display = '';
        }
    }
    
    var xmlHttp;
    var count;
    function getSubGroup() {

		var selected=document.getElementById('groupname').value;

		 if (typeof XMLHttpRequest != "undefined") {
			 xmlHttp = new XMLHttpRequest();
            
         } else if (window.ActiveXObject) {
        	 xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
             
         }
		xmlHttp.onreadystatechange = stateChanged;
		xmlHttp.open("GET", "AjaxController?process=SubGroupName&action=getSubGroupNames&groupname="+selected,true);
		xmlHttp.send(null);
	}
    
	function stateChanged() {

		if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete") {
			//document.getElementById("mydivmobile").innerHTML = xmlHttp.responseText;
			document.getElementById("subgroupname").innerHTML = xmlHttp.responseText;
			
			/* count = xmlHttp.responseXML.getElementsByTagName("subgroup")[0];
			var childCount=count.childNodes[0].nodeValue;
			document.getElementById("mydivmobile").innerHTML = childCount;
			document.getElementById("subgroupname").innerHTML = childCount; */
			/* var showdata = xmlHttp.responseText;
			document.getElementById("mydivmobile").innerHTML = showdata; */
		    document.getElementById("sgname").style.display = '';
            document.getElementById("subgroupname").style.display = '';
            document.getElementById("newsubgr").style.display = "none";
		}
	}
	function GetXmlHttpObject() {
		var xmlHttp = null;
		try {
			xmlHttp = new XMLHttpRequest();
		} catch (e) {
			try {
				xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
			} catch (e) {
				xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
		}
		return xmlHttp;
	}

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
	<form id="form1"
		 method="POST">
		<div style="height: 28px">
			<button id="add">Create Account</button>
			<br />
		</div>

		<div id="effect" class="ui-widget-content ui-corner-all">
			<div id="tabs">
				<ul>
					<li><a href="#tabs-1">Create Account</a></li>
				</ul>



				<div id="tabs-1">
					<table width="100%" border="0" align="center" id="table1">
						

						<tr>

							<td width="30%" class="alignRight"><label> <font
									color="red"><div id="mydiv"></div></font>
							</label></td>
							<td width="20%" class="alignRight"></td>
							<td class="alignRight"><font color="red"><div
										id="mydivmobile"></div></font></td>
						</tr>

						<tr>
							<td class="alignRight">Group Name&nbsp;</td><br>
							<td width="28%"> <label>
									<select name="groupname" id="groupname" onchange="getSubGroup()"
									style="width: 240px;">
										<option selected>Select Group</option>

										<c:forEach items="${accountgroupmaster}" var="accountgroupmaster">

											<option value="${accountgroupmaster.accountgroupid}">
												<c:out value="${accountgroupmaster.accountgroupname}" />
											</option>


										</c:forEach>

								</select></label> </td>
							<tr>
							<td><br /></td>
						</tr>
						
						<tr>
						<td width="30%" class="alignRight">Sub-Group Name &nbsp;</td>
							<td width="12%" align="left" id="subgroupname"><label> <select name="subgroupname" id="sgname"  onchange="dropdowndist()"
									style="width: 240px" ">
									<option >Select Sub-Group</option>
									<%-- 	<option selected>${count}</option>

										<c:forEach items="${count}" var="accountsubgroupmaster">

											<option>
												<c:out value="${accountsubgroupmaster.accountsubgroupname}" />
											</option>


										</c:forEach>
										<c:forEach items="${accountsubgroupmaster}" var="accountsubgroupmaster">

											<option>
												<c:out value="${accountsubgroupmaster.accountsubgroupname}" />
											</option>


										</c:forEach>
										<option>New Sub-Group</option> --%>
								</select>
							</label>
							
							</td>
							<td width="12%" align="left" id="newsubgr" style="display: none;">
							<label>
                               <input name="newsubgroup" id="newsubgroup" type="text" size="36"  placeholder="Add New Sub-Group" />
                            </label>
							</td>
							
							
						</tr>
						
						<tr>
							<td><br /></td>
						</tr>
						
						<tr>
						<td width="30%" class="alignRight">Account Name &nbsp;</td>
							<td width="12%" align="left"><label> 
							<input name="accountname" id="accountname" type="text" size="36" required/>
							</label>
						</tr>
						
						<tr>
							<td><br /></td>
						</tr>


						<tr>
						<td width="30%" class="alignRight">Opening Balance &nbsp;</td>
							<td width="12%" align="left"><label> 
							<input name="openingbalance" id="openingbalance" type="text" value="0" size="36"/>
							</label>
						</tr>
						
						
						<tr>
							<td><br /></td>
						</tr>


						<div>
							<table width="100%">
								<tr>

									<td><br /></td>
								</tr>
								<tr>
									<td align="center">

										<button id="save">Save</button>

										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<button id="reset">Reset</button>

									</td>


								</tr>
							</table>

						</div>
						
			</div>
		</div>

		<div style="overflow: scroll; height: 600px">
			<table width="100%">
				<tr>
					<td class="headerTD">Account Details</td>
				</tr>
			</table>
			<table width="100%" border="0" style="border-color: #4b6a84;"
				id="myTable">

				<thead>
					<tr>
						<th class="headerText"><input type="checkbox" id="chckHead" /></th>
						<th title="click to sort" class="headerText">Account Name<img
							alt=" " style="position: relative; top: 4px;"
							src="css/dataTable/images/sort_both.png" /></th>
						<th title="click to sort" class="headerText">Opening Balance<img
							alt=" " style="position: relative; top: 4px;"
							src="css/dataTable/images/sort_both.png" /></th>
						<th title="click to sort" class="headerText">Sub-Group<img
							alt=" " style="position: relative; top: 4px;"
							src="css/dataTable/images/sort_both.png" />&nbsp;&nbsp;
						</th>
						<th title="click to sort" class="headerText">Group<img
							alt=" " style="position: relative; top: 4px;"
							src="css/dataTable/images/sort_both.png" />&nbsp;&nbsp;
						</th>

					</tr>
				</thead>

				<tbody>

					<c:forEach items="${accountdetailsbalance}" var="accountdetailsbalance">

						<tr style="border-color: #000000" border="1" cellpadding="1"
							cellspacing="1">
							<td class="dataText"><input type="checkbox"
								id="<c:out value="${accountdetailsbalance.accountdetailsbalanceid}"/>" class="chcktbl"
								name="accountids"
								value="<c:out value="${accountdetailsbalance.accountdetailsbalanceid}-${accountdetailsbalance.accountDetails.accountdetailsid}"/>" /></td>
								
							<td class="dataText"><c:out value="${accountdetailsbalance.accountDetails.accountname}" /></td>
								<td class="dataText"><c:out value="${accountdetailsbalance.openingbalance}" />(${accountdetailsbalance.crdr})</td>
							<td class="dataText"><c:out value="${accountdetailsbalance.accountDetails.accountSubGroupMaster.accountsubgroupname}" /></td>
							<td class="dataText"><c:out value="${accountdetailsbalance.accountDetails.accountGroupMaster.accountgroupname}" /></td>
						</tr>
					</c:forEach>




				</tbody>
				<tfoot><tr>
                            <td  class="footerTD" colspan="2" ><button id="delete">Delete</button> 
                    
                        </tr></tfoot>
			</table>

		</div>


	</form>

</body>
</html>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
    <title>Update Enquiry Form</title>
<link rel="stylesheet" href="/iqra/css/datePicker/jquery-ui-1.8.18.custom.css">
<link rel="stylesheet" href="/iqra/css/validation/jquery.ketchup.css">
<script type="text/javascript"
	src="/iqra/js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
<script src="/iqra/js/datePicker/jquery-1.7.1.js"></script>
<script src="/iqra/js/datePicker/ui/jquery.ui.core.js"></script>
<script src="/iqra/js/datePicker/ui/jquery.ui.widget.js"></script>
<script src="/iqra/js/datePicker/ui/jquery.ui.datepicker.js"></script>
<script src="/iqra/js/datePicker/ui/jquery.ui.tabs.js"></script>
<script src="/iqra/js/datePicker/ui/sliderAccess.js"></script>
<script src="/iqra/js/datePicker/ui/jquery-ui-timepicker-addon.js"></script>
<script src="/iqra/js/validation/jquery.ketchup.all.min.js"></script>
<script type="text/javascript"
	src="/iqra/js/datePicker/ui/jquery.ui.button.js"></script>
<link rel="stylesheet" href="/iqra/css/datePicker/demos.css">

<style>
   td{
        font-family: Tahoma;
	font-size: 14px;
	font-style: normal;
	text-transform: capitalize;
	color: #325F6D;
	text-align: left;
	vertical-align: middle;
	font-weight: bold;
    }
    
    @media print {
        button {
            display: none;
        }
    }
</style>

<style type="text/css">
.myclass {
	font-size: 1.3em;
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
	height: 28px;
	color: black;
	text-transform: capitalize;
	border-radius: 4px;
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

.alignLeft {
	font-family: Tahoma;
	font-size: 14px;
	font-style: normal;
	text-transform: capitalize;
	color: #325F6D;
	text-align: left;
	vertical-align: middle;
	font-weight: bold;
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
}

.alignRight {
	font-family: Tahoma;
	font-size: 12px;
	font-style: normal;
	text-transform: capitalize;
	color: #325F6D;
	text-align: right;
	vertical-align: middle;
	font-weight: bold;
}

.alignRightHead {
	font-family: Tahoma;
	font-size: 12px;
	font-style: normal;
	text-transform: capitalize;
	color: #325F6D;
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

/* Styling for the tab heading to match addStudent page */
#tabs ul li a {
    font-family: Arial, Helvetica, sans-serif;
    font-size: 14px;
    font-weight: bold;
    color: #325F6D;
    text-decoration: none;
    padding: 8px 12px;
}

#tabs ul li {
    display: inline;
    margin-right: 5px;
}

#tabs ul {
    list-style-type: none;
    margin: 0;
    padding: 0;
    border-bottom: 1px solid #5d7e9b;
}
</style>

<script type="text/javascript">
	$(function() {
		$("#tabs").tabs();
		$("#modify").button().click(function() {
			updateLoginDetail();
		});
	});
	
	function updateLoginDetail(){
		var form1=document.getElementById("form1");
		form1.action="/iqra/LoginProcess/updateDetailsOfLogin?id=<c:out value='${logindetail.lid}'/>";
		form1.submit();
	}
	
	function typeofrelation(){
		var listitem = document.getElementById("subscriber");
		var listitemtext = listitem.options[listitem.selectedIndex].text;
		alert(listitemtext);
		
		if(listitemtext=="1"){
			document.getElementById("typeofrelation").value="subscriber";
		}
	}
	
	function yesCheck(id) {
		if (id == "yes:male") {
			document.getElementById("no:male").checked = false;
		}
	}
	
	function noCheck(id) {
		if (id == "no:male") {
			document.getElementById("yes:male").checked = false;
		}
	}
</script>

<script>
	$(function() {
		$("#datepicker").datepicker({
			changeYear : true,
			changeMonth : true,
			dateFormat: 'dd/mm/yy',
			yearRange: "-50:+0"
		});
		$("#anim").change(function() {
			$("#datepicker").datepicker("option", "showAnim", $(this).val());
		});
	});
</script>
    </head>
   <body>
    <form method="post" id="form1">
        <div>
			<div id="tabs">
				<ul>
					<li><a href="#fragment-1">Update Login Detail</a></li>
				</ul>
				
				<div id="fragment-1">
					<table style="width: auto;height: auto;" border="0" align="center" id="table1">
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td class="alignLeft">User Name</td>
							<td><input type="text" name="username" value="${logindetail.username}" class="myclass"/></td>
							<td class="alignLeft" style="padding-left: 20px;">Password</td>
							<td><input type="text" name="password" value="${logindetail.password}" class="myclass"/></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td class="alignLeft">User Type</td>
							<td><input name="usertype" type="text" class="myclass" id="datepicker" size="20" autocomplete="false"   value=" <c:out default="" value="${logindetail.usertype}" />"></td>
							<td class="alignLeft" style="padding-left: 20px;"></td>
							<td></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
												<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						
						
						
						
						
						
						
						
						
						
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr align="center">
							<td class="alignRight">&nbsp;</td>
							<td align="center">
								<button id="modify" class="save">Update</button>
							</td>
							<td colspan="2">&nbsp;</td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
					</table>
				</div>
			</div>
		</div>
    </form>
    </body>
    </html>
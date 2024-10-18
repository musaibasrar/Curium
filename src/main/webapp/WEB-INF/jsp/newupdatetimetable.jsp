<%--
    Document   : Period Master
    Created on : APR 11, 2018, 3:49:45 PM
    Author     : Musaib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Student Details</title>

        <script type="text/javascript" language="JavaScript" src="/dolphin/js/motionpack.js"></script>
        <link rel="stylesheet" href="/dolphin/css/datePicker/jquery-ui-1.8.18.custom.css">
        <link rel="stylesheet" href="/dolphin/css/graph/jquery.jqplot.css">

        <link rel="stylesheet" href="/dolphin/css/datePicker/demos.css">
        <script type="text/javascript" src="/dolphin/js/datePicker/jquery-1.7.1.js"></script>
        <script type="text/javascript" src="/dolphin/js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
        <script type="text/javascript" src="/dolphin/js/datePicker/ui/jquery.ui.dialog.js"></script>
        <script type="text/javascript" src="/dolphin/js/datePicker/ui/jquery.ui.autocomplete.js"></script>
        <script type="text/javascript" src="/dolphin/js/datePicker/ui/jquery.ui.core.js"></script>
        <script type="text/javascript" src="/dolphin/js/datePicker/ui/jquery.ui.widget.js"></script>
        <script type="text/javascript" src="/dolphin/js/datePicker/ui/jquery.ui.datepicker.js"></script>
        <script type="text/javascript" src="/dolphin/js/datePicker/ui/jquery.ui.accordion.js"></script>
        <script type="text/javascript" src="/dolphin/js/datePicker/ui/sliderAccess.js"></script>
        <script type="text/javascript" src="/dolphin/js/datePicker/ui/jquery-ui-timepicker-addon.js"></script>

        <script  type="text/javascript" src="/dolphin/js/datePicker/ui/jquery.ui.position.js"></script>
        <script type="text/javascript" src="/dolphin/js/datePicker/ui/jquery.ui.mouse.js"></script>
        <script type="text/javascript" src="/dolphin/js/datePicker/ui/jquery.ui.draggable.js"></script>
        <script type="text/javascript" src="/dolphin/js/datePicker/ui/jquery.ui.resizable.js"></script>
        <script type="text/javascript" src="/dolphin/js/graph/jquery.jqplot.js"></script>
        <script  type="text/javascript" src="/dolphin/js/graph/plugins/jqplot.dateAxisRenderer.min.js"></script>
        <script type="text/javascript" src="/dolphin/js/graph/plugins/jqplot.barRenderer.min.js"></script>
        <script type="text/javascript" src="/dolphin/js/graph/plugins/jqplot.categoryAxisRenderer.min.js"></script>
        <script type="text/javascript" src="/dolphin/js/graph/plugins/jqplot.cursor.min.js"></script>
        <script type="text/javascript" src="/dolphin/js/graph/plugins/jqplot.highlighter.min.js"></script>
        <script type="text/javascript" src="/dolphin/js/graph/plugins/jqplot.dragable.min.js"></script>
        <script type="text/javascript" src="/dolphin/js/graph/plugins/jqplot.trendline.min.js"></script>

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
        </style>
        
        <script type="text/javascript">
            
            
<!--             function updatePeriods() {
        		
        		var form1 = document.getElementById("form1");
        		if(form1.checkValidity()) {
        			form1.action = "/dolphin/PeriodProcess/updatenewPeriodDetails";
        			form1.method = "POST";
        			form1.submit();
        		  }
        	}

           
            
      
        </script>

        
        
<script type="text/javascript">
$("#tabs").tabs();

$("#update").button().click(function() {
	updatePeriods();
	return false;
});

                                
                               
 -->                            </script>
                            
                            <script type="text/javascript">
	function updatePeriods() {
		var form1 = document.getElementById("form1");
		form1.action = "/dolphin/PeriodProcess/updatenewPeriodDetails";
		form1.method = "POST";
		form1.submit();

	}
	$(function() {

		$("#tabs").tabs();
		$("#update").button().click(function() {
			updatePeriods();
		});
		//$("#effect").hide();

	});
	
	</script>


</head>
  <%
//allow access only if session exists
String user = null;
if(session.getAttribute("userAuth") == null){
	response.sendRedirect("/dolphin/UserProcess/sessionTimeOut");
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
	<form id="form1">
		
		<div>
			<table width="100%">
				<tr>
					<td class="headerTD">TIME TABLE &nbsp;&nbsp;&nbsp;&nbsp;<select name='classsec' id='classsec'><option selected>${timetable.class_}</option>
					<c:forEach items="${classdetailslist}" var="classdetailslist">
												<option value="${classdetailslist.classdetails}">
													<c:out value="${classdetailslist.classdetails}" />
												</option>
										</c:forEach>
										</select>
				  <%--  <c:forEach items="${periodmasterlist}" var="periodmasterlist"> --%>

							<lable>Day start time<c:set var="item" value="${fn:split(timetable.daystart, ' ')}" />
							<c:set var="itempart" value="${fn:split(item[0], ':')}" />
							<select name='daystarttime' id='daystarttime'><option selected>
							<c:out value="${itempart[0]}" /></option>
							 <option value="1">1</option>
										<option value="2">2</option>
										<option value="3">3</option>
										<option value="4">4</option>
										<option value="5">5</option>
										<option value="6">6</option>
										<option value="7">7</option>
										<option value="8">8</option>
										<option value="9">9</option>
										<option value="10">10</option>
										<option value="11">11</option>
										<option value="12">12</option></select>
							</lable>
							<lable><input type="hidden" name="periodmasterid" value="${periodMasterid}" /></lable>
							<lable><input type="hidden" name="academicyear" value="${currentAcademicYear}" /></lable>
							<lable>
							<select name='daystartminutes' id='daystartminutes'><option selected>
							<c:out value="${itempart[1]}" /></option>
							<option value="00">00</option>
										<option value="05">05</option>
										<option value="10">10</option>
										<option value="15">15</option>
										<option value="20">20</option>
										<option value="25">25</option>
										<option value="30">30</option>
										<option value="35">35</option>
										<option value="40">40</option>
										<option value="45">45</option>
										<option value="50">50</option>
										<option value="55">55</option>
						  
						  </select></lable>
						  <lable>
						  <select name='daystartam' id='daystartam' ><option selected><c:out value="${item[1]}" /></option>
										<option value="AM">AM</option>
										<option value="PM">PM</option>
										
						  
						  </select>
						  </lable>
							
							<lable>Day end time<c:set var="item" value="${fn:split(timetable.dayend, ' ')}" />
							<c:set var="itempart" value="${fn:split(item[0], ':')}" />
							<select name='dayendtime' id='dayendtime'><option selected>
							<c:out value="${itempart[0]}" /></option>
							 <option value="1">1</option>
										<option value="2">2</option>
										<option value="3">3</option>
										<option value="4">4</option>
										<option value="5">5</option>
										<option value="6">6</option>
										<option value="7">7</option>
										<option value="8">8</option>
										<option value="9">9</option>
										<option value="10">10</option>
										<option value="11">11</option>
										<option value="12">12</option></select>
							</lable>
							<lable>
							<select name='dayendminutes' id='dayendminutes'><option selected>
							<c:out value="${itempart[1]}" /></option>
							<option value="00">00</option>
										<option value="05">05</option>
										<option value="10">10</option>
										<option value="15">15</option>
										<option value="20">20</option>
										<option value="25">25</option>
										<option value="30">30</option>
										<option value="35">35</option>
										<option value="40">40</option>
										<option value="45">45</option>
										<option value="50">50</option>
										<option value="55">55</option>
						  
						  </select></lable>
						  <lable>
						  <select name='dayendam' id='dayendam' ><option selected><c:out value="${item[1]}" /></option>
										<option value="AM">AM</option>
										<option value="PM">PM</option>
										
						  
						  </select>
							</lable>
							<lable>No.of period &nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="totalperiods" style="width:15px" value="<c:out value="${timetable.totalperiods}" />" readonly/></lable>
					<%-- </c:forEach> --%>
					</td>
				</tr>
				
			</table>
			<table width="100%" border="1" style="border-color: #4b6a84;"
				id="myTable">

				

				<tbody>
					<c:forEach items="${periodmap}" var="periodmap">

						<tr style="border-color: #000000" border="1" cellpadding="1"
							cellspacing="1">
							<td class="dataText"><label><select name='days' id='days'><option selected><c:out value="${periodmap.key}" /></option>
							<option value='monday'>MON</option>
							<option value='tuesday'>TUE</option>
							<option value='wednesday'>WED</option>
							<option value='thursday'>THU</option>
							<option value='friday'>FRI</option>
							<option value='saturday'>SAT</option>
							<option value='sunday'>SUN</option>
							</select></label></td>
							<c:forEach items="${periodmap.value}" var="periodmapvalue">
							<td><input type="hidden" name="periodid" value="${periodmapvalue.idperioddetails}"/></td>
							<td class="dataText"><label><select name='periods' id='periods'><option selected><c:out value="${periodmapvalue.periods}" /></option>
							<option value='period1'>Period-1</option>
							<option value='period2'>Period-2</option>
							<option value='period3'>Period-3</option>
							<option value='period4'>Period-4</option>
							<option value='period5'>Period-5</option>
							<option value='period6'>Period-6</option>
							<option value='period7'>Period-7</option>
							<option value='period8'>Period-8</option>
							<option value='period9'>Period-9</option>
							<option value='period10'>Period-10</option></select>
							</label><br><label><select name='subject' id='subject'><option selected><c:out value="${periodmapvalue.subject}" /></option>
						<c:forEach items="${listSubjectNames}" var="subjectnames">
												<option value="${subjectnames.subjectname}">
													<c:out value="${subjectnames.subjectname}" />
												</option>
										</c:forEach>
													</select>
							<br><label><select name='staff' id='staff'><option selected><c:out value="${periodmapvalue.staff}" /></option>
							<c:forEach items="${employeeList}" var="employee">
												<option>
													<c:out value="${employee.teachername}" />
												</option>
										</c:forEach>
							</select>
							</label>
							<br><label><c:set var="item" value="${fn:split(periodmapvalue.timings, 'To')}" />
							<c:set var="itempart" value="${fn:split(item[0], ':')}" />
						  <select name='periodstarttimehr' id='periodstarttimehr' ><option selected><c:out value="${itempart[0]}" /></option>
						                <option value="1">1</option>
										<option value="2">2</option>
										<option value="3">3</option>
										<option value="4">4</option>
										<option value="5">5</option>
										<option value="6">6</option>
										<option value="7">7</option>
										<option value="8">8</option>
										<option value="9">9</option>
										<option value="10">10</option>
										<option value="11">11</option>
										<option value="12">12</option>
						  
						  </select>
						  <select name='periodstarttimemin' id='periodstarttimemin' ><option selected><c:out value="${itempart[1]}" /></option>
										<option value="00">00</option>
										<option value="05">05</option>
										<option value="10">10</option>
										<option value="15">15</option>
										<option value="20">20</option>
										<option value="25">25</option>
										<option value="30">30</option>
										<option value="35">35</option>
										<option value="40">40</option>
										<option value="45">45</option>
										<option value="50">50</option>
										<option value="55">55</option>
						  
						  </select>
						  <select name='periodstarttimeam' id='periodstarttimeam' ><option selected><c:out value="${itempart[2]}" /></option>
										<option value="AM">AM</option>
										<option value="PM">PM</option>
										
						  
						  </select>to
						  <c:set var="itemparts" value="${fn:split(item[1], ' ')}" />
						    <c:set var="itempartss" value="${fn:split(itemparts[0], ':')}" />
						  <select name='periodendtimehr' id='periodendtimehr' ><option selected><c:out value="${itempartss[0]}" /></option>
						                <option value="1">1</option>
										<option value="2">2</option>
										<option value="3">3</option>
										<option value="4">4</option>
										<option value="5">5</option>
										<option value="6">6</option>
										<option value="7">7</option>
										<option value="8">8</option>
										<option value="9">9</option>
										<option value="10">10</option>
										<option value="11">11</option>
										<option value="12">12</option>
						  
						  </select>
						  <select name='periodendtimemin' id='periodendtimemin'><option selected><c:out value="${itempartss[1]}" /></option>
						                <option value="00">00</option>
										<option value="05">05</option>
										<option value="10">10</option>
										<option value="15">15</option>
										<option value="20">20</option>
										<option value="25">25</option>
										<option value="30">30</option>
										<option value="35">35</option>
										<option value="40">40</option>
										<option value="45">45</option>
										<option value="50">50</option>
										<option value="55">55</option>
						                
										
										
						  
						  </select>
						  <select name='periodendtimeam' id='periodendtimeam' ><option selected><c:out value="${itemparts[1]}" /></option>
										<option value="AM">AM</option>
										<option value="PM">PM</option>
										
						  
						  </select>
							</td>
							</c:forEach>
							
						</tr>
					</c:forEach>
				</tbody>
				
			</table>

		</div>
		 <table  width="70%"  id="table11" align="center">
                        <tr>
                            <td width="30%"> 

                            </td>
                            <td>
                                <button id="update">Update</button>
                            </td>

                        </tr>

                    </table>
 
	</form>

</body>

</html>

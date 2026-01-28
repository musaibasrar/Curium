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

        <title>Time Table</title>

        <script type="text/javascript" language="JavaScript" src="/sky/js/motionpack.js"></script>
        <link rel="stylesheet" href="/sky/css/datePicker/jquery-ui-1.8.18.custom.css">
        <link rel="stylesheet" href="/sky/css/graph/jquery.jqplot.css">

        <link rel="stylesheet" href="/sky/css/datePicker/demos.css">
        <script type="text/javascript" src="/sky/js/datePicker/jquery-1.7.1.js"></script>
        <script type="text/javascript" src="/sky/js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
        <script type="text/javascript" src="/sky/js/datePicker/ui/jquery.ui.dialog.js"></script>
        <script type="text/javascript" src="/sky/js/datePicker/ui/jquery.ui.autocomplete.js"></script>
        <script type="text/javascript" src="/sky/js/datePicker/ui/jquery.ui.core.js"></script>
        <script type="text/javascript" src="/sky/js/datePicker/ui/jquery.ui.widget.js"></script>
        <script type="text/javascript" src="/sky/js/datePicker/ui/jquery.ui.datepicker.js"></script>
        <script type="text/javascript" src="/sky/js/datePicker/ui/jquery.ui.accordion.js"></script>
        <script type="text/javascript" src="/sky/js/datePicker/ui/sliderAccess.js"></script>
        <script type="text/javascript" src="/sky/js/datePicker/ui/jquery-ui-timepicker-addon.js"></script>

        <script  type="text/javascript" src="/sky/js/datePicker/ui/jquery.ui.position.js"></script>
        <script type="text/javascript" src="/sky/js/datePicker/ui/jquery.ui.mouse.js"></script>
        <script type="text/javascript" src="/sky/js/datePicker/ui/jquery.ui.draggable.js"></script>
        <script type="text/javascript" src="/sky/js/datePicker/ui/jquery.ui.resizable.js"></script>
        <script type="text/javascript" src="/sky/js/graph/jquery.jqplot.js"></script>
        <script  type="text/javascript" src="/sky/js/graph/plugins/jqplot.dateAxisRenderer.min.js"></script>
        <script type="text/javascript" src="/sky/js/graph/plugins/jqplot.barRenderer.min.js"></script>
        <script type="text/javascript" src="/sky/js/graph/plugins/jqplot.categoryAxisRenderer.min.js"></script>
        <script type="text/javascript" src="/sky/js/graph/plugins/jqplot.cursor.min.js"></script>
        <script type="text/javascript" src="/sky/js/graph/plugins/jqplot.highlighter.min.js"></script>
        <script type="text/javascript" src="/sky/js/graph/plugins/jqplot.dragable.min.js"></script>
        <script type="text/javascript" src="/sky/js/graph/plugins/jqplot.trendline.min.js"></script>

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
            
            function updatePeriodDetail(){
               
                var form1=document.getElementById("form1");
                form1.action="/sky/PeriodProcess/updatePeriodDetails";
                method="POST";
                form1.submit();
            }
            
           
            
      
        </script>

        
        
<script type="text/javascript">
                                $(function() {
                                    
                                    $( "#modify" )
                                    .button()
                                    .click(function() {
                                        updatePeriodDetail();

                                    });
                                   
                                });
                                
                               
                            </script>


</head>
  <%
//allow access only if session exists
String user = null;
if(session.getAttribute("userAuth") == null){
	response.sendRedirect("/sky/UserProcess/sessionTimeOut");
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
					<td class="headerTD">TIME TABLE &nbsp;&nbsp;&nbsp;&nbsp;${timetable.class_}</td>
				</tr>
			</table>
			<table width="100%" border="1" style="border-color: #4b6a84;"
				id="myTable">

				

				<tbody>
					<c:forEach items="${periodmap}" var="periodmap">

						<tr style="border-color: #000000" border="1" cellpadding="1"
							cellspacing="1">
							<td class="dataText"><c:out value="${periodmap.key}" /></td>
							<c:forEach items="${periodmap.value}" var="periodmapvalue">
							<td class="dataText"><label><c:out value="${periodmapvalue.periods}" /></label><br><label><c:out value="${periodmapvalue.subject}" />
							<br><label><c:out value="${periodmapvalue.staff}" />
							</label>
							<br><label><c:out value="${periodmapvalue.timings}" /></label>
							</td>
							</c:forEach>
							
						</tr>
					</c:forEach>
				</tbody>
				
			</table>

		</div>
		<table  width="70%"  id="table11" align="center">
		<tr><td><br></td></tr>
                        <tr>
                                <td align="center" style="font-size:16px;"><a id="print" href="/sky/PeriodProcess/updatePeriodDetails?id=<c:out value="${periodMasterid}" />">Modify</a></td>
                            </td>

                        </tr>

                    </table>

	</form>

</body>

</html>

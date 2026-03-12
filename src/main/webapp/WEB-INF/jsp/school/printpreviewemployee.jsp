<%@page import="java.lang.String"%>
<%@page import = "java.util.logging.Logger" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head >
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Employee Id Card</title> 

        <script type="text/javascript" language="JavaScript" src="/school/js/motionpack.js"></script>
        <link rel="stylesheet" href="/school/css/datePicker/jquery-ui-1.8.18.custom.css">
        <link rel="stylesheet" href="/school/css/graph/jquery.jqplot.css">

        <link rel="stylesheet" href="/school/css/datePicker/demos.css">
        <script type="text/javascript" src="/school/js/datePicker/jquery-1.7.1.js"></script>
        <script type="text/javascript" src="/school/js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
        <script type="text/javascript" src="/school/js/datePicker/ui/jquery.ui.dialog.js"></script>
        <script type="text/javascript" src="/school/js/datePicker/ui/jquery.ui.autocomplete.js"></script>
        <script type="text/javascript" src="/school/js/datePicker/ui/jquery.ui.core.js"></script>
        <script type="text/javascript" src="/school/js/datePicker/ui/jquery.ui.widget.js"></script>
        <script type="text/javascript" src="/school/js/datePicker/ui/jquery.ui.datepicker.js"></script>
        <script type="text/javascript" src="/school/js/datePicker/ui/jquery.ui.accordion.js"></script>
        <script type="text/javascript" src="/school/js/datePicker/ui/sliderAccess.js"></script>
        <script type="text/javascript" src="/school/js/datePicker/ui/jquery-ui-timepicker-addon.js"></script>

        <script  type="text/javascript" src="/school/js/datePicker/ui/jquery.ui.position.js"></script>
        <script type="text/javascript" src="/school/js/datePicker/ui/jquery.ui.mouse.js"></script>
        <script type="text/javascript" src="/school/js/datePicker/ui/jquery.ui.draggable.js"></script>
        <script type="text/javascript" src="/school/js/datePicker/ui/jquery.ui.resizable.js"></script>
        <script type="text/javascript" src="/school/js/graph/jquery.jqplot.js"></script>
        <script  type="text/javascript" src="/school/js/graph/plugins/jqplot.dateAxisRenderer.min.js"></script>
        <script type="text/javascript" src="/school/js/graph/plugins/jqplot.barRenderer.min.js"></script>
        <script type="text/javascript" src="/school/js/graph/plugins/jqplot.categoryAxisRenderer.min.js"></script>
        <script type="text/javascript" src="/school/js/graph/plugins/jqplot.cursor.min.js"></script>
        <script type="text/javascript" src="/school/js/graph/plugins/jqplot.highlighter.min.js"></script>
        <script type="text/javascript" src="/school/js/graph/plugins/jqplot.dragable.min.js"></script>
        <script type="text/javascript" src="/school/js/graph/plugins/jqplot.trendline.min.js"></script>

<style type="text/css">

.dataTextBoldCenter {
	font-weight: normal;
	font-family: Tahoma;
	color: black;
	font-size: 14px;
	letter-spacing: normal;
	text-align: center;
}

.addressLine{
	font-weight: normal;
	font-family: ariel;
	color: black;
	font-size: 9px;
	letter-spacing: normal;
	text-align: center;
}

@media print {
    .fontsize {
    	font-size: 15px ;
        font-weight: bold;
        font-family: 'Times New Roman';
    }
    .header,.hide { visibility: hidden }
    .bodymargin{
        margin-left: 0px ;
        margin-right: 0px;
    }
}
        
@page {
     margin-left:  0cm;
     margin-right: 0cm;
     margin-bottom: 0cm;
     margin-top: 0cm;
}

@media screen {
    .fontsize { font-size: 15px;
                font-weight: bold;
                font-family: 'Times New Roman'
}

.bodymargin{
    margin-left: 0px ;
    margin-right: 0px;
}

body { margin: 0; }
    .card { page-break-inside: avoid; }
}

.card {
    width: 11cm;
    height: 7cm;
    background: #FEE12B;
}

.card:hover {
    box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2);
}

.container {
    padding: 1px 16px;
}


 .tableidcard {
        border-spacing: 0px;
        table-layout: fixed;
        margin-left: auto;
        margin-right: auto;
        width: 310px;
      }
      .tdidcard {
        font-size: 16px;
      }

    .vertical-line {
      border-left: 2px solid #350c76; /* Add a vertical line */
    }
</style>
        
<script type="text/javascript">
		function updateContact() {
		    var form1 = document.getElementById("form1");
		    form1.action = "/school/PersonalProcess/updateContactDetails?id=1";
		    form1.submit();
		}
		
		function hideButton() {
		
		}
        $(function() {
            $("#accordion").accordion({
                collapsible: true,
                autoHeight: false});
        });
         $(function() {
             $("#print").button()
         });

         function autoScaleAddress() {
             // 9.7cm is roughly 366 pixels depending on DPI
             var maxHeight = 320; 
             var containers = document.querySelectorAll('.address-container');

             containers.forEach(function(container) {
                 var fontSize = 10; // Start size
                 // While the content is taller than the card, reduce font size
                 while (container.scrollHeight > maxHeight && fontSize > 8) {
                     fontSize -= 1;
                     container.style.fontSize = fontSize + "px";
                 }
             });
         }

         // Run after page loads
         window.onload = autoScaleAddress;
</script>

  <%
//allow access only if session exists
String user = null;
if(session.getAttribute("userAuth") == null){
	response.sendRedirect("/school/UserProcess/sessionTimeOut");
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
 
<%! 
public String formatAddress(Object rawAttr, int maxChars) {
    if (rawAttr == null) return "";
    
    // Replace literal newlines with a unique marker
    String address = rawAttr.toString().replace("\n", " [NL] ").replace("\r", "");
    String[] words = address.split("\\s+"); 
    
    StringBuilder result = new StringBuilder();
    StringBuilder currentLine = new StringBuilder();
    String indent = "&nbsp;";

    for (String word : words) {
        if (word.equals("[NL]")) {
            if (currentLine.length() > 0) {
                result.append(indent).append(currentLine.toString().trim()).append("<br/>");
                currentLine = new StringBuilder();
            }
            continue;
        }

        int spaceNeeded = (currentLine.length() == 0) ? 0 : 1;
        if (currentLine.length() + spaceNeeded + word.length() > maxChars) {
            result.append(indent).append(currentLine.toString().trim()).append("<br/>");
            currentLine = new StringBuilder();
        }

        if (currentLine.length() > 0) currentLine.append(" ");
        currentLine.append(word);
    }
    
    if (currentLine.length() > 0) {
        result.append(indent).append(currentLine.toString().trim());
    }

    return result.toString();
}
%>
</head>
     

    <body class="bodymargin">
       
        <form action="/school/" method="post" id="form1" class="bodymargin">
			
          <c:forEach begin="1" end="${totalNumberOfRecords}">
           <%!  int i = 1;  %> <!-- Variable i is available to all methods and expressions in the JSP page  -->
           <%
			    Object addrObj = request.getSession().getAttribute("address" + i);
			    String formatted = formatAddress(addrObj, 20);
			    
			    Logger logger = Logger.getLogger(this.getClass().getName());
			    String message = "Loop values { "
			            + "totalNumberOfRecords = " + pageContext.findAttribute("totalNumberOfRecords")
			            + "loop counter = " + i
			            + "formatted address = " + formatted;
			    logger.info( message );
			%>

  <div class="card" style="
    max-width: 7cm; 
    max-height: 11cm; 
    width: 100%; 
    height: auto; 
    margin-bottom: 1cm; 
    border: 1px solid #000; 
    padding: 10px; 
    box-sizing: border-box; 
    position: relative; 
    background-color: #fff;
    page-break-inside: avoid;">

 <!--  <div class="card" style="background-color: white; width: 5.5cm; height: 8.6cm;border: 1px solid;border-radius: 5px;margin: 20px;">  -->
  <div class="table-container" style="margin-bottom: 5px;">
  <table width="100%">
  <tr align="center"><td style="text-align:center;padding:0px;">
    <p style="margin-bottom:0px;margin-top:0px;padding:0px;font-size:14px;font-weight: 900; color:red">${branchname}</p></td></tr><tr><td style="text-align:center;padding:0px;">
   <p style="font-size:7px;margin-bottom:0px;margin-top:0px;padding:0px;">${branchaddress}</p>
   <p style="font-size:7px;margin-bottom:0px;margin-top:0px;padding:0px;">${branchcontact}</p>
   </table>
  </div>

  <div class="table-container" style=" ">
 <table style="margin-left:10px;">
 <tr>
 <td style="writing-mode:vertical-lr;background-color: green;border-radius: 5px;text-align: center;font-weight: bold;transform:rotate(180deg);">IDENTITY-CARD
 </td>
 <td>
 <img src="data:image;base64,<%= request.getSession().getAttribute("employeephoto" + i + "")%>" style="height:78px;width:60px;border: 1px solid black;border-radius: 5px;" alt="Photo" />
 </td>
 <td style="writing-mode:vertical-lr;font-weight: bold;transform:rotate(180deg);">
 SESSION ${currentacadmicyear}
 </td>
 <td>&nbsp;&nbsp;
 <img src="/school/images/school.png" width="72" height="72"/>
 </td>
 </tr>
 </table>
   </div>
   <div align="center">
   <p style="font-size:18px;margin-bottom:0px;margin-top:0px; text-transform: uppercase;">&nbsp;&nbsp; <%= request.getSession().getAttribute("teachername" + i + "")%></p>
	</div>
    <table style="border-collapse: collapse;border-radius: 10px;background-color:white;width: 90%;margin-left: 10px">
  <tr>
    <td style="padding: 0;">&nbsp;&nbsp;F/NAME</td>
    <td style="padding: 0;">:&nbsp;<%= request.getSession().getAttribute("guardian" + i + "")%></td>
  </tr>
  <tr>
    <td style="padding: 0;white-space: nowrap;">&nbsp;&nbsp;DESIGNATION</td>
    <td style="padding: 0;">:&nbsp;<%= request.getSession().getAttribute("designation" + i + "")%></td>
  </tr>
  <tr>
    <td style="padding: 0;white-space: nowrap;">&nbsp;&nbsp;EMPLOYEE ID</td>
    <td style="padding: 0;">:&nbsp;<%= request.getSession().getAttribute("staffid" + i + "") %></td>
  </tr>
  <tr>
    <td style="padding: 0;white-space: nowrap;">&nbsp;&nbsp;MOBILE No.</td>
    <td style="padding: 0;">:&nbsp;<%= request.getSession().getAttribute("contactnumber" + i + "")%></td>
  </tr>
  <tr>
    <td style="padding: 0;">&nbsp;&nbsp;D.O.B.</td>
    <td style="padding: 0;">:&nbsp;<%= request.getSession().getAttribute("dateofjoining" + i + "") %></td>
  </tr>
  <tr>
    <td style="padding: 0;">&nbsp;&nbsp;MOBILE No.</td>
    <td style="padding: 0;">:&nbsp;<%= request.getSession().getAttribute("contactnumber" + i + "") %></td>
  </tr>
  <tr>
    <td style="padding: 0;">&nbsp;&nbsp;ADDRESS</td>
    <td style="padding: 0;">
       <div id="addr_<%= i %>" class="address-container">:<%= formatted %> </div>
    </td>
  </tr>
</table>
   
 <div style="margin-top: 0px; padding-right: 15px; display: flex; flex-direction: column; align-items: flex-end;">
    <!-- Signature Container -->
    <div style="margin-top: auto; display: flex; justify-content: center; padding-top: 10px;">
      <img src="/school/images/principalsignature.png" 
           alt="Principal Signature" 
           style="width: 45px; height: auto; display: block;" />
    </div>
    <div style="font-weight: bold; font-size: 11px; margin-top: 2px;">Principal</div>
 </div>
</div>
                    <% i = i + 1;%>
                        
</c:forEach>
                    <% i = 1;%>
  
                   <table  width="70%"  id="table11" align="left">
                    <tr>
                        <td width="30%"> 

                        </td>
                        <td>
                            <button id="print" type="button" style="background-image: url(/school/images/print.jpg);width: 63px;height: 60px" onclick="window.print();
                                    this.style.visibility = 'hidden', loading.style.visibility = 'visible'" class="hide"></button>     
                        </td>

                    </tr>

                </table>     
        </form>
    </body>
</html>

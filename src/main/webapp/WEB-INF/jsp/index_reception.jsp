<%-- 
    Document   : index
    Created on : Dec 29, 2011, 5:42:37 PM
    Author     : Musaib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript">

            /***********************************************
             * Collapsible Frames script- © Dynamic Drive (www.dynamicdrive.com)
             * This notice must stay intact for use
             * Visit http://www.dynamicdrive.com/ for full source code
             ***********************************************/

            var columntype=""
            var defaultsetting=""

            function getCurrentSetting(){
                if (document.body)
                    return (document.body.cols)? document.body.cols : document.body.rows
            }

            function setframevalue(coltype, settingvalue){
                if (coltype=="rows")
                    document.body.rows=settingvalue
                else if (coltype=="cols")
                    document.body.cols=settingvalue
            }

            function resizeFrame(contractsetting){
                if (getCurrentSetting()!=defaultsetting)
                    setframevalue(columntype, defaultsetting)
                else
                    setframevalue(columntype, contractsetting)
            }

            function init(){
                if (!document.all && !document.getElementById) return
                if (document.body!=null){
                    columntype=(document.body.cols)? "cols" : "rows"
                    defaultsetting=(document.body.cols)? document.body.cols : document.body.rows
                }
                else
                    setTimeout("init()",100)
            }

            setTimeout("init()",100)

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

    <frameset    rows="139,*"frameborder="0" border="0" framespacing="0"  >
        <frame   src="/abc/header"   name="topFrame" scrolling="NO" noresize frameborder="0">

            <frameset  cols="175,*" frameborder="0" border="0" framespacing="0">
                <frame  src="/abc/left_reception" name="leftFrame" scrolling="NO"  frameborder="1"   />
                <frame src="/abc/StudentProcess/viewAll" name="mainFrame" scrolling="yes" />
            </frameset>

   




    <noframes>
        <body>
        </body>
    </noframes>
</html>

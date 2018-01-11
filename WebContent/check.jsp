<%--
    Document   : check
    Created on : Feb 6, 2013, 10:12:56 AM
    Author     : Musaib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.model.user.dto.Login" %>
<%@page import="com.model.user.dao.UserDAO" %>
<%@page import="com.model.personal.dto.PersonalDetails" %>
<%@page import="com.model.personal.dao.PersonalDetailsDAO" %>
<%@page import="com.util.*" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%
            String name = request.getParameter("name");
                      

           
            PersonalDetails personaldetails;
          
          
            
            personaldetails = new PersonalDetailsDAO().readUniqueObject(name);
           


            if (personaldetails != null) {
                out.println("name exists");
                System.out.println("Name Exists " + personaldetails.getName());
            }

   

            
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Check</title>
    </head>
    <body>

        <h1></h1>
    </body>
</html>

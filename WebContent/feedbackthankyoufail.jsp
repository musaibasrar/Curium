<%-- 
    Document   : login
    Created on : Jan 9, 2012, 5:44:56 PM
    Author     : Musaib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
   
<head>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<meta charset="UTF-8">
	 <title>Curium - School ERP</title>
	<link rel="stylesheet" href="css/bootstrap3.min.css">
	<style type="text/css">

body {
  margin: 0;
  text-align: center;
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  font-family: sans-serif;
}

h1,
p {
  margin: 0;
  padding: 0;
  line-height: 1.5;
}

.app {
  width: 90%;
  /* max-width: 500px; */
  margin: 0 auto;
}
.container {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  align-items: center;
}

.item {
  width: 90px;
  height: 90px;
  display: flex;
  justify-content: center;
  align-items: center;
  user-select: none;
}
.radio {
  display: none;
}
.radio ~ span {
  font-size: 3rem;
  filter: grayscale(100);
  cursor: pointer;
  transition: 0.3s;
}

.radio:checked ~ span {
  filter: grayscale(0);
  font-size: 4rem;
}

	
	</style>
	

        
</head>
<body>

<form action="Controller?process=QueryProcess&action=feedback" method="post">
	<div class="app">
	 
  <h1>Oops! something went wrong. Please resubmit your response</h1>
</div>
 </form>
</body>
</html>

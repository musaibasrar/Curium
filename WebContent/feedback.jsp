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
	 
	 
	 <input type="hidden" id="no" name="no" value="<%= request.getParameter("no")%>">
	 <input type="hidden" id="id" name="id" value="<%= request.getParameter("id")%>">
  <h1>How happy are you with the response from our staff?</h1>
  <p>Let us know</p>

  <div class="container">
    <div class="item">
      <label for="0">
      <input class="radio" type="radio" name="feedback" id="0" value="1" required>
      <span>1</span>
    </label>
    </div>

    <div class="item">
      <label for="1">
      <input class="radio" type="radio" name="feedback" id="1" value="2">
      <span>2</span>
    </label>
    </div>

    <div class="item">
      <label for="2">
      <input class="radio" type="radio" name="feedback" id="2" value="3">
      <span>3</span>
    </label>
    </div>

    <div class="item">
      <label for="3">
      <input class="radio" type="radio" name="feedback" id="3" value="4">
      <span>4</span>
    </label>
    </div>

    <div class="item">
      <label for="4">
      <input class="radio" type="radio" name="feedback" id="4" value="5">
      <span>5</span>
    </label>
    </div>

  </div>
  
  <button type="submit" class="btn btn-primary">Sumbit</button>
</div>
 </form>
</body>
</html>

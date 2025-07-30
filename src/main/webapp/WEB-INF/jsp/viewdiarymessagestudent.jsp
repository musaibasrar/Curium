<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Student Logbook</title>
  	<link rel="stylesheet" href="/brightschool/css/bootstrap3.min.css">
	<script type="text/javascript" src="/brightschool/js/openWindow.js"></script>
     <script src="/brightschool/js/jquery.min.js"></script>
        <script src="/brightschool/js/bootstrap.min.js"></script>
        <script src="/brightschool/js/popper.min.js"></script>
    <style type="text/css">
        body {
            font-family: 'Roboto', sans-serif;
        }
        .main-content {
            border-radius: 20px;
            box-shadow: 0 5px 5px rgba(0,0,0,.4);
            margin: 2em auto;
            max-width: 100%;
            padding: 1em;
            background-color: #fff;
        }
        .company__info {
            background-color: #9D0176;
            border-radius: 20px 20px 0 0;
            color: #fff;
            padding: 1em;
            text-align: center;
        }
        .company__info h2 {
            font-size: 2em;
        }
        .form__input {
            width: 100%;
            border: 1px solid #aaa;
            padding: 1em;
            margin: 1em 0;
            outline: none;
            transition: all .5s ease;
            font-size: 16px;
        }
        .form__input:focus {
            border-color: #1786b4;
            box-shadow: 0 0 5px rgba(0,80,80,.4);
        }
        .btn {
            transition: all .5s ease;
            border-radius: 30px;
            color: #fff;
            font-weight: 600;
            background-color: #9D0176;
            border: none;
            padding: 0.5em 2em;
            margin-top: 1.5em;
        }
        .btn:hover, .btn:focus {
            background-color: #146c9c;
        }
        .message-content {
            text-align: justify;
            font-size: 16px;
            margin: 0.5em 0;
        }
        .message-subject {
            font-weight: bold;
            font-size: 18px;
            margin-top: 1em;
        }
    </style>
</head>

<body>
    <div class="container">
        <!-- Main Content -->
        <div class="company__info">
            <h2>Logbook</h2>
        </div>
        <div class="main-content">
            <div class="form-group">
                <p class="message-subject">Subject: ${studentdiary.subject}</p>
                <label for="message" style="font-size:20px;">Message</label>
                <p id="message" class="message-content">${studentdiary.message}</p>
            </div>
            <form action="/brightschool/StudentDiaryProcess/viewDiaryStudentParent?id=${username}&urlbranchid=${Parents.student.branchid}" method="post">
                <div class="text-center">
                    <input type="submit" value="Back" class="btn">
                </div>
            </form>
        </div>
    </div>
</body>

</html>

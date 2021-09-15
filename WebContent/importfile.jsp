<%-- 
    Document   : Import File
    Created on : JULY 6, 2021, 02:25:00 PM
    Author     : Adeeba
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Import File</title>

<script type="text/javascript">
$(document).ready(function() {

    $('#btn').click(function() {
        event.preventDefault();

        var form = $('#myform')[0];
        var data = new FormData(form);

        $.ajax({
            type : "POST",
            enctype : 'multipart/form-data',
            data : data,
            processData : false,
            contentType : false,
            cache : false,
            success : function(responseText) {
                $('#ajaxGetUserServletResponse').text(responseText);
            }
        });
    }); 
});
</script>

</head>
<body background="images/bg.jpg">

<form id="form1" action="Controller?process=ImportProcess&action=readFile" method="post"  enctype="multipart/form-data">
<table width="100%" border="0" align="center">
<tr>								
<td><br /><br />
<input type="file" name="fileToImport" id="fileToImport" accept=".xls,.xlsx"/>
<br /><br />
<input type="submit" value="Upload File" />
</td>
</tr>
</table>
</form>
</body>
</html>
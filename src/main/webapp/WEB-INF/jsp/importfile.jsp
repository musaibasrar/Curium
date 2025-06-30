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

</head>
<body>

<form id="form1" action="/ruyaa/ImportProcess/readFile" method="post"  enctype="multipart/form-data">
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
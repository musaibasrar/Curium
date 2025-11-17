<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Report Card</title>
<style>
td,tr,table{
border: 2px solid brown;
border-collapse:collapse;

}
</style>
</head>
<body>
<div style="border:2px solid brown;padding:4px;">
<table width="100%"  height="20px" style="background-color:brown;">
<tr><td style="color:white;text-align:center;">Formative Assessments</td></tr>
</table>
<table width="100%" style="margin-top:5px;">
<tr>
<td>Subject</td>
<td>Written assignment<br>(10)</td>
<td>Listening Comprehension<br>(10)</td>
<td>Conversation & dialogue<br>(10)</td>
<td>Optional Activity<br>(10)</td>
<td>Total 60</td>
<td>Reduced to 10</td>
<td>Grade</td>
</tr>
<tr>
<td>English</td>
<td></td>
<td></td>
<td></td>
<td></td>
<td></td>
<td></td>
<td></td>
</tr>
<tr>
<td>Kannada</td>
<td></td>
<td></td>
<td></td>
<td></td>
<td></td>
<td></td>
<td></td>
</tr>
<tr>
<td>Urdu/Hindi</td>
<td></td>
<td></td>
<td></td>
<td></td>
<td></td>
<td></td>
<td></td>
</tr>
<tr>
<td rowspan="2">Mathematics</td>
<td>Written assignment<br>(10)</td>
<td>Group Project<br>(10)</td>
<td>MCQs<br>(10)</td>
<td>Optional Activity<br>(10)</td>
<td>Total 60</td>
<td>Reduced to 10</td>
<td>Grade</td>
</tr>
<tr>
<td>&emsp;</td>
<td></td>
<td></td>
<td></td>
<td></td>
<td></td>
<td></td>
</tr>
<tr>
<td>Science</td>
<td></td>
<td></td>
<td></td>
<td></td>
<td></td>
<td></td>
<td></td>
</tr>
<tr>
<td>Social S</td>
<td></td>
<td></td>
<td></td>
<td></td>
<td></td>
<td></td>
<td></td>
</tr>
</table>
<table width="100%" style="margin-top:5px;">
<tr>
<td rowspan="2" colspan="2">
Attendance</td>
<td rowspan="4">
Marks Detail
</td>
<td>
Maximum Marks
</td>
<td>60</td> 
</tr>
<tr>

<td>Marks Obtained</td>
<td></td>
</tr>
<tr>
<td>No. of Working Days</td>
<td>&emsp;&emsp;</td>
<td>Percentage</td>
<td></td>
</tr>
<tr>
<td>No. of Present Days</td>
<td></td>
<td>Overall Grade</td>
<td></td>
</tr>
</table>

<table width="100%" height="110px" >
<tr>
<td>Performance</td>
<td><span style="display:inline-flex;height:40px;border: 1px solid brown;align-items:center;margin:5px;padding:5px;border-radius:10px">Excellent  <span height="30px" width="20px" style="display:inline-flex;border:1px solid black"  >&emsp;&emsp;</span></span></td>
<td><span style="display:inline-flex;height:40px;border: 1px solid brown;align-items:center;margin:5px;padding:5px;border-radius:10px">Good  <span height="30px" width="20px" style="display:inline-flex;border:1px solid black"  >&emsp;&emsp;</span></span></td>
<td><span style="display:inline-flex;height:40px;border: 1px solid brown;align-items:center;margin:5px;padding:5px;border-radius:10px">Average  <span height="30px" width="20px" style="display:inline-flex;border:1px solid black"  >&emsp;&emsp;</span></span></td>
</tr>
<tr>
<td>Remarks</td>
<td colspan="3"></td>
</tr>


</table>
<table width="100%" style="border:0px">
<tr style="border:0px">
<td style="border:0px">
<br>
</td>
</tr>
<tr style="border:0px">
<td style="border:0px">
<br>
</td>
</tr><tr style="border:0px">
<td style="border:0px">
<br>
</td>
<tr style="border:0px"><td style="border:0px">
Class Teacher</td>
<td style="border:0px;text-align:center;">Parent's Signature</td>
<td style="border:0px;text-align:right">
                            HM signature     
                        </td>
</tr>
</table>
</div>
 <button id="print" type="button"  onclick="window.print();
                                    this.style.visibility = 'hidden', loading.style.visibility = 'visible'" class="hide">print</button> 
</body>
</html>

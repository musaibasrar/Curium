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
<tr><td style="color:white;text-align:center;">Summative Assessments</td></tr>
</table>
<table width="100%" style="margin-top:5px;">
<tr>
<td>Subject</td>
<td>Minimum Marks</td>
<td>Marks Obtained</td>
<td>R to 20</td>

</tr>
<tr>
<td>English</td>
<td>60</td>
<td></td>
<td></td>

</tr>
<tr>
<td>Kannada</td>
<td>60</td>
<td></td>
<td></td>

</tr>
<tr>
<td>Urdu/Hindi</td>
<td>60</td>
<td></td>
<td></td>

</tr>
<tr>
<td>Mathematics</td>
<td>60</td>
<td></td>
<td></td>

</tr>

<tr>
<td>EVS</td>
<td>60</td>
<td></td>
<td></td>

</tr>
</table>
<table width="100%" style="margin-top:5px;">
<tr>
<td>Maximum Marks</td>
<td>300</td>
<td>Marks Obtained</td>
<td>&emsp;&emsp;</td>
</tr>
<tr>
<td>Percentage</td>
<td></td>
<td>Grade</td>
<td></td>
</tr>
<tr>
<td>No. of Working Days</td>
<td></td>
<td>No. of Days Present</td>
<td></td>
</tr>
</table>

<div style="display:flex;">
<table style="width:50%;margin-top:5px;margin-right:2px;">
<tr><td rowspan="7" style="writing-mode:vertical-lr;font-weight: bold;transform:rotate(180deg);text-align:center;">Part2</td><td>Subject</td><td>Grade</td></tr>
<tr><td>Computer</td><td></td></tr>
<tr><td>Project Work</td><td></td></tr>
<tr><td>GK</td><td></td></tr>
<tr><td>Fine Work & SUPW</td><td></td></tr>
<tr><td>Physical Edu</td><td></td></tr>
<tr><td>Value Education</td><td></td></tr>

</table>
<table style="width:50%;margin-top:5px;margin-left:2px;">
<tr><td rowspan="7" style="writing-mode:vertical-lr;font-weight: bold;transform:rotate(180deg);text-align:center;">Part3</td><td>Attitude Towards</td><td>Grade</td></tr>
<tr><td>Schoolmates</td><td></td></tr>
<tr><td>Teachers</td><td></td></tr>
<tr><td>Punctuality</td><td></td></tr>
<tr><td>Resposibilty</td><td></td></tr>
<tr><td>Cleanliness</td><td></td></tr>
<tr><td>Self discipline</td><td></td></tr>
</table>
</div>
<table width="100%" height="110px" style="margin-top:5px;">
<tr>
<td rowspan="3" style="writing-mode:vertical-lr;font-weight: bold;transform:rotate(180deg);text-align:center;background-color:brown;color:white;">
Remarks
</td>
<td rowspan="3">
Conduct
</td>
<td>
Excellent(&emsp;&emsp;)
</td>
<td rowspan="3">
Performance
</td>
<td>
Excellent(&emsp;&emsp;)
</td>
</tr>
<tr>
<td>
Good(&emsp;&emsp;)
</td>
<td>
Good(&emsp;&emsp;)
</td>
</tr>
<tr>
<td>
Average(&emsp;&emsp;)
</td>
<td>
Average(&emsp;&emsp;)
</td>
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

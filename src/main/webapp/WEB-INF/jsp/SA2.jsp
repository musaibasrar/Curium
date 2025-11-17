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

<tr><td colspan="5" style="text-align:center"> Part A</td></tr>

<tr>
<td>Subject</td>
<td>Minimum Marks</td>
<td>Marks Obtained</td>
<td>R to 20</td>
<td>Grade</td>

</tr>
<tr>
<td>English</td>
<td>60</td>
<td></td>
<td></td>
<td></td>

</tr>
<tr>
<td>Kannada</td>
<td>60</td>
<td></td>
<td></td>
<td></td>

</tr>
<tr>
<td>Urdu/Hindi</td>
<td>60</td>
<td></td>
<td></td>
<td></td>

</tr>
<tr>
<td>Mathematics</td>
<td>60</td>
<td></td>
<td></td>
<td></td>
</tr>

<tr>
<td>EVS</td>
<td>60</td>
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

<table width="100%">
<tr>
<td rowspan="8" style="writing-mode:vertical-lr;font-weight: bold;transform:rotate(180deg);text-align:center;">Part B</td>
<td>General Knowledge</td>
<td>&emsp;&emsp;</td>
<td rowspan="3">Performance</td>
<td>Excellent <span height="20px" width="20px" style="display:inline-flex;border:1px solid black"  >&emsp;&emsp;</span></td>
</tr>
<tr>
<td>Moral/Deenyat</td>
<td>&emsp;&emsp;</td>
<td>Good <span height="20px" width="20px" style="display:inline-flex;border:1px solid black"  >&emsp;&emsp;</span></td>
</tr>
<tr>
<td>Fine,Arts & Deenyaat</td>
<td>&emsp;&emsp;</td>
<td>Average <span height="20px" width="20px" style="display:inline-flex;border:1px solid black"  >&emsp;&emsp;</span></td>
</tr>
<tr>
<<td>Physical Education</td>
<td></td>
<td colspan="2">REMARKS</td>
</tr>
<tr>
<td>Project</td>
<td></td>
<td colspan="2" rowspan="4"></td>
</tr>
<tr>
<td>
Punctuality</td>
<td></td></tr>
<tr>
<td>
Cleanliness</td>
<td></td></tr>
<tr>
<td>
Self Discipline</td>
<td></td></tr>

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

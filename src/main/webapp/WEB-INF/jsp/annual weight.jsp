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
<tr><td style="color:white;text-align:center;">Annual Weight</td></tr>
</table>
<table width="100%" style="margin-top:5px;">
<tr>
<td rowspan="3" >Subject</td>
<td colspan="4">Semester I</td>
<td colspan="4">Semester II</td>
<td rowspan="2">Over all</td>
</tr>
<tr>
<td>FA-1</td>
<td>FA-2</td>
<td>SA-1</td>
<td>Total</td>
<td>FA-3</td>
<td>FA-4</td>
<td>SA-2</td>
<td>Total</td>
</tr>
<tr>
<td>15%</td>
<td>15%</td>
<td>20%</td>
<td>50%</td>
<td>15%</td>
<td>15%</td>
<td>20%</td>
<td>50%</td>
<td>100%</td>
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
<td></td>
<td></td>
</tr>

<tr>
<td>Mathematics</td>
<td></td>
<td></td>
<td></td>
<td></td>
<td></td>
<td></td>
<td></td>
<td></td>
<td></td>
</tr>
<tr>
<td>EVS</td>
<td></td>
<td></td>
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
<td>No. of Days Persent</td>
<td></td>
</tr>
</table>

<table width="100%" height="110px" style="margin-top:5px;">
<tr>
<td rowspan="3" style="writing-mode:vertical-lr;font-weight: bold;transform:rotate(180deg);">
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

<table width="100%" style="border:0px;">
<tr style="border:0px;"><td style="border:0px;"><br></td></tr>
<tr style="border:0px;"><td style="border:0px;"><br></td></tr>
<tr style="border:0px;">
<td style="border:0px;text-align:center">Congrutulation</td>
</tr>
<tr style="border:0px;"><td style="border:0px;"><br></td></tr>
<tr style="border:0px;"><td style="border:0px;text-align:center"><span  style="width:150px;display:inline-block;border:1px solid brown;">RESULT</span><span  style="width:300px;display:inline-block;border:1px solid brown;">&emsp;</span>
</td></tr>
<tr style="border:0px;"><td style="border:0px;"><br></td></tr>
<tr style="border:0px;">
<td style="border:0px;text-align:center">Promoted to ..........&emsp;&emsp;&emsp;&emsp;&emsp;New session begins on ..........</td>
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
<td style="border:0px;text-align:center;">&emsp;&emsp;&emsp;&emsp;</td>
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

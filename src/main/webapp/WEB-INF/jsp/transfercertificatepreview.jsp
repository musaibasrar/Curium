<%-- 
    Document   : Transfer Certificate Preview
    Created on : Mar 21 2018, 09:58 AM
    Author     : Musaib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html moznomarginboxes >
<head>

<style type="text/css">
<!--
.headerText {
	width: 10px;
	font-family: Tahoma;
	font-size: 12px;
	color: #FFFFFF;
	font-weight: normal;
	width: auto;
	height: 22px;
	vertical-align: middle;
	text-align: center;
}
.headerTextLeft {
	width: 10px;
	font-family: Tahoma;
	font-size: 12px;
	color: #FFFFFF;
	font-weight: normal;
	width: auto;
	height: 22px;
	vertical-align: middle;
	text-align: left;
}
.dataTextBold {
	font-weight: bold;
	font-family: Tahoma;
	color: black;
	font-size: 12px;
	letter-spacing: normal;
	text-align: center;
}
.dataTextBoldLeft {
	font-weight: normal;
	font-family: Tahoma;
	color: black;
	font-size: 12px;
	letter-spacing: normal;
	text-align: left;
}
.dataTextBoldCenter {
	font-weight: bold;
	font-family: Tahoma;
	color: black;
	font-size: 45px;
	letter-spacing: normal;
	text-align: center;
}
.addressLine{
	font-weight: normal;
	font-family: ariel;
	color: black;
	font-size: 22px;
	letter-spacing: normal;
	text-align: center;
}
.dataText {
	font-family: Tahoma;
	color: black;
	font-size: 12px;
	letter-spacing: normal;
	text-align: center;
}

  td{
	font-style:italic;
	font-size: 20px;
	margin-top:5px;
	margin-bottom: 5px;
	
} 

-->
span{
    display:inline-block;
    border-bottom:2px solid black;
    padding-bottom:1px;
    width: 200px;
    font-weight: normal;
    text-align:center;
    margin-top:5px;
	margin-bottom: 5px;
    }
h3{
//margin-bottom:0px;
margin-top:0px;
}
</style>



<!-- <style type="text/css">
        @media print {
            .fontsize { font-size: 15px ;
                        font-weight: bold;
                        font-family: 'Times New Roman';
                        
                        
            }
            .header,.hide { visibility: hidden }
            .bodymargin{
            	margin-top: 0px;
                margin-left: 0px ;
                margin-right: 0px;
            }
            
        }
        
        @page {
              size: auto;   /* auto is the current printer page size */
           	  margin: 0mm;  /* this affects the margin in the printer settings */ 
            
        }
        @media screen {
            .fontsize { font-size: 15px;
                        font-weight: bold;
                        font-family: 'Times New Roman'
            }
            .bodymargin{
                margin-left: 0px ;
                margin-right: 0px;
            }
        }
    </style> -->

    <style type="text/css">
        @media print {
            .fontsize { font-size: 15px ;
                        font-weight: bold;
                        font-family: 'Times New Roman';
                        
                        
            }
            .header,.hide { visibility: hidden }
            .bodymargin{
                margin-left: 0px ;
                margin-right: 0px;
            }
            
        }
        
        @page {
              
             margin-left:  1cm;
             margin-right: 1cm;
             margin-bottom: 0cm;
             margin-top: 0cm;
        }
        @media screen {
            .fontsize { font-size: 12px;
                        font-weight: bold;
                        font-family: 'Times New Roman'
            }
            .bodymargin{
                margin-left: 0px ;
                margin-right: 0px;
            }
        }
        .input{
        width:10px;
        text-align:center;
        }
    </style>
	<script type="text/javascript" src="/alirfan/js/datePicker/jquery-1.7.1.js"></script>
        <script type="text/javascript" src="/alirfan/js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
        <script src="/alirfan/js/print/jquery.printPage.js" type="text/javascript"></script>
        <title>Print Transfer Certificate</title>
          <!--    <script type="text/javascript">
             window.onload = function(){
            	 window.print();
             }
        </script>-->
       

</head>
<%
//allow access only if session exists
String user = null;
if(session.getAttribute("userAuth") == null){
	response.sendRedirect("/alirfan/UserProcess/sessionTimeOut");
}else user = (String) session.getAttribute("userAuth");
String userName = null;
String sessionID = null;
Cookie[] cookies = request.getCookies();
if(cookies !=null){
for(Cookie cookie : cookies){
	if(cookie.getName().equals("user")) userName = cookie.getValue();
	if(cookie.getName().equals("JSESSIONID")) sessionID = cookie.getValue();
}
}
%>
<body >
<jsp:useBean id="now" class="java.util.Date" scope="page" />
	<form method="post" class="bodymargin">
		<br>
		<table align="center">
		
		<tr>
		<td><br><br><br></td>
		</tr>
			<tr><td>
			
			<img  src="/alirfan/images/alirfan.jpg" alt="Al-Irfan" width="120" height="140">&emsp;&emsp;</td>
				<td style="font-style:normal;text-align:center;" >
				<label class="addressLine">Al-Hira Educational & Welfare Society`s.</label><br>
				<label class="dataTextBoldCenter" style="text-transform: uppercase;">Al-Irfan School</label><br>
				<label class="addressLine">(Senior and Secondary Residential and day Boarding)</label><br>
				<label class="addressLine"> ${branchaddress}</label><br>
				</td>
				<td>&emsp;&emsp;<img  src="/alirfan/images/cbse.png" alt="cbse logo" width="110" height="122"></td>
				</tr>
</table>

<TABLE align="center">

                <tr>
                    <td>Email id:</td><td>&nbsp; mail@alirfanschool.edu.in&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
                    &emsp;&emsp;&emsp;&emsp;</td>
                     <td>CBSE affiliation No.:</td><td>&nbsp;1130056</td>
                </tr>
                
                <tr>
					<td>UDISE No.:</td><td>&nbsp;27190406102</td>
					<td>School Code:</td><td>&nbsp;30032</td>               
                </tr>
                
                
                
            </TABLE>
            
            <table align="center">
            
			<tr>

				<td align="center">

			<h3><u>SCHOOL LEAVING CERTIFICATE</u></h3>
			</td>
			</tr>
			
						
			<tr><td align="center">
			(No change in an entry is to be made except by the authority issuing the certificate)
			</td></tr>
			
			</table>
			<table width="100%" style="border:1px solid black"></table>

		<table align="center">
		<br>
		<br>
		<tr><td>Sr No.:&nbsp;<span style="font-weight: bold;width: 120px;"> <c:out value="${studentdetails.student.studentexternalid}" /> </span></td>
             <td>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;G.R. No.:&nbsp;<span style="font-weight: bold;width: 200px;"><c:out value="${studentdetails.student.sts}" /> </span></td>
			</tr>
			
			<tr><td>Student ID:&nbsp;<span style="font-weight: bold;width: 120px;">${grno}</span></td>
             <td>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;UID No.:&nbsp;<span style="font-weight: bold;width: 200px;"><c:out value="${studentdetails.student.disabilitychild}" /></span></td>
			</tr>
			
			<tr>

				<td >
					1.&nbsp;&nbsp;&nbsp;Name of pupil in full </td><td>
				<span style="font-weight: bold;width: 500px;"><c:out value="${studentdetails.student.name}" /></span>	
				</td>
				</tr>
				   
				    <tr>
				    <td>
					2.&nbsp;&nbsp;&nbsp;Mother's name </td>
				    <td><span style="font-weight: bold;width: 500px;"><c:out value="${studentdetails.mothersname}" /></span>
				    </td>
			        </tr>
			        
			        <tr>
				    <td>
					3.&nbsp;&nbsp;&nbsp;Father's/Guardian's Name </td><td>
				   <span style="font-weight: bold;width: 500px;"> <c:out value="${studentdetails.fathersname}" /></span>
				   </td>
				   </tr>
				
				    <tr>
				    <td >
				    4.&nbsp;&nbsp;&nbsp;Nationality </td><td>
					<span style="font-weight: bold;width: 200px;"><c:out value="${studentdetails.student.nationality}" /></span>
					</td>
					</tr>
					
					<tr>
					<td>
					5.&nbsp;&nbsp;&nbsp;Mother Tongue </td><td>
									<span style="font-weight: bold;width: 120px;"><c:out value="${studentdetails.student.mothertongue}" /></span>
					 6.&nbsp;&nbsp;&nbsp;Religion <span style="font-weight: bold;width: 120px;float:none;"><c:out value="${studentdetails.student.religion}" /></span>
					 </td></tr>
				
					<tr>
					<td>
					7.&nbsp;&nbsp;&nbsp;Caste </td><td>
					<span style="font-weight: bold;width: 120px;"><c:out value="${studentdetails.student.studentscaste}" /></span>
					 8.&nbsp;&nbsp;&nbsp;Sub-caste: <span style="font-weight: bold;width: 120px;"><c:out value="${studentdetails.student.crecord}" /></span>
					 </td>
					 </tr>
					
					
					<tr>
				    <td >
					9.&nbsp;&nbsp;&nbsp;Place of the birth
					<td>
					<span style="font-weight: bold;width: 500px;"><c:out value="${studentdetails.student.placeofbirth}" /></span>
				    </td></tr>
				
				
				    <tr>
					<td >
					10.&nbsp;&nbsp;&nbsp;Date of Birth <td>
				    <span style="font-weight: bold;width: 500px;"><fmt:formatDate value="${studentdetails.student.dateofbirth}" pattern="dd/MM/yyyy" /></span>
				    </td></tr>
				
				
				    <tr>
				    <td >
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Date of Birth in words <td>
				     <span style="font-weight: bold;width: 500px;"><c:out value="${dateinword}" /></span>
				    </td></tr>
					
				   <tr>
				   <td >
					11.&nbsp;&nbsp;&nbsp;Last school and class attended</td> <td>
					 <span style="font-weight: bold;width: 500px;">${branchname}
					  </span>
				   </td></tr>	
				
				   <tr><td></td>
				   <td>
				   <span style="font-weight: bold;width: 500px;"><c:out value="${studentdetails.student.classstudying}" /></span>
				   </td>
				   </tr>
				
				   <tr>
				   <td>
					12.&nbsp;&nbsp;&nbsp;Date of admission </td><td>
					<span style="font-weight: bold;width: 120px;"><fmt:formatDate value="${studentdetails.student.admissiondate}" pattern="dd/MM/yyyy" /></span>
					 Admission standard <span style="font-weight: bold;width: 120px;float:none;"><c:out value="${studentdetails.student.classadmittedin}" /></span>
					 </td>
					 </tr>
				
				
				    <tr>
				    <td>
					13.&nbsp;&nbsp;&nbsp;Progress </td><td>
					<span style="font-weight: bold;width: 120px;float:none;">${progress} </span>
					 14.&nbsp;&nbsp;&nbsp;Conduct <span style="font-weight: bold;width: 120px;float:none;">${conduct} </span>
					 </td></tr>
				
				    <tr>
 				    <td>
					15.&nbsp;&nbsp;&nbsp;Date of Leaving</td><td>
					<span style="font-weight: bold;width: 500px;"><fmt:formatDate type="date" value="${studentdetails.student.dateleaving}" pattern="dd/MM/yyyy"/></span>
				    </td></tr>	
				
				
				    <tr>
				    <td >
					16.&nbsp;&nbsp;&nbsp;Standard in which studying  </td><td>
				    <span style="font-weight: bold;width: 500px;"><c:out value="${studentdetails.student.classstudying}" />&emsp;<c:out value="${studentdetails.student.yearofadmission}" /></span>	
				     </td></tr>	
				
				
				   <tr>
				  <td >
					17.&nbsp;&nbsp;&nbsp;Reason of Leaving school </td><td>
					<span style="font-weight: bold;width: 500px;">${studentdetails.student.reasonleaving}</span>
				    </td></tr>	
					
				    <tr>
				    <td >
					18.&nbsp;&nbsp;&nbsp;Remarks</td><td>
					<span style="font-weight: bold;width: 500px;">${Remarks}</span>
				     </td></tr>	
				
				<tr>
				<td colspan="2" text-align="center">
				Certified that the above information is in accordance with the school General Register
				</td>
				</tr>
				
				<tr>
				<td >
					19.&nbsp;&nbsp;&nbsp;Date of issue </td><td>
					<span style="font-weight: bold;width: 120px;"><input name="dateofcr" type="text" class="textField" style="border: none;border-color: transparent;"
					size="10" value="<fmt:formatDate type="date" value="${now}" pattern="dd/MM/yyyy"/>" ></span>
				</td></tr>	
		</table>
		
		
		<TABLE align="center" id="dataTable" >
		
		<tr>
		<td>
		<br><br><br>
		<br><br><br>
		</td>
		</tr>
			
		<tr>
		<td>Class Teacher&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
		<td>Clerk&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
			<td>Principal</td>

			</tr>
        <tr>
         <td>
                            <button id="print" type="button" style="background-image: url(/alirfan/images/print.jpg);width: 63px;height: 60px" onclick="window.print();
                                    this.style.visibility = 'hidden', loading.style.visibility = 'visible'" class="hide"></button>     
                        </td>
        </tr>
			<%-- <tr>
               <td align="center"><a id="print" href="/alirfan/DocumentsProcess/PrintTransferCertificate?id=<c:out value="${studentdetails.student.sid}" />">Print</a></td>
             </tr> --%>
		</TABLE>

		<%-- <a id="print" href="/alirfan/Controller?process=StudentProcess&action=GenerateBonafide&id=<c:out value="${studentdetails.student.sid}" />">Print</a> --%>
	</form>


</body>
</html>
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
	font-size: 30px;
	letter-spacing: normal;
	text-align: center;
}
.addressLine{
	font-weight: normal;
	font-family: ariel;
	color: black;
	font-size: 14px;
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
	font-size: 18px;
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
        
        span{
    display:inline;
    border:1px solid black;
    padding:1px;
    font-weight: normal;
    text-align:center;
}
    </style>
	<script type="text/javascript" src="/noblewisdom/js/datePicker/jquery-1.7.1.js"></script>
        <script type="text/javascript" src="/noblewisdom/js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
        <script src="/noblewisdom/js/print/jquery.printPage.js" type="text/javascript"></script>
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
	response.sendRedirect("/noblewisdom/UserProcess/sessionTimeOut");
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
		
		
			<tr><td style="margin-right:0px;padding-left:10px;">
			
			<img  src="/noblewisdom/images/noblewisdom.jpg" alt="noblewisdom logo" width="103" height="94"></td>
				<td style="font-style:normal;text-align:center;" >
				<label class="dataTextBoldCenter" style="text-transform: uppercase;">Noble Wisdom English Medium School</label><br>
				<label class="addressLine">Educating Students to be successful in both the worlds</label><br>
				<%-- <label class="addressLine"> ${branchaddress}</label><br> --%>
				</td>
				<!-- <td style="margin-left:0px;padding-left:0px;">&emsp;&emsp;<img  src="/noblewisdom/images/cbse.png" alt="cbse logo" width="90" height="100"></td> -->
				</tr>
</table>

<TABLE align="center">

                
                <tr>
                    <td>Email id:</td><td>&nbsp; mail@noblewisdomschool.edu.in.com&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
                   </td>
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

			<h3 style="margin-bottom:0px;"><u>SCHOOL LEAVING CERTIFICATE</u><br>DUPLICATE</h3>
			</td>
			</tr>
			
						
			<tr><td align="center">
			(No change in an entry is to be made except by the authority issuing the certificate)
			</td></tr>
			
			</table>
			<table width="100%" style="border:1px solid black"></table>

		<table align="center" >
		<br>
		<br>
		<tr><td>Sr No.:&nbsp; <c:out value="${studentdetails.student.studentexternalid}" /> </td>
             <td>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;G.R. No.:&nbsp;<c:out value="${studentdetails.student.sts}" /> </td>
			</tr>
			
			<tr><td>Student ID:&nbsp;<span> ${grno}</span></td>
             <td>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;UID No.:&nbsp;<c:out value="${studentdetails.student.disabilitychild}" /></td>
			</tr>
			
			<tr>

				<td >
					1.&nbsp;&nbsp;&nbsp;Name of pupil in full </td>
					<td style="text-transform:uppercase;font-weight:bold;">:&nbsp;&nbsp;
				<c:out value="${studentdetails.student.name}" />
				</td>
				</tr>
				   
				    <tr>
				    <td>
					2.&nbsp;&nbsp;&nbsp;Mother's name </td>
				    <td style="text-transform:uppercase;font-weight:bold;">:&nbsp;&nbsp;<c:out value="${studentdetails.mothersname}" />
				    </td>
			        </tr>
			        
			        <tr>
				    <td>
					3.&nbsp;&nbsp;&nbsp;Father's/Guardian's Name&emsp;&emsp;&emsp; </td>
					<td style="text-transform:uppercase;font-weight:bold;">:&nbsp;&nbsp;
				    <c:out value="${studentdetails.fathersname}" />
				   </td>
				   </tr>
				
				    <tr>
				    <td >
				    4.&nbsp;&nbsp;&nbsp;Nationality </td>
				    <td style="text-transform:uppercase;font-weight:bold;">:&nbsp;&nbsp;
					<c:out value="${studentdetails.student.nationality}" />
					</td>
					</tr>
					
					<tr>
					<td>
					5.&nbsp;&nbsp;&nbsp;Mother Tongue </td>
					<td style="text-transform:uppercase;font-weight:bold;">
									:&nbsp;&nbsp;<c:out value="${studentdetails.student.mothertongue}" />
					</td>
					</tr>
					<tr>
					<td>				
					 6.&nbsp;&nbsp;&nbsp;Religion&nbsp;&nbsp;&nbsp;</td>
					 <td style="text-transform:uppercase;font-weight:bold;">:&nbsp;&nbsp; <c:out value="${studentdetails.student.religion}" />
					 </td></tr>
				
					<tr>
					<td>
					7.&nbsp;&nbsp;&nbsp;Caste </td><td style="text-transform:uppercase;font-weight:bold;">
					:&nbsp;&nbsp;<c:out value="${studentdetails.student.caste}" />
					</td>
					</tr>
					<tr>
					<td>
					 8.&nbsp;&nbsp;&nbsp;Sub-caste: </td><td style="text-transform:uppercase;font-weight:bold;">:&nbsp;&nbsp;<c:out value="${studentdetails.student.socialcategory}" />
					 </td>
					 </tr>
					
					
					<tr>
				    <td >
					9.&nbsp;&nbsp;&nbsp;Place of the birth</td>
					<td style="text-transform:uppercase;font-weight:bold;">:&nbsp;&nbsp;
					<c:out value="${studentdetails.student.placeofbirth}" />
				    </td></tr>
				
				
				    <tr>
					<td >
					10.&nbsp;&nbsp;&nbsp;Date of Birth </td><td style="text-transform:uppercase;font-weight:bold;">:&nbsp;&nbsp;
				    <fmt:formatDate value="${studentdetails.student.dateofbirth}" pattern="dd/MM/yyyy" />
				    </td></tr>
				
				
				    <tr>
				    <td >
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Date of Birth in words</td> <td style="text-transform:uppercase;font-weight:bold;">
				     :&nbsp;&nbsp;<c:out value="${dateinword}" />
				    </td></tr>
					
				   <tr>
				   <td >
					11.&nbsp;&nbsp;&nbsp;Last school and<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; class attended</td>
					 <td style="text-transform:uppercase;font-weight:bold;">:&nbsp;&nbsp;
					 ${branchname}
				   </td></tr>	
				
				   <tr><td></td>
				   <td style="text-transform:uppercase;font-weight:bold;">:&nbsp;&nbsp;
				   <c:set var="itemparts" value="${fn:split(studentdetails.student.classstudying, '--')}" />
						  <c:out value="${itemparts[0]}" />
				   </td>
				   </tr>
				
				   <tr>
				   <td>
					12.&nbsp;&nbsp;&nbsp;Date of admission </td><td style="text-transform:uppercase;font-weight:bold;">:&nbsp;&nbsp;
					<fmt:formatDate value="${studentdetails.student.admissiondate}" pattern="dd/MM/yyyy" />
					  &emsp;&emsp;<label style="text-transform:none; font-weight:normal;">Admission standard</label> &nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;<c:set var="itemparts" value="${fn:split(studentdetails.student.classadmittedin, '--')}" />
						  <c:out value="${itemparts[0]}" />
					 </td>
					 </tr>
				
				
				    <tr>
				    <td>
					13.&nbsp;&nbsp;&nbsp;Progress </td><td style="text-transform:uppercase;font-weight:bold;">:&nbsp;&nbsp;
				      ${progress} &nbsp;&nbsp;&nbsp;</td></tr><tr><td>
					 14.&nbsp;&nbsp;&nbsp;Conduct</td><td style="text-transform:uppercase;font-weight:bold;">:&nbsp;&nbsp;${conduct} 
					 </td></tr>
				
				    <tr>
 				    <td>
					15.&nbsp;&nbsp;&nbsp;Date of Leaving</td><td style="text-transform:uppercase;font-weight:bold;">:&nbsp;&nbsp;
					${leavingdate}
				    </td></tr>	
				
				
				    <tr>
				    <td >
					16.&nbsp;&nbsp;&nbsp;Standard in which studying  </td><td style="text-transform:uppercase;font-weight:bold;">:&nbsp;&nbsp;
				    <c:set var="itemparts" value="${fn:split(studentdetails.student.classstudying, '--')}" />
						  <c:out value="${itemparts[0]}" />&emsp;<c:out value="${studentdetails.student.yearofadmission}" />	
				     </td></tr>	
				
				
				   <tr>
				  <td >
					17.&nbsp;&nbsp;&nbsp;Reason of Leaving school </td><td style="text-transform:uppercase;font-weight:bold;">
					:&nbsp;&nbsp;${leavingReason}
				    </td></tr>	
					
				    <tr>
				    <td >
					18.&nbsp;&nbsp;&nbsp;Remarks</td><td style="text-transform:uppercase;font-weight:bold;">
					:&nbsp;&nbsp;${Remarks}
				     </td></tr>	
				
				<tr>
				<td colspan="2" text-align="center">
				Certified that the above information is in accordance with the school General Register
				</td>
				</tr>
				
				<tr>
				<td >
					19.&nbsp;&nbsp;&nbsp;Date of issue </td><td style="text-transform:uppercase;font-weight:bold;">:&nbsp;&nbsp;${dateOfTc}
					<%-- <input name="dateofcr" type="text" class="textField" style="border: none;border-color: transparent;"
					size="10" value="<fmt:formatDate type="date" value="${now}" pattern="dd/MM/yyyy"/>" >
				 --%></td></tr>	
		</table>
		
		
		<TABLE align="center" id="dataTable" >
		
		<tr>
		<td>
		<br><br>
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
                            <button id="print" type="button" style="background-image: url(/noblewisdom/images/print.jpg);width: 63px;height: 60px" onclick="window.print();
                                    this.style.visibility = 'hidden', loading.style.visibility = 'visible'" class="hide"></button>     
                        </td>
        </tr>
			<%-- <tr>
               <td align="center"><a id="print" href="/noblewisdom/DocumentsProcess/PrintTransferCertificate?id=<c:out value="${studentdetails.student.sid}" />">Print</a></td>
             </tr> --%>
		</TABLE>

		<%-- <a id="print" href="/noblewisdom/Controller?process=StudentProcess&action=GenerateBonafide&id=<c:out value="${studentdetails.student.sid}" />">Print</a> --%>
	</form>


</body>
</html>
<%-- 
    Document   : create new cases
    Created on : Aug 17, 2022, 1:57:17 AM
    Author     : Musaib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html >
    <head >
        <%
            response.setHeader("Cache-Control", "no-cache"); //Forces caches to obtain a new copy of the page from the origin server
            response.setHeader("Cache-Control", "no-store"); //Directs caches not to store the page under any circumstance
            response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
            response.setHeader("Pragma", "no-cache"); //HTTP 1.0 backward compatibility
        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create New Cases</title>
        <style type="text/css" title="currentStyle">
            @import "css/dataTable/css/demo_page.css";
            @import "css/dataTable/css/jquery.dataTables.css";
        </style>
        <link rel="stylesheet" href="/sla/css/datePicker/jquery-ui-1.8.17.custom.css">
        <link rel="stylesheet" href="/sla/css/datePicker/demos.css">
        <!--<script type="text/javascript" language="javascript" src="/sla/js/dataTable/jquery.js"></script>-->
        <script type="text/javascript" src="/sla/js/datePicker/jquery-1.7.1.js"></script>
        <script type="text/javascript" language="javascript" src="/sla/js/dataTable/jquery.dataTables.js"></script>
        <script type="text/javascript" src="/sla/js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
        <script type="text/javascript" src="/sla/js/datePicker/ui/jquery.ui.core.js"></script>
        <script type="text/javascript" src="/sla/js/datePicker/ui/jquery.ui.widget.js"></script>
        <script type="text/javascript" src="/sla/js/datePicker/ui/jquery.ui.button.js"></script>
		<script type="text/javascript" src="/sla/js/chosen.jquery.min.js"></script>
		<link rel="stylesheet" href="/sla/css/chosen.min.css">
       
        <style type="text/css" >
			.myclass {
				font-size: 1.3em;
				border-top-style: solid;
				border-right-style: solid;
				border-bottom-style: solid;
				border-left-style: solid;
				border-top-color: #5d7e9b;
				border-right-color: #5d7e9b;
				border-bottom-color: #5d7e9b;
				border-left-color: #5d7e9b;
				/* border-top-width: 1px;
				border-right-width: 1px;
				border-bottom-width: 1px;
				border-left-width: 1px; */
				width: 220px;
				height: 28px;
				color: black;
				text-transform: capitalize;
				border-radius: 4px;
			}
			.myclassone {
				font-size: 1.3em;
				border-top-style: solid;
				border-right-style: solid;
				border-bottom-style: solid;
				border-left-style: solid;
				border-top-color: #5d7e9b;
				border-right-color: #5d7e9b;
				border-bottom-color: #5d7e9b;
				border-left-color: #5d7e9b;
				width: 200px;
				height: 28px;
				color: black;
				text-transform: capitalize;
				border-radius: 4px;
			}
            <!--
            .header {
                font-family: Arial, Helvetica, sans-serif;
                font-size: 12px;
                background-color: #4b6a84;
            }
            .table {
                background-color: #3399CC;
                text-align: center;
                width: auto;


            }
            .headerText {
                border-radius:3px;
                width: 10px;
                font-family: Tahoma;
                font-size: 12px;
                background-color: #4b6a84;
                color: #FFFFFF;
                font-weight: normal;
                width: auto ;
                height: 22px;
                vertical-align: middle;
                text-align: center;

            }
            .dataText {
                border-radius:3px;
                font-family: Tahoma;
                color: #4b6a84;
                font-size: 13px;
                letter-spacing: normal;
                text-align: center;
                background-color: #E3EFFF;

            }
            .dataTextInActive {
                border-radius:3px;
                font-family: Tahoma;
                color: #4b6a84;
                font-size: 12px;
                font-weight: bold;
                letter-spacing: normal;
                text-align: center;
                background-color: #E3EFFF;
                text-decoration:none;
            }
            .dataTextActive {
                border-radius:3px;
                font-family: Tahoma;
                color: #4b6a84;
                font-size: 12px;
                font-weight: bold;
                letter-spacing: normal;
                text-align: center;
                background-color: #E3EFFF;
                text-decoration: underline;
            }
            .dataTextHidden {
                font-family: Tahoma;
                color: #4b6a84;
                font-size: 13px;
                letter-spacing: normal;
                text-align: center;
                background-color: #E3EFFF;

            }
            .headerTD{
                border-radius:6px;
                background-color:#4b6a84;
                background-image: url("/images/ui-bg_diagonals-small_50_466580_40x40.png");
                color: #FFFFFF;
                font-family: Tahoma;
                font-size: 13px;
                text-transform: uppercase;
                text-align: center;
                font-weight: bold;
                height: 22px;
            }
            .footerTD{
                border-radius:6px;
                background-color:#4b6a84;


                text-align: left;


            }
            .alignLeft {
				font-family: Tahoma;
				font-size: 14px;
				font-style: normal;
				text-transform: capitalize;
				color: #325F6D;
				text-align: left;
				vertical-align: middle;
				font-weight: bold;
			}           
        </style>
        <script type="text/javascript" charset="utf-8">
            $(document).ready(function() {
                $('#myTable').dataTable( {
                    "sScrollY": "380px",
                    "bPaginate": true,
                    "bLengthChange": false,
                    "bFilter": true,
                    "bSort": true,
                    "bInfo": true,
                    "bStateSave": false,
                    "bProcessing": false,
                    "bServerSide": false,
                    "bAutoWidth": false,
                    "iDisplayLength": 2000,
                    "aoColumnDefs":[
                        { 'bSortable': false, 'aTargets': [ 0 ] }
                    ]
                    
                } );
            } );
        </script>
        <script type="text/javascript">
            function deleteRecords(){
                var form1=document.getElementById("form1");
                form1.action="/sla/StudentProcess/archiveMultiple";
               form1.submit();
           }
            function filter2 (phrase, _id)
            {
                var words = phrase.value.toLowerCase().split(" ");
                var table = document.getElementById(_id);
                var ele;
                var dd=table.rows.length;
                //var aa=dd/2;
                var aa=dd-1;
                var display=true;

                for (var r = 1; r < table.rows.length; r++)
                {

                    ele = table.rows[r].innerHTML.replace(/<[^>]+>/g,"");
                    var displayStyle = 'none';
                    for (var i = 0; i < words.length; i++)
                    {
                        if (ele.toLowerCase().indexOf(words[i])>=0)
                        {
                            displayStyle = '';
                        }
                        else
                        {
                            displayStyle = 'none';
                            dd=dd-1;
                            display=false;
                            break;
                        }
                    }
                    table.rows[r].style.display = displayStyle;
                }

                var label = document.getElementById("labelDisplay");
                if(display==true)
                {
                    label.innerHTML = "Matching Results: "+aa;
                    label.style.display='none';
                }
                else
                {
                    label.innerHTML ="Matching Results: "+dd;
                    label.style.display='block';
                }
            }

        </script>
        
        <script type="text/javascript">
            $(function(){
                $('#chckHead').click(function () {
                    var length = $('.chcktbl:checked').length;
                    var trLength=$('.trClass').length;
                    if(length>0){
                        $('.chcktbl:checked').attr('checked', false);
                        this.checked=false;

                    }
                    else{
                        if (this.checked == false) {
                            $('.chcktbl:checked').attr('checked', false);
                        }
                        else {
                            $('.chcktbl:not(:checked)').attr('checked', true);
                        }

                    }

                });
                $('.chcktbl').click(function () {
                    var length = $('.chcktbl:checked').length;
                    var trLength=$('.trClass').length;
                    alert(tdLength);
                    if (length > trLength) {

                        $('.chcktbl:not(:checked)').attr('disabled', true);
                    }
                    else {
                        $('.chcktbl:not(:checked)').attr('disabled', false);
                    }
                });
                
                $( "#go" )
                .button()
                

            });
            
            function saveCases(){
                var form1=document.getElementById("form1");
               form1.action="/sla/CasesProcess/addCases";
               form1.submit();
            }
            
        </script>
        
        
        <script type="text/javascript">
            $(function(){
                $("#delete").button({
                    icons:{
                        primary: "ui-icon-trash"
                    }
                }).click(function(){
                	if(confirm('Are you sure,you want to archive?')){
                		deleteRecords();	
                	}
                    return false;

                });
                
                $("#createcases").button({
             		icons:{
                 		primary: "ui-icon-check"
             		}
         				}).click(function(){
             					saveCases();
             					return false;
		         });
                
                
                  $('#chckHeadReceived').click(function () {
                      var length = $('.chcktbl:checked').length;
                      var trLength=$('.trClass').length;
                      if(length>0){
                          $('.chcktbl:checked').attr('checked', false);
                          this.checked=false;

                      }
                      else{
                          if (this.checked == false) {
                              $('.chcktbl:checked').attr('checked', false);
                          }
                          else {
                              $('.chcktbl:not(:checked)').attr('checked', true);
                          }

                      }

                  });
                
                $('#chckHead').click(function () {
                    var length = $('.chcktbl:checked').length;
                    var trLength=$('.trClass').length;
                    if(length>0){
                        $('.chcktbl:checked').attr('checked', false);
                        this.checked=false;

                    }
                    else{
                        if (this.checked == false) {
                            $('.chcktbl:checked').attr('checked', false);
                        }
                        else {
                            $('.chcktbl:not(:checked)').attr('checked', true);
                        }

                    }

                });
                $('.chcktbl').click(function () {
                    var length = $('.chcktbl:checked').length;
                    var trLength=$('.trClass').length;
                    alert(tdLength);
                    if (length > trLength) {

                        $('.chcktbl:not(:checked)').attr('disabled', true);
                    }
                    else {
                        $('.chcktbl:not(:checked)').attr('disabled', false);
                    }
                });
                
                $( "#go" )
                .button()
                
                
            	var addItemsButtonID="#addnewitem";
                var removeItemsButtonID="#removenewitem";
                
                $( addItemsButtonID )
                .button({
                    icons: {
                        primary: "ui-icon-plus"
                    }
                })
                .click(function() {
                    addRow();
                    return false;
                });
                
               $(removeItemsButtonID)
                .button({
                    icons: {
                        primary: "ui-icon-minus"
                    }
                })
                .click(function() {
                    deleteRow('dataTableTasks');
                    return false;
                }); 
                

            });
            
            function deleteRow(tableID) {
                try {
                    var table = document.getElementById(tableID);
                    var rowCount = table.rows.length;
                    if(rowCount==1){
                        alert('No records to delete');
                    }
                    for(var i=1; i<rowCount; i++) {
                        var row = table.rows[i];
                        var chkbox = row.cells[0].childNodes[0];
                        if(null != chkbox && true == chkbox.checked) {
                            table.deleteRow(i);
                            rowCount--;
                            i--;
                        }
                    }
                }catch(e) {
                    alert(e);
                }
            }
            
				            
            function check(studentid){
            	
            	var length = $('.chcktbl:checked').length;
                var trLength=$('.trClass').length;
                if(length>0){
                    $('.chcktbl:checked').attr('checked', false);
                    this.checked=false;

                }
                else{
                    if (this.checked == false) {
                        $('.chcktbl:checked').attr('checked', false);
                    }
                }

            	
      	      	  document.getElementById("studentid_"+studentid).checked = true;  
      	      	  
      	      	  
            }
            
            $(function() {
        		$("#expecteddeliverydate").datepicker({
        			changeYear : true,
        			changeMonth : true,
        			dateFormat: 'dd/mm/yy',
        			yearRange: "-10:+0"
        		});
        		$("#anim").change(function() {
        			$("#expecteddeliverydate").datepicker("option", "showAnim", $(this).val());
        		});
        		$(".chosen-select").chosen({
        			  no_results_text: "Oops, nothing found!"
        			})
        	});
            
            
            function addRow() {
                var rowCount = document.getElementById('dataTableTasks').rows.length;    
                var col1="<td class='dataTextInActive'><input type='checkbox' class = 'chcktbl' id=items_"+rowCount+" /><input type='hidden' name='itemids' id=items_id_"+rowCount+" value='' /></td>";
                var col2="<td class='dataTextInActive'><label> <select name='referredbyone' id=onereferredby_"+rowCount+" class='myclassone'><option selected></option><c:forEach items='${studentList}' var='studentList'><option value='${studentList.sid}'><c:out value='${studentList.name}' /></option></c:forEach></select></label></td>";
                var newRow = $("<tr class='trClass'>"+col1+col2+"</tr>");
            
            $(function() {
                $("#dataTableTasks").find('tbody').append(newRow);
            });
            }  
            
        </script>
        
        <script type="text/javascript">

    		function chooseCourtName() {
        			var court = document.getElementById("court").value;

        			if (court === "DC") { 
            				document.getElementById("onselectedcourtsdistrict").style.display = '';
            				document.getElementById("onselectedcourtsrevenue").style.display = "none";
            				document.getElementById("onselectedcourts").style.display = "none";
    	    		}else if(court==="RC"){
        				document.getElementById("onselectedcourtsrevenue").style.display = '';
        				document.getElementById("onselectedcourtsdistrict").style.display = "none";
        				document.getElementById("onselectedcourts").style.display = "none";
	    			}else if(court==="AOC"){
        				document.getElementById("onselectedcourts").style.display = '';
        				document.getElementById("onselectedcourtsdistrict").style.display = "none";
        				document.getElementById("onselectedcourtsrevenue").style.display = "none";
	    			}
	    	}
    		
    		function chooseCourtType() {
    			var court = document.getElementById("court").value;

    			if (court === "SC") { 
        				document.getElementById("supremecourtdd").style.display = '';
        				document.getElementById("aocourtdd").style.display = "none";
        				document.getElementById("highcourtdd").style.display = "none";
        				document.getElementById("districtcourtdd").style.display = "none";
        				document.getElementById("ksatcourtdd").style.display = "none";
        				document.getElementById("waqfcourtdd").style.display = "none";
        				document.getElementById("rticourtdd").style.display = "none";
        				document.getElementById("revenuecourtdd").style.display = "none";
	    		}else if(court === "HC"){
	    			document.getElementById("supremecourtdd").style.display = "none";
	    			document.getElementById("aocourtdd").style.display = "none";
    				document.getElementById("highcourtdd").style.display = '';
    				document.getElementById("districtcourtdd").style.display = "none";
    				document.getElementById("ksatcourtdd").style.display = "none";
    				document.getElementById("waqfcourtdd").style.display = "none";
    				document.getElementById("rticourtdd").style.display = "none";
    				document.getElementById("revenuecourtdd").style.display = "none";
    			}else if (court === "DC"){
	    			document.getElementById("supremecourtdd").style.display = "none";
	    			document.getElementById("aocourtdd").style.display = "none";
    				document.getElementById("highcourtdd").style.display = "none";
    				document.getElementById("districtcourtdd").style.display = '';
    				document.getElementById("ksatcourtdd").style.display = "none";
    				document.getElementById("waqfcourtdd").style.display = "none";
    				document.getElementById("rticourtdd").style.display = "none";
    				document.getElementById("revenuecourtdd").style.display = "none";
    			}else if (court === "KSAT"){
	    			document.getElementById("supremecourtdd").style.display = "none";
	    			document.getElementById("aocourtdd").style.display = "none";
    				document.getElementById("highcourtdd").style.display = "none";
    				document.getElementById("districtcourtdd").style.display = "none";
    				document.getElementById("ksatcourtdd").style.display = '';
    				document.getElementById("waqfcourtdd").style.display = "none";
    				document.getElementById("rticourtdd").style.display = "none";
    				document.getElementById("revenuecourtdd").style.display = "none";
    			}else if (court === "WT"){
	    			document.getElementById("supremecourtdd").style.display = "none";
	    			document.getElementById("aocourtdd").style.display = "none";
    				document.getElementById("highcourtdd").style.display = "none";
    				document.getElementById("districtcourtdd").style.display = "none";
    				document.getElementById("ksatcourtdd").style.display = "none";
    				document.getElementById("waqfcourtdd").style.display = '';
    				document.getElementById("rticourtdd").style.display = "none";
    				document.getElementById("revenuecourtdd").style.display = "none";
    			}else if (court === "RTIC"){
	    			document.getElementById("supremecourtdd").style.display = "none";
	    			document.getElementById("aocourtdd").style.display = "none";
    				document.getElementById("highcourtdd").style.display = "none";
    				document.getElementById("districtcourtdd").style.display = "none";
    				document.getElementById("ksatcourtdd").style.display = "none";
    				document.getElementById("waqfcourtdd").style.display = "none";
    				document.getElementById("rticourtdd").style.display = '';
    				document.getElementById("revenuecourtdd").style.display = "none";
    			}else if (court === "RC"){
	    			document.getElementById("supremecourtdd").style.display = "none";
	    			document.getElementById("aocourtdd").style.display = "none";
    				document.getElementById("highcourtdd").style.display = "none";
    				document.getElementById("districtcourtdd").style.display = "none";
    				document.getElementById("ksatcourtdd").style.display = "none";
    				document.getElementById("waqfcourtdd").style.display = "none";
    				document.getElementById("rticourtdd").style.display = "none";
    				document.getElementById("revenuecourtdd").style.display = '';
    			}else if (court === "AOC"){
	    			document.getElementById("supremecourtdd").style.display = "none";
    				
    				document.getElementById("highcourtdd").style.display = "none";
    				document.getElementById("districtcourtdd").style.display = "none";
    				document.getElementById("ksatcourtdd").style.display = "none";
    				document.getElementById("waqfcourtdd").style.display = "none";
    				document.getElementById("rticourtdd").style.display = "none";
    				document.getElementById("aocourtdd").style.display = '';
    				document.getElementById("revenuecourtdd").style.display = "none";
    			}
    	} 		
    		
    	</script>
    	
    	
    	
    </head>
      <%
//allow access only if session exists
String user = null;
if(session.getAttribute("userAuth") == null){
	response.sendRedirect("/sla/UserProcess/sessionTimeOut");
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
    <body  >

        <form name="form1" id="form1" method="post">
            <div style="overflow: hidden" class="ui-tabs ui-widget ui-widget-content ui-corner-all">
                <table width="100%">
                    <tr>
                        <td  class="headerTD">Create Case</td>
                    </tr>
                </table>
		           		<table style="margin-left: auto;margin-right: auto;">
								<tr>
									<td><input type="hidden" id = "studentid_${parents.student.sid}" name="studentIDs"  value="<c:out value="${parents.pid}:${parents.contactnumber}:${parents.student.name}:${parents.student.sid}"/>"/>
									<input type="hidden" name="assignto" id="assignto" value="1:Muqtadir:9741948657"><br>
									</td>
								</tr>
								<tr>
									<td class="alignLeft" style="font-weight: bold;font-size: 16px;">Client Name:</td>
									<td style="font-weight: bold;font-size: 16px;color: #eb6000;text-transform: uppercase;"><label> <c:out value="${student.name}" /></label></td>
								</tr>		           		
		           				<tr>
									<td><br /></td>
								</tr>	           		
								<tr>
									<td class="alignLeft" style="font-weight: bold;font-size: 16px;">UID:</td>
									<td style="font-weight: bold;font-size: 16px;color: #eb6000"><label> <c:out value="${student.studentexternalid}" /></label></td>
								</tr>		           		
		           				<tr>
									<td><br /></td>
								</tr>
								
								<tr>
									<td class="alignLeft">Court:</td>
									<td><label> <select name="court" id="court" class="myclass" onchange="chooseCourtName();chooseCourtType()">
											<option selected></option>
											<option value="SC">Supreme Court</option>
											<option value="HC">High Court</option>
											<option value="DC">District Court</option>
											<option value="KSAT">Karnataka State Administrative Tribunal</option>
											<option value="WT">Waqf Tribunal</option>
											<option value="RC">Revenue Court</option>
											<option value="RTIC">RTI Cases</option>
											<option value="AOC">Any Other Court</option>
										</select>
									</label></td>
								</tr>
								
								<tr>
									<td><br></td>
								</tr>
								
								<tr id="supremecourtdd" style="display: none;">
									<td class="alignLeft">Case Type:</td>
									<td><label> <select name="supremecourt" id="supremecourt" class="myclass">
													<option selected></option>
													<option value="SLP (C)">SLP (Civil) - SLP(C)</option>
													<option value="TC (C)">Transfer Case (Civil) - TC(C) </option>
													<option value="SLP (CRL) CRMLP">SLP (Criminal) CRLMP - SLP(CRL) CRMLP</option>
													<option value="CRL A">Criminal Appeal - CRL A</option>
													<option value="SMC(C)">Suo Moto Contempt Petition (Civil) - SMC(C)</option>
													<option value="ELECT PET (C)">Election Petition (Civil) - ELECT PET (C)</option>
													<option value="WP (C)">Writ Petition (Civil) - WP (C)</option>
													<option value="WP (CRL)">Writ Petition (Criminal) - WP (CRL)</option>
													<option value="TP (C)">Transfer Petition (Civil) - TP (C) </option>
													<option value="RP (C)">Review Petition (Civil) - RP (C) </option>
													<option value="RP (CRL)">Review Petition (Criminal) - RP (CRL)</option>
													<option value="CONMT PET (C)">Contempt Petition (Civil) - CONMT PET (C)</option>
													<option value="CONMT PET (CRL)">Contempt Petition (Criminal) - CONMT PET (CRL)</option>
													<option value="CURATIVE PET(C) ">Curative Petition (Civil) - CURATIVE PET(C) </option>
													<option value="CURATIVE PET(R) ">Curative Petition (Criminal) - CURATIVE PET(R) </option>
													<option value="ARBIT CASE(C) ">Arbitration Petition - ARBIT CASE(C) </option>
													<option value="CA">Civil Appeal - CA</option>
													<option value="SMC(CRL)">Suo Moto Contempt Petition (Criminal) - SMC(CRL)</option>
													<option value="REF U/S 143">REF U/S 143</option>
													<option value="REF U/S 14 RTI">REF U/S 14 RTI</option>
													<option value="REF U/S 17 RTI">REF U/S 17 RTI</option>
													<option value="FILE NUMBER">FILE NUMBER</option>
												</select>
									</label></td>
								</tr>
								
								<tr id="highcourtdd" style="display: none;">
									<td class="alignLeft">Case Type:</td>
									<td><label> <select name="highcourt" id="highcourt" class="myclass">
													<option selected></option>
													<option value="AC">Arbitration Case - AC</option>
													<option value="AP EFA">Arbitration Petition (Enforcement of Foreign Tribnual Award) - AP EFA</option>
													<option value="AP IM">Arbitration Petition Interim Measure - AP IM</option>
													<option value="CA">Company Application Matter - CA</option>
													<option value="CCC">Civil Contempt Petition - CCC</option>
													<option value="CC(CIA)">Criminal Complaint (Commission of Inquiry Act) - CC(CIA)</option>
													<option value="CEA">Central Excise Appeal - CEA</option>
													<option value="CMP">Civil MISC Petition</option>
													<option value="COA">U/s. 10 of Companies Act - COA</option>
													<option value="COMAP">Commercial Appeals - COMAP</option>
													<option value="COMAP CR">Commercail Appeals Cross Objection - COMAP CR</option>
													<option value="COM APLN">Commercial Application - COM APLN</option>
													<option value="COMPA">Company Appeal - COMPA</option>
													<option value="COM S">Commercial Suit - COM S</option>
													<option value="COP">Company Petition - COP</option>
													<option value="CP">Civil Petition - CP</option>
													<option value="CP KLRA">CP on Karnataka Land Reforms Act - CP KLRA</option>
													<option value="CRA">Cross Appeals - CRA</option>
													<option value="CRC">Civil Referred Case - CRC</option>
													<option value="CRL A">Criminal Appeal - CRL A</option>
													<option value="CRL CCC">Criminal Contempt Petition - CRL CCC</option>
													<option value="CRL P">Criminal Petition - CRL P</option>
													<option value="CRL RC">Criminal Referred Case - CRL RC</option>
													<option value="CRL RP">Crimnal Revision Petition - CRL RP</option>
													<option value="CROB">Cross Objection - CROB</option>
													<option value="CRP">Civil Revision Petition - CRP</option>
													<option value="CSTA">Custom Appeal - CSTA</option>
													<option value="EP">Election Petition - EP</option>
													<option value="EX FA">Execution First Appeal - EX FA</option>
													<option value="EX SA">Execution Second Appeal - EXSA</option>
													<option value="GTA">Gift Tax Appeal - GTA</option>
													<option value="HRRP">House Rent Rev. Petition - HRRP</option>
													<option value="ITA">Income Tax Appeal - ITA</option>
													<option value="ITA CROB">I.T Appeal Cross Objection -ITA CROB</option>
													<option value="ITRC">Income Tax Referred Case - ITRC</option>
													<option value="LRRP">Land Reforms Revision Petition - LRRP</option>
													<option value="LTRP">Luxury Tax Revision Pet - LTRP</option>
													<option value="MFA">Miscl. First Appeal - MFA</option>
													<option value="MFA CROB">MFA Cross Objection  - MFA CROB</option>
													<option value="MISC">Misc - MISC</option>
													<option value="MISC CRL">Miscellaneous Case for CRML - MISC CRL</option>
													<option value="MISC CVL">Miscellaneous Case for Civil - MISC CVL</option>
													<option value="MISC P">Misc Petition - MISC P</option>
													<option value="MISC W">Miscellaneous Case for Writ - MISC W</option>
													<option value="MSA">Miscellaneous Secon Appeal - MSA</option>
													<option value="MSA CROB">MSA Cross Objection - MSA CROB</option>
													<option value="OLR">Official Liquidator Report - OLR</option>
													<option value="PROB CP">Probate Civil Petition - PROB CP</option>
													<option value="RERA A">RERA Appeals - RERA A</option>
													<option value="RFA">Regular First Appeal -RFA</option>
													<option value="RFA CROB">RFA Cross Objection - RFA CROB</option>
													<option value="RP">Review Petition - RP</option>
													<option value="RPFC">Rev.Pet Family Court - RPFC</option>
													<option value="RSA">Regular Second Appeal - RSA</option>
													<option value="RSA CROB">RSA Cross Objection - RSA CROB</option>
													<option value="SA">Second Appeal - SA</option>
													<option value="SCLAP">Supreme Court Leave Petition - SCLAP</option>
													<option value="STA">Salex Tax Appeals - STA</option>
													<option value="STRP">Sales Tax Revision Petition - STRP</option>
													<option value="TAET">Tax Appeal on Entry Tax - TAET</option>
													<option value="TOS">Testamentory Original Suit - TOS</option>
													<option value="TRC">Tax Referred Case - TRC</option>
													<option value="WA">Writ Appeal - WA </option>
													<option value="WA CROB">WA Cross Objection - WA CROB</option>
													<option value="WP">Writ Petition - WP</option>
													<option value="WPCP">Civil Petition in Writ Side - WPCP</option>
													<option value="WPHC">Habeas Corpus - WPHC</option>
													<option value="WTA">Welath Tax Appeal - WTA</option>
												</select>
									</label></td>
								</tr>
								
								<tr id="ksatcourtdd" style="display: none;">
									<td class="alignLeft">Case Type:</td>
									<td><label> <select name="ksatcourt" id="ksatcourt" class="myclass">
													<option selected></option>
													<option value="OA">Original Application - OA</option>
													<option value="RA">Review Application - RA</option>
													<option value="CA">Contempt Application - CA</option>
													<option value="MA">Miscellaneous Application - MA</option>
												</select>
									</label></td>
								</tr>
								
								<tr id="districtcourtdd" style="display: none;">
									<td class="alignLeft">Case Type:</td>
									<td><label> <select name="districtcourt" id="districtcourt" class="myclass">
													<option selected></option>
													<option value="AA">Arbitration Application - AA</option>
													<option value="AC">Arbitration Cases - AC</option>
													<option value="AIR MISC">Pre Trial - AIR MISC</option>
													<option value="AP">Arbitration Main and Misc - AP</option>
													<option value="Appeal">Appeal</option>
													<option value="Appl 10(1)(c)">Application u/s 10(1)(c) </option>
													<option value="Appl 10(4)(A)">Application u/s 10(4)(A) </option>
													<option value="Appl 33(2)b">Application u/s 33(2)b</option>
													<option value="APPL 33(C)(2)">APPL 33(C)(2)</option>
													<option value="APPLN">Application - APPLN</option>
													<option value="Appln u/s 33 A of ID Act">Application under section 33</option>
													<option value="Appl u/s 11">Application u/s 11</option>
													<option value="AS">Arbitration Suits - AS</option>
													<option value="CC">Criminal Cases - CC</option>
													<option value="CCC">Closer Criminal Cases - CCC</option>
													<option value="C Crl MISC">Closer Crl.miscellaneous - C Crl MISC</option>
													<option value="C CRL RP">Closer Criminal Revision Petition - C CRL RP</option>
													<option value="CJC">Closer Juvenile Cases - CJC</option>
													<option value="COA">Company Applications - COA</option>
													<option value="COM AA">Commercial Arbitration Application - COM AA</option>
													<option value="Com AP">Commercial Arbitration Petition - Com AP</option>
													<option value="COM AS">Commercial Arbitration Suit - COM AS</option>
													<option value="COM EX">Commercial Execution Case - COM EX</option>
													<option value="COM MISC">Commercial Miscellaneous Case - COM MISC</option>
													<option value="COM OS">Commercial Original Suit - COM OS</option>
													<option value="COP">Company Petitions - COP</option>
													<option value="CC">Consumer Complaint - CC</option>
													<option value="EA">Consumer Execution Application - EA</option>
													<option value="Cr">Crime Case - Cr</option>
													<option value="CRL A">Criminal Appeal - CRL A</option>
													<option value="Crl Misc">Criminal Miscellaneous Cases - Crl Misc</option>
													<option value="Crl EP">Criminal Execution Petition - Crl EP</option>
													<option value="CRL MA">Criminal Misc.Appeal - CRL MA</option>
													<option value="CRL RP">Criminal Revision Petitions - CRL RP</option>
													<option value="EAT">Education Appellate Tribunal - EAT</option>
													<option value="ECA">Employee Compensation Applicant - ECA</option>
													<option value="ELEC A">Election Appeal - ELEC A</option>
													<option value="ELEC C">Election Petitions - ELEC C</option>
													<option value="EX">Execution Petition Under Order - EX</option>
													<option value="Ex A">Execution Appeals - Ex A</option>
													<option value="Ex C">Execution Cases - Ex C</option>
													<option value="FDP">Petitioner for Final Decree Pr - FDP</option>
													<option value="FIR">First Information Report - FIR</option>
													<option value="G and WC">Appointment of Guardian, other - G and WC</option>
													<option value="HRC">House Rent Control Cases - HRC</option>
													<option value="HRCA">House Rent Control Appeals - HRCA</option>
													<option value="IC">Insolvency Cases - IC</option>
													<option value="IDact S10">Under Sec 10 of ID Act Ref.Ca - IDact S10</option>
													<option value="IID 1947 Sec 32(A)">Sec 32 (A) - IID 1947 Sec 32(A)</option>
													<option value="Ind Emp SO Act">Appeal under Industrial Employ - Ind Emp SO Act</option>
													<option value="JC">Juvenile Cases - JC</option>
													<option value="KID">KID</option>
													<option value="KID 10 4A">KID 10-4A - KID 10 4A</option>
													<option value="LAC">Land Acquisition Cases - LAC</option>
													<option value="LAC (APPL)">L.A.C.APPEAL - LAC (APPL)</option>
													<option value="MA">Miscellaneous Appeal - MA</option>
													<option value="MC">Matrimonial Cases - MC</option>
													<option value="Misc">Miscellaneous Cases - Misc </option>
													<option value="Misc Appln">Miscellaneous Application - Misc Appln</option>
													<option value="MVC">Accident Claimes under M.V. - MVC</option>
													<option value="OL">Other Law Cases - OL</option>
													<option value="OS">Original Suit - OS</option>
													<option value="P and SC">Probate and Succession Cases - P and SC</option>
													<option value="PCR">Private Complaints - PCR</option>
													<option value="PLAC">Permanent Lok Adalat Case - PLAC</option>
													<option value="P MIS">Petition Filed Indegent Person - P MIS</option>
													<option value="RA">Regular Appeal - RA</option>
													<option value="REV">Revision Petitions - REV</option>
													<option value="REV (RENT)">Revision Petition under RENT C - REV (RENT)</option>
													<option value="Ref 10(1)(c) ">Reference u/s 10(1)(c) - Ref 10(1)(c) </option>
													<option value="Ref U/S 2A2">Ref U/S 2A2</option>
													<option value="RP">Review Petition - RP</option>
													<option value="SC">Session Cases - SC</option>
													<option value="SC">Small Cause Suit - SC</option>
													<option value="SPLC">Special Cases - SPL C</option>
													<option value="SPL C 376">Rape Case - SPL C 376</option>
													<option value="SPL C (AC Act)">Special Cases Anti Corruption - SPL C (AC Act)</option>
													<option value="Spl Case IPC">Special Case (IPC and CPR) - Spl Case IPC</option>
													<option value="SPL C (CBI)">CBI Cases - SPL C (CBI)</option>
													<option value="SPL C Corr">Special Cases Corruption - SPL C Corr</option>
													<option value="SPL CDRUGS AND C ACT">SPL.C Drugs and Cosmetics Act - SPL CDRUGS AND C ACT</option>
													<option value="SPL C (EC Act)">Special Essential Commodities - SPL C (EC Act)</option>
													<option value="SPL C (Elect)">Special Elect. Cases - SPL C (Elect)</option>
													<option value="Spl C KCOCA">Special Case KCOCA - Spl C KCOCA</option>
													<option value="SPL C NDPS">Special Cases N.D.P.S - SPL C NDPS</option>
													<option value="SPL C POCS">Protection of Child - SPL C POCS</option>
													<option value="SPL C SC ST">Special Cases SC ST PA.ACT - SPL C SC ST</option>
													<option value="SPL C (SVC)">SVC Cases - SPL C (SVC)</option>
													<option value="SPL KPTCL">Karnatak Power Transfer Corp - SPL KPTCL</option>
													<option value="WC">Workmen Compensation - WC</option>

												</select>
									</label></td>
								</tr>
								
								<tr id="waqfcourtdd" style="display: none;">
									<td class="alignLeft">Case Type:</td>
									<td><label> <select name="waqfcourt" id="waqfcourt" class="myclass">
													<option selected></option>
													<option value="APPLICATION">Application - APPLICATION</option>
													<option value="APPEAL">Appeal - APPEAL</option>
													<option value="CIV MISC">Civil Miscellaneous - CIV MISC</option>
													<option value="OS">Original Suit - OS</option>
													<option value="RP">Review Petition - RP</option>
												</select>
									</label></td>
								</tr>
								
								<tr id="rticourtdd" style="display: none;">
									<td class="alignLeft">Case Type:</td>
									<td><label> <select name="rticourt" id="rticourt" class="myclass">
													<option selected></option>
													<option value="RTI APPLN">Application - RTI APPLN</option>
													<option value="RTI FA">First Appeal - RTI FA</option>
													<option value="RTI SA">Second Appeal - RTI SA</option>
													<option value="KIC APL">KIC APL - KIC APL</option>
													<option value="KIC COM">KIC COM - KIC COM</option>
													<option value="KIC PTN">KIC PTN - KIC PTN</option>
												</select>
									</label></td>
								</tr>
								
								
								<tr id="revenuecourtdd" style="display: none;">
									<td class="alignLeft">Case Type:</td>
									<td><label> <input type="text" class="myclass" name="revenuecourt" id="revenuecourt">
									</label></td>
								</tr>
								
								<tr id="aocourtdd" style="display: none;">
									<td class="alignLeft">Case Type:</td>
									<td><label> <input type="text" class="myclass" name="anyothercourt" id="anyothercourt">
									</label></td>
								</tr>
								
								<tr>
									<td><br></td>
								</tr>
								
								
								<tr>
									<td class="alignLeft">Case No:</td>
									<td><label> <input type="text" class="myclass" name="caseno" id="caseno">
									</label></td>
								</tr>
								
								<tr>
									<td><br></td>
								</tr>
								
								<tr >
									<td class="alignLeft">Year:</td>
									<td><label> <select name="caseyear" id="caseyear" class="myclass">
													<option selected></option>
													<option value="2022">2022</option>
													<option value="2021">2021</option>
													<option value="2020">2020</option>
													<option value="2019">2019</option>
													<option value="2018">2018</option>
													<option value="2017">2017</option>
													<option value="2016">2016</option>
													<option value="2015">2015</option>
													<option value="2014">2014</option>
													<option value="2013">2013</option>
													<option value="2012">2012</option>
													<option value="2011">2011</option>
													<option value="2010">2010</option>
													<option value="2009">2009</option>
													<option value="2008">2008</option>
													<option value="2007">2007</option>
													<option value="2006">2006</option>
													<option value="2005">2005</option>
													<option value="2004">2004</option>
													<option value="2003">2003</option>
													<option value="2002">2002</option>
													<option value="2001">2001</option>
													<option value="2000">2000</option>
													<option value="1999">1999</option>
													<option value="1998">1998</option>
													<option value="1997">1997</option>
													<option value="1996">1996</option>
												</select>
									</label></td>
								</tr>
								
								<tr>
									<td><br></td>
								</tr>
								
								<tr style="display: none;" id="onselectedcourtsdistrict">
									<td class="alignLeft">Court Name:</td>
									<td><label> <select class="myclass" name="districtcourtname" id="districtcourtname">
												<option selected></option>
													<option value="PDJ">PRL District and Sessions Judge - PDJ</option>
													<option value="DJ1">First Addl District and Sessions Judge - DJ1</option>
													<option value="DJ2">Second Addl District and Sessions Judge - DJ2</option>
													<option value="DJ3">Third Addl District and Sessions Judge - DJ3</option>
													<option value="DJ4">Fourth Addl District and Sessions Judge - DJ4</option>
													<option value="DJ5">Fifth Addl District and Sessions Judge - DJ5</option>
													<option value="POCSO">POCSO Court - POCSO</option>
													<option value="PFC">PRL Family Court - PFC</option>
													<option value="FC1">First Addl Family Court - FC1</option>
													<option value="LC">Labour Court - LC</option>
													<option value="CC">Consumer Court - CC</option>
													<option value="COMC">Commercial Court - COMC</option>
													<option value="PLA">Permanent Lok Adalat - PLA</option>
													<option value="JJB">Juvenile Justice Board - JJB</option>
													<option value="PSCJ">PRL Senior Civil Judge and CJM - PSCJ</option>
													<option value="SCJ1">First Addl Senior Civil Judge and CJM - SCJ1</option>
													<option value="SCJ2">Second Addl Senior Civil Judge and CJM - SCJ2</option>
													<option value="SCJ3">Third Addl Senior Civil Judge and CJM - SCJ3</option>
													<option value="PCJ">PRL Civil Judge and JMFC - PCJ</option>
													<option value="CJ1">First Addl Civil Judge and JMFC - CJ1</option>
													<option value="CJ2">Second Addl Civil Judge and JMFC - CJ2</option>
													<option value="CJ3">Third Addl Civil Judge and JMFC - CJ3</option>
													<option value="CJ4">Fourth Addl Civil Judge and JMFC - CJ4</option>
													<option value="CJ5">Fifth Addl Civil Judge and JMFC - CJ5</option>
													<option value="SCJ Chitapur">Senior Civil Judge and CJM, Chitapur - SCJ Chitapur</option>
													<option value="CJ Chitapur">Civil Judge and JMFC, Chitapur - CJ Chitapur</option>
													<option value="CJ Shahabad">Civil Judge and JMFC, Shahabad - CJ Shahabad</option>
													<option value="SCJ Jevargi">Senior Civil Judge and CJM, Jevargi - SCJ Jevargi</option>
													<option value="CJ Jevargi">Civil Judge and JMFC, Jevargi - CJ Jevargi</option>
													<option value="SCJ Afzalpur">Senior Civil Judge and CJM, Afzalpur - SCJ Afzalpur</option>
													<option value="CJ Afzalpur">Civil Judge and JMFC, Afzalpur - CJ Afzalpur</option>
													<option value="SCJ Aland">Senior Civil Judge and CJM, Aland - SCJ Aland</option>
													<option value="PCJ Aland">PRL Civil Judge and JMFC, Aland - PCJ Aland </option>
													<option value="SCJ Sedam">Senior Civil Judge and CJM, Sedam - SCJ Sedam</option>
													<option value="CJ Sedam">Civil Judge and JMFC, Sedam - CJ Sedam</option>
													<option value="SCJ Chincholi">Senior Civil Judge and CJM, Chincholi - SCJ Chincholi</option>
													<option value="CJ Chincholi">Civil Judge and JMFC, Chincholi - CJ Chincholi</option>
													<option value="DJ Yadgir">District and Sessions Judge, Yadgir - DJ Yadgir</option>
													<option value="PSCJ Shorapur">PRL Senior Civil Judge and CJM, Shorapur - PSCJ Shorapur</option>
													<option value="PCJ Shorapur">PRL Civil Judge and JMFC, Shorapur - PCJ Shorapur</option>
													<option value="SCJ Shahapur">Senior Civil Judge and JMFC, Shahapur - SCJ Shorapur</option>
													<option value="CJ Shahapur">Civil Judge and JMFC, Shahapur - CJ Shorapur</option>
													<option value="SCJ Manvi">Senior Civil Judge and JMFC, Manvi - SCJ Manvi</option>
													<option value="CJ Manvi">Civil Judge and JMFC, Manvi - CJ Manvi</option>
										</select>
									</label></td>
								</tr>
								
								<tr style="display: none;" id="onselectedcourtsrevenue">
									<td class="alignLeft">Court Name:</td>
									<td><label> <select class="myclass" name="revenuecourtname" id="revenuecourtname">
													<option selected></option>
													<option value="KAT">Karnataka Appellate Tribunal - KAT</option>
													<option value="RC">Rigional Commissioner - RC</option>
													<option value="DC">Deputy Commissioner - DC</option>
													<option value="AC">Assistant Commissioner - AC</option>
													<option value="THS">Tahsildar - THS</option>
													<option value="THS ALN">Tahsildar, Aland - THS ALN</option>
													<option value="JDLR">Joint Director of Land Records - JDLR</option>
													<option value="DDLR">Deputy Director of Land Records - DDLR</option>
													<option value="ADLR">Assistant Director of Land Records - ADLR</option>
													<option value="CCK">City Corporation Kalaburagi - CCK</option>
													<option value="DC Raichur">Deputy Commissioner, Raichur - DC Raichur</option>
													<option value="AC Raichur">Assitant Commissioner, Raichur - AC Raichur</option>
													<option value="DDLR Raichur">Deputy Director of Land Records, Raichur - DDLR Raichur</option>
													<option value="ADLR Raichur">Assistant Director of Land Records, Raichur - ADLR Raichur</option>
													<option value="THS Manvi">Tahsildar, Manvi - THS Manvi</option>
													<option value="TMC Manvi">Town Municipal Council, Manvi - TMC Manvi</option>
													<option value="DC Bidar">Deputy Commissioner, Bidar - DC Bidar</option>
													<option value="ARCS">Assistant Registrar of Co-operative Societies - ARCS</option>
											</select>
									</label></td>
								</tr>
								
								<tr style="display: none;" id="onselectedcourts">
									<td class="alignLeft">Court Name:</td>
									<td><label> <input type="text" class="myclass" name="courtname" id="courtname">
									</label></td>
								</tr>
								
								<tr>
									<td><br></td>
								</tr>
								
								<tr >
									<td class="alignLeft">Petitioner:</td>
									<td><label> <input type="text" class="myclass" name="petitioner" id="petitioner">
									</label></td>
								</tr>
								
								<tr>
									<td><br></td>
								</tr>
								
								<tr >
									<td class="alignLeft">Respondent:</td>
									<td><label> <input type="text" class="myclass" name="respondent" id="respondent">
									</label></td>
								</tr>
								
								<tr>
									<td><br></td>
								</tr>
								
								<tr>
									<td class="alignLeft">Referred By:</td>
									<td>
										<select multiple class="chosen-select" name="referredby" id="referredby" style="width: 225px;border-radius: 4px;">
  										     <c:forEach items='${studentList}' var='studentList'>
  										     	<option value='${studentList.sid}'><c:out value='${studentList.name}' /></option>
  										     </c:forEach>
  										  </select>
										<!-- <button id="addnewitem"></button><button id="removenewitem"></button> -->
									</td>
								
								</tr>
								
								<tr>
									<td></td>
									<!-- <td>
										<table style="margin-left: auto;margin-right: auto;border: 0px solid black;" id="dataTableTasks">
											<thead>
												<tr>
													<th class="headerText">
														<input type="checkbox" id="chckHeadReceived" />
													<th class="headerText">Client</th>
												</tr>
											</thead>
					
											<tbody>						
											</tbody>
										</table>
									</td> -->
								</tr>
								
								
								
						</table>
						<div align="center">
						<p>
						<label><button id="createcases">Save</button></label></p>
					</div>
					</div>
        </form>
    </body>
</html>
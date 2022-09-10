<%-- 
    Document   : create new caveat
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
        <title>Create New Caveat</title>
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
				border-top-width: 1px;
				border-right-width: 1px;
				border-bottom-width: 1px;
				border-left-width: 1px;
				width: 220px;
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
            
            function saveCaveat(){
                var form1=document.getElementById("form1");
               form1.action="/sla/CaveatProcess/addCaveats";
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
                
                $("#createcaveat").button({
             		icons:{
                 		primary: "ui-icon-check"
             		}
         				}).click(function(){
             					saveCaveat();
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
        		$("#caveatdate").datepicker({
        			changeYear : true,
        			changeMonth : true,
        			dateFormat: 'dd/mm/yy',
        			yearRange: "-10:+0"
        		});
        		$("#anim").change(function() {
        			$("#caveatdate").datepicker("option", "showAnim", $(this).val());
        		});
        	});
            
            $(function() {
        		$("#caveatexpirydate").datepicker({
        			changeYear : true,
        			changeMonth : true,
        			dateFormat: 'dd/mm/yy',
        			yearRange: "-10:+0"
        		});
        		$("#anim").change(function() {
        			$("#caveatexpirydate").datepicker("option", "showAnim", $(this).val());
        		});
        	});
            
            function addRow() {
                var rowCount = document.getElementById('dataTableTasks').rows.length;    
                
                var col1="<td class='dataTextInActive'><input type='checkbox' class = 'chcktbl' id=items_"+rowCount+" /><input type='hidden' name='itemids' id=items_id_"+rowCount+" value='' /></td>";
                var col2="<td class='dataTextInActive'><label> <select name='assignto' id=assignto_"+rowCount+" style='width: 250px;height: 25px;'><option selected></option><c:forEach items='${employeeList}' var='employeeList'><option value='${employeeList.tid}:${employeeList.teachername}:${employeeList.contactnumber}'><c:out value='${employeeList.teachername}' /></option></c:forEach></select></label></td>";
                var col3="<td class='dataTextInActive'><input type='text' name='task'  id=task_"+rowCount+" class='textfieldvaluesshorts' style='font-size: 14px;'/></td>";
                var col4="<td class='dataTextInActive'><input type='text' name='description'  id=description_"+rowCount+" class='textfieldvaluesshorts' style='font-size: 14px;' /></td>";
                var col5="<td class='dataTextInActive'><label><input type='date'  name='expecteddeliverydatetask' class='textfield' style='font-size: 14px;' style='width: 250px;height: 25px;'	id=expecteddeliverydatetask_"+rowCount+" autocomplete='false'></label></td>";
                /* var col4="<td class='dataTextInActive'><input type='text' value='1' onclick='calculate("+rowCount+")'  onkeyup='calculate("+rowCount+")' name='feesQuantities' id=fees_quantity_"+rowCount+" /><input type='hidden'   id=hiddenfees_quantity_"+rowCount+" value='' /></td>"; */
                /* var col4="<td class='dataTextInActive'><select  onchange='calculate("+rowCount+")'  name='feesQuantities' id=fees_quantity_"+rowCount+"><option></option><option>JAN</option><option>Feb</option><option>MAR</option><option>APR</option><option>MAY</option><option>JUN</option><option>JUL</option><option>AUG</option><option>SEP</option><option>OCT</option><option>NOV</option><option>DEC</option></select><input type='hidden'   id=hiddenfees_quantity_"+rowCount+" value='' /></td>"; */
                /* var col4="<td class='dataTextInActive'><input class='feesAmount' type='text' value='0'      name='feesAmounts' id=fees_amount_"+rowCount+" /></td>"; */
                var newRow = $("<tr class='trClass'>"+col1+col2+col3+col4+col5+"</tr>");
            
            $(function() {
                $("#dataTableTasks").find('tbody').append(newRow);
            });
            }  
        </script>
        
        <script type="text/javascript">

    		function chooseCourtName() {
        			var court = document.getElementById("court").value;

        			if (court === "DC" || court==="RC" || court==="AOC") { 
            				document.getElementById("onselectedcourts").style.display = '';
    	    		}else {
        				document.getElementById("onselectedcourts").style.display = "none";
	    		} 
	    	}
    		
    		function chooseCourtType() {
    			var court = document.getElementById("court").value;

    			if (court === "SC") { 
        				document.getElementById("supremecourtdd").style.display = '';
        				
        				document.getElementById("highcourtdd").style.display = "none";
        				document.getElementById("districtcourtdd").style.display = "none";
        				document.getElementById("ksatcourtdd").style.display = "none";
        				document.getElementById("waqfcourtdd").style.display = "none";
        				document.getElementById("rticourtdd").style.display = "none";
        				document.getElementById("revenuecourtdd").style.display = "none";
	    		}else if(court === "HC"){
	    			document.getElementById("supremecourtdd").style.display = "none";
    				
    				document.getElementById("highcourtdd").style.display = '';
    				document.getElementById("districtcourtdd").style.display = "none";
    				document.getElementById("ksatcourtdd").style.display = "none";
    				document.getElementById("waqfcourtdd").style.display = "none";
    				document.getElementById("rticourtdd").style.display = "none";
    				document.getElementById("revenuecourtdd").style.display = "none";
    			}else if (court === "DC"){
	    			document.getElementById("supremecourtdd").style.display = "none";
    				
    				document.getElementById("highcourtdd").style.display = "none";
    				document.getElementById("districtcourtdd").style.display = '';
    				document.getElementById("ksatcourtdd").style.display = "none";
    				document.getElementById("waqfcourtdd").style.display = "none";
    				document.getElementById("rticourtdd").style.display = "none";
    				document.getElementById("revenuecourtdd").style.display = "none";
    			}else if (court === "KSAT"){
	    			document.getElementById("supremecourtdd").style.display = "none";
    				
    				document.getElementById("highcourtdd").style.display = "none";
    				document.getElementById("districtcourtdd").style.display = "none";
    				document.getElementById("ksatcourtdd").style.display = '';
    				document.getElementById("waqfcourtdd").style.display = "none";
    				document.getElementById("rticourtdd").style.display = "none";
    				document.getElementById("revenuecourtdd").style.display = "none";
    			}else if (court === "WT"){
	    			document.getElementById("supremecourtdd").style.display = "none";
    				
    				document.getElementById("highcourtdd").style.display = "none";
    				document.getElementById("districtcourtdd").style.display = "none";
    				document.getElementById("ksatcourtdd").style.display = "none";
    				document.getElementById("waqfcourtdd").style.display = '';
    				document.getElementById("rticourtdd").style.display = "none";
    				document.getElementById("revenuecourtdd").style.display = "none";
    			}else if (court === "RTIC"){
	    			document.getElementById("supremecourtdd").style.display = "none";
    				
    				document.getElementById("highcourtdd").style.display = "none";
    				document.getElementById("districtcourtdd").style.display = "none";
    				document.getElementById("ksatcourtdd").style.display = "none";
    				document.getElementById("waqfcourtdd").style.display = "none";
    				document.getElementById("rticourtdd").style.display = '';
    				document.getElementById("revenuecourtdd").style.display = "none";
    			}else if (court === "RC"){
	    			document.getElementById("supremecourtdd").style.display = "none";
    				
    				document.getElementById("highcourtdd").style.display = "none";
    				document.getElementById("districtcourtdd").style.display = "none";
    				document.getElementById("ksatcourtdd").style.display = "none";
    				document.getElementById("waqfcourtdd").style.display = "none";
    				document.getElementById("rticourtdd").style.display = "none";
    				document.getElementById("revenuecourtdd").style.display = '';
    			}
    	} 		
    		
    		/*  function calculateValidity(id){
    			 
    			 var startDate = document.getElementById('caveatdate').value;
    			 var dateSplit = startDate.split('/');
    			 var month = parseInt(dateSplit[1])+parseInt(3);
    			 var endDate = dateSplit[0]+'/'+month+'/'+dateSplit[2];
    			 document.getElementById('validtill').value = endDate;
    			 
    		 } */
    		
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
									<td><label> <select name="court" id="court" class="myclass" onchange="chooseCourtName();">
											<option selected></option>
											<option value="SC">Supreme Court</option>
											<option value="HC">High Court</option>
											<option value="DC">District Court</option>
											<option value="KSAT">Karnataka State Administrative Tribunal</option>
											<option value="WT">Waqf Tribunal</option>
											<option value="RC">Revenue Court</option>
											<option value="RTIC">RTI Commission</option>
											<option value="CC">Consumer Court</option>
											<option value="CSC">Co-operative Societies Court</option>
											<option value="AOC">Any Other Court</option>
										</select>
									</label></td>
								</tr>
								
								<tr>
									<td><br></td>
								</tr>
								
								<tr>
									<td class="alignLeft">Caveat No:</td>
									<td><label> <input type="text" class="myclass" name="caveatno" id="caveatno">
									</label></td>
								</tr>
								
								<tr>
									<td><br></td>
								</tr>
								
								<tr>
									<td class="alignLeft">Year:</td>
									<td><label> <select name="caveatyear" id="caveatyear" class="myclass">
													<option selected value="2022">2022</option>
												</select>
									</label></td>
								</tr>
								
								<tr>
									<td><br></td>
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
								
								<tr >
									<td class="alignLeft">Caveat Date:</td>
									<td><label>
											<input type="text"  name="caveatdate"
												class="myclass"
												id="caveatdate" autocomplete="false"
												data-validate="validate(required)">
									</label></td>
								</tr>
								
								<tr>
									<td><br></td>
								</tr>
								<tr >
									<td class="alignLeft">Caveat Expiry Date:</td>
									<td><label>
											<input type="text"  name="caveatexpirydate"
												class="myclass"
												id="caveatexpirydate" autocomplete="false"
												data-validate="validate(required)">
									</label></td>
								</tr>
								
								<tr>
									<td><br></td>
								</tr>
						</table>
						<div align="center">
						<p>
						<label><button id="createcaveat">Save</button></label></p>
					</div>
					</div>
        </form>
    </body>
</html>
<%-- 
    Document   : viewAll
    Created on : Dec 29, 2012, 1:57:17 PM
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
        <title>Create Query</title>
        <style type="text/css" title="currentStyle">
            @import "css/dataTable/css/demo_page.css";
            @import "css/dataTable/css/jquery.dataTables.css";
        </style>
        <link rel="stylesheet" href="/demov2/css/datePicker/jquery-ui-1.8.17.custom.css">
        <link rel="stylesheet" href="/demov2/css/datePicker/demos.css">
        <!--<script type="text/javascript" language="javascript" src="/demov2/js/dataTable/jquery.js"></script>-->
        <script type="text/javascript" src="/demov2/js/datePicker/jquery-1.7.1.js"></script>
        <script type="text/javascript" language="javascript" src="/demov2/js/dataTable/jquery.dataTables.js"></script>
        <script type="text/javascript" src="/demov2/js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
        <script type="text/javascript" src="/demov2/js/datePicker/ui/jquery.ui.core.js"></script>
        <script type="text/javascript" src="/demov2/js/datePicker/ui/jquery.ui.widget.js"></script>
        <script type="text/javascript" src="/demov2/js/datePicker/ui/jquery.ui.button.js"></script>
        <style type="text/css" >
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
                form1.action="/demov2/StudentProcess/archiveMultiple";
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
            
            function saveTask(){
                var form1=document.getElementById("form1");
               form1.action="/demov2/JobProcess/addTask";
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
                
                $(".querybutton").button({
                    icons:{
                        primary: "ui-icon-pencil"
                    }
                })
                
                
                $("#createtask").button({
             		icons:{
                 		primary: "ui-icon-check"
             		}
         				}).click(function(){
             					saveTask();
             					return false;
		         });
                
                $(function() {
                    $( "#dialogquery" ).dialog({
                        autoOpen: false,
                        height: 580,
                        width: 650,
                        modal: true,
                        buttons: {
                            OK: function() {                              	
                            	writeQuery(document.getElementById("assignto"),document.getElementById("filetype"),
                            			document.getElementById("typeofwork"),document.getElementById("typeofworkcourt"),document.getElementById("typeofworknoncourt"),
                            			document.getElementById("typeofworkcourtcases"),document.getElementById("typeofworkcourtdocs"),document.getElementById("typeofworknoncourtabt"),
                            			document.getElementById("typeofworknoncourtcd"),document.getElementById("typeofworknoncourtsr"),document.getElementById("typeofworknoncourtdr"),
                            			document.getElementById("typeofworknoncourtcs"),document.getElementById("typeofworknoncourturd"),document.getElementById("typeofworknoncourtrlo"),
                            			document.getElementById("typeofworknoncourtmw"),document.getElementById("typeofworknoncourtno"),document.getElementById("expecteddeliverydate"));
                                		$( this ).dialog( "close" );
                         		   }
                        }
                    });
                });
                
                
                $(".appointmentbutton").button({
                    icons:{
                        primary: " ui-icon-calendar"
                    }
                }).click(function(){
                	$( "#dialogappointment" ).dialog( "open" );
                    return false;
                	});
                
                  $(function() {
                      $( "#dialogappointment" ).dialog({
                          autoOpen: false,
                          height: 230,
                          width: 550,
                          modal: true,
                          buttons: {
                              OK: function() {                              	
                            	  fixAppointment(document.getElementById("appointmentdate"),document.getElementById("appointmenttime"));
                                  		$( this ).dialog( "close" );
                           		   }
                          }
                      });
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
            
				function fixAppointment(appointmentdate,appointmenttime){
		            	  
						var appointmentDate = appointmentdate.value;
						var appointmentTime = appointmenttime.value;
		            	var form1 = document.getElementById("form1");
		        		form1.action = "/demov2/AppointmentProcess/addAppointment?appointmentdate="+appointmentDate+"&appointmenttime="+appointmentTime+"";
		        		form1.method = "POST";
		        		form1.submit();
		            }
		
				function writeQuery(assignto,filetype,typeofwork,typeofworkcourt,typeofworknoncourt,typeofworkcourtcases,typeofworkcourtdocs,typeofworknoncourtabt,typeofworknoncourtcd,typeofworknoncourtsr,typeofworknoncourtdr,typeofworknoncourtcs,typeofworknoncourturd,typeofworknoncourtrlo,typeofworknoncourtmw,typeofworknoncourtno,expecteddeliverydate){
					
					var assignto = assignto.value;					
					var form1 = document.getElementById("form1");
					
		    		form1.action = "/demov2/JobProcess/addQuery?staffid="+assignto+"&filetype="+filetype.value+"&typeofwork="+typeofwork.value+"&typeofworkcourt="+typeofworkcourt.value+"&typeofworknoncourt="+typeofworknoncourt.value+"&typeofworkcourtcases="+typeofworkcourtcases.value+"&typeofworkcourtdocs="+typeofworkcourtdocs.value+"&typeofworknoncourtabt="+typeofworknoncourtabt.value+"&typeofworknoncourtcd="+typeofworknoncourtcd.value+"&typeofworknoncourtsr="+typeofworknoncourtsr.value+"&typeofworknoncourtdr="+typeofworknoncourtdr.value+"&typeofworknoncourtcs="+typeofworknoncourtcs.value+"&typeofworknoncourturd="+typeofworknoncourturd.value+"&typeofworknoncourtrlo="+typeofworknoncourtrlo.value+"&typeofworknoncourtmw="+typeofworknoncourtmw.value+"&typeofworknoncourtno="+typeofworknoncourtno.value+"&expecteddeliverydate="+expecteddeliverydate.value+"";
		    		form1.method = "POST";
		    		form1.submit();
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
        	});
            
            function addRow() {
                var rowCount = document.getElementById('dataTableTasks').rows.length;    
                
                var col1="<td class='dataTextInActive'><input type='checkbox' class = 'chcktbl' id=items_"+rowCount+" /><input type='hidden' name='itemids' id=items_id_"+rowCount+" value='' /></td>";
                var col2="<td class='dataTextInActive'><label> <select name='assignto' id=assignto_"+rowCount+" style='width: 250px;height: 25px;'><option selected></option><<option value='${employee.tid}:${employee.teachername}:${employee.contactnumber}'><c:out value='${employee.teachername}' /></option></select></label></td>";
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
        
    	
    </head>
      <%
//allow access only if session exists
String user = null;
if(session.getAttribute("userAuth") == null){
	response.sendRedirect("/demov2/UserProcess/sessionTimeOut");
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
                        <td  class="headerTD">Create Task</td>
                    </tr>
                </table>
		           		<table style="margin-left: auto;margin-right: auto;">
								<tr>
									<td><br></td>
								</tr>
								<tr>
									<td class="alignLeft" style="font-weight: bold;font-size: 16px;">Job No.:</td>
									<td style="font-weight: bold;font-size: 16px;color: #eb6000;text-transform: uppercase;"><label> <c:out value="${jobno}" /></label>
									<input type="hidden" id=jobid" name="jobid" value="${jobid}"> 
									</td>
								</tr>		           		
		           				<tr>
									<td><br /></td>
								</tr>	           		
						</table>
						
						<div align="center">
						<p>
						<h2 style="text-decoration: underline;color: #eb6000">Tasks Details</h2>	
						<label><button id="addnewitem">Add Task</button></label><label><button id="removenewitem">Remove Task</button></label></p>
						
									
					</div>
					
					<br>
					<table style="margin-left: auto;margin-right: auto;border: 1px solid black;" id="dataTableTasks">
						<thead>
							<tr>
								<th class="headerText">
								<input type="checkbox" id="chckHeadReceived" />
								<!-- <input type="checkbox"
									id="selectAll" name="selectAll"
									onclick="selectAllRow('dataTable')" /> --> </th>
								<th class="headerText">Assign To</th>
								<th class="headerText">Task</th>
								<th class="headerText">Description</th>
								<th class="headerText">Expected DD</th>
							</tr>
						</thead>

						<tbody>						
						</tbody>
					</table>
						<div align="center">
						<p>
						<label><button id="createtask">Save</button></label></p>
					</div>
					</div>
        </form>
    </body>
</html>
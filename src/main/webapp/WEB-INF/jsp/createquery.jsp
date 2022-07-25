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
            
            function saveQuery(){
                var form1=document.getElementById("form1");
               form1.action="/sla/QueryProcess/addQuery";
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
                
                
                $("#createquery").button({
             		icons:{
                 		primary: "ui-icon-check"
             		}
         				}).click(function(){
             					saveQuery();
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
		        		form1.action = "/sla/AppointmentProcess/addAppointment?appointmentdate="+appointmentDate+"&appointmenttime="+appointmentTime+"";
		        		form1.method = "POST";
		        		form1.submit();
		            }
		
				function writeQuery(assignto,filetype,typeofwork,typeofworkcourt,typeofworknoncourt,typeofworkcourtcases,typeofworkcourtdocs,typeofworknoncourtabt,typeofworknoncourtcd,typeofworknoncourtsr,typeofworknoncourtdr,typeofworknoncourtcs,typeofworknoncourturd,typeofworknoncourtrlo,typeofworknoncourtmw,typeofworknoncourtno,expecteddeliverydate){
					
					var assignto = assignto.value;					
					var form1 = document.getElementById("form1");
					
		    		form1.action = "/sla/QueryProcess/addQuery?staffid="+assignto+"&filetype="+filetype.value+"&typeofwork="+typeofwork.value+"&typeofworkcourt="+typeofworkcourt.value+"&typeofworknoncourt="+typeofworknoncourt.value+"&typeofworkcourtcases="+typeofworkcourtcases.value+"&typeofworkcourtdocs="+typeofworkcourtdocs.value+"&typeofworknoncourtabt="+typeofworknoncourtabt.value+"&typeofworknoncourtcd="+typeofworknoncourtcd.value+"&typeofworknoncourtsr="+typeofworknoncourtsr.value+"&typeofworknoncourtdr="+typeofworknoncourtdr.value+"&typeofworknoncourtcs="+typeofworknoncourtcs.value+"&typeofworknoncourturd="+typeofworknoncourturd.value+"&typeofworknoncourtrlo="+typeofworknoncourtrlo.value+"&typeofworknoncourtmw="+typeofworknoncourtmw.value+"&typeofworknoncourtno="+typeofworknoncourtno.value+"&expecteddeliverydate="+expecteddeliverydate.value+"";
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

    		function choosetypeofwork() {
        			var typeofwork = document.getElementById("typeofwork");
        			var typeofworktext = typeofwork.options[typeofwork.selectedIndex].text;

        			if (typeofworktext == "Court") { 
            				document.getElementById("typeofworknoncourttd").style.display = "none";
            				document.getElementById("typeofworkcourtdocstr").style.display = "none";
            				document.getElementById("typeofworknoncourtabttr").style.display = "none";
            				document.getElementById("typeofworknoncourtcdtr").style.display = "none";
            				document.getElementById("typeofworknoncourtsrtr").style.display = "none";
            				document.getElementById("typeofworknoncourtdrtr").style.display = "none";
            				document.getElementById("typeofworknoncourtcstr").style.display = "none";
            				document.getElementById("typeofworknoncourturdtr").style.display = "none";
            				document.getElementById("typeofworknoncourtrlotr").style.display = "none";
            				document.getElementById("typeofworknoncourtmwtr").style.display = "none";
            				document.getElementById("typeofworkcourtcasestr").style.display = "none";
            				document.getElementById("typeofworknoncourtnotr").style.display = "none";
            				
            				document.getElementById("typeofworkcourttd").style.display = '';
    	    		}else if(typeofworktext == "Non Court") {
        				document.getElementById("typeofworkcourttd").style.display = "none";
        				document.getElementById("typeofworkcourtdocstr").style.display = "none";
        				document.getElementById("typeofworknoncourtabttr").style.display = "none";
        				document.getElementById("typeofworknoncourtcdtr").style.display = "none";
        				document.getElementById("typeofworknoncourtsrtr").style.display = "none";
        				document.getElementById("typeofworknoncourtdrtr").style.display = "none";
        				document.getElementById("typeofworknoncourtcstr").style.display = "none";
        				document.getElementById("typeofworknoncourturdtr").style.display = "none";
        				document.getElementById("typeofworknoncourtrlotr").style.display = "none";
        				document.getElementById("typeofworknoncourtmwtr").style.display = "none";
        				document.getElementById("typeofworkcourtcasestr").style.display = "none";
        				document.getElementById("typeofworknoncourtnotr").style.display = "none";
        				
        				document.getElementById("typeofworknoncourttd").style.display = '';
	    		} 
	    	}
    		
    		function choosecourtwork(){

    			var typeofwork = document.getElementById("typeofworkcourt");
    			var typeofworktext = typeofwork.options[typeofwork.selectedIndex].text;

    			if (typeofworktext == "Cases") { 
        				document.getElementById("typeofworkcourtdocstr").style.display = "none";
        				document.getElementById("typeofworknoncourtabttr").style.display = "none";
        				document.getElementById("typeofworknoncourtcdtr").style.display = "none";
        				document.getElementById("typeofworknoncourtsrtr").style.display = "none";
        				document.getElementById("typeofworknoncourtdrtr").style.display = "none";
        				document.getElementById("typeofworknoncourtcstr").style.display = "none";
        				document.getElementById("typeofworknoncourturdtr").style.display = "none";
        				document.getElementById("typeofworknoncourtrlotr").style.display = "none";
        				document.getElementById("typeofworknoncourtmwtr").style.display = "none";
        				document.getElementById("typeofworknoncourtnotr").style.display = "none";
        				
        				document.getElementById("typeofworkcourtcasestr").style.display = '';
	    		}else if(typeofworktext == "Certified Documents") {
	    			document.getElementById("typeofworkcourtcasestr").style.display = "none";
    				document.getElementById("typeofworknoncourtabttr").style.display = "none";
    				document.getElementById("typeofworknoncourtcdtr").style.display = "none";
    				document.getElementById("typeofworknoncourtsrtr").style.display = "none";
    				document.getElementById("typeofworknoncourtdrtr").style.display = "none";
    				document.getElementById("typeofworknoncourtcstr").style.display = "none";
    				document.getElementById("typeofworknoncourturdtr").style.display = "none";
    				document.getElementById("typeofworknoncourtrlotr").style.display = "none";
    				document.getElementById("typeofworknoncourtmwtr").style.display = "none";
    				document.getElementById("typeofworknoncourtnotr").style.display = "none";
    				
    				document.getElementById("typeofworkcourtdocstr").style.display = '';
    			} 
    	
    		}

    		function selectnoncourtwork(){

    			var typeofwork = document.getElementById("typeofworknoncourt");
    			var typeofworktext = typeofwork.options[typeofwork.selectedIndex].text;

    			if (typeofworktext == "Arbitration") { 
        				document.getElementById("typeofworkcourtdocstr").style.display = "none";
        				document.getElementById("typeofworkcourtdocs").selectedIndex = 0;
        				document.getElementById("typeofworknoncourtabttr").style.display = '';
        				document.getElementById("typeofworknoncourtcdtr").style.display = "none";
        				document.getElementById("typeofworknoncourtcd").selectedIndex = 0;
        				document.getElementById("typeofworknoncourtsrtr").style.display = "none";
        				document.getElementById("typeofworknoncourtsr").selectedIndex = 0;
        				document.getElementById("typeofworknoncourtdrtr").style.display = "none";
        				document.getElementById("typeofworknoncourtdr").selectedIndex = 0;
        				document.getElementById("typeofworknoncourtcstr").style.display = "none";
        				document.getElementById("typeofworknoncourtcs").selectedIndex = 0;
        				document.getElementById("typeofworknoncourturdtr").style.display = "none";
        				document.getElementById("typeofworknoncourturd").selectedIndex = 0;
        				document.getElementById("typeofworknoncourtrlotr").style.display = "none";
        				document.getElementById("typeofworknoncourtrlo").selectedIndex = 0;
        				document.getElementById("typeofworknoncourtmwtr").style.display = "none";
        				document.getElementById("typeofworknoncourtmw").selectedIndex = 0;
        				document.getElementById("typeofworkcourtcasestr").style.display = "none";
        				document.getElementById("typeofworkcourtcases").selectedIndex = 0;
        				document.getElementById("typeofworknoncourtnotr").style.display = "none";
        				document.getElementById("typeofworknoncourtno").selectedIndex = 0;
	    		}else if(typeofworktext == "Certified Documents") {
	    			document.getElementById("typeofworkcourtcasestr").style.display = "none";
	    			document.getElementById("typeofworkcourtcases").selectedIndex = 0;
    				document.getElementById("typeofworknoncourtabttr").style.display = "none";
    				document.getElementById("typeofworknoncourtabt").selectedIndex = 0;
    				document.getElementById("typeofworknoncourtcdtr").style.display = '';
    				document.getElementById("typeofworknoncourtsrtr").style.display = "none";
    				document.getElementById("typeofworknoncourtsr").selectedIndex = 0;
    				document.getElementById("typeofworknoncourtdrtr").style.display = "none";
    				document.getElementById("typeofworknoncourtdr").selectedIndex = 0;
    				document.getElementById("typeofworknoncourtcstr").style.display = "none";
    				document.getElementById("typeofworknoncourtcs").selectedIndex = 0;
    				document.getElementById("typeofworknoncourturdtr").style.display = "none";
    				document.getElementById("typeofworknoncourturd").selectedIndex = 0;
    				document.getElementById("typeofworknoncourtrlotr").style.display = "none";
    				document.getElementById("typeofworknoncourtrlo").selectedIndex = 0;
    				document.getElementById("typeofworknoncourtmwtr").style.display = "none";
    				document.getElementById("typeofworknoncourtmw").selectedIndex = 0;
    				document.getElementById("typeofworkcourtcasestr").style.display = "none";
    				document.getElementById("typeofworkcourtcases").selectedIndex = 0;
    				document.getElementById("typeofworkcourtdocstr").style.display = "none";
    				document.getElementById("typeofworkcourtdocs").selectedIndex = 0;
    				document.getElementById("typeofworknoncourtnotr").style.display = "none";
    				document.getElementById("typeofworknoncourtno").selectedIndex = 0;
    			}else if(typeofworktext == "Sub Registrar") {
	    			document.getElementById("typeofworkcourtcasestr").style.display = "none";
	    			document.getElementById("typeofworkcourtcases").selectedIndex = 0;
    				document.getElementById("typeofworknoncourtabttr").style.display = "none";
    				document.getElementById("typeofworknoncourtabt").selectedIndex = 0;
    				document.getElementById("typeofworknoncourtcdtr").style.display = "none";
    				document.getElementById("typeofworknoncourtcd").selectedIndex = 0;
    				document.getElementById("typeofworknoncourtsrtr").style.display = '';
    				document.getElementById("typeofworknoncourtdrtr").style.display = "none";
    				document.getElementById("typeofworknoncourtdr").selectedIndex = 0;
    				document.getElementById("typeofworknoncourtcstr").style.display = "none";
    				document.getElementById("typeofworknoncourtcs").selectedIndex = 0;
    				document.getElementById("typeofworknoncourturdtr").style.display = "none";
    				document.getElementById("typeofworknoncourturd").selectedIndex = 0;
    				document.getElementById("typeofworknoncourtrlotr").style.display = "none";
    				document.getElementById("typeofworknoncourtrlo").selectedIndex = 0;
    				document.getElementById("typeofworknoncourtmwtr").style.display = "none";
    				document.getElementById("typeofworknoncourtmw").selectedIndex = 0;
    				document.getElementById("typeofworkcourtcasestr").style.display = "none";
    				document.getElementById("typeofworkcourtcases").selectedIndex = 0;
    				document.getElementById("typeofworkcourtdocstr").style.display = "none";
    				document.getElementById("typeofworkcourtdocs").selectedIndex = 0;
    				document.getElementById("typeofworknoncourtnotr").style.display = "none";
    				document.getElementById("typeofworknoncourtno").selectedIndex = 0;
    			} else if(typeofworktext == "District Registrar") {
	    			document.getElementById("typeofworkcourtcasestr").style.display = "none";
	    			document.getElementById("typeofworkcourtcases").selectedIndex = 0;
    				document.getElementById("typeofworknoncourtabttr").style.display = "none";
    				document.getElementById("typeofworknoncourtabt").selectedIndex = 0;
    				document.getElementById("typeofworknoncourtcdtr").style.display = "none";
    				document.getElementById("typeofworknoncourtcd").selectedIndex = 0;
    				document.getElementById("typeofworknoncourtsrtr").style.display = "none";
    				document.getElementById("typeofworknoncourtsr").selectedIndex = 0;
    				document.getElementById("typeofworknoncourtdrtr").style.display = '';
    				document.getElementById("typeofworknoncourtcstr").style.display = "none";
    				document.getElementById("typeofworknoncourtcs").selectedIndex = 0;
    				document.getElementById("typeofworknoncourturdtr").style.display = "none";
    				document.getElementById("typeofworknoncourturd").selectedIndex = 0;
    				document.getElementById("typeofworknoncourtrlotr").style.display = "none";
    				document.getElementById("typeofworknoncourtrlo").selectedIndex = 0;
    				document.getElementById("typeofworknoncourtmwtr").style.display = "none";
    				document.getElementById("typeofworknoncourtmw").selectedIndex = 0;
    				document.getElementById("typeofworkcourtcasestr").style.display = "none";
    				document.getElementById("typeofworkcourtcases").selectedIndex = 0;
    				document.getElementById("typeofworkcourtdocstr").style.display = "none";
    				document.getElementById("typeofworkcourtdocs").selectedIndex = 0;
    				document.getElementById("typeofworknoncourtnotr").style.display = "none";
    				document.getElementById("typeofworknoncourtno").selectedIndex = 0;
    			} else if(typeofworktext == "Co-operative Society") {
	    			document.getElementById("typeofworkcourtcasestr").style.display = "none";
	    			document.getElementById("typeofworkcourtcases").selectedIndex = 0;
    				document.getElementById("typeofworknoncourtabttr").style.display = "none";
    				document.getElementById("typeofworknoncourtabt").selectedIndex = 0;
    				document.getElementById("typeofworknoncourtcdtr").style.display = "none";
    				document.getElementById("typeofworknoncourtcd").selectedIndex = 0;
    				document.getElementById("typeofworknoncourtsrtr").style.display = "none";
    				document.getElementById("typeofworknoncourtsr").selectedIndex = 0;
    				document.getElementById("typeofworknoncourtdrtr").style.display = "none";
    				document.getElementById("typeofworknoncourtdr").selectedIndex = 0;
    				document.getElementById("typeofworknoncourtcstr").style.display = '';
    				document.getElementById("typeofworknoncourturdtr").style.display = "none";
    				document.getElementById("typeofworknoncourturd").selectedIndex = 0;
    				document.getElementById("typeofworknoncourtrlotr").style.display = "none";
    				document.getElementById("typeofworknoncourtrlo").selectedIndex = 0;
    				document.getElementById("typeofworknoncourtmwtr").style.display = "none";
    				document.getElementById("typeofworknoncourtmw").selectedIndex = 0;
    				document.getElementById("typeofworkcourtcasestr").style.display = "none";
    				document.getElementById("typeofworkcourtcases").selectedIndex = 0;
    				document.getElementById("typeofworkcourtdocstr").style.display = "none";
    				document.getElementById("typeofworkcourtdocs").selectedIndex = 0;
    				document.getElementById("typeofworknoncourtnotr").style.display = "none";
    				document.getElementById("typeofworknoncourtno").selectedIndex = 0;
    			} else if(typeofworktext == "Unregistered Documents") {
	    			document.getElementById("typeofworkcourtcasestr").style.display = "none";
	    			document.getElementById("typeofworkcourtcases").selectedIndex = 0;
    				document.getElementById("typeofworknoncourtabttr").style.display = "none";
    				document.getElementById("typeofworknoncourtabt").selectedIndex = 0;
    				document.getElementById("typeofworknoncourtcdtr").style.display = "none";
    				document.getElementById("typeofworknoncourtcd").selectedIndex = 0;
    				document.getElementById("typeofworknoncourtsrtr").style.display = "none";
    				document.getElementById("typeofworknoncourtsr").selectedIndex = 0;
    				document.getElementById("typeofworknoncourtdrtr").style.display = "none";
    				document.getElementById("typeofworknoncourtdr").selectedIndex = 0;
    				document.getElementById("typeofworknoncourtcstr").style.display = "none";
    				document.getElementById("typeofworknoncourtcs").selectedIndex = 0;
    				document.getElementById("typeofworknoncourturdtr").style.display = '';
    				document.getElementById("typeofworknoncourtrlotr").style.display = "none";
    				document.getElementById("typeofworknoncourtrlo").selectedIndex = 0;
    				document.getElementById("typeofworknoncourtmwtr").style.display = "none";
    				document.getElementById("typeofworknoncourtmw").selectedIndex = 0;
    				document.getElementById("typeofworkcourtcasestr").style.display = "none";
    				document.getElementById("typeofworkcourtcases").selectedIndex = 0;
    				document.getElementById("typeofworkcourtdocstr").style.display = "none";
    				document.getElementById("typeofworkcourtdocs").selectedIndex = 0;
    				document.getElementById("typeofworknoncourtnotr").style.display = "none";
    				document.getElementById("typeofworknoncourtno").selectedIndex = 0;
    			} else if(typeofworktext == "Reading & Legal Opinion") {
	    			document.getElementById("typeofworkcourtcasestr").style.display = "none";
	    			document.getElementById("typeofworkcourtcases").selectedIndex = 0;
    				document.getElementById("typeofworknoncourtabttr").style.display = "none";
    				document.getElementById("typeofworknoncourtabt").selectedIndex = 0;
    				document.getElementById("typeofworknoncourtcdtr").style.display = "none";
    				document.getElementById("typeofworknoncourtcd").selectedIndex = 0;
    				document.getElementById("typeofworknoncourtsrtr").style.display = "none";
    				document.getElementById("typeofworknoncourtsr").selectedIndex = 0;
    				document.getElementById("typeofworknoncourtdrtr").style.display = "none";
    				document.getElementById("typeofworknoncourtdr").selectedIndex = 0;
    				document.getElementById("typeofworknoncourtcstr").style.display = "none";
    				document.getElementById("typeofworknoncourtcs").selectedIndex = 0;
    				document.getElementById("typeofworknoncourturdtr").style.display = "none";
    				document.getElementById("typeofworknoncourturd").selectedIndex = 0;
    				document.getElementById("typeofworknoncourtrlotr").style.display = '';
    				document.getElementById("typeofworknoncourtmwtr").style.display = "none";
    				document.getElementById("typeofworknoncourtmw").selectedIndex = 0;
    				document.getElementById("typeofworkcourtcasestr").style.display = "none";
    				document.getElementById("typeofworkcourtcases").selectedIndex = 0;
    				document.getElementById("typeofworkcourtdocstr").style.display = "none";
    				document.getElementById("typeofworkcourtdocs").selectedIndex = 0;
    				document.getElementById("typeofworknoncourtnotr").style.display = "none";
    				document.getElementById("typeofworknoncourtno").selectedIndex = 0;
    			} else if(typeofworktext == "Mutation Work") {
	    			document.getElementById("typeofworkcourtcasestr").style.display = "none";
	    			document.getElementById("typeofworkcourtcases").selectedIndex = 0;
    				document.getElementById("typeofworknoncourtabttr").style.display = "none";
    				document.getElementById("typeofworknoncourtabt").selectedIndex = 0;
    				document.getElementById("typeofworknoncourtcdtr").style.display = "none";
    				document.getElementById("typeofworknoncourtcd").selectedIndex = 0;
    				document.getElementById("typeofworknoncourtsrtr").style.display = "none";
    				document.getElementById("typeofworknoncourtsr").selectedIndex = 0;
    				document.getElementById("typeofworknoncourtdrtr").style.display = "none";
    				document.getElementById("typeofworknoncourtdr").selectedIndex = 0;
    				document.getElementById("typeofworknoncourtcstr").style.display = "none";
    				document.getElementById("typeofworknoncourtcs").selectedIndex = 0;
    				document.getElementById("typeofworknoncourturdtr").style.display = "none";
    				document.getElementById("typeofworknoncourturd").selectedIndex = 0;
    				document.getElementById("typeofworknoncourtrlotr").style.display = "none";
    				document.getElementById("typeofworknoncourtrlo").selectedIndex = 0;
    				document.getElementById("typeofworknoncourtmwtr").style.display = '';
    				document.getElementById("typeofworkcourtcasestr").style.display = "none";
    				document.getElementById("typeofworkcourtcases").selectedIndex = 0;
    				document.getElementById("typeofworkcourtdocstr").style.display = "none";
    				document.getElementById("typeofworkcourtdocs").selectedIndex = 0;
    				document.getElementById("typeofworknoncourtnotr").style.display = "none";
    				document.getElementById("typeofworknoncourtno").selectedIndex = 0;
    			} else if(typeofworktext == "Notice") {
	    			document.getElementById("typeofworkcourtcasestr").style.display = "none";
	    			document.getElementById("typeofworkcourtcases").selectedIndex = 0;
    				document.getElementById("typeofworknoncourtabttr").style.display = "none";
    				document.getElementById("typeofworknoncourtabt").selectedIndex = 0;
    				document.getElementById("typeofworknoncourtcdtr").style.display = "none";
    				document.getElementById("typeofworknoncourtcd").selectedIndex = 0;
    				document.getElementById("typeofworknoncourtsrtr").style.display = "none";
    				document.getElementById("typeofworknoncourtsr").selectedIndex = 0;
    				document.getElementById("typeofworknoncourtdrtr").style.display = "none";
    				document.getElementById("typeofworknoncourtdr").selectedIndex = 0;
    				document.getElementById("typeofworknoncourtcstr").style.display = "none";
    				document.getElementById("typeofworknoncourtcs").selectedIndex = 0;
    				document.getElementById("typeofworknoncourturdtr").style.display = "none";
    				document.getElementById("typeofworknoncourturd").selectedIndex = 0;
    				document.getElementById("typeofworknoncourtrlotr").style.display = "none";
    				document.getElementById("typeofworknoncourtrlo").selectedIndex = 0;
    				document.getElementById("typeofworknoncourtmwtr").style.display = "none";
    				document.getElementById("typeofworknoncourtmw").selectedIndex = 0;
    				document.getElementById("typeofworkcourtcasestr").style.display = "none";
    				document.getElementById("typeofworkcourtcases").selectedIndex = 0;
    				document.getElementById("typeofworkcourtdocstr").style.display = "none";
    				document.getElementById("typeofworkcourtdocs").selectedIndex = 0;
    				document.getElementById("typeofworknoncourtnotr").style.display = '';
    			} 
    	
    		}    		
    		
    	</script>
    	
    	
    	<!-- <script>
    	
				var jobObject = {
				  "Court": {
				    "Cases": ["Supreme Court Case", "High Court Case", "Tables", "Lists"],
				    "Certified Documents": ["Supreme Court Documents", "High Court Documents", "Backgrounds", "Float"],
				    "JavaScript": ["Variables", "Operators", "Functions", "Conditions"]    
				  },
				  "Non-Court": {
				    "Arbitration": ["Arbitration"],
				    "Certified Documents": ["Corporation Documents", "CTS Documents", "Encumberance Certificate"]
				  }
				}
				window.onload = function() {
				  var jobSel = document.getElementById("job");
				  var caseSel = document.getElementById("case");
				  var subcaseSel = document.getElementById("subcase");
				  for (var x in jobObject) {
				    jobSel.options[jobSel.options.length] = new Option(x, x);
				  }
				  jobSel.onchange = function() {
				    //empty Chapters- and Topics- dropdowns
				    subcaseSel.length = 1;
				    caseSel.length = 1;
				    //display correct values
				    for (var y in jobObject[this.value]) {
				      caseSel.options[caseSel.options.length] = new Option(y, y);
				    }
				  }
				  caseSel.onchange = function() {
				    //empty Chapters dropdown
				    subcaseSel.length = 1;
				    //display correct values
				    var z = jobObject[jobSel.value][this.value];
				    for (var i = 0; i < z.length; i++) {
				      subcaseSel.options[subcaseSel.options.length] = new Option(z[i], z[i]);
				    }
				  }
				}
</script>
 -->
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
                        <td  class="headerTD">Create Job</td>
                    </tr>
                </table>
		           		<table style="margin-left: auto;margin-right: auto;">
								<tr>
									<td><input type="hidden" id = "studentid_${parents.student.sid}" name="studentIDs"  value="<c:out value="${parents.pid}:${parents.contactnumber}:${parents.student.name}"/>"/><br /></td>
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
									<td class="alignLeft">Type of Job:</td>
									<td><label> <select name="typeofwork" id="typeofwork" style="width: 250px;height: 25px;" onchange="choosetypeofwork()">
											<option selected></option>
											<option value="Court">Court</option>
											<option value="Non Court">Non Court</option>
										</select>
									</label></td>
								</tr>
								
								<tr>
									<td><br></td>
								</tr>
								
								
								<tr style="display: none;" id="typeofworkcourttd">
									<td class="alignLeft">Type of Court Jobs:</td>
									<td><label> <select name="typeofworkcourt" id="typeofworkcourt" style="width: 250px;height: 25px;" onchange="choosecourtwork()">
											<option selected></option>
											<option value="Cases">Cases</option>
											<option value="Certified Documents">Certified Documents</option>
										</select>
									</label></td>
								</tr>
																
									<tr style="display: none;" id="typeofworknoncourttd">
									<td class="alignLeft">Type of Non-Court Jobs:</td>
									<td><label> <select name="typeofworknoncourt" id="typeofworknoncourt" style="width: 250px;height: 25px;" onchange="selectnoncourtwork()">
											<option selected></option>
											<option value="Arbitration">Arbitration</option>
											<option value="Certified Documents">Certified Documents</option>
											<option value="Co-operative Society">Co-operative Society</option>
											<option value="District Registrar">District Registrar</option>
											<option value="Mutation Work">Mutation Work</option>
											<option value="Notice">Notice</option>
											<option value="Reading & Legal Opinion">Reading & Legal Opinion</option>
											<option value="Sub Registrar">Sub Registrar</option>
											<option value="Unregistered Documents">Unregistered Documents</option>
										</select>
									</label></td>
								</tr>
								
								<tr>
									<td><br></td>
								</tr>
								
								
								<tr style="display: none;" id="typeofworkcourtcasestr">
									<td class="alignLeft">Type of Cases:</td>
									<td><label> <select name="typeofworkcourtcases" id="typeofworkcourtcases" style="width: 250px;height: 25px;">
											<option selected></option>
											<option value="CC">Consumer Court Case</option>
											<option value="CSC">Co-Societies Court Case</option>
											<option value="DC">District Court Case</option>
											<option value="FC">Family Court Case</option>
											<option value="HC">High Court Case</option>
											<option value="KAT">Karantaka Administrative Tribunal Case</option>
											<option value="RC">Revenue Courts Case</option>
											<option value="SC">Supreme Court Case</option>
											<option value="WT">Waqf Tribunal Case</option>
											<option value="AOC">Any Other Court Case</option>
										</select>
									</label></td>
								</tr>
								
								<tr style="display: none;" id="typeofworkcourtdocstr">
									<td class="alignLeft">Type of Cases:</td>
									<td><label> <select name="typeofworkcourtdocs" id="typeofworkcourtdocs" style="width: 250px;height: 25px;">
											<option selected></option>
											<option value="CCD">Consumer Court Documents</option>
											<option value="CSCD">Co-Societies Court Documents</option>
											<option value="DCD">District Court Documents</option>
											<option value="FCD">Family Court Documents</option>
											<option value="HCD">High Court Documents</option>
											<option value="KATD">Karantaka Administrative Tribunal Documents</option>
											<option value="RCD">Revenue Courts Documents</option>
											<option value="SCD">Supreme Court Documents</option>
											<option value="WTD">Waqf Tribunal Documents</option>
											<option value="AOCD">Any Others Court  Documents</option>
										</select>
									</label></td>
								</tr>
								
								<tr style="display: none;" id="typeofworknoncourtabttr">
									<td class="alignLeft">Type of Cases:</td>
									<td><label> <select name="typeofworknoncourtabt" id="typeofworknoncourtabt" style="width: 250px;height: 25px;">
											<option selected></option>
											<option value="ABT">Arbitration</option>
										</select>
									</label></td>
								</tr>
								
								
								<tr style="display: none;" id="typeofworknoncourtcdtr">
									<td class="alignLeft">Type of Cases:</td>
									<td><label> <select name="typeofworknoncourtcd" id="typeofworknoncourtcd" style="width: 250px;height: 25px;">
											<option selected></option>
											<option value="CPD">Corporation Documents</option>
											<option value="CTSD">CTS Documents</option>
											<option value="DRD">District Registrar Documents</option>
											<option value="EC">Encumberance Certificate</option>
											<option value="LSD">Land Survey Documents</option>
											<option value="RED">Revenue Documents</option>
											<option value="SRD">Sub Registrar Documents</option>
										</select>
									</label></td>
								</tr>
								
								<tr style="display: none;" id="typeofworknoncourtsrtr">
									<td class="alignLeft">Type of Cases:</td>
									<td><label> <select name="typeofworknoncourtsr" id="typeofworknoncourtsr" style="width: 250px;height: 25px;">
											<option selected></option>
											<option value="AD">Adoption Deed</option>
											<option value="ASA">Agriculture Sale Agreement</option>
											<option value="ASAGPA">Agriculture Sale Agreement & GPA</option>
											<option value="ASD">Agriculture Sale Deed</option>
											<option value="AT">Amendment of Trust</option>
											<option value="ASSD">Assignment Deed</option>
											<option value="CD">Cancellation Deed</option>
											<option value="COND">Consenting Deed</option>
											<option value="ED">Easementary Deed</option>
											<option value="EXD">Exchange Deed</option>
											<option value="GPA">General Power of Attorney</option>
											<option value="GD">Gift Deed</option>
											<option value="SA">House plot NA land Sale Agreements</option>
											<option value="SD">House plot NA land Sale Deed</option>
											<option value="JDA">Joint Development and GPA</option>
											<option value="LD">Lease Deed</option>
											<option value="MC">Marriage Certificate</option>
											<option value="OD">Other Deeds</option>
											<option value="PTD">Partition Deed</option>
											<option value="PVS">Property Valuation Statement</option>
											<option value="RCD">Reconveyance Deed</option>
											<option value="RFD">Rectification Deed</option>
											<option value="RD">Release Deed</option>
											<option value="STD">Settlement Deed</option>
											<option value="TD">Title Deposit</option>
											<option value="TRD">Transfer Deed</option>
											<option value="TRUST">Trust</option>
											<option value="WN">Waqfnama</option>
											<option value="WILL">Will</option>
										</select>
									</label></td>
								</tr>
								
								
								<tr style="display: none;" id="typeofworknoncourtdrtr">
									<td class="alignLeft">Type of Cases:</td>
									<td><label> <select name="typeofworknoncourtdr" id="typeofworknoncourtdr" style="width: 250px;height: 25px;">
											<option selected></option>
											<option value="DP">Dissolution of Partnership</option>
											<option value="GPAAU">GPA Authentication</option>
											<option value="PD">Partnership Deed</option>
											<option value="RP">Reconstitution of Partnership</option>
										</select>
									</label></td>
								</tr>
								
								<tr style="display: none;" id="typeofworknoncourtcstr">
									<td class="alignLeft">Type of Cases:</td>
									<td><label> <select name="typeofworknoncourtcs" id="typeofworknoncourtcs" style="width: 250px;height: 25px;">
											<option selected></option>
											<option value="CSBL">Co-operative Society Bye Laws</option>
											<option value="MLD">Money Lending Liecense</option>
											<option value="SBL">Society Bye Laws</option>
										</select>
									</label></td>
								</tr>
								
								
								<tr style="display: none;" id="typeofworknoncourturdtr">
									<td class="alignLeft">Type of Cases:</td>
										<td><label> <select name="typeofworknoncourturd" id="typeofworknoncourturd" style="width: 250px;height: 25px;">
													<option selected></option>
													<option value="AF">Affidavit</option>
													<option value="BTA">Business Transfer Agreement</option>
													<option value="CA">Construction Agreement</option>
													<option value="DD">Divorce Deed</option>
													<option value="FA">Franchise Agreement</option>
													<option value="HL">Hand Loan</option>
													<option value="ID">Investment Deed</option>
													<option value="KN">Khulanama</option>
													<option value="MOU">MOU</option>
													<option value="MJDA">MOU for JDA</option>
													<option value="MN">Mubaratnama</option>
													<option value="NASA">Notary Agriculture Sale Agreement</option>
													<option value="NFS">Notary Family Settlement</option>
													<option value="NGD">Notary Gift Deed</option>
													<option value="NSA">Notary House plot NA land Sale Agreement</option>
													<option value="NL">Notary Lease</option>
													<option value="NSD">Notary Sale Deed</option>
													<option value="NW">Notary Will</option>
													<option value="RPT">Receipts</option>
													<option value="RTR">RERA Title Report</option>
													<option value="RES">Resolution</option>
													<option value="SMP">Sale of Moveable Property</option>
													<option value="TN">Talaqnama</option>
													<option value="TR">Title Report</option>
											</select>
									</label></td>
								</tr>
								
								
								<tr style="display: none;" id="typeofworknoncourtrlotr">
									<td class="alignLeft">Type of Cases:</td>
									<td><label> <select name="typeofworknoncourtrlo" id="typeofworknoncourtrlo" style="width: 250px;height: 25px;">
											<option selected></option>
											<option value="ILO">Inheritance Legal Opinion</option>
											<option value="LO">Legal Opinion</option>
											<option value="READ">Reading</option>
										</select>
									</label></td>
								</tr>
								
								<tr style="display: none;" id="typeofworknoncourtmwtr">
									<td class="alignLeft">Type of Cases:</td>
									<td><label> <select name="typeofworknoncourtmw" id="typeofworknoncourtmw" style="width: 250px;height: 25px;">
											<option selected></option>
											<option value="CPM">Corporation Mutation</option>
											<option value="CTSM">CTS Mutation</option>
											<option value="GM">GESCOM Mutation</option>
											<option value="PM">Panchayat Mutation</option>
										</select>
									</label></td>
								</tr>
								
								<tr style="display: none;" id="typeofworknoncourtnotr">
									<td class="alignLeft">Type of Cases:</td>
									<td><label> <select name="typeofworknoncourtno" id="typeofworknoncourtno" style="width: 250px;height: 25px;">
											<option selected></option>
											<option value="PN">Paper Notice</option>
											<option value="LN">Legal Notice</option>
											<option value="NC">Name Change</option>
										</select>
									</label></td>
								</tr>
								
								<tr>
									<td><br></td>
								</tr>
								
									<tr>
										
										<td class="alignLeft">File Type: &nbsp;</td>
	
										<td ><label>
											<select name="filetype" id="filetype" style="width: 250px;height: 25px;">
											<option selected></option>
											<option value="F">F</option>
											<option value="OF">OF</option>
											<option value="CF">CF</option>
											<option value="WA">WA</option>
										</select> 
												<!-- <input type="text" name="filetype" id="filetype"/> -->
										</label></td>
								</tr>
								
								<tr>
									<td><br></td>
								</tr>
								
									<tr>
										
										<td class="alignLeft">Expected Delivery Date: &nbsp;</td>
	
										<td ><label>
											<input type="text"  name="expecteddeliverydate"
									class="textField" style="font-size: 14px;width: 250px;height: 25px;"
									id="expecteddeliverydate" autocomplete="false" required
									data-validate="validate(required)">
										</label></td>
								</tr>
								<tr>
									<td><br></td>
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
						<label><button id="createquery">Save</button></label></p>
					</div>
					</div>
        </form>
    </body>
</html>
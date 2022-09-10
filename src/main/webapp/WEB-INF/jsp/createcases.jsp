<%-- 
    Document   : createcases
    Created on : Aug 17, 2022, 12:15:17 AM
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
        <title>Create Cases</title>
        <style type="text/css" title="currentStyle">
            @import "/sla/css/dataTable/css/demo_page.css";
            @import "/sla/css/dataTable/css/jquery.dataTables.css";
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
            .alert-box {
				padding: 15px;
			    margin-bottom: 20px;
			    border: 1px solid transparent;
			    border-radius: 4px;  
			}
			
			.success {
			    color: #3c763d;
			    background-color: #dff0d8;
			    border-color: #d6e9c6;
			    display: none;
			}
			
			.failure {
			    color: #a94442;
			    background-color: #f2dede;
			    border-color: #ebccd1;
			    display: none;
			}
			
			.button {
			  background-color: #4CAF50; /* Green */
			  border: none;
			  color: white;
			  padding: 8px;
			  text-align: center;
			  text-decoration: none;
			  display: inline-block;
			  font-size: 12px;
			  margin: 4px 2px;
			  cursor: pointer;
			  border-radius: 12px;
			}

.buttonred {
  background-color: red; /* Green */
  border: none;
  color: white;
  padding: 8px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 12px;
  margin: 4px 2px;
  cursor: pointer;
  border-radius: 12px;
} 
        </style>
        <script type="text/javascript">
            var getMember;
            var getVisit;
            function getdata() {

                if (typeof XMLHttpRequest != "undefined") {
                    getMember = new XMLHttpRequest();
                    getVisit = new XMLHttpRequest();
                } else if (window.ActiveXObject) {
                    getMember = new ActiveXObject("Microsoft.XMLHTTP");
                    getVisit = new ActiveXObject("Microsoft.XMLHTTP");
                }

                getMember.onreadystatechange = processMemberData;
                getMember.open("POST", "ContactController",true);
                getMember.send(null);

                getVisit.onreadystatechange = processVisitData;
                getVisit.open("POST", "AppointmentController",true);
                getVisit.send(null);
            }

            function processMemberData() {
                if (getMember.readyState==4)
                {
                    if (getMember.status==200){

                        var count = getMember.responseXML.getElementsByTagName("count")[0];
                        var childCount=count.childNodes[0].nodeValue;
                        var mdiv = document.getElementById("n1");
                        mdiv.innerHTML=childCount;
                        mdiv.style.visibility='visible';
                        setTimeout('getdata();', 60000);


                    }
                }

            }
            function processVisitData() {
                if (getVisit.readyState==4)
                {
                    if (getVisit.status==200){

                        var visitCount = getVisit.responseXML.getElementsByTagName("visitcount")[0];
                        var childVisitCount=visitCount.childNodes[0].nodeValue;
                        var mdiv = document.getElementById("n2");
                        mdiv.innerHTML=childVisitCount;
                        mdiv.style.visibility='visible';
                        setTimeout('getdata();', 60000);


                    }
                }

            }

        </script>
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
            var getMember;
           
            function getlist() {
                
       
                try{
                    var listitem = document.getElementById("advsearch").value;
                    if (typeof XMLHttpRequest != "undefined") {
                        getMember = new XMLHttpRequest();
                   
                    } else if (window.ActiveXObject) {
                        getMember = new ActiveXObject("Microsoft.XMLHTTP");
                    
                    }

                    getMember.onreadystatechange = processMemberData;
                    getMember.open("POST", "detailslist?alphabet="+listitem+"",true);
                    getMember.send(null);
               
                    
                }catch(e){
                    alert(e);
                }
            }

            function processMemberData() {
                if (getMember.readyState==4)
                {
                    if (getMember.status==200){

                        var count = getMember.responseXML.getElementsByTagName("count")[0];
                        var childCount=count.childNodes[0].nodeValue;
                        var mdiv = document.getElementById("n1");
                        mdiv.innerHTML=childCount;
                        mdiv.style.visibility='visible';
                        setTimeout('getdata();', 60000);


                    }
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
            
            function refreshPage(){
                 var form1=document.getElementById("form1");
                form1.action="/sla/PersonalProcess/ViewAllGo";
                form1.submit();
                
                //window.location.reload();
            } 
            function redirect(){
                 var form1=document.getElementById("form1");
                    form1.action="/sla/PersonalProcess/redirect";
                    form1.submit();
                
                //window.location.reload();
            } 
            
            function viewStudentDetails(sid,branchid){
                var form1=document.getElementById("form1");
               form1.action="/sla/StudentProcess/ViewDetails?id="+sid+"&urlbranchid="+branchid+"";
               form1.submit();
               
               //window.location.reload();
           } 
            
            function createCases(sid,branchid){
                var form1=document.getElementById("form1");
               form1.action="/sla/CasesProcess/createNewCases?id="+sid+"&urlbranchid="+branchid+"";
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
					
					var querystatus = '<c:out default="" value="${appointmentresult}"/>';
		            
		            if(querystatus == "true"){
		            	 $(function(){
		            		 $( "div.success" ).fadeIn( 800 ).delay( 2000 );
		            		 $( "div.success" ).fadeOut("slow");
		            	 });
		            	 }else if(querystatus == "false"){
		            	  $(function(){
		            		 $( "div.failure" ).fadeIn( 800 ).delay( 2000 );
		            		 $( "div.success" ).fadeOut("slow");
		            		 });
		            	 }
		            
		        	function closediv(divid){
		        		var x = document.getElementById("div"+divid);
		        		  if (x.style.display === "none") {
		        		    x.style.display = "block";
		        		    return false;
		        		  } else {
		        		    x.style.display = "none";
		        		    var form1 = document.getElementById("form1");
		        			form1.action = "/sla/StudentProcess/viewAllStudentsWithParents";
		        			form1.method = "POST";
		        			form1.submit();
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
            <div style="overflow: hidden">
                <table width="100%">
                    <tr>
                        <td  class="headerTD">View All Clients</td>
                    </tr>

                    

                </table>
                <table   width="100%"  border="0" style="border-color:#4b6a84;"  id="myTable">

                    <thead>
                        <tr>
                            <th class="headerText"><input  type="checkbox" id = "chckHead" /></th>
                            <th title="click to sort" class="headerText">UID</th>
                            <!-- <th title="click to sort" class="headerText">Admission Number</th> -->
                            <th title="click to sort" class="headerText">Name&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                            <th title="click to sort" class="headerText">Contact Number</th>
                            <!-- <th title="click to sort" class="headerText">Father's Name&nbsp;</th>
                            <th title="click to sort" class="headerText">Mother's Name&nbsp;</th> -->
                            <th title="click to sort" class="headerText">Create Cases</th>
                        </tr>
                    </thead>

                    <tbody>
                        <c:forEach items="${studentList}" var="Parents">
											
                            <tr class="trClass" style="border-color:#000000" border="1"  cellpadding="1"  cellspacing="1" >
                            	<td class="dataText" style="text-align: center;"><input type="checkbox" id = "studentid_${Parents.student.sid}" class = "chcktbl"  name="studentIDs"  value="<c:out value="${Parents.pid}:${Parents.contactnumber}:${Parents.student.name}"/>"/></td>
                                <%-- <td class="dataText"><input type="checkbox" id = "<c:out value="${Parents.student.sid}"/>" class = "chcktbl"  name="studentIDs"  value="<c:out value="${Parents.student.sid}"/>"/></td> --%>
                                <td  class="dataTextInActive"><a class="dataTextInActive" style="cursor: pointer;" onclick="viewStudentDetails(${Parents.student.sid},${Parents.student.branchid})"><c:out value="${Parents.student.studentexternalid}"/></a></td>
                                <%-- <td  class="dataTextInActive"><a class="dataTextInActive" href="/sla/StudentProcess/ViewDetails?id=<c:out value='${Parents.student.sid}'/>&urlbranchid=<c:out value='${Parents.student.branchid}'/>"><c:out value="${Parents.student.admissionnumber}"/></a></td> --%>
                                <td class="dataText" style="text-transform:uppercase"><c:out value="${Parents.student.name}"/></td>
                                <%-- <td class="dataText" style="text-transform:uppercase">
                                 <c:forEach var="splt" items="${fn:split(Parents.student.classstudying,'--')}">
						    		${splt} 
								</c:forEach>
                                </td> --%>
                                <td class="dataText" style="text-transform:uppercase"><c:out value="${Parents.contactnumber}"/></td>
                                <%-- <td class="dataText" style="text-transform:uppercase"><c:out value="${Parents.mothersname}"/></td> --%>
                                <!-- <fmt:formatDate value="${Parents.student.admissiondate}" pattern="yyyy-MM-dd"/>  -->
                                <!-- <td class="dataText"><fmt:formatDate value="${Parents.student.admissiondate}" pattern="yyyy-MM-dd"/></td> -->
								<td class="dataText" style='white-space: nowrap'><button id="query_${Parents.student.sid}" class="querybutton" onclick="createCases(${Parents.student.sid},${Parents.student.branchid})">Create Case</button></td>                                 
                            </tr>
                        </c:forEach>
                    </tbody>
                    <tfoot><tr>
                            <td  class="footerTD" colspan="4" ><input value="Archive" type="submit" id="delete"/> </td>
                    
                        </tr></tfoot>
                </table>

            </div>
            
            <div align="center">
             <%--For displaying Previous link except for the 1st page --%>
                <c:if test="${currentPage != 1}">
                    <td><a style="color: #4B6A84;font-size: 12px" href="/sla/StudentProcess/viewAll?page=${currentPage - 1}">Previous</a></td>
                </c:if>

                <%--For displaying Page numbers.
                The when condition does not display a link for the current page--%>
                <table border="0" cellpadding="5" cellspacing="5">
                    <tr>
                        <c:forEach begin="1" end="${noOfPages}" var="i">
                            <c:choose>
                                <c:when test="${currentPage eq i}">
                                    <td style="color: #1D599B;font-weight:bolder;font-size: 20px ">${i}</td>
                                </c:when>
                                <c:otherwise>
                                    <td style="color: black;font-weight:bold;font-size: 15px "><a style="color: #4B6A84" href="/sla/StudentProcess/viewAll?page=${i}">${i}</a></td>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </tr>
                </table>

                <%--For displaying Next link --%>
                <c:if test="${currentPage lt noOfPages}">
                    <td ><a style="color: #4B6A84;font-size: 12px" href="/sla/StudentProcess/viewAll?page=${currentPage + 1}">Next</a></td>
                </c:if>
                    </div>
            
            
					
            
        </form>
    </body>
</html>
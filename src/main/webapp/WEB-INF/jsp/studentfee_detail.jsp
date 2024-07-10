<!DOCTYPE html>
<html lang="en">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=2.0">
    <title>Student Profile</title>
    <link rel="stylesheet" href="/presidency/css/bootstrap.min.css">
        <script src="/presidency/js/jquery.min.js"></script>
        <script src="/presidency/js/bootstrap.min.js"></script>
        <script src="/presidency/js/popper.min.js"></script>
<style>
 .headerText {
		background-image: url("/images/ui-bg_diagonals-small_50_466580_40x40.png");
                border-radius:3px;
                font-family: Tahoma;
                font-size: 18px;
                background-color: #4b6a84;
                color: #FFFFFF;
                font-weight: bold;
                width: auto ;
                height: 22px;
                vertical-align: middle;
                text-align: center;
	     }
.dataText {
                border-radius:3px;
                font-family: Tahoma;
                color: #4b6a84;
                font-size: 18px;
                letter-spacing: normal;
                text-align: center;
                background-color: #E3EFFF;

            }
            
#myTable tr:nth-child(even) td {
  background-color: #e5e5e5; 
  border : 2px solid;
}

#myTable tr:nth-child(odd) td {
  background-color: #ffffff;
  border : 2px solid;
}

#myTable2 tr:nth-child(even) td {
  background-color: #e5e5e5; 
  border : 2px solid;
}

#myTable2 tr:nth-child(odd) td {
  background-color: #ffffff;
  border : 2px solid;
}
</style>
</head>
<body>
   <section style="background-color: #ffff;">
   <br>
 <div class="col-lg-14">       
<!--Fees Structure-->
<h2 align="center">Fees Structure&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;UID: &nbsp;<c:out value="${student.studentexternalid}" /></h2>
        <div class="card mb-4">
          <div class="card-body">
	    <div class="row" >
		<div class="col"style="text-align:center;font-size:big;"> 
	  	<label style="font-size: 25px;color: #ff914d"> Academic Year:${currentAcademicYear}</label>&nbsp;&nbsp;
         <label style="font-size: 25px;color: #0cc0df"> Total fees: Rs. ${totalfees}</label>&nbsp;&nbsp;
		 <label style="font-size: 25px;color: #00BF63">Total fees paid: Rs. ${sumoffees}</label>&nbsp;&nbsp;	
	     <label style="font-size: 25px;color: #ff3131"> Due Amount : Rs. ${dueamount}</label>
              
             </div>
		</div>
           <div class="row"  >
		<div class="col"><table  class="table" id="myTable" style="border:2px solid;">

                    <thead>
                        <tr  >
                            
                            <th title="click to sort" class="headerText">Fees Category</th>
                            <th title="click to sort" class="headerText">Fees Amount&nbsp;</th>
                            <th title="click to sort" class="headerText">Total Installments&nbsp;</th>
                            <th title="click to sort" class="headerText">Total Fees Amount&nbsp;</th>
                            <th title="click to sort" class="headerText">Fees Paid&nbsp;</th>
                            <th title="click to sort" class="headerText">Due Amount&nbsp;</th>
                            <th title="click to sort" class="headerText">Concession&nbsp;</th>
                            <th title="click to sort" class="headerText">Waive Off&nbsp;</th>
                             


                        </tr>
                    </thead>

                    <tbody>
                      <c:forEach items="${feesstructure}" var="feesstructure">  

                            <tr >
                                
                                <td class="dataText"><c:out value="${feesstructure.feescategory.feescategoryname}"/></td>
                                <td class="dataText"><c:out value="${feesstructure.feescategory.amount}"/></td>
                                <td class="dataText"><c:out value="${feesstructure.totalinstallment}"/></td>
                                <td class="dataText"><c:out value="${feesstructure.feesamount}"/></td>
                                <td class="dataText"><c:out value="${feesstructure.feespaid}"/></td>
                                <td class="dataText"><c:out value="${feesstructure.feesamount-feesstructure.feespaid-feesstructure.concession-feesstructure.waiveoff}"/></td>
                                <td class="dataText"><c:out value="${feesstructure.concession}"/></td>
                                <td class="dataText"><c:out value="${feesstructure.waiveoff}"/></td>
                            </tr>
                        
                       </c:forEach>
                        
                    </tbody>
                   
                </table>
             </div>
		</div>
           
            
            
           
            </div>
            </div>
<!--End Fees Structure--> 

<!--fees detail-->
<h2 align="center">Fees Detail</h2>
        <div class="card mb-4">
          <div class="card-body">
            <div class="row" >
		<div class="col"style="text-align:center"> 
	        <label style="font-size: 25px;color: #0cc0df"> Total fees: Rs.${totalfees}</label>&nbsp;&nbsp;
		    <label style="font-size: 25px;color: #00BF63"> Total fees paid: Rs.${sumoffees}</label>&nbsp;&nbsp;	
	  		<label style="font-size: 25px;color: #ff3131"> Due Amount : Rs.${dueamount} </label>
       </div>
		</div>
	    <div class="row"  >
		<div class="col"> 
               <table class="table" id="myTable2">

                    <thead>
                        <tr>
                            <th title="click to sort" class="headerText">Date of fees</th>
                            <th title="click to sort" class="headerText">Reference Number</th>
                            <th title="click to sort" class="headerText">Total Amount</th>
                            <th title="click to sort" class="headerText">View Details</th>
                        </tr>
                    </thead>

                    <tbody>
                        
                     <c:forEach items="${receiptinfo}" var="receiptinfo">
                            <tr>
                                <td class="dataText"><c:out value="${receiptinfo.date}"/></td>
                                <td class="dataText"><c:out value="${receiptinfo.receiptnumber}"/></td>
                                <td class="dataText"><c:out value="${receiptinfo.totalamount}"/></td>
                                <td class="dataText"><a class="dataTextInActive" target="_blank" href="/presidency/FeesCollection/ViewDetails?id=<c:out value='${receiptinfo.receiptnumber}'/>&sid=<c:out value='${student.sid}'/>">View Details</a></td>
                                 

                            </tr>
                        
                         </c:forEach>
                        
                    </tbody>
                   
                </table>
				
                    

              
             </div>
		</div>
           
            </div>
            </div>
      </div>
</section>
</body>
</html>
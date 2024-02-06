<!DOCTYPE html>
<html lang="en">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student Profile</title>
    <!-- Add Bootstrap CSS link -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<style>
 .headerText {
		background-image: url("/images/ui-bg_diagonals-small_50_466580_40x40.png");
                border-radius:3px;
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
</style>
</head>
<body>
   <section style="background-color: #eee;">
  <div class="container py-5">
<!-- navbar start-->
    <div class="row">
      <div class="col">
        <nav aria-label="breadcrumb" class="bg-light rounded-3 p-3 mb-4">
          <ol class="breadcrumb mb-0">
            <li class="breadcrumb-item"><a href="#">Home</a></li>
            <li class="breadcrumb-item"><a href="#">User</a></li>
            <li class="breadcrumb-item active" aria-current="page">User Profile</li>
          </ol>
        </nav>
      </div>
    </div>
<!-- navbar end-->

<!-- profile pic start-->
    <div class="row">
      <div class="col-lg-4">
        <div class="card mb-4">
          <div class="card-body text-center">
             <img class="rounded-circle" src="data:image;base64,<c:out value="${student.studentpic}"/>" alt="Student's Photo" style="width: 200px;height: 200px;">
            <h5 class="my-3"><c:out value="${student.name}" /></h5>
            <p class="text-muted mb-1"><c:forEach var="splt" items="${fn:split(student.classstudying,'--')}">
						    ${splt} 
							</c:forEach></p>
            
            
          </div>
        </div>
<!-- profile pic end-->



<!--End Parent Detail-->
      </div>
       


 <div class="col-lg-8">       
<!--Fees Structure-->
<h6>Fees Structure&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;UID: &nbsp;<c:out value="${student.studentexternalid}" /></h6>
<hr class="mt-0 mb-4">

        <div class="card mb-4">
          <div class="card-body">
	    <div class="row" >
		<div class="col"style="text-align:center;font-size:small;"> 
	  <td> Academic Year:</td> <td> ${currentAcademicYear}</td>&nbsp;&nbsp;&nbsp;
          <td> Total fees: Rs.</td> <td>  ${totalfees}</td>&nbsp;&nbsp;&nbsp;
	  <td> Total fees paid: Rs.</td> <td>  ${sumoffees}</td>&nbsp;&nbsp;&nbsp;	
	  <td> Due Amount : Rs.</td> <td> ${dueamount}</td>
              
             </div>
		</div>
           <div class="row"  >
		<div class="col"><table  class="table" >

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
<h6>Fees Detail</h6>
<hr class="mt-0 mb-4">

        <div class="card mb-4">
          <div class="card-body">
            <div class="row" >
		<div class="col"style="text-align:center"> 
          <td> Total fees: Rs.</td> <td>${totalfees}</td>&nbsp;&nbsp;&nbsp;
	  <td> Total fees paid: Rs.</td> <td> ${sumoffees}</td>&nbsp;&nbsp;&nbsp;	
	  <td> Due Amount : Rs.</td> <td>  ${dueamount} </td>
              
             </div>
		</div>
	    <div class="row"  >
		<div class="col"> 
               <table class="table">

                    <thead>
                        <tr  >
                            
                            <th title="click to sort" class="headerText">Date of fees</th>
                            <th title="click to sort" class="headerText">Reference Number</th>
                            <th title="click to sort" class="headerText">Total Amount</th>
                            <th title="click to sort" class="headerText">View Details</th>
                             


                        </tr>
                    </thead>

                    <tbody>
                        
                     <c:forEach items="${receiptinfo}" var="receiptinfo">
                            <tr  >
                                
                                <td class="dataText"><c:out value="${receiptinfo.date}"/></td>
                                <td class="dataText"><c:out value="${receiptinfo.receiptnumber}"/></td>
                                <td class="dataText"><c:out value="${receiptinfo.totalamount}"/></td>
                                <td class="dataText"><a class="dataTextInActive" target="_blank" href="/demov2/FeesCollection/ViewDetails?id=<c:out value='${receiptinfo.receiptnumber}'/>&sid=<c:out value='${student.sid}'/>">View Details</a></td>
                                 

                            </tr>
                        
                         </c:forEach>
                        
                    </tbody>
                   
                </table>
				
                    

              
             </div>
		</div>
           
            </div>
            </div>
                
          
          
<!--end fee detail-->
	
       
      </div>
    </div>
  </div>
</section>

    <!-- Add Bootstrap JS and Popper.js script -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

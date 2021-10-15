/**
 * 
 */
package com.model.order.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hpsf.Array;

import com.model.branch.dao.BranchDAO;
import com.model.branch.dto.Branch;
import com.model.branch.service.BranchService;
import com.model.order.booksinfo.dao.BooksInfoDAO;
import com.model.order.booksinfo.dto.BooksInfo;
import com.model.order.dao.OrderDAO;
import com.model.order.dto.Books;
import com.model.order.dto.BooksSalesReport;
import com.model.order.dto.Ordersdetails;
import com.model.order.dto.Orderssummary;
import com.model.sendsms.service.SmsService;
import com.util.DataUtil;
import com.util.DateUtil;

/**
 * @author Musaib_2
 *
 */
public class OrderService {

	HttpServletRequest request;
	HttpServletResponse response;
	HttpSession httpSession;
	private String BRANCHID = "branchid";
	
	private static final Logger logger = LogManager.getLogger(OrderService.class);
	
	public OrderService(HttpServletRequest request,
			HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.httpSession = request.getSession();
	}

	public void bookDetails() {
                 List<Books> booksList = new OrderDAO().readBooks();
                 httpSession.setAttribute("bookslist", booksList);
                 List<BooksInfo> booksInfoList = new BooksInfoDAO().getBooksInfo(); 
                 request.setAttribute("booksinfolist", booksInfoList);
    }


    public void addBooks() {
        
        Books book = new Books();
        boolean result = false;
        book.setTitle(DataUtil.camelCase(request.getParameter("title")));
        book.setAuthor(DataUtil.camelCase(request.getParameter("author")));
        book.setLanguage(DataUtil.camelCase(request.getParameter("language")));
        book.setEdition(DataUtil.camelCase(request.getParameter("edition")));
        book.setPrice(Float.parseFloat(request.getParameter("price")));
        book.setQuantity(DataUtil.parseInt(request.getParameter("quantity")));
        book.setDiscount(DataUtil.parseInt(request.getParameter("discount")));
        book.setIsbn("DEFAULT");
        book.setPurchaseddate(DateUtil.datePars(request.getParameter("purchaseddate")));
        book.setCreateddate(new Date());
        if (!book.getTitle().equalsIgnoreCase("")) {
            result = new OrderDAO().addBooks(book);
        }
        if(result) {
            
        }
        request.setAttribute("bookssave", result);
    }


    public void updateBooks() {
        
        String[] booksIds = request.getParameterValues("booksids");
        String[] title = request.getParameterValues("updatetitle");
        String[] language = request.getParameterValues("updatelanguage");
        String[] author = request.getParameterValues("updateauthor");
        String[] quantity = request.getParameterValues("updatequantity");
        String[] price = request.getParameterValues("updateprice");
        String[] discount = request.getParameterValues("updatediscount");
        String[] purchaseddate = request.getParameterValues("updatepurchaseddate");
        String[] createddate = request.getParameterValues("updatecreateddate");
        
        if(booksIds!=null){
            
            List<Books> booksList = new ArrayList<Books>();
            
            for(int i=0; i<booksIds.length;i++) {
                Books book = new Books();
                String[] bookId = booksIds[i].split(":");
                book.setId(Integer.valueOf(bookId[0]));
                book.setTitle(title[Integer.valueOf(bookId[1])]);
                book.setAuthor(author[Integer.valueOf(bookId[1])]);
                book.setQuantity(Integer.parseInt(quantity[Integer.valueOf(bookId[1])]));
                book.setPrice(Float.parseFloat(price[Integer.valueOf(bookId[1])]));
                book.setLanguage(language[Integer.valueOf(bookId[1])]);
                book.setDiscount(Integer.parseInt(discount[Integer.valueOf(bookId[1])]));
                book.setPurchaseddate(DateUtil.datePars(purchaseddate[Integer.valueOf(bookId[1])]));
                book.setCreateddate(DateUtil.datePars(createddate[Integer.valueOf(bookId[1])]));
                book.setIsbn("DEFAULT");
                booksList.add(book);
            }
            boolean result = new OrderDAO().updateMultipleBooks(booksList);
            request.setAttribute("booksupdate", result);
        }
}


    public void deleteMultipleBooks() {
        
        String[] booksIds = request.getParameterValues("booksids");
        if(booksIds!=null){
        
       List<Integer> ids = new ArrayList<Integer>();
       for (String id : booksIds) {
           String[] bookId = id.split(":");
           ids.add(Integer.valueOf(bookId[0]));
       }
       boolean result = new OrderDAO().deleteMultipleBooks(ids);
       request.setAttribute("booksdelete", result);
        }
        
    }

    public void confirmOrder() {
    	
    	String orderDate = DateUtil.dateFromatConversionSlash(request.getParameter("orderdate"));
        Orderssummary orderSummary = new Orderssummary();
        List<Ordersdetails> orderDetailsList = new ArrayList<Ordersdetails>();
        
        if(httpSession.getAttribute(BRANCHID)!=null) {
            String[] booksIds = request.getParameterValues("booksids");
            String[] quantity = request.getParameterValues("quantity");
            String[] price = request.getParameterValues("price");
            Float grandTotal = 0f;
            boolean confirmation = true;
            
            for (String bookId : booksIds) {
                Ordersdetails orderDetails = new Ordersdetails();
                String[] bkId = bookId.split(":");
                orderDetails.setBookid(Integer.parseInt(bkId[0]));
                if(Integer.parseInt(quantity[Integer.parseInt(bkId[1])]) == 0) {
                	confirmation = false;
                }
                orderDetails.setQuantity(Integer.parseInt(quantity[Integer.parseInt(bkId[1])]));
                orderDetails.setPrice(Float.parseFloat(price[Integer.parseInt(bkId[1])]));
                grandTotal = grandTotal + orderDetails.getPrice()*orderDetails.getQuantity();
                orderDetailsList.add(orderDetails);
            }
            
            if(confirmation) {
            
            //String centerCodeLogin = httpSession.getAttribute("logincentercode").toString();
            String centerC = request.getParameter("centercode");
            String[] centerCode = centerC.split(":");
            orderSummary.setCentercode(centerCode[0]);
            orderSummary.setOrderdate(DateUtil.dateParserUpdateStd(orderDate));
            orderSummary.setNarration("PENDING");
            orderSummary.setDiscount(0);
            orderSummary.setTotalafterdiscount(grandTotal);
            
            confirmation = new OrderDAO().confirmOrderSummary(orderSummary,orderDetailsList,httpSession.getAttribute("currentAcademicYear").toString());
            if(confirmation) {
                try {
                    Properties properties = new Properties();
                    InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("Backuplocation.properties");
                    properties.load(inputStream);
                    String orderSendingNumber = properties.getProperty("sendordernumber");
                    new SmsService(request, response).sendSMS(orderSendingNumber, "New order has been placed.");
                } catch (Exception e) {
                    logger.error(e);
                }
            }
            }
            request.setAttribute("ordersave", confirmation);
        }
    
        
        
    }


    public void viewOrder() {
       List<Orderssummary> orderSummary = new OrderDAO().viewOrder();
       Map<Orderssummary,Branch> SumOrd = new LinkedHashMap<Orderssummary,Branch>();
       
       for (Orderssummary orderssummary : orderSummary) {
        Branch branch = new OrderDAO().getBranch(orderssummary.getCentercode());
        SumOrd.put(orderssummary, branch);
    }
       new BranchService(request, response).viewBranches();
       request.setAttribute("ordersummarylist", SumOrd);
        
    }

    public void rejectOrders() {
        String[] orderIds = request.getParameterValues("orderids");
        List<Integer> ids = new ArrayList<Integer>();
        boolean result = false;
        List<ArrayList<Ordersdetails>> ordersList = new ArrayList<ArrayList<Ordersdetails>>();
        		
        if(orderIds!=null) {
            for (String orderid : orderIds) {
                String[] orId = orderid.split(":");
                ids.add(Integer.parseInt(orId[0]));
                ordersList.add((ArrayList<Ordersdetails>) new OrderDAO().getOrderDetails(Integer.parseInt(orId[0])));
            }
            
            
            
           result = new OrderDAO().rejectOrders(ids,ordersList);
        }
       request.setAttribute("rejectorders", result);
    }


    public void deliverOrders() {
        String[] orderIds = request.getParameterValues("orderids");
        String[] paymentStatus = request.getParameterValues("paymentstatus");
        boolean result = false;
        boolean quantityUpdate = false;
        
        if(orderIds!=null) {
            List<Integer> ids = new LinkedList<Integer>();
            List<String> paymentStatusList = new LinkedList<String>();
            List<String> discountList = new LinkedList<String>();
            List<String> totalList = new LinkedList<String>();
            List<Orderssummary> orderSummaryList = new ArrayList<Orderssummary>();
            List<List<Ordersdetails>> listOrderdetails = new ArrayList<List<Ordersdetails>>();
            
            for (String orderid : orderIds) {
                String[] orId = orderid.split(":");
                
                
                /*paymentStatusList.add(DataUtil.emptyString(paymentStatus[Integer.parseInt(orId[1])]));
                discountList.add(DataUtil.emptyString(request.getParameter("discount_"+Integer.parseInt(orId[1]))));
                totalList.add(DataUtil.emptyString(request.getParameter("total_"+Integer.parseInt(orId[1]))));*/
                
                Orderssummary orderSummary = new Orderssummary();
                Float totalPrice = 0f;
                orderSummary.setIdorders(Integer.parseInt(orId[0]));
                orderSummary.setPaymentstatus(DataUtil.emptyString(paymentStatus[Integer.parseInt(orId[1])]));
                orderSummary.setDiscount(Integer.parseInt(DataUtil.emptyString(request.getParameter("discount_"+Integer.parseInt(orId[1])))));
                
                //Float discount = (Float.valueOf(DataUtil.emptyString(request.getParameter("total_"+Integer.parseInt(orId[1])))) * Integer.parseInt(DataUtil.emptyString(request.getParameter("discount_"+Integer.parseInt(orId[1])))) ) / 100;
                //totalPrice = Float.valueOf(DataUtil.emptyString(request.getParameter("total_"+Integer.parseInt(orId[1])))) - discount;
                
                totalPrice = Float.valueOf(DataUtil.emptyString(request.getParameter("total_"+Integer.parseInt(orId[1]))));
                
                orderSummary.setTotalafterdiscount(totalPrice);
                
                if(!"DELIVERED".equalsIgnoreCase(DataUtil.emptyString(request.getParameter("status_"+Integer.parseInt(orId[1]))))) {
                	orderSummaryList.add(orderSummary);
                	ids.add(Integer.parseInt(orId[0]));
                }
                
            }
           
              for (Integer id : ids) {
                  List<Ordersdetails> orderDetailsList = new OrderDAO().getOrderDetails(id);
                   //quantityUpdate = new OrderDAO().updateBooksQuantity(orderDetailsList);
                   listOrderdetails.add(orderDetailsList);
              }
              
                  result = new OrderDAO().deliverOrdersConfirmation(orderSummaryList, listOrderdetails);
              
              /*if(quantityUpdate) {
                  result = new OrderDAO().deliverOrdersConfirmation(orderSummaryList);
              }*/
        }
        
       request.setAttribute("deliverorders", result);
    }


    public void updateOrders() {
        
        String[] orderIds = request.getParameterValues("orderids");
        String[] paymentStatus = request.getParameterValues("paymentstatus");
        boolean result = false;
        
        if(orderIds!=null) {
            List<Integer> ids = new LinkedList<Integer>();
            List<String> paymentStatusList = new LinkedList<String>();
            
            for (String orderid : orderIds) {
                String[] orId = orderid.split(":");
                ids.add(Integer.parseInt(orId[0]));
                paymentStatusList.add(DataUtil.emptyString(paymentStatus[Integer.parseInt(orId[1])]));
            }
           result = new OrderDAO().updateOrders(ids,paymentStatusList);
        }

       request.setAttribute("updateorders", result);
       
    }


    public void searchOrders() {
        
        String SQL = "From Orderssummary";
        String connector = " WHERE ";
        String subQuery = null;
        
        if(!DataUtil.emptyString(request.getParameter("branchid")).equalsIgnoreCase("")) {
            subQuery = "centercode = '"+request.getParameter("branchid")+"'";
        }
        
        if(DateUtil.datePars(request.getParameter("fromdate"))!=null && DateUtil.datePars(request.getParameter("todate"))!=null) {
            if(subQuery==null) {
                subQuery = "orderdate between '"+DataUtil.emptyString(request.getParameter("fromdate"))+"' and '"+DataUtil.emptyString(request.getParameter("todate"))+"'";
            }else {
                subQuery = subQuery + "and orderdate between '"+DataUtil.emptyString(request.getParameter("fromdate"))+"' and '"+DataUtil.emptyString(request.getParameter("todate"))+"'";     
            }
        }
        
        if(!DataUtil.emptyString(request.getParameter("orderstatus")).equalsIgnoreCase("")) {
            if(subQuery==null) {
                subQuery = "narration = '"+request.getParameter("orderstatus")+"'";
            }else {
                subQuery = subQuery + "and narration = '"+request.getParameter("orderstatus")+"'";
            }
        }
        
        if(subQuery!=null) {
            SQL = SQL+connector+subQuery;
        }
        
        List<Orderssummary> orderSummary = new OrderDAO().viewOrder(SQL);
        Map<Orderssummary,Branch> SumOrd = new LinkedHashMap<Orderssummary,Branch>();
        
        for (Orderssummary orderssummary : orderSummary) {
         Branch branch = new BranchDAO().getBranch(Integer.parseInt(orderssummary.getCentercode()));
         SumOrd.put(orderssummary, branch);
        }
        new BranchService(request, response).viewBranches();
        request.setAttribute("ordersummarylist", SumOrd);
        
        }

    public void viewOrderDetails() {
        
        String orderId = request.getParameter("id");
        List<Ordersdetails> orderDetailsList = new OrderDAO().getOrderDetails(Integer.parseInt(orderId));
        Map<Ordersdetails,Books> orderBooksMap = new LinkedHashMap<Ordersdetails,Books>();
        
        for (Ordersdetails ordersdetails : orderDetailsList) {
            Books books = new OrderDAO().getBook(ordersdetails.getBookid());
            orderBooksMap.put(ordersdetails, books);
        }
        httpSession.setAttribute("orderbooksmap", orderBooksMap);
        //httpSession.setAttribute("ordernumber", orderId);
        httpSession.setAttribute("ordernumber", DataUtil.emptyString(request.getParameter("invoicenumber")));
        httpSession.setAttribute("centername", DataUtil.emptyString(request.getParameter("centername")));
        httpSession.setAttribute("orderdate", DataUtil.emptyString(request.getParameter("orderdate")));
        httpSession.setAttribute("discount", DataUtil.emptyString(request.getParameter("discount")));
        httpSession.setAttribute("grandtotalafterdiscount", DataUtil.emptyString(request.getParameter("grandtotal")));
        
    }

    public void viewOrderCenter() {
        Branch branch = new BranchDAO().getBranch(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
        List<Orderssummary> orderSummary = new OrderDAO().viewOrderCenter(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
        Map<Orderssummary,Branch> SumOrd = new LinkedHashMap<Orderssummary,Branch>();
        
        for (Orderssummary orderssummary : orderSummary) {
         SumOrd.put(orderssummary, branch);
     }
        new BranchService(request, response).viewBranchesCenter();
        request.setAttribute("ordersummarylist", SumOrd);
         
     }

    public void searchOrdersCenter() {
        
        String SQL = "From Orderssummary";
        String connector = " WHERE ";
        String subQuery = null;
        
        if(!DataUtil.emptyString(request.getParameter("branchid")).equalsIgnoreCase("")) {
            subQuery = "centercode = '"+request.getParameter("branchid")+"'";
        }
        
        if(DateUtil.datePars(request.getParameter("fromdate"))!=null && DateUtil.datePars(request.getParameter("todate"))!=null) {
            if(subQuery==null) {
                subQuery = "orderdate between '"+DataUtil.emptyString(request.getParameter("fromdate"))+"' and '"+DataUtil.emptyString(request.getParameter("todate"))+"'";
            }else {
                subQuery = subQuery + "and orderdate between '"+DataUtil.emptyString(request.getParameter("fromdate"))+"' and '"+DataUtil.emptyString(request.getParameter("todate"))+"'";     
            }
        }
        
        if(!DataUtil.emptyString(request.getParameter("orderstatus")).equalsIgnoreCase("")) {
            if(subQuery==null) {
                subQuery = "narration = '"+request.getParameter("orderstatus")+"'";
            }else {
                subQuery = subQuery + "and narration = '"+request.getParameter("orderstatus")+"'";
            }
        }
        
        if(subQuery!=null) {
            SQL = SQL+connector+subQuery;
        }
        
        List<Orderssummary> orderSummary = new OrderDAO().viewOrder(SQL);
        Map<Orderssummary,Branch> SumOrd = new LinkedHashMap<Orderssummary,Branch>();
        
        for (Orderssummary orderssummary : orderSummary) {
         Branch branch = new BranchDAO().getBranch(Integer.parseInt(orderssummary.getCentercode()));
         SumOrd.put(orderssummary, branch);
        }
        new BranchService(request, response).viewBranchesCenter();
        request.setAttribute("ordersummarylist", SumOrd);
        
        }

	public void generateBooksPurchaseReport() {
		
		List<Books> booksList = new ArrayList<Books>();
		
		 if(httpSession.getAttribute(BRANCHID)!=null){
			 
			 	
			 	String fromDate = DateUtil.dateFromatConversionSlash(request.getParameter("transactiondatefrom"));
			 	String toDate = DateUtil.dateFromatConversionSlash(request.getParameter("transactiondateto"));
				String title = request.getParameter("title");
				String author = request.getParameter("author");
				String language = request.getParameter("language");
				
				String queryMain = "from Books b ";
				String subQuery = "";
				
				if(!fromDate.isEmpty() && !toDate.isEmpty()) {
					subQuery = "b.purchaseddate between '"+fromDate+"' and '"+toDate+"'";
					httpSession.setAttribute("fromdateselected", "From:&nbsp;"+fromDate);
					httpSession.setAttribute("todateselected", "To:&nbsp;"+toDate);
				}else {
					httpSession.setAttribute("fromdateselected", "");
					httpSession.setAttribute("todateselected", "");
				}
				
				if(!title.isEmpty()) {
					if(subQuery.isEmpty()) {
						subQuery = "b.title = '"+title+"'";
					}else {
						subQuery = subQuery + " and b.title = '"+title+"'";
					}
					httpSession.setAttribute("titleselected", "Title:&nbsp;"+title);
				}else {
					httpSession.setAttribute("titleselected", "");
				}
				
				if(!author.isEmpty()) {
					
					if(subQuery.isEmpty()) {
						subQuery = "b.author = '"+author+"'";
					}else {
						subQuery = subQuery + " and b.author = '"+author+"'";
					}
					httpSession.setAttribute("authorselected", "Author:&nbsp;"+author);
				}else {
					httpSession.setAttribute("authorselected", "");
				}
				
				if(!language.isEmpty()) {
					
					if(subQuery.isEmpty()) {
						subQuery = "b.language = '"+language+"'";
					}else {
						subQuery = subQuery + " and b.language = '"+language+"'";
					}
					httpSession.setAttribute("languageselected", "language:&nbsp;"+language);
				}else {
					httpSession.setAttribute("languageselected", "");
				}
				
				if (!subQuery.isEmpty()) {
					subQuery = " where "+subQuery;
				}
				
				booksList = new OrderDAO().getBooksReport(queryMain+subQuery);
		 }
		 
		 		httpSession.setAttribute("bookslist", booksList);
		 
	}

	public void generateBooksSalesReport() {
		
		List<BooksSalesReport> booksSalesReportList = new ArrayList<BooksSalesReport>();
		
		 if(httpSession.getAttribute(BRANCHID)!=null){
			 
			 	
			 	String fromDate = DateUtil.dateFromatConversionSlash(request.getParameter("transactiondatefrom"));
			 	String toDate = DateUtil.dateFromatConversionSlash(request.getParameter("transactiondateto"));
				String title = request.getParameter("title");
				String author = request.getParameter("author");
				String language = request.getParameter("language");
				String centercode = request.getParameter("centercode");
				String status = request.getParameter("status");
				String[] center = centercode.split("--");
				
				String queryMain = "select os.orderdate,os.centercode,b.title,b.author,b.language,od.quantity,od.price,os.narration" + 
						" from orderssummary os join ordersdetails od on os.idorders = od.orderssummaryid" + 
						" JOIN books b on od.bookid = b.id ";
				String subQuery = "";
				
				if(!fromDate.isEmpty() && !toDate.isEmpty()) {
					subQuery = "os.orderdate between '"+fromDate+"' and '"+toDate+"'";
					httpSession.setAttribute("fromdateselected", "From:&nbsp;"+fromDate);
					httpSession.setAttribute("todateselected", "To:&nbsp;"+toDate);
				}else {
					httpSession.setAttribute("fromdateselected", "");
					httpSession.setAttribute("todateselected", "");
				}
				
				if(!title.isEmpty()) {
					if(subQuery.isEmpty()) {
						subQuery = "b.title = '"+title+"'";
					}else {
						subQuery = subQuery + " and b.title = '"+title+"'";
					}
					httpSession.setAttribute("titleselected", "Title:&nbsp;"+title);
				}else {
					httpSession.setAttribute("titleselected", "");
				}
				
				if(!author.isEmpty()) {
					
					if(subQuery.isEmpty()) {
						subQuery = "b.author = '"+author+"'";
					}else {
						subQuery = subQuery + " and b.author = '"+author+"'";
					}
					httpSession.setAttribute("authorselected", "Author:&nbsp;"+author);
				}else {
					httpSession.setAttribute("authorselected", "");
				}
				
				if(!language.isEmpty()) {
					
					if(subQuery.isEmpty()) {
						subQuery = "b.language = '"+language+"'";
					}else {
						subQuery = subQuery + " and b.language = '"+language+"'";
					}
					httpSession.setAttribute("languageselected", "Language:&nbsp;"+language);
				}else {
					httpSession.setAttribute("languageselected", "");
				}
				
				if(!centercode.isEmpty()) {
					
					if(subQuery.isEmpty()) {
						subQuery = "os.centercode = '"+center[0]+"'";
					}else {
						subQuery = subQuery + " and os.centercode = '"+center[0]+"'";
					}
					httpSession.setAttribute("centercodeselected", "Center:&nbsp;"+center[1]);
				}else {
					httpSession.setAttribute("centercodeselected", "");
				}
				
				if(!status.isEmpty()) {
					
					if(subQuery.isEmpty()) {
						subQuery = "os.narration = '"+status+"'";
					}else {
						subQuery = subQuery + " and os.narration = '"+status+"'";
					}
					httpSession.setAttribute("statusselected", "Status:&nbsp;"+status);
				}else {
					httpSession.setAttribute("statusselected", "");
				}
				
				if (!subQuery.isEmpty()) {
					subQuery = " where "+subQuery;
				}
				
				List<Object[]> rows = new OrderDAO().getBooksSalesReport(queryMain+subQuery);
				
				for(Object[] row : rows){
					BooksSalesReport booksSalesReport = new BooksSalesReport();
					booksSalesReport.setOrderdate(DataUtil.returnValue(row[0]));
					booksSalesReport.setCentercode(DataUtil.returnValue(row[1]));
					booksSalesReport.setTitle(DataUtil.returnValue(row[2]));
					booksSalesReport.setAuthor(DataUtil.returnValue(row[3]));
					booksSalesReport.setLanguage(DataUtil.returnValue(row[4]));
					booksSalesReport.setQuantity(DataUtil.parseInt(DataUtil.returnValue(row[5])));
					booksSalesReport.setPrice(Float.parseFloat(DataUtil.returnValue(row[6])));
					booksSalesReport.setNarration(DataUtil.returnValue(row[7]));
					booksSalesReportList.add(booksSalesReport);
				}
		 }
		 
		 		httpSession.setAttribute("bookslistreport", booksSalesReportList);
		 
	}

	public void generateBooksSalesSummaryReport() {
		
		List<Orderssummary> orderSummaryList = new ArrayList<Orderssummary>();
		
		 if(httpSession.getAttribute(BRANCHID)!=null){
			 
			 	
			 	String fromDate = DateUtil.dateFromatConversionSlash(request.getParameter("transactiondatefrom"));
			 	String toDate = DateUtil.dateFromatConversionSlash(request.getParameter("transactiondateto"));
				String centercode = request.getParameter("centercode");
				String status = request.getParameter("status");
				String[] center = centercode.split("--");
				
				String queryMain = "from Orderssummary os "; 
				String subQuery = "";
				
				if(!fromDate.isEmpty() && !toDate.isEmpty()) {
					subQuery = "os.orderdate between '"+fromDate+"' and '"+toDate+"'";
					httpSession.setAttribute("fromdateselected", "From:&nbsp;"+fromDate);
					httpSession.setAttribute("todateselected", "To:&nbsp;"+toDate);
				}else {
					httpSession.setAttribute("fromdateselected", "");
					httpSession.setAttribute("todateselected", "");
				}
				
				
				
				if(!centercode.isEmpty()) {
					
					if(subQuery.isEmpty()) {
						subQuery = "os.centercode = '"+center[0]+"'";
					}else {
						subQuery = subQuery + " and os.centercode = '"+center[0]+"'";
					}
					httpSession.setAttribute("centercodeselected", "Center:&nbsp;"+center[1]);
				}else {
					httpSession.setAttribute("centercodeselected", "");
				}
				
				if(!status.isEmpty()) {
					
					if(subQuery.isEmpty()) {
						subQuery = "os.narration = '"+status+"'";
					}else {
						subQuery = subQuery + " and os.narration = '"+status+"'";
					}
					httpSession.setAttribute("statusselected", "Status:&nbsp;"+status);
				}else {
					httpSession.setAttribute("statusselected", "");
				}
				
				if (!subQuery.isEmpty()) {
					subQuery = " where "+subQuery;
				}
				
				orderSummaryList = new OrderDAO().getBooksSalesSummaryReport(queryMain+subQuery);
		 }
		 		httpSession.setAttribute("ordersummarylist", orderSummaryList);
	}

	
	public void rejectedOrders() {
	       List<Orderssummary> orderSummary = new OrderDAO().viewRejectedOrder();
	       Map<Orderssummary,Branch> SumOrd = new LinkedHashMap<Orderssummary,Branch>();
	       
	       for (Orderssummary orderssummary : orderSummary) {
	        Branch branch = new OrderDAO().getBranch(orderssummary.getCentercode());
	        SumOrd.put(orderssummary, branch);
	    }
	       new BranchService(request, response).viewBranches();
	       request.setAttribute("ordersummarylist", SumOrd);
	        
	    }
}

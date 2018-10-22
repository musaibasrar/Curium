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

import com.model.branch.dao.BranchDAO;
import com.model.branch.dto.Branch;
import com.model.branch.service.BranchService;
import com.model.order.dao.OrderDAO;
import com.model.order.dto.Books;
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
    }


    public void addBooks() {
        
        Books book = new Books();
        boolean result = false;
        book.setTitle(DataUtil.camelCase(request.getParameter("title")));
        book.setAuthor(DataUtil.camelCase(request.getParameter("author")));
        book.setLanguage(DataUtil.camelCase(request.getParameter("language")));
        book.setEdition(DataUtil.camelCase(request.getParameter("edition")));
        book.setPrice(DataUtil.parseInt(request.getParameter("price")));
        book.setQuantity(DataUtil.parseInt(request.getParameter("quantity")));
        book.setIsbn("DEFAULT");
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
        
        if(booksIds!=null){
            
            List<Books> booksList = new ArrayList<Books>();
            
            for(int i=0; i<booksIds.length;i++) {
                Books book = new Books();
                String[] bookId = booksIds[i].split(":");
                book.setId(Integer.valueOf(bookId[0]));
                book.setTitle(title[Integer.valueOf(bookId[1])]);
                book.setAuthor(author[Integer.valueOf(bookId[1])]);
                book.setQuantity(Integer.parseInt(quantity[Integer.valueOf(bookId[1])]));
                book.setPrice(Integer.parseInt(price[Integer.valueOf(bookId[1])]));
                book.setLanguage(language[Integer.valueOf(bookId[1])]);
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
        Orderssummary orderSummary = new Orderssummary();
        List<Ordersdetails> orderDetailsList = new ArrayList<Ordersdetails>();
        
        if(httpSession.getAttribute(BRANCHID)!=null) {
            String[] booksIds = request.getParameterValues("booksids");
            String[] quantity = request.getParameterValues("quantity");
            orderSummary.setCentercode((httpSession.getAttribute(BRANCHID).toString()));
            orderSummary.setOrderdate(new Date());
            orderSummary.setNarration("PENDING");
            
            for (String bookId : booksIds) {
                Ordersdetails orderDetails = new Ordersdetails();
                String[] bkId = bookId.split(":");
                orderDetails.setBookid(Integer.parseInt(bkId[0]));
                orderDetails.setQuantity(Integer.parseInt(quantity[Integer.parseInt(bkId[1])]));
                orderDetailsList.add(orderDetails);
            }
            
            boolean confirmation = new OrderDAO().confirmOrderSummary(orderSummary,orderDetailsList);
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
            request.setAttribute("ordersave", confirmation);
        }
    
        
        
    }


    public void viewOrder() {
       List<Orderssummary> orderSummary = new OrderDAO().viewOrder();
       Map<Orderssummary,Branch> SumOrd = new LinkedHashMap<Orderssummary,Branch>();
       
       for (Orderssummary orderssummary : orderSummary) {
        Branch branch = new BranchDAO().getBranch(Integer.parseInt(orderssummary.getCentercode()));
        SumOrd.put(orderssummary, branch);
    }
       new BranchService(request, response).viewBranches();
       request.setAttribute("ordersummarylist", SumOrd);
        
    }

    public void rejectOrders() {
        String[] orderIds = request.getParameterValues("orderids");
        List<Integer> ids = new ArrayList<Integer>();
        boolean result = false;
        
        if(orderIds!=null) {
            for (String orderid : orderIds) {
                String[] orId = orderid.split(":");
                ids.add(Integer.parseInt(orId[0]));
            }
           result = new OrderDAO().rejectOrders(ids);
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
            
            for (String orderid : orderIds) {
                String[] orId = orderid.split(":");
                ids.add(Integer.parseInt(orId[0]));
                paymentStatusList.add(DataUtil.emptyString(paymentStatus[Integer.parseInt(orId[1])]));
            }
           
              for (Integer id : ids) {
                  List<Ordersdetails> orderDetailsList = new OrderDAO().getOrderDetails(id);
                   quantityUpdate = new OrderDAO().updateBooksQuantity(orderDetailsList);
              }
              
              if(quantityUpdate) {
                  result = new OrderDAO().deliverOrders(ids,paymentStatusList);
              }
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
        httpSession.setAttribute("ordernumber", orderId);
        httpSession.setAttribute("centername", DataUtil.emptyString(request.getParameter("centername")));
        httpSession.setAttribute("orderdate", DataUtil.emptyString(request.getParameter("orderdate")));
        
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
}

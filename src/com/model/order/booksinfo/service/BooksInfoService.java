package com.model.order.booksinfo.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.account.dao.AccountDAO;
import com.model.account.dto.Accountsubgroupmaster;
import com.model.order.booksinfo.dao.BooksInfoDAO;
import com.model.order.booksinfo.dto.BooksInfo;
import com.util.DataUtil;

public class BooksInfoService {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession httpSession;
	private String BRANCHID = "branchid";
	private String USERID = "userloginid";
	
	public BooksInfoService(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.httpSession = request.getSession();
	}


	public String viewBooksInfoDetails() {
		
		List<BooksInfo> booksInfoList = new ArrayList<BooksInfo>();
		
		 if(httpSession.getAttribute(BRANCHID)!=null){
			 booksInfoList = new BooksInfoDAO().getBooksInfo(); 
		 }
		 
		 request.setAttribute("booksinfolist", booksInfoList);
		 
		return "addbooksinfo.jsp";
	}


	public String addBookInfoDetails() {
		
		BooksInfo booksInfo = new BooksInfo();

		String result = "false";
		 if(httpSession.getAttribute(BRANCHID)!=null){
             
			 booksInfo.setTitle(DataUtil.emptyString(request.getParameter("title")));
			 booksInfo.setAuthor(DataUtil.emptyString(request.getParameter("author")));
			 
			 booksInfo= new BooksInfoDAO().addNewBooksInfo(booksInfo);
			 
			 if(booksInfo.getId()!=null) {
				 request.setAttribute("bookinfosave", true);
				 request.setAttribute("booktitle", booksInfo.getTitle());
				 result = "true";
			 }
		 }
		return result;
	}

	public void deleteMultipleBooksInfo() {
        
        String[] booksInfoIds = request.getParameterValues("booksinfoids");
        if(booksInfoIds!=null){
        
       List<Integer> ids = new ArrayList<Integer>();
       for (String id : booksInfoIds) {
           String[] messId = id.split(":");
           ids.add(Integer.valueOf(messId[0]));
       		}
       boolean result = new BooksInfoDAO().deleteBooksInfo(ids);
       request.setAttribute("itemsdelete", result);
        
        }
	}


	public void getAuthor() throws IOException {
		
		List<BooksInfo> booksInfo = new ArrayList<BooksInfo>();
		
		if(httpSession.getAttribute(BRANCHID)!=null){
			String title = DataUtil.emptyString(request.getParameter("title"));
			booksInfo = new BooksInfoDAO().getAuthor(title);
			PrintWriter out = response.getWriter(); 
			response.setContentType("text/xml");
		        response.setHeader("Cache-Control", "no-cache");
		        try {
		        	
		        		String buffer = "<select name='author' style='width: 225px' id='author' class='textfieldvalues'>";
		        		/*buffer = buffer +  "<option></option>";*/
			        	for(int i =0; i<booksInfo.size();i++){
			        		buffer = buffer +  "<option value="+booksInfo.get(i).getAuthor()+">"+booksInfo.get(i).getAuthor()+"</option>";
			        	}
			        	/*buffer = buffer+"<option value='New Group'>New Group</option></select>";*/
			        	response.getWriter().println(buffer);
		        } catch (Exception e) {
		            out.write("<subgroup>0</subgroup>");
		        } finally {
		            out.flush();
		            out.close();
		        }
		}
		
		
	}
}

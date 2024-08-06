package org.ideoholic.curium.model.library.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.ideoholic.curium.model.academicyear.dao.YearDAO;
import org.ideoholic.curium.model.diary.dao.diaryDAO;
import org.ideoholic.curium.model.employee.dao.EmployeeDAO;
import org.ideoholic.curium.model.employee.dto.Teacher;
import org.ideoholic.curium.model.feescategory.dao.feesCategoryDAO;
import org.ideoholic.curium.model.feescategory.dto.Feescategory;
import org.ideoholic.curium.model.hr.dao.HrDAO;
import org.ideoholic.curium.model.library.dao.LibraryDAO;
import org.ideoholic.curium.model.library.dto.Book;
import org.ideoholic.curium.model.library.dto.BookHistory;
import org.ideoholic.curium.model.library.dto.BookIssue;
import org.ideoholic.curium.model.mess.item.dao.MessItemsDAO;
import org.ideoholic.curium.model.mess.item.dto.MessItems;
import org.ideoholic.curium.model.mess.stockentry.dto.MessStockAvailability;
import org.ideoholic.curium.util.DataUtil;
import org.ideoholic.curium.util.DateUtil;
import org.springframework.web.multipart.MultipartFile;

public class LibraryService {
	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession httpSession;
	private String BRANCHID = "branchid";

	private static final int BUFFER_SIZE = 4096;

	public LibraryService(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.httpSession = request.getSession();
	}

	public void addBook() {

		Book book = new Book();

		if (httpSession.getAttribute(BRANCHID) != null) {

			book.setSubject(request.getParameter("subject"));
			book.setAuthor(request.getParameter("author"));
			book.setPublisher(request.getParameter("publisher"));
			book.setIsbn(request.getParameter("isbn"));
			book.setAvailableQty(Integer.parseInt(request.getParameter("availableQty")));
			book.setIssuedQty(Integer.parseInt(request.getParameter("issuedQty")));
			book.setShelf(request.getParameter("shelfe"));
			book.setBookname(request.getParameter("bookname"));
			book = new LibraryDAO().create(book);
		}
	}

	public void viewBooks() {

		if (httpSession.getAttribute(BRANCHID) != null) {
			try {
				List<Book> list = new LibraryDAO().readListOfBook();
				httpSession.setAttribute("book", list);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public void deleteRecord() {
		String[] idbook = request.getParameterValues("id");
		if (idbook != null) {
			List<Integer> ids = new ArrayList();
			for (String id : idbook) {
				System.out.println("id" + id);
				ids.add(Integer.valueOf(id));
			}
			new LibraryDAO().deleteRecord(ids);
		}
	}

	public boolean viewBooksAvailable() {
		boolean result = false;

		if (httpSession.getAttribute(BRANCHID) != null) {
			try {
				List<Book> list = new LibraryDAO().readListOfBook();

				List<Book> availableList = new ArrayList<Book>();
				List<Book> issuedList = new ArrayList<Book>();
				for (Book book : list) {
					int availableqty = book.getAvailableQty();
					int issuedqty = book.getIssuedQty();

					if (availableqty > issuedqty) {
						availableList.add(book);
					}

					if (issuedqty > 0) {
						issuedList.add(book);
					}
				}
				httpSession.setAttribute("availablebooklist", availableList);

				httpSession.setAttribute("issuedbooklist", issuedList);
				result = true;
			} catch (Exception e) {
				e.printStackTrace();
				result = false;
			}
		}
		return result;

	}

	public boolean updateBook() {
		boolean result = false;
		String uid = request.getParameter("studentexternalid");
		String studentname = request.getParameter("studentname");
		// String date
		// =DateUtil.dateFromatConversionSlash(request.getParameter("transactiondate"));
		String[] bids = request.getParameterValues("bookissueid");
		if (bids != null && uid != null) {
			List<Integer> ids = new ArrayList();
			for (String id : bids) {
				ids.add(Integer.valueOf(id));
				BookHistory bookHistory = new BookHistory();
				// fill book history

				bookHistory.setBookName(request.getParameter("bookname_" + id));
				bookHistory.setStudentName(studentname);
				bookHistory.setUid(uid);
				bookHistory.setIssueDate(DateUtil.indiandateParser(request.getParameter("issuedate")));
				bookHistory = new LibraryDAO().add(bookHistory);

				// fill issue book table
				BookIssue bookIssue = new BookIssue();
				bookIssue.setBookId(Integer.parseInt(id));
				bookIssue.setReturned("No");
				bookIssue.setBookHolder(uid);
				bookIssue.setBookName(request.getParameter("bookname_" + id));
				bookIssue.setStartDate(DateUtil.indiandateParser(request.getParameter("issuedate")));
				bookIssue = new LibraryDAO().add(bookIssue);

			}
			new LibraryDAO().updatebookAfterIssue(ids);
			result = true;
		}
		return result;

	}

	public void searchstudentBook() {
		String sid = request.getParameter("studentexternalid");
		List<BookIssue> list = new LibraryDAO().readListOfBook(sid);
		List<BookIssue> booksList = new ArrayList<BookIssue>();
		for (BookIssue bookIssue : list) {
			int totalDays = 0;
			Date todaysDate = new Date();
			Date issueDate = bookIssue.getStartDate();
			 long difference = todaysDate.getTime() - issueDate.getTime();
		       float daysBetween = (difference / (1000*60*60*24));
		       totalDays= (int) daysBetween;
		       bookIssue.setNoOfDays(totalDays);
		       booksList.add(bookIssue);
		}
		request.setAttribute("bookslist", booksList);
		request.setAttribute("studentNameDetails", request.getParameter("admno"));
		// request.setAttribute("admnoDetails", request.getParameter("admno"));
		request.setAttribute("admnoDetails", request.getParameter("admissionnumber"));
		request.setAttribute("classandsecDetails", request.getParameter("classandsec"));
		request.setAttribute("studentIdDetails", request.getParameter("studentId"));
		request.setAttribute("dateoffeesDetails", request.getParameter("studentexternalid"));

	}

	public void bookReturnByStudent() {
		Book book = new Book();
		// String dates=request.getParameter("transactiondate");
		// String date
		// =DateUtil.dateFromatConversionSlash(request.getParameter("transactiondate"));
		String[] bids = request.getParameterValues("bookissueid");
		String[] bookids = request.getParameterValues("bookid");
		if (bids != null) {
			List<Integer> ids = new ArrayList();
			for (String id : bids) {
				ids.add(Integer.valueOf(id));
			}
			new LibraryDAO().updatebookissueAfterReturn(ids);
		}
		//String[] bookids = request.getParameterValues("bookid");
		if (bookids != null) {
			List<Integer> ids = new ArrayList();
			for (String id : bookids) {
				ids.add(Integer.valueOf(id));
			}
			new LibraryDAO().updatebookAfterReturn(ids);
		}

	}

	public boolean viewBookdetails() {
		boolean result = false;
		int bid = Integer.parseInt(request.getParameter("id"));
		if (httpSession.getAttribute(BRANCHID) != null) {
			try {
				Book list = new LibraryDAO().readDetailsOfBook(bid);
				httpSession.setAttribute("book", list);
				result = true;
			} catch (Exception e) {
				e.printStackTrace();
				result = false;
			}
		}
		return result;

	}

	public void updateBookitems() {
		Book book = new Book();
		int bid = Integer.parseInt(request.getParameter("bid"));
		String bookname = request.getParameter("bookname");
		String subject = request.getParameter("subject");
		String author = request.getParameter("author");
		String publisher = request.getParameter("publisher");
		String isbn = request.getParameter("isbn");
		String status = request.getParameter("status");
		String shelf = request.getParameter("shelf");
		String uid = request.getParameter("studentexternalid");
		// String dates=request.getParameter("transactiondate");
		String date = DateUtil.dateFromatConversionSlash(request.getParameter("transactiondate"));

		new LibraryDAO().updatebookdetail(bid, bookname, subject, author, publisher, isbn, shelf);
	}

	public void getBookHistory() {

		if (httpSession.getAttribute(BRANCHID) != null) {
			try {
				List<BookHistory> list = new LibraryDAO().readListOfBookHistory();
				httpSession.setAttribute("bookhistory", list);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public void deleteBookHistory() {
		String[] idbook = request.getParameterValues("id");
		if (idbook != null) {
			List<Integer> ids = new ArrayList();
			for (String id : idbook) {
				System.out.println("id" + id);
				ids.add(Integer.valueOf(id));
			}
			new LibraryDAO().deleteBookHistoryRecord(ids);
		}
	}

}

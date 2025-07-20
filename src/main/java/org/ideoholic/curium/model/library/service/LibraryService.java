package org.ideoholic.curium.model.library.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.library.dao.LibraryDAO;
import org.ideoholic.curium.model.library.dto.Book;
import org.ideoholic.curium.model.library.dto.BookDto;
import org.ideoholic.curium.model.library.dto.BookHistory;
import org.ideoholic.curium.model.library.dto.BookIssue;
import org.ideoholic.curium.model.library.dto.BooksHistoryRequestDto;
import org.ideoholic.curium.model.library.dto.BooksHistoryResponseDto;
import org.ideoholic.curium.model.library.dto.BooksRequestDto;
import org.ideoholic.curium.model.library.dto.BooksResponseDto;
import org.ideoholic.curium.model.parents.dto.Parents;
import org.ideoholic.curium.model.student.dao.StudentDetailsDAO;
import org.ideoholic.curium.util.DateUtil;

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

	public ResultResponse addBook(BookDto bookDto, String branchId) {

		Book book = new Book();

		if (branchId != null) {
			book.setSubject(bookDto.getSubject());
			book.setAuthor(bookDto.getAuthor());
			book.setPublisher(bookDto.getPublisher());
			book.setIsbn(bookDto.getIsbn());
			book.setAvailableQty(bookDto.getAvailableqty());
			book.setIssuedQty(bookDto.getIssuedqty());
			book.setShelf(bookDto.getShelf());
			book.setBookname(bookDto.getBookname());
			book.setBranchid(Integer.parseInt(branchId));
			book = new LibraryDAO().create(book);
			return ResultResponse.builder().success(true).build();
		}
		return ResultResponse.builder().success(false).build();
	}

	public BooksResponseDto viewBooks(String branchId) {
		BooksResponseDto result = BooksResponseDto.builder().build();

		if (branchId != null) {
			try {
				List<Book> list = new LibraryDAO().readListOfBook(branchId);
				result.setBooksList(list);
				result.setSuccess(true);
			} catch (Exception e) {
				e.printStackTrace();
				result.setSuccess(false);
			}
		}
		return result;
	}
	
	public ResultResponse deleteRecord(BooksRequestDto booksList) {
		String[] idbook = booksList.getBookIds();
		if (idbook != null) {
			List<Integer> ids = new ArrayList<>();
			for (String id : idbook) {
				ids.add(Integer.valueOf(id));
			}
			new LibraryDAO().deleteRecord(ids);
			return ResultResponse.builder().success(true).build();
		}
		return ResultResponse.builder().success(false).build();
	}


	public BooksResponseDto viewBooksAvailable(String branchId) {
		BooksResponseDto result = BooksResponseDto.builder().build();
		
		if (branchId != null) {
			try {
				List<Book> list = new LibraryDAO().readListOfBook(branchId);
				List<Book> availableList = new ArrayList<>();
				List<Book> issuedList = new ArrayList<>();
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
				result.setAvailableList(availableList);
				result.setIssuedList(issuedList);
				result.setSuccess(true);
			} catch (Exception e) {
				e.printStackTrace();
				result.setSuccess(false);
			}
		}
		return result;

	}

	public ResultResponse updateBook(BooksRequestDto booksListDto) {

		String uid = booksListDto.getStudentExternalId();
		// String dates=request.getParameter("transactiondate");
		String[] bids = booksListDto.getBookIds();
		String[] bookNames = booksListDto.getBookName();
		List<BookHistory> bookHistoryList = new ArrayList<BookHistory>();
		List<BookIssue> bookIssueList = new ArrayList<BookIssue>();
		
		if (bids != null) {
			List<Integer> ids = new ArrayList<>();
			int i=0;
			for (String id : bids) {
				ids.add(Integer.valueOf(id));
				BookHistory bookHistory = new BookHistory();
				bookHistory.setBid(id);
				bookHistory.setBookName(bookNames[i]);
				bookHistory.setStudentName(booksListDto.getStudentName());
				bookHistory.setUid(uid);
				bookHistory.setIssueDate(booksListDto.getIssueDate());
				bookHistory.setExpectedReturnDate(booksListDto.getExpectedReturnDate());
				bookHistory.setStudentName(booksListDto.getStudentName());
				bookHistory.setSid(booksListDto.getStudentId());
				bookHistoryList.add(bookHistory);
				
				BookIssue bookIssue = new BookIssue();
				bookIssue.setBookId(Integer.parseInt(id));
				bookIssue.setReturned("No");
				bookIssue.setBookHolder(uid);
				bookIssue.setSid(Integer.parseInt(booksListDto.getStudentId()));
				bookIssue.setStudentName(booksListDto.getStudentName());
				bookIssue.setBookName(bookNames[i]);
				bookIssue.setStartDate(booksListDto.getIssueDate());
				bookIssue.setEndDate(booksListDto.getExpectedReturnDate());
				bookIssueList.add(bookIssue);
				i++;
			}
			//new LibraryDAO().updatebook(uid, ids, date);
			new LibraryDAO().updatebookAfterIssue(ids,bookHistoryList,bookIssueList);
			return ResultResponse.builder().success(true).build();
		}

		return ResultResponse.builder().success(false).build();
	}


	public BooksResponseDto searchstudentBook(BooksRequestDto booksListDto) {
		BooksResponseDto result = BooksResponseDto.builder().build();
		
		String sid = booksListDto.getStudentExternalId();
		List<BookIssue> list = new LibraryDAO().readListOfBooksIssued(sid);
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

		result.setBooksIssuedList(booksList);
		result.setStudentNameDetails(booksListDto.getAdmNo());
		result.setAdmnoDetails(booksListDto.getAdmissionNumber());
		result.setClassAndSecDetails(booksListDto.getClassAndSec());
		result.setStudentIdDetails(booksListDto.getStudentId());
		result.setDateOfFeesDetails(booksListDto.getStudentExternalId());
		return result;
	}

	public ResultResponse bookReturnByStudent(BooksRequestDto booksListDto) {

		String[] bids = booksListDto.getBookIds();
		
		if (bids != null) {
			List<Integer> bookIds = new ArrayList<>();
			List<Integer> bookIssueIds = new ArrayList<>();
			List<Integer> NoOfDays = new ArrayList<>();
			String[] bIssueIds = booksListDto.getBookIssueIds();
			String[] bIds = booksListDto.getBookIds();
			String[] NoDays = booksListDto.getNoOfDays();
			int i=0;
			for (String bookIssueId : bIssueIds) {
				bookIssueIds.add(Integer.parseInt(bookIssueId));
				bookIds.add(Integer.parseInt(bIds[i]));
				NoOfDays.add(Integer.parseInt(NoDays[i]));
				i++;
			}
			
			 new LibraryDAO().updateBookOnReturn(bookIds,bookIssueIds,NoOfDays,booksListDto.getExpectedReturnDate());
			 return ResultResponse.builder().success(true).build();
		}
		return ResultResponse.builder().build();
	}

	public BooksResponseDto viewBookdetails(String bookId, String branchId) {
		BooksResponseDto result = BooksResponseDto.builder().build();
		int bid = Integer.parseInt(bookId);
		if (branchId != null) {
			try {
				Book list = new LibraryDAO().readDetailsOfBook(bid);
				result.setBook(list);
				result.setSuccess(true);
			} catch (Exception e) {
				e.printStackTrace();
				result.setSuccess(false);
			}
		}
		return result;
	}

	public ResultResponse updateBookitems(BookDto bookDto) {
		ResultResponse result = ResultResponse.builder().build();
		try {
			Book book = new Book();

			int bid = Integer.parseInt(bookDto.getBId());
			String bookname = bookDto.getBookname();
			String subject = bookDto.getSubject();
			String author = bookDto.getAuthor();
			String publisher = bookDto.getPublisher();
			String isbn = bookDto.getIsbn();
			int availableQty = bookDto.getAvailableqty();
			int issuedQty = bookDto.getIssuedqty();
			String shelf = bookDto.getShelf();
			String uid = bookDto.getStudentExternalId();
			String date = DateUtil.dateFromatConversionSlash(bookDto.getTransactionDate());

			new LibraryDAO().updatebookdetail(bid, bookname, subject, author, publisher, isbn, shelf, availableQty, issuedQty);
			result.setSuccess(true);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
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

	public BooksHistoryResponseDto getBookHistory(BooksHistoryRequestDto dto) {
		BooksHistoryResponseDto result = BooksHistoryResponseDto.builder().build();
		if (httpSession.getAttribute(BRANCHID) != null) {
			try {
				List<BookHistory> list = new LibraryDAO().readListOfBookHistory(dto.getDateOfIssueFrom(),dto.getDateOfIssueTo());
				result.setBooksHistoryList(list);
				result.setSuccess(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
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

	public ResultResponse getActiveStudentsWithParents(String branchid) {
		
		if (branchid != null) {
		try {
			List<Parents> list = new StudentDetailsDAO()
					.getStudentsList("from Parents as parents where parents.student.archive=0 and parents.student.passedout=0 AND parents.student.droppedout=0 and parents.student.leftout=0 and parents.student.branchid = " + Integer.parseInt(branchid));
			return ResultResponse.builder().success(true).resultList(list).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	return ResultResponse.builder().success(false).build();}

}
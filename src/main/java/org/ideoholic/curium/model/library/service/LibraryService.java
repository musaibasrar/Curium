package org.ideoholic.curium.model.library.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.library.dao.LibraryDAO;
import org.ideoholic.curium.model.library.dto.Book;
import org.ideoholic.curium.model.library.dto.BookDto;
import org.ideoholic.curium.model.library.dto.BooksRequestDto;
import org.ideoholic.curium.model.library.dto.BooksResponseDto;
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
			book.setStatus(bookDto.getStatus());
			book.setBookHolder(bookDto.getBookHolder());
			book.setShelf(bookDto.getShelf());
			book.setBookname(bookDto.getBookname());
			book = new LibraryDAO().create(book);
			return ResultResponse.builder().success(true).build();
		}
		return ResultResponse.builder().success(false).build();
	}


	public BooksResponseDto viewBooks(String branchId) {
		BooksResponseDto result = BooksResponseDto.builder().build();

		if (branchId != null) {
			try {
				List<Book> list = new LibraryDAO().readListOfBook();
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
				System.out.println("id" + id);
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
				List<Book> list = new LibraryDAO().readListOfBook();
				List<Book> availableList = new ArrayList<>();
				List<Book> issuedList = new ArrayList<>();
				for (Book book : list) {
					String status = book.getStatus();
					if ("Available".equalsIgnoreCase(status)) {
						availableList.add(book);
					} else if ("Issued".equalsIgnoreCase(status)) {
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
		String date = DateUtil.dateFromatConversionSlash(booksListDto.getTransactionDate());
		String[] bids = booksListDto.getBookIds();
		if (bids != null) {
			List<Integer> ids = new ArrayList<>();
			for (String id : bids) {
				ids.add(Integer.valueOf(id));
			}
			new LibraryDAO().updatebook(uid, ids, date);
			return ResultResponse.builder().success(true).build();
		}

		return ResultResponse.builder().success(false).build();
	}


	public BooksResponseDto searchstudentBook(BooksRequestDto booksListDto) {
		BooksResponseDto result = BooksResponseDto.builder().build();
		
		String sid = booksListDto.getStudentExternalId();
		List<Book> list = new LibraryDAO().readListOfBook(sid);
		System.out.println(list);

		result.setBooksList(list);
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
			List<Integer> ids = new ArrayList<>();
			for (String id : bids) {
				ids.add(Integer.valueOf(id));
			}
			 new LibraryDAO().updatebook(ids);
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
			String status = bookDto.getStatus();
			String shelf = bookDto.getShelf();
			String uid = bookDto.getStudentExternalId();
			String date = DateUtil.dateFromatConversionSlash(bookDto.getTransactionDate());

			new LibraryDAO().updatebookdetail(bid, bookname, subject, author, publisher, isbn, shelf);
			result.setSuccess(true);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}
        
       
        
	
}


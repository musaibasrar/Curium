package org.ideoholic.curium.model.library.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.library.dto.BookDto;
import org.ideoholic.curium.model.library.dto.BooksHistoryRequestDto;
import org.ideoholic.curium.model.library.dto.BooksHistoryResponseDto;
import org.ideoholic.curium.model.library.dto.BooksRequestDto;
import org.ideoholic.curium.model.library.dto.BooksResponseDto;
import org.ideoholic.curium.model.library.service.LibraryService;
import org.ideoholic.curium.model.parents.dto.Parents;
import org.ideoholic.curium.model.student.dao.studentDetailsDAO;
import org.ideoholic.curium.util.Constants;
import org.ideoholic.curium.util.DataUtil;
import org.ideoholic.curium.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibraryActionAdapter {
	
    @Autowired
    private HttpServletRequest request;
    
	@Autowired
	private HttpServletResponse response;

    @Autowired
    private HttpSession httpSession;

    private String BRANCHID = "branchid";

	public boolean addBook() {
		LibraryService libraryService = new LibraryService(request, response);

		BookDto bookDto = BookDto.builder()
				.subject(request.getParameter("subject"))
				.author(request.getParameter("author"))
				.publisher(request.getParameter("publisher"))
				.isbn(request.getParameter("isbn"))
				.availableqty(Integer.parseInt(request.getParameter("availableQty")))
				.issuedqty(Integer.parseInt(request.getParameter("issuedQty")))
			//	.bookHolder(request.getParameter("bookholder"))
				.shelf(request.getParameter("shelfe"))
				.bookname(request.getParameter("bookname"))
				.build();
		
		return libraryService.addBook(bookDto, httpSession.getAttribute(BRANCHID).toString())
				.isSuccess();		
	}

	public boolean viewBooks() {
		LibraryService libraryService = new LibraryService(request, response);

		BooksResponseDto result = libraryService.viewBooks(httpSession.getAttribute(BRANCHID).toString());
		httpSession.setAttribute("book", result.getBooksList());

		return result.isSuccess();
	}

	public boolean deleteRecord() {
		LibraryService libraryService = new LibraryService(request, response);

		return libraryService.deleteRecord(BooksRequestDto.builder()
				.bookIds(request.getParameterValues("id"))
				.build()).isSuccess();
	}

	public boolean viewBooksAvailable() {
		LibraryService libraryService = new LibraryService(request, response);
		
		BooksResponseDto result = libraryService.viewBooksAvailable(httpSession.getAttribute(BRANCHID).toString());
		
		httpSession.setAttribute("availablebooklist", result.getAvailableList());
		httpSession.setAttribute("issuedbooklist", result.getIssuedList());
		
		return result.isSuccess();
	}

	public boolean updateBook() {
		LibraryService libraryService = new LibraryService(request, response);

		return libraryService.updateBook(
				BooksRequestDto.builder()
				.studentExternalId(request.getParameter("studentexternalid"))
				.issueDate(DateUtil.indiandateParser(request.getParameter("issuedate")))
				.expectedReturnDate(DateUtil.indiandateParser(request.getParameter("expectedreturndate")))
				.bookIds(request.getParameterValues("bookissueid"))
				.bookName(request.getParameterValues("bookname"))
				.studentName(request.getParameter("studentname"))
				.studentId(request.getParameter("studentId"))
				.build()
				).isSuccess();
		
	}

	public boolean searchStudentBook() {
		LibraryService libraryService = new LibraryService(request, response);

		BooksResponseDto response = libraryService.searchstudentBook(
				BooksRequestDto.builder()
				.studentExternalId(request.getParameter("studentexternalid"))
				.build());

		request.setAttribute("bookslist", response.getBooksIssuedList());
		request.setAttribute("studentNameDetails", response.getStudentNameDetails());
		request.setAttribute("admnoDetails", response.getAdmnoDetails());
		request.setAttribute("classandsecDetails", response.getClassAndSecDetails());
		request.setAttribute("studentIdDetails", response.getStudentIdDetails());
		request.setAttribute("dateoffeesDetails", response.getDateOfFeesDetails());

		return response.isSuccess();
	}

	public boolean bookReturnByStudent() {
		LibraryService libraryService = new LibraryService(request, response);
		
		return libraryService.bookReturnByStudent(
				BooksRequestDto.builder()
				.bookIds(request.getParameterValues("bookid"))
				.bookIssueIds(request.getParameterValues("bookissueid"))
				.noOfDays(request.getParameterValues("noofdays"))
				.expectedReturnDate(DateUtil.indiandateParser(request.getParameter("returndate")))
				.build()).isSuccess();
		
	}

	public boolean viewBookdetails() {
		LibraryService libraryService = new LibraryService(request, response);
		
		BooksResponseDto response = libraryService.viewBookdetails(request.getParameter("id"), httpSession.getAttribute(BRANCHID).toString());
		
		httpSession.setAttribute("book", response.getBook());
		
		return response.isSuccess();
	}

	public boolean updateBookitems() {
		LibraryService libraryService = new LibraryService(request, response);
		
		BookDto bookDto = BookDto.builder()
				.bId(request.getParameter("bid"))
				.bookname(request.getParameter("bookname"))
				.subject(request.getParameter("subject"))
				.author(request.getParameter("author"))
				.publisher(request.getParameter("publisher"))
				.isbn(request.getParameter("isbn"))
			    .availableqty(Integer.parseInt(request.getParameter("availableQty")))
				.issuedqty(Integer.parseInt(request.getParameter("issuedQty")))
				.shelf(request.getParameter("shelf"))
				.studentExternalId(request.getParameter("studentexternalid"))
				.transactionDate(request.getParameter("transactiondate"))
				.build();
		
		
		return libraryService.updateBookitems(bookDto).isSuccess();
	}

	public boolean getBookHistory() {
		LibraryService libraryService = new LibraryService(request, response);

		BooksHistoryResponseDto response = libraryService.getBookHistory(
				BooksHistoryRequestDto.builder()
				.dateOfIssueFrom(DateUtil.indiandateParser(request.getParameter("fromdate")))
				.dateOfIssueTo(DateUtil.indiandateParser(request.getParameter("todate")))
				.build());

		request.setAttribute("bookslist", response.getBooksHistoryList());
		return response.isSuccess();
		
	}

	public boolean getActiveStudentsWithParents() {

		LibraryService libraryService = new LibraryService(request, response);
		ResultResponse resultResponse = libraryService.getActiveStudentsWithParents(httpSession.getAttribute(Constants.BRANCHID).toString());
		request.setAttribute("studentListtc", resultResponse.getResultList());
		return resultResponse.isSuccess();
	}
	
}

package org.ideoholic.curium.model.library.action;

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
    
    @Autowired
    private LibraryService libraryService;

	public boolean addBook() {

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
		
		return libraryService.addBook(bookDto, DataUtil.getSessionAttributeOrElseNull(httpSession, Constants.BRANCHID))
				.isSuccess();		
	}

	public boolean viewBooks() {

		BooksResponseDto result = libraryService.viewBooks(DataUtil.getSessionAttributeOrElseNull(httpSession, Constants.BRANCHID));
		httpSession.setAttribute("book", result.getBooksList());

		return result.isSuccess();
	}

	public boolean deleteRecord() {

		return libraryService.deleteRecord(BooksRequestDto.builder()
				.bookIds(request.getParameterValues("id"))
				.build()).isSuccess();
	}

	public boolean viewBooksAvailable() {
		
		BooksResponseDto result = libraryService.viewBooksAvailable(DataUtil.getSessionAttributeOrElseNull(httpSession, Constants.BRANCHID));
		
		httpSession.setAttribute("availablebooklist", result.getAvailableList());
		httpSession.setAttribute("issuedbooklist", result.getIssuedList());
		
		return result.isSuccess();
	}

	public boolean updateBook() {

		return libraryService.updateBook(
				BooksRequestDto.builder()
				.studentExternalId(request.getParameter("studentexternalid"))
				.issueDate(DateUtil.indiandateParser(request.getParameter("issuedate")))
				.expectedReturnDate(DateUtil.dateFromatConversionSlash(request.getParameter("expectedreturndate")))
				.bookIds(request.getParameterValues("bookissueid"))
				.bookName(request.getParameterValues("bookname"))
				.studentName(request.getParameter("studentname"))
				.studentId(request.getParameter("studentId"))
				.build()
				).isSuccess();
		
	}

	public boolean searchStudentBook() {

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
		
		return libraryService.bookReturnByStudent(
				BooksRequestDto.builder()
				.bookIds(request.getParameterValues("bookid"))
				.bookIssueIds(request.getParameterValues("bookissueid"))
				.noOfDays(request.getParameterValues("noofdays"))
				.expectedReturnDate(DateUtil.dateFromatConversionSlash(request.getParameter("returndate")))
				.build()).isSuccess();
		
	}

	public boolean viewBookdetails() {
		
		BooksResponseDto response = libraryService.viewBookdetails(request.getParameter("id"), DataUtil.getSessionAttributeOrElseNull(httpSession, Constants.BRANCHID));
		
		httpSession.setAttribute("book", response.getBook());
		
		return response.isSuccess();
	}

	public boolean updateBookitems() {
		
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

		BooksHistoryResponseDto response = libraryService.getBookHistory(
				BooksHistoryRequestDto.builder()
				.dateOfIssueFrom(DateUtil.dateFromatConversionSlash(request.getParameter("fromdate")))
				.dateOfIssueTo(DateUtil.dateFromatConversionSlash(request.getParameter("todate")))
				.build(), DataUtil.getSessionAttributeOrElseNull(httpSession, Constants.BRANCHID));

		request.setAttribute("bookhistorylist", response.getBooksHistoryList());
		return response.isSuccess();
		
	}

	public boolean getActiveStudentsWithParents() {

		ResultResponse resultResponse = libraryService.getActiveStudentsWithParents(DataUtil.getSessionAttributeOrElseNull(httpSession, Constants.BRANCHID));
		request.setAttribute("studentListtc", resultResponse.getResultList());
		return resultResponse.isSuccess();
	}
	
}

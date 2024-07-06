package org.ideoholic.curium.model.library.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ideoholic.curium.model.diary.service.Diaryservice;
import org.ideoholic.curium.model.documents.service.DocumentService;
import org.ideoholic.curium.model.employee.service.EmployeeService;
import org.ideoholic.curium.model.library.service.LibraryService;
import org.ideoholic.curium.model.mess.item.service.MessItemsService;
import org.ideoholic.curium.model.std.service.StandardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
@RequestMapping("/LibraryProcess")
public class LibraryAction {
	@Autowired
	HttpServletRequest request;
	@Autowired
	HttpServletResponse response;
	
	public String error ="error";

	
	@GetMapping("/addbooks")
	public String addBooks() {
		return "addbook";
	}
	
	@PostMapping("/saveBook")
	public String saveBook() {
		new LibraryService(request, response).addBook();
		return "bookSave";

	}
	
	@RequestMapping(value = "/viewbooks", method = { RequestMethod.GET, RequestMethod.POST })
	public String viewbooks() {
		new LibraryService(request, response).viewBooks();
		return "Viewbook";

	}
	
	@PostMapping("/deleteRecord")
	public String deleteRecord() {
		new LibraryService(request, response).deleteRecord();
		new LibraryService(request, response).viewBooks();
		return "Viewbook";
	}
	
	@RequestMapping(value = "/issuebooks", method = { RequestMethod.GET, RequestMethod.POST })
	public String issuebooks() {
		if(new DocumentService(request, response).transferCertificate()){
			new LibraryService(request, response).viewBooksAvailable();
		return "issuebook";
		}
		 return error;
	}
	
	@PostMapping("/bookIssuedStudent")
	public String bookIssuedStudent() {
		new LibraryService(request, response).updateBook();
		return "bookIssued";

	}
	
	@GetMapping("/returnbooks")
	public String bookReturnStudent() {
		new DocumentService(request, response).transferCertificate();
		return "bookReturn";

	}
	
	@PostMapping("/searchbooks")
	public String searchbooks() {
		new LibraryService(request, response).searchstudentBook();
		return "bookReturn";

	}
	
	@PostMapping("/bookReturnByStudent")
	public String bookReturnByStudent() {
		new LibraryService(request, response).bookReturnByStudent();
		return "bookReturnedSuccessfully";

	}
	
	@GetMapping("/bookdetail")
	public String bookdetail() {
		new LibraryService(request, response).viewBookdetails();
		return "book_details";
	}
	
	@PostMapping("/updateBookDetails")
	public String updateEmployeeDetails() {
		if (new LibraryService(request, response).viewBookdetails()) {
			return "book_update";
		} else {
			return "viewAll";
		}
	}
	
	@PostMapping("/updateBook")
	public String updateBook() {

		new LibraryService(request, response).updateBookitems();
		return viewbooks();
	}

}

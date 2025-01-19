package org.ideoholic.curium.model.library.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/LibraryProcess")
public class LibraryAction {

	@Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private LibraryActionAdapter libraryActionAdapter;

    
    public String error ="error";


    @GetMapping("/addbooks")
    public String addBooks() {
        return "addbook";
    }

    @PostMapping("/saveBook")
    public String saveBook() {
        libraryActionAdapter.addBook();
        return "bookSave";

    }

    @RequestMapping(value = "/viewbooks", method = { RequestMethod.GET, RequestMethod.POST })
    public String viewbooks() {
        libraryActionAdapter.viewBooks();
        return "Viewbook";

    }

    @PostMapping("/deleteRecord")
    public String deleteRecord() {
        libraryActionAdapter.deleteRecord();
        libraryActionAdapter.viewBooks();
        return "Viewbook";
    }

    @RequestMapping(value = "/issuebooks", method = { RequestMethod.GET, RequestMethod.POST })
    public String issuebooks() {
        if (libraryActionAdapter.getActiveStudentsWithParents()) {
            libraryActionAdapter.viewBooksAvailable();
            return "issuebook";
        }
        return error;
    }

    @PostMapping("/bookIssuedStudent")
    public String bookIssuedStudent() {
        libraryActionAdapter.updateBook();
        return "bookIssued";

    }

    @GetMapping("/returnbooks")
    public String bookReturnStudent() {
    	libraryActionAdapter.getActiveStudentsWithParents();
        return "bookReturn";

    }

    @PostMapping("/searchbooks")
    public String searchbooks() {
        libraryActionAdapter.searchStudentBook();
        return "bookReturn";

    }

    @PostMapping("/bookReturnByStudent")
    public String bookReturnByStudent() {
        libraryActionAdapter.bookReturnByStudent();
        return "bookReturnedSuccessfully";

    }

    @GetMapping("/bookdetail")
    public String bookdetail() {
        libraryActionAdapter.viewBookdetails();
        return "book_details";
    }

    @PostMapping("/updateBookDetails")
    public String updateEmployeeDetails() {
        if (libraryActionAdapter.viewBookdetails()) {
            return "book_update";
        } else {
            return "viewAll";
        }
    }

    @PostMapping("/updateBook")
    public String updateBook() {

        libraryActionAdapter.updateBookitems();
        return viewbooks();
    }
    
    @PostMapping("/searchBookHistory")
	public String bookHistory() {
    	libraryActionAdapter.getBookHistory();
		return "bookhistory";
	}
	
	@PostMapping("/deleteBookHistory")
	public String deleteBookHistory() {
		//libraryActionAdapter.deleteBookHistory();
		return "bookhistory";
	}
}

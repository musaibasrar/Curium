package org.ideoholic.curium.model.studentdiary.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.diary.service.Diaryservice;
import org.ideoholic.curium.model.student.service.StudentService;
import org.ideoholic.curium.model.studentdiary.service.StudentDiaryservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/StudentDiaryProcess")
public class StudentDiaryAction {
	@Autowired
	HttpServletRequest request;
	@Autowired
	HttpServletResponse response;
	@Autowired
	HttpSession httpSession;
	
	@GetMapping("/getdiarystudent")
	public String getdiarystudent() {
		new StudentService(request, response).viewAllStudentsParents();
		return "studentdiary";
	}
	
	@PostMapping("/addDiary")
	public String addDiary() {
		new StudentDiaryservice(request, response).addDiary();
		return "studentdiarysaved";

	}
	
	
	@RequestMapping(value = "/viewdiarystudent", method = { RequestMethod.GET, RequestMethod.POST })
	public String viewdiarystudent() {
		new StudentDiaryservice(request, response).viewDiary();
		return "viewdiarystudent";
	}
	
	@RequestMapping(value = "/viewDiaryStudentParent", method = { RequestMethod.GET, RequestMethod.POST })
	public String viewDiaryStudentParent() {
		new StudentDiaryservice(request, response).viewDiaryParent();
		return "viewdiarystudent";
	}
	@PostMapping("/deleteRecord")
	public String deleteRecord() {
		new StudentDiaryservice(request, response).deleteRecord();
		new StudentDiaryservice(request, response).viewDiary();
		return "viewdiarystudent";
	}
	@PostMapping("/diarySaved")
	public String diarySaved() {
		return "viewdiarystudent";
	}
	@PostMapping("/ViewDiaryDetails")
	public String ViewDiaryDetails() {
		if(new Diaryservice(request, response).viewDetailsOfDiaryMessage()) { 

		}
		return "viewDiaryMessage";
		}
	@PostMapping("/ViewDiaryDetailsParent")
	public String ViewDiaryDetailsParent() {
		if(new StudentDiaryservice(request, response).viewDetailsOfDiaryMessage()) { 

		}
		return "viewdiarymessagestudent";
		}
}

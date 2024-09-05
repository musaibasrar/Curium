package org.ideoholic.curium.model.studentdiary.action;

import org.ideoholic.curium.model.diary.action.DiaryActionAdapter;
import org.ideoholic.curium.model.std.action.StandardActionAdapter;
import org.ideoholic.curium.model.student.action.StudentActionAdapter;
import org.ideoholic.curium.model.studentdiary.service.StudentDiaryservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/StudentDiaryProcess")
public class StudentDiaryAction {
	@Autowired
	HttpServletRequest request;
	@Autowired
	HttpServletResponse response;
	@Autowired
	HttpSession httpSession;
	@Autowired
	private DiaryActionAdapter diaryActionAdapter;
	@Autowired
	private StandardActionAdapter standardActionAdapter;
	@Autowired
	private StudentActionAdapter studentActionAdapter;
	
	@GetMapping("/getdiarystudent")
	public String getdiarystudent() {
		studentActionAdapter.viewAllStudentsParents();
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
		return "studentviewdiary";
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
		return "studentviewdiary";
	}
	@PostMapping("/diarySaved")
	public String diarySaved() {
		return "viewdiarystudent";
	}
	@PostMapping("/ViewDiaryDetails")
	public String ViewDiaryDetails() {
		if(diaryActionAdapter.viewDetailsOfDiaryMessage()) {

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

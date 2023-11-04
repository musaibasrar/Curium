package org.ideoholic.curium.model.diary.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.diary.service.Diaryservice;
import org.ideoholic.curium.model.feescategory.service.FeesService;
import org.ideoholic.curium.model.std.service.StandardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/DiaryProcess")
public class DiaryAction {
	@Autowired
	HttpServletRequest request;
	@Autowired
	HttpServletResponse response;
	@Autowired
	HttpSession httpSession;
	
	@GetMapping("/getdiarystudent")
	public String getdiarystudent() {
		new StandardService(request, response).viewClasses();
		return "diary";
	}
	
	@PostMapping("/addDiary")
	public void addDiary() {
		new Diaryservice(request, response).addDiary();
		
	}
	
	@GetMapping("/viewdiarystudent")
	public String viewdiarystudent() {
		new Diaryservice(request, response).viewDiary();
		return "viewDiary";
	}
	@GetMapping("/viewDiaryStudentParent")
	public String viewDiaryStudentParent() {
		new Diaryservice(request, response).viewDiaryParent();
		return "viewDiaryParent";
	}
	@PostMapping("/deleteRecord")
	public String deleteRecord() {
		new Diaryservice(request, response).deleteRecord();
		return "viewDiary";
	}
}

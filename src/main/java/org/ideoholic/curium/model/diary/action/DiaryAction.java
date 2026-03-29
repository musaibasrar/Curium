package org.ideoholic.curium.model.diary.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.diary.service.DiaryService;
import org.ideoholic.curium.model.std.action.StandardActionAdapter;
import org.ideoholic.curium.model.std.service.StandardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/DiaryProcess")
public class DiaryAction {
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private HttpServletResponse response;
	@Autowired
	private HttpSession httpSession;
	@Autowired
	private DiaryActionAdapter diaryActionAdapter;
	@Autowired
	private StandardActionAdapter standardActionAdapter;
	
	@GetMapping("/getdiarystudent")
	public String getdiarystudent() {
		standardActionAdapter.viewClasses();
		return "diary";
	}
	
	@RequestMapping(value = "/addDiary", method = RequestMethod.POST, consumes = "multipart/form-data")
	public String addDiary(@RequestParam("fileToUpload") MultipartFile[] uploadedFiles) {
		diaryActionAdapter.addDiary(uploadedFiles);
		return "diarySaved";

	}
	
	
	@RequestMapping(value = "/viewdiarystudent", method = { RequestMethod.GET, RequestMethod.POST })
	public String viewdiarystudent() {
		diaryActionAdapter.viewDiary();
		return "viewDiary";
	}
	
	
	//@GetMapping("/viewDiaryStudentParent")
	@RequestMapping(value = "/viewDiaryStudentParent", method = { RequestMethod.GET, RequestMethod.POST })
	public String viewDiaryStudentParent() {
		diaryActionAdapter.viewDiaryParent();
		return "viewDiaryParent";
	}
	@PostMapping("/deleteRecord")
	public String deleteRecord() {
		diaryActionAdapter.deleteRecord();
		diaryActionAdapter.viewDiary();
		return "viewDiary";
	}
	@PostMapping("/diarySaved")
	public String diarySaved() {
		return "viewDiary";
	}
	@PostMapping("/ViewDiaryDetails")
	public String ViewDiaryDetails() {
		if(diaryActionAdapter.viewDetailsOfDiaryMessage()) {

		}
		return "viewDiaryMessage";
		}
	@PostMapping("/ViewDiaryDetailsParent")
	public String ViewDiaryDetailsParent() {
		if(diaryActionAdapter.viewDetailsOfDiaryMessage()) {

		}
		return "viewDiaryMessageParent";
		}
}

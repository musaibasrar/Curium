/**
 * 
 */
package org.ideoholic.curium.model.student.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.stampfees.service.StampFeesService;
import org.ideoholic.curium.model.std.service.StandardService;
import org.ideoholic.curium.model.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * @author Musaib_2
 * 
 */
@Controller
@RequestMapping("/StudentProcess")
public class StudentAction {
	@Autowired
	HttpServletRequest request;
	@Autowired
	HttpServletResponse response;
	@Autowired
	HttpSession httpSession;

	@PostMapping("/multiClassSearch")
	public String multiClassSearch() {
		new StampFeesService(request, response).multiClassSearch();
		return "studentsdetailsreports";
	}

	@GetMapping("/advanceSearchStudents")
	public String advanceSearchStudents() {
		new StandardService(request, response).viewClasses();
		return "AdvanceSearch";
	}

	@RequestMapping(value = "/viewAllSuperAdmin", method = { RequestMethod.GET, RequestMethod.POST })
	public String viewAllSuperAdmin() {
		new StudentService(request, response).viewAllStudentsSuperAdmin();
		return "viewAllWithParents";
	}

	@GetMapping("/addNew")
	public String addNew() {
		new StandardService(request, response).viewClasses();
		return new StudentService(request, response).addNew();
	}

	@PostMapping("/download")
	public String downlaodFile() {
		if (new StudentService(request, response).downlaodFile()) {
			return "exportsuccess";
		}
		return "exportfailure";
	}

	@PostMapping("/GenerateBonafide")
	public String generateBonafide() {
		String result = new StudentService(request, response).generateBonafide();
		if (result != null) {
			return result;
		} else {
			return "bonafidefailure";
		}
	}

	@PostMapping("/searchStudentsForBonafide")
	public String searchStudentsForBonafide() {
		new StampFeesService(request, response).advanceSearch();
		return "studentsdetailsbonafide";
	}

	@GetMapping("/searchForStudents")
	public String searchForStudents() {
		new StampFeesService(request, response).advanceSearch();
		return "studentsdetailsreports";
	}

	@PostMapping("/feesStructurePerYear")
	public String feesStructurePerYear() {
		new StudentService(request, response).viewfeesStructurePerYear();
		return "student_details_feesstructure";
	}

	@GetMapping("/ViewFeesStructure")
	public String ViewFeesStructure() {
		if (new StudentService(request, response).viewDetailsOfStudent()) {
			if (httpSession.getAttribute("userType").toString().equalsIgnoreCase("superadmin")) {
				return "student_details_feesstructure_admin";
			} else if (httpSession.getAttribute("userType").toString().equalsIgnoreCase("admin")) {
				return "student_details_feesstructure";
			} else if (!httpSession.getAttribute("userType").toString().equalsIgnoreCase("admin")) {
				return "student_details_feesstructure";
			} else {
				return "student_details_feesstructure";
			}
		} else {
			return "viewAll";
		}
	}

	@GetMapping("/viewAllStudentsWithParents")
	public String viewAllStudentsWithParents() {
		new StudentService(request, response).viewAllStudentsParents();
		return "viewAllWithParents";
	}

	@GetMapping("/viewAllStudents")
	public String viewAllStudents() {
		new StudentService(request, response).viewAllStudentsParents();
		return "viewAllWithParents";
	}

	@PostMapping("/promoteClass")
	public String promoteClass() {
		if (new StudentService(request, response).promoteMultiple()) {
			return "successpromote";
		}
		return "failurepromote";
	}

	@PostMapping("/restoreMultiple")
	public String restoreMultiple() {
		new StudentService(request, response).restoreMultiple();
		return viewAll();
	}

	@PostMapping("/deleteMultiple")
	public String deleteMultiple() {
		new StudentService(request, response).deleteMultiple();
		return archiveViewAll();
	}

	@GetMapping("/archiveViewAll")
	public String archiveViewAll() {
		new StudentService(request, response).viewAllStudentsArchive();
		System.out.println("IN action's view all Archive");
		return "ArchiveviewAll";
	}

	@PostMapping("/archiveMultiple")
	public String archiveMultiple() {
		new StudentService(request, response).archiveMultiple();
		return viewAll();
	}

	@RequestMapping(value = "/updateStudent", method = RequestMethod.POST, consumes = "multipart/form-data")
	public String updateStudent(@RequestParam("fileToUpload") MultipartFile[] uploadedFiles) {
		String idbranchid = new StudentService(request, response).updateStudent(uploadedFiles);
		String id[] = idbranchid.split("_");
		return viewStudent(id[0], id[1]);
	}

	@PostMapping("/updateStudentDetails")
	public String updateStudentDetails(HttpServletRequest request, HttpServletResponse response) {
		if (new StudentService(request, response).viewDetailsOfStudent()) {
			String urlBranchId = request.getParameter("urlbranchid");
			if ("1".equalsIgnoreCase(urlBranchId) || "2".equalsIgnoreCase(urlBranchId)
					|| "3".equalsIgnoreCase(urlBranchId)) {
				return "student_update";
			} else if (request.getParameter("urlbranchid").equalsIgnoreCase("4")) {
				return "student_update";
			} else if (request.getParameter("urlbranchid").equalsIgnoreCase("5")) {
				return "student_update";
			}
			return "student_update";
		}
		return "viewAll";
	}

	@RequestMapping(value = "/ViewDetails", method = { RequestMethod.GET, RequestMethod.POST })
	public String viewStudent() {
		String branchId;
		if (new StudentService(request, response).viewDetailsOfStudent()) {
			Object obj = request.getAttribute("urlbranchid");
			branchId = (obj == null) ? request.getParameter("urlbranchid") : obj.toString(); 
			if (branchId.equalsIgnoreCase("1")) {
				return "student_details";
			} else if (branchId.equalsIgnoreCase("2")) {
				return "student_details";
			} else if (branchId.equalsIgnoreCase("3")) {
				return "student_details";
			} else if (branchId.equalsIgnoreCase("4")) {
				return "student_details";
			} else if (branchId.equalsIgnoreCase("5")) {
				return "student_details";
			} else if (httpSession.getAttribute("userType").toString().equalsIgnoreCase("feescollector")) {
				return "student_details_withoutmodify";
			}
			return "student_details";
		} else {
			return "error";
		}
	}

	@RequestMapping(value = "/AddStudent", method = RequestMethod.POST, consumes = "multipart/form-data")
	public String addStudent(MultipartHttpServletRequest request,
			@RequestParam("fileToUpload") MultipartFile[] uploadedFiles) {
		if (new StudentService(request, response).addStudent(uploadedFiles)) {
			return "saved";
		} else {
			return "notSaved";
		}
	}

	@RequestMapping(value = "/viewAll", method = { RequestMethod.GET, RequestMethod.POST })
	public String viewAll() {
		new StudentService(request, response).viewAllStudentsParents();
		return "viewAllWithParents";
	}

	@PostMapping("/exportDataForStudents")
	public String exportDataForStudents() {
		if (new StudentService(request, response).exportDataForStudents()) {
			return "exportsuccess";
		} else {
			return "exportfailure";
		}
	}

	private String viewStudent(String id, String branchId) {
		request.setAttribute("id", id);
		request.setAttribute("urlbranchid", branchId);
		return viewStudent();
	}
	
	@PostMapping("/printAdmissionForm")
	public String printAdmissionForm() {
			return "printadmissionform";
	}
	//from here new coding
	/*@PostMapping("/searchfeecategory")
	public String searchfeecategory() {
		new StudentService(request, response).getfeecategory();
		return "addStudent";
	}*/

}

package org.ideoholic.curium.model.importfile.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.importfile.service.ImportFileService;
import org.ideoholic.curium.model.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;;

@Controller
@RequestMapping("/ImportProcess")
public class ImportFileAction {

	@Autowired
	HttpServletRequest request;

	@Autowired
	HttpServletResponse response;

	@Autowired
	HttpSession httpSession;


	@RequestMapping(value = "/readFile", method = RequestMethod.POST, consumes = "multipart/form-data")
	public String readFile(MultipartHttpServletRequest request,
			@RequestParam("fileToImport") MultipartFile[] uploadedFiles) {
		if (new ImportFileService(request, response).readFile(uploadedFiles)) {
			return "importfile";
		} else {
			return "importfile";
		}
	}

}
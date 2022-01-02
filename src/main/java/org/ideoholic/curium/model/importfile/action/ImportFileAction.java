package org.ideoholic.curium.model.importfile.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.importfile.service.ImportFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;;

@Controller
@RequestMapping("/ImportProcess")
public class ImportFileAction {

	@Autowired
	HttpServletRequest request;

	@Autowired
	HttpServletResponse response;

	@Autowired
	HttpSession httpSession;


	@PostMapping("/readFile")
	public String readFile() {
		try {
			if (new ImportFileService(request, response).readFile()) {
				return "importfile";
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "importsuccess";
	}
}
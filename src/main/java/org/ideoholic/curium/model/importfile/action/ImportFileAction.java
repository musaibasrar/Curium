package org.ideoholic.curium.model.importfile.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.feescollection.action.FeesCollectionActionAdapter;
import org.ideoholic.curium.model.feescollection.service.FeesCollectionService;
import org.ideoholic.curium.model.importfile.service.ImportFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;;

@Controller
@RequestMapping("/ImportProcess")
public class ImportFileAction {

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private HttpServletResponse response;

	@Autowired
	private HttpSession httpSession;

	@Autowired
	private ImportFileActionAdapter importFileActionAdapter;
	
	@Autowired
	private FeesCollectionActionAdapter feesCollectionActionAdapter;

	@RequestMapping(value = "/readFile", method = RequestMethod.POST, consumes = "multipart/form-data")
	public String readFile(@RequestParam("fileToImport") MultipartFile uploadedFiles) {
		try {
			if (importFileActionAdapter.readFile(uploadedFiles)) {
				return "importfile";
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "importsuccess";
	}
	
	 @RequestMapping(value = "/readFileFees", method = RequestMethod.POST, consumes = "multipart/form-data")
		public String readFileForFees(@RequestParam("fileToImport") MultipartFile uploadedFiles) {
			try {
				if (feesCollectionActionAdapter.readFileForFees(uploadedFiles)) {
					return "importfile";
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			return "importsuccess";
		}
}
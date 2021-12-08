package org.ideoholic.curium.model.importfile.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.importfile.service.ImportFileService;;

public class ImportFileAction {
	
	HttpServletRequest request;
	HttpServletResponse response;
	HttpSession httpSession;
	String url;
	String errorPage = "error.jsp";
	
	public ImportFileAction(HttpServletRequest request,
		HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.httpSession = request.getSession();
	}

	public String execute(String action){
		
		// TODO Auto-generated method stub
		if (action.equalsIgnoreCase("readFile")) {
			url = readFile();
		}
		return url;
	}
	
	
	
private String readFile(){ 
 try {
		if(new ImportFileService(request, response).readFile()){
		return "importfile.jsp";
		}
        }catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	   }
       return "importsuccess.jsp";
 }
}
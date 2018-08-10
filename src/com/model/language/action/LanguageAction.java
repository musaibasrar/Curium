package com.model.language.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.model.language.service.LanguageService;

public class LanguageAction {

	HttpServletRequest request;
	HttpServletResponse response;
	HttpSession httpSession;
	String url;
	private static final Logger logger = LogManager.getLogger(LanguageAction.class);
	
	public LanguageAction(HttpServletRequest request,
			HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.httpSession = request.getSession();

	}

	public String execute(String action) {
            if (action.equalsIgnoreCase("viewLanguage")) {
                logger.info("Action is view language");
                url = viewLanguage();
            } else if (action.equalsIgnoreCase("addLanguage")) {
                logger.info("Action is addlanguage");
                url = addLanguage();
            } else if (action.equalsIgnoreCase("deleteMultipleLanguage")) {
                logger.info("Action is deleteMultiplelanguage");
                url = deleteMultipleLanguage();
            }else if (action.equalsIgnoreCase("updateMultipleLanguage")) {
                logger.info("Action is updateMultiplelanguage");
                url = updateMultipleLanguage();
            }
            return url;
	}

        private String updateMultipleLanguage() {
            new LanguageService(request, response).updateMultipleLanguage();
            return "Controller?process=LanguageProcess&action=viewLanguage";
        }
    
        private String deleteMultipleLanguage() {
            new LanguageService(request, response).deleteMultipleLanguage();
            return "Controller?process=LanguageProcess&action=viewLanguage";
        }
    
        private String addLanguage() {
            new LanguageService(request, response).addLanguage();
            return "Controller?process=LanguageProcess&action=viewLanguage";
        }
    
        private String viewLanguage() {
            new LanguageService(request, response).viewLanguage();
            return "language.jsp";
        }
}

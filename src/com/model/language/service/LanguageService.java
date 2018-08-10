package com.model.language.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.model.branch.dao.BranchDAO;
import com.model.branch.dto.Branch;
import com.model.examlevels.dao.ExamLevelDetailsDAO;
import com.model.examlevels.dto.Examleveldetails;
import com.model.language.dao.LanguageDAO;
import com.model.language.dto.Language;
import com.util.DataUtil;

public class LanguageService {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession httpSession;
	private static final Logger logger = LogManager.getLogger(LanguageService.class);
	
	public LanguageService(HttpServletRequest request,
			HttpServletResponse response) {

		this.request = request;
		this.response = response;
		this.httpSession = request.getSession();

	}

    public void viewLanguage() {
        
        try {
            List<Language> list = new LanguageDAO().readListOfObjects();
            request.setAttribute("languageslist", list);
            logger.info("Language details List "+list.size());
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        
    }

    public void addLanguage() {

        Language lang = new Language();
        boolean result = false;
        lang.setLanguage(DataUtil.emptyString(request.getParameter("language")));

        if (!lang.getLanguage().equalsIgnoreCase("")) {
            result = new LanguageDAO().addLanguage(lang);
        }
        request.setAttribute("languagesave", result);
        request.setAttribute("languageupdate", " ");
        request.setAttribute("languagedelete", " ");
    }

    public void deleteMultipleLanguage() {
        
        String[] langIds = request.getParameterValues("languageids");
        if(langIds!=null){
        
       List<Integer> ids = new ArrayList<Integer>();
       for (String id : langIds) {
           String[] brId = id.split(":");
           ids.add(Integer.valueOf(brId[0]));
       }
       boolean result = new LanguageDAO().deleteMultipleLanguage(ids);
       request.setAttribute("languagedelete", result);
        }
        
    }

    public void updateMultipleLanguage() {
        
        String[] langIds = request.getParameterValues("languageids");
        String[] langName = request.getParameterValues("updatelanguage");

        if(langIds!=null){
            
            List<Language> langList = new ArrayList<Language>();
            
            for(int i=0; i<langIds.length;i++) {
                Language lang = new Language();
                String[] langId = langIds[i].split(":");
                lang.setIdlanguage(Integer.valueOf(langId[0]));
                lang.setLanguage(langName[Integer.valueOf(langId[1])]);
                langList.add(lang);
            }
            boolean result = new LanguageDAO().updateMultipleLanguage(langList);
            request.setAttribute("languageupdate", result);
        }
        
    }
        
}

package com.model.referencebooks.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.model.examlevels.dao.ExamLevelDetailsDAO;
import com.model.examlevels.dto.Examleveldetails;
import com.model.examlevels.dto.Subexamlevel;
import com.model.examlevels.service.ExamLevelService;
import com.model.language.service.LanguageService;
import com.model.qualification.dao.QualificationDAO;
import com.model.qualification.dto.Qualification;
import com.model.referencebooks.dao.ReferenceBooksDAO;
import com.model.referencebooks.dto.Referencebooks;
import com.model.subjectdetails.service.SubjectDetailsService;
import com.util.DataUtil;

public class ReferenceBooksService {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession httpSession;
	private static final Logger logger = LogManager.getLogger(ReferenceBooksService.class);
	
	public ReferenceBooksService(HttpServletRequest request,
			HttpServletResponse response) {

		this.request = request;
		this.response = response;
		this.httpSession = request.getSession();

	}

    public void viewReferenceBooks() {
        
            /*List<Referencebooks> list = new ReferenceBooksDAO().readListOfObjects();
            request.setAttribute("referencebookslist", list);
            logger.info("Reference Books List "+list.size());*/
            new ExamLevelService(request, response).examLevels();
            new LanguageService(request, response).viewLanguage();
        
    }

    public void deleteMultiple() {
        
        String[] referencebooksIds = request.getParameterValues("referencebooksid");
        if(referencebooksIds!=null){
        
       List<Integer> ids = new ArrayList<Integer>();
       for (String id : referencebooksIds) {
           String[] brId = id.split(":");
           ids.add(Integer.valueOf(brId[0]));
       }
       boolean result = new ReferenceBooksDAO().deleteMultiple(ids);
       request.setAttribute("referencedelete", result);
        }
        
    }

    public void updateMultipleRecords() {
        
        String[] referenceBooksIds = request.getParameterValues("referencebooksid");
        String[] referenceBooksUpdate = request.getParameterValues("updatereferencebooks");
        String[] updateExamLevel = request.getParameterValues("updateexamlevel");
        String[] updateLanguage = request.getParameterValues("updatelanguage");
        String[] updateSubject = request.getParameterValues("updatesubject");

        if(referenceBooksIds!=null){
            
            List<Referencebooks> referenceBooksList = new ArrayList<Referencebooks>();
            
            for(int i=0; i<referenceBooksIds.length;i++) {
                Referencebooks refBooks = new Referencebooks();
                String[] refId = referenceBooksIds[i].split(":");
                refBooks.setIdreferencebooks((Integer.valueOf(refId [0])));
                refBooks.setReferencebooks(referenceBooksUpdate[Integer.valueOf(refId [1])]);
                refBooks.setExamlevelcode(updateExamLevel[Integer.valueOf(refId [1])]);
                refBooks.setLanguage(updateLanguage[Integer.valueOf(refId [1])]);
                refBooks.setSubject(updateSubject[Integer.valueOf(refId [1])]);
                referenceBooksList.add(refBooks);
            }
            boolean result = new ReferenceBooksDAO().updateMultipleReferenceBooks(referenceBooksList);
            request.setAttribute("referenceupdate", result);
        }
        
    }

    public void addReferenceBooks() {

        Referencebooks ref = new Referencebooks();
        boolean result = false;
        ref.setReferencebooks(DataUtil.camelCase(request.getParameter("bookname")));
        ref.setExamlevelcode(DataUtil.emptyString(request.getParameter("examlevel")).toUpperCase());
        ref.setLanguage(DataUtil.emptyString(request.getParameter("languageoptedsave")).toUpperCase());
        ref.setSubject(DataUtil.emptyString(request.getParameter("subjectnameAjax")).toUpperCase());
        
        if (!ref.getReferencebooks().equalsIgnoreCase("")) {
            result = new ReferenceBooksDAO().addReferenceBooks(ref);
        }
        request.setAttribute("referencesave", result);
    }

    public void searchReferenceBooks() {
        
        List<Referencebooks> list = new ReferenceBooksDAO().getReferenceBooks(DataUtil.emptyString(request.getParameter("examlevelsearch")),DataUtil.emptyString(request.getParameter("languageopted")));
        request.setAttribute("referencebookslist", list);
        logger.info("Reference Books List "+list.size());
        new ExamLevelService(request, response).examLevels();
        new LanguageService(request, response).viewLanguage();
        new SubjectDetailsService(request, response).readListOfSubjects();
    
}

    public List<Referencebooks> getReferenceBooks(String examLevel, String subject, String languageopted) {
        List<Referencebooks> list = new ArrayList<Referencebooks>();
        list = new ReferenceBooksDAO().getReferenceBooks(examLevel,subject,languageopted);
        return list;
    }
        
}

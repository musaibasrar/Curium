package com.model.qualification.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.model.examlevels.dao.ExamLevelDetailsDAO;
import com.model.examlevels.dto.Examleveldetails;
import com.model.qualification.dao.QualificationDAO;
import com.model.qualification.dto.Qualification;
import com.util.DataUtil;

public class QualificationService {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession httpSession;
	private static final Logger logger = LogManager.getLogger(QualificationService.class);
	
	public QualificationService(HttpServletRequest request,
			HttpServletResponse response) {

		this.request = request;
		this.response = response;
		this.httpSession = request.getSession();

	}

    public void viewQualification() {
        
        try {
            List<Qualification> list = new QualificationDAO().readListOfObjects();
            request.setAttribute("qualificationlist", list);
            logger.info("Qualification details List "+list.size());
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        
    }

    public void addQualification() {

        Qualification qual = new Qualification();
        boolean result = false;
        qual.setQualification(DataUtil.emptyString(request.getParameter("qualification")));

        if (!qual.getQualification().equalsIgnoreCase("")) {
            result = new QualificationDAO().addQualification(qual);
        }
        request.setAttribute("qualificaitonsave", result);
    }

    public void deleteMultipleQualification() {
        
        String[] qualificationIds = request.getParameterValues("qualificationids");
        if(qualificationIds!=null){
        
       List<Integer> ids = new ArrayList<Integer>();
       for (String id : qualificationIds) {
           String[] brId = id.split(":");
           ids.add(Integer.valueOf(brId[0]));
       }
       boolean result = new QualificationDAO().deleteMultipleQualification(ids);
       request.setAttribute("qualificationdelete", result);
        }
        
    }

    public void updateMultipleQualification() {
        
        String[] qualificationIds = request.getParameterValues("qualificationids");
        String[] qualificationUpdate = request.getParameterValues("updatequalification");

        if(qualificationIds!=null){
            
            List<Qualification> qualificationList = new ArrayList<Qualification>();
            
            for(int i=0; i<qualificationIds.length;i++) {
                Qualification qual = new Qualification();
                String[] qualId = qualificationIds[i].split(":");
                qual.setIdqualification(Integer.valueOf(qualId[0]));
                qual.setQualification(qualificationUpdate[Integer.valueOf(qualId[1])]);
                qualificationList.add(qual);
            }
            boolean result = new QualificationDAO().updateMultipleQualification(qualificationList);
            request.setAttribute("qualificationupdate", result);
        }
        
    }
        
}

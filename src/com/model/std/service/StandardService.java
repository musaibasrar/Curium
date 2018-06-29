package com.model.std.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.std.dao.StandardDetailsDAO;
import com.model.std.dto.Classsec;
import com.util.DataUtil;

public class StandardService {

	HttpServletRequest request;
	HttpServletResponse response;
	HttpSession httpSession;
	private String CURRENTACADEMICYEAR = "currentAcademicYear";
	private String BRANCHID = "branchid";

	public StandardService(HttpServletRequest request,
			HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.httpSession = request.getSession();
	}

    public boolean createClass() {
        
        if(httpSession.getAttribute(BRANCHID)!=null){
            Classsec classsec = new Classsec();
            classsec.setClassdetails(DataUtil.emptyString(request.getParameter("classdetails")));
            classsec.setSection(DataUtil.emptyString(request.getParameter("section")));
            classsec.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
            new StandardDetailsDAO().create(classsec);
            viewClasses();
            return true;
            }
            
        return false;
    
    }

    public boolean viewClasses() {
        if(httpSession.getAttribute(BRANCHID)!=null){
            List<Classsec> classsecList = new StandardDetailsDAO().viewClasses(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
            httpSession.setAttribute("classdetailslist", classsecList);
            return true;
        }
        
        return false;
    }

    public boolean deleteClasses() {
        
        String[] classIds = request.getParameterValues("classids");
        if (classIds != null) {
                List ids = new ArrayList();
                for (String id : classIds) {
                        ids.add(Integer.valueOf(id));
                }
                new StandardDetailsDAO().deleteMultiple(ids);
                return viewClasses();
        }
        return false;
    }
}

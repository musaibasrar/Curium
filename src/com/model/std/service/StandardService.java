package com.model.std.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.std.dao.StandardDetailsDAO;
import com.model.std.dto.Classhierarchy;
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

	public void addClassHierarchy() {
        
        if(httpSession.getAttribute(BRANCHID)!=null){
            Classhierarchy classHierarchy = new Classhierarchy();
            classHierarchy.setLowerclass(DataUtil.emptyString(request.getParameter("lowerclass")));
            classHierarchy.setUpperclass(DataUtil.emptyString(request.getParameter("upperclass")));
            classHierarchy.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
            new StandardDetailsDAO().createClassHierarchy(classHierarchy);
            viewClasses();
            }
    }

	public void deleteClassHierarchy() {
        
        String[] classIds = request.getParameterValues("idclasshierarchy");
        if (classIds != null) {
                List ids = new ArrayList();
                for (String id : classIds) {
                        ids.add(Integer.valueOf(id));
                }
                new StandardDetailsDAO().deleteClassHierarchy(ids);
                viewClasses();
        }
    }

	public void viewClassHierarchy() {
        if(httpSession.getAttribute(BRANCHID)!=null){
            List<Classhierarchy> classHierarchy = new StandardDetailsDAO().viewClassHierarchy(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
            httpSession.setAttribute("classhierarchy", classHierarchy);
        }
    }

	public boolean graduateMultiple() {
		String[] studentIds = request.getParameterValues("studentIDs");
		boolean result = false;
			List ids = new ArrayList();
			for (String id : studentIds) {
				System.out.println("id" + id);
				ids.add(Integer.valueOf(id));
		}
		if (new StandardDetailsDAO().graduateMultiple(ids)) {
			result = true;
		}
		return result;

	}
	
	public boolean droppedoutMultiple() {
	String[] studentIds = request.getParameterValues("studentIDs");
	boolean result = false;
		List ids = new ArrayList();
		for (String id : studentIds) {
			System.out.println("id" + id);
			ids.add(Integer.valueOf(id));
		}
		if(new StandardDetailsDAO().droppedoutMultiple(ids)){
			result = true;
		}
		return result;
	}

	public boolean leftoutMultiple() {
		String[] studentIds = request.getParameterValues("studentIDs");
		boolean result = false;
		List ids = new ArrayList();
		for (String id : studentIds) {
			System.out.println("id" + id);
			ids.add(Integer.valueOf(id));

		}
		if(new StandardDetailsDAO().leftoutMultiple(ids)) {
			result = true;
		}
		return result;
	}
}

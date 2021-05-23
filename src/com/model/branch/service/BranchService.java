package com.model.branch.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.model.branch.dao.BranchDAO;
import com.model.branch.dto.Branch;
import com.model.branch.dto.Districts;
import com.model.examlevels.dto.Subexamlevel;
import com.model.examlevels.service.ExamLevelService;
import com.util.DataUtil;

public class BranchService {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession httpSession;
	private static final Logger logger = LogManager.getLogger(BranchService.class);
	
	public BranchService(HttpServletRequest request,
			HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.httpSession = request.getSession();
	}

    public void viewBranches() {
        try {
            List<Branch> list = new BranchDAO().readListOfObjects();
            Collections.sort(list);
            httpSession.setAttribute("branchList", list);
            logger.info("Branch List "+list.size());
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        
    }

    public void viewDistricts() {
        
        try {
            List<Districts> list = new BranchDAO().readListOfObjectsDistrict();
            request.setAttribute("districtsList", list);
            logger.info("District List "+list.size());
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        
    }

    public void addDistricts() {
        
        Districts district = new Districts();
        boolean result = false;
        district.setDistrictcode(DataUtil.emptyString(request.getParameter("districtcode")));
        district.setDistrictname(DataUtil.emptyString(request.getParameter("districtname")));
        district.setState(DataUtil.emptyString(request.getParameter("state")));

        if (!district.getDistrictname().equalsIgnoreCase("")) {
            result = new BranchDAO().checkDistrict(district);
            
            if(!result) {
                result = new BranchDAO().create(district);
                request.setAttribute("districtsave", result);
            }else {
                request.setAttribute("districtduplicate", result);
            }
            
        }
        
        
    }

    public void updateDistricts() {
        
        String[] districtIds = request.getParameterValues("districtids");
        String[] districtNames = request.getParameterValues("distname");
        String[] distcode = request.getParameterValues("distcode");
        String[] state = request.getParameterValues("stateupdate");
        boolean result = false;
        if(districtIds!=null){
            
            List<Districts> districtList = new ArrayList<Districts>();
            
            for(int i=0; i<districtIds.length;i++) {
                Districts dist = new Districts();
                String[] distId = districtIds[i].split(":");
                dist.setIddistrict(Integer.valueOf(distId[0]));
                dist.setDistrictname(districtNames[Integer.parseInt(distId[1])]);
                dist.setDistrictcode(distcode[Integer.parseInt(distId[1])]);
                dist.setState(state[Integer.parseInt(distId[1])]);
                districtList.add(dist);
            }
           result =  new BranchDAO().updateMultiple(districtList);
           request.setAttribute("districtupdate", result);
        }
}

    public void deleteDistricts() {
        String[] distIds = request.getParameterValues("districtids");
        boolean result = false;
        if (distIds != null) {

            List<Integer> ids = new ArrayList<Integer>();
            for (String id : distIds) {
                String[] distId = id.split(":");
                ids.add(Integer.valueOf(distId[0]));
            }
            result = new BranchDAO().deleteMultiple(ids);
            request.setAttribute("districtdelete", result);
        }
    }

    public void deleteCenters() {
        
        String[] branchIds = request.getParameterValues("branchids");
        if(branchIds!=null){
        
       List<Integer> ids = new ArrayList<Integer>();
       for (String id : branchIds) {
           String[] brId = id.split(":");
           ids.add(Integer.valueOf(brId[0]));
       }
       boolean result = new BranchDAO().deleteMultipleCenters(ids);
       request.setAttribute("centerdelete", result);
        }
        
    }

    public void updateCenters() {
        
        String[] branchIds = request.getParameterValues("branchids");
        String[] centerCode = request.getParameterValues("updatecentercode");
        String[] centerNames = request.getParameterValues("updatecentername");
        String[] distCode = request.getParameterValues("updatedistcode");

        if(branchIds!=null){
            
            List<Branch> branchList = new ArrayList<Branch>();
            
            for(int i=0; i<branchIds.length;i++) {
                Branch branch = new Branch();
                String[] brId = branchIds[i].split(":");
                branch.setIdbranch(Integer.valueOf(brId[0]));
                branch.setCentercode(centerCode[Integer.valueOf(brId[1])]);
                branch.setCentername(centerNames[Integer.valueOf(brId[1])]);
                branch.setDistrictcode(distCode[Integer.valueOf(brId[1])]);
                branchList.add(branch);
            }
            boolean result = new BranchDAO().updateMultipleCenters(branchList);
            request.setAttribute("centerupdate", result);
        }
}

    public void addBranches() {
        
        Branch branch = new Branch();
        boolean result = false;
        String[] distCode = DataUtil.emptyString(request.getParameter("districtcode")).split("-");
        
        branch.setCentercode(DataUtil.emptyString(request.getParameter("centercode")));
        branch.setCentername(DataUtil.emptyString(request.getParameter("centername")));
        branch.setDistrictcode(distCode[1]);
        branch.setState(distCode[2]);
        
        if (!branch.getCentername().equalsIgnoreCase("")) {
            
            result = new BranchDAO().checkBranch(branch);
            
            if(!result) {
                result = new BranchDAO().addBranches(branch);
                request.setAttribute("centersave", result);
            }else {
                request.setAttribute("centerduplicate", result);
            }
        }
        
       
    }

    public void viewBranchesCenter(int branchId) {
        
        try {
            List<Branch> list = new BranchDAO().readListOfObjects(branchId);
            request.setAttribute("branchList", list);
            logger.info("Branch List "+list.size());
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        
    }
    
    public void viewBranchesCenter() {
        
        try {
            List<Branch> list = new BranchDAO().readListOfObjects(Integer.parseInt(httpSession.getAttribute("branchid").toString()));
            request.setAttribute("branchList", list);
            logger.info("Branch List "+list.size());
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        
    }

    public void viewDistrictsCenter(String districtcode) {
        
        try {
            List<Districts> list = new BranchDAO().getDistrict(districtcode);
            request.setAttribute("districtsList", list);
            logger.info("District List "+list.size());
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        
    }

    public String getDistrictName()  {
       
            Branch branch = new BranchDAO().getDistrictName(DataUtil.emptyString(request.getParameter("centercode")));
                        if(branch!=null){
                               return branch.getDistrictcode();
                        }
            return "";            
        }

	public void viewBranchesById() {
		
		List<Branch> list = new ArrayList<Branch>();
		
        try {
        	
        	int branchid = Integer.parseInt(httpSession.getAttribute("branchid").toString());
        	
        	if(branchid==1) {
        		list = new BranchDAO().readListOfObjects();
                Collections.sort(list);
        	}else {
        		list = new BranchDAO().readListOfObjects(branchid);
        	}
            
            httpSession.setAttribute("branchList", list);
            logger.info("Branch List "+list.size());
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        
    }

}

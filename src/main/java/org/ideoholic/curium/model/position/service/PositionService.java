package org.ideoholic.curium.model.position.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.department.dao.departmentDAO;
import org.ideoholic.curium.model.department.dto.Department;
import org.ideoholic.curium.model.position.dao.positionDAO;
import org.ideoholic.curium.model.position.dto.Position;
import org.ideoholic.curium.model.student.dao.studentDetailsDAO;
import org.ideoholic.curium.util.DataUtil;

public class PositionService {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession httpSession;

	public PositionService(HttpServletRequest request,
			HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.httpSession = request.getSession();
	}

	public void addPosition() {
		
		Position position = new Position();

		position.setPositionname(DataUtil.emptyString(request
				.getParameter("position")));
		position.setBranchid(Integer.parseInt(httpSession.getAttribute("branchid").toString()));
		position.setUserid(Integer.parseInt(httpSession.getAttribute("userloginid").toString()));
		if(!position.getPositionname().equalsIgnoreCase("")){
			position = new positionDAO().create(position);
		}
		

	}

	public boolean viewPosition() {
		boolean result = false;
        try {
        	List<Position> list = new positionDAO().readListOfObjects(Integer.parseInt(httpSession.getAttribute("branchid").toString()));
            httpSession.setAttribute("positionList", list);

            result = true;
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
	}

	public void deleteMultiple() {
		 String[] positionIds = request.getParameterValues("positionIDs");
		 if(positionIds!=null){
	        List<Integer> ids = new ArrayList();
	        for (String id : positionIds) {
	            System.out.println("id" + id);
	            ids.add(Integer.valueOf(id));

	        }
	        System.out.println("id length" + positionIds.length);
	        new positionDAO().deleteMultiple(ids);
		 }
	}

}

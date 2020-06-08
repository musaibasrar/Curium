package org.ideoholic.stampfees.service;

import java.util.ArrayList;
import java.util.List;

import com.model.parents.dto.Parents;
import com.model.stampfees.dao.StampFeesDAO;
import com.model.student.dao.studentDetailsDAO;
import com.util.DataUtil;

public class StampFeesServiceImpl implements StampFeesService {
	
	public String advanceSearch(String branchId,String studentname, String addClass,String addSec) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		List<Parents> searchStudentList = new ArrayList<Parents>();
		
		if(branchId!=null){
		
		String queryMain = "From Parents as parents where";
		String conClassStudying = "";

		if (!addClass.equalsIgnoreCase("")) {
			conClassStudying = addClass+"--"+"%";
		}
		if (!addSec.equalsIgnoreCase("")) {
			conClassStudying = addClass;
			conClassStudying = conClassStudying+"--"+addSec+"%";
		}

		String classStudying = DataUtil.emptyString(conClassStudying);
		String querySub = "";

		if (!studentname.equalsIgnoreCase("")) {
			querySub = " parents.Student.name like '%" + studentname + "%' AND parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0 AND parents.branchid="+Integer.parseInt(branchId.toString());
		}

		if (!classStudying.equalsIgnoreCase("")
				&& !querySub.equalsIgnoreCase("")) {
			querySub = querySub + " AND parents.Student.classstudying like '"
					+ classStudying + "' AND parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0";
		} else if (!classStudying.equalsIgnoreCase("")) {
			querySub = querySub + " parents.Student.classstudying like '"
					+ classStudying + "' AND parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0 AND parents.branchid="+Integer.parseInt(branchId.toString())+" order by parents.Student.admissionnumber ASC";
		}

		if(!"".equalsIgnoreCase(querySub)) {
			queryMain = queryMain + querySub;
			searchStudentList = new studentDetailsDAO().getStudentsList(queryMain);
		}
		
	}
		sb.append("searchStudentList").append(searchStudentList);
		sb.append("}");
		return sb.toString();
	}

	
	public String deleteFeesStamp(String currentYear, String[] studentIds) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		if(studentIds!=null){
			List ids = new ArrayList();
	        for (String id : studentIds) {
	            System.out.println("id" + id);
	            ids.add(Integer.valueOf(id));
	            
	        }
	        System.out.println("id length" + studentIds.length);
	        new StampFeesDAO().deleteMultiple(ids,currentYear);
		
	}
		sb.append("}");
		return sb.toString();
		}
}

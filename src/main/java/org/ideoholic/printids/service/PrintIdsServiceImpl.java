package org.ideoholic.printids.service;

import java.util.ArrayList;
import java.util.List;

import com.model.parents.dto.Parents;
import com.model.student.dao.studentDetailsDAO;
import com.util.DataUtil;

public class PrintIdsServiceImpl implements PrintIdsService {
	
public String searchDetails(String branchId,String studentName,String addClass,String addSec) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
		
		List<Parents> searchStudentList = new ArrayList<Parents>();
		
		if(branchId!=null){
			String queryMain = "From Parents as parents where";
			String conClassStudying = "";

			if (!addClass.equalsIgnoreCase("")) {

				conClassStudying = addClass+"--" +"%";

			}
			if (!addSec.equalsIgnoreCase("")) {
				conClassStudying = addClass;
				conClassStudying = conClassStudying+"--"+addSec+"%";
			}

			String classStudying = DataUtil.emptyString(conClassStudying);
			String querySub = "";

			if (!studentName.equalsIgnoreCase("")) {
				querySub = " parents.Student.name like '%" + studentName + "%' AND parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0 AND parents.branchid="+Integer.parseInt(branchId.toString());
			}

			if (!classStudying.equalsIgnoreCase("")
					&& !querySub.equalsIgnoreCase("")) {
				querySub = querySub + " AND parents.Student.classstudying like '"
						+ classStudying + "' AND parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0";
			} else if (!classStudying.equalsIgnoreCase("")) {
				querySub = querySub + " parents.Student.classstudying like '"
						+ classStudying + "' AND parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0 and parents.branchid="+Integer.parseInt(branchId.toString());
			}

			queryMain = queryMain + querySub;
			/*
			 * queryMain =
			 * "FROM Parents as parents where  parents.Student.dateofbirth = '2006-04-06'"
			 * ;
			 */
			System.out.println("SEARCH QUERY ***** " + queryMain);
			searchStudentList = new studentDetailsDAO().getStudentsList(queryMain);
		}
		sb.append("searchStudentList").append(searchStudentList);
			sb.append("}");
			return sb.toString();
		
	}

}

package com.model.printids.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.model.parents.dto.Parents;
import com.model.printids.dao.PrintIdsDAO;
import com.model.student.dao.studentDetailsDAO;
import com.util.DataUtil;

public class PrintIdsService {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession httpSession;
	
	private static final Logger logger = LogManager.getLogger(PrintIdsService.class);

	public PrintIdsService(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.httpSession = request.getSession();
	}

	public void searchDetails() {
		
		List<Parents> searchStudentList = new ArrayList<Parents>();
		
		if(httpSession.getAttribute("branchid")!=null){
			String queryMain = "From Parents as parents where";
			String studentname = DataUtil.emptyString(request
					.getParameter("namesearch"));

			String addClass = request.getParameter("classsearch");
			String addSec = request.getParameter("secsearch");
			String conClassStudying = "";

			if (!addClass.equalsIgnoreCase("Class")) {

				conClassStudying = addClass + " " + "%";

			}
			if (!addSec.equalsIgnoreCase("Sec")) {
				conClassStudying = addClass;
				conClassStudying = conClassStudying + " " + addSec;
			}

			String classStudying = DataUtil.emptyString(conClassStudying);
			String querySub = "";

			if (!studentname.equalsIgnoreCase("")) {
				querySub = " parents.Student.name like '%" + studentname + "%' AND parents.Student.archive=0 AND parent.Student.passedout = 0 AND parent.Student.droppedout = 0 AND parents.branchid="+Integer.parseInt(httpSession.getAttribute("branchid").toString());
			}

			if (!classStudying.equalsIgnoreCase("")
					&& !querySub.equalsIgnoreCase("")) {
				querySub = querySub + " AND parents.Student.classstudying like '"
						+ classStudying + "' AND parents.Student.archive=0 AND parent.Student.passedout = 0 AND parent.Student.droppedout = 0";
			} else if (!classStudying.equalsIgnoreCase("")) {
				querySub = querySub + " parents.Student.classstudying like '"
						+ classStudying + "' AND parents.Student.archive=0 AND parent.Student.passedout = 0 AND parent.Student.droppedout = 0 and parents.branchid="+Integer.parseInt(httpSession.getAttribute("branchid").toString());
			}

			queryMain = queryMain + querySub + " Order By parents.Student.admissionnumber ASC";
			/*
			 * queryMain =
			 * "FROM Parents as parents where  parents.Student.dateofbirth = '2006-04-06'"
			 * ;
			 */
			logger.info("SEARCH QUERY ***** " + queryMain);
			searchStudentList = new studentDetailsDAO().getStudentsList(queryMain);
		}
			request.setAttribute("searchStudentList", searchStudentList);
		
	}

	public boolean printMultiple() {
		boolean result = false;
        String[] studentIDs = request.getParameterValues("studentIDs");
        List<Long> ids = new ArrayList<Long>();
        Parents parentsDetails = new Parents();
     
        	
          int i = 1;
       
          for (String id : studentIDs) {

              
               logger.info("Value of i is " + i);
               int sid = Integer.valueOf(id);
               parentsDetails = new PrintIdsDAO().printMultipleIds(id);
               
               //PersonalDetails personal = new PersonalDetailsDAO().printMultiple(pid);

               if (parentsDetails != null) {
                   httpSession.setAttribute("studentname" + i + "", parentsDetails.getStudent().getName());
                   httpSession.setAttribute("fathersname" + i + "", parentsDetails.getFathersname());
                   httpSession.setAttribute("class" + i + "", parentsDetails.getClass());
                   httpSession.setAttribute("Address" + i + "", parentsDetails.getAddresspermanent());
                   httpSession.setAttribute("Contactnumber" + i + "", parentsDetails.getContactnumber());
                   httpSession.setAttribute("studentpic" + i + "",parentsDetails.getStudent().getStudentpic());
                   
                   //result = true;
               } else {

                  
                   //result = false;
               }

               i++;
           }
       
       httpSession.setAttribute("iInitial", i);
       i = (int) (Math.ceil((float) (i) / 3));
       httpSession.setAttribute("endValue", i);
       
       
        if (parentsDetails == null) {
            result = false;
        } else {
            result = true;
        }
        return result;

}
	
}

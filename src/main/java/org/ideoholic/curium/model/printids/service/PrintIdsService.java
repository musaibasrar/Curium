package org.ideoholic.curium.model.printids.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.mess.card.dto.Card;
import org.ideoholic.curium.model.parents.dto.Parents;
import org.ideoholic.curium.model.printids.dao.PrintIdsDAO;
import org.ideoholic.curium.model.student.dao.studentDetailsDAO;
import org.ideoholic.curium.util.DataUtil;
import org.ideoholic.curium.util.DateUtil;

public class PrintIdsService {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession httpSession;

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

			if (!addClass.equalsIgnoreCase("")) {

				conClassStudying = addClass+"--" +"%";

			}
			if (!addSec.equalsIgnoreCase("")) {
				conClassStudying = addClass;
				conClassStudying = conClassStudying+"--"+addSec+"%";
			}

			String classStudying = DataUtil.emptyString(conClassStudying);
			String querySub = "";

			if (!studentname.equalsIgnoreCase("")) {
				querySub = " parents.Student.name like '%" + studentname + "%' AND parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0 AND parents.branchid="+Integer.parseInt(httpSession.getAttribute("branchid").toString());
			}

			if (!classStudying.equalsIgnoreCase("")
					&& !querySub.equalsIgnoreCase("")) {
				querySub = querySub + " AND parents.Student.classstudying like '"
						+ classStudying + "' AND parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0";
			} else if (!classStudying.equalsIgnoreCase("")) {
				querySub = querySub + " parents.Student.classstudying like '"
						+ classStudying + "' AND parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0 and parents.branchid="+Integer.parseInt(httpSession.getAttribute("branchid").toString());
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
			request.setAttribute("searchStudentList", searchStudentList);
		
	}

	public boolean printMultiple() {
		boolean result = false;
        String[] studentIDs = request.getParameterValues("studentIDs");
        List<Long> ids = new ArrayList<Long>();
        Parents parentsDetails = new Parents();
     
        	
          int i = 1;
       
          for (String id : studentIDs) {

              
               System.out.println("Value of i is " + i);
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
	
	
public void searchDetailsCardValidity() {
		
		List<Parents> searchStudentList = new ArrayList<Parents>();
		Map<Parents,Card> parentsCardList = new HashMap<Parents,Card>();
		
		if(httpSession.getAttribute("branchid")!=null){
			String queryMain = "From Parents as parents where";
			String studentname = DataUtil.emptyString(request
					.getParameter("namesearch"));

			String addClass = request.getParameter("classsearch");
			String addSec = request.getParameter("secsearch");
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

			if (!studentname.equalsIgnoreCase("")) {
				querySub = " parents.Student.name like '%" + studentname + "%' AND parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0 AND parents.branchid="+Integer.parseInt(httpSession.getAttribute("branchid").toString());
			}

			if (!classStudying.equalsIgnoreCase("")
					&& !querySub.equalsIgnoreCase("")) {
				querySub = querySub + " AND parents.Student.classstudying like '"
						+ classStudying + "' AND parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0 and parents.branchid="+Integer.parseInt(httpSession.getAttribute("branchid").toString());
			} else if (!classStudying.equalsIgnoreCase("")) {
				querySub = querySub + " parents.Student.classstudying like '"
						+ classStudying + "' AND parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0 and parents.branchid="+Integer.parseInt(httpSession.getAttribute("branchid").toString());
			}

			queryMain = queryMain + querySub;
			/*
			 * queryMain =
			 * "FROM Parents as parents where  parents.Student.dateofbirth = '2006-04-06'"
			 * ;
			 */
			System.out.println("SEARCH QUERY ***** " + queryMain);
			searchStudentList = new studentDetailsDAO().getStudentsList(queryMain);
			List<Integer> studentids = new ArrayList<>(); 
			
			for (Parents parents : searchStudentList) {
				studentids.add(parents.getStudent().getSid());
			}
			
			List<Card> cardList = new studentDetailsDAO().getCardDetails(studentids);
			
			for (Parents parents : searchStudentList) {
				
				for (Card card : cardList) {
					
					if(card.getSid() == parents.getStudent().getSid()) {
						parentsCardList.put(parents, card);
					}
				}
				
			}
			
		}
			request.setAttribute("parentscardlist", parentsCardList);
		
	}

	
public boolean updateCardValidity() {
		
		boolean result = false;
        String[] studentIDs = request.getParameterValues("studentIDs");
        List<Card> cardList = new ArrayList<Card>();
        
        Parents parentsDetails = new Parents();
        
        if(studentIDs !=null) {
        	
        for (String id : studentIDs) {
        	Card card = new Card();
        	
             int sid = Integer.valueOf(id);
             
            card.setSid(sid);
            card.setValidfrom(DateUtil.indiandateParser(request.getParameter("validfrom_"+sid)));
            card.setValidto(DateUtil.indiandateParser(request.getParameter("validto_"+sid)));
             cardList.add(card);
             
         }
        
        if(cardList.size()>0) {
        	result = new PrintIdsDAO().updateCardValidity(cardList);
        }
        }
        request.setAttribute("updatecard", result);
        return result;
	}
	
}

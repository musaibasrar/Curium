package com.model.printids.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.mess.card.dto.Card;
import com.model.parents.dto.Parents;
import com.model.printids.dao.PrintIdsDAO;
import com.model.student.dao.studentDetailsDAO;
import com.util.DataUtil;
import com.util.DateUtil;

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
        List<Integer> ids = new ArrayList<Integer>();
        Parents parentsDetails = new Parents();
        
        for (String id : studentIDs) {

             int sid = Integer.valueOf(id);
             ids.add(sid);
             
         }
     
        List<Parents> parentsDetailsList = new PrintIdsDAO().printMultipleIds(ids);
        /*request.setAttribute("messcardstudentlist", parentsDetailsList);*/
        Map<Parents,Card> parentsCard = new HashMap<Parents,Card>();
        
        List<Card> cardList = new studentDetailsDAO().getCardDetails(ids);
        
        for (Parents parentsList : parentsDetailsList) {
			
        	for (Card card : cardList) {
        		
        		if(card.getSid() == parentsList.getStudent().getSid()) {
        			parentsCard.put(parentsList, card);
        		}
				
			}
		}
        
        request.setAttribute("messcardstudentlist", parentsCard);
        /*String date = DateUtil.todaysDateydm();
        LocalDate lastDayOfMonth = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyyMMdd"))
               .with(TemporalAdjusters.lastDayOfMonth());
        int day = lastDayOfMonth.getDayOfMonth();
        int month = lastDayOfMonth.getMonthValue();
        int year = lastDayOfMonth.getYear();
        
        String validTill = Integer.toString(day)+"/"+Integer.toString(month)+"/"+Integer.toString(year);
        request.setAttribute("validtill", validTill);*/
         /* int i = 1;
       
          for (String id : studentIDs) {

              
               System.out.println("Value of i is " + i);
               int sid = Integer.valueOf(id);
               parentsDetails = new PrintIdsDAO().printMultipleIds(id);
               
               //PersonalDetails personal = new PersonalDetailsDAO().printMultiple(pid);

               if (parentsDetails != null) {
                   httpSession.setAttribute("studentname" + i + "", parentsDetails.getStudent().getName());
                   httpSession.setAttribute("admissionnumber" + i + "", parentsDetails.getStudent().getAdmissionnumber());
                   httpSession.setAttribute("uid" + i + "", parentsDetails.getStudent().getStudentexternalid());
                   String breakFast = DataUtil.emptyString(parentsDetails.getStudent().getBreakfast());
                   String lunch = DataUtil.emptyString(parentsDetails.getStudent().getLunch());
                   String dinner = DataUtil.emptyString(parentsDetails.getStudent().getDinner());
                   httpSession.setAttribute("breakfast" + i + "", breakFast);
                   httpSession.setAttribute("lunch" + i + "", lunch);
                   httpSession.setAttribute("dinner" + i + "", dinner);
                   httpSession.setAttribute("studentpic" + i + "",parentsDetails.getStudent().getStudentpic());
                   //result = true;
               } else {

                  
                   //result = false;
               }

               i++;
           }
       
       httpSession.setAttribute("iInitial", i);
       i = (int) (Math.ceil((float) (i) / 3));
       httpSession.setAttribute("endValue", i);*/
       
       
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

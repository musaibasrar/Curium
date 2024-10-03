package org.ideoholic.curium.model.printids.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.documents.dto.SearchStudentDto;
import org.ideoholic.curium.model.documents.dto.SearchStudentResponseDto;
import org.ideoholic.curium.model.employee.dto.PrintMultipleEmployeesResponseDto;
import org.ideoholic.curium.model.mess.card.dto.Card;
import org.ideoholic.curium.model.parents.dto.Parents;
import org.ideoholic.curium.model.printids.dao.PrintIdsDAO;
import org.ideoholic.curium.model.printids.dto.ParentCardResponsDto;
import org.ideoholic.curium.model.printids.dto.PrintIdsDto;
import org.ideoholic.curium.model.student.dao.studentDetailsDAO;
import org.ideoholic.curium.model.student.dto.StudentIdsDto;
import org.ideoholic.curium.util.DataUtil;
import org.ideoholic.curium.util.DateUtil;
import org.springframework.stereotype.Service;

@Service
public class PrintIdsService {


	
	public SearchStudentResponseDto searchDetails(SearchStudentDto searchStudentDto,String branchid) {
		
		SearchStudentResponseDto searchStudentResponseDto = new SearchStudentResponseDto();
		List<Parents> searchStudentList = new ArrayList<Parents>();
		
		if(branchid!=null){
			String queryMain = "From Parents as parents where";
			String studentname = DataUtil.emptyString(searchStudentDto.getNameSearch());

			String addClass = searchStudentDto.getClassSearch();
			String addSec = searchStudentDto.getSecSearch();
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
				querySub = " parents.Student.name like '%" + studentname + "%' AND parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0 AND parents.branchid="+Integer.parseInt(branchid);
			}

			if (!classStudying.equalsIgnoreCase("")
					&& !querySub.equalsIgnoreCase("")) {
				querySub = querySub + " AND parents.Student.classstudying like '"
						+ classStudying + "' AND parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0";
			} else if (!classStudying.equalsIgnoreCase("")) {
				querySub = querySub + " parents.Student.classstudying like '"
						+ classStudying + "' AND parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0 and parents.branchid="+Integer.parseInt(branchid);
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
		    searchStudentResponseDto.setSearchStudentList(searchStudentList);
			return searchStudentResponseDto;
		
	}

	public PrintMultipleEmployeesResponseDto printMultiple(StudentIdsDto studentIdsDto,String currentAcademicYear) {
		PrintMultipleEmployeesResponseDto printMultipleEmployeesResponseDto = new PrintMultipleEmployeesResponseDto();
        String[] studentIDs = studentIdsDto.getStudentIds();
        List<Long> ids = new ArrayList<Long>();
        Parents parentsDetails = new Parents();
     
        	
          int i = 1;
       
          for (String id : studentIDs) {

              
               System.out.println("Value of i is " + i);
               int sid = Integer.valueOf(id);
               parentsDetails = new PrintIdsDAO().printMultipleIds(id);
               
               //PersonalDetails personal = new PersonalDetailsDAO().printMultiple(pid);

               if (parentsDetails != null) {
            	   printMultipleEmployeesResponseDto.getResultParams().put("studentname" + i + "", parentsDetails.getStudent().getName());
            	   printMultipleEmployeesResponseDto.getResultParams().put("fathersname" + i + "", parentsDetails.getFathersname());
            	   printMultipleEmployeesResponseDto.getResultParams().put("mothersname" + i + "", parentsDetails.getMothersname());
            	   printMultipleEmployeesResponseDto.getResultParams().put("classsection" + i + "", parentsDetails.getStudent().getClassstudying());
            	   printMultipleEmployeesResponseDto.getResultParams().put("contactnumber" + i + "", parentsDetails.getContactnumber());
            	   printMultipleEmployeesResponseDto.getResultParams().put("address" + i + "", parentsDetails.getAddresspermanent());
            	   printMultipleEmployeesResponseDto.getResultParams().put("studentpic" + i + "",parentsDetails.getStudent().getStudentpic());
            	   printMultipleEmployeesResponseDto.getResultParams().put("dateofbirth" + i + "", DateUtil.dateParserddMMYYYY(parentsDetails.getStudent().getDateofbirth()));
            	   printMultipleEmployeesResponseDto.getResultParams().put("currentacadmicyear", currentAcademicYear);
            	   printMultipleEmployeesResponseDto.getResultParams().put("rollnumber" + i + "", parentsDetails.getStudent().getSts());
            	   printMultipleEmployeesResponseDto.getResultParams().put("admissionnumber" + i + "", parentsDetails.getStudent().getAdmissionnumber());
                   //result = true;
               } else {

                  
                   //result = false;
               }

               i++;
           }
       
       printMultipleEmployeesResponseDto.setInitialValue(i);
       i = (int) (Math.ceil((float) (i) / 3));
       printMultipleEmployeesResponseDto.setEndValue(i);
       
       
        if (parentsDetails == null) {
        	printMultipleEmployeesResponseDto.setSuccess(false);
        } else {
        	printMultipleEmployeesResponseDto.setSuccess(true);
        }
        return printMultipleEmployeesResponseDto;

}
	
	
public ParentCardResponsDto searchDetailsCardValidity(SearchStudentDto searchStudentDto,String branchid) {
		
	    ParentCardResponsDto parentCardResponsDto = new ParentCardResponsDto();
		List<Parents> searchStudentList = new ArrayList<Parents>();
		Map<Parents,Card> parentsCardList = new HashMap<Parents,Card>();
		
		if(branchid!=null){
			String queryMain = "From Parents as parents where";
			String studentname = DataUtil.emptyString(searchStudentDto.getNameSearch());

			String addClass = searchStudentDto.getClassSearch();
			String addSec = searchStudentDto.getSecSearch();
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
				querySub = " parents.Student.name like '%" + studentname + "%' AND parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0 AND parents.branchid="+Integer.parseInt(branchid);
			}

			if (!classStudying.equalsIgnoreCase("")
					&& !querySub.equalsIgnoreCase("")) {
				querySub = querySub + " AND parents.Student.classstudying like '"
						+ classStudying + "' AND parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0 and parents.branchid="+Integer.parseInt(branchid);
			} else if (!classStudying.equalsIgnoreCase("")) {
				querySub = querySub + " parents.Student.classstudying like '"
						+ classStudying + "' AND parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0 and parents.branchid="+Integer.parseInt(branchid);
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
		    parentCardResponsDto.setParentsCardList(parentsCardList);
		    return parentCardResponsDto;
		
	}

	
        public ResultResponse updateCardValidity(PrintIdsDto printIdsDto) {
		
        ResultResponse result = ResultResponse.builder().build();
		boolean success = false;
        String[] studentIDs = printIdsDto.getStudentIDs();
        List<Card> cardList = new ArrayList<Card>();
        
        Parents parentsDetails = new Parents();
        
        if(studentIDs !=null) {
        	
        for (String id : studentIDs) {
        	Card card = new Card();
        	
             int sid = Integer.valueOf(id);
             
            card.setSid(sid);
            card.setValidfrom(DateUtil.indiandateParser(printIdsDto.getRequestParams().get("validfrom_"+sid)));
            card.setValidto(DateUtil.indiandateParser(printIdsDto.getRequestParams().get("validto_"+sid)));
             cardList.add(card);
             
         }
        
        if(cardList.size()>0) {
        	success = new PrintIdsDAO().updateCardValidity(cardList);
        }
        }
        
        result.setSuccess(success);
        return result;
	}
	
}

package org.ideoholic.curium.model.stampfees.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.account.dao.AccountDAO;
import org.ideoholic.curium.model.account.dto.VoucherEntrytransactions;
import org.ideoholic.curium.model.documents.dto.SearchStudentDto;
import org.ideoholic.curium.model.documents.dto.SearchStudentResponseDto;
import org.ideoholic.curium.model.feescategory.dao.feesCategoryDAO;
import org.ideoholic.curium.model.feescategory.dto.Feescategory;
import org.ideoholic.curium.model.feescategory.dto.FeescategoryResponseDto;
import org.ideoholic.curium.model.feescategory.dto.OtherFeecategory;
import org.ideoholic.curium.model.parents.dto.Parents;
import org.ideoholic.curium.model.stampfees.dao.StampFeesDAO;
import org.ideoholic.curium.model.stampfees.dto.Academicfeesstructure;
import org.ideoholic.curium.model.stampfees.dto.Academicotherfeesstructure;
import org.ideoholic.curium.model.stampfees.dto.StampFeesDto;
import org.ideoholic.curium.model.student.dao.studentDetailsDAO;
import org.ideoholic.curium.model.student.dto.StudentIdsDto;
import org.ideoholic.curium.model.student.dto.Studentfeesstructure;
import org.ideoholic.curium.model.student.dto.Studentotherfeesstructure;
import org.ideoholic.curium.util.DataUtil;
import org.ideoholic.curium.util.DateUtil;

public class StampFeesService {

	HttpServletRequest request;
	HttpServletResponse response;
	HttpSession httpSession;
	private String CURRENTACADEMICYEAR = "currentAcademicYear";
	private String BRANCHID = "branchid";
	private String USERID = "userloginid";

	public StampFeesService(HttpServletRequest request,
			HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.httpSession = request.getSession();
	}

	public SearchStudentResponseDto advanceSearch(SearchStudentDto searchStudentDto, String branchid) {
		SearchStudentResponseDto searchStudentResponseDto = new SearchStudentResponseDto();
		List<Parents> searchStudentList = new ArrayList<Parents>();
		
		if(branchid!=null){
		
		String queryMain = "From Parents as parents where";
		String studentname = DataUtil.emptyString(searchStudentDto.getNameSearch());
		String addClass = searchStudentDto.getClassSearch();
		String addSec = searchStudentDto.getSecSearch();
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
			querySub = " parents.Student.name like '%" + studentname + "%' AND parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0 AND parents.Student.branchid="+Integer.parseInt(branchid);
		}

		if (!classStudying.equalsIgnoreCase("")
				&& !querySub.equalsIgnoreCase("")) {
			querySub = querySub + " AND parents.Student.classstudying like '"
					+ classStudying + "' AND parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0";
		} else if (!classStudying.equalsIgnoreCase("")) {
			querySub = querySub + " parents.Student.classstudying like '"
					+ classStudying + "' AND parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0 AND parents.Student.branchid="+Integer.parseInt(branchid)+" order by parents.Student.admissionnumber ASC";
		}

		if(!"".equalsIgnoreCase(querySub)) {
			queryMain = queryMain + querySub;
			searchStudentList = new studentDetailsDAO().getStudentsList(queryMain);
		}
		
	}
		searchStudentResponseDto.setSearchStudentList(searchStudentList);
		return searchStudentResponseDto;
	}
	
	public void multiClassSearch() {

		List<Parents> searchStudentList = new ArrayList<Parents>();
		
		if(httpSession.getAttribute(BRANCHID)!=null){
		
		String academicYear = request.getParameter("academicyear");
			
		String queryMain = "From Parents as parents where (parents.Student.promotedyear='"+academicYear+"' or parents.Student.yearofadmission='"+academicYear+"') AND parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0 and ";
		String studentname = DataUtil.emptyString(request.getParameter("namesearch"));
		String[] addClass = request.getParameterValues("classsearch");
		//String addSec = request.getParameter("secsearch");
		StringBuffer conClassStudying = new StringBuffer();

			int i = 0;
			for (String classOne : addClass) {
				
				if(i>0) {
					conClassStudying.append("' OR parents.Student.classstudying LIKE '"+classOne+"--"+"%");
				}else {
					conClassStudying.append(classOne+"--"+"%");
				}
				
				i++;
			}
			
		
		/*if (!addSec.equalsIgnoreCase("")) {
			//conClassStudying = addClass;
			conClassStudying = conClassStudying+"--"+addSec+"%";
		}*/

		String classStudying = DataUtil.emptyString(conClassStudying.toString());
		String querySub = "";

		if (!studentname.equalsIgnoreCase("")) {
			querySub = " parents.Student.name like '%" + studentname + "%' and parents.Student.branchid="+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString());
		}

		if (!classStudying.equalsIgnoreCase("")
				&& !querySub.equalsIgnoreCase("")) {
			querySub = querySub + " AND (parents.Student.classstudying like '"
					+ classStudying + "') AND parents.Student.branchid="+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString())+" order by parents.Student.admissionnumber ASC";
		} else if (!classStudying.equalsIgnoreCase("")) {
			querySub = querySub + " (parents.Student.classstudying like '"
					+ classStudying + "') AND parents.Student.branchid="+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString())+" order by parents.Student.admissionnumber ASC";
		}

		if(!"".equalsIgnoreCase(querySub)) {
			queryMain = queryMain + querySub;
			searchStudentList = new studentDetailsDAO().getStudentsList(queryMain);
		}
		
	}
		request.setAttribute("searchStudentList", searchStudentList);

	
	}

	public void advanceSearchByParents() {

		List<Parents> searchParentsList = new ArrayList<Parents>();
		
		if(httpSession.getAttribute(BRANCHID)!=null){
			String queryMain = "From Parents as parents where parents.branchid="+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString());
			String fathersname = DataUtil.emptyString(request
					.getParameter("fathersname"));
			String mothersname = DataUtil.emptyString(request
					.getParameter("mothersname"));

			String querySub = "";

			if (!fathersname.equalsIgnoreCase("")) {
				querySub = "AND parents.fathersname like '%" + fathersname + "%'";
			}

			if (!mothersname.equalsIgnoreCase("") && !querySub.equalsIgnoreCase("")) {
				querySub = querySub + " AND parents.mothersname like '%"
						+ mothersname + "%'";
			} else if (!mothersname.equalsIgnoreCase("")) {
				querySub = querySub + "AND parents.mothersname like '%" + mothersname
						+ "%'";
			}

			queryMain = queryMain + querySub;
			/*
			 * queryMain =
			 * "FROM Parents as parents where  parents.Student.dateofbirth = '2006-04-06'"
			 * ;
			 */
			System.out.println("SEARCH QUERY ***** " + queryMain);
			searchParentsList = new studentDetailsDAO()
					.getStudentsList(queryMain);
		}
		
		request.setAttribute("studentList", searchParentsList);

	}

	public void addFeesStamp(StampFeesDto stampFeesDto,String currentAcademicYear,String branchid,String userid ) {
		
		if(currentAcademicYear!=null){
		String[] studentIds = stampFeesDto.getStudentIds();
		if(studentIds!=null){
		Academicfeesstructure academicfessstructure = new Academicfeesstructure();
		List<Academicfeesstructure> listOfacademicfessstructure = new ArrayList<Academicfeesstructure>();
		List<Studentfeesstructure> listOfstudentfeesstructure = new ArrayList<Studentfeesstructure>();
		
		String feesTotalAmount = stampFeesDto.getFeesTotalAmount();
		Long grandTotal = 0l;

		String[] feesCategoryIds = stampFeesDto.getFeesCategoryIds();
		String[] feesAmount = stampFeesDto.getFeesAmount();
		String[] concession = stampFeesDto.getConcession();
		String[] totalInstallments = stampFeesDto.getTotalInstallments();
		String[] feesYears = stampFeesDto.getFeesYears();
		
		List<Integer> ids = new ArrayList();
		listOfacademicfessstructure.clear();
		for (String id : studentIds) {
			academicfessstructure = new Academicfeesstructure();
			academicfessstructure.setSid(Integer.valueOf(id));
			academicfessstructure.setAcademicyear(feesYears[0]);
			academicfessstructure.setUserid(Integer.parseInt(userid));
			academicfessstructure.setTotalfees(feesTotalAmount);
			grandTotal = grandTotal + Long.parseLong(academicfessstructure.getTotalfees());
			academicfessstructure.setBranchid(Integer.parseInt(branchid));
			academicfessstructure.setUserid(Integer.parseInt(userid));
			
			listOfacademicfessstructure.add(academicfessstructure);
			// ids.add(Integer.valueOf(id));

		}
		
		for (String id : studentIds) {

			for(int i=0; i < feesCategoryIds.length ; i++){
			String[] feesCatAndIndex =  feesCategoryIds[i].split("_");
			int feesCatIndex = Integer.parseInt(feesCatAndIndex[1]);
			Studentfeesstructure studentfeesstructure = new Studentfeesstructure();   
			Feescategory feescategory = new Feescategory();
			studentfeesstructure.setSid(Integer.valueOf(id));
			feescategory.setIdfeescategory(Integer.parseInt(feesCatAndIndex[0]));
			studentfeesstructure.setFeescategory(feescategory);
			studentfeesstructure.setFeesamount(Long.parseLong(feesAmount[feesCatIndex]));
			studentfeesstructure.setFeespaid((long) 0);
			studentfeesstructure.setWaiveoff((long) 0);
			studentfeesstructure.setTotalinstallment(Integer.parseInt(totalInstallments[feesCatIndex]));
			studentfeesstructure.setAcademicyear(feesYears[feesCatIndex]);
			studentfeesstructure.setBranchid(Integer.parseInt(branchid));
			studentfeesstructure.setUserid(Integer.parseInt(userid));
			studentfeesstructure.setConcession(Integer.parseInt(concession[feesCatIndex]));
			listOfstudentfeesstructure.add(studentfeesstructure);
		}
			

			
		}
		
		//Accounts
		//Pass J.V. : credit the Fees as income & debit the cash
		
		int crFees = getLedgerAccountId("unearnedstudentfeesincome"+Integer.parseInt(branchid));
		int drAccount = getLedgerAccountId("studentfeesreceivable"+Integer.parseInt(branchid));;
		
		VoucherEntrytransactions transactions = new VoucherEntrytransactions();
		
		transactions.setDraccountid(drAccount);
		transactions.setCraccountid(crFees);
		transactions.setDramount(new BigDecimal(grandTotal));
		transactions.setCramount(new BigDecimal(grandTotal));
		transactions.setVouchertype(4);
		transactions.setTransactiondate(DateUtil.todaysDate());
		transactions.setEntrydate(DateUtil.todaysDate());
		transactions.setNarration("Towards Fees Stamp");
		transactions.setCancelvoucher("no");
		transactions.setFinancialyear(new AccountDAO().getCurrentFinancialYear(Integer.parseInt(branchid)).getFinancialid());
		transactions.setBranchid(Integer.parseInt(branchid));
		transactions.setUserid(Integer.parseInt(userid));
		
		String updateDrAccount="update Accountdetailsbalance set currentbalance=currentbalance+"+grandTotal+" where accountdetailsid="+drAccount;

		String updateCrAccount="update Accountdetailsbalance set currentbalance=currentbalance+"+grandTotal+" where accountdetailsid="+crFees;
		
		// End J.V
		new StampFeesDAO().addStampFees(listOfacademicfessstructure,currentAcademicYear,listOfstudentfeesstructure,transactions,updateDrAccount,updateCrAccount);
		//new studentDetailsDAO().addStudentfeesstructure(listOfstudentfeesstructure,httpSession.getAttribute(CURRENTACADEMICYEAR).toString());

		}
		}
	}

	private int getLedgerAccountId(String itemAccount) {
		
		int result = 0;
	 	
	 	Properties properties = new Properties();
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("Util.properties");
		
        		try {
					properties.load(inputStream);
				} catch (IOException e) {
					e.printStackTrace();
				}
		    
        		String ItemLedgerId = properties.getProperty(itemAccount);
		    
		    if(ItemLedgerId!=null) {
		    	result = Integer.parseInt(ItemLedgerId);
		    }else {
		    	String ItemLedger = properties.getProperty(itemAccount.toLowerCase());
		    	result = Integer.parseInt(ItemLedger.toLowerCase());
		    }
		    
		    return result;
	}

	public void deleteFeesStamp(StudentIdsDto studentIdsDto) {
		String currentYear = studentIdsDto.getCurrentYear();
		String[] studentIds = studentIdsDto.getStudentIds();
		if(studentIds!=null){
			List<Integer> ids = new ArrayList();
	        for (String id : studentIds) {
	            System.out.println("id" + id);
	            ids.add(Integer.valueOf(id));
	            
	        }
	        System.out.println("id length" + studentIds.length);
	        new StampFeesDAO().deleteMultiple(ids,currentYear);
		
	}
		}
	
	public void addotherFeesStamp(StampFeesDto stampFeesDto,String currentAcademicYear,String branchid,String userid ) {

		if(currentAcademicYear!=null){
		String[] studentIds = stampFeesDto.getStudentIds();
		if(studentIds!=null){
			Academicotherfeesstructure academicfessstructure = new Academicotherfeesstructure();
		List<Academicotherfeesstructure> listOfacademicfessstructure = new ArrayList<Academicotherfeesstructure>();
		List<Studentotherfeesstructure> listOfstudentfeesstructure = new ArrayList<Studentotherfeesstructure>();

		String feesTotalAmount = stampFeesDto.getFeesTotalAmount();
		Long grandTotal = 0l;

		String[] feesCategoryIds = stampFeesDto.getFeesCategoryIds();
		String[] feesAmount = stampFeesDto.getFeesAmount();
		String[] concession = stampFeesDto.getConcession();
		String[] totalInstallments = stampFeesDto.getTotalInstallments();
		String[] feesYears = stampFeesDto.getFeesYears();
		
		List<Integer> ids = new ArrayList();
		listOfacademicfessstructure.clear();
		for (String id : studentIds) {
			academicfessstructure = new Academicotherfeesstructure();
			academicfessstructure.setSid(Integer.valueOf(id));
			academicfessstructure.setAcademicyear(feesYears[0]);
			academicfessstructure.setUserid(Integer.parseInt(userid));
			academicfessstructure.setTotalfees(feesTotalAmount);
			grandTotal = grandTotal + Long.parseLong(academicfessstructure.getTotalfees());
			academicfessstructure.setBranchid(Integer.parseInt(branchid));
			academicfessstructure.setUserid(Integer.parseInt(userid));

			listOfacademicfessstructure.add(academicfessstructure);
			// ids.add(Integer.valueOf(id));

		}

		for (String id : studentIds) {

			for(int i=0; i < feesCategoryIds.length ; i++){

			Studentotherfeesstructure studentfeesstructure = new Studentotherfeesstructure();   
			OtherFeecategory feescategory = new OtherFeecategory();
			studentfeesstructure.setSid(Integer.valueOf(id));
			feescategory.setIdfeescategory(Integer.parseInt(feesCategoryIds[i]));
			studentfeesstructure.setOtherfeescategory(feescategory);
			studentfeesstructure.setFeesamount(Long.parseLong(feesAmount[i]));
			studentfeesstructure.setFeespaid((long) 0);
			studentfeesstructure.setWaiveoff((long) 0);
			studentfeesstructure.setTotalinstallment(Integer.parseInt(totalInstallments[i]));
			studentfeesstructure.setAcademicyear(feesYears[i]);
			studentfeesstructure.setBranchid(Integer.parseInt(branchid));
			studentfeesstructure.setUserid(Integer.parseInt(userid));
			studentfeesstructure.setConcession(Integer.parseInt(concession[i]));
			listOfstudentfeesstructure.add(studentfeesstructure);
		}



		}

		new StampFeesDAO().addotherStampFees(listOfacademicfessstructure,currentAcademicYear,listOfstudentfeesstructure);
		//new studentDetailsDAO().addStudentfeesstructure(listOfstudentfeesstructure,httpSession.getAttribute(CURRENTACADEMICYEAR).toString());

		}
		}
	}
	
	public SearchStudentResponseDto otheradvanceSearch(SearchStudentDto searchStudentDto,String branchid) {
		SearchStudentResponseDto searchStudentResponseDto = new SearchStudentResponseDto();
		List<Parents> searchStudentList = new ArrayList<Parents>();

		if(httpSession.getAttribute(BRANCHID)!=null){

		String queryMain = "From Parents as parents where";
		String studentname = DataUtil.emptyString(searchStudentDto.getNameSearch());
		String addClass = searchStudentDto.getClassSearch();
		String addSec = searchStudentDto.getSecSearch();
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
			querySub = " parents.Student.name like '%" + studentname + "%' AND parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0 AND parents.Student.branchid="+Integer.parseInt(branchid);
		}

		if (!classStudying.equalsIgnoreCase("")
				&& !querySub.equalsIgnoreCase("")) {
			querySub = querySub + " AND parents.Student.classstudying like '"
					+ classStudying + "' AND parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0";
		} else if (!classStudying.equalsIgnoreCase("")) {
			querySub = querySub + " parents.Student.classstudying like '"
					+ classStudying + "' AND parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0 AND parents.Student.branchid="+Integer.parseInt(branchid)+" order by parents.Student.admissionnumber ASC";
		}

		if(!"".equalsIgnoreCase(querySub)) {
			queryMain = queryMain + querySub;
			searchStudentList = new studentDetailsDAO().getStudentsList(queryMain);
		}

	}
		searchStudentResponseDto.setSearchStudentList(searchStudentList);
		return searchStudentResponseDto;
	}

	public FeescategoryResponseDto advanceSearchForStampFees(SearchStudentDto searchStudentDto,String branchid,String currentAcademicYear){
		
		FeescategoryResponseDto feescategoryResponseDto = new FeescategoryResponseDto();

        if(branchid!=null){
        	String className = searchStudentDto.getClassSearch();
        	
            List<Feescategory> feecategoryList= new feesCategoryDAO().getfeecategoryofstudent(className,currentAcademicYear);
            feescategoryResponseDto.setFeescategory(feecategoryList);
  		
    		
    		
    		// Get Student Details
    		
    		List<Parents> searchStudentList = new ArrayList<Parents>();
    		
    		if(branchid!=null){
    		
    		String queryMain = "From Parents as parents where";
    		String studentname = DataUtil.emptyString(searchStudentDto.getNameSearch());
    		String addClass = searchStudentDto.getClassSearch();
    		String addSec = searchStudentDto.getSecSearch();
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
    			querySub = " parents.Student.name like '%" + studentname + "%' AND parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0 AND parents.Student.branchid="+Integer.parseInt(branchid);
    		}

    		if (!classStudying.equalsIgnoreCase("")
    				&& !querySub.equalsIgnoreCase("")) {
    			querySub = querySub + " AND parents.Student.classstudying like '"
    					+ classStudying + "' AND parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0";
    		} else if (!classStudying.equalsIgnoreCase("")) {
    			querySub = querySub + " parents.Student.classstudying like '"
    					+ classStudying + "' AND parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0 AND parents.Student.branchid="+Integer.parseInt(branchid)+" order by parents.Student.admissionnumber ASC";
    		}

    		if(!"".equalsIgnoreCase(querySub)) {
    			queryMain = queryMain + querySub;
    			searchStudentList = new studentDetailsDAO().getStudentsList(queryMain);
    		}
    		
    	}
    		feescategoryResponseDto.setSearchStudentList(searchStudentList);


        }
        return feescategoryResponseDto;
	}

}

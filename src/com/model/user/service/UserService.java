package com.model.user.service;

import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.academicyear.dao.YearDAO;
import com.model.academicyear.dto.Currentacademicyear;
import com.model.adminexpenses.service.AdminService;
import com.model.branch.dto.Branch;
import com.model.employee.dao.EmployeeDAO;
import com.model.employee.dto.Teacher;
import com.model.feescollection.action.FeesCollectionAction;
import com.model.feescollection.dto.Receiptinfo;
import com.model.feescollection.service.FeesCollectionService;
import com.model.parents.dto.Parents;
import com.model.std.dao.StandardDetailsDAO;
import com.model.std.dto.Classsec;
import com.model.std.service.StandardService;
import com.model.student.dao.studentDetailsDAO;
import com.model.user.dao.UserDAO;
import com.model.user.dto.Login;
import com.util.DataUtil;

public class UserService {
	
	 HttpServletRequest request;
	    HttpServletResponse response;
	    HttpSession httpSession;
	    private Login login;
	    private String BRANCHID = "branchid";

	public UserService(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
        this.response = response;
        this.httpSession = request.getSession();
	}

	public boolean authenticateUser() {
        boolean result;
       String userName = request.getParameter("loginName");
       String password = request.getParameter("password");

       login = new UserDAO().readUniqueObject(userName, password);

       if (login != null) {
            Currentacademicyear currentAcademicYear = new YearDAO().showYear();
            String academicyear = "";
            if(currentAcademicYear!=null){
            academicyear = currentAcademicYear.getCurrentacademicyear();
            }
            httpSession.setAttribute("currentAcademicYear",academicyear);
            httpSession.setAttribute("username",login.getUsername());
            httpSession.setAttribute("branchid",login.getBranch().getIdbranch());
            httpSession.setAttribute("branchname",login.getBranch().getBranchname());
            String[] userType = login.getUsertype().split("-");
            httpSession.setAttribute("userType", userType[0]);
            httpSession.setAttribute("typeOfUser",userType[0]);
            httpSession.setAttribute("userAuth", userType[0]);
            httpSession.setAttribute("userloginid", login.getUserid());
			//setting session to expiry in 60 mins
           	httpSession.setMaxInactiveInterval(60*60);
			Cookie cookie = new Cookie("user",  login.getUsertype());
			cookie.setMaxAge(30*60);
			response.addCookie(cookie);
           result = true;
       } else {
           result = false;
       }
       return result;
   }

	public void logOutUser() {
		httpSession.invalidate();
        new UserDAO().sessionClose();
		
	}

	public void dashBoard() {
		
		if(httpSession.getAttribute(BRANCHID)!=null){
			
			//List<Branch> branchList = new BranchDAO().readListOfObjects();
            List<Classsec> classsecList = new StandardDetailsDAO().viewClasses(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
            List<String> xaxisList = new LinkedList<String>() ;
            List<String> yaxisList = new LinkedList<String>() ;
            int totalStudents = 0;
            // int[] test = new int[branchList.size()] ;
            for (Classsec classstudying : classsecList) {
        	
		        	String classStudying = classstudying.getClassdetails();
		    		
		    		if (!classStudying.equalsIgnoreCase("")) {
		    			classStudying = classStudying+"--" +"%";
		    		}
		    		
                    List<Parents> student = new studentDetailsDAO().getStudentsList("FROM Parents as parents where parents.Student.classstudying like '"+classStudying+"'"
                    		+ " AND parents.Student.archive=0 AND parents.Student.passedout=0 AND parents.Student.droppedout=0 AND parents.Student.leftout=0");
                    totalStudents+=student.size();
                    xaxisList.add("\""+classstudying.getClassdetails()+"\"");
                    if(student.size()>0) {
                        String studentCount = Integer.toString(student.size());
                        yaxisList.add("\""+studentCount+"\"");
                    }else {
                        yaxisList.add("\""+0+"\"");
                    }
                    
                	}
        
        	// Total Teachers
        	List<Teacher> teacher = new EmployeeDAO().readCurrentTeachers();
        	request.setAttribute("totalteachers", teacher.size());
        	// End Total Teachers
        	
        	//Fees Details
        	new FeesCollectionService(request, response).getFeesDetailsDashBoard();
        	//End Fees Details
        	
        	//Daily Expenses
        		new AdminService(request, response).dailyExpenses();
        		
        	//Monthly Expenses
        		new AdminService(request, response).getMonthlyExpenses();
        		
        	//Get Boys & Girls
        		new AdminService(request, response).getTotalBoysGirls();
        		
        	
        request.setAttribute("studentxaxis", xaxisList);
        request.setAttribute("studentyaxis", yaxisList);
        request.setAttribute("totalstudents", totalStudents);
        feesdailysearch();
		feesmonthlysearch();
		}
		
	}
	
	public void feesdailysearch() {
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date newdate = new Date();
		Calendar startCalendar = new GregorianCalendar();
		startCalendar.setTime(newdate);
		String todaysDate = df.format(newdate);
		List<Receiptinfo> feesDetailsList = new ArrayList<Receiptinfo>();
		Date dateBefore = null;
		Date dateAfter = null;
		
		String queryMain = "From Receiptinfo as feesdetails where feesdetails.branchid = "+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString())+" and feesdetails.cancelreceipt=0 and";
		
		try {
			dateBefore = df.parse(todaysDate);
			dateAfter = df.parse(todaysDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Calendar start = Calendar.getInstance();
		start.setTime(dateBefore);
		Calendar end = Calendar.getInstance();
		end.setTime(dateAfter);
		start.set(Calendar.DAY_OF_MONTH, start.getActualMinimum(Calendar.DAY_OF_MONTH));
		end.set(Calendar.DAY_OF_MONTH, end.getActualMaximum(Calendar.DAY_OF_MONTH));
		end.add(Calendar.DATE, 1);
		
		List<String> dailyDatesList = new LinkedList<String>();
		List<String> totalFeesSum = new LinkedList<String>();
		
		for (Date date = start.getTime(); start.before(end); start.add(Calendar.DAY_OF_MONTH,+1), date = start.getTime()) {
			todaysDate = new SimpleDateFormat("YYYY-MM-dd").format(date);
			String querySub = "";
			querySub = " feesdetails.date = '" + todaysDate + "'";
			feesDetailsList = new UserDAO().getReceiptDetailsList(queryMain + querySub);
			BigDecimal sumOfFees = BigDecimal.ZERO;
			for (Receiptinfo receiptinfo : feesDetailsList) {
				BigDecimal fee = new BigDecimal(receiptinfo.getTotalamount());
				sumOfFees = sumOfFees.add(fee);
			}
			totalFeesSum.add("\"" + sumOfFees + "\"");
			dailyDatesList.add("\"" + todaysDate + "\"");
		}
		request.setAttribute("studenttotalfees", totalFeesSum);
		request.setAttribute("currentdate", dailyDatesList);
	}

	public void feesmonthlysearch() {
		
		List<String> monthList = new LinkedList<String>();
		List<String> totalFeesSum = new LinkedList<String>();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date newdate = new Date();
		String todaysDate = df.format(newdate);
		List<Receiptinfo> feesDetailsList = new ArrayList<Receiptinfo>();
		Date dateBefore = null;
		Date dateAfter = null;
		String queryMain = "From Receiptinfo as feesdetails where feesdetails.branchid = "+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString())+" and feesdetails.cancelreceipt=0 and ";
		String toDate = DataUtil.emptyString(request.getParameter("todate"));
		String fromDate = DataUtil.emptyString(request.getParameter("fromdate"));
		
		try {
			dateBefore = df.parse(todaysDate);
			dateAfter = df.parse(todaysDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Calendar start1 = Calendar.getInstance();
		start1.setTime(dateBefore);
		Calendar end1 = Calendar.getInstance();
		end1.setTime(dateAfter);
		start1.set(Calendar.MONTH, start1.getActualMinimum(Calendar.MONTH));
		start1.set(Calendar.DAY_OF_MONTH, start1.getActualMinimum(Calendar.DAY_OF_MONTH));
		end1.set(Calendar.MONTH, end1.getActualMaximum(Calendar.MONTH));
		end1.add(Calendar.DAY_OF_MONTH, 1);
		
		for (Date date = start1.getTime(); start1.before(end1); start1.add(Calendar.MONTH,+1), date = start1.getTime()) {
			fromDate = new SimpleDateFormat("YYYY-MM-dd").format(date);
			Calendar endday = Calendar.getInstance();
			endday.setTime(date);
			endday.set(Calendar.DAY_OF_MONTH, start1.getActualMaximum(Calendar.DAY_OF_MONTH));
			Date enddayofmonth = endday.getTime();
			toDate = new SimpleDateFormat("YYYY-MM-dd").format(enddayofmonth);
			String querySub = "";
			querySub = " feesdetails.date between '" + fromDate + "' AND '" + toDate + "'";
			feesDetailsList = new UserDAO().getReceiptDetailsList(queryMain + querySub);
			BigDecimal sumOfFees = BigDecimal.ZERO;
			for (Receiptinfo receiptinfo : feesDetailsList) {
				BigDecimal fee = new BigDecimal(receiptinfo.getTotalamount());
				sumOfFees = sumOfFees.add(fee);
			}
			totalFeesSum.add("\"" + sumOfFees + "\"");
			//Date Format
			SimpleDateFormat month_date = new SimpleDateFormat("MMM yyyy", Locale.ENGLISH);
			String monthYear = month_date.format(date);
			
			monthList.add("\"" + monthYear + "\"");
		}
		
		request.setAttribute("monthlystudentsfees", totalFeesSum);
		request.setAttribute("monthlist", monthList);
	}
	

	public void advanceSearch() {
		
		List<Parents> searchStudentList = new ArrayList<Parents>();
		
		if(httpSession.getAttribute(BRANCHID)!=null){
		String queryMain ="From Parents as parents where parents.branchid="+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString())+" AND";
		String studentname= DataUtil.emptyString(request.getParameter("name"));
		String gender = DataUtil.emptyString(request.getParameter("gender"));
		String dateOfBirth = DataUtil.emptyString(request.getParameter("dateofbirth"));
		int age = DataUtil.parseInt(request.getParameter("age"));
		
		String addClass = request.getParameter("addclass");
		String addSec = request.getParameter("addsec");
		String conClassStudying = "";
		
		
		if (!addClass.equalsIgnoreCase("")) {

			conClassStudying = addClass+"--" +"%";

		}
		if (!addSec.equalsIgnoreCase("")) {
			conClassStudying = addClass;
			conClassStudying = conClassStudying+"--"+addSec+"%";
		}
			
		
			
			String classStudying = DataUtil.emptyString(conClassStudying);
			
		
			String addClassE = request.getParameter("admclassE");
			String addSecE = request.getParameter("admsecE");
			String conClassAdmittedIn = "";
			
			if (!addClassE.equalsIgnoreCase("")) {

				conClassAdmittedIn = addClassE+"--" +"%";

			}
			if (!addSecE.equalsIgnoreCase("")) {
				conClassAdmittedIn = addClassE;
				conClassAdmittedIn = conClassAdmittedIn+addSecE;
			}
			
			String classAdmitted = DataUtil.emptyString(conClassAdmittedIn);
			String lastClass = DataUtil.emptyString(request.getParameter("lastclass"));
			String lastSchool =  DataUtil.emptyString(request.getParameter("lastschool"));
			String admissionNo =  DataUtil.emptyString(request.getParameter("admnno"));
			String dateOfAdmission =  DataUtil.emptyString(request.getParameter("dateofadmission"));
			String bloodGroup =  DataUtil.emptyString(request.getParameter("bloodgroup"));
			String nationality =  DataUtil.emptyString(request.getParameter("nationality"));
			String religion =  DataUtil.emptyString(request.getParameter("religion"));
			String caste =  DataUtil.emptyString(request.getParameter("caste"));
			String motherT =  DataUtil.emptyString(request.getParameter("motherT"));
			String createdDate =  DataUtil.emptyString(request.getParameter("createddate"));
			String remarks =  DataUtil.emptyString(request.getParameter("remarks"));
			String stsNumber = DataUtil.emptyString(request.getParameter("sts"));
			String rte = DataUtil.emptyString(request.getParameter("rte"));
			
			String querySub = "";
			
			if(!studentname.equalsIgnoreCase("")){
				querySub = " parents.Student.name like '%"+studentname+"%'" ;
			}
			
			if(!classStudying.equalsIgnoreCase("")  &&  !querySub.equalsIgnoreCase("") ){
				querySub = querySub + " parents.Student.classstudying like '"+classStudying+"'";
			}else if(!classStudying.equalsIgnoreCase("")){
				querySub = querySub + " parents.Student.classstudying like '"+classStudying+"'";
			}
			
			if(!gender.equalsIgnoreCase("")  &&  !querySub.equalsIgnoreCase("") ){
				querySub = querySub + " parents.Student.gender like '%"+gender+"%'";
			}else if(!gender.equalsIgnoreCase("")){
				querySub = querySub + " parents.Student.gender like '%"+gender+"%'";
			}
			
			if(!lastClass.equalsIgnoreCase("")  &&  !querySub.equalsIgnoreCase("") ){
				querySub = querySub + " parents.Student.stdlaststudied = '"+lastClass+"'";
			}else if(!lastClass.equalsIgnoreCase("")){
				querySub = querySub + " parents.Student.stdlaststudied = '"+lastClass+"'";
			}
			
			if(!dateOfBirth.equalsIgnoreCase("") &&  !querySub.equalsIgnoreCase("") ){
				querySub = querySub + " parents.Student.dateofbirth = '"+dateOfBirth+"'";
			}else if(!dateOfBirth.equalsIgnoreCase("")){
				querySub = querySub + " parents.Student.dateofbirth = '"+dateOfBirth+"'";
			}
			
			if(age != 0  &&  !querySub.equalsIgnoreCase("") ){
				querySub = querySub + " parents.Student.age = '"+age+"'";
			}else if(age != 0){
				querySub = querySub + " parents.Student.age = '"+age+"'";
			}
			
			if(!classAdmitted.equalsIgnoreCase("")  &&  !querySub.equalsIgnoreCase("") ){
				querySub = querySub + " parents.Student.classadmittedin like '"+classAdmitted+"'";
			}else if(!classAdmitted.equalsIgnoreCase("")){
				querySub = querySub + " parents.Student.classadmittedin like '"+classAdmitted+"'";
			}
			
			if(!lastSchool.equalsIgnoreCase("")  &&  !querySub.equalsIgnoreCase("") ){
				querySub = querySub + " parents.Student.schoollastattended like '%"+lastSchool+"%'";
			}else if(!lastSchool.equalsIgnoreCase("")){
				querySub = querySub + " parents.Student.schoollastattended like '%"+lastSchool+"%'";
			}
			
			
			if(!admissionNo.equalsIgnoreCase("")  &&  !querySub.equalsIgnoreCase("") ){
				querySub = querySub + " parents.Student.admissionnumber like '%"+admissionNo+"%'";
			}else if(!admissionNo.equalsIgnoreCase("")){
				querySub = querySub + " parents.Student.admissionnumber like '%"+admissionNo+"%'";
			}
			
			if(!dateOfAdmission.equalsIgnoreCase("")  &&  !querySub.equalsIgnoreCase("") ){
				querySub = querySub + " parents.Student.admissiondate = '"+dateOfAdmission+"'";
			}else if(!dateOfAdmission.equalsIgnoreCase("")){
				querySub = querySub + " parents.Student.admissiondate = '"+dateOfAdmission+"'";
			}
			
			if(!bloodGroup.equalsIgnoreCase("")  &&  !querySub.equalsIgnoreCase("") ){
				querySub = querySub + " parents.Student.bloodgroup like '%"+bloodGroup+"%'";
			}else if(!bloodGroup.equalsIgnoreCase("")){
				querySub = querySub + " parents.Student.bloodgroup like '%"+bloodGroup+"%'";
			}
			
			if(!nationality.equalsIgnoreCase("")  &&  !querySub.equalsIgnoreCase("") ){
				querySub = querySub + " parents.Student.nationality like '%"+nationality+"%'";
			}else if(!nationality.equalsIgnoreCase("")){
				querySub = querySub + " parents.Student.nationality like '%"+nationality+"%'";
			}
			
			if(!religion.equalsIgnoreCase("")  &&  !querySub.equalsIgnoreCase("") ){
				querySub = querySub + " parents.Student.religion like '%"+religion+"%'";
			}else if(!religion.equalsIgnoreCase("")){
				querySub = querySub + " parents.Student.religion like '%"+religion+"%'";
			}
			
			if(!caste.equalsIgnoreCase("")  &&  !querySub.equalsIgnoreCase("") ){
				querySub = querySub + " parents.Student.caste like '%"+caste+"%'";
			}else if(!caste.equalsIgnoreCase("")){
				querySub = querySub + " parents.Student.caste like '%"+caste+"%'";
			}
			
			if(!motherT.equalsIgnoreCase("")  &&  !querySub.equalsIgnoreCase("") ){
				querySub = querySub + " parents.Student.mothertongue like '%"+motherT+"%'";
			}else if(!motherT.equalsIgnoreCase("")){
				querySub = querySub + " parents.Student.mothertongue like '%"+motherT+"%'";
			}
			
			if(!createdDate.equalsIgnoreCase("")  &&  !querySub.equalsIgnoreCase("") ){
				querySub = querySub + " parents.Student.createddate = '"+createdDate+"'";
			}else if(!createdDate.equalsIgnoreCase("")){
				querySub = querySub + " parents.Student.createddate = '"+createdDate+"'";
			} 
			
			if(!remarks.equalsIgnoreCase("")  &&  !querySub.equalsIgnoreCase("") ){
				querySub = querySub + " parents.Student.remarks like '%"+remarks+"%'";
			}else if(!remarks.equalsIgnoreCase("")){
				querySub = querySub + " parents.Student.remarks like '%"+remarks+"%'";
			}
			
			if(!stsNumber.equalsIgnoreCase("")  &&  !querySub.equalsIgnoreCase("") ){
				querySub = querySub + " parents.Student.sts = '"+stsNumber+"'";
			}else if(!stsNumber.equalsIgnoreCase("")){
				querySub = querySub + " parents.Student.sts = '"+stsNumber+"'";
			}
			
			if(!rte.equalsIgnoreCase("")  &&  !querySub.equalsIgnoreCase("") ){
				querySub = querySub + " parents.Student.rte = '"+rte+"'";
			}else if(!rte.equalsIgnoreCase("")){
				querySub = querySub + " parents.Student.rte = '"+rte+"'";
			}
			
			queryMain = queryMain+querySub+" AND parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0";
			searchStudentList = new studentDetailsDAO().getStudentsList(queryMain);
	}
			
			request.setAttribute("searchStudentList", searchStudentList);
		
		
	}

	public boolean backupData(String fileName) {
        
        boolean result = false;
        try {
            Properties properties = new Properties();
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("Util.properties");
            properties.load(inputStream);
            String backupDirectoryIS = properties.getProperty("backupdirectory");
            System.out.println("the backup directory from input stream is " + backupDirectoryIS);


            int processComplete; // to verify that either process completed or not
            String sqlExtension = ".sql";
            String backupLocation = backupDirectoryIS + fileName + sqlExtension;
            String mysqlPath = properties.getProperty("mysqlpath");
            System.out.println("the back up for  the backuplocation " + backupLocation);
            request.setAttribute("Backuplocation", backupLocation);
            Process runtimeProcess = Runtime.getRuntime().exec(mysqlPath + backupLocation);


            // call the mysqldump in terminal and execute it

            processComplete = runtimeProcess.waitFor();//store the state in variable

            if (processComplete == 1) {//if values equal 1 process failed
                System.out.println("FAILED");
                result = false;

            } else if (processComplete == 0) {//if values equal 0 process failed
                System.out.println("success");

                //display message
                result = true;

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

	public boolean ChangePassword() {
		
		
        boolean result;
        Login login = new Login();
        String currentPassword = request.getParameter("currentpassword");
        String newPassword = request.getParameter("newpassword");
        String ConfirmNewPassword = request.getParameter("confirmpassword");
        
        login = new UserDAO().readPassword(currentPassword);
        
        if (login != null && newPassword.equals(ConfirmNewPassword)) {
            login.setPassword(newPassword);  
            login = new UserDAO().update(login);
            result = true;
        } else {
            result = false;
        }
        return result;
    }

	public void advanceSearchByParents() {
		
		List<Parents> searchParentsList = new ArrayList<Parents>();
		
		if(httpSession.getAttribute(BRANCHID)!=null){
			
		String queryMain ="From Parents as parents where parents.branchid="+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString())+" AND";
		String fathersname= DataUtil.emptyString(request.getParameter("fathersname"));
		String mothersname = DataUtil.emptyString(request.getParameter("mothersname"));
		
		
			String querySub = "";
			
			if(!fathersname.equalsIgnoreCase("")){
				querySub = " parents.fathersname like '%"+fathersname+"%'" ;
			}
			
			if(!mothersname.equalsIgnoreCase("")  &&  !querySub.equalsIgnoreCase("") ){
				querySub = querySub + " AND parents.mothersname like '%"+mothersname+"%'";
			}else if(!mothersname.equalsIgnoreCase("")){
				querySub = querySub + " parents.mothersname like '%"+mothersname+"%'";
			}
			
			
			
			queryMain = queryMain+querySub;
			/*queryMain = "FROM Parents as parents where  parents.Student.dateofbirth = '2006-04-06'"; */
			System.out.println("SEARCH QUERY ***** "+queryMain);
			searchParentsList = new studentDetailsDAO().getStudentsList(queryMain);
			
		}
			request.setAttribute("studentList", searchParentsList);
		
		
	}

	public void searchByDate() {
		 
		List<Receiptinfo> feesDetailsList = new ArrayList<Receiptinfo>();
		String branchId = request.getParameter("selectedbranchid");
		int idBranch = 0;
                
		if(httpSession.getAttribute(BRANCHID)!=null){
		

	        if(branchId!=null) {
	        	String[] branchIdName = branchId.split(":");
	        	idBranch = Integer.parseInt(branchIdName[0]);
	        	httpSession.setAttribute("feesdetailsbranchname", branchIdName[1]);
	        	httpSession.setAttribute("branchname", "Branch Name:");
	        }else {
	        	idBranch = Integer.parseInt(httpSession.getAttribute(BRANCHID).toString());
	        }
	        
		String queryMain ="From Receiptinfo as feesdetails where feesdetails.cancelreceipt=0 and feesdetails.branchid="+idBranch+" AND";
		String toDate= DataUtil.emptyString(request.getParameter("todate"));
		String fromDate = DataUtil.emptyString(request.getParameter("fromdate"));
		String oneDay = DataUtil.emptyString(request.getParameter("oneday"));
		
		
			String querySub = "";
			
			if(!oneDay.equalsIgnoreCase("")){
				querySub = " feesdetails.date = '"+oneDay+"'" ;
				 httpSession.setAttribute("dayone", oneDay);
				 httpSession.setAttribute("datefrom", "");
				 httpSession.setAttribute("dateto", "");
			}else if(!"".equalsIgnoreCase(DataUtil.emptyString((String) httpSession.getAttribute("dayone")))) {
				querySub = " feesdetails.date = '"+(String) httpSession.getAttribute("dayone")+"'" ;
			}
			
			if(!fromDate.equalsIgnoreCase("")  && !toDate.equalsIgnoreCase("")){
				querySub = " feesdetails.date between '"+fromDate+"' AND '"+toDate+"'";
				httpSession.setAttribute("datefrom", fromDate);
				httpSession.setAttribute("dateto", toDate);
				httpSession.setAttribute("dayone", "");
			}else if(!"".equalsIgnoreCase(DataUtil.emptyString((String) httpSession.getAttribute("datefrom"))) && 
					!"".equalsIgnoreCase(DataUtil.emptyString((String) httpSession.getAttribute("dateto"))) ) {
				querySub = " feesdetails.date between '"+(String) httpSession.getAttribute("datefrom")+"' AND '"+(String) httpSession.getAttribute("dateto")+"'";
			}
			
			queryMain = queryMain+querySub;
			/*queryMain = "FROM Parents as parents where  parents.Student.dateofbirth = '2006-04-06'"; */
			System.out.println("SEARCH QUERY ***** "+queryMain);
			feesDetailsList = new UserDAO().getReceiptDetailsList(queryMain);
			
	}
			long sumOfFees = 0l;
			for (Receiptinfo receiptinfo : feesDetailsList) {
				sumOfFees = sumOfFees + receiptinfo.getTotalamount();
			}
			
			httpSession.setAttribute("searchfeesdetailslist", feesDetailsList);
			httpSession.setAttribute("sumofdetailsfees", sumOfFees);
	}

	public boolean addUser(Teacher employee) {
		
		Login user = new Login();
		user.setUsername(employee.getTeacherexternalid());
		final String ALPHA_NUMERIC_STRING = "RSTUABCDJKL6789MNOPQRSTUVWXYZ012345EFGHI";
		int count =4;
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
		int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
		builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		user.setPassword(builder.toString());
		user.setUsertype("staff");
		Branch branch = new Branch();
		branch.setIdbranch(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
		user.setBranch(branch);
		
		return new UserDAO().addUser(user);
		
	}

}

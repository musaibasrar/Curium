package com.model.user.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.academicyear.dao.YearDAO;
import com.model.academicyear.dto.Currentacademicyear;
import com.model.branch.dao.BranchDAO;
import com.model.branch.dto.Branch;
import com.model.branch.service.BranchService;
import com.model.employee.dao.EmployeeDAO;
import com.model.employee.dto.Teacher;
import com.model.feescollection.dto.Receiptinfo;
import com.model.order.dao.OrderDAO;
import com.model.order.dto.Books;
import com.model.order.service.OrderService;
import com.model.parents.dto.Parents;
import com.model.qualification.dao.QualificationDAO;
import com.model.qualification.dto.Qualification;
import com.model.student.dao.studentDetailsDAO;
import com.model.student.service.StudentService;
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
            request.setAttribute("userType", login.getUsertype());
            httpSession.setAttribute("typeOfUser",login.getUsertype());
            httpSession.setAttribute("userAuth", login.getUsertype());
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
		
		        List<Branch> branchList = new BranchDAO().readListOfObjects();
		        List<String> xaxisList = new LinkedList<String>() ;
		        List<String> yaxisList = new LinkedList<String>() ;
		       // int[] test = new int[branchList.size()] ;
		        for (Branch branch : branchList) {
                            List<Parents> student = new studentDetailsDAO().getStudentsList("FROM Parents as parents where parents.Student.branchid="+branch.getIdbranch());
                            xaxisList.add("\"" + branch.getCentername() + "\"");
                            if(student!=null) {
                                String studentCount = Integer.toString(student.size());
                                yaxisList.add("\"" + studentCount + "\"");
                            }else {
                                yaxisList.add("\"" + 0 + "\"");
                            }
                            
                        }
		        request.setAttribute("studentxaxis", xaxisList);
		        request.setAttribute("studentyaxis", yaxisList);
		        
		        //Books
		        List<Books> booksList = new OrderDAO().readBooks();
		        List<String> booksName = new LinkedList<String>();
		        List<String> quantity = new LinkedList<String>();
		        
		        for (Books book : booksList) {
                            booksName.add("\"" + book.getTitle() + "\"");
                            quantity.add("\"" + Integer.toString(book.getQuantity()) + "\"");
                        }
		        
		        request.setAttribute("booksxaxis", booksName);
                        request.setAttribute("booksyaxis", quantity);
                        
                        //Qualification
                        List<Qualification> qualificationList = new QualificationDAO().readListOfObjects();
                        List<String> qualificationName = new LinkedList<String>();
                        List<String> qualificationquantity = new LinkedList<String>();
                        
                        for (Qualification qualification : qualificationList) {
                            List<Parents> student = new studentDetailsDAO().getStudentsList("FROM Parents as parents where parents.Student.qualification ='"+qualification.getQualification()+"'");
                            qualificationName.add("\"" + qualification.getQualification() + "\"");
                            qualificationquantity.add("\"" + student.size() + "\"");
                        }
                        request.setAttribute("qualificationxaxis", qualificationName);
                        request.setAttribute("qualificationyaxis", qualificationquantity);
                        
		
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
		
		
		if(!addClass.equalsIgnoreCase("Class") ){
			
			conClassStudying = addClass  + " " +"%";
			
		}
		if( !addSec.equalsIgnoreCase("Sec")){
			conClassStudying = addClass;
			conClassStudying = conClassStudying + " " +addSec;
		}
			
		
			
			String classStudying = DataUtil.emptyString(conClassStudying);
			
		
			String addClassE = request.getParameter("admclassE");
			String addSecE = request.getParameter("admsecE");
			String conClassAdmittedIn = "";
			if(!addClassE.equalsIgnoreCase("Class") ){
				
				conClassAdmittedIn = addClassE  + " " +"%";
				
			}
			if( !addSecE.equalsIgnoreCase("Sec")){
				conClassAdmittedIn = addClassE;
				conClassAdmittedIn = conClassAdmittedIn + " " +addSecE;
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
		
			String querySub = "";
			
			if(!studentname.equalsIgnoreCase("")){
				querySub = " parents.Student.name like '%"+studentname+"%'" ;
			}
			
			if(!classStudying.equalsIgnoreCase("")  &&  !querySub.equalsIgnoreCase("") ){
				querySub = querySub + " AND parents.Student.classstudying like '"+classStudying+"'";
			}else if(!classStudying.equalsIgnoreCase("")){
				querySub = querySub + " parents.Student.classstudying like '"+classStudying+"'";
			}
			
			if(!gender.equalsIgnoreCase("")  &&  !querySub.equalsIgnoreCase("") ){
				querySub = querySub + " AND parents.Student.gender like '%"+gender+"%'";
			}else if(!gender.equalsIgnoreCase("")){
				querySub = querySub + " parents.Student.gender like '%"+gender+"%'";
			}
			
			if(!lastClass.equalsIgnoreCase("")  &&  !querySub.equalsIgnoreCase("") ){
				querySub = querySub + " AND parents.Student.stdlaststudied = '"+lastClass+"'";
			}else if(!lastClass.equalsIgnoreCase("")){
				querySub = querySub + " parents.Student.stdlaststudied = '"+lastClass+"'";
			}
			
			if(!dateOfBirth.equalsIgnoreCase("") &&  !querySub.equalsIgnoreCase("") ){
				querySub = querySub + " AND parents.Student.dateofbirth = '"+dateOfBirth+"'";
			}else if(!dateOfBirth.equalsIgnoreCase("")){
				querySub = querySub + " parents.Student.dateofbirth = '"+dateOfBirth+"'";
			}
			
			if(age != 0  &&  !querySub.equalsIgnoreCase("") ){
				querySub = querySub + " AND parents.Student.age = '"+age+"'";
			}else if(age != 0){
				querySub = querySub + " parents.Student.age = '"+age+"'";
			}
			
			if(!classAdmitted.equalsIgnoreCase("")  &&  !querySub.equalsIgnoreCase("") ){
				querySub = querySub + " AND parents.Student.classadmittedin like '"+classAdmitted+"'";
			}else if(!classAdmitted.equalsIgnoreCase("")){
				querySub = querySub + " parents.Student.classadmittedin like '"+classAdmitted+"'";
			}
			
			if(!lastSchool.equalsIgnoreCase("")  &&  !querySub.equalsIgnoreCase("") ){
				querySub = querySub + " AND parents.Student.schoollastattended like '%"+lastSchool+"%'";
			}else if(!lastSchool.equalsIgnoreCase("")){
				querySub = querySub + " parents.Student.schoollastattended like '%"+lastSchool+"%'";
			}
			
			
			if(!admissionNo.equalsIgnoreCase("")  &&  !querySub.equalsIgnoreCase("") ){
				querySub = querySub + " AND parents.Student.admissionnumber like '%"+admissionNo+"%'";
			}else if(!admissionNo.equalsIgnoreCase("")){
				querySub = querySub + " parents.Student.admissionnumber like '%"+admissionNo+"%'";
			}
			
			if(!dateOfAdmission.equalsIgnoreCase("")  &&  !querySub.equalsIgnoreCase("") ){
				querySub = querySub + " AND parents.Student.admissiondate = '"+dateOfAdmission+"'";
			}else if(!dateOfAdmission.equalsIgnoreCase("")){
				querySub = querySub + " parents.Student.admissiondate = '"+dateOfAdmission+"'";
			}
			
			if(!bloodGroup.equalsIgnoreCase("")  &&  !querySub.equalsIgnoreCase("") ){
				querySub = querySub + " AND parents.Student.bloodgroup like '%"+bloodGroup+"%'";
			}else if(!bloodGroup.equalsIgnoreCase("")){
				querySub = querySub + " parents.Student.bloodgroup like '%"+bloodGroup+"%'";
			}
			
			if(!nationality.equalsIgnoreCase("")  &&  !querySub.equalsIgnoreCase("") ){
				querySub = querySub + " AND parents.Student.nationality like '%"+nationality+"%'";
			}else if(!nationality.equalsIgnoreCase("")){
				querySub = querySub + " parents.Student.nationality like '%"+nationality+"%'";
			}
			
			if(!religion.equalsIgnoreCase("")  &&  !querySub.equalsIgnoreCase("") ){
				querySub = querySub + " AND parents.Student.religion like '%"+religion+"%'";
			}else if(!religion.equalsIgnoreCase("")){
				querySub = querySub + " parents.Student.religion like '%"+religion+"%'";
			}
			
			if(!caste.equalsIgnoreCase("")  &&  !querySub.equalsIgnoreCase("") ){
				querySub = querySub + " AND parents.Student.caste like '%"+caste+"%'";
			}else if(!caste.equalsIgnoreCase("")){
				querySub = querySub + " parents.Student.caste like '%"+caste+"%'";
			}
			
			if(!motherT.equalsIgnoreCase("")  &&  !querySub.equalsIgnoreCase("") ){
				querySub = querySub + " AND parents.Student.mothertongue like '%"+motherT+"%'";
			}else if(!motherT.equalsIgnoreCase("")){
				querySub = querySub + " parents.Student.mothertongue like '%"+motherT+"%'";
			}
			
			if(!createdDate.equalsIgnoreCase("")  &&  !querySub.equalsIgnoreCase("") ){
				querySub = querySub + " AND parents.Student.createddate = '"+createdDate+"'";
			}else if(!createdDate.equalsIgnoreCase("")){
				querySub = querySub + " parents.Student.createddate = '"+createdDate+"'";
			} 
			
			if(!remarks.equalsIgnoreCase("")  &&  !querySub.equalsIgnoreCase("") ){
				querySub = querySub + " AND parents.Student.remarks like '%"+remarks+"%'";
			}else if(!remarks.equalsIgnoreCase("")){
				querySub = querySub + " parents.Student.remarks like '%"+remarks+"%'";
			}
			
			queryMain = queryMain+querySub+" AND parents.Student.archive=0";
			/*queryMain = "FROM Parents as parents where  parents.Student.dateofbirth = '2006-04-06'"; */
			System.out.println("SEARCH QUERY ***** "+queryMain);
			searchStudentList = new studentDetailsDAO().getStudentsList(queryMain);
	}
			
			request.setAttribute("searchStudentList", searchStudentList);
		
		
	}

	public boolean backupData(String fileName) {
        
        boolean result = false;
        try {
            Properties properties = new Properties();
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("Backuplocation.properties");
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
            login.setLastmodifiedby(httpSession.getAttribute("username").toString());
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
		
		if(httpSession.getAttribute(BRANCHID)!=null){
			
		String queryMain ="From Receiptinfo as feesdetails where feesdetails.branchid="+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString())+" AND";
		String toDate= DataUtil.emptyString(request.getParameter("todate"));
		String fromDate = DataUtil.emptyString(request.getParameter("fromdate"));
		String oneDay = DataUtil.emptyString(request.getParameter("oneday"));
		
		
			String querySub = "";
			
			if(!oneDay.equalsIgnoreCase("")){
				querySub = " feesdetails.date = '"+oneDay+"'" ;
				 
			}
			
			if(!fromDate.equalsIgnoreCase("")  && !toDate.equalsIgnoreCase("")){
				querySub = " feesdetails.date between '"+fromDate+"' AND '"+toDate+"'";
				
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

    public void viewLogin() {
        new BranchService(request, response).viewBranches();
        List<Login> loginList = new UserDAO().readListOfUsers();
        request.setAttribute("loginlist", loginList);
               
    }

    public void addUser() {
        
        boolean result = false;
        Login login = new Login();
        Branch branch = new BranchDAO().getBranch(DataUtil.emptyString(request.getParameter("centercode")));
        login.setBranch(branch);
        login.setUsername(DataUtil.emptyString(request.getParameter("username")));
        login.setPassword(DataUtil.emptyString(request.getParameter("passwd")));
        login.setUsertype(DataUtil.emptyString(request.getParameter("usertype")));
        login.setAddstudentflag((byte)DataUtil.parseInt(request.getParameter("admissionprocess")));
        login.setLastmodifiedby(httpSession.getAttribute("username").toString());
        
        if (!login.getPassword().equalsIgnoreCase("")) {
            result = new UserDAO().addUser(login);
        }
        
        request.setAttribute("usersave", result);
    }

    public void updateMultipleUsers() {
        
        String[] loginIds = request.getParameterValues("loginids");
        String[] centerCode = request.getParameterValues("updatecentercode");
        String[] userName = request.getParameterValues("updateusername");
        String[] password = request.getParameterValues("updatepassword");
        String[] addStudentFlag = request.getParameterValues("updateadmissionprocess");
        String[] userType = request.getParameterValues("updateusertype");
        
        if(loginIds!=null){
            
            List<Login> loginList = new ArrayList<Login>();
            Map<Integer, List<String>> userMap = new HashMap<Integer, List<String>>();
            
            for(int i=0; i<loginIds.length;i++) {
                List<String> valSetOne = new ArrayList<String>();
                String[] lginId = loginIds[i].split(":");
                valSetOne.add(centerCode[Integer.valueOf(lginId[1])]);
                valSetOne.add(userName[Integer.valueOf(lginId[1])]);
                valSetOne.add(password[Integer.valueOf(lginId[1])]);
                valSetOne.add(userType[Integer.valueOf(lginId[1])]);
                valSetOne.add(addStudentFlag[Integer.valueOf(lginId[1])]);
                valSetOne.add(httpSession.getAttribute("username").toString());
                userMap.put(Integer.valueOf(lginId[0]), valSetOne);
            }
            boolean result = new UserDAO().updateMultipleUsers(userMap);
            request.setAttribute("userupdate", result);
        }
}

    public void deleteMultipleUsers() {

        
        String[] loginIds = request.getParameterValues("loginids");
        if(loginIds!=null){
        
       List<Integer> ids = new ArrayList<Integer>();
       for (String id : loginIds) {
           String[] brId = id.split(":");
           ids.add(Integer.valueOf(brId[0]));
       }
       boolean result = new UserDAO().deleteMultipleUsers(ids);
       request.setAttribute("userdelete", result);
        }
    }

    public void pauseAllUsers() {
        boolean result = new UserDAO().pauseAllUsers();
        request.setAttribute("userupdate", result);
        
    }

    public void resumeAllUsers() {
        boolean result = new UserDAO().resumeAllUsers();
        request.setAttribute("userupdate", result);
        
    }
}

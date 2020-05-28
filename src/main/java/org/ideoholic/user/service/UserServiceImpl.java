package org.ideoholic.user.service;

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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.model.academicyear.dao.YearDAO;
import com.model.academicyear.dto.Currentacademicyear;
import com.model.feescollection.dto.Receiptinfo;
import com.model.parents.dto.Parents;
import com.model.std.dao.StandardDetailsDAO;
import com.model.std.dto.Classsec;
import com.model.student.dao.studentDetailsDAO;
import com.model.user.dao.UserDAO;
import com.model.user.dto.Login;
import com.util.DataUtil;

@Service
public class UserServiceImpl implements UserService {
	private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	private static final Object BranchName = null;

	public String authenticateUser(String userName, String password) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		Login login = new UserDAO().readUniqueObject(userName, password);

		if (login != null) {
			Currentacademicyear currentAcademicYear = new YearDAO().showYear();
			String academicyear = "";
			if (currentAcademicYear != null) {
				academicyear = currentAcademicYear.getCurrentacademicyear();
			}
			sb.append("currentAcademicYear:").append(academicyear);
			sb.append(", username:").append(login.getUsername());
			sb.append(", branchid:").append(login.getBranch().getIdbranch());
			sb.append("branchname:").append(login.getBranch().getBranchname());
			String[] userType = login.getUsertype().split("-");
			sb.append(", userType:").append(userType[0]);
			sb.append(", typeOfUser:").append(userType[0]);
			sb.append(", userAuth:").append(userType[0]);

		} else {
			sb.append("authenticated:").append(false);
		}
		sb.append("}");
		return sb.toString();
	}

	@Override
	public String logout() {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("logout:").append(true);
		sb.append("}");
		new UserDAO().sessionClose();
		return sb.toString();
	}
	
   public String changePassword(String currentPassword, String newPassword, String confirmNewPassword) {
		
		StringBuffer sb = new StringBuffer();
		sb.append("{"); 
         Login login = new UserDAO().readPassword(currentPassword);
        
        if (login != null && newPassword.equals(confirmNewPassword)) {
            login.setPassword(newPassword);  
            login = new UserDAO().update(login);
            sb.append("result:").append(true);
        } else {
        	sb.append("result:").append(false);
        }
        
        sb.append("}");
        return sb.toString();
    }
   
	public String dashBoard(String branchId,String toDate, String fromDate) {
		
		StringBuffer sb = new StringBuffer();
		sb.append("{");

		if (branchId != null) {

			// List<Branch> branchList = new BranchDAO().readListOfObjects();
			List<Classsec> classsecList = new StandardDetailsDAO()
					.viewClasses(Integer.parseInt(branchId.toString()));
			List<String> xaxisList = new LinkedList<String>();
			List<String> yaxisList = new LinkedList<String>();
			// int[] test = new int[branchList.size()] ;
			for (Classsec classstudying : classsecList) {

				String classStudying = classstudying.getClassdetails();

				if (!classStudying.equalsIgnoreCase("")) {
					classStudying = classStudying + "--" + "%";
				}

				List<Parents> student = new studentDetailsDAO().getStudentsList(
						"FROM Parents as parents where parents.Student.classstudying like '" + classStudying + "'"
								+ " AND parents.Student.archive=0 AND parents.Student.passedout=0 AND parents.Student.droppedout=0 AND parents.Student.leftout=0");
				xaxisList.add("\"" + classstudying.getClassdetails() + "\"");
				if (student.size() > 0) {
					String studentCount = Integer.toString(student.size());
					yaxisList.add("\"" + studentCount + "\"");
				} else {
					yaxisList.add("\"" + 0 + "\"");
				}
			}
			sb.append("studentxaxis:").append(xaxisList);
			sb.append(",studentyaxis:").append(yaxisList);
			feesdailysearch();
			feesmonthlysearch(toDate, fromDate);
		}
		sb.append("}");
		return sb.toString();
	}
	
public String feesdailysearch() {
	
	StringBuffer sb = new StringBuffer();
	sb.append("{");
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date newdate = new Date();
		Calendar startCalendar = new GregorianCalendar();
		startCalendar.setTime(newdate);
		String todaysDate = df.format(newdate);
		List<Receiptinfo> feesDetailsList = new ArrayList<Receiptinfo>();
		Date dateBefore = null;
		Date dateAfter = null;
		
		String queryMain = "From Receiptinfo as feesdetails where feesdetails.cancelreceipt=0 and";
		
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
		sb.append("studenttotalfees:").append(totalFeesSum);
		sb.append(",currentdate:").append(dailyDatesList);
		sb.append("}");
		return sb.toString();
}


	public String feesmonthlysearch(String toDate, String fromDate) {
		
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		List<String> monthList = new LinkedList<String>();
		List<String> totalFeesSum = new LinkedList<String>();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date newdate = new Date();
		String todaysDate = df.format(newdate);
		List<Receiptinfo> feesDetailsList = new ArrayList<Receiptinfo>();
		Date dateBefore = null;
		Date dateAfter = null;
		String queryMain = "From Receiptinfo as feesdetails where feesdetails.cancelreceipt=0 and ";
		
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
		sb.append("monthlystudentsfees:").append(totalFeesSum);
		sb.append(",monthlist:").append(monthList);
		sb.append("}");
		return sb.toString();
	}


    public String advanceSearch(SearchParameterDto searchParameter, String branchId) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");

		List<Parents> searchStudentList = new ArrayList<Parents>();
		
		if(branchId!=null){
		String queryMain ="From Parents as parents where parents.branchid="+Integer.parseInt(branchId.toString())+" AND";
		String conClassStudying = "";
		
		
		if (!searchParameter.getAddclass().equalsIgnoreCase("")) {

			conClassStudying = searchParameter.getAddclass()+"--" +"%";

		}
		if (!searchParameter.getAddsec().equalsIgnoreCase("")) {
			conClassStudying = searchParameter.getAddclass();
			conClassStudying = conClassStudying+"--"+searchParameter.getAddsec()+"%";
		}
			
		
			
			String classStudying = DataUtil.emptyString(conClassStudying);
			
			String conClassAdmittedIn = "";
			
			if (!searchParameter.getAddClassE().equalsIgnoreCase("")) {

				conClassAdmittedIn = searchParameter.getAddClassE()+"--" +"%";

			}
			if (!searchParameter.getAddSecE().equalsIgnoreCase("")) {
				conClassAdmittedIn = searchParameter.getAddClassE();
				conClassAdmittedIn = conClassAdmittedIn+searchParameter.getAddSecE();
			}
			
			String classAdmitted = DataUtil.emptyString(conClassAdmittedIn);
			
			String querySub = "";
			
			if(!searchParameter.getStudentname().equalsIgnoreCase("")){
				querySub = " parents.Student.name like '%"+searchParameter.getStudentname()+"%'" ;
			}
			
			if(!classStudying.equalsIgnoreCase("")  &&  !querySub.equalsIgnoreCase("") ){
				querySub = querySub + " parents.Student.classstudying like '"+classStudying+"'";
			}else if(!classStudying.equalsIgnoreCase("")){
				querySub = querySub + " parents.Student.classstudying like '"+classStudying+"'";
			}
			
			if(!searchParameter.getGender().equalsIgnoreCase("")  &&  !querySub.equalsIgnoreCase("") ){
				querySub = querySub + " parents.Student.gender like '%"+searchParameter.getGender()+"%'";
			}else if(!searchParameter.getGender().equalsIgnoreCase("")){
				querySub = querySub + " parents.Student.gender like '%"+searchParameter.getGender()+"%'";
			}
			
			if(!searchParameter.getLastclass().equalsIgnoreCase("")  &&  !querySub.equalsIgnoreCase("") ){
				querySub = querySub + " parents.Student.stdlaststudied = '"+searchParameter.getLastclass()+"'";
			}else if(!searchParameter.getLastclass().equalsIgnoreCase("")){
				querySub = querySub + " parents.Student.stdlaststudied = '"+searchParameter.getLastclass()+"'";
			}
			
			if(!searchParameter.getDateofbirth().equalsIgnoreCase("") &&  !querySub.equalsIgnoreCase("") ){
				querySub = querySub + " parents.Student.dateofbirth = '"+searchParameter.getDateofbirth()+"'";
			}else if(!searchParameter.getDateofbirth().equalsIgnoreCase("")){
				querySub = querySub + " parents.Student.dateofbirth = '"+searchParameter.getDateofbirth()+"'";
			}
			
			if(!searchParameter.getAge().equals(0)  &&  !querySub.equalsIgnoreCase("") ){
				querySub = querySub + " parents.Student.age = '"+searchParameter.getAge()+"'";
			}else if(!searchParameter.getAge().equals(0)){
				querySub = querySub + " parents.Student.age = '"+searchParameter.getAge()+"'";
			}
			
			if(!classAdmitted.equalsIgnoreCase("")  &&  !querySub.equalsIgnoreCase("") ){
				querySub = querySub + " parents.Student.classadmittedin like '"+classAdmitted+"'";
			}else if(!classAdmitted.equalsIgnoreCase("")){
				querySub = querySub + " parents.Student.classadmittedin like '"+classAdmitted+"'";
			}
			
			if(!searchParameter.getLastschool().equalsIgnoreCase("")  &&  !querySub.equalsIgnoreCase("") ){
				querySub = querySub + " parents.Student.schoollastattended like '%"+searchParameter.getLastschool()+"%'";
			}else if(!searchParameter.getLastschool().equalsIgnoreCase("")){
				querySub = querySub + " parents.Student.schoollastattended like '%"+searchParameter.getLastschool()+"%'";
			}
			
			
			if(!searchParameter.getAdmissionNo().equalsIgnoreCase("")  &&  !querySub.equalsIgnoreCase("") ){
				querySub = querySub + " parents.Student.admissionnumber like '%"+searchParameter.getAdmissionNo()+"%'";
			}else if(!searchParameter.getAdmissionNo().equalsIgnoreCase("")){
				querySub = querySub + " parents.Student.admissionnumber like '%"+searchParameter.getAdmissionNo()+"%'";
			}
			
			if(!searchParameter.getDateofadmission().equalsIgnoreCase("")  &&  !querySub.equalsIgnoreCase("") ){
				querySub = querySub + " parents.Student.admissiondate = '"+searchParameter.getDateofadmission()+"'";
			}else if(!searchParameter.getDateofadmission().equalsIgnoreCase("")){
				querySub = querySub + " parents.Student.admissiondate = '"+searchParameter.getDateofadmission()+"'";
			}
			
			if(!searchParameter.getBloodgroup().equalsIgnoreCase("")  &&  !querySub.equalsIgnoreCase("") ){
				querySub = querySub + " parents.Student.bloodgroup like '%"+searchParameter.getBloodgroup()+"%'";
			}else if(!searchParameter.getBloodgroup().equalsIgnoreCase("")){
				querySub = querySub + " parents.Student.bloodgroup like '%"+searchParameter.getBloodgroup()+"%'";
			}
			
			if(!searchParameter.getNationality().equalsIgnoreCase("")  &&  !querySub.equalsIgnoreCase("") ){
				querySub = querySub + " parents.Student.nationality like '%"+searchParameter.getNationality()+"%'";
			}else if(!searchParameter.getNationality().equalsIgnoreCase("")){
				querySub = querySub + " parents.Student.nationality like '%"+searchParameter.getNationality()+"%'";
			}
			
			if(!searchParameter.getReligion().equalsIgnoreCase("")  &&  !querySub.equalsIgnoreCase("") ){
				querySub = querySub + " parents.Student.religion like '%"+searchParameter.getReligion()+"%'";
			}else if(!searchParameter.getReligion().equalsIgnoreCase("")){
				querySub = querySub + " parents.Student.religion like '%"+searchParameter.getReligion()+"%'";
			}
			
			if(!searchParameter.getCaste().equalsIgnoreCase("")  &&  !querySub.equalsIgnoreCase("") ){
				querySub = querySub + " parents.Student.caste like '%"+searchParameter.getCaste()+"%'";
			}else if(!searchParameter.getCaste().equalsIgnoreCase("")){
				querySub = querySub + " parents.Student.caste like '%"+searchParameter.getCaste()+"%'";
			}
			
			if(!searchParameter.getMotherT().equalsIgnoreCase("")  &&  !querySub.equalsIgnoreCase("") ){
				querySub = querySub + " parents.Student.mothertongue like '%"+searchParameter.getMotherT()+"%'";
			}else if(!searchParameter.getMotherT().equalsIgnoreCase("")){
				querySub = querySub + " parents.Student.mothertongue like '%"+searchParameter.getMotherT()+"%'";
			}
			
			if(!searchParameter.getCreateddate().equalsIgnoreCase("")  &&  !querySub.equalsIgnoreCase("") ){
				querySub = querySub + " parents.Student.createddate = '"+searchParameter.getCreateddate()+"'";
			}else if(!searchParameter.getCreateddate().equalsIgnoreCase("")){
				querySub = querySub + " parents.Student.createddate = '"+searchParameter.getCreateddate()+"'";
			} 
			
			if(!searchParameter.getRemarks().equalsIgnoreCase("")  &&  !querySub.equalsIgnoreCase("") ){
				querySub = querySub + " parents.Student.remarks like '%"+searchParameter.getRemarks()+"%'";
			}else if(!searchParameter.getRemarks().equalsIgnoreCase("")){
				querySub = querySub + " parents.Student.remarks like '%"+searchParameter.getRemarks()+"%'";
			}
			
			if(!searchParameter.getSts().equalsIgnoreCase("")  &&  !querySub.equalsIgnoreCase("") ){
				querySub = querySub + " parents.Student.sts = '"+searchParameter.getSts()+"'";
			}else if(!searchParameter.getSts().equalsIgnoreCase("")){
				querySub = querySub + " parents.Student.sts = '"+searchParameter.getSts()+"'";
			}
			
			if(!searchParameter.getRte().equalsIgnoreCase("")  &&  !querySub.equalsIgnoreCase("") ){
				querySub = querySub + " parents.Student.rte = '"+searchParameter.getRte()+"'";
			}else if(!searchParameter.getRte().equalsIgnoreCase("")){
				querySub = querySub + " parents.Student.rte = '"+searchParameter.getRte()+"'";
			}
			
			queryMain = queryMain+querySub+" AND parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0";
			searchStudentList = new studentDetailsDAO().getStudentsList(queryMain);
	}
		sb.append("searchStudentList:").append(searchStudentList);
		
			sb.append("}");
			return sb.toString();
	}


    


public String advanceSearchByParents(String branchId, String fathersName, String mothersName) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");

	
	List<Parents> searchParentsList = new ArrayList<Parents>();
	
	if(branchId!=null){
		
	String queryMain ="From Parents as parents where parents.branchid="+Integer.parseInt(branchId.toString())+" AND";
	
		String querySub = "";
		
		if(!fathersName.equalsIgnoreCase("")){
			querySub = " parents.fathersname like '%"+fathersName+"%'" ;
		}
		
		if(!mothersName.equalsIgnoreCase("")  &&  !querySub.equalsIgnoreCase("") ){
			querySub = querySub + " AND parents.mothersname like '%"+mothersName+"%'";
		}else if(!mothersName.equalsIgnoreCase("")){
			querySub = querySub + " parents.mothersname like '%"+mothersName+"%'";
		}
		
		
		
		queryMain = queryMain+querySub;
		/*queryMain = "FROM Parents as parents where  parents.Student.dateofbirth = '2006-04-06'"; */
		System.out.println("SEARCH QUERY ***** "+queryMain);
		searchParentsList = new studentDetailsDAO().getStudentsList(queryMain);
		
	}
	    sb.append("studentList:").append(searchParentsList);
		sb.append("}");
		return sb.toString();
	
	
}


public String backupData(String fileName) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");

    
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
        sb.append("Backuplocation:").append(backupLocation);
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
    return sb.toString();
}


public String searchByDate(String branchId, String selectedbranchid, String toDate, String fromDate, String oneDay,
		 String dayOne,String dateFrom, String dateTo) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
	 
	List<Receiptinfo> feesDetailsList = new ArrayList<Receiptinfo>();
	int idBranch = 0;
            
	if(branchId!=null){
	

        if(branchId!=null) {
        	String[] branchIdName = branchId.split(":");
        	idBranch = Integer.parseInt(branchIdName[0]);
        	sb.append("feesdetailsbranchname:").append(branchIdName[1]);
        	sb.append(",branchname:").append(BranchName);
        }else {
        	idBranch = Integer.parseInt(branchId.toString());
        }
        
	String queryMain ="From Receiptinfo as feesdetails where feesdetails.cancelreceipt=0 and feesdetails.branchid="+idBranch+" AND";
	
		String querySub = "";
		
		if(!oneDay.equalsIgnoreCase("")){
			querySub = " feesdetails.date = '"+oneDay+"'" ;
			sb.append("dayone:").append(oneDay);
			 sb.append(",datefrom:").append("");
			 sb.append(",dateto:").append("");
		}else if(!"".equalsIgnoreCase(DataUtil.emptyString(dayOne))) {
			querySub = " feesdetails.date = '"+dayOne+"'" ;
		}
		
		if(!fromDate.equalsIgnoreCase("")  && !toDate.equalsIgnoreCase("")){
			querySub = " feesdetails.date between '"+fromDate+"' AND '"+toDate+"'";
			sb.append("datefrom:").append(fromDate);
			sb.append(",dateto:").append(toDate);
			sb.append(",dayone:").append("");
		}else if(!"".equalsIgnoreCase(DataUtil.emptyString(dateFrom)) && 
				!"".equalsIgnoreCase(DataUtil.emptyString(dateTo)) ) {
			querySub = " feesdetails.date between '"+dateFrom+"' AND '"+dateTo+"'";
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
		sb.append("searchfeesdetailslist").append(feesDetailsList);
		sb.append(",sumofdetailsfees").append(sumOfFees);
		sb.append("}");
		return sb.toString();
}

}
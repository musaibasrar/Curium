package org.ideoholic.curium.model.user.service;

import lombok.extern.slf4j.Slf4j;
import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.academicyear.dao.YearDAO;
import org.ideoholic.curium.model.academicyear.dto.Currentacademicyear;
import org.ideoholic.curium.model.account.dao.AccountDAO;
import org.ideoholic.curium.model.account.dto.VoucherEntrytransactions;
import org.ideoholic.curium.model.adminexpenses.service.AdminService;
import org.ideoholic.curium.model.appointment.dto.DailyExpensesResponseDto;
import org.ideoholic.curium.model.appointment.dto.MonthlyExpensesResponseDto;
import org.ideoholic.curium.model.attendance.dao.AttendanceDAO;
import org.ideoholic.curium.model.attendance.dto.Studentdailyattendance;
import org.ideoholic.curium.model.branch.dto.Branch;
import org.ideoholic.curium.model.employee.dao.EmployeeDAO;
import org.ideoholic.curium.model.employee.dto.Teacher;
import org.ideoholic.curium.model.feescollection.action.FeesCollectionActionAdapter;
import org.ideoholic.curium.model.feescollection.dto.Receiptinfo;
import org.ideoholic.curium.model.parents.dto.Parents;
import org.ideoholic.curium.model.std.action.StandardActionAdapter;
import org.ideoholic.curium.model.std.dao.StandardDetailsDAO;
import org.ideoholic.curium.model.std.dto.Classsec;
import org.ideoholic.curium.model.student.dao.studentDetailsDAO;
import org.ideoholic.curium.model.student.dto.Student;
import org.ideoholic.curium.model.user.dao.UserDAO;
import org.ideoholic.curium.model.user.dto.*;
import org.ideoholic.curium.util.DataUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.Map.Entry;

@Slf4j
public class UserService {

	private StandardActionAdapter standardActionAdapter;
	private AdminService adminService;
	private FeesCollectionActionAdapter feesCollectionActionAdapter;

	 HttpServletRequest request;
	    HttpServletResponse response;
	    HttpSession httpSession;
	    private Login login;
	    private String BRANCHID = "branchid";

	public UserService(HttpServletRequest request, HttpServletResponse response, StandardActionAdapter standardActionAdapter, AdminService adminService, FeesCollectionActionAdapter feesCollectionActionAdapter) {
		this.request = request;
        this.response = response;
        this.httpSession = request.getSession();
		this.standardActionAdapter = standardActionAdapter;
		this.adminService = adminService;
		this.feesCollectionActionAdapter = feesCollectionActionAdapter;
	}

	public UserAuthenticationResponseDto authenticateUser(UserAuthenticationDto dto) {
		UserAuthenticationResponseDto result = UserAuthenticationResponseDto.builder().build();

       String userName = dto.getUserName();
       String password = dto.getPassword();

       login = new UserDAO().readUniqueObject(userName, password);

       if (login != null) {
            Currentacademicyear currentAcademicYear = new YearDAO().showYear();
            String academicyear = "";
            if(currentAcademicYear!=null){
            academicyear = currentAcademicYear.getCurrentacademicyear();
            }
			result.setAcademicYear(academicyear);
			result.setUserName(login.getUsername());

			result.setBranchId(login.getBranch().getIdbranch());
			result.setBranchName(login.getBranch().getBranchname());
			result.setBranchCode(login.getBranch().getBranchcode());
			result.setBranchAddress(login.getBranch().getAddress());
			result.setBranchContact(login.getBranch().getContact());

            String[] userType = login.getUsertype().split("-");
			result.setUserType(userType[0]);
			result.setTypeOfUser(userType[0]);
			result.setUserAuth(userType[0]);
			result.setUserLoginId(login.getUserid());
			//setting session to expiry in 60 mins
           	httpSession.setMaxInactiveInterval(60*60);
			Cookie cookie = new Cookie("user",  login.getUsertype());
			cookie.setMaxAge(30*60);
			response.addCookie(cookie);
			
			if(userType[0].equalsIgnoreCase("parents")) {
				LocalDate currentDate = LocalDate.now();
				Studentdailyattendance attendance = new AttendanceDAO().getStudentTodaysAttendance(userName,currentDate);
			       if(attendance != null) {
				   result.setAttendanceStatus(attendance.getAttendancestatus());
			       }
			}
			
			//get the Previous Year List
			String[] parts = academicyear.split("/");
			int startYear = Integer.parseInt(parts[0]);

			List<String> academicYears = new ArrayList<>();
			for (int i = 0; i < 4; i++) {
			    int year1 = startYear - i;
			    int year2 = year1 + 1;
			    academicYears.add(year1 + "/" + (String.valueOf(year2).substring(2)));
			}
			result.setPreviousAcademicYears(academicYears);
			// end
			
           result.setSuccess(true);
       } else {
           result.setSuccess(false);
       }
       return result;
   }

	public void logOutUser() {
		httpSession.invalidate();
        new UserDAO().sessionClose();
		
	}

	public DashBoardResponseDto dashBoard(SearchByDateDto dto, String branchId, String currentAcademicYear) {
		DashBoardResponseDto result = DashBoardResponseDto.builder().build();

		try {

			if (branchId != null) {

				//List<Branch> branchList = new BranchDAO().readListOfObjects();
				List<Classsec> classsecList = new StandardDetailsDAO().viewClasses(Integer.parseInt(branchId));
				List<String> xaxisList = new LinkedList<>();
				List<String> yaxisList = new LinkedList<>();
				int totalStudents = 0;
				String academicYear = currentAcademicYear;
				// int[] test = new int[branchList.size()] ;
				for (Classsec classstudying : classsecList) {

					String classStudying = classstudying.getClassdetails();
					if (!classStudying.equalsIgnoreCase("")) {

						classStudying = classStudying + "--" + "%";

						List<Parents> student = new studentDetailsDAO().getStudentsList("FROM Parents as parents where (parents.Student.promotedyear='" + academicYear + "' or parents.Student.yearofadmission='" + academicYear + "') AND parents.Student.classstudying like '" + classStudying + "'"
								+ " AND parents.Student.archive=0 AND parents.Student.passedout=0 AND parents.Student.droppedout=0 AND parents.Student.leftout=0 AND parents.Student.branchid='" + Integer.parseInt(branchId) + "' ");
						totalStudents += student.size();
						xaxisList.add("\"" + classstudying.getClassdetails() + "\"");
						if (student.size() > 0) {
							String studentCount = Integer.toString(student.size());
							yaxisList.add("\"" + studentCount + "\"");
						} else {
							yaxisList.add("\"" + 0 + "\"");
						}

					}
				}
				// Total Teachers
				List<Teacher> teacher = new EmployeeDAO().readCurrentTeachers(Integer.parseInt(branchId));
				result.setTeacherSize(teacher.size());
				// End Total Teachers

				//Fees Details
				feesCollectionActionAdapter.getFeesDetailsDashBoard(); //TODO: After FessCollection becomes @Service, use feesCollectionService.getFeesDetailsDashBoard() instead.
				//End Fees Details

				//Daily Expenses
				DailyExpensesResponseDto dailyResponse = adminService.dailyExpenses(dto.getBranchId(), branchId);
				result.setDailyExpensesResponseDto(dailyResponse);

				//Monthly Expenses
				MonthlyExpensesResponseDto monthlyExpense = adminService.getMonthlyExpenses(branchId, dto.getToDate(), dto.getFromDate());
				result.setMonthlyExpensesResponseDto(monthlyExpense);

				//Get Boys & Girls
				ResultResponse resultResponse = adminService.getTotalBoysGirls(branchId);
				result.setBoysGirls(resultResponse.getResultList());

				result.setXaxisList(xaxisList);
				result.setYaxisList(yaxisList);
				result.setTotalStudents(totalStudents);
				feesdailysearch();
				feesmonthlysearch();

				result.setSuccess(true);
			}
		}catch (Exception e){
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
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
		String queryMain = "From Receiptinfo as feesdetails where feesdetails.cancelreceipt=0 and ";
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
		
		start1.set(Calendar.MONTH, Calendar.JUNE);
		start1.set(Calendar.DAY_OF_MONTH, 1);
		end1.set(Calendar.YEAR, start1.get(Calendar.YEAR) + 1);
		end1.set(Calendar.MONTH, Calendar.MAY);
		end1.add(Calendar.DAY_OF_MONTH, end1.getActualMaximum(Calendar.DAY_OF_MONTH));
		
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
	

	public ResultResponse advanceSearch(AdvanceSearchDto dto, String branchId) {
		ResultResponse result = ResultResponse.builder().build();

		List<Parents> searchStudentList = new ArrayList<>();
		try {
			if (branchId != null) {
				String queryMain = "From Parents as parents where parents.branchid=" + Integer.parseInt(branchId) + "";
				String studentname = DataUtil.emptyString(dto.getName());
				String gender = DataUtil.emptyString(dto.getGender());
				String dateOfBirth = DataUtil.emptyString(dto.getDateOfBirth());
				int age = DataUtil.parseInt(dto.getAge());

				String addClass = dto.getAddClass();
				String addSec = dto.getAddSec();
				String conClassStudying = "";


				if (!addClass.equalsIgnoreCase("")) {

					conClassStudying = addClass + "--" + "%";

				}
				if (!addSec.equalsIgnoreCase("")) {
					conClassStudying = addClass;
					conClassStudying = conClassStudying + "--" + addSec + "%";
				}


				String classStudying = DataUtil.emptyString(conClassStudying);


				String addClassE = dto.getAdmClassE();
				String addSecE = dto.getAdmSecE();
				String conClassAdmittedIn = "";

				if (!addClassE.equalsIgnoreCase("")) {

					conClassAdmittedIn = addClassE + "--" + "%";

				}
				if (!addSecE.equalsIgnoreCase("")) {
					conClassAdmittedIn = addClassE;
					conClassAdmittedIn = conClassAdmittedIn + addSecE;
				}

				String classAdmitted = DataUtil.emptyString(conClassAdmittedIn);
				//String lastClass = DataUtil.emptyString(request.getParameter("lastclass"));
				//String lastSchool =  DataUtil.emptyString(request.getParameter("lastschool"));
				String admissionNo = DataUtil.emptyString(dto.getAdmNo());
				String dateOfAdmission = DataUtil.emptyString(dto.getDateOfAdmission());
				String bloodGroup = DataUtil.emptyString(dto.getBloodGroup());
				String nationality = DataUtil.emptyString(dto.getNationality());
				String religion = DataUtil.emptyString(dto.getReligion());
				String caste = DataUtil.emptyString(dto.getCaste());
				String motherT = DataUtil.emptyString(dto.getMotherTongue());
				String createdDate = DataUtil.emptyString(dto.getCreatedDate());
				String remarks = DataUtil.emptyString(dto.getRemarks());
				String stsNumber = DataUtil.emptyString(dto.getSts());
				String studentExternalId = DataUtil.emptyString(dto.getUId());
				//String rte = DataUtil.emptyString(request.getParameter("rte"));

				String querySub = "";

				if (!studentname.equalsIgnoreCase("")) {
					querySub = " AND parents.Student.name like '%" + studentname + "%'";
				}

				if (!classStudying.equalsIgnoreCase("")) {
					querySub = querySub + " AND parents.Student.classstudying like '" + classStudying + "'";
				}

				if (!gender.equalsIgnoreCase("")) {
					querySub = querySub + " AND  parents.Student.gender like '" + gender + "%'";
				}

				/*
				 * if(!lastClass.equalsIgnoreCase("") && !querySub.equalsIgnoreCase("") ){
				 * querySub = querySub + " parents.Student.stdlaststudied = '"+lastClass+"'";
				 * }else if(!lastClass.equalsIgnoreCase("")){ querySub = querySub +
				 * " parents.Student.stdlaststudied = '"+lastClass+"'"; }
				 */

				if (!dateOfBirth.equalsIgnoreCase("")) {
					querySub = querySub + " AND parents.Student.dateofbirth = '" + dateOfBirth + "'";
				}


				if (age != 0) {
					querySub = querySub + " AND parents.Student.age = '" + age + "'";
				}

				if (!classAdmitted.equalsIgnoreCase("")) {
					querySub = querySub + " AND parents.Student.classadmittedin like '" + classAdmitted + "'";
				}
				/*
				 * if(!lastSchool.equalsIgnoreCase("") && !querySub.equalsIgnoreCase("") ){
				 * querySub = querySub +
				 * " parents.Student.schoollastattended like '%"+lastSchool+"%'"; }else
				 * if(!lastSchool.equalsIgnoreCase("")){ querySub = querySub +
				 * " parents.Student.schoollastattended like '%"+lastSchool+"%'"; }
				 */


				if (!admissionNo.equalsIgnoreCase("")) {
					querySub = querySub + " AND parents.Student.admissionnumber like '%" + admissionNo + "%'";
				}

				if (!dateOfAdmission.equalsIgnoreCase("")) {
					querySub = querySub + " AND parents.Student.admissiondate = '" + dateOfAdmission + "'";
				}

				if (!bloodGroup.equalsIgnoreCase("")) {
					querySub = querySub + " AND parents.Student.bloodgroup like '%" + bloodGroup + "%'";
				}

				if (!nationality.equalsIgnoreCase("")) {
					querySub = querySub + " AND  parents.Student.nationality like '%" + nationality + "%'";
				}

				if (!religion.equalsIgnoreCase("")) {
					querySub = querySub + " AND  parents.Student.religion like '%" + religion + "%'";
				}

				if (!caste.equalsIgnoreCase("")) {
					querySub = querySub + " AND  parents.Student.caste like '%" + caste + "%'";
				}

				if (!motherT.equalsIgnoreCase("")) {
					querySub = querySub + " AND  parents.Student.mothertongue like '%" + motherT + "%'";
				}

				if (!createdDate.equalsIgnoreCase("")) {
					querySub = querySub + " AND  parents.Student.createddate = '" + createdDate + "'";
				}

				if (!remarks.equalsIgnoreCase("")) {
					querySub = querySub + " AND  parents.Student.remarks like '%" + remarks + "%'";
				}


				if (!stsNumber.equalsIgnoreCase("")) {
					querySub = querySub + " AND  parents.Student.sts = '" + stsNumber + "'";
				}


				if (!studentExternalId.equalsIgnoreCase("")) {
					querySub = querySub + " AND  parents.Student.studentexternalid = '" + studentExternalId + "'";
				}
				/* *
				 * if(!rte.equalsIgnoreCase("") && !querySub.equalsIgnoreCase("") ){ querySub =
				 * querySub + " parents.Student.rte = '"+rte+"'"; }else
				 * if(!rte.equalsIgnoreCase("")){ querySub = querySub +
				 * " parents.Student.rte = '"+rte+"'"; }
				 */

				queryMain = queryMain + querySub + " AND parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0";
				searchStudentList = new studentDetailsDAO().getStudentsList(queryMain);
			}

			result.setResultList(searchStudentList);
			result.setSuccess(true);
		}catch (Exception e){
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}

	public ResultResponse backupData(String fileName) {
		ResultResponse result = ResultResponse.builder().build();

        try {
            Properties properties = new Properties();
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("Util.properties");
            properties.load(inputStream);
            String backupDirectoryIS = properties.getProperty("backupdirectory");
			log.error("the backup directory from input stream is " + backupDirectoryIS);


            int processcomplete; // to verify that either process completed or not
            String sqlExtension = ".sql";
            String backupLocation = backupDirectoryIS + fileName + sqlExtension;
            String mysqlPath = properties.getProperty("mysqlpath");
			result.setMessage(backupLocation);
            Process runtimeProcess = Runtime.getRuntime().exec(mysqlPath + backupLocation);


            // call the mysqldump in terminal and execute it

            processcomplete = runtimeProcess.waitFor();//store the state in variable

            if (processcomplete == 1) {//if values equal 1 process failed
				log.error("FAILED");
				result.setSuccess(false);

            } else if (processcomplete == 0) {//if values equal 0 process failed
				log.error("success");

                //display message
				result.setSuccess(true);

            }

        } catch (Exception e) {
            e.printStackTrace();
			result.setSuccess(false);
        }
        return result;
    }

	public ResultResponse ChangePassword(UserAuthenticationDto dto) {
		ResultResponse result = ResultResponse.builder().build();

        Login login = new Login();
        String currentPassword = dto.getCurrentPassword();
        String newPassword = dto.getNewPassword();
        String confirmNewPassword = dto.getConfirmNewPassword();
        
        login = new UserDAO().readPassword(currentPassword);
        
        if (login != null && newPassword.equals(confirmNewPassword)) {
            login.setPassword(newPassword);  
            login = new UserDAO().update(login);
			result.setSuccess(true);
        } else {
			result.setSuccess(false);
        }
        return result;
    }

	public ResultResponse advanceSearchByParents(SearchByParentDto dto, String branchId) {
		ResultResponse result = ResultResponse.builder().build();
		
		List<Parents> searchParentsList = new ArrayList<>();
		try {
			if (branchId != null) {

				String queryMain = "From Parents as parents where parents.branchid=" + Integer.parseInt(branchId) + " AND";
				String fathersname = DataUtil.emptyString(dto.getFathersName());
				String mothersname = DataUtil.emptyString(dto.getMothersName());
				String contactnumber = DataUtil.emptyString(dto.getContactNumber());


				String querySub = "";

				if (!fathersname.equalsIgnoreCase("")) {
					querySub = " parents.fathersname like '%" + fathersname + "%'";
				}

				if (!mothersname.equalsIgnoreCase("")) {
					querySub = querySub + " AND parents.mothersname like '%" + mothersname + "%'";
				} else if (!mothersname.equalsIgnoreCase("")) {
					querySub = querySub + " parents.mothersname like '%" + mothersname + "%'";
				} else if (!contactnumber.equalsIgnoreCase("")) {
					querySub = querySub + " parents.contactnumber like '%" + contactnumber + "%'";
				}


	 			queryMain = queryMain + querySub;
				/*queryMain = "FROM Parents as parents where  parents.Student.dateofbirth = '2006-04-06'"; */
				log.error("SEARCH QUERY ***** " + queryMain);
				searchParentsList = new studentDetailsDAO().getStudentsList(queryMain);

			}
			result.setResultList(searchParentsList);
			result.setSuccess(true);
		}catch (Exception e){
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}

	public SearchByDateResponseDto searchByDate(SearchByDateDto dto, String strBranchId, Object dayOne, Object dateFrom, Object dateTo) {
		SearchByDateResponseDto result = SearchByDateResponseDto.builder().build();

		List<Receiptinfo> feesDetailsList = new ArrayList<>();
		String branchId = dto.getBranchId();
		int idBranch = 0;
        try {
			if(strBranchId!=null){


				if(branchId!=null) {
					String[] branchIdName = branchId.split(":");
					idBranch = Integer.parseInt(branchIdName[0]);
					result.setFeesDetailsBranchName(branchIdName[1]);
					result.setBranchName("Branch Name:");
				}else {
					idBranch = Integer.parseInt(strBranchId);
				}

				String queryMain ="From Receiptinfo as feesdetails where feesdetails.cancelreceipt=0 and feesdetails.branchid="+idBranch+" AND";
				String toDate= DataUtil.emptyString(dto.getToDate());
				String fromDate = DataUtil.emptyString(dto.getFromDate());
				String oneDay = DataUtil.emptyString(dto.getOneDay());
				String modeOfPayment = DataUtil.emptyString(dto.getModeOfPayment());
				String academicYear = DataUtil.emptyString(dto.getAcademicYear());

				String querySub = "";

				if(!oneDay.equalsIgnoreCase("")){
					result.setDayOne(oneDay);
					result.setDateFrom("");
					result.setDateTo("");
					querySub = " feesdetails.date = '"+oneDay+"'" ;
				}else if( dayOne!= null ) {
					querySub = " feesdetails.date = '"+dayOne.toString()+"'" ;
 				}

				if(!fromDate.equalsIgnoreCase("")  && !toDate.equalsIgnoreCase("")){
					querySub = " feesdetails.date between '"+fromDate+"' AND '"+toDate+"'";
					result.setDateFrom(fromDate);
					result.setDateTo(toDate);
					result.setDayOne("");
				}else if(dateFrom!=null && dateTo!=null) {
					if(!dateFrom.toString().equalsIgnoreCase("") &&  !dateTo.toString().equalsIgnoreCase("")) {
						querySub = " feesdetails.date between '"+dateFrom.toString()+"' AND '"+dateTo.toString()+"'";
					}
							
				}

				if(!modeOfPayment.equalsIgnoreCase("")){
					querySub = querySub+" and feesdetails.paymenttype = '"+modeOfPayment+"'" ;
				}
				
				if(!academicYear.equalsIgnoreCase("")){
					querySub = querySub+" and feesdetails.academicyear = '"+academicYear+"'" ;
				}

				queryMain = queryMain+querySub;
				/*queryMain = "FROM Parents as parents where  parents.Student.dateofbirth = '2006-04-06'"; */
				log.error("SEARCH QUERY ***** "+queryMain);
				feesDetailsList = new UserDAO().getReceiptDetailsList(queryMain);

			}
			long sumOfFees = 0l;
			long fine = 0l;
			long misc = 0l;
			Map<Receiptinfo,Parents> feesMap = new HashMap<Receiptinfo,Parents>();

			for (Receiptinfo receiptinfo : feesDetailsList) {
				sumOfFees = sumOfFees + receiptinfo.getTotalamount();
				fine = fine + receiptinfo.getFine();
				misc = misc + receiptinfo.getMisc();
				Parents student = new Parents();
				student = new studentDetailsDAO().readUniqueObjectParents(receiptinfo.getSid());
				VoucherEntrytransactions voucherEntryTransactions = new AccountDAO().getVoucherDetails(receiptinfo.getReceiptvoucher().toString());
				String[] narrationDetails = voucherEntryTransactions.getNarration().split(":");
				Student ss = student.getStudent();
				ss.setRemarks(narrationDetails[0]);
				student.setStudent(ss);
				feesMap.put(receiptinfo, student);
			}

			result.setFeesMap(feesMap);
			result.setSumOfFees(sumOfFees);
			result.setSumOfOnlyFee(sumOfFees-fine-misc);
			result.setFine(fine);
			result.setMisc(misc);

			for (Entry<Receiptinfo, Parents> entry : feesMap.entrySet()) {
				log.error("Key: " + entry.getKey().getReceiptnumber() + ", Value: " + entry.getValue().getStudent().getName());
			}
			result.setSuccess(true);
		}catch (Exception e){
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}

	public boolean addUser(Teacher employee, String branchId) {
		
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
		user.setUsertype("teacher");
		Branch branch = new Branch();
		branch.setIdbranch(Integer.parseInt(branchId));
		user.setBranch(branch);
		
		return new UserDAO().addUser(user);
		
	}

	public UserAuthenticationResponseDto authenticateMultiUser(String strUserName, String strSuperUserAuth, String strBranchId) {
		UserAuthenticationResponseDto result = UserAuthenticationResponseDto.builder().build();

        
        String userName =null;
        String superUserAuth = null;

        
        	if(strUserName!=null) {
        		userName = strUserName;
	        }
        
        	if(strSuperUserAuth!=null) {
	        	superUserAuth = DataUtil.emptyString(strSuperUserAuth);
	        }
        
        if(userName != null) {
        	int branchId = Integer.parseInt(strBranchId);
        	login = new UserDAO().getLoginDetails(userName, branchId);

       if (login != null) {
    	   
    	   Enumeration em = httpSession.getAttributeNames();
    	   while (em.hasMoreElements()) {
    		    String element = (String)em.nextElement();
    		    if (!"uname".equals(element))
    		    	httpSession.removeAttribute(element);
    		}
    	   
            Currentacademicyear currentAcademicYear = new YearDAO().showYear();
            String academicyear = "";
            if(currentAcademicYear!=null){
            academicyear = currentAcademicYear.getCurrentacademicyear();
            }
			result.setAcademicYear(academicyear);
			result.setUserName(login.getUsername());

			result.setBranchId(login.getBranch().getIdbranch());
			result.setBranchName(login.getBranch().getBranchname());
			result.setBranchCode(login.getBranch().getBranchcode());
			result.setBranchAddress(login.getBranch().getAddress());
			result.setBranchContact(login.getBranch().getContact());
            
            String[] userType = login.getUsertype().split("-");
			result.setUserType(userType[0]);
			result.setTypeOfUser(userType[0]);
			result.setUserAuth(userType[0]);
			result.setSuperUserAuth("superAdmin");
			result.setUserLoginId(login.getUserid());
            
			//setting session to expiry in 60 mins
           	httpSession.setMaxInactiveInterval(60*60);
			Cookie cookie = new Cookie("user",  login.getUsertype());
			cookie.setMaxAge(30*60);
			response.addCookie(cookie);
			
			//get the Previous Year List
			String[] parts = academicyear.split("/");
			int startYear = Integer.parseInt(parts[0]);

			List<String> academicYears = new ArrayList<>();
			for (int i = 0; i < 4; i++) {
			    int year1 = startYear - i;
			    int year2 = year1 + 1;
			    academicYears.add(year1 + "/" + (String.valueOf(year2).substring(2)));
			}
			result.setPreviousAcademicYears(academicYears);
			// end
			
		    result.setSuccess(true);
       } else {
		   result.setSuccess(false);
       }
        }
       return result;
   }

}

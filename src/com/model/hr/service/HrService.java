package com.model.hr.service;


import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.academicyear.dao.YearDAO;
import com.model.academicyear.dto.Currentacademicyear;
import com.model.attendance.dao.AttendanceDAO;
import com.model.attendance.dto.Attendancemaster;
import com.model.attendance.dto.Holidaysmaster;
import com.model.attendance.dto.Weeklyoff;
import com.model.employee.dao.EmployeeDAO;
import com.model.employee.dto.Teacher;
import com.model.employee.service.EmployeeService;
import com.model.hr.dao.HrDAO;
import com.model.hr.dto.Leaveapplication;
import com.model.hr.dto.Leavedetails;
import com.model.hr.dto.Leavetypemaster;
import com.model.hr.dto.Payadvancesalary;
import com.model.hr.dto.Paybasic;
import com.model.hr.dto.Payhead;
import com.model.hr.dto.Payheadstaffdetails;
import com.model.hr.dto.Pf;
import com.model.hr.dto.Processsalarydetails;
import com.model.hr.dto.Processsalarydetailsheads;
import com.util.DataUtil;
import com.util.DateUtil;

public class HrService {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession httpSession;
	private String BRANCHID = "branchid";
	private String USERID = "userloginid";

	public HrService(HttpServletRequest request,
			HttpServletResponse response) {

		this.request = request;
		this.response = response;
		this.httpSession = request.getSession();

	}

	public boolean leaveType() {

		List<Leavetypemaster> list = new ArrayList<Leavetypemaster>();
		
		if(httpSession.getAttribute(BRANCHID)!=null){
			list = new HrDAO().readListOfLeaveTypes(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
		}
		httpSession.setAttribute("leavetypemaster", list);
        	
        return true;
	}

	public boolean saveLeaveType() {
		
		Leavetypemaster leaveMaster = new Leavetypemaster();
		leaveMaster.setLeavetypename(DataUtil.emptyString(request.getParameter("leavetypename")));
		leaveMaster.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
		leaveMaster.setUserid(Integer.parseInt(httpSession.getAttribute(USERID).toString()));
		return new HrDAO().saveLeaveType(leaveMaster);
	}

	public boolean deleteLeaveType() {
		Leavetypemaster leaveType = new Leavetypemaster();
		leaveType.setIdleavetypemaster(Integer.parseInt(DataUtil.emptyString(request.getParameter("idleave"))));
		return new HrDAO().deleteLeaveType(leaveType);
	}

	public boolean addLeaves() {
		String[] leaveTypeName = request.getParameterValues("leavetypename");
		String[] totalLeaves = request.getParameterValues("totalleaves");
		String[] staff = request.getParameterValues("employeeIDs");
		List<Leavedetails> leaveDetailsList = new ArrayList<Leavedetails>();

		if(httpSession.getAttribute("currentAcademicYear")!=null){
		for (String staffId : staff) {
			
			for (int i=0; i<leaveTypeName.length; i++) {
				Leavedetails leaveDetails = new Leavedetails();
				Teacher teacher = new Teacher();
				Leavetypemaster leave = new Leavetypemaster();
				teacher.setTid(Integer.parseInt(staffId));
				leave.setIdleavetypemaster(Integer.parseInt(leaveTypeName[i]));
				leaveDetails.setLeaveTypeMaster(leave);
				leaveDetails.setTeacher(teacher);
				leaveDetails.setNumberofleaves(Integer.parseInt(totalLeaves[i]));
				leaveDetails.setAcademicyear(httpSession.getAttribute("currentAcademicYear").toString());
				leaveDetails.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
				leaveDetails.setUserid(Integer.parseInt(httpSession.getAttribute(USERID).toString()));
				leaveDetailsList.add(leaveDetails);
			}
			
		}
		return new HrDAO().addLeaves(leaveDetailsList);
	}
		return false;
	}

	public boolean viewLeavesDetails() {
		
		Currentacademicyear currentYear = new YearDAO().showYear();
		httpSession.setAttribute("currentAcademicYear",currentYear.getCurrentacademicyear());
		
		List<Leavedetails> leaveDetailsList = new HrDAO().getLeaveDetails(DataUtil.emptyString(request.getParameter("id")),currentYear.getCurrentacademicyear());
		request.setAttribute("leavedetailslist", leaveDetailsList);
		
		if(!leaveDetailsList.isEmpty()){
			request.setAttribute("teachername", leaveDetailsList.get(0).getTeacher().getTeachername());
			httpSession.setAttribute("leavedetailsteachersid", leaveDetailsList.get(0).getTeacher().getTid());
		}
		
		httpSession.setAttribute("academicPerYear", currentYear.getCurrentacademicyear());
		
		return true;
	}

	public boolean leaveDetailsPerYear() {
		
		List<Leavedetails> leaveDetailsList = new HrDAO().getLeaveDetails(DataUtil.emptyString(request.getParameter("leavedetailsteachersid")),DataUtil.emptyString(request.getParameter("academicyear")));
		request.setAttribute("leavedetailslist", leaveDetailsList);
		
		httpSession.setAttribute("academicPerYear", DataUtil.emptyString(request.getParameter("academicyear")));
		
		return true;
	}

	public void payHead() {

		if(httpSession.getAttribute("currentAcademicYear")!=null){
		List<Payhead> payHeadList = new HrDAO().getPayHeadList(httpSession.getAttribute("currentAcademicYear").toString(), Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
		request.setAttribute("payheadlist", payHeadList);
		}
	}

	public boolean savePayHead() {
		
		Payhead payHead = new Payhead();
		if(httpSession.getAttribute("currentAcademicYear")!=null){
		payHead.setPayheadname(DataUtil.emptyString(request.getParameter("payheadname")));
		payHead.setPayheadtype(DataUtil.emptyString(request.getParameter("type")));
		payHead.setValidatory(DataUtil.emptyString(request.getParameter("validatory")));
		payHead.setDescription(DataUtil.emptyString(request.getParameter("description")));
		payHead.setAcademicyear(httpSession.getAttribute("currentAcademicYear").toString());
		payHead.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
		payHead.setUserid(Integer.parseInt(httpSession.getAttribute(USERID).toString()));
		return new HrDAO().savePayHead(payHead);
		}
		return false;
	}

	public boolean addPayHeadStaffDetails() {
		
		if(httpSession.getAttribute("currentAcademicYear")!=null){
			String[] staffIds = request.getParameterValues("employeeIDs");
			String[] values = request.getParameterValues("values");
			String payHeadId = DataUtil.emptyString(request.getParameter("payhead"));
			String amountPerc = DataUtil.emptyString(request.getParameter("amtper"));
			
			List<Payheadstaffdetails> payHeadStaffDetailsList = new ArrayList<Payheadstaffdetails>();
			
			for(int i=0; i<staffIds.length; i++){
				Payheadstaffdetails payHeadStaffDetails = new Payheadstaffdetails();
				Teacher teacher = new Teacher();
				Payhead payHead = new Payhead();
				payHeadStaffDetails.setAcademicyear(httpSession.getAttribute("currentAcademicYear").toString());
				payHeadStaffDetails.setAmountperc(amountPerc);
				teacher.setTid(Integer.parseInt(staffIds[i]));
				payHeadStaffDetails.setTeacher(teacher);
				payHead.setIdpayhead(Integer.parseInt(payHeadId));
				payHeadStaffDetails.setPayhead(payHead);
				payHeadStaffDetails.setValue(new BigDecimal(values[i]));
				payHeadStaffDetails.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
				payHeadStaffDetails.setUserid(Integer.parseInt(httpSession.getAttribute(USERID).toString()));
				payHeadStaffDetailsList.add(payHeadStaffDetails);
			}
			
			
			return new HrDAO().addPayHeadStaffDetails(payHeadStaffDetailsList);
		}
		return false;
	}

	public boolean addBasicPay() {
		
		if(httpSession.getAttribute("currentAcademicYear")!=null){
		String[] staffIds =  request.getParameterValues("employeeIDs");
		String[] basicPay = request.getParameterValues("basicpay");
		String[] paymentType = request.getParameterValues("paymenttype");
		String[] accountNo = request.getParameterValues("accountno");
		String[] overTime = request.getParameterValues("ot");
		List<Integer> overTimeList = new ArrayList<Integer>();
		
		if(overTime != null){
			for (String string : overTime) {
				String[] ot = string.split("_");
				overTimeList.add(Integer.parseInt(ot[1]));
			}
		}
		
		List<Paybasic> payBasicList = new ArrayList<Paybasic>();
		
		for(int i=0; i<staffIds.length; i++){
			Paybasic payBasic = new Paybasic();
			Teacher teacher = new Teacher();
			payBasic.setAcademicyear(httpSession.getAttribute("currentAcademicYear").toString());
			payBasic.setAccountno(accountNo[i]);
			payBasic.setBasicpay(new BigDecimal(basicPay[i]));
			teacher.setTid(Integer.parseInt(staffIds[i]));
			payBasic.setTeacher(teacher);
			if(overTimeList.contains(Integer.parseInt(staffIds[i]))){
				payBasic.setOvertime("yes");
			}else{
				payBasic.setOvertime("no");
			}
			payBasic.setPaymenttype(paymentType[i]);
			payBasic.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
			payBasic.setUserid(Integer.parseInt(httpSession.getAttribute(USERID).toString()));
			payBasicList.add(payBasic);
		}
		return new HrDAO().savePayBasic(payBasicList);
		
		}
		return false;
	}

	public void addPf() {
		String paidByManagement = DataUtil.emptyString(request.getParameter("paidbymanagement"));
		String paidByStaff = DataUtil.emptyString(request.getParameter("paidbystaff"));
		String date = DataUtil.emptyString(request.getParameter("datepf"));
		
		Pf pf = new Pf();
		pf.setDate(DateUtil.dateParserUpdateStd(DataUtil.emptyString(request.getParameter("datepf"))));
		pf.setPaidbyemployee(Integer.parseInt(paidByStaff));
		pf.setPaidbymanagement(Integer.parseInt(paidByManagement));
		pf.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
		pf.setUserid(Integer.parseInt(httpSession.getAttribute(USERID).toString()));
		new HrDAO().addPf(pf);
		
	}

	public void pfSettings() {
		
		List<Pf> pf = new ArrayList<Pf>();
		
		if(httpSession.getAttribute(BRANCHID)!=null){
			pf = new HrDAO().pfSettings(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
		}
		
		request.setAttribute("pflist", pf);
		
	}

	public void deletePf() {
		String[] pfids = request.getParameterValues("pfids");
		if (pfids != null) {
			List ids = new ArrayList();
			for (String id : pfids) {
				ids.add(Integer.valueOf(id));
			}
			new HrDAO().deletePf(ids);
		}
		
	}

	public boolean saveAdvanceSalary() {
		
		Payadvancesalary payAdvanceSalary = new Payadvancesalary();
		Teacher teacher = new Teacher();
		if(httpSession.getAttribute(BRANCHID)!=null){
			payAdvanceSalary.setAmount(new BigDecimal(DataUtil.emptyString(request.getParameter("amount"))));
			payAdvanceSalary.setDeductionpermonth(new BigDecimal(DataUtil.emptyString(request.getParameter("deductionpermonth"))));
			payAdvanceSalary.setDeductionstartmonth(DataUtil.emptyString(request.getParameter("deductionmonth")));
			payAdvanceSalary.setDeductionstartyear(DataUtil.emptyString(request.getParameter("deductionyear")));
			teacher.setTid(Integer.parseInt(DataUtil.emptyString(request.getParameter("staffid"))));
			payAdvanceSalary.setTeacher(teacher);
			payAdvanceSalary.setYear(DataUtil.emptyString(request.getParameter("year")));
			payAdvanceSalary.setMonth(DataUtil.emptyString(request.getParameter("month")));
			payAdvanceSalary.setSalaryfordays(Integer.parseInt(DataUtil.emptyString(request.getParameter("salaryforday"))));
			payAdvanceSalary.setStatus("apply");
			payAdvanceSalary.setDate(DateUtil.dateParserUpdateStd(DataUtil.emptyString(request.getParameter("dateadvance"))));
			payAdvanceSalary.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
			payAdvanceSalary.setUserid(Integer.parseInt(httpSession.getAttribute(USERID).toString()));
			return new HrDAO().saveAdvanceSalary(payAdvanceSalary);	
		}
		return false;
	}

	public void salaryApprovalDispaly() {
		
		List<Payadvancesalary> payAdvanceSalary = new ArrayList<Payadvancesalary>();
		
		if(httpSession.getAttribute(BRANCHID)!=null){
			payAdvanceSalary = new HrDAO().salaryApprovalDispaly(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
		}
		
		request.setAttribute("payadvancesalarylist", payAdvanceSalary);
	}

	public boolean saveAdvanceSalaryApproval() {
		
		String paymentAdvance = request.getParameter("payadvance");
		String reason = request.getParameter("reason_"+paymentAdvance);
		String status = request.getParameter("status_"+paymentAdvance);
		if(httpSession.getAttribute(BRANCHID)!=null && paymentAdvance!=null){
		Payadvancesalary payAdvance = new Payadvancesalary();
		payAdvance.setIdpayadvancesalary(Integer.parseInt(paymentAdvance));
		payAdvance.setReason(DataUtil.emptyString(reason));
		payAdvance.setStatus(DataUtil.emptyString(status));
		payAdvance.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
		
		return new HrDAO().saveAdvanceSalaryApproval(payAdvance);
		}
		return false;
	}

	public boolean deleteAdvaceSalaryApproval() {
		String idPayAdvanceSalary = DataUtil.emptyString(request.getParameter("payadvance"));
		if(!idPayAdvanceSalary.equalsIgnoreCase("")){
		Payadvancesalary payAdvance = new Payadvancesalary();
		payAdvance.setIdpayadvancesalary(Integer.parseInt(idPayAdvanceSalary));
		return new HrDAO().deleteAdvaceSalaryApproval(payAdvance);
		}
		return false;
	}

	public boolean salaryIssue() {
		
		List<Payadvancesalary> payAdvanceSalary = new ArrayList<Payadvancesalary>();
		
		if(httpSession.getAttribute(BRANCHID)!=null){
			payAdvanceSalary = new HrDAO().salaryIssue(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
		}
		
		request.setAttribute("salaryissue", payAdvanceSalary);
		return true;
	}

	public boolean applyLeave() {
		Leaveapplication leaveApplication = new Leaveapplication();
		
		if(httpSession.getAttribute("currentAcademicYear")!=null && httpSession.getAttribute("userAuth") !=null){
			
			leaveApplication.setAcademicyear(httpSession.getAttribute("currentAcademicYear").toString());
			
			leaveApplication.setLeavetype(DataUtil.emptyString(request.getParameter("leavetypename")));
			leaveApplication.setReason(DataUtil.emptyString(request.getParameter("reason")));
			leaveApplication.setStatus("pending");
			leaveApplication.setFromdate(DateUtil.dateParserUpdateStd(request.getParameter("fromdate")));
			leaveApplication.setTodate(DateUtil.dateParserUpdateStd(request.getParameter("todate")));
			String userName = httpSession.getAttribute("username").toString();
				Teacher teacher = new EmployeeDAO().getEmployeeDetails(userName);
				Teacher addTeacher = new Teacher();
				addTeacher.setTid(teacher.getTid());
				leaveApplication.setTeacher(addTeacher);
				int totalLeaves = calculateLeaves(DateUtil.dateParserUpdateStd(request.getParameter("fromdate")),DateUtil.dateParserUpdateStd(request.getParameter("todate")),teacher.getTid());
				if(totalLeaves==0){
					return false;
				}
				leaveApplication.setTotalleaves(totalLeaves);
				leaveApplication.setDateofapply(new Date());
				leaveApplication.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
				leaveApplication.setUserid(Integer.parseInt(httpSession.getAttribute(USERID).toString()));
				return new HrDAO().applyLeave(leaveApplication);
		}
		
		
		return false;
	}

	private Integer calculateLeaves(Date fromDate,
			Date toDate, int teacherid) {

	     int totalLeaves = 0;
		Calendar start = Calendar.getInstance();
		start.setTime(fromDate);
		Calendar end = Calendar.getInstance();
		end.setTime(toDate);
		end.add(Calendar.DATE, 1);
		
	       for (Date date = start.getTime(); start.before(end); start.add(Calendar.DAY_OF_MONTH, +1), date = start.getTime()) {
				try {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					date = sdf.parse(sdf.format(date)) ;
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Currentacademicyear currentAcademicYear = new YearDAO().showYear();
				
					List<Attendancemaster> staffAttendanceMaster = new AttendanceDAO().getAttendanceMasterDetails(Integer.toString((teacherid)));
					if(staffAttendanceMaster.get(0).getWeeklyoff() == null){
						return 0;
					}
					String[] weeklyOffString = staffAttendanceMaster.get(0).getWeeklyoff().split(",");
					List<Integer> staffWeeklyOffList = new ArrayList<Integer>();
					boolean staffWeeklyOff = false;
					boolean staffHoliday = false;
					for (String weekOffS : weeklyOffString) {
						staffWeeklyOffList.add(Integer.parseInt(weekOffS));
					}
					List<Weeklyoff> staffWeekOff = new AttendanceDAO().readListOfWeeklyOff(staffWeeklyOffList, currentAcademicYear.getCurrentacademicyear());
					for (Weeklyoff weeklyoff : staffWeekOff) {
						if(weeklyoff.getWeeklyoffday().equalsIgnoreCase(new SimpleDateFormat("EEEE").format(date))){
							staffWeeklyOff = true;
						}
					}
					
					if(!staffWeeklyOff){
						String[] holidayString = staffAttendanceMaster.get(0).getHolidayname().split(",");
						List<Integer> staffHolidayList = new ArrayList<Integer>();
						for (String singleHoliday : holidayString) {
							staffHolidayList.add(Integer.parseInt(singleHoliday));
						}
						List<Holidaysmaster> staffHolidays = new AttendanceDAO().readListOfholidays(staffHolidayList, currentAcademicYear.getCurrentacademicyear());
						for (Holidaysmaster holidaysmaster : staffHolidays) {
							Date fromDateHm = holidaysmaster.getFromdate();
							Date toDateHm = holidaysmaster.getTodate();
							if(fromDateHm.compareTo(date) * date.compareTo(toDateHm) >= 0){
								staffHoliday = true;
							}
						}
						}
					if(!staffWeeklyOff && !staffHoliday){
						totalLeaves++;
				}
			}
		return totalLeaves;
	}

	public boolean leaveApprovals() {
		
		if(httpSession.getAttribute("currentAcademicYear")!=null){
			List<Leaveapplication> listLeaveApplication = new HrDAO().leaveApprovals(httpSession.getAttribute("currentAcademicYear").toString(), Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
			request.setAttribute("leaveapplicationlist", listLeaveApplication);
			return true;
		}
		return false;
	}

	public boolean rejectLeave() {
		
		String[] idleaveapplication = request.getParameterValues("idleaveapplication");
		
		if (idleaveapplication != null) {
			List ids = new ArrayList();
			for (String id : idleaveapplication) {
				ids.add(Integer.valueOf(id));
			}
			return new HrDAO().rejectLeave(ids);
		}
		
		return false;
	}

	public boolean approveLeave() {
		
		String[] idleaveapplication = request.getParameterValues("idleaveapplication");
		
		if (idleaveapplication != null) {
			List ids = new ArrayList();
			for (String id : idleaveapplication) {
				ids.add(Integer.valueOf(id));
			}
			return new HrDAO().approveLeave(ids);
		}
		
		return false;
	}

	public boolean processStaffSalary() {
		
		String[] staffids = request.getParameterValues("employeeIDs");
		Map<String, BigDecimal> earningMaps = new HashMap<String, BigDecimal>();
		Map<String, BigDecimal> deductionMaps = new HashMap<String, BigDecimal>();
		List<Processsalarydetailsheads> processSalarydetailsheadList = new ArrayList<Processsalarydetailsheads>();
		List<Processsalarydetails> processsalarydetailsList = new ArrayList<Processsalarydetails>(); 
		
		if(httpSession.getAttribute("currentAcademicYear")!=null){
			
		for (String staffid : staffids) {
				
			
			if(!checkprocessedStaffSalary(Integer.parseInt(staffid),DataUtil.emptyString(request.getParameter("month")),
					  DataUtil.emptyString(request.getParameter("year")))){
			
			BigDecimal totalEarnings = BigDecimal.ZERO;
			BigDecimal totalDeduction = BigDecimal.ZERO;
			
			//get all the earnings
				
				//get the basic pay
			
				Paybasic basicPay = new HrDAO().getBasicPay(Integer.parseInt(staffid),httpSession.getAttribute("currentAcademicYear").toString());
				BigDecimal basicPayStaff = basicPay.getBasicpay();
				
				
				Processsalarydetailsheads processHeadsBasic = new Processsalarydetailsheads();
				processHeadsBasic.setPayheadname("Basic Pay");
				processHeadsBasic.setPayheadtype("Earning");
				processHeadsBasic.setAmount(basicPay.getBasicpay());
				processHeadsBasic.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
				processSalarydetailsheadList.add(processHeadsBasic);   
	               
				// earning/deduction calculations
				List<Payheadstaffdetails> payheadstaffdetailsList = new HrDAO().getPayHeadStaff(Integer.parseInt(staffid),httpSession.getAttribute("currentAcademicYear").toString());
					
				if(!payheadstaffdetailsList.isEmpty()){
					for (Payheadstaffdetails payheadstaffdetails : payheadstaffdetailsList) {
						
						Processsalarydetailsheads processHeads = new Processsalarydetailsheads();
			               processHeads.setPayheadname(payheadstaffdetails.getPayhead().getPayheadname());
			               processHeads.setPayheadtype(payheadstaffdetails.getPayhead().getPayheadtype());
						
						if("Earning".equalsIgnoreCase(payheadstaffdetails.getPayhead().getPayheadtype())){
							
							String headName = payheadstaffdetails.getPayhead().getPayheadname();
							String amountPer = payheadstaffdetails.getAmountperc();
							
							if("percentage".equalsIgnoreCase(amountPer)){
								BigDecimal totalAmount = basicPayStaff.multiply( payheadstaffdetails.getValue()).divide(new BigDecimal(100));
								 processHeads.setAmount(totalAmount);
								 earningMaps.put(headName, totalAmount);
								 totalEarnings = totalEarnings.add(totalAmount);
							}else{
								processHeads.setAmount(payheadstaffdetails.getValue());
								earningMaps.put(headName, payheadstaffdetails.getValue());
								totalEarnings = totalEarnings.add(payheadstaffdetails.getValue());
							}
						}else{
							String headName = payheadstaffdetails.getPayhead().getPayheadname();
							String amountPer = payheadstaffdetails.getAmountperc();
							
							if("percentage".equalsIgnoreCase(amountPer)){
								BigDecimal totalAmount = basicPayStaff.multiply( payheadstaffdetails.getValue()).divide(new BigDecimal(100));
								processHeads.setAmount(totalAmount);
								deductionMaps.put(headName, totalAmount);
								totalDeduction = totalDeduction.add(totalAmount);
							}else{
								processHeads.setAmount(payheadstaffdetails.getValue());
								deductionMaps.put(headName, payheadstaffdetails.getValue());
								totalDeduction = totalDeduction.add(payheadstaffdetails.getValue());
							}
						}
						processHeads.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
						processHeads.setUserid(Integer.parseInt(httpSession.getAttribute(USERID).toString()));
						processSalarydetailsheadList.add(processHeads);
					}
				}else{
					return false;
				}
				
				 
				// Process Salary
				   Processsalarydetails processSalary = new Processsalarydetails();
			       Teacher teacher = new Teacher();
			       teacher.setTid(Integer.parseInt(staffid));
			       processSalary.setTeacher(teacher);
			       processSalary.setMonth(DataUtil.emptyString(request.getParameter("month")));
			       processSalary.setYear(Integer.parseInt(DataUtil.emptyString(request.getParameter("year"))));
			       processSalary.setStatus("PROCESSED");
			       processSalary.setAcademicyear(httpSession.getAttribute("currentAcademicYear").toString());
			       processSalary.setProcesseddate(DateUtil.dateParserUpdateStd(request.getParameter("dateprocess")));
			       processSalary.setPaymenttype(basicPay.getPaymenttype());
			       BigDecimal netPayment = basicPayStaff.add(totalEarnings);
			       netPayment = netPayment.subtract(totalDeduction);
			       processSalary.setNetpayment(netPayment);	
			       processSalary.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
			       processSalary.setUserid(Integer.parseInt(httpSession.getAttribute(USERID).toString()));
			       processsalarydetailsList.add(processSalary);
			       
			}
		}
		
		if(!processsalarydetailsList.isEmpty()){
			return new HrDAO().processStaffSalary(processsalarydetailsList,processSalarydetailsheadList);
		}
		
		}
		return false;
	}

	public boolean getPayHead() throws IOException {


		if(httpSession.getAttribute("currentAcademicYear")!=null){
			String payHeadType = request.getParameter("payHeadType");
		List<Payhead> payHeadList = new HrDAO().getPayHeadListDynamic(payHeadType,httpSession.getAttribute("currentAcademicYear").toString(), Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
		request.setAttribute("payheadlistdynamic", payHeadList);
		
		
		PrintWriter out = response.getWriter(); 
		response.setContentType("text/xml");
	        response.setHeader("Cache-Control", "no-cache");
	        try {
	        	
	        	
	        	if(!payHeadList.isEmpty()){
	        		String buffer = "<select name='payhead' style='width: 240px' id='payhead' >";
		        	for(int i =0; i<payHeadList.size();i++){
		        		buffer = buffer +  "<option value="+payHeadList.get(i).getIdpayhead()+">"+payHeadList.get(i).getPayheadname()+"</option>";
		        	}
		        	response.getWriter().println(buffer);
	        	}else{
	        		String buffer = "<select name='payhead' style='width: 240px' id='payhead'>";
	        		buffer = buffer+"<option></option>";
		        	buffer = buffer+"</select>";
		        	response.getWriter().println(buffer);
	        	}
	        	
	        } catch (Exception e) {
	            out.write("<subgroup>0</subgroup>");
	        } finally {
	            out.flush();
	            out.close();
	        }
	        
		}
	return true;
	}

	public boolean issueStaffSalary() {

		if(httpSession.getAttribute("currentAcademicYear")!=null){
			
			List<Processsalarydetails> processSalaryDetailsList = new HrDAO().issueStaffSalary(httpSession.getAttribute("currentAcademicYear").toString(), Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
			request.setAttribute("processsalarydetailslist", processSalaryDetailsList);
			
			if(processSalaryDetailsList.isEmpty()){
				return false;
			}
			return true;
		}
		return false;
	}

	public void printSalarySlip() {
		
		String processSalaryId = DataUtil.emptyString(request.getParameter("salaryid"));
		
		if(processSalaryId!=null){
			
			Map<String, BigDecimal> earningsMap = new LinkedHashMap<String, BigDecimal>();
			Map<String, BigDecimal> deductionsMap = new LinkedHashMap<String, BigDecimal>();
			BigDecimal totalEarnings = BigDecimal.ZERO;
			BigDecimal totalDeductions = BigDecimal.ZERO;
			
			Processsalarydetails processSalaryDetails = new HrDAO().getProcessSalaryDetails(Integer.parseInt(processSalaryId));
			request.setAttribute("processsalarydetails", processSalaryDetails);
			
			List<Processsalarydetailsheads> processSalaryHeads = new HrDAO().getProcessSalaryHeads(Integer.parseInt(processSalaryId));
			
			for (Processsalarydetailsheads processsalarydetailsheads : processSalaryHeads) {
				
				if("Earning".equalsIgnoreCase(processsalarydetailsheads.getPayheadtype())){
					
					totalEarnings = totalEarnings.add(processsalarydetailsheads.getAmount());
					earningsMap.put(processsalarydetailsheads.getPayheadname(), processsalarydetailsheads.getAmount());

				}else if("Deduction".equalsIgnoreCase(processsalarydetailsheads.getPayheadtype())){
					
					totalDeductions = totalDeductions.add(processsalarydetailsheads.getAmount());
					deductionsMap.put(processsalarydetailsheads.getPayheadname(), processsalarydetailsheads.getAmount());
				}
				
			}
			
			request.setAttribute("earningmap", earningsMap);
			request.setAttribute("deductionmap", deductionsMap);
			request.setAttribute("totalearning", totalEarnings);
			request.setAttribute("totaldeduction", totalDeductions);
			request.setAttribute("netpay", processSalaryDetails.getNetpayment());
		}
		
	}

	public void getStaffDetails() {
		
		if(httpSession.getAttribute("currentAcademicYear")!=null){
			
			String staffId = request.getParameter("staffid");
			List<Payheadstaffdetails> payHeadDetailsList = new HrDAO().getStaffDetails(Integer.parseInt(staffId), httpSession.getAttribute("currentAcademicYear").toString());
			request.setAttribute("payheaddetailslist", payHeadDetailsList);
		}
		
	}

	public boolean deletePayHeadStaff() {
		
		String[] StaffId = request.getParameterValues("teacherid");
		String[] idpayheadstaffdetails = request.getParameterValues("idpayheadstaffdetails");
		
		List ids = new ArrayList();
		
		if (idpayheadstaffdetails != null) {
			for (String id : idpayheadstaffdetails) {
				ids.add(Integer.valueOf(id));
			}
		}
		
		List<Processsalarydetails> processSalaryDetails = new HrDAO().getStaffinfo(Integer.parseInt(StaffId[0]));
		
		if(processSalaryDetails.isEmpty()){
				if(new HrDAO().deletePayHeadStaff(ids))
				{
					List<Payheadstaffdetails> payHeadDetailsList = new HrDAO().getStaffDetails(Integer.parseInt(StaffId[0]), httpSession.getAttribute("currentAcademicYear").toString());
					request.setAttribute("payheaddetailslist", payHeadDetailsList);
					return true;
					
				}
		}
		
		return false;
	}

	public boolean checkprocessedStaffSalary(int staffId, String month, String year) {
			
		Processsalarydetails processSalaryDetails = new HrDAO().checkprocessedStaffSalary(staffId,month,year);
		
			if(processSalaryDetails!=null){
				return true;
			}
			
		return false;
	}

	public boolean issueProcessedSalary() {

		String[] idProcessSalaryDetails = request.getParameterValues("idprocesssalarydetails");
		boolean result = false;
		
		if (idProcessSalaryDetails != null) {
			List ids = new ArrayList();
			for (String id : idProcessSalaryDetails) {
				ids.add(Integer.valueOf(id));
			}
			result = new HrDAO().issueProcessedSalary(ids);
		}
		issueStaffSalary();
		return result;
	}

	public boolean cancelProcessedSalary() {
		
		String[] idProcessSalaryDetails = request.getParameterValues("idprocesssalarydetails");
		boolean result = false;
		
		if (idProcessSalaryDetails != null) {
			List ids = new ArrayList();
			for (String id : idProcessSalaryDetails) {
				ids.add(Integer.valueOf(id));
			}
			result = new HrDAO().cancelProcessedSalary(ids);
		}
		issueStaffSalary();
		return result;
	}
	
public void updateBasicpayEmployees() {
		
		if(httpSession.getAttribute(BRANCHID)!=null){
		String[] staffIds =  request.getParameterValues("employeeIDs");
		String[] basicPay = request.getParameterValues("basicpay");
		String[] paymentType = request.getParameterValues("paymenttype");
		String[] accountNo = request.getParameterValues("accountno");
		String[] overTime = request.getParameterValues("overtime");
		String[] academicYear = request.getParameterValues("academicyear");
		
		List<Integer> overTimeList = new ArrayList<Integer>();
		
		if(overTime != null){
			for (String string : overTime) {
				String[] ot = string.split("_");
				overTimeList.add(Integer.parseInt(ot[1]));
			}
		}
		
		
		List<Paybasic> payBasicList = new ArrayList<Paybasic>();
		
		for(int i=0; i<staffIds.length; i++){
			String[] splitId = staffIds[i].split(":");
			Paybasic payBasic = new Paybasic();
			Teacher teacher = new Teacher();
			payBasic.setIdpaybasic(Integer.parseInt(splitId[2]));
			payBasic.setAcademicyear(academicYear[Integer.parseInt(splitId[1])]);
			payBasic.setAccountno(accountNo[Integer.parseInt(splitId[1])]);
			payBasic.setBasicpay(new BigDecimal(basicPay[Integer.parseInt(splitId[1])]));
			teacher.setTid(Integer.parseInt(splitId[0]));
			payBasic.setTeacher(teacher);
			if(overTimeList.contains(Integer.parseInt(splitId[0]))){
				payBasic.setOvertime("yes");
			}else{
				payBasic.setOvertime("no");
			}
			payBasic.setPaymenttype(paymentType[Integer.parseInt(splitId[1])]);
			payBasic.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
			
			payBasicList.add(payBasic);
		}
		 boolean result = new HrDAO().updatePayBasic(payBasicList);
			 request.setAttribute("basicpayupdate", result);
		}
		new EmployeeService(request, response).basicpayEmployees();
	}
	
}

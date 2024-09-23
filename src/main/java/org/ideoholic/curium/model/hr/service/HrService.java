package org.ideoholic.curium.model.hr.service;


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

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.academicyear.dao.YearDAO;
import org.ideoholic.curium.model.academicyear.dto.Currentacademicyear;
import org.ideoholic.curium.model.attendance.dao.AttendanceDAO;
import org.ideoholic.curium.model.attendance.dto.Attendancemaster;
import org.ideoholic.curium.model.attendance.dto.Holidaysmaster;
import org.ideoholic.curium.model.attendance.dto.Weeklyoff;
import org.ideoholic.curium.model.employee.action.EmployeeActionAdapter;
import org.ideoholic.curium.model.employee.dao.EmployeeDAO;
import org.ideoholic.curium.model.employee.dto.Teacher;
import org.ideoholic.curium.model.hr.dao.HrDAO;
import org.ideoholic.curium.model.hr.dto.*;
import org.ideoholic.curium.util.DataUtil;
import org.ideoholic.curium.util.DateUtil;

public class HrService {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession httpSession;
	private String BRANCHID = "branchid";
	private String USERID = "userloginid";
	private EmployeeActionAdapter employeeActionAdapter;

	public HrService(HttpServletRequest request,
			HttpServletResponse response) {

		this.request = request;
		this.response = response;
		this.httpSession = request.getSession();

	}

	public LeaveTypeResponseDto leaveType(String branchId) {
        LeaveTypeResponseDto leaveTypeResponseDto = new LeaveTypeResponseDto();

		List<Leavetypemaster> list = new ArrayList<>();
		
		if(branchId!=null){
			list = new HrDAO().readListOfLeaveTypes(Integer.parseInt(branchId));
		}
        leaveTypeResponseDto.setLeavetypemaster(list);
		leaveTypeResponseDto.setSuccess(true);

        return leaveTypeResponseDto;
	}

	public ResultResponse saveLeaveType(LeaveTypeDto dto, String branchId, String userId) {
		
		Leavetypemaster leaveMaster = new Leavetypemaster();
		leaveMaster.setLeavetypename(DataUtil.emptyString(dto.getLeaveTypeName()));
		leaveMaster.setBranchid(Integer.parseInt(branchId));
		leaveMaster.setUserid(Integer.parseInt(userId));

		return ResultResponse.builder().success(new HrDAO().saveLeaveType(leaveMaster)).build();

	}

	public ResultResponse deleteLeaveType(LeaveTypeDto dto) {
		Leavetypemaster leaveType = new Leavetypemaster();
		leaveType.setIdleavetypemaster(Integer.parseInt(DataUtil.emptyString(dto.getIdLeave())));

		return ResultResponse.builder().success(new HrDAO().deleteLeaveType(leaveType)).build();

	}

	public ResultResponse addLeaves(LeaveTypeDto dto, String currentAcademicYear, String branchId, String userId) {
		String[] leaveTypeName = dto.getLeaveTypeNames();
		String[] totalLeaves = dto.getTotalLeaves();
		String[] staff = dto.getStaff();
		List<Leavedetails> leaveDetailsList = new ArrayList<>();

		if(currentAcademicYear!=null){
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
				leaveDetails.setAcademicyear(currentAcademicYear);
				leaveDetails.setBranchid(Integer.parseInt(branchId));
				leaveDetails.setUserid(Integer.parseInt(userId));
				leaveDetailsList.add(leaveDetails);
			}
			
		}
		return ResultResponse.builder().success(new HrDAO().addLeaves(leaveDetailsList)).build();

	}
		return ResultResponse.builder().build();
	}

	public LeavesDetailsResponseDto viewLeavesDetails() {
		LeavesDetailsResponseDto result = new LeavesDetailsResponseDto();
		
		Currentacademicyear currentYear = new YearDAO().showYear();

		result.setCurrentAcademicYear(currentYear);
		
		List<Leavedetails> leaveDetailsList = new HrDAO().getLeaveDetails(DataUtil.emptyString(request.getParameter("id")),currentYear.getCurrentacademicyear());
		result.setLeaveDetailsList(leaveDetailsList);
		
		if(!leaveDetailsList.isEmpty()){
			result.setTeacherName( leaveDetailsList.get(0).getTeacher().getTeachername());
			result.setLeaveDetailsTeachersId(leaveDetailsList.get(0).getTeacher().getTid().toString());
			result.setSuccess(true);
		}

		result.setAcademicPerYear(currentYear.getCurrentacademicyear());
		
		return result;
	}

	public LeavesDetailsResponseDto leaveDetailsPerYear(LeaveDetailsDto dto) {
		LeavesDetailsResponseDto result = new LeavesDetailsResponseDto();

		List<Leavedetails> leaveDetailsList = new HrDAO().getLeaveDetails(DataUtil.emptyString(dto.getLeaveDetailsTeachersId()),DataUtil.emptyString(dto.getAcademicYear()));
		result.setLeaveDetailsList(leaveDetailsList);

		
		result.setAcademicPerYear(dto.getAcademicYear());
		result.setSuccess(true);
		
		return result;
	}

	public PayHeadResponseDto payHead(String currentAcademicYear, String branchId) {
        PayHeadResponseDto result = new PayHeadResponseDto();

		if(currentAcademicYear!=null){
		List<Payhead> payHeadList = new HrDAO().getPayHeadList(currentAcademicYear, Integer.parseInt(branchId));
		result.setPayHeadList(payHeadList);
		}
		return result;
	}

	public ResultResponse savePayHead(PayHeadDto dto, String currentAcademicYear, String branchId, String userId) {

		Payhead payHead = new Payhead();
		if(currentAcademicYear!=null){
		payHead.setPayheadname(DataUtil.emptyString(dto.getPayHeadName()));
		payHead.setPayheadtype(DataUtil.emptyString(dto.getType()));
		payHead.setValidatory(DataUtil.emptyString(dto.getValidatory()));
		payHead.setDescription(DataUtil.emptyString(dto.getDescription()));
		payHead.setAcademicyear(currentAcademicYear);
		payHead.setBranchid(Integer.parseInt(branchId));
		payHead.setUserid(Integer.parseInt(userId));

		return ResultResponse.builder().success(new HrDAO().savePayHead(payHead)).build();
		}
		return ResultResponse.builder().build();
	}

	public ResultResponse addPayHeadStaffDetails(PayHeadStaffDetailsDto dto, String currentAcademicYear, String branchId, String userId) {
		
		if(currentAcademicYear!=null){
			String[] staffIds = dto.getStaffIds();
			String[] values = dto.getValues();
			String payHeadId = DataUtil.emptyString(dto.getPayHeadId());
			String amountPerc = DataUtil.emptyString(dto.getAmountPer());
			
			List<Payheadstaffdetails> payHeadStaffDetailsList = new ArrayList<>();
			
			for(int i=0; i<staffIds.length; i++){
				Payheadstaffdetails payHeadStaffDetails = new Payheadstaffdetails();
				Teacher teacher = new Teacher();
				Payhead payHead = new Payhead();
				payHeadStaffDetails.setAcademicyear(currentAcademicYear);
				payHeadStaffDetails.setAmountperc(amountPerc);
				teacher.setTid(Integer.parseInt(staffIds[i]));
				payHeadStaffDetails.setTeacher(teacher);
				payHead.setIdpayhead(Integer.parseInt(payHeadId));
				payHeadStaffDetails.setPayhead(payHead);
				payHeadStaffDetails.setValue(new BigDecimal(values[i]));
				payHeadStaffDetails.setBranchid(Integer.parseInt(branchId));
				payHeadStaffDetails.setUserid(Integer.parseInt(userId));
				payHeadStaffDetailsList.add(payHeadStaffDetails);
			}
			
			
			return ResultResponse.builder().success(new HrDAO().addPayHeadStaffDetails(payHeadStaffDetailsList)).build();
		}
		return ResultResponse.builder().build();
	}

	public ResultResponse addBasicPay(BasicPayDto dto, String currentAcademicYear, String branchId, String userId) {
		
		if(currentAcademicYear!=null){
		String[] staffIds = dto.getStaffIds();
		String[] basicPay = dto.getBasicPay();
		String[] paymentType = dto.getPaymentType();
		String[] accountNo = dto.getAccountNo();
		String[] overTime = dto.getOverTime();
		List<Integer> overTimeList = new ArrayList<>();
		
		if(overTime != null){
			for (String string : overTime) {
				String[] ot = string.split("_");
				overTimeList.add(Integer.parseInt(ot[1]));
			}
		}
		
		List<Paybasic> payBasicList = new ArrayList<>();
		
		for(int i=0; i<staffIds.length; i++){
			Paybasic payBasic = new Paybasic();
			Teacher teacher = new Teacher();
			payBasic.setAcademicyear(currentAcademicYear);
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
			payBasic.setBranchid(Integer.parseInt(branchId));
			payBasic.setUserid(Integer.parseInt(userId));
			payBasicList.add(payBasic);
		}
		return ResultResponse.builder().success(new HrDAO().savePayBasic(payBasicList)).build();
		
		}
		return ResultResponse.builder().build();
	}

	public void addPf(PfDto dto, String branchId, String userId) {
		String paidByManagement = DataUtil.emptyString(dto.getPaidByManagement());
		String paidByStaff = DataUtil.emptyString(dto.getPaidByStaff());
		String date = DataUtil.emptyString(dto.getDate());
		
		Pf pf = new Pf();
		pf.setDate(DateUtil.dateParserUpdateStd(DataUtil.emptyString(dto.getDate())));
		pf.setPaidbyemployee(Integer.parseInt(paidByStaff));
		pf.setPaidbymanagement(Integer.parseInt(paidByManagement));
		pf.setBranchid(Integer.parseInt(branchId));
		pf.setUserid(Integer.parseInt(userId));
		new HrDAO().addPf(pf);

	}

	public PfSettingsResponseDto pfSettings(String branchId) {
		PfSettingsResponseDto result = new PfSettingsResponseDto();

		List<Pf> pf = new ArrayList<>();
		
		if(branchId!=null){
			pf = new HrDAO().pfSettings(Integer.parseInt(branchId));
		}

		result.setPf(pf);

		return  result;

		
	}

	public void deletePf(PfDto dto) {
		String[] pfids = dto.getPfids();
		if (pfids != null) {
			List<Integer> ids = new ArrayList<>();
			for (String id : pfids) {
				ids.add(Integer.valueOf(id));
			}
			new HrDAO().deletePf(ids);
		}

		
	}

	public ResultResponse saveAdvanceSalary(SaveAdvanceSalaryDto dto, String branchId, String userId) {
		
		Payadvancesalary payAdvanceSalary = new Payadvancesalary();
		Teacher teacher = new Teacher();
		if(branchId!=null){
			payAdvanceSalary.setAmount(new BigDecimal(DataUtil.emptyString(dto.getAmount())));
			payAdvanceSalary.setDeductionpermonth(new BigDecimal(DataUtil.emptyString(dto.getDeductionPerMonth())));
			payAdvanceSalary.setDeductionstartmonth(DataUtil.emptyString(dto.getDeductionMonth()));
			payAdvanceSalary.setDeductionstartyear(DataUtil.emptyString(dto.getDeductionYear()));
			teacher.setTid(Integer.parseInt(DataUtil.emptyString(dto.getStaffId())));
			payAdvanceSalary.setTeacher(teacher);
			payAdvanceSalary.setYear(DataUtil.emptyString(dto.getYear()));
			payAdvanceSalary.setMonth(DataUtil.emptyString(dto.getMonth()));
			payAdvanceSalary.setSalaryfordays(Integer.parseInt(DataUtil.emptyString(dto.getSalaryForDay())));
			payAdvanceSalary.setStatus("apply");
			payAdvanceSalary.setDate(DateUtil.dateParserUpdateStd(DataUtil.emptyString(dto.getDateAdvance())));
			payAdvanceSalary.setBranchid(Integer.parseInt(branchId));
			payAdvanceSalary.setUserid(Integer.parseInt(userId));
			return ResultResponse.builder().success( new HrDAO().saveAdvanceSalary(payAdvanceSalary)).build();
		}
		return ResultResponse.builder().build();
	}

	public SalaryResponseDto salaryApprovalDispaly(String branchId) {
		SalaryResponseDto result = new SalaryResponseDto();

		List<Payadvancesalary> payAdvanceSalary = new ArrayList<>();

		if (branchId != null) {
			payAdvanceSalary = new HrDAO().salaryApprovalDispaly(Integer.parseInt(branchId));
		}

		result.setPayAdvanceSalary(payAdvanceSalary);

		return result;
	}

	public ResultResponse saveAdvanceSalaryApproval(AdvanceSalaryApprovalDto dto ,String branchId) {
		
		String paymentAdvance = dto.getPaymentAdvance();
		String reason = dto.getReason();
		String status = dto.getStatus();
		if(branchId!=null && paymentAdvance!=null){
		Payadvancesalary payAdvance = new Payadvancesalary();
		payAdvance.setIdpayadvancesalary(Integer.parseInt(paymentAdvance));
		payAdvance.setReason(DataUtil.emptyString(reason));
		payAdvance.setStatus(DataUtil.emptyString(status));
		payAdvance.setBranchid(Integer.parseInt(branchId));
		
		return ResultResponse.builder().success(new HrDAO().saveAdvanceSalaryApproval(payAdvance)).build();
		}
		return ResultResponse.builder().build();
	}

	public ResultResponse deleteAdvaceSalaryApproval(DeleteAdvaceSalaryApprovalDto dto) {
		String idPayAdvanceSalary = DataUtil.emptyString(dto.getIdPayAdvanceSalary());
		if(!idPayAdvanceSalary.equalsIgnoreCase("")){
		Payadvancesalary payAdvance = new Payadvancesalary();
		payAdvance.setIdpayadvancesalary(Integer.parseInt(idPayAdvanceSalary));
		return ResultResponse.builder().success(new HrDAO().deleteAdvaceSalaryApproval(payAdvance)).build();
		}
		return ResultResponse.builder().success(false).build();
	}

	public SalaryResponseDto salaryIssue(String branchId) {
		SalaryResponseDto result = new SalaryResponseDto();

		List<Payadvancesalary> payAdvanceSalary = new ArrayList<>();
		
		if(branchId!=null){
			payAdvanceSalary = new HrDAO().salaryIssue(Integer.parseInt(branchId));
		}
		result.setPayAdvanceSalary(payAdvanceSalary);
		result.setSuccess(true);

		return result;
	}

	public ResultResponse applyLeave(ApplyLeaveDto dto,String currentAcademicyear, String userAuth, String username, String branchId, String userId) {
		Leaveapplication leaveApplication = new Leaveapplication();
		
		if(currentAcademicyear!=null && userAuth!=null){
			
			leaveApplication.setAcademicyear(currentAcademicyear);
			
			leaveApplication.setLeavetype(DataUtil.emptyString(dto.getLeaveTypeName()));
			leaveApplication.setReason(DataUtil.emptyString(dto.getReason()));
			leaveApplication.setStatus("pending");
			leaveApplication.setFromdate(DateUtil.dateParserUpdateStd(dto.getFromDate()));
			leaveApplication.setTodate(DateUtil.dateParserUpdateStd(dto.getToDate()));
			String userName = username;
				Teacher teacher = new EmployeeDAO().getEmployeeDetails(userName);
				Teacher addTeacher = new Teacher();
				addTeacher.setTid(teacher.getTid());
				leaveApplication.setTeacher(addTeacher);
				int totalLeaves = calculateLeaves(DateUtil.dateParserUpdateStd(dto.getFromDate()),DateUtil.dateParserUpdateStd(dto.getToDate()),teacher.getTid());
				if(totalLeaves==0){
					return ResultResponse.builder().success(false).build();
				}
				leaveApplication.setTotalleaves(totalLeaves);
				leaveApplication.setDateofapply(new Date());
				leaveApplication.setBranchid(Integer.parseInt(branchId));
				leaveApplication.setUserid(Integer.parseInt(userId));

				return ResultResponse.builder().success( new HrDAO().applyLeave(leaveApplication)).build();
		}
		
		
		return ResultResponse.builder().build();
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
					List<Integer> staffWeeklyOffList = new ArrayList<>();
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
						List<Integer> staffHolidayList = new ArrayList<>();
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

	public LeaveApprovalsResponseDto leaveApprovals(String currentAcademicYear,String branchId) {
		LeaveApprovalsResponseDto result = new LeaveApprovalsResponseDto();

		if(currentAcademicYear!=null){
			List<Leaveapplication> listLeaveApplication = new HrDAO().leaveApprovals(currentAcademicYear, Integer.parseInt(branchId));
			result.setListLeaveApplication(listLeaveApplication);
			result.setSuccess(true);
		}
		return result;
	}

	public ResultResponse rejectLeave(RejectLeaveDto dto) {
		
		String[] idleaveapplication = dto.getIdleaveapplication();
		
		if (idleaveapplication != null) {
			List<Integer> ids = new ArrayList<>();
			for (String id : idleaveapplication) {
				ids.add(Integer.valueOf(id));
			}
			return ResultResponse.builder().success( new HrDAO().rejectLeave(ids)).build();
		}
		
		return ResultResponse.builder().build();
	}

	public boolean approveLeave() {
		
		String[] idleaveapplication = request.getParameterValues("idleaveapplication");
		
		if (idleaveapplication != null) {
			List<Integer> ids = new ArrayList<>();
			for (String id : idleaveapplication) {
				ids.add(Integer.valueOf(id));
			}
			return new HrDAO().approveLeave(ids);
		}
		
		return false;
	}

	public boolean processStaffSalary() {
		
		String[] staffids = request.getParameterValues("employeeIDs");
		Map<String, BigDecimal> earningMaps = new HashMap<>();
		Map<String, BigDecimal> deductionMaps = new HashMap<>();
		List<Processsalarydetailsheads> processSalarydetailsheadList = new ArrayList<>();
		List<Processsalarydetails> processsalarydetailsList = new ArrayList<>();
		
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
			
			Map<String, BigDecimal> earningsMap = new LinkedHashMap<>();
			Map<String, BigDecimal> deductionsMap = new LinkedHashMap<>();
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
		
		List<Integer> ids = new ArrayList<>();
		
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
			List<Integer> ids = new ArrayList<>();
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
			List<Integer> ids = new ArrayList<>();
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
		
		List<Integer> overTimeList = new ArrayList<>();
		
		if(overTime != null){
			for (String string : overTime) {
				String[] ot = string.split("_");
				overTimeList.add(Integer.parseInt(ot[1]));
			}
		}
		
		
		List<Paybasic> payBasicList = new ArrayList<>();
		
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
		employeeActionAdapter.basicpayEmployees();
	}
	
}

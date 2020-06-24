package org.ideoholic.hr.service;

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

import org.ideoholic.employee.service.EmployeeServiceImpl;

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

public class HrServiceImpl implements HrService {
	
	EmployeeServiceImpl employee;
	
	public String leaveType(String branchId) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");

		List<Leavetypemaster> list = new ArrayList<Leavetypemaster>();
		
		if(branchId!=null){
			list = new HrDAO().readListOfLeaveTypes(Integer.parseInt(branchId.toString()));
		}
		sb.append("leavetypemaster").append(list);
        	
		sb.append("result:").append(true);
		sb.append("}");
        return sb.toString();
	}
	
public String saveLeaveType(String branchId, String leaveTypeName) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
		
		Leavetypemaster leaveMaster = new Leavetypemaster();
		leaveMaster.setLeavetypename(DataUtil.emptyString(leaveTypeName));
		leaveMaster.setBranchid(Integer.parseInt(branchId.toString()));
		boolean saveLeaveType= new HrDAO().saveLeaveType(leaveMaster);
		sb.append("}");
		return sb.toString();
	}


public String deleteLeaveType(String idLeave) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
	Leavetypemaster leaveType = new Leavetypemaster();
	leaveType.setIdleavetypemaster(Integer.parseInt(DataUtil.emptyString(idLeave)));
	boolean deleteLeaveType= new HrDAO().deleteLeaveType(leaveType);
	sb.append("}");
	return sb.toString();
}

public String addLeaves(String branchId, String currentAcademicYear,String[] leaveTypeName1,String[] totalLeaves,String[] staff) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
	List<Leavedetails> leaveDetailsList = new ArrayList<Leavedetails>();

	if(currentAcademicYear!=null){
	for (String staffId : staff) {
		
		for (int i=0; i<leaveTypeName1.length; i++) {
			Leavedetails leaveDetails = new Leavedetails();
			Teacher teacher = new Teacher();
			Leavetypemaster leave = new Leavetypemaster();
			teacher.setTid(Integer.parseInt(staffId));
			leave.setIdleavetypemaster(Integer.parseInt(leaveTypeName1[i]));
			leaveDetails.setLeaveTypeMaster(leave);
			leaveDetails.setTeacher(teacher);
			leaveDetails.setNumberofleaves(Integer.parseInt(totalLeaves[i]));
			leaveDetails.setAcademicyear(currentAcademicYear.toString());
			leaveDetails.setBranchid(Integer.parseInt(branchId.toString()));
			leaveDetailsList.add(leaveDetails);
		}
		
	}
	boolean addLeaves= new HrDAO().addLeaves(leaveDetailsList);
}
	sb.append("result:").append(false);
	sb.append("}");
	return sb.toString();
}

public String viewLeavesDetails(String id) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
	
	Currentacademicyear currentYear = new YearDAO().showYear();
	sb.append("currentAcademicYear").append(currentYear.getCurrentacademicyear());
	
	List<Leavedetails> leaveDetailsList = new HrDAO().getLeaveDetails(DataUtil.emptyString(id),currentYear.getCurrentacademicyear());
	sb.append("leavedetailslist").append(leaveDetailsList);
	
	if(!leaveDetailsList.isEmpty()){
		sb.append("teachername").append(leaveDetailsList.get(0).getTeacher().getTeachername());
		sb.append("leavedetailsteachersid").append(leaveDetailsList.get(0).getTeacher().getTid());
	}
	
	sb.append("academicPerYear").append(currentYear.getCurrentacademicyear());

	sb.append("result:").append(true);
	sb.append("}");
	return sb.toString();
}

public String leaveDetailsPerYear(String leaveDetailsTeacherSid, String academicYear) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
	
	List<Leavedetails> leaveDetailsList = new HrDAO().getLeaveDetails(DataUtil.emptyString(leaveDetailsTeacherSid),DataUtil.emptyString(academicYear));
	sb.append("leavedetailslist").append(leaveDetailsList);
	
	sb.append("academicPerYear").append(DataUtil.emptyString(academicYear));

	sb.append("result:").append(true);
	sb.append("}");
	return sb.toString();
}

public String payHead(String currentAcademicYear, String branchId) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");

	if(currentAcademicYear!=null){
	List<Payhead> payHeadList = new HrDAO().getPayHeadList(currentAcademicYear.toString(), Integer.parseInt(branchId.toString()));
	sb.append("payheadlist").append(payHeadList);
	}
	sb.append("}");
	return sb.toString();
}

public String savePayHead(String currentAcademicYear,String payHeadName,String type,String validatory,String description,
		String branchId) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
	
	Payhead payHead = new Payhead();
	if(currentAcademicYear!=null){
	payHead.setPayheadname(DataUtil.emptyString(payHeadName));
	payHead.setPayheadtype(DataUtil.emptyString(type));
	payHead.setValidatory(DataUtil.emptyString(validatory));
	payHead.setDescription(DataUtil.emptyString(description));
	payHead.setAcademicyear(currentAcademicYear.toString());
	payHead.setBranchid(Integer.parseInt(branchId.toString()));
	boolean savePayHead= new HrDAO().savePayHead(payHead);
	}
	sb.append("result:").append(false);
	sb.append("}");
	return sb.toString();
}

public String addPayHeadStaffDetails(String currentAcademicYear,String[] staffIds,String[] values,String payHeadId,
		String amountPerc, String branchId) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
	
	if(currentAcademicYear!=null){
		
		List<Payheadstaffdetails> payHeadStaffDetailsList = new ArrayList<Payheadstaffdetails>();
		
		for(int i=0; i<staffIds.length; i++){
			Payheadstaffdetails payHeadStaffDetails = new Payheadstaffdetails();
			Teacher teacher = new Teacher();
			Payhead payHead = new Payhead();
			payHeadStaffDetails.setAcademicyear(currentAcademicYear.toString());
			payHeadStaffDetails.setAmountperc(amountPerc);
			teacher.setTid(Integer.parseInt(staffIds[i]));
			payHeadStaffDetails.setTeacher(teacher);
			payHead.setIdpayhead(Integer.parseInt(payHeadId));
			payHeadStaffDetails.setPayhead(payHead);
			payHeadStaffDetails.setValue(new BigDecimal(values[i]));
			payHeadStaffDetails.setBranchid(Integer.parseInt(branchId.toString()));
			payHeadStaffDetailsList.add(payHeadStaffDetails);
		}
		
		
		boolean addPayHeadStaffDetails= new HrDAO().addPayHeadStaffDetails(payHeadStaffDetailsList);
	}
	sb.append("}");
	return sb.toString();
}

public String addBasicPay(String currentAcademicYear,String[] staffIds,String[] basicPay,String[] paymentType,
		String[] accountNo,String[] overTime,String branchId) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
	
	if(currentAcademicYear!=null){
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
		payBasic.setAcademicyear(currentAcademicYear.toString());
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
		payBasic.setBranchid(Integer.parseInt(branchId.toString()));
		
		payBasicList.add(payBasic);
	}
	boolean savePayBasic= new HrDAO().savePayBasic(payBasicList);
	
	}
	sb.append("result:").append(false);
	sb.append("}");
	return sb.toString();
}

public String pfSettings(String branchId) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
	
	List<Pf> pf = new ArrayList<Pf>();
	
	if(branchId!=null){
		pf = new HrDAO().pfSettings(Integer.parseInt(branchId.toString()));
	}
	
	sb.append("pflist").append(pf);
	sb.append("}");
	return sb.toString();
	
}

public String addPf(String paidByManagement,String paidByStaff,String datepf,String branchId) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
	
	Pf pf = new Pf();
	pf.setDate(DateUtil.dateParserUpdateStd(DataUtil.emptyString(datepf)));
	pf.setPaidbyemployee(Integer.parseInt(paidByStaff));
	pf.setPaidbymanagement(Integer.parseInt(paidByManagement));
	pf.setBranchid(Integer.parseInt(branchId.toString()));
	new HrDAO().addPf(pf);
	sb.append("}");
	return sb.toString();
}

public String deletePf(String[] pfids) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
	if (pfids != null) {
		List ids = new ArrayList();
		for (String id : pfids) {
			ids.add(Integer.valueOf(id));
		}
		new HrDAO().deletePf(ids);
	}
	sb.append("}");
	return sb.toString();
}

public String saveAdvanceSalaryApproval(String paymentAdvance,String reason,String status,String branchId) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
	
	if(branchId!=null && paymentAdvance!=null){
	Payadvancesalary payAdvance = new Payadvancesalary();
	payAdvance.setIdpayadvancesalary(Integer.parseInt(paymentAdvance));
	payAdvance.setReason(DataUtil.emptyString(reason));
	payAdvance.setStatus(DataUtil.emptyString(status));
	payAdvance.setBranchid(Integer.parseInt(branchId.toString()));
	
	boolean saveAdvanceSalaryApproval= new HrDAO().saveAdvanceSalaryApproval(payAdvance);
	}
	sb.append("result:").append(false);
	sb.append("}");
	return sb.toString();
}

public String deleteAdvaceSalaryApproval(String idPayAdvanceSalary ) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
	
	if(!idPayAdvanceSalary.equalsIgnoreCase("")){
	Payadvancesalary payAdvance = new Payadvancesalary();
	payAdvance.setIdpayadvancesalary(Integer.parseInt(idPayAdvanceSalary));
	boolean deleteAdvaceSalaryApproval= new HrDAO().deleteAdvaceSalaryApproval(payAdvance);
	}
	sb.append("Result:").append(false);
	sb.append("}");
	return sb.toString();
}

public String salaryIssue(String branchId) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
	
	List<Payadvancesalary> payAdvanceSalary = new ArrayList<Payadvancesalary>();
	
	if(branchId!=null){
		payAdvanceSalary = new HrDAO().salaryIssue(Integer.parseInt(branchId.toString()));
	}
	
	sb.append("salaryissue").append(payAdvanceSalary);
	sb.append("result:").append(true);
	sb.append("}");
	return sb.toString();
}

public String applyLeave(String currentAcademicYear,String userAuth,String leaveTypeName,String reason,String fromDate,
		String toDate,String userName,String branchId) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
	Leaveapplication leaveApplication = new Leaveapplication();
	
	if(currentAcademicYear!=null && userAuth !=null){
		
		leaveApplication.setAcademicyear(currentAcademicYear.toString());
		
		leaveApplication.setLeavetype(DataUtil.emptyString(leaveTypeName));
		leaveApplication.setReason(DataUtil.emptyString(reason));
		leaveApplication.setStatus("pending");
		leaveApplication.setFromdate(DateUtil.dateParserUpdateStd(fromDate));
		leaveApplication.setTodate(DateUtil.dateParserUpdateStd(toDate));
			Teacher teacher = new EmployeeDAO().getEmployeeDetails(userName);
			Teacher addTeacher = new Teacher();
			addTeacher.setTid(teacher.getTid());
			leaveApplication.setTeacher(addTeacher);
			int totalLeaves = calculateLeaves(DateUtil.dateParserUpdateStd(fromDate),DateUtil.dateParserUpdateStd(toDate),teacher.getTid());
			if(totalLeaves==0){
				sb.append("result:").append(false);
				return sb.toString();
			}
			leaveApplication.setTotalleaves(totalLeaves);
			leaveApplication.setDateofapply(new Date());
			leaveApplication.setBranchid(Integer.parseInt(branchId.toString()));
			boolean applyLeave= new HrDAO().applyLeave(leaveApplication);
	}
	sb.append("result:").append(false);
	sb.append("}");
	return sb.toString();
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

public String leaveApprovals(String currentAcademicYear, String branchId) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
	
	if(currentAcademicYear!=null){
		List<Leaveapplication> listLeaveApplication = new HrDAO().leaveApprovals(currentAcademicYear.toString(), Integer.parseInt(branchId.toString()));
		sb.append("leaveapplicationlist").append(listLeaveApplication);
		sb.append("result:").append(true);
		return sb.toString();
	}
	sb.append("result:").append(false);
	sb.append("}");
	return sb.toString();
}

public String approveLeave(String[] idleaveapplication) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
		
	if (idleaveapplication != null) {
		List ids = new ArrayList();
		for (String id : idleaveapplication) {
			ids.add(Integer.valueOf(id));
		}
		boolean approveLeave= new HrDAO().approveLeave(ids);
	}
	sb.append("result:").append(false);
	sb.append("}");
	return sb.toString();
}

public String rejectLeave(String[] idleaveapplication) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
	
	
	if (idleaveapplication != null) {
		List ids = new ArrayList();
		for (String id : idleaveapplication) {
			ids.add(Integer.valueOf(id));
		}
		boolean rejectLeave= new HrDAO().rejectLeave(ids);
	}
	sb.append("result:").append(false);
	sb.append("}");
	return sb.toString();
}

public String processStaffSalary(String[] staffIds, String currentAcademicYear,String month,String year,String branchId,
		String dateprocess) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
	
	Map<String, BigDecimal> earningMaps = new HashMap<String, BigDecimal>();
	Map<String, BigDecimal> deductionMaps = new HashMap<String, BigDecimal>();
	List<Processsalarydetailsheads> processSalarydetailsheadList = new ArrayList<Processsalarydetailsheads>();
	List<Processsalarydetails> processsalarydetailsList = new ArrayList<Processsalarydetails>(); 
	
	if(currentAcademicYear!=null){
		
	for (String staffid : staffIds) {
			
		
		if(!checkprocessedStaffSalary(Integer.parseInt(staffid),DataUtil.emptyString(month),
				  DataUtil.emptyString(year))){
		
		BigDecimal totalEarnings = BigDecimal.ZERO;
		BigDecimal totalDeduction = BigDecimal.ZERO;
		
		//get all the earnings
			
			//get the basic pay
		
			Paybasic basicPay = new HrDAO().getBasicPay(Integer.parseInt(staffid),currentAcademicYear.toString());
			BigDecimal basicPayStaff = basicPay.getBasicpay();
			
			
			Processsalarydetailsheads processHeadsBasic = new Processsalarydetailsheads();
			processHeadsBasic.setPayheadname("Basic Pay");
			processHeadsBasic.setPayheadtype("Earning");
			processHeadsBasic.setAmount(basicPay.getBasicpay());
			processHeadsBasic.setBranchid(Integer.parseInt(branchId.toString()));
			processSalarydetailsheadList.add(processHeadsBasic);   
               
			// earning/deduction calculations
			List<Payheadstaffdetails> payheadstaffdetailsList = new HrDAO().getPayHeadStaff(Integer.parseInt(staffid),currentAcademicYear.toString());
				
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
					processHeads.setBranchid(Integer.parseInt(branchId.toString()));
					processSalarydetailsheadList.add(processHeads);
				}
			}else{
				sb.append("result:").append(false);
				return sb.toString();
			}
			
			 
			// Process Salary
			   Processsalarydetails processSalary = new Processsalarydetails();
		       Teacher teacher = new Teacher();
		       teacher.setTid(Integer.parseInt(staffid));
		       processSalary.setTeacher(teacher);
		       processSalary.setMonth(DataUtil.emptyString(month));
		       processSalary.setYear(Integer.parseInt(DataUtil.emptyString(year)));
		       processSalary.setStatus("PROCESSED");
		       processSalary.setAcademicyear(currentAcademicYear.toString());
		       processSalary.setProcesseddate(DateUtil.dateParserUpdateStd(dateprocess));
		       processSalary.setPaymenttype(basicPay.getPaymenttype());
		       BigDecimal netPayment = basicPayStaff.add(totalEarnings);
		       netPayment = netPayment.subtract(totalDeduction);
		       processSalary.setNetpayment(netPayment);	
		       processSalary.setBranchid(Integer.parseInt(branchId.toString()));
		       
		       processsalarydetailsList.add(processSalary);
		       
		}
	}
	
	if(!processsalarydetailsList.isEmpty()){
		boolean processStaffSalary= new HrDAO().processStaffSalary(processsalarydetailsList,processSalarydetailsheadList);
	}
	
	}
	sb.append("result:").append(false);
	sb.append("}");
	return sb.toString();
}

public boolean checkprocessedStaffSalary(int staffId, String month, String year) {
	
	Processsalarydetails processSalaryDetails = new HrDAO().checkprocessedStaffSalary(staffId,month,year);
	
		if(processSalaryDetails!=null){
			return true;
		}
		
	return false;
}

public String getPayHead(String currentAcademicYear,String payHeadType,String branchId) throws IOException {
	StringBuffer sb = new StringBuffer();
	sb.append("{");


	if(currentAcademicYear!=null){
	List<Payhead> payHeadList = new HrDAO().getPayHeadListDynamic(payHeadType,currentAcademicYear.toString(), Integer.parseInt(branchId.toString()));
	sb.append("payheadlistdynamic").append(payHeadList);
	
	
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
	sb.append("result:").append(true);
	sb.append("}");
return sb.toString();
}

public String issueStaffSalary(String currentAcademicYear,String branchId) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");

	if(currentAcademicYear!=null){
		
		List<Processsalarydetails> processSalaryDetailsList = new HrDAO().issueStaffSalary(currentAcademicYear.toString(), Integer.parseInt(branchId.toString()));
		sb.append("processsalarydetailslist").append(processSalaryDetailsList);
		
		if(processSalaryDetailsList.isEmpty()){
			sb.append("result:").append(false);
			return sb.toString();
		}
		sb.append("result:").append(true);
		return sb.toString();
	}
	sb.append("result:").append(false);
	sb.append("}");
	return sb.toString();
}

public String printSalarySlip(String processSalaryId) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
	
	
	if(processSalaryId!=null){
		
		Map<String, BigDecimal> earningsMap = new LinkedHashMap<String, BigDecimal>();
		Map<String, BigDecimal> deductionsMap = new LinkedHashMap<String, BigDecimal>();
		BigDecimal totalEarnings = BigDecimal.ZERO;
		BigDecimal totalDeductions = BigDecimal.ZERO;
		
		Processsalarydetails processSalaryDetails = new HrDAO().getProcessSalaryDetails(Integer.parseInt(processSalaryId));
		sb.append("processsalarydetails").append(processSalaryDetails);
		
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
		
		sb.append("earningmap").append(earningsMap);
		sb.append(",deductionmap").append(deductionsMap);
		sb.append(",totalearning").append(totalEarnings);
		sb.append(",totaldeduction").append(totalDeductions);
		sb.append(",netpay").append(processSalaryDetails.getNetpayment());
	}
	sb.append("}");
	return sb.toString();
}

public String getStaffDetails(String currentAcademicYear,String staffId) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
	
	if(currentAcademicYear!=null){
		
		List<Payheadstaffdetails> payHeadDetailsList = new HrDAO().getStaffDetails(Integer.parseInt(staffId), currentAcademicYear.toString());
		sb.append("payheaddetailslist").append(payHeadDetailsList);
	}
	sb.append("}");
	return sb.toString();
}

public String deletePayHeadStaff(String[] StaffIds,String[] idpayheadstaffdetails,String currentAcademicYear) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
	
	
	List ids = new ArrayList();
	
	if (idpayheadstaffdetails != null) {
		for (String id : idpayheadstaffdetails) {
			ids.add(Integer.valueOf(id));
		}
	}
	
	List<Processsalarydetails> processSalaryDetails = new HrDAO().getStaffinfo(Integer.parseInt(StaffIds[0]));
	
	if(processSalaryDetails.isEmpty()){
			if(new HrDAO().deletePayHeadStaff(ids))
			{
				List<Payheadstaffdetails> payHeadDetailsList = new HrDAO().getStaffDetails(Integer.parseInt(StaffIds[0]), currentAcademicYear.toString());
				sb.append("payheaddetailslist").append(payHeadDetailsList);
				sb.append("result:").append(true);
				return sb.toString();
				
			}
	}
	sb.append("result:").append(false);
	sb.append("}");
	return sb.toString();
}

public String issueProcessedSalary(String[] idProcessSalaryDetails) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");

	boolean result = false;
	
	if (idProcessSalaryDetails != null) {
		List ids = new ArrayList();
		for (String id : idProcessSalaryDetails) {
			ids.add(Integer.valueOf(id));
		}
		result = new HrDAO().issueProcessedSalary(ids);
	}
	issueStaffSalary(null, null);
	sb.append("}");
	return sb.toString();
}


public String updateBasicpayEmployees(String branchId,String[] staffIds,String[] basicPay,String[] paymentType,String[] accountNo,
		String[] overTime,String[] academicYear1) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
	
	if(branchId!=null){
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
		payBasic.setAcademicyear(academicYear1[Integer.parseInt(splitId[1])]);
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
		payBasic.setBranchid(Integer.parseInt(branchId.toString()));
		
		payBasicList.add(payBasic);
	}
	 boolean result = new HrDAO().updatePayBasic(payBasicList);
	 sb.append("basicpayupdate").append(result);	
	}
	employee.basicpayEmployees(branchId);
	sb.append("}");
	return sb.toString();
}
}

package com.model.periods.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.academicyear.dao.YearDAO;
import com.model.academicyear.dto.Currentacademicyear;
import com.model.documents.dao.DocumentDAO;
import com.model.documents.dto.Transfercertificate;
import com.model.employee.service.EmployeeService;
import com.model.parents.dto.Parents;
import com.model.periods.dao.PeriodDAO;
import com.model.periods.dto.Perioddetails;
import com.model.periods.dto.Periodmaster;
import com.model.student.dao.studentDetailsDAO;
import com.model.student.dto.Student;
import com.model.subjectdetails.service.SubjectDetailsService;
import com.util.DataUtil;
import com.util.DateUtil;

public class PeriodService {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession httpSession;
	private String BRANCHID = "branchid";
	/**
    * Size of a byte buffer to read/write file
    */
   private static final int BUFFER_SIZE = 4096;
	
	public PeriodService(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.httpSession = request.getSession();
	}
	
	
	public boolean periodConfiguration(){
		
		List<Periodmaster> periodMaster = new ArrayList<Periodmaster>();
		
		if(httpSession.getAttribute(BRANCHID)!=null){
			
			Currentacademicyear currentYear = new YearDAO().showYear();
	        httpSession.setAttribute("currentYear", currentYear.getCurrentacademicyear());
	        
	        new SubjectDetailsService(request, response).readListOfSubjects();
	        
	        new EmployeeService(request, response).ViewAllEmployee();
	       
	        periodMaster = new PeriodDAO().getPeriodsDetails(currentYear.getCurrentacademicyear(), Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
	        request.setAttribute("periodmasterlist", periodMaster);
		}
		
        
        if(periodMaster.isEmpty()){
        	return false;
        }
        
		return true;
		
	}

	public boolean savePeriods() {
		
		String academicYear = DataUtil.emptyString(request.getParameter("academicyear"));
		String totalNoOfPeriods = DataUtil.emptyString(request.getParameter("totalperiods"));
		String durationOfPeriodsHr = DataUtil.emptyString(request.getParameter("periodduration"));
		String durationOfPeriodsMin = DataUtil.emptyString(request.getParameter("perioddurationmin"));
		String dayStartTimeHr = DataUtil.emptyString(request.getParameter("daystarttime"));
		String dayStartTimeMin = DataUtil.emptyString(request.getParameter("daystartminutes"));
		String dayStartAm = DataUtil.emptyString(request.getParameter("daystartam"));
		String dayEndTimeHr = DataUtil.emptyString(request.getParameter("dayendtime"));
		String dayEndTimeMin = DataUtil.emptyString(request.getParameter("dayendminutes"));
		String dayEndAm = DataUtil.emptyString(request.getParameter("dayendam"));
		String fromClass = DataUtil.emptyString(request.getParameter("fromclass"));
		String toClass = DataUtil.emptyString(request.getParameter("toclass"));
		
		String[] periods = request.getParameterValues("periods");
		String[] subjects = request.getParameterValues("subject");
		String[] staff = request.getParameterValues("staff");
		String[] periodStartTimeHr = request.getParameterValues("periodstarttimehr");
		String[] periodStartTimeMin = request.getParameterValues("periodstarttimemin");
		String[] periodStartTimeAm = request.getParameterValues("periodstarttimeam");
		String[] periodEndTimeHr = request.getParameterValues("periodendtimehr");
		String[] periodEndTimeMin = request.getParameterValues("periodendtimemin");
		String[] periodEndTimeAm = request.getParameterValues("periodendtimeam");
		String[] days = request.getParameterValues("days");
		
		
		Map<String,List<Perioddetails>> periodMap = new HashMap<String,List<Perioddetails>>();
		int getPeriod=0;
		
		for(int i=0; i<days.length; i++){
			List<Perioddetails> periodList = new ArrayList<Perioddetails>();
			
			for (int j = 0; j < Integer.parseInt(totalNoOfPeriods); j++) {
				Perioddetails periodDetails = new Perioddetails();
				periodDetails.setPeriods(periods[getPeriod]);
				periodDetails.setSubject(subjects[getPeriod]);
				periodDetails.setStaff(staff[getPeriod]);
				periodDetails.setTimings(periodStartTimeHr[getPeriod]+":"+periodStartTimeMin[getPeriod]+": "+periodStartTimeAm[getPeriod]+ " To "+periodEndTimeHr[getPeriod]+":"+periodEndTimeMin[getPeriod]+" "+periodEndTimeAm[getPeriod]);
				periodDetails.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
				getPeriod++;
				periodList.add(periodDetails);
			}
				periodMap.put(days[i], periodList);
		}

		List<Perioddetails> periodDetailsList = new ArrayList<Perioddetails>();
		Periodmaster periodMaster = new Periodmaster();
		periodMaster.setAcademicyear(academicYear);
		if(!fromClass.equalsIgnoreCase(toClass) || toClass!=""){
			periodMaster.setClass_(fromClass+"-"+toClass);
		}else{
			periodMaster.setClass_(fromClass);
		}
		periodMaster.setDaystart(dayStartTimeHr+":"+dayStartTimeMin+" "+dayStartAm);
		periodMaster.setDayend(dayEndTimeHr+":"+dayEndTimeMin+" "+dayEndAm);
		periodMaster.setDurationofperiod(durationOfPeriodsHr+":"+durationOfPeriodsMin);
		periodMaster.setTotalperiods(Integer.parseInt(totalNoOfPeriods));
		periodMaster.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
		return new PeriodDAO().save(periodMaster,periodMap);
	}


	public boolean viewTimeTable() {
		String periodMasterid = request.getParameter("id");
		
		if(periodMasterid!=null){
		
			Periodmaster periodMaster = new PeriodDAO().getTimeTable(periodMasterid);
			request.setAttribute("timetable", periodMaster);
			
			List<Perioddetails> periodD= new PeriodDAO().getTimeTablePeriodDetails(periodMasterid);
			request.setAttribute("timetableperioddetails", periodD);
			
			Map<String,List<Perioddetails>> periodMap = new LinkedHashMap<String,List<Perioddetails>>();
			
			List<Perioddetails> periodDetailsMon = new ArrayList<Perioddetails>();
			List<Perioddetails> periodDetailsTue = new ArrayList<Perioddetails>();
			List<Perioddetails> periodDetailsWed = new ArrayList<Perioddetails>();
			List<Perioddetails> periodDetailsThu = new ArrayList<Perioddetails>();
			List<Perioddetails> periodDetailsFri = new ArrayList<Perioddetails>();
			List<Perioddetails> periodDetailsSat = new ArrayList<Perioddetails>();
			List<Perioddetails> periodDetailsSun = new ArrayList<Perioddetails>();
			
			for (Perioddetails periodDetailsSingle : periodD) {
				
					if("Monday".equalsIgnoreCase(periodDetailsSingle.getDays())){
						periodDetailsMon.add(periodDetailsSingle);
					}else if("Tuesday".equalsIgnoreCase(periodDetailsSingle.getDays())){
						periodDetailsTue.add(periodDetailsSingle);
					}else if("Wednesday".equalsIgnoreCase(periodDetailsSingle.getDays())){
						periodDetailsWed.add(periodDetailsSingle);
					}else if("Thursday".equalsIgnoreCase(periodDetailsSingle.getDays())){
						periodDetailsThu.add(periodDetailsSingle);
					}else if("Friday".equalsIgnoreCase(periodDetailsSingle.getDays())){
						periodDetailsFri.add(periodDetailsSingle);
					}else if("Saturday".equalsIgnoreCase(periodDetailsSingle.getDays())){
						periodDetailsSat.add(periodDetailsSingle);
					}else if("Sunday".equalsIgnoreCase(periodDetailsSingle.getDays())){
						periodDetailsSun.add(periodDetailsSingle);
					}
					
			}
			
			if(!periodDetailsMon.isEmpty()){
				periodMap.put("Monday", periodDetailsMon);
			}
			
			if(!periodDetailsTue.isEmpty()){
				periodMap.put("Tueday", periodDetailsTue);
			}

			if(!periodDetailsWed.isEmpty()){
				periodMap.put("Wednesday", periodDetailsWed);
			}

			if(!periodDetailsThu.isEmpty()){
				periodMap.put("Thurday", periodDetailsThu);
			}

			if(!periodDetailsFri.isEmpty()){
				periodMap.put("Friday", periodDetailsFri);
			}

			if(!periodDetailsSat.isEmpty()){
				periodMap.put("Saturday", periodDetailsSat);
			}

			if(!periodDetailsSun.isEmpty()){
				periodMap.put("Sunday", periodDetailsSun);
			}
			request.setAttribute("periodmap", periodMap);
			return true;
		}
		return false;
	}


	public boolean deletePeriods() {
		
		String[] periodMasterid = request.getParameterValues("idperiodmaster");
		if (periodMasterid != null) {
			List ids = new ArrayList();
			for (String id : periodMasterid) {
				ids.add(Integer.valueOf(id));
			}
			return new PeriodDAO().deletePeriods(ids);
		}
		
		return false;
	}


	public boolean generateTimeTable() {

		List<Periodmaster> periodMaster = new ArrayList<Periodmaster>();
		
		if(httpSession.getAttribute(BRANCHID)!=null){

			Currentacademicyear currentYear = new YearDAO().showYear();
	        httpSession.setAttribute("currentYear", currentYear.getCurrentacademicyear());
	       
	        periodMaster = new PeriodDAO().getPeriodsDetails(currentYear.getCurrentacademicyear(),Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
	        request.setAttribute("periodmasterlist", periodMaster);
		}
		
        if(periodMaster.isEmpty()){
        	return false;
        }
        
		return true;
		
	
	}
}

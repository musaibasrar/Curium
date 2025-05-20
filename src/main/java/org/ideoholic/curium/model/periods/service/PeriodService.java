package org.ideoholic.curium.model.periods.service;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.academicyear.dao.YearDAO;
import org.ideoholic.curium.model.academicyear.dto.Currentacademicyear;
import org.ideoholic.curium.model.employee.action.EmployeeActionAdapter;
import org.ideoholic.curium.model.periods.dao.PeriodDAO;
import org.ideoholic.curium.model.periods.dto.*;
import org.ideoholic.curium.model.std.action.StandardActionAdapter;
import org.ideoholic.curium.model.subjectdetails.action.SubjectDetailsActionAdapter;
import org.ideoholic.curium.util.DataUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.Map.Entry;

public class PeriodService {
    private StandardActionAdapter standardActionAdapter;
	private EmployeeActionAdapter employeeActionAdapter;
	private SubjectDetailsActionAdapter subjectDetailsActionAdapter;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession httpSession;
	private String BRANCHID = "branchid";
	private String USERID = "userloginid";
	/**
    * Size of a byte buffer to read/write file
    */
   private static final int BUFFER_SIZE = 4096;
	
	public PeriodService(HttpServletRequest request, HttpServletResponse response, StandardActionAdapter standardActionAdapter, EmployeeActionAdapter employeeActionAdapter,SubjectDetailsActionAdapter subjectDetailsActionAdapter ) {
		this.request = request;
		this.response = response;
		this.httpSession = request.getSession();
		this.standardActionAdapter= standardActionAdapter;
		this.employeeActionAdapter = employeeActionAdapter;
		this.subjectDetailsActionAdapter = subjectDetailsActionAdapter;
	}
	
	
	public TimeTableResponseDto periodConfiguration(String branchId){
		TimeTableResponseDto result = TimeTableResponseDto.builder().success(false).build();
		
		List<Periodmaster> periodMaster = new ArrayList<Periodmaster>();
		
		try {
	                Currentacademicyear currentYear = new YearDAO().showYear();
					result.setCurrentYear(currentYear.getCurrentacademicyear());
	                
	                subjectDetailsActionAdapter.readListOfSubjectNames();
	                
	                employeeActionAdapter.ViewAllEmployee();
	                standardActionAdapter.viewClasses();
	                periodMaster = new PeriodDAO().getPeriodsDetails(currentYear.getCurrentacademicyear(), Integer.parseInt(branchId));
					result.setPeriodMaster(periodMaster);
		    
                } catch (Exception e) {
                   return result;
                }
		
	    result.setSuccess(true);
		return result;
		
	}
	
	public ResultResponse savePeriods(PeriodsSaveDto dto, String branchId, String userId) {
		ResultResponse result = ResultResponse.builder().build();
		
		String[] classesPeriodCat = dto.getFromClass();
		
		for (String periodCat : classesPeriodCat) {
		String academicYear = DataUtil.emptyString(dto.getAcademicYear());
		String totalNoOfPeriods = DataUtil.emptyString(dto.getTotalNoOfPeriods());
		String durationOfPeriodsHr = DataUtil.emptyString(dto.getDurationOfPeriodsHr());
		String durationOfPeriodsMin = DataUtil.emptyString(dto.getDurationOfPeriodsMin());
		String dayStartTimeHr = DataUtil.emptyString(dto.getDayStartTimeHr());
		String dayStartTimeMin = DataUtil.emptyString(dto.getDayStartTimeMin());
		String dayStartAm = DataUtil.emptyString(dto.getDayStartAm());
		String dayEndTimeHr = DataUtil.emptyString(dto.getDayEndTimeHr());
		String dayEndTimeMin = DataUtil.emptyString(dto.getDayEndTimeMin());
		String dayEndAm = DataUtil.emptyString(dto.getDayEndAm());
		//String fromClass = DataUtil.emptyString(dto.getFromClass());
		String toClass = DataUtil.emptyString(dto.getToClass());
		
		String[] periods = dto.getPeriods();
		String[] subjects = dto.getSubjects();
		String[] staff = dto.getStaff();
		String[] periodStartTimeHr = dto.getPeriodStartTimeHr();
		String[] periodStartTimeMin = dto.getPeriodStartTimeMin();
		String[] periodStartTimeAm = dto.getPeriodStartTimeAm();
		String[] periodEndTimeHr = dto.getPeriodEndTimeHr();
		String[] periodEndTimeMin = dto.getPeriodEndTimeMin();
		String[] periodEndTimeAm = dto.getPeriodEndTimeAm();
		String[] days = dto.getDays();
		
		Map<String,List<Perioddetails>> periodMap = new HashMap<>();
		int getPeriod=0;
		
		for(int i=0; i<days.length; i++){
			List<Perioddetails> periodList = new ArrayList<>();
			
			for (int j = 0; j < Integer.parseInt(totalNoOfPeriods); j++) {
				Perioddetails periodDetails = new Perioddetails();
				periodDetails.setPeriods(periods[getPeriod]);
				periodDetails.setSubject(subjects[getPeriod]);
				periodDetails.setStaff(staff[getPeriod]);
				periodDetails.setTimings(periodStartTimeHr[getPeriod]+":"+periodStartTimeMin[getPeriod]+": "+periodStartTimeAm[getPeriod]+ " To "+periodEndTimeHr[getPeriod]+":"+periodEndTimeMin[getPeriod]+" "+periodEndTimeAm[getPeriod]);
				periodDetails.setBranchid(Integer.parseInt(branchId));
				periodDetails.setUserid(Integer.parseInt(userId));
				getPeriod++;
				periodList.add(periodDetails);
			}
				periodMap.put(days[i], periodList);
		}

		List<Perioddetails> periodDetailsList = new ArrayList<>();
		Periodmaster periodMaster = new Periodmaster();
		periodMaster.setAcademicyear(academicYear);
		periodMaster.setClass_(periodCat);
		periodMaster.setDaystart(dayStartTimeHr+":"+dayStartTimeMin+" "+dayStartAm);
		periodMaster.setDayend(dayEndTimeHr+":"+dayEndTimeMin+" "+dayEndAm);
		periodMaster.setDurationofperiod(durationOfPeriodsHr+":"+durationOfPeriodsMin);
		periodMaster.setTotalperiods(Integer.parseInt(totalNoOfPeriods));
		periodMaster.setBranchid(Integer.parseInt(branchId));
		periodMaster.setUserid(Integer.parseInt(userId));

		result.setSuccess(new PeriodDAO().save(periodMaster,periodMap));
		}
		return result;
	}


	public TimeTableViewResponseDto viewTimeTable(String periodMasterId) {
		TimeTableViewResponseDto result = TimeTableViewResponseDto.builder().build();

        if(periodMasterId !=null){
		
			Periodmaster periodMaster = new PeriodDAO().getTimeTable(periodMasterId);
			result.setPeriodMaster(periodMaster);
			
			List<Perioddetails> periodD= new PeriodDAO().getTimeTablePeriodDetails(periodMasterId);
			result.setPeriodDetails(periodD);
			
			Map<String,List<Perioddetails>> periodMap = new LinkedHashMap<>();
			
			List<Perioddetails> periodDetailsMon = new ArrayList<>();
			List<Perioddetails> periodDetailsTue = new ArrayList<>();
			List<Perioddetails> periodDetailsWed = new ArrayList<>();
			List<Perioddetails> periodDetailsThu = new ArrayList<>();
			List<Perioddetails> periodDetailsFri = new ArrayList<>();
			List<Perioddetails> periodDetailsSat = new ArrayList<>();
			List<Perioddetails> periodDetailsSun = new ArrayList<>();
			
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
				periodMap.put("Tuesday", periodDetailsTue);
			}

			if(!periodDetailsWed.isEmpty()){
				periodMap.put("Wednesday", periodDetailsWed);
			}

			if(!periodDetailsThu.isEmpty()){
				periodMap.put("Thursday", periodDetailsThu);
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
			result.setPeriodMap(periodMap);
			//change later
			result.setPeriodMasterId(periodMasterId);
			result.setSuccess(true);
		}
		return result;
	}


	public ResultResponse deletePeriods(PeriodMasterIdDto dto) {
		ResultResponse result = ResultResponse.builder().build();
		
		String[] periodMasterid = dto.getPeriodMasterId();
		if (periodMasterid != null) {
			List<Integer> ids = new ArrayList();
			for (String id : periodMasterid) {
				ids.add(Integer.valueOf(id));
			}
			result.setSuccess(new PeriodDAO().deletePeriods(ids));
			return result;
		}
		
		result.setSuccess(false);
		return result;
	}


	public TimeTableResponseDto generateTimeTable(String branchId) {
		TimeTableResponseDto result = TimeTableResponseDto.builder().success(false).build();

		List<Periodmaster> periodMaster = new ArrayList<>();
		
		if(branchId!=null){

			Currentacademicyear currentYear = new YearDAO().showYear();
	        result.setCurrentYear(currentYear.getCurrentacademicyear());
	       
	        periodMaster = new PeriodDAO().getPeriodsDetails(currentYear.getCurrentacademicyear(),Integer.parseInt(branchId));
	        result.setPeriodMaster(periodMaster);
		}

        if(periodMaster.isEmpty()){
			result.setSuccess(false);
        }else {
			result.setSuccess(true);
		}
		return result;
	}


	public TeacherTimeTableResponseDto viewTeacherTimeTable(String teacherName, String branchId) {
		
		TeacherTimeTableResponseDto result = TeacherTimeTableResponseDto.builder().success(false).build();
		List<Map> periodMapList = new LinkedList<>();
		
		Map<String,String> mondayMap = new HashMap<>();
		Map<String,String> tuesdayMap = new HashMap<>();
		Map<String,String> wednesdayMap = new HashMap<>();
		Map<String,String> thursdayMap = new HashMap<>();
		Map<String,String> fridayMap = new HashMap<>();
		Map<String,String> saturdayMap = new HashMap<>();
		Map<String,String> sundayMap = new HashMap<>();
		
		if(branchId!=null){
            List<Perioddetails> periodDetailsList = new PeriodDAO().getPeriodDetailsForTeacher(teacherName);
			
			for (Perioddetails perioddetails : periodDetailsList) {
				
				Periodmaster periodMaster = new PeriodDAO().getTimeTable(perioddetails.getPeriodmasterid().toString());
				
				if("monday".equalsIgnoreCase(perioddetails.getDays())) {
					String periodNo = perioddetails.getPeriods();
					mondayMap.put(periodNo.substring(periodNo.length() - 1), periodMaster.getClass_());
				}else if("tuesday".equalsIgnoreCase(perioddetails.getDays())) {
					String periodNo = perioddetails.getPeriods();
					tuesdayMap.put(periodNo.substring(periodNo.length() - 1), periodMaster.getClass_());
				}else if("wednesday".equalsIgnoreCase(perioddetails.getDays())) {
					String periodNo = perioddetails.getPeriods();
					wednesdayMap.put(periodNo.substring(periodNo.length() - 1), periodMaster.getClass_());
				}else if("thursday".equalsIgnoreCase(perioddetails.getDays())) {
					String periodNo = perioddetails.getPeriods();
					thursdayMap.put(periodNo.substring(periodNo.length() - 1), periodMaster.getClass_());
				}else if("friday".equalsIgnoreCase(perioddetails.getDays())) {
					String periodNo = perioddetails.getPeriods();
					fridayMap.put(periodNo.substring(periodNo.length() - 1), periodMaster.getClass_());
				}else if("saturday".equalsIgnoreCase(perioddetails.getDays())) {
					String periodNo = perioddetails.getPeriods();
					saturdayMap.put(periodNo.substring(periodNo.length() - 1), periodMaster.getClass_());
				}else if("sunday".equalsIgnoreCase(perioddetails.getDays())) {
					String periodNo = perioddetails.getPeriods();
					sundayMap.put(periodNo.substring(periodNo.length() - 1), periodMaster.getClass_());
				}
				
			}
			
			if(!mondayMap.isEmpty()) {
				periodMapList.add(mondayMap);
			}
			if(!tuesdayMap.isEmpty()) {
				periodMapList.add(tuesdayMap);
			}
			if(!wednesdayMap.isEmpty()) {
				periodMapList.add(wednesdayMap);
			}
			if(!thursdayMap.isEmpty()) {
				periodMapList.add(thursdayMap);
			}
			if(!fridayMap.isEmpty()) {
				periodMapList.add(fridayMap);
			}
			if(!saturdayMap.isEmpty()) {
				periodMapList.add(saturdayMap);
			}
			if(!sundayMap.isEmpty()) {
				periodMapList.add(sundayMap);
			}

			result.setTeacherName(teacherName);
			result.setPeriodMapList(periodMapList);
			
			result.setSuccess(true);
		}
		
		
		return result;
	}


	public TimeTableViewResponseDto updatePeriodDetails(String periodMasterId) {
		TimeTableViewResponseDto result = TimeTableViewResponseDto.builder().build();

		result.setPeriodMasterId(periodMasterId);
		try {
			if(periodMasterId !=null){

				Periodmaster periodMaster = new PeriodDAO().getTimeTable(periodMasterId);
				result.setPeriodMaster(periodMaster);

				List<Perioddetails> periodD= new PeriodDAO().getTimeTablePeriodDetails(periodMasterId);
				result.setPeriodDetails(periodD);

				Map<String,List<Perioddetails>> periodMap = new LinkedHashMap<>();

				List<Perioddetails> periodDetailsMon = new ArrayList<>();
				List<Perioddetails> periodDetailsTue = new ArrayList<>();
				List<Perioddetails> periodDetailsWed = new ArrayList<>();
				List<Perioddetails> periodDetailsThu = new ArrayList<>();
				List<Perioddetails> periodDetailsFri = new ArrayList<>();
				List<Perioddetails> periodDetailsSat = new ArrayList<>();
				List<Perioddetails> periodDetailsSun = new ArrayList<>();

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
					periodMap.put("Tuesday", periodDetailsTue);
				}

				if(!periodDetailsWed.isEmpty()){
					periodMap.put("Wednesday", periodDetailsWed);
				}

				if(!periodDetailsThu.isEmpty()){
					periodMap.put("Thursday", periodDetailsThu);
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
				result.setPeriodMap(periodMap);
			}

			result.setSuccess(true);
		} catch (Exception e){
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}


	public ResultResponse getPeriodDetail() {
		ResultResponse result = ResultResponse.builder().build();
        
        try {
        	employeeActionAdapter.ViewAllEmployee();
        	subjectDetailsActionAdapter.readListOfSubjectNames();
			result.setSuccess(true);
	    } catch (Exception e) {
	        e.printStackTrace();
			result.setSuccess(false);
	    }
		return result;
	}


	public ResultResponse updatenewPeriodDetails(PeriodsSaveDto dto, String branchId, String userId) {
		ResultResponse result = ResultResponse.builder().build();

		String academicYear = DataUtil.emptyString(dto.getAcademicYear());
		String totalNoOfPeriods = DataUtil.emptyString(dto.getTotalNoOfPeriods());
		String dayStartTimeHr = DataUtil.emptyString(dto.getDayStartTimeHr());
		String dayStartTimeMin = DataUtil.emptyString(dto.getDayStartTimeMin());
		String dayStartAm = DataUtil.emptyString(dto.getDayStartAm());
		String dayEndTimeHr = DataUtil.emptyString(dto.getDayEndTimeHr());
		String dayEndTimeMin = DataUtil.emptyString(dto.getDayEndTimeMin());
		String dayEndAm = DataUtil.emptyString(dto.getDayEndAm());
		String periodmasterid = DataUtil.emptyString(dto.getPeriodMasterId());
		//String fromClass = DataUtil.emptyString(dto.getFromClass());
		String toClass = DataUtil.emptyString(dto.getToClass());
		
		String[] fromClass = dto.getFromClass();
		String[] periods = dto.getPeriods();
		String[] periodid = dto.getPeriodId();
		String[] subjects = dto.getSubjects();
		String[] staff = dto.getStaff();
		String[] periodStartTimeHr = dto.getPeriodStartTimeHr();
		String[] periodStartTimeMin = dto.getPeriodStartTimeMin();
		String[] periodStartTimeAm = dto.getPeriodStartTimeAm();
		String[] periodEndTimeHr = dto.getPeriodEndTimeHr();
		String[] periodEndTimeMin = dto.getPeriodEndTimeMin();
		String[] periodEndTimeAm = dto.getPeriodEndTimeAm();
		String[] days = dto.getDays();
		
		
		Map<String,List<Perioddetails>> periodMap = new HashMap<String,List<Perioddetails>>();
		List<Perioddetails> periodDetailsList = new ArrayList<Perioddetails>();
		int getPeriod=0;
		
		for(int i=0; i<days.length; i++){
			List<Perioddetails> periodList = new ArrayList<Perioddetails>();
			
			for (int j = 0; j < Integer.parseInt(totalNoOfPeriods); j++) {
				Perioddetails periodDetails = new Perioddetails();
				//periodDetails.setPeriodmasterid(Integer.parseInt(periodmasterid));
				periodDetails.setPeriods(periods[getPeriod]);
				periodDetails.setIdperioddetails(Integer.parseInt(periodid[getPeriod].toString()));
				periodDetails.setSubject(subjects[getPeriod]);
				periodDetails.setStaff(staff[getPeriod]);
				periodDetails.setTimings(periodStartTimeHr[getPeriod]+":"+periodStartTimeMin[getPeriod]+": "+periodStartTimeAm[getPeriod]+ " To "+periodEndTimeHr[getPeriod]+":"+periodEndTimeMin[getPeriod]+" "+periodEndTimeAm[getPeriod]);
				periodDetails.setBranchid(Integer.parseInt(branchId));
				periodDetails.setUserid(Integer.parseInt(userId));
				getPeriod++;
				periodList.add(periodDetails);
			}
				periodMap.put(days[i], periodList);
		}
		
		for (Entry<String, List<Perioddetails>> entry : periodMap.entrySet())
		{
			for (Perioddetails perioddetails : entry.getValue()) {
				perioddetails.setDays(entry.getKey());
				periodDetailsList.add(perioddetails);
			}
		}

		Periodmaster periodMaster = new Periodmaster();
		periodMaster.setClass_(fromClass[0]+"--"+toClass);
		periodMaster.setAcademicyear(academicYear);
		periodMaster.setDaystart(dayStartTimeHr+":"+dayStartTimeMin+" "+dayStartAm);
		periodMaster.setDayend(dayEndTimeHr+":"+dayEndTimeMin+" "+dayEndAm);
		periodMaster.setTotalperiods(Integer.parseInt(totalNoOfPeriods));
		periodMaster.setIdperiodmaster(Integer.parseInt(periodmasterid.toString()));
		periodMaster.setBranchid(Integer.parseInt(branchId));
		periodMaster.setUserid(Integer.parseInt(userId));
		Set<Perioddetails> setPeriodDetails = new HashSet<>(periodDetailsList);
		periodMaster.setPeriodDetails(setPeriodDetails);

		result.setSuccess(new PeriodDAO().update(periodMaster));

		return result;
	}


	
}

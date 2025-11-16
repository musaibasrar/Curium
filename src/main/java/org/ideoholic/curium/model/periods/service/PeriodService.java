package org.ideoholic.curium.model.periods.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.academicyear.dao.YearDAO;
import org.ideoholic.curium.model.academicyear.dto.Currentacademicyear;
import org.ideoholic.curium.model.employee.dto.EmployeesWithSalaryResponseDto;
import org.ideoholic.curium.model.employee.service.EmployeeService;
import org.ideoholic.curium.model.periods.dao.PeriodDAO;
import org.ideoholic.curium.model.periods.dto.PeriodDetailsDto;
import org.ideoholic.curium.model.periods.dto.PeriodMasterIdDto;
import org.ideoholic.curium.model.periods.dto.Perioddetails;
import org.ideoholic.curium.model.periods.dto.Periodmaster;
import org.ideoholic.curium.model.periods.dto.PeriodsSaveDto;
import org.ideoholic.curium.model.periods.dto.TeacherTimeTableResponseDto;
import org.ideoholic.curium.model.periods.dto.TimeTableResponseDto;
import org.ideoholic.curium.model.periods.dto.TimeTableViewResponseDto;
import org.ideoholic.curium.model.std.service.StandardService;
import org.ideoholic.curium.model.subjectdetails.dto.SubjectsResponseDto;
import org.ideoholic.curium.model.subjectdetails.service.SubjectDetailsService;
import org.ideoholic.curium.util.DataUtil;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class PeriodService {
	
	private final YearDAO yearDao;
	private final PeriodDAO periodDao;
    private final StandardService standardService;
    private final EmployeeService employeeService;
    private final SubjectDetailsService subjectDetailsService;
    
	/**
    * Size of a byte buffer to read/write file
    */
   private static final int BUFFER_SIZE = 4096;
	
	
   public TimeTableResponseDto periodConfiguration(String branchId) {
       TimeTableResponseDto result = TimeTableResponseDto.builder().success(false).build();

       try {
           Currentacademicyear currentYear = yearDao.showYear();
           result.setCurrentYear(currentYear.getCurrentacademicyear());

           SubjectsResponseDto subjects = subjectDetailsService.readListOfSubjects(branchId);
           result.setSubjects(subjects.getSubjects());

           EmployeesWithSalaryResponseDto employees = employeeService.viewAllEmployee(branchId);
           result.setEmployeeList(employees.getEmployeeList());

           ResultResponse classSecs = standardService.viewClasses(branchId);
           result.setClassSecs(classSecs.getResultList());

           List<Periodmaster> periodMaster = periodDao.getPeriodsDetails(currentYear.getCurrentacademicyear(), Integer.parseInt(branchId));
           result.setPeriodMaster(periodMaster);
       } catch (Exception e) {
           return result;
       }

       result.setSuccess(true);
       return result;

   }

	public ResultResponse savePeriods(PeriodsSaveDto dto, String branchId, String userId) {
		ResultResponse result = ResultResponse.builder().build();
		
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
		String fromClass = DataUtil.emptyString(dto.getFromClass());
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
		periodMaster.setClass_(fromClass);
		periodMaster.setDaystart(dayStartTimeHr+":"+dayStartTimeMin+" "+dayStartAm);
		periodMaster.setDayend(dayEndTimeHr+":"+dayEndTimeMin+" "+dayEndAm);
		periodMaster.setDurationofperiod(durationOfPeriodsHr+":"+durationOfPeriodsMin);
		periodMaster.setTotalperiods(Integer.parseInt(totalNoOfPeriods));
		periodMaster.setBranchid(Integer.parseInt(branchId));
		periodMaster.setUserid(Integer.parseInt(userId));

		result.setSuccess(periodDao.save(periodMaster,periodMap));

		return result;
	}


	public TimeTableViewResponseDto viewTimeTable(String periodMasterId) {
		TimeTableViewResponseDto result = TimeTableViewResponseDto.builder().build();

        if(periodMasterId !=null){
		
			Periodmaster periodMaster = periodDao.getTimeTable(periodMasterId);
			result.setPeriodMaster(periodMaster);
			
			List<Perioddetails> periodD= periodDao.getTimeTablePeriodDetails(periodMasterId);
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
			List<Integer> ids = new ArrayList<>();
			for (String id : periodMasterid) {
				ids.add(Integer.valueOf(id));
			}
			result.setSuccess(periodDao.deletePeriods(ids));
			return result;
		}
		
		result.setSuccess(false);
		return result;
	}


	public TimeTableResponseDto generateTimeTable(String branchId) {
		TimeTableResponseDto result = TimeTableResponseDto.builder().success(false).build();

		List<Periodmaster> periodMaster = new ArrayList<>();
		
        if (branchId != null) {

            Currentacademicyear currentYear = yearDao.showYear();
            result.setCurrentYear(currentYear.getCurrentacademicyear());

            periodMaster = periodDao.getPeriodsDetails(currentYear.getCurrentacademicyear(), Integer.parseInt(branchId));
            result.setPeriodMaster(periodMaster);
        }

        result.setSuccess(!periodMaster.isEmpty());
		return result;
	}


	public TeacherTimeTableResponseDto viewTeacherTimeTable(String teacherName, String branchId) {
		
		TeacherTimeTableResponseDto result = TeacherTimeTableResponseDto.builder().success(false).build();
		List<Map<String, String>> periodMapList = new LinkedList<>();
		
		Map<String,String> mondayMap = new HashMap<>();
		Map<String,String> tuesdayMap = new HashMap<>();
		Map<String,String> wednesdayMap = new HashMap<>();
		Map<String,String> thursdayMap = new HashMap<>();
		Map<String,String> fridayMap = new HashMap<>();
		Map<String,String> saturdayMap = new HashMap<>();
		Map<String,String> sundayMap = new HashMap<>();
		
		if(branchId!=null){
            List<Perioddetails> periodDetailsList = periodDao.getPeriodDetailsForTeacher(teacherName);
			
			for (Perioddetails perioddetails : periodDetailsList) {
				
				Periodmaster periodMaster = periodDao.getTimeTable(perioddetails.getPeriodMaster().getIdperiodmaster().toString());
				
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

				Periodmaster periodMaster = periodDao.getTimeTable(periodMasterId);
				result.setPeriodMaster(periodMaster);

				List<Perioddetails> periodD= periodDao.getTimeTablePeriodDetails(periodMasterId);
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


	public PeriodDetailsDto getPeriodDetail(String branchId) {
		PeriodDetailsDto result = PeriodDetailsDto.builder().build();
        
        try {
        	EmployeesWithSalaryResponseDto employeesWithSalaryResponseDto = employeeService.viewAllEmployee(branchId);
            SubjectsResponseDto subjectsResponse = subjectDetailsService.readListOfSubjectNames(branchId);
            result.setEmployeeList(employeesWithSalaryResponseDto.getEmployeeList());
            result.setEmployeeListProcessSalary(employeesWithSalaryResponseDto.getEmployeeListProcessSalary());
            result.setSubjects(subjectsResponse.getSubjects());
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
		String fromClass = DataUtil.emptyString(dto.getFromClass());
		String toClass = DataUtil.emptyString(dto.getToClass());
		
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
		periodMaster.setClass_(fromClass);
		periodMaster.setAcademicyear(academicYear);
		periodMaster.setDaystart(dayStartTimeHr+":"+dayStartTimeMin+" "+dayStartAm);
		periodMaster.setDayend(dayEndTimeHr+":"+dayEndTimeMin+" "+dayEndAm);
		periodMaster.setTotalperiods(Integer.parseInt(totalNoOfPeriods));
		periodMaster.setIdperiodmaster(Integer.parseInt(periodmasterid.toString()));
		periodMaster.setBranchid(Integer.parseInt(branchId));
		periodMaster.setUserid(Integer.parseInt(userId));
		Set<Perioddetails> setPeriodDetails = new HashSet<>(periodDetailsList);
		periodMaster.setPeriodDetails(setPeriodDetails);

		result.setSuccess(periodDao.update(periodMaster));

		return result;
	}
}


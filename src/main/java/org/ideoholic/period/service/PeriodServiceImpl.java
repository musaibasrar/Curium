package org.ideoholic.period.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.ideoholic.employee.service.EmployeeServiceImpl;
import org.ideoholic.period.dto.SaveParamDto;
import org.ideoholic.standard.service.StandardServiceImpl;
import org.ideoholic.subjectdetails.service.SubjectDetailsServiceImpl;

import com.model.academicyear.dao.YearDAO;
import com.model.academicyear.dto.Currentacademicyear;
import com.model.employee.service.EmployeeService;
import com.model.periods.dao.PeriodDAO;
import com.model.periods.dto.Perioddetails;
import com.model.periods.dto.Periodmaster;
import com.model.std.service.StandardService;
import com.model.subjectdetails.service.SubjectDetailsService;
import com.util.DataUtil;

public class PeriodServiceImpl implements PeriodService {
	

	SubjectDetailsServiceImpl subjectDetails;
	EmployeeServiceImpl employee;
	StandardServiceImpl standard;
	
public String periodConfiguration(String branchId){
	StringBuffer sb = new StringBuffer();
	sb.append("{");
		
		List<Periodmaster> periodMaster = new ArrayList<Periodmaster>();
		
		try {
	                Currentacademicyear currentYear = new YearDAO().showYear();
	                sb.append("currentYear").append(currentYear.getCurrentacademicyear());
	                
	                subjectDetails.readListOfSubjects(branchId);
	                
	                employee.ViewAllEmployee(branchId);
	                standard.viewClasses(branchId);
	                periodMaster = new PeriodDAO().getPeriodsDetails(currentYear.getCurrentacademicyear(), Integer.parseInt(branchId.toString()));
	                sb.append("periodmasterlist").append(periodMaster);
		    
                } catch (Exception e) {
                	sb.append("result:").append(false);
                   return sb.toString();
                }
		sb.append("result:").append(true);
		sb.append("}");
		return sb.toString();
		
	}


public String savePeriods(String branchId, SaveParamDto saveDto) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
	
	Map<String,List<Perioddetails>> periodMap = new HashMap<String,List<Perioddetails>>();
	int getPeriod=0;
	
	for(int i=0; i<saveDto.getDays().length; i++){
		List<Perioddetails> periodList = new ArrayList<Perioddetails>();
		
		for (int j = 0; j < Integer.parseInt(saveDto.getTotalNoOfPeriods()); j++) {
			Perioddetails periodDetails = new Perioddetails();
			periodDetails.setPeriods(saveDto.getPeriods()[getPeriod]);
			periodDetails.setSubject(saveDto.getSubjects()[getPeriod]);
			periodDetails.setStaff(saveDto.getStaff()[getPeriod]);
			periodDetails.setTimings(saveDto.getPeriodStartTimeHr()[getPeriod]+":"+saveDto.getPeriodStartTimeMin()[getPeriod]+": "+saveDto.getPeriodStartTimeAm()[getPeriod]+ " To "+saveDto.getPeriodEndTimeHr()[getPeriod]+":"+saveDto.getPeriodEndTimeMin()[getPeriod]+" "+saveDto.getPeriodEndTimeAm()[getPeriod]);
			periodDetails.setBranchid(Integer.parseInt(branchId.toString()));
			getPeriod++;
			periodList.add(periodDetails);
		}
			periodMap.put(saveDto.getDays()[i], periodList);
	}

	List<Perioddetails> periodDetailsList = new ArrayList<Perioddetails>();
	Periodmaster periodMaster = new Periodmaster();
	periodMaster.setAcademicyear(saveDto.getAcademicYear());
	periodMaster.setClass_(saveDto.getFromClass());
	periodMaster.setDaystart(saveDto.getDayStartTimeHr()+":"+saveDto.getDayStartTimeMin()+" "+saveDto.getDayStartAm());
	periodMaster.setDayend(saveDto.getDayEndTimeHr()+":"+saveDto.getDayEndTimeMin()+" "+saveDto.getDayEndAm());
	periodMaster.setDurationofperiod(saveDto.getDurationOfPeriodsHr()+":"+saveDto.getDurationOfPeriodsMin());
	periodMaster.setTotalperiods(Integer.parseInt(saveDto.getTotalNoOfPeriods()));
	periodMaster.setBranchid(Integer.parseInt(branchId.toString()));
	boolean save= new PeriodDAO().save(periodMaster,periodMap);
	sb.append("}");
	return sb.toString();
}

public String viewTimeTable(String periodMasterid) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
	
	if(periodMasterid!=null){
	
		Periodmaster periodMaster = new PeriodDAO().getTimeTable(periodMasterid);
		sb.append("timetable").append(periodMaster);
		
		List<Perioddetails> periodD= new PeriodDAO().getTimeTablePeriodDetails(periodMasterid);
		sb.append("timetableperioddetails").append(periodD);
		
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
		sb.append("periodmap").append(periodMap);
		sb.append("result:").append(true);
		return sb.toString();
	}
	sb.append("result:").append(false);
	sb.append("}");
	return sb.toString();
}

public String deletePeriods(String[] periodMasterid1) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
	
	if (periodMasterid1 != null) {
		List ids = new ArrayList();
		for (String id : periodMasterid1) {
			ids.add(Integer.valueOf(id));
		}
		boolean deletePeriods= new PeriodDAO().deletePeriods(ids);
	}
	sb.append("result:").append(false);
	sb.append("}");
	return sb.toString();
}

public String generateTimeTable(String braanchId) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");

	List<Periodmaster> periodMaster = new ArrayList<Periodmaster>();
	
	if(braanchId!=null){

		Currentacademicyear currentYear = new YearDAO().showYear();
		sb.append("currentYear").append(currentYear.getCurrentacademicyear());
       
        periodMaster = new PeriodDAO().getPeriodsDetails(currentYear.getCurrentacademicyear(),Integer.parseInt(braanchId.toString()));
        sb.append("periodmasterlist").append(periodMaster);
	}
	
    if(periodMaster.isEmpty()){
    	
    	sb.append("result:").append(false);
    	return sb.toString();
    }
    sb.append("result:").append(true);
    sb.append("}");
	return sb.toString();
	

}

public String viewTeacherTimeTable(String branchId,String teacherName) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
	
	boolean result = false;
	List<Map> periodMapList = new LinkedList<Map>();
	
	Map<String,String> mondayMap = new HashMap<String,String>();
	Map<String,String> tuesdayMap = new HashMap<String,String>();
	Map<String,String> wednesdayMap = new HashMap<String,String>();
	Map<String,String> thursdayMap = new HashMap<String,String>();
	Map<String,String> fridayMap = new HashMap<String,String>();
	Map<String,String> saturdayMap = new HashMap<String,String>();
	Map<String,String> sundayMap = new HashMap<String,String>();
	
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
		
		 sb.append("teachername").append(teacherName);
		 sb.append("teacherperiodmasterlist").append(periodMapList);
		
		result = true;
	}
	
	sb.append("}");
	return sb.toString();
}

}

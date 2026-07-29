package org.ideoholic.curium.model.assessmentdetails.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.assessmentdetails.dao.HolisticAssessmentDAO;
import org.ideoholic.curium.model.assessmentdetails.dto.AddAssessmentDto;
import org.ideoholic.curium.model.assessmentdetails.dto.AddAssessmentScheduleDto;
import org.ideoholic.curium.model.assessmentdetails.dto.AssessmentIdsDto;
import org.ideoholic.curium.model.assessmentdetails.dto.AssessmentListResponseDto;
import org.ideoholic.curium.model.assessmentdetails.dto.AssessmentScheduleDto;
import org.ideoholic.curium.model.assessmentdetails.dto.AssessmentScheduleResponseDto;
import org.ideoholic.curium.model.assessmentdetails.dto.HolisticAssessment;
import org.ideoholic.curium.model.assessmentdetails.dto.HolisticAssessmentSchedule;
import org.ideoholic.curium.util.DataUtil;
import org.ideoholic.curium.util.DateUtil;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * Service for Holistic Development Assessment
 * Duplicated from ExamDetailsService for independent assessment module
 */
@Slf4j
@Service
public class HolisticAssessmentService {

    public ResultResponse addAssessment(AddAssessmentDto addAssessmentDto, String branchId) {

        HolisticAssessment assessment = new HolisticAssessment();

        if (branchId != null) {
            assessment.setAssessmentname(DataUtil.emptyString(addAssessmentDto.getAssessmentName()));
            assessment.setBranchid(Integer.parseInt(branchId));
            assessment = new HolisticAssessmentDAO().addAssessment(assessment);
        }
        
        if (assessment == null) {
            return ResultResponse.builder().success(false).build();
        }
        return ResultResponse.builder().success(true).build();
    }

    public AssessmentListResponseDto readListOfAssessments(String branchId) {
        AssessmentListResponseDto assessmentListResponseDto = new AssessmentListResponseDto();
        
        if (branchId != null) {
            List<HolisticAssessment> assessments = new HolisticAssessmentDAO().readListOfAssessments(Integer.parseInt(branchId));
            assessmentListResponseDto.setAssessments(assessments);
            assessmentListResponseDto.setSuccess(true);
            
            if (assessments == null) {
                assessmentListResponseDto.setSuccess(false);
            }
        }

        return assessmentListResponseDto;
    }

    public ResultResponse deleteMultiple(AssessmentIdsDto assessmentIdsDto) {

        String[] assessmentIds = assessmentIdsDto.getAssessmentIds();
        
        if (assessmentIds != null) {
            List<Integer> ids = new ArrayList<>();
            for (String id : assessmentIds) {
                log.debug("Assessment id: " + id);
                ids.add(Integer.valueOf(id));
            }
            new HolisticAssessmentDAO().deleteMultiple(ids);
            return ResultResponse.builder().success(true).build();
        } else {
            return ResultResponse.builder().success(false).build();
        }
    }

    public ResultResponse addSchedule(AddAssessmentScheduleDto addScheduleDto, String branchId) {

        boolean result;
        List<HolisticAssessmentSchedule> assessmentScheduleList = new ArrayList<>();

        String[] subject = addScheduleDto.getSubject();
        String[] date = addScheduleDto.getDate();
        String[] startTime = addScheduleDto.getStartTime();
        String[] endTime = addScheduleDto.getEndTime();
        String[] classesSelected = addScheduleDto.getClassesSelected();
        String sectionSelected = DataUtil.emptyString(addScheduleDto.getSectionSelected());

        if (branchId != null) {

            for (String selectedClass : classesSelected) {

                for (int i = 0; i < subject.length; i++) {
                    HolisticAssessmentSchedule assessmentSchedule = new HolisticAssessmentSchedule();
                    assessmentSchedule.setAcademicyear(DataUtil.emptyString(addScheduleDto.getAcademicyear()));
                    
                    if(sectionSelected.equalsIgnoreCase("")) {
                        assessmentSchedule.setClasses(selectedClass);
                    } else {
                        assessmentSchedule.setClasses(selectedClass+"--"+sectionSelected);
                    }
                    
                    assessmentSchedule.setAssessmentname(DataUtil.emptyString(addScheduleDto.getAssessment()));
                    assessmentSchedule.setDate(DateUtil.dateParserUpdateStd(date[i]));
                    
                    // Parse start time
                    String[] starttimeSplit = startTime[i].split(":");
                    String hours = starttimeSplit[0];
                    String meridian = null;
                    String outputStartTime = null;
                    
                    if (Integer.parseInt(hours) < 12) {
                        outputStartTime = startTime[i];
                        meridian = "AM";
                    } else if (Integer.parseInt(hours) >= 12) {
                        DateFormat df = new SimpleDateFormat("HH:mm");
                        DateFormat outputformat = new SimpleDateFormat("hh:mm");
                        Date date1;
                        try {
                            date1 = df.parse(startTime[i]);
                            outputStartTime = outputformat.format(date1);
                        } catch (ParseException pe) {
                            pe.printStackTrace();
                        }
                        meridian = "PM";
                    }

                    assessmentSchedule.setStarttime(outputStartTime + " " + meridian);
                    
                    // Parse end time
                    String[] endtimeSplit = endTime[i].split(":");
                    String endhours = endtimeSplit[0];
                    String endmeridian = null;
                    String outputEndTime = null;
                    
                    if (Integer.parseInt(endhours) < 12) {
                        outputEndTime = endTime[i];
                        endmeridian = "AM";
                    } else if (Integer.parseInt(endhours) >= 12) {
                        DateFormat df = new SimpleDateFormat("HH:mm");
                        DateFormat outputformat = new SimpleDateFormat("hh:mm");
                        Date date1;

                        try {
                            date1 = df.parse(endTime[i]);
                            outputEndTime = outputformat.format(date1);
                        } catch (ParseException pe) {
                            pe.printStackTrace();
                        }

                        endmeridian = "PM";
                    }

                    assessmentSchedule.setEndtime(outputEndTime + " " + endmeridian);
                    assessmentSchedule.setSubject(DataUtil.emptyString(subject[i]));
                    assessmentSchedule.setBranchid(Integer.parseInt(branchId));
                    assessmentScheduleList.add(assessmentSchedule);
                }
            }
            result = new HolisticAssessmentDAO().addAssessmentSchedule(assessmentScheduleList);
            return ResultResponse.builder().success(result).build();
        }
        return ResultResponse.builder().build();
    }

    public AssessmentScheduleResponseDto getAssessmentSchedule(String branchId) {

        AssessmentScheduleResponseDto result = new AssessmentScheduleResponseDto();

        if (branchId != null) {
            List<HolisticAssessmentSchedule> assessments = new HolisticAssessmentDAO().readListOfAssessmentSchedule(null, null, null, Integer.parseInt(branchId));
            result.setAssessmentschedules(assessments);
            result.setSuccess(true);
            if (assessments == null) {
                result.setSuccess(false);
            }
        }

        return result;
    }

    public AssessmentScheduleResponseDto getAssessmentScheduleDetails(AssessmentScheduleDto assessmentScheduleDto, String branchId) {
        AssessmentScheduleResponseDto result = new AssessmentScheduleResponseDto();

        String academicYear = DataUtil.emptyString(assessmentScheduleDto.getAcademicYear());
        String classH = DataUtil.emptyString(assessmentScheduleDto.getClassH());
        String classAdmno = DataUtil.emptyString(assessmentScheduleDto.getClassAdmno());
        String studentName = DataUtil.emptyString(assessmentScheduleDto.getStudentName());
        String assessment = DataUtil.emptyString(assessmentScheduleDto.getAssessment());
        String section = DataUtil.emptyString(assessmentScheduleDto.getSection());

        if (academicYear.isEmpty()) {
            academicYear = DataUtil.emptyString(assessmentScheduleDto.getAssessmentName());
        }
        if (classH.isEmpty()) {
            classH = DataUtil.emptyString(assessmentScheduleDto.getClassDetails());
        }
        if (assessment.isEmpty()) {
            assessment = DataUtil.emptyString(assessmentScheduleDto.getAssessmentName());
        }

        result.setSelectedclass(classH);
        result.setSelectedassessment(assessment);
        result.setSelectedstudentname(studentName);
        result.setSelectedclassandsec(classAdmno);
        result.setSelectedadmissionno(classAdmno);

        if (!classAdmno.equals("")) {
            String[] c = classAdmno.split("--");
            classH = c[0];
        } else if (!section.equals("")) {
            classH = classH + "--" + section;
        }

        if (branchId != null) {
            List<HolisticAssessmentSchedule> assessmentschedules = new HolisticAssessmentDAO()
                    .getAssessmentScheduleDetails(academicYear, classH, assessment, Integer.parseInt(branchId));
            result.setAssessmentschedules(assessmentschedules);
            result.setSuccess(true);
            if (!assessmentschedules.isEmpty()) {
                return result;
            }
        }
        return result;
    }

    public ResultResponse deleteAssessmentSchedule(AssessmentIdsDto assessmentIdsDto) {

        String[] scheduleIds = assessmentIdsDto.getAssessmentIds();
        
        if (scheduleIds != null) {
            List<Integer> ids = new ArrayList<>();
            for (String id : scheduleIds) {
                log.debug("Schedule id: " + id);
                ids.add(Integer.valueOf(id));
            }
            new HolisticAssessmentDAO().deleteMultipleSchedule(ids);
            return ResultResponse.builder().success(true).build();
        } else {
            return ResultResponse.builder().success(false).build();
        }
    }
}

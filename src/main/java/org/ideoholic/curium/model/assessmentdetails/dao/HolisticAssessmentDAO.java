package org.ideoholic.curium.model.assessmentdetails.dao;

import java.util.ArrayList;
import java.util.List;

import org.ideoholic.curium.model.assessmentdetails.dto.HolisticAssessment;
import org.ideoholic.curium.model.assessmentdetails.dto.HolisticAssessmentSchedule;
import org.ideoholic.curium.repositories.HolisticAssessmentRepository;
import org.ideoholic.curium.repositories.HolisticAssessmentScheduleRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class HolisticAssessmentDAO {

    private final HolisticAssessmentRepository holisticAssessmentRepo;
    private final HolisticAssessmentScheduleRepository holisticAssessmentScheduleRepo;

    @Transactional
    public HolisticAssessment create(HolisticAssessment assessment) {
        try {
            assessment = holisticAssessmentRepo.save(assessment);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return assessment;
    }

    @Transactional
    public List<HolisticAssessment> readListOfAssessments(int branchId) {
        List<HolisticAssessment> assessments = new ArrayList<>();
        try {
            assessments = holisticAssessmentRepo.findByBranchid(branchId);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return assessments;
    }

    @Transactional
    public boolean deleteMultiple(List<Integer> ids) {
        try {
            holisticAssessmentRepo.deleteByAssessmentidIn(ids);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(e.getMessage(), e);
            return false;
        }
    }

    @Transactional
    public boolean addSchedule(List<HolisticAssessmentSchedule> assessmentScheduleList) {
        try {
            holisticAssessmentScheduleRepo.saveAll(assessmentScheduleList);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(e.getMessage(), e);
            return false;
        }
    }

    @Transactional
    public List<HolisticAssessmentSchedule> getAssessmentSchedule(int branchId) {
        List<HolisticAssessmentSchedule> schedule = new ArrayList<>();
        try {
            schedule = holisticAssessmentScheduleRepo.findByBranchid(branchId);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return schedule;
    }

    @Transactional
    public List<HolisticAssessmentSchedule> getAssessmentScheduleDetails(String assessment, String classH,
            String academicYear, int branchId) {
        List<HolisticAssessmentSchedule> schedule = new ArrayList<>();
        try {
            schedule = holisticAssessmentScheduleRepo
                    .findByAssessmentnameAndClassesAndAcademicyearAndBranchidOrderByDateAsc(assessment, classH,
                            academicYear, branchId);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return schedule;
    }

    @Transactional
    public boolean deleteAssessmentSchedule(List<Integer> ids) {
        try {
            holisticAssessmentScheduleRepo.deleteByIdassessmentscheduleIn(ids);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(e.getMessage(), e);
            return false;
        }
    }
}

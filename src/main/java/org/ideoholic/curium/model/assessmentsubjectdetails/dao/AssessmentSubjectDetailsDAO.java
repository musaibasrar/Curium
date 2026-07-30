package org.ideoholic.curium.model.assessmentsubjectdetails.dao;

import java.util.ArrayList;
import java.util.List;

import org.ideoholic.curium.model.assessmentsubjectdetails.dto.AssessmentSubject;
import org.ideoholic.curium.model.assessmentsubjectdetails.dto.AssessmentSubjectMaster;
import org.ideoholic.curium.repositories.AssessmentSubjectMasterRepository;
import org.ideoholic.curium.repositories.AssessmentSubjectRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class AssessmentSubjectDetailsDAO {

    private final AssessmentSubjectRepository assessmentSubjectRepo;
    private final AssessmentSubjectMasterRepository assessmentSubjectMasterRepo;

    @Transactional
    public List<AssessmentSubject> readListOfAssessmentSubjects(int branchId) {
        List<AssessmentSubject> subjects = new ArrayList<>();
        try {
            subjects = assessmentSubjectRepo.findByBranchid(branchId);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return subjects;
    }

    @Transactional
    public AssessmentSubject addAssessmentSubject(AssessmentSubject subject) {
        try {
            subject = assessmentSubjectRepo.save(subject);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return subject;
    }

    @Transactional
    public boolean deleteMultiple(List<Integer> ids) {
        try {
            assessmentSubjectRepo.deleteByAssessmentsubjectidIn(ids);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(e.getMessage(), e);
            return false;
        }
    }

    @Transactional
    public AssessmentSubjectMaster addAssessmentSubjectMaster(AssessmentSubjectMaster subjectMaster) {
        try {
            subjectMaster = assessmentSubjectMasterRepo.save(subjectMaster);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return subjectMaster;
    }

    @Transactional
    public List<AssessmentSubjectMaster> readListOfSubjectNames(int branchId) {
        List<AssessmentSubjectMaster> subjectNames = new ArrayList<>();
        try {
            subjectNames = assessmentSubjectMasterRepo.findByBranchid(branchId);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return subjectNames;
    }

    @Transactional
    public boolean deleteMultipleSubjectMaster(List<Integer> ids) {
        try {
            assessmentSubjectMasterRepo.deleteBySubjectidIn(ids);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(e.getMessage(), e);
            return false;
        }
    }

    @Transactional
    public List<AssessmentSubject> readAllAssessmentSubjects(String classCategory, int branchId) {
        List<AssessmentSubject> subjects = new ArrayList<>();
        try {
            subjects = assessmentSubjectRepo.findByAssessmentclassAndBranchid(classCategory, branchId);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return subjects;
    }

    @Transactional
    public AssessmentSubject getAssessmentSubjectDetails(Integer assessmentSubjectId) {
        AssessmentSubject subject = null;
        try {
            subject = assessmentSubjectRepo.findByAssessmentsubjectid(assessmentSubjectId);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return subject;
    }

    @Transactional
    public List<Object[]> getCategoriesWithSubjects(int branchId) {
        List<Object[]> categories = new ArrayList<>();
        try {
            categories = assessmentSubjectMasterRepo.getCategoriesWithSubjects(branchId);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return categories;
    }
}

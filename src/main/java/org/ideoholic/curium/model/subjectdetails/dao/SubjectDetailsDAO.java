package org.ideoholic.curium.model.subjectdetails.dao;

import java.util.ArrayList;
import java.util.List;

import org.ideoholic.curium.model.subjectdetails.dto.SubSubject;
import org.ideoholic.curium.model.subjectdetails.dto.Subject;
import org.ideoholic.curium.model.subjectdetails.dto.Subjectmaster;
import org.ideoholic.curium.repositories.SubSubjectRepository;
import org.ideoholic.curium.repositories.SubjectMasterRepository;
import org.ideoholic.curium.repositories.SubjectRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Musaib_2
 *
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class SubjectDetailsDAO {

    private final SubjectRepository subjectRepo;
    private final SubSubjectRepository subSubjectRepo;
    private final SubjectMasterRepository subjectmasterRepo;


    @Transactional
    public List<Subject> readListOfSubjects(int branchId, String examClass) {
        List<Subject> results = new ArrayList<>();
        try {
            // session.createQuery("From Subject where examclass = '"+examClass+"' and branchid="+branchId).list();
            results = subjectRepo.findByExamclassAndBranchid(examClass, branchId);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return results;
    }

    @Transactional
    public Subject addSubject(Subject subject) {
        try {
            // session.save(subject);
            return subjectRepo.save(subject);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return subject;
    }

    @Transactional
    public void deleteMultiple(List<Integer> ids) {
        try {
            // session.createQuery("delete from Subject where subid IN (:ids)").setParameterList("ids", ids).executeUpdate();
            subjectRepo.deleteBySubidIn(ids);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }

    @Transactional
    public Subject getSubjectDetails(Integer subid) {
        Subject subject = new Subject();
        try {
            // session.createQuery("From Subject where id="+subid)
            Subject found = subjectRepo.findBySubid(subid);
            if(found != null) subject = found;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(e.getMessage(), e);
        }
        return subject;
    }

    @Transactional
    public Subjectmaster addSubjectMaster(Subjectmaster subject) {
        try {
            // session.save(subject);
            return subjectmasterRepo.save(subject);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(e.getMessage(), e);
            e.printStackTrace();
            return subject;
        }
    }

    @Transactional
    public List<Subjectmaster> readListOfSubjectNames(int branchId) {
        List<Subjectmaster> results = new ArrayList<>();
        try {
            // session.createQuery("From Subjectmaster where branchid="+branchId)
            results = subjectmasterRepo.findByBranchid(branchId);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return results;
    }

    @Transactional
    public void deleteMultipleSubjects(List<Integer> ids) {
        try {
            // session.createQuery("delete from Subjectmaster where subjectid IN (:ids)").setParameterList("ids", ids).executeUpdate();
            subjectmasterRepo.deleteBySubjectidIn(ids);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }

    @Transactional
    public List<Subject> readAllSubjects(int branchId) {
        List<Subject> results = new ArrayList<>();
        try {
            // session.createQuery("From Subject where branchid="+branchId)
            results = subjectRepo.findByBranchid(branchId);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return results;
    }

    @Transactional
    public List<Subject> readAllSubjectsClassWise(int branchId, String examClass, String examName) {
        List<Subject> results = new ArrayList<>();
        try {
            // session.createQuery("From Subject where examclass='"+examClass+"' and examname='"+examName+"' and branchid="+branchId)
            results = subjectRepo.fetchByExamSubject(examClass, examName, branchId);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return results;
    }

    @Transactional
    public List<Subjectmaster> readListOfSubjectMasterNames(int branchId) {
        List<Subjectmaster> results = new ArrayList<>();
        try {
            // session.createQuery("From Subjectmaster where branchid="+branchId)
            results = subjectmasterRepo.findByBranchid(branchId);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return results;
    }

    @Transactional
    public Subject readSubjectByExam(int branchId, String examClass, String examName, int subId) {
        Subject results = new Subject();
        try {
            // session.createQuery("From Subject where examclass='"+examClass+"' and subjectid='"+subId+"' and examname='"+examName+"' and branchid="+branchId)
            Subject found = subjectRepo.fetchByExamNameSubject(examClass, subId, examName, branchId);
            if(found != null) results = found;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(e.getMessage(), e);
        }
        return results;
    }

    @Transactional
    public SubSubject readSubSubject(int branchId, int subjectId, String subSubject) {
        SubSubject subsubject = new SubSubject();
        try {
            // session.createQuery("From SubSubject where subjectid='"+subjectId+"' and subsubjectname='"+subSubject+"'  and branchid = "+branchId+"");
            SubSubject found = subSubjectRepo.fetchSubSubjects(subjectId, subSubject, branchId);
            if(found != null) subsubject = found;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return subsubject;
    }

    @Transactional
    public List<SubSubject> readListOfSubSubject(int branchId) {
        List<SubSubject> results = new ArrayList<>();
        try {
            // session.createQuery("From SubSubject where branchid="+branchId)
            results = subSubjectRepo.findByBranchid(branchId);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return results;
    }

    @Transactional
    public boolean addSubSubject(List<SubSubject> subSubjectList) {
        boolean result = false;
        try {
            /*
                for (SubSubject subSubject : subSubjectList) { session.save(subSubject); }
            */
            subSubjectRepo.saveAll(subSubjectList);
            result = true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return result;
    }

    @Transactional
    public void deleteMultipleSubSubject(List<Integer> ids) {
        try {
            // session.createQuery("delete from SubSubject where subjectid IN (:ids)").setParameterList("ids", ids).executeUpdate();
            subSubjectRepo.deleteBySubjectidIn(ids);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }
}
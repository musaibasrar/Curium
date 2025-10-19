package org.ideoholic.curium.model.marksdetails.dao;

import java.util.ArrayList;
import java.util.List;

import org.ideoholic.curium.model.marksdetails.dto.ExamRank;
import org.ideoholic.curium.model.marksdetails.dto.Marks;
import org.ideoholic.curium.model.marksdetails.dto.MarksGrade;
import org.ideoholic.curium.model.marksdetails.dto.SubjectGrade;
import org.ideoholic.curium.repositories.ExamRankRepository;
import org.ideoholic.curium.repositories.MarksGradeRepository;
import org.ideoholic.curium.repositories.MarksRepository;
import org.ideoholic.curium.repositories.SubjectGradeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MarksDetailsDAO {

    private final MarksRepository marksRepository;

    private final MarksGradeRepository marksGradeRepository;

    private final ExamRankRepository examRankRepository;

    private final SubjectGradeRepository subjectGradeRepository;

    @Transactional
    public String addMarks(List<Marks> marksList) {
        String output = "success";
        try {
            // session.save(marks);
            marksRepository.saveAll(marksList);
        } catch (Exception hibernateException) {
            log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            output = "Duplicate";
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return output;
    }

    @Transactional
    public List<Marks> readListOfMarks(List<Integer> ids) {
        List<Marks> results = new ArrayList<Marks>();
        try {
            // Query query = session.createQuery("From Marks where sid IN (:ids)");
            results = marksRepository.findByStudentSidIn(ids);
        } catch (Exception hibernateException) {
            log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            throw hibernateException;
        }
        return results;
    }

    @Transactional
    public List<Marks> readListOfMarks(Integer id, int subjectId, int examId) {
        List<Marks> results = new ArrayList<Marks>();
        try {
            // Query query = session.createQuery("From Marks where subid="+subjectId+" and examid="+examId+" and sid IN (:ids)");
            results = marksRepository.findByStudentIdSubjectIdExamId(id, subjectId, examId);
        } catch (Exception hibernateException) {
            log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            throw hibernateException;
        }
        return results;
    }

    @Transactional
    public boolean updateMarks(List<Marks> marksList) {
        boolean result = false;
        try {
            // session.update(marks);
            marksRepository.saveAll(marksList);
            result = true;
        } catch (Exception hibernateException) {
            log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            throw hibernateException;
        }
        return result;
    }

    @Transactional
    public boolean deleteMultiple(List<Integer> ids, List<Integer> studentListids) {
        boolean result = false;
        try {
            // Query query = session.createQuery("delete from Marks  where marksid IN (:ids) and sid IN (:studentids)");
            marksRepository.deleteByMarksidInAndStudentSidIn(ids, studentListids);
            result = true;
        } catch (Exception hibernateException) {
            log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            result = false;
            throw hibernateException;
        }
        return result;
    }

    @Transactional
    public List<Marks> readMarksforStudent(int id, String currentAcademicYear, int examId) {
        List<Marks> results = new ArrayList<Marks>();
        try {
            // Query query = session.createQuery("From Marks where sid = '"+id+"' and academicyear = '"+currentAcademicYear+"' and examid = '"+examId+"' ORDER BY examid,subid ASC");
            results = marksRepository.findByStudentIdAndAcademicyearAndExamId(id, currentAcademicYear, examId);
        } catch (Exception hibernateException) {
            log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            throw hibernateException;
        }
        return results;
    }

    @Transactional
    public List<Marks> readMarksPerExam(Integer sid, Integer exid, String currentAcademicYear) {
        List<Marks> results = new ArrayList<Marks>();
        try {
            // Query query = session.createQuery("From Marks where sid = "+sid+" and examid = "+exid+" and academicyear = '"+currentAcademicYear+"' ORDER BY subid ASC");
            results = marksRepository.findByStudentIdAndExamIdAndAcademicYear(sid, exid, currentAcademicYear);
        } catch (Exception hibernateException) {
            log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            throw hibernateException;
        }
        return results;
    }

    @Transactional
    public List<MarksGrade> readMarksGrade(int branchid) {
        List<MarksGrade> results = new ArrayList<MarksGrade>();
        try {
            // Query query = session.createQuery("From MarksGrade where branchid = "+branchid+"");
            results = marksGradeRepository.findByBranchid(branchid);
        } catch (Exception hibernateException) {
            log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            throw hibernateException;
        }
        return results;
    }

    @Transactional
    public boolean saveMarks(List<ExamRank> examRankList) {
        boolean result = false;
        try {
            for (ExamRank examrank : examRankList) {
                // Original: Query query = session.createQuery("From ExamRank where sid="+examrank.fetchSid()+" and examid="+examrank.fetchExamid()+" and academicyear='"+examrank.getAcademicyear()+"' and branchid = "+examrank.getBranchid()+"");
                ExamRank found = examRankRepository.findByStudentIdExamIdAcademicYearAndBranchId(examrank.fetchSid(), examrank.fetchExamid(), examrank.getAcademicyear(), examrank.getBranchid());
                if (found == null) {
                    examRankRepository.save(examrank);
                } else {
                    // Query queryUpdate = session.createSQLQuery("update examrank set marksobtained="+examrank.getMarksobtained()+" where id = "+examrank.getId()+"");
                    found.setMarksobtained(examrank.getMarksobtained());
                    examRankRepository.save(found);
                }
            }
            result = true;
        } catch (Exception hibernateException) {
            log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            throw hibernateException;
        }
        return result;
    }

    @Transactional
    public ExamRank getExamRank(int sid, Integer exid, String currentAcademicYear, int branchid) {
        ExamRank ex = null;
        try {
            // Query query = session.createQuery("From ExamRank where sid="+sid+" and examid="+exid+" and academicyear='"+currentAcademicYear+"' and branchid="+branchid+"");
            ex = examRankRepository.findByStudentIdExamIdAcademicYearAndBranchId(sid, exid, currentAcademicYear, branchid);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
            throw e;
        }
        return ex;
    }

    @Transactional
    public List<SubjectGrade> readSubjectGrade(int branchid, int examid, String classSelected) {
        List<SubjectGrade> results = new ArrayList<SubjectGrade>();
        try {
            // Query query = session.createQuery("From SubjectGrade where classsec='"+classSelected+"' and branchid = "+branchid+"");
            results = subjectGradeRepository.findByClasssecAndBranchid(classSelected, branchid);
        } catch (Exception hibernateException) {
            log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            throw hibernateException;
        }
        return results;
    }

    @Transactional
    public List<ExamRank> getListExamRank(List<Integer> studentsIds, Integer exid, String currentAcademicYear, int branchId) {
        List<ExamRank> exmaRankList = new ArrayList<ExamRank>();
        try {
            // Query query = session.createQuery("From ExamRank where examid="+exid+" and academicyear='"+currentAcademicYear+"' and branchid="+branchId+" and sid IN (:ids)");
            exmaRankList = examRankRepository.findByExamIdAcademicYearBranchIdAndStudentIdIn(exid, currentAcademicYear, branchId, studentsIds);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
            throw e;
        }
        return exmaRankList;
    }

    @Transactional
    public boolean updateExamRank(List<ExamRank> listExamRank) {
        boolean result = false;
        try {
            for (ExamRank examrank : listExamRank) {
                // Query queryUpdate = session.createSQLQuery("update examrank set rank="+examrank.getRank()+" where id = "+examrank.getId()+"");
                examRankRepository.findById(examrank.getId()).ifPresent(examRank -> {
                	examRank.setRank(examrank.getRank());
                    examRankRepository.save(examRank);
                });
            }
            result = true;
        } catch (Exception hibernateException) {
            log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            throw hibernateException;
        }
        return result;
    }

    @Transactional
    public List<Marks> readListOfMarksPerSubject(List<Integer> ids, int subjectId, int exid) {
        List<Marks> results = new ArrayList<Marks>();
        try {
            // Query query = session.createQuery("From Marks where subid="+subjectId+" and examid="+exid+" and sid IN (:ids)");
            results = marksRepository.findByStudentSubjectIdAndExamId(ids, subjectId, exid);
        } catch (Exception hibernateException) {
            log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            throw hibernateException;
        }
        return results;
    }

    @Transactional
    public List<Marks> readMarksforStudentPerSubject(int sid, String currentAcademicYear, int subid) {
        List<Marks> results = new ArrayList<Marks>();
        try {
            // Query query = session.createQuery("From Marks where sid = "+sid+" and subid = "+subid+" and academicyear = '"+currentAcademicYear+"' ORDER BY subid ASC");
            results = marksRepository.findByStudentIdSubjectIdAndAcademicYear(sid, subid, currentAcademicYear);
        } catch (Exception hibernateException) {
            log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            throw hibernateException;
        }
        return results;
    }

    @Transactional
    public List<Marks> readMarksPerExamPerSubject(Integer sid, String currentAcademicYear, List<Integer> exid) {
        List<Marks> results = new ArrayList<Marks>();
        try {
            // Query query = session.createQuery("From Marks where sid = "+sid+" and academicyear = '"+currentAcademicYear+"' and examid IN (:ids) ORDER BY subid ASC");
            results = marksRepository.findByStudentIdAcademicYearAndExamIdIn(sid, currentAcademicYear, exid);
        } catch (Exception hibernateException) {
            log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            throw hibernateException;
        }
        return results;
    }

    @Transactional
    public String addMarksSubSubject(List<Marks> marksList, List<Marks> marksListA1, List<Marks> marksListA2, List<Marks> marksListA3, List<Marks> marksListA4) {
        String output = "success";
        try {
            List<Marks> allMarks = new ArrayList<>();
            allMarks.addAll(marksList);
            allMarks.addAll(marksListA1);
            allMarks.addAll(marksListA2);
            allMarks.addAll(marksListA3);
            allMarks.addAll(marksListA4);
            // session.save(marks)
            marksRepository.saveAll(allMarks);
        } catch (Exception hibernateException) {
            log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            output = "Duplicate";
            throw hibernateException;
        }
        return output;
    }

    @Transactional
    public boolean updateMarksSub(String[] marksid, String[] studentsMarks) {
        try {
            for (int i = 0; i < studentsMarks.length; i++) {
                // Query query = session.createSQLQuery("update marks set marksobtained = '"+studentsMarks[i]+"' where marksid = '"+marksid[i]+"'");
                Integer id = Integer.valueOf(marksid[i]);
                Float marksObtained = Float.valueOf(studentsMarks[i]);
                marksRepository.findById(id).ifPresent(marks -> {
                	marks.setMarksobtained(marksObtained);
                    marksRepository.save(marks);
                });
            }
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            log.info(e.getLocalizedMessage(), e);
            throw e;
        }
    }
}
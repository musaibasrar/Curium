package org.ideoholic.curium.model.studentdiary.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.ideoholic.curium.model.diary.dao.diaryDAO;
import org.ideoholic.curium.model.diary.dto.Diary;
import org.ideoholic.curium.model.studentdiary.dto.StudentDiary;
import org.ideoholic.curium.model.studentdiary.dto.StudentDiaryProjection;
import org.ideoholic.curium.util.HibernateUtil;
import org.ideoholic.curium.util.Session;
import org.ideoholic.curium.util.Session.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class StudentDiaryDAO {
	
	
	@Autowired
	private StudentDiaryRepository studentDiaryRepo;

	@Transactional
	public StudentDiary create(StudentDiary diary) {
		try {
			studentDiaryRepo.save(diary);
            
        }catch (Exception hibernateException) { 
        	log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            throw hibernateException;
        } 
		 return diary;
	}
	@SuppressWarnings({ "finally", "unchecked" })
	public  List<Object[]>  readListOfObjects(int offset, int noOfRecords, int branchId) {
		Session session = null;
	    Transaction transaction = null;
		List<Object[]> results = new ArrayList<Object[]>();
        try {
            
            transaction = session.beginTransaction();
            Query query = session.createQuery("select d.id,d.sid,s.name,d.classsec,d.academicyear,d.branchid,d.subject,d.message,d.createddate,d.userid from StudentDiary d JOIN Student s ON d.sid=s.sid where  d.branchid="+branchId+" order by d.createddate DESC");
            query.setFirstResult(offset);
			query.setMaxResults(noOfRecords);
			results = query.list();
            transaction.commit();
        }catch (Exception hibernateException) { 
        	log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            throw hibernateException;
        } finally {
    			HibernateUtil.closeSession();
            return results;
        }
	}
 
	@Transactional
	public  List<StudentDiaryProjection>  readListOfParentObjects(int offset, int noOfRecords, int branchId, int sid) {
	    List<StudentDiaryProjection> results = new ArrayList<>();
        try {
        	
        	Pageable pageable = PageRequest.of(offset, noOfRecords);
			results = studentDiaryRepo.findByBranchIdAndSid(branchId, sid, pageable).toList();
        } catch (Exception hibernateException) { 
        	log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            throw hibernateException;       
        }
        return results;
	}
	
	@Transactional
	public int getNoOfRecords(int branchId, int sid) {
		List<StudentDiary> results = new ArrayList<StudentDiary>();
		int noOfRecords = 0;
		try {
			noOfRecords = studentDiaryRepo.countByBranchidAndSid(branchId, sid);

		} catch (Exception hibernateException) { 
        	log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            throw hibernateException;   

		} 
		return noOfRecords;
	}
	
	@SuppressWarnings({ "finally", "unchecked" })
	public int getNoOfRecords(int branchId) {
		Session session = null;
	    Transaction transaction = null;
		List<StudentDiary> results = new ArrayList<StudentDiary>();
		int noOfRecords = 0;
		try {
			transaction = session.beginTransaction();

						Query query = session.createQuery("select count(*) from StudentDiary where branchid="+branchId+" ");
			noOfRecords = Integer.parseInt(query.uniqueResult().toString()); 
			
			transaction.commit();

		}catch (Exception hibernateException) { 
        	log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            throw hibernateException;

		} finally {
				HibernateUtil.closeSession();
			return noOfRecords;
		}
	}

	 @Transactional
	public void deleteRecord(List<Integer> ids) {
		try {
			studentDiaryRepo.deleteAllById(ids);
			
		}catch (Exception hibernateException) { 
        	log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            throw hibernateException;
		}
	}

	 @Transactional
	public StudentDiary getMessage(long id) {
		StudentDiary diary = new StudentDiary();
		Long did = new Long(id);
		try {
			Optional<StudentDiary> diarymsg = studentDiaryRepo.findById(did.intValue());
			diary = diarymsg.orElse(null);
		}  catch (Exception hibernateException) { 
        	log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            throw hibernateException;
		}
		return diary;
	}

}

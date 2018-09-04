/**
 * 
 */
package com.model.subjectdetails.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.model.examlevels.dto.Subexamlevel;
import com.model.subjectdetails.dto.Subject;
import com.util.HibernateUtil;

/**
 * @author Musaib_2
 *
 */
public class SubjectDetailsDAO {

	Session session;
	/**
	 * * Hibernate Session Variable
	 */
	Transaction transaction;
	
	
	public SubjectDetailsDAO() {
		session = HibernateUtil.openCurrentSession();
	}
	
	@SuppressWarnings({ "unchecked", "finally" })
	public List<Subject> readListOfSubjects(int branchId) {
		
		List<Subject> results = new ArrayList<Subject>();
		try {

			transaction = session.beginTransaction();
			results = (List<Subject>) session.createQuery("From Subject where branchid="+branchId)
					.list();
			transaction.commit();
		} catch (HibernateException hibernateException) {
			transaction.rollback();
			hibernateException.printStackTrace();
		} finally {
			 //session.close();
			return results;
		}
	}

	public Subject addSubject(Subject subject, String[] examlevelids) {
		try {
			transaction = session.beginTransaction();
			session.save(subject);
			
			for (String examLevelCode : examlevelids) {
			    Subexamlevel subExamLevel = new Subexamlevel();
			    subExamLevel.setSubjectname(subject.getSubjectname());
			    subExamLevel.setExamlevel(examLevelCode);
			    session.save(subExamLevel);
                        }
			transaction.commit();
		} catch (HibernateException hibernateException) {
			transaction.rollback();
			hibernateException.printStackTrace();
		} finally {
			//session.close();
			return subject;
		}
		
	}

	public void deleteMultiple(List ids) {
		try {
			transaction = session.beginTransaction();
			Query query = session
					.createQuery("delete from Subject where subid IN (:ids)");
			query.setParameterList("ids", ids);
			query.executeUpdate();
			transaction.commit();
		} catch (HibernateException hibernateException) {
			hibernateException.printStackTrace();
		}
	}

    public Subject getSubjectDetails(String subject) {
        Subject subjectDet = new Subject();
        try {
            transaction = session.beginTransaction();
            Query query = session
                            .createQuery("from Subject where subjectname = '"+subject+"'");
            subjectDet = (Subject) query.uniqueResult();
            transaction.commit();
    } catch (HibernateException hibernateException) {
            hibernateException.printStackTrace();
    }
        return subjectDet;
    }

}

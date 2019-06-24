package com.model.std.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.util.Session;
import org.hibernate.SessionFactory;
import com.util.Session.Transaction;
import org.hibernate.query.Query;

import com.model.std.dto.Classhierarchy;
import com.model.std.dto.Classsec;
import com.model.student.dto.Student;
import com.util.HibernateUtil;

public class StandardDetailsDAO {

	Session session = null;
    /**
     * * Hibernate Session Variable
     */
    Transaction transaction = null;
    /**
     * * Hibernate Transaction Variable
     */
    Transaction transaction1;
    SessionFactory sessionFactory;

    private static final Logger logger = LogManager.getLogger(StandardDetailsDAO.class);
    
	public StandardDetailsDAO() {
		session = HibernateUtil.openCurrentSession();
	}

	@SuppressWarnings("finally")
	public Classsec create(Classsec classsec) {
		 try {
	            transaction = session.beginTransaction();
	            session.save(classsec);
	            transaction.commit();
	        } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
	            
	            hibernateException.printStackTrace();
	        } finally {
	    			HibernateUtil.closeSession();
	            return classsec;
	        }
	}

    public List<Classsec> viewClasses(int branchId) {
        
        List<Classsec> classsecList = new ArrayList<Classsec>();
        try {
            transaction = session.beginTransaction();
            classsecList = session.createQuery("From Classsec where branchid="+branchId).setCacheable(true).setCacheRegion("commonregion").list();
            transaction.commit();
        } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
            hibernateException.printStackTrace();
        } finally {
    			HibernateUtil.closeSession();
            return classsecList;
        }
    }

    public void deleteMultiple(List ids) {

        try {
                transaction = session.beginTransaction();
                Query query = session.createQuery("delete from Classsec where stdrdid IN (:ids)");
                query.setParameterList("ids", ids);
                query.executeUpdate();
                transaction.commit();
        } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
                hibernateException.printStackTrace();
        }finally {
			HibernateUtil.closeSession();
		}
    }

	public void createClassHierarchy(Classhierarchy classHierarchy) {
		 try {
	            transaction = session.beginTransaction();
	            session.save(classHierarchy);
	            transaction.commit();
	        } catch (Exception hibernateException) { 
	        	transaction.rollback(); 
	        	logger.error(hibernateException);
	            hibernateException.printStackTrace();
	        } finally {
				HibernateUtil.closeSession();
			}
	}

	public void deleteClassHierarchy(List ids) {

        try {
                transaction = session.beginTransaction();
                Query query = session.createQuery("delete from Classhierarchy where idclasshierarchy IN (:ids)");
                query.setParameterList("ids", ids);
                query.executeUpdate();
                transaction.commit();
        } catch (Exception hibernateException) { 
        		transaction.rollback(); 
        		logger.error(hibernateException);
                hibernateException.printStackTrace();
        }finally {
			HibernateUtil.closeSession();
		}
    }

	public List<Classhierarchy> viewClassHierarchy(int branchid) {
        
        List<Classhierarchy> classHierarchyList = new ArrayList<Classhierarchy>();
        try {
            transaction = session.beginTransaction();
            classHierarchyList = session.createQuery("From Classhierarchy where branchid="+branchid).list();
            transaction.commit();
        } catch (Exception hibernateException) { 
        	transaction.rollback(); 
        	logger.error(hibernateException);
            hibernateException.printStackTrace();
        }finally {
			HibernateUtil.closeSession();
		}
        return classHierarchyList;
    }

	@SuppressWarnings("finally")
	public boolean graduateMultiple(List ids) {
		boolean result = false;
		try {
			transaction = session.beginTransaction();
			Query query = session
					.createQuery("update Student set passedout = 1  where id IN (:ids)");
			query.setParameterList("ids", ids);
			query.executeUpdate();
			transaction.commit();
			result = true;
		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			hibernateException.printStackTrace();
			result = false;
		} finally {
				HibernateUtil.closeSession();
			return result;
		}
		
			}

	@SuppressWarnings("finally")
	public boolean droppedoutMultiple(List ids) {
		boolean result = false;
		try {
			transaction = session.beginTransaction();
			Query query = session
					.createQuery("update Student set droppedout = 1  where id IN (:ids)");
			query.setParameterList("ids", ids);
			query.executeUpdate();
			transaction.commit();
			result = true;
		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
		hibernateException.printStackTrace();
		result = false;
	} finally {
			HibernateUtil.closeSession();
		return result;
	}
	
		}

	@SuppressWarnings("finally")
	public boolean leftoutMultiple(List ids) {
		boolean result = false;
		try {
			transaction = session.beginTransaction();
			Query query = session
					.createQuery("update Student set leftout = 1  where id IN (:ids)");
			query.setParameterList("ids", ids);
			query.executeUpdate();
			transaction.commit();
			result = true;
		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
		hibernateException.printStackTrace();
		result = false;
	} finally {
			HibernateUtil.closeSession();
		return result;
	}
	
		}
	
	public List<Student> readListOfStudentsGraduated() {
        List<Student> results = new ArrayList<Student>();

        try {
                // this.session =
                // HibernateUtil.getSessionFactory().openCurrentSession();
                transaction = session.beginTransaction();

                results = (List<Student>) session.createQuery(
                                "FROM Student s where s.passedout = 1 order by s.admissionnumber DESC")
                                .list();
                transaction.commit();

        } catch (Exception hibernateException) {transaction.rollback();
                hibernateException.printStackTrace();

        } finally {
                HibernateUtil.closeSession();
                return results;
        }
	}

	public List<Student> readListOfStudentsDropped() {
        List<Student> results = new ArrayList<Student>();

        try {
                // this.session =
                // HibernateUtil.getSessionFactory().openCurrentSession();
                transaction = session.beginTransaction();

                results = (List<Student>) session.createQuery(
                                "FROM Student s where s.droppedout = 1 order by s.admissionnumber DESC")
                                .list();
                transaction.commit();

        } catch (Exception hibernateException) {transaction.rollback();
                hibernateException.printStackTrace();

        } finally {
                HibernateUtil.closeSession();
                return results;
        }
	}
	
	public void restoreMultipleGraduate(List ids) {
        try {
            transaction = session.beginTransaction();
            Query query = session
                            .createQuery("update Student set passedout = 0  where id IN (:ids)");
            query.setParameterList("ids", ids);
            query.executeUpdate();
            transaction.commit();
    } catch (Exception hibernateException) {transaction.rollback();
            hibernateException.printStackTrace();
    }finally {
		HibernateUtil.closeSession();
	}

	}

    public void restoreMultipleDroppedout(List ids) {
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("update Student set droppedout = 0  where id IN (:ids)");
            query.setParameterList("ids", ids);
            query.executeUpdate();
            transaction.commit();
    } catch (Exception hibernateException) {transaction.rollback();
            hibernateException.printStackTrace();
    }finally {
		HibernateUtil.closeSession();
	}

    }
    
	@SuppressWarnings({ "unchecked", "finally" })
	public List<Student> getStudentsByClass(String classofStd, int branchId) {
		java.util.List<Student> results = new ArrayList<Student>();
		try {
			// this.session =
			// HibernateUtil.getSessionFactory().openCurrentSession();
			transaction = session.beginTransaction();
			results = (java.util.List<Student>) session.createQuery("From Student s where s.branchid="+branchId+" AND s.classstudying LIKE '"+classofStd+"%' AND s.archive=0 AND s.passedout=0 AND s.droppedout=0 and s.leftout=0").list();
			transaction.commit();

		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			
			hibernateException.printStackTrace();

		} finally {
				HibernateUtil.closeSession();
			return results;
		}
	}

	public List<Student> readListOfStudentsLeft() {
        List<Student> results = new ArrayList<Student>();

        try {
                // this.session =
                // HibernateUtil.getSessionFactory().openCurrentSession();
                transaction = session.beginTransaction();

                results = (List<Student>) session.createQuery(
                                "FROM Student s where s.leftout = 1 order by s.admissionnumber DESC")
                                .list();
                transaction.commit();

        } catch (Exception hibernateException) {transaction.rollback();
                hibernateException.printStackTrace();

        } finally {
                HibernateUtil.closeSession();
                return results;
        }
	}

	public void restoreMultipleLeftout(List ids) {
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("update Student set leftout = 0  where id IN (:ids)");
            query.setParameterList("ids", ids);
            query.executeUpdate();
            transaction.commit();
    } catch (Exception hibernateException) {transaction.rollback();
            hibernateException.printStackTrace();
    }finally {
		HibernateUtil.closeSession();
	}

    }
	
}

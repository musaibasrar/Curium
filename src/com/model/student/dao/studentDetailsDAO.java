package com.model.student.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.util.Session;
import com.util.Session.Transaction;
import org.hibernate.query.Query;

import com.model.degreedetails.dto.Degreedetails;
import com.model.mess.card.dto.Card;
import com.model.parents.dto.Parents;
import com.model.pudetails.dto.Pudetails;
import com.model.std.dto.Classhierarchy;
import com.model.student.dto.Student;
import com.model.student.dto.Studentfeesstructure;
import com.util.HibernateUtil;

public class studentDetailsDAO {
	Session session = null;
	/**
	 * * Hibernate Session Variable
	 */
	Transaction transaction = null;
	/**
	 * * Hibernate Transaction Variable
	 */
	Transaction transaction1;
	//SessionFactory sessionFactory;
	
	private static final Logger logger = LogManager.getLogger(studentDetailsDAO.class);
	
	public studentDetailsDAO() {
		session = HibernateUtil.openCurrentSession();
	}

	@SuppressWarnings("finally")
	public Student create(Student student) {
		try {
			// this.session = sessionFactory.openCurrentSession();
			transaction = session.beginTransaction();
			session.save(student);

			transaction.commit();
			System.out.println("in add3");
		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			
			hibernateException.printStackTrace();
		} finally {
				HibernateUtil.closeSession();
			return student;
		}
	}

	@SuppressWarnings("finally")
	public List<Student> readListOfObjectsPagination(int offset, int noOfRecords, int branchId) {
		List<Student> results = new ArrayList<Student>();

		try {
			// this.session =
			// HibernateUtil.getSessionFactory().openCurrentSession();
			transaction = session.beginTransaction();

			// results = (List<PersonalDetails>)
			// session.createQuery("From PersonalDetails p where p.subscriber=1 and  p.archive = 0 order by name desc LIMIT 5 ").list();
			Query query = session
					.createQuery("FROM Student s where s.archive = 0 AND branchid="+branchId+" order by name ASC").setCacheable(true).setCacheRegion("commonregion");
			query.setFirstResult(offset);
			query.setMaxResults(noOfRecords);
			results = query.list();
			transaction.commit();

		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			
			hibernateException.printStackTrace();

		} finally {
				HibernateUtil.closeSession();
			return results;
		}
	}

	@SuppressWarnings({ "unchecked", "finally" })
	public int getNoOfRecords(int branchId) {
		List<Student> results = new ArrayList<Student>();
		int noOfRecords = 0;
		try {
			// this.session =
			// HibernateUtil.getSessionFactory().openCurrentSession();
			transaction = session.beginTransaction();

			results = (List<Student>) session.createQuery("From Student where archive=0 AND passedout=0 AND droppedout=0 and leftout=0 AND branchid="+branchId).setCacheable(true).setCacheRegion("commonregion")
					.list();
			noOfRecords = results.size();
			logger.info("The size of list is:::::::::::::::::::::::::::::::::::::::::: "
							+ noOfRecords);
			transaction.commit();

		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			
			hibernateException.printStackTrace();

		} finally {
				HibernateUtil.closeSession();
			return noOfRecords;
		}
	}

	public Student readUniqueObject(long id) {
		Student student = new Student();
		try {
			// this.session =
			// HibernateUtil.getSessionFactory().openCurrentSession();

			transaction = session.beginTransaction();
			Query query = session
					.createQuery("from Student as student where student.sid="
							+ id);
			student = (Student) query.uniqueResult();
			transaction.commit();
		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			
			hibernateException.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		 }
		return student;
	}

	@SuppressWarnings("finally")
	public Student update(Student student) {
		try {
			// this.session = sessionFactory.openCurrentSession();
			transaction = session.beginTransaction();
			session.update(student);
			transaction.commit();
			System.out.println("in add2");
		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			
			hibernateException.printStackTrace();
		} finally {
				HibernateUtil.closeSession();
			return student;
		}
	}

	@SuppressWarnings({ "unchecked", "finally" })
	public List<Student> readListOfStudents(int branchId) {
		List<Student> results = new ArrayList<Student>();

		try {
			// this.session =
			// HibernateUtil.getSessionFactory().openCurrentSession();
			transaction = session.beginTransaction();

			results = (List<Student>) session.createQuery("From Student where archive=0 AND passedout=0 AND droppedout=0 and leftout=0 AND branchid="+branchId).setCacheable(true).setCacheRegion("commonregion")
					.list();
			transaction.commit();

		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			
			hibernateException.printStackTrace();

		} finally {
				HibernateUtil.closeSession();
			return results;
		}
	}

	public void archiveMultiple(List ids) {
		try {
			transaction = session.beginTransaction();
			Query query = session
					.createQuery("update Student set archive = 1  where id IN (:ids)");
			query.setParameterList("ids", ids);
			query.executeUpdate();
			transaction.commit();
		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			hibernateException.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		 }

	}

	@SuppressWarnings("unchecked")
	public List<Student> readListOfStudentsArchive() {
		List<Student> results = new ArrayList<Student>();

		try {
			// this.session =
			// HibernateUtil.getSessionFactory().openCurrentSession();
			transaction = session.beginTransaction();

			results = (List<Student>) session.createQuery(
					"FROM Student s where s.archive = 1").setCacheable(true).setCacheRegion("commonregion")
					.list();
			transaction.commit();

		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			
			hibernateException.printStackTrace();

		} finally {
				HibernateUtil.closeSession();
			return results;
		}
	}

	public void deleteMultiple(List ids, List iddetails) {
		try {
			transaction = session.beginTransaction();
			
			
			Query query = session
					.createQuery("delete from Parents as parents where parents.Student.sid IN (:ids)");
			query.setParameterList("ids", ids);
			Query query2 = session
					.createQuery("delete from Student where sid IN (:ids)");
			query2.setParameterList("ids", ids);
			
			if(iddetails.size()>0) {
				Query query3 = session
                        .createQuery("delete from Pudetails where idpudetails IN (:iddetails)");
                query3.setParameterList("iddetails", iddetails);
                query3.executeUpdate();
			}
			
			query.executeUpdate();
			query2.executeUpdate();
			
			transaction.commit();
		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			hibernateException.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		 }

	}

	public void restoreMultiple(List ids) {
		try {
			transaction = session.beginTransaction();
			Query query = session
					.createQuery("update Student set archive = 0  where id IN (:ids)");
			query.setParameterList("ids", ids);
			query.executeUpdate();
			transaction.commit();
		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			hibernateException.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		 }

	}

	@SuppressWarnings("finally")
	public boolean promoteMultiple(List ids, String classStudying) {
		boolean result = false;
		
		  String stringclassStudying = classStudying;
		  String[] temp;
		 
		  /* delimiter */
		  String delimiter = "--";
		  /* given string will be split by the argument delimiter provided. */
		  temp = stringclassStudying.split(delimiter);
		  /* print substrings */
		 classStudying = temp[0];
		 String sec = "";
		 if(temp.length>=2){
			  sec = temp[1];
		 }
		 

		try {
			transaction = session.beginTransaction();
			Query query1 = session.createQuery("From Classhierarchy where lowerclass = '"+classStudying+"'");
			Classhierarchy ch = (Classhierarchy) query1.uniqueResult();
			
			if(ch!=null) {
				String hql = "UPDATE Student set classstudying = :promotedclass WHERE sid IN (:ids)";
				Query query = session.createQuery(hql);
				query.setParameter("promotedclass", ch.getUpperclass()+sec);
				query.setParameterList("ids", ids);
				query.executeUpdate();
			}
	
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

	public List<Parents> readListOfObjectsPaginationALL(int offset,
			int noOfRecords, int branchId) {
		List<Parents> results = new ArrayList<Parents>();

		try {
			
			transaction = session.beginTransaction();
			Query query = session
					.createQuery("From Parents as parents where parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0 AND parents.branchid = "+branchId+" order by parents.Student.sid DESC").setCacheable(true).setCacheRegion("commonregion");
			query.setFirstResult(offset);   
			query.setMaxResults(noOfRecords);
			results = query.getResultList();
			
			transaction.commit();
			

		} catch (Exception hibernateException) {  transaction.rollback(); logger.error(hibernateException);
			
			System.out.println("Exception is "+hibernateException);
			hibernateException.printStackTrace();

		} finally {
				HibernateUtil.closeSession();
			return results;
		}
	}

	public List<Student> readListOfObjectsForIcon(int branchId) {
		List<Student> results = new ArrayList<Student>();

		try {
			// this.session =
			// HibernateUtil.getSessionFactory().openCurrentSession();
			transaction = session.beginTransaction();

			// results = (List<PersonalDetails>)
			// session.createQuery("From PersonalDetails p where p.subscriber=1 and  p.archive = 0 order by name desc LIMIT 5 ").list();
			Query query = session
					.createQuery("FROM Student s where s.archive = 0 and s.passedout=0 AND s.droppedout=0 and s.leftout=0 and s.branchid= "+branchId+" order by name ASC");
			
			results = query.setCacheable(true).setCacheRegion("commonregion").list();
			transaction.commit();

		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			
			hibernateException.printStackTrace();

		} finally {
				HibernateUtil.closeSession();
			return results;
		}
	}

	public void addStudentfeesstructure(List<Studentfeesstructure> listOfstudentfeesstructure, String currentYear) {
		try {
			// this.session = sessionFactory.openCurrentSession();
			
			transaction = session.beginTransaction();
			
			for (Studentfeesstructure studentfeesstructure : listOfstudentfeesstructure) {
					
					Query query = session.createQuery("from Studentfeesstructure as sfs where sfs.sid = '"+studentfeesstructure.getSid()+"' and sfs.Feescategory.idfeescategory = '"+studentfeesstructure.getIdfeescategory()+"' and sfs.academicyear = '"+currentYear+"'");
					Studentfeesstructure feesStructure = (Studentfeesstructure) query.uniqueResult();
					if(feesStructure != null){
						
						Query queryUpdate = session
								.createQuery("update Studentfeesstructure set idfeescategory = '"+studentfeesstructure.getIdfeescategory()+"',feesamount = '"+studentfeesstructure.getFeesamount()+"'  where sid = '"+studentfeesstructure.getSid()+"' and academicyear = '"+currentYear+"'");
						
						
						queryUpdate.executeUpdate();
					}else if(feesStructure == null){
						session.save(studentfeesstructure);
					}
			}
			
			transaction.commit();
		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			
			hibernateException.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		 }
	}

	public List<Studentfeesstructure> getStudentFeesStructure(long id,
			String currentYear) {
		List<Studentfeesstructure> results = new ArrayList<Studentfeesstructure>();

		try {
			// this.session =
			// HibernateUtil.getSessionFactory().openCurrentSession();
			transaction = session.beginTransaction();

			// results = (List<PersonalDetails>)
			// session.createQuery("From PersonalDetails p where p.subscriber=1 and  p.archive = 0 order by name desc LIMIT 5 ").list();
			Query query = session
					.createQuery("from Studentfeesstructure sfs where sfs.sid = '"+id+"' and sfs.academicyear = '"+currentYear+"'");
			
			results = query.list();
			transaction.commit();

		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			
			hibernateException.printStackTrace();

		} finally {
				HibernateUtil.closeSession();
			return results;
		}
	}

	public Parents getStudentRecords(String queryMain) {
		Parents parents = new Parents();
        try {
            transaction = session.beginTransaction();
            Query HQLquery = session.createQuery(queryMain);
            parents = (Parents) HQLquery.setCacheable(true).setCacheRegion("commonregion").uniqueResult();
            transaction.commit();
        } catch (Exception hibernateException) {
        	transaction.rollback(); 
        	logger.error(hibernateException);
            hibernateException.printStackTrace();
        }
        finally {
			HibernateUtil.closeSession();
		 }
        return parents;
	}

	public java.util.List<Parents> getStudentsList(String query) {
		java.util.List<Parents> parents = new ArrayList<Parents>();
        try {
            //this.session = HibernateUtil.getSessionFactory().openCurrentSession();

            transaction = session.beginTransaction();
            Query HQLquery = session.createQuery(query);
            parents = (java.util.List<Parents>) HQLquery.setCacheable(true).setCacheRegion("commonregion").list();
            transaction.commit();
        } catch (Exception hibernateException) {
        	transaction.rollback(); 
        	logger.error(hibernateException);
            hibernateException.printStackTrace();
        }finally {
			HibernateUtil.closeSession();
		 }
        return parents;
	}
	
	public List<Student> getListStudents(String query) {
		java.util.List<Student> student = new ArrayList<Student>();
        try {
            transaction = session.beginTransaction();
            Query HQLquery = session.createQuery(query);
            student = HQLquery.setCacheable(true).setCacheRegion("commonregion").list();
            transaction.commit();
        } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
            
            hibernateException.printStackTrace();
        }finally {
			HibernateUtil.closeSession();
		 }
        return student;
	}

	public List<Studentfeesstructure> getStudentFeesStructureDetails(int sfsid) {
		List<Studentfeesstructure> studentFeesStructure = new ArrayList<Studentfeesstructure>();

		try {
			transaction = session.beginTransaction();
			studentFeesStructure = session.createQuery("from Studentfeesstructure sfs where sfs.sfsid = '"+sfsid+"'").list();
			transaction.commit();

		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			
			hibernateException.printStackTrace();

		} finally {
				HibernateUtil.closeSession();
			return studentFeesStructure;
		}
	}

	public boolean updateStudent(Student student) {
		
		try {
			transaction = session.beginTransaction();
			Query queryUpdate = session
					.createQuery("update Student set reasonleaving = '"+student.getReasonleaving()+"'  where sid = '"+student.getSid()+"'");
			queryUpdate.executeUpdate();
			transaction.commit();
			return true;
		} catch (Exception e) { transaction.rollback(); logger.error(e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		 }
		return false;
	}

    public void updatePuDetails(Pudetails puDetails) {
        try {
            // this.session = sessionFactory.openCurrentSession();
            transaction = session.beginTransaction();
            session.update(puDetails);
            transaction.commit();
    } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
            
            hibernateException.printStackTrace();
    } finally {
		HibernateUtil.closeSession();
	 }
    }
    
    public void updateDegreeDetails(Degreedetails degreeDetails) {
        try {
            // this.session = sessionFactory.openCurrentSession();
            transaction = session.beginTransaction();
            session.update(degreeDetails);
            transaction.commit();
    } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
            
            hibernateException.printStackTrace();
    } finally {
		HibernateUtil.closeSession();
	 }
    }

    public List<Parents> readListStudentsSuperAdmin(int offset, int noOfRecords) {
        List<Parents> results = new ArrayList<Parents>();

        try {
                
                transaction = session.beginTransaction();
                Query query = session
                                .createQuery("From Parents as parents where parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0 order by name ASC");
                query.setFirstResult(offset);   
                query.setMaxResults(noOfRecords);
                results = query.getResultList();
                
                transaction.commit();
                

        } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
                
                System.out.println("Exception is "+hibernateException);
                hibernateException.printStackTrace();

        } finally {
    			HibernateUtil.closeSession();
                return results;
        }
}

    public int getNoOfRecords() {
        List<Student> results = new ArrayList<Student>();
        int noOfRecords = 0;
        try {
                transaction = session.beginTransaction();
                results = (List<Student>) session.createQuery("From Student where archive=0 and passedout=0 AND droppedout=0 and leftout=0").setCacheable(true).setCacheRegion("commonregion").list();
                noOfRecords = results.size();
                transaction.commit();
        } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
                
                hibernateException.printStackTrace();

        } finally {
    			HibernateUtil.closeSession();
                return noOfRecords;
        }
}

	public String getPromotionClass(String classStudying) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Card> getCardDetails(List<Integer> ids){
		
		List<Card> cardDetailsList = new ArrayList<Card>();
		try {
			transaction = session.beginTransaction();
			Query query = session
					.createQuery("From Card as card where card.sid IN (:ids)");
			query.setParameterList("ids", ids);
			cardDetailsList = query.list();
			transaction.commit();
		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			hibernateException.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		 }
		return cardDetailsList;
	}
	
	
	public java.util.List<Parents> getParentsStudents(String query, List<Integer> studentids) {
		java.util.List<Parents> parents = new ArrayList<Parents>();
        try {
            //this.session = HibernateUtil.getSessionFactory().openCurrentSession();

            transaction = session.beginTransaction();
            Query HQLquery = session.createQuery(query);
            HQLquery.setParameterList("ids", studentids);
            parents = (java.util.List<Parents>) HQLquery.setCacheable(true).setCacheRegion("commonregion").list();
            transaction.commit();
			
        } catch (Exception hibernateException) {
        	transaction.rollback(); 
        	logger.error(hibernateException);
            hibernateException.printStackTrace();
        }finally {
			HibernateUtil.closeSession();
		 }
        return parents;
	}
	
}

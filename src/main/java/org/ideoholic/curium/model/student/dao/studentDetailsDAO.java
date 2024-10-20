package org.ideoholic.curium.model.student.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.query.Query;

import org.ideoholic.curium.model.degreedetails.dto.Degreedetails;
import org.ideoholic.curium.model.mess.card.dto.Card;
import org.ideoholic.curium.model.parents.dto.Parents;
import org.ideoholic.curium.model.pudetails.dto.Pudetails;
import org.ideoholic.curium.model.std.dto.Classhierarchy;
import org.ideoholic.curium.model.student.dto.Student;
import org.ideoholic.curium.model.student.dto.Studentfeesstructure;
import org.ideoholic.curium.model.student.dto.Studentotherfeesstructure;
import org.ideoholic.curium.util.HibernateUtil;
import org.ideoholic.curium.util.Session;
import org.ideoholic.curium.util.Session.Transaction;

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
	public List<Object[]> readListOfObjectsPagination(int offset, int noOfRecords, int branchId) {
		List<Object[]> results = new ArrayList<Object[]>();

		try {
			// this.session =
			// HibernateUtil.getSessionFactory().openCurrentSession();
			transaction = session.beginTransaction();

			// results = (List<PersonalDetails>)
			// session.createQuery("From PersonalDetails p where p.subscriber=1 and  p.archive = 0 order by name desc LIMIT 5 ").list();
			/*
			 * Query query = session
			 * .createQuery("FROM Student s where s.archive = 0 AND branchid="
			 * +branchId+" order by name ASC").setCacheable(true).setCacheRegion(
			 * "commonregion");
			 */
			Query query = session
					.createQuery("select s.sid, s.studentexternalid, s.admissionnumber, s.name, s.classstudying, f.fathersname, f.mothersname from Student s JOIN Parents f ON s.sid=f.Student.sid where s.archive = 0 AND s.branchid="+branchId+" order by s.sid DESC").setCacheable(true).setCacheRegion("commonregion");
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

			/*
			 * results = (List<Student>) session.
			 * createQuery("From Student where archive=0 AND passedout=0 AND droppedout=0 and leftout=0 AND branchid="
			 * +branchId).setCacheable(true).setCacheRegion("commonregion") .list();
			 * noOfRecords = results.size();
			 */
			Query query = session.createQuery("select count(*) from Student where  archive=0 AND passedout=0 AND droppedout=0 and leftout=0 AND branchid="+branchId).setCacheable(true).setCacheRegion("commonregion");
			noOfRecords = Integer.parseInt(query.uniqueResult().toString()); 
			
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
	//code for readploginUniqueObject
	public Student readploginUniqueObject(String id) {
		Student student = new Student();
		try {
			// this.session =
			// HibernateUtil.getSessionFactory().openCurrentSession();

			transaction = session.beginTransaction();
			Query query = session
					.createQuery("from Student as student where student.studentexternalid='"+id+"'");
			student = (Student) query.uniqueResult();
			transaction.commit();
		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			
			hibernateException.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		 }
		return student;
	}
	//end readploginUniqueObject
	public Parents readUniqueObjectParents(long id) {
		Parents parents = new Parents();
		try {
			// this.session =
			// HibernateUtil.getSessionFactory().openCurrentSession();

			transaction = session.beginTransaction();
			Query query = session
					.createQuery("from Parents as parent where parent.Student.sid="
							+ id);
			parents = (Parents) query.uniqueResult();
			transaction.commit();
		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			
			hibernateException.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		 }
		return parents;
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
	public boolean promoteMultiple(List<Student> students, String classStudying, String promotedYear, int branchid) {
		boolean result = false;
		
		  String stringclassStudying = classStudying;
		  String[] temp;
		 
		  String delimiter = "--";
		  temp = stringclassStudying.split(delimiter);
		 classStudying = temp[0];
		 String sec = "";
		 if(temp.length>=2){
			  sec = temp[1];
		 }
		 

		try {
			transaction = session.beginTransaction();
			Query query1 = session.createQuery("From Classhierarchy where lowerclass = '"+classStudying+"'  and branchid="+branchid+"");
			Classhierarchy ch = (Classhierarchy) query1.uniqueResult();
			
			if(ch!=null) {
				
				for (Student student : students) {
					
					 String[] tempstudent = student.getClassstudying().split(delimiter);
					 String sect = "";
					 
					 if(tempstudent.length>=2){
						  sect = tempstudent[1];
					 }
					 
					String hql = "UPDATE Student set classstudying = '"+ch.getUpperclass()+"--"+sect+"', promotedyear='"+promotedYear+"', branchid='"+ch.getBranchid()+"' WHERE sid = "+student.getSid()+"";
					Query query = session.createQuery(hql);
					query.executeUpdate();
				}
				
			}else if(ch==null) {
				
				Query queryUpdateBranchClassHierarchy = session.createQuery("From Classhierarchy where lowerclass = '"+classStudying+"'");
				Classhierarchy chUpdateBranch = (Classhierarchy) queryUpdateBranchClassHierarchy.uniqueResult();
				if(chUpdateBranch!=null) {
				for (Student student : students) {
					
					 String[] tempstudent = student.getClassstudying().split(delimiter);
					 String sect = "";
					 
					 if(tempstudent.length>=2){
						  sect = tempstudent[1];
					 }
					 
					String hqlUpdateBranch = "UPDATE Student set classstudying = '"+chUpdateBranch.getUpperclass()+"--"+sect+"', promotedyear='"+promotedYear+"', branchid='"+chUpdateBranch.getBranchid()+"' WHERE sid = "+student.getSid()+"";
					Query queryUpdateBranch = session.createQuery(hqlUpdateBranch);
					queryUpdateBranch.executeUpdate();
				}
				
			
			}
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
					.createQuery("From Parents as parents where parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0 AND parents.Student.branchid = "+branchId+" order by parents.Student.sid desc").setCacheable(true).setCacheRegion("commonregion");
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

	public List<Studentfeesstructure> getStudentFeesStructurebyFeesCategory(long id, List<Integer> feesCat) {
		List<Studentfeesstructure> results = new ArrayList<Studentfeesstructure>();

		try {
			// this.session =
			// HibernateUtil.getSessionFactory().openCurrentSession();
			transaction = session.beginTransaction();

			// results = (List<PersonalDetails>)
			// session.createQuery("From PersonalDetails p where p.subscriber=1 and  p.archive = 0 order by name desc LIMIT 5 ").list();
			Query query = session
					.createQuery("from Studentfeesstructure sfs where sfs.sid = '"+id+"' and sfs.Feescategory.idfeescategory IN (:feescat)");
			query.setParameterList("feescat", feesCat);
			results = query.list();
			transaction.commit();

		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			
			hibernateException.printStackTrace();

		} finally {
				HibernateUtil.closeSession();
			return results;
		}
	}
	
	public List<Studentotherfeesstructure> getStudentOtherFeesStructure(long id,
			String currentYear) {
		List<Studentotherfeesstructure> results = new ArrayList<Studentotherfeesstructure>();

		try {
			// this.session =
			// HibernateUtil.getSessionFactory().openCurrentSession();
			transaction = session.beginTransaction();

			// results = (List<PersonalDetails>)
			// session.createQuery("From PersonalDetails p where p.subscriber=1 and  p.archive = 0 order by name desc LIMIT 5 ").list();
			Query query = session
					.createQuery("from Studentotherfeesstructure sfs where sfs.sid = '"+id+"' and sfs.academicyear = '"+currentYear+"'");

			results = query.list();
			transaction.commit();

		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);

			hibernateException.printStackTrace();

		} finally {
				HibernateUtil.closeSession();
			return results;
		}
	}
	
	public List<Studentotherfeesstructure> getotherStudentFeesStructureDetails(int sfsid) {
		List<Studentotherfeesstructure> studentFeesStructure = new ArrayList<Studentotherfeesstructure>();

		try {
			transaction = session.beginTransaction();
			studentFeesStructure = session.createQuery("from Studentotherfeesstructure sfs where sfs.sfsid = '"+sfsid+"'").list();
			transaction.commit();

		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);

			hibernateException.printStackTrace();

		} finally {
				HibernateUtil.closeSession();
			return studentFeesStructure;
		}
	}
	
	public List<Studentotherfeesstructure> getStudentotherFeesStructurebyFeesCategory(long id, List<Integer> feesCat) {
		List<Studentotherfeesstructure> results = new ArrayList<Studentotherfeesstructure>();

		try {
			// this.session =
			// HibernateUtil.getSessionFactory().openCurrentSession();
			transaction = session.beginTransaction();

			// results = (List<PersonalDetails>)
			// session.createQuery("From PersonalDetails p where p.subscriber=1 and  p.archive = 0 order by name desc LIMIT 5 ").list();
			Query query = session
					.createQuery("from Studentotherfeesstructure sfs where sfs.sid = '"+id+"' and sfs.otherfeescategory.idfeescategory IN (:feescat)");
			query.setParameterList("feescat", feesCat);
			results = query.list();
			transaction.commit();

		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);

			hibernateException.printStackTrace();

		} finally {
				HibernateUtil.closeSession();
			return results;
		}
	}
	
	public List<Studentotherfeesstructure> getStudentOtherFeesStructureDetails(int sfsid) {
		List<Studentotherfeesstructure> studentFeesStructure = new ArrayList<Studentotherfeesstructure>();

		try {
			transaction = session.beginTransaction();
			studentFeesStructure = session.createQuery("from Studentotherfeesstructure sfs where sfs.sfsid = '"+sfsid+"'").list();
			transaction.commit();

		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			
			hibernateException.printStackTrace();

		} finally {
				HibernateUtil.closeSession();
			return studentFeesStructure;
		}
	}
	
	public List<Object[]> readStudentsParentsPerBranch(int branchId) {
		List<Object[]> results = new ArrayList<Object[]>();

		try {
			transaction = session.beginTransaction();

			Query query = session
					.createQuery("select s.sid, s.studentexternalid, s.admissionnumber, s.name, s.classstudying, f.fathersname, f.mothersname from Student s JOIN Parents f ON s.sid=f.Student.sid where s.archive = 0 AND s.branchid="+branchId+" order by s.sid DESC").setCacheable(true).setCacheRegion("commonregion");
			results = query.list();
			transaction.commit();

		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			
			hibernateException.printStackTrace();

		} finally {
				HibernateUtil.closeSession();
			return results;
		}
	}

	public List<Student> getListofsid(String classsec) {
		List<Student> results = new ArrayList<Student>();
		try {

			transaction = session.beginTransaction();
			Query query = session.createQuery("From Student where classstudying = "+classsec+"");
			results = query.list();
			transaction.commit();
		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			
			hibernateException.printStackTrace();
		} finally {
				HibernateUtil.closeSession();
			return results;
		}
	}
	
public List<Parents> getReferredList(List<Integer> sidList) {
		
		List<Parents> DetailsList = new ArrayList<Parents>();
		try {
			transaction = session.beginTransaction();
			Query query = session
					.createQuery("From Parents as parents where parents.Student.sid IN (:ids)");
			query.setParameterList("ids", sidList);
			DetailsList = query.list();
			transaction.commit();
		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			hibernateException.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		 }
		return DetailsList;
	}
}

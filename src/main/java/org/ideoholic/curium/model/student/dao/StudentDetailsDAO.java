package org.ideoholic.curium.model.student.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.query.Query;
import org.ideoholic.curium.model.degreedetails.dto.Degreedetails;
import org.ideoholic.curium.model.mess.card.dto.Card;
import org.ideoholic.curium.model.parents.dto.Parents;
import org.ideoholic.curium.model.pudetails.dto.Pudetails;
import org.ideoholic.curium.model.std.dto.Classhierarchy;
import org.ideoholic.curium.model.student.dto.Student;
import org.ideoholic.curium.model.student.dto.Studentfeesstructure;
import org.ideoholic.curium.model.student.dto.Studentotherfeesstructure;
import org.ideoholic.curium.repositories.ParentsRepository;
import org.ideoholic.curium.repositories.StudentOtherFeesStructureRepository;
import org.ideoholic.curium.repositories.StudentRepository;
import org.ideoholic.curium.util.HibernateUtil;
import org.ideoholic.curium.util.QueryUtil;
import org.ideoholic.curium.util.Session;
import org.ideoholic.curium.util.Session.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class StudentDetailsDAO {
	
	@Autowired
	private QueryUtil queryUtil;
	
	@Autowired
	private ParentsRepository parentsRepo;
	
	@Autowired
	private StudentRepository studentRepo;
	
	@Autowired
	private StudentOtherFeesStructureRepository studentOtherFeesStructureRepo;
	
	@SuppressWarnings("finally")
	public Student create(Student student) {
		Transaction transaction = null;
		try {
			Session session = HibernateUtil.openCurrentSession();
			transaction = session.beginTransaction();
			session.save(student);

			transaction.commit();
			log.debug("in add3");
		} catch (Exception hibernateException) { transaction.rollback(); log.error(hibernateException.getMessage(), hibernateException);
			
			hibernateException.printStackTrace();
		} finally {
				HibernateUtil.closeSession();
			return student;
		}
	}

	@SuppressWarnings("finally")
	public List<Object[]> readListOfObjectsPagination(int offset, int noOfRecords, int branchId) {
		List<Object[]> results = new ArrayList<Object[]>();
		Transaction transaction = null;
		try {
			Session session = HibernateUtil.openCurrentSession();
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
					.createQuery("select s.sid, s.studentexternalid, s.admissionnumber, s.name, s.classstudying, f.fathersname, f.mothersname from Student s JOIN Parents f ON s.sid=f.student.sid where s.archive = 0 AND s.branchid="+branchId+" order by s.sid DESC").setCacheable(true).setCacheRegion("commonregion");
			query.setFirstResult(offset);
			query.setMaxResults(noOfRecords);
			results = query.list();
			transaction.commit();

		} catch (Exception hibernateException) { transaction.rollback(); log.error(hibernateException.getMessage(), hibernateException);
			
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
		Transaction transaction = null;
		try {
			Session session = HibernateUtil.openCurrentSession();
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

		} catch (Exception hibernateException) { transaction.rollback(); log.error(hibernateException.getMessage(), hibernateException);
			
			hibernateException.printStackTrace();

		} finally {
				HibernateUtil.closeSession();
			return noOfRecords;
		}
	}

	public Student readUniqueObject(long id) {
		Student student = null;
		Transaction transaction = null;
		try {
			Session session = HibernateUtil.openCurrentSession();

			transaction = session.beginTransaction();
			Query query = session
					.createQuery("from Student as student where student.sid="
							+ id);
			student = (Student) query.uniqueResult();
			transaction.commit();
		} catch (Exception hibernateException) { transaction.rollback(); log.error(hibernateException.getMessage(), hibernateException);
			
			hibernateException.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		 }
		return student;
	}
	//code for readploginUniqueObject
	public Student readploginUniqueObject(String id) {
		Student student = new Student();
		Transaction transaction = null;
		try {
			Session session = HibernateUtil.openCurrentSession();

			transaction = session.beginTransaction();
			Query query = session
					.createQuery("from Student as student where student.studentexternalid='"+id+"'");
			student = (Student) query.uniqueResult();
			transaction.commit();
		} catch (Exception hibernateException) { transaction.rollback(); log.error(hibernateException.getMessage(), hibernateException);
			
			hibernateException.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		 }
		return student;
	}
	//end readploginUniqueObject
	public Parents readUniqueObjectParents(long id) {
		Parents parents = new Parents();
		Transaction transaction = null;
		try {
			Session session = HibernateUtil.openCurrentSession();

			transaction = session.beginTransaction();
			Query query = session
					.createQuery("from Parents as parent where parent.student.sid="
							+ id);
			parents = (Parents) query.uniqueResult();
			transaction.commit();
		} catch (Exception hibernateException) { transaction.rollback(); log.error(hibernateException.getMessage(), hibernateException);
			
			hibernateException.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		 }
		return parents;
	}

	@SuppressWarnings("finally")
	public Student update(Student student) {
		Transaction transaction = null;
		try {
			Session session = HibernateUtil.openCurrentSession();
			transaction = session.beginTransaction();
			session.update(student);
			transaction.commit();
			System.out.println("in add2");
		} catch (Exception hibernateException) { transaction.rollback(); log.error(hibernateException.getMessage(), hibernateException);
			
			hibernateException.printStackTrace();
		} finally {
				HibernateUtil.closeSession();
			return student;
		}
	}

	@SuppressWarnings({ "unchecked", "finally" })
	public List<Student> readListOfStudents(int branchId) {
		List<Student> results = new ArrayList<Student>();
		Transaction transaction = null;
		try {
			Session session = HibernateUtil.openCurrentSession();
			transaction = session.beginTransaction();

			results = (List<Student>) session.createQuery("From Student where archive=0 AND passedout=0 AND droppedout=0 and leftout=0 AND branchid="+branchId).setCacheable(true).setCacheRegion("commonregion")
					.list();
			transaction.commit();

		} catch (Exception hibernateException) { transaction.rollback(); log.error(hibernateException.getMessage(), hibernateException);
			
			hibernateException.printStackTrace();

		} finally {
				HibernateUtil.closeSession();
			return results;
		}
	}

	public void archiveMultiple(List ids) {
		Transaction transaction = null;
		try {
			Session session = HibernateUtil.openCurrentSession();
			transaction = session.beginTransaction();
			Query query = session
					.createQuery("update Student set archive = 1  where id IN (:ids)");
			query.setParameterList("ids", ids);
			query.executeUpdate();
			transaction.commit();
		} catch (Exception hibernateException) { transaction.rollback(); log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		 }

	}

	@SuppressWarnings("unchecked")
	public List<Student> readListOfStudentsArchive() {
		List<Student> results = new ArrayList<Student>();
		Transaction transaction = null;
		try {
			Session session = HibernateUtil.openCurrentSession();
			transaction = session.beginTransaction();

			results = (List<Student>) session.createQuery(
					"FROM Student s where s.archive = 1").setCacheable(true).setCacheRegion("commonregion")
					.list();
			transaction.commit();

		} catch (Exception hibernateException) { transaction.rollback(); log.error(hibernateException.getMessage(), hibernateException);
			
			hibernateException.printStackTrace();

		} finally {
				HibernateUtil.closeSession();
			return results;
		}
	}

	public void deleteMultiple(List ids, List iddetails) {
		Transaction transaction = null;
		try {
			Session session = HibernateUtil.openCurrentSession();
			transaction = session.beginTransaction();
			
			
			Query query = session
					.createQuery("delete from Parents as parents where parents.student.sid IN (:ids)");
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
		} catch (Exception hibernateException) { transaction.rollback(); log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		 }

	}

	public void restoreMultiple(List ids) {
		Transaction transaction = null;
		try {
			Session session = HibernateUtil.openCurrentSession();
			transaction = session.beginTransaction();
			Query query = session
					.createQuery("update Student set archive = 0  where id IN (:ids)");
			query.setParameterList("ids", ids);
			query.executeUpdate();
			transaction.commit();
		} catch (Exception hibernateException) { transaction.rollback(); log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		 }

	}

	@SuppressWarnings("finally")
	public boolean promoteMultiple(List<Student> students, String classStudying, String promotedYear, int branchid) {
		boolean result = false;
		Transaction transaction = null;
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
			Session session = HibernateUtil.openCurrentSession();
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
		} catch (Exception hibernateException) { transaction.rollback(); log.error(hibernateException.getMessage(), hibernateException);
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
		Transaction transaction = null;
		try {
			Session session = HibernateUtil.openCurrentSession();
			transaction = session.beginTransaction();
			Query query = session
					.createQuery("From Parents as parents where parents.student.archive=0 and parents.student.passedout=0 AND parents.student.droppedout=0 and parents.student.leftout=0 AND parents.student.branchid = "+branchId+" order by parents.student.sid desc").setCacheable(true).setCacheRegion("commonregion");
			query.setFirstResult(offset);   
			query.setMaxResults(noOfRecords);
			results = query.getResultList();
			
			transaction.commit();
			

		} catch (Exception hibernateException) {  transaction.rollback(); log.error(hibernateException.getMessage(), hibernateException);
			
			System.out.println("Exception is "+hibernateException);
			hibernateException.printStackTrace();

		} finally {
				HibernateUtil.closeSession();
			return results;
		}
	}

	public List<Student> readListOfObjectsForIcon(int branchId) {
		List<Student> results = new ArrayList<Student>();
		Transaction transaction = null;
		try {
			Session session = HibernateUtil.openCurrentSession();
			transaction = session.beginTransaction();

			// results = (List<PersonalDetails>)
			// session.createQuery("From PersonalDetails p where p.subscriber=1 and  p.archive = 0 order by name desc LIMIT 5 ").list();
			Query query = session
					.createQuery("FROM Student s where s.archive = 0 and s.passedout=0 AND s.droppedout=0 and s.leftout=0 and s.branchid= "+branchId+" order by name ASC");
			
			results = query.setCacheable(true).setCacheRegion("commonregion").list();
			transaction.commit();

		} catch (Exception hibernateException) { transaction.rollback(); log.error(hibernateException.getMessage(), hibernateException);
			
			hibernateException.printStackTrace();

		} finally {
				HibernateUtil.closeSession();
			return results;
		}
	}

	public void addStudentfeesstructure(List<Studentfeesstructure> listOfstudentfeesstructure, String currentYear) {
		Transaction transaction = null;
		try {
			Session session = HibernateUtil.openCurrentSession();
			
			transaction = session.beginTransaction();
			
			for (Studentfeesstructure studentfeesstructure : listOfstudentfeesstructure) {
					
					Query query = session.createQuery("from Studentfeesstructure as sfs where sfs.sid = '"+studentfeesstructure.fetchSid()+"' and sfs.Feescategory.idfeescategory = '"+studentfeesstructure.fetchIdfeescategory()+"' and sfs.academicyear = '"+currentYear+"'");
					Studentfeesstructure feesStructure = (Studentfeesstructure) query.uniqueResult();
					if(feesStructure != null){
						
						Query queryUpdate = session
								.createQuery("update Studentfeesstructure set idfeescategory = '"+studentfeesstructure.fetchIdfeescategory()+"',feesamount = '"+studentfeesstructure.getFeesamount()+"'  where sid = '"+studentfeesstructure.fetchSid()+"' and academicyear = '"+currentYear+"'");
						
						
						queryUpdate.executeUpdate();
					}else if(feesStructure == null){
						session.save(studentfeesstructure);
					}
			}
			
			transaction.commit();
		} catch (Exception hibernateException) { transaction.rollback(); log.error(hibernateException.getMessage(), hibernateException);
			
			hibernateException.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		 }
	}

	public List<Studentfeesstructure> getStudentFeesStructure(long id,
			String currentYear) {
		List<Studentfeesstructure> results = new ArrayList<Studentfeesstructure>();
		Transaction transaction = null;
		try {
			Session session = HibernateUtil.openCurrentSession();
			transaction = session.beginTransaction();

			// results = (List<PersonalDetails>)
			// session.createQuery("From PersonalDetails p where p.subscriber=1 and  p.archive = 0 order by name desc LIMIT 5 ").list();
			Query query = session
					.createQuery("from Studentfeesstructure sfs where sfs.student.sid = '"+id+"' and sfs.academicyear = '"+currentYear+"'");
			
			results = query.list();
			transaction.commit();

		} catch (Exception hibernateException) { transaction.rollback(); log.error(hibernateException.getMessage(), hibernateException);
			
			hibernateException.printStackTrace();

		} finally {
				HibernateUtil.closeSession();
			return results;
		}
	}

	public Parents getStudentRecords(String queryMain) {
		Parents parents = new Parents();
		Transaction transaction = null;
        try {
        	Session session = HibernateUtil.openCurrentSession();
            transaction = session.beginTransaction();
            Query HQLquery = session.createQuery(queryMain);
            parents = (Parents) HQLquery.setCacheable(true).setCacheRegion("commonregion").uniqueResult();
            transaction.commit();
        } catch (Exception hibernateException) {
        	transaction.rollback(); 
        	log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
        }
        finally {
			HibernateUtil.closeSession();
		 }
        return parents;
	}

	public java.util.List<Parents> getStudentsList(String query) {
		java.util.List<Parents> parents = new ArrayList<Parents>();
		Transaction transaction = null;
        try {
        	Session session = HibernateUtil.openCurrentSession();

            transaction = session.beginTransaction();
            Query HQLquery = session.createQuery(query);
            parents = (java.util.List<Parents>) HQLquery.setCacheable(true).setCacheRegion("commonregion").list();
            transaction.commit();
        } catch (Exception hibernateException) {
        	transaction.rollback(); 
        	log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
        }finally {
			HibernateUtil.closeSession();
		 }
        return parents;
	}
	
	public List<Student> getListStudents(String query) {
		java.util.List<Student> student = new ArrayList<Student>();
		Transaction transaction = null;
        try {
        	Session session = HibernateUtil.openCurrentSession();
            transaction = session.beginTransaction();
            Query HQLquery = session.createQuery(query);
            student = HQLquery.setCacheable(true).setCacheRegion("commonregion").list();
            transaction.commit();
        } catch (Exception hibernateException) { transaction.rollback(); log.error(hibernateException.getMessage(), hibernateException);
            
            hibernateException.printStackTrace();
        }finally {
			HibernateUtil.closeSession();
		 }
        return student;
	}

	public List<Studentfeesstructure> getStudentFeesStructureDetails(int sfsid) {
		List<Studentfeesstructure> studentFeesStructure = new ArrayList<Studentfeesstructure>();
		Transaction transaction = null;
		try {
			Session session = HibernateUtil.openCurrentSession();
			transaction = session.beginTransaction();
			studentFeesStructure = session.createQuery("from Studentfeesstructure sfs where sfs.sfsid = '"+sfsid+"'").list();
			transaction.commit();

		} catch (Exception hibernateException) { transaction.rollback(); log.error(hibernateException.getMessage(), hibernateException);
			
			hibernateException.printStackTrace();

		} finally {
				HibernateUtil.closeSession();
			return studentFeesStructure;
		}
	}

	public boolean updateStudent(Student student) {
		Transaction transaction = null;
		try {
			Session session = HibernateUtil.openCurrentSession();
			transaction = session.beginTransaction();
			Query queryUpdate = session
					.createQuery("update Student set reasonleaving = '"+student.getReasonleaving()+"'  where sid = '"+student.getSid()+"'");
			queryUpdate.executeUpdate();
			transaction.commit();
			return true;
		} catch (Exception e) { transaction.rollback(); log.error(e.getMessage(), e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		 }
		return false;
	}

    public void updatePuDetails(Pudetails puDetails) {
    	Transaction transaction = null;
        try {
        	Session session = HibernateUtil.openCurrentSession();
            transaction = session.beginTransaction();
            session.update(puDetails);
            transaction.commit();
    } catch (Exception hibernateException) { transaction.rollback(); log.error(hibernateException.getMessage(), hibernateException);
            
            hibernateException.printStackTrace();
    } finally {
		HibernateUtil.closeSession();
	 }
    }
    
    public void updateDegreeDetails(Degreedetails degreeDetails) {
    	Transaction transaction = null;
        try {
        	Session session = HibernateUtil.openCurrentSession();
            transaction = session.beginTransaction();
            session.update(degreeDetails);
            transaction.commit();
    } catch (Exception hibernateException) { transaction.rollback(); log.error(hibernateException.getMessage(), hibernateException);
            
            hibernateException.printStackTrace();
    } finally {
		HibernateUtil.closeSession();
	 }
    }

    public List<Parents> readListStudentsSuperAdmin(int offset, int noOfRecords) {
        List<Parents> results = new ArrayList<Parents>();
        Transaction transaction = null;
        try {
        		Session session = HibernateUtil.openCurrentSession();
                transaction = session.beginTransaction();
                Query query = session
                                .createQuery("From Parents as parents where parents.student.archive=0 and parents.student.passedout=0 AND parents.student.droppedout=0 and parents.student.leftout=0 order by name ASC");
                query.setFirstResult(offset);   
                query.setMaxResults(noOfRecords);
                results = query.getResultList();
                
                transaction.commit();
                

        } catch (Exception hibernateException) { transaction.rollback(); log.error(hibernateException.getMessage(), hibernateException);
                
                log.debug("Exception is {}", hibernateException);
                hibernateException.printStackTrace();

        } finally {
    			HibernateUtil.closeSession();
                return results;
        }
}

    public int getNoOfRecords() {
        List<Student> results = new ArrayList<Student>();
        int noOfRecords = 0;
        Transaction transaction = null;
        try {
        	Session session = HibernateUtil.openCurrentSession();
                transaction = session.beginTransaction();
                results = (List<Student>) session.createQuery("From Student where archive=0 and passedout=0 AND droppedout=0 and leftout=0").setCacheable(true).setCacheRegion("commonregion").list();
                noOfRecords = results.size();
                transaction.commit();
        } catch (Exception hibernateException) { transaction.rollback(); log.error(hibernateException.getMessage(), hibernateException);
                
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
		Transaction transaction = null;
			List<Card> cardDetailsList = new ArrayList<Card>();
			try {
				Session session = HibernateUtil.openCurrentSession();
				transaction = session.beginTransaction();
				Query query = session
						.createQuery("From Card as card where card.sid IN (:ids)");
				query.setParameterList("ids", ids);
				cardDetailsList = query.list();
				transaction.commit();
			} catch (Exception hibernateException) { transaction.rollback(); log.error(hibernateException.getMessage(), hibernateException);
				hibernateException.printStackTrace();
			}finally {
				HibernateUtil.closeSession();
			 }
			return cardDetailsList;
		}

	public List<Studentfeesstructure> getStudentFeesStructurebyFeesCategory(long id, List<Integer> feesCat) {
		List<Studentfeesstructure> results = new ArrayList<Studentfeesstructure>();
		Transaction transaction = null;
		try {
			Session session = HibernateUtil.openCurrentSession();
			transaction = session.beginTransaction();

			// results = (List<PersonalDetails>)
			// session.createQuery("From PersonalDetails p where p.subscriber=1 and  p.archive = 0 order by name desc LIMIT 5 ").list();
			Query query = session
					.createQuery("from Studentfeesstructure sfs where sfs.student.sid = '"+id+"' and sfs.feescategory.idfeescategory IN (:feescat)");
			query.setParameterList("feescat", feesCat);
			results = query.list();
			transaction.commit();

		} catch (Exception hibernateException) { transaction.rollback(); log.error(hibernateException.getMessage(), hibernateException);
			
			hibernateException.printStackTrace();

		} finally {
				HibernateUtil.closeSession();
			return results;
		}
	}
	
	public List<Studentotherfeesstructure> getStudentOtherFeesStructure(long id,
			String currentYear) {
		List<Studentotherfeesstructure> results = new ArrayList<Studentotherfeesstructure>();
		Transaction transaction = null;
		try {
			Session session = HibernateUtil.openCurrentSession();
			transaction = session.beginTransaction();

			// results = (List<PersonalDetails>)
			// session.createQuery("From PersonalDetails p where p.subscriber=1 and  p.archive = 0 order by name desc LIMIT 5 ").list();
			Query query = session
					.createQuery("from Studentotherfeesstructure sfs where sfs.sid = '"+id+"' and sfs.academicyear = '"+currentYear+"'");

			results = query.list();
			transaction.commit();

		} catch (Exception hibernateException) { transaction.rollback(); log.error(hibernateException.getMessage(), hibernateException);

			hibernateException.printStackTrace();

		} finally {
				HibernateUtil.closeSession();
			return results;
		}
	}

	@Transactional
	public List<Studentotherfeesstructure> getStudentotherFeesStructurebyFeesCategory(Long id, List<Integer> feesCat) {
		List<Studentotherfeesstructure> results = new ArrayList<Studentotherfeesstructure>();
		try {

			// results = (List<PersonalDetails>)
			// session.createQuery("From PersonalDetails p where p.subscriber=1 and  p.archive = 0 order by name desc LIMIT 5 ").list();
			// .createQuery("from Studentotherfeesstructure sfs where sfs.sid = '"+id+"' and sfs.otherfeescategory.idfeescategory IN (:feescat)");
			results = studentOtherFeesStructureRepo.findByStudentSidAndOtherfeescategoryIdfeescategoryIn(id.intValue(), feesCat);
		} catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();

			throw hibernateException;
		}
		return results;
	}
	
	@Transactional
	public Studentotherfeesstructure getStudentOtherFeesStructureDetails(int sfsid) {
		Studentotherfeesstructure studentFeesStructure = new Studentotherfeesstructure();
		try {
			// studentFeesStructure = (Studentotherfeesstructure) session.createQuery("from Studentotherfeesstructure sfs where sfs.sfsid = '"+sfsid+"'").uniqueResult();
			studentFeesStructure = studentOtherFeesStructureRepo.findById(sfsid).orElse(studentFeesStructure);
		} catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();

			throw hibernateException;
		}
		return studentFeesStructure;
	}
	
	@Transactional
	public List<Object[]> readStudentsParentsPerBranch(int branchId) {
		List<Object[]> results = new ArrayList<>();
		try {

			// .createQuery("select s.sid, s.studentexternalid, s.admissionnumber, s.name, s.classstudying, f.fathersname, f.mothersname from Student s JOIN Parents f ON s.sid=f.student.sid where s.archive = 0 AND s.branchid="+branchId+" order by s.sid DESC")
			results = studentRepo.findStudentDetailsByBranchId(branchId);

		} catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();

			throw hibernateException;
		}
		return results;
	}

	@Transactional
	public List<Student> getListofsid(String classsec) {
		List<Student> results = new ArrayList<Student>();
		try {
			// Query query = session.createQuery("From Student where classstudying = "+classsec+"");
			results = studentRepo.findByClassstudying(classsec);
		} catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
			
			throw hibernateException;
		}
		return results;
	}
	
	@Transactional
	public List<Parents> getReferredList(List<Integer> sidList) {
		List<Parents> detailsList = new ArrayList<>();
		try {
			// Query query = session.createQuery("from Parents as parents where parents.student.sid IN (:ids)");
			detailsList = parentsRepo.findByStudentSidIn(sidList);
		} catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
			
			throw hibernateException;
		}
		return detailsList;
	}

	@Transactional
	public Student readUniqueStudent(String queryString) {
		Student student = new Student();
		try {
			List<Student> studentList = queryUtil.findByClassLimitedTo(queryString, Student.class, 1);
			if (!CollectionUtils.isEmpty(studentList)) {
				student = studentList.get(0);
			}
		} catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();

			throw hibernateException;
		}
		return student;
	}

}
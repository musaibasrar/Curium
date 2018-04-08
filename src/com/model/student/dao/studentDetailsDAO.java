package com.model.student.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.model.parents.dto.Parents;
import com.model.stampfees.dto.Academicfeesstructure;
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

	public studentDetailsDAO() {
		session = HibernateUtil.openSession();
	}

	@SuppressWarnings("finally")
	public Student create(Student student) {
		try {
			// this.session = sessionFactory.openCurrentSession();
			transaction = session.beginTransaction();
			session.save(student);

			transaction.commit();
			System.out.println("in add3");
		} catch (HibernateException hibernateException) {
			transaction.rollback();
			hibernateException.printStackTrace();
		} finally {
			session.close();
			return student;
		}
	}

	@SuppressWarnings("finally")
	public List<Student> readListOfObjectsPagination(int offset, int noOfRecords) {
		List<Student> results = new ArrayList<Student>();

		try {
			// this.session =
			// HibernateUtil.getSessionFactory().openCurrentSession();
			transaction = session.beginTransaction();

			// results = (List<PersonalDetails>)
			// session.createQuery("From PersonalDetails p where p.subscriber=1 and  p.archive = 0 order by name desc LIMIT 5 ").list();
			Query query = session
					.createQuery("FROM Student s where s.archive = 0 order by name ASC");
			query.setFirstResult(offset);
			query.setMaxResults(noOfRecords);
			results = query.list();
			transaction.commit();

		} catch (HibernateException hibernateException) {
			transaction.rollback();
			hibernateException.printStackTrace();

		} finally {
			// session.close();
			return results;
		}
	}

	@SuppressWarnings({ "unchecked", "finally" })
	public int getNoOfRecords() {
		List<Student> results = new ArrayList<Student>();
		int noOfRecords = 0;
		try {
			// this.session =
			// HibernateUtil.getSessionFactory().openCurrentSession();
			transaction = session.beginTransaction();

			results = (List<Student>) session.createQuery("From Student")
					.list();
			noOfRecords = results.size();
			System.out
					.println("The size of list is:::::::::::::::::::::::::::::::::::::::::: "
							+ noOfRecords);
			transaction.commit();

		} catch (HibernateException hibernateException) {
			transaction.rollback();
			hibernateException.printStackTrace();

		} finally {
			// session.close();
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
		} catch (HibernateException hibernateException) {
			transaction.rollback();
			hibernateException.printStackTrace();
		}
		// session.close();
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
		} catch (HibernateException hibernateException) {
			transaction.rollback();
			hibernateException.printStackTrace();
		} finally {
			// session.close();
			return student;
		}
	}

	@SuppressWarnings({ "unchecked", "finally" })
	public List<Student> readListOfStudents() {
		List<Student> results = new ArrayList<Student>();

		try {
			// this.session =
			// HibernateUtil.getSessionFactory().openCurrentSession();
			transaction = session.beginTransaction();

			results = (List<Student>) session.createQuery("From Student where archive=0")
					.list();
			transaction.commit();

		} catch (HibernateException hibernateException) {
			transaction.rollback();
			hibernateException.printStackTrace();

		} finally {
			// session.close();
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
		} catch (HibernateException hibernateException) {
			hibernateException.printStackTrace();
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
					"FROM Student s where s.archive = 1 order by name ASC")
					.list();
			System.out.println("name of student " + results.size());
			transaction.commit();

		} catch (HibernateException hibernateException) {
			transaction.rollback();
			hibernateException.printStackTrace();

		} finally {
			// session.close();
			return results;
		}
	}

	public void deleteMultiple(List ids) {
		try {
			transaction = session.beginTransaction();
			
			
			Query query = session
					.createQuery("delete from Parents as parents where parents.Student.sid IN (:ids)");
			query.setParameterList("ids", ids);
			Query query2 = session
					.createQuery("delete from Student where sid IN (:ids)");
			query2.setParameterList("ids", ids);
			query.executeUpdate();
			query2.executeUpdate();
			transaction.commit();
		} catch (HibernateException hibernateException) {
			hibernateException.printStackTrace();
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
		} catch (HibernateException hibernateException) {
			hibernateException.printStackTrace();
		}

	}

	@SuppressWarnings({ "unchecked", "finally" })
	public List<Student> getListOfStudents(String classofStd) {
		java.util.List<Student> results = new ArrayList<Student>();

		try {
			// this.session =
			// HibernateUtil.getSessionFactory().openCurrentSession();
			transaction = session.beginTransaction();

			results = (java.util.List<Student>) session.createQuery(
					"From Student s where s.classstudying LIKE '" + classofStd
							+ " %'").list();

			transaction.commit();

		} catch (HibernateException hibernateException) {
			transaction.rollback();
			hibernateException.printStackTrace();

		} finally {
			// session.close();
			return results;
		}
	}

	@SuppressWarnings("finally")
	public boolean promoteMultiple(List ids, String classStudying) {
		boolean result = false;
		
		  String stringclassStudying = classStudying;
		  String[] temp;
		 
		  /* delimiter */
		  String delimiter = " ";
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

			String hql = "UPDATE Student set classstudying = :promotedclass WHERE sid IN (:ids)";
			Query query = session.createQuery(hql);
			if(classStudying.equalsIgnoreCase("Nursery")){
				query.setParameter("promotedclass", "L.K.G "+sec);
			}else if(classStudying.equalsIgnoreCase("L.K.G")){
				query.setParameter("promotedclass", "U.K.G "+sec);
			}else if(classStudying.equalsIgnoreCase("U.K.G")){
				query.setParameter("promotedclass", "I "+sec);
			}else if(classStudying.equalsIgnoreCase("I")){
				query.setParameter("promotedclass", "II "+sec);
			}else if(classStudying.equalsIgnoreCase("II")){
				query.setParameter("promotedclass", "III "+sec);
			}else if(classStudying.equalsIgnoreCase("III")){
				query.setParameter("promotedclass", "IV "+sec);
			}else if(classStudying.equalsIgnoreCase("IV")){
				query.setParameter("promotedclass", "V "+sec);
			}else if(classStudying.equalsIgnoreCase("V")){
				query.setParameter("promotedclass", "VI "+sec);
			}else if(classStudying.equalsIgnoreCase("VI")){
				query.setParameter("promotedclass", "VII "+sec);
			}else if(classStudying.equalsIgnoreCase("VII")){
				query.setParameter("promotedclass", "VIII "+sec);
			}else if(classStudying.equalsIgnoreCase("VIII")){
				query.setParameter("promotedclass", "IX "+sec);
			}else if(classStudying.equalsIgnoreCase("IX")){
				query.setParameter("promotedclass", "X "+sec);
			}
			
			query.setParameterList("ids", ids);

			query.executeUpdate();
			transaction.commit();
			result = true;
		} catch (HibernateException hibernateException) {
			hibernateException.printStackTrace();
			result = false;
		} finally {
			return result;
		}

	}

	public List<Parents> readListOfObjectsPaginationALL(int offset,
			int noOfRecords) {
		List<Parents> results = new ArrayList<Parents>();

		try {
			// this.session =
			// HibernateUtil.getSessionFactory().openCurrentSession();
			transaction = session.beginTransaction();

			// results = (List<PersonalDetails>)
			// session.createQuery("From PersonalDetails p where p.subscriber=1 and  p.archive = 0 order by name desc LIMIT 5 ").list();
			/*Query query = session
					.createQuery("from Student p inner join p.Parents");*/
			/*Query query = session
					.createSQLQuery("select Student.name,Parents.fathersname From Student inner join Parents on Student.sid = Parents.sid");*/
			/*Query query = session
					.createSQLQuery("select Student.name,Parents.fathersname From Student inner join Parents on Student.sid = Parents.sid").addEntity(Student.class);*/
			Query query = session
					.createQuery("From Parents as parents where parents.Student.archive=0 order by name ASC");
			query.setFirstResult(offset);   
			query.setMaxResults(noOfRecords);
			results = query.list();
			
			transaction.commit();
			

		} catch (HibernateException hibernateException) {
			transaction.rollback();
			hibernateException.printStackTrace();

		} finally {
			// session.close();
			return results;
		}
	}

	public List<Student> readListOfObjectsForIcon() {
		List<Student> results = new ArrayList<Student>();

		try {
			// this.session =
			// HibernateUtil.getSessionFactory().openCurrentSession();
			transaction = session.beginTransaction();

			// results = (List<PersonalDetails>)
			// session.createQuery("From PersonalDetails p where p.subscriber=1 and  p.archive = 0 order by name desc LIMIT 5 ").list();
			Query query = session
					.createQuery("FROM Student s where s.archive = 0 order by name ASC");
			
			results = query.list();
			transaction.commit();

		} catch (HibernateException hibernateException) {
			transaction.rollback();
			hibernateException.printStackTrace();

		} finally {
			// session.close();
			return results;
		}
	}

	public void addStudentfeesstructure(List<Studentfeesstructure> listOfstudentfeesstructure, String currentYear) {
		try {
			// this.session = sessionFactory.openCurrentSession();
			
			
			for (Studentfeesstructure studentfeesstructure : listOfstudentfeesstructure) {
					
				transaction = session.beginTransaction();

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
		} catch (HibernateException hibernateException) {
			transaction.rollback();
			hibernateException.printStackTrace();
		} finally {
			session.close();
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

		} catch (HibernateException hibernateException) {
			transaction.rollback();
			hibernateException.printStackTrace();

		} finally {
			//session.close();
			return results;
		}
	}

	public Parents getStudentRecords(String queryMain) {
		Parents parents = new Parents();
        try {
            //this.session = HibernateUtil.getSessionFactory().openCurrentSession();

            transaction = session.beginTransaction();
            Query HQLquery = session.createQuery(queryMain);
            parents = (Parents) HQLquery.uniqueResult();
            transaction.commit();
        } catch (HibernateException hibernateException) {
            transaction.rollback();
            hibernateException.printStackTrace();
        }
        //session.close();
        return parents;
	}

	public java.util.List<Parents> getStudentsList(String query) {
		java.util.List<Parents> parents = new ArrayList<Parents>();
        try {
            //this.session = HibernateUtil.getSessionFactory().openCurrentSession();

            transaction = session.beginTransaction();
            Query HQLquery = session.createQuery(query);
            parents = (java.util.List<Parents>) HQLquery.list();
            transaction.commit();
        } catch (HibernateException hibernateException) {
            transaction.rollback();
            hibernateException.printStackTrace();
        }
        //session.close();
        return parents;
	}
	
	public List<Student> getListStudents(String query) {
		java.util.List<Student> student = new ArrayList<Student>();
        try {
            transaction = session.beginTransaction();
            Query HQLquery = session.createQuery(query);
            student = HQLquery.list();
            transaction.commit();
        } catch (HibernateException hibernateException) {
            transaction.rollback();
            hibernateException.printStackTrace();
        }finally{
        	session.close();
        }
        return student;
	}

	public List<Studentfeesstructure> getStudentFeesStructureDetails(int sfsid) {
		List<Studentfeesstructure> studentFeesStructure = new ArrayList<Studentfeesstructure>();

		try {
			transaction = session.beginTransaction();
			studentFeesStructure = session.createQuery("from Studentfeesstructure sfs where sfs.sfsid = '"+sfsid+"'").list();
			transaction.commit();

		} catch (HibernateException hibernateException) {
			transaction.rollback();
			hibernateException.printStackTrace();

		} finally {
			//session.close();
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
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return false;
	}
	
}

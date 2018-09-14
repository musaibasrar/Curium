package com.model.student.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.model.parents.dto.Parents;
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
		} catch (HibernateException hibernateException) {transaction.rollback();
			transaction.rollback();
			hibernateException.printStackTrace();
		} finally {
			//session.close();
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
					.createQuery("FROM Student s where s.archive = 0 AND s.passedout = 0 AND s.droppedout = 0 order by name ASC");
			query.setFirstResult(offset);
			query.setMaxResults(noOfRecords);
			results = query.list();
			transaction.commit();

		} catch (HibernateException hibernateException) {transaction.rollback();
			transaction.rollback();
			hibernateException.printStackTrace();

		} finally {
			// //session.close();
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

			results = (List<Student>) session.createQuery("From Student where archive=0 AND passedout = 0 AND droppedout = 0 ")
					.list();
			noOfRecords = results.size();
			System.out
					.println("The size of list is:::::::::::::::::::::::::::::::::::::::::: "
							+ noOfRecords);
			transaction.commit();

		} catch (HibernateException hibernateException) {transaction.rollback();
			transaction.rollback();
			hibernateException.printStackTrace();

		} finally {
			// //session.close();
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
		} catch (HibernateException hibernateException) {transaction.rollback();
			transaction.rollback();
			hibernateException.printStackTrace();
		}
		// //session.close();
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
		} catch (HibernateException hibernateException) {transaction.rollback();
			transaction.rollback();
			hibernateException.printStackTrace();
		} finally {
			// //session.close();
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

			results = (List<Student>) session.createQuery("From Student where archive=0 AND passedout = 0 AND droppedout = 0 ")
					.list();
			transaction.commit();

		} catch (HibernateException hibernateException) {transaction.rollback();
			transaction.rollback();
			hibernateException.printStackTrace();

		} finally {
			// //session.close();
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
		} catch (HibernateException hibernateException) {transaction.rollback();
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

		} catch (HibernateException hibernateException) {transaction.rollback();
			transaction.rollback();
			hibernateException.printStackTrace();

		} finally {
			// //session.close();
			return results;
		}
	}

	public boolean deleteMultiple(List ids) {
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
			return true;
		} catch (HibernateException hibernateException) {transaction.rollback();
			hibernateException.printStackTrace();
		}
		return false;
	}

	public void restoreMultiple(List ids) {
		try {
			transaction = session.beginTransaction();
			Query query = session
					.createQuery("update Student set archive = 0  where id IN (:ids)");
			query.setParameterList("ids", ids);
			query.executeUpdate();
			transaction.commit();
		} catch (HibernateException hibernateException) {transaction.rollback();
			hibernateException.printStackTrace();
		}

	}

	@SuppressWarnings({ "unchecked", "finally" })
	public List<Student> getListOfStudents(String examLevel, int branchId) {
		java.util.List<Student> results = new ArrayList<Student>();

		try {
			// this.session =
			// HibernateUtil.getSessionFactory().openCurrentSession();
			transaction = session.beginTransaction();

			results = (java.util.List<Student>) session.createQuery(
					"From Student s where s.examlevel = '" +examLevel+ "'").list();
			transaction.commit();

		} catch (HibernateException hibernateException) {transaction.rollback();
			transaction.rollback();
			hibernateException.printStackTrace();

		} finally {
			// //session.close();
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
			Query query1 = session.createQuery("From Classhierarchy where lowerclass = '"+classStudying+"'");
			Classhierarchy ch = (Classhierarchy) query1.uniqueResult();
			String hql = "UPDATE Student set examlevel = :promotedclass WHERE sid IN (:ids)";
			Query query = session.createQuery(hql);
			if(ch!=null) {
			    query.setParameter("promotedclass", ch.getUpperclass());
			}
			query.setParameterList("ids", ids);
			query.executeUpdate();
			transaction.commit();
			result = true;
		} catch (Exception e) {transaction.rollback();
			e.printStackTrace();
			result = false;
		} finally {
			return result;
		}

	}

	public List<Parents> readListOfObjectsPaginationALL(int offset,
			int noOfRecords, int branchId) {
		List<Parents> results = new ArrayList<Parents>();

		try {
			transaction = session.beginTransaction();
			Query query = session.createQuery("From Parents as parents where parents.Student.archive=0 AND parents.Student.passedout = 0 AND parents.Student.droppedout = 0 order by parents.Student.sid DESC");
			query.setFirstResult(offset);   
			query.setMaxResults(noOfRecords);
			results = query.getResultList();
			transaction.commit();
		} catch (Exception e) {transaction.rollback();
			transaction.rollback();
			System.out.println("Exception is "+e);
			e.printStackTrace();
		} finally {
			// //session.close();
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
					.createQuery("FROM Student s where s.archive = 0 AND s.passedout = 0 AND s.droppedout = 0 order by name ASC");
			
			results = query.list();
			transaction.commit();

		} catch (HibernateException hibernateException) {transaction.rollback();
			transaction.rollback();
			hibernateException.printStackTrace();

		} finally {
			// //session.close();
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
		} catch (HibernateException hibernateException) {transaction.rollback();
			transaction.rollback();
			hibernateException.printStackTrace();
		} finally {
			//session.close();
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

		} catch (HibernateException hibernateException) {transaction.rollback();
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
        } catch (HibernateException hibernateException) {transaction.rollback();
            transaction.rollback();
            hibernateException.printStackTrace();
        }
        //session.close();
        return parents;
	}

	public java.util.List<Parents> getStudentsList(String query) {
		java.util.List<Parents> parents = new ArrayList<Parents>();
        try {
            transaction = session.beginTransaction();
            Query HQLquery = session.createQuery(query);
            parents = (java.util.List<Parents>) HQLquery.list();
            transaction.commit();
        } catch (HibernateException hibernateException) {transaction.rollback();
            transaction.rollback();
            hibernateException.printStackTrace();
        }
        return parents;
	}
	
	public List<Student> getListStudents(String query) {
		java.util.List<Student> student = new ArrayList<Student>();
        try {
            transaction = session.beginTransaction();
            Query HQLquery = session.createQuery(query);
            student = HQLquery.list();
            transaction.commit();
        } catch (HibernateException hibernateException) {transaction.rollback();
            transaction.rollback();
            hibernateException.printStackTrace();
        }finally{
        	//session.close();
        }
        return student;
	}

	public List<Studentfeesstructure> getStudentFeesStructureDetails(int sfsid) {
		List<Studentfeesstructure> studentFeesStructure = new ArrayList<Studentfeesstructure>();

		try {
			transaction = session.beginTransaction();
			studentFeesStructure = session.createQuery("from Studentfeesstructure sfs where sfs.sfsid = '"+sfsid+"'").list();
			transaction.commit();

		} catch (HibernateException hibernateException) {transaction.rollback();
			transaction.rollback();
			hibernateException.printStackTrace();

		} finally {
			//session.close();
			return studentFeesStructure;
		}
	}

	/*public boolean updateStudent(Student student) {
		
		try {
			transaction = session.beginTransaction();
			Query queryUpdate = session
					.createQuery("update Student set reasonleaving = '"+student.getReasonleaving()+"'  where sid = '"+student.getSid()+"'");
			queryUpdate.executeUpdate();
			transaction.commit();
			return true;
		} catch (Exception e) {transaction.rollback();
			e.printStackTrace();
		}finally{
			//session.close();
		}
		return false;
	}*/
	
	public List<Parents> readListStudentsSuperAdmin(int offset, int noOfRecords) {
	        List<Parents> results = new ArrayList<Parents>();

	        try {
	                
	                transaction = session.beginTransaction();
	                Query query = session
	                                .createQuery("From Parents as parents where parents.Student.archive=0 AND parents.Student.passedout = 0 AND parents.Student.droppedout = 0  order by name ASC");
	                query.setFirstResult(offset);   
	                query.setMaxResults(noOfRecords);
	                results = query.getResultList();
	                
	                transaction.commit();
	                

	        } catch (Exception e) {transaction.rollback();
	                transaction.rollback();
	                System.out.println("Exception is "+e);
	                e.printStackTrace();

	        } finally {
	                // //session.close();
	                return results;
	        }
	}
	
	
	    public int getNoOfRecords() {
	        List<Student> results = new ArrayList<Student>();
	        int noOfRecords = 0;
	        try {
	                transaction = session.beginTransaction();
	                results = (List<Student>) session.createQuery("From Student where archive=0 AND s.passedout = 0 AND s.droppedout = 0 ").list();
	                noOfRecords = results.size();
	                transaction.commit();
	        } catch (HibernateException hibernateException) {transaction.rollback();
	                transaction.rollback();
	                hibernateException.printStackTrace();

	        } finally {
	                return noOfRecords;
	        }
	}

        public boolean graduateMultiple(List ids) {
            boolean result = false;

          try {
                  transaction = session.beginTransaction();
                  String hql = "UPDATE Student set passedout = 1 WHERE sid IN (:ids)";
                  Query query = session.createQuery(hql);
                  query.setParameterList("ids", ids);
                  query.executeUpdate();
                  transaction.commit();
                  result = true;
          } catch (Exception e) {transaction.rollback();
                  e.printStackTrace();
                  result = false;
          } finally {
                  return result;
          }

  }

        public boolean droppedMultiple(List ids) {
            boolean result = false;

          try {
                  transaction = session.beginTransaction();
                  String hql = "UPDATE Student set droppedout = 1 WHERE sid IN (:ids)";
                  Query query = session.createQuery(hql);
                  query.setParameterList("ids", ids);
                  query.executeUpdate();
                  transaction.commit();
                  result = true;
          } catch (Exception e) {transaction.rollback();
                  e.printStackTrace();
                  result = false;
          } finally {
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
                                    "FROM Student s where s.passedout = 1 order by name ASC")
                                    .list();
                    transaction.commit();

            } catch (HibernateException hibernateException) {transaction.rollback();
                    transaction.rollback();
                    hibernateException.printStackTrace();

            } finally {
                    // //session.close();
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
                                    "FROM Student s where s.droppedout = 1 order by name ASC")
                                    .list();
                    transaction.commit();

            } catch (HibernateException hibernateException) {transaction.rollback();
                    transaction.rollback();
                    hibernateException.printStackTrace();

            } finally {
                    // //session.close();
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
        } catch (HibernateException hibernateException) {transaction.rollback();
                hibernateException.printStackTrace();
        }

}

        public void restoreMultipleDroppedout(List ids) {
            try {
                transaction = session.beginTransaction();
                Query query = session.createQuery("update Student set droppedout = 0  where id IN (:ids)");
                query.setParameterList("ids", ids);
                query.executeUpdate();
                transaction.commit();
        } catch (HibernateException hibernateException) {transaction.rollback();
                hibernateException.printStackTrace();
        }

}

        public int getNoOfRecordsCenter(int branchId) {
            List<Student> results = new ArrayList<Student>();
            int noOfRecords = 0;
            try {
                    transaction = session.beginTransaction();
                    results = (List<Student>) session.createQuery("From Student where archive=0 AND passedout = 0 AND droppedout = 0 AND branchid="+branchId)
                                    .list();
                    noOfRecords = results.size();
                    System.out.println("The size of list is:::::::::::::::::::::::::::::::::::::::::: "+ noOfRecords);
                    transaction.commit();
            } catch (HibernateException hibernateException) {transaction.rollback();
                    transaction.rollback();
                    hibernateException.printStackTrace();

            } finally {
                    // //session.close();
                    return noOfRecords;
            }
    }

        public List<Parents> readListOfObjectsPaginationALLCenter(int offset, int noOfRecords, int branchId) {
            List<Parents> results = new ArrayList<Parents>();

            try {
                    transaction = session.beginTransaction();
                    Query query = session.createQuery("From Parents as parents where parents.Student.archive=0 AND parents.Student.passedout = 0 AND parents.Student.droppedout = 0 AND parents.Student.branchid = "+branchId+" order by name ASC");
                    query.setFirstResult(offset);   
                    query.setMaxResults(noOfRecords);
                    results = query.getResultList();
                    transaction.commit();
            } catch (Exception e) {transaction.rollback();
                    transaction.rollback();
                    System.out.println("Exception is "+e);
                    e.printStackTrace();
            } finally {
                    // //session.close();
                    return results;
            }
    }

        public List<Student> readListOfStudentsCenter(int branchId) {
            List<Student> results = new ArrayList<Student>();

            try {
                    // this.session =
                    // HibernateUtil.getSessionFactory().openCurrentSession();
                    transaction = session.beginTransaction();

                    results = (List<Student>) session.createQuery("From Student where archive=0 AND passedout = 0 AND droppedout = 0 AND branchid="+branchId)
                                    .list();
                    transaction.commit();

            } catch (HibernateException hibernateException) {transaction.rollback();
                    transaction.rollback();
                    hibernateException.printStackTrace();

            } finally {
                    // //session.close();
                    return results;
            }
    }
	
}

package com.model.student.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import com.util.Session;
import com.util.Session.Transaction;
import org.hibernate.query.Query;

import com.model.attendance.dao.AttendanceDAO;
import com.model.attendance.dto.Studentdailyattendance;
import com.model.hr.dto.Paybasic;
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
		} catch (HibernateException hibernateException) {transaction.rollback();
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
					.createQuery("FROM Student s where s.archive = 0 AND s.passedout = 0 AND (s.remarks = 'approved' OR s.remarks='admin') AND s.droppedout = 0 order by admissionnumber DESC");
			query.setFirstResult(offset);
			query.setMaxResults(noOfRecords);
			results = query.list();
			transaction.commit();

		} catch (HibernateException hibernateException) {transaction.rollback();
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

			results = (List<Student>) session.createQuery("From Student where archive=0 AND passedout = 0 AND (remarks = 'approved' OR remarks = 'admin') AND droppedout = 0 order by admissionnumber DESC").setCacheable(true).setCacheRegion("commonregion")
					.list();
			noOfRecords = results.size();
			transaction.commit();

		} catch (HibernateException hibernateException) {transaction.rollback();
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
		} catch (HibernateException hibernateException) {transaction.rollback();
			hibernateException.printStackTrace();
		}
		finally {
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
		} catch (HibernateException hibernateException) {transaction.rollback();
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

			results = (List<Student>) session.createQuery("From Student where archive=0 AND passedout = 0 AND (remarks = 'approved' OR remarks = 'admin') AND droppedout = 0 order by admissionnumber DESC").setCacheable(true).setCacheRegion("commonregion")
					.list();
			transaction.commit();

		} catch (HibernateException hibernateException) {transaction.rollback();
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
		} catch (HibernateException hibernateException) {transaction.rollback();
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
					"FROM Student s where s.archive = 1 order by admissionnumber DESC")
					.list();
			transaction.commit();

		} catch (HibernateException hibernateException) {transaction.rollback();
			hibernateException.printStackTrace();

		} finally {
			 HibernateUtil.closeSession();
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
		}finally {
			HibernateUtil.closeSession();
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
		}finally {
			HibernateUtil.closeSession();
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
					"From Student s where s.examlevel = '" +examLevel+ "' AND s.archive = 0 AND s.passedout = 0 AND s.droppedout = 0 AND (s.remarks = 'approved' OR s.remarks = 'admin') order by s.admissionnumber DESC").list();
			transaction.commit();

		} catch (HibernateException hibernateException) {transaction.rollback();
			hibernateException.printStackTrace();

		} finally {
			HibernateUtil.closeSession();
			return results;
		}
	}

	@SuppressWarnings("finally")
	public boolean promoteOrDemoteMultiple(List<Student> studentList, String classStudying,  String currentAcademicYear, String promoteOrDemote) {
		boolean result = false;
		
		  String stringclassStudying = classStudying;
		  String[] temp;
		  //int previousAcademicYear = Integer.parseInt(currentAcademicYear)-1;
		 
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
			
			if("Promote".equalsIgnoreCase(promoteOrDemote)){
				
				Query query1 = session.createQuery("From Classhierarchy where lowerclass = '"+classStudying+"'");
				Classhierarchy ch = (Classhierarchy) query1.uniqueResult();
				if(ch!=null) {
				    
				    for (Student student : studentList) {
				        String hql = "UPDATE student set examlevel = '"+ch.getUpperclass()+"',"
				        		+ " admissionnumber = CONCAT(REPLACE(LEFT(admissionnumber,2),LEFT('"+student.getAdmissionnumber()+"',2)"
            					+ " , '"+currentAcademicYear+"'),SUBSTRING(admissionnumber, 3, CHAR_LENGTH(admissionnumber))),"
			                     		+ " admissionnumber = replace(admissionnumber, '"+classStudying+"', '"+ch.getUpperclass()+"'),"
				                        + " age = age+1, qualification = '"+student.getQualification()+"' WHERE sid ="+student.getSid()+"";
	                                Query query = session.createSQLQuery(hql);
	                                query.executeUpdate();
	                                
	                            }
				}
				
			}else if("Demote".equalsIgnoreCase(promoteOrDemote)){
				Query query1 = session.createQuery("From Classhierarchy where upperclass = '"+classStudying+"'");
				Classhierarchy ch = (Classhierarchy) query1.uniqueResult();
				if(ch!=null) {
				    for (Student student : studentList) {
				        String hql = "UPDATE student set examlevel = :demotedclass,"
				                		+ "admissionnumber = CONCAT(REPLACE(LEFT(admissionnumber,2), LEFT('"+student.getAdmissionnumber()+"',2), LEFT('"+student.getAdmissionnumber()+"',2)),"
		        	                    + "SUBSTRING(admissionnumber, 3, CHAR_LENGTH(admissionnumber))),"
		        	                    + " admissionnumber = replace(admissionnumber, '"+classStudying+"', '"+ch.getLowerclass()+"'),"
				                        + " age = age-1, qualification = '"+student.getQualification()+"' WHERE sid ="+student.getSid();
	                                Query query = session.createQuery(hql);
	                                query.setParameter("demotedclass", ch.getLowerclass());
	                                query.executeUpdate();
	                        }
				}
			}
			
			transaction.commit();
			
			
			result = true;
		} catch (Exception e) {transaction.rollback();
			e.printStackTrace();
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
			Date date= new Date();
			 long time = date.getTime();
			Query query = session.createQuery("From Parents as parents where parents.Student.archive=0 AND parents.Student.passedout = 0 AND (parents.Student.remarks = 'approved' OR parents.Student.remarks = 'admin')  AND  parents.Student.droppedout = 0 order by parents.Student.sid DESC").setCacheable(true).setCacheRegion("commonregion");
			query.setFirstResult(offset);   
			query.setMaxResults(noOfRecords);
			results = query.getResultList();
			
			Date date2= new Date();
			 long time2 = date2.getTime();
			 logger.info("Query Student View All time: "+(time-time2));
			transaction.commit();
		} catch (Exception e) {transaction.rollback();
		        logger.info("Exception is "+e);
			e.printStackTrace();
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
					.createQuery("FROM Student s where s.archive = 0 AND s.passedout = 0 AND (s.remarks = 'approved' OR s.remarks = 'admin') AND s.droppedout = 0 order by s.admissionnumber DESC");
			
			results = query.list();
			transaction.commit();

		} catch (HibernateException hibernateException) {transaction.rollback();
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
		} catch (HibernateException hibernateException) {transaction.rollback();
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

		} catch (HibernateException hibernateException) {transaction.rollback();
			hibernateException.printStackTrace();

		} finally {
			HibernateUtil.closeSession();
			return results;
		}
	}

	public Parents getStudentRecords(String queryMain) {
		Parents parents = new Parents();
        try {
            //this.session = HibernateUtil.getSessionFactory().openCurrentSession();

            transaction = session.beginTransaction();
            Query HQLquery = session.createQuery(queryMain);
            parents = (Parents) HQLquery.setCacheable(true).setCacheRegion("commonregion").uniqueResult();
            transaction.commit();
        } catch (HibernateException hibernateException) {transaction.rollback();
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
            transaction = session.beginTransaction();
            Query HQLquery = session.createQuery(query);
            parents = (java.util.List<Parents>) HQLquery.setCacheable(true).setCacheRegion("commonregion").list();
            transaction.commit();
        } catch (HibernateException hibernateException) {transaction.rollback();
            hibernateException.printStackTrace();
        }finally {
			HibernateUtil.closeSession();
		}
        return parents;
	}
	
	public java.util.List<Parents> getStudentsListWithParam(String query, List<String> centerCode) {
		java.util.List<Parents> parents = new ArrayList<Parents>();
        try {
            transaction = session.beginTransaction();
            
            if(!centerCode.isEmpty()) {
            	Query HQLquery = session.createQuery(query).setParameterList("ids", centerCode);
            	parents = (java.util.List<Parents>) HQLquery.setCacheable(true).setCacheRegion("commonregion").list();
            }else {
            	Query HQLquery = session.createQuery(query);
            	parents = (java.util.List<Parents>) HQLquery.setCacheable(true).setCacheRegion("commonregion").list();
            }
            
            
            transaction.commit();
        } catch (HibernateException hibernateException) {transaction.rollback();
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
        } catch (HibernateException hibernateException) {transaction.rollback();
            hibernateException.printStackTrace();
        }finally{
        	HibernateUtil.closeSession();
        }
        return student;
	}
	
	public BigInteger getStudentsListSQL(String query) {
		BigInteger totalCount = null;
        try {
            transaction = session.beginTransaction();
            Query SQLquery = session.createSQLQuery(query);
            totalCount = (BigInteger) SQLquery.uniqueResult();
            transaction.commit();
        } catch (HibernateException hibernateException) {transaction.rollback();
            hibernateException.printStackTrace();
        }finally{
        	HibernateUtil.closeSession();
        }
        return totalCount;
	}

	public List<Studentfeesstructure> getStudentFeesStructureDetails(int sfsid) {
		List<Studentfeesstructure> studentFeesStructure = new ArrayList<Studentfeesstructure>();

		try {
			transaction = session.beginTransaction();
			studentFeesStructure = session.createQuery("from Studentfeesstructure sfs where sfs.sfsid = '"+sfsid+"'").list();
			transaction.commit();

		} catch (HibernateException hibernateException) {transaction.rollback();
			hibernateException.printStackTrace();

		} finally {
			HibernateUtil.closeSession();
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
			HibernateUtil.closeSession();
		}
		return false;
	}*/
	
	public List<Parents> readListStudentsSuperAdmin(int offset, int noOfRecords) {
	        List<Parents> results = new ArrayList<Parents>();

	        try {
	                
	                transaction = session.beginTransaction();
	                Query query = session
	                                .createQuery("From Parents as parents where parents.Student.archive=0 AND parents.Student.passedout = 0 AND (parents.Student.remarks = 'approved' OR parents.Student.remarks = 'admin') AND parents.Student.droppedout = 0   Order By parents.Student.admissionnumber ASC").setCacheable(true).setCacheRegion("commonregion");
	                query.setFirstResult(offset);   
	                query.setMaxResults(noOfRecords);
	                results = query.getResultList();
	                
	                transaction.commit();
	                

	        } catch (Exception e) {transaction.rollback();
	                logger.error(e);
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
	                results = (List<Student>) session.createQuery("From Student s where s.archive=0 AND s.passedout = 0 AND (s.remarks = 'approved' OR s.remarks = 'admin') AND s.droppedout = 0").setCacheable(true).setCacheRegion("commonregion").list();
	                noOfRecords = results.size();
	                transaction.commit();
	        } catch (HibernateException hibernateException) {transaction.rollback();
	                hibernateException.printStackTrace();

	        } finally {
	    			HibernateUtil.closeSession();
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
      			HibernateUtil.closeSession();
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

            } catch (HibernateException hibernateException) {transaction.rollback();
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

            } catch (HibernateException hibernateException) {transaction.rollback();
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
        } catch (HibernateException hibernateException) {transaction.rollback();
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
        } catch (HibernateException hibernateException) {transaction.rollback();
                hibernateException.printStackTrace();
        }finally {
			HibernateUtil.closeSession();
		}

}

        public int getNoOfRecordsCenter(int branchId) {
            List<Student> results = new ArrayList<Student>();
            int noOfRecords = 0;
            try {
                    transaction = session.beginTransaction();
                    results = (List<Student>) session.createQuery("From Student where archive=0 AND passedout = 0 AND (remarks = 'approved' OR remarks='admin') AND droppedout = 0 AND branchid="+branchId+" order by admissionnumber DESC").setCacheable(true).setCacheRegion("commonregion")
                                    .list();
                    noOfRecords = results.size();
                    transaction.commit();
            } catch (HibernateException hibernateException) {transaction.rollback();
                    hibernateException.printStackTrace();

            } finally {
                    HibernateUtil.closeSession();
                    return noOfRecords;
            }
    }

        public List<Parents> readListOfObjectsPaginationALLCenter(int offset, int noOfRecords, int branchId) {
            List<Parents> results = new ArrayList<Parents>();

            try {
                    transaction = session.beginTransaction();
                    Query query = session.createQuery("From Parents as parents where parents.Student.archive=0 AND parents.Student.passedout = 0 AND parents.Student.droppedout = 0 AND (parents.Student.remarks = 'approved' OR parents.Student.remarks = 'admin')  AND parents.Student.branchid = "+branchId+" order by parents.Student.admissionnumber DESC").setCacheable(true).setCacheRegion("commonregion");
                    query.setFirstResult(offset);   
                    query.setMaxResults(noOfRecords);
                    results = query.getResultList();
                    transaction.commit();
            } catch (Exception e) {transaction.rollback();
                    logger.error("Exception is "+e);
                    e.printStackTrace();
            } finally {
                    HibernateUtil.closeSession();
                    return results;
            }
    }

        public List<Student> readListOfStudentsCenter(int branchId) {
            List<Student> results = new ArrayList<Student>();

            try {
                    // this.session =
                    // HibernateUtil.getSessionFactory().openCurrentSession();
                    transaction = session.beginTransaction();

                    results = (List<Student>) session.createQuery("From Student where archive=0 AND passedout = 0 AND (remarks = 'approved' OR remarks = 'admin') AND droppedout = 0 AND branchid="+branchId+" order by admissionnumber DESC").setCacheable(true).setCacheRegion("commonregion")
                                    .list();
                    transaction.commit();

            } catch (HibernateException hibernateException) {transaction.rollback();
                    hibernateException.printStackTrace();

            } finally {
                    HibernateUtil.closeSession();
                    return results;
            }
    }

		public List<Parents> getStudentsListAttendance(String searchQuery, List<String> studentExternalId) {
            List<Parents> studentDetails = new ArrayList<Parents>();
                  transaction = session.beginTransaction();
                  try {
                  //String hql = "UPDATE Student set passedout = 1 WHERE sid IN (:ids)";
                  String hql = searchQuery+"  AND parent.Student.studentexternalid IN (:ids) Order By parent.Student.admissionnumber ASC";
                  Query query = session.createQuery(hql);
                  query.setParameterList("ids", studentExternalId);
                  studentDetails = query.getResultList();
                  transaction.commit();
          } catch (Exception e) {transaction.rollback();
                  e.printStackTrace();
          } finally {
      			HibernateUtil.closeSession();
                  return studentDetails;
          }

  }
		
		
	public List<Student> getStudentsPresentAttendanceList(String searchQuery) {
            List<Student> studentDetails = new ArrayList<Student>();
                  transaction = session.beginTransaction();
                  try {
                  //String hql = "UPDATE Student set passedout = 1 WHERE sid IN (:ids)";
                  //String hql = searchQuery+"  AND parent.Student.studentexternalid IN (:ids) Order By parent.Student.admissionnumber ASC";
                  Query query = session.createSQLQueryEntity(searchQuery,Student.class);
                  studentDetails = query.list();
                  transaction.commit();
          } catch (Exception e) {transaction.rollback();
                  e.printStackTrace();
          } finally {
      			HibernateUtil.closeSession();
                  return studentDetails;
          }

  }

		public boolean approveRecords(Map<String, String> idAdmissionNo) {
			try {
				transaction = session.beginTransaction();
				
				
				for (Map.Entry<String,String> admnoid : idAdmissionNo.entrySet()) {
					Query query = session.createQuery("update Student set admissionnumber = '"+admnoid.getValue()+"' , remarks='approved',approvedon=current_date() where sid = "+admnoid.getKey()+"");
					query.executeUpdate();
				}
				
				/*
				 * Query query = session
				 * .createQuery("update Student set remarks='approved',approvedon=current_date() where sid IN (:ids)"
				 * ); query.setParameterList("ids", idAdmissionNo); query.executeUpdate();
				 */
				
				transaction.commit();
				
				return true;
			} catch (HibernateException hibernateException) {transaction.rollback();
				hibernateException.printStackTrace();
			}finally {
				HibernateUtil.closeSession();
			}
			return false;
		}

		public void rejectRecords(List ids) {
			try {
				transaction = session.beginTransaction();
				Query query = session
						.createQuery("update Student set remarks='rejected' where id IN (:ids)");
				query.setParameterList("ids", ids);
				query.executeUpdate();
				transaction.commit();
			} catch (HibernateException hibernateException) {transaction.rollback();
				hibernateException.printStackTrace();
			}finally {
				HibernateUtil.closeSession();
			}

		}
	
}
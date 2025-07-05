package org.ideoholic.curium.model.employee.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.query.Query;
import org.ideoholic.curium.model.employee.dto.Teacher;
import org.ideoholic.curium.model.hr.dto.Paybasic;
import org.ideoholic.curium.repositories.PaybasicRepository;
import org.ideoholic.curium.repositories.TeacherRepository;
import org.ideoholic.curium.util.HibernateUtil;
import org.ideoholic.curium.util.Session;
import org.ideoholic.curium.util.Session.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EmployeeDAO {
	
	@Autowired
	private TeacherRepository teacherRepository;
	
	@Autowired
	private PaybasicRepository payBasicRepo;

	@Transactional
	public boolean create(Teacher employee) {
		boolean result = false;
		try {
			List<Teacher> queryList = teacherRepository.findByBranchidOrderByTidDesc(employee.getBranchid());
			String externalId = employee.getTeacherexternalid();

			if (queryList.size() > 0) {
				String tEId = queryList.get(0).getTeacherexternalid();
				String externalIdNo = tEId.length() > 2 ? tEId.substring(tEId.length() - 2) : tEId;
				employee.setTeacherexternalid(externalId + String.format("%02d", Integer.parseInt(externalIdNo) + 1));
			} else {
				employee.setTeacherexternalid(externalId + String.format("%02d", 1));
			}
			teacherRepository.save(employee);
			result = true;
		} catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
			throw hibernateException;
		}
		return result;
	}

	@Transactional
	public List<Teacher> readListOfObjects(int branchId) {
		List<Teacher> results = new ArrayList<Teacher>();
		try {
			results = teacherRepository.findByBranchid(branchId);
		} catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
			throw hibernateException;
		}
		return results;
	}
	
	@Transactional
	public List<Teacher> readListOfEmployeesBasicPay(int branchId) {
		List<Teacher> results = new ArrayList<>();
		try {
			List<Paybasic> payList = payBasicRepo.findAll();
			List<Integer> tidList = new ArrayList<>();
			tidList.add(0);
			for (Paybasic paybasic : payList) {
				tidList.add(paybasic.getTeacher().getTid());
			}
			results = teacherRepository.findByBranchidAndTidNotIn(branchId, tidList);
		} catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
			throw hibernateException;
		}
		return results;
	}

	@Transactional
	public List<Teacher> readListOfObjects() {
		List<Teacher> results = new ArrayList<Teacher>();
		try {
			results = teacherRepository.findAll();
		} catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}
		return results;
	}
	
	@SuppressWarnings({ "unchecked", "finally" })
	public List<Teacher> readCurrentTeachers(int branchid) {
		Transaction transaction = null;
		List<Teacher> results = new ArrayList<Teacher>();
		try {
			Session session = HibernateUtil.openCurrentSession();
			transaction = session.beginTransaction();
			results = (List<Teacher>) session.createQuery("From Teacher where currentemployee = 1 AND branchid='"+branchid+"'")
					.list();
			transaction.commit();
		} catch (Exception hibernateException) {
			transaction.rollback();
			log.error(hibernateException.getMessage(), hibernateException);
			
			hibernateException.printStackTrace();
		} finally {
				HibernateUtil.closeSession();
			return results;
		}
	}
	
	public Teacher readUniqueObject(long id) {
		Transaction transaction = null;
		Teacher employee = new Teacher();

		try {
			Session session = HibernateUtil.openCurrentSession();
			transaction = session.beginTransaction();
			Query query = session.createQuery("From Teacher as employee where employee.tid=" + id);
			employee = (Teacher) query.uniqueResult();
			transaction.commit();
		} catch (Exception hibernateException) {
			transaction.rollback();
			log.error(hibernateException.getMessage(), hibernateException);
			
			hibernateException.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}

		return employee;
	}

	public Teacher update(Teacher employee) {
		Transaction transaction = null;
		try {
			Session session = HibernateUtil.openCurrentSession();
            //this.session = sessionFactory.openCurrentSession();
            transaction = session.beginTransaction();
            session.update(employee);
            transaction.commit();
            
        } catch (Exception hibernateException) {
        	transaction.rollback();
        	log.error(hibernateException.getMessage(), hibernateException);
            
            hibernateException.printStackTrace();
        } finally {
    			HibernateUtil.closeSession();
            return employee;
        }
	}

	public void deleteMultiple(List ids) {
		Transaction transaction = null;
		try {
			Session session = HibernateUtil.openCurrentSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("delete from Teacher where tid IN (:ids)");
            query.setParameterList("ids", ids);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception hibernateException) {
        	transaction.rollback();
        	log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
        }finally {
			HibernateUtil.closeSession();
		}
		
	}

	@SuppressWarnings("unchecked")
	public int getNoOfEmployees(int branchId) {
		Transaction transaction = null;
		List<Teacher> results = new ArrayList<Teacher>();
		int noOfRecords = 0;
		try {
			Session session = HibernateUtil.openCurrentSession();
			transaction = session.beginTransaction();
			results = (List<Teacher>) session.createQuery("From Teacher where branchid="+branchId)
					.list();
			noOfRecords = results.size();
			transaction.commit();
		} catch (Exception hibernateException) {
			transaction.rollback(); 
			log.error(hibernateException.getMessage(), hibernateException);
			
			hibernateException.printStackTrace();
		} finally {
				HibernateUtil.closeSession();
			return noOfRecords;
		}
	}

	public List<Teacher> readListOfEmployeesByName(String staffName, int branchId) {
		Transaction transaction = null;
		List<Teacher> employee = new ArrayList<Teacher>();
		try {
			Session session = HibernateUtil.openCurrentSession();
			transaction = session.beginTransaction();
			
			List<Paybasic> payList = session.createQuery("From Paybasic").list();
			List tidList = new ArrayList<>();
			tidList.add(0);
			for (Paybasic paybasic : payList) {
				tidList.add(paybasic.getTeacher().getTid());
			}
			Query query = session.createQuery("From Teacher where teachername='"+staffName+"' and branchid="+branchId+" and tid NOT IN (:basicPayList)");
			query.setParameterList("basicPayList", tidList);
			employee = query.getResultList();
			
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			log.error(e.getMessage(), e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return employee;
	}
	
	public List<Teacher> readListOfEmployeesByDepartment(String staffDepartment, int branchId) {
		Transaction transaction = null;
		List<Teacher> employee = new ArrayList<Teacher>();
		try {
			Session session = HibernateUtil.openCurrentSession();
			transaction = session.beginTransaction();
			
			List<Paybasic> payList = session.createQuery("From Paybasic").list();
			List tidList = new ArrayList<>();
			tidList.add(0);
			for (Paybasic paybasic : payList) {
				tidList.add(paybasic.getTeacher().getTid());
			}
			Query query = session.createQuery("From Teacher where department='"+staffDepartment+"' and branchid="+branchId+" and tid NOT IN (:basicPayList)");
			query.setParameterList("basicPayList", tidList);
			employee = query.getResultList();
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			log.error(e.getMessage(), e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return employee;
	}

	public List<String> getEmployeeExternalId() {
		Transaction transaction = null;
		List<String> employeeExtId = new ArrayList<String>();
		try {
			Session session = HibernateUtil.openCurrentSession();
			transaction = session.beginTransaction();
			employeeExtId = session.createQuery("select teacherexternalid from Teacher").list();
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			log.error(e.getMessage(), e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return employeeExtId;
	}

	public void delete(Teacher employee) {
		Transaction transaction = null;
		try {
			Session session = HibernateUtil.openCurrentSession();
            transaction = session.beginTransaction();
            session.delete(employee);
            transaction.commit();
            
        } catch (Exception hibernateException) {
        	transaction.rollback();
        	log.error(hibernateException.getMessage(), hibernateException);
            
            hibernateException.printStackTrace();
        }finally {
			HibernateUtil.closeSession();
		}
	}

	public Teacher getEmployeeDetails(String userName) {
		Transaction transaction = null;
		Teacher employee = new Teacher();
		try {
			Session session = HibernateUtil.openCurrentSession();
			transaction = session.beginTransaction();
			Query query = session.createQuery("From Teacher as employee where employee.teacherexternalid='"+userName+"'");
			employee = (Teacher) query.uniqueResult();
			transaction.commit();
		} catch (Exception hibernateException) {
			transaction.rollback();
			log.error(hibernateException.getMessage(), hibernateException);
			
			hibernateException.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return employee;
	}

	public List<Paybasic> readListOfEmployeesBasicPayDetails(int branchId) {
		Transaction transaction = null;
		List<Paybasic> payList = new ArrayList<Paybasic>();
		try {
			Session session = HibernateUtil.openCurrentSession();
			transaction = session.beginTransaction();
			payList = session.createQuery("From Paybasic where branchid="+branchId).list();
			transaction.commit();
		} catch (Exception hibernateException) { 
			transaction.rollback(); 
			log.error(hibernateException.getMessage(), hibernateException);
			
			hibernateException.printStackTrace();
		} finally {
				HibernateUtil.closeSession();
			return payList;
		}
	}

}

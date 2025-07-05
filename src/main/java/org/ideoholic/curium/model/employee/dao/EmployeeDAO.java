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
	
	@Transactional
	public List<Teacher> readCurrentTeachers(int branchid) {
		List<Teacher> results = new ArrayList<>();
		try {
			results = teacherRepository.findByCurrentemployeeAndBranchid("1", branchid);
		} catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
			throw hibernateException;
		}
		return results;
	}
	
	@Transactional
	public Teacher readUniqueObject(long id) {
		Teacher employee = null;

		try {
			employee = teacherRepository.findById(Long.valueOf(id).intValue()).orElse(new Teacher());
		} catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);

			hibernateException.printStackTrace();
			throw hibernateException;
		}

		return employee;
	}

	@Transactional
	public Teacher update(Teacher employee) {
		try {
			teacherRepository.save(employee);
        } catch (Exception hibernateException) {
        	log.error(hibernateException.getMessage(), hibernateException);
            
            hibernateException.printStackTrace();
            throw hibernateException;
        }
		return employee;
	}

	@Transactional
	public void deleteMultiple(List<Integer> ids) {
		try {
            teacherRepository.deleteAllById(ids);
        } catch (Exception hibernateException) {
        	log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            throw hibernateException;
		}
	}

	@Transactional
	public List<Teacher> readListOfEmployeesByName(String staffName, int branchId) {
		List<Teacher> employee = new ArrayList<Teacher>();
		try {
			List<Paybasic> payList = payBasicRepo.findAll();
			List<Integer> tidList = new ArrayList<>();
			tidList.add(0);
			for (Paybasic paybasic : payList) {
				tidList.add(paybasic.getTeacher().getTid());
			}
			employee = teacherRepository.findByTeachernameAndBranchidAndTidNotIn(staffName, branchId, tidList);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			e.printStackTrace();
			throw e;
		}
		return employee;
	}

	@Transactional
	public List<Teacher> readListOfEmployeesByDepartment(String staffDepartment, int branchId) {
		List<Teacher> employee = new ArrayList<>();
		try {
			List<Paybasic> payList = payBasicRepo.findAll();
			List<Integer> tidList = new ArrayList<>();
			tidList.add(0);
			for (Paybasic paybasic : payList) {
				tidList.add(paybasic.getTeacher().getTid());
			}
			employee = teacherRepository.findByDepartmentAndBranchidAndTidNotIn(staffDepartment, branchId, tidList);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			e.printStackTrace();
			throw e;
		}
		return employee;
	}

	@Transactional
	public List<String> getEmployeeExternalId() {
		List<String> employeeExtId = new ArrayList<>();
		try {
			employeeExtId = teacherRepository.fetchTeacherexternalid();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			e.printStackTrace();
			throw e;
		}
		return employeeExtId;
	}

	@Transactional
	public void delete(Teacher employee) {
		try {
            teacherRepository.delete(employee);
        } catch (Exception hibernateException) {
        	log.error(hibernateException.getMessage(), hibernateException);
            
            hibernateException.printStackTrace();
            throw hibernateException;
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

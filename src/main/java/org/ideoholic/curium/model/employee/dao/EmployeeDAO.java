package org.ideoholic.curium.model.employee.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.ideoholic.curium.model.employee.dto.Teacher;
import org.ideoholic.curium.model.hr.dto.Paybasic;
import org.ideoholic.curium.repositories.PaybasicRepository;
import org.ideoholic.curium.repositories.TeacherRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmployeeDAO {
	
	private final PaybasicRepository payBasicRepo;
	private final TeacherRepository teacherRepository;

	@Transactional
	public boolean create(Teacher employee) {
		boolean result = false;
		try {
			// Query<Teacher> queryTeacher = session.createQuery("from Teacher where branchid = "+employee.getBranchid()+" order by id DESC");
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
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return result;
	}

	@Transactional
	public List<Teacher> readListOfObjects(int branchId) {
		List<Teacher> results = new ArrayList<Teacher>();
		try {
			// results = (List<Teacher>) session.createQuery("From Teacher where branchid="+branchId).list();
			results = teacherRepository.findByCurrentemployeeAndBranchid("1",branchId);
		} catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return results;
	}
	
	@Transactional
	public List<Teacher> readListOfEmployeesBasicPay(int branchId) {
		List<Teacher> results = new ArrayList<>();
		try {
			// List<Paybasic> payList = session.createQuery("From Paybasic").list();
			List<Paybasic> payList = payBasicRepo.findAll();
			List<Integer> tidList = new ArrayList<>();
			tidList.add(0);
			for (Paybasic paybasic : payList) {
				tidList.add(paybasic.getTeacher().getTid());
			}
			// Query query = session.createQuery("From Teacher where branchid="+branchId+" and tid NOT IN (:basicPayList)");
			results = teacherRepository.findByBranchidAndTidNotIn(branchId, tidList);
		} catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return results;
	}

	@Transactional
	public List<Teacher> readListOfObjects() {
		List<Teacher> results = new ArrayList<Teacher>();
		try {
			// results = (List<Teacher>) session.createQuery("From Teacher").list();
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
			// results = (List<Teacher>) session.createQuery("From Teacher where currentemployee = 1 AND branchid='"+branchid+"'").list();
			results = teacherRepository.findByCurrentemployeeAndBranchid("1", branchid);
		} catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return results;
	}
	
	@Transactional
	public Teacher readUniqueObject(long id) {
		Teacher employee = null;

		try {
			// Query query = session.createQuery("From Teacher as employee where employee.tid=" + id);
			employee = teacherRepository.findById(Long.valueOf(id).intValue()).orElse(new Teacher());
		} catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);

			hibernateException.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
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
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
		return employee;
	}

	@Transactional
	public void deleteMultiple(List<Integer> ids) {
		try {
			// Query query = session.createQuery("delete from Teacher where tid IN (:ids)");
            teacherRepository.deleteAllById(ids);
        } catch (Exception hibernateException) {
        	log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
	}

	@Transactional
	public List<Teacher> readListOfEmployeesByName(String staffName, int branchId) {
		List<Teacher> employee = new ArrayList<Teacher>();
		try {
			// List<Paybasic> payList = session.createQuery("From Paybasic").list();
			List<Paybasic> payList = payBasicRepo.findAll();
			List<Integer> tidList = new ArrayList<>();
			tidList.add(0);
			for (Paybasic paybasic : payList) {
				tidList.add(paybasic.getTeacher().getTid());
			}
			// Query query = session.createQuery("From Teacher where teachername='"+staffName+"' and branchid="+branchId+" and tid NOT IN (:basicPayList)");
			employee = teacherRepository.findByTeachernameAndBranchidAndTidNotIn(staffName, branchId, tidList);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return employee;
	}

	@Transactional
	public List<Teacher> readListOfEmployeesByDepartment(String staffDepartment, int branchId) {
		List<Teacher> employee = new ArrayList<>();
		try {
			// List<Paybasic> payList = session.createQuery("From Paybasic").list();
			List<Paybasic> payList = payBasicRepo.findAll();
			List<Integer> tidList = new ArrayList<>();
			tidList.add(0);
			for (Paybasic paybasic : payList) {
				tidList.add(paybasic.getTeacher().getTid());
			}
			// Query query = session.createQuery("From Teacher where department='"+staffDepartment+"' and branchid="+branchId+" and tid NOT IN (:basicPayList)");
			employee = teacherRepository.findByDepartmentAndBranchidAndTidNotIn(staffDepartment, branchId, tidList);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return employee;
	}

	@Transactional
	public List<String> getEmployeeExternalId() {
		List<String> employeeExtId = new ArrayList<>();
		try {
			// employeeExtId = session.createQuery("select teacherexternalid from Teacher").list();
			employeeExtId = teacherRepository.fetchTeacherexternalid();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
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
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
	}

	public Teacher getEmployeeDetails(String userName) {
		Teacher employee = null;
		try {
			// Query query = session.createQuery("From Teacher as employee where employee.teacherexternalid='"+userName+"'");
			employee = teacherRepository.findByTeacherexternalid(userName).orElse(new Teacher());
		} catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);
			
			hibernateException.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return employee;
	}

	public List<Paybasic> readListOfEmployeesBasicPayDetails(int branchId) {
		List<Paybasic> payList = new ArrayList<>();
		try {
			// payList = session.createQuery("From Paybasic where branchid="+branchId).list();
			payList = payBasicRepo.findByBranchid(branchId);
		} catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);

			hibernateException.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return payList;
	}

	public void archiveMultipleEmployee(List<Integer> ids) {
		try {
			List<Teacher> archiveTeachers = teacherRepository.findAllById(ids);
			
			for (Teacher teacher : archiveTeachers) {
				teacher.setCurrentemployee("0");
				teacherRepository.save(teacher);
			}
			//Query query = session.createQuery("update Teacher set currentemployee = 0  where id IN (:ids)");
		} catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);

			hibernateException.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		
	}

	public List<Teacher> readListOfEmployeeArchive(int branchId) {
		List<Teacher> results = new ArrayList<Teacher>();

		try {
			results = teacherRepository.findByCurrentemployeeAndBranchid("0", branchId);
			//results = (List<Teacher>) session.createQuery("FROM Teacher t where t.currentemployee = 0 and branchid="+branchId+"").setCacheable(true).setCacheRegion("commonregion").list();
		} catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);

			hibernateException.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return results;

	}

	public void restoreMultipleEmployee(List<Integer> ids) {
		try {
			
			
			List<Teacher> restoreTeachers = teacherRepository.findAllById(ids);
			
			for (Teacher teacher : restoreTeachers) {
				teacher.setCurrentemployee("1");
				teacherRepository.save(teacher);
			}
			//Query query = session.createQuery("update Teacher set currentemployee = 1  where id IN (:ids)");
		} catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);

			hibernateException.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		
	}

}

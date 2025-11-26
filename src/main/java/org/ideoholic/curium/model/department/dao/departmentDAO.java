package org.ideoholic.curium.model.department.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.ideoholic.curium.model.department.dto.Department;
import org.ideoholic.curium.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class departmentDAO {
    @Autowired
    private DepartmentRepository departmentRepository;


	@Transactional
    public Department create(Department department) {

		try {
           departmentRepository.save(department);
            
        } catch (Exception hibernateException)  {
            log.error(hibernateException.getMessage(), hibernateException);

            hibernateException.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();

        }
        return department;
	}



	@Transactional
	public List<Department> readListOfObjects(int branchId) {

		List<Department> results = new ArrayList<>();
		try {

			results = departmentRepository.findByBranchid(branchId);
		} catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);

			hibernateException.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}

		return results;

	}

    @Transactional
	public boolean  deleteMultiple(List<Integer> ids) {
        boolean result = false;
		try {
            departmentRepository.deleteAllById(ids);
            result = true;
        } catch (Exception hibernateException) { ;
            log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
		return result;
	}
}

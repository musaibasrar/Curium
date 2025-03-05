package org.ideoholic.curium.model.department.dao;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.hibernate.HibernateException;
import org.ideoholic.curium.exceptions.CustomResponseException;
import org.ideoholic.curium.util.Session;
import org.hibernate.SessionFactory;
import org.ideoholic.curium.util.Session.Transaction;
import org.hibernate.query.Query;

import org.ideoholic.curium.model.department.dto.Department;
import org.ideoholic.curium.util.HibernateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

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
            throw hibernateException;

        }
        return department;
	}



	@Transactional
    public List<Department> readListOfObjects(int branchId) {
		
		List<Department> results = new ArrayList<Department>();
        try {
            
            results = departmentRepository.finadAllDepartment(branchId);
        } catch (Exception hibernateException) {
            log.error(hibernateException.getMessage(), hibernateException);
            
            hibernateException.printStackTrace();
            throw hibernateException;
        }

            return results;

	}


	public boolean  deleteMultiple(List ids) {
        boolean result = false;
        Transaction transaction =  null;
        Session session = HibernateUtil.openCurrentSession();
		try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("delete from Department where depid IN (:ids)");
            query.setParameterList("ids", ids);
            query.executeUpdate();
            transaction.commit();
            result= true;
        } catch (Exception hibernateException) { transaction.rollback();
            log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
        }finally {
			HibernateUtil.closeSession();
		}
		return result;
	}
}

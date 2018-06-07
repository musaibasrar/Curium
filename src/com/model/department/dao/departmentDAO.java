package com.model.department.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.model.department.dto.Department;
import com.model.feescategory.dto.Feescategory;
import com.util.HibernateUtil;

public class departmentDAO {

	
	Session session = null;
    /**
     * * Hibernate Session Variable
     */
    Transaction transaction = null;
    /**
     * * Hibernate Transaction Variable
     */
  
    SessionFactory sessionFactory;
    

	public departmentDAO() {
		session = HibernateUtil.openSession();
	}


	@SuppressWarnings("finally")
	public Department create(Department department) {
		try {
            //this.session = sessionFactory.openCurrentSession();
            transaction = session.beginTransaction();
            session.save(department);


            transaction.commit();
            
        } catch (HibernateException hibernateException) {
            transaction.rollback();
            hibernateException.printStackTrace();
        } finally {
            session.close();
            return department;
        }
	}


	@SuppressWarnings({ "unchecked", "finally" })
	public List<Department> readListOfObjects(int branchId) {
		
		List<Department> results = new ArrayList<Department>();
        try {
            
            transaction = session.beginTransaction();
            results = (List<Department>) session.createQuery("From Department where branchid="+branchId).list();
            transaction.commit();
        } catch (HibernateException hibernateException) {
            transaction.rollback();
            hibernateException.printStackTrace();
        } finally {
            //session.close();
            return results;
        }
	}


	public void deleteMultiple(List ids) {
		try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("delete from Department where depid IN (:ids)");
            query.setParameterList("ids", ids);
            query.executeUpdate();
            transaction.commit();
        } catch (HibernateException hibernateException) {
            hibernateException.printStackTrace();
        }
		
	}
}

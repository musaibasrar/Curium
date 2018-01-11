package com.model.employee.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.model.employee.dto.Teacher;
import com.model.position.dto.Position;
import com.model.student.dto.Student;
import com.util.HibernateUtil;

public class EmployeeDAO {

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

	public EmployeeDAO() {
		session = HibernateUtil.openSession();
	}

	@SuppressWarnings("finally")
	public Teacher create(Teacher employee) {
		try {
			// this.session = sessionFactory.openCurrentSession();
			transaction = session.beginTransaction();
			session.save(employee);

			transaction.commit();

		} catch (HibernateException hibernateException) {
			transaction.rollback();
			hibernateException.printStackTrace();
		} finally {
			session.close();
			return employee;
		}
	}

	@SuppressWarnings({ "unchecked", "finally" })
	public List<Teacher> readListOfObjects() {

		List<Teacher> results = new ArrayList<Teacher>();
		try {

			transaction = session.beginTransaction();
			results = (List<Teacher>) session.createQuery("From Teacher")
					.list();
			transaction.commit();
		} catch (HibernateException hibernateException) {
			transaction.rollback();
			hibernateException.printStackTrace();
		} finally {
			session.close();
			return results;
		}
	}

	public Teacher readUniqueObject(long id) {

		Teacher employee = new Teacher();

		try {

			transaction = session.beginTransaction();
			Query query = session.createQuery("From Teacher as employee where employee.tid=" + id);
			employee = (Teacher) query.uniqueResult();
			transaction.commit();
		} catch (HibernateException hibernateException) {
			transaction.rollback();
			hibernateException.printStackTrace();
		} 

		return employee;
	}

	public Teacher update(Teacher employee) {
		try {
            //this.session = sessionFactory.openCurrentSession();
            transaction = session.beginTransaction();
            session.update(employee);
            transaction.commit();
            
        } catch (HibernateException hibernateException) {
            transaction.rollback();
            hibernateException.printStackTrace();
        } finally {
            //session.close();
            return employee;
        }
	}

	public void deleteMultiple(List ids) {
		try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("delete from Teacher where tid IN (:ids)");
            query.setParameterList("ids", ids);
            query.executeUpdate();
            transaction.commit();
        } catch (HibernateException hibernateException) {
            hibernateException.printStackTrace();
        }
		
	}

	@SuppressWarnings("unchecked")
	public int getNoOfEmployees() {

		List<Teacher> results = new ArrayList<Teacher>();
		int noOfRecords = 0;
		try {

			transaction = session.beginTransaction();
			results = (List<Teacher>) session.createQuery("From Teacher")
					.list();
			noOfRecords = results.size();
			transaction.commit();
		} catch (HibernateException hibernateException) {
			transaction.rollback();
			hibernateException.printStackTrace();
		} finally {
			// session.close();
			return noOfRecords;
		}
	}

}

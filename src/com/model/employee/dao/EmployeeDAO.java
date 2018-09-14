package com.model.employee.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.model.employee.dto.Organizersdetails;
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
		session = HibernateUtil.openCurrentSession();
	}

	@SuppressWarnings("finally")
	public boolean create(Teacher employee) {
		boolean result = false;
		try {
			transaction = session.beginTransaction();
			session.save(employee);
			transaction.commit();
			result = true;
		} catch (HibernateException hibernateException) {transaction.rollback();
			transaction.rollback();
			hibernateException.printStackTrace();
		} finally {
			//session.close();
		}
		return result;
	}

	@SuppressWarnings({ "unchecked", "finally" })
	public List<Teacher> readListOfObjects(int branchId) {

		List<Teacher> results = new ArrayList<Teacher>();
		try {

			transaction = session.beginTransaction();
			results = (List<Teacher>) session.createQuery("From Teacher where branchid="+branchId)
					.list();
			transaction.commit();
		} catch (HibernateException hibernateException) {transaction.rollback();
			transaction.rollback();
			hibernateException.printStackTrace();
		} finally {
			//session.close();
			return results;
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
		} catch (HibernateException hibernateException) {transaction.rollback();
			transaction.rollback();
			hibernateException.printStackTrace();
		} finally {
			//session.close();
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
		} catch (HibernateException hibernateException) {transaction.rollback();
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
            
        } catch (HibernateException hibernateException) {transaction.rollback();
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
        } catch (HibernateException hibernateException) {transaction.rollback();
            hibernateException.printStackTrace();
        }
		
	}

	@SuppressWarnings("unchecked")
	public int getNoOfEmployees(int branchId) {

		List<Teacher> results = new ArrayList<Teacher>();
		int noOfRecords = 0;
		try {

			transaction = session.beginTransaction();
			results = (List<Teacher>) session.createQuery("From Teacher where branchid="+branchId)
					.list();
			noOfRecords = results.size();
			transaction.commit();
		} catch (HibernateException hibernateException) {transaction.rollback();
			transaction.rollback();
			hibernateException.printStackTrace();
		} finally {
			 //session.close();
			return noOfRecords;
		}
	}

	public List<Teacher> readListOfEmployeesByName(String staffName, int branchId) {
		List<Teacher> employee = new ArrayList<Teacher>();
		try {
			transaction = session.beginTransaction();
			employee = session.createQuery("From Teacher where teachername='"+staffName+"' and branchid="+branchId).list();
			transaction.commit();
		} catch (Exception e) {transaction.rollback();
			e.printStackTrace();
		}finally{
			//session.close();
		}
		return employee;
	}
	
	public List<Teacher> readListOfEmployeesByDepartment(String staffDepartment, int branchId) {
		List<Teacher> employee = new ArrayList<Teacher>();
		try {
			transaction = session.beginTransaction();
			employee = session.createQuery("From Teacher where department = '"+staffDepartment+"' and branchid="+branchId).list();
			transaction.commit();
		} catch (Exception e) {transaction.rollback();
			e.printStackTrace();
		}finally{
			//session.close();
		}
		return employee;
	}

	public List<String> getEmployeeExternalId() {
		List<String> employeeExtId = new ArrayList<String>();
		try {
			transaction = session.beginTransaction();
			employeeExtId = session.createQuery("select teacherexternalid from Teacher").list();
			transaction.commit();
		} catch (Exception e) {transaction.rollback();
			e.printStackTrace();
		}finally{
			//session.close();
		}
		return employeeExtId;
	}

	public void delete(Teacher employee) {
		
		try {
            transaction = session.beginTransaction();
            session.delete(employee);
            transaction.commit();
            
        } catch (HibernateException hibernateException) {transaction.rollback();
            transaction.rollback();
            hibernateException.printStackTrace();
        } finally {
            //session.close();
        }
		
	}

	public Teacher getEmployeeDetails(String userName) {
		
		Teacher employee = new Teacher();
		try {
			transaction = session.beginTransaction();
			Query query = session.createQuery("From Teacher as employee where employee.teacherexternalid='"+userName+"'");
			employee = (Teacher) query.uniqueResult();
			transaction.commit();
		} catch (HibernateException hibernateException) {transaction.rollback();
			transaction.rollback();
			hibernateException.printStackTrace();
		} 
		return employee;
	}

    public boolean create(Organizersdetails orgDet) {
        boolean result = false;
        try {
                transaction = session.beginTransaction();
                session.save(orgDet);
                transaction.commit();
                result = true;
        } catch (HibernateException hibernateException) {transaction.rollback();
                transaction.rollback();
                hibernateException.printStackTrace();
        } finally {
                //session.close();
        }
        return result;
}

    public List<Organizersdetails> readListOfOrganizers() {

        List<Organizersdetails> results = new ArrayList<Organizersdetails>();
        try {

                transaction = session.beginTransaction();
                results = (List<Organizersdetails>) session.createQuery("From Organizersdetails")
                                .list();
                transaction.commit();
        } catch (HibernateException hibernateException) {transaction.rollback();
                transaction.rollback();
                hibernateException.printStackTrace();
        } finally {
                //session.close();
                return results;
        }
}

    public Organizersdetails readOrganizersdetails(long id) {

        Organizersdetails orgDetails = new Organizersdetails();

        try {
                transaction = session.beginTransaction();
                Query query = session.createQuery("From Organizersdetails where idorganizersdetails=" + id);
                orgDetails = (Organizersdetails) query.uniqueResult();
                transaction.commit();
        } catch (HibernateException hibernateException) {transaction.rollback();
                transaction.rollback();
                hibernateException.printStackTrace();
        } 

        return orgDetails;
}

    public Organizersdetails update(Organizersdetails orgDet) {
        try {
    //this.session = sessionFactory.openCurrentSession();
    transaction = session.beginTransaction();
    session.update(orgDet);
    transaction.commit();
    
} catch (HibernateException hibernateException) {transaction.rollback();
    transaction.rollback();
    hibernateException.printStackTrace();
} finally {
    //session.close();
    return orgDet;
}
}

    public void deleteMultipleOrganizers(List ids) {
        try {
    transaction = session.beginTransaction();
    Query query = session.createQuery("delete from Organizersdetails where idorganizersdetails IN (:ids)");
    query.setParameterList("ids", ids);
    query.executeUpdate();
    transaction.commit();
} catch (HibernateException hibernateException) {transaction.rollback();
    hibernateException.printStackTrace();
}
        
}

}

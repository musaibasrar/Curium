package com.model.appointment.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.model.appointment.dto.Appointment;
import com.model.examdetails.dto.Examschedule;
import com.model.feescollection.dto.Receiptinfo;
import com.model.mess.card.dto.Card;
import com.model.parents.dto.Parents;
import com.model.query.dto.ParentQuery;
import com.model.student.dto.Student;
import com.util.DateUtil;
import com.util.HibernateUtil;
import com.util.Session;
import com.util.Session.Transaction;

public class AppointmentDAO {
	Session session = null;
	/**
	 * * Hibernate Session Variable
	 */
	Transaction transaction = null;
	/**
	 * * Hibernate Transaction Variable
	 */
	SessionFactory sessionFactory;
	
	private static final Logger logger = LogManager.getLogger(AppointmentDAO.class);

	public AppointmentDAO() {
		session = HibernateUtil.openCurrentSession();
	}


		public String addAppointment(Appointment appointment) {
		
			String resultString = null;
			try {
					transaction = session.beginTransaction();
					
		
					 Query queryAppointment = session.createQuery("from Appointment where branchid = "+appointment.getBranchid()+" order by id DESC");
					 	List<Appointment> appointmentList = queryAppointment.list();
					 	
					 	if(appointmentList.size() > 0) {
					 		String appNo = appointmentList.get(0).getExternalid();
					 		String splitAppNo = appNo.substring(2);
					 		
					 		if(Integer.parseInt(splitAppNo) < 1000) {
					 			appointment.setExternalid("AP"+String.format("%03d", Integer.parseInt(splitAppNo)+1));
					 		}else {
					 			appointment.setExternalid("AP"+String.format("%03d", 1));
					 		}
					 	}else {
					 		appointment.setExternalid("AP"+String.format("%03d", 1));
					 	}
					 	
					 	
					session.save(appointment);
					transaction.commit();
					resultString = appointment.getExternalid();
			} catch (Exception e) { transaction.rollback(); logger.error(e);
				e.printStackTrace();
			}finally {
				HibernateUtil.closeSession();
			}
			return resultString;
		}


		public List<Appointment> readListOfObjectsPagination(int offset,
				int noOfRecords, int branchId) {
			
			List<Appointment> results = new ArrayList<Appointment>();

			try {
				
				transaction = session.beginTransaction();
				Query query = session.createQuery("From Appointment as appointment where appointment.branchid = "+branchId+" order by appointment.id desc").setCacheable(true).setCacheRegion("commonregion");
				query.setFirstResult(offset);   
				query.setMaxResults(noOfRecords);
				results = query.getResultList();
				transaction.commit();
				

			} catch (Exception hibernateException) {  transaction.rollback(); logger.error(hibernateException);
				hibernateException.printStackTrace();

			} finally {
					HibernateUtil.closeSession();
				return results;
			}
		}
		
		public int getNoOfRecords(int branchId) {
			List<Appointment> results = new ArrayList<Appointment>();
			int noOfRecords = 0;
			try {
				// this.session =
				// HibernateUtil.getSessionFactory().openCurrentSession();
				transaction = session.beginTransaction();

				results = (List<Appointment>) session.createQuery("From Appointment where branchid="+branchId).setCacheable(true).setCacheRegion("commonregion")
						.list();
				noOfRecords = results.size();
				logger.info("The size of list is:::::::::::::::::::::::::::::::::::::::::: "
								+ noOfRecords);
				transaction.commit();

			} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
				
				hibernateException.printStackTrace();

			} finally {
					HibernateUtil.closeSession();
				return noOfRecords;
			}
		}
		
		public int getNoOfRecords() {
			
			List<Appointment> results = new ArrayList<Appointment>();
			int noOfRecords = 0;
			try {
				// this.session =
				// HibernateUtil.getSessionFactory().openCurrentSession();
				transaction = session.beginTransaction();

				results = (List<Appointment>) session.createQuery("From Appointment where status != 'Cancelled'").setCacheable(true).setCacheRegion("commonregion")
						.list();
				noOfRecords = results.size();
				logger.info("The size of list is:::::::::::::::::::::::::::::::::::::::::: "
								+ noOfRecords);
				transaction.commit();

			} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
				
				hibernateException.printStackTrace();

			} finally {
					HibernateUtil.closeSession();
				return noOfRecords;
			}
		}


		public boolean completeAppointments(List<Integer> appointmentIdsList) {
			
			boolean result = false;
			try {
				transaction = session.beginTransaction();
				
				for (Integer appId : appointmentIdsList) {
					Query query = session.createQuery("update Appointment set status = 'Completed' where id="+appId+"");
					query.executeUpdate();
				}
				
				transaction.commit();
				result = true;
			} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
				hibernateException.printStackTrace();
			}finally {
				HibernateUtil.closeSession();
			 }
			return result;
		}


		public List<Appointment> cancelAppointments(List<Integer> appointmentIdsList) {
			
			List<Appointment> result = new ArrayList<Appointment>();
			try {
				transaction = session.beginTransaction();
				
				for (Integer appId : appointmentIdsList) {
					Appointment app = new Appointment();
					Query query = session.createQuery("update Appointment set status = 'Cancelled' where id="+appId+"");
					query.executeUpdate();
					Query queryApp = session.createQuery("from Appointment where id="+appId+"");
					app = (Appointment) queryApp.uniqueResult();
					result.add(app);
				}
				
				transaction.commit();
			} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
				hibernateException.printStackTrace();
			}finally {
				HibernateUtil.closeSession();
			 }
			return result;
		}


		public int getNoOfRecordsMonthly(String fromDate, String toDate) {

			List<Appointment> results = new ArrayList<Appointment>();
			int noOfRecords = 0;
			try {
				// this.session =
				// HibernateUtil.getSessionFactory().openCurrentSession();
				transaction = session.beginTransaction();

				Query query = session.createQuery("From Appointment where (appointmentdate between '"+fromDate+"' and '"+toDate+"')  and status !='Cancelled'").setCacheable(true).setCacheRegion("commonregion");
				results = query.getResultList();
				noOfRecords = results.size();
				logger.info("The size of list is:::::::::::::::::::::::::::::::::::::::::: "
								+ noOfRecords);
				transaction.commit();

			} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
				
				hibernateException.printStackTrace();

			} finally {
					HibernateUtil.closeSession();
				return noOfRecords;
			}
		
					
		}


		public int getNoOfRecordsCompletedAppointments() {
			
			List<Appointment> results = new ArrayList<Appointment>();
			int noOfRecords = 0;
			try {
				// this.session =
				// HibernateUtil.getSessionFactory().openCurrentSession();
				transaction = session.beginTransaction();

				results = (List<Appointment>) session.createQuery("From Appointment where status = 'Completed'").setCacheable(true).setCacheRegion("commonregion")
						.list();
				noOfRecords = results.size();
				logger.info("The size of list is:::::::::::::::::::::::::::::::::::::::::: "
								+ noOfRecords);
				transaction.commit();

			} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
				
				hibernateException.printStackTrace();

			} finally {
					HibernateUtil.closeSession();
				return noOfRecords;
			}
		}


		public int getNoOfRecordsIncompleteAppointments() {
			
			List<Appointment> results = new ArrayList<Appointment>();
			int noOfRecords = 0;
			try {
				// this.session =
				// HibernateUtil.getSessionFactory().openCurrentSession();
				transaction = session.beginTransaction();

				results = (List<Appointment>) session.createQuery("From Appointment where status = 'Scheduled' ").setCacheable(true).setCacheRegion("commonregion")
						.list();
				noOfRecords = results.size();
				logger.info("The size of list is:::::::::::::::::::::::::::::::::::::::::: "
								+ noOfRecords);
				transaction.commit();

			} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
				
				hibernateException.printStackTrace();

			} finally {
					HibernateUtil.closeSession();
				return noOfRecords;
			}
		}


		public int getNoOfRecordsTodayCompletedAppointments() {
			
			List<Appointment> results = new ArrayList<Appointment>();
			int noOfRecords = 0;
			try {
				// this.session =
				// HibernateUtil.getSessionFactory().openCurrentSession();
				transaction = session.beginTransaction();

				results = (List<Appointment>) session.createQuery("From Appointment where status = 'Completed' and createddate = CURDATE()").setCacheable(true).setCacheRegion("commonregion")
						.list();
				noOfRecords = results.size();
				logger.info("The size of list is:::::::::::::::::::::::::::::::::::::::::: "
								+ noOfRecords);
				transaction.commit();

			} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
				
				hibernateException.printStackTrace();

			} finally {
					HibernateUtil.closeSession();
				return noOfRecords;
			}
		}


		public int getNoOfRecordsTodayIncompleteAppointments() {
			
			List<Appointment> results = new ArrayList<Appointment>();
			int noOfRecords = 0;
			try {
				// this.session =
				// HibernateUtil.getSessionFactory().openCurrentSession();
				transaction = session.beginTransaction();

				results = (List<Appointment>) session.createQuery("From Appointment where status = 'Scheduled'  and createddate = CURDATE()").setCacheable(true).setCacheRegion("commonregion")
						.list();
				noOfRecords = results.size();
				logger.info("The size of list is:::::::::::::::::::::::::::::::::::::::::: "
								+ noOfRecords);
				transaction.commit();

			} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
				
				hibernateException.printStackTrace();

			} finally {
					HibernateUtil.closeSession();
				return noOfRecords;
			}
		}


		public List<Appointment> generateAppointmentsReport(String query) {
			
	        List<Appointment> results = new ArrayList<Appointment>();
	        
	        try {
	                transaction = session.beginTransaction();
	                results = (List<Appointment>) session.createQuery(query).setCacheable(true).setCacheRegion("commonregion").list();
	                transaction.commit();
	        } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
	                
	                hibernateException.printStackTrace();

	        } finally {
	    			HibernateUtil.closeSession();
	        }
	        return results;
}

}

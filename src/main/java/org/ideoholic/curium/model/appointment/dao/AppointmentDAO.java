package org.ideoholic.curium.model.appointment.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.query.Query;
import org.ideoholic.curium.model.appointment.dto.Appointment;
import org.ideoholic.curium.util.HibernateUtil;
import org.ideoholic.curium.util.Session;
import org.ideoholic.curium.util.Session.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AppointmentDAO {

	@Autowired
	private AppointmentRepository appoinmentRepo;

	@Transactional
	public String addAppointment(Appointment appointment) {
		String resultString = null;
		try {

			List<Appointment> appointmentList = appoinmentRepo.findByBranchidOrderByIdDesc(appointment.getBranchid());

			if (appointmentList.size() > 0) {
				String appNo = appointmentList.get(0).getExternalid();
				String splitAppNo = appNo.substring(2);

				if (Integer.parseInt(splitAppNo) < 1000) {
					appointment.setExternalid("AP" + String.format("%03d", Integer.parseInt(splitAppNo) + 1));
				} else {
					appointment.setExternalid("AP" + String.format("%03d", 1));
				}
			} else {
				appointment.setExternalid("AP" + String.format("%03d", 1));
			}
			appointment = appoinmentRepo.save(appointment);
			resultString = appointment.getExternalid();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return resultString;
	}


		public List<Appointment> readListOfObjectsPagination(int offset,
				int noOfRecords, int branchId) {
			
			List<Appointment> results = new ArrayList<Appointment>();

			Transaction transaction = null;
			try{
				Session session = HibernateUtil.openCurrentSession();
				
				transaction = session.beginTransaction();
				Query query = session.createQuery("From Appointment as appointment where appointment.branchid = "+branchId+" order by appointment.id desc").setCacheable(true).setCacheRegion("commonregion");
				query.setFirstResult(offset);   
				query.setMaxResults(noOfRecords);
				results = query.getResultList();
				transaction.commit();
				

			} catch (Exception hibernateException) {  transaction.rollback(); log.error(hibernateException.getMessage(), hibernateException);
				hibernateException.printStackTrace();

			} finally {
					HibernateUtil.closeSession();
				return results;
			}
		}
		
		public int getNoOfRecords(int branchId) {
			List<Appointment> results = new ArrayList<Appointment>();
			int noOfRecords = 0;
			Transaction transaction = null;
			try{
				Session session = HibernateUtil.openCurrentSession();
				transaction = session.beginTransaction();

				results = (List<Appointment>) session.createQuery("From Appointment where branchid="+branchId).setCacheable(true).setCacheRegion("commonregion")
						.list();
				noOfRecords = results.size();
				log.info("The size of list is:::::::::::::::::::::::::::::::::::::::::: "
								+ noOfRecords);
				transaction.commit();

			} catch (Exception hibernateException) { transaction.rollback(); log.error(hibernateException.getMessage(), hibernateException);
				
				hibernateException.printStackTrace();

			} finally {
					HibernateUtil.closeSession();
				return noOfRecords;
			}
		}
		
		public int getNoOfRecords() {
			
			List<Appointment> results = new ArrayList<Appointment>();
			int noOfRecords = 0;
			Transaction transaction = null;
			try{
				Session session = HibernateUtil.openCurrentSession();
				transaction = session.beginTransaction();

				results = (List<Appointment>) session.createQuery("From Appointment where status != 'Cancelled'").setCacheable(true).setCacheRegion("commonregion")
						.list();
				noOfRecords = results.size();
				log.info("The size of list is:::::::::::::::::::::::::::::::::::::::::: "
								+ noOfRecords);
				transaction.commit();

			} catch (Exception hibernateException) { transaction.rollback(); log.error(hibernateException.getMessage(), hibernateException);
				
				hibernateException.printStackTrace();

			} finally {
					HibernateUtil.closeSession();
				return noOfRecords;
			}
		}


		public boolean completeAppointments(List<Integer> appointmentIdsList) {
			
			boolean result = false;
			Transaction transaction = null;
			try{
				Session session = HibernateUtil.openCurrentSession();
				transaction = session.beginTransaction();
				
				for (Integer appId : appointmentIdsList) {
					Query query = session.createQuery("update Appointment set status = 'Completed' where id="+appId+"");
					query.executeUpdate();
				}
				
				transaction.commit();
				result = true;
			} catch (Exception hibernateException) { transaction.rollback(); log.error(hibernateException.getMessage(), hibernateException);
				hibernateException.printStackTrace();
			}finally {
				HibernateUtil.closeSession();
			 }
			return result;
		}


		public List<Appointment> cancelAppointments(List<Integer> appointmentIdsList) {
			
			List<Appointment> result = new ArrayList<Appointment>();
			Transaction transaction = null;
			try{
				Session session = HibernateUtil.openCurrentSession();
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
			} catch (Exception hibernateException) { transaction.rollback(); log.error(hibernateException.getMessage(), hibernateException);
				hibernateException.printStackTrace();
			}finally {
				HibernateUtil.closeSession();
			 }
			return result;
		}


		public int getNoOfRecordsMonthly(String fromDate, String toDate) {

			List<Appointment> results = new ArrayList<Appointment>();
			int noOfRecords = 0;
			Transaction transaction = null;
			try{
				Session session = HibernateUtil.openCurrentSession();
				transaction = session.beginTransaction();

				Query query = session.createQuery("From Appointment where (appointmentdate between '"+fromDate+"' and '"+toDate+"')  and status !='Cancelled'").setCacheable(true).setCacheRegion("commonregion");
				results = query.getResultList();
				noOfRecords = results.size();
				log.info("The size of list is:::::::::::::::::::::::::::::::::::::::::: "
								+ noOfRecords);
				transaction.commit();

			} catch (Exception hibernateException) { transaction.rollback(); log.error(hibernateException.getMessage(), hibernateException);
				
				hibernateException.printStackTrace();

			} finally {
					HibernateUtil.closeSession();
				return noOfRecords;
			}
		
					
		}


		public int getNoOfRecordsCompletedAppointments() {
			
			List<Appointment> results = new ArrayList<Appointment>();
			int noOfRecords = 0;
			Transaction transaction = null;
			try{
				Session session = HibernateUtil.openCurrentSession();
				transaction = session.beginTransaction();

				results = (List<Appointment>) session.createQuery("From Appointment where status = 'Completed'").setCacheable(true).setCacheRegion("commonregion")
						.list();
				noOfRecords = results.size();
				log.info("The size of list is:::::::::::::::::::::::::::::::::::::::::: "
								+ noOfRecords);
				transaction.commit();

			} catch (Exception hibernateException) { transaction.rollback(); log.error(hibernateException.getMessage(), hibernateException);
				
				hibernateException.printStackTrace();

			} finally {
					HibernateUtil.closeSession();
				return noOfRecords;
			}
		}


		public int getNoOfRecordsIncompleteAppointments() {
			
			List<Appointment> results = new ArrayList<Appointment>();
			int noOfRecords = 0;
			Transaction transaction = null;
			try{
				Session session = HibernateUtil.openCurrentSession();
				transaction = session.beginTransaction();

				results = (List<Appointment>) session.createQuery("From Appointment where status = 'Scheduled' ").setCacheable(true).setCacheRegion("commonregion")
						.list();
				noOfRecords = results.size();
				log.info("The size of list is:::::::::::::::::::::::::::::::::::::::::: "
								+ noOfRecords);
				transaction.commit();

			} catch (Exception hibernateException) { transaction.rollback(); log.error(hibernateException.getMessage(), hibernateException);
				
				hibernateException.printStackTrace();

			} finally {
					HibernateUtil.closeSession();
				return noOfRecords;
			}
		}


		public int getNoOfRecordsTodayCompletedAppointments() {
			
			List<Appointment> results = new ArrayList<Appointment>();
			int noOfRecords = 0;
			Transaction transaction = null;
			try{
				Session session = HibernateUtil.openCurrentSession();
				transaction = session.beginTransaction();

				results = (List<Appointment>) session.createQuery("From Appointment where status = 'Completed' and createddate = CURDATE()").setCacheable(true).setCacheRegion("commonregion")
						.list();
				noOfRecords = results.size();
				log.info("The size of list is:::::::::::::::::::::::::::::::::::::::::: "
								+ noOfRecords);
				transaction.commit();

			} catch (Exception hibernateException) { transaction.rollback(); log.error(hibernateException.getMessage(), hibernateException);
				
				hibernateException.printStackTrace();

			} finally {
					HibernateUtil.closeSession();
				return noOfRecords;
			}
		}


		public int getNoOfRecordsTodayIncompleteAppointments() {
			
			List<Appointment> results = new ArrayList<Appointment>();
			int noOfRecords = 0;
			Transaction transaction = null;
			try{
				Session session = HibernateUtil.openCurrentSession();
				transaction = session.beginTransaction();

				results = (List<Appointment>) session.createQuery("From Appointment where status = 'Scheduled'  and createddate = CURDATE()").setCacheable(true).setCacheRegion("commonregion")
						.list();
				noOfRecords = results.size();
				log.info("The size of list is:::::::::::::::::::::::::::::::::::::::::: "
								+ noOfRecords);
				transaction.commit();

			} catch (Exception hibernateException) { transaction.rollback(); log.error(hibernateException.getMessage(), hibernateException);
				
				hibernateException.printStackTrace();

			} finally {
					HibernateUtil.closeSession();
				return noOfRecords;
			}
		}


		public List<Appointment> generateAppointmentsReport(String query) {
			
	        List<Appointment> results = new ArrayList<Appointment>();
	        
			Transaction transaction = null;
			try{
					Session session = HibernateUtil.openCurrentSession();
	                transaction = session.beginTransaction();
	                results = (List<Appointment>) session.createQuery(query).setCacheable(true).setCacheRegion("commonregion").list();
	                transaction.commit();
	        } catch (Exception hibernateException) { transaction.rollback(); log.error(hibernateException.getMessage(), hibernateException);
	                
	                hibernateException.printStackTrace();

	        } finally {
	    			HibernateUtil.closeSession();
	        }
	        return results;
}

		public boolean updateAppointments(List<Appointment> appointmentList) {
			
			boolean result = false;
			Transaction transaction = null;
			try{
				Session session = HibernateUtil.openCurrentSession();
				transaction = session.beginTransaction();
				
					for (Appointment appointment : appointmentList) {
						Query query = session.createQuery("update Appointment set appointmentstarttime = '"+appointment.getAppointmentstarttime()+"', appointmentendtime='"+appointment.getAppointmentendtime()+"', totaltime='"+appointment.getTotaltime()+"' where id="+appointment.getId()+"");
						query.executeUpdate();
					}
					
					transaction.commit();
					result = true;
			} catch (Exception hibernateException) { transaction.rollback(); log.error(hibernateException.getMessage(), hibernateException);
				hibernateException.printStackTrace();
			}finally {
				HibernateUtil.closeSession();
			 }
			return result;
		}

}

package com.model.periods.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.util.Session;
import com.util.Session.Transaction;
import org.hibernate.query.Query;

import com.model.periods.dto.Perioddetails;
import com.model.periods.dto.Periodmaster;
import com.util.HibernateUtil;

public class PeriodDAO {
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
	
	private static final Logger logger = LogManager.getLogger(PeriodDAO.class);

	public PeriodDAO() {
		session = HibernateUtil.openCurrentSession();
	}
	
	public boolean save(Periodmaster periodMaster,Map<String, List<Perioddetails>> periodMap) {
		
		try{
			transaction = session.beginTransaction();
			session.save(periodMaster);
			
			for (Entry<String, List<Perioddetails>> entry : periodMap.entrySet())
			{
				for (Perioddetails perioddetails : entry.getValue()) {
					perioddetails.setPeriodmasterid(periodMaster.getIdperiodmaster());
					perioddetails.setDays(entry.getKey());
					session.save(perioddetails);
				}
			}
			transaction.commit();
			return true;
		}catch (Exception e) { transaction.rollback(); logger.error(e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		
		return false;
	}

	public List<Periodmaster> getPeriodsDetails(String currentacademicyear, int branchId) {
		List<Periodmaster> periodMaster = new ArrayList<Periodmaster>();
		
		try {
			transaction = session.beginTransaction();
			periodMaster = session.createQuery("from Periodmaster where academicyear='"+currentacademicyear+"' and branchid="+branchId).list();
			transaction.commit();
		} catch (Exception e) { transaction.rollback(); logger.error(e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return periodMaster;
		
	}

	public Periodmaster getTimeTable(String periodMasterid) {
			Periodmaster periodMaster = new Periodmaster();
		try {
			transaction = session.beginTransaction();
			Query query = session.createQuery("from Periodmaster where idperiodmaster="+periodMasterid).setCacheable(true).setCacheRegion("commonregion");
			periodMaster = (Periodmaster) query.uniqueResult();
			transaction.commit();
		} catch (Exception e) { transaction.rollback(); logger.error(e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return periodMaster;
		
	}

	public List<Perioddetails> getTimeTablePeriodDetails(String periodMasterid) {
		List<Perioddetails> periodDetailsList = new ArrayList<Perioddetails>();
		try {
			transaction = session.beginTransaction();
			periodDetailsList = session.createQuery("from Perioddetails where periodmasterid="+periodMasterid+" order by idperioddetails ASC").list();
			transaction.commit();
		} catch (Exception e) { transaction.rollback(); logger.error(e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return periodDetailsList;
	}

	public boolean deletePeriods(List periodMasterid) {
		
		try {
			transaction = session.beginTransaction();
			Query query = session
					.createQuery("delete from Periodmaster as period where period.idperiodmaster IN (:ids)");
			query.setParameterList("ids", periodMasterid);
			query.executeUpdate();
			transaction.commit();
			return true;
		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			hibernateException.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return false;
	}

	public List<Perioddetails> getPeriodDetailsForTeacher(String teacherName) {
		
		List<Perioddetails> periodDetailsList = new ArrayList<Perioddetails>();
		try {
			transaction = session.beginTransaction();
			periodDetailsList = session.createQuery("from Perioddetails where staff='"+teacherName+"' order by idperioddetails ASC").list();
			transaction.commit();
		} catch (Exception e) { transaction.rollback(); logger.error(e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return periodDetailsList;
	}
	
}

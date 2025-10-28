package org.ideoholic.curium.model.sponsor.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.ideoholic.curium.model.employee.dto.Teacher;
import org.ideoholic.curium.model.sponsor.dto.Sponsor;
import org.ideoholic.curium.model.sponsor.dto.SponsorDto;
import org.ideoholic.curium.model.student.dto.Studentfeesstructure;
import org.ideoholic.curium.util.HibernateUtil;
import org.ideoholic.curium.util.Session;
import org.ideoholic.curium.util.Session.Transaction;

public class SponsorDao {
	
	Session session = null;
   
    Transaction transaction = null;
    
    SessionFactory sessionFactory;
    private static final Logger logger = LogManager.getLogger(SponsorDao.class);


    public SponsorDao() {
		session = HibernateUtil.openCurrentSession();
	}
    
	public boolean addSponsor(Sponsor sponsor) {

		boolean result = false;
		try {
            transaction = session.beginTransaction();
            session.save(sponsor);
            transaction.commit();
            result = true;
            
        } catch (Exception hibernateException) { transaction.rollback();
        logger.error(hibernateException);
            
            hibernateException.printStackTrace();
        } finally {
    			HibernateUtil.closeSession();
        }
		
		return result;
		
	}

	public List<Sponsor> viewSponsor(int branchId) {
		List<Sponsor> results = new ArrayList<Sponsor>();
		try {

			transaction = session.beginTransaction();
			results = (List<Sponsor>) session.createQuery("From Sponsor as sponsor where sponsor.branchid=" + branchId)
					.list();
			transaction.commit();
		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			
			hibernateException.printStackTrace();
		} finally {
				HibernateUtil.closeSession();
			return results;
		}
	}

	public void deleteMultiple(List<Integer> ids) {
		try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("delete from Sponsor where id IN (:ids)");
            query.setParameterList("ids", ids);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
            hibernateException.printStackTrace();
        }finally {
			HibernateUtil.closeSession();
		}
		
	}

	public Sponsor readUniqueObject(int id) {
		Sponsor sponsor = new Sponsor();

		try {

			transaction = session.beginTransaction();
			Query query = session.createQuery("From Sponsor as sponsor where sponsor.id=" + id);
			sponsor = (Sponsor) query.uniqueResult();
			transaction.commit();
		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			
			hibernateException.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}

		return sponsor;
	}

	public boolean updateSponsor(Sponsor sponsor) {
		boolean result = false;
		try {
            transaction = session.beginTransaction();
            session.update(sponsor);
            transaction.commit();
            result = true;
        } catch (Exception hibernateException) { 
        	transaction.rollback(); logger.error(hibernateException);
            
            hibernateException.printStackTrace();
        } finally {
    			HibernateUtil.closeSession();
        }
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<Studentfeesstructure> getFeesStructuredBySponsor(int branchId, String sponsorName) {
		List<Studentfeesstructure> results = new ArrayList<Studentfeesstructure>();
		try {

			transaction = session.beginTransaction();
			results = (List<Studentfeesstructure>) session.createQuery("From Studentfeesstructure as sfr where sfr.concessionnotes='" + sponsorName+ "' and sfr.branchid=" + branchId)
					.list();
			transaction.commit();
		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			
			hibernateException.printStackTrace();
		} finally {
				HibernateUtil.closeSession();
			return results;
		}
	}

}

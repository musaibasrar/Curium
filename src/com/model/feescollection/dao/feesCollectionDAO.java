/**
 * 
 */
package com.model.feescollection.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.util.Session;
import org.hibernate.SessionFactory;
import com.util.Session.Transaction;
import org.hibernate.query.Query;

import com.model.account.dto.VoucherEntrytransactions;
import com.model.feescollection.dto.Feescollection;
import com.model.feescollection.dto.Receiptinfo;
import com.util.HibernateUtil;

/**
 * @author Musaib_2
 *
 */
public class feesCollectionDAO {
	Session session = null;
    Transaction transaction = null;
    Transaction transaction1;
    SessionFactory sessionFactory;
    
    private static final Logger logger = LogManager.getLogger(feesCollectionDAO.class);

	public feesCollectionDAO() {
		session = HibernateUtil.openCurrentSession();
	}

	@SuppressWarnings("finally")
	public boolean create(Receiptinfo receiptInfo, List<Feescollection> feescollectionList, VoucherEntrytransactions transactions, String updateDrAccount, String updateCrAccount) {
		 
		boolean result = false;
		try {
			 
			 transaction = session.beginTransaction();

			 	Query queryReceipt = session.createQuery("from Receiptinfo where branchid = "+receiptInfo.getBranchid()+" order by receiptnumber DESC");
			 	List<Receiptinfo> ReceiptList = queryReceipt.list();
			 	  
			 	
			 	if(ReceiptList.size() > 0) {
			 		receiptInfo.setBranchreceiptnumber(Integer.toString(Integer.parseInt(ReceiptList.get(0).getBranchreceiptnumber())+1));
			 	}else {
			 		receiptInfo.setBranchreceiptnumber(Integer.toString(1));
			 	}
			 	
			 	session.save(receiptInfo);
			 	
			 	transactions.setNarration(transactions.getNarration().concat(" Receipt no: "+receiptInfo.getReceiptnumber()));
				session.save(transactions);
				Query queryAccounts = session.createQuery(updateDrAccount);
				queryAccounts.executeUpdate();
				Query queryqueryAccounts1 = session.createQuery(updateCrAccount);
				queryqueryAccounts1.executeUpdate();
				
			
			for (Feescollection singleFeescollection :  feescollectionList) {
				
				singleFeescollection.setReceiptnumber(receiptInfo.getReceiptnumber());
				Query query = session.createQuery("update Studentfeesstructure set feespaid=feespaid+"+singleFeescollection.getAmountpaid()+" where sfsid="+singleFeescollection.getSfsid());
				query.executeUpdate();
				session.save(singleFeescollection);
				
			}
	            transaction.commit();
	            result = true;
			 
		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
	            
	            hibernateException.printStackTrace();
	        } finally {
				HibernateUtil.closeSession();
			}
		return result;

	}
	@SuppressWarnings({ "unchecked", "finally" })
	public List<Feescollection> readListOfObject(Integer feeid) {

		List<Feescollection> results = new ArrayList<Feescollection>();
		try {

			transaction = session.beginTransaction();
			results = (List<Feescollection>) session.createQuery("From Feescollection where feesdetailsid="+feeid).list();
			
			transaction.commit();
		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			
			hibernateException.printStackTrace();
		} finally {
				HibernateUtil.closeSession();
			return results;
		}
	}

	public List<Feescollection> getFeesForTheCurrentYear(long id, String currentAcademicYear) {
		List<Feescollection> results = new ArrayList<Feescollection>();
		try {

			transaction = session.beginTransaction();
			results = (List<Feescollection>) session.createQuery("From Feescollection where sid='"+id+"' and academicyear = '"+currentAcademicYear+"'").list();
			
			transaction.commit();
		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			
			hibernateException.printStackTrace();
		} finally {
				HibernateUtil.closeSession();
			return results;
		}
	}

	public void createReceipt(Receiptinfo receiptInfo) {

		try {
			 transaction = session.beginTransaction();
			 session.save(receiptInfo);
			 transaction.commit();
		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
	            
	            hibernateException.printStackTrace();
	        } finally {
				HibernateUtil.closeSession();
			}
	}

	public Receiptinfo getReceiptInfoDetails(Integer receiptNumber, String currentAcademicYear) {
		
		Receiptinfo receiptDetails = new Receiptinfo();
		
		try {
			 
			 transaction = session.beginTransaction();
			 Query query = session.createQuery("from Receiptinfo where receiptnumber = '"+receiptNumber+"' and academicyear = '"+currentAcademicYear+"'");
			 receiptDetails = (Receiptinfo) query.uniqueResult();
			 transaction.commit();
		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
	            
	            hibernateException.printStackTrace();
	        } finally {
				HibernateUtil.closeSession();
			}
		return receiptDetails;
		
	}

	public List<Receiptinfo> getReceiptDetailsPerStudent(long id,
			String currentacademicyear) {
		List<Receiptinfo> receiptInfo = new ArrayList<Receiptinfo>();
		try{
			transaction = session.beginTransaction();
			receiptInfo = session.createQuery("from Receiptinfo where sid = '"+id+"' and academicyear = '"+currentacademicyear+"' and cancelreceipt=0").list();
			transaction.commit();
		}catch (Exception e) { transaction.rollback(); logger.error(e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return receiptInfo;
	}

	public List<Feescollection> getFeesCollectionDetails(int receiptId) {

		List<Feescollection> results = new ArrayList<Feescollection>();
		try {

			transaction = session.beginTransaction();
			results = (List<Feescollection>) session.createQuery("From Feescollection where receiptnumber="+receiptId).list();
			transaction.commit();
			
		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			
			hibernateException.printStackTrace();
		} finally {
				HibernateUtil.closeSession();
			return results;
		}
	}

}

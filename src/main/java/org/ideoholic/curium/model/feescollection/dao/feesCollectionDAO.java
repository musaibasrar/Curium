/**
 * 
 */
package org.ideoholic.curium.model.feescollection.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.ideoholic.curium.util.Session;
import org.hibernate.SessionFactory;
import org.ideoholic.curium.util.Session.Transaction;
import org.hibernate.query.Query;

import org.ideoholic.curium.model.account.dto.VoucherEntrytransactions;
import org.ideoholic.curium.model.feescollection.dto.Feescollection;
import org.ideoholic.curium.model.feescollection.dto.Receiptinfo;
import org.ideoholic.curium.model.student.dto.Studentfeesstructure;
import org.ideoholic.curium.util.HibernateUtil;

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
	public boolean create(Receiptinfo receiptInfo, List<Feescollection> feescollectionList, VoucherEntrytransactions transactions, String updateCrAccount,
			String updateDrAccount, VoucherEntrytransactions transactionsIncome, String updateDrAccountIncome, String updateCrAccountIncome) {
		 
		boolean result = false;
		try {
			 
			 transaction = session.beginTransaction();
			
			 Query queryReceipt = session.createQuery("from Receiptinfo where branchid = "+receiptInfo.getBranchid()+" order by receiptnumber DESC");
			 	List<Receiptinfo> ReceiptList = queryReceipt.list();
			 	
			 	if(ReceiptList.size() > 0) {
			 		receiptInfo.setBranchreceiptnumber(String.format("%03d",Integer.parseInt(ReceiptList.get(0).getBranchreceiptnumber())+1));
			 	}else {
			 		receiptInfo.setBranchreceiptnumber(String.format("%03d",1));
			 	}
			 	
			 	//Receipts
			 	transactions.setNarration(transactions.getNarration().concat(" Receipt no: "+receiptInfo.getBranchreceiptnumber()));
				session.save(transactions);
				Query queryAccounts = session.createQuery(updateDrAccount);
				queryAccounts.executeUpdate();
				Query queryqueryAccounts1 = session.createQuery(updateCrAccount);
				queryqueryAccounts1.executeUpdate();
				//
				
				// J.V
				transactionsIncome.setNarration(transactionsIncome.getNarration().concat(" Receipt no: "+receiptInfo.getBranchreceiptnumber()));
				session.save(transactionsIncome);
				Query queryAccountsIncome = session.createQuery(updateDrAccountIncome);
				queryAccountsIncome.executeUpdate();
				Query queryqueryAccountsIncome1 = session.createQuery(updateCrAccountIncome);
				queryqueryAccountsIncome1.executeUpdate();
				//
				
				receiptInfo.setReceiptvoucher(transactions.getTransactionsid().intValue());
				receiptInfo.setJournalvoucher(transactionsIncome.getTransactionsid().intValue());
				session.save(receiptInfo);
				
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

	public Receiptinfo getReceiptInfoDetails(Integer receiptNumber) {
		
		Receiptinfo receiptDetails = new Receiptinfo();
		
		try {
			 
			 transaction = session.beginTransaction();
			 Query query = session.createQuery("from Receiptinfo where receiptnumber = '"+receiptNumber+"' ");
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

	public List<Studentfeesstructure> getStudentsFeesStructure(List<Integer> studentids, String currentYear, String searchCriteria) {
		
		List<Studentfeesstructure> results = new ArrayList<Studentfeesstructure>();

		try {
			// this.session =
			// HibernateUtil.getSessionFactory().openCurrentSession();
			transaction = session.beginTransaction();

			// results = (List<PersonalDetails>)
			// session.createQuery("From PersonalDetails p where p.subscriber=1 and  p.archive = 0 order by name desc LIMIT 5 ").list();
			Query query = session.createQuery("from Studentfeesstructure sfs where sfs.sid in (:ids) and sfs."+searchCriteria+" > 0 and sfs.academicyear = '"+currentYear+"'");
			query.setParameter("ids", studentids);
			results = query.list();
			transaction.commit();

		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			
			hibernateException.printStackTrace();

		} finally {
				HibernateUtil.closeSession();
			return results;
		}
	}

}

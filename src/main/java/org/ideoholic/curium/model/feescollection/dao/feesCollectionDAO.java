/**
 * 
 */
package org.ideoholic.curium.model.feescollection.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.ideoholic.curium.model.account.dto.VoucherEntrytransactions;
import org.ideoholic.curium.model.feescollection.dto.Feescollection;
import org.ideoholic.curium.model.feescollection.dto.Otherfeescollection;
import org.ideoholic.curium.model.feescollection.dto.Otherreceiptinfo;
import org.ideoholic.curium.model.feescollection.dto.Receiptinfo;
import org.ideoholic.curium.model.student.dto.Studentfeesstructure;
import org.ideoholic.curium.util.HibernateUtil;
import org.ideoholic.curium.util.Session;
import org.ideoholic.curium.util.Session.Transaction;

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
	public boolean create(Receiptinfo receiptInfo, List<Feescollection> feescollectionList, List<VoucherEntrytransactions> transactionsList, List<String> updateDrAccountList,
			List<String> updateCrAccountList, VoucherEntrytransactions transactionsIncome, String updateDrAccountIncome, String updateCrAccountIncome) {
				int[] transactionsId = new int[transactionsList.size()];
				int i = 0;
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
			 	for (VoucherEntrytransactions transactions : transactionsList) {
			 		transactions.setNarration(transactions.getNarration().concat(" Receipt no: "+receiptInfo.getBranchreceiptnumber()));
					session.save(transactions);
					transactionsId[i]=transactions.getTransactionsid().intValue();
					i++;
				}
			 	
			 	receiptInfo.setReceiptvoucher(transactionsId[0]);
			 	receiptInfo.setJournalvoucher(transactionsId[1]);
			 	receiptInfo.setMisc(Integer.toUnsignedLong(transactionsId[transactionsList.size()-1]));
				session.save(receiptInfo);
			 	
			 	for (String updateCrAccount : updateCrAccountList) {
			 		
			 		Query queryAccounts = session.createQuery(updateCrAccount);
					queryAccounts.executeUpdate();
					
				}
			 	
			 	for (String updateDrAccount : updateDrAccountList) {
			 		
					Query queryqueryAccounts1 = session.createQuery(updateDrAccount);
					queryqueryAccounts1.executeUpdate();
				}
			 	
				//
				/*
				// J.V
				transactionsIncome.setNarration(transactionsIncome.getNarration().concat(" Receipt no: "+receiptInfo.getBranchreceiptnumber()));
				session.save(transactionsIncome);
				Query queryAccountsIncome = session.createQuery(updateDrAccountIncome);
				queryAccountsIncome.executeUpdate();
				Query queryqueryAccountsIncome1 = session.createQuery(updateCrAccountIncome);
				queryqueryAccountsIncome1.executeUpdate();
				//
				*/
			 	
				
				//receiptInfo.setJournalvoucher(transactionsIncome.getTransactionsid().intValue());
				
				
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
	
	@SuppressWarnings("finally")
	public boolean createother(Otherreceiptinfo receiptInfo, List<Otherfeescollection> feescollectionList, VoucherEntrytransactions transactions, String updateCrAccount,
			String updateDrAccount, VoucherEntrytransactions transactionsIncome, String updateDrAccountIncome, String updateCrAccountIncome) {

		boolean result = false;
		try {

			 transaction = session.beginTransaction();

			 Query queryReceipt = session.createQuery("from Otherreceiptinfo where branchid = "+receiptInfo.getBranchid()+" order by receiptnumber DESC");
			 	List<Otherreceiptinfo> ReceiptList = queryReceipt.list();

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

			for (Otherfeescollection singleFeescollection :  feescollectionList) {
				singleFeescollection.setReceiptnumber(receiptInfo.getReceiptnumber());
				Query query = session.createQuery("update Studentotherfeesstructure set feespaid=feespaid+"+singleFeescollection.getAmountpaid()+" where sfsid="+singleFeescollection.getSfsid());
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
	public List<Otherfeescollection> otherreadListOfObject(Integer feeid) {

		List<Otherfeescollection> results = new ArrayList<Otherfeescollection>();
		try {

			transaction = session.beginTransaction();
			results = (List<Otherfeescollection>) session.createQuery("From Otherfeescollection where feesdetailsid="+feeid).list();

			transaction.commit();
		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);

			hibernateException.printStackTrace();
		} finally {
				HibernateUtil.closeSession();
			return results;
		}
	}
	
	public Otherreceiptinfo getOtherReceiptInfoDetails(Integer receiptNumber) {

		Otherreceiptinfo receiptDetails = new Otherreceiptinfo();

			try {

				 transaction = session.beginTransaction();
				 Query query = session.createQuery("from Otherreceiptinfo where receiptnumber = '"+receiptNumber+"' ");
				 receiptDetails = (Otherreceiptinfo) query.uniqueResult();
				 transaction.commit();
			} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);

		            hibernateException.printStackTrace();
		        } finally {
					HibernateUtil.closeSession();
				}
			return receiptDetails;

		}
	
	public List<Otherreceiptinfo> getOtherReceiptDetailsPerStudent(long id,
			String currentacademicyear) {
		List<Otherreceiptinfo> receiptInfo = new ArrayList<Otherreceiptinfo>();
		try{
			transaction = session.beginTransaction();
			receiptInfo = session.createQuery("from Otherreceiptinfo where sid = '"+id+"' and academicyear = '"+currentacademicyear+"' and cancelreceipt=0").list();
			transaction.commit();
		}catch (Exception e) { transaction.rollback(); logger.error(e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return receiptInfo;
	}
	
	
	public List<Otherfeescollection> getOtherFeesCollectionDetails(int receiptId) {

		List<Otherfeescollection> results = new ArrayList<Otherfeescollection>();
		try {

			transaction = session.beginTransaction();
			results = (List<Otherfeescollection>) session.createQuery("From Otherfeescollection where receiptnumber="+receiptId).list();
			transaction.commit();
			
		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			
			hibernateException.printStackTrace();
		} finally {
				HibernateUtil.closeSession();
			return results;
		}
	}

	public List<Object[]> readListOfStudentsCollectionReport(int branchId) {
		List<Object[]> results = new ArrayList<Object[]>();

		try {
			transaction = session.beginTransaction();
			Query query = session
					.createQuery("SELECT s.sid,s.name,s.classstudying,MAX(fc.date) AS last_payment_date,DATEDIFF(CURRENT_DATE, MAX(fc.date)) AS days_since_last_payment FROM Student s LEFT JOIN Feescollection fc ON s.sid = fc.sid WHERE s.archive = 0 AND s.branchid = '"+branchId+"' GROUP BY s.sid, s.name, s.classstudying ORDER BY days_since_last_payment DESC").setCacheable(true).setCacheRegion("commonregion");
			results = query.list();
			transaction.commit();

		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			
			hibernateException.printStackTrace();

		} finally {
				HibernateUtil.closeSession();
			return results;
		}
	}

	public Receiptinfo getReceiptInfoDetails(String bookReceiptNo) {
		
		Receiptinfo receiptDetails = new Receiptinfo();
		
		try {
			 
			 transaction = session.beginTransaction();
			 Query query = session.createQuery("from Receiptinfo where bookreceiptno = '"+bookReceiptNo+"' ");
			 receiptDetails = (Receiptinfo) query.uniqueResult();
			 transaction.commit();
		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
	            
	            hibernateException.printStackTrace();
	        } finally {
				HibernateUtil.closeSession();
			}
		return receiptDetails;
		
	}

}

package org.ideoholic.curium.model.feescategory.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.query.Query;
import org.ideoholic.curium.model.account.dto.VoucherEntrytransactions;
import org.ideoholic.curium.model.feescategory.dto.Concession;
import org.ideoholic.curium.model.feescategory.dto.Feescategory;
import org.ideoholic.curium.model.feescategory.dto.OtherFeecategory;
import org.ideoholic.curium.model.feescollection.dto.Feescollection;
import org.ideoholic.curium.model.stampfees.dto.Academicfeesstructure;
import org.ideoholic.curium.model.student.dto.Studentfeesstructure;
import org.ideoholic.curium.repositories.AcademicfeesstructureRepository;
import org.ideoholic.curium.repositories.FeescategoryRepository;
import org.ideoholic.curium.repositories.FeescollectionRepository;
import org.ideoholic.curium.repositories.OtherFeecategoryRepository;
import org.ideoholic.curium.repositories.StudentFeesStructureRepository;
import org.ideoholic.curium.repositories.VoucherEntryTransactionsRepository;
import org.ideoholic.curium.util.HibernateUtil;
import org.ideoholic.curium.util.QueryUtil;
import org.ideoholic.curium.util.Session;
import org.ideoholic.curium.util.Session.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FeesCategoryDAO {

	@Autowired
	private FeescategoryRepository feesCatRepo;
	@Autowired
	private FeescollectionRepository feescollectionRepo;
	@Autowired
	private StudentFeesStructureRepository studentFeesStructureRepo;
	@Autowired
	private VoucherEntryTransactionsRepository voucherEntryTransactionsRepo;
	@Autowired
	private AcademicfeesstructureRepository academicfeesstructureRepo;
	@Autowired
	private OtherFeecategoryRepository otherFeecategoryRepo;
	@Autowired
	private QueryUtil queryUtil;
	
	@Transactional
	public List<Feescategory> readListOfObjects(int branchId, String academicYear) {
		
		List<Feescategory> results = new ArrayList<Feescategory>();
		try {
			// session.createQuery("From Feescategory where academicyear='"+academicYear+"' and branchid="+branchId).list();
			results = feesCatRepo.findByAcademicyearAndBranchid(academicYear, branchId);
		} catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();

			throw hibernateException;
		}
		return results;
	}

	@Transactional
	public Feescategory create(Feescategory feescategory) {
		try {
			feesCatRepo.save(feescategory);
		} catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();

			throw hibernateException;
		}
		return feescategory;
	}

	@Transactional
	public void deleteMultiple(List<Integer> ids) {
		try {
			// Query query = session.createQuery("delete from Feescategory as fess where fess.idfeescategory IN (:ids)");
			feesCatRepo.deleteAllById(ids);
		} catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();

			throw hibernateException;
		}

	}

	@Transactional
	public void deleteFeesCategory(List<Integer> ids, List<Integer> feesCatId, String sid, List<VoucherEntrytransactions> transactionsList, List<String> debitEntries, List<String> creditEntries) {
		List<Feescollection> feesCollection = new ArrayList<Feescollection>();
		int sId = Integer.parseInt(sid);
		try {
			//Query queryOne = session.createQuery("from Feescollection as feescollection where feescollection.sid = '"+sid+"' and feescollection.sfsid IN (:ids)");
			 feesCollection = feescollectionRepo.findByStudentSidAndStudentFeeStructureSfsidIn(sId, ids);
			
			if(feesCollection.isEmpty()){
				//Query query = session.createQuery("delete from Studentfeesstructure as fees where fees.sid = "+sid+" and fees.sfsid IN (:ids)");
				studentFeesStructureRepo.deleteBySidAndSfsidIn(sId, ids);
				
				for (VoucherEntrytransactions transactions : transactionsList) {
					voucherEntryTransactionsRepo.save(transactions);
				}
				
				for (String updateDrAccount : debitEntries) {
					//Query queryAccounts = session.createQuery(updateDrAccount);
					//queryAccounts.executeUpdate();
					queryUtil.runUpdateQuery(updateDrAccount);
				}
				
				for (String updateCrAccount : creditEntries) {
					//Query queryqueryAccounts1 = session.createQuery(updateCrAccount);
					//queryqueryAccounts1.executeUpdate();
					queryUtil.runUpdateQuery(updateCrAccount);
				}
				
			}
		} catch (Exception hibernateException) { 
        	log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            throw hibernateException; 
		}
	}

	
	@Transactional
	public void waiveOffFees(List<Concession> concessionList, String sid,
			List<VoucherEntrytransactions> transactionsApplyList, List<String> updateDrAccountApplyList,
			List<String> updateCrAccountApplyList) {
		try {
			for (Concession concession : concessionList) {
				// Query query = session.createQuery("update Studentfeesstructure as fees set fees.waiveoff='"+Integer.parseInt(concession.getConcession())+"' where fees.sfsid='"+concession.getSfsid()+"'");
				studentFeesStructureRepo.findById(concession.getSfsid()).ifPresent(studentfeesstructure -> {
					studentfeesstructure.setWaiveoff(Long.parseLong(concession.getConcession()));
					studentFeesStructureRepo.save(studentfeesstructure);
				});
				// Query queryAcademicFees = session.createQuery("update Academicfeesstructure as academicfees set academicfees.totalfees=academicfees.totalfees-'"+Integer.parseInt(concession.getConcession())+"' where academicfees.sid='"+sid+"'");
				academicfeesstructureRepo.findById(Integer.parseInt(sid)).ifPresent(academicfeesstructure -> {
					Integer totalFees = Integer.parseInt(academicfeesstructure.getTotalfees());
					totalFees -= Integer.parseInt(concession.getConcession());
					academicfeesstructure.setTotalfees(String.valueOf(totalFees));
					academicfeesstructureRepo.save(academicfeesstructure);
				});
			}

			// accounts

			for (VoucherEntrytransactions transactions : transactionsApplyList) {
				voucherEntryTransactionsRepo.save(transactions);
			}

			for (String updateDrAccountApply : updateDrAccountApplyList) {
				// Query queryAccountsApply = session.createQuery(updateDrAccountApply);
				// queryAccountsApply.executeUpdate();
				queryUtil.runUpdateQuery(updateDrAccountApply);
			}

			for (String updateCrAccountApply : updateCrAccountApplyList) {
				// Query queryAccountsApply = session.createQuery(updateCrAccountApply);
				// queryAccountsApply.executeUpdate();
				queryUtil.runUpdateQuery(updateCrAccountApply);
			}

		} catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
			throw hibernateException;
		}
	}

	@Transactional
	public void applyConcession(List<Concession> concessionList, String sid, List<VoucherEntrytransactions> transactionsReverseList, List<VoucherEntrytransactions> transactionsApplyList, List<String> updateDrAccountReverseList, List<String> updateCrAccountReverseList, List<String> updateDrAccountApplyList, List<String> updateCrAccountApplyList) {
		try {
			for (Concession concession : concessionList) {
				//Query query = session.createQuery("update Studentfeesstructure as fees set fees.concession='"+Integer.parseInt(concession.getConcession())+"', fees.concessionnotes='"+concession.getConcessionNotes()+"' where fees.sfsid='"+concession.getSfsid()+"'");
				studentFeesStructureRepo.findById(concession.getSfsid()).ifPresent(studentfeesstructure -> {
					studentfeesstructure.setConcession(Integer.parseInt(concession.getConcession()));
					studentFeesStructureRepo.save(studentfeesstructure);
				});
				
				//Query queryAcademicFees = session.createQuery("update Academicfeesstructure as academicfees set academicfees.totalfees=academicfees.totalfees+'"+Integer.parseInt(concession.getConcessionOld())+"'-'"+Integer.parseInt(concession.getConcession())+"' where academicfees.sid='"+sid+"'");
				academicfeesstructureRepo.findById(Integer.parseInt(sid)).ifPresent(academicfeesstructure -> {
					Integer currentTotalFees = Integer.parseInt(academicfeesstructure.getTotalfees());
					currentTotalFees += Integer.parseInt(concession.getConcessionOld()) - Integer.parseInt(concession.getConcession());
					academicfeesstructure.setTotalfees(String.valueOf(currentTotalFees));
					academicfeesstructureRepo.save(academicfeesstructure);
				});
			}
			
			//accounts
			for (VoucherEntrytransactions transactions : transactionsReverseList) {
				voucherEntryTransactionsRepo.save(transactions);
			}
			
			for (VoucherEntrytransactions transactions : transactionsApplyList) {
				voucherEntryTransactionsRepo.save(transactions);
			}
			
			for (String updateDrAccountReverse : updateDrAccountReverseList) {
				//Query queryAccountsReverse = session.createQuery(updateDrAccountReverse);
				//queryAccountsReverse.executeUpdate();
				queryUtil.runUpdateQuery(updateDrAccountReverse);
			}
			
			for (String updateCrAccountReverse : updateCrAccountReverseList) {
				//Query queryAccountsReverse = session.createQuery(updateCrAccountReverse);
				//queryAccountsReverse.executeUpdate();
				queryUtil.runUpdateQuery(updateCrAccountReverse);
			}
			
			for (String updateDrAccountApply : updateDrAccountApplyList) {
				//Query queryAccountsApply = session.createQuery(updateDrAccountApply);
				//queryAccountsApply.executeUpdate();
				queryUtil.runUpdateQuery(updateDrAccountApply);
			}
			
			for (String updateCrAccountApply : updateCrAccountApplyList) {
				//Query queryAccountsApply = session.createQuery(updateCrAccountApply);
				//queryAccountsApply.executeUpdate();
				queryUtil.runUpdateQuery(updateCrAccountApply);
			}
			
			
		} catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
			throw hibernateException;
		}
	}
	
	@Transactional
	public List<OtherFeecategory> readListOfOtherFeeObjects(int branchId, String academicYear, String nextYear) {
		List<OtherFeecategory> results = new ArrayList<OtherFeecategory>();
        try {

           // results = (List<OtherFeecategory>) session.createQuery("From OtherFeecategory where (academicyear='"+academicYear+"' or academicyear='"+nextYear+"') and branchid="+branchId).list();
            results = otherFeecategoryRepo.findByBranchAndAcademicYear(branchId, academicYear, nextYear);
        } catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
			throw hibernateException;
        } 
        return results;
	}
	
	@Transactional
	public OtherFeecategory createOtherFeeCategory(OtherFeecategory ofeescategory) {
		try {
            ofeescategory = otherFeecategoryRepo.save(ofeescategory);
        } catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
			throw hibernateException;
        } 
		 return ofeescategory;
	}
	
	@Transactional
	public void odeleteMultiple(List<Integer> ids) {
		try {

			//Query query = session.createQuery("delete from OtherFeecategory as fess where fess.idfeescategory IN (:ids)");
			otherFeecategoryRepo.deleteAllById(ids);
		} catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
			throw hibernateException;
		}

	}
	
	public List <Feescategory> getfeecategoryofstudent(String classname, String searchYear, String branchId)
	{
		List <Feescategory> result= new ArrayList();
		try {
			//Query query = session.createQuery("from Feescategory where particularname like '"+classname+"--%' and academicyear = '"+searchYear+"' and branchid='"+branchId+"'");
			result = feesCatRepo.findFeecategoryOfStudent(classname, searchYear, branchId);
		}  catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
			throw hibernateException;
		}
		return result;
	}
	
	public void applyotherConcession(List<Concession> concessionList, String sid) {
		Session session = HibernateUtil.openCurrentSession();
	    Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			for (Concession concession : concessionList) {
				Query query = session.createQuery("update Studentotherfeesstructure as fees set fees.concession='"+Integer.parseInt(concession.getConcession())+"' where fees.sfsid='"+concession.getSfsid()+"'");
				query.executeUpdate();
				Query queryAcademicFees = session.createQuery("update Academicotherfeesstructure as academicfees set academicfees.totalfees=academicfees.totalfees+'"+Integer.parseInt(concession.getConcessionOld())+"'-'"+Integer.parseInt(concession.getConcession())+"' where academicfees.sid='"+sid+"'");
				queryAcademicFees.executeUpdate();
			}
			transaction.commit();
		} catch (Exception hibernateException) {
			transaction.rollback(); 
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
	}

	public List<Feescategory> readListOfFeeCategory(int branchId, String academicYear, String nextYear) {
		Session session = HibernateUtil.openCurrentSession();
	    Transaction transaction = null;
		List<Feescategory> results = new ArrayList<Feescategory>();
        try {
            
            transaction = session.beginTransaction();
            results = (List<Feescategory>) session.createQuery("From Feescategory where (academicyear='"+academicYear+"' or academicyear='"+nextYear+"') and branchid="+branchId).list();
            transaction.commit();
        } catch (Exception hibernateException) { transaction.rollback(); log.error(hibernateException.getMessage(), hibernateException);
            
            hibernateException.printStackTrace();
        } finally {
    			HibernateUtil.closeSession();
            return results;
        }
	}

	@SuppressWarnings("finally")
	public boolean create(List<Feescategory> feesCategoryList) {
		Session session = HibernateUtil.openCurrentSession();
	    Transaction transaction = null;
		boolean result = false;
		try {
            //this.session = sessionFactory.openCurrentSession();
            transaction = session.beginTransaction();
            for (Feescategory feescategory : feesCategoryList) {
            	session.save(feescategory);
			}
            transaction.commit();
            result = true;
        } catch (Exception hibernateException) { transaction.rollback(); log.error(hibernateException.getMessage(), hibernateException);
            
            hibernateException.printStackTrace();
        } finally {
    			HibernateUtil.closeSession();
            return result;
        }
	}
	
	public void deleteOtherFeesCategory(List<Integer> ids, List<Integer> feesCatId, String sid) {
		Session session = HibernateUtil.openCurrentSession();
	    Transaction transaction = null;
		List<Feescollection> feesCollection = new ArrayList<Feescollection>();
		try {
			transaction = session.beginTransaction();
			Query queryOne = session.createQuery("from Otherfeescollection as feescollection where feescollection.sid = '"+sid+"' and feescollection.sfsid IN (:ids)");
			queryOne.setParameterList("ids", ids);
			feesCollection = queryOne.list();
			
			if(feesCollection.isEmpty()){
				Query query = session.createQuery("delete from Studentotherfeesstructure as fees where fees.sid = "+sid+" and fees.otherfeescategory.idfeescategory IN (:feescat)");
				query.setParameterList("feescat", feesCatId);
				query.executeUpdate();
			}
			transaction.commit();
		} catch (Exception hibernateException) { transaction.rollback(); log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
	}
	
	@SuppressWarnings("finally")
	public boolean createOtherFeescategory(List<OtherFeecategory> feesCategoryList) {
		boolean result = false;
		Transaction transaction = null;
		try {
			Session session = HibernateUtil.openCurrentSession();
            transaction = session.beginTransaction();
            for (OtherFeecategory feescategory : feesCategoryList) {
            	session.save(feescategory);
			}
            transaction.commit();
            result = true;
        } catch (Exception hibernateException) { transaction.rollback(); log.error(hibernateException.getMessage(), hibernateException);
            
            hibernateException.printStackTrace();
        } finally {
    			HibernateUtil.closeSession();
            return result;
        }
	}

	public List<OtherFeecategory> getOtherFeeCategory(String className, String searchYear, String branchId) {
		Transaction transaction = null;
		List <OtherFeecategory> result= new ArrayList();
		try {
			Session session = HibernateUtil.openCurrentSession();
			transaction = session.beginTransaction();
			Query query = session
					.createQuery("from OtherFeecategory where particularname like '"+className+"--%' and academicyear = '"+searchYear+"' and branchid='"+branchId+"'");
			result=query.list();
			transaction.commit();

		} catch (Exception hibernateException) { transaction.rollback(); log.error(hibernateException.getMessage(), hibernateException);

			hibernateException.printStackTrace();

		} finally {
				HibernateUtil.closeSession();
			return result;

		}
	}

}

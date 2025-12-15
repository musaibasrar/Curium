package org.ideoholic.curium.model.feescategory.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.ideoholic.curium.model.account.dto.VoucherEntrytransactions;
import org.ideoholic.curium.model.feescategory.dto.Concession;
import org.ideoholic.curium.model.feescategory.dto.Feescategory;
import org.ideoholic.curium.model.feescategory.dto.OtherFeecategory;
import org.ideoholic.curium.model.feescollection.dto.Feescollection;
import org.ideoholic.curium.model.stampfees.dto.Academicotherfeesstructure;
import org.ideoholic.curium.model.student.dto.Studentotherfeesstructure;
import org.ideoholic.curium.repositories.AcademicFeesStructureRepository;
import org.ideoholic.curium.repositories.AcademicOtherFeesStructureRepository;
import org.ideoholic.curium.repositories.FeesCategoryRepository;
import org.ideoholic.curium.repositories.FeesCollectionRepository;
import org.ideoholic.curium.repositories.OtherFeecategoryRepository;
import org.ideoholic.curium.repositories.OtherfeescollectionRepository;
import org.ideoholic.curium.repositories.StudentFeesStructureRepository;
import org.ideoholic.curium.repositories.StudentOtherFeesStructureRepository;
import org.ideoholic.curium.repositories.VoucherEntryTransactionsRepository;
import org.ideoholic.curium.util.QueryUtil;
import org.springframework.stereotype.Component;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class FeesCategoryDAO {

	private final FeesCategoryRepository feesCatRepo;
	private final FeesCollectionRepository feescollectionRepo;
	private final StudentFeesStructureRepository studentFeesStructureRepo;
	private final VoucherEntryTransactionsRepository voucherEntryTransactionsRepo;
	private final AcademicFeesStructureRepository academicfeesstructureRepo;
	private final OtherFeecategoryRepository otherFeecategoryRepo;
	private final StudentOtherFeesStructureRepository studentOtherFeesStructureRepo;
	private final AcademicOtherFeesStructureRepository academicotherfeesstructureRepo;
	private final OtherfeescollectionRepository otherfeescollectionRepo;
	private final QueryUtil queryUtil;
	
	@Transactional
	public List<Feescategory> readListOfObjects(int branchId, String academicYear) {
		
		List<Feescategory> results = new ArrayList<Feescategory>();
		try {
			// session.createQuery("From Feescategory where academicyear='"+academicYear+"' and branchid="+branchId).list();
			results = feesCatRepo.findByAcademicyearAndBranchid(academicYear, branchId);
		} catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();

			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
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

			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
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

			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
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
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); 
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
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
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
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
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
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
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
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
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
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}

	}
	
	@Transactional
	public List <Feescategory> getfeecategoryofstudent(String classname, String searchYear, String branchId)
	{
		List <Feescategory> result= new ArrayList<>();
		try {
			//Query query = session.createQuery("from Feescategory where particularname like '"+classname+"--%' and academicyear = '"+searchYear+"' and branchid='"+branchId+"'");
			result = feesCatRepo.findFeecategoryOfStudent(classname, searchYear, Integer.parseInt(branchId));
		}  catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return result;
	}
	
	@Transactional
	public void applyotherConcession(List<Concession> concessionList, String sid) {
		try {
			for (Concession concession : concessionList) {
				// Query query = session.createQuery("update Studentotherfeesstructure as fees set fees.concession='"+Integer.parseInt(concession.getConcession())+"' where fees.sfsid='"+concession.getSfsid()+"'");
				Studentotherfeesstructure studentotherfeesstructure = studentOtherFeesStructureRepo.findById(concession.getSfsid()).orElse(null);
				if (studentotherfeesstructure != null) {
					studentotherfeesstructure.setConcession(Integer.parseInt(concession.getConcession()));
				}
				studentOtherFeesStructureRepo.save(studentotherfeesstructure);
				// Query queryAcademicFees = session.createQuery("update Academicotherfeesstructure as academicfees set academicfees.totalfees=academicfees.totalfees+'"+Integer.parseInt(concession.getConcessionOld())+"'-'"+Integer.parseInt(concession.getConcession())+"' where academicfees.sid='"+sid+"'");
				Academicotherfeesstructure academicotherfeesstructure = academicotherfeesstructureRepo.findById(Integer.parseInt(sid)).orElse(null);
				if (academicotherfeesstructure != null) {
					Integer totalFees = Integer.parseInt(concession.getConcessionOld()) - Integer.parseInt(concession.getConcession());
					totalFees += Integer.parseInt(academicotherfeesstructure.getTotalfees());
					academicotherfeesstructure.setTotalfees(String.valueOf(totalFees));
					academicotherfeesstructureRepo.save(academicotherfeesstructure);
				}
			}
		} catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
	}

	@Transactional
	public List<Feescategory> readListOfFeeCategory(int branchId, String academicYear, String nextYear) {
		List<Feescategory> results = new ArrayList<Feescategory>();
        try {
            
           // results = (List<Feescategory>) session.createQuery("From Feescategory where (academicyear='"+academicYear+"' or academicyear='"+nextYear+"') and branchid="+branchId).list();
        	results = feesCatRepo.findByBranchidAndAcademicyearIn(branchId, Arrays.asList(academicYear, nextYear));
        }catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        } 
        return results;
	}

	@Transactional
	public boolean create(List<Feescategory> feesCategoryList) {
		boolean result = false;
		try {
            for (Feescategory feescategory : feesCategoryList) {
            	feesCatRepo.save(feescategory);
			}
            result = true;
        } catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        } 
		 return result;
	}
	
	@Transactional
	public void deleteOtherFeesCategory(List<Integer> ids, List<Integer> feesCatId, String sid) {
        int sId = Integer.parseInt(sid);
		List<Feescollection> feesCollection = new ArrayList<Feescollection>();
		try {
			//Query queryOne = session.createQuery("from Otherfeescollection as feescollection where feescollection.sid = '"+sid+"' and feescollection.sfsid IN (:ids)");
			feesCollection = otherfeescollectionRepo.findByStudentSidAndOtherFeesStructureIn(sId, ids);
			
			if(feesCollection.isEmpty()){
				//Query query = session.createQuery("delete from Studentotherfeesstructure as fees where fees.sid = "+sid+" and fees.otherfeescategory.idfeescategory IN (:feescat)");
				studentOtherFeesStructureRepo.deleteBySidAndSfsidIn(sId, ids);
			}
		}  catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
	}
	
	@Transactional
	public boolean createOtherFeescategory(List<OtherFeecategory> feesCategoryList) {
	    boolean result = false;
	    try {
	        // Session session = HibernateUtil.openCurrentSession();
	        // transaction = session.beginTransaction();
	        for (OtherFeecategory feescategory : feesCategoryList) {
	            // session.save(feescategory);
	            otherFeecategoryRepo.save(feescategory);
	        }
	        // transaction.commit();
	        result = true;
	    } catch (Exception hibernateException) {
	        log.error(hibernateException.getMessage(), hibernateException);
	        hibernateException.printStackTrace();
	        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
	    }
	    return result;
	}

	@Transactional
	public List<OtherFeecategory> getOtherFeeCategory(String className, String searchYear, String branchId) {
		List <OtherFeecategory> result= new ArrayList<>();
		try {
			// Query query = session.createQuery("from OtherFeecategory where particularname like '"+className+"--%' and academicyear = '"+searchYear+"' and branchid='"+branchId+"'");
			result = otherFeecategoryRepo.findByClassNamePrefixAndAcademicYearAndBranchId(className, searchYear, branchId);

		}  catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return result;
	}

}

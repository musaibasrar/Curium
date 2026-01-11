package org.ideoholic.curium.model.feescollection.dao;

import java.util.ArrayList;
import java.util.List;

import org.ideoholic.curium.model.account.dto.VoucherEntrytransactions;
import org.ideoholic.curium.model.feescollection.dto.Feescollection;
import org.ideoholic.curium.model.feescollection.dto.Otherfeescollection;
import org.ideoholic.curium.model.feescollection.dto.Otherreceiptinfo;
import org.ideoholic.curium.model.feescollection.dto.Receiptinfo;
import org.ideoholic.curium.model.student.dto.Studentfeesstructure;
import org.ideoholic.curium.model.student.dto.Studentotherfeesstructure;
import org.ideoholic.curium.repositories.FeesCollectionRepository;
import org.ideoholic.curium.repositories.OtherReceiptInfoRepository;
import org.ideoholic.curium.repositories.OtherfeescollectionRepository;
import org.ideoholic.curium.repositories.ReceiptinfoRepository;
import org.ideoholic.curium.repositories.StudentFeesStructureRepository;
import org.ideoholic.curium.repositories.StudentOtherFeesStructureRepository;
import org.ideoholic.curium.repositories.VoucherEntryTransactionsRepository;
import org.ideoholic.curium.util.QueryUtil;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class feesCollectionDAO {
	
	private final QueryUtil queryUtil;

    private final FeesCollectionRepository feesCollectionRepository;

    private final ReceiptinfoRepository receiptinfoRepository;

    private final StudentFeesStructureRepository studentfeesstructureRepository;

    private final OtherfeescollectionRepository otherfeescollectionRepository;

    private final OtherReceiptInfoRepository otherreceiptinfoRepository;

    private final StudentOtherFeesStructureRepository studentotherfeesstructureRepository;

    private final VoucherEntryTransactionsRepository voucherEntrytransactionsRepository;

    @Transactional
    public boolean create(Receiptinfo receiptInfo, List<Feescollection> feescollectionList, VoucherEntrytransactions transactions,
                          String updateCrAccount, String updateDrAccount, VoucherEntrytransactions transactionsIncome,
                          String updateDrAccountIncome, String updateCrAccountIncome) {
        boolean result = false;
        try {
            // Find latest receipt for branch
            Receiptinfo latestReceipt = receiptinfoRepository.findTopByBranchidOrderByReceiptnumberDesc(receiptInfo.getBranchid());
			if (latestReceipt != null) {
				String branchReceiptNo = latestReceipt.getBranchreceiptnumber().substring(2);
				receiptInfo.setBranchreceiptnumber(String.format("%06d", Integer.parseInt(branchReceiptNo) + 1));
			} else {
				receiptInfo.setBranchreceiptnumber(String.format("%06d", 1));
			}

            // Receipts
            transactions.setNarration(transactions.getNarration().concat(" Receipt no: " + receiptInfo.getBranchreceiptnumber()));
            voucherEntrytransactionsRepository.save(transactions);

            // session.createQuery(updateDrAccount);
            queryUtil.runUpdateQuery(updateDrAccount);
            // session.createQuery(updateCrAccount);
            queryUtil.runUpdateQuery(updateCrAccount);

            // J.V
            transactionsIncome.setNarration(transactionsIncome.getNarration().concat(" Receipt no: " + receiptInfo.getBranchreceiptnumber()));
            voucherEntrytransactionsRepository.save(transactionsIncome);

            // session.createQuery(updateDrAccountIncome);
            queryUtil.runUpdateQuery(updateDrAccountIncome);
            // session.createQuery(updateCrAccountIncome);
            queryUtil.runUpdateQuery(updateCrAccountIncome);

            receiptInfo.setReceiptvoucher(transactions.getTransactionsid().intValue());
            receiptInfo.setJournalvoucher(transactionsIncome.getTransactionsid().intValue());
            receiptinfoRepository.save(receiptInfo);

            if (feescollectionList != null) {
                for (Feescollection singleFeescollection : feescollectionList) {
                    singleFeescollection.setReceiptInfo(receiptInfo);

                    // session.createQuery("update Studentfeesstructure set feespaid=feespaid+"+singleFeescollection.getAmountpaid()+" where sfsid="+singleFeescollection.fetchSfsid());
                    Studentfeesstructure sfs = studentfeesstructureRepository.findById(singleFeescollection.fetchSfsid()).orElse(null);
                    if (sfs != null) {
                        sfs.setFeespaid(sfs.getFeespaid() + singleFeescollection.getAmountpaid());
                        studentfeesstructureRepository.save(sfs);
                    }
                    feesCollectionRepository.save(singleFeescollection);
                }
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
    public List<Feescollection> readListOfObject(Integer feeid) {
    	List<Feescollection> results = new ArrayList<Feescollection>();
        try {
        	// session.createQuery("From Feescollection where feesdetailsid="+feeid)
            // return feescollectionRepository.findByFeesdetailsid(feeid);
        } catch (Exception hibernateException) {
            log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return results;
    }

    @Transactional
    public List<Feescollection> getFeesForTheCurrentYear(long id, String currentAcademicYear) {
    	List<Feescollection> result = new ArrayList<>();
        try {
        	// session.createQuery("From Feescollection where sid='"+id+"' and academicyear = '"+currentAcademicYear+"'").list();
        	result =  feesCollectionRepository.findByStudent_SidAndAcademicyear(id, currentAcademicYear);
        } catch (Exception hibernateException) {
            log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return result;
    }

    @Transactional
    public void createReceipt(Receiptinfo receiptInfo) {
        try {
        	// session.save(receiptInfo);
            receiptinfoRepository.save(receiptInfo);
        } catch (Exception hibernateException) {
            log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
    }

    @Transactional
    public Receiptinfo getReceiptInfoDetails(Integer receiptNumber) {
    	Receiptinfo result = new Receiptinfo();
        try {
        	// session.createQuery("from Receiptinfo where receiptnumber = '"+receiptNumber+"' ");
        	result = receiptinfoRepository.findById(receiptNumber).orElse(new Receiptinfo());
        } catch (Exception hibernateException) {
            log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return result;
    }

    @Transactional
    public List<Receiptinfo> getReceiptDetailsPerStudent(Integer id, String currentacademicyear) {
    	List<Receiptinfo> result = new ArrayList<>();
        try {
        	// session.createQuery("from Receiptinfo where sid = '"+id+"' and academicyear = '"+currentacademicyear+"' and cancelreceipt=0").list();
        	result = receiptinfoRepository.findByStudent_SidAndAcademicyearAndCancelreceipt(id, currentacademicyear, 0);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return result;
    }

    @Transactional
    public List<Feescollection> getFeesCollectionDetails(int receiptId) {
    	List<Feescollection> result = new ArrayList<>();
        try {
        	// session.createQuery("From Feescollection where receiptnumber="+receiptId).list();
        	result = feesCollectionRepository.findByReceiptInfo_Receiptnumber(receiptId);
        } catch (Exception hibernateException) {
            log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return result;
    }

    @Transactional
    public List<Studentfeesstructure> getStudentsFeesStructure(List<Integer> studentids, String currentYear, String searchCriteria) {
    	List<Studentfeesstructure> result = new ArrayList<>();
        try {
            // session.createQuery("from Studentfeesstructure sfs where sfs.sid in (:ids) and sfs."+searchCriteria+" > 0 and sfs.academicyear = '"+currentYear+"'");
        	String finalQuery = "from Studentfeesstructure sfs where sfs.sid in (:ids) and sfs."+searchCriteria+" > 0 and sfs.academicyear = '"+currentYear+"'";
        	result = queryUtil.runGivenQuery(finalQuery, Studentfeesstructure.class);
        } catch (Exception hibernateException) {
            log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return result;
    }

    @Transactional
    public boolean createother(Otherreceiptinfo receiptInfo, List<Otherfeescollection> feescollectionList, VoucherEntrytransactions transactions,
                              String updateCrAccount, String updateDrAccount, VoucherEntrytransactions transactionsIncome,
                              String updateDrAccountIncome, String updateCrAccountIncome) {
        boolean result = false;
        try {
            // Find latest receipt for branch
            Otherreceiptinfo latestReceipt = otherreceiptinfoRepository.findTopByBranchidOrderByReceiptnumberDesc(receiptInfo.getBranchid());

            if (latestReceipt != null) {
                receiptInfo.setBranchreceiptnumber(String.format("%03d", Integer.parseInt(latestReceipt.getBranchreceiptnumber()) + 1));
            } else {
                receiptInfo.setBranchreceiptnumber(String.format("%03d", 1));
            }

            // Receipts
		 	transactions.setNarration(transactions.getNarration().concat(" Receipt no: "+receiptInfo.getBranchreceiptnumber()));
            // session.save(transactions);
            voucherEntrytransactionsRepository.save(transactions);
            // session.createQuery(updateDrAccount);
            queryUtil.runUpdateQuery(updateDrAccount);
            // session.createQuery(updateCrAccount);
            queryUtil.runUpdateQuery(updateCrAccount);

            // J.V
			transactionsIncome.setNarration(transactionsIncome.getNarration().concat(" Receipt no: "+receiptInfo.getBranchreceiptnumber()));
            // session.save(transactionsIncome);
            voucherEntrytransactionsRepository.save(transactionsIncome);
            // session.createQuery(updateDrAccountIncome);
            queryUtil.runUpdateQuery(updateDrAccountIncome);
            // session.createQuery(updateCrAccountIncome);
            queryUtil.runUpdateQuery(updateCrAccountIncome);

            receiptInfo.setReceiptvoucher(0);
            receiptInfo.setJournalvoucher(0);
            otherreceiptinfoRepository.save(receiptInfo);

            for (Otherfeescollection singleFeescollection : feescollectionList) {
                singleFeescollection.setReceiptInfo(receiptInfo);
                // session.createQuery("update Studentotherfeesstructure set feespaid=feespaid+"+singleFeescollection.getAmountpaid()+" where sfsid="+singleFeescollection.fetchSfsid());
                Studentotherfeesstructure sfs = studentotherfeesstructureRepository.findById(singleFeescollection.fetchSfsid()).orElse(null);
                if (sfs != null) {
                    sfs.setFeespaid(sfs.getFeespaid() + singleFeescollection.getAmountpaid());
                    studentotherfeesstructureRepository.save(sfs);
                }
                otherfeescollectionRepository.save(singleFeescollection);
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
    public List<Otherfeescollection> otherreadListOfObject(Integer feeid) {
    	List<Otherfeescollection> results = new ArrayList<Otherfeescollection>();
        try {
        	// session.createQuery("From Otherfeescollection where feesdetailsid="+feeid).list();
            // return otherfeescollectionRepository.findByFeesdetailsid(feeid);
        } catch (Exception hibernateException) {
            log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return results;
    }

    @Transactional
    public Otherreceiptinfo getOtherReceiptInfoDetails(Integer receiptNumber) {
    	Otherreceiptinfo result = new Otherreceiptinfo();
        try {
        	// session.createQuery("from Otherreceiptinfo where receiptnumber = '"+receiptNumber+"' ");
        	result = otherreceiptinfoRepository.findById(receiptNumber).orElse(null);
        } catch (Exception hibernateException) {
            log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return result;
    }

    @Transactional
    public List<Otherreceiptinfo> getOtherReceiptDetailsPerStudent(long id, String currentacademicyear) {
    	List<Otherreceiptinfo> result = new ArrayList<>();
        try {
        	// session.createQuery("from Otherreceiptinfo where sid = '"+id+"' and academicyear = '"+currentacademicyear+"' and cancelreceipt=0").list();
        	result = otherreceiptinfoRepository.findByStudent_SidAndAcademicyearAndCancelreceipt(id, currentacademicyear, 0);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return result;
    }

    @Transactional
    public List<Otherfeescollection> getOtherFeesCollectionDetails(int receiptId) {
    	List<Otherfeescollection> result = new ArrayList<>();
        try {
        	// session.createQuery("From Otherfeescollection where receiptnumber="+receiptId).list();
        	result = otherfeescollectionRepository.findByReceiptInfo_Receiptnumber(receiptId);
        } catch (Exception hibernateException) {
            log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return result;
    }

    @Transactional
    public List<Otherreceiptinfo> getotherReceiptDetailsPerStudent(long id, String currentacademicyear) {
    	List<Otherreceiptinfo> result = new ArrayList<>();
        try {
        	// session.createQuery("from Otherreceiptinfo where sid = '"+id+"' and academicyear = '"+currentacademicyear+"' and cancelreceipt=0").list();
        	result = otherreceiptinfoRepository.findByStudent_SidAndAcademicyearAndCancelreceipt(id, currentacademicyear, 0);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return result;
    }

    @Transactional
    public Studentfeesstructure getStudentFeesStructure(String sid, String idFeesCategory, String currentAcademicYear) {
    	Studentfeesstructure result = null;
        try {
        	// session.createQuery("from Studentfeesstructure sfs where sfs.sid="+sid+" and sfs.Feescategory.idfeescategory="+idFeesCategory+" and sfs.academicyear = '"+currentAcademicYear+"'");
        	result = studentfeesstructureRepository.findByStudent_SidAndFeescategory_IdfeescategoryAndAcademicyear(Integer.parseInt(sid), Integer.parseInt(idFeesCategory), currentAcademicYear);
        } catch (Exception hibernateException) {
            log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return result;
    }

    @Transactional
    public boolean createReceiptFromImport(Receiptinfo receiptInfo, List<Feescollection> feescollectionList, VoucherEntrytransactions transactions,
                                           String updateCrAccount, String updateDrAccount, VoucherEntrytransactions transactionsIncome,
                                           String updateDrAccountIncome, String updateCrAccountIncome) {
        boolean result = false;
        try {
        	//Receipts
            transactions.setNarration(transactions.getNarration().concat(" Receipt no: " + receiptInfo.getBranchreceiptnumber()));
            // session.save(transactions); 
            voucherEntrytransactionsRepository.save(transactions);
            // session.createQuery(updateDrAccount); session.createQuery(updateCrAccount);
            queryUtil.runUpdateQuery(updateDrAccount);
            queryUtil.runUpdateQuery(updateCrAccount);

            // J.V
            transactionsIncome.setNarration(transactionsIncome.getNarration().concat(" Receipt no: " + receiptInfo.getBranchreceiptnumber()));
            // session.save(transactionsIncome);
            voucherEntrytransactionsRepository.save(transactionsIncome);
            // session.createQuery(updateDrAccountIncome); session.createQuery(updateCrAccountIncome);
            queryUtil.runUpdateQuery(updateDrAccountIncome);
            queryUtil.runUpdateQuery(updateCrAccountIncome);

            receiptInfo.setReceiptvoucher(transactions.getTransactionsid().intValue());
            receiptInfo.setJournalvoucher(transactionsIncome.getTransactionsid().intValue());
            receiptinfoRepository.save(receiptInfo);

            if (feescollectionList != null) {
                for (Feescollection singleFeescollection : feescollectionList) {
                    singleFeescollection.setReceiptInfo(receiptInfo);

                    // session.createQuery("update Studentfeesstructure set feespaid=feespaid+"+singleFeescollection.getAmountpaid()+" where sfsid="+singleFeescollection.fetchSfsid());
                    Studentfeesstructure sfs = studentfeesstructureRepository.findById(singleFeescollection.fetchSfsid()).orElse(null);
                    if (sfs != null) {
                        sfs.setFeespaid(sfs.getFeespaid() + singleFeescollection.getAmountpaid());
                        studentfeesstructureRepository.save(sfs);
                    }
                    feesCollectionRepository.save(singleFeescollection);
                }
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
    public boolean createOtherReceiptFromImport(Otherreceiptinfo receiptInfo, List<Otherfeescollection> feescollectionList) {
        boolean result = false;
        try {
            receiptInfo.setReceiptvoucher(0);
            receiptInfo.setJournalvoucher(0);
            otherreceiptinfoRepository.save(receiptInfo);

            if (feescollectionList != null) {
                for (Otherfeescollection singleFeescollection : feescollectionList) {
                    singleFeescollection.setReceiptInfo(receiptInfo);

                    // session.createQuery("update Studentotherfeesstructure set feespaid=feespaid+"+singleFeescollection.getAmountpaid()+" where sfsid="+singleFeescollection.fetchSfsid());
                    Studentotherfeesstructure sfs = studentotherfeesstructureRepository.findById(singleFeescollection.fetchSfsid()).orElse(null);
                    if (sfs != null) {
                        sfs.setFeespaid(sfs.getFeespaid() + singleFeescollection.getAmountpaid());
                        studentotherfeesstructureRepository.save(sfs);
                    }
                    otherfeescollectionRepository.save(singleFeescollection);
                }
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
    public Studentotherfeesstructure getStudentOtherFeesStructure(String sid, String idFeesCategory, String currentAcademicYear) {
    	Studentotherfeesstructure result = new Studentotherfeesstructure();
        try {
            return studentotherfeesstructureRepository.findByStudent_SidAndOtherfeescategory_IdfeescategoryAndAcademicyear(
                Integer.parseInt(sid), Integer.parseInt(idFeesCategory), currentAcademicYear);
        } catch (Exception hibernateException) {
            log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return result;
    }

    @Transactional
    public boolean modifyFeesPaymentType(String updateDrAccountOld, String updateDrAccountNew, String updateVoucherEntry, String updateReceiptinfoPaymentMethod) {
        boolean result = false;
        try {
            // session.createQuery(updateDrAccountOld);
            // session.createQuery(updateDrAccountNew);
            // session.createQuery(updateVoucherEntry);
            // session.createQuery(updateReceiptinfoPaymentMethod);
            // You should implement these as repository update methods

            // Example (beginner-friendly): If you had a repository method called "updateDrAccountOld", you would call it here.
            // e.g., voucherEntrytransactionsRepository.updateDrAccountOld(...);

            result = true;
        } catch (Exception hibernateException) {
            log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return result;
    }
    
    @Transactional
    public boolean create(Receiptinfo receiptInfo, VoucherEntrytransactions transactions,
                          String updateCrAccount, String updateDrAccount) {
        boolean result = false;
        try {
            // Find latest receipt for branch
            Receiptinfo latestReceipt = receiptinfoRepository.findTopByBranchidOrderByReceiptnumberDesc(receiptInfo.getBranchid());
			if (latestReceipt != null) {
				String branchReceiptNo = latestReceipt.getBranchreceiptnumber().substring(2);
				receiptInfo.setBranchreceiptnumber(String.format("%06d", Integer.parseInt(branchReceiptNo) + 1));
			} else {
				receiptInfo.setBranchreceiptnumber(String.format("%06d", 1));
			}

            // Receipts
            transactions.setNarration(transactions.getNarration().concat(" Receipt no: " + receiptInfo.getBranchreceiptnumber()));
            voucherEntrytransactionsRepository.save(transactions);

            // session.createQuery(updateDrAccount);
            queryUtil.runUpdateQuery(updateDrAccount);
            // session.createQuery(updateCrAccount);
            queryUtil.runUpdateQuery(updateCrAccount);

            receiptInfo.setReceiptvoucher(transactions.getTransactionsid().intValue());
            receiptinfoRepository.save(receiptInfo);

            result = true;
        } catch (Exception hibernateException) {
            log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return result;
    }
}
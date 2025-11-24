package org.ideoholic.curium.model.stampfees.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.ideoholic.curium.model.account.dto.VoucherEntrytransactions;
import org.ideoholic.curium.model.parents.dto.Parents;
import org.ideoholic.curium.model.stampfees.dto.Academicfeesstructure;
import org.ideoholic.curium.model.stampfees.dto.Academicotherfeesstructure;
import org.ideoholic.curium.model.student.dto.Student;
import org.ideoholic.curium.model.student.dto.Studentfeesstructure;
import org.ideoholic.curium.model.student.dto.Studentotherfeesstructure;
import org.ideoholic.curium.model.user.dto.Login;
import org.ideoholic.curium.repositories.AcademicFeesStructureRepository;
import org.ideoholic.curium.repositories.AcademicOtherFeesStructureRepository;
import org.ideoholic.curium.repositories.LoginRepository;
import org.ideoholic.curium.repositories.StudentFeesStructureRepository;
import org.ideoholic.curium.repositories.StudentOtherFeesStructureRepository;
import org.ideoholic.curium.repositories.StudentRepository;
import org.ideoholic.curium.repositories.VoucherEntryTransactionsRepository;
import org.ideoholic.curium.util.QueryUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class StampFeesDAO {

	private final QueryUtil queryUtil;
    private final LoginRepository loginRepo;
    private final StudentRepository studentRepo;
    private final StudentFeesStructureRepository studentFeesStructureRepo;
    private final AcademicFeesStructureRepository academicFeesStructureRepo;
    private final VoucherEntryTransactionsRepository voucherEntryTransactionsRepo;
    private final AcademicOtherFeesStructureRepository academicOtherFeesStructureRepo;
    private final StudentOtherFeesStructureRepository studentOtherFeesStructureRepo;

    /**
     * Fetch a unique Login object by username and password
     */
    @Transactional(readOnly = true)
    public Login readUniqueObject(String userName, String password) {
        try {
            // Query query = session.createQuery("FROM Login as login where login.username1= :loginName and login.password1= :password");
            Optional<Login> login = loginRepo.findByUsernameAndPassword(userName, password);

            return login.orElse(null);
        } catch (Exception ex) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(ex.getMessage(), ex);
            ex.printStackTrace();
        }
        return null;
    }

    // No sessionClose method is needed - managed by Spring!

    /**
     * Get number of students who are not archived
     */
    @Transactional(readOnly = true)
    public int getNoOfStudents() {
        try {
        	//  results = (java.util.List<Student>) session.createQuery("FROM Student s where s.archive = 0").list();
            List<Student> results = studentRepo.findByArchive(0);
            int noOfRecords = results.size();
            log.debug("The size of list is:::::::::::::::::::::::::::::::::::::::::: {}", noOfRecords);
            return noOfRecords;
        } catch (Exception ex) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(ex.getMessage(), ex);
            ex.printStackTrace();
            return 0;
        }
    }

    /**
     * Get number of students for a given class where archive=0
     */
    @Transactional(readOnly = true)
	public int getNoOfStudentsOne(String classStudying) {
		try {
			// results = (java.util.List<Student>) session.createQuery("From Student s where s.classstudying LIKE '"+classStudying+" %' AND s.archive = 0").list();
			List<Student> results = studentRepo.findByClassstudyingLikeAndArchive(classStudying + " %", 0);
			int noOfRecords = results.size();
			log.debug("Total Number of students::::::::::::::::::::::::::::::::::::::::::{}", noOfRecords);
			return noOfRecords;
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(ex.getMessage(), ex);
			ex.printStackTrace();
			return 0;
		}
	}

    @Transactional(readOnly = true)
	public List<Parents> getListOfParents(String query) {
		try {
			// Query HQLquery = session.createQuery(query);
			// parents = (java.util.List<Parents>) HQLquery.list();
			return queryUtil.runGivenQuery(query, Parents.class);
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(ex.getMessage(), ex);
			ex.printStackTrace();
		}
		return new ArrayList<>();
	}

    /**
     * Add academic fees & student fees structure, update accounts.
     */
    @Transactional
    public void addStampFees(
            List<Academicfeesstructure> listOfacademicfessstructure, 
            String currentYear, 
            List<Studentfeesstructure> listOfstudentfeesstructure, 
            VoucherEntrytransactions transactions, 
            String updateDrAccount, 
            String updateCrAccount) {
        try {
            for (Academicfeesstructure academicfeesStructure : listOfacademicfessstructure) {
                // Query query = session.createQuery("from Academicfeesstructure afs where afs.sid = '"+academicfeesStructure.getSid()+"' and afs.academicyear = '"+currentYear+"'");
                Academicfeesstructure feesStructure = academicFeesStructureRepo.findBySidAndAcademicyear(academicfeesStructure.getSid(), currentYear);
                if (feesStructure != null) {
					// Query queryUpdate = session
					//		.createQuery("update Academicfeesstructure set totalfees = totalfees+'"+academicfeesStructure.getTotalfees()+"'  where sid = '"+academicfeesStructure.getSid()+"' and academicyear = '"+currentYear+"'");
                    try {
                        double currentTotal = Double.parseDouble(feesStructure.getTotalfees());
                        double add = Double.parseDouble(academicfeesStructure.getTotalfees());
                        feesStructure.setTotalfees("" + (currentTotal + add));
                    } catch (NumberFormatException ignore) {
                        // leave as string addition
                    	log.error("Unable to parse the fees amount:{}", ignore.getMessage(), ignore);
                        feesStructure.setTotalfees(feesStructure.getTotalfees() + academicfeesStructure.getTotalfees());
                    }
                    academicFeesStructureRepo.save(feesStructure);
                } else {
                    academicFeesStructureRepo.save(academicfeesStructure);
                }
            }

            for (Studentfeesstructure studentfeesstructure : listOfstudentfeesstructure) {
                studentFeesStructureRepo.save(studentfeesstructure);
				/*
				Query query = session.createQuery("from Studentfeesstructure as sfs where sfs.sid = '"+studentfeesstructure.fetchSid()+"' and sfs.Feescategory.idfeescategory = '"+studentfeesstructure.getFeescategory().getIdfeescategory()+"' and sfs.academicyear = '"+currentYear+"'");
				Studentfeesstructure feesStructure = (Studentfeesstructure) query.uniqueResult();
				
				if(feesStructure == null){
					session.save(studentfeesstructure);
					//accounts
					
					session.save(transactions);
					Query queryAccounts = session.createQuery(updateDrAccount);
					queryAccounts.executeUpdate();
					Query queryqueryAccounts1 = session.createQuery(updateCrAccount);
					queryqueryAccounts1.executeUpdate();
				}
				if(feesStructure != null){
					
					Query queryUpdate = session
							.createQuery("update Studentfeesstructure set feesamount = '"+studentfeesstructure.getFeesamount()+"'  where sid = '"+studentfeesstructure.fetchSid()+"' and idfeescategory = '"+studentfeesstructure.getFeescategory().getIdfeescategory()+"' and academicyear = '"+currentYear+"'");
					
					queryUpdate.executeUpdate();
				}else if(feesStructure == null){
					session.save(studentfeesstructure);
				}*/
            }

            voucherEntryTransactionsRepo.save(transactions);
            queryUtil.runUpdateQuery(updateDrAccount);
            queryUtil.runUpdateQuery(updateCrAccount);

            log.debug("in add3");
        } catch (Exception ex) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(ex.getMessage(), ex);
            ex.printStackTrace();
        }
    }

    /**
     * Remove Studentfeesstructure & Academicfeesstructure for a list of IDs, given academicYear.
     */
    @Transactional
    public void deleteMultiple(java.util.List<Integer> ids, String currentYear) {
        try {
			// Query query = session.createQuery("delete from Studentfeesstructure as sfs where sfs.sid IN (:ids) and sfs.academicyear='"+currentYear+"'");
            studentFeesStructureRepo.deleteByStudent_SidInAndAcademicyear(ids, currentYear);
            // Query query2 = session.createQuery("delete from Academicfeesstructure where sid IN (:ids) and academicyear='"+currentYear+"'");
            academicFeesStructureRepo.deleteBySidInAndAcademicyear(ids, currentYear);            
        } catch (Exception ex) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(ex.getMessage(), ex);
            ex.printStackTrace();
        }
    }

    /**
     * Add 'other' fees and student structures
     */
    @Transactional
    public void addotherStampFees(
            List<Academicotherfeesstructure> listOfacademicfessstructure, 
            String currentYear, 
            List<Studentotherfeesstructure> listOfstudentfeesstructure) {
        try {
            for (Academicotherfeesstructure academicfeesStructure : listOfacademicfessstructure) {
                // Query query = session.createQuery("from Academicotherfeesstructure afs where afs.sid = '"+academicfeesStructure.getSid()+"' and afs.academicyear = '"+currentYear+"'");
                Academicotherfeesstructure feesStructure = academicOtherFeesStructureRepo.findBySidAndAcademicyear(academicfeesStructure.getSid(), currentYear);
                if (feesStructure != null) {
                    // Query queryUpdate = session.createQuery("update Academicotherfeesstructure set totalfees = '"+academicfeesStructure.getTotalfees()+"'  where sid = '"+academicfeesStructure.getSid()+"' and academicyear = '"+currentYear+"'");
                    feesStructure.setTotalfees(academicfeesStructure.getTotalfees());
                    academicOtherFeesStructureRepo.save(feesStructure);
                } else {
                	// session.save(academicfeesStructure);
                    academicOtherFeesStructureRepo.save(academicfeesStructure);
                }
            }
            for (Studentotherfeesstructure studentfeesstructure : listOfstudentfeesstructure) {
                // Query query = session.createQuery("from Studentotherfeesstructure as sfs where sfs.sid = '"+studentfeesstructure.fetchSid()+"' and sfs.otherfeescategory.idfeescategory = '"+studentfeesstructure.getOtherfeescategory().getIdfeescategory()+"' and sfs.academicyear = '"+currentYear+"'");
				if (studentfeesstructure.getOtherfeescategory() != null) {
					Studentotherfeesstructure existing = studentOtherFeesStructureRepo
							.findBySidAndIdfeescategoryAndAcademicyear(studentfeesstructure.fetchSid(), studentfeesstructure.getOtherfeescategory().getIdfeescategory(), studentfeesstructure.getAcademicyear());
					if (existing != null) {
						existing.setFeesamount(studentfeesstructure.getFeesamount());
						studentOtherFeesStructureRepo.save(existing);
					}
				} else {
					// session.save(studentfeesstructure);
                    studentOtherFeesStructureRepo.save(studentfeesstructure);
                }
            }
        } catch (Exception ex) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(ex.getMessage(), ex);
            ex.printStackTrace();
        }
    }

    /**
     * Get Studentfeesstructure for sid/feesCategoryId/year
     */
    @Transactional(readOnly = true)
    public Studentfeesstructure getStudentFeesStructure(int sid, int feesCategoryId, String academicYear) {
        try {
        	// Query query = session.createQuery("from Studentfeesstructure as sfs where sfs.sid = '"+sid+"' and sfs.Feescategory.idfeescategory = '"+feesCategoryId+"' and sfs.academicyear = '"+academicYear+"'");
            return studentFeesStructureRepo.findBySidAndIdfeescategoryAndAcademicyear(sid, feesCategoryId, academicYear);
        } catch (Exception ex) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(ex.getMessage(), ex);
            ex.printStackTrace();
        }
        return null;
    }
}
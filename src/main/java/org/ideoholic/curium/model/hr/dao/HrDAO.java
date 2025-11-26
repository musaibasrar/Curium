package org.ideoholic.curium.model.hr.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.ideoholic.curium.model.hr.dto.Leaveapplication;
import org.ideoholic.curium.model.hr.dto.Leavedetails;
import org.ideoholic.curium.model.hr.dto.Leavetypemaster;
import org.ideoholic.curium.model.hr.dto.Payadvancesalary;
import org.ideoholic.curium.model.hr.dto.Paybasic;
import org.ideoholic.curium.model.hr.dto.Payhead;
import org.ideoholic.curium.model.hr.dto.Payheadstaffdetails;
import org.ideoholic.curium.model.hr.dto.Pf;
import org.ideoholic.curium.model.hr.dto.Processsalarydetails;
import org.ideoholic.curium.model.hr.dto.Processsalarydetailsheads;
import org.ideoholic.curium.repositories.LeaveApplicationRepository;
import org.ideoholic.curium.repositories.LeaveDetailsRepository;
import org.ideoholic.curium.repositories.LeaveTypeMasterRepository;
import org.ideoholic.curium.repositories.PayAdvanceSalaryRepository;
import org.ideoholic.curium.repositories.PayHeadRepository;
import org.ideoholic.curium.repositories.PayHeadStaffDetailsRepository;
import org.ideoholic.curium.repositories.PaybasicRepository;
import org.ideoholic.curium.repositories.PfRepository;
import org.ideoholic.curium.repositories.ProcessSalaryDetailsHeadsRepository;
import org.ideoholic.curium.repositories.ProcessSalaryDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class HrDAO {

    @Autowired
    private LeaveTypeMasterRepository leaveTypeMasterRepository;

    @Autowired
    private LeaveDetailsRepository leaveDetailsRepository;

    @Autowired
    private LeaveApplicationRepository leaveApplicationRepository;

    @Autowired
    private PayHeadRepository payHeadRepository;

    @Autowired
    private PayAdvanceSalaryRepository payAdvanceSalaryRepository;

    @Autowired
    private PayHeadStaffDetailsRepository payHeadStaffDetailsRepository;

    @Autowired
    private PaybasicRepository payBasicRepository;

    @Autowired
    private PfRepository pfRepository;

    @Autowired
    private ProcessSalaryDetailsRepository processSalaryDetailsRepository;

    @Autowired
    private ProcessSalaryDetailsHeadsRepository processSalaryDetailsHeadsRepository;


    @Transactional
    public List<Leavetypemaster> readListOfLeaveTypes(int branchId) {
        List<Leavetypemaster> list = new ArrayList<Leavetypemaster>();

        try {
            // session.createQuery("From Leavetypemaster where branchid="+branchId).list();
            list = leaveTypeMasterRepository.findByBranchid(branchId);
        } catch (Exception hibernateException) {
            log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return list;
    }

    @Transactional
    public boolean saveLeaveType(Leavetypemaster leaveMaster) {
        try {
            // session.save(leaveMaster);
            leaveTypeMasterRepository.save(leaveMaster);
            return true;
        } catch (Exception hibernateException) {
            log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();

            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return false;
    }

    @Transactional
    public boolean deleteLeaveType(Leavetypemaster leaveType) {
        try {
            // session.delete(leaveType);
            leaveTypeMasterRepository.delete(leaveType);
            return true;
        } catch (Exception hibernateException) {
            log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();

            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return false;
    }

    @Transactional
    public boolean addLeaves(List<Leavedetails> leaveDetailsList) {
        try {
            // for (Leavedetails leavedetails : leaveDetailsList) {
            // session.save(leavedetails);
            // }
            leaveDetailsRepository.saveAll(leaveDetailsList);
            return true;
        } catch (Exception hibernateException) {
            log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();

            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return false;
    }

    @Transactional
    public List<Leavedetails> getLeaveDetails(String teacherId, String academicYear) {
        List<Leavedetails> leaveDetailsList = new ArrayList<Leavedetails>();
        try {
            // session.createQuery("From Leavedetails where idteacher="+teacherId+" and academicyear='"+academicYear+"'").list();
            leaveDetailsList = leaveDetailsRepository.findByTeacherTidAndAcademicyear(teacherId, academicYear);
        } catch (Exception hibernateException) {
            log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();

            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return leaveDetailsList;

    }

    @Transactional
    public boolean savePayHead(Payhead payHead) {
        try {
            // session.save(payHead);
            payHeadRepository.save(payHead);
            return true;
        } catch (Exception hibernateException) {
            log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();

            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return false;
    }

    @Transactional
    public List<Payhead> getPayHeadList(String academicYear, int branchId) {
    	List<Payhead> result = new ArrayList<>();
        try {
            // session.createQuery("from Payhead where academicyear='"+academicYear+"' and branchid="+branchId).list();
            return payHeadRepository.findByAcademicyearAndBranchid(academicYear, branchId);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return result;
    }

    @Transactional
    public boolean addPayHeadStaffDetails(List<Payheadstaffdetails> payHeadStaffDetailsList) {
        try {
            // for (Payheadstaffdetails payheadstaffdetails : payHeadStaffDetailsList) {
            //     session.save(payheadstaffdetails);
            // }
            payHeadStaffDetailsRepository.saveAll(payHeadStaffDetailsList);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return false;
    }

    @Transactional
    public boolean savePayBasic(List<Paybasic> payBasicList) {
        try {
            // for (Paybasic payBasic : payBasicList) {
            //     session.save(payBasic);
            // }
            payBasicRepository.saveAll(payBasicList);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return false;
    }

    @Transactional
    public void addPf(Pf pf) {
        try {
            // session.save(pf);
            pfRepository.save(pf);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
    }

    @Transactional
    public List<Pf> pfSettings(int branchId) {
    	List<Pf> result = new ArrayList<>();
        try {
            // session.createQuery("From Pf where branchid = "+branchId+" order by date Desc").list();
            return pfRepository.findByBranchidOrderByDateDesc(branchId);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return result;
    }

    @Transactional
    public void deletePf(List<Integer> ids) {
        try {
            // session.createQuery("delete from Pf where idpf IN (:ids)");
            pfRepository.deleteAllById(ids);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
    }

    @Transactional
    public boolean saveAdvanceSalary(Payadvancesalary payAdvanceSalary) {
        try {
            // session.save(payAdvanceSalary);
            payAdvanceSalaryRepository.save(payAdvanceSalary);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return false;
    }

    @Transactional
    public List<Payadvancesalary> salaryApprovalDispaly(int branchId) {
    	List<Payadvancesalary> result = new ArrayList<>();
        try {
            // session.createQuery("from Payadvancesalary where status='apply' and branchid = "+branchId).list();
            return payAdvanceSalaryRepository.findByStatusAndBranchid("apply", branchId);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return result;
    }

    @Transactional
    public boolean saveAdvanceSalaryApproval(Payadvancesalary payAdvance) {
        try {
            // Query query= session.createSQLQuery("update hr_payadvancesalary set reason = '"+payAdvance.getReason()+"',status = '"+payAdvance.getStatus()+"' where idpayadvancesalary="+payAdvance.getIdpayadvancesalary());
            // query.executeUpdate();
            Payadvancesalary adv = payAdvanceSalaryRepository.findById(payAdvance.getIdpayadvancesalary()).orElse(null);
            if (adv != null) {
                adv.setReason(payAdvance.getReason());
                adv.setStatus(payAdvance.getStatus());
                payAdvanceSalaryRepository.save(adv);
                return true;
            }
            return false;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return false;
    }

    @Transactional
    public boolean deleteAdvaceSalaryApproval(Payadvancesalary payAdvance) {
        try {
            // session.delete(payAdvance);
            payAdvanceSalaryRepository.delete(payAdvance);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return false;
    }

    @Transactional
    public List<Payadvancesalary> salaryIssue(int branchId) {
    	List<Payadvancesalary> result = new ArrayList<>();
        try {
            // session.createQuery("from Payadvancesalary where status='approved' or status='rejected' and branchid="+branchId).list();
            List<String> statuses = new ArrayList<>();
            statuses.add("approved");
            statuses.add("rejected");
            return payAdvanceSalaryRepository.findByBranchidAndStatusIn(branchId, statuses);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return result;
    }

    @Transactional
    public boolean applyLeave(Leaveapplication leaveApplication) {
        try {
            // session.save(leaveApplication);
            leaveApplicationRepository.save(leaveApplication);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return false;
    }

    @Transactional
    public List<Leaveapplication> leaveApprovals(String currentAcademicYear, int branchId) {
    	List<Leaveapplication> result = new ArrayList<>();
        try {
            // session.createQuery("from Leaveapplication where academicyear='"+currentAcademicYear+"' and branchid="+branchId).list();
            return leaveApplicationRepository.findByAcademicyearAndBranchid(currentAcademicYear, branchId);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return result;
    }

    @Transactional
	public boolean rejectLeave(List<Integer> ids) {
		try {
			// Query query= session.createSQLQuery("update hr_leaveapplication set status = 'rejected' where idleaveapplication IN (:ids)");
			for (Integer id : ids) {
				leaveApplicationRepository.findById(id).map(leaveApplication -> {
					leaveApplication.setStatus("rejected");
					return leaveApplicationRepository.save(leaveApplication);
				});
			}
			return true;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return false;
	}

    @Transactional
	public boolean approveLeave(List<Integer> ids) {
		try {
			// Query query= session.createSQLQuery("update hr_leaveapplication set status = 'approved' where idleaveapplication IN (:ids)");
			for (Integer id : ids) {
				leaveApplicationRepository.findById(id).map(leaveApplication -> {
					leaveApplication.setStatus("approved");
					return leaveApplicationRepository.save(leaveApplication);
				});
			}
			return true;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return false;
	}

    @Transactional
    public boolean processStaffSalary(List<Processsalarydetails> processsalarydetailsList, List<Processsalarydetailsheads> processSalarydetailsheadList) {
        try {
            // for (Processsalarydetails processsalarydetails : processsalarydetailsList) {
            //     session.save(processsalarydetails);
            //     for (Processsalarydetailsheads processsalarydetailsheads : processSalarydetailsheadList) {
            //         processsalarydetailsheads.setProcesssalarydetails(processsalarydetails);
            //         session.save(processsalarydetailsheads);
            //     }
            // }
            for (Processsalarydetails processsalarydetails : processsalarydetailsList) {
                Processsalarydetails saved = processSalaryDetailsRepository.save(processsalarydetails);
                for (Processsalarydetailsheads heads : processSalarydetailsheadList) {
                    heads.setProcesssalarydetails(saved);
                    processSalaryDetailsHeadsRepository.save(heads);
                }
            }
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return false;
    }

    @Transactional
    public List<Payhead> getPayHeadListDynamic(String payHeadType, String academicYear, int branchId) {
    	List<Payhead> result = new ArrayList<>();
        try {
            // session.createQuery("from Payhead where payheadtype='"+payHeadType+"' and academicyear='"+academicYear+"' and branchid="+branchId).list();
            return payHeadRepository.findByPayheadtypeAndAcademicyearAndBranchid(payHeadType, academicYear, branchId);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return result;
    }

    @Transactional
    public Paybasic getBasicPay(int idteacher, String academicYear) {
        try {
            // Query query = session.createSQLQueryEntity("select * from hr_paybasic where idteacher = "+idteacher+" and academicyear='"+academicYear+"' ORDER BY idpaybasic DESC LIMIT 1",Paybasic.class);
            // basicPay = (Paybasic) query.uniqueResult();
            return payBasicRepository.findByTeacherTidAndAcademicyear(idteacher, academicYear);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return null;
    }

    @Transactional
    public List<Payheadstaffdetails> getPayHeadStaff(int teacherid, String academicYear) {
    	List<Payheadstaffdetails> result = new ArrayList<>();
        try {
            // session.createQuery("from Payheadstaffdetails where idteacher = "+teacherid+" and academicyear='"+academicYear+"'").list();
            return payHeadStaffDetailsRepository.findByTeacherTidAndAcademicyear(teacherid, academicYear);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return result;
    }

    @Transactional
    public List<Processsalarydetails> issueStaffSalary(String academicYear, int branchId) {
    	List<Processsalarydetails> result = new ArrayList<>();
        try {
            // session.createQuery("from Processsalarydetails where academicyear='"+academicYear+"' and branchid="+branchId).list();
            return processSalaryDetailsRepository.findByAcademicyearAndBranchid(academicYear, branchId);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return result;
    }

    @Transactional
    public Processsalarydetails getProcessSalaryDetails(int processId) {
        try {
            // Query query = session.createQuery("from Processsalarydetails where idprocesssalarydetails="+processId+"");
            // processSalaryDetails = (Processsalarydetails) query.uniqueResult();
            return processSalaryDetailsRepository.findById(processId).orElse(null);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return null;
    }

    @Transactional
    public List<Processsalarydetailsheads> getProcessSalaryHeads(int processId) {
    	List<Processsalarydetailsheads> result = new ArrayList<>();
        try {
            // session.createQuery("from Processsalarydetailsheads where idprocesssalary="+processId+"").list();
            return processSalaryDetailsHeadsRepository.findByProcesssalarydetailsIdprocesssalarydetails(processId);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return result;
    }

    @Transactional
    public Processsalarydetailsheads getProcessSalaryBasicPay(int processId) {
        try {
            // Query query = session.createQuery("from Processsalarydetailsheads where idprocesssalary="+processId+" and payheadname='Basic Pay'");
            // processSalaryHeads = (Processsalarydetailsheads) query.uniqueResult();
            return processSalaryDetailsHeadsRepository.findByProcesssalarydetailsIdprocesssalarydetailsAndPayheadname(processId, "Basic Pay");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return null;
    }

    @Transactional
    public List<Payheadstaffdetails> getStaffDetails(int staffId, String academicYear) {
    	List<Payheadstaffdetails> result = new ArrayList<>();
        try {
            // session.createQuery("from Payheadstaffdetails where idteacher="+staffId+" and academicyear='"+academicYear+"'").list();
            return payHeadStaffDetailsRepository.findByTeacherTidAndAcademicyear(staffId, academicYear);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return result;
    }

    @Transactional
    public List<Processsalarydetails> getStaffinfo(int teacherId) {
    	List<Processsalarydetails> result = new ArrayList<>();
        try {
            // session.createQuery("from Processsalarydetails where teacherid="+teacherId+"").list();
            return processSalaryDetailsRepository.findByTeacherTid(teacherId);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return result;
    }

    @Transactional
    public boolean deletePayHeadStaff(List<Integer> ids) {
        try {
            // Query query = session.createQuery("delete from Payheadstaffdetails where idpayheadstaffdetails IN (:ids)");
        	payHeadStaffDetailsRepository.deleteAllById(ids);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return false;
    }

    @Transactional
	public Processsalarydetails checkprocessedStaffSalary(int staffId, String month, Integer year) {
        try {
            // Query query = session.createQuery("from Processsalarydetails where teacherid="+staffId+" and month='"+month+"' and year='"+year+"'");
            // processSalaryDetails = (Processsalarydetails) query.uniqueResult();
            return processSalaryDetailsRepository.findByTeacherTidAndMonthAndYear(staffId, month, year);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return null;
    }

    @Transactional
    public boolean issueProcessedSalary(List<Integer> ids) {
        try {
            // Query query= session.createSQLQuery("update hr_processsalarydetails set status = 'ISSUED' where idprocesssalarydetails IN (:ids)");
			for (Integer id : ids) {
				processSalaryDetailsRepository.findById(id).map(salaryDetails -> {
					salaryDetails.setStatus("ISSUED");
					return processSalaryDetailsRepository.save(salaryDetails);
				});
			}
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return false;
    }

    @Transactional
    public boolean cancelProcessedSalary(List<Integer> ids) {
        try {
            // Query query= session.createSQLQuery("update hr_processsalarydetails set status = 'CANCELLED' where idprocesssalarydetails IN (:ids)");
			for (Integer id : ids) {
				processSalaryDetailsRepository.findById(id).map(salaryDetails -> {
					salaryDetails.setStatus("CANCELLED");
					return processSalaryDetailsRepository.save(salaryDetails);
				});
			}
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return false;
    }

    @Transactional
    public boolean updatePayBasic(List<Paybasic> payBasicList) {
        try {
            // for (Paybasic payBasic : payBasicList) {
            //     session.update(payBasic);
            // }
            payBasicRepository.saveAll(payBasicList);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return false;
    }
}
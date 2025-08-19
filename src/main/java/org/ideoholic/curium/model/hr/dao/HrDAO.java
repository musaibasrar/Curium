package org.ideoholic.curium.model.hr.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.query.Query;
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
import org.ideoholic.curium.util.HibernateUtil;
import org.ideoholic.curium.util.Session;
import org.ideoholic.curium.util.Session.Transaction;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class HrDAO {

	public List<Leavetypemaster> readListOfLeaveTypes(int branchId) {
		Transaction transaction = null;
		List<Leavetypemaster> list = new ArrayList<Leavetypemaster>();

		try {
			Session session = HibernateUtil.openCurrentSession();
            transaction = session.beginTransaction();
            list = session.createQuery("From Leavetypemaster where branchid="+branchId).list();
            transaction.commit();
        } catch (Exception hibernateException) { transaction.rollback(); log.error(hibernateException.getMessage(), hibernateException);
            
            hibernateException.printStackTrace();
        } finally {
			HibernateUtil.closeSession();
		}
        return list;
	}

	public boolean saveLeaveType(Leavetypemaster leaveMaster) {
		Transaction transaction = null;
		try { Session session = HibernateUtil.openCurrentSession();
            transaction = session.beginTransaction();
            session.save(leaveMaster);
            transaction.commit();
            return true;
        } catch (Exception hibernateException) { transaction.rollback(); log.error(hibernateException.getMessage(), hibernateException);
            
            hibernateException.printStackTrace();
        }finally {
			HibernateUtil.closeSession();
		}
		return false;
	}

	public boolean deleteLeaveType(Leavetypemaster leaveType) {
		Transaction transaction = null;
		try { Session session = HibernateUtil.openCurrentSession();
            transaction = session.beginTransaction();
            session.delete(leaveType);
            transaction.commit();
            return true;
        } catch (Exception hibernateException) { transaction.rollback(); log.error(hibernateException.getMessage(), hibernateException);
            
            hibernateException.printStackTrace();
        }finally {
			HibernateUtil.closeSession();
		}
		return false;
	}

	public boolean addLeaves(List<Leavedetails> leaveDetailsList) {
		Transaction transaction = null;
		try { Session session = HibernateUtil.openCurrentSession();
            transaction = session.beginTransaction();
            for (Leavedetails leavedetails : leaveDetailsList) {
            	session.save(leavedetails);
			}
            transaction.commit();
            return true;
        } catch (Exception hibernateException) { transaction.rollback(); log.error(hibernateException.getMessage(), hibernateException);
            
            hibernateException.printStackTrace();
        } finally {
			HibernateUtil.closeSession();
		}
		return false;
	}

	public List<Leavedetails> getLeaveDetails(String teacherId, String academicYear) {
		List<Leavedetails> leaveDetailsList = new ArrayList<Leavedetails>();
		Transaction transaction = null;
		try { Session session = HibernateUtil.openCurrentSession();
            transaction = session.beginTransaction();
            leaveDetailsList = session.createQuery("From Leavedetails where idteacher="+teacherId+" and academicyear='"+academicYear+"'").list();
            transaction.commit();
        } catch (Exception hibernateException) { transaction.rollback(); log.error(hibernateException.getMessage(), hibernateException);
            
            hibernateException.printStackTrace();
        } finally {
			HibernateUtil.closeSession();
		}
		return leaveDetailsList;
		
	}

	public boolean savePayHead(Payhead payHead) {
		Transaction transaction = null;
		try { Session session = HibernateUtil.openCurrentSession();
            transaction = session.beginTransaction();
            session.save(payHead);
            transaction.commit();
            return true;
        } catch (Exception hibernateException) { transaction.rollback(); log.error(hibernateException.getMessage(), hibernateException);
            
            hibernateException.printStackTrace();
        } finally {
			HibernateUtil.closeSession();
		}
		return false;
	}

	public List<Payhead> getPayHeadList(String academicYear, int branchId) {
		List<Payhead> payHead = new ArrayList<Payhead>();
		Transaction transaction = null;
		try { Session session = HibernateUtil.openCurrentSession();
			transaction = session.beginTransaction();
			payHead = session.createQuery("from Payhead where academicyear='"+academicYear+"' and branchid="+branchId).list();
			transaction.commit();
		} catch (Exception e) { transaction.rollback(); log.error(e.getMessage(), e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		
		return payHead;
	}

	public boolean addPayHeadStaffDetails(
			List<Payheadstaffdetails> payHeadStaffDetailsList) {
		Transaction transaction = null;
		try { Session session = HibernateUtil.openCurrentSession();
			transaction = session.beginTransaction();
			for (Payheadstaffdetails payheadstaffdetails : payHeadStaffDetailsList) {
				session.save(payheadstaffdetails);
			}
			transaction.commit();
			return true;
		} catch (Exception e) { transaction.rollback(); log.error(e.getMessage(), e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return false;		
	}

	public boolean savePayBasic(List<Paybasic> payBasicList) {
		Transaction transaction = null;
			try { Session session = HibernateUtil.openCurrentSession();
				transaction = session.beginTransaction();
				for (Paybasic payBasic : payBasicList) {
					session.save(payBasic);
				}
				transaction.commit();
				return true;
			} catch (Exception e) { transaction.rollback(); log.error(e.getMessage(), e);
				e.printStackTrace();
			}finally {
				HibernateUtil.closeSession();
			}
			return false;		
	}

	public void addPf(Pf pf) {
		Transaction transaction = null;
		try { Session session = HibernateUtil.openCurrentSession();
			transaction = session.beginTransaction();
			session.save(pf);
			transaction.commit();
		} catch (Exception e) { transaction.rollback(); log.error(e.getMessage(), e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		
	}

	public List<Pf> pfSettings(int branchId) {
		Transaction transaction = null;
		List<Pf> pf = new ArrayList<Pf>();
		
		try { Session session = HibernateUtil.openCurrentSession();
			transaction = session.beginTransaction();
			pf = session.createQuery("From Pf where branchid = "+branchId+" order by date Desc").list();
			transaction.commit();
		} catch (Exception e) { transaction.rollback(); log.error(e.getMessage(), e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return pf;
	}

	public void deletePf(List ids) {
		Transaction transaction = null;
		try { Session session = HibernateUtil.openCurrentSession();
			transaction = session.beginTransaction();
			Query query = session.createQuery("delete from Pf where idpf IN (:ids)");
			query.setParameterList("ids", ids);
			query.executeUpdate();
			transaction.commit();
		} catch (Exception hibernateException) { transaction.rollback(); log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		
	}

	public boolean saveAdvanceSalary(Payadvancesalary payAdvanceSalary) {
		Transaction transaction = null;
		try { Session session = HibernateUtil.openCurrentSession();
			transaction = session.beginTransaction();
			session.save(payAdvanceSalary);
			transaction.commit();
			return true;
		} catch (Exception e) { transaction.rollback(); log.error(e.getMessage(), e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return false;
	}

	public List<Payadvancesalary> salaryApprovalDispaly(int branchId) {
		Transaction transaction = null;
		List<Payadvancesalary> payAdvanceSalary = new ArrayList<Payadvancesalary>();
		try { Session session = HibernateUtil.openCurrentSession();
			transaction = session.beginTransaction();
			payAdvanceSalary = session.createQuery("from Payadvancesalary where status='apply' and branchid = "+branchId).list();
			transaction.commit();
		} catch (Exception e) { transaction.rollback(); log.error(e.getMessage(), e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return payAdvanceSalary;
	}

	public boolean saveAdvanceSalaryApproval(Payadvancesalary payAdvance) {
		Transaction transaction = null;
		try { Session session = HibernateUtil.openCurrentSession();
			transaction = session.beginTransaction();
			Query query= session.createSQLQuery("update hr_payadvancesalary set reason = '"+payAdvance.getReason()+"',status = '"+payAdvance.getStatus()+"' where idpayadvancesalary="+payAdvance.getIdpayadvancesalary());
			query.executeUpdate();
			transaction.commit();
			return true;
		} catch (Exception e) { transaction.rollback(); log.error(e.getMessage(), e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return false;
	}

	public boolean deleteAdvaceSalaryApproval(Payadvancesalary payAdvance) {
		Transaction transaction = null;
		try { Session session = HibernateUtil.openCurrentSession();
            transaction = session.beginTransaction();
            session.delete(payAdvance);
            transaction.commit();
            return true;
        } catch (Exception hibernateException) { transaction.rollback(); log.error(hibernateException.getMessage(), hibernateException);
            
            hibernateException.printStackTrace();
        }finally {
			HibernateUtil.closeSession();
		}
		return false;
	}

	public List<Payadvancesalary> salaryIssue(int branchId) {
		Transaction transaction = null;
		List<Payadvancesalary> payAdvanceSalary = new ArrayList<Payadvancesalary>();
		try { Session session = HibernateUtil.openCurrentSession();
			transaction = session.beginTransaction();
			payAdvanceSalary = session.createQuery("from Payadvancesalary where status='approved' or status='rejected' and branchid="+branchId).list();
			transaction.commit();
		} catch (Exception e) { transaction.rollback(); log.error(e.getMessage(), e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return payAdvanceSalary;
	}

	public boolean applyLeave(Leaveapplication leaveApplication) {
		Transaction transaction = null;
		try { Session session = HibernateUtil.openCurrentSession();
			transaction = session.beginTransaction();
			session.save(leaveApplication);
			transaction.commit();
			return true;
		} catch (Exception e) { transaction.rollback(); log.error(e.getMessage(), e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return false;
	}

	public List<Leaveapplication> leaveApprovals(String currentAcademicYear, int branchId) {
		Transaction transaction = null;
		List<Leaveapplication> listLeaveApplication = new ArrayList<Leaveapplication>();
		
		try { Session session = HibernateUtil.openCurrentSession();
			transaction = session.beginTransaction();
			listLeaveApplication = session.createQuery("from Leaveapplication where academicyear='"+currentAcademicYear+"' and branchid="+branchId).list();
			transaction.commit();
		} catch (Exception e) { transaction.rollback(); log.error(e.getMessage(), e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return listLeaveApplication;
	}

	public boolean rejectLeave(List ids) {
		Transaction transaction = null;
		try { Session session = HibernateUtil.openCurrentSession();
			transaction = session.beginTransaction();
			Query query= session.createSQLQuery("update hr_leaveapplication set status = 'rejected' where idleaveapplication IN (:ids)");
			query.setParameterList("ids", ids);
			query.executeUpdate();
			transaction.commit();
			return true;
		} catch (Exception e) { transaction.rollback(); log.error(e.getMessage(), e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return false;
		
	}

	public boolean approveLeave(List ids) {
		Transaction transaction = null;
		try { Session session = HibernateUtil.openCurrentSession();
			transaction = session.beginTransaction();
			Query query= session.createSQLQuery("update hr_leaveapplication set status = 'approved' where idleaveapplication IN (:ids)");
			query.setParameterList("ids", ids);
			query.executeUpdate();
			transaction.commit();
			return true;
		} catch (Exception e) { 
			transaction.rollback(); 
			log.error(e.getMessage(), e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return false;
		
	}

	public boolean processStaffSalary(List<Processsalarydetails> processsalarydetailsList, List<Processsalarydetailsheads> processSalarydetailsheadList) {
		Transaction transaction = null;
		try { Session session = HibernateUtil.openCurrentSession();
			transaction = session.beginTransaction();
			for (Processsalarydetails processsalarydetails : processsalarydetailsList) {
				session.save(processsalarydetails);
				for (Processsalarydetailsheads processsalarydetailsheads : processSalarydetailsheadList) {
					processsalarydetailsheads.setProcesssalarydetails(processsalarydetails);
					session.save(processsalarydetailsheads);
				}
			}
			transaction.commit();
			return true;
		} catch (Exception e) { transaction.rollback(); log.error(e.getMessage(), e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return false;
		
	}

	public List<Payhead> getPayHeadListDynamic(String payHeadType, String academicYear, int branchId) {
			List<Payhead> payHead = new ArrayList<Payhead>();
			Transaction transaction = null;
			try { Session session = HibernateUtil.openCurrentSession();
				transaction = session.beginTransaction();
				payHead = session.createQuery("from Payhead where payheadtype='"+payHeadType+"' and academicyear='"+academicYear+"' and branchid="+branchId).list();
				transaction.commit();
			} catch (Exception e) { transaction.rollback(); log.error(e.getMessage(), e);
				e.printStackTrace();
			}finally {
				HibernateUtil.closeSession();
			}			
			return payHead;
	}

	public Paybasic getBasicPay(int idteacher, String academicYear) {
		Transaction transaction = null;
		Paybasic basicPay = new Paybasic();
		
		try { Session session = HibernateUtil.openCurrentSession();
			transaction = session.beginTransaction();
			Query query = session.createSQLQueryEntity("select * from hr_paybasic where idteacher = "+idteacher+" and academicyear='"+academicYear+"' ORDER BY idpaybasic DESC LIMIT 1",Paybasic.class);
			basicPay = (Paybasic) query.uniqueResult();
			transaction.commit();
		} catch (Exception e) { 
			transaction.rollback(); 
			log.error(e.getMessage(), e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return basicPay;
	}

	public List<Payheadstaffdetails> getPayHeadStaff(int teacherid, String academicYear) {
		Transaction transaction = null;
		List<Payheadstaffdetails> payHeadStaffList = new ArrayList<Payheadstaffdetails>();
		
		try { Session session = HibernateUtil.openCurrentSession();
			transaction = session.beginTransaction();
			payHeadStaffList = session.createQuery("from Payheadstaffdetails where idteacher = "+teacherid+" and academicyear='"+academicYear+"'").list();
			transaction.commit();
		} catch (Exception e) { transaction.rollback(); log.error(e.getMessage(), e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return payHeadStaffList;
	}

	public List<Processsalarydetails> issueStaffSalary(String academicYear, int branchId) {
		Transaction transaction = null;
	List<Processsalarydetails> processSalaryDetails = new ArrayList<Processsalarydetails>();
		
		try { Session session = HibernateUtil.openCurrentSession();
			transaction = session.beginTransaction();
			processSalaryDetails = session.createQuery("from Processsalarydetails where academicyear='"+academicYear+"' and branchid="+branchId).list();
			transaction.commit();
		} catch (Exception e) { transaction.rollback(); log.error(e.getMessage(), e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return processSalaryDetails;
	}

	public Processsalarydetails getProcessSalaryDetails(int processId) {
		Transaction transaction = null;
		Processsalarydetails processSalaryDetails = new Processsalarydetails();
		
		try { Session session = HibernateUtil.openCurrentSession();
			transaction = session.beginTransaction();
			Query query = session.createQuery("from Processsalarydetails where idprocesssalarydetails="+processId+"");
			processSalaryDetails = (Processsalarydetails) query.uniqueResult();
			transaction.commit();
		} catch (Exception e) { transaction.rollback(); log.error(e.getMessage(), e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return processSalaryDetails;
	}

	public List<Processsalarydetailsheads> getProcessSalaryHeads(int processId) {
		Transaction transaction = null;
		List<Processsalarydetailsheads> processSalaryHeadsList = new ArrayList<Processsalarydetailsheads>();
		
		try { Session session = HibernateUtil.openCurrentSession();
			transaction = session.beginTransaction();
			processSalaryHeadsList = session.createQuery("from Processsalarydetailsheads where idprocesssalary="+processId+"").list();
			transaction.commit();
		} catch (Exception e) { transaction.rollback(); log.error(e.getMessage(), e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return processSalaryHeadsList;
	}

	public Processsalarydetailsheads getProcessSalaryBasicPay(int processId) {
		Transaction transaction = null;
		Processsalarydetailsheads processSalaryHeads = new Processsalarydetailsheads();
		
		try { Session session = HibernateUtil.openCurrentSession();
			transaction = session.beginTransaction();
			Query query = session.createQuery("from Processsalarydetailsheads where idprocesssalary="+processId+" and payheadname='Basic Pay'");
			processSalaryHeads = (Processsalarydetailsheads) query.uniqueResult();
			transaction.commit();
		} catch (Exception e) { transaction.rollback(); log.error(e.getMessage(), e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return processSalaryHeads;
	}

	public List<Payheadstaffdetails> getStaffDetails(int staffId, String academicYear) {
		Transaction transaction = null;
		List<Payheadstaffdetails> PayHeadStaffDetailsList = new ArrayList<Payheadstaffdetails>();
		
		try { Session session = HibernateUtil.openCurrentSession();
			transaction = session.beginTransaction();
			PayHeadStaffDetailsList = session.createQuery("from Payheadstaffdetails where idteacher="+staffId+" and academicyear='"+academicYear+"'").list();
			transaction.commit();
		} catch (Exception e) { transaction.rollback(); log.error(e.getMessage(), e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return PayHeadStaffDetailsList;
	}

	public List<Processsalarydetails> getStaffinfo(int teacherId) {
		Transaction transaction = null;
		List<Processsalarydetails> processSalaryDetails = new ArrayList<Processsalarydetails>();
		
		try { Session session = HibernateUtil.openCurrentSession();
			transaction = session.beginTransaction();
			processSalaryDetails = session.createQuery("from Processsalarydetails where teacherid="+teacherId+"").list();
			transaction.commit();
		} catch (Exception e) { transaction.rollback(); log.error(e.getMessage(), e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return processSalaryDetails;
	}

	public boolean deletePayHeadStaff(List ids) {
		Transaction transaction = null;
		try { Session session = HibernateUtil.openCurrentSession();
			transaction = session.beginTransaction();
			Query query = session.createQuery("delete from Payheadstaffdetails where idpayheadstaffdetails IN (:ids)");
			query.setParameterList("ids", ids);
			query.executeUpdate();
			transaction.commit();
			return true;
			
			} catch (Exception hibernateException) { transaction.rollback(); log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return false;
	}

	public Processsalarydetails checkprocessedStaffSalary(int staffId, String month, String year) {
		Transaction transaction = null;
		Processsalarydetails processSalaryDetails = new Processsalarydetails();
		
		try { Session session = HibernateUtil.openCurrentSession();
			transaction = session.beginTransaction();
			Query query = session.createQuery("from Processsalarydetails where teacherid="+staffId+" and month='"+month+"' and year='"+year+"'");
			processSalaryDetails = (Processsalarydetails) query.uniqueResult();
			transaction.commit();
		} catch (Exception e) { transaction.rollback(); log.error(e.getMessage(), e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return processSalaryDetails;
	}

	public boolean issueProcessedSalary(List ids) {
		Transaction transaction = null;
		try { Session session = HibernateUtil.openCurrentSession();
			transaction = session.beginTransaction();
			Query query= session.createSQLQuery("update hr_processsalarydetails set status = 'ISSUED' where idprocesssalarydetails IN (:ids)");
			query.setParameterList("ids", ids);
			query.executeUpdate();
			transaction.commit();
			return true;
		} catch (Exception e) { transaction.rollback(); log.error(e.getMessage(), e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return false;
	}

	public boolean cancelProcessedSalary(List ids) {
		Transaction transaction = null;
		try { Session session = HibernateUtil.openCurrentSession();
			transaction = session.beginTransaction();
			Query query= session.createSQLQuery("update hr_processsalarydetails set status = 'CANCELLED' where idprocesssalarydetails IN (:ids)");
			query.setParameterList("ids", ids);
			query.executeUpdate();
			transaction.commit();
			return true;
		} catch (Exception e) { transaction.rollback(); log.error(e.getMessage(), e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return false;
	}

	public boolean updatePayBasic(List<Paybasic> payBasicList) {
		Transaction transaction = null;
		try { Session session = HibernateUtil.openCurrentSession();
			transaction = session.beginTransaction();
			for (Paybasic payBasic : payBasicList) {
				session.update(payBasic);
			}
			transaction.commit();
			return true;
		} catch (Exception e) { transaction.rollback(); log.error(e.getMessage(), e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return false;		
}
	
}

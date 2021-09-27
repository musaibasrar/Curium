package com.model.hr.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.util.Session;
import org.hibernate.SessionFactory;
import com.util.Session.Transaction;
import org.hibernate.query.Query;

import com.model.hr.dto.Leaveapplication;
import com.model.hr.dto.Leavedetails;
import com.model.hr.dto.Leavetypemaster;
import com.model.hr.dto.Payadvancesalary;
import com.model.hr.dto.Paybasic;
import com.model.hr.dto.Payhead;
import com.model.hr.dto.Payheadstaffdetails;
import com.model.hr.dto.Pf;
import com.model.hr.dto.Processsalarydetails;
import com.model.hr.dto.Processsalarydetailsheads;
import com.util.HibernateUtil;

public class HrDAO {

	
	Session session = null;
    /**
     * * Hibernate Session Variable
     */
    Transaction transaction = null;
    /**
     * * Hibernate Transaction Variable
     */
  
    SessionFactory sessionFactory;
    
    private static final Logger logger = LogManager.getLogger(HrDAO.class);

	public HrDAO() {
		session = HibernateUtil.openCurrentSession();
	}

	public List<Leavetypemaster> readListOfLeaveTypes(int branchId) {
		
		List<Leavetypemaster> list = new ArrayList<Leavetypemaster>();

		try {
            transaction = session.beginTransaction();
            list = session.createQuery("From Leavetypemaster where branchid="+branchId).list();
            transaction.commit();
        } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
            
            hibernateException.printStackTrace();
        } finally {
			HibernateUtil.closeSession();
		}
        return list;
	}

	public boolean saveLeaveType(Leavetypemaster leaveMaster) {
		
		try {
            transaction = session.beginTransaction();
            session.save(leaveMaster);
            transaction.commit();
            return true;
        } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
            
            hibernateException.printStackTrace();
        }finally {
			HibernateUtil.closeSession();
		}
		return false;
	}

	public boolean deleteLeaveType(Leavetypemaster leaveType) {
		try {
            transaction = session.beginTransaction();
            session.delete(leaveType);
            transaction.commit();
            return true;
        } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
            
            hibernateException.printStackTrace();
        }finally {
			HibernateUtil.closeSession();
		}
		return false;
	}

	public boolean addLeaves(List<Leavedetails> leaveDetailsList) {

		try {
            transaction = session.beginTransaction();
            for (Leavedetails leavedetails : leaveDetailsList) {
            	session.save(leavedetails);
			}
            transaction.commit();
            return true;
        } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
            
            hibernateException.printStackTrace();
        } finally {
			HibernateUtil.closeSession();
		}
		return false;
	}

	public List<Leavedetails> getLeaveDetails(String teacherId, String academicYear) {
		List<Leavedetails> leaveDetailsList = new ArrayList<Leavedetails>();
		
		try {
            transaction = session.beginTransaction();
            leaveDetailsList = session.createQuery("From Leavedetails where idteacher="+teacherId+" and academicyear='"+academicYear+"'").list();
            transaction.commit();
        } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
            
            hibernateException.printStackTrace();
        } finally {
			HibernateUtil.closeSession();
		}
		return leaveDetailsList;
		
	}

	public boolean savePayHead(Payhead payHead) {
		
		try {
            transaction = session.beginTransaction();
            session.save(payHead);
            transaction.commit();
            return true;
        } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
            
            hibernateException.printStackTrace();
        } finally {
			HibernateUtil.closeSession();
		}
		return false;
	}

	public List<Payhead> getPayHeadList(String academicYear, int branchId) {
		List<Payhead> payHead = new ArrayList<Payhead>();
		
		try {
			transaction = session.beginTransaction();
			payHead = session.createQuery("from Payhead where academicyear='"+academicYear+"' and branchid="+branchId).list();
			transaction.commit();
		} catch (Exception e) { transaction.rollback(); logger.error(e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		
		return payHead;
	}

	public boolean addPayHeadStaffDetails(
			List<Payheadstaffdetails> payHeadStaffDetailsList) {
		
		try {
			transaction = session.beginTransaction();
			for (Payheadstaffdetails payheadstaffdetails : payHeadStaffDetailsList) {
				session.save(payheadstaffdetails);
			}
			transaction.commit();
			return true;
		} catch (Exception e) { transaction.rollback(); logger.error(e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return false;		
	}

	public boolean savePayBasic(List<Paybasic> payBasicList) {
			
			try {
				transaction = session.beginTransaction();
				for (Paybasic payBasic : payBasicList) {
					session.save(payBasic);
				}
				transaction.commit();
				return true;
			} catch (Exception e) { transaction.rollback(); logger.error(e);
				e.printStackTrace();
			}finally {
				HibernateUtil.closeSession();
			}
			return false;		
	}

	public void addPf(Pf pf) {

		try {
			transaction = session.beginTransaction();
			session.save(pf);
			transaction.commit();
		} catch (Exception e) { transaction.rollback(); logger.error(e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		
	}

	public List<Pf> pfSettings(int branchId) {
		
		List<Pf> pf = new ArrayList<Pf>();
		
		try {
			transaction = session.beginTransaction();
			pf = session.createQuery("From Pf where branchid = "+branchId+" order by date Desc").list();
			transaction.commit();
		} catch (Exception e) { transaction.rollback(); logger.error(e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return pf;
	}

	public void deletePf(List ids) {
		
		try {
			transaction = session.beginTransaction();
			Query query = session.createQuery("delete from Pf where idpf IN (:ids)");
			query.setParameterList("ids", ids);
			query.executeUpdate();
			transaction.commit();
		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			hibernateException.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		
	}

	public boolean saveAdvanceSalary(Payadvancesalary payAdvanceSalary) {

		try {
			transaction = session.beginTransaction();
			session.save(payAdvanceSalary);
			transaction.commit();
			return true;
		} catch (Exception e) { transaction.rollback(); logger.error(e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return false;
	}

	public List<Payadvancesalary> salaryApprovalDispaly(int branchId) {
		
		List<Payadvancesalary> payAdvanceSalary = new ArrayList<Payadvancesalary>();
		try {
			transaction = session.beginTransaction();
			payAdvanceSalary = session.createQuery("from Payadvancesalary where status='apply' and branchid = "+branchId).list();
			transaction.commit();
		} catch (Exception e) { transaction.rollback(); logger.error(e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return payAdvanceSalary;
	}

	public boolean saveAdvanceSalaryApproval(Payadvancesalary payAdvance) {

		try {
			transaction = session.beginTransaction();
			Query query= session.createSQLQuery("update hr_payadvancesalary set reason = '"+payAdvance.getReason()+"',status = '"+payAdvance.getStatus()+"' where idpayadvancesalary="+payAdvance.getIdpayadvancesalary());
			query.executeUpdate();
			transaction.commit();
			return true;
		} catch (Exception e) { transaction.rollback(); logger.error(e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return false;
	}

	public boolean deleteAdvaceSalaryApproval(Payadvancesalary payAdvance) {
		try {
            transaction = session.beginTransaction();
            session.delete(payAdvance);
            transaction.commit();
            return true;
        } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
            
            hibernateException.printStackTrace();
        }finally {
			HibernateUtil.closeSession();
		}
		return false;
	}

	public List<Payadvancesalary> salaryIssue(int branchId) {
		List<Payadvancesalary> payAdvanceSalary = new ArrayList<Payadvancesalary>();
		try {
			transaction = session.beginTransaction();
			payAdvanceSalary = session.createQuery("from Payadvancesalary where status='approved' or status='rejected' and branchid="+branchId).list();
			transaction.commit();
		} catch (Exception e) { transaction.rollback(); logger.error(e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return payAdvanceSalary;
	}

	public boolean applyLeave(Leaveapplication leaveApplication) {
		
		try {
			transaction = session.beginTransaction();
			session.save(leaveApplication);
			transaction.commit();
			return true;
		} catch (Exception e) { transaction.rollback(); logger.error(e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return false;
	}

	public List<Leaveapplication> leaveApprovals(String currentAcademicYear, int branchId) {
		
		List<Leaveapplication> listLeaveApplication = new ArrayList<Leaveapplication>();
		
		try {
			transaction = session.beginTransaction();
			listLeaveApplication = session.createQuery("from Leaveapplication where academicyear='"+currentAcademicYear+"' and branchid="+branchId).list();
			transaction.commit();
		} catch (Exception e) { transaction.rollback(); logger.error(e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return listLeaveApplication;
	}

	public boolean rejectLeave(List ids) {
		
		try {
			transaction = session.beginTransaction();
			Query query= session.createSQLQuery("update hr_leaveapplication set status = 'rejected' where idleaveapplication IN (:ids)");
			query.setParameterList("ids", ids);
			query.executeUpdate();
			transaction.commit();
			return true;
		} catch (Exception e) { transaction.rollback(); logger.error(e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return false;
		
	}

	public boolean approveLeave(List ids) {
		
		try {
			transaction = session.beginTransaction();
			Query query= session.createSQLQuery("update hr_leaveapplication set status = 'approved' where idleaveapplication IN (:ids)");
			query.setParameterList("ids", ids);
			query.executeUpdate();
			transaction.commit();
			return true;
		} catch (Exception e) { 
			transaction.rollback(); 
			logger.error(e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return false;
		
	}

	public boolean processStaffSalary(List<Processsalarydetails> processsalarydetailsList, List<Processsalarydetailsheads> processSalarydetailsheadList) {

		try {
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
		} catch (Exception e) { transaction.rollback(); logger.error(e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return false;
		
	}

	public List<Payhead> getPayHeadListDynamic(String payHeadType, String academicYear, int branchId) {
			List<Payhead> payHead = new ArrayList<Payhead>();
			
			try {
				transaction = session.beginTransaction();
				payHead = session.createQuery("from Payhead where payheadtype='"+payHeadType+"' and academicyear='"+academicYear+"' and branchid="+branchId).list();
				transaction.commit();
			} catch (Exception e) { transaction.rollback(); logger.error(e);
				e.printStackTrace();
			}finally {
				HibernateUtil.closeSession();
			}			
			return payHead;
	}

	public Paybasic getBasicPay(int idteacher, String academicYear) {
		
		Paybasic basicPay = new Paybasic();
		
		try {
			transaction = session.beginTransaction();
			Query query = session.createSQLQueryEntity("select * from hr_paybasic where idteacher = "+idteacher+" and academicyear='"+academicYear+"' ORDER BY idpaybasic DESC LIMIT 1",Paybasic.class);
			basicPay = (Paybasic) query.uniqueResult();
			transaction.commit();
		} catch (Exception e) { 
			transaction.rollback(); 
			logger.error(e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return basicPay;
	}

	public List<Payheadstaffdetails> getPayHeadStaff(int teacherid, String academicYear) {
		
		List<Payheadstaffdetails> payHeadStaffList = new ArrayList<Payheadstaffdetails>();
		
		try {
			transaction = session.beginTransaction();
			payHeadStaffList = session.createQuery("from Payheadstaffdetails where idteacher = "+teacherid+" and academicyear='"+academicYear+"'").list();
			transaction.commit();
		} catch (Exception e) { transaction.rollback(); logger.error(e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return payHeadStaffList;
	}

	public List<Processsalarydetails> issueStaffSalary(String academicYear, int branchId) {
		
	List<Processsalarydetails> processSalaryDetails = new ArrayList<Processsalarydetails>();
		
		try {
			transaction = session.beginTransaction();
			processSalaryDetails = session.createQuery("from Processsalarydetails where academicyear='"+academicYear+"' and branchid="+branchId).list();
			transaction.commit();
		} catch (Exception e) { transaction.rollback(); logger.error(e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return processSalaryDetails;
	}

	public Processsalarydetails getProcessSalaryDetails(int processId) {
		
		Processsalarydetails processSalaryDetails = new Processsalarydetails();
		
		try {
			transaction = session.beginTransaction();
			Query query = session.createQuery("from Processsalarydetails where idprocesssalarydetails="+processId+"");
			processSalaryDetails = (Processsalarydetails) query.uniqueResult();
			transaction.commit();
		} catch (Exception e) { transaction.rollback(); logger.error(e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return processSalaryDetails;
	}

	public List<Processsalarydetailsheads> getProcessSalaryHeads(int processId) {
		
		List<Processsalarydetailsheads> processSalaryHeadsList = new ArrayList<Processsalarydetailsheads>();
		
		try {
			transaction = session.beginTransaction();
			processSalaryHeadsList = session.createQuery("from Processsalarydetailsheads where idprocesssalary="+processId+"").list();
			transaction.commit();
		} catch (Exception e) { transaction.rollback(); logger.error(e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return processSalaryHeadsList;
	}

	public Processsalarydetailsheads getProcessSalaryBasicPay(int processId) {
		
		Processsalarydetailsheads processSalaryHeads = new Processsalarydetailsheads();
		
		try {
			transaction = session.beginTransaction();
			Query query = session.createQuery("from Processsalarydetailsheads where idprocesssalary="+processId+" and payheadname='Basic Pay'");
			processSalaryHeads = (Processsalarydetailsheads) query.uniqueResult();
			transaction.commit();
		} catch (Exception e) { transaction.rollback(); logger.error(e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return processSalaryHeads;
	}

	public List<Payheadstaffdetails> getStaffDetails(int staffId, String academicYear) {
		
		List<Payheadstaffdetails> PayHeadStaffDetailsList = new ArrayList<Payheadstaffdetails>();
		
		try {
			transaction = session.beginTransaction();
			PayHeadStaffDetailsList = session.createQuery("from Payheadstaffdetails where idteacher="+staffId+" and academicyear='"+academicYear+"'").list();
			transaction.commit();
		} catch (Exception e) { transaction.rollback(); logger.error(e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return PayHeadStaffDetailsList;
	}

	public List<Processsalarydetails> getStaffinfo(int teacherId) {

		List<Processsalarydetails> processSalaryDetails = new ArrayList<Processsalarydetails>();
		
		try {
			transaction = session.beginTransaction();
			processSalaryDetails = session.createQuery("from Processsalarydetails where teacherid="+teacherId+"").list();
			transaction.commit();
		} catch (Exception e) { transaction.rollback(); logger.error(e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return processSalaryDetails;
	}

	public boolean deletePayHeadStaff(List ids) {
		
		try {
			transaction = session.beginTransaction();
			Query query = session.createQuery("delete from Payheadstaffdetails where idpayheadstaffdetails IN (:ids)");
			query.setParameterList("ids", ids);
			query.executeUpdate();
			transaction.commit();
			return true;
			
			} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			hibernateException.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return false;
	}

	public Processsalarydetails checkprocessedStaffSalary(int staffId, String month, String year) {
		
		Processsalarydetails processSalaryDetails = new Processsalarydetails();
		
		try {
			transaction = session.beginTransaction();
			Query query = session.createQuery("from Processsalarydetails where teacherid="+staffId+" and month='"+month+"' and year='"+year+"'");
			processSalaryDetails = (Processsalarydetails) query.uniqueResult();
			transaction.commit();
		} catch (Exception e) { transaction.rollback(); logger.error(e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return processSalaryDetails;
	}

	public boolean issueProcessedSalary(List ids) {

		try {
			transaction = session.beginTransaction();
			Query query= session.createSQLQuery("update hr_processsalarydetails set status = 'ISSUED' where idprocesssalarydetails IN (:ids)");
			query.setParameterList("ids", ids);
			query.executeUpdate();
			transaction.commit();
			return true;
		} catch (Exception e) { transaction.rollback(); logger.error(e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return false;
	}

	public boolean cancelProcessedSalary(List ids) {
		
		try {
			transaction = session.beginTransaction();
			Query query= session.createSQLQuery("update hr_processsalarydetails set status = 'CANCELLED' where idprocesssalarydetails IN (:ids)");
			query.setParameterList("ids", ids);
			query.executeUpdate();
			transaction.commit();
			return true;
		} catch (Exception e) { transaction.rollback(); logger.error(e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return false;
	}

	public boolean updatePayBasic(List<Paybasic> payBasicList) {
		
		try {
			transaction = session.beginTransaction();
			for (Paybasic payBasic : payBasicList) {
				session.update(payBasic);
			}
			transaction.commit();
			return true;
		} catch (Exception e) { transaction.rollback(); logger.error(e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return false;		
}
	
}

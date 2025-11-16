package org.ideoholic.curium.model.periods.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.ideoholic.curium.model.periods.dto.Perioddetails;
import org.ideoholic.curium.model.periods.dto.Periodmaster;
import org.ideoholic.curium.repositories.PeriodDetailsRepository;
import org.ideoholic.curium.repositories.PeriodMasterRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class PeriodDAO {

	private final PeriodMasterRepository periodMasterRepo;

	private final PeriodDetailsRepository periodDetailsRepo;

	@Transactional
	public boolean save(Periodmaster periodMaster, Map<String, List<Perioddetails>> periodMap) {

		try {
			// session.save(periodMaster);
			periodMasterRepo.save(periodMaster);

			for (Entry<String, List<Perioddetails>> entry : periodMap.entrySet()) {
				for (Perioddetails perioddetails : entry.getValue()) {
					perioddetails.setPeriodMaster(periodMaster);
					perioddetails.setDays(entry.getKey());
					// session.save(perioddetails);
					periodDetailsRepo.save(perioddetails);
				}
			}

			return true;
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return false;
	}

	@Transactional
	public List<Periodmaster> getPeriodsDetails(String currentacademicyear, int branchId) {
		List<Periodmaster> periodMaster = new ArrayList<Periodmaster>();
		try {
			// Hibernate reference
			// periodMaster = session.createQuery("from Periodmaster where
			// academicyear='"+currentacademicyear+"' and branchid="+branchId).list();
			periodMaster = periodMasterRepo.findByAcademicyearAndBranchid(currentacademicyear, branchId);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return periodMaster;
	}

	@Transactional
	public Periodmaster getTimeTable(String periodMasterid) {
		Periodmaster periodMaster = null;
		try {
			// Hibernate reference
			// Query query = session.createQuery("from Periodmaster where
			// idperiodmaster="+periodMasterid).setCacheable(true).setCacheRegion("commonregion");
			// periodMaster = (Periodmaster) query.uniqueResult();
			Integer id = Integer.valueOf(periodMasterid);
			periodMaster = periodMasterRepo.findById(id).orElse(new Periodmaster());
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return periodMaster;
	}

	@Transactional
	public List<Perioddetails> getTimeTablePeriodDetails(String periodMasterid) {
		List<Perioddetails> periodDetailsList = new ArrayList<Perioddetails>();
		try {
			Integer id = Integer.valueOf(periodMasterid);
			periodDetailsList = periodDetailsRepo.findByPeriodMasterIdperiodmasterOrderByIdperioddetailsAsc(id);
			// Hibernate reference
			// periodDetailsList = session.createQuery("from Perioddetails where
			// periodmasterid="+periodMasterid+" order by idperioddetails ASC").list();
			// transaction.commit();
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return periodDetailsList;
	}

	@Transactional
	public boolean deletePeriods(List<Integer> periodMasterid) {
		try {
			// Hibernate reference:
			// Query query = session.createQuery("delete from Periodmaster as period where
			// period.idperiodmaster IN (:ids)");
			// query.setParameterList("ids", periodMasterid);
			// query.executeUpdate();

			periodMasterRepo.deleteAllById(periodMasterid);

			return true;
		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}
		return false;
	}

	@Transactional
	public List<Perioddetails> getPeriodDetailsForTeacher(String teacherName) {
		List<Perioddetails> periodDetailsList = new ArrayList<Perioddetails>();
		try {
			// Hibernate reference:
			// periodDetailsList = session.createQuery("from Perioddetails where staff='"+teacherName+"' order by idperioddetails ASC").list();
			periodDetailsList = periodDetailsRepo.findByStaffOrderByIdperioddetailsAsc(teacherName);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return periodDetailsList;
	}

	@Transactional
	public boolean update(Periodmaster periodMaster) {
		try {
			// Hibernate reference
			// transaction = session.beginTransaction();
			// session.update(periodMaster);
			periodMasterRepo.save(periodMaster);
			return true;
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return false;
	}

}
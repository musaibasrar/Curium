package org.ideoholic.curium.model.adminexpenses.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.query.Query;
import org.ideoholic.curium.model.adminexpenses.dto.Adminexpenses;
import org.ideoholic.curium.util.HibernateUtil;
import org.ideoholic.curium.util.QueryUtil;
import org.ideoholic.curium.util.Session;
import org.ideoholic.curium.util.Session.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AdminDetailsDAO {

	@Autowired
	private AdminExpensesRepository adminExpensesRepo;
	@Autowired
	private QueryUtil queryUtil;
	
	@Transactional
	public Adminexpenses create(Adminexpenses adminexpenses) {
		try {
			adminexpenses = adminExpensesRepo.save(adminexpenses);
		} catch (Exception hibernateException) { 
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}
		return adminexpenses;
	}


	@Transactional
	public List<Adminexpenses> readListOfExpenses(Integer branchId) {
		List<Adminexpenses> results = new ArrayList<Adminexpenses>();

		try {

			results =adminExpensesRepo.findByBranchId(branchId);



		} catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);

			hibernateException.printStackTrace();
			throw hibernateException;
		}
		return results;
	}

	

	@Transactional
	public void deleteMultiple(List<Integer> ids) {

		try{
			List<Adminexpenses> adminExpenses = adminExpensesRepo.findAllById(ids);
			for(Adminexpenses adminExpense: adminExpenses) {
				adminExpense.setVoucherstatus("CANCELLED");
				adminExpensesRepo.save(adminExpense);
			}
			//adminExpensesRepo.deleteAllById(ids);
		} catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
			throw hibernateException;
		}

	}
@Transactional
	public List<Adminexpenses> searchExpensesbydate(String queryMain) {
		
		List<Adminexpenses> adminExpenses = new ArrayList<Adminexpenses>();

		try{

            adminExpenses = queryUtil.runGivenQuery(queryMain, Adminexpenses.class);


        } catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);
            
            hibernateException.printStackTrace();
			throw hibernateException;
        }

        return adminExpenses;
	}

	@Transactional
	public Adminexpenses readExpenses(int expensesIds, Integer  branchId) {

			Adminexpenses results = new Adminexpenses();
			try{

				results = adminExpensesRepo.findByExpenseId(expensesIds,branchId);

			} catch (Exception hibernateException) {
				log.error(hibernateException.getMessage(), hibernateException);
				
				hibernateException.printStackTrace();
				throw hibernateException;

			}
			return results;
	}

	public void rejectVoucher(List ids) {
		Transaction transaction = null;
		try{
			Session session = HibernateUtil.openCurrentSession();
			transaction = session.beginTransaction();
			Query query = session
					.createQuery("update Adminexpenses set voucherstatus='rejected' where idAdminExpenses IN (:ids)");
			query.setParameterList("ids", ids);
			query.executeUpdate();
			transaction.commit();
		} catch (Exception hibernateException) { transaction.rollback(); log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}

	}

	public void approveVoucher(List ids) {
		Transaction transaction = null;
		try{
			Session session = HibernateUtil.openCurrentSession();
			transaction = session.beginTransaction();
			Query query = session
					.createQuery("update Adminexpenses set voucherstatus='approved' where idAdminExpenses IN (:ids)");
			query.setParameterList("ids", ids);
			query.executeUpdate();
			transaction.commit();
		} catch (Exception hibernateException) { transaction.rollback(); log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}

	}

}

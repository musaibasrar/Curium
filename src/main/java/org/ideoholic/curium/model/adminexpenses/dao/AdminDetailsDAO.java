package org.ideoholic.curium.model.adminexpenses.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.ideoholic.curium.model.adminexpenses.dto.Adminexpenses;
import org.ideoholic.curium.repositories.AdminExpensesRepository;
import org.ideoholic.curium.util.QueryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

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

			results =adminExpensesRepo.findByBranchid(branchId);



		} catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);

			hibernateException.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
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
		} catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
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
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
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
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();

			}
			return results;
	}
    @Transactional
	public void rejectVoucher(List<Integer> ids) {
		try{

			for (Integer id : ids) {

				Adminexpenses result = adminExpensesRepo.findById(id).orElse(null);
                if(result!=null){
					result.setVoucherstatus("rejected");
					adminExpensesRepo.save(result);
				}
			}


		} catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);

			hibernateException.printStackTrace();
	        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}

	}
    @Transactional
	public void approveVoucher(List<Integer> ids) {
		try{

			for(Integer id: ids){
				Adminexpenses result = adminExpensesRepo.findById(id).orElse(null);
				if(result!=null){
					result.setVoucherstatus("approved");
					adminExpensesRepo.save(result);
				}
			}
		} catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);

			hibernateException.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}

	}

}

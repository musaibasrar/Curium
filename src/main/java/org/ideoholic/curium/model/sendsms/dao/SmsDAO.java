package org.ideoholic.curium.model.sendsms.dao;

import java.util.ArrayList;
import java.util.List;

import org.ideoholic.curium.model.parents.dto.Parents;
import org.ideoholic.curium.util.QueryUtil;
import org.springframework.stereotype.Component;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class SmsDAO {

	private final QueryUtil queryUtil;

	public long countNumbers(String queryMain) {
		long totalNumbers = 0;
		try {
			// this.session =
			// HibernateUtil.getSessionFactory().openCurrentSession();

			// Query query = session.createQuery("select count(*)" + queryMain + "AND contactnumber IS NOT NULL and contactnumber <> '' ");
			String finalQuery = "select count(*)" + queryMain + "AND contactnumber IS NOT NULL and contactnumber <> '' ";

			totalNumbers = queryUtil.runGivenQueryForCount(finalQuery);
		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);

			hibernateException.printStackTrace();
		}
		return totalNumbers;
	}

	public List<Parents> getContactNumbers(int offset, int noOfRecords) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Object> readListOfObjectsPaginationALL(int offset, int noOfRecords, String queryMain) {
		List<Object> results = new ArrayList<Object>();

		try {
			// Query query = session.createQuery(queryMain);
			// query.setFirstResult(offset);
			// query.setMaxResults(noOfRecords);
			// results = query.list();
			results = queryUtil.runGivenQueryWithPagination(queryMain, Object.class, offset, noOfRecords);

		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}
		return results;
	}

}

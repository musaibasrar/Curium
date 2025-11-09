package org.ideoholic.curium.util;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class QueryUtil {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List findByClassLimitedTo(String query, Class clazz, int limit) {
		return entityManager.createQuery(query, clazz).setMaxResults(limit).getResultList();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List runGivenQuery(String query, Class clazz) {
		return entityManager.createQuery(query, clazz).getResultList();
	}
	
	public void runUpdateQuery(String query) {
		 entityManager.createQuery(query).executeUpdate();
	}
	
	public Object runGivenQueryForSingleResult(String query,Class clazz) {
		 return entityManager.createQuery(query, clazz).getSingleResult();
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List runGivenQueryWithPagination(String query, Class clazz, int offset, int limit) {
		return entityManager.createQuery(query, clazz).setFirstResult(offset).setMaxResults(limit).getResultList();
	}

	/**
	 * Helper to turn a list of ids into comma-separated String to use in an IN (...) clause.
	 * Kept simple as a convenience to reduce repetitive concat logic in DAO.
	 * NOTE: this does simple toString on elements; ensure ids list contains numeric ids.
	 */
	public static String joinIds(List<?> ids) {
		if (ids == null || ids.isEmpty()) {
			return "";
		}
		return ids.stream().map(Object::toString).collect(Collectors.joining(","));
	}
}
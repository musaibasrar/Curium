package org.ideoholic.curium.util;

import java.util.List;

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
}
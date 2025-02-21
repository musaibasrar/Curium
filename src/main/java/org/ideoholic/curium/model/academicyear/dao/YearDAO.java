package org.ideoholic.curium.model.academicyear.dao;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.ideoholic.curium.util.Session;
import org.ideoholic.curium.util.Session.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

import org.hibernate.query.Query;

import org.ideoholic.curium.model.academicyear.dto.Currentacademicyear;
import org.ideoholic.curium.model.user.dao.LoginRepository;
import org.ideoholic.curium.model.user.dao.UserDAO;
import org.ideoholic.curium.util.HibernateUtil;
import org.ideoholic.curium.util.QueryUtil;

@Slf4j
@Component
public class YearDAO {
	
	
	@Autowired
    private YearRepository yearRepo;

    @Autowired
    private QueryUtil queryUtil;


    @Transactional
	@SuppressWarnings("finally")
	public String create(Currentacademicyear currentacademicyear) {
		String error = null;
		try {
			// this.session = sessionFactory.openCurrentSession();
			yearRepo.save(currentacademicyear);

		} catch (Exception hibernateException) {
             log.error(hibernateException.getMessage(), hibernateException);
	            hibernateException.printStackTrace();
	            throw hibernateException;
		} 
		
		return error;
	}

    @Transactional
	public Currentacademicyear showYear() {
		Currentacademicyear currentacademicyear = new Currentacademicyear();
		try {
			 Session session = HibernateUtil.openCurrentSession();
			Query query = session.createQuery(
					"from Currentacademicyear as ca where ca.cayid = (select max(cayid) from Currentacademicyear) ");
			currentacademicyear = (Currentacademicyear) query.setCacheable(true).setCacheRegion("commonregion")
					.uniqueResult();
		} catch (Exception hibernateException) {
			 log.error(hibernateException.getMessage(), hibernateException);
	            hibernateException.printStackTrace();
	            throw hibernateException;
	            } 
		return currentacademicyear;
	}

}

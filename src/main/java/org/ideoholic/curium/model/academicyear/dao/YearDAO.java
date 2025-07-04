package org.ideoholic.curium.model.academicyear.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.ideoholic.curium.model.academicyear.dto.Currentacademicyear;
import org.ideoholic.curium.repositories.YearRepository;
import org.ideoholic.curium.util.QueryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class YearDAO {
	
	@Autowired
    private YearRepository yearRepo;

    @Autowired
    private QueryUtil queryUtil;

    @Transactional
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
			
			List<Currentacademicyear> ca = queryUtil.runGivenQuery("select ca from Currentacademicyear as ca where ca.cayid = (select max(cayid) from Currentacademicyear) ", Currentacademicyear.class);
			if(!CollectionUtils.isEmpty(ca)) {
				currentacademicyear = ca.get(0);
			}
		} catch (Exception hibernateException) { 
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
			throw hibernateException;
		} 
		return currentacademicyear;
	}

}

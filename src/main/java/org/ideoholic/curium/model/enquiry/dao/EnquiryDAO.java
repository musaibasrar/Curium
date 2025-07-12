package org.ideoholic.curium.model.enquiry.dao;

import javax.transaction.Transactional;

import org.ideoholic.curium.model.enquiry.dto.Enquiry;
import org.ideoholic.curium.repositories.EnquiryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EnquiryDAO {

	@Autowired
	private EnquiryRepository enquiryRepo;

	@Transactional
	public void create(Enquiry enquiry) {
		try {
			enquiryRepo.save(enquiry);
		} catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();

			throw hibernateException;
		}
	}

}

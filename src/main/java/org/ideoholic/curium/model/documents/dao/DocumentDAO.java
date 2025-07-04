package org.ideoholic.curium.model.documents.dao;

import javax.transaction.Transactional;

import org.ideoholic.curium.model.documents.dto.Transfercertificate;
import org.ideoholic.curium.repositories.TransferCertificateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DocumentDAO {
	
	@Autowired
    private TransferCertificateRepository transferCertificateRepo;

	@Transactional 
	public String generateTransferCertificate(Transfercertificate tc) {
		String status = "false";
		try {
			transferCertificateRepo.save(tc);
			status = "true";
		} catch (Exception hibernateException) { 
        	log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            throw hibernateException;
		}
		return status;
	}


	@Transactional 
	public Transfercertificate getTransferCertificateDetails(int studentId) {
		Transfercertificate tc = new Transfercertificate();
		
		try {
			tc = transferCertificateRepo.findBySid(studentId);
		}  catch (Exception hibernateException) { 
        	log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            throw hibernateException;
		}
		return tc;
	}
}

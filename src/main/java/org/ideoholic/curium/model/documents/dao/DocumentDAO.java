package org.ideoholic.curium.model.documents.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.ideoholic.curium.model.documents.dto.StudyCertificate;
import org.ideoholic.curium.model.documents.dto.Transfercertificate;
import org.ideoholic.curium.repositories.StudyCertificateRepository;
import org.ideoholic.curium.repositories.TransferCertificateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DocumentDAO {
	
	@Autowired
    private TransferCertificateRepository transferCertificateRepo;
	
	@Autowired
    private StudyCertificateRepository studyCertificateRepo;

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

	@Transactional
	public List<Transfercertificate> getTCertificateDetails() {
		List<Transfercertificate> tc = new ArrayList<Transfercertificate>();
		try {
			tc = transferCertificateRepo.findAll();
		} catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            throw hibernateException;
		}
		return tc;
	}

	public boolean add(StudyCertificate studyCertificate) {
		boolean status = false;
		try {
			studyCertificateRepo.save(studyCertificate);
		} catch (Exception hibernateException) { 
        	log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            throw hibernateException;
		}
		return status;
	}



	public List<StudyCertificate> getStudentCertificateList(int branchId) {
		List<StudyCertificate> sc = new ArrayList<StudyCertificate>();
		try {
			studyCertificateRepo.findByBranchId(branchId);
		} catch (Exception hibernateException) { 
        	log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            throw hibernateException;
		}
		return sc;
	}



	@SuppressWarnings("unchecked")
	public List<StudyCertificate> getListOfIssuedStudyCertificate(String[] sIds) {
		List<Integer> ids = Arrays.stream(sIds)
                .map(Integer::valueOf)
                .toList();
		List<StudyCertificate> sc = new ArrayList<StudyCertificate>();
		try {
			sc = studyCertificateRepo.findAllByStudentIdsIn(ids);
		} catch (Exception hibernateException) { 
        	log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            throw hibernateException;
		}
		return sc;

	}

}

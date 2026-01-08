package org.ideoholic.curium.model.documents.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.ideoholic.curium.model.documents.dto.StudyCertificate;
import org.ideoholic.curium.model.documents.dto.Transfercertificate;
import org.ideoholic.curium.model.student.dto.Student;
import org.ideoholic.curium.repositories.StudentRepository;
import org.ideoholic.curium.repositories.StudyCertificateRepository;
import org.ideoholic.curium.repositories.TransferCertificateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DocumentDAO {
	
	@Autowired
    private TransferCertificateRepository transferCertificateRepo;
	
	@Autowired
    private StudyCertificateRepository studyCertificateRepo;
	
	@Autowired
    private StudentRepository studentRepo;

	@Transactional 
	public String generateTransferCertificate(Transfercertificate tc, Student student) {
		String status = "false";
		try {
			transferCertificateRepo.save(tc);
			studentRepo.save(student);
			status = "true";
		} catch (Exception hibernateException) { 
        	log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
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
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
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
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
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
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
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
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return sc;
	}



	public List<StudyCertificate> getListOfIssuedStudyCertificate(String[] sIds, Integer branchId) {
		List<Integer> ids = Arrays.stream(sIds)
                .map(Integer::valueOf)
                .toList();
		List<StudyCertificate> sc = new ArrayList<StudyCertificate>();
		try {
			sc = studyCertificateRepo.findAllByStudentIdsIn(ids, branchId);
		} catch (Exception hibernateException) { 
        	log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return sc;

	}

}

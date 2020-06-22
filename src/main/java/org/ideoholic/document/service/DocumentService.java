package org.ideoholic.document.service;

import java.util.Date;

public interface DocumentService {

	String transferCertificate(String branchId);

	String generateTransferCertificate(int studentId, String leavingReason, Date dateOfTc);

	String printTransferCertificate(int studentId);

	String admissionAbstract(String branchId);

	String searchForStudents(String branchId, String studentname, String admissionNumber, String addClass,
			String addSec);

	String downlaodFile(byte[] buffer);

}

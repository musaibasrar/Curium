package org.ideoholic.email.service;

public interface EmailService {

	String sendAllEmail(String branchId, String addClass, String addSec, String subject, String messageBody);

}

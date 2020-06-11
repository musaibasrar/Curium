package org.ideoholic.sms.service;

public interface SmsService {

	String sendAllSMS(String branchId, String addClass, String addSec, String messageBody);

	String sendNumbersSMS(String numbers, int resultSMS);

	String sendStaffSMS(String branchId, String department, String messageBodyStaff);

	String sendSMS(String numbers, String message);

}

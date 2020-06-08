package org.ideoholic.stampfees.service;

public interface StampFeesService {

	String advanceSearch(String branchId, String studentname, String addClass, String addSec);

	String deleteFeesStamp(String currentYear, String[] studentIds);

}

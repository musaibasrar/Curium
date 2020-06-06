package org.ideoholic.position.service;

public interface PositionService {

	
	String addPosition(String position, String branchId);

	String viewPosition(String branchId);

	String deleteMultiple(String[] positionIds);

}

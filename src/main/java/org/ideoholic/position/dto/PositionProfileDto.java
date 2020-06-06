package org.ideoholic.position.dto;

public class PositionProfileDto {

	private String Position;
	private String branchId;
	private String[] positionIds;
	
	
	public String[] getPositionIds() {
		return positionIds;
	}
	public void setPositionIds(String[] positionIds) {
		this.positionIds = positionIds;
	}
	public String getPosition() {
		return Position;
	}
	public void setPosition(String position) {
		Position = position;
	}
	public String getBranchId() {
		return branchId;
	}
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	
}

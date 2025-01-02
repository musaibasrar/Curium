package org.ideoholic.curium.model.feescollection.dto;

import java.util.List;

import org.ideoholic.curium.model.parents.dto.Parents;
import org.ideoholic.curium.model.student.dto.Studentfeesstructure;

public class StudentFeesCollectionClassWiseReport {

	
	private Parents parent;
	private List<String> feescollection;
	private int userid;
	
	public StudentFeesCollectionClassWiseReport() {
	}


	public StudentFeesCollectionClassWiseReport(Parents parents, List<String> feesCollectionDetails) {
		this.parent = parents;
		this.feescollection = feesCollectionDetails;
	}
	

	public Parents getParents() {
		return parent;
	}


	public void setParents(Parents parents) {
		this.parent = parents;
	}

	
	public int getUserid() {
			return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}


	public List<String> getFeescollection() {
		return feescollection;
	}


	public void setFeescollection(List<String> feescollection) {
		this.feescollection = feescollection;
	}

	
}

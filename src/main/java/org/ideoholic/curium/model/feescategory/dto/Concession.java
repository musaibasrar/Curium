package org.ideoholic.curium.model.feescategory.dto;

// default package
// Generated 6 Dec, 2014 11:27:37 AM by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Feescategory generated by hbm2java
 */
@Entity
public class Concession implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int sfsid;
	private int feescatid;
	private String concession;
	private String concessionOld;
	
	
	public Concession() {
	}

	public Concession(int sfsid, int feescatid, String concession, String concessionold) {
		this.sfsid = sfsid;
		this.feescatid = feescatid;
		this.concession = concession;
		this.concessionOld = concessionold;
	}

	public int getSfsid() {
		return sfsid;
	}

	public void setSfsid(int sfsid) {
		this.sfsid = sfsid;
	}

	public int getFeescatid() {
		return feescatid;
	}

	public void setFeescatid(int feescatid) {
		this.feescatid = feescatid;
	}

	public String getConcession() {
		return concession;
	}

	public void setConcession(String concession) {
		this.concession = concession;
	}

	public String getConcessionOld() {
		return concessionOld;
	}

	public void setConcessionOld(String concessionOld) {
		this.concessionOld = concessionOld;
	}

}

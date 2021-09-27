package org.ideoholic.curium.model.periods.dto;

// default package
// Generated 7 Apr, 2018 6:08:39 PM by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToMany;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

import org.ideoholic.curium.model.feescollection.dto.Feescollection;

/**
 * Periodmaster generated by hbm2java
 */
@Entity
@Table(name = "periodmaster")
public class Periodmaster implements java.io.Serializable {

	private Integer idperiodmaster;
	private String class_;
	private Integer totalperiods;
	private Integer totalbreaks;
	private String daystart;
	private String dayend;
	private String durationofperiod;
	private String academicyear;
	private int branchid;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "Periodmaster")
	private Set<Perioddetails> periodDetails =
			new HashSet<Perioddetails>(0);
	
	public Periodmaster() {
	}

	public Periodmaster(String class_, Integer totalperiods,
			Integer totalbreaks, String daystart, String dayend, int branchid,
			String durationofperiod, String academicyear, Set<Perioddetails> periodDetails) {
		this.class_ = class_;
		this.totalperiods = totalperiods;
		this.totalbreaks = totalbreaks;
		this.daystart = daystart;
		this.dayend = dayend;
		this.durationofperiod = durationofperiod;
		this.academicyear = academicyear;
		this.periodDetails = periodDetails;
		this.branchid = branchid;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idperiodmaster", unique = true, nullable = false)
	public Integer getIdperiodmaster() {
		return this.idperiodmaster;
	}

	public void setIdperiodmaster(Integer idperiodmaster) {
		this.idperiodmaster = idperiodmaster;
	}

	@Column(name = "class", length = 45)
	public String getClass_() {
		return this.class_;
	}

	public void setClass_(String class_) {
		this.class_ = class_;
	}

	@Column(name = "totalperiods")
	public Integer getTotalperiods() {
		return this.totalperiods;
	}

	public void setTotalperiods(Integer totalperiods) {
		this.totalperiods = totalperiods;
	}

	@Column(name = "totalbreaks")
	public Integer getTotalbreaks() {
		return this.totalbreaks;
	}

	public void setTotalbreaks(Integer totalbreaks) {
		this.totalbreaks = totalbreaks;
	}

	@Column(name = "daystart", length = 45)
	public String getDaystart() {
		return this.daystart;
	}

	public void setDaystart(String daystart) {
		this.daystart = daystart;
	}

	@Column(name = "dayend", length = 45)
	public String getDayend() {
		return this.dayend;
	}

	public void setDayend(String dayend) {
		this.dayend = dayend;
	}

	@Column(name = "durationofperiod", length = 45)
	public String getDurationofperiod() {
		return this.durationofperiod;
	}

	public void setDurationofperiod(String durationofperiod) {
		this.durationofperiod = durationofperiod;
	}

	@Column(name = "academicyear", length = 45)
	public String getAcademicyear() {
		return this.academicyear;
	}

	public void setAcademicyear(String academicyear) {
		this.academicyear = academicyear;
	}

	public Set<Perioddetails> getPeriodDetails() {
		return periodDetails;
	}

	public void setPeriodDetails(Set<Perioddetails> periodDetails) {
		this.periodDetails = periodDetails;
	}
	
	@Column(name = "branchid")
	public int getBranchid() {
	return branchid;
	}

	public void setBranchid(int branchid) {
	this.branchid = branchid;
	}
}

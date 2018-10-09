package com.util;

// default package
// Generated 31 Mar, 2018 10:55:17 AM by Hibernate Tools 4.0.0

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


public class HallTicket implements java.io.Serializable {

	private Date date;
	private String starttime;
	private String endtime;
	private String subject;
	private String examname;
	private String referencebooks;
	
	
	public HallTicket() {
	}

	public HallTicket(Date date, String starttime, String endtime,
			String subject, String examname, String referencebooks) {
		this.date = date;
		this.starttime = starttime;
		this.endtime = endtime;
		this.subject = subject;
		this.examname = examname;
		this.referencebooks = referencebooks;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getStarttime() {
		return this.starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return this.endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getExamname() {
		return this.examname;
	}

	public void setExamname(String examname) {
		this.examname = examname;
	}

    
    public String getReferencebooks() {
        return this.referencebooks;
    }

    
    public void setReferencebooks(String referencebooks) {
        this.referencebooks = referencebooks;
    }
	
}

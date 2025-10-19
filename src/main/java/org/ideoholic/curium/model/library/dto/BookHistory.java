package org.ideoholic.curium.model.library.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "bookhistory")
public class BookHistory implements java.io.Serializable{
	
	private int id;
	private String bid;
	private String bookName;
	private String studentName;
	private String uid;
	private Date issueDate;
	private Date expectedReturnDate;
	private Date actualReturnDate;
	private String sid;
	public BookHistory() {
	}
	public BookHistory(int id, String bid, String bookName, String studentName, String uid, Date issueDate,
			Date expectedReturnDate, Date actualReturnDate, String sid) {
		this.id = id;
		this.bid = bid;
		this.bookName = bookName;
		this.studentName = studentName;
		this.uid = uid;
		this.issueDate = issueDate;
		this.expectedReturnDate = expectedReturnDate;
		this.actualReturnDate = actualReturnDate;
		this.sid = sid;
	}
	@Column(name = "id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name = "bid", length = 200)
	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
	@Column(name = "bookName", length = 200)
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	@Column(name = "studentName", length = 200)
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	@Column(name = "uid", length = 200)
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	@Column(name = "issueDate")
	public Date getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}
	@Column(name = "expectedReturnDate")
	public Date getExpectedReturnDate() {
		return expectedReturnDate;
	}
	public void setExpectedReturnDate(Date expectedReturnDate) {
		this.expectedReturnDate = expectedReturnDate;
	}
	@Column(name = "actualReturnDate")
	public Date getActualReturnDate() {
		return actualReturnDate;
	}
	public void setActualReturnDate(Date actualReturnDate) {
		this.actualReturnDate = actualReturnDate;
	}
	@Column(name = "sid", length = 45)
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	
}

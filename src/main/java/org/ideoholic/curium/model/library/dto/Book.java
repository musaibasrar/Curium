package org.ideoholic.curium.model.library.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "book")
public class Book implements java.io.Serializable{
  
	private int bid;
	private String bookname;
	private String subject;
	private String author;
	private String publisher;
	private String isbn;
	private String status;
	private String bookHolder;
	private String shelf;
	private Date startdate;
	private Date enddate;
	private String noofdays;
	
	public Book() {
	}

	public Book(int bid, String bookname, String subject, String author, String publisher, String isbn, String status, String bookHolder,
			String shelf,Date startdate, Date enddate,String noofdays) {
		this.bid = bid;
		this.bookname = bookname;
		this.subject = subject;
		this.author = author;
		this.publisher = publisher;
		this.isbn = isbn;
		this.status = status;
		this.bookHolder = bookHolder;
		this.shelf = shelf;
		this.startdate = startdate;
		this.enddate = enddate;
		this.noofdays = noofdays;
	}

	@Column(name = "bid")
	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	
	@Column(name = "bookname", length = 200)
	public String getBookname() {
		return bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	@Column(name = "subject", length = 200)
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Column(name = "author", length = 200)
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Column(name = "publisher", length = 200)
	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	@Column(name = "isbn", length = 200)
	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	@Column(name = "status", length = 50)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "bookholder", length = 100)
	public String getBookHolder() {
		return bookHolder;
	}

	public void setBookHolder(String bookHolder) {
		this.bookHolder = bookHolder;
	}

	@Column(name = "shelf",length = 100)
	public String getShelf() {
		return shelf;
	}

	public void setShelf(String shelf) {
		this.shelf = shelf;
	}

	@Column(name = "startdate")
	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	@Column(name = "enddate")
	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	@Column(name = "noofdays",length = 20)
	public String getNoofdays() {
		return noofdays;
	}

	public void setNoofdays(String noofdays) {
		this.noofdays = noofdays;
	}
	
	
	

}

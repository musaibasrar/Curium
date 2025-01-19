package org.ideoholic.curium.model.library.dto;

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
	private int availableQty;
	private int issuedQty;
	private String shelf;
	
	public Book() {
	}

	

	public Book(int bid, String bookname, String subject, String author, String publisher, String isbn,
			int availableQty, int issuedQty, String shelf) {
		super();
		this.bid = bid;
		this.bookname = bookname;
		this.subject = subject;
		this.author = author;
		this.publisher = publisher;
		this.isbn = isbn;
		this.availableQty = availableQty;
		this.issuedQty = issuedQty;
		this.shelf = shelf;
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

	
	 @Column(name = "availableqty")
	public int getAvailableQty() {
		return availableQty;
	}

	public void setAvailableQty(int availableQty) {
		this.availableQty = availableQty;
	}


	 @Column(name = "issuedqty")
	public int getIssuedQty() {
		return issuedQty;
	}

	public void setIssuedQty(int issuedQty) {
		this.issuedQty = issuedQty;
	}

	@Column(name = "shelf",length = 100)
	public String getShelf() {
		return shelf;
	}

	public void setShelf(String shelf) {
		this.shelf = shelf;
	}

	

}

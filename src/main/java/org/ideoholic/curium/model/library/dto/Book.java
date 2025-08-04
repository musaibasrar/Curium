package org.ideoholic.curium.model.library.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "book")
public class Book implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sid", unique = true, nullable = false)
	private int bid;

	@Column(name = "bookname", length = 200)
	private String bookname;

	@Column(name = "subject", length = 200)
	private String subject;

	@Column(name = "author", length = 200)
	private String author;

	@Column(name = "publisher", length = 200)
	private String publisher;

	@Column(name = "isbn", length = 200)
	private String isbn;

	@Column(name = "availableqty")
	private int availableQty;

	@Column(name = "issuedqty")
	private int issuedQty;

	@Column(name = "shelf", length = 100)
	private String shelf;

	@Column(name = "status", length = 50)
	private String status;

	@Column(name = "bookHolder", length = 200)
	private String bookHolder;

	@Column(name = "startdate")
	private Date startdate;

	@Column(name = "enddate")
	private Date enddate;

	@Column(name = "noofdays", length = 50)
	private String noofdays;

	@Column(name = "branchid")
	private Integer branchid;
}

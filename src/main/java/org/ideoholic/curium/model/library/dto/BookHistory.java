package org.ideoholic.curium.model.library.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bookhistory")
public class BookHistory implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private int id;

	@Column(name = "bid", length = 200)
	private String bid;

	@Column(name = "bookname", length = 200)
	private String bookName;

	@Column(name = "studentname", length = 200)
	private String studentName;

	@Column(name = "uid", length = 200)
	private String uid;

	@Column(name = "issuedate")
	private Date issueDate;

	@Column(name = "expectedreturnDate")
	private Date expectedReturnDate;

	@Column(name = "actualreturndate")
	private Date actualReturnDate;

	@Column(name = "sid", length = 45)
	private String sid;

}

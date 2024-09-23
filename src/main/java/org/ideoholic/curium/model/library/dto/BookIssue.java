package org.ideoholic.curium.model.library.dto;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bookissue")
public class BookIssue implements java.io.Serializable{
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "accountdetailsid", unique = true, nullable = false)
	public Integer id;

	
	@Column(name = "bookholder", length = 100)
	private String bookHolder;
	
	@Column(name = "bookname", length = 100)
	private String bookName;

	
	@Column(name = "startdate")
	private Date startDate;
	
	
	@Column(name = "enddate")
	private Date endDate;
	
	@Column(name = "actualreturndate")
	private Date actualReturnDate;
	
	@Column(name = "noofdays")
	private int noOfDays;
	
	@Column(name = "sid")
	private int sid;
	
	@Column(name = "bookid")
	private int bookId;
	
	@Column(name = "studentname", length = 200)
	private String studentName;
		
	@Column(name = "returned", length = 50)
	private String returned;
	
}

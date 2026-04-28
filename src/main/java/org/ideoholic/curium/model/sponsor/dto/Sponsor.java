package org.ideoholic.curium.model.sponsor.dto;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sponsor")
public class Sponsor implements java.io.Serializable{
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private int id;
	
	@Column(name = "branchid")
	private int branchid;
	
	@Column(name = "userid")
	private int userid;
	
	@Column(name = "name", length = 100)
	private String name;
	
	@Column(name = "contact", length = 45)
	private String contact;
	
	@Column(name = "address", length = 1000)
	private String address;
	
	@Column(name = "notes", length = 1000)
	private String notes;

}

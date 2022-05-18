package org.ideoholic.curium.model.mess.card.dto;

import static javax.persistence.GenerationType.AUTO;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * messItems generated by hbm2java
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "mess_card")
public class Card implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = AUTO)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "sid")
	private Integer sid;

	@Column(name = "validfrom")
	private Date validfrom;

	@Column(name = "validto")
	private Date validto;

	@Column(name = "userid")
	private int userid;

}

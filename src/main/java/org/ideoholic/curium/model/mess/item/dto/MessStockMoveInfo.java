package org.ideoholic.curium.model.mess.item.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
@Table(name = "mess_stockmoveinfo")
public class MessStockMoveInfo implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "receiptnumber")
    private Integer receiptnumber;

    @Column(name = "sid", nullable = false)
    private int sid;

    @Column(name = "studentname", length = 45)
    private String studentName;

    @Column(name = "date", length = 10)
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "totalamount", precision = 10, scale = 0)
    private Long totalamount;

    @Column(name = "academicyear", length = 15)
    private String academicyear;

    @Column(name = "cancelreceipt", columnDefinition = "int default 0")
    private int cancelreceipt;

    @Column(name = "branchreceiptnumber", length = 20)
    private String branchreceiptnumber;

    @Column(name = "userid")
    private int userid;

    @Column(name = "branchid")
    private int branchid;

    @Column(name = "paymenttype", length = 100)
    private String paymenttype;

    @Column(name = "classsec", length = 20)
    private String classsec;

    @Column(name = "receiptvoucher")
    private int receiptvoucher;

    @Column(name = "journalvoucher")
    private int journalvoucher;

    @Column(name = "due", precision = 10, scale = 0)
    private Long due;

    @Column(name = "misc", precision = 10, scale = 0)
    private Long misc;
}

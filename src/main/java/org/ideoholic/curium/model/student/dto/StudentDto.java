package org.ideoholic.curium.model.student.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ideoholic.curium.model.degreedetails.dto.Degreedetails;
import org.ideoholic.curium.model.pudetails.dto.Pudetails;
import org.ideoholic.curium.util.DateUtil;

import java.util.Date;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto implements java.io.Serializable {

    private Integer sid;

    private String name;

    private String classstudying;

    private String classadmittedin;

    private Integer age;
    private String gender;
    private String dateofbirth;
    private String bloodgroup;
    private String nationality;
    private String religion;
    private String caste;
    private Date admissiondate;
    private String admissionnumber;
    private String mothertongue;
    private String remarks;
    private String schoollastattended;
    private String stdlaststudied;

    private String createddate;

    private String archive;
    private String mediumofinstructionlastschool;

    private String studentpic;

    private String studentexternalid;

    private String crecord;

    private String crecorddate;

    private String placeofbirth;

    private Integer nooftc;

    private String dateoftc;

    private String classonleaving;

    private String dateleaving;

    private String reasonleaving;

    private Integer notcissued;

    private String datetcissued;

    private String guardiandetails;

    private String subsequentprogress;

    private int branchid;

    private String languagesstudied;

    private String instructionmediumlastschool;

    private Integer passedout;

    private Integer droppedout;

    private Integer leftout;

    private Integer semester;

    private String stream;

    private String mediumofinstruction;

    private String previousschooltype;

    private String previouschooladdress;

    private String urbanrural;

    private String studentscastecertno;

    private String studentscaste;

    private String socialcategory;

    private Integer belongtobpl;

    private String bplcardno;

    private String bhagyalakshmibondnumber;

    private String disabilitychild;

    private String sts;

    private String specialcategory;

    private Integer rte;

    private String bankname;

    private String bankbranch;

    private String accno;

    private String bankifsc;

    private int userid;

    private String studentdoc1;

    private String studentdoc2;

    private String studentdoc3;

    private String studentdoc4;

    private String studentdoc5;

    private String lastcourse;
    private Integer totalmarks;
    private String lastfirstlanguage;
    private String lastsecondlanguage;
    private String lastschooladdress;
    private String registrationnumber;
    private Pudetails pudetails;
    private Degreedetails degreedetails;

    private String yearofadmission;

    private String promotedyear;
    private String addclass;
    private String addsec;
    private String admclassE;
    private String admsecE;
    private String lastclass;
    private String lastschool;
    private String motherT;
    private String dateofcr;
    private Integer tcno;
    private String progress;
    private String reasonforleaving;
    private String dateoftcissued;
    private String guardian;

    /* Parents Data */
    private String fathersname;
    private String mothersname;
    private String profession;
    private String parentsannualincome;
    private String addresspermanent;
    private String addresstemporary;
    private Integer noofdependents;
    private String contactnumber;
    private String cocontactnumber;
    private String email;
    private String fathersqualification;
    private String mothersqualification;
    private String fatherscastecertno;
    private String motherscastecertno;
    private String fatherscaste;
    private String motherscaste;
    private String remarksadditional;

    /* PU Details Data */
    private Integer pep;
    private String passedyear;
    private String regno;
    private String resultclass;
    private String xsecondlanguage;
    private String aggmarks;
    private String xmediuminstruction;
    private String PUmediuminstruction;

    private String arts1;
    private String arts2;
    private String science1;
    private String science2;

    /* Degree Details Data */
    private Integer pepdc;
    private String passedyeardc;
    private String regnodc;
    private String resultclassdc;
    private String mediumofinstructiondc;
    private String qpartone;
    private String qparttwo;
    private String ppartone;
    private String pparttwo;
    private Integer pumarkscard;
    private Integer medicalreport;
    private Integer incomecertificate;
    private Integer migrationcertificate;
    private Integer originaltc;
    private Integer castecertificate;
    private String games;
    private String extracurricular;
    private String employer;
    private Integer karnataka;
    private Integer pid;
    private String classSec;
    private String studentPicUpdate;
    private String studentDoc1Update;
    private String studentDoc2Update;
    private String studentDoc3Update;
    private String studentDoc4Update;
    private String studentDoc5Update;
    private String annualincome;
    private String subjectspart1;
    private String subjectspart2;
    private Integer pudetailsid;
    private Integer iddegreedetails;

    private String studentPicDelete;
    private String studentDoc1Delete;
    private String studentDoc2Delete;
    private String studentDoc3Delete;
    private String studentDoc4Delete;
    private String newcategory;

    private String studentDoc5Delete;

    private String classstudy;
    private String secstudying;
    private String classadm;

    public Date getCreateddate() {
        return DateUtil.indiandateParser(this.createddate);
    }

    public Date getCrecorddate() {
        return DateUtil.indiandateParser(this.crecorddate);
    }

    public Date getDateoftc() {
        return DateUtil.indiandateParser(this.dateoftc);
    }

    public Date getDateleaving() {
        return DateUtil.indiandateParser(this.dateleaving);
    }

    public Date getDatetcissued() {
        return DateUtil.indiandateParser(this.datetcissued);
    }
}
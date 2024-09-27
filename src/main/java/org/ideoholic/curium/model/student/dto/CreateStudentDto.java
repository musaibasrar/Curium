package org.ideoholic.curium.model.student.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateStudentDto {

	private String name;
	private String gender;
	private String dateofbirth;
	private Integer age;
	private String addclass;
	private String addsec;
	private String admclassE;
	private String admsecE;
	private String lastclass;
	private String lastschool;
	private String admnno;
	private String dateofadmission;
	private String bloodgroup;
	private String nationality;
	private String religion;
	private String caste;
	private String motherT;
	private String createddate;
	private String remarks;
	private String crecord;
	private String dateofcr;
	private String place;
	private Integer tcno;
	private String dateoftc;
	private String classonleaving;
	private String progress;
	private String dateofleaving;
	private String reasonforleaving;
	private Integer notcissued;
	private String dateoftcissued;
	private String guardian;
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
	private String specialcategory;
	private String newcategory;
	private Integer sts;
	private Integer rte;
	private String bankname;
	private String bankifsc;
	private String accno;
	private String bankbranch;
	private String languagesstudied;
	private String mediumofinstructionlastschool;
	private String yearofadmission;
	private String promotedyear;

	/* Parents Data */
	private String fathersname;
	private String mothersname;
	private String profession;
	private String parentsannualincome;
	private String permanentaddress;
	private String temporaryaddress;
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

	private Integer sid;
	private Integer pid;
	private String classSec;
	private String secStudying;
	private String studentPicUpdate;
	private String studentDoc1Update;
	private String studentDoc2Update;
	private String studentDoc3Update;
	private String studentDoc4Update;
	private String studentDoc5Update;
	private String studentexternalid;
	private Integer passedout;
	private Integer leftout;
	private Integer droppedout;
	private String archive;

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
	private String studentDoc5Delete;
}

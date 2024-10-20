package org.ideoholic.curium.model.documents.dto;

import java.util.Date;

import org.ideoholic.curium.model.parents.dto.Parents;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransferCertificateResponseDto {
	    private TransferStatus status;
		private Parents parents;
		private Transfercertificate tc;
	    private String reason;
		private String bookNo;
		private String tcNo;
		private String caste;
		private String classInWord;
		private String lastExam;
		private String failPass;
		private String firstSubject;
		private String secondSubject;
		private String thirdSubject;
		private String FourthSubject;
		private String FifthSubject;
		private String sixthSubject;
		private String pinFig;
		private String pinWord;
		private String dues;
		private String concession;
		private String workingDays;
		private String present;
		private String ncc;
		private String game;
		private String conduct;
		private String dateCert;
		private String Remarks;
		private String dateInWord;
		private Date dateOfTc;
}

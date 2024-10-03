package org.ideoholic.curium.model.student.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.ideoholic.curium.model.feescollection.dto.Otherreceiptinfo;
import org.ideoholic.curium.model.feescollection.dto.Receiptinfo;
import org.ideoholic.curium.model.parents.dto.Parents;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class StudentDetailsResponseDto {
    private String currentYearFromService;
    private List<Receiptinfo> receiptInfo;
    private Student student;
    private String classStudying;
    private String classParts;
    private String secStudying;
    private String secClassParts;
    private String classAdmittedParts;
    private String secAdmParts;
    private String classAdm;
    private String secAdm;
    private String classAdmitted;
    private Parents parents;
    private List<Studentfeesstructure> feesStructure;
    private long dueAmount;
    private long totalSum;
    private long totalFineAmount;
    private long totalMiscAmount;
    private long totalFeesAmount;
    private long totalFeesConcession;
    private String academicPerYear;
    private String currentAcademicYear;
    private boolean resultFromService;
    private boolean success;
    private List<Otherreceiptinfo> otherReceiptInfo;
    private List<Studentotherfeesstructure> studentOtherFeesStructure;
}

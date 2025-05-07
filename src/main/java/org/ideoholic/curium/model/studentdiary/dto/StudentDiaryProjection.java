package org.ideoholic.curium.model.studentdiary.dto;

import java.time.LocalDateTime;
import java.util.Date;

public interface StudentDiaryProjection {
	
	int getId();
    Integer getSid();
    String getName();
    String getClasssec();
    String getAcademicyear();
    Integer getBranchid();
    String getSubject();
    String getMessage();
    Date getCreateddate();
    Integer getUserid();

}

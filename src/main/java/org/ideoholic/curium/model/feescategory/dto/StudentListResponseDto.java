package org.ideoholic.curium.model.feescategory.dto;

import java.util.List;

import org.ideoholic.curium.model.appointment.dto.Appointment;
import org.ideoholic.curium.model.parents.dto.Parents;
import org.ideoholic.curium.model.student.dto.Student;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class StudentListResponseDto {
		private List<Student> studentList;
		private List<Parents> parentDetails;
	    private String statusSelected;
	    private String studentSelected;
	    private List<Appointment> appointmentList;
	    private String transactionFromDateSelected;
	    private String transactionToDateSelected;
		@Builder.Default
		private boolean success = false;
}

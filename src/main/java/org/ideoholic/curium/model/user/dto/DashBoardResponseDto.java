package org.ideoholic.curium.model.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.ideoholic.curium.model.appointment.dto.DailyExpensesResponseDto;
import org.ideoholic.curium.model.appointment.dto.MonthlyExpensesResponseDto;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class DashBoardResponseDto {
    private int teacherSize;
    private List<String> xaxisList;
    private List<String> yaxisList;
    private List<String> boysGirls;
    private int totalStudents;
    @Builder.Default
    private boolean success = false;
    private DailyExpensesResponseDto dailyExpensesResponseDto;
    private MonthlyExpensesResponseDto monthlyExpensesResponseDto;
}

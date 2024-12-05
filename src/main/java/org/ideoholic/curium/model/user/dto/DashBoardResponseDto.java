package org.ideoholic.curium.model.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.ideoholic.curium.model.adminexpenses.dto.Adminexpenses;
import org.ideoholic.curium.model.employee.dto.Teacher;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class DashBoardResponseDto {
    private int teacherSize;
    private String expensesDateBranchName;
    private String branchName;
    private String dayOne;
    private List<Adminexpenses> dailyAdminExpenses;
    private long dailyExpenses;
    private List<String> monthlyExpenses;
    private List<String> monthListExpenses;
    private List<String> boysGirls;
    private List<String> xaxisList;
    private List<String> yaxisList;
    private int totalStudents;
    @Builder.Default
    private boolean success = false;
}

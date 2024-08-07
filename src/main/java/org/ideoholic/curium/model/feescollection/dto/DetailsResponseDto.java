package org.ideoholic.curium.model.feescollection.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ideoholic.curium.model.parents.dto.Parents;
import org.ideoholic.curium.model.student.dto.Student;
import org.ideoholic.curium.model.user.dto.Login;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class DetailsResponseDto {
    private Student student;
    private String receiptDate;
    private Receiptinfo receiptInfo;
    private Map<String,Long> feeCatMap;
    private String duplicate;
    private String grandTotal;
    private Login userLogin;
    private Parents parents;
    private boolean success;

}

package org.ideoholic.curium.model.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchByParentDto {
    String fathersName;
    String mothersName;
    String contactNumber;
}

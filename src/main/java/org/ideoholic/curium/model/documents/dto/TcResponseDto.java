package org.ideoholic.curium.model.documents.dto;

import org.ideoholic.curium.model.parents.dto.Parents;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TcResponseDto {
	
	private Parents parents;
	private Transfercertificate tc;
	private boolean success;
}

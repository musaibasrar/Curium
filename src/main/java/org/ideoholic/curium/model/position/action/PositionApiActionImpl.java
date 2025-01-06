package org.ideoholic.curium.model.position.action;

import lombok.extern.slf4j.Slf4j;
import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.position.dto.PositionDto;
import org.ideoholic.curium.model.position.dto.PositionResponseDto;
import org.ideoholic.curium.model.position.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PositionApiActionImpl implements PositionApiAction {

    @Autowired
    private PositionService positionService;

    public ResponseEntity<ResultResponse> deleteMultiple(PositionDto dto) {
        ResultResponse result = positionService.deleteMultiple(dto);
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<PositionResponseDto> viewPosition(String branchId) {
        PositionResponseDto result = positionService.viewPosition(branchId);
        log.debug("IN action's position view");
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<ResultResponse> addPosition(PositionDto dto, String branchId, String userId) {
       ResultResponse result = positionService.addPosition(dto,branchId,userId);
        log.debug("IN action's add position");
        return ResponseEntity.ok(result);
    }

}

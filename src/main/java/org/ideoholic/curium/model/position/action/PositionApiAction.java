package org.ideoholic.curium.model.position.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.position.dto.PositionDto;
import org.ideoholic.curium.model.position.dto.PositionResponseDto;
import org.ideoholic.curium.util.Constants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/positionProcess")
public interface PositionApiAction {

    @PostMapping("/deleteMultiple")
    public ResponseEntity<ResultResponse> deleteMultiple(@RequestBody PositionDto dto);

    @GetMapping("/positionView")
    public ResponseEntity<PositionResponseDto> viewPosition(@RequestHeader(value = Constants.BRANCHID) String branchId);

    @PostMapping("/addPosition")
    public ResponseEntity<ResultResponse> addPosition(PositionDto dto,@RequestHeader(value = Constants.BRANCHID) String branchId,@RequestHeader(value = Constants.USERID) String userId);

}

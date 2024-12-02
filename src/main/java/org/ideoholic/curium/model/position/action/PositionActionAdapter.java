package org.ideoholic.curium.model.position.action;

import org.ideoholic.curium.model.position.dto.PositionIdsDto;
import org.ideoholic.curium.model.position.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Service
public class PositionActionAdapter {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;
    @Autowired
    private HttpSession httpSession;


    public void deleteMultiple() {
        PositionService positionService = new PositionService(request, response);

        PositionIdsDto dto = new PositionIdsDto();
        dto.setPositionIds(request.getParameterValues("positionIDs"));

        positionService.deleteMultiple(dto);
    }
}

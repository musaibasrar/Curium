package org.ideoholic.curium.model.position.action;

import org.ideoholic.curium.model.position.dto.PositionDto;
import org.ideoholic.curium.model.position.dto.PositionResponseDto;
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
    private String BRANCHID = "branchid";
    private String USERID = "userloginid";


    public void deleteMultiple() {
        PositionService positionService = new PositionService(request, response);

        PositionDto dto = new PositionDto();
        dto.setPositionIds(request.getParameterValues("positionIDs"));

        positionService.deleteMultiple(dto);
    }

    public void viewPosition() {
        PositionService positionService = new PositionService(request, response);

        PositionResponseDto responseDto = positionService.viewPosition(httpSession.getAttribute(BRANCHID).toString());
        httpSession.setAttribute("positionList", responseDto.getPositionList());
    }

    public void addPosition() {
        PositionService positionService = new PositionService(request, response);

        PositionDto dto = new PositionDto();
        dto.setPosition(request.getParameter("position"));

        positionService.addPosition(dto, httpSession.getAttribute(BRANCHID).toString(), httpSession.getAttribute(USERID).toString());
    }
}

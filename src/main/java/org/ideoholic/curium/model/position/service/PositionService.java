package org.ideoholic.curium.model.position.service;

import lombok.extern.slf4j.Slf4j;
import org.ideoholic.curium.model.position.dao.positionDAO;
import org.ideoholic.curium.model.position.dto.Position;
import org.ideoholic.curium.model.position.dto.PositionDto;
import org.ideoholic.curium.model.position.dto.PositionResponseDto;
import org.ideoholic.curium.util.DataUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class PositionService {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession httpSession;

	public PositionService(HttpServletRequest request,
			HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.httpSession = request.getSession();
	}

	public void addPosition(PositionDto dto, String branchId, String userId) {
		
		Position position = new Position();

		position.setPositionname(DataUtil.emptyString(dto.getPosition()));
		position.setBranchid(Integer.parseInt(branchId));
		position.setUserid(Integer.parseInt(userId));
		if(!position.getPositionname().equalsIgnoreCase("")){
			position = new positionDAO().create(position);
		}
		

	}

	public PositionResponseDto viewPosition(String branchId) {
		PositionResponseDto result = PositionResponseDto.builder().build();
        try {
        	List<Position> list = new positionDAO().readListOfObjects(Integer.parseInt(branchId));
			result.setPositionList(list);

            result.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
        }
        return result;
	}

	public void deleteMultiple(PositionDto dto) {
		 String[] positionIds = dto.getPositionIds();
		 if(positionIds!=null){
	        List<Integer> ids = new ArrayList();
	        for (String id : positionIds) {
				log.error("id" + id);
	            ids.add(Integer.valueOf(id));

	        }
			log.error("id length" + positionIds.length);
	        new positionDAO().deleteMultiple(ids);
		 }
	}

}

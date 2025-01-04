package org.ideoholic.curium.model.position.service;

import lombok.extern.slf4j.Slf4j;
import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.position.dao.positionDAO;
import org.ideoholic.curium.model.position.dto.Position;
import org.ideoholic.curium.model.position.dto.PositionDto;
import org.ideoholic.curium.model.position.dto.PositionResponseDto;
import org.ideoholic.curium.util.DataUtil;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
@Service
@Slf4j
public class PositionService {

	public ResultResponse addPosition(PositionDto dto, String branchId, String userId) {
		
		Position position = new Position();

		position.setPositionname(DataUtil.emptyString(dto.getPosition()));
		position.setBranchid(Integer.parseInt(branchId));
		position.setUserid(Integer.parseInt(userId));
		if(!position.getPositionname().equalsIgnoreCase("")){
			position = new positionDAO().create(position);
			return ResultResponse.builder().success(true).build();
		}

		return ResultResponse.builder().build();
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

	public ResultResponse deleteMultiple(PositionDto dto) {
		 String[] positionIds = dto.getPositionIds();
		 if(positionIds!=null){
	        List<Integer> ids = new ArrayList();
	        for (String id : positionIds) {
				log.error("id" + id);
	            ids.add(Integer.valueOf(id));

	        }
			log.error("id length" + positionIds.length);
	        new positionDAO().deleteMultiple(ids);
			 return ResultResponse.builder().success(true).build();

		 }
		 return ResultResponse.builder().build();
	}

}

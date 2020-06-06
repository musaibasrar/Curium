package org.ideoholic.position.service;

import java.util.ArrayList;
import java.util.List;

import com.model.position.dao.positionDAO;
import com.model.position.dto.Position;
import com.util.DataUtil;

public class PositionServiceImpl implements PositionService {
	
public String addPosition(String Position, String branchId) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
		Position position = new Position();

		position.setBranchid(Integer.parseInt(branchId.toString()));
		if(!position.getPositionname().equalsIgnoreCase("")){
			position = new positionDAO().create(position);
		}
		
		sb.append("}");
		return sb.toString();
	}


public String viewPosition(String branchId) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
	boolean result = false;
    try {
    	List<Position> list = new positionDAO().readListOfObjects(Integer.parseInt(branchId.toString()));
    	sb.append("positionList").append(list);
        result = true;
    } catch (Exception e) {
        e.printStackTrace();
        result = false;
    }
    sb.append("}");
    return sb.toString();
}

public String deleteMultiple(String[] positionIds) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
	 if(positionIds!=null){
       List ids = new ArrayList();
       for (String id : positionIds) {
           System.out.println("id" + id);
           ids.add(Integer.valueOf(id));

       }
       System.out.println("id length" + positionIds.length);
       new positionDAO().deleteMultiple(ids);
	 }
	 sb.append("}");
	return sb.toString();
}
}

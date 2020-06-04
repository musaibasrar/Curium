package org.ideoholic.fees.service;

import java.util.ArrayList;
import java.util.List;

import com.model.feescategory.dao.feesCategoryDAO;
import com.model.feescategory.dto.Feescategory;
import com.util.DataUtil;

public class FeesServiceImple implements FeesService {
	
	 public String viewFees(String branchId) {
		 StringBuffer sb = new StringBuffer();
			sb.append("{");
         
         boolean result = false;
         
         if(branchId!=null){
                 try {
                        List<Feescategory> list = new feesCategoryDAO().readListOfObjects(Integer.parseInt(branchId.toString()));
                        sb.append("feescategory").append(list);
                    result = true;
                } catch (Exception e) {
                    e.printStackTrace();
                    result = false;
                }
         }
         sb.append("}");
        return sb.toString();
}

	 public String addFeesParticular(String branchId, String feescategory1, String fromclass, String toclass, String amount ) {
		 StringBuffer sb = new StringBuffer();
			sb.append("{");
         
         Feescategory feescategory = new Feescategory();
         
         if(branchId!=null){
                 
                 feescategory.setFeescategoryname(DataUtil.emptyString(feescategory1));
                 if(!DataUtil.emptyString(fromclass).equalsIgnoreCase("ALL") && !DataUtil.emptyString(toclass).equalsIgnoreCase("ALL")){
                         feescategory.setParticularname(DataUtil.emptyString(fromclass)+"-"+DataUtil.emptyString(toclass));
                 }else{
                         feescategory.setParticularname(DataUtil.emptyString(fromclass));
                 }
                 
                 feescategory.setAmount(DataUtil.parseInt(amount));
                 feescategory.setBranchid(Integer.parseInt(branchId));
                 if(!feescategory.getFeescategoryname().equalsIgnoreCase("") && !feescategory.getParticularname().equalsIgnoreCase("") && feescategory.getAmount() != 0 ){
                         feescategory =  new feesCategoryDAO().create(feescategory);
                 }
         }
         sb.append("}");
 		return sb.toString();    
 }
	 

public String deleteMultiple(String[] idfeescategory) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
    if(idfeescategory!=null){
   List ids = new ArrayList();
   for (String id : idfeescategory) {
       System.out.println("id" + id);
       ids.add(Integer.valueOf(id));
   }
   new feesCategoryDAO().deleteMultiple(ids);
    }
    sb.append("}");
	return sb.toString();
}


public String deleteFeesCategory(String[] idfeescategory,  String studentId) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
    
    List sfsId = new ArrayList();
    List feesCatId = new ArrayList();
    
    
    if(idfeescategory!=null){
            
            for (String string : idfeescategory) {
                    String[] test = string.split("_");
                    sfsId.add(Integer.valueOf(test[0]));
                    feesCatId.add(Integer.valueOf(test[1]));
           }
   new feesCategoryDAO().deleteFeesCategory(sfsId,feesCatId,studentId);
   
   return "Controller?process=StudentProcess&action=ViewFeesStructure&id="+studentId;
    }
    sb.append("}");
   return "error.jsp";
   
}  
}

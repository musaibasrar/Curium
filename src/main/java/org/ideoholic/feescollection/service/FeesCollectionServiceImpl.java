package org.ideoholic.feescollection.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ideoholic.feescollection.dto.FeesCollectionProfileDto;

import com.model.feescollection.dao.feesCollectionDAO;
import com.model.feescollection.dto.Feescollection;
import com.model.feescollection.dto.Receiptinfo;
import com.model.feesdetails.dao.feesDetailsDAO;
import com.model.feesdetails.dto.Feesdetails;
import com.model.student.dao.studentDetailsDAO;
import com.model.student.dto.Studentfeesstructure;
import com.model.user.dao.UserDAO;
import com.util.DataUtil;

public class FeesCollectionServiceImpl implements FeesCollectionService {
	
	private Object branchName;

	public String add(Feesdetails feesdetails,String sid,String[] feesIDS, String[] feesMonths,String[] feesAmounts,
			String[] feesCat) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");

		Feescollection feescollection = new Feescollection();
		//Feesdetails feesdetails = new Feesdetails();
		String months = null;
		String amount = null;
		String feesCategory = null;

		if(feesIDS!=null && feesMonths!=null && feesAmounts!=null && feesCat!=null){
		/*for (int i = 0; i < feesIDS.length; i++) {
			feescollection.setFeeid(DataUtil.parseInt(feesIDS[i]));
			months = DataUtil.emptyString(feesMonths[i]);
			amount = DataUtil.emptyString(feesAmounts[i]);
			feesCategory = DataUtil.emptyString(feesCat[i]);

			feescollection.setFeescategory(feesCategory);
			feescollection.setFeesamount(amount);
			feescollection.setFormonth(months);
			feescollection.setSid(DataUtil.parseInt(sid));
			feescollection.setFeesdetailsid(feesdetails.getFeesdetailsid());
			feescollection = new feesCollectionDAO().create(feescollection);
		}*/

		}
		sb.append("}");
		return sb.toString();
	}
	
	public String getStampFees(String currentAcademicYear, long id, String studentName,String admno,String classandsec,
			String studentId,String dateoffees) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		if(currentAcademicYear!=null){
		List<Studentfeesstructure> feesstructure = new studentDetailsDAO().getStudentFeesStructure(id, currentAcademicYear.toString());
		//List<Feescollection> feesCollection = new feesCollectionDAO().getFeesForTheCurrentYear(id, httpSession.getAttribute(CURRENTACADEMICYEAR).toString());
		Map<Studentfeesstructure,Long> feesMap = new HashMap<Studentfeesstructure, Long>();
		
		for (Map.Entry<Studentfeesstructure, Long> feescollection2 : feesMap.entrySet()) {
			Studentfeesstructure sf = feescollection2.getKey();
			sf.getFeescategory().getFeescategoryname();
		}
		
		for (Studentfeesstructure singleFeesStructure : feesstructure) {
			Long totalAmountPerCategory = 0l;
			/*for (Feescollection singleFeescollection : feesCollection) {
				
				if(singleFeescollection.getSfsid() == singleFeesStructure.getSfsid()){
					totalAmountPerCategory = totalAmountPerCategory + singleFeescollection.getAmountpaid();
				}
				
			}*/
			Long totalDueAmount = singleFeesStructure.getFeesamount() - singleFeesStructure.getFeespaid();
			feesMap.put(singleFeesStructure,totalDueAmount);
		}
		sb.append("studentfeesdetails").append(feesMap);
		sb.append("studentNameDetails").append(studentName);
		sb.append("admnoDetails").append(admno);
		sb.append("classandsecDetails").append(classandsec);
		sb.append("studentIdDetails").append(studentId);
		sb.append("dateoffeesDetails").append(dateoffees);
		
		}
		sb.append("}");
		return sb.toString();
	}
	
public String cancelFeesReceipt(String currentAcademicYear, int receiptId) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
		
		if(currentAcademicYear!=null){
			
			List<Feescollection> feesCollection = new feesCollectionDAO().getFeesCollectionDetails(receiptId);
			boolean result = new feesDetailsDAO().cancelFeesReceipt(receiptId, feesCollection);
			
			sb.append("cancelreceiptresult").append(result);
				
		}
		sb.append("}");
		return sb.toString();
	}

public String viewCancelledReceipts(String branchId,String branchId1,String toDate,String fromDate,String oneDay, String dayOne,
		String dayonecancel, String datefromcancel, String datetocancel) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
	 
	List<Receiptinfo> feesDetailsList = new ArrayList<Receiptinfo>();
	int idBranch = 0;
           
	if(branchId1!=null){
	

        if(branchId1!=null) {
        	String[] branchIdName = branchId1.split(":");
        	idBranch = Integer.parseInt(branchIdName[0]);
        	sb.append("feesdetailsbranchname").append(branchIdName[1]);
        	sb.append("branchname").append(branchName);
        }else {
        	idBranch = Integer.parseInt(branchId.toString());
        }
        
	String queryMain ="From Receiptinfo as feesdetails where cancelreceipt=1 and feesdetails.branchid="+idBranch+" AND";
	
		String querySub = "";
		
		if(!oneDay.equalsIgnoreCase("")){
			querySub = " feesdetails.date = '"+oneDay+"'" ;
			sb.append("dayonecancel").append(oneDay);
			 sb.append("datefromcancel").append("");
			 sb.append("datetocancel").append("");
		}else if(!"".equalsIgnoreCase(DataUtil.emptyString(dayOne))) {
			querySub = " feesdetails.date = '"+dayonecancel+"'" ;
		}
		
		if(!fromDate.equalsIgnoreCase("")  && !toDate.equalsIgnoreCase("")){
			querySub = " feesdetails.date between '"+fromDate+"' AND '"+toDate+"'";
			sb.append("datefromcancel").append(fromDate);
			sb.append("datetocancel").append(toDate);
			sb.append("dayonecancel").append("");
			
		}else if(!"".equalsIgnoreCase(DataUtil.emptyString(datefromcancel)) && 
				!"".equalsIgnoreCase(DataUtil.emptyString(datetocancel)) ) {
			querySub = " feesdetails.date between '"+datefromcancel+"' AND '"+datetocancel+"'";
		}
		
		queryMain = queryMain+querySub;
		/*queryMain = "FROM Parents as parents where  parents.Student.dateofbirth = '2006-04-06'"; */
		feesDetailsList = new UserDAO().getReceiptDetailsList(queryMain);
		
}
		long sumOfFees = 0l;
		for (Receiptinfo receiptinfo : feesDetailsList) {
			sumOfFees = sumOfFees + receiptinfo.getTotalamount();
		}
		
		sb.append("searchfeesdetailslistcancelled").append(feesDetailsList);
		sb.append("sumofdetailsfeescancelled").append(sumOfFees);
		sb.append("}");
		return sb.toString();
}

}

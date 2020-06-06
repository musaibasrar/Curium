package org.ideoholic.feescollection.service;

import com.model.feescollection.dto.Feescollection;
import com.model.feesdetails.dto.Feesdetails;

public interface FeesCollectionService {



	 String add(Feesdetails feesdetails, String sid, String[] feesIDS, String[] feesMonths, String[] feesAmounts,
			String[] feesCat);

	String getStampFees(String currentAcademicYear, long id, String studentName, String admno, String classandsec,
			String studentId, String dateoffees);

	String cancelFeesReceipt(String currentAcademicYear, int receiptId);

	String viewCancelledReceipts(String branchId, String branchId1, String toDate, String fromDate, String oneDay,
			String dayOne, String dayonecancel, String datefromcancel, String datetocancel);

}

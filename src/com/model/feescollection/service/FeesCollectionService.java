package com.model.feescollection.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.employee.dao.EmployeeDAO;
import com.model.employee.dto.Teacher;
import com.model.feescollection.dao.feesCollectionDAO;
import com.model.feescollection.dto.Feescollection;
import com.model.feescollection.dto.Receiptinfo;
import com.model.feesdetails.dao.feesDetailsDAO;
import com.model.feesdetails.dto.Feesdetails;
import com.model.parents.dao.parentsDetailsDAO;
import com.model.parents.dto.Parents;
import com.model.student.dao.studentDetailsDAO;
import com.model.student.dto.Student;
import com.model.student.dto.Studentfeesstructure;
import com.util.DataUtil;
import com.util.DateUtil;
import com.util.NumberToWord;

public class FeesCollectionService {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession httpSession;
	private String CURRENTACADEMICYEAR = "currentAcademicYear";

	public FeesCollectionService(HttpServletRequest request,
			HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.httpSession = request.getSession();
	}

	public Feescollection add(Feesdetails feesdetails) {

		Feescollection feescollection = new Feescollection();
		//Feesdetails feesdetails = new Feesdetails();
		String months = null;
		String amount = null;
		String feesCategory = null;

		String sid = request.getParameter("studentId");
		String[] feesIDS = request.getParameterValues("feesIDS");
		String[] feesMonths = request.getParameterValues("feesQuantities");
		String[] feesAmounts = request.getParameterValues("feesAmounts");
		String[] feesCat = request.getParameterValues("feesNames");
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

		return feescollection;
	}

	public void preview(Feescollection feescollection) {

		/*if(feescollection != null){
    List<Feescollection> list = new feesCollectionDAO()
				.readListOfObject(feescollection.getFeesdetailsid());
		httpSession.setAttribute("feescollection", list);
		Student student = new studentDetailsDAO()
				.readUniqueObject(feescollection.getSid());
		httpSession.setAttribute("student", student);
		Feesdetails feesdetails = new feesDetailsDAO()
				.readUniqueObject(feescollection.getFeesdetailsid().longValue());
		httpSession.setAttribute("feesdetails", feesdetails);
		}*/
	}

	public void preview() {
		
		 long sid=DataUtil.parseLong(request.getParameter("sid"));	
		 int id=DataUtil.parseInt(request.getParameter("id"));
		 long idFees=DataUtil.parseLong(request.getParameter("id"));
		    List<Feescollection> list = new feesCollectionDAO()
						.readListOfObject(id);
				httpSession.setAttribute("feescollection", list);
				Student student = new studentDetailsDAO()
						.readUniqueObject(sid);
				httpSession.setAttribute("student", student);
				Parents parents = new parentsDetailsDAO().readUniqueObject(sid);
				httpSession.setAttribute("parents", parents);
				Feesdetails feesdetails = new feesDetailsDAO()
						.readUniqueObject(idFees);
				NumberToWord toWord = new NumberToWord();
				String grandTotal = "";
				if(feesdetails.getGrandtotal() != null || !feesdetails.getGrandtotal().equalsIgnoreCase("")){
					String gTotal = feesdetails.getGrandtotal();
					gTotal =  gTotal.substring(0, gTotal.indexOf('.'));
					grandTotal = toWord.convert(Integer.parseInt(gTotal));
				}
				
				StringBuffer res = new StringBuffer();
				String[] strArr = grandTotal.split(" ");
				
				for(String str : strArr){
					char[] stringArray = str.trim().toCharArray();
					stringArray[0] = Character.toUpperCase(stringArray[0]);
					str = new String(stringArray);
					res.append(str).append(" ");
				}
				grandTotal = res.toString().trim();
				httpSession.setAttribute("grandTotal", grandTotal+" "+"Only");
				httpSession.setAttribute("feesdetails", feesdetails);
		
	}

	public void getStampFees() {
		if(httpSession.getAttribute(CURRENTACADEMICYEAR)!=null){
		long id = Long.parseLong(request.getParameter("studentId"));
		List<Studentfeesstructure> feesstructure = new studentDetailsDAO().getStudentFeesStructure(id, httpSession.getAttribute(CURRENTACADEMICYEAR).toString());
		Object cay = httpSession.getAttribute(CURRENTACADEMICYEAR);
		List<Feescollection> feesCollection = new feesCollectionDAO().getFeesForTheCurrentYear(id, httpSession.getAttribute(CURRENTACADEMICYEAR).toString());
		Map<Studentfeesstructure,Long> feesMap = new HashMap<Studentfeesstructure, Long>();
		
		for (Map.Entry<Studentfeesstructure, Long> feescollection2 : feesMap.entrySet()) {
			Studentfeesstructure sf = feescollection2.getKey();
			sf.getFeescategory().getFeescategoryname();
		}
		
		String feescat = feesstructure.get(0).getFeescategory().getFeescategoryname();
		for (Studentfeesstructure singleFeesStructure : feesstructure) {
			Long totalAmountPerCategory = 0l;
			for (Feescollection singleFeescollection : feesCollection) {
				
				if(singleFeescollection.getSfsid() == singleFeesStructure.getSfsid()){
					totalAmountPerCategory = totalAmountPerCategory + singleFeescollection.getAmountpaid();
				}
				
			}
			Long totalDueAmount = singleFeesStructure.getFeesamount() - totalAmountPerCategory;
			feesMap.put(singleFeesStructure,totalDueAmount);
		}
		request.setAttribute("studentfeesdetails", feesMap);
		request.setAttribute("studentNameDetails", request.getParameter("studentName"));
		request.setAttribute("admnoDetails", request.getParameter("admno"));
		request.setAttribute("classandsecDetails", request.getParameter("classandsec"));
		request.setAttribute("studentIdDetails", request.getParameter("studentId"));
		request.setAttribute("dateoffeesDetails", request.getParameter("dateoffees"));
		
		}
	}

	public Receiptinfo add() {
		
		List<Feescollection> feescollection = new ArrayList<Feescollection>();
		Receiptinfo receiptInfo =new Receiptinfo();
		boolean createFeesCollection = false;
		if(httpSession.getAttribute(CURRENTACADEMICYEAR)!=null){
		
		String sid = request.getParameter("studentIdDetails");
		String[] amountPaying = request.getParameterValues("amountpaying");
		String[] fine = request.getParameterValues("fine");
		String grandTotalAmount = request.getParameter("grandTotalAmount");
		//String[] idsfeescategory = request.getParameterValues("idfeescategory");
		String[] studentSfsIds = request.getParameterValues("studentsfsids");
		
		if(studentSfsIds!=null){
			
			// create receipt information
			receiptInfo.setAcademicyear(httpSession.getAttribute(CURRENTACADEMICYEAR).toString());
			receiptInfo.setDate(new Date());
			receiptInfo.setSid(DataUtil.parseInt(sid));
			receiptInfo.setTotalamount(DataUtil.parseLong(grandTotalAmount));
			new feesCollectionDAO().createReceipt(receiptInfo);
			 
			if(receiptInfo.getReceiptnumber()!= null){
				for (int i = 0; i < studentSfsIds.length; i++) {
					Feescollection feesCollect = new Feescollection();
					feesCollect.setSfsid(DataUtil.parseInt(studentSfsIds[i]));
					feesCollect.setAmountpaid(DataUtil.parseLong(amountPaying[i]));
					feesCollect.setSid(DataUtil.parseInt(sid));
					feesCollect.setFine(DataUtil.parseLong(fine[i]));
					feesCollect.setDate(new Date());
					feesCollect.setAcademicyear(httpSession.getAttribute(CURRENTACADEMICYEAR).toString());
					feesCollect.setReceiptnumber(receiptInfo.getReceiptnumber());
					feescollection.add(feesCollect);
				}
				createFeesCollection = new feesCollectionDAO().create(feescollection);
				
			}
		}
		}
		return receiptInfo;
	}

	public void preview(Receiptinfo receiptInfo) {
		
		if(httpSession.getAttribute(CURRENTACADEMICYEAR)!=null){
		
			Receiptinfo rinfo = new feesCollectionDAO().getReceiptInfoDetails(receiptInfo.getReceiptnumber(),httpSession.getAttribute(CURRENTACADEMICYEAR).toString());
			Set<Feescollection> setFeesCollection = rinfo.getFeesCollectionRecords();
			Map<String,Long> feeCatMap = new HashMap<String, Long>();

			for (Feescollection feescollectionSingle : setFeesCollection) {
				List<Studentfeesstructure> studentfeesstructure = new studentDetailsDAO().getStudentFeesStructureDetails(feescollectionSingle.getSfsid());
				feeCatMap.put(studentfeesstructure.get(0).getFeescategory().getFeescategoryname(), feescollectionSingle.getAmountpaid());
			}
			Date receiptDate = receiptInfo.getDate();
			String reDate = new SimpleDateFormat("yyyy-MM-dd").format(receiptDate);
			Student student = new studentDetailsDAO().readUniqueObject(receiptInfo.getSid());
			httpSession.setAttribute("student", student);
			request.setAttribute("recieptdate", reDate);
			request.setAttribute("recieptinfo", receiptInfo);
			request.setAttribute("feescatmap", feeCatMap);
		}
		
	}

	public void previewDetails() {
		
		
		if(httpSession.getAttribute(CURRENTACADEMICYEAR)!=null){
			String receiptNumber = request.getParameter("id");
			String dp = request.getParameter("duplicate");
			Receiptinfo rinfo = new feesCollectionDAO().getReceiptInfoDetails(Integer.parseInt(receiptNumber),httpSession.getAttribute(CURRENTACADEMICYEAR).toString());
			Set<Feescollection> setFeesCollection = rinfo.getFeesCollectionRecords();
			Map<String,Long> feeCatMap = new HashMap<String, Long>();

			for (Feescollection feescollectionSingle : setFeesCollection) {
				List<Studentfeesstructure> studentfeesstructure = new studentDetailsDAO().getStudentFeesStructureDetails(feescollectionSingle.getSfsid());
				feeCatMap.put(studentfeesstructure.get(0).getFeescategory().getFeescategoryname(), feescollectionSingle.getAmountpaid());
			}
			Date receiptDate = rinfo.getDate();
			String reDate = new SimpleDateFormat("yyyy-MM-dd").format(receiptDate);
			Student student = new studentDetailsDAO().readUniqueObject(rinfo.getSid());
			Parents parents = new parentsDetailsDAO().readUniqueObject(rinfo.getSid());
			httpSession.setAttribute("parents", parents);
			httpSession.setAttribute("student", student);
			request.setAttribute("recieptdate", reDate);
			request.setAttribute("recieptinfo", rinfo);
			request.setAttribute("feescatmap", feeCatMap);
			request.setAttribute("duplicate", dp);
			NumberToWord toWord = new NumberToWord();
			String grandTotal = toWord.convert(rinfo.getTotalamount().intValue());
			httpSession.setAttribute("grandTotal", grandTotal+" "+"Only");
		}
		
	}

	public void previewFeesDetails() {
		
		if(httpSession.getAttribute(CURRENTACADEMICYEAR)!=null){
			long sid=DataUtil.parseLong(request.getParameter("sid"));	
			int receiptNo = DataUtil.parseInt(request.getParameter("id"));
			 
			Receiptinfo rinfo = new feesCollectionDAO().getReceiptInfoDetails(receiptNo,httpSession.getAttribute(CURRENTACADEMICYEAR).toString());
			Set<Feescollection> setFeesCollection = rinfo.getFeesCollectionRecords();
			Map<String,Long> feeCatMap = new HashMap<String, Long>();

			for (Feescollection feescollectionSingle : setFeesCollection) {
				List<Studentfeesstructure> studentfeesstructure = new studentDetailsDAO().getStudentFeesStructureDetails(feescollectionSingle.getSfsid());
				feeCatMap.put(studentfeesstructure.get(0).getFeescategory().getFeescategoryname(), feescollectionSingle.getAmountpaid());
			}
			Date receiptDate = rinfo.getDate();
			String reDate = new SimpleDateFormat("yyyy-MM-dd").format(receiptDate);
			Student student = new studentDetailsDAO().readUniqueObject(sid);
			httpSession.setAttribute("student", student);
			request.setAttribute("recieptdate", reDate);
			request.setAttribute("recieptinfo", rinfo);
			request.setAttribute("feescatmap", feeCatMap);
			request.setAttribute("duplicate", "duplicate");
			
		}
	}
	}



package org.ideoholic.curium.model.enquiry.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.enquiry.dao.EnquiryDAO;
import org.ideoholic.curium.model.enquiry.dto.Enquiry;
import org.ideoholic.curium.model.job.dao.JobDAO;
import org.ideoholic.curium.model.task.dto.Task;
import org.ideoholic.curium.util.DataUtil;
import org.ideoholic.curium.util.DateUtil;

public class EnquiryService {
	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession httpSession;

	

	public EnquiryService(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.httpSession = request.getSession();
	}



	public void getCertificate() {
		
		
	    String name= request.getParameter("subject");		
	    String place= request.getParameter("place");	
	    String mobile= request.getParameter("mobile");	
	    String date= request.getParameter("date");
	    request.setAttribute("name", name);
		 request.setAttribute("place", place);
		 request.setAttribute("mobile", mobile);
		 request.setAttribute("date", date);

	    Enquiry enquiry = new Enquiry();
        
       	 
                
	    enquiry.setName(name);
	    enquiry.setAddress(place);
	    enquiry.setMobileno(mobile);
	          
                enquiry =  new EnquiryDAO().create(enquiry);
                }



	public void saveEnquiry() {
		
		
	    String name= request.getParameter("name");		
	    String place= request.getParameter("place");	
	    String mobile= request.getParameter("mobile");
	    String fatherName= request.getParameter("fathername");
	    String motherName= request.getParameter("mothername");
	    String admissionClass= request.getParameter("admissionclass");
	    String createdDate= request.getParameter("createddate");
	    String siblings= request.getParameter("siblings");
	    String academicYear = request.getParameter("yearofadmission");
	    Enquiry enquiry = new Enquiry();
	    enquiry.setName(name);
	    enquiry.setAddress(place);
	    enquiry.setMobileno(mobile);
	    enquiry.setFathername(fatherName);
	    enquiry.setMothername(motherName);
	    enquiry.setAdmissionclass(admissionClass);
	    enquiry.setCreateddate(DateUtil.indiandateParser(createdDate));
	    enquiry.setSiblings(siblings);
	    enquiry.setAcademicyear(academicYear);
	    enquiry.setStatus("Processing");
	    enquiry.setBranchid(Integer.parseInt(httpSession.getAttribute("branchid").toString()));
	    enquiry.setUserid(Integer.parseInt(httpSession.getAttribute("userloginid").toString()));
        enquiry =  new EnquiryDAO().create(enquiry);
       
	}



	public boolean getEnquiries() {


		boolean result = false;
		//String pages = "1";
		if(httpSession.getAttribute("branchid")!=null){
			try {
				int page = 1;
				int recordsPerPage = 500;
					if (!"".equalsIgnoreCase(DataUtil.emptyString(request.getParameter("page")))) {
						page = Integer.parseInt(request.getParameter("page"));
					}

				List<Enquiry> list = new EnquiryDAO().readListOfObjectsPaginationEnquiry((page - 1) * recordsPerPage,
						recordsPerPage, Integer.parseInt(httpSession.getAttribute("branchid").toString()));
				int noOfRecords = new EnquiryDAO().getNoOfRecordsEnquiry(Integer.parseInt(httpSession.getAttribute("branchid").toString()));
				int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
				request.setAttribute("enquirylist", list);
				request.setAttribute("noOfPages", noOfPages);
				request.setAttribute("currentPage", page);
				result = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return result;
	
		
	}



	public void approved() {
		
		String[] enquiryIds = request.getParameterValues("enquiryids");
		int userId = Integer.parseInt(httpSession.getAttribute("userloginid").toString());
		List<Integer> enquiryIdsList = new ArrayList<Integer>();
		boolean result = false;
		
		if(enquiryIds!=null) {
			for (String ids : enquiryIds) {
				enquiryIdsList.add(Integer.parseInt(ids));
			}
			
			result = new EnquiryDAO().updateStatus(enquiryIdsList, userId, "Approved");
			
			
			if(result){
				request.setAttribute("querycompleted","success");
				request.setAttribute("querystatus",true);
			}else {
				request.setAttribute("querystatus",false);
			}
		}
	}
	
	public void rejected() {
			
			String[] enquiryIds = request.getParameterValues("enquiryids");
			int userId = Integer.parseInt(httpSession.getAttribute("userloginid").toString());
			List<Integer> enquiryIdsList = new ArrayList<Integer>();
			boolean result = false;
			
			if(enquiryIds!=null) {
				for (String ids : enquiryIds) {
					enquiryIdsList.add(Integer.parseInt(ids));
				}
				
				result = new EnquiryDAO().updateStatus(enquiryIdsList, userId, "Rejected");
				
				
				if(result){
					request.setAttribute("querycompleted","success");
					request.setAttribute("querystatus",true);
				}else {
					request.setAttribute("querystatus",false);
				}
			}
		}
	
	public void processing() {
			
			String[] enquiryIds = request.getParameterValues("enquiryids");
			int userId = Integer.parseInt(httpSession.getAttribute("userloginid").toString());
			List<Integer> enquiryIdsList = new ArrayList<Integer>();
			boolean result = false;
			
			if(enquiryIds!=null) {
				for (String ids : enquiryIds) {
					enquiryIdsList.add(Integer.parseInt(ids));
				}
				
				result = new EnquiryDAO().updateStatus(enquiryIdsList, userId, "Processing");
				
				
				if(result){
					request.setAttribute("querycompleted","success");
					request.setAttribute("querystatus",true);
				}else {
					request.setAttribute("querystatus",false);
				}
			}
		}
	
		public void onHold() {
				
				String[] enquiryIds = request.getParameterValues("enquiryids");
				int userId = Integer.parseInt(httpSession.getAttribute("userloginid").toString());
				List<Integer> enquiryIdsList = new ArrayList<Integer>();
				boolean result = false;
				
				if(enquiryIds!=null) {
					for (String ids : enquiryIds) {
						enquiryIdsList.add(Integer.parseInt(ids));
					}
					
					result = new EnquiryDAO().updateStatus(enquiryIdsList, userId, "On Hold");
					
					
					if(result){
						request.setAttribute("querycompleted","success");
						request.setAttribute("querystatus",true);
					}else {
						request.setAttribute("querystatus",false);
					}
				}
			}
	   
		}


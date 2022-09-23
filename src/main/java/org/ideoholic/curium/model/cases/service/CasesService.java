package org.ideoholic.curium.model.cases.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.ideoholic.curium.model.cases.dao.CasesDAO;
import org.ideoholic.curium.model.cases.dto.Cases;
import org.ideoholic.curium.model.department.dto.Department;
import org.ideoholic.curium.model.employee.dto.Teacher;
import org.ideoholic.curium.model.job.dao.JobDAO;
import org.ideoholic.curium.model.parents.dao.parentsDetailsDAO;
import org.ideoholic.curium.model.parents.dto.Parents;
import org.ideoholic.curium.model.sendsms.service.SmsService;
import org.ideoholic.curium.model.student.dao.studentDetailsDAO;
import org.ideoholic.curium.model.task.dto.Task;
import org.ideoholic.curium.util.DataUtil;
import org.ideoholic.curium.util.DateUtil;

import org.ideoholic.curium.model.job.dto.JobQuery;
import org.ideoholic.curium.model.mess.item.dao.MessItemsDAO;
import org.ideoholic.curium.model.mess.item.dto.MessItems;
import org.ideoholic.curium.model.mess.stockentry.dao.MessStockEntryDAO;
import org.ideoholic.curium.model.mess.stockentry.dto.MessStockEntry;

public class CasesService {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession httpSession;
	private static final int BUFFER_SIZE = 4096;

	public CasesService(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.httpSession = request.getSession();
	}

	public boolean addCases() {
		
		boolean result = false;
		
		String[] studentId = request.getParameterValues("studentIDs");
		
		String[] pidContact = studentId[0].split(":");
		
		
		String assignto = request.getParameter("assignto");
		String[] dep = assignto.split(":");
		String court = request.getParameter("court");
		
		String supremecourt = request.getParameter("supremecourt");
		String highcourt = request.getParameter("highcourt");
		String ksatcourt = request.getParameter("ksatcourt");
		String districtcourt = request.getParameter("districtcourt");
		String waqfcourt = request.getParameter("waqfcourt");
		String rticourt = request.getParameter("rticourt");
		String revenuecourt = request.getParameter("revenuecourt");
		String anyothercourt = request.getParameter("anyothercourt");
		
		String caseTitle = request.getParameter("casetitle");
		String caseNo = request.getParameter("caseno");
		String courtName = request.getParameter("courtname");
		String courtNameDistrict = request.getParameter("districtcourtname");
		String courtNameRevenue = request.getParameter("revenuecourtname");
		String caseYear = request.getParameter("caseyear");

		String petitioner = request.getParameter("petitioner");
		String respondent = request.getParameter("respondent");
		
		String[] referredby = request.getParameterValues("referredby");
		String caseNoDetails = "";
		StringBuilder sbf = new StringBuilder();
		if(httpSession.getAttribute("branchid")!=null){
								
					Cases cases = new Cases();
					cases.setCourt(court);
					cases.setCasetitle(petitioner+" V/s "+respondent);
					caseNoDetails = supremecourt+highcourt+ksatcourt+districtcourt+waqfcourt+rticourt+anyothercourt+revenuecourt+"_"+caseNo+"_"+caseYear;
					cases.setCasenumber(caseNoDetails);
					
					cases.setCourtname(courtName+courtNameDistrict+courtNameDistrict);
					cases.setAssignto(Integer.parseInt(dep[0]));
					cases.setBranchid(Integer.parseInt(httpSession.getAttribute("branchid").toString()));
					cases.setCreateddate(new Date());
					cases.setCreateduserid(Integer.parseInt(httpSession.getAttribute("userloginid").toString()));
					cases.setStatus("Pending");
					cases.setSid(Integer.parseInt(pidContact[3]));
					
					if(referredby!=null) {
						for (String string : referredby) {
							sbf.append(string+",");
						}
						cases.setReferredby(sbf.toString());
					}
					String resultCases = new CasesDAO().addCases(cases);
					String sendQuerySMS = new DataUtil().getPropertiesValue("sendcasessms");
									
					if(resultCases!=null && "yes".equalsIgnoreCase(sendQuerySMS)) {
						result = true;
						 String messageClient = "Your File No. "+resultCases+" is alloted to "+dep[1]+", Mobile No "+dep[2]+"";
						 String messageInternal = "You are alloted with File No. "+resultCases+", client name: "+pidContact[2]+", contact no. "+pidContact[1]+" ";
						 
						 new SmsService(request, response).sendSMS("91"+pidContact[1], messageClient);
						 new SmsService(request, response).sendSMS("91"+dep[1], messageInternal);
					}else if(resultCases!=null && "no".equalsIgnoreCase(sendQuerySMS)) {
						result = true;
					}
				}
		
		return result;
	}

	
	public boolean viewAllCases() {

		boolean result = false;
		//String pages = "1";
		if(httpSession.getAttribute("branchid")!=null){
			try {
				int page = 1;
				int recordsPerPage = 500;
					if (!"".equalsIgnoreCase(DataUtil.emptyString(request.getParameter("page")))) {
						page = Integer.parseInt(request.getParameter("page"));
					}

				List<Cases> list = new CasesDAO().readListOfObjectsPagination((page - 1) * recordsPerPage,
						recordsPerPage, Integer.parseInt(httpSession.getAttribute("branchid").toString()));
				int noOfRecords = new CasesDAO().getNoOfRecords(Integer.parseInt(httpSession.getAttribute("branchid").toString()));
				int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
				request.setAttribute("caseslist", list);
				request.setAttribute("noOfPages", noOfPages);
				request.setAttribute("currentPage", page);
				result = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}

	public void completeQueries() {
		
		String[] QueryIds = request.getParameterValues("queryids");
		int userId = Integer.parseInt(httpSession.getAttribute("userloginid").toString());
		List<Integer> QueryIdsList = new ArrayList<Integer>();
		List<JobQuery> result = new ArrayList<JobQuery>();;
		
		if(QueryIds!=null) {
			for (String ids : QueryIds) {
				QueryIdsList.add(Integer.parseInt(ids));
			}
			
			result = new JobDAO().completeQueries(QueryIdsList, userId);
			String sendCompletedQuerySMS = new DataUtil().getPropertiesValue("sendcompletedquerysms");
			
			if(!result.isEmpty() && "yes".equalsIgnoreCase(sendCompletedQuerySMS)) {
				request.setAttribute("querycompleted","success");
				request.setAttribute("querystatus",true);
				for (JobQuery JobQuery : result) {
					 String message = "Your enquiry with enq. no "+JobQuery.getExternalid()+" related to "+JobQuery.getTeacher().getTeachername()+" has been resolved.";
					 new SmsService(request, response).sendSMS("91"+JobQuery.getParent().getContactnumber(), message);
				}
			}
		}
	}

	public void cancelQueries() {
		
		String[] QueryIds = request.getParameterValues("queryids");
		int userId = Integer.parseInt(httpSession.getAttribute("userloginid").toString());
		List<Integer> QueryIdsList = new ArrayList<Integer>();
		boolean result = false;
		
		if(QueryIds!=null) {
			for (String ids : QueryIds) {
				QueryIdsList.add(Integer.parseInt(ids));
			}
			
			result = new JobDAO().cancelQueries(QueryIdsList, userId);
			request.setAttribute("querystatus",result);
		}
		
	}
	
	public void pendingCases() {
		
		String[] casesIds = request.getParameterValues("casesids");
		int userId = Integer.parseInt(httpSession.getAttribute("userloginid").toString());
		List<Integer> casesIdsList = new ArrayList<Integer>();
		boolean result = false;
		
		if(casesIds!=null) {
			for (String ids : casesIds) {
				casesIdsList.add(Integer.parseInt(ids));
			}
			
			result = new CasesDAO().pendingCases(casesIdsList, userId);
			request.setAttribute("casestatus",result);
		}
		
	}

	public void viewQueryDetails() throws IOException {
		
		
		if(httpSession.getAttribute("branchid")!=null){
			
			int queryId = Integer.parseInt(request.getParameter("id"));
			
			JobQuery JobQuery = new JobDAO().viewQueryDetails(queryId);
			
			PrintWriter out = response.getWriter(); 
			response.setContentType("text/xml");
		        response.setHeader("Cache-Control", "no-cache");
		        
		        
		        try {
		        	
		        	StringBuilder tableBuilder = new StringBuilder(
		        				"<table  style='margin-left: auto;margin-right: auto;'>" + 
		        				"						<tr>" + 
		        				"							" + 
		        				"						</tr>" + 
		        				"					</table>"
		        			
		        			
		        			);
		        	
		        	
		        	StringBuilder rowBuidler = new StringBuilder( "<table border='0' style='margin-left: auto;margin-right: auto;' style='border-color:#4b6a84' id='querydetailspopup'>");
		        	
		        			   
		        		rowBuidler.append(
		        				   "<tr style='border-color:#000000' border='0' cellpadding='1' cellspacing='1'>" + 
		        	                 "<td class='alignLeft'>Query:</td>" + 
		        			         "<td class='dataText'><textarea name='JobQuerypopup' id='JobQuerypopup' rows='5' cols='38'>"+JobQuery.getResponse()+"</textarea>"
		        			         		+ "<input type='hidden' id='queryid' name='queryid' value='"+queryId+"'></td>" +
		        			         "</tr>"+
		        			         "<tr>" + 
		        	                 "<td><br><br></td>" + 
		        			         "</tr>"+
		        			         "<tr style='border-color:#000000' border='1' cellpadding='1' cellspacing='1' >" + 
		        			         "<td class='alignLeft'>Response:</td>" +
		        			         "<td class='dataText'><textarea name='responsepopup' id='responsepopup' rows='5' cols='38'>"+JobQuery.getResponse()+"</textarea></td>" + 
		        			         "</tr>");
		        		
		        		
		        	
		        	rowBuidler.append("</tbody>" + 
		        			"		                </table>");
		        	
		        	tableBuilder.append(rowBuidler.toString());
		        	String outputTable = tableBuilder.toString();
		        	
		        	response.getWriter().println(outputTable);
		        	
		        } catch (Exception e) {
		            out.write("<table> <tr><td>Data Not Available</td></tr></table>");
		        } finally {
		            out.flush();
		            out.close();
		        }
		}
		
		
	}

	public void updateQueries() {
		
			String queryId = request.getParameter("queryid");
			String JobQuery = request.getParameter("JobQuery");
			String response = request.getParameter("response");
			int userId = Integer.parseInt(httpSession.getAttribute("userloginid").toString());
			boolean result = false;
			JobQuery = JobQuery.replace("'", "''");
			response = response.replace("'", "''");
			result = new JobDAO().updateQueries(queryId, JobQuery, response, userId);
			request.setAttribute("querystatus",result);
		}

	public boolean viewAllQueriesDepartmentWise() {

		boolean result = false;
		//String pages = "1";
		if(httpSession.getAttribute("branchid")!=null){
			
			String[] loginDetails = httpSession.getAttribute("usertypedetails").toString().split("-");
			try {
				int page = 1;
				int recordsPerPage = 500;
					if (!"".equalsIgnoreCase(DataUtil.emptyString(request.getParameter("page")))) {
						page = Integer.parseInt(request.getParameter("page"));
					}

				List<JobQuery> list = new JobDAO().readListOfObjectsPaginationDepartmentWise((page - 1) * recordsPerPage,
						recordsPerPage, Integer.parseInt(httpSession.getAttribute("branchid").toString()), Integer.parseInt(loginDetails[1]));
				request.setAttribute("studentList", list);
				int noOfRecords = new JobDAO().getNoOfRecordsDepartmentWise(Integer.parseInt(httpSession.getAttribute("branchid").toString()), Integer.parseInt(loginDetails[1]));
				int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
				request.setAttribute("queryList", list);
				request.setAttribute("noOfPages", noOfPages);
				request.setAttribute("currentPage", page);
				result = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}

	public int getNoOfRecordsMonthly() {
		
				Date monthOf = new Date();
				String Currentmonth = null;   
				 
				Calendar cStart = Calendar.getInstance();
				cStart.setTime(monthOf);
				cStart.set(Calendar.DAY_OF_MONTH, cStart.getActualMinimum(Calendar.DAY_OF_MONTH));
				monthOf = cStart.getTime();
				Timestamp TimestampFrom = new Timestamp(monthOf.getTime());
				
				cStart.set(Calendar.DAY_OF_MONTH, cStart.getActualMaximum(Calendar.DAY_OF_MONTH));
				Date lastDayOfMonth = cStart.getTime();
				Timestamp Timestampto = new Timestamp(lastDayOfMonth.getTime());
				
				Currentmonth = cStart.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
				
				Date dateFrom = new Date(TimestampFrom.getTime());
				Date dateTo = new Date(Timestampto.getTime());
			    String fromDate = new SimpleDateFormat("yyyy-MM-dd").format(dateFrom);
			    String toDate = new SimpleDateFormat("yyyy-MM-dd").format(dateTo);
				
				
				httpSession.setAttribute("Currentmonth", Currentmonth+"'s");
			    return new JobDAO().getNoOfRecordsMonthly(fromDate, toDate);
	}

	public void generateQueriesReport() {
		
		String fromDate = DateUtil.dateFromatConversionSlash(request.getParameter("transactiondatefrom"));
		String toDate = DateUtil.dateFromatConversionSlash(request.getParameter("transactiondateto"));
		String dep = request.getParameter("employee");
		String[] depParts = dep.split(":");
		String status = request.getParameter("status");
		String studentId = request.getParameter("studentId");
		String admnno = request.getParameter("clientname");
		String studentName = request.getParameter("studentName");
		String queryMain = "from JobQuery pq where pq.createddate between '"+fromDate+"' and '"+toDate+"' ";
		String subQuery = "";
		List<JobQuery> JobQueryList = new ArrayList<JobQuery>();
				
		if(!dep.isEmpty()) {
			subQuery = "and pq.teacher.tid = "+Integer.parseInt(depParts[0])+"";
			httpSession.setAttribute("teacherselected", "teacher:&nbsp;"+depParts[1]);
		}else {
			httpSession.setAttribute("teacherselected", "");
		}
		
		if(!status.isEmpty()) {
			subQuery = subQuery + " and status = '"+status+"'";
			httpSession.setAttribute("statusselected", "Status:&nbsp;"+status);
		}else {
		httpSession.setAttribute("statusselected", "");
		}
		
		if(!studentId.isEmpty()) {
			subQuery = subQuery + "and pq.parent.Student.sid = '"+studentId+"'";
			httpSession.setAttribute("studentselected", "Student Name:&nbsp;"+studentName);
		}else {
			httpSession.setAttribute("studentselected", "");
		}
		
		JobQueryList = new JobDAO().generateQueriesReport(queryMain+subQuery);
		
		httpSession.setAttribute("parentquerylist", JobQueryList);
		httpSession.setAttribute("transactionfromdateselected", "From:"+request.getParameter("transactiondatefrom"));
		httpSession.setAttribute("transactiontodateselected", "To:"+request.getParameter("transactiondateto"));
		
	}

	public void getMonthlyQueries() {
		
		List<String> monthList = new LinkedList<String>();
		List<String> totalQueries = new LinkedList<String>();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date newdate = new Date();
		String todaysDate = df.format(newdate);
		List<JobQuery> JobQueryList = new ArrayList<JobQuery>();
		Date dateBefore = null;
		Date dateAfter = null;
		String queryMain = "from JobQuery pq where pq.status!='Cancelled' and ";
		String toDate = null;
		String fromDate = null;
		
		try {
			dateBefore = df.parse(todaysDate);
			dateAfter = df.parse(todaysDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Calendar start1 = Calendar.getInstance();
		start1.setTime(dateBefore);
		Calendar end1 = Calendar.getInstance();
		end1.setTime(dateAfter);
		start1.set(Calendar.MONTH, start1.getActualMinimum(Calendar.MONTH));
		start1.set(Calendar.DAY_OF_MONTH, start1.getActualMinimum(Calendar.DAY_OF_MONTH));
		end1.set(Calendar.MONTH, end1.getActualMaximum(Calendar.MONTH));
		end1.add(Calendar.DAY_OF_MONTH, 1);
		
		for (Date date = start1.getTime(); start1.before(end1); start1.add(Calendar.MONTH,+1), date = start1.getTime()) {
			fromDate = new SimpleDateFormat("YYYY-MM-dd").format(date);
			Calendar endday = Calendar.getInstance();
			endday.setTime(date);
			endday.set(Calendar.DAY_OF_MONTH, start1.getActualMaximum(Calendar.DAY_OF_MONTH));
			Date enddayofmonth = endday.getTime();
			toDate = new SimpleDateFormat("YYYY-MM-dd").format(enddayofmonth);
			String querySub = "";
			querySub = " pq.createddate between '"+fromDate+"' and '"+toDate+"'";
			JobQueryList = new JobDAO().generateQueriesReport(queryMain+querySub);
			
			totalQueries.add("\"" + JobQueryList.size() + "\"");
			//Date Format
			SimpleDateFormat month_date = new SimpleDateFormat("MMM yyyy", Locale.ENGLISH);
			String monthYear = month_date.format(date);
			
			monthList.add("\"" + monthYear + "\"");
		}
		
		request.setAttribute("monthlystudentsqueries", totalQueries);
		request.setAttribute("monthlist", monthList);
	}

	  public boolean feedback() {
		
		  boolean result =false;
		String id = request.getParameter("id");
		String pid = request.getParameter("no");
		String feedbackpoints = request.getParameter("feedback");
		
		result = new JobDAO().feedback(Integer.parseInt(id), pid, feedbackpoints);
		
		return result;
	}

	public boolean download() {
		boolean result = false;
		try {

			File downloadFile = new File(System.getProperty("java.io.tmpdir")+"/queriesreport.xlsx");
	        FileInputStream inStream = new FileInputStream(downloadFile);

	        // get MIME type of the file
			String mimeType = "application/vnd.ms-excel";

			// set content attributes for the response
			response.setContentType(mimeType);
			// response.setContentLength((int) bis.length());

			// set headers for the response
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"",
					"queriesreport.xlsx");
			response.setHeader(headerKey, headerValue);

			// get output stream of the response
			OutputStream outStream = response.getOutputStream();

			byte[] buffer = new byte[BUFFER_SIZE];
			int bytesRead = -1;

			// write bytes read from the input stream into the output stream
			while ((bytesRead = inStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, bytesRead);
			}

			inStream.close();
			outStream.close();
			result = true;
		} catch (Exception e) {
			System.out.println("" + e);
		}
		return result;
	}

	public boolean exportQueriesReport() {
		
		
		List<JobQuery> queriesList = (List<JobQuery>) httpSession.getAttribute("parentquerylist");
	
			boolean writeSucees = false;
			
			try {

				// Creating an excel file
				XSSFWorkbook workbook = new XSSFWorkbook();
				XSSFSheet sheet = workbook.createSheet("ListOfQueries");
				Map<String, Object[]> data = new HashMap<String, Object[]>();
				Map<String, Object[]> headerData = new HashMap<String, Object[]>();
				headerData.put("Header",
						new Object[] { "UID", "Job No.", "Created Date","Updated Date","Staff", "Client Name",
								"Contact Number", "Status"});
				int i = 1;
				for (JobQuery queryDetails : queriesList) {
					data.put(Integer.toString(i),
							new Object[] { DataUtil.emptyString(Integer.toString(queryDetails.getId())),  DataUtil.emptyString(queryDetails.getExternalid()),
									 DataUtil.emptyString(DateUtil.getStringDate(queryDetails.getCreateddate())),
									 DataUtil.emptyString(DateUtil.getStringDate(queryDetails.getUpdateddate())),
									 DataUtil.emptyString(queryDetails.getTeacher().getTeachername()),
									 DataUtil.emptyString(queryDetails.getParent().getStudent().getName()),
									 DataUtil.emptyString(queryDetails.getParent().getContactnumber()),
									 DataUtil.emptyString(queryDetails.getStatus())});
					i++;
				}
				Row headerRow = sheet.createRow(0);
				Object[] objArrHeader = headerData.get("Header");
				int cellnum1 = 0;
				for (Object obj : objArrHeader) {
					Cell cell = headerRow.createCell(cellnum1++);
					if (obj instanceof String)
						cell.setCellValue((String) obj);
				}
				Set<String> keyset = data.keySet();
				int rownum = 1;
				for (String key : keyset) {
					Row row = sheet.createRow(rownum++);
					Object[] objArr = data.get(key);
					int cellnum = 0;
					for (Object obj : objArr) {
						Cell cell = row.createCell(cellnum++);
						if (obj instanceof Date)
							cell.setCellValue((Date) obj);
						else if (obj instanceof Boolean)
							cell.setCellValue((Boolean) obj);
						else if (obj instanceof String)
							cell.setCellValue((String) obj);
						else if (obj instanceof Double)
							cell.setCellValue((Double) obj);
					}
				}
					FileOutputStream out = new FileOutputStream(new File(System.getProperty("java.io.tmpdir")+"/queriesreport.xlsx"));
					workbook.write(out);
					out.close();
					writeSucees = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return writeSucees;
			// getFile(name, path);
	}

	public void generateCasesReportForClient() {
		
		String studentId = request.getParameter("id");
		
		String queryMain = "from Cases as cases where cases.sid = '"+studentId+"'";
		List<Cases> casesQueryList = new ArrayList<Cases>();
				
		casesQueryList = new CasesDAO().generateCasesReport(queryMain);
		
		httpSession.setAttribute("caseslist", casesQueryList);
	}


	public void disposeCases() {
		
		String[] casesIds = request.getParameterValues("casesids");
		int userId = Integer.parseInt(httpSession.getAttribute("userloginid").toString());
		String disDate = request.getParameter("disposedate");
		String fileTaken = request.getParameter("disposefiletaken");
		String fileTakenBy = request.getParameter("disposefiletakenby");
		String fileTakenByName = request.getParameter("disposefiletakenbyname");
		String fileTakenByContact = request.getParameter("disposefiletakenbycontact");
		
		List<Integer> casesIdsList = new ArrayList<Integer>();
		boolean result = false;
		
		if(casesIds!=null) {
			for (String ids : casesIds) {
				casesIdsList.add(Integer.parseInt(ids));
			}
			
			Cases cases = new Cases();
			cases.setFiletaken(fileTaken);
			cases.setFiletakenby(fileTakenBy);
			cases.setFiletakenbyname(fileTakenByName);
			cases.setFiletakenbynumber(fileTakenByContact);
			
			result = new CasesDAO().disposeCases(casesIdsList, userId, cases, disDate);
			request.setAttribute("casesstatus",result);
		}
		
	}

	public void nocCases() {
		
		String[] casesIds = request.getParameterValues("casesids");
		int userId = Integer.parseInt(httpSession.getAttribute("userloginid").toString());
		String disDate = request.getParameter("nocdate");
		String fileTaken = request.getParameter("nocfiletaken");
		String fileTakenBy = request.getParameter("nocfiletakenby");
		String fileTakenByName = request.getParameter("nocfiletakenbyname");
		String fileTakenByContact = request.getParameter("nocfiletakenbycontact");
		
		List<Integer> casesIdsList = new ArrayList<Integer>();
		boolean result = false;
		
		if(casesIds!=null) {
			for (String ids : casesIds) {
				casesIdsList.add(Integer.parseInt(ids));
			}
			
			Cases cases = new Cases();
			cases.setFiletaken(fileTaken);
			cases.setFiletakenby(fileTakenBy);
			cases.setFiletakenbyname(fileTakenByName);
			cases.setFiletakenbynumber(fileTakenByContact);
			
			result = new CasesDAO().nocCases(casesIdsList, userId, cases, disDate);
			request.setAttribute("casesstatus",result);
		}
		
	}

	public void cancelCases() {
		
		String[] casesIds = request.getParameterValues("casesids");
		int userId = Integer.parseInt(httpSession.getAttribute("userloginid").toString());
		List<Integer> casesIdsList = new ArrayList<Integer>();
		boolean result = false;
		
		if(casesIds!=null) {
			for (String ids : casesIds) {
				casesIdsList.add(Integer.parseInt(ids));
			}
			
			result = new CasesDAO().cancelCases(casesIdsList, userId);
			request.setAttribute("casestatus",result);
		}
		
	}

	public void generateCasesReport() {
		
		String fromDate = DateUtil.dateFromatConversionSlash(request.getParameter("transactiondatefrom"));
		String toDate = DateUtil.dateFromatConversionSlash(request.getParameter("transactiondateto"));
		String court = request.getParameter("court");
		String status = request.getParameter("status");
		String studentId = request.getParameter("studentId");
		String admnno = request.getParameter("clientname");
		String studentName = request.getParameter("studentName");
		String queryMain = "from Cases as cases where cases.createddate between '"+fromDate+"' and '"+toDate+"' ";
		String subQuery = "";
		List<Cases> casesList = new ArrayList<Cases>();
				
		if(!court.isEmpty()) {
			subQuery = "and cases.court = '"+court+"' ";
			httpSession.setAttribute("courtselected", "court:&nbsp;"+court);
		}else {
			httpSession.setAttribute("courtselected", "");
		}
		
		if(!status.isEmpty()) {
			subQuery = subQuery + " and cases.status = '"+status+"'";
			httpSession.setAttribute("statusselected", "Status:&nbsp;"+status);
		}else {
		httpSession.setAttribute("statusselected", "");
		}
		
		if(!studentId.isEmpty()) {
			subQuery = subQuery + "and cases.sid = '"+studentId+"'";
			httpSession.setAttribute("studentselected", "Client Name:&nbsp;"+studentName);
		}else {
			httpSession.setAttribute("studentselected", "");
		}
		
		casesList = new CasesDAO().generateCasesReport(queryMain+subQuery);
		
		httpSession.setAttribute("caseslist", casesList);
		httpSession.setAttribute("transactionfromdateselected", "From:"+request.getParameter("transactiondatefrom"));
		httpSession.setAttribute("transactiontodateselected", "To:"+request.getParameter("transactiondateto"));
		
	}

	public void getReferredbyDetails() throws IOException {
		
		if(httpSession.getAttribute("branchid")!=null){
			
			
			PrintWriter out = response.getWriter();
			try {
				
			String ref = request.getParameter("referredby");
			
			String[] refBy = ref.split(",");
			List<Integer> sidList = new ArrayList<Integer>();
			for (String refered : refBy) {
				sidList.add(Integer.parseInt(refered));
			}
			
			List<Parents> parentsList = new ArrayList<Parents>();
			parentsList = new studentDetailsDAO().getReferredList(sidList);
			
			response.setContentType("text/xml");
		        response.setHeader("Cache-Control", "no-cache");
		        
		        
		        	
		        	StringBuilder rowBuidler = new StringBuilder( "<table border='1' style='margin-left: auto;margin-right: auto;' style='border-color:#4b6a84' id='vd'>" + 
			        													"<thead>" + 
			        													"<tr class='headerText' >" + 
			        													"<th>Client Name</th>" + 
			        													"<th>Contact Number</th>" + 
			        													"</tr>" + 
			        													"</thead>" + 
		        													"<tbody>");
		        	
		        	
		        	for (Parents listofParents : parentsList) {
		        		
		        		rowBuidler.append(
		        	                 "<tr style='border-color:#000000' border='1' cellpadding='1' cellspacing='1' >" + 
		        			         "<td class='dataText'>"+listofParents.getStudent().getName()+"</td>" + 
		        			         "<td class='dataText'>"+listofParents.getContactnumber()+"</td>" + 
		        			         "</tr>");
		        		
		        		
		        	}
		        	
		        	rowBuidler.append("</tbody>" + 
		        			"		                </table>");
		        	
		        	String outputTable = rowBuidler.toString();
		        	
		        	response.getWriter().println(outputTable);
		        	
		        } catch (Exception e) {
		            out.write("<table> <tr><td>Data Not Available</td></tr></table>");
		        } finally {
		            out.flush();
		            out.close();
		        }
		}
		
		
	}
}

package com.model.query.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
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

import com.model.appointment.dto.Appointment;
import com.model.department.dto.Department;
import com.model.feescollection.dto.Receiptinfo;
import com.model.mess.card.dto.Card;
import com.model.mess.item.dao.MessItemsDAO;
import com.model.mess.item.dto.MessItems;
import com.model.mess.stockentry.dao.MessStockEntryDAO;
import com.model.mess.stockentry.dto.MessStockEntry;
import com.model.mess.stockmove.dao.MessStockMoveDAO;
import com.model.mess.stockmove.dto.MessStockMove;
import com.model.parents.dto.Parents;
import com.model.printids.dao.PrintIdsDAO;
import com.model.query.dao.QueryDAO;
import com.model.query.dto.ParentQuery;
import com.model.sendsms.service.SmsService;
import com.model.student.dao.studentDetailsDAO;
import com.model.user.dao.UserDAO;
import com.util.DataUtil;
import com.util.DateUtil;
import com.util.StockIssuance;

public class QueryService {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession httpSession;
	private static final int BUFFER_SIZE = 4096;

	public QueryService(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.httpSession = request.getSession();
	}

	public boolean addQuery() {
		
		boolean result = false;
		
		String[] studentId = request.getParameterValues("studentIDs");
		String department = request.getParameter("department");
		String queryString = request.getParameter("parentquery");
		String[] dep = department.split(":");
		String[] pidContact = studentId[0].split(":");
		
		if(httpSession.getAttribute("branchid")!=null){
	
					ParentQuery query = new ParentQuery();
					query.setAcademicyear(DataUtil.emptyString(httpSession.getAttribute("currentAcademicYear").toString()));
					query.setQuery(queryString);
					query.setBranchid(Integer.parseInt(httpSession.getAttribute("branchid").toString()));
					query.setCreateddate(new Date());
					query.setCreateduserid(Integer.parseInt(httpSession.getAttribute("userloginid").toString()));
					query.setResponse("");
					query.setStatus("Assigned");
					//Query.setStdid(1);
					Parents parent = new Parents();
					parent.setPid(Integer.parseInt(pidContact[0]));
					query.setParent(parent);
					
					Department departmnet = new Department();
					departmnet.setDepid(Integer.parseInt(dep[0]));
					query.setDepartment(departmnet);
						  
					String resultQuery = new QueryDAO().addQuery(query);
					String sendQuerySMS = new DataUtil().getPropertiesValue("sendquerysms");
									
					if(resultQuery!=null && "yes".equalsIgnoreCase(sendQuerySMS)) {
						result = true;
						 String feedbacklink = new DataUtil().getPropertiesValue("feedbacklink");
						 String[] queryValues = resultQuery.split(":");
						 String param = "?id="+queryValues[1]+"&no="+pidContact[0]+"";
						 feedbacklink = feedbacklink+param;
						 String messageOne = "Your enquiry related to "+dep[1]+" has been submitted successfully";
						 String message= "Click "+feedbacklink+" to give feedback on enq. # "+queryValues[0]+"";
						 //String message = "Your enquiry no. is "+queryValues[0]+".Please click the link to give your feedback. "+feedbacklink+"";
						 new SmsService(request, response).sendSMS("91"+pidContact[1], messageOne);
						 new SmsService(request, response).sendSMS("91"+pidContact[1], message);
					}
				}
		
		return result;
	}

	
	public boolean viewAllQueries() {

		boolean result = false;
		//String pages = "1";
		if(httpSession.getAttribute("branchid")!=null){
			try {
				int page = 1;
				int recordsPerPage = 500;
					if (!"".equalsIgnoreCase(DataUtil.emptyString(request.getParameter("page")))) {
						page = Integer.parseInt(request.getParameter("page"));
					}

				List<ParentQuery> list = new QueryDAO().readListOfObjectsPagination((page - 1) * recordsPerPage,
						recordsPerPage, Integer.parseInt(httpSession.getAttribute("branchid").toString()));
				request.setAttribute("studentList", list);
				int noOfRecords = new QueryDAO().getNoOfRecords(Integer.parseInt(httpSession.getAttribute("branchid").toString()));
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

	public void completeQueries() {
		
		String[] QueryIds = request.getParameterValues("queryids");
		int userId = Integer.parseInt(httpSession.getAttribute("userloginid").toString());
		List<Integer> QueryIdsList = new ArrayList<Integer>();
		List<ParentQuery> result = new ArrayList<ParentQuery>();;
		
		if(QueryIds!=null) {
			for (String ids : QueryIds) {
				QueryIdsList.add(Integer.parseInt(ids));
			}
			
			result = new QueryDAO().completeQueries(QueryIdsList, userId);
			String sendCompletedQuerySMS = new DataUtil().getPropertiesValue("sendcompletedquerysms");
			
			if(!result.isEmpty() && "yes".equalsIgnoreCase(sendCompletedQuerySMS)) {
				request.setAttribute("querycompleted","success");
				request.setAttribute("querystatus",true);
				for (ParentQuery parentQuery : result) {
					 String message = "Your enquiry with enq. no "+parentQuery.getExternalid()+" related to "+parentQuery.getDepartment().getDepartmentname()+" has been resolved.";
					 new SmsService(request, response).sendSMS("91"+parentQuery.getParent().getContactnumber(), message);
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
			
			result = new QueryDAO().cancelQueries(QueryIdsList, userId);
			request.setAttribute("querystatus",result);
		}
		
	}
	
	public void inProgressQueries() {
		
		String[] queryIds = request.getParameterValues("queryids");
		int userId = Integer.parseInt(httpSession.getAttribute("userloginid").toString());
		List<Integer> QueryIdsList = new ArrayList<Integer>();
		boolean result = false;
		
		if(queryIds!=null) {
			for (String ids : queryIds) {
				QueryIdsList.add(Integer.parseInt(ids));
			}
			
			result = new QueryDAO().inProgressQueries(QueryIdsList, userId);
			request.setAttribute("querystatus",result);
		}
		
	}

	public void viewQueryDetails() throws IOException {
		
		
		if(httpSession.getAttribute("branchid")!=null){
			
			int queryId = Integer.parseInt(request.getParameter("id"));
			
			ParentQuery parentQuery = new QueryDAO().viewQueryDetails(queryId);
			
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
		        			         "<td class='dataText'><textarea name='parentquerypopup' id='parentquerypopup' rows='5' cols='38'>"+parentQuery.getQuery()+"</textarea>"
		        			         		+ "<input type='hidden' id='queryid' name='queryid' value='"+queryId+"'></td>" +
		        			         "</tr>"+
		        			         "<tr>" + 
		        	                 "<td><br><br></td>" + 
		        			         "</tr>"+
		        			         "<tr style='border-color:#000000' border='1' cellpadding='1' cellspacing='1' >" + 
		        			         "<td class='alignLeft'>Response:</td>" +
		        			         "<td class='dataText'><textarea name='responsepopup' id='responsepopup' rows='5' cols='38'>"+parentQuery.getResponse()+"</textarea></td>" + 
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
			String parentQuery = request.getParameter("parentquery");
			String response = request.getParameter("response");
			int userId = Integer.parseInt(httpSession.getAttribute("userloginid").toString());
			boolean result = false;
			result = new QueryDAO().updateQueries(queryId, parentQuery, response, userId);
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

				List<ParentQuery> list = new QueryDAO().readListOfObjectsPaginationDepartmentWise((page - 1) * recordsPerPage,
						recordsPerPage, Integer.parseInt(httpSession.getAttribute("branchid").toString()), loginDetails[1]);
				request.setAttribute("studentList", list);
				int noOfRecords = new QueryDAO().getNoOfRecordsDepartmentWise(Integer.parseInt(httpSession.getAttribute("branchid").toString()), loginDetails[1]);
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
			    return new QueryDAO().getNoOfRecordsMonthly(fromDate, toDate);
	}

	public void generateQueriesReport() {
		
		String fromDate = DateUtil.dateFromatConversionSlash(request.getParameter("transactiondatefrom"));
		String toDate = DateUtil.dateFromatConversionSlash(request.getParameter("transactiondateto"));
		String dep = request.getParameter("department");
		String[] depParts = dep.split(":");
		String status = request.getParameter("status");
		String studentId = request.getParameter("studentId");
		String admnno = request.getParameter("admnno");
		String studentName = request.getParameter("studentName");
		String queryMain = "from ParentQuery pq where pq.createddate between '"+fromDate+"' and '"+toDate+"' ";
		String subQuery = "";
		List<ParentQuery> parentQueryList = new ArrayList<ParentQuery>();
				
		if(!dep.isEmpty()) {
			subQuery = "and pq.department.id = "+Integer.parseInt(depParts[0])+"";
			httpSession.setAttribute("departmentselected", "Department:&nbsp;"+depParts[1]);
		}else {
			httpSession.setAttribute("departmentselected", "");
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
		
		parentQueryList = new QueryDAO().generateQueriesReport(queryMain+subQuery);
		
		httpSession.setAttribute("parentquerylist", parentQueryList);
		httpSession.setAttribute("transactionfromdateselected", "From:"+request.getParameter("transactiondatefrom"));
		httpSession.setAttribute("transactiontodateselected", "To:"+request.getParameter("transactiondateto"));
		
	}

	public void getMonthlyQueries() {
		
		List<String> monthList = new LinkedList<String>();
		List<String> totalQueries = new LinkedList<String>();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date newdate = new Date();
		String todaysDate = df.format(newdate);
		List<ParentQuery> parentQueryList = new ArrayList<ParentQuery>();
		Date dateBefore = null;
		Date dateAfter = null;
		String queryMain = "from ParentQuery pq where pq.status!='Cancelled' and ";
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
			parentQueryList = new QueryDAO().generateQueriesReport(queryMain+querySub);
			
			totalQueries.add("\"" + parentQueryList.size() + "\"");
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
		
		result = new QueryDAO().feedback(Integer.parseInt(id), pid, feedbackpoints);
		
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
		
		
		List<ParentQuery> queriesList = (List<ParentQuery>) httpSession.getAttribute("parentquerylist");
	
			boolean writeSucees = false;
			
			try {

				// Creating an excel file
				XSSFWorkbook workbook = new XSSFWorkbook();
				XSSFSheet sheet = workbook.createSheet("ListOfQueries");
				Map<String, Object[]> data = new HashMap<String, Object[]>();
				Map<String, Object[]> headerData = new HashMap<String, Object[]>();
				headerData.put("Header",
						new Object[] { "Query UID", "Query No.", "Created Date","Updated Date","Department", "Admission Number", "Student Name",
								"Class", "Father Name", "Status", "Feedback"});
				int i = 1;
				for (ParentQuery queryDetails : queriesList) {
					data.put(Integer.toString(i),
							new Object[] { DataUtil.emptyString(Integer.toString(queryDetails.getId())),  DataUtil.emptyString(queryDetails.getExternalid()),
									 DataUtil.emptyString(DateUtil.getStringDate(queryDetails.getCreateddate())),
									 DataUtil.emptyString(DateUtil.getStringDate(queryDetails.getUpdateddate())),
									 DataUtil.emptyString(queryDetails.getDepartment().getDepartmentname()),
									 DataUtil.emptyString(queryDetails.getParent().getStudent().getAdmissionnumber()),
									 DataUtil.emptyString(queryDetails.getParent().getStudent().getName()),
									 DataUtil.emptyString(queryDetails.getParent().getStudent().getClassstudying().replace("--", " ")),
									 DataUtil.emptyString(queryDetails.getParent().getFathersname()),
									 DataUtil.emptyString(queryDetails.getStatus()), DataUtil.emptyString(queryDetails.getFeedback())});
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
}

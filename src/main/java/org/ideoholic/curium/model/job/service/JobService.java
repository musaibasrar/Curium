package org.ideoholic.curium.model.job.service;

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
import org.ideoholic.curium.model.employee.dto.Teacher;
import org.ideoholic.curium.model.job.dao.JobDAO;
import org.ideoholic.curium.model.job.dto.JobQuery;
import org.ideoholic.curium.model.parents.dto.Parents;
import org.ideoholic.curium.model.sendsms.service.SmsService;
import org.ideoholic.curium.model.student.dao.studentDetailsDAO;
import org.ideoholic.curium.model.task.dto.Task;
import org.ideoholic.curium.util.DataUtil;
import org.ideoholic.curium.util.DateUtil;

public class JobService {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession httpSession;
	private static final int BUFFER_SIZE = 4096;

	public JobService(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.httpSession = request.getSession();
	}

	public boolean addQuery() {
		
		boolean result = false;
		
		String[] studentId = request.getParameterValues("studentIDs");
		String queryString = request.getParameter("JobQuery");
		
		String[] pidContact = studentId[0].split(":");
		
		
		String filetype = request.getParameter("filetype");
		String typeofwork = request.getParameter("typeofwork");
		String typeofworkcourt = request.getParameter("typeofworkcourt");
		String typeofworknoncourt = request.getParameter("typeofworknoncourt");
		String typeofworkcourtcases = request.getParameter("typeofworkcourtcases");
		String typeofworkcourtdocs = request.getParameter("typeofworkcourtdocs");
		String typeofworknoncourtabt = request.getParameter("typeofworknoncourtabt");
		String typeofworknoncourtcd = request.getParameter("typeofworknoncourtcd");
		String typeofworknoncourtsr = request.getParameter("typeofworknoncourtsr");
		String typeofworknoncourtdr = request.getParameter("typeofworknoncourtdr");
		String typeofworknoncourtcs = request.getParameter("typeofworknoncourtcs");
		String typeofworknoncourturd = request.getParameter("typeofworknoncourturd");
		String typeofworknoncourtrlo = request.getParameter("typeofworknoncourtrlo");
		String typeofworknoncourtmw = request.getParameter("typeofworknoncourtmw");
		String typeofworknoncourtno = request.getParameter("typeofworknoncourtno");
		String expecteddeliverydate = request.getParameter("expecteddeliverydate");
		
		String[] referredby = request.getParameterValues("referredby");
		StringBuilder sbf = new StringBuilder();
		
		if(httpSession.getAttribute("branchid")!=null){
								
					JobQuery query = new JobQuery();
					query.setAcademicyear(DataUtil.emptyString(httpSession.getAttribute("currentAcademicYear").toString()));
					query.setResponse(queryString);
					query.setBranchid(Integer.parseInt(httpSession.getAttribute("branchid").toString()));
					query.setCreateddate(new Date());
					query.setExpecteddeliverydate(DateUtil.indiandateParser(expecteddeliverydate));
					query.setCreateduserid(Integer.parseInt(httpSession.getAttribute("userloginid").toString()));
					query.setResponse(filetype);
					query.setStatus("To Do");
					
					if(referredby!=null) {
						for (String string : referredby) {
							sbf.append(string+",");
						}
						query.setReferredby(sbf.toString());
					}
					
					
					query.setTypeofwork(typeofwork);
					String subWork = typeofworkcourt.concat(typeofworknoncourt);
					query.setTypeofsubwork(subWork);
					
					String subSubWork = typeofworkcourtcases.concat(typeofworkcourtdocs).concat(typeofworknoncourtabt).concat(typeofworknoncourtcd).concat(typeofworknoncourtsr).concat(typeofworknoncourtdr).concat(typeofworknoncourtcs).concat(typeofworknoncourturd).concat(typeofworknoncourtrlo).concat(typeofworknoncourtmw).concat(typeofworknoncourtno);
					query.setTypeofsubsubwork(subSubWork);
					//Query.setStdid(1);
					Parents parent = new Parents();
					parent.setPid(Integer.parseInt(pidContact[0]));
					query.setParent(parent);
					
					/*
					 * Teacher teacher = new Teacher(); teacher.setTid(Integer.parseInt(dep[0]));
					 * query.setTeacher(teacher);
					 */
					
					
					//GET TASKS

					String[] assignto = request.getParameterValues("assignto");
					String[] task = request.getParameterValues("task");
					String[] description = request.getParameterValues("description");
					String[] expecteddd = request.getParameterValues("expecteddeliverydatetask");
					
					//String[] dep = assignto.split(":");
					List<Task> taskList = new ArrayList<Task>();
						for (int i=0; i<assignto.length; i++ ) {
							Task newTask = new Task();
							Teacher teacher = new Teacher();
							String[] staffDetails = assignto[i].split(":");
							newTask.setTasks(task[i]);
							newTask.setDescription(description[i]);
							//newTask.setStaffid(Integer.parseInt(staffDetails[0]));
							newTask.setExpecteddeliverydate(DateUtil.dateParserUpdateStd(expecteddd[i]));
							newTask.setBranchid(Integer.parseInt(httpSession.getAttribute("branchid").toString()));
							teacher.setTid(Integer.parseInt(staffDetails[0]));
							newTask.setTeacher(teacher);
							newTask.setStatus("To Do");
							taskList.add(newTask);
						}
					//
					
			 		Date date = new Date();
			 		Calendar calendar = Calendar.getInstance();
			 		calendar.setTime(date);
			 		int year = Calendar.getInstance().get(Calendar.YEAR) % 100;
			 		//int year = calendar.get(Calendar.YEAR);
			 		int month = calendar.get(Calendar.MONTH);
			 		
			 		String externalId = Integer.toString(year).concat("_").concat(Integer.toString(month+1)).concat("_").concat(subSubWork).concat("_").concat(filetype);
			 		query.setExternalid(externalId);
			 		
					String resultQuery = new JobDAO().addQuery(query);
					
					
					if(resultQuery!=null) {
						String[] queryValues = resultQuery.split(":");
						String resultTask = new JobDAO().addTask(taskList,Integer.parseInt(queryValues[1]));
					}
					
					String sendQuerySMS = new DataUtil().getPropertiesValue("sendjobsms");
									
					if(resultQuery!=null && "yes".equalsIgnoreCase(sendQuerySMS)) {
						result = true;
						 String feedbacklink = new DataUtil().getPropertiesValue("feedbacklink");
						 String[] queryValues = resultQuery.split(":");
						 String param = "?id="+queryValues[1]+"&no="+pidContact[0]+"";
						 feedbacklink = feedbacklink+param;
						 String messageClient = "File No. is "+queryValues[0]+"";
						 String messageInternal = "You are alloted with File No. "+queryValues[0]+", client name: "+pidContact[2]+", contact no. "+pidContact[1]+" ";
						 
						 String message= "Click "+feedbacklink+" to give feedback on enq. # "+queryValues[0]+"";
						 new SmsService(request, response).sendSMS("91"+pidContact[1], messageClient);
						//check new SmsService(request, response).sendSMS("91"+dep[1], messageInternal);
					}else if(resultQuery!=null && "no".equalsIgnoreCase(sendQuerySMS)) {
						result = true;
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

				List<JobQuery> list = new JobDAO().readListOfObjectsPagination((page - 1) * recordsPerPage,
						recordsPerPage, Integer.parseInt(httpSession.getAttribute("branchid").toString()));
				request.setAttribute("studentList", list);
				int noOfRecords = new JobDAO().getNoOfRecords(Integer.parseInt(httpSession.getAttribute("branchid").toString()));
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
	
	public void inProgressQueries() {
		
		String[] queryIds = request.getParameterValues("queryids");
		int userId = Integer.parseInt(httpSession.getAttribute("userloginid").toString());
		List<Integer> QueryIdsList = new ArrayList<Integer>();
		boolean result = false;
		
		if(queryIds!=null) {
			for (String ids : queryIds) {
				QueryIdsList.add(Integer.parseInt(ids));
			}
			
			result = new JobDAO().inProgressQueries(QueryIdsList, userId);
			request.setAttribute("querystatus",result);
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
		//String dep = request.getParameter("employee");
		//String[] depParts = dep.split(":");
		String status = request.getParameter("status");
		String studentId = request.getParameter("studentId");
		String admnno = request.getParameter("clientname");
		String studentName = request.getParameter("studentName");
		String queryMain = "from JobQuery pq where pq.createddate between '"+fromDate+"' and '"+toDate+"' ";
		String subQuery = "";
		List<JobQuery> JobQueryList = new ArrayList<JobQuery>();
				
		/*
		 * if(!dep.isEmpty()) { subQuery =
		 * "and pq.teacher.tid = "+Integer.parseInt(depParts[0])+"";
		 * httpSession.setAttribute("teacherselected", "teacher:&nbsp;"+depParts[1]);
		 * }else { httpSession.setAttribute("teacherselected", ""); }
		 */
		
		if(!status.isEmpty()) {
			subQuery = subQuery + " and pq.status = '"+status+"'";
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
					
					StringBuffer staffNames = new StringBuffer();
					for (Task row : queryDetails.getTasks()) {
							staffNames.append(row.getTeacher().getTeachername()+",");
						
					}
					
					if(queryDetails.getTasks().isEmpty()) {
						staffNames.append(queryDetails.getTeacher().getTeachername());
					}
					
					data.put(Integer.toString(i),
							new Object[] { DataUtil.emptyString(Integer.toString(queryDetails.getId())),  DataUtil.emptyString(queryDetails.getExternalid()),
									 DataUtil.emptyString(DateUtil.getStringDate(queryDetails.getCreateddate())),
									 DataUtil.emptyString(DateUtil.getStringDate(queryDetails.getUpdateddate())),
									 DataUtil.emptyString(staffNames.toString()),
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

	public void generateQueriesReportForClient() {
		
		String studentId = request.getParameter("id");
		
		String queryMain = "from JobQuery pq where pq.parent.Student.sid = '"+studentId+"'";
		List<JobQuery> JobQueryList = new ArrayList<JobQuery>();
				
		JobQueryList = new JobDAO().generateQueriesReport(queryMain);
		
		httpSession.setAttribute("queryList", JobQueryList);
	}

	public void toDoQueries() {
		
		String[] queryIds = request.getParameterValues("queryids");
		int userId = Integer.parseInt(httpSession.getAttribute("userloginid").toString());
		List<Integer> QueryIdsList = new ArrayList<Integer>();
		boolean result = false;
		
		if(queryIds!=null) {
			for (String ids : queryIds) {
				QueryIdsList.add(Integer.parseInt(ids));
			}
			
			result = new JobDAO().toDoQueries(QueryIdsList, userId);
			request.setAttribute("querystatus",result);
		}
		
	}

	public void updateQueryRemarks() {
		
		String queryId = request.getParameter("queryid");
		String remarks = request.getParameter("queryremarks");
		int userId = Integer.parseInt(httpSession.getAttribute("userloginid").toString());
		boolean result = false;
		remarks = remarks.replace("'", "''");
		result = new JobDAO().updateQueryRemarks(queryId, remarks, userId);
		request.setAttribute("querystatus",result);
	}

	public boolean viewTaskDetails() {
		
		boolean result = false;
		if(httpSession.getAttribute("branchid")!=null){
			
			int jobId = Integer.parseInt(request.getParameter("jobid"));
			List<Task> taskDetails = new JobDAO().viewTaksDetails(jobId);
			request.setAttribute("taskdetails",taskDetails);
			result = true;
		}
		
		return result;
	}

	public boolean viewAllTasks() {

		boolean result = false;
		//String pages = "1";
		if(httpSession.getAttribute("branchid")!=null){
			try {
				int page = 1;
				int recordsPerPage = 500;
					if (!"".equalsIgnoreCase(DataUtil.emptyString(request.getParameter("page")))) {
						page = Integer.parseInt(request.getParameter("page"));
					}

				List<Task> list = new JobDAO().readListOfObjectsPaginationTask((page - 1) * recordsPerPage,
						recordsPerPage, Integer.parseInt(httpSession.getAttribute("branchid").toString()));
				request.setAttribute("studentList", list);
				int noOfRecords = new JobDAO().getNoOfRecordsTask(Integer.parseInt(httpSession.getAttribute("branchid").toString()));
				int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
				request.setAttribute("taskdetails", list);
				request.setAttribute("noOfPages", noOfPages);
				request.setAttribute("currentPage", page);
				result = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}

	public boolean viewAllTasksDepartmentWise() {

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

				List<Task> list = new JobDAO().readListOfObjectsPaginationDepartmentWiseTask((page - 1) * recordsPerPage,
						recordsPerPage, Integer.parseInt(httpSession.getAttribute("branchid").toString()), Integer.parseInt(loginDetails[1]));
				request.setAttribute("studentList", list);
				int noOfRecords = new JobDAO().getNoOfRecordsDepartmentWiseTask(Integer.parseInt(httpSession.getAttribute("branchid").toString()), Integer.parseInt(loginDetails[1]));
				int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
				request.setAttribute("taskdetails", list);
				request.setAttribute("noOfPages", noOfPages);
				request.setAttribute("currentPage", page);
				result = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}

	public void completeTasks() {
		
		String[] TaskIds = request.getParameterValues("taskids");
		int userId = Integer.parseInt(httpSession.getAttribute("userloginid").toString());
		List<Integer> TaskIdsList = new ArrayList<Integer>();
		List<Task> result = new ArrayList<Task>();
		int toDo = -1;
		int inProgress = -1;
		int completed = 0;
		String jobStatus = null;
		
		if(TaskIds!=null) {
			for (String ids : TaskIds) {
				TaskIdsList.add(Integer.parseInt(ids));
			}
			completed = TaskIdsList.size();
			
			List<Task> listTask = new JobDAO().viewTaksDetails(Integer.parseInt(request.getParameter("jobid")));
			int length = listTask.size();
			
			for (Task task : listTask) {
				
				if(task.getStatus().equalsIgnoreCase("To Do")) {
					toDo = toDo+1;
				}else if (task.getStatus().equalsIgnoreCase("In Progress")) {
					inProgress = inProgress+1;
				}else if (task.getStatus().equalsIgnoreCase("Completed")) {
					completed = completed+1;
				}else if (task.getStatus().equalsIgnoreCase("Cancelled")) {
					length = length-1;
				}
			}
			
			if(completed>=length) {
				jobStatus ="Completed";
			}else {
				jobStatus ="In Progress";
			}
			
			result = new JobDAO().completeTasks(TaskIdsList, userId, jobStatus, Integer.parseInt(request.getParameter("jobid")));
			String sendCompletedQuerySMS = new DataUtil().getPropertiesValue("sendcompletedquerysms");
			
			
			if(!result.isEmpty() && "yes".equalsIgnoreCase(sendCompletedQuerySMS) && jobStatus.equalsIgnoreCase("Completed")) {
				request.setAttribute("querycompleted","success");
				request.setAttribute("querystatus",true);
				  for (Task JobQuery : result) { 
					  String message =  "Your job with job. no "+JobQuery.getJobquery().getExternalid()+" has been resolved."; 
					  new SmsService(request,response).sendSMS("91"+JobQuery.getJobquery().getParent().getContactnumber(), message); 
					  }
				 
			}else if(!result.isEmpty()){
				request.setAttribute("querycompleted","success");
				request.setAttribute("querystatus",true);
			}else {
				request.setAttribute("querystatus",false);
			}
		}
	}

	public void cancelTasks() {
		
		String[] taskIds = request.getParameterValues("taskids");
		int userId = Integer.parseInt(httpSession.getAttribute("userloginid").toString());
		List<Integer> taskIdsList = new ArrayList<Integer>();
		List<Task> result = new ArrayList<Task>();
		int toDo = 0;
		int inProgress = 0;
		int completed = 0;
		int cancel=0;
		
		String jobStatus = null;
		
		if(taskIds!=null) {
			for (String ids : taskIds) {
				taskIdsList.add(Integer.parseInt(ids));
			}
			cancel=taskIdsList.size();
			
			List<Task> listTask = new JobDAO().viewTaksDetails(Integer.parseInt(request.getParameter("jobid")));
			int length = listTask.size();
			
			for (Task task : listTask) {
				
				Integer i = task.getId();
				
				if(!taskIdsList.contains(i)) {
					
					if(task.getStatus().equalsIgnoreCase("To Do")) {
						toDo = toDo+1;
					}else if (task.getStatus().equalsIgnoreCase("In Progress")) {
						inProgress = inProgress+1;
					}else if (task.getStatus().equalsIgnoreCase("Completed")) {
						completed = completed+1;
					}else if (task.getStatus().equalsIgnoreCase("Cancelled")) {
						cancel = cancel+1;
					}	
				}
			}
			
			if(completed==0 && inProgress==0 && toDo==0){
				jobStatus ="Cancelled";
			}else if(toDo==0 && inProgress==0 ) {
				jobStatus ="Completed";
			}else if(completed==0 && inProgress==0) {
				jobStatus ="To Do";
			}else {
				jobStatus="In Progress";
			}
			
			
			result = new JobDAO().cancelTasks(taskIdsList, userId, jobStatus, Integer.parseInt(request.getParameter("jobid")));
			
			if(!result.isEmpty()) {
				request.setAttribute("querystatus",true);
			}else {
				request.setAttribute("querystatus",false);
			}
			
		}
		
	}

	public void toDoTasks() {
		
		String[] taskIds = request.getParameterValues("taskids");
		int userId = Integer.parseInt(httpSession.getAttribute("userloginid").toString());
		List<Integer> taskIdsList = new ArrayList<Integer>();
		List<Task> result = new ArrayList<Task>();
		int toDo = 0;
		int inProgress = 0;
		
		String jobStatus = null;
		
		if(taskIds!=null) {
			for (String ids : taskIds) {
				taskIdsList.add(Integer.parseInt(ids));
			}
			toDo = taskIdsList.size();
			
			List<Task> listTask = new JobDAO().viewTaksDetails(Integer.parseInt(request.getParameter("jobid")));
			int length = listTask.size();
			
			for (Task task : listTask) {
				
					Integer i = task.getId();
				
				if(!taskIdsList.contains(i)) {
					
				if(task.getStatus().equalsIgnoreCase("To Do")) {
					toDo = toDo+1;
				}else if (task.getStatus().equalsIgnoreCase("In Progress")) {
					inProgress = inProgress+1;
				}else if (task.getStatus().equalsIgnoreCase("Completed")) {
					inProgress = inProgress+1;
				}else if (task.getStatus().equalsIgnoreCase("Cancelled")) {
					length = length-1;
				}
				
				}
			}
			
			if(toDo>=length && inProgress==0) {
				jobStatus ="To Do";
			}else {
				jobStatus ="In Progress";
			}
			
			result = new JobDAO().toDoTasks(taskIdsList, userId, jobStatus, Integer.parseInt(request.getParameter("jobid")));
			
			if(!result.isEmpty()) {
				request.setAttribute("querystatus",true);
			}else {
				request.setAttribute("querystatus",false);
			}
		}
		
	}

	public void inProgressTasks() {
		
		String[] taskIds = request.getParameterValues("taskids");
		int userId = Integer.parseInt(httpSession.getAttribute("userloginid").toString());
		List<Integer> taskIdsList = new ArrayList<Integer>();
		List<Task> result = new ArrayList<Task>();
		
		if(taskIds!=null) {
			for (String ids : taskIds) {
				taskIdsList.add(Integer.parseInt(ids));
			}
			
			result = new JobDAO().inProgressTasks(taskIdsList, userId, "In Progress", Integer.parseInt(request.getParameter("jobid")));
			
			if(!result.isEmpty()) {
				request.setAttribute("querystatus",true);
			}else {
				request.setAttribute("querystatus",false);
			}
		}
		
	}

	public void createTask() {
		
		String jobId = request.getParameter("jobid");
		String jobno = request.getParameter("jobno");
		request.setAttribute("jobid",jobId);
		request.setAttribute("jobno",jobno);
	}

	public boolean addTask() {
		
		boolean result = false;
		
		String jobid = request.getParameter("jobid");
		
		if(httpSession.getAttribute("branchid")!=null){
					
					
					//GET TASKS

					String[] assignto = request.getParameterValues("assignto");
					String[] task = request.getParameterValues("task");
					String[] description = request.getParameterValues("description");
					String[] expecteddd = request.getParameterValues("expecteddeliverydatetask");
					
					//String[] dep = assignto.split(":");
					List<Task> taskList = new ArrayList<Task>();
						for (int i=0; i<assignto.length; i++ ) {
							Task newTask = new Task();
							Teacher teacher = new Teacher();
							String[] staffDetails = assignto[i].split(":");
							newTask.setTasks(task[i]);
							newTask.setDescription(description[i]);
							//newTask.setStaffid(Integer.parseInt(staffDetails[0]));
							newTask.setExpecteddeliverydate(DateUtil.dateParserUpdateStd(expecteddd[i]));
							newTask.setBranchid(Integer.parseInt(httpSession.getAttribute("branchid").toString()));
							teacher.setTid(Integer.parseInt(staffDetails[0]));
							newTask.setTeacher(teacher);
							newTask.setStatus("To Do");
							taskList.add(newTask);
						}
					//
					
			 		Date date = new Date();
			 		Calendar calendar = Calendar.getInstance();
			 		calendar.setTime(date);
			 		int year = Calendar.getInstance().get(Calendar.YEAR) % 100;
			 		//int year = calendar.get(Calendar.YEAR);
			 		int month = calendar.get(Calendar.MONTH);
			 						
						String resultTask = new JobDAO().addTask(taskList,Integer.parseInt(jobid));
						
						if(resultTask.equalsIgnoreCase("true")) {
							result = true;
						}
				}
		
		return result;
	}

	public void generateTasksReport() {
		
		String fromDate = DateUtil.dateFromatConversionSlash(request.getParameter("transactiondatefrom"));
		String toDate = DateUtil.dateFromatConversionSlash(request.getParameter("transactiondateto"));
		String dep = request.getParameter("employee");
		String[] depParts = dep.split(":");
		String status = request.getParameter("status");
		String studentId = request.getParameter("studentId");
		String admnno = request.getParameter("clientname");
		String studentName = request.getParameter("studentName");
		String queryMain = "from Task pq where pq.jobquery.createddate between '"+fromDate+"' and '"+toDate+"' ";
		String subQuery = "";
		List<Task> JobQueryList = new ArrayList<Task>();
				
		if(!dep.isEmpty()) {
			subQuery = "and pq.teacher.tid = "+Integer.parseInt(depParts[0])+"";
			httpSession.setAttribute("teacherselected", "teacher:&nbsp;"+depParts[1]);
		}else {
			httpSession.setAttribute("teacherselected", "");
		}
		
		if(!status.isEmpty()) {
			subQuery = subQuery + " and pq.status = '"+status+"'";
			httpSession.setAttribute("statusselected", "Status:&nbsp;"+status);
		}else {
		httpSession.setAttribute("statusselected", "");
		}
		
		if(!studentId.isEmpty()) {
			subQuery = subQuery + "and pq.jobquery.parent.Student.sid = '"+studentId+"'";
			httpSession.setAttribute("studentselected", "Client Name:&nbsp;"+studentName);
		}else {
			httpSession.setAttribute("studentselected", "");
		}
		
		JobQueryList = new JobDAO().generateTasksReport(queryMain+subQuery);
		
		httpSession.setAttribute("parenttaskslist", JobQueryList);
		httpSession.setAttribute("transactionfromdateselected", "From:"+request.getParameter("transactiondatefrom"));
		httpSession.setAttribute("transactiontodateselected", "To:"+request.getParameter("transactiondateto"));
		
	}

	public void getReferredbyDetails() throws IOException{
		
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
		        	response.getWriter().println("<table> <tr><td>Data Not Available</td></tr></table>");
		        } finally {
		            out.flush();
		            out.close();
		        }
		}
		
		
	}

	public boolean viewOneJobDetails() {
		
		boolean result = false;
		if(httpSession.getAttribute("branchid")!=null){
			
			int jobId = Integer.parseInt(request.getParameter("jobid"));
			List<JobQuery> JobQueryList = new JobDAO().viewOneJobDetails(jobId);
			request.setAttribute("queryList",JobQueryList);
			result = true;
		}
		
		return result;
	}
}

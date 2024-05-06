package org.ideoholic.curium.model.feescategory.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.academicyear.dao.YearDAO;
import org.ideoholic.curium.model.academicyear.dto.Currentacademicyear;
import org.ideoholic.curium.model.account.dao.AccountDAO;
import org.ideoholic.curium.model.account.dto.VoucherEntrytransactions;
import org.ideoholic.curium.model.feescategory.dao.feesCategoryDAO;
import org.ideoholic.curium.model.feescategory.dto.Concession;
import org.ideoholic.curium.model.feescategory.dto.Feescategory;
import org.ideoholic.curium.model.feescategory.dto.OtherFeecategory;
import org.ideoholic.curium.model.feescollection.dao.feesCollectionDAO;
import org.ideoholic.curium.model.feesdetails.dao.feesDetailsDAO;
import org.ideoholic.curium.model.parents.dto.Parents;
import org.ideoholic.curium.model.student.dao.studentDetailsDAO;
import org.ideoholic.curium.model.student.dto.Student;
import org.ideoholic.curium.model.student.dto.Studentfeesstructure;
import org.ideoholic.curium.util.DataUtil;
import org.ideoholic.curium.util.DateUtil;

public class FeesService {
        
                private HttpServletRequest request;
            private HttpServletResponse response;
            private HttpSession httpSession;
            private String BRANCHID = "branchid";
            private String USERID = "userloginid";
            /**
             * Size of a byte buffer to read/write file
             */
            private static final int BUFFER_SIZE = 4096;
        
        public FeesService(HttpServletRequest request, HttpServletResponse response) {
                this.request = request;
       this.response = response;
       this.httpSession = request.getSession();
        }


        public boolean viewFees() {
                
                 boolean result = false;
                 
                 if(httpSession.getAttribute(BRANCHID)!=null){
                	 String[] currentYear = httpSession.getAttribute("currentAcademicYear").toString().split("/");
              	   int cYear = Integer.parseInt(currentYear[0])+1;
              	   int cYear2 = Integer.parseInt(currentYear[1])+1;
              	   String nextYear = ""+cYear+"/"+cYear2+"";
                         try {
                                List<Feescategory> list = new feesCategoryDAO().readListOfFeeCategory(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()),httpSession.getAttribute("currentAcademicYear").toString(),nextYear);
                            httpSession.setAttribute("feescategory", list);
                            result = true;
                        } catch (Exception e) {
                            e.printStackTrace();
                            result = false;
                        }
                 }
                return result;
        }


        public void addFeesParticular() {
                
                if(httpSession.getAttribute(BRANCHID)!=null){
                	
                	String[] classesFeesCat = request.getParameterValues("fromclass");
                	List<Feescategory> feesCategoryList = new ArrayList<Feescategory>();
                	
                	for (String feeCat : classesFeesCat) {
                		Feescategory feescategorynew = new Feescategory();
                		feescategorynew.setFeescategoryname(DataUtil.emptyString(request.getParameter("feescategory")));
                		feescategorynew.setParticularname(DataUtil.emptyString(feeCat)+"--");
                		feescategorynew.setAmount(DataUtil.parseInt(request.getParameter("amount")));
                		feescategorynew.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
                		feescategorynew.setUserid(Integer.parseInt(httpSession.getAttribute("userloginid").toString()));
                		feescategorynew.setAcademicyear(DataUtil.emptyString(request.getParameter("categoryyear")));
                        if(!feescategorynew.getFeescategoryname().equalsIgnoreCase("") && !feescategorynew.getParticularname().equalsIgnoreCase("") && feescategorynew.getAmount() != 0 ){
                        	feesCategoryList.add(feescategorynew);
                        }
                		}
                	boolean result =  new feesCategoryDAO().create(feesCategoryList);
                	
                        /*
                          Feescategory feescategory = new Feescategory();
                          feescategory.setFeescategoryname(DataUtil.emptyString(request.getParameter("feescategory")));
                        if(!DataUtil.emptyString(request.getParameter("fromclass")).equalsIgnoreCase("ALL") && !DataUtil.emptyString(request.getParameter("toclass")).equalsIgnoreCase("ALL")){
                                feescategory.setParticularname(DataUtil.emptyString(request.getParameter("fromclass"))+"--");
                        }else{
                                feescategory.setParticularname(DataUtil.emptyString(request.getParameter("fromclass"))+"--");
                        }
                        
                        feescategory.setAmount(DataUtil.parseInt(request.getParameter("amount")));
                        feescategory.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
                        feescategory.setUserid(Integer.parseInt(httpSession.getAttribute("userloginid").toString()));
                        feescategory.setAcademicyear(DataUtil.emptyString(request.getParameter("categoryyear")));
                        if(!feescategory.getFeescategoryname().equalsIgnoreCase("") && !feescategory.getParticularname().equalsIgnoreCase("") && feescategory.getAmount() != 0 ){
                                feescategory =  new feesCategoryDAO().create(feescategory);
                        }*/
                }
        }


        public void deleteMultiple() {
                 String[] idfeescategory = request.getParameterValues("idfeescategory");
                 if(idfeescategory!=null){
                List<Integer> ids = new ArrayList();
                for (String id : idfeescategory) {
                    System.out.println("id" + id);
                    ids.add(Integer.valueOf(id));
                }
                new feesCategoryDAO().deleteMultiple(ids);
                 }
        }


        public boolean viewAllStudentsList() {

                boolean result = false;
                try {
                        List<Object[]> list = new feesDetailsDAO().readListOfStudents(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
                        
                        List<Parents> parentDetails = new ArrayList<Parents>();
                        for(Object[] parentdetails: list){
                        	Parents parent = new Parents();
                        	Student student = new Student();
                            student.setSid((Integer)parentdetails[0]);
                            student.setName((String)parentdetails[1]);
                            student.setClassstudying((String)parentdetails[2]);
                            student.setStudentexternalid((String)parentdetails[3]);
                            student.setAdmissionnumber((String)parentdetails[4]);
                            parent.setFathersname((String)parentdetails[5]);
                            parent.setStudent(student);
                            parentDetails.add(parent);
                        }
                        
                        request.setAttribute("studentListFeesCollection", parentDetails);
                        result = true;
                } catch (Exception e) {
                        result = false;
                }
                return result;
        }


        public boolean downlaodFile() {
                boolean result = false;
                try {

                        File downloadFile = new File(System.getProperty("java.io.tmpdir")+"/feesdetails.xlsx");
                FileInputStream inStream = new FileInputStream(downloadFile);

                // get MIME type of the file
                        String mimeType = "application/vnd.ms-excel";

                        // set content attributes for the response
                        response.setContentType(mimeType);
                        // response.setContentLength((int) bis.length());

                        // set headers for the response
                        String headerKey = "Content-Disposition";
                        String headerValue = String.format("attachment; filename=\"%s\"",
                                        "feesdetails.xlsx");
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


        public String deleteFeesCategory() {
                
                 String[] idfeescategory = request.getParameterValues("sfsid");
                 List<Integer> sfsId = new ArrayList();
                 List<Integer> feesCatId = new ArrayList();
                 List<VoucherEntrytransactions> transactionsList = new ArrayList<VoucherEntrytransactions>();
                 List<String> debitEntries = new ArrayList<String>();
                 List<String> creditEntries = new ArrayList<String>();
                 
                 String studentId = request.getParameter("id");
                 
                 if(idfeescategory!=null){
                         
                         for (String string : idfeescategory) {
                                 String[] test = string.split("_");
                                 sfsId.add(Integer.valueOf(test[0]));
                                 feesCatId.add(Integer.valueOf(test[1]));
                                 
                               //Accounts
                          		//Pass J.V. : credit the Fees as income & debit the cash
                                  List<Studentfeesstructure> sfs = new studentDetailsDAO().getStudentFeesStructureDetails(Integer.valueOf(test[0]));
                                  
                          		int drFees = getLedgerAccountId("unearnedstudentfeesincome"+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
                          		int crAccount = getLedgerAccountId("studentfeesreceivable"+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));;

                          		VoucherEntrytransactions transactions = new VoucherEntrytransactions();

                          		transactions.setDraccountid(drFees);
                          		transactions.setCraccountid(crAccount);
                          		transactions.setDramount(new BigDecimal(sfs.get(0).getFeesamount()));
                          		transactions.setCramount(new BigDecimal(sfs.get(0).getFeesamount()));
                          		transactions.setVouchertype(4);
                          		transactions.setTransactiondate(DateUtil.todaysDate());
                          		transactions.setEntrydate(DateUtil.todaysDate());
                          		transactions.setNarration("Towards Reversal of Fees Stamp");
                          		transactions.setCancelvoucher("no");
                          		transactions.setFinancialyear(new AccountDAO().getCurrentFinancialYear(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString())).getFinancialid());
                          		transactions.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
                          		transactions.setUserid(Integer.parseInt(httpSession.getAttribute(USERID).toString()));

                          		String updateCrAccount="update Accountdetailsbalance set currentbalance=currentbalance-"+sfs.get(0).getFeesamount()+" where accountdetailsid="+crAccount;

                          		String updateDrAccount="update Accountdetailsbalance set currentbalance=currentbalance-"+sfs.get(0).getFeesamount()+" where accountdetailsid="+drFees;
                          		transactionsList.add(transactions);
                          		debitEntries.add(updateDrAccount);
                          		creditEntries.add(updateCrAccount);
                          		// End J.V
                          		
                        }
                new feesCategoryDAO().deleteFeesCategory(sfsId,feesCatId,studentId,transactionsList,debitEntries,creditEntries);
                
                return studentId;
                 }
                throw new IllegalArgumentException("Fees category for the given student does not exist");
                
        }


    public void viewAllBranchStudents() {
        
        try {
                List<Student> list = new feesDetailsDAO().readListOfAllBranchStudents();
                request.setAttribute("studentListFeesCollection", list);
        } catch (Exception e) {
        }
        
    }


	/*
	 * public String waiveOffFeesOld() {
	 * 
	 * String[] idfeescategory = request.getParameterValues("sfsid"); List<Integer>
	 * sfsId = new ArrayList(); List<Integer> feesCatId = new ArrayList();
	 * 
	 * String studentId = request.getParameter("id");
	 * 
	 * if(idfeescategory!=null){
	 * 
	 * for (String string : idfeescategory) { String[] test = string.split("_");
	 * sfsId.add(Integer.valueOf(test[0])); feesCatId.add(Integer.valueOf(test[1]));
	 * } new feesCategoryDAO().waiveOffFees(sfsId,feesCatId,studentId);
	 * 
	 * return
	 * "/noman/StudentProcess/ViewFeesStructure&id="+studentId; }
	 * 
	 * return "error.jsp";
	 * 
	 * }
	 */
    
    public String waiveOffFees() {
        
        String[] idfeescategory = request.getParameterValues("sfsid");
        List<Integer> sfsId = new ArrayList<Integer>();
        List<Integer> feesCatId = new ArrayList<Integer>();
        List<String> consession = new ArrayList<String>();
        List<Concession> concessionList = new ArrayList<Concession>();
        
        String studentId = request.getParameter("id");
        
        if(idfeescategory!=null){
                
                for (String string : idfeescategory) {
                	
                		Concession con = new Concession();
                		String[] test = string.split("_");
                        sfsId.add(Integer.valueOf(test[0]));
                		String dueAmount = request.getParameter("dueamount:"+Integer.valueOf(test[0]));
                        //String concessionAmount = request.getParameter("waiveoff:"+Integer.valueOf(test[0]));
                        
                        	feesCatId.add(Integer.valueOf(test[1]));
                            con.setSfsid(Integer.valueOf(test[0]));
                            con.setFeescatid(Integer.valueOf(test[1]));
                            con.setConcessionOld(request.getParameter("waiveoff:"+Integer.valueOf(test[0])));
                            con.setConcession(dueAmount);
                            concessionList.add(con);
                        
               }
           new feesCategoryDAO().waiveOffFees(concessionList,studentId);
           return studentId;
        }
        
        throw new IllegalArgumentException("Fees category for the given student does not exist");
       
	}


	public void searchFeesWaiveofforConcessionReport(String searchCriteria) {
		
		List<Parents> searchStudentList = new ArrayList<Parents>();
		Map<Parents,List<Studentfeesstructure>> parentsStudentFeesStructure = new HashMap<Parents,List<Studentfeesstructure>>();
		
		if(httpSession.getAttribute("branchid")!=null){
			String queryMain = "From Parents as parents where";
			String studentname = DataUtil.emptyString(request
					.getParameter("namesearch"));

			String addClass = request.getParameter("classsearch");
			String addSec = request.getParameter("secsearch");
			String conClassStudying = "";

			if (!addClass.equalsIgnoreCase("")) {

				conClassStudying = addClass+"--" +"%";

			}
			if (!addSec.equalsIgnoreCase("")) {
				conClassStudying = addClass;
				conClassStudying = conClassStudying+"--"+addSec+"%";
			}

			String classStudying = DataUtil.emptyString(conClassStudying);
			String querySub = "";

			if (!studentname.equalsIgnoreCase("")) {
				querySub = " parents.Student.name like '%" + studentname + "%' AND parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0 AND parents.branchid="+Integer.parseInt(httpSession.getAttribute("branchid").toString());
			}

			if (!classStudying.equalsIgnoreCase("")
					&& !querySub.equalsIgnoreCase("")) {
				querySub = querySub + " AND parents.Student.classstudying like '"
						+ classStudying + "' AND parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0 and parents.branchid="+Integer.parseInt(httpSession.getAttribute("branchid").toString());
			} else if (!classStudying.equalsIgnoreCase("")) {
				querySub = querySub + " parents.Student.classstudying like '"
						+ classStudying + "' AND parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0 and parents.branchid="+Integer.parseInt(httpSession.getAttribute("branchid").toString());
			}

			queryMain = queryMain + querySub;
			/*
			 * queryMain =
			 * "FROM Parents as parents where  parents.Student.dateofbirth = '2006-04-06'"
			 * ;
			 */
			System.out.println("SEARCH QUERY ***** " + queryMain);
			searchStudentList = new studentDetailsDAO().getStudentsList(queryMain);
			List<Integer> studentids = new ArrayList<>(); 
			
			for (Parents parents : searchStudentList) {
				studentids.add(parents.getStudent().getSid());
			}
			Currentacademicyear currentYear = new YearDAO().showYear();
			httpSession.setAttribute("currentyearfromservice",currentYear.getCurrentacademicyear());
			
			List<Studentfeesstructure> listStudentsFeesStructure = new feesCollectionDAO().getStudentsFeesStructure(studentids, currentYear.getCurrentacademicyear(), searchCriteria);
			
			
			for (Parents parents : searchStudentList) {
				
				List<Studentfeesstructure> singleStudent = new ArrayList<Studentfeesstructure>();
				
				for (Studentfeesstructure fees : listStudentsFeesStructure) {
					
					int feeSid = fees.getSid();
					int sid = parents.getStudent().getSid();
						
					if(feeSid == sid) {
								singleStudent.add(fees);
						}
				}
				parentsStudentFeesStructure.put(parents, singleStudent);
				
			}
			
		}
		if("waiveoff".equalsIgnoreCase(searchCriteria)) {
			httpSession.setAttribute("studentsfeesstructuredetailswaiveoff", parentsStudentFeesStructure);
			httpSession.setAttribute("studentsfeesstructuredetailsconcession", null);
		}else if("concession".equalsIgnoreCase(searchCriteria)) {
			httpSession.setAttribute("studentsfeesstructuredetailswaiveoff", null);
			httpSession.setAttribute("studentsfeesstructuredetailsconcession", parentsStudentFeesStructure);
		}
		
		
	}


	public String applyConcession() {
        
        String[] idfeescategory = request.getParameterValues("sfsid");
        List<Integer> sfsId = new ArrayList<Integer>();
        List<Integer> feesCatId = new ArrayList<Integer>();
        List<String> consession = new ArrayList<String>();
        List<Concession> concessionList = new ArrayList<Concession>();
        
        String studentId = request.getParameter("id");
        
        if(idfeescategory!=null){
                
                for (String string : idfeescategory) {
                	
                		Concession con = new Concession();
                		String[] test = string.split("_");
                        sfsId.add(Integer.valueOf(test[0]));
                		String dueAmount = request.getParameter("dueamount:"+Integer.valueOf(test[0]));
                        String concessionAmount = request.getParameter("concession:"+Integer.valueOf(test[0]));
                        
                        if(Integer.parseInt(concessionAmount)<=Integer.parseInt(dueAmount)) {
                        	feesCatId.add(Integer.valueOf(test[1]));
                            con.setSfsid(Integer.valueOf(test[0]));
                            con.setFeescatid(Integer.valueOf(test[1]));
                            con.setConcessionOld(request.getParameter("concessionold:"+Integer.valueOf(test[0])));
                            con.setConcession(request.getParameter("concession:"+Integer.valueOf(test[0])));
                            concessionList.add(con);
                        }
                        
               }
           new feesCategoryDAO().applyConcession(concessionList,studentId);
           return studentId;
        }
        
        throw new IllegalArgumentException("Fees category for the given student does not exist");
       
	}


	public void viewFeesYearly() throws IOException {
        
        String academicYear = request.getParameter("year");
        if(httpSession.getAttribute(BRANCHID)!=null){
        	
                List<Feescategory> list = new feesCategoryDAO().readListOfObjects(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()),academicYear);
                httpSession.setAttribute("feescategory", list);
                PrintWriter out = response.getWriter(); 
       			response.setContentType("text/xml");
       		    response.setHeader("Cache-Control", "no-cache");
       		        try {
       		        	
       		        	if(!list.isEmpty()){
       		        		String buffer = "<div style='overflow:scroll;width:420px; height: 100px;'>";
       		        		/*String buffer = "<select name='subgroupname' style='width: 240px' id='sgname' onchange='dropdowndist();getSSGroup();'>";
       		        		buffer = buffer +  "<option></option>";*/
       			        	for(int i =0; i<list.size();i++){
       			        		buffer = buffer +  "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
       			        				+ "<label class='labelClass' style='font-weight: bold;color:#325F6D'> <input"
       			        				+ "									 type='checkbox' name='feescategory' class='chcktbl' value="+list.get(i).getIdfeescategory()+""
       			        				+ "									size='36'> "+list.get(i).getFeescategoryname()+" : </label> <label style='font-weight: bold;color:#eb6000'>"+list.get(i).getParticularname()+""
       			        				+ "							</label><br>";
       			        	}
       			        	buffer = buffer + "</div>";
       			        	response.getWriter().println(buffer);
       		        	}else{
       		        		String buffer = "<input type='checkbox'  name='chcktbl'>";
       		        		response.getWriter().println(buffer);
       		        	}
       		        	
       		        } catch (Exception e) {
       		            out.write("<subgroup>0</subgroup>");
       		        } finally {
       		            out.flush();
       		            out.close();
       		        }
        }
	}
	
	   public boolean viewOtherFees() {

           boolean result = false;

           if(httpSession.getAttribute(BRANCHID)!=null){
        	   String[] currentYear = httpSession.getAttribute("currentAcademicYear").toString().split("/");
        	   int cYear = Integer.parseInt(currentYear[0])+1;
        	   int cYear2 = Integer.parseInt(currentYear[1])+1;
        	   String nextYear = ""+cYear+"/"+cYear2+"";
        		try {
                          List<OtherFeecategory> list = new feesCategoryDAO().readListOfOtherFeeObjects(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()),httpSession.getAttribute("currentAcademicYear").toString(),nextYear);
                      httpSession.setAttribute("otherfeescategory", list);
                      result = true;
                  } catch (Exception e) {
                      e.printStackTrace();
                      result = false;
                  }
           }
          return result;
  }
	   
	   public void addOtherFeesParticular() {

           OtherFeecategory ofeescategory = new OtherFeecategory();

           if(httpSession.getAttribute(BRANCHID)!=null){

                   ofeescategory.setFeescategoryname(DataUtil.emptyString(request.getParameter("feescategory")));
                   if(!DataUtil.emptyString(request.getParameter("fromclass")).equalsIgnoreCase("ALL") && !DataUtil.emptyString(request.getParameter("toclass")).equalsIgnoreCase("ALL")){
                           ofeescategory.setParticularname(DataUtil.emptyString(request.getParameter("fromclass"))+"-"+DataUtil.emptyString(request.getParameter("toclass")));
                   }else{
                           ofeescategory.setParticularname(DataUtil.emptyString(request.getParameter("fromclass")));
                   }

                   ofeescategory.setAmount(DataUtil.parseInt(request.getParameter("amount")));
                   ofeescategory.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
                   ofeescategory.setUserid(Integer.parseInt(httpSession.getAttribute("userloginid").toString()));
                   ofeescategory.setAcademicyear(request.getParameter("categoryyearof"));
                   if(!ofeescategory.getFeescategoryname().equalsIgnoreCase("") && !ofeescategory.getParticularname().equalsIgnoreCase("") && ofeescategory.getAmount() != 0 ){
                           ofeescategory =  new feesCategoryDAO().createOtherFeeCategory(ofeescategory);
                   }
           }
   }
	   
	   public void odeleteMultiple() {
           String[] idfeescategory = request.getParameterValues("idfeescategory");
           if(idfeescategory!=null){
          List<Integer> ids = new ArrayList();
          for (String id : idfeescategory) {
              System.out.println("id" + id);
              ids.add(Integer.valueOf(id));
          }
          new feesCategoryDAO().odeleteMultiple(ids);
           }
  }
	   
	   public void getFeeCategory() throws IOException {

	        if(httpSession.getAttribute(BRANCHID)!=null){
	        	String classname = request.getParameter("classstudying");
	        	String[] yearofAdmission = request.getParameter("yearofadmission").split("/");
	        	String[] currentAcademicYear = httpSession.getAttribute("currentAcademicYear").toString().split("/");
	        	String searchYear = null;
	        	int yoa = Integer.parseInt(yearofAdmission[0]);
	        	int ca = Integer.parseInt(currentAcademicYear[0]);
	        	
	        	if(yoa == ca || yoa < ca) {
	        		searchYear = httpSession.getAttribute("currentAcademicYear").toString();
	        	}else if (yoa > ca) {
	        		searchYear = request.getParameter("yearofadmission");
	        	}
	        	
	            List<Feescategory> feecategoryList= new feesCategoryDAO().getfeecategoryofstudent(classname,searchYear);
	            httpSession.setAttribute("feescategory", feecategoryList);

	            Locale indiaLocale = new Locale("en", "IN");
	    		PrintWriter out = response.getWriter(); 
	    		response.setContentType("text/xml");
	            response.setHeader("Cache-Control", "no-cache");

	    		if(feecategoryList.size() > 0) {

	    		        try {
	    		        	String buffer = "<div style='overflow:scroll;width:750px; height: 250px;'><table id='dataTable'><thead><tr>"
	    		        			+ "   			        				                            <td style='padding-right: 30px;font-weight: bold;color:#eb6000'>Fees Category</td>"
	    		        			+ "   			        											<td style='padding-right: 20px;font-weight: bold;color:#eb6000'>class</td>	"
	    		        			+ "																	<td style='padding-right: 100px;font-weight: bold;color:#eb6000'>Fees Amount</td>"
	    		        			+ "   			        											<td style='padding-right: 40px;font-weight: bold;color:#eb6000'>No.of installments in a Year</td>"
	    		        			+ "																	<td style='font-weight: bold;color:#eb6000'>Fees Total Amount</td></tr>"
	    		        			+ "   			        										</thead>";
	   		        		/*String buffer = "<select name='subgroupname' style='width: 240px' id='sgname' onchange='dropdowndist();getSSGroup();'>";
	   		        		buffer = buffer +  "<option></option>";*/
	   			        	for(int i =0; i<feecategoryList.size();i++){
	   			        		buffer = buffer +  "<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
	   			        				+ "<label class='labelClass' style='font-weight: bold;color:#325F6D'> <input"
	   			        				+ "									 type='checkbox' name='feescategory' class='chcktbl' value="+feecategoryList.get(i).getIdfeescategory()+""
	   			        				+ "									size='18'> "+feecategoryList.get(i).getFeescategoryname()+" : </label></td><td> <label style='font-weight: bold;color:#eb6000'>"+feecategoryList.get(i).getParticularname()+""
	   			        				+ "							</label> &nbsp;&nbsp;<input type='hidden' value='0' name='feesConcession' id='feesConcession_"+i+"' /><input type='hidden' class='feesId' name='feesIDS' id=fees_id_"+i+" value='"+feecategoryList.get(i).getIdfeescategory()+"'></td><td><input class='feesAmount' type='text' value='"+feecategoryList.get(i).getAmount()+"'   name='fessCat'  id=hiddenfees_amount_"+i+" size='18'/></td><td> <input"
	   			        						+ "   			     type='text' value='0' name='feesCount' id='feesCount_"+i+"'"
	   			        						+ "   			        				+ \"								onclick='calculate("+i+")' onkeyup='calculate("+i+")' size='18'><br></td>"
	   			        						+ "<td> <input class='feesFullAmount' type='text' value='0' name='feesFullCat' id='hiddenfees_full_amount_"+i+"' size='18'></td></tr>";
	   			        	}
	   			        	buffer = buffer + " <tfoot><tr><td colspan='4' align='right'>Toatal</td><td align='center'><input type='text' name='feesTotalAmount' id=feesTotalAmount value='0' /></td></tr></table></div>";

	    			        	response.getWriter().println(buffer);

	    		        } catch (Exception e) {
	    		            out.write("<input name='feescategoryempty'  type='text' class='textfieldvalues' id='feescategoryempty'  style='font-size: 14px;' readonly>");
	    		        } finally {
	    		            out.flush();
	    		            out.close();
	    		        }
	    		}else {

	    		        try {
	    		        		String buffer = "<input name='balance'  type='text' class='textfieldvalues' id='balance' value='0' style='font-size: 14px;' readonly>";
	    			        	response.getWriter().println(buffer);

	    		        } catch (Exception e) {
	    		            out.write("<input name='balance'  type='text' class='textfieldvalues' id='balance'  style='font-size: 14px;' readonly>");
	    		        } finally {
	    		            out.flush();
	    		            out.close();
	    		        }
	    		}


	        }
	    }
	   
	   public String applyotherConcession() {

	        String[] idfeescategory = request.getParameterValues("sfsid");
	        List<Integer> sfsId = new ArrayList<Integer>();
	        List<Integer> feesCatId = new ArrayList<Integer>();
	        List<String> consession = new ArrayList<String>();
	        List<Concession> concessionList = new ArrayList<Concession>();

	        String studentId = request.getParameter("id");

	        if(idfeescategory!=null){

	                for (String string : idfeescategory) {

	                		Concession con = new Concession();
	                		String[] test = string.split("_");
	                        sfsId.add(Integer.valueOf(test[0]));
	                		String dueAmount = request.getParameter("dueamount:"+Integer.valueOf(test[0]));
	                        String concessionAmount = request.getParameter("concession:"+Integer.valueOf(test[0]));

	                        if(Integer.parseInt(concessionAmount)<=Integer.parseInt(dueAmount)) {
	                        	feesCatId.add(Integer.valueOf(test[1]));
	                            con.setSfsid(Integer.valueOf(test[0]));
	                            con.setFeescatid(Integer.valueOf(test[1]));
	                            con.setConcessionOld(request.getParameter("concessionold:"+Integer.valueOf(test[0])));
	                            con.setConcession(request.getParameter("concession:"+Integer.valueOf(test[0])));
	                            concessionList.add(con);
	                        }

	               }
	           new feesCategoryDAO().applyotherConcession(concessionList,studentId);
	           return studentId;
	        }

	        throw new IllegalArgumentException("Fees category for the given student does not exist");

		}
	   
		private int getLedgerAccountId(String itemAccount) {

			int result = 0;

		 	Properties properties = new Properties();
			InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("Util.properties");

	        		try {
						properties.load(inputStream);
					} catch (IOException e) {
						e.printStackTrace();
					}

	        		String ItemLedgerId = properties.getProperty(itemAccount);

			    if(ItemLedgerId!=null) {
			    	result = Integer.parseInt(ItemLedgerId);
			    }else {
			    	String ItemLedger = properties.getProperty(itemAccount.toLowerCase());
			    	result = Integer.parseInt(ItemLedger.toLowerCase());
			    }

			    return result;
		}


		public void getFeeCategoryHeadWise() throws IOException {

	        if(httpSession.getAttribute(BRANCHID)!=null){
	        	String classname = request.getParameter("classstudying");
	        	String[] yearofAdmission = request.getParameter("yearofadmission").split("/");
	        	String[] currentAcademicYear = httpSession.getAttribute("currentAcademicYear").toString().split("/");
	        	String searchYear = null;
	        	int yoa = Integer.parseInt(yearofAdmission[0]);
	        	int ca = Integer.parseInt(currentAcademicYear[0]);
	        	
	        	if(yoa == ca || yoa < ca) {
	        		searchYear = httpSession.getAttribute("currentAcademicYear").toString();
	        	}else if (yoa > ca) {
	        		searchYear = request.getParameter("yearofadmission");
	        	}
	        	
	            List<Feescategory> feecategoryList= new feesCategoryDAO().getfeecategoryofstudent(classname,searchYear);
	            httpSession.setAttribute("feescategory", feecategoryList);

	            Locale indiaLocale = new Locale("en", "IN");
	    		PrintWriter out = response.getWriter(); 
	    		response.setContentType("text/xml");
	            response.setHeader("Cache-Control", "no-cache");

	    		if(feecategoryList.size() > 0) {

	    		        try {
	    		        	String buffer = "<div style='overflow:scroll;width:350px; height: 250px;'><table id='dataTable'><thead><tr>"
	    		        			+ "   			        				                            <td style='padding-right: 30px;font-weight: bold;color:#eb6000'>Fees Category</td>"
	    		        			+ "																	</tr>"
	    		        			+ "   			        										</thead>";
	   		        		/*String buffer = "<select name='subgroupname' style='width: 240px' id='sgname' onchange='dropdowndist();getSSGroup();'>";
	   		        		buffer = buffer +  "<option></option>";*/
	   			        	for(int i =0; i<feecategoryList.size();i++){
	   			        		buffer = buffer +  "<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
	   			        				+ "<label class='labelClass' style='font-weight: bold;color:#325F6D'> <input"
	   			        				+ "									 type='checkbox' name='feescategory' class='chcktbl' value="+feecategoryList.get(i).getIdfeescategory()+""
	   			        				+ "									size='18'> "+feecategoryList.get(i).getFeescategoryname()+" </label><input type='hidden' value='0' name='feesConcession' id='feesConcession_"+i+"' /><input type='hidden' class='feesId' name='feesIDS' id=fees_id_"+i+" value='"+feecategoryList.get(i).getIdfeescategory()+"'></td>"
	   			        						+ "</tr>";
	   			        	}
	   			        	buffer = buffer + "</table></div>";

	    			        	response.getWriter().println(buffer);

	    		        } catch (Exception e) {
	    		            out.write("<input name='feescategoryempty'  type='text' class='textfieldvalues' id='feescategoryempty'  style='font-size: 14px;' readonly>");
	    		        } finally {
	    		            out.flush();
	    		            out.close();
	    		        }
	    		}else {

	    		        try {
	    		        		String buffer = "<input name='balance'  type='text' class='textfieldvalues' id='balance' value='0' style='font-size: 14px;' readonly>";
	    			        	response.getWriter().println(buffer);

	    		        } catch (Exception e) {
	    		            out.write("<input name='balance'  type='text' class='textfieldvalues' id='balance'  style='font-size: 14px;' readonly>");
	    		        } finally {
	    		            out.flush();
	    		            out.close();
	    		        }
	    		}


	        }
	    }
}

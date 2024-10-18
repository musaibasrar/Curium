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

import javax.servlet.http.HttpServletResponse;

import org.ideoholic.curium.model.academicyear.dao.YearDAO;
import org.ideoholic.curium.model.academicyear.dto.Currentacademicyear;
import org.ideoholic.curium.model.account.dao.AccountDAO;
import org.ideoholic.curium.model.account.dto.VoucherEntrytransactions;
import org.ideoholic.curium.model.documents.dto.SearchStudentDto;
import org.ideoholic.curium.model.documents.dto.SearchStudentResponseDto;
import org.ideoholic.curium.model.feescategory.dao.feesCategoryDAO;
import org.ideoholic.curium.model.feescategory.dto.Concession;
import org.ideoholic.curium.model.feescategory.dto.ConcessionDto;
import org.ideoholic.curium.model.feescategory.dto.FeesCategoryDto;
import org.ideoholic.curium.model.feescategory.dto.Feescategory;
import org.ideoholic.curium.model.feescategory.dto.FeescategoryResponseDto;
import org.ideoholic.curium.model.feescategory.dto.IdFeescategoryDto;
import org.ideoholic.curium.model.feescategory.dto.OtherFeecategory;
import org.ideoholic.curium.model.feescategory.dto.OtherFeecategoryDto;
import org.ideoholic.curium.model.feescategory.dto.OtherFeesCategoryResponseDto;
import org.ideoholic.curium.model.feescategory.dto.SearchFeesResponseDto;
import org.ideoholic.curium.model.feescategory.dto.StudentListResponseDto;
import org.ideoholic.curium.model.feescollection.dao.feesCollectionDAO;
import org.ideoholic.curium.model.feesdetails.dao.feesDetailsDAO;
import org.ideoholic.curium.model.parents.dto.ParentListResponseDto;
import org.ideoholic.curium.model.parents.dto.Parents;
import org.ideoholic.curium.model.student.dao.studentDetailsDAO;
import org.ideoholic.curium.model.student.dto.Student;
import org.ideoholic.curium.model.student.dto.StudentIdDto;
import org.ideoholic.curium.model.student.dto.Studentfeesstructure;
import org.ideoholic.curium.util.DataUtil;
import org.ideoholic.curium.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeesService {
        
			@Autowired
            private HttpServletResponse response;
            /**
             * Size of a byte buffer to read/write file
             */
            private static final int BUFFER_SIZE = 4096;
        
			


        public FeescategoryResponseDto viewFees(String branchid,String currentAcademicYear ) {
        
        	FeescategoryResponseDto feescategoryResponseDto = new FeescategoryResponseDto();
                 
                 if(branchid!=null){
                	 String[] currentYear = currentAcademicYear.split("/");
              	   int cYear = Integer.parseInt(currentYear[0])+1;
              	   int cYear2 = Integer.parseInt(currentYear[1])+1;
              	   String nextYear = ""+cYear+"/"+cYear2+"";
                         try {
                                List<Feescategory> list = new feesCategoryDAO().readListOfFeeCategory(Integer.parseInt(branchid),currentAcademicYear,nextYear);
                                feescategoryResponseDto.setFeescategory(list);
                                feescategoryResponseDto.setSuccess(true);    
                        } catch (Exception e) {
                            e.printStackTrace();
                            feescategoryResponseDto.setSuccess(false);
                        }
                 }
                return feescategoryResponseDto;
        }


        public void addFeesParticular(FeesCategoryDto feesCategoryDto,String branchid,String userlogin) {
                
                if(branchid!=null){
                	
                	String[] classesFeesCat = feesCategoryDto.getFromClass();
                	List<Feescategory> feesCategoryList = new ArrayList<Feescategory>();
                	
                	for (String feeCat : classesFeesCat) {
                		Feescategory feescategorynew = new Feescategory();
                		feescategorynew.setFeescategoryname(DataUtil.emptyString(feesCategoryDto.getFeesCategory()));
                		feescategorynew.setParticularname(DataUtil.emptyString(feeCat)+"--");
                		feescategorynew.setAmount(DataUtil.parseInt(feesCategoryDto.getAmount()));
                		feescategorynew.setBranchid(Integer.parseInt(branchid));
                		feescategorynew.setUserid(Integer.parseInt(userlogin));
                		feescategorynew.setAcademicyear(DataUtil.emptyString(feesCategoryDto.getCategoryYear()));
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


        public void deleteMultiple(IdFeescategoryDto idFeescategoryDto) {
                 String[] idfeescategory = idFeescategoryDto.getIdFeesCategory(); 
                 if(idfeescategory!=null){
                List<Integer> ids = new ArrayList();
                for (String id : idfeescategory) {
                    System.out.println("id" + id);
                    ids.add(Integer.valueOf(id));
                }
                new feesCategoryDAO().deleteMultiple(ids);
                 }
        }


		public ParentListResponseDto viewAllStudentsList(String branchid) {
			ParentListResponseDto parentResponseDto = new ParentListResponseDto();
			try {
				List<Object[]> list = new feesDetailsDAO().readListOfStudents(Integer.parseInt(branchid));

				List<Parents> parentDetails = new ArrayList<Parents>();
				for (Object[] parentdetails : list) {
					Parents parent = new Parents();
					Student student = new Student();
					student.setSid((Integer) parentdetails[0]);
					student.setName((String) parentdetails[1]);
					student.setClassstudying((String) parentdetails[2]);
					student.setStudentexternalid((String) parentdetails[3]);
					student.setAdmissionnumber((String) parentdetails[4]);
					parent.setFathersname((String) parentdetails[5]);
					parent.setStudent(student);
					parentDetails.add(parent);
				}
				parentResponseDto.setParentsList(parentDetails);
				parentResponseDto.setSuccess(true);
			} catch (Exception e) {
				parentResponseDto.setSuccess(false);
			}
			return parentResponseDto;
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


        public StudentIdDto deleteFeesCategory(ConcessionDto concessionDto,String branchid,String userid) {
                
        	     StudentIdDto studentIdDto = new StudentIdDto();
                 String[] idfeescategory = concessionDto.getSfsid();
                 List<Integer> sfsId = new ArrayList();
                 List<Integer> feesCatId = new ArrayList();
                 List<VoucherEntrytransactions> transactionsList = new ArrayList<VoucherEntrytransactions>();
                 List<String> debitEntries = new ArrayList<String>();
                 List<String> creditEntries = new ArrayList<String>();
                 
                 String studentId = concessionDto.getId();
                 
                 if(idfeescategory!=null){
                         
                         for (String string : idfeescategory) {
                                 String[] test = string.split("_");
                                 sfsId.add(Integer.valueOf(test[0]));
                                 feesCatId.add(Integer.valueOf(test[1]));
                                 
                               //Accounts
                          		//Pass J.V. : credit the Fees as income & debit the cash
                                  List<Studentfeesstructure> sfs = new studentDetailsDAO().getStudentFeesStructureDetails(Integer.valueOf(test[0]));
                                  
                          		int drFees = getLedgerAccountId("unearnedstudentfeesincome"+Integer.parseInt(branchid));
                          		int crAccount = getLedgerAccountId("studentfeesreceivable"+Integer.parseInt(branchid));;

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
                          		transactions.setFinancialyear(new AccountDAO().getCurrentFinancialYear(Integer.parseInt(branchid)).getFinancialid());
                          		transactions.setBranchid(Integer.parseInt(branchid));
                          		transactions.setUserid(Integer.parseInt(userid));

                          		String updateCrAccount="update Accountdetailsbalance set currentbalance=currentbalance-"+sfs.get(0).getFeesamount()+" where accountdetailsid="+crAccount;

                          		String updateDrAccount="update Accountdetailsbalance set currentbalance=currentbalance-"+sfs.get(0).getFeesamount()+" where accountdetailsid="+drFees;
                          		transactionsList.add(transactions);
                          		debitEntries.add(updateDrAccount);
                          		creditEntries.add(updateCrAccount);
                          		// End J.V
                          		
                        }
                new feesCategoryDAO().deleteFeesCategory(sfsId,feesCatId,studentId,transactionsList,debitEntries,creditEntries);
                
                studentIdDto.getStudentId();
                return studentIdDto;
                 }
                throw new IllegalArgumentException("Fees category for the given student does not exist");
                
        }


    public StudentListResponseDto viewAllBranchStudents() {
        
    	StudentListResponseDto studentListResponseDto = new StudentListResponseDto();
        try {
                List<Student> list = new feesDetailsDAO().readListOfAllBranchStudents();
                studentListResponseDto.setStudentList(list);
        } catch (Exception e) {
        }
        return studentListResponseDto;
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
	 * "/demov2/StudentProcess/ViewFeesStructure&id="+studentId; }
	 * 
	 * return "error.jsp";
	 * 
	 * }
	 */
    
    public StudentIdDto waiveOffFees(ConcessionDto concessionDto) {
        
    	StudentIdDto studentIdDto = new StudentIdDto();
        String[] idfeescategory = concessionDto.getSfsid();
        List<Integer> sfsId = new ArrayList<Integer>();
        List<Integer> feesCatId = new ArrayList<Integer>();
        List<String> consession = new ArrayList<String>();
        List<Concession> concessionList = new ArrayList<Concession>();
        
        String studentId = concessionDto.getId();
        
        if(idfeescategory!=null){
                
                for (String string : idfeescategory) {
                	
                		Concession con = new Concession();
                		String[] test = string.split("_");
                        sfsId.add(Integer.valueOf(test[0]));
                        String dueAmount = concessionDto.getRequestParams().get("dueamount:"+Integer.valueOf(test[0]));
                        //String concessionAmount = request.getParameter("waiveoff:"+Integer.valueOf(test[0]));
                        
                        	feesCatId.add(Integer.valueOf(test[1]));
                            con.setSfsid(Integer.valueOf(test[0]));
                            con.setFeescatid(Integer.valueOf(test[1]));
                            con.setConcessionOld(concessionDto.getRequestParams().get("waiveoff:"+Integer.valueOf(test[0])));
                            con.setConcession(dueAmount);
                            concessionList.add(con);
                        
               }
           new feesCategoryDAO().waiveOffFees(concessionList,studentId);
           studentIdDto.setStudentId(studentId);
           return studentIdDto;
        }
        
        throw new IllegalArgumentException("Fees category for the given student does not exist");
       
	}


	public SearchFeesResponseDto searchFeesWaiveofforConcessionReport(SearchStudentDto searchStudentDto,String searchCriteria,String branchid) {
		
		SearchFeesResponseDto searchFeesResponseDto = new SearchFeesResponseDto();
		List<Parents> searchStudentList = new ArrayList<Parents>();
		Map<Parents,List<Studentfeesstructure>> parentsStudentFeesStructure = new HashMap<Parents,List<Studentfeesstructure>>();
		
		if(branchid!=null){
			String queryMain = "From Parents as parents where";
			String studentname = DataUtil.emptyString(searchStudentDto.getNameSearch());

			String addClass = searchStudentDto.getClassSearch();
			String addSec = searchStudentDto.getSecSearch();
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
				querySub = " parents.Student.name like '%" + studentname + "%' AND parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0 AND parents.branchid="+Integer.parseInt(branchid);
			}

			if (!classStudying.equalsIgnoreCase("")
					&& !querySub.equalsIgnoreCase("")) {
				querySub = querySub + " AND parents.Student.classstudying like '"
						+ classStudying + "' AND parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0 and parents.branchid="+Integer.parseInt(branchid);
			} else if (!classStudying.equalsIgnoreCase("")) {
				querySub = querySub + " parents.Student.classstudying like '"
						+ classStudying + "' AND parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0 and parents.branchid="+Integer.parseInt(branchid);
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
			searchFeesResponseDto.setCurrentYearFromService(currentYear.getCurrentacademicyear());
			
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
			searchFeesResponseDto.setStudentsFeesStructureDetailsWaiveoff(parentsStudentFeesStructure);
			searchFeesResponseDto.setStudentsFeesStructureDetailsConcession(null);
		}else if("concession".equalsIgnoreCase(searchCriteria)) {
			searchFeesResponseDto.setStudentsFeesStructureDetailsWaiveoff(null);
			searchFeesResponseDto.setStudentsFeesStructureDetailsConcession(parentsStudentFeesStructure);
		}
		
		return searchFeesResponseDto;
	}


	public StudentIdDto applyConcession(ConcessionDto concessionDto) {
		
		StudentIdDto studentIdDto = new StudentIdDto();
        String[] idfeescategory = concessionDto.getSfsid();
        List<Integer> sfsId = new ArrayList<Integer>();
        List<Integer> feesCatId = new ArrayList<Integer>();
        List<String> consession = new ArrayList<String>();
        List<Concession> concessionList = new ArrayList<Concession>();
        
        String studentId = concessionDto.getId();
        
        if(idfeescategory!=null){
                
                for (String string : idfeescategory) {
                	
                		Concession con = new Concession();
                		String[] test = string.split("_");
                        sfsId.add(Integer.valueOf(test[0]));
                        String dueAmount = concessionDto.getRequestParams().get("dueamount:"+Integer.valueOf(test[0]));
                        String concessionAmount = concessionDto.getRequestParams().get("concession:"+Integer.valueOf(test[0]));
                        
                        if(Integer.parseInt(concessionAmount)<=Integer.parseInt(dueAmount)) {
                        	feesCatId.add(Integer.valueOf(test[1]));
                            con.setSfsid(Integer.valueOf(test[0]));
                            con.setFeescatid(Integer.valueOf(test[1]));
                            con.setConcessionOld(concessionDto.getRequestParams().get("concessionold:"+Integer.valueOf(test[0])));
                            con.setConcession(concessionDto.getRequestParams().get("concession:"+Integer.valueOf(test[0])));
                            concessionList.add(con);
                        }
                        
               }
           new feesCategoryDAO().applyConcession(concessionList,studentId);
           studentIdDto.setStudentId(studentId);
           return studentIdDto;
        }
        
        throw new IllegalArgumentException("Fees category for the given student does not exist");
       
	}


	public FeescategoryResponseDto viewFeesYearly(String academicYear,String branchid) throws IOException {
		FeescategoryResponseDto feescategoryResponseDto = new FeescategoryResponseDto();
        if(branchid!=null){
        	
                List<Feescategory> list = new feesCategoryDAO().readListOfObjects(Integer.parseInt(branchid),academicYear);
                feescategoryResponseDto.setFeescategory(list);
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
	    return feescategoryResponseDto;
	}
	
	   public OtherFeesCategoryResponseDto viewOtherFees(String branchid, String currentAcademicYear ) {

		   OtherFeesCategoryResponseDto otherFeesCategoryResponseDto = new OtherFeesCategoryResponseDto();
           boolean result = false;

           if(branchid!=null){
        	   String[] currentYear = currentAcademicYear.split("/");
        	   int cYear = Integer.parseInt(currentYear[0])+1;
        	   int cYear2 = Integer.parseInt(currentYear[1])+1;
        	   String nextYear = ""+cYear+"/"+cYear2+"";
        		try {
                          List<OtherFeecategory> list = new feesCategoryDAO().readListOfOtherFeeObjects(Integer.parseInt(branchid),currentAcademicYear,nextYear);
                          otherFeesCategoryResponseDto.setOtherFeesCategory(list);
                          //httpSession.setAttribute("otherfeescategory", list);
                          otherFeesCategoryResponseDto.setSuccess(true);
                          result = true;
                  } catch (Exception e) {
                      e.printStackTrace();
                      otherFeesCategoryResponseDto.setSuccess(false);
                      result = false;
                  }
           }
          return otherFeesCategoryResponseDto;
  }
	   
	   public void addOtherFeesParticular(OtherFeecategoryDto otherFeecategoryDto,String branchid,String userloginid) {

           OtherFeecategory ofeescategory = new OtherFeecategory();

           if(branchid!=null){

                   ofeescategory.setFeescategoryname(DataUtil.emptyString(otherFeecategoryDto.getFeesCategory()));
                   if(!DataUtil.emptyString(otherFeecategoryDto.getFromClass()).equalsIgnoreCase("ALL") && !DataUtil.emptyString(otherFeecategoryDto.getToClass()).equalsIgnoreCase("ALL")){
                           ofeescategory.setParticularname(DataUtil.emptyString(otherFeecategoryDto.getFromClass())+"-"+DataUtil.emptyString(otherFeecategoryDto.getToClass()));
                   }else{
                           ofeescategory.setParticularname(DataUtil.emptyString(otherFeecategoryDto.getFromClass()));
                   }

                   ofeescategory.setAmount(DataUtil.parseInt(otherFeecategoryDto.getAmount()));
                   ofeescategory.setBranchid(Integer.parseInt(branchid));
                   ofeescategory.setUserid(Integer.parseInt(userloginid));
                   ofeescategory.setAcademicyear(otherFeecategoryDto.getCategoryYearOf());
                   if(!ofeescategory.getFeescategoryname().equalsIgnoreCase("") && !ofeescategory.getParticularname().equalsIgnoreCase("") && ofeescategory.getAmount() != 0 ){
                           ofeescategory =  new feesCategoryDAO().createOtherFeeCategory(ofeescategory);
                   }
           }
   }
	   
	   public void odeleteMultiple(IdFeescategoryDto idFeescategoryDto) {
           String[] idfeescategory = idFeescategoryDto.getIdFeesCategory();
           if(idfeescategory!=null){
          List<Integer> ids = new ArrayList();
          for (String id : idfeescategory) {
              System.out.println("id" + id);
              ids.add(Integer.valueOf(id));
          }
          new feesCategoryDAO().odeleteMultiple(ids);
           }
  }
	   
	   public FeescategoryResponseDto getFeeCategory(String classname,String yearofAdmissionStr,String currentAcademicYearStr,String branchid) throws IOException {

		   FeescategoryResponseDto feescategoryResponseDto = new FeescategoryResponseDto();
	        if(branchid!=null){
	        	String[] yearofAdmission = yearofAdmissionStr.split("/");
	        	String[] currentAcademicYear = currentAcademicYearStr.split("/");
	        	String searchYear = null;
	        	int yoa = Integer.parseInt(yearofAdmission[0]);
	        	int ca = Integer.parseInt(currentAcademicYear[0]);
	        	
	        	if(yoa == ca || yoa < ca) {
	        		searchYear = currentAcademicYearStr;
	        	}else if (yoa > ca) {
	        		searchYear = yearofAdmissionStr;
	        	}
	        	
	            List<Feescategory> feecategoryList= new feesCategoryDAO().getfeecategoryofstudent(classname,searchYear);
	            feescategoryResponseDto.setFeescategory(feecategoryList);

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
	   			        				+ "									 type='checkbox' name='feescategory' class='chcktbl' value="+feecategoryList.get(i).getIdfeescategory()+"--"+i+""
	   			        				+ "									size='18'> "+feecategoryList.get(i).getFeescategoryname()+" : </label></td><td> <label style='font-weight: bold;color:#eb6000'>"+feecategoryList.get(i).getParticularname()+""
	   			        				+ "							</label> &nbsp;&nbsp;<input type='hidden' value='0' name='feesConcession' id='feesConcession_"+i+"' /><input type='hidden' class='feesId' name='feesIDS' id=fees_id_"+i+" value='"+feecategoryList.get(i).getIdfeescategory()+"'></td><td><input class='feesAmount' type='text' value='"+feecategoryList.get(i).getAmount()+"'   name='fessCat'  id=hiddenfees_amount_"+i+" size='18'/></td><td> <input"
	   			        						+ "   			     type='text' value='0' name='feesCount' id='feesCount_"+i+"'"
	   			        						+ "   			        				+ \"								onclick='calculate("+i+")' onkeyup='calculate("+i+")' size='18' required><br></td>"
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
	        return feescategoryResponseDto;
	    }
	   
	   public StudentIdDto applyotherConcession(ConcessionDto concessionDto) {

		   StudentIdDto studentIdDto = new StudentIdDto();
	        String[] idfeescategory = concessionDto.getSfsid();
	        List<Integer> sfsId = new ArrayList<Integer>();
	        List<Integer> feesCatId = new ArrayList<Integer>();
	        List<String> consession = new ArrayList<String>();
	        List<Concession> concessionList = new ArrayList<Concession>();

	        String studentId = concessionDto.getId();

	        if(idfeescategory!=null){

	                for (String string : idfeescategory) {

	                		Concession con = new Concession();
	                		String[] test = string.split("_");
	                        sfsId.add(Integer.valueOf(test[0]));
	                			                        String dueAmount = concessionDto.getRequestParams().get("dueamount:"+Integer.valueOf(test[0]));
	                        String concessionAmount = concessionDto.getRequestParams().get("concession:"+Integer.valueOf(test[0]));

	                        if(Integer.parseInt(concessionAmount)<=Integer.parseInt(dueAmount)) {
	                        	feesCatId.add(Integer.valueOf(test[1]));
	                            con.setSfsid(Integer.valueOf(test[0]));
	                            con.setFeescatid(Integer.valueOf(test[1]));
	                            con.setConcessionOld(concessionDto.getRequestParams().get("concessionold:"+Integer.valueOf(test[0])));
	                            con.setConcession(concessionDto.getRequestParams().get("concession:"+Integer.valueOf(test[0])));
	                            concessionList.add(con);
	                        }

	               }
	           new feesCategoryDAO().applyotherConcession(concessionList,studentId);
	           studentIdDto.setStudentId(studentId);
	           return studentIdDto;
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

		public FeescategoryResponseDto getFeeCategoryHeadWise(String classname,String yearofAdmissionStr,String currentAcademicYearStr,String branchid) throws IOException {

			FeescategoryResponseDto feescategoryResponseDto = new FeescategoryResponseDto();
	        if(branchid!=null){
	        	String[] yearofAdmission = yearofAdmissionStr.split("/");
	        	String[] currentAcademicYear = currentAcademicYearStr.split("/");
	        	String searchYear = null;
	        	int yoa = Integer.parseInt(yearofAdmission[0]);
	        	int ca = Integer.parseInt(currentAcademicYear[0]);
	        	
	        	if(yoa == ca || yoa < ca) {
	        		searchYear = currentAcademicYearStr;
	        	}else if (yoa > ca) {
	        		searchYear = yearofAdmissionStr;
	        	}
	        	
	            List<Feescategory> feecategoryList= new feesCategoryDAO().getfeecategoryofstudent(classname,searchYear);
	            feescategoryResponseDto.setFeescategory(feecategoryList);

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
	        return feescategoryResponseDto;
	    }
		
		public SearchStudentResponseDto getDndReport(String branchid) {

			SearchStudentResponseDto searchStudentResponseDto = new SearchStudentResponseDto();
			String queryMain = "From Parents as parents where parents.Student.branchid="+Integer.parseInt(branchid)+" AND";
			String querySub = " parents.Student.archive = 0 AND parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0 AND crecorddate is not null order by parents.Student.crecorddate DESC";
			queryMain = queryMain + querySub;

			List<Parents> searchStudentList = new studentDetailsDAO().getStudentsList(queryMain);
			searchStudentResponseDto.setSearchStudentList(searchStudentList);
			return searchStudentResponseDto;
		}
		
		public StudentIdDto deleteOtherFeesCategory(ConcessionDto concessionDto) {
            
			StudentIdDto studentIdDto = new StudentIdDto();
            String[] idfeescategory = concessionDto.getSfsid();
            List<Integer> sfsId = new ArrayList();
            List<Integer> feesCatId = new ArrayList();
            
            String studentId = concessionDto.getId();
            
            if(idfeescategory!=null){
                    
                    for (String string : idfeescategory) {
                            String[] test = string.split("_");
                            sfsId.add(Integer.valueOf(test[0]));
                            feesCatId.add(Integer.valueOf(test[1]));
                   }
           new feesCategoryDAO().deleteOtherFeesCategory(sfsId,feesCatId,studentId);
           studentIdDto.setStudentId(studentId);
           return studentIdDto;
            }
           throw new IllegalArgumentException("Fees category for the given student does not exist");
           
   }
}

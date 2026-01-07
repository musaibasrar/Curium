package org.ideoholic.curium.model.feescategory.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletResponse;

import org.ideoholic.curium.dto.ResultResponse;
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
import org.ideoholic.curium.model.std.dao.StandardDetailsDAO;
import org.ideoholic.curium.model.std.dto.Classhierarchy;
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

						if (feesCategoryDto.getMonths()==null) {
							Feescategory feescategorynew = new Feescategory();
							feescategorynew.setFeescategoryname(DataUtil.emptyString(feesCategoryDto.getFeesCategory()));
							feescategorynew.setParticularname(DataUtil.emptyString(feeCat) + "--");
							feescategorynew.setAmount(DataUtil.parseInt(feesCategoryDto.getAmount()));
							feescategorynew.setBranchid(Integer.parseInt(branchid));
							feescategorynew.setUserid(Integer.parseInt(userlogin));
							feescategorynew.setAcademicyear(DataUtil.emptyString(feesCategoryDto.getCategoryYear()));
							feescategorynew.setTotalinstallments(feesCategoryDto.getTotalInstallments());
							if (!feescategorynew.getFeescategoryname().equalsIgnoreCase("")
									&& !feescategorynew.getParticularname().equalsIgnoreCase("")
									&& feescategorynew.getAmount() != 0) {
								feesCategoryList.add(feescategorynew);
							}
						} else if (feesCategoryDto.getMonths().length > 1) {
							
							for (String monthlyFees : feesCategoryDto.getMonths()) {
								
								Feescategory feescategorynew = new Feescategory();
								feescategorynew.setFeescategoryname(monthlyFees+" "+DataUtil.emptyString(feesCategoryDto.getFeesCategory()));
								feescategorynew.setParticularname(DataUtil.emptyString(feeCat) + "--");
								feescategorynew.setAmount(DataUtil.parseInt(feesCategoryDto.getAmount()));
								feescategorynew.setBranchid(Integer.parseInt(branchid));
								feescategorynew.setUserid(Integer.parseInt(userlogin));
								feescategorynew.setAcademicyear(DataUtil.emptyString(feesCategoryDto.getCategoryYear()));
								feescategorynew.setTotalinstallments(feesCategoryDto.getTotalInstallments());
								if (!feescategorynew.getFeescategoryname().equalsIgnoreCase("")
										&& !feescategorynew.getParticularname().equalsIgnoreCase("")
										&& feescategorynew.getAmount() != 0) {
									feesCategoryList.add(feescategorynew);
								}
							
							}
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
					student.setAdmissiondate((Date)parentdetails[5]);
					parent.setFathersname((String) parentdetails[6]);
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


        public ResultResponse downlaodFile() {
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
                        ResultResponse.builder().success(true).build();
                } catch (Exception e) {
                        System.out.println("" + e);
                }
                return ResultResponse.builder().success(false).build();
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
	 * "/dolphin/StudentProcess/ViewFeesStructure&id="+studentId; }
	 * 
	 * return "error.jsp";
	 * 
	 * }
	 */
    
    public StudentIdDto waiveOffFees(ConcessionDto concessionDto, String academicYear, String branchId, String userId) {
        
    	StudentIdDto studentIdDto = new StudentIdDto();
        String[] idfeescategory = concessionDto.getSfsid();
        List<Integer> sfsId = new ArrayList<Integer>();
        List<Integer> feesCatId = new ArrayList<Integer>();
        List<String> consession = new ArrayList<String>();
        List<Concession> concessionList = new ArrayList<Concession>();
        List<VoucherEntrytransactions> transactionsApplyList = new ArrayList<VoucherEntrytransactions>();
        List<String> updateDrAccountApplyList = new ArrayList<String>();
        List<String> updateCrAccountApplyList = new ArrayList<String>();
        
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
                            
                            
                          //Accounts
                    		//Pass J.V. : credit the Fees as income & debit the cash
                    		
                            //Apply New Concession
                            BigDecimal grandTotalConcessionApply = new BigDecimal(con.getConcession());
                            if(grandTotalConcessionApply.compareTo(BigDecimal.ZERO)==1) {
                    		int crFees = getLedgerAccountId("studentfeesreceivable"+Integer.parseInt(branchId));
                    		int drAccount = getLedgerAccountId("unearnedstudentfeesincome"+Integer.parseInt(branchId));;
                    		
                    		VoucherEntrytransactions transactionsApply = new VoucherEntrytransactions();
                    		
                    		transactionsApply.setDraccountid(drAccount);
                    		transactionsApply.setCraccountid(crFees);
                    		transactionsApply.setDramount(grandTotalConcessionApply);
                    		transactionsApply.setCramount(grandTotalConcessionApply);
                    		transactionsApply.setVouchertype(4);
                    		transactionsApply.setTransactiondate(DateUtil.todaysDate());
                    		transactionsApply.setEntrydate(DateUtil.todaysDate());
                    		transactionsApply.setNarration("Towards Fees Waiveoff");
                    		transactionsApply.setCancelvoucher("no");
                    		transactionsApply.setFinancialyear(new AccountDAO().getCurrentFinancialYear(Integer.parseInt(branchId)).getFinancialid());
                    		transactionsApply.setBranchid(Integer.parseInt(branchId));
                    		transactionsApply.setUserid(Integer.parseInt(userId));
                    		
                    		String updateDrAccountApply="update Accountdetailsbalance set currentbalance=currentbalance+"+grandTotalConcessionApply+" where accountdetailsid="+drAccount;

                    		String updateCrAccountApply="update Accountdetailsbalance set currentbalance=currentbalance+"+grandTotalConcessionApply+" where accountdetailsid="+crFees;
                    		
                    		updateDrAccountApplyList.add(updateDrAccountApply);
                    		updateCrAccountApplyList.add(updateCrAccountApply);
                    		transactionsApplyList.add(transactionsApply);
                            }
                    		// End J.V
                        
               }
           new feesCategoryDAO().waiveOffFees(concessionList,studentId,transactionsApplyList,updateDrAccountApplyList,updateCrAccountApplyList);
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
			searchFeesResponseDto.setCurrentYearFromService(searchStudentDto.getAcademicyear());
			
			List<Studentfeesstructure> listStudentsFeesStructure = new feesCollectionDAO().getStudentsFeesStructure(studentids, searchStudentDto.getAcademicyear(), searchCriteria);
			
			
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


	public StudentIdDto applyConcession(ConcessionDto concessionDto, String academicYear, String branchId, String userId) {
		
		StudentIdDto studentIdDto = new StudentIdDto();
        String[] idfeescategory = concessionDto.getSfsid();
        List<Integer> sfsId = new ArrayList<Integer>();
        List<Integer> feesCatId = new ArrayList<Integer>();
        List<Concession> concessionList = new ArrayList<Concession>();
        List<VoucherEntrytransactions> transactionsReverseList = new ArrayList<VoucherEntrytransactions>();
        List<VoucherEntrytransactions> transactionsApplyList = new ArrayList<VoucherEntrytransactions>();
        List<String> updateDrAccountReverseList = new ArrayList<String>();
        List<String> updateCrAccountReverseList = new ArrayList<String>();
        List<String> updateDrAccountApplyList = new ArrayList<String>();
        List<String> updateCrAccountApplyList = new ArrayList<String>();
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
                            con.setConcessionNotes(concessionDto.getRequestParams().get("concessionnotes:"+Integer.valueOf(test[0])));
                            concessionList.add(con);
                            
                            
                            
                            //Accounts
                    		//Pass J.V. : credit the Fees as income & debit the cash
                    		
                            
                            //Reverse Old Concession
                            BigDecimal grandTotalConcessionReverse = new BigDecimal(con.getConcessionOld());
                            if(grandTotalConcessionReverse.compareTo(BigDecimal.ZERO)==1) {
                            	int crFees = getLedgerAccountId("unearnedstudentfeesincome"+Integer.parseInt(branchId));
                        		int drAccount = getLedgerAccountId("studentfeesreceivable"+Integer.parseInt(branchId));
                        		
                        		VoucherEntrytransactions transactionsReverse = new VoucherEntrytransactions();
                        		
                        		transactionsReverse.setDraccountid(drAccount);
                        		transactionsReverse.setCraccountid(crFees);
                        		transactionsReverse.setDramount(grandTotalConcessionReverse);
                        		transactionsReverse.setCramount(grandTotalConcessionReverse);
                        		transactionsReverse.setVouchertype(4);
                        		transactionsReverse.setTransactiondate(DateUtil.todaysDate());
                        		transactionsReverse.setEntrydate(DateUtil.todaysDate());
                        		transactionsReverse.setNarration("Towards Fees Stamp");
                        		transactionsReverse.setCancelvoucher("no");
                        		transactionsReverse.setFinancialyear(new AccountDAO().getCurrentFinancialYear(Integer.parseInt(branchId)).getFinancialid());
                        		transactionsReverse.setBranchid(Integer.parseInt(branchId));
                        		transactionsReverse.setUserid(Integer.parseInt(branchId));
                        		
                        		String updateDrAccountReverse="update Accountdetailsbalance set currentbalance=currentbalance+"+grandTotalConcessionReverse+" where accountdetailsid="+drAccount;

                        		String updateCrAccountReverse="update Accountdetailsbalance set currentbalance=currentbalance+"+grandTotalConcessionReverse+" where accountdetailsid="+crFees;
                        		
                        		updateDrAccountReverseList.add(updateDrAccountReverse);
                        		updateCrAccountReverseList.add(updateCrAccountReverse);
                        		transactionsReverseList.add(transactionsReverse);
                            }
                            
                            //
                            
                            //Apply New Concession
                            BigDecimal grandTotalConcessionApply = new BigDecimal(con.getConcession());
                            if(grandTotalConcessionApply.compareTo(BigDecimal.ZERO)==1) {
                    		int crFees = getLedgerAccountId("studentfeesreceivable"+Integer.parseInt(branchId));
                    		int drAccount = getLedgerAccountId("unearnedstudentfeesincome"+Integer.parseInt(branchId));;
                    		
                    		VoucherEntrytransactions transactionsApply = new VoucherEntrytransactions();
                    		
                    		transactionsApply.setDraccountid(drAccount);
                    		transactionsApply.setCraccountid(crFees);
                    		transactionsApply.setDramount(grandTotalConcessionApply);
                    		transactionsApply.setCramount(grandTotalConcessionApply);
                    		transactionsApply.setVouchertype(4);
                    		transactionsApply.setTransactiondate(DateUtil.todaysDate());
                    		transactionsApply.setEntrydate(DateUtil.todaysDate());
                    		transactionsApply.setNarration("Towards Fees Concession");
                    		transactionsApply.setCancelvoucher("no");
                    		transactionsApply.setFinancialyear(new AccountDAO().getCurrentFinancialYear(Integer.parseInt(branchId)).getFinancialid());
                    		transactionsApply.setBranchid(Integer.parseInt(branchId));
                    		transactionsApply.setUserid(Integer.parseInt(userId));
                    		
                    		String updateDrAccountApply="update Accountdetailsbalance set currentbalance=currentbalance+"+grandTotalConcessionApply+" where accountdetailsid="+drAccount;

                    		String updateCrAccountApply="update Accountdetailsbalance set currentbalance=currentbalance+"+grandTotalConcessionApply+" where accountdetailsid="+crFees;
                    		
                    		updateDrAccountApplyList.add(updateDrAccountApply);
                    		updateCrAccountApplyList.add(updateCrAccountApply);
                    		transactionsApplyList.add(transactionsApply);
                            }
                    		// End J.V
                        }
                        
               }
                
           new feesCategoryDAO().applyConcession(concessionList,studentId,transactionsReverseList,transactionsApplyList,updateDrAccountReverseList,updateCrAccountReverseList,updateDrAccountApplyList,updateCrAccountApplyList);
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

           if(branchid!=null){
        	   

           	
           	String[] classesOtherFeesCat = otherFeecategoryDto.getFromClass();
           	List<OtherFeecategory> feesOtherCategoryList = new ArrayList<OtherFeecategory>();
           	
           	for (String feeCat : classesOtherFeesCat) {
           		OtherFeecategory otherFeescategorynew = new OtherFeecategory();
           		otherFeescategorynew.setFeescategoryname(DataUtil.emptyString(otherFeecategoryDto.getFeesCategory()));
           		otherFeescategorynew.setParticularname(DataUtil.emptyString(feeCat)+"--");
           		otherFeescategorynew.setAmount(DataUtil.parseInt(otherFeecategoryDto.getAmount()));
           		otherFeescategorynew.setBranchid(Integer.parseInt(branchid));
           		otherFeescategorynew.setUserid(Integer.parseInt(userloginid));
           		otherFeescategorynew.setAcademicyear(DataUtil.emptyString(otherFeecategoryDto.getCategoryYearOf()));
                   if(!otherFeescategorynew.getFeescategoryname().equalsIgnoreCase("") && !otherFeescategorynew.getParticularname().equalsIgnoreCase("") && otherFeescategorynew.getAmount() != 0 ){
                	   feesOtherCategoryList.add(otherFeescategorynew);
                   }
           		}
           		boolean result =  new feesCategoryDAO().createOtherFeescategory(feesOtherCategoryList);
                
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
	   
	   public FeescategoryResponseDto getFeeCategory(String classname,String yearofAdmissionStr,String currentAcademicYearStr,String branchid, String feesCategories) throws IOException {

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
	        	
	            List<Feescategory> feecategoryList= new feesCategoryDAO().getfeecategoryofstudent(classname,searchYear,branchid);
	            feescategoryResponseDto.setFeescategory(feecategoryList);

	            int grandTotalAmount=0;
	            Locale indiaLocale = new Locale("en", "IN");
	    		PrintWriter out = response.getWriter(); 
	    		response.setContentType("text/xml");
	            response.setHeader("Cache-Control", "no-cache");

	    		if(feecategoryList.size() > 0) {

	    		        try {StringBuilder buffer = new StringBuilder();

buffer.append("<table id='dataTable' style='width:100%; border-collapse:collapse;'>")

      // Column width mapping — fixes <thead> misalignment 100%
      .append("<colgroup>")
      .append(" <col style='width:20%'>")  // Fees Category
      .append(" <col style='width:15%'>")  // Class
      .append(" <col style='width:15%'>")  // Fees Amount
      .append(" <col style='width:25%'>")  // Installments
      .append(" <col style='width:25%'>")  // Total Amount
      .append("</colgroup>")

      // ---------------------- HEADER -------------------------
      .append("<thead>")
      .append("<tr style='background:#4b6a84; color:white; height:22px; font-family:Tahoma;'>")
      .append(" <th><input  type='checkbox' checked id = 'chckHead' onclick='toggleFeesCount(this)' /></th>")
      .append(" <th>Fees Category</th>")
      .append(" <th>Class</th>")
      .append(" <th>Fees Amount</th>")
      .append(" <th>No. of Installments in a Year</th>")
      .append(" <th>Total Amount</th>")
      .append("</tr>")
      .append("</thead>")

      .append("<tbody>");

// ---------------------- BODY LOOP -------------------------
for(int i = 0; i < feecategoryList.size(); i++) {

    int totalAmountPerCategory = 0;
    grandTotalAmount += totalAmountPerCategory;
    boolean checkFeesCat = false;
    String checkBoxChecked = "";
    for (String category : feesCategories.split(",")) {
    	String feesCategoryName = feecategoryList.get(i).getFeescategoryname().toLowerCase();
    	String feeCat = category.toLowerCase();
        if (feesCategoryName.contains(feeCat)) {
        	checkFeesCat = true;
        	totalAmountPerCategory=feecategoryList.get(i).getTotalinstallments() * feecategoryList.get(i).getAmount();
        	break;
        }
    }
    
    if(checkFeesCat) {
    	checkBoxChecked= " checked";
    }

    buffer.append("<tr style='height:26px;'>")
    
    // Column 1 – Fee Category checkbox
    .append("<td style='text-align:center;'>")
    .append("<input type='checkbox' class='chcktbl' name='feescategory'")
    .append(checkBoxChecked)
    .append(" id='feesCat_").append(i).append("'")
    .append(" value='").append(feecategoryList.get(i).getIdfeescategory()).append("--").append(i).append("'")
    .append(" onclick='updateFeesCategory(").append(i).append(")'/></td> ")

          // Column 1 – Fee Category checkbox
          .append("<td>")
          .append(feecategoryList.get(i).getFeescategoryname()).append("</td>")

          // Column 2 – Class
          .append("<td>")
          .append(feecategoryList.get(i).getParticularname())
          .append("<input type='hidden' name='feesConcession' id='feesConcession_").append(i).append("' value='0'/>")
          .append("<input type='hidden' name='feesIDS' class='feesId' id='fees_id_").append(i).append("' value='").append(feecategoryList.get(i).getIdfeescategory()).append("'/>")
          .append("</td>")

          // Column 3 – Fee Amount
          .append("<td><input class='feesAmount' size='18' type='text'")
          .append(" value='").append(feecategoryList.get(i).getAmount()).append("'")
          .append(" name='fessCat' id='hiddenfees_amount_").append(i).append("'/></td>")

          // Column 4 – No. of installments
          .append("<td>")
          .append("<input type='text' size='18' required")
          .append(" value='").append(checkFeesCat ? feecategoryList.get(i).getTotalinstallments() : "0").append("'")
          .append(" name='feesCount' id='feesCount_").append(i).append("'")
          .append(" onkeyup='calculate(").append(i).append(")' onclick='calculate(").append(i).append(")'/>")
          .append("<input type='hidden' name='totalinstallmentsactual' id='totalinstallmentsactual_").append(i).append("' value='").append(feecategoryList.get(i).getTotalinstallments()).append("'/>")
          .append("</td>")

          // Column 5 – Final Total
          .append("<td>")
          .append("<input class='feesFullAmount' size='18' type='text'")
          .append(" name='feesFullCat' id='hiddenfees_full_amount_").append(i).append("'")
          .append(" value='").append(totalAmountPerCategory).append("'/>")
          .append("</td>")

          .append("</tr>");
}

// ---------------------- FOOTER -------------------------
buffer.append("</tbody>")
      .append("<tfoot>")
      .append("<tr>")
      .append(" <td colspan='4' style='text-align:right; font-weight:bold;'>Total</td>")
      .append(" <td style='text-align:left;'>")
      .append("   <input type='text' size='18' style='font-weight:bold;' onclick='calculateGrandTotal()' id='feesTotalAmount' name='feesTotalAmount' value='").append(grandTotalAmount).append("'>")
      .append(" </td>")
      .append("</tr>")
      .append("</tfoot>")
      .append("</table>");

response.getWriter().println(buffer.toString());
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
	                            con.setConcessionNotes(concessionDto.getRequestParams().get("concessionnotes:"+Integer.valueOf(test[0])));
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
	        	String searchYear = yearofAdmissionStr;
	        	String searchClassName = null;
	        	
	            
	        	int diff = Integer.parseInt(currentAcademicYear[0])-Integer.parseInt(yearofAdmission[0]);
	        	//Get the Class for yearofadmission
	        	List<Classhierarchy> classHierarchyList = new StandardDetailsDAO().viewClassHierarchy(Integer.parseInt(branchid));
	        	
	        	String[] classHierarchyArray = new String[classHierarchyList.size()];
	        	int j=0;
	        	for (Classhierarchy classHierarchy : classHierarchyList) {
	        		classHierarchyArray[j]=classHierarchy.getLowerclass();
	        		j++;
				}

	        	 int classIndex = -1;

	             for (int i = 0; i < classHierarchyArray.length; i++) {
	                 if (classHierarchyArray[i].equals(classname)) {
	                	 classIndex = i;
	                     break;
	                 }
	             }
	             
	             if(diff>0) {
	            	 searchClassName = classHierarchyArray[classIndex-diff];
	             }else {
	            	 searchClassName = classname;
	             }
	        	
	             List<Feescategory> feecategoryList= new feesCategoryDAO().getfeecategoryofstudent(searchClassName,searchYear,branchid);
		         feescategoryResponseDto.setFeescategory(feecategoryList);
		         feescategoryResponseDto.setFeesDueSearchYear(searchYear);
		         feescategoryResponseDto.setFeesDueSearchClass(searchClassName);
	            
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


		public OtherFeesCategoryResponseDto getOtherFeeCategory(String classname,String yearofAdmissionStr,String currentAcademicYearStr,String branchid)  throws IOException{

			OtherFeesCategoryResponseDto otherFeesCategoryResponseDto = new OtherFeesCategoryResponseDto();
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
		        	
		            List<OtherFeecategory> feecategoryList= new feesCategoryDAO().getOtherFeeCategory(classname,searchYear,branchid);
		            otherFeesCategoryResponseDto.setOtherFeesCategory(feecategoryList);

		            Locale indiaLocale = new Locale("en", "IN");
		    		PrintWriter out = response.getWriter(); 
		    		response.setContentType("text/xml");
		            response.setHeader("Cache-Control", "no-cache");

		    		if(feecategoryList.size() > 0) {

		    		        try {
		    		        	String buffer = "<div style='overflow:scroll;width:750px; height: 250px;'><table id='dataTableOtherFees'><thead><tr>"
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
		   			        				+ "									 type='checkbox' name='otherFeesCategory' class='chcktblotherfees' value="+feecategoryList.get(i).getIdfeescategory()+"--"+i+""
		   			        				+ "									size='18'> "+feecategoryList.get(i).getFeescategoryname()+" : </label></td><td> <label style='font-weight: bold;color:#eb6000'>"+feecategoryList.get(i).getParticularname()+""
		   			        				+ "							</label> &nbsp;&nbsp;&nbsp;&nbsp;<input type='hidden' value='0' name='otherFeesConcession' id='otherFeesConcession_"+i+"' /> <input type='hidden' class='otherFeesId' name='otherFeesIDS' id=otherFees_id_"+i+" value='"+feecategoryList.get(i).getIdfeescategory()+"'></td><td><input class='otherFeesAmount' type='text' value='"+feecategoryList.get(i).getAmount()+"'   name='otherFessCat'  id=hiddenOtherFees_amount_"+i+" size='18'/></td><td> <input"
		   			        						+ "   			     type='text' value='0' name='otherFeesCount' id='otherFeesCount_"+i+"'"
		   			        						+ "   			        				+ \"								onclick='calculateOtherFees("+i+")' onkeyup='calculateOtherFees("+i+")' size='18' required><br></td>"
		   			        						+ "<td> <input class='otherFeesFullAmount' type='text' value='0' name='otherFeesFullCat' id='hiddenotherFees_full_amount_"+i+"' size='18'></td></tr>";
		   			        	}
		   			        	buffer = buffer + " <tfoot><tr><td colspan='4' align='right'>Total</td><td align='center'><input type='text' name='otherFeesTotalAmount' id='otherFeesTotalAmount' value='0' /></td></tr></table></div>";

		    			        	response.getWriter().println(buffer);

		    		        } catch (Exception e) {
		    		            out.write("<input name='otherfeescategoryempty'  type='text' class='textfieldvalues' id='otherfeescategoryempty'  style='font-size: 14px;' readonly>");
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
		        return otherFeesCategoryResponseDto;
		    }


		public FeescategoryResponseDto getFeesMonths(String branchId) {
			FeescategoryResponseDto feescategoryResponseDto = new FeescategoryResponseDto();
			feescategoryResponseDto.setFeesMonths(new DataUtil().getPropertiesValue("feesmonths"+branchId));
			return feescategoryResponseDto;
		}
}

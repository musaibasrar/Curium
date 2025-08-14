package org.ideoholic.curium.model.feesdetails.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.query.Query;
import org.ideoholic.curium.model.feescategory.dto.Feescategory;
import org.ideoholic.curium.model.feescategory.dto.OtherFeecategory;
import org.ideoholic.curium.model.feescollection.dto.Feescollection;
import org.ideoholic.curium.model.feescollection.dto.Otherfeescollection;
import org.ideoholic.curium.model.feescollection.dto.Otherreceiptinfo;
import org.ideoholic.curium.model.feescollection.dto.Receiptinfo;
import org.ideoholic.curium.model.feesdetails.dto.Feesdetails;
import org.ideoholic.curium.model.stampfees.dao.StampFeesDAO;
import org.ideoholic.curium.model.stampfees.dto.Academicotherfeesstructure;
import org.ideoholic.curium.model.student.dao.StudentDetailsDAO;
import org.ideoholic.curium.model.student.dto.CreateStudentDto;
import org.ideoholic.curium.model.student.dto.Student;
import org.ideoholic.curium.model.student.dto.Studentfeesstructure;
import org.ideoholic.curium.model.student.dto.Studentotherfeesstructure;
import org.ideoholic.curium.repositories.AcademicfeesstructureRepository;
import org.ideoholic.curium.repositories.FeescategoryRepository;
import org.ideoholic.curium.repositories.FeesdetailsRepository;
import org.ideoholic.curium.repositories.OtherReceiptInfoRepository;
import org.ideoholic.curium.repositories.ReceiptinfoRepository;
import org.ideoholic.curium.repositories.StudentFeesStructureRepository;
import org.ideoholic.curium.repositories.StudentOtherFeesStructureRepository;
import org.ideoholic.curium.repositories.StudentRepository;
import org.ideoholic.curium.util.DataUtil;
import org.ideoholic.curium.util.HibernateUtil;
import org.ideoholic.curium.util.QueryUtil;
import org.ideoholic.curium.util.Session;
import org.ideoholic.curium.util.Session.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class feesDetailsDAO {
	
	@Autowired
    private FeesdetailsRepository feesDetailsRepo;
	@Autowired
    private FeescategoryRepository feescategoryRepository;
	@Autowired
    private ReceiptinfoRepository receiptinfoRepo;
	@Autowired
    private AcademicfeesstructureRepository academicfeesstructureRepo;
	@Autowired
	private QueryUtil queryUtil;
	@Autowired
	private StudentRepository studentRepo;
	@Autowired
	private StudentFeesStructureRepository studentFeesStructureRepo;
	@Autowired
	private OtherReceiptInfoRepository otherReceiptInfoRepo;
	@Autowired
	private StudentOtherFeesStructureRepository studentOtherFeesStructureRepository;
	
       

	 @Transactional
        public List<Feescategory> readListOfObjects() {
                
                List<Feescategory> results = new ArrayList<Feescategory>();
        try {
            
        	results = feescategoryRepository.findAll();
        }catch (Exception hibernateException) { 
        	log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            throw hibernateException;
        } 
        return results;
        }

        @Transactional
        public Feesdetails create(Feesdetails feesdetails) {
                try {
                	feesDetailsRepo.save(feesdetails); 
        }catch (Exception hibernateException) { 
        	log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            throw hibernateException;       
            } 

       
            return feesdetails;
        }

        @Transactional
        public Feesdetails readUniqueObject(Long feesDetailsid) {
        	     int feesDetailsId = feesDetailsid.intValue();
                 Feesdetails feesdetails = new Feesdetails();
                try {
                	 // Query query = session.createQuery("From Feesdetails as feesdetails where feesdetails.feesdetailsid=" + feesDetailsid);
                	feesdetails = feesDetailsRepo.findById(feesDetailsId).orElse(null);
                } catch (Exception hibernateException) { 
                	log.error(hibernateException.getMessage(), hibernateException);
                    hibernateException.printStackTrace();
                    throw hibernateException;
                }
                return feesdetails;
        }
        
        @Transactional
        public Receiptinfo readFeesDetails(Long feesDetailsid) {
        	 int feesDetailsId = feesDetailsid.intValue();
                 Receiptinfo feesdetails = new Receiptinfo();
                try {
                	 // Query query = session.createQuery("From Receiptinfo as feesdetails where feesdetails.receiptnumber=" + feesDetailsid);
                	feesdetails = receiptinfoRepo.findById(feesDetailsId).orElse(null);
                } catch (Exception hibernateException) { 
                	log.error(hibernateException.getMessage(), hibernateException);
                    hibernateException.printStackTrace();
                    throw hibernateException;
                }
                return feesdetails;
        }
        
        @Transactional
        public List<Feesdetails> readList(Long sid, String currentYear) {
        	int sId = sid.intValue();
                 
                 List<Feesdetails> results = new ArrayList<Feesdetails>();
                try {
                	// String query = "From Feesdetails as feesdetails where feesdetails.sid='"+sid+"' AND feesdetails.academicyear='"+currentYear+"'";
                    results = feesDetailsRepo.findByStudent_sidAndAcademicyear(sId, currentYear);
                } catch (Exception hibernateException) { 
                	log.error(hibernateException.getMessage(), hibernateException);
                    hibernateException.printStackTrace();
                    throw hibernateException;               
                    }
                return results;
        }

        @Transactional
        public String feesSum(long id, String currentYear) {
        	int Sid = (int)id;
                 
                String results = "";
                try {
                	 // Query query =  session.createQuery("select sum(grandtotal) From Feesdetails as feesdetails where feesdetails.sid=" + id +"and feesdetails.academicyear='"+currentYear+"'");
                    results =  feesDetailsRepo.sumGrandTotalBySidAndAcademicyear(Sid, currentYear);
                } catch (Exception hibernateException) { 
                	log.error(hibernateException.getMessage(), hibernateException);
                    hibernateException.printStackTrace();
                    throw hibernateException;
                }
                finally {
        			HibernateUtil.closeSession();
        		}
                return results;
        }

        public String dueAmount(long id, String currentYear) {
            int sid = (int)id;
            String paidFees = "";
            String totalFees = "";
            String dueFees = "";
    try {
    	            // String queryPaidFees = "select sum(grandtotal) from Feesdetails as feesdetails where feesdetails.sid=" + id +"and feesdetails.academicyear='"+currentYear+"'";
                    paidFees = feesDetailsRepo.getPaidFeesSum(sid, currentYear);
                    //String queryTotalFees = "select afs.totalfees from Academicfeesstructure as afs where afs.sid="+id+"and afs.academicyear='"+currentYear+"'";
                    totalFees = academicfeesstructureRepo.getTotalFees(sid, currentYear);
                    double TF = Double.parseDouble(totalFees);
                    double PF = Double.parseDouble(paidFees);
                    double Df = TF - PF;
                    dueFees = Double.toString(Df);
    } catch (Exception hibernateException) { 
    	log.error(hibernateException.getMessage(), hibernateException);
        hibernateException.printStackTrace();
        throw hibernateException;        }
    
    finally {
		HibernateUtil.closeSession();
	}
    return dueFees;
    }
        @Transactional
        public String feesDetailsSum(String queryMain) {
                 
                String results = "";
                try {
                    //this.session = HibernateUtil.getSessionFactory().openCurrentSession();

                                //Query query =  session.createQuery(queryMain);
                                //results =  (String) query.uniqueResult();
                                results =  queryUtil.runGivenQueryForSingleResult(queryMain,String.class).toString();
                                
                  
                } catch (Exception hibernateException) { 
                	log.error(hibernateException.getMessage(), hibernateException);
                    hibernateException.printStackTrace();
                    throw hibernateException;
                }
                return results;
        }
      
        @Transactional
        public String feesTotal(Integer sid, String currentYear) {
                String results = "";
                try {
                   
                	// Query queryTotalFees =  session.createQuery("select totalfees From Academicfeesstructure as afs where afs.sid=" + id +"and afs.academicyear='"+currentYear+"'");
                    results =  academicfeesstructureRepo.getTotalFees(sid, currentYear);          
                               
                                
                } catch (Exception hibernateException) { 
                	log.error(hibernateException.getMessage(), hibernateException);
                    hibernateException.printStackTrace();
                    throw hibernateException;
                }
                return results;
        }

        @Transactional
        public List<Object[]> readListOfStudents(int branchId) {
                List<Object[]> results = new ArrayList<Object[]>();

                try {
                       // Query q = session.createQuery("select s.sid, s.name, s.classstudying, s.studentexternalid, s.admissionnumber, p.fathersname from Student s JOIN Parents p ON s.sid=p.student.sid where s.sid in (select f.sid from Studentfeesstructure f where f.branchid = "+branchId+")").setCacheable(true).setCacheRegion("commonregion");
                	results= studentRepo.findStudentsByBranchId(branchId);
                } catch (Exception hibernateException) { 
                	log.error(hibernateException.getMessage(), hibernateException);
                    hibernateException.printStackTrace();
                    throw hibernateException;

                }
                return results;
        }
        
        @Transactional
        public List<Student> readListOfAllBranchStudents() {
            List<Student> results = new ArrayList<Student>();

            try {

                    //results = (List<Student>) session.createQuery("FROM Student s where s.archive = 0 and s.sid in (select f.sid from Studentfeesstructure f) ")
                    results = studentRepo.findAllActiveBranchStudents();

            } catch (Exception hibernateException) { 
            	log.error(hibernateException.getMessage(), hibernateException);
                hibernateException.printStackTrace();
                throw hibernateException;

            } 
            return results;
    }

        @Transactional
		public boolean cancelFeesReceipt(int receiptId, List<Feescollection> feesCollection, String updateReceiptDrAccount, String updateReceiptCrAccount, String cancelReceiptVoucher, String updateJournalDrAccount, String updateJournalCrAccount, String cancelJournalVoucher) {
			boolean result = false;

            try {
            	       // Query query = session.createQuery("update Receiptinfo set cancelreceipt=1 where receiptnumber="+receiptId);
            	        Receiptinfo receiptinfo = receiptinfoRepo.findById(receiptId).orElse(null);
				        if(receiptinfo != null) {
            	           receiptinfo.setCancelreceipt(1);
            	           receiptinfoRepo.save(receiptinfo);
						}
                    
                    for (Feescollection feescoll : feesCollection) {
                    	//Query queryStudentFS = session.createQuery("update Studentfeesstructure set feespaid=feespaid-"+feescoll.getAmountpaid()+" where sfsid="+feescoll.fetchSfsid());
                    	Studentfeesstructure studentfeesstructure = studentFeesStructureRepo.findById(feescoll.fetchSfsid()).orElse(null);
						if(studentfeesstructure != null) {
                    	   studentfeesstructure.setFeespaid(studentfeesstructure.getFeespaid() + feescoll.getAmountpaid());
                    	   studentFeesStructureRepo.save(studentfeesstructure);
						}
					}
                    
                    if(updateReceiptDrAccount!=null && updateReceiptCrAccount!=null && cancelReceiptVoucher != null && updateJournalDrAccount!=null && updateJournalCrAccount!=null && cancelJournalVoucher!=null) {
	                   // Query updateReceiptDr = session.createQuery(updateReceiptDrAccount);
	        			//updateReceiptDr.executeUpdate();
	        			queryUtil.runUpdateQuery(updateReceiptDrAccount);
	        			//Query updateReceiptCr = session.createQuery(updateReceiptCrAccount);
	        			//updateReceiptCr.executeUpdate();
	        			queryUtil.runUpdateQuery(updateReceiptCrAccount);
	        			//Query cancelReceiptVoucherQuery = session.createQuery(cancelReceiptVoucher);
	        			//cancelReceiptVoucherQuery.executeUpdate();
	        			queryUtil.runUpdateQuery(cancelJournalVoucher);
	        			//Query updateJournalDr = session.createQuery(updateJournalDrAccount);
	        			//updateJournalDr.executeUpdate();
	        			queryUtil.runUpdateQuery(updateJournalDrAccount);
	        			//Query updateJournalCr = session.createQuery(updateJournalCrAccount);
	        			//updateJournalCr.executeUpdate();
	        			queryUtil.runUpdateQuery(updateJournalCrAccount);
	        			//Query cancelJournalVoucherQuery = session.createQuery(cancelJournalVoucher);
	        			//cancelJournalVoucherQuery.executeUpdate();
	        			queryUtil.runUpdateQuery(cancelJournalVoucher);
                    }
                    
                    result = true;
            } catch (Exception hibernateException) { 
            	log.error(hibernateException.getMessage(), hibernateException);
                hibernateException.printStackTrace();
                throw hibernateException;
            }
            return result;
			
		}

        @Transactional
		public boolean undoFeesReceipt(int receiptId, List<Feescollection> feesCollection) {
			boolean result = false;

            try {
                    
            	        //Query query = session.createQuery("update Receiptinfo set cancelreceipt=0 where receiptnumber="+receiptId);
            	        Receiptinfo receiptinfo = receiptinfoRepo.findById(receiptId).orElse(null);
				        if(receiptinfo != null) {
            	           receiptinfo.setCancelreceipt(0);
            	           receiptinfoRepo.save(receiptinfo);
						}
                    
                    for (Feescollection feescoll : feesCollection) {
                    	//Query queryStudentFS = session.createQuery("update Studentfeesstructure set feespaid=feespaid+"+feescoll.getAmountpaid()+" where sfsid="+feescoll.fetchSfsid());
                    	Studentfeesstructure studentfeesstructure = studentFeesStructureRepo.findById(feescoll.fetchSfsid()).orElse(null);
						if(studentfeesstructure != null) {
                    	   studentfeesstructure.setFeespaid(studentfeesstructure.getFeespaid() - feescoll.getAmountpaid());
                    	   studentFeesStructureRepo.save(studentfeesstructure);
						}
					}
                    result = true;
            } catch (Exception hibernateException) { 
            	log.error(hibernateException.getMessage(), hibernateException);
                hibernateException.printStackTrace();
                throw hibernateException;

            }
            return result;
			
		}

        @Transactional
		public Otherreceiptinfo readOtherFeesDetails(Integer receiptNumber) {
			Otherreceiptinfo feesdetails = new Otherreceiptinfo();
           try {
              // Query query = session.createQuery("From Otherreceiptinfo as feesdetails where feesdetails.receiptnumber=" + feesDetailsid);
               feesdetails = otherReceiptInfoRepo.findById(receiptNumber).orElse(null);
           } catch (Exception hibernateException) { 
        	log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            throw hibernateException;
           }
           return feesdetails;
   }
		

        @Transactional
		public boolean cancelOtherFeesReceipt(int receiptId, List<Otherfeescollection> feesCollection, String updateReceiptDrAccount, String updateReceiptCrAccount, String cancelReceiptVoucher, String updateJournalDrAccount, String updateJournalCrAccount, String cancelJournalVoucher) {
			boolean result = false;

            try {
                    
	                   // Query query = session.createQuery("update Otherreceiptinfo set cancelreceipt=1 where receiptnumber="+receiptId);
	                    Otherreceiptinfo otherreceiptinfo = otherReceiptInfoRepo.findById(receiptId).orElse(null);
				        if(otherreceiptinfo != null) {
	                       otherreceiptinfo.setCancelreceipt(1);
	                       otherReceiptInfoRepo.save(otherreceiptinfo);
						}
                    
                    for (Otherfeescollection feescoll : feesCollection) {
                    	//Query queryStudentFS = session.createQuery("update Studentotherfeesstructure set feespaid=feespaid-"+feescoll.getAmountpaid()+" where sfsid="+feescoll.fetchSfsid());
                    	Studentotherfeesstructure studentotherfeesstructure = studentOtherFeesStructureRepository.findById(feescoll.fetchSfsid()).orElse(null);
						if(studentotherfeesstructure != null) {
                    	   studentotherfeesstructure.setFeespaid(studentotherfeesstructure.getFeespaid() - feescoll.getAmountpaid());
                    	   studentOtherFeesStructureRepository.save(studentotherfeesstructure);
					    }
                    	
					}
                    
                    if(updateReceiptDrAccount!=null && updateReceiptCrAccount!=null && cancelReceiptVoucher != null && updateJournalDrAccount!=null && updateJournalCrAccount!=null && cancelJournalVoucher!=null) {
	                   // Query updateReceiptDr = session.createQuery(updateReceiptDrAccount);
	        			//updateReceiptDr.executeUpdate();
	        			queryUtil.runUpdateQuery(updateReceiptDrAccount);
	        			//Query updateReceiptCr = session.createQuery(updateReceiptCrAccount);
	        			//updateReceiptCr.executeUpdate();
	        			queryUtil.runUpdateQuery(updateReceiptCrAccount);
	        			//Query cancelReceiptVoucherQuery = session.createQuery(cancelReceiptVoucher);
	        			//cancelReceiptVoucherQuery.executeUpdate();
	        			queryUtil.runUpdateQuery(cancelReceiptVoucher);
	        			//Query updateJournalDr = session.createQuery(updateJournalDrAccount);
	        			//updateJournalDr.executeUpdate();
	        			queryUtil.runUpdateQuery(updateJournalDrAccount);
	        			//Query updateJournalCr = session.createQuery(updateJournalCrAccount);
	        			//updateJournalCr.executeUpdate();
	        			queryUtil.runUpdateQuery(updateJournalCrAccount);
	        			//Query cancelJournalVoucherQuery = session.createQuery(cancelJournalVoucher);
	        			//cancelJournalVoucherQuery.executeUpdate();
	        			queryUtil.runUpdateQuery(cancelJournalVoucher);
                    }
                    result = true;
            } catch (Exception hibernateException) {
            	log.error(hibernateException.getMessage(), hibernateException);
                hibernateException.printStackTrace();

                throw hibernateException;
            }
            return result;
		}
		
		  public List<Object[]> readListOfStudentsOtherFees(int branchId) {
			  Session session = HibernateUtil.openCurrentSession();
	        	Transaction transaction = null;
              List<Object[]> results = new ArrayList<Object[]>();

              try {
                      // this.session =
                      // HibernateUtil.getSessionFactory().openCurrentSession();
                      transaction = session.beginTransaction();

						/*
						 * results = (List<Parents>) session.
						 * createQuery("FROM Parents p where p.student.sid in (select f.sid from Studentfeesstructure f where f.branchid = "
						 * +branchId+")") .list();
						 */
                      Query q = session.createQuery("select s.sid, s.name, s.classstudying, s.studentexternalid, s.admissionnumber, p.fathersname from Student s JOIN Parents p ON s.sid=p.student.sid where s.sid in (select f.sid from Studentotherfeesstructure f where f.branchid = "+branchId+")").setCacheable(true).setCacheRegion("commonregion");
                      results= (List<Object[]>)q.list();
                      transaction.commit();

              } catch (Exception hibernateException) { 
              	log.error(hibernateException.getMessage(), hibernateException);
                hibernateException.printStackTrace();
                throw hibernateException;
              } finally {
          			HibernateUtil.closeSession();
                      return results;
              }
      }

		public void stampOtherFees(Integer stdIds, String setYear, CreateStudentDto dto, String currentAcademicYear, String branchId, String userId) {

		if(currentAcademicYear!=null){
			String[] feesCategoryIds = dto.getOtherFeesCategory();
			if(feesCategoryIds!=null) {

				String[] studentIds = {stdIds.toString()};
				if(studentIds!=null){
					Academicotherfeesstructure academicfessstructure = new Academicotherfeesstructure();
					List<Academicotherfeesstructure> listOfacademicfessstructure = new ArrayList<Academicotherfeesstructure>();
					List<Studentotherfeesstructure> listOfstudentfeesstructure = new ArrayList<Studentotherfeesstructure>();

					String feesTotalAmount = dto.getOtherFeesTotalAmount();
					Long grandTotal = 0l;

					String[] feesAmount = dto.getOtherFeesAmount();
					String[] concession = dto.getOtherFeesConcession();
					String[] totalInstallments = dto.getOtherTotalInstallments();

					List<Integer> ids = new ArrayList();
					listOfacademicfessstructure.clear();
					for (String id : studentIds) {
						System.out.println("id" + id);
						academicfessstructure = new Academicotherfeesstructure();
						academicfessstructure.setSid(Integer.valueOf(id));
						academicfessstructure.setAcademicyear(setYear);
						academicfessstructure.setUserid(Integer.parseInt(userId));
						academicfessstructure.setTotalfees(feesTotalAmount);
						grandTotal = grandTotal + Long.parseLong(academicfessstructure.getTotalfees());
						academicfessstructure.setBranchid(Integer.parseInt(branchId));
						academicfessstructure.setUserid(Integer.parseInt(userId));

						listOfacademicfessstructure.add(academicfessstructure);
						// ids.add(Integer.valueOf(id));

					}

					for (String id : studentIds) {
						Student student = new StudentDetailsDAO().readUniqueObject(DataUtil.parseInt(id));
						for(int i=0; i < feesCategoryIds.length ; i++){
							String[] feesCategoryIdsdiv = 	feesCategoryIds[i].split("--");
							
							Studentotherfeesstructure studentfeesstructure = new Studentotherfeesstructure();
							OtherFeecategory feescategory = new OtherFeecategory();
							studentfeesstructure.setStudent(student);
							feescategory.setIdfeescategory(Integer.parseInt(feesCategoryIdsdiv[0]));
							studentfeesstructure.setOtherfeescategory(feescategory);
							studentfeesstructure.setFeesamount(Long.parseLong(feesAmount[Integer.parseInt(feesCategoryIdsdiv[1])]));
							studentfeesstructure.setFeespaid((long) 0);
							studentfeesstructure.setWaiveoff((long) 0);
							studentfeesstructure.setTotalinstallment(Integer.parseInt(totalInstallments[Integer.parseInt(feesCategoryIdsdiv[1])]));
							studentfeesstructure.setAcademicyear(setYear);
							studentfeesstructure.setBranchid(Integer.parseInt(branchId));
							studentfeesstructure.setUserid(Integer.parseInt(userId));
							studentfeesstructure.setConcession(Integer.parseInt(concession[Integer.parseInt(feesCategoryIdsdiv[1])]));
							listOfstudentfeesstructure.add(studentfeesstructure);
						}



					}
					new StampFeesDAO().addotherStampFees(listOfacademicfessstructure,currentAcademicYear,listOfstudentfeesstructure);

				}
			}
		}
	}
}

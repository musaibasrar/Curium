package org.ideoholic.curium.model.feesdetails.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.query.Query;
import org.ideoholic.curium.model.feescategory.dto.Feescategory;
import org.ideoholic.curium.model.feescollection.dto.Feescollection;
import org.ideoholic.curium.model.feescollection.dto.Otherfeescollection;
import org.ideoholic.curium.model.feescollection.dto.Otherreceiptinfo;
import org.ideoholic.curium.model.feescollection.dto.Receiptinfo;
import org.ideoholic.curium.model.feesdetails.dto.Feesdetails;
import org.ideoholic.curium.model.student.dto.Student;
import org.ideoholic.curium.repositories.FeescategoryRepository;
import org.ideoholic.curium.repositories.FeesdetailsRepository;
import org.ideoholic.curium.util.HibernateUtil;
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

        public Feesdetails readUniqueObject(Long feesDetailsid) {
        	Session session = HibernateUtil.openCurrentSession();
        	Transaction transaction = null;
                 Feesdetails feesdetails = new Feesdetails();
                try {
                    //this.session = HibernateUtil.getSessionFactory().openCurrentSession();

                    transaction = session.beginTransaction();
                    Query query = session.createQuery("From Feesdetails as feesdetails where feesdetails.feesdetailsid=" + feesDetailsid);
                    feesdetails = (Feesdetails) query.uniqueResult();
                    transaction.commit();
                } catch (Exception hibernateException) { 
                	log.error(hibernateException.getMessage(), hibernateException);
                    hibernateException.printStackTrace();
                    throw hibernateException;
                }finally {
        			HibernateUtil.closeSession();
        		}
                return feesdetails;
        }
        
        public Receiptinfo readFeesDetails(Long feesDetailsid) {
        	Session session =HibernateUtil.openCurrentSession();
        	Transaction transaction = null;
                 Receiptinfo feesdetails = new Receiptinfo();
                try {
                    //this.session = HibernateUtil.getSessionFactory().openCurrentSession();

                    transaction = session.beginTransaction();
                    Query query = session.createQuery("From Receiptinfo as feesdetails where feesdetails.receiptnumber=" + feesDetailsid);
                    feesdetails = (Receiptinfo) query.uniqueResult();
                    transaction.commit();
                } catch (Exception hibernateException) { 
                	log.error(hibernateException.getMessage(), hibernateException);
                    hibernateException.printStackTrace();
                    throw hibernateException;
                }finally {
        			HibernateUtil.closeSession();
        		}
                return feesdetails;
        }
        
        @SuppressWarnings("unchecked")
        public List<Feesdetails> readList(Long sid, String currentYear) {
        	Session session = HibernateUtil.openCurrentSession();
        	Transaction transaction = null;
                 
                 List<Feesdetails> results = new ArrayList<Feesdetails>();
                try {
                    transaction = session.beginTransaction();
                    String query = "From Feesdetails as feesdetails where feesdetails.sid='"+sid+"' AND feesdetails.academicyear='"+currentYear+"'";
                                results = (List<Feesdetails>) session.createQuery(query).list();
                                
                                
                  
                    transaction.commit();
                } catch (Exception hibernateException) { 
                	log.error(hibernateException.getMessage(), hibernateException);
                    hibernateException.printStackTrace();
                    throw hibernateException;                }finally {
        			HibernateUtil.closeSession();
        		}
                return results;
        }

        public String feesSum(long id, String currentYear) {
        	Session session = HibernateUtil.openCurrentSession();
        	Transaction transaction = null;
                 
                String results = "";
                try {
                    //this.session = HibernateUtil.getSessionFactory().openCurrentSession();

                    transaction = session.beginTransaction();
                    
                                Query query =  session.createQuery("select sum(grandtotal) From Feesdetails as feesdetails where feesdetails.sid=" + id +"and feesdetails.academicyear='"+currentYear+"'");
                                results =  (String) query.uniqueResult();
                                /*
                                Query queryTotalFees =  session.createQuery("select totalfees From Academicfessstructure as afs where afs.sid=" + id +"and afs.academicyear="+currentYear);
                                results =  (String) queryTotalFees.uniqueResult();*/
                                
                  
                    transaction.commit();
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
        	Session session = HibernateUtil.openCurrentSession();
        	Transaction transaction = null;
                
                String paidFees = "";
                String totalFees = "";
                String dueFees = "";
        try {
            //this.session = HibernateUtil.getSessionFactory().openCurrentSession();

            transaction = session.beginTransaction();
            
            String queryPaidFees = "select sum(grandtotal) from Feesdetails as feesdetails where feesdetails.sid=" + id +"and feesdetails.academicyear='"+currentYear+"'";
            String queryTotalFees = "select afs.totalfees from Academicfeesstructure as afs where afs.sid="+id+"and afs.academicyear='"+currentYear+"'";
                        Query queryPF =  session.createQuery(queryPaidFees);
                        paidFees =  (String) queryPF.uniqueResult();
                        Query queryTF =  session.createQuery(queryTotalFees);
                        totalFees =  (String) queryTF.uniqueResult();
                        transaction.commit();
        } catch (Exception hibernateException) { 
        	log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            throw hibernateException;        }
        
        finally {
			HibernateUtil.closeSession();
		}
        return dueFees;
        }

        public String feesDetailsSum(String queryMain) {
        	Session session = HibernateUtil.openCurrentSession();
        	Transaction transaction = null;
                 
                String results = "";
                try {
                    //this.session = HibernateUtil.getSessionFactory().openCurrentSession();

                    transaction = session.beginTransaction();
                                Query query =  session.createQuery(queryMain);
                                results =  (String) query.uniqueResult();
                                
                  
                    transaction.commit();
                } catch (Exception hibernateException) { 
                	log.error(hibernateException.getMessage(), hibernateException);
                    hibernateException.printStackTrace();
                    throw hibernateException;
                }finally {
        			HibernateUtil.closeSession();
        		}
                return results;
        }

        public String feesTotal(long id, String currentYear) {
        	Session session = HibernateUtil.openCurrentSession(); 
        	Transaction transaction = null;
                 
                String results = "";
                try {
                    //this.session = HibernateUtil.getSessionFactory().openCurrentSession();

                    transaction = session.beginTransaction();
                   
                                
                                Query queryTotalFees =  session.createQuery("select totalfees From Academicfeesstructure as afs where afs.sid=" + id +"and afs.academicyear='"+currentYear+"'");
                                results =  (String) queryTotalFees.uniqueResult();
                                
                  
                    transaction.commit();
                } catch (Exception hibernateException) { 
                	log.error(hibernateException.getMessage(), hibernateException);
                    hibernateException.printStackTrace();
                    throw hibernateException;
                }finally {
        			HibernateUtil.closeSession();
        		}
                return results;
        }

        public List<Object[]> readListOfStudents(int branchId) {
        	Session session = HibernateUtil.openCurrentSession(); 
        	Transaction transaction = null;
                List<Object[]> results = new ArrayList<Object[]>();

                try {
                        // this.session =
                        // HibernateUtil.getSessionFactory().openCurrentSession();
                        transaction = session.beginTransaction();

						/*
						 * results = (List<Parents>) session.
						 * createQuery("FROM Parents p where p.Student.sid in (select f.sid from Studentfeesstructure f where f.branchid = "
						 * +branchId+")") .list();
						 */
                        Query q = session.createQuery("select s.sid, s.name, s.classstudying, s.studentexternalid, s.admissionnumber, p.fathersname from Student s JOIN Parents p ON s.sid=p.student.sid where s.sid in (select f.sid from Studentfeesstructure f where f.branchid = "+branchId+")").setCacheable(true).setCacheRegion("commonregion");
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
        
        
        public List<Student> readListOfAllBranchStudents() {
        	Session session = HibernateUtil.openCurrentSession();
        	Transaction transaction = null;
            List<Student> results = new ArrayList<Student>();

            try {
                    transaction = session.beginTransaction();

                    results = (List<Student>) session.createQuery("FROM Student s where s.archive = 0 and s.sid in (select f.sid from Studentfeesstructure f) ")
                                    .list();
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

		public boolean cancelFeesReceipt(int receiptId, List<Feescollection> feesCollection, String updateReceiptDrAccount, String updateReceiptCrAccount, String cancelReceiptVoucher, String updateJournalDrAccount, String updateJournalCrAccount, String cancelJournalVoucher) {
			Session session = HibernateUtil.openCurrentSession();
        	Transaction transaction = null;
			boolean result = false;

            try {
                    transaction = session.beginTransaction();
                    
	                    Query query = session.createQuery("update Receiptinfo set cancelreceipt=1 where receiptnumber="+receiptId);
	                    query.executeUpdate();
                    
                    for (Feescollection feescoll : feesCollection) {
                    	Query queryStudentFS = session.createQuery("update Studentfeesstructure set feespaid=feespaid-"+feescoll.getAmountpaid()+" where sfsid="+feescoll.fetchSfsid());
                    	queryStudentFS.executeUpdate();
					}
                    
                    if(updateReceiptDrAccount!=null && updateReceiptCrAccount!=null && cancelReceiptVoucher != null && updateJournalDrAccount!=null && updateJournalCrAccount!=null && cancelJournalVoucher!=null) {
	                    Query updateReceiptDr = session.createQuery(updateReceiptDrAccount);
	        			updateReceiptDr.executeUpdate();
	        			Query updateReceiptCr = session.createQuery(updateReceiptCrAccount);
	        			updateReceiptCr.executeUpdate();
	        			Query cancelReceiptVoucherQuery = session.createQuery(cancelReceiptVoucher);
	        			cancelReceiptVoucherQuery.executeUpdate();
	        			
	        			Query updateJournalDr = session.createQuery(updateJournalDrAccount);
	        			updateJournalDr.executeUpdate();
	        			Query updateJournalCr = session.createQuery(updateJournalCrAccount);
	        			updateJournalCr.executeUpdate();
	        			Query cancelJournalVoucherQuery = session.createQuery(cancelJournalVoucher);
	        			cancelJournalVoucherQuery.executeUpdate();
                    }
                    
                    transaction.commit();
                    result = true;
            } catch (Exception hibernateException) { 
            	log.error(hibernateException.getMessage(), hibernateException);
                hibernateException.printStackTrace();
                throw hibernateException;
            }finally {
    			HibernateUtil.closeSession();
    		}
            return result;
			
		}

		public boolean undoFeesReceipt(int receiptId, List<Feescollection> feesCollection) {
			Session session = HibernateUtil.openCurrentSession();
        	Transaction transaction = null;
			
			boolean result = false;

            try {
                    transaction = session.beginTransaction();
                    
	                    Query query = session.createQuery("update Receiptinfo set cancelreceipt=0 where receiptnumber="+receiptId);
	                    query.executeUpdate();
                    
                    for (Feescollection feescoll : feesCollection) {
                    	Query queryStudentFS = session.createQuery("update Studentfeesstructure set feespaid=feespaid+"+feescoll.getAmountpaid()+" where sfsid="+feescoll.fetchSfsid());
                    	queryStudentFS.executeUpdate();
					}
                    
                    transaction.commit();
                    result = true;
            } catch (Exception hibernateException) { 
            	log.error(hibernateException.getMessage(), hibernateException);
                hibernateException.printStackTrace();
                throw hibernateException;

            }finally {
    			HibernateUtil.closeSession();
    		}
            return result;
			
		}

		public Otherreceiptinfo readOtherFeesDetails(long feesDetailsid) {
			Session session = HibernateUtil.openCurrentSession();
        	Transaction transaction = null;
			Otherreceiptinfo feesdetails = new Otherreceiptinfo();
           try {
               //this.session = HibernateUtil.getSessionFactory().openCurrentSession();

               transaction = session.beginTransaction();
               Query query = session.createQuery("From Otherreceiptinfo as feesdetails where feesdetails.receiptnumber=" + feesDetailsid);
               feesdetails = (Otherreceiptinfo) query.uniqueResult();
               transaction.commit();
           } catch (Exception hibernateException) { 
        	log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            throw hibernateException;
           }finally {
   			HibernateUtil.closeSession();
   		}
           return feesdetails;
   }
		

		public boolean cancelOtherFeesReceipt(int receiptId, List<Otherfeescollection> feesCollection, String updateReceiptDrAccount, String updateReceiptCrAccount, String cancelReceiptVoucher, String updateJournalDrAccount, String updateJournalCrAccount, String cancelJournalVoucher) {
			Session session = HibernateUtil.openCurrentSession(); 
        	Transaction transaction = null;
			boolean result = false;

            try {
                    transaction = session.beginTransaction();
                    
	                    Query query = session.createQuery("update Otherreceiptinfo set cancelreceipt=1 where receiptnumber="+receiptId);
	                    query.executeUpdate();
                    
                    for (Otherfeescollection feescoll : feesCollection) {
                    	Query queryStudentFS = session.createQuery("update Studentotherfeesstructure set feespaid=feespaid-"+feescoll.getAmountpaid()+" where sfsid="+feescoll.fetchSfsid());
                    	queryStudentFS.executeUpdate();
					}
                    
                    if(updateReceiptDrAccount!=null && updateReceiptCrAccount!=null && cancelReceiptVoucher != null && updateJournalDrAccount!=null && updateJournalCrAccount!=null && cancelJournalVoucher!=null) {
	                    Query updateReceiptDr = session.createQuery(updateReceiptDrAccount);
	        			updateReceiptDr.executeUpdate();
	        			Query updateReceiptCr = session.createQuery(updateReceiptCrAccount);
	        			updateReceiptCr.executeUpdate();
	        			Query cancelReceiptVoucherQuery = session.createQuery(cancelReceiptVoucher);
	        			cancelReceiptVoucherQuery.executeUpdate();
	        			
	        			Query updateJournalDr = session.createQuery(updateJournalDrAccount);
	        			updateJournalDr.executeUpdate();
	        			Query updateJournalCr = session.createQuery(updateJournalCrAccount);
	        			updateJournalCr.executeUpdate();
	        			Query cancelJournalVoucherQuery = session.createQuery(cancelJournalVoucher);
	        			cancelJournalVoucherQuery.executeUpdate();
                    }
                    
                    transaction.commit();
                    result = true;
            } catch (Exception hibernateException) { 
            	log.error(hibernateException.getMessage(), hibernateException);
                hibernateException.printStackTrace();
                throw hibernateException;

            }finally {
    			HibernateUtil.closeSession();
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
						 * createQuery("FROM Parents p where p.Student.sid in (select f.sid from Studentfeesstructure f where f.branchid = "
						 * +branchId+")") .list();
						 */
                      Query q = session.createQuery("select s.sid, s.name, s.classstudying, s.studentexternalid, s.admissionnumber, p.fathersname from Student s JOIN Parents p ON s.sid=p.Student.sid where s.sid in (select f.sid from Studentotherfeesstructure f where f.branchid = "+branchId+")").setCacheable(true).setCacheRegion("commonregion");
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
}

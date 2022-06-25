package org.ideoholic.curium.model.feesdetails.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.ideoholic.curium.util.Session;
import org.hibernate.SessionFactory;
import org.ideoholic.curium.util.Session.Transaction;
import org.hibernate.query.Query;

import org.ideoholic.curium.model.feescategory.dto.Feescategory;
import org.ideoholic.curium.model.feescollection.dto.Feescollection;
import org.ideoholic.curium.model.feescollection.dto.Receiptinfo;
import org.ideoholic.curium.model.feesdetails.dto.Feesdetails;
import org.ideoholic.curium.model.parents.dto.Parents;
import org.ideoholic.curium.model.student.dto.Student;
import org.ideoholic.curium.util.HibernateUtil;

public class feesDetailsDAO {
        Session session = null;
    /**
     * * Hibernate Session Variable
     */
    Transaction transaction = null;
    /**
     * * Hibernate Transaction Variable
     */
  
    SessionFactory sessionFactory;
    
    private static final Logger logger = LogManager.getLogger(feesDetailsDAO.class);

        public feesDetailsDAO() {
                session = HibernateUtil.openCurrentSession();
        }

        @SuppressWarnings({ "finally", "unchecked" })
        public List<Feescategory> readListOfObjects() {
                
                List<Feescategory> results = new ArrayList<Feescategory>();
        try {
            
            transaction = session.beginTransaction();
            results = (List<Feescategory>) session.createQuery("From Feescategory").list();
            transaction.commit();
        } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
            
            hibernateException.printStackTrace();
        } finally {
    			HibernateUtil.closeSession();
            return results;
        }
        }

        @SuppressWarnings("finally")
        public Feesdetails create(Feesdetails feesdetails) {
                try {
            //this.session = sessionFactory.openCurrentSession();
            transaction = session.beginTransaction();
            session.save(feesdetails);


            transaction.commit();
            
        } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
            
            hibernateException.printStackTrace();
        } finally {
    			HibernateUtil.closeSession();
            return feesdetails;
        }
        }

        public Feesdetails readUniqueObject(Long feesDetailsid) {
                 Feesdetails feesdetails = new Feesdetails();
                try {
                    //this.session = HibernateUtil.getSessionFactory().openCurrentSession();

                    transaction = session.beginTransaction();
                    Query query = session.createQuery("From Feesdetails as feesdetails where feesdetails.feesdetailsid=" + feesDetailsid);
                    feesdetails = (Feesdetails) query.uniqueResult();
                    transaction.commit();
                } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
                    
                    hibernateException.printStackTrace();
                }finally {
        			HibernateUtil.closeSession();
        		}
                return feesdetails;
        }
        
        public Receiptinfo readFeesDetails(Long feesDetailsid) {
                 Receiptinfo feesdetails = new Receiptinfo();
                try {
                    //this.session = HibernateUtil.getSessionFactory().openCurrentSession();

                    transaction = session.beginTransaction();
                    Query query = session.createQuery("From Receiptinfo as feesdetails where feesdetails.receiptnumber=" + feesDetailsid);
                    feesdetails = (Receiptinfo) query.uniqueResult();
                    transaction.commit();
                } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
                    
                    hibernateException.printStackTrace();
                }finally {
        			HibernateUtil.closeSession();
        		}
                return feesdetails;
        }
        
        @SuppressWarnings("unchecked")
        public List<Feesdetails> readList(Long sid, String currentYear) {
                 
                 List<Feesdetails> results = new ArrayList<Feesdetails>();
                try {
                    transaction = session.beginTransaction();
                    String query = "From Feesdetails as feesdetails where feesdetails.sid='"+sid+"' AND feesdetails.academicyear='"+currentYear+"'";
                                results = (List<Feesdetails>) session.createQuery(query).list();
                                
                                
                  
                    transaction.commit();
                } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
                    
                    hibernateException.printStackTrace();
                }finally {
        			HibernateUtil.closeSession();
        		}
                return results;
        }

        public String feesSum(long id, String currentYear) {
                 
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
                } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
                    
                    hibernateException.printStackTrace();
                }
                finally {
        			HibernateUtil.closeSession();
        		}
                return results;
        }

        public String dueAmount(long id, String currentYear) {
                
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
        } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
            
            hibernateException.printStackTrace();
        }
        
        finally {
			HibernateUtil.closeSession();
		}
        return dueFees;
        }

        public String feesDetailsSum(String queryMain) {
                 
                String results = "";
                try {
                    //this.session = HibernateUtil.getSessionFactory().openCurrentSession();

                    transaction = session.beginTransaction();
                                Query query =  session.createQuery(queryMain);
                                results =  (String) query.uniqueResult();
                                
                  
                    transaction.commit();
                } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
                    
                    hibernateException.printStackTrace();
                }finally {
        			HibernateUtil.closeSession();
        		}
                return results;
        }

        public String feesTotal(long id, String currentYear) {
                 
                String results = "";
                try {
                    //this.session = HibernateUtil.getSessionFactory().openCurrentSession();

                    transaction = session.beginTransaction();
                   
                                
                                Query queryTotalFees =  session.createQuery("select totalfees From Academicfeesstructure as afs where afs.sid=" + id +"and afs.academicyear='"+currentYear+"'");
                                results =  (String) queryTotalFees.uniqueResult();
                                
                  
                    transaction.commit();
                } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
                    
                    hibernateException.printStackTrace();
                }finally {
        			HibernateUtil.closeSession();
        		}
                return results;
        }

        public List<Parents> readListOfStudents(int branchId) {
                List<Parents> results = new ArrayList<Parents>();

                try {
                        // this.session =
                        // HibernateUtil.getSessionFactory().openCurrentSession();
                        transaction = session.beginTransaction();

                        results = (List<Parents>) session.createQuery("FROM Parents p where p.Student.sid in (select f.sid from Studentfeesstructure f where f.branchid = "+branchId+")")
                                        .list();
                        transaction.commit();

                } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
                        
                        hibernateException.printStackTrace();

                } finally {
            			HibernateUtil.closeSession();
                        return results;
                }
        }
        
        
        public List<Student> readListOfAllBranchStudents() {
            List<Student> results = new ArrayList<Student>();

            try {
                    transaction = session.beginTransaction();

                    results = (List<Student>) session.createQuery("FROM Student s where s.archive = 0 and s.sid in (select f.sid from Studentfeesstructure f) ")
                                    .list();
                    transaction.commit();

            } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
                    
                    hibernateException.printStackTrace();

            } finally {
        			HibernateUtil.closeSession();
                    return results;
            }
    }

		public boolean cancelFeesReceipt(int receiptId, List<Feescollection> feesCollection, String updateReceiptDrAccount, String updateReceiptCrAccount, String cancelReceiptVoucher, String updateJournalDrAccount, String updateJournalCrAccount, String cancelJournalVoucher) {
			
			boolean result = false;

            try {
                    transaction = session.beginTransaction();
                    
	                    Query query = session.createQuery("update Receiptinfo set cancelreceipt=1 where receiptnumber="+receiptId);
	                    query.executeUpdate();
                    
                    for (Feescollection feescoll : feesCollection) {
                    	Query queryStudentFS = session.createQuery("update Studentfeesstructure set feespaid=feespaid-"+feescoll.getAmountpaid()+" where sfsid="+feescoll.getSfsid());
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
            } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
                    
                    hibernateException.printStackTrace();

            }finally {
    			HibernateUtil.closeSession();
    		}
            return result;
			
		}

		public boolean undoFeesReceipt(int receiptId, List<Feescollection> feesCollection) {
			
			boolean result = false;

            try {
                    transaction = session.beginTransaction();
                    
	                    Query query = session.createQuery("update Receiptinfo set cancelreceipt=0 where receiptnumber="+receiptId);
	                    query.executeUpdate();
                    
                    for (Feescollection feescoll : feesCollection) {
                    	Query queryStudentFS = session.createQuery("update Studentfeesstructure set feespaid=feespaid+"+feescoll.getAmountpaid()+" where sfsid="+feescoll.getSfsid());
                    	queryStudentFS.executeUpdate();
					}
                    
                    transaction.commit();
                    result = true;
            } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
                    
                    hibernateException.printStackTrace();

            }finally {
    			HibernateUtil.closeSession();
    		}
            return result;
			
		}

}

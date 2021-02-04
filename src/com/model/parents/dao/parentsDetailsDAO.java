package com.model.parents.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.util.Session;
import org.hibernate.SessionFactory;
import com.util.Session.Transaction;
import org.hibernate.query.Query;

import com.model.mess.card.dto.Card;
import com.model.parents.dto.Parents;
import com.model.stampfees.dto.Academicfeesstructure;
import com.model.student.dto.Studentfeesstructure;
import com.util.DateUtil;
import com.util.HibernateUtil;

public class parentsDetailsDAO {
	 Session session = null;
	    /**
	     * * Hibernate Session Variable
	     */
	    Transaction transaction = null;
	    /**
	     * * Hibernate Transaction Variable
	     */
	    Transaction transaction1;
	    SessionFactory sessionFactory;
	    
	    private static final Logger logger = LogManager.getLogger(parentsDetailsDAO.class);
	    
	    public parentsDetailsDAO() {
	    	   session = HibernateUtil.openCurrentSession();
		}

	@SuppressWarnings("finally")
	public Parents create(Parents parents) {
		 try {
	            //this.session = sessionFactory.openCurrentSession();
	            transaction = session.beginTransaction();
	            session.save(parents);
	            Query query = session.createQuery("update Student set studentexternalid = concat(studentexternalid,"+parents.getStudent().getSid()+") where sid="+parents.getStudent().getSid());
	            query.executeUpdate();
	            transaction.commit();
	           
	        } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
	            
	            hibernateException.printStackTrace();
	        } finally {
	    			HibernateUtil.closeSession();
	            return parents;
	        }
	}

	public Parents readUniqueObject(long id) {
		Parents parents = new Parents();
        try {
            //this.session = HibernateUtil.getSessionFactory().openCurrentSession();

            transaction = session.beginTransaction();
            Query query = session.createQuery("from Parents as parents where parents.Student.sid=" + id);
            parents = (Parents) query.uniqueResult();
            transaction.commit();
        } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
            
            hibernateException.printStackTrace();
        }finally {
			HibernateUtil.closeSession();
		}
        return parents;
	}

	@SuppressWarnings("finally")
	public Parents update(Parents parents) {
		
		try {
            //this.session = sessionFactory.openCurrentSession();
            transaction = session.beginTransaction();
            session.update(parents);
            transaction.commit();
            System.out.println("in update parents");
        } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
            
            hibernateException.printStackTrace();
        } finally {
    			HibernateUtil.closeSession();
            return parents;
        }
	}

	public Parents create(Parents parents, Academicfeesstructure academicFessStructure,
			List<Studentfeesstructure> studentFeesStructure, String currentYear, Card cardDetails) {
		 try {
	            //this.session = sessionFactory.openCurrentSession();
	            transaction = session.beginTransaction();
	            session.save(parents);
	            Query query = session.createQuery("update Student set studentexternalid = concat(studentexternalid,"+parents.getStudent().getSid()+") where sid="+parents.getStudent().getSid());
	            query.executeUpdate();
	            
	            
	            //academic fees structure
	            academicFessStructure.setSid(parents.getStudent().getSid());

				Query queryacademicFessStructure = session.createQuery("from Academicfeesstructure afs where afs.sid = '"+academicFessStructure.getSid()+"' and afs.academicyear = '"+currentYear+"'");
				Academicfeesstructure feesStructure = (Academicfeesstructure) queryacademicFessStructure.uniqueResult();
				if(feesStructure != null){
					Query queryUpdate = session
							.createQuery("update Academicfeesstructure set totalfees = '"+academicFessStructure.getTotalfees()+"'  where sid = '"+academicFessStructure.getSid()+"' and academicyear = '"+currentYear+"'");
					
					queryUpdate.executeUpdate();
				}else if(feesStructure == null){
					session.save(academicFessStructure);
				}
				
				//end academic fees structure
	            
				//Fees Structure
		            for (Studentfeesstructure studentfeesstr : studentFeesStructure) {
						studentfeesstr.setSid(parents.getStudent().getSid());
						
						Query querystudentfeesstr = session.createQuery("from Studentfeesstructure as sfs where sfs.sid = '"+studentfeesstr.getSid()+"' and sfs.Feescategory.idfeescategory = '"+studentfeesstr.getIdfeescategory()+"' and sfs.academicyear = '"+currentYear+"'");
						Studentfeesstructure studentFeesStr = (Studentfeesstructure) querystudentfeesstr.uniqueResult();
						if(studentFeesStr != null){
							
							Query queryUpdate = session
									.createQuery("update Studentfeesstructure set idfeescategory = '"+studentfeesstr.getIdfeescategory()+"',feesamount = '"+studentfeesstr.getFeesamount()+"'  where sid = '"+studentfeesstr.getSid()+"' and academicyear = '"+currentYear+"'");
							
							
							queryUpdate.executeUpdate();
						}else if(studentFeesStr == null){
							session.save(studentfeesstr);
						}
				
					}
				
				//End Fees Structure
		            
		            // Mess Card
		            	cardDetails.setSid(parents.getStudent().getSid());
		            	Query queryCard = session.createQuery("from Card where sid = "+parents.getStudent().getSid());
		            	Card card = (Card) queryCard.uniqueResult();
		            	if(card!=null) {
		            		Query queryUpdate = session.createQuery("update Card set validfrom = '"+DateUtil.dateParseryyyymmdd(card.getValidfrom())+"', validto = '"+DateUtil.dateParseryyyymmdd(card.getValidto())+"' where sid="+card.getSid());
		            		queryUpdate.executeUpdate();
		            	}else {
		            		session.save(cardDetails);
		            	}
		            // End Mess Card
	            
	            transaction.commit();
	           
	        } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
	            
	            hibernateException.printStackTrace();
	        } finally {
	    			HibernateUtil.closeSession();
	            return parents;
	        }
	}

}

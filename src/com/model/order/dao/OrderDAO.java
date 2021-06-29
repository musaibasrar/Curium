package com.model.order.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;

import com.model.branch.dto.Branch;
import com.model.order.dto.Books;
import com.model.order.dto.Ordersdetails;
import com.model.order.dto.Orderssummary;
import com.util.HibernateUtil;
import com.util.Session;
import com.util.Session.Transaction;

public class OrderDAO {

	Session session;
	Transaction transaction;
	
	public OrderDAO() {
		session = HibernateUtil.openCurrentSession();
	}

    public List<Books> readBooks() {
       
            List<Books> booksList = new ArrayList<Books>();
            try {
                    transaction = session.beginTransaction();
                    booksList = session.createQuery("from Books").list();
                    transaction.commit();
            } catch (Exception e) {transaction.rollback();
                    e.printStackTrace();
            }finally{
                    HibernateUtil.closeSession();
            }
            return booksList;
         }



    public boolean addBooks(Books book) {
        boolean result = false;
        
        try {
            // this.session = sessionFactory.openCurrentSession();
            transaction = session.beginTransaction();
            session.save(book);
            transaction.commit();
            result = true;
    } catch (HibernateException hibernateException) {transaction.rollback();
            hibernateException.printStackTrace();
    } finally {
            HibernateUtil.closeSession();
    }
        return result;
}



    public boolean updateMultipleBooks(List<Books> booksList) {
        boolean result = false;
        try {
            transaction = session.beginTransaction();
            for (Books book : booksList) {
                session.update(book);
            }
            transaction.commit();
            result = true;
        } catch (HibernateException hibernateException) {transaction.rollback();
            hibernateException.printStackTrace();
        }finally {
			HibernateUtil.closeSession();
		}
        return result;
    }



    public boolean deleteMultipleBooks(List<Integer> ids) {
        
        boolean result = false;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("delete from Books where id IN (:ids)");
            query.setParameterList("ids", ids);
            query.executeUpdate();
            transaction.commit();
            result = true;
        } catch (HibernateException hibernateException) {transaction.rollback();
            hibernateException.printStackTrace();
        }finally {
			HibernateUtil.closeSession();
		}
        return result;
    }



    public boolean confirmOrderSummary(Orderssummary orderSummary, List<Ordersdetails> orderDetailsList, String currentAcademicYear) {
        boolean result = false;
        
        try {
            transaction = session.beginTransaction();
            session.save(orderSummary);
            Integer orderSum = orderSummary.getIdorders();
            orderSummary.setInvoicenumber("BIE"+Integer.toString(orderSum)+"/"+currentAcademicYear.replace("/", "-"));
            session.update(orderSummary);
            
            for (Ordersdetails ordersdetails : orderDetailsList) {
                ordersdetails.setOrderssummaryid(orderSum);
                session.save(ordersdetails);
            }
            transaction.commit();
            result = true;
    } catch (HibernateException hibernateException) {transaction.rollback();
            hibernateException.printStackTrace();
    } finally {
            HibernateUtil.closeSession();
    }
        return result;
}



    public List<Orderssummary> viewOrder() {
        
        List<Orderssummary> booksList = new ArrayList<Orderssummary>();
        try {
                transaction = session.beginTransaction();
                booksList = session.createQuery("from Orderssummary where narration!='REJECTED' order by idorders DESC").list();
                transaction.commit();
        } catch (Exception e) {transaction.rollback();
                e.printStackTrace();
        }finally{
                HibernateUtil.closeSession();
        }
        return booksList;
     }



    public boolean rejectOrders(List<Integer> ids, List<ArrayList<Ordersdetails>> ordersList) {
        
        boolean result = false;
        try {
            transaction = session.beginTransaction();
            Query query = session
                            .createQuery("update Orderssummary set narration = 'REJECTED',confirmationdate = current_date(),totalafterdiscount=totalafterdiscount*100/(100-discount),discount=0 where idorders IN (:ids)");
            query.setParameterList("ids", ids);
            query.executeUpdate();
            
            for (List<Ordersdetails> list : ordersList) {
            	
           	 for (Ordersdetails ordersdetails : list) {
                    Query queryUpdateBooksQuantity = session.createQuery("update Books set quantity = quantity+"+ordersdetails.getQuantity()+" where id ="+ordersdetails.getBookid());
                    queryUpdateBooksQuantity.executeUpdate();
                }
			}
            
            transaction.commit();
            result = true;
    } catch (HibernateException hibernateException) {transaction.rollback();
            hibernateException.printStackTrace();
    }finally {
		HibernateUtil.closeSession();
	}
            return result;
}


/*
 * public boolean deliverOrders(List<Integer> ids, List<String>
 * paymentStatusList) {
 * 
 * boolean result = false; try { transaction = session.beginTransaction();
 * 
 * for (int i=0; i<ids.size(); i++) { Query query = session.
 * createQuery("update Orderssummary set narration = 'DELIVERED', paymentstatus = '"
 * +paymentStatusList.get(i)
 * +"', confirmationdate = current_date()  where idorders = "+ids.get(i)+"");
 * query.executeUpdate(); }
 * 
 * transaction.commit(); result = true; } catch (HibernateException
 * hibernateException) {transaction.rollback();
 * hibernateException.printStackTrace(); }finally {
 * HibernateUtil.closeSession(); } return result; }
 */



    public boolean updateOrders(List<Integer> ids, List<String> paymentStatusList) {
        
        boolean result = false;
        try {
            transaction = session.beginTransaction();
            
            for (int i=0; i<ids.size(); i++) {
                Query query = session.createQuery("update Orderssummary set paymentstatus = '"+paymentStatusList.get(i)+"'  where idorders = "+ids.get(i)+"");
                query.executeUpdate();
            }
           
            transaction.commit();
            result = true;
    } catch (HibernateException hibernateException) {transaction.rollback();
            hibernateException.printStackTrace();
    }finally {
		HibernateUtil.closeSession();
	}
            return result;
}

    public List<Orderssummary> viewOrder(String SQL) {
        java.util.List<Orderssummary> orderList = new ArrayList<Orderssummary>();
            try {
                transaction = session.beginTransaction();
                Query HQLquery = session.createQuery(SQL);
                orderList = (java.util.List<Orderssummary>) HQLquery.list();
                transaction.commit();
            } catch (HibernateException hibernateException) {transaction.rollback();
                hibernateException.printStackTrace();
            }finally {
    			HibernateUtil.closeSession();
    		}
            return orderList;
            }

    public List<Ordersdetails> getOrderDetails(Integer id) {
        
        List<Ordersdetails> ordersList = new ArrayList<Ordersdetails>();
        try {
                transaction = session.beginTransaction();
                ordersList = session.createQuery("from Ordersdetails where orderssummaryid="+id).list();
                transaction.commit();
        } catch (Exception e) {transaction.rollback();
                e.printStackTrace();
        }finally{
                HibernateUtil.closeSession();
        }
        return ordersList;
     }

    public boolean updateBooksQuantity(List<Ordersdetails> orderDetailsList) {
        
        boolean result = false;
        try {
            transaction = session.beginTransaction();
            for (Ordersdetails ordersdetails : orderDetailsList) {
                Query query = session.createQuery("update Books set quantity = quantity-"+ordersdetails.getQuantity()+" where id ="+ordersdetails.getBookid());
                query.executeUpdate();
            }
            transaction.commit();
            result = true;
        } catch (HibernateException hibernateException) {transaction.rollback();
            hibernateException.printStackTrace();
        }finally {
			HibernateUtil.closeSession();
		}
        return result;
    }

    public Books getBook(Integer bookid) {
        
        Books book= new Books();
        try {
                transaction = session.beginTransaction();
                Query query = session.createQuery("from Books where id="+bookid);
                book = (Books) query.uniqueResult();
                transaction.commit();
        } catch (Exception e) {transaction.rollback();
                e.printStackTrace();
        }finally{
                HibernateUtil.closeSession();
        }
        return book;
     }

    public List<Orderssummary> viewOrderCenter(int branchId) {
        
        List<Orderssummary> booksList = new ArrayList<Orderssummary>();
        try {
                transaction = session.beginTransaction();
                booksList = session.createQuery("from Orderssummary where centercode = '"+branchId+"'").list();
                transaction.commit();
        } catch (Exception e) {transaction.rollback();
                e.printStackTrace();
        }finally{
                HibernateUtil.closeSession();
        }
        return booksList;
     }

	public List<Books> getBooksReport(String query) {
		
        List<Books> booksList = new ArrayList<Books>();
        try {
            transaction = session.beginTransaction();
            Query HQLquery = session.createQuery(query);
            booksList = (java.util.List<Books>) HQLquery.list();
            transaction.commit();
        } catch (HibernateException hibernateException) {transaction.rollback();
            hibernateException.printStackTrace();
        }finally {
			HibernateUtil.closeSession();
		}
        return booksList;
        }

	public List<Object[]> getBooksSalesReport(String query) {
		
		List<Object[]> rows = new ArrayList<Object[]>();
        try {
            transaction = session.beginTransaction();
            Query HQLquery = session.createSQLQuery(query);
            rows = HQLquery.list();
            transaction.commit();
        } catch (HibernateException hibernateException) {transaction.rollback();
            hibernateException.printStackTrace();
        }finally {
			HibernateUtil.closeSession();
		}
        return rows;
        }

	public boolean deliverOrdersConfirmation(List<Orderssummary> orderSummaryList, List<List<Ordersdetails>> listOrderdetails) {
        
        boolean result = false;
        try {
            transaction = session.beginTransaction();
            
            for (Orderssummary orderssummary : orderSummaryList) {
            	Query query = session.createQuery("update Orderssummary set narration = 'DELIVERED', paymentstatus = '"+orderssummary.getPaymentstatus()+"', discount = '"+orderssummary.getDiscount()+"', totalafterdiscount= '"+orderssummary.getTotalafterdiscount()+"', confirmationdate = current_date()  where idorders = "+orderssummary.getIdorders()+"");
                query.executeUpdate();
            }
            
            for (List<Ordersdetails> list : listOrderdetails) {
            	
            	 for (Ordersdetails ordersdetails : list) {
                     Query query = session.createQuery("update Books set quantity = quantity-"+ordersdetails.getQuantity()+" where id ="+ordersdetails.getBookid());
                     query.executeUpdate();
                 }
			}
 
            transaction.commit();
            result = true;
    } catch (HibernateException hibernateException) {transaction.rollback();
            hibernateException.printStackTrace();
    }finally {
		HibernateUtil.closeSession();
	}
            return result;
}

	public List<Orderssummary> getBooksSalesSummaryReport(String query) {
		
        List<Orderssummary> orderSummaryList = new ArrayList<Orderssummary>();
        try {
            transaction = session.beginTransaction();
            Query HQLquery = session.createQuery(query);
            orderSummaryList = (java.util.List<Orderssummary>) HQLquery.list();
            transaction.commit();
        } catch (HibernateException hibernateException) {transaction.rollback();
            hibernateException.printStackTrace();
        }finally {
			HibernateUtil.closeSession();
		}
        return orderSummaryList;
        }

	public Branch getBranch(String centerCode) {
        Branch results = new Branch();
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("From Branch where centercode = '"+centerCode+"'").setCacheable(true).setCacheRegion("commonregion");
            results = (Branch) query.uniqueResult();
            transaction.commit();
        } catch (HibernateException hibernateException) {transaction.rollback();
            hibernateException.printStackTrace();
        } finally {
            HibernateUtil.closeSession();
            return results;
        }
    }

	public List<Orderssummary> viewRejectedOrder() {
        
        List<Orderssummary> booksList = new ArrayList<Orderssummary>();
        try {
                transaction = session.beginTransaction();
                booksList = session.createQuery("from Orderssummary where narration='REJECTED' order by idorders DESC").list();
                transaction.commit();
        } catch (Exception e) {transaction.rollback();
                e.printStackTrace();
        }finally{
                HibernateUtil.closeSession();
        }
        return booksList;
     }


}

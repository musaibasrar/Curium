package com.model.order.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.model.order.dto.Books;
import com.model.order.dto.Ordersdetails;
import com.model.order.dto.Orderssummary;
import com.util.HibernateUtil;

public class OrderDAO {

	Session session;
	Transaction transaction;
	
	public OrderDAO() {
		session = HibernateUtil.openSession();
	}

    public List<Books> readBooks() {
       
            List<Books> booksList = new ArrayList<Books>();
            try {
                    transaction = session.beginTransaction();
                    booksList = session.createQuery("from Books").list();
                    transaction.commit();
            } catch (Exception e) {
                    e.printStackTrace();
            }finally{
                    //session.close();
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
    } catch (HibernateException hibernateException) {
            transaction.rollback();
            hibernateException.printStackTrace();
    } finally {
            //session.close();
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
        } catch (HibernateException hibernateException) {
            hibernateException.printStackTrace();
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
        } catch (HibernateException hibernateException) {
            hibernateException.printStackTrace();
        }
        return result;
    }



    public boolean confirmOrderSummary(Orderssummary orderSummary, List<Ordersdetails> orderDetailsList) {
        boolean result = false;
        
        try {
            transaction = session.beginTransaction();
            session.save(orderSummary);
            Integer orderSum = orderSummary.getIdorders();
            
            for (Ordersdetails ordersdetails : orderDetailsList) {
                ordersdetails.setOrderssummaryid(orderSum);
                session.save(ordersdetails);
            }
            transaction.commit();
            result = true;
    } catch (HibernateException hibernateException) {
            transaction.rollback();
            hibernateException.printStackTrace();
    } finally {
            //session.close();
    }
        return result;
}



    public List<Orderssummary> viewOrder() {
        
        List<Orderssummary> booksList = new ArrayList<Orderssummary>();
        try {
                transaction = session.beginTransaction();
                booksList = session.createQuery("from Orderssummary").list();
                transaction.commit();
        } catch (Exception e) {
                e.printStackTrace();
        }finally{
                //session.close();
        }
        return booksList;
     }



    public boolean rejectOrders(List<Integer> ids) {
        
        boolean result = false;
        try {
            transaction = session.beginTransaction();
            Query query = session
                            .createQuery("update Orderssummary set narration = 'REJECTED',confirmationdate = current_date() where idorders IN (:ids)");
            query.setParameterList("ids", ids);
            query.executeUpdate();
            transaction.commit();
            result = true;
    } catch (HibernateException hibernateException) {
            hibernateException.printStackTrace();
    }
            return result;
}



    public boolean deliverOrders(List<Integer> ids, List<String> paymentStatusList) {
        
        boolean result = false;
        try {
            transaction = session.beginTransaction();
            
            for (int i=0; i<ids.size(); i++) {
                Query query = session.createQuery("update Orderssummary set narration = 'DELIVERED', paymentstatus = '"+paymentStatusList.get(i)+"', confirmationdate = current_date()  where idorders = "+ids.get(i)+"");
                query.executeUpdate();
            }
           
            transaction.commit();
            result = true;
    } catch (HibernateException hibernateException) {
            hibernateException.printStackTrace();
    }
            return result;
}



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
    } catch (HibernateException hibernateException) {
            hibernateException.printStackTrace();
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
            } catch (HibernateException hibernateException) {
                transaction.rollback();
                hibernateException.printStackTrace();
            }
            return orderList;
            }

    public List<Ordersdetails> getOrderDetails(Integer id) {
        
        List<Ordersdetails> ordersList = new ArrayList<Ordersdetails>();
        try {
                transaction = session.beginTransaction();
                ordersList = session.createQuery("from Ordersdetails where orderssummaryid="+id).list();
                transaction.commit();
        } catch (Exception e) {
                e.printStackTrace();
        }finally{
                //session.close();
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
        } catch (HibernateException hibernateException) {
            hibernateException.printStackTrace();
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
        } catch (Exception e) {
                e.printStackTrace();
        }finally{
                //session.close();
        }
        return book;
     }


}

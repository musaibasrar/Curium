/**
 * 
 */
package com.util;

import org.hibernate.query.Query;

/**
 * 
 *
 */
public class Session {

    private static Session myLocalSession = new Session();
    private org.hibernate.Session currentSession = null;

    /**
     * 
     */
    private Session() {}

    private void setSession(org.hibernate.Session session) {
        this.currentSession = session;
    }
    
    public static Session getInstance(org.hibernate.Session session) {
        myLocalSession.setSession(session);
        return myLocalSession;
    }
    
    private org.hibernate.Session getCurrentSession(){
        return currentSession;
    }

    public Transaction beginTransaction() {
        System.out.println("Begin transaction");
        return new Transaction(getCurrentSession().beginTransaction());
    }

    public org.hibernate.query.Query createQuery(String query) {
        System.out.println("Query:  "+query);
        return getCurrentSession().createQuery(query);
    }
    
    public org.hibernate.query.Query createSQLQuery(String query) {
        System.out.println("SQLQuery:"+query);
        return getCurrentSession().createSQLQuery(query);
    }
    
    public Query createSQLQueryEntity(String query, Class class1) {
        return getCurrentSession().createSQLQuery(query).addEntity(class1);
    }

    public void save(java.io.Serializable obj) {
        System.out.println("save "+obj.getClass());
        getCurrentSession().save(obj);
    }

    public void update(java.io.Serializable obj) {
        System.out.println("update "+obj.getClass());
        getCurrentSession().update(obj);
    }
    
    public void delete(java.io.Serializable obj) {
        System.out.println("delete "+obj.getClass());
        getCurrentSession().delete(obj);
    }
    
    public void saveOrUpdate(java.io.Serializable obj) {
        System.out.println("saveOrUpdate "+obj.getClass());
        getCurrentSession().saveOrUpdate(obj);
    }
    
    public class Transaction {
        private org.hibernate.Transaction transaction;
        
        public Transaction(org.hibernate.Transaction transaction) {
            this.transaction = transaction;
        }
        
        public void commit(){
            System.out.println("Transaction committed");
            this.transaction.commit();
        }
        
        public void rollback() {
            System.out.println("Transaction roll back");
            this.transaction.rollback();
        }
        
    }

}

package org.ideoholic.curium.model.event.dao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.ideoholic.curium.model.event.dto.Event;
import org.ideoholic.curium.util.HibernateUtil;
import org.ideoholic.curium.util.Session;
import org.ideoholic.curium.util.Session.Transaction;

public class EventDAO {
    private static final Logger logger = LogManager.getLogger(EventDAO.class);
    
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

    
	public EventDAO() {
		session = HibernateUtil.openCurrentSession();
	}
    
    @SuppressWarnings("unchecked")
    public List<Event> getEvents(LocalDateTime start, LocalDateTime end) {
        List<Event> events = new ArrayList<>();
        
        try {
            transaction = session.beginTransaction();
            
            String queryString = "FROM Event as event";
            
            if (start != null && end != null) {
                queryString += " WHERE event.startDateTime BETWEEN :start AND :end";
            }
            
            queryString += " ORDER BY event.startDateTime ASC";
            
            Query<Event> query = session.createQuery(queryString);
            
            if (start != null && end != null) {
                query.setParameter("start", start);
                query.setParameter("end", end);
            }
            
            events = query.list();
            transaction.commit();
        } catch (Exception hibernateException) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Error getting events", hibernateException);
        } finally {
            if (session != null) {
                HibernateUtil.closeSession();
            }
        }
        return events;
    }

    public Event getEventById(Long id) {
        Event event = null;
        
        try {
            transaction = session.beginTransaction();
            
            Query query = session.createQuery("FROM Event as event WHERE event.id = :id");
            query.setParameter("id", id);
            event = (Event) query.uniqueResult();
            
            transaction.commit();
        } catch (Exception hibernateException) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Error getting event by id: " + id, hibernateException);
        } finally {
            if (session != null) {
                HibernateUtil.closeSession();
            }
        }
        return event;
    }

    public boolean saveEvent(Event event) {
        boolean result = false;
        
        try {
            transaction = session.beginTransaction();
            
            session.save(event);
            
            transaction.commit();
            result = true;
        } catch (Exception hibernateException) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Error saving event", hibernateException);
        } finally {
            if (session != null) {
                HibernateUtil.closeSession();
            }
        }
        return result;
    }

    public boolean updateEvent(Event event) {
        boolean result = false;
        
        try {
            transaction = session.beginTransaction();
            
            session.update(event);
            
            transaction.commit();
            result = true;
        } catch (Exception hibernateException) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Error updating event", hibernateException);
        } finally {
            if (session != null) {
                HibernateUtil.closeSession();
            }
        }
        return result;
    }

    public boolean deleteEvent(Long id) {
        boolean result = false;
        
        try {
            transaction = session.beginTransaction();
            
            Query query = session.createQuery("FROM Event as event WHERE event.id = :id");
            query.setParameter("id", id);
            Event event = (Event) query.uniqueResult();
            
            if (event != null) {
                session.delete(event);
                transaction.commit();
                result = true;
            }
        } catch (Exception hibernateException) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Error deleting event with id: " + id, hibernateException);
        } finally {
            if (session != null) {
                HibernateUtil.closeSession();
            }
        }
        return result;
    }
} 
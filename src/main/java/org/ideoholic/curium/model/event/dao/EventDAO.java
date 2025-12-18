package org.ideoholic.curium.model.event.dao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.ideoholic.curium.model.event.dto.Event;
import org.ideoholic.curium.repositories.EventRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class EventDAO {
    
    private final EventRepository eventRepository;

    @Transactional
	public List<Event> getEvents(LocalDateTime start, LocalDateTime end, String branchId, String userId) {
		List<Event> events = new ArrayList<>();

		try {
			// String queryString = "FROM Event as event";
			if (start != null && end != null) {
				// queryString += " WHERE event.startDateTime BETWEEN :start AND :end AND event.branchid = :branchid";
				events = eventRepository.findByStartDateTimeBetweenAndBranchidOrderByStartDateTimeAsc(start, end, Integer.parseInt(branchId));
			} else {
				// queryString += " ORDER BY event.startDateTime ASC";
				events = eventRepository.findAllByOrderByStartDateTimeAsc();
			}
		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error("Error getting events", hibernateException);
		}
		return events;
	}

    @Transactional
	public Event getEventById(Long id) {
		Event event = null;

		try {
			// Query query = session.createQuery("FROM Event as event WHERE event.id = :id");
			event = eventRepository.findById(id).orElse(null);
		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error("Error getting event by id:{}", id, hibernateException);
		}
		return event;
	}

    @Transactional
    public boolean saveEvent(Event event) {
        boolean result = false;
        try {
        	// session.save(event);
            eventRepository.save(event);
            result = true;
        } catch (Exception hibernateException) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error("Error saving event", hibernateException);
        } 
        return result;
    }

    @Transactional
    public boolean updateEvent(Event event) {
        boolean result = false;
        try {
        	// session.update(event);
            eventRepository.save(event); 
            result = true;
        } catch (Exception hibernateException) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error("Error updating event", hibernateException);
        } 
        return result;
    }

    @Transactional
	public boolean deleteEvent(Long id) {
		boolean result = false;
		try {
			// Query query = session.createQuery("FROM Event as event WHERE event.id = :id");
			// query.setParameter("id", id);
			// Event event = (Event) query.uniqueResult();
			Event event = eventRepository.findById(id).orElse(null);
			// if (event != null) {
            //    session.delete(event);
			if (event != null) {
				eventRepository.delete(event);
				result = true;
			}
		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error("Error deleting event with id:{}", id, hibernateException);
		}
		return result;
	}
} 
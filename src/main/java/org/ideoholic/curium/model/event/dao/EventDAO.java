package org.ideoholic.curium.model.event.dao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.ideoholic.curium.model.event.dto.Event;
import org.ideoholic.curium.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class EventDAO {
    
    private final EventRepository eventRepository;

    @Autowired
    public EventDAO(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }
    
    @SuppressWarnings("unchecked")
    @Transactional
    public List<Event> getEvents(LocalDateTime start, LocalDateTime end, String branchId, String userId) {
        List<Event> events = new ArrayList<>();
        
        try {
          
            if (start != null && end != null) {
                events = eventRepository.findByStartDateTimeBetweenAndBranchidOrderByStartDateTimeAsc(start, end, Integer.parseInt(branchId));
            } else {
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
           
            Optional<Event> opt = eventRepository.findById(id);
            if (opt.isPresent()) {
                event = opt.get();
            } else {
                event = null;
            }
        } catch (Exception hibernateException) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error("Error getting event by id: " + id, hibernateException);
        } 
        return event;
    }

    @Transactional
    public boolean saveEvent(Event event) {
        boolean result = false;
        try {
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
            Optional<Event> opt = eventRepository.findById(id);
            if (opt.isPresent()) {
                eventRepository.delete(opt.get());
                result = true;
            }
        } catch (Exception hibernateException) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error("Error deleting event with id: " + id, hibernateException);
        }
        return result;
    }
} 
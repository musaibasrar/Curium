package org.ideoholic.curium.model.event.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ideoholic.curium.model.event.dao.EventDAO;
import org.ideoholic.curium.model.event.dto.Event;
import org.ideoholic.curium.model.event.dto.EventDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EventService {
    
    private static final Logger logger = LogManager.getLogger(EventService.class);
    private final EventDAO eventDAO;
    
    public EventService() {
        this.eventDAO = new EventDAO();
    }
    
    @Transactional(readOnly = true)
    public List<EventDTO> getEvents(LocalDateTime start, LocalDateTime end, String branchId, String userId) {
        List<Event> events = eventDAO.getEvents(start, end, branchId, userId);
        List<EventDTO> eventDTOs = new ArrayList<>();
        
        for (Event event : events) {
            eventDTOs.add(convertToDTO(event));
        }
        
        return eventDTOs;
    }
    
    @Transactional(readOnly = true)
    public EventDTO getEventById(Long id) {
        Event event = eventDAO.getEventById(id);
        if (event != null) {
            return convertToDTO(event);
        }
        return null;
    }
    
    @Transactional
    public boolean createEvent(EventDTO eventDTO, String branchid, String userId) {
        try {
            Event event = convertToEntity(eventDTO);
            event.setCreatedAt(LocalDateTime.now());
            event.setUpdatedAt(LocalDateTime.now());
            event.setBranchid(Integer.parseInt(branchid));
            event.setUserid(Integer.parseInt(userId));
            return eventDAO.saveEvent(event);
        } catch (Exception e) {
            logger.error("Error creating event", e);
            return false;
        }
    }
    
    @Transactional
    public boolean updateEvent(Long id, EventDTO eventDTO, String branchid, String userId) {
        try {
            Event existingEvent = eventDAO.getEventById(id);
            if (existingEvent != null) {
                updateEventFromDTO(existingEvent, eventDTO);
                existingEvent.setUpdatedAt(LocalDateTime.now());
                existingEvent.setBranchid(Integer.parseInt(branchid));
                existingEvent.setUserid(Integer.parseInt(userId));
                return eventDAO.updateEvent(existingEvent);
            }
            return false;
        } catch (Exception e) {
            logger.error("Error updating event", e);
            return false;
        }
    }
    
    @Transactional
    public boolean deleteEvent(Long id) {
        try {
            return eventDAO.deleteEvent(id);
        } catch (Exception e) {
            logger.error("Error deleting event", e);
            return false;
        }
    }
    
    private EventDTO convertToDTO(Event event) {
        EventDTO dto = new EventDTO();
        dto.setId(event.getId());
        dto.setTitle(event.getTitle());
        dto.setDescription(event.getDescription());
        dto.setStart(event.getStartDateTime());
        dto.setEnd(event.getEndDateTime());
        dto.setLocation(event.getLocation());
        dto.setColor(event.getColor());
        dto.setAllDay(event.isAllDay());
        dto.setCreatedBy(event.getCreatedBy());
        return dto;
    }
    
    private Event convertToEntity(EventDTO dto) {
        Event event = new Event();
        updateEventFromDTO(event, dto);
        return event;
    }
    
    private void updateEventFromDTO(Event event, EventDTO dto) {
        event.setTitle(dto.getTitle());
        event.setDescription(dto.getDescription());
        event.setStartDateTime(dto.getStart());
        event.setEndDateTime(dto.getEnd());
        event.setLocation(dto.getLocation());
        event.setColor(dto.getColor());
        event.setAllDay(dto.isAllDay());
        event.setCreatedBy(dto.getCreatedBy());
    }
} 
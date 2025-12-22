package org.ideoholic.curium.model.event.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ideoholic.curium.model.event.dao.EventDAO;
import org.ideoholic.curium.model.event.dto.Event;
import org.ideoholic.curium.model.event.dto.EventDTO;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventService {
    
    private final EventDAO eventDao;

    
    public List<Map<String, Object>> getEvents(LocalDateTime start, LocalDateTime end, String branchId, String userId) {
        List<Event> events = eventDao.getEvents(start, end, branchId, userId);
        List<EventDTO> eventDTOs = new ArrayList<>();
        List<Map<String, Object>> eventMap = new ArrayList<>();
        
        for (Event event : events) {
            eventDTOs.add(convertToDTO(event));
        }
        
        for (EventDTO dto : eventDTOs) {
            Map<String, Object> event = new HashMap<>();
            event.put("id", dto.getId());
            event.put("title", dto.getTitle());
            
            // Format dates as ISO strings for FullCalendar
            if (dto.getStart() != null) {
                // Use ISO format for better compatibility with FullCalendar
                event.put("start", dto.getStart().format(DateTimeFormatter.ISO_DATE_TIME));
            }
            if (dto.getEnd() != null) {
                // Use ISO format for better compatibility with FullCalendar
                event.put("end", dto.getEnd().format(DateTimeFormatter.ISO_DATE_TIME));
            }
            
            event.put("allDay", dto.isAllDay());
            event.put("backgroundColor", dto.getColor());
            
            Map<String, Object> extendedProps = new HashMap<>();
            extendedProps.put("description", dto.getDescription());
            extendedProps.put("location", dto.getLocation());
            event.put("extendedProps", extendedProps);
            
            eventMap.add(event);
        }
        
        return eventMap;
    }
    
    public EventDTO getEventById(Long id) {
        Event event = eventDao.getEventById(id);
        if (event != null) {
            return convertToDTO(event);
        }
        return null;
    }
    
    public boolean createEvent(EventDTO eventDTO, String branchid, String userId) {
        try {
            Event event = convertToEntity(eventDTO);
            event.setCreatedAt(LocalDateTime.now());
            event.setUpdatedAt(LocalDateTime.now());
            event.setBranchid(Integer.parseInt(branchid));
            event.setUserid(Integer.parseInt(userId));
            return eventDao.saveEvent(event);
        } catch (Exception e) {
            log.error("Error creating event", e);
            return false;
        }
    }
    
    public boolean updateEvent(Long id, EventDTO eventDTO, String branchid, String userId) {
        try {
            Event existingEvent = eventDao.getEventById(id);
            if (existingEvent != null) {
                updateEventFromDTO(existingEvent, eventDTO);
                existingEvent.setUpdatedAt(LocalDateTime.now());
                existingEvent.setBranchid(Integer.parseInt(branchid));
                existingEvent.setUserid(Integer.parseInt(userId));
                return eventDao.updateEvent(existingEvent);
            }
            return false;
        } catch (Exception e) {
        	log.error("Error updating event", e);
            return false;
        }
    }
    
    public boolean deleteEvent(Long id) {
        try {
            return eventDao.deleteEvent(id);
        } catch (Exception e) {
        	log.error("Error deleting event", e);
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
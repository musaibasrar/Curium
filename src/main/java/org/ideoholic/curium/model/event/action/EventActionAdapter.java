package org.ideoholic.curium.model.event.action;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.event.dto.EventDTO;
import org.ideoholic.curium.model.event.service.EventService;
import org.ideoholic.curium.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Service
public class EventActionAdapter {

    @Autowired
    private HttpServletRequest request;
    
    @Autowired
    private HttpServletResponse response;
    
    @Autowired
    private HttpSession httpSession;
    
    @Autowired
    private EventService eventService;
    
    private String BRANCHID = "branchid";
    
    private String userId = Constants.USERID;
    
    private final ObjectMapper objectMapper = new ObjectMapper()
        .registerModule(new JavaTimeModule());

    public void setHttpObjects(HttpServletRequest request, HttpServletResponse response, HttpSession httpSession) {
        this.request = request;
        this.response = response;
        this.httpSession = httpSession;
    }
    
    public List<Map<String, Object>> getEvents() {
        String startParam = request.getParameter("start");
        String endParam = request.getParameter("end");
        
        LocalDateTime start = null;
        LocalDateTime end = null;
        
        if (startParam != null && !startParam.isEmpty()) {
            start = LocalDateTime.parse(startParam, DateTimeFormatter.ISO_DATE_TIME);
        }
        
        if (endParam != null && !endParam.isEmpty()) {
            end = LocalDateTime.parse(endParam, DateTimeFormatter.ISO_DATE_TIME);
        }
        
        List<EventDTO> eventDTOs = eventService.getEvents(start, end, httpSession.getAttribute(BRANCHID).toString(), httpSession.getAttribute(userId).toString());
        List<Map<String, Object>> events = new ArrayList<>();
        
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
            
            events.add(event);
        }
        
        return events;
    }
    
    public EventDTO getEventById() {
        String idParam = request.getParameter("id");
        Long id = Long.parseLong(idParam);
        return eventService.getEventById(id);
    }
    
    public boolean createEvent() {
        try {
            // Get parameters from request
            String title = request.getParameter("title");
            String description = request.getParameter("description");
            String startStr = request.getParameter("start");
            String endStr = request.getParameter("end");
            String location = request.getParameter("location");
            String color = request.getParameter("color");
            String allDayStr = request.getParameter("allDay");
            
            // Validate required fields
            if (title == null || startStr == null || endStr == null) {
                return false;
            }
            
            // Parse date strings to LocalDateTime
            DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
            LocalDateTime start = LocalDateTime.parse(startStr.replace("Z", ""), formatter);
            LocalDateTime end = LocalDateTime.parse(endStr.replace("Z", ""), formatter);
            
            // Create EventDTO
            EventDTO eventDTO = new EventDTO();
            eventDTO.setTitle(title);
            eventDTO.setDescription(description);
            eventDTO.setStart(start);
            eventDTO.setEnd(end);
            eventDTO.setLocation(location);
            eventDTO.setColor(color != null && !color.isEmpty() ? color : "#3788d8");
            eventDTO.setAllDay(Boolean.parseBoolean(allDayStr));
            
            // Get user from session if available
            if (httpSession.getAttribute("USERID") != null) {
                eventDTO.setCreatedBy(httpSession.getAttribute("USERID").toString());
            }
            
            return eventService.createEvent(eventDTO,httpSession.getAttribute(BRANCHID).toString(), httpSession.getAttribute(userId).toString());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean updateEvent() {
        try {
            String idParam = request.getParameter("id");
            Long id = Long.parseLong(idParam);
            
            // Get parameters from request, same as createEvent
            String title = request.getParameter("title");
            String description = request.getParameter("description");
            String startStr = request.getParameter("start");
            String endStr = request.getParameter("end");
            String location = request.getParameter("location");
            String color = request.getParameter("color");
            String allDayStr = request.getParameter("allDay"); // Assuming allDay might be passed

            // Validate required fields
            if (title == null || startStr == null || endStr == null) {
                return false;
            }
            
            // Parse date strings to LocalDateTime
            DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
            LocalDateTime start = LocalDateTime.parse(startStr.replace("Z", ""), formatter);
            LocalDateTime end = LocalDateTime.parse(endStr.replace("Z", ""), formatter);

            // Create EventDTO
            EventDTO eventDTO = new EventDTO();
            eventDTO.setTitle(title);
            eventDTO.setDescription(description);
            eventDTO.setStart(start);
            eventDTO.setEnd(end);
            eventDTO.setLocation(location);
            eventDTO.setColor(color != null && !color.isEmpty() ? color : "#3788d8");
            eventDTO.setAllDay(allDayStr != null && Boolean.parseBoolean(allDayStr));
            
            return eventService.updateEvent(id, eventDTO, httpSession.getAttribute(BRANCHID).toString(), httpSession.getAttribute(userId).toString());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean deleteEvent() {
        try {
            String idParam = request.getParameter("id");
            if (idParam == null || idParam.isEmpty()) {
                return false;
            }
            
            try {
                Long id = Long.parseLong(idParam);
                return eventService.deleteEvent(id);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
} 
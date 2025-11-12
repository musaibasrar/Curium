package org.ideoholic.curium.model.event.action;

import java.io.BufferedReader;
import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
        
        
        List<Map<String, Object>> events = eventService.getEvents(start, end, httpSession.getAttribute(BRANCHID).toString(), httpSession.getAttribute(userId).toString());
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

    		String idParam = request.getParameter("id");
            Long id = Long.parseLong(idParam);
            
            // Read JSON from request body
            BufferedReader reader = request.getReader();
            StringBuilder json = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                json.append(line);
            }
            
            // Parse JSON to EventDTO
            EventDTO eventDTO = objectMapper.readValue(json.toString(), EventDTO.class);
            return eventService.updateEvent(id, eventDTO, httpSession.getAttribute(BRANCHID).toString(), httpSession.getAttribute(userId).toString());
       
    }
    
    public boolean deleteEvent() {
            String idParam = request.getParameter("id");
            if (idParam == null || idParam.isEmpty()) {
                return false;
            }
                Long id = Long.parseLong(idParam);
                return eventService.deleteEvent(id);
    }
} 
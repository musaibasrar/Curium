package org.ideoholic.curium.model.event.action;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.event.dto.EventDTO;
import org.ideoholic.curium.model.event.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/EventProcess")
public class EventAction {

    @Autowired
    private HttpServletRequest request;
    
    @Autowired
    private HttpServletResponse response;
    
    @Autowired
    private HttpSession httpSession;
    
    @Autowired
    private EventActionAdapter eventActionAdapter;

    @Autowired
    private EventService eventService;

    @GetMapping("/calendar")
    public String showCalendar() {
        return "calendar";
    }

    @GetMapping("/create")
    public String showCreateEvent(Model model) {
        return "create_event";
    }
    
    @GetMapping("/getEvents")
    @ResponseBody
    public List<Map<String, Object>> getEvents() {
        return eventActionAdapter.getEvents();
    }
    
    @GetMapping("/getEventById")
    @ResponseBody
    public EventDTO getEventById() {
        return eventActionAdapter.getEventById();
    }
    
    private String getUserId(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            Object userObj = session.getAttribute("user");
            if (userObj != null) {
                return userObj.toString();
            }
        }
        return "system";
    }

    @PostMapping("/createEvent")
    public String createEvent(Model model) {
        boolean success = eventActionAdapter.createEvent();
        if (success) {
            model.addAttribute("successMessage", "Event created successfully!");
            return "create_event";
        } else {
            model.addAttribute("errorMessage", "Failed to create event. Please try again.");
            return "create_event";
        }
    }
    
    @PostMapping("/updateEvent")
    @ResponseBody
    public boolean updateEvent() {
        return eventActionAdapter.updateEvent();
    }
    
    @PostMapping("/deleteEvent")
    @ResponseBody
    public boolean deleteEvent() {
        return eventActionAdapter.deleteEvent();
    }
    
    @GetMapping(value = "/viewCalendar")
    public String viewCalendar() {
        return "view_calendar";
    }
} 
package org.ideoholic.curium.model.event.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class EventDTO {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime start;
    private LocalDateTime end;
    private String location;
    private String color;
    private boolean allDay;
    private String createdBy;	
    private String branchId;
} 
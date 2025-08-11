<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Calendar Events</title>
    <!-- FullCalendar CSS -->
    <link href='https://cdn.jsdelivr.net/npm/fullcalendar@5.11.3/main.min.css' rel='stylesheet' />
    
    <!-- FullCalendar JS -->
    <script src='https://cdn.jsdelivr.net/npm/fullcalendar@5.11.3/main.min.js'></script>
    
      <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap" rel="stylesheet">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    
    <style>
    
    :root {
            --primary-color: #000000;
            --primary-dark: #2d4a5f;
            --primary-light: #5a8ab0;
            --secondary-color: #f8f9fa;
            --text-color: #333;
            --border-color: #e0e0e0;
            --hover-color: #f5f5f5;
            --booked-color: #DC2626;
        }
        
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }
        
        .fc-event {
            cursor: pointer;
        }
        
        .fc-event-title {
            color: #02a429 !important; /* Make event titles red */
        }
        
        #calendar {
            margin: 10px;
            padding: 10px;
        }
        
        #eventModal {
            display: none;
            position: fixed;
            z-index: 1000;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0,0,0,0.4);
            overflow-y: auto;
        }
        
        .modal-content {
            background-color: #fefefe;
            margin: 15% auto;
            padding: 20px;
            border: 1px solid #888;
            width: 90%;
            max-width: 500px;
            border-radius: 5px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
            position: relative;
        }
        
        .close {
            color: #aaa;
            float: right;
            font-size: 28px;
            font-weight: bold;
            cursor: pointer;
        }
        
        .form-group {
            margin-bottom: 15px;
        }
        
        .form-group label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }
        
        .form-group input, .form-group textarea {
            width: 100%;
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
        }
        
          .header {
            background-color: var(--primary-color);
            color: white;
            padding: 1rem 2rem;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }

        .header h1 {
            margin: 0;
            font-size: 1.5rem;
            font-weight: 500;
        }
        
        .button-group {
            display: flex;
            justify-content: flex-start;
            margin-top: 20px;
            gap: 10px;
        }
        
        button {
            background-color: #4CAF50;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-weight: bold;
        }
        
        button:hover {
            background-color: #45a049;
        }
        
        #deleteButton {
            background-color: #f44336;
        }
        
        #deleteButton:hover {
            background-color: #d32f2f;
        }
        
        /* Responsive adjustments */
        @media (max-width: 768px) {
            .modal-content {
                width: 95%;
                margin: 10% auto;
                padding: 15px;
            }
            
            .fc-header-toolbar {
                flex-direction: column;
                align-items: center;
            }
            
            .fc-toolbar-chunk {
                margin-bottom: 10px;
            }
            
            .fc-button {
                padding: 6px 10px;
                font-size: 0.9em;
            }
            
            .button-group {
                flex-direction: column;
            }
            
            .button-group button {
                width: 100%;
                margin-bottom: 10px;
            }
        }
    </style>
</head>
<body>

	<div class="header">
        <h1><i class="far fa-calendar-alt"></i> Create Event</h1>
    </div>
    
    <div id="calendar"></div>

    <!-- Event Modal -->
    <div id="eventModal" class="modal">
        <div class="modal-content">
            <span class="close">&times;</span>
            <h2 id="modalTitle">Event Details</h2>
            <form id="eventForm">
                <input type="hidden" id="eventId">
                <div class="form-group">
                    <label for="title">Title:</label>
                    <input type="text" id="title" required>
                </div>
                <div class="form-group">
                    <label for="description">Description:</label>
                    <textarea id="description" rows="3"></textarea>
                </div>
                <div class="form-group">
                    <label for="start">Start:</label>
                    <input type="datetime-local" id="start" required>
                </div>
                <div class="form-group">
                    <label for="end">End:</label>
                    <input type="datetime-local" id="end" required>
                </div>
                <div class="form-group">
                    <label for="location">Notes:</label>
                    <input type="text" id="location">
                </div>
                <div class="form-group">
                    <label for="color">Color:</label>
                    <input type="color" id="color">
                </div>
                <!-- <div class="form-group">
                    <label for="allDay">All Day:</label>
                    <input type="checkbox" id="allDay">
                </div> -->
                <div class="button-group">
                    <button type="submit" id="saveButton">Save</button>
                    <button type="button" id="deleteButton">Delete</button>
                </div>
            </form>
        </div>
    </div>

    <script>
        document.addEventListener('DOMContentLoaded', function() {
            var calendarEl = document.getElementById('calendar');
            
            // Initialize FullCalendar
            var calendar = new FullCalendar.Calendar(calendarEl, {
                initialView: 'dayGridMonth',
                headerToolbar: {
                    left: 'prev,next today',
                    center: 'title',
                    right: 'dayGridMonth,timeGridWeek,timeGridDay'
                },
                buttonText: {
                    today: 'Today',
                    month: 'Month',
                    week: 'Week',
                    day: 'Day'
                },
                editable: true,
                selectable: true,
                selectMirror: true,
                dayMaxEvents: true,
                selectLongPressDelay: 100,
                displayEventTime: false,
                events: '/jdh/EventProcess/getEvents',
                eventClassNames: 'custom-event-width',
                select: function(arg) {
                    openModal(null, arg);
                },
                eventClick: function(info) {
                    openModal(info.event, null);
                },
                height: 'auto',
                contentHeight: 'auto',
                aspectRatio: 1.8,
                timeZone: 'local'
            });
            calendar.render();

            var modal = document.getElementById('eventModal');
            var span = document.getElementsByClassName('close')[0];
            var form = document.getElementById('eventForm');
            var deleteButton = document.getElementById('deleteButton');
            var saveButton = document.getElementById('saveButton');

            function openModal(event, selectInfo) {
                modal.style.display = 'block';
                if (event) {
                    // Editing existing event
                    document.getElementById('eventId').value = event.id;
                    document.getElementById('title').value = event.title;
                    document.getElementById('description').value = event.extendedProps.description || '';
                    
                    // Debug log to check the received date values
                    console.log('Event start:', event.start);
                    console.log('Event end:', event.end);
                    
                    // Convert the ISO string dates to local datetime-local format
                    let startDate = null;
                    let endDate = null;
                    
                    if (event.start) {
                        startDate = new Date(event.start);
                    }
                    if (event.end) {
                        endDate = new Date(event.end);
                    } else {
                        // If no end date, use start date plus 1 hour
                        endDate = new Date(startDate);
                        endDate.setHours(endDate.getHours() + 1);
                    }
                    
                    // Format dates for datetime-local input
                    if (startDate) {
                        const startStr = startDate.getFullYear() + '-' +
                            String(startDate.getMonth() + 1).padStart(2, '0') + '-' +
                            String(startDate.getDate()).padStart(2, '0') + 'T' +
                            String(startDate.getHours()).padStart(2, '0') + ':' +
                            String(startDate.getMinutes()).padStart(2, '0');
                        document.getElementById('start').value = startStr;
                    }
                    
                    if (endDate) {
                        const endStr = endDate.getFullYear() + '-' +
                            String(endDate.getMonth() + 1).padStart(2, '0') + '-' +
                            String(endDate.getDate()).padStart(2, '0') + 'T' +
                            String(endDate.getHours()).padStart(2, '0') + ':' +
                            String(endDate.getMinutes()).padStart(2, '0');
                        document.getElementById('end').value = endStr;
                    }
                    
                    document.getElementById('location').value = event.extendedProps.location || '';
                    document.getElementById('color').value = event.backgroundColor || '#3788d8';
                    saveButton.textContent = 'Update';
                    saveButton.style.display = 'block';
                    deleteButton.style.display = 'block';
                } else {
                    // Creating new event
                    document.getElementById('eventId').value = '';
                    document.getElementById('title').value = '';
                    document.getElementById('description').value = '';
                    
                    // Handle new event creation dates
                    let startDate = selectInfo.start;
                    startDate.setHours(15, 0, 0, 0);
                    // Always set end date to same date as start date, but 1 hour later
                    let endDate = new Date(startDate);
                    endDate.setHours(23, 55, 0, 0);
                    
                    if (startDate) {
                        const startStr = startDate.getFullYear() + '-' +
                            String(startDate.getMonth() + 1).padStart(2, '0') + '-' +
                            String(startDate.getDate()).padStart(2, '0') + 'T' +
                            String(startDate.getHours()).padStart(2, '0') + ':' +
                            String(startDate.getMinutes()).padStart(2, '0');
                        document.getElementById('start').value = startStr;
                        
                        const endStr = endDate.getFullYear() + '-' +
                            String(endDate.getMonth() + 1).padStart(2, '0') + '-' +
                            String(endDate.getDate()).padStart(2, '0') + 'T' +
                            String(endDate.getHours()).padStart(2, '0') + ':' +
                            String(endDate.getMinutes()).padStart(2, '0');
                        document.getElementById('end').value = endStr;
                    }
                    
                    document.getElementById('location').value = '';
                    document.getElementById('color').value = '#3788d8';
                    saveButton.textContent = 'Save';
                    saveButton.style.display = 'block';
                    deleteButton.style.display = 'none';
                }
            }

            span.onclick = function() {
                modal.style.display = 'none';
            }

            window.onclick = function(event) {
                if (event.target == modal) {
                    modal.style.display = 'none';
                }
            }

            // Handle form submission
            document.getElementById('eventForm').addEventListener('submit', function(e) {
                e.preventDefault();
                
                // Get form values
                var title = document.getElementById('title').value;
                var description = document.getElementById('description').value;
                var startInput = document.getElementById('start').value;
                var endInput = document.getElementById('end').value;
                var location = document.getElementById('location').value;
                var color = document.getElementById('color').value;
                //var allDay = document.getElementById('allDay').checked;
                
                // Debug log
                console.log('Form values:', {
                    title: title,
                    description: description,
                    startInput: startInput,
                    endInput: endInput,
                    location: location,
                    color: color
                    //allDay: allDay
                });
                
                // Format dates to ISO format
                var startDate = new Date(startInput);
                var endDate = new Date(endInput);
                
                // Debug log for dates
                console.log('Formatted dates:', {
                    startDate: startDate,
                    endDate: endDate,
                    startISO: startDate.toISOString(),
                    endISO: endDate.toISOString()
                });
                
                // Create event data object with properly formatted dates
                var eventData = {
                    title: title,
                    description: description,
                    start: document.getElementById('start').value,
                    end: document.getElementById('end').value,
                    location: location,
                    color: color
                    //allDay: allDay
                };

                var eventId = document.getElementById('eventId').value;
                var url = '/jdh/EventProcess/createEvent';
                var method = 'POST';
                if (eventId) {
                    url = '/jdh/EventProcess/updateEvent?id=' + eventId;
                }

                // Convert the data to URL-encoded form data
                var formBody = [];
                for (var key in eventData) {
                    var encodedKey = encodeURIComponent(key);
                    var encodedValue = encodeURIComponent(eventData[key]);
                    formBody.push(encodedKey + "=" + encodedValue);
                }
                formBody = formBody.join("&");
                
                // Debug log for request
                console.log('Request URL:', url);
                console.log('Request method:', method);
                console.log('Request body:', formBody);

                fetch(url, {
                    method: method,
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    },
                    body: formBody
                })
                .then(response => {
                    console.log('Response status:', response.status);
                    if (response.ok) {
                        calendar.refetchEvents();
                        modal.style.display = 'none';
                    } else {
                        alert('Error saving event');
                    }
                })
                .catch(error => {
                    console.error('Error:', error);
                    alert('Error saving event');
                });
            });

            deleteButton.onclick = function() {
                var eventId = document.getElementById('eventId').value;
                if (eventId && confirm('Are you sure you want to delete this event?')) {
                    fetch('/jdh/EventProcess/deleteEvent?id=' + eventId, {
                        method: 'POST'
                    })
                    .then(response => {
                        if (response.ok) {
                            calendar.refetchEvents();
                            modal.style.display = 'none';
                        } else {
                            alert('Error deleting event');
                        }
                    })
                    .catch(error => {
                        console.error('Error:', error);
                        alert('Error deleting event');
                    });
                }
            }

            function formatDateTime(dateTimeStr) {
                if (!dateTimeStr) return null;
                const date = new Date(dateTimeStr);
                return date.toISOString();
            }
            
            // New function specifically for datetime-local input fields
            function formatDateTimeForInput(date) {
                if (!date) return '';
                
                // Handle both Date objects and ISO strings
                const d = date instanceof Date ? date : new Date(date);
                
                // Check if date is valid
                if (isNaN(d.getTime())) {
                    console.error('Invalid date:', date);
                    return '';
                }
                
                // Format the date and time in local timezone for datetime-local input
                const year = d.getFullYear();
                const month = String(d.getMonth() + 1).padStart(2, '0');
                const day = String(d.getDate()).padStart(2, '0');
                const hours = String(d.getHours()).padStart(2, '0');
                const minutes = String(d.getMinutes()).padStart(2, '0');
                
                // Return in format YYYY-MM-DDThh:mm (required format for datetime-local input)
                return `${year}-${month}-${day}T${hours}:${minutes}`;
            }
        });
    </script>
</body>
</html> 
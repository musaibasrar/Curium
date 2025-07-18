<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Calendar Events</title>
    <!-- FullCalendar CSS -->
    <link href='https://cdn.jsdelivr.net/npm/fullcalendar@5.11.3/main.min.css' rel='stylesheet' />
    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap" rel="stylesheet">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <!-- FullCalendar JS -->
    <script src='https://cdn.jsdelivr.net/npm/fullcalendar@5.11.3/main.min.js'></script>
    
    <style>
        :root {
            --primary-color: #4b6a84;
            --primary-dark: #2d4a5f;
            --primary-light: #5a8ab0;
            --secondary-color: #f8f9fa;
            --text-color: #333;
            --border-color: #e0e0e0;
            --hover-color: #f5f5f5;
            --booked-color: #DC2626;
        }

        body {
            font-family: 'Roboto', Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f8f9fa;
            color: var(--text-color);
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

        .container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 20px;
        }
        
        #calendar {
            background: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }
        
        .fc-event {
            cursor: pointer;
            border-radius: 4px;
            padding: 2px 4px;
            transition: all 0.3s ease;
            background-color: #ffd600 !important;
            border-color: #ffd600 !important;
        }
        
        .fc-event:hover {
            transform: translateY(-1px);
            box-shadow: 0 2px 5px rgba(0,0,0,0.2);
            background-color: #DC262 !important;
            border-color: #DC262 !important;
        }

        .fc-button {
            background-color: var(--primary-color) !important;
            border-color: var(--primary-color) !important;
            transition: all 0.3s ease !important;
        }

        .fc-button:hover {
            background-color: var(--primary-dark) !important;
            border-color: var(--primary-dark) !important;
        }

        .fc-button-active {
            background-color: var(--primary-dark) !important;
            border-color: var(--primary-dark) !important;
        }
        
        #eventModal {
            display: none;
            position: fixed;
            z-index: 1000;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0,0,0,0.5);
            overflow-y: auto;
            animation: fadeIn 0.3s ease;
        }
        
        @keyframes fadeIn {
            from { opacity: 0; }
            to { opacity: 1; }
        }
        
        .modal-content {
            background-color: #fefefe;
            margin: 5% auto;
            padding: 25px;
            border: none;
            width: 90%;
            max-width: 500px;
            border-radius: 12px;
            box-shadow: 0 4px 20px rgba(0,0,0,0.15);
            position: relative;
            animation: slideIn 0.3s ease;
        }
        
        @keyframes slideIn {
            from { transform: translateY(-20px); opacity: 0; }
            to { transform: translateY(0); opacity: 1; }
        }
        
        .close {
            color: #999;
            float: right;
            font-size: 28px;
            font-weight: bold;
            cursor: pointer;
            transition: color 0.3s ease;
        }
        
        .close:hover {
            color: var(--primary-color);
        }
        
        .event-details {
            margin: 20px 0;
        }
        
        .event-detail-row {
            display: flex;
            margin-bottom: 15px;
            padding: 10px;
            background-color: var(--secondary-color);
            border-radius: 6px;
            transition: background-color 0.3s ease;
        }

        .event-detail-row:hover {
            background-color: var(--hover-color);
        }
        
        .event-details label {
            font-weight: 500;
            min-width: 120px;
            color: var(--primary-color);
        }
        
        .event-details span {
            flex: 1;
            padding-left: 10px;
        }

        #modalTitle {
            color: var(--primary-color);
            margin: 0 0 20px 0;
            padding-bottom: 10px;
            border-bottom: 2px solid var(--border-color);
        }

        .fc-toolbar-title {
            color: #1cd047 !important;
        }

        .fc-day-today {
            background-color: rgba(65, 104, 132, 0.1) !important;
        }

        .fc-day-today .fc-daygrid-day-number {
            background-color: var(--primary-color);
            color: white;
            border-radius: 50%;
            padding: 4px;
        }
        
        /* Responsive adjustments */
        @media (max-width: 768px) {
            .container {
                padding: 10px;
            }
            
            .modal-content {
                width: 95%;
                margin: 10% auto;
                padding: 20px;
            }
            
            .fc-header-toolbar {
                flex-direction: column;
                align-items: center;
                gap: 10px;
            }
            
            .fc-toolbar-chunk {
                margin-bottom: 10px;
            }
            
            .fc-button {
                padding: 8px 12px !important;
                font-size: 0.9em !important;
            }

            .event-detail-row {
                flex-direction: column;
            }

            .event-details label {
                margin-bottom: 5px;
            }

            .event-details span {
                padding-left: 0;
            }
        }
    </style>
</head>
<body>
   	<div class="header">
        <h1><i class="far fa-calendar-alt"></i>  Event Calendar</h1>
    </div>
		<div id="calendar"></div>
   <!--  <div class="container">
        <div id="calendar"></div>
    </div> -->

    <!-- Event Modal -->
    <div id="eventModal" class="modal">
        <div class="modal-content">
            <span class="close">&times;</span>
            <h2 id="modalTitle">Event Details</h2>
            <div class="event-details">
                <div class="event-detail-row">
                    <label><i class="fas fa-heading"></i> Title:</label>
                    <span id="eventTitle"></span>
                </div>
                <div class="event-detail-row">
                    <label><i class="fas fa-align-left"></i> Description:</label>
                    <span id="eventDescription"></span>
                </div>
                <div class="event-detail-row">
                    <label><i class="fas fa-hourglass-start"></i> Start:</label>
                    <span id="eventStart"></span>
                </div>
                <div class="event-detail-row">
                    <label><i class="fas fa-hourglass-end"></i> End:</label>
                    <span id="eventEnd"></span>
                </div>
                <div class="event-detail-row">
                    <label><i class="fas fa-map-marker-alt"></i> Notes:</label>
                    <span id="eventLocation"></span>
                </div>
            </div>
        </div>
    </div>

    <script>
        document.addEventListener('DOMContentLoaded', function() {
            var calendarEl = document.getElementById('calendar');

            // Colors for different times of the day
            const timeBasedColors = {
                morning: '#039be5',   // Light Blue (before 12 PM)
                afternoon: '#43a047', // Green (12 PM - 5 PM)
                evening: '#e53935'    // Red (after 5 PM)
            };

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
                displayEventTime: false,
                editable: false,
                selectable: false,
                events: '/shadaan/EventProcess/getEvents',
                
                // This function transforms each event object before it is rendered
                eventDataTransform: function(eventData) {
                    const startDate = new Date(eventData.start);
                    const hour = startDate.getHours();

                    if (hour < 12) {
                        eventData.backgroundColor = timeBasedColors.morning;
                        eventData.borderColor = timeBasedColors.morning;
                    } else if (hour >= 12 && hour < 17) {
                        eventData.backgroundColor = timeBasedColors.afternoon;
                        eventData.borderColor = timeBasedColors.afternoon;
                    } else {
                        eventData.backgroundColor = timeBasedColors.evening;
                        eventData.borderColor = timeBasedColors.evening;
                    }
                    
                    return eventData;
                },

                eventClick: function(info) {
                    showEventDetails(info.event);
                },
                
                height: 'auto',
                contentHeight: 'auto',
                aspectRatio: 1.8,
                timeZone: 'local'
            });
            calendar.render();

            var modal = document.getElementById('eventModal');
            var span = document.getElementsByClassName('close')[0];

            function showEventDetails(event) {
                document.getElementById('eventTitle').textContent = event.title;
                document.getElementById('eventDescription').textContent = event.extendedProps.description || 'No description available';
                
                // Format dates
                const startDate = new Date(event.start);
                const endDate = event.end ? new Date(event.end) : startDate;
                
                document.getElementById('eventStart').textContent = formatDateTime(startDate);
                document.getElementById('eventEnd').textContent = formatDateTime(endDate);
                document.getElementById('eventLocation').textContent = event.extendedProps.location || 'No location specified';
                
                modal.style.display = 'block';
            }

            span.onclick = function() {
                modal.style.display = 'none';
            }

            window.onclick = function(event) {
                if (event.target == modal) {
                    modal.style.display = 'none';
                }
            }

            function formatDateTime(date) {
                if (!date) return '';
                
                const options = { 
                    weekday: 'long', 
                    year: 'numeric', 
                    month: 'long', 
                    day: 'numeric',
                    hour: '2-digit',
                    minute: '2-digit'
                };
                
                return date.toLocaleDateString('en-US', options);
            }
        });
    </script>
</body>
</html> 
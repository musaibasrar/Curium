<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Event</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            margin: 0;
            padding: 0;
        }
        
        .form-container {
            max-width: 800px;
            margin: 20px auto;
            padding: 20px;
            background-color: white;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            border-radius: 5px;
        }
        
        .form-group {
            margin-bottom: 15px;
        }
        
        .form-label {
            font-weight: bold;
            margin-bottom: 5px;
        }
        
        .form-control {
            width: 100%;
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
        }
        
        .btn {
            padding: 10px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-weight: bold;
            margin-right: 10px;
        }
        
        .btn-primary {
            background-color: #4CAF50;
            color: white;
        }
        
        .btn-primary:hover {
            background-color: #45a049;
        }
        
        .btn-secondary {
            background-color: #6c757d;
            color: white;
        }
        
        .btn-secondary:hover {
            background-color: #5a6268;
        }
        
        .invalid-feedback {
            color: #dc3545;
            font-size: 0.875em;
            margin-top: 5px;
        }
        
        .alert {
            padding: 15px;
            margin-bottom: 20px;
            border: 1px solid transparent;
            border-radius: 4px;
        }
        
        .alert-success {
            color: #3c763d;
            background-color: #dff0d8;
            border-color: #d6e9c6;
        }
        
        .alert-danger {
            color: #a94442;
            background-color: #f2dede;
            border-color: #ebccd1;
        }
        
        /* Responsive adjustments */
        @media (max-width: 768px) {
            .form-container {
                margin: 10px;
                padding: 15px;
            }
            
            .row {
                flex-direction: column;
            }
            
            .col-md-6 {
                width: 100%;
                margin-bottom: 15px;
            }
            
            .btn {
                width: 100%;
                margin-bottom: 10px;
            }
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="form-container">
            <h2 class="mb-4">Create New Event</h2>
            
            <!-- Success Message -->
            <c:if test="${not empty successMessage}">
                <div class="alert alert-success">
                    ${successMessage}
                </div>
            </c:if>
            
            <!-- Error Message -->
            <c:if test="${not empty errorMessage}">
                <div class="alert alert-danger">
                    ${errorMessage}
                </div>
            </c:if>
            
            <form id="eventForm" action="/abc/EventProcess/createEvent" method="post" class="needs-validation" novalidate>
                <div class="form-group">
                    <label for="title" class="form-label">Title *</label>
                    <input type="text" class="form-control" id="title" name="title" required>
                    <div class="invalid-feedback">Please enter a title.</div>
                </div>

                <div class="form-group">
                    <label for="description" class="form-label">Description</label>
                    <textarea class="form-control" id="description" name="description" rows="3"></textarea>
                </div>

                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <label for="start" class="form-label">Start Date/Time *</label>
                            <input type="datetime-local" class="form-control" id="start" name="start" required>
                            <div class="invalid-feedback">Please select a start date and time.</div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <label for="end" class="form-label">End Date/Time *</label>
                            <input type="datetime-local" class="form-control" id="end" name="end" required>
                            <div class="invalid-feedback">Please select an end date and time.</div>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="location" class="form-label">Notes</label>
                    <input type="text" class="form-control" id="location" name="location">
                </div>

                <div class="form-group">
                    <label for="color" class="form-label">Event Color</label>
                    <input type="color" class="form-control" id="color" name="color" value="#3788d8">
                </div>

                <div class="form-group">
                    <div class="form-check">
                        <input type="checkbox" class="form-check-input" id="allDay" name="allDay" value="true">
                        <label class="form-check-label" for="allDay">All Day Event</label>
                    </div>
                </div>

                <div class="form-group mt-4">
                    <button type="submit" class="btn btn-primary">Create Event</button>
                    <a href="/abc/EventProcess/calendar" class="btn btn-secondary">Cancel</a>
                </div>
            </form>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        document.addEventListener('DOMContentLoaded', function() {
            const form = document.getElementById('eventForm');
            
            form.addEventListener('submit', function(e) {
                if (!form.checkValidity()) {
                    e.preventDefault();
                    e.stopPropagation();
                    form.classList.add('was-validated');
                    return;
                }
                
                // Form will submit normally to the server
            });
        });
    </script>
</body>
</html> 
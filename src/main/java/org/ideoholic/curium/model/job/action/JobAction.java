/**
 *
 */
package org.ideoholic.curium.model.job.action;

import org.ideoholic.curium.model.employee.action.EmployeeActionAdapter;
import org.ideoholic.curium.model.job.service.JobService;
import org.ideoholic.curium.model.student.action.StudentActionAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Musaib_2
 *
 */
@Controller
@RequestMapping("/JobProcess")
public class JobAction {

    @Autowired
    HttpServletRequest request;
    @Autowired
    HttpServletResponse response;
    @Autowired
    HttpSession httpSession;
    @Autowired
    EmployeeActionAdapter employeeActionAdapter;
    @Autowired
    private JobActionAdapter jobActionAdapter;
    @Autowired
    private StudentActionAdapter studentActionAdapter;


    @PostMapping("/download")
    private String download() {
        if(jobActionAdapter.download()) {
            return "exportsuccessquery";
        }
        return "exportfailure";
    }

    @PostMapping("/exportQueriesReport")
    private String exportQueriesReport() {
        jobActionAdapter.exportQueriesReport();
        return "exportsuccessquery";
    }

    @PostMapping("/feedback")
    private String feedback() {

        if(jobActionAdapter.feedback()) {
            return "feedbackthankyou";
        }else {
            return "feedbackthankyoufail";
        }
    }

    @PostMapping("/printQueriesReport")
    private String printQueriesReport() {
        return "printqueriesreport";
    }

    @PostMapping("/generateQueriesReport")
    private String generateQueriesReport() {
    	jobActionAdapter.generateQueriesReport();
        return queryReport();
    }

    @GetMapping("/queryReport")
    private String queryReport() {
        employeeActionAdapter.ViewAllEmployee();
        return "queriesreport";
    }

    @RequestMapping(value = "/viewAllQueriesDepartmentWise", method = { RequestMethod.GET, RequestMethod.POST })
    private String viewAllQueriesDepartmentWise() {

        if(jobActionAdapter.viewAllQueriesDepartmentWise()){
            return "queries";
        }else{
            return "error";
        }
    }

    @PostMapping("/updateQueries")
    private String updateQueries() {
    	jobActionAdapter.updateQueries();
        return viewAllQueries();
    }

    @PostMapping("/updateQueryRemarks")
    private String updateQueryRemarks() {
    	jobActionAdapter.updateQueryRemarks();
        return viewAllQueries();
    }

    @PostMapping("/viewQueryDetails")
    private void viewQueryDetails() {

        try {
        	jobActionAdapter.viewQueryDetails();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @PostMapping("/inProgressQueries")
    private String inProgressQueries() {
    	jobActionAdapter.inProgressQueries();
        return viewAllQueries();
    }

    @PostMapping("/toDoQueries")
    private String toDoQueries() {
    	jobActionAdapter.toDoQueries();
        return viewAllQueries();
    }

    @PostMapping("/cancelQueries")
    private String cancelQueries() {
    	jobActionAdapter.cancelQueries();
        return viewAllQueries();
    }

    @PostMapping("/completeQueries")
    private String completeQueries() {
    	jobActionAdapter.completeQueries();
        return viewAllQueries();
    }

    @RequestMapping(value = "/CreateQuery", method = { RequestMethod.GET, RequestMethod.POST })
    private String createQuery() {
        String result = "error";

        if(httpSession.getAttribute("userType").toString().equalsIgnoreCase("admin")) {
            employeeActionAdapter.viewDetailsEmployee();
            employeeActionAdapter.ViewAllEmployee();
            result = "createquery";
        }else if(httpSession.getAttribute("userType").toString().equalsIgnoreCase("teacher")) {
            employeeActionAdapter.viewDetailsEmployeeStaffLogin();
            result = "createqueryteacher";
        }

        return result;
    }

    @RequestMapping(value = "/viewAllQueries", method = { RequestMethod.GET, RequestMethod.POST })
    private String viewAllQueries() {

        boolean result = false;

        if(httpSession.getAttribute("userType").toString().equalsIgnoreCase("admin")) {
            result = jobActionAdapter.viewAllQueries();
        }else if(httpSession.getAttribute("userType").toString().equalsIgnoreCase("teacher")) {
            result = jobActionAdapter.viewAllQueriesDepartmentWise();
        }else if(httpSession.getAttribute("userType").toString().equalsIgnoreCase("reception")) {
            result = jobActionAdapter.viewAllQueries();
            return "queriesreadonly";
        }else {
            result = jobActionAdapter.viewAllQueries();
        }

        if(result){
            return "queries";
        }else{
            return "error";
        }
    }

    @PostMapping("/addQuery")
    private String addQuery() {

        if(jobActionAdapter.addQuery()){
            return "querysuccess";
        }else{
            return "error";
        }
    }


    @RequestMapping(value = "/viewAllTasks", method = { RequestMethod.GET, RequestMethod.POST })
    private String viewAllTasks() {

        boolean result = false;

        if(httpSession.getAttribute("userType").toString().equalsIgnoreCase("admin")) {
            result = jobActionAdapter.viewAllTasks();
        }else if(httpSession.getAttribute("userType").toString().equalsIgnoreCase("teacher")) {
            result = jobActionAdapter.viewAllTasksDepartmentWise();
        }else if(httpSession.getAttribute("userType").toString().equalsIgnoreCase("reception")) {
            result = jobActionAdapter.viewAllTasks();
            return "viewalltasksreadonly";
        }else {
            result = jobActionAdapter.viewAllTasks();
        }

        if(result){
            return "viewalltasks";
        }else{
            return "error";
        }
    }

    @PostMapping("/ViewTaskDetails")
    private String viewTaskDetails() {

        if(jobActionAdapter.viewTaskDetails()){
            return "tasks";
        }else{
            return "error";
        }
    }

    @PostMapping("/viewOneJobDetails")
    private String viewOneJobDetails() {

        if(jobActionAdapter.viewOneJobDetails()){
            return "queries";
        }else{
            return "error";
        }
    }

    @PostMapping("/inProgressTasks")
    private String inProgressTasks() {
    	jobActionAdapter.inProgressTasks();
        String displayType = request.getParameter("display").toString();

        if(displayType.equalsIgnoreCase("viewall")) {
            return viewAllTasks();
        }else {
            return viewTaskDetails();
        }

    }

    @PostMapping("/toDoTasks")
    private String toDoTasks() {
        new JobService(request, response).toDoTasks();
        String displayType = request.getParameter("display").toString();

        if(displayType.equalsIgnoreCase("viewall")) {
            return viewAllTasks();
        }else {
            return viewTaskDetails();
        }
    }

    @PostMapping("/cancelTasks")
    private String cancelTasks() {
        new JobService(request, response).cancelTasks();
        String displayType = request.getParameter("display").toString();

        if(displayType.equalsIgnoreCase("viewall")) {
            return viewAllTasks();
        }else {
            return viewTaskDetails();
        }
    }

    @PostMapping("/completeTasks")
    private String completeTasks() {
        new JobService(request, response).completeTasks();
        String displayType = request.getParameter("display").toString();

        if(displayType.equalsIgnoreCase("viewall")) {
            return viewAllTasks();
        }else {
            return viewTaskDetails();
        }
    }

    @PostMapping("/updateTaskRemarks")
    private String updateTaskRemarks() {
    	jobActionAdapter.updateQueryRemarks();
        String displayType = request.getParameter("display").toString();

        if(displayType.equalsIgnoreCase("viewall")) {
            return viewAllTasks();
        }else {
            return viewTaskDetails();
        }
    }


    @PostMapping("/CreateTask")
    private String createTask() {

        String result = "error";

        if(httpSession.getAttribute("userType").toString().equalsIgnoreCase("admin")) {
            employeeActionAdapter.ViewAllEmployee();
            new JobService(request, response).createTask();
            result = "createtask";
        }else if(httpSession.getAttribute("userType").toString().equalsIgnoreCase("teacher")) {
            employeeActionAdapter.viewDetailsEmployeeStaffLogin();
            result = "createtaskteacher";
        }

        return result;
    }


    @PostMapping("/addTask")
    private String addTask() {

        if(new JobService(request, response).addTask()){
            return "querysuccess";
        }else{
            return "error";
        }
    }

    @GetMapping("/taskReport")
    private String taskReport() {
        employeeActionAdapter.ViewAllEmployee();
        studentActionAdapter.viewAllStudentsList();
        return "tasksreport";
    }

    @PostMapping("/generateTasksReport")
    private String generateTasksReport() {
        new JobService(request, response).generateTasksReport();
        return taskReport();
    }

    @PostMapping("/printTasksReport")
    private String printTasksReport() {
        return "printtasksreport";
    }

    @GetMapping("/viewReferredby")
    public void mrvDetails() {
        try {
            new JobService(request, response).getReferredbyDetails();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

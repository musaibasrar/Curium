package org.ideoholic.curium.model.std.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.std.dto.ClassDto;
import org.ideoholic.curium.model.std.dto.ClassIdsDto;
import org.ideoholic.curium.model.std.dto.StdOfClassDto;
import org.ideoholic.curium.model.std.dto.UpperLowerClassDto;
import org.ideoholic.curium.model.std.service.StandardService;
import org.ideoholic.curium.model.student.dto.StudentIdsDto;
import org.ideoholic.curium.dao.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StandardActionAdapter {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpSession httpSession;
    
    @Autowired
    private StandardService standardService;

    private String BRANCHID = "branchid";
    private String USERID = "userloginid";
    private String CURRENTACADEMICYEAR = "currentacademicyear";


    public boolean viewClasses() {

        ResultResponse resultResponse = standardService.viewClasses(httpSession.getAttribute(BRANCHID).toString());
        httpSession.setAttribute("classdetailslist", resultResponse.getResultList());
        return resultResponse.isSuccess();
    }

    public void restoreMultipleLeftout() {
        
        StudentIdsDto dto = new StudentIdsDto();
        dto.setStudentIds(request.getParameterValues("studentIDs"));
        standardService.restoreMultipleLeftout(dto);
    }

    public void restoreMultipleGraduate() {
        
        StudentIdsDto dto = new StudentIdsDto();
        dto.setStudentIds(request.getParameterValues("studentIDs"));
        standardService.restoreMultipleGraduate(dto);
    }

    public void restoreMultipleDroppedout() {
        
        StudentIdsDto dto = new StudentIdsDto();
        dto.setStudentIds(request.getParameterValues("studentIDs"));
        standardService.restoreMultipleDroppedout(dto);
    }

    public void viewGraduated() {
        
        ResultResponse resultResponse = standardService.viewGraduated();
        request.setAttribute("studentListGraduated", resultResponse.getResultList());
    }

    public void viewDropped() {
        
        ResultResponse resultResponse = standardService.viewDropped();
        request.setAttribute("studentListDropped", resultResponse.getResultList());
    }

    public boolean graduateMultiple() {
        
        StudentIdsDto dto = new StudentIdsDto();
        dto.setStudentIds(request.getParameterValues("studentIDs"));

        ResultResponse resultResponse = standardService.graduateMultiple(dto);
        return resultResponse.isSuccess();
    }

    public boolean droppedoutMultiple() {
        
        StudentIdsDto dto = new StudentIdsDto();
        dto.setStudentIds(request.getParameterValues("studentIDs"));

        ResultResponse resultResponse = standardService.droppedoutMultiple(dto);
        return resultResponse.isSuccess();
    }

    public void addClassHierarchy() {
        
        UpperLowerClassDto dto = new UpperLowerClassDto();
        dto.setLowerClass(request.getParameter("lowerclass"));
        dto.setUpperClass(request.getParameter("upperclass"));

        standardService.addClassHierarchy(dto, httpSession.getAttribute(BRANCHID).toString(), httpSession.getAttribute(USERID).toString());
    }

    public void deleteClassHierarchy() {
        
        ClassIdsDto dto = new ClassIdsDto();
        dto.setClassIds(request.getParameterValues("idclasshierarchy"));

        standardService.deleteClassHierarchy(dto, httpSession.getAttribute(BRANCHID).toString());
    }

    public boolean createClass() {
        
        ClassDto classDto = new ClassDto();
        classDto.setClassDetails(request.getParameter("classdetails"));
        classDto.setSection(request.getParameter("section"));

        ResultResponse resultResponse = standardService.createClass(classDto, httpSession.getAttribute(BRANCHID).toString(), httpSession.getAttribute(USERID).toString());
        return resultResponse.isSuccess();
    }

    public boolean deleteClasses() {
                ClassIdsDto dto = new ClassIdsDto();
        dto.setClassIds(request.getParameterValues("classids"));

        ResultResponse resultResponse = standardService.deleteClasses(dto, httpSession.getAttribute(BRANCHID).toString());
        return resultResponse.isSuccess();
    }

    public void searchByClass() {
        
        StdOfClassDto dto = new StdOfClassDto();
        dto.setClassOfStd(request.getParameter("classofstd"));

        ResultResponse resultResponse = standardService.searchByClass(dto, httpSession.getAttribute(BRANCHID).toString(), httpSession.getAttribute(CURRENTACADEMICYEAR).toString());
        request.setAttribute("studentList", resultResponse.getResultList());
    }

    public boolean leftoutMultiple() {
        
        StudentIdsDto dto = new StudentIdsDto();
        dto.setStudentIds(request.getParameterValues("studentIDs"));

        ResultResponse resultResponse = standardService.leftoutMultiple(dto);
        return resultResponse.isSuccess();
    }

    public void viewClassHierarchy() {
        
        ResultResponse resultResponse = standardService.viewClassHierarchy(httpSession.getAttribute(BRANCHID).toString());
        request.setAttribute("classhierarchy", resultResponse.getResultList());
    }

    public void viewleft() {
        
        ResultResponse resultResponse = standardService.viewleft();
        request.setAttribute("studentListLeft", resultResponse.getResultList());
    }
}

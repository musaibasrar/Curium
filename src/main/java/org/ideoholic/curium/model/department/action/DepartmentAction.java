package org.ideoholic.curium.model.department.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/DepartmentProcess")
public class DepartmentAction {

    @Autowired
    private DepartmentActionAdapter departmentActionAdapter;


    @PostMapping("/deleteMultiple")
    public String deleteMultiple() {
        departmentActionAdapter.deleteMultiple();
        return departmentView();
    }

    @GetMapping("/departmentView")
    public String departmentView() {
        departmentActionAdapter.viewDepartment();
        System.out.println("IN action's department view");
        return "department";
    }

    @PostMapping("/addDepartment")
    public String addDepartment() {

        departmentActionAdapter.addDepartment();
        System.out.println("IN action's add department");
        return departmentView();
    }

}

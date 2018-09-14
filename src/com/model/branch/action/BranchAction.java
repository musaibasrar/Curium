package com.model.branch.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.model.branch.service.BranchService;

public class BranchAction {

    HttpServletRequest request;
    HttpServletResponse response;
    HttpSession httpSession;
    String url;
    private static final Logger logger = LogManager.getLogger(BranchAction.class);

    public BranchAction(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.httpSession = request.getSession();

    }

    public String execute(String action) {
        if (action.equalsIgnoreCase("viewBranches")) {
            logger.info("View Branches");
            url = viewBranches();
        } else if (action.equalsIgnoreCase("addBranches")) {
            logger.info("add Branches");
            url = addBranches();
        } else if (action.equalsIgnoreCase("viewDistricts")) {
            logger.info("View Districts");
            url = viewDistricts();
        } else if (action.equalsIgnoreCase("addDistricts")) {
            logger.info("Add Districts");
            url = addDistricts();
        } else if (action.equalsIgnoreCase("updateMultiple")) {
            logger.info("update Districts");
            url = updateDistricts();
        } else if (action.equalsIgnoreCase("deleteMultiple")) {
            logger.info("delete Districts");
            url = deleteDistricts();
        } else if (action.equalsIgnoreCase("deleteMultipleCenters")) {
            logger.info("delete Centers");
            url = deleteCenters();
        } else if (action.equalsIgnoreCase("updateMultipleCenters")) {
            logger.info("update Centers");
            url = updateCenters();
        }else if (action.equalsIgnoreCase("printCenters")) {
            logger.info("print Centers");
            url = printCenters();
        }
        return url;
    }

    private String printCenters() {
        return "printcenters.jsp";
    }

    private String addBranches() {
        new BranchService(request, response).addBranches();
        return "Controller?process=BranchProcess&action=viewBranches";
    }

    private String updateCenters() {
        new BranchService(request, response).updateCenters();
        return "Controller?process=BranchProcess&action=viewBranches";
    }

    private String deleteCenters() {
        new BranchService(request, response).deleteCenters();
        return "Controller?process=BranchProcess&action=viewBranches";
    }

    private String deleteDistricts() {
        new BranchService(request, response).deleteDistricts();
        return "Controller?process=BranchProcess&action=viewDistricts";
    }

    private String updateDistricts() {
        new BranchService(request, response).updateDistricts();
        return "Controller?process=BranchProcess&action=viewDistricts";
    }

    private String addDistricts() {
        new BranchService(request, response).addDistricts();
        return "Controller?process=BranchProcess&action=viewDistricts";
    }

    private String viewDistricts() {
        new BranchService(request, response).viewDistricts();
        return "districtdetails.jsp";
    }

    private String viewBranches() {
        new BranchService(request, response).viewBranches();
        new BranchService(request, response).viewDistricts();
        return "centerdetails.jsp";
    }

}

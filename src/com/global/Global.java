package com.global;

import java.util.HashMap;
import java.util.Map;

import com.model.parents.dto.Parents;

public class Global {

    public Global() {
        
    }
    
    public Global(Map<Parents, String> studentsReports) {
        this.studentsReports = studentsReports;
    }


    private Map<Parents,String> studentsReports = new HashMap<Parents,String>();

    
    public Map<Parents, String> getStudentsReports() {
        return this.studentsReports;
    }

    
    public void setStudentsReports(Map<Parents, String> studentsReports) {
        this.studentsReports = studentsReports;
    }
}

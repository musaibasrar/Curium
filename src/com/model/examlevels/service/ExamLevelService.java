package com.model.examlevels.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.model.branch.dao.BranchDAO;
import com.model.branch.dto.Branch;
import com.model.branch.service.BranchService;
import com.model.examlevels.dao.ExamLevelDetailsDAO;
import com.model.examlevels.dto.Examleveldetails;
import com.model.examlevels.dto.Subexamlevel;
import com.model.student.dao.studentDetailsDAO;
import com.model.student.dto.Student;
import com.util.DataUtil;

public class ExamLevelService {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession httpSession;
	private static final Logger logger = LogManager.getLogger(ExamLevelService.class);
	
	public ExamLevelService(HttpServletRequest request,
			HttpServletResponse response) {

		this.request = request;
		this.response = response;
		this.httpSession = request.getSession();

	}

    public void examLevels() {
        
        try {
            List<Examleveldetails> list = new ExamLevelDetailsDAO().readListOfObjects();
            request.setAttribute("examleveldetails", list);
            logger.info("exam level details List "+list.size());
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        
    }

    public void addExamLevels() {

        Examleveldetails examLevel = new Examleveldetails();
        boolean result = false;
        examLevel.setLevelcode(DataUtil.emptyString(request.getParameter("levelcode")));
        examLevel.setLevelname(DataUtil.emptyString(request.getParameter("levelname")));
        examLevel.setDuration(DataUtil.emptyString(request.getParameter("duration")));
        

        if (!examLevel.getLevelname().equalsIgnoreCase("")) {
            result = new ExamLevelDetailsDAO().addExamLevels(examLevel);
        }
        request.setAttribute("levelsave", result);
    }

    public void deleteMultipleExamLevels() {
        
        String[] branchIds = request.getParameterValues("examlevelids");
        if(branchIds!=null){
        
       List<Integer> ids = new ArrayList<Integer>();
       for (String id : branchIds) {
           String[] brId = id.split(":");
           ids.add(Integer.valueOf(brId[0]));
       }
       boolean result = new ExamLevelDetailsDAO().deleteMultipleExamLevels(ids);
       request.setAttribute("leveldelete", result);
        }
        
    }

    public void updateMultipleExamLevels() {
        
        String[] examLevelIds = request.getParameterValues("examlevelids");
        String[] levelCode = request.getParameterValues("updatelevelcode");
        String[] levelNames = request.getParameterValues("updatelevelname");
        String[] duration = request.getParameterValues("updateduration");

        if(examLevelIds!=null){
            
            List<Examleveldetails> examLevelList = new ArrayList<Examleveldetails>();
            
            for(int i=0; i<examLevelIds.length;i++) {
                Examleveldetails examLevel = new Examleveldetails();
                String[] exId = examLevelIds[i].split(":");
                examLevel.setIdexamlevel(Integer.valueOf(exId[0]));
                examLevel.setLevelcode(levelCode[Integer.valueOf(exId[1])]);
                examLevel.setLevelname(levelNames[Integer.valueOf(exId[1])]);
                examLevel.setDuration(duration[Integer.valueOf(exId[1])]);
                examLevelList.add(examLevel);
            }
            boolean result = new ExamLevelDetailsDAO().updateMultipleExamLevels(examLevelList);
            request.setAttribute("levelupdate", result);
        }
        
    }

    public void getAdmissionNo() throws IOException {
        
        String admissionNumber = null;   
        String currentAcademicYear = null;
        
        if("".equalsIgnoreCase(DataUtil.emptyString(request.getParameter("admissionyear")))) {
            currentAcademicYear = (String) httpSession.getAttribute("currentAcademicYear"); 
        }else {
            currentAcademicYear = request.getParameter("admissionyear");
        }
        
        String subAcademicYear = currentAcademicYear.substring(2, 4);
        List<Student> lastSid = new studentDetailsDAO().getListStudents("From Student where examlevel='"+request.getParameter("examlevel")+"'"
                + " and centercode='"+request.getParameter("centercode")+"' and admissionnumber like '"+subAcademicYear+"%' order by sid DESC");
        int admission = 1;
        if(lastSid.size() > 0) {
            //admission = lastSid.get(0).getSid();
            admission = Integer.parseInt(lastSid.get(0).getAdmissionnumber().substring(lastSid.get(0).getAdmissionnumber().length()-3));
            admission++;
        }
        
        admissionNumber = currentAcademicYear.substring(2, 4)+request.getParameter("centercode")+request.getParameter("examlevel")+String.format("%03d", admission);
        System.out.println("AdmissionNumber "+admissionNumber);

        String districtName = new BranchService(request, response).getDistrictName();
        
        PrintWriter out = response.getWriter(); 
        response.setContentType("text/plain");
        response.setHeader("Cache-Control", "no-cache");
        try {
        //String buffer = "<input name='admno' type='text' class='textField' id='admno' size='36' value='"+admissionNumber+"' >";
        String buffer = admissionNumber+"$"+districtName;
        response.getWriter().println(buffer);
                
        } catch (Exception e) {
            out.write("<input name='admno' type='text' class='textField' id='admno' size='36' value='' >");
        } finally {
            out.flush();
            out.close();
        }
        
    }

    public List<Subexamlevel> getSubExamLevelSubject(String examLevel) {
        return new ExamLevelDetailsDAO().getSubExamLevelSubject(examLevel);
    }

    public List<Examleveldetails> getExamLevelDetails(String examLevel) {
        return new ExamLevelDetailsDAO().getExamLevelDetails(examLevel);
    }
        
}

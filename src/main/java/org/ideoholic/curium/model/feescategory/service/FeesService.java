package org.ideoholic.curium.model.feescategory.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.academicyear.dao.YearDAO;
import org.ideoholic.curium.model.academicyear.dto.Currentacademicyear;
import org.ideoholic.curium.model.feescategory.dao.feesCategoryDAO;
import org.ideoholic.curium.model.feescategory.dto.Concession;
import org.ideoholic.curium.model.feescategory.dto.Feescategory;
import org.ideoholic.curium.model.feescollection.dao.feesCollectionDAO;
import org.ideoholic.curium.model.feesdetails.dao.feesDetailsDAO;
import org.ideoholic.curium.model.mess.card.dto.Card;
import org.ideoholic.curium.model.parents.dto.Parents;
import org.ideoholic.curium.model.student.dao.studentDetailsDAO;
import org.ideoholic.curium.model.student.dto.Student;
import org.ideoholic.curium.model.student.dto.Studentfeesstructure;
import org.ideoholic.curium.util.DataUtil;

public class FeesService {
        
                private HttpServletRequest request;
            private HttpServletResponse response;
            private HttpSession httpSession;
            private String BRANCHID = "branchid";
            /**
             * Size of a byte buffer to read/write file
             */
            private static final int BUFFER_SIZE = 4096;
        
        public FeesService(HttpServletRequest request, HttpServletResponse response) {
                this.request = request;
       this.response = response;
       this.httpSession = request.getSession();
        }


        public boolean viewFees() {
                
                 boolean result = false;
                 
                 if(httpSession.getAttribute(BRANCHID)!=null){
                         try {
                                List<Feescategory> list = new feesCategoryDAO().readListOfObjects(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()),httpSession.getAttribute("currentAcademicYear").toString());
                            httpSession.setAttribute("feescategory", list);
                            result = true;
                        } catch (Exception e) {
                            e.printStackTrace();
                            result = false;
                        }
                 }
                return result;
        }


        public void addFeesParticular() {
                
                Feescategory feescategory = new Feescategory();
                
                if(httpSession.getAttribute(BRANCHID)!=null){
                        
                        feescategory.setFeescategoryname(DataUtil.emptyString(request.getParameter("feescategory")));
                        if(!DataUtil.emptyString(request.getParameter("fromclass")).equalsIgnoreCase("ALL") && !DataUtil.emptyString(request.getParameter("toclass")).equalsIgnoreCase("ALL")){
                                feescategory.setParticularname(DataUtil.emptyString(request.getParameter("fromclass"))+"-"+DataUtil.emptyString(request.getParameter("toclass")));
                        }else{
                                feescategory.setParticularname(DataUtil.emptyString(request.getParameter("fromclass")));
                        }
                        
                        feescategory.setAmount(DataUtil.parseInt(request.getParameter("amount")));
                        feescategory.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
                        feescategory.setUserid(Integer.parseInt(httpSession.getAttribute("userloginid").toString()));
                        feescategory.setAcademicyear(httpSession.getAttribute("currentAcademicYear").toString());
                        if(!feescategory.getFeescategoryname().equalsIgnoreCase("") && !feescategory.getParticularname().equalsIgnoreCase("") && feescategory.getAmount() != 0 ){
                                feescategory =  new feesCategoryDAO().create(feescategory);
                        }
                }
        }


        public void deleteMultiple() {
                 String[] idfeescategory = request.getParameterValues("idfeescategory");
                 if(idfeescategory!=null){
                List<Integer> ids = new ArrayList();
                for (String id : idfeescategory) {
                    System.out.println("id" + id);
                    ids.add(Integer.valueOf(id));
                }
                new feesCategoryDAO().deleteMultiple(ids);
                 }
        }


        public boolean viewAllStudentsList() {

                boolean result = false;
                try {
                        List<Parents> list = new feesDetailsDAO().readListOfStudents(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
                        request.setAttribute("studentListFeesCollection", list);
                        result = true;
                } catch (Exception e) {
                        result = false;
                }
                return result;
        }


        public boolean downlaodFile() {
                boolean result = false;
                try {

                        File downloadFile = new File(System.getProperty("java.io.tmpdir")+"/feesdetails.xlsx");
                FileInputStream inStream = new FileInputStream(downloadFile);

                // get MIME type of the file
                        String mimeType = "application/vnd.ms-excel";

                        // set content attributes for the response
                        response.setContentType(mimeType);
                        // response.setContentLength((int) bis.length());

                        // set headers for the response
                        String headerKey = "Content-Disposition";
                        String headerValue = String.format("attachment; filename=\"%s\"",
                                        "feesdetails.xlsx");
                        response.setHeader(headerKey, headerValue);

                        // get output stream of the response
                        OutputStream outStream = response.getOutputStream();

                        byte[] buffer = new byte[BUFFER_SIZE];
                        int bytesRead = -1;

                        // write bytes read from the input stream into the output stream
                        while ((bytesRead = inStream.read(buffer)) != -1) {
                                outStream.write(buffer, 0, bytesRead);
                        }

                        inStream.close();
                        outStream.close();
                        result = true;
                } catch (Exception e) {
                        System.out.println("" + e);
                }
                return result;
        }


        public String deleteFeesCategory() {
                
                 String[] idfeescategory = request.getParameterValues("sfsid");
                 List<Integer> sfsId = new ArrayList();
                 List<Integer> feesCatId = new ArrayList();
                 
                 String studentId = request.getParameter("id");
                 
                 if(idfeescategory!=null){
                         
                         for (String string : idfeescategory) {
                                 String[] test = string.split("_");
                                 sfsId.add(Integer.valueOf(test[0]));
                                 feesCatId.add(Integer.valueOf(test[1]));
                        }
                new feesCategoryDAO().deleteFeesCategory(sfsId,feesCatId,studentId);
                
                return studentId;
                 }
                throw new IllegalArgumentException("Fees category for the given student does not exist");
                
        }


    public void viewAllBranchStudents() {
        
        try {
                List<Student> list = new feesDetailsDAO().readListOfAllBranchStudents();
                request.setAttribute("studentListFeesCollection", list);
        } catch (Exception e) {
        }
        
    }


	/*
	 * public String waiveOffFeesOld() {
	 * 
	 * String[] idfeescategory = request.getParameterValues("sfsid"); List<Integer>
	 * sfsId = new ArrayList(); List<Integer> feesCatId = new ArrayList();
	 * 
	 * String studentId = request.getParameter("id");
	 * 
	 * if(idfeescategory!=null){
	 * 
	 * for (String string : idfeescategory) { String[] test = string.split("_");
	 * sfsId.add(Integer.valueOf(test[0])); feesCatId.add(Integer.valueOf(test[1]));
	 * } new feesCategoryDAO().waiveOffFees(sfsId,feesCatId,studentId);
	 * 
	 * return
	 * "Controller?process=StudentProcess&action=ViewFeesStructure&id="+studentId; }
	 * 
	 * return "error.jsp";
	 * 
	 * }
	 */
    
    public String waiveOffFees() {
        
        String[] idfeescategory = request.getParameterValues("sfsid");
        List<Integer> sfsId = new ArrayList<Integer>();
        List<Integer> feesCatId = new ArrayList<Integer>();
        List<String> consession = new ArrayList<String>();
        List<Concession> concessionList = new ArrayList<Concession>();
        
        String studentId = request.getParameter("id");
        
        if(idfeescategory!=null){
                
                for (String string : idfeescategory) {
                	
                		Concession con = new Concession();
                		String[] test = string.split("_");
                        sfsId.add(Integer.valueOf(test[0]));
                		String dueAmount = request.getParameter("dueamount:"+Integer.valueOf(test[0]));
                        //String concessionAmount = request.getParameter("waiveoff:"+Integer.valueOf(test[0]));
                        
                        	feesCatId.add(Integer.valueOf(test[1]));
                            con.setSfsid(Integer.valueOf(test[0]));
                            con.setFeescatid(Integer.valueOf(test[1]));
                            con.setConcessionOld(request.getParameter("waiveoff:"+Integer.valueOf(test[0])));
                            con.setConcession(dueAmount);
                            concessionList.add(con);
                        
               }
           new feesCategoryDAO().waiveOffFees(concessionList,studentId);
           return studentId;
        }
        
        throw new IllegalArgumentException("Fees category for the given student does not exist");
       
	}


	public void searchFeesWaiveofforConcessionReport(String searchCriteria) {
		
		List<Parents> searchStudentList = new ArrayList<Parents>();
		Map<Parents,List<Studentfeesstructure>> parentsStudentFeesStructure = new HashMap<Parents,List<Studentfeesstructure>>();
		
		if(httpSession.getAttribute("branchid")!=null){
			String queryMain = "From Parents as parents where";
			String studentname = DataUtil.emptyString(request
					.getParameter("namesearch"));

			String addClass = request.getParameter("classsearch");
			String addSec = request.getParameter("secsearch");
			String conClassStudying = "";

			if (!addClass.equalsIgnoreCase("")) {

				conClassStudying = addClass+"--" +"%";

			}
			if (!addSec.equalsIgnoreCase("")) {
				conClassStudying = addClass;
				conClassStudying = conClassStudying+"--"+addSec+"%";
			}

			String classStudying = DataUtil.emptyString(conClassStudying);
			String querySub = "";

			if (!studentname.equalsIgnoreCase("")) {
				querySub = " parents.Student.name like '%" + studentname + "%' AND parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0 AND parents.branchid="+Integer.parseInt(httpSession.getAttribute("branchid").toString());
			}

			if (!classStudying.equalsIgnoreCase("")
					&& !querySub.equalsIgnoreCase("")) {
				querySub = querySub + " AND parents.Student.classstudying like '"
						+ classStudying + "' AND parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0 and parents.branchid="+Integer.parseInt(httpSession.getAttribute("branchid").toString());
			} else if (!classStudying.equalsIgnoreCase("")) {
				querySub = querySub + " parents.Student.classstudying like '"
						+ classStudying + "' AND parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0 and parents.branchid="+Integer.parseInt(httpSession.getAttribute("branchid").toString());
			}

			queryMain = queryMain + querySub;
			/*
			 * queryMain =
			 * "FROM Parents as parents where  parents.Student.dateofbirth = '2006-04-06'"
			 * ;
			 */
			System.out.println("SEARCH QUERY ***** " + queryMain);
			searchStudentList = new studentDetailsDAO().getStudentsList(queryMain);
			List<Integer> studentids = new ArrayList<>(); 
			
			for (Parents parents : searchStudentList) {
				studentids.add(parents.getStudent().getSid());
			}
			Currentacademicyear currentYear = new YearDAO().showYear();
			httpSession.setAttribute("currentyearfromservice",currentYear.getCurrentacademicyear());
			
			List<Studentfeesstructure> listStudentsFeesStructure = new feesCollectionDAO().getStudentsFeesStructure(studentids, currentYear.getCurrentacademicyear(), searchCriteria);
			
			
			for (Parents parents : searchStudentList) {
				
				List<Studentfeesstructure> singleStudent = new ArrayList<Studentfeesstructure>();
				
				for (Studentfeesstructure fees : listStudentsFeesStructure) {
					
					int feeSid = fees.getSid();
					int sid = parents.getStudent().getSid();
						
					if(feeSid == sid) {
								singleStudent.add(fees);
						}
				}
				parentsStudentFeesStructure.put(parents, singleStudent);
				
			}
			
		}
		if("waiveoff".equalsIgnoreCase(searchCriteria)) {
			httpSession.setAttribute("studentsfeesstructuredetailswaiveoff", parentsStudentFeesStructure);
			httpSession.setAttribute("studentsfeesstructuredetailsconcession", null);
		}else if("concession".equalsIgnoreCase(searchCriteria)) {
			httpSession.setAttribute("studentsfeesstructuredetailswaiveoff", null);
			httpSession.setAttribute("studentsfeesstructuredetailsconcession", parentsStudentFeesStructure);
		}
		
		
	}


	public String applyConcession() {
        
        String[] idfeescategory = request.getParameterValues("sfsid");
        List<Integer> sfsId = new ArrayList<Integer>();
        List<Integer> feesCatId = new ArrayList<Integer>();
        List<String> consession = new ArrayList<String>();
        List<Concession> concessionList = new ArrayList<Concession>();
        
        String studentId = request.getParameter("id");
        
        if(idfeescategory!=null){
                
                for (String string : idfeescategory) {
                	
                		Concession con = new Concession();
                		String[] test = string.split("_");
                        sfsId.add(Integer.valueOf(test[0]));
                		String dueAmount = request.getParameter("dueamount:"+Integer.valueOf(test[0]));
                        String concessionAmount = request.getParameter("concession:"+Integer.valueOf(test[0]));
                        
                        if(Integer.parseInt(concessionAmount)<=Integer.parseInt(dueAmount)) {
                        	feesCatId.add(Integer.valueOf(test[1]));
                            con.setSfsid(Integer.valueOf(test[0]));
                            con.setFeescatid(Integer.valueOf(test[1]));
                            con.setConcessionOld(request.getParameter("concessionold:"+Integer.valueOf(test[0])));
                            con.setConcession(request.getParameter("concession:"+Integer.valueOf(test[0])));
                            concessionList.add(con);
                        }
                        
               }
           new feesCategoryDAO().applyConcession(concessionList,studentId);
           return studentId;
        }
        
        throw new IllegalArgumentException("Fees category for the given student does not exist");
       
	}


	public void viewFeesYearly() throws IOException {
        
        String academicYear = request.getParameter("year");
        if(httpSession.getAttribute(BRANCHID)!=null){
        	
                List<Feescategory> list = new feesCategoryDAO().readListOfObjects(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()),academicYear);
                httpSession.setAttribute("feescategory", list);
                PrintWriter out = response.getWriter(); 
       			response.setContentType("text/xml");
       		    response.setHeader("Cache-Control", "no-cache");
       		        try {
       		        	
       		        	if(!list.isEmpty()){
       		        		String buffer = "<div style='overflow:scroll;width:420px; height: 100px;'>";
       		        		/*String buffer = "<select name='subgroupname' style='width: 240px' id='sgname' onchange='dropdowndist();getSSGroup();'>";
       		        		buffer = buffer +  "<option></option>";*/
       			        	for(int i =0; i<list.size();i++){
       			        		buffer = buffer +  "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
       			        				+ "<label class='labelClass' style='font-weight: bold;color:#325F6D'> <input"
       			        				+ "									 type='checkbox' name='feescategory' class='chcktbl' value="+list.get(i).getIdfeescategory()+""
       			        				+ "									size='36'> "+list.get(i).getFeescategoryname()+" : </label> <label style='font-weight: bold;color:#eb6000'>"+list.get(i).getParticularname()+""
       			        				+ "							</label><br>";
       			        	}
       			        	buffer = buffer + "</div>";
       			        	response.getWriter().println(buffer);
       		        	}else{
       		        		String buffer = "<input type='checkbox'  name='chcktbl'>";
       		        		response.getWriter().println(buffer);
       		        	}
       		        	
       		        } catch (Exception e) {
       		            out.write("<subgroup>0</subgroup>");
       		        } finally {
       		            out.flush();
       		            out.close();
       		        }
        }
	}
}

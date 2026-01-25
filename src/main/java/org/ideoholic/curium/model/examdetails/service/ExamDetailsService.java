/**
 *
 */
package org.ideoholic.curium.model.examdetails.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.examdetails.dao.ExamDetailsDAO;
import org.ideoholic.curium.model.examdetails.dto.AddExamDto;
import org.ideoholic.curium.model.examdetails.dto.AddScheduleDto;
import org.ideoholic.curium.model.examdetails.dto.ExamIdsDto;
import org.ideoholic.curium.model.examdetails.dto.ExamScheduleDto;
import org.ideoholic.curium.model.examdetails.dto.ExamScheduleResponseDto;
import org.ideoholic.curium.model.examdetails.dto.Exams;
import org.ideoholic.curium.model.examdetails.dto.ExamsListResponseDto;
import org.ideoholic.curium.model.examdetails.dto.Examschedule;
import org.ideoholic.curium.model.examdetails.dto.HallTicketResponseDto;
import org.ideoholic.curium.model.examdetails.dto.PrintPreviewHallTicketDto;
import org.ideoholic.curium.model.feescategory.dao.feesCategoryDAO;
import org.ideoholic.curium.model.feescategory.dto.Feescategory;
import org.ideoholic.curium.model.feescollection.dto.StudentFeesReport;
import org.ideoholic.curium.model.parents.dto.Parents;
import org.ideoholic.curium.model.student.dao.StudentDetailsDAO;
import org.ideoholic.curium.model.student.dto.Student;
import org.ideoholic.curium.model.student.dto.Studentfeesstructure;
import org.ideoholic.curium.util.DataUtil;
import org.ideoholic.curium.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Musaib_2
 *
 */
@Slf4j
@Service
public class ExamDetailsService {
	
	@Autowired
	private ExamDetailsDAO examDetailsDao;
	
	@Autowired
	private StudentDetailsDAO studentDetailsDao;

	public ResultResponse addExam(AddExamDto addExamDto, String branchId) {

		Exams exams = new Exams();

		if (branchId != null) {

			exams.setExamname(DataUtil.emptyString(addExamDto.getExamName()));
			exams.setBranchid(Integer.parseInt(branchId));
			exams = examDetailsDao.addExams(exams);
		}
		if (exams == null) {
			return ResultResponse.builder().success(false).build();
		}
		return ResultResponse.builder().success(true).build();
	}


    public ExamsListResponseDto readListOfExams(String branchId) {
        ExamsListResponseDto examsListResponseDto = new ExamsListResponseDto();
        if (branchId != null) {

            List<Exams> exams  = examDetailsDao.readListOfExams(Integer.parseInt(branchId));
            examsListResponseDto.setExams(exams);
            examsListResponseDto.setSuccess(true);
            if (exams == null) {
                examsListResponseDto.setSuccess(false);
            }
        }

        return examsListResponseDto;
    }


    public ResultResponse deleteMultiple(ExamIdsDto examIdsDto) {

        String[] examIds = examIdsDto.getExamIds();
        if (examIds != null) {
            List<Integer> ids = new ArrayList<>();
            for (String id : examIds) {
                log.debug("id" + id);
                ids.add(Integer.valueOf(id));

            }
            examDetailsDao.deleteMultiple(ids);
            return ResultResponse.builder().success(true).build();
        } else {
            return ResultResponse.builder().success(false).build();
        }
    }

    public ResultResponse addSchedule(AddScheduleDto addScheduleDto, String branchId) {

        boolean result;
        List<Examschedule> examScheduleList = new ArrayList<>();

        String[] subject = addScheduleDto.getSubject();
        String[] date = addScheduleDto.getDate();
        String[] startTime = addScheduleDto.getStartTime();
        String[] endTime = addScheduleDto.getEndTime();
        String[] classesSelected = addScheduleDto.getClassesSelected();


        if (branchId != null) {

            for (String selectedClass : classesSelected) {

                for (int i = 0; i < subject.length; i++) {
                    Examschedule examschedule = new Examschedule();
                    examschedule.setAcademicyear(DataUtil.emptyString(addScheduleDto.getAcademicyear()));
                    examschedule.setClasses(selectedClass);
                    examschedule.setExamname(DataUtil.emptyString(addScheduleDto.getExam()));
                    examschedule.setDate(DateUtil.dateParserUpdateStd(date[i]));
                    String[] starttimeSplit = startTime[i].split(":");
                    String hours = starttimeSplit[0];
                    String meridian = null;
                    String outputStartTime = null;
                    if (Integer.parseInt(hours) < 12) {
                        outputStartTime = startTime[i];
                        meridian = "AM";
                    } else if (Integer.parseInt(hours) >= 12) {

                        DateFormat df = new SimpleDateFormat("HH:mm");
                        //Date/time pattern of desired output date
                        DateFormat outputformat = new SimpleDateFormat("hh:mm");
                        Date date1;
                        try {
                            //Conversion of input String to date
                            date1 = df.parse(startTime[i]);
                            //old date format to new date format
                            outputStartTime = outputformat.format(date1);
                        } catch (ParseException pe) {
                            pe.printStackTrace();
                        }
                        meridian = "PM";
                    }

                    examschedule.setStarttime(outputStartTime + " " + meridian);
                    String[] endtimeSplit = endTime[i].split(":");
                    String endhours = endtimeSplit[0];
                    String endmeridian = null;
                    String outputEndTime = null;
                    if (Integer.parseInt(endhours) < 12) {
                        outputEndTime = endTime[i];
                        endmeridian = "AM";
                    } else if (Integer.parseInt(endhours) >= 12) {

                        DateFormat df = new SimpleDateFormat("HH:mm");
                        //Date/time pattern of desired output date
                        DateFormat outputformat = new SimpleDateFormat("hh:mm");
                        Date date1;

                        try {
                            //Conversion of input String to date
                            date1 = df.parse(endTime[i]);
                            //old date format to new date format
                            outputEndTime = outputformat.format(date1);
                        } catch (ParseException pe) {
                            pe.printStackTrace();
                        }

                        endmeridian = "PM";
                    }

                    examschedule.setEndtime(outputEndTime + " " + endmeridian);
                    examschedule.setSubject(DataUtil.emptyString(subject[i]));
                    examschedule.setBranchid(Integer.parseInt(branchId));
                    examScheduleList.add(examschedule);
                }
            }
            result = examDetailsDao.addExamSchedule(examScheduleList);
            return ResultResponse.builder().success(result).build();
        }
        return ResultResponse.builder().build();
    }


    public ExamScheduleResponseDto getExamSchedule(String branchId) {

        ExamScheduleResponseDto result = new ExamScheduleResponseDto();

        if (branchId != null) {

            List<Examschedule> exams = examDetailsDao.readListOfExamSchedule(Integer.parseInt(branchId));
            result.setExamschedules(exams);
            result.setSuccess(true);
            if (exams == null) {
                result.setSuccess(false);
            }
        }

        return result;
    }


    public ResultResponse deleteExamSchedule(ExamIdsDto examIdsDto) {

        String[] examIds = examIdsDto.getExamIds();

        if (examIds != null) {
            List<Integer> ids = new ArrayList<>();
            for (String id : examIds) {
                ids.add(Integer.valueOf(id));

            }
            examDetailsDao.deleteExamSchedule(ids);
            return ResultResponse.builder().success(true).build();
        } else {
            return ResultResponse.builder().success(false).build();
        }

    }


    public ExamScheduleResponseDto getExamScheduleDetails(ExamScheduleDto examScheduleDto, String branchId) {
        ExamScheduleResponseDto result = new ExamScheduleResponseDto();

        String academicYear = examScheduleDto.getAcademicYear();
        String classH = examScheduleDto.getClassH();
        String classAdmno = examScheduleDto.getClassAdmno();
        String studentName = examScheduleDto.getStudentName();
        String exam = examScheduleDto.getExam();

        result.setSelectedclass(classH);
        result.setSelectedexam(exam);
        result.setSelectedstudentname(studentName);
        result.setSelectedclassandsec(classAdmno);
        result.setSelectedadmissionno(examScheduleDto.getClassAdmno());


        if (!classAdmno.equals("")) {
            String[] c = classAdmno.split("--");
            classH = c[0];
        }
        if (branchId != null) {

            List<Examschedule> examschedules = examDetailsDao.getExamScheduleDetails(academicYear, classH, exam, Integer.parseInt(branchId));
            result.setExamschedules(examschedules);
            result.setSuccess(true);
            if (!examschedules.isEmpty()) {
                return result;
            }
        }
        return result;
    }


    public HallTicketResponseDto printPreviewHallTicket(PrintPreviewHallTicketDto printPreviewHallTicketDto, String branchId) {
        HallTicketResponseDto result = new HallTicketResponseDto();

        String[] examName = printPreviewHallTicketDto.getExamName();
        String[] classes = printPreviewHallTicketDto.getClasses();
        String[] subject = printPreviewHallTicketDto.getSubject();
        String[] dateOfExam = printPreviewHallTicketDto.getDateOfExam();
        String[] startTime = printPreviewHallTicketDto.getStartTime();
        String[] endTime = printPreviewHallTicketDto.getEndTime();
        String classAndSec = printPreviewHallTicketDto.getClassAndSec();
        String admNo = printPreviewHallTicketDto.getAdmNo();
        String studentName = printPreviewHallTicketDto.getStudentName();
        String academicYear = printPreviewHallTicketDto.getAcademicYear();
        String[] studentIds = printPreviewHallTicketDto.getStudentIds();
        List<Integer> studentIdsList = Arrays.stream(studentIds)
				.map(Integer::parseInt)
					.collect(Collectors.toList());
        
        if (examName != null) {

            List<Parents> studentList = new ArrayList<>();
            List<Examschedule> examscheduleList = new ArrayList<>();
            String classStudying = DataUtil.emptyString(printPreviewHallTicketDto.getClassStudying());
            classStudying = classStudying + "--" + "%";

            if (admNo.equals("")) {
            	studentList = studentDetailsDao.getReferredList(studentIdsList);
                //studentList = new studentDetailsDAO().getStudentsList("from Parents as parents where parents.student.classstudying LIKE '" + classStudying + "' and (parents.student.promotedyear='" + academicYear + "' or parents.student.yearofadmission='" + academicYear + "') and parents.student.archive=0 and parents.student.passedout=0 AND parents.student.droppedout=0 and parents.student.leftout=0 AND parents.student.branchid = " + branchId + " order by parents.student.sid desc");
            } else {
                Parents parent = new Parents();
                Student student = new Student();
                student.setAdmissionnumber(admNo);
                student.setName(studentName);
                student.setClassstudying(classAndSec);
                parent.setStudent(student);
                studentList.add(parent);
            }

            result.setStudentList(studentList);

            for (int i = 0; i < endTime.length; i++) {
                Examschedule exams = new Examschedule();
                exams.setClasses(classes[i]);
                exams.setDate(DateUtil.dateParserUpdateStd(dateOfExam[i]));
                exams.setExamname(examName[i]);
                exams.setSubject(subject[i]);
                exams.setStarttime(startTime[i]);
                exams.setEndtime(endTime[i]);
                examscheduleList.add(exams);
            }
            result.setExamname(examName[0]);
            result.setExamscheduleList(examscheduleList);
            result.setUrlbranchid(branchId);

        }

        return result;
    }
    
    public ResultResponse getStudentsForHallTicket(PrintPreviewHallTicketDto dto, String branchId, String currentAcademicYear) {
		ResultResponse result = ResultResponse.builder().build();

		List<Integer> feesCatList = new ArrayList<>(); 
		

		//Get Students

		List<Parents> searchStudentList = new ArrayList<Parents>();
		List<StudentFeesReport> studentFeesReportList = new ArrayList<StudentFeesReport>();

		if(branchId!=null && currentAcademicYear!=null){

		String queryMain = "From Parents as parents where";
		String[] addClass = dto.getClasses();
		String addSec = dto.getClassAndSec();
		StringBuffer conClassStudying = new StringBuffer();

			int i = 0;
			for (String classOne : addClass) {

				if(i>0) {
					if (!addSec.equalsIgnoreCase("")) {
					conClassStudying.append("' OR parents.Student.classstudying LIKE '"+classOne+"--"+ addSec + "%");
					}else {
					conClassStudying.append("' OR parents.Student.classstudying LIKE '"+classOne+"--"+"%");
					}
					
				}else {
					if (!addSec.equalsIgnoreCase("")) {
					conClassStudying.append(classOne+"--"+ addSec + "%");
					}else {
					conClassStudying.append(classOne+"--"+"%");
					}
				}

				i++;
			}
			
		String classStudying = DataUtil.emptyString(conClassStudying.toString());
		String querySub = "";

		if (!classStudying.equalsIgnoreCase("")) {
			querySub = querySub + " (parents.Student.classstudying like '"
					+ classStudying + "') AND parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0 AND parents.Student.branchid="+Integer.parseInt(branchId)+" order by parents.Student.admissionnumber ASC";
		}

		if(!"".equalsIgnoreCase(querySub)) {
			queryMain = queryMain + querySub;
			searchStudentList = new studentDetailsDAO().getStudentsList(queryMain);
		}

		//End Students
			
		if(dto.getShowFees()!=null && dto.getShowFees().equalsIgnoreCase("showfees")) {
			
		// Start Fees Categories
			List<Feescategory> feecategoryList= new feesCategoryDAO().getfeecategoryofstudent(dto.getClasses()[0], currentAcademicYear, branchId);
			 for (Feescategory CatFeesList : feecategoryList) {
				 feesCatList.add(CatFeesList.getIdfeescategory());
			}
		 
		//End Fees Categories

			for (Parents parents : searchStudentList) {

				Date dateNow = new Date();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		        String formattedDate = dateFormat.format(dateNow);
		        Date cDate = new Date();
		        try {
					cDate = dateFormat.parse(formattedDate);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Date DNDDate = parents.getStudent().getCrecorddate();

				if (DNDDate == null || cDate.compareTo(DNDDate) > 0) {

				StudentFeesReport studentFeesReport = new StudentFeesReport();

				long id = parents.getStudent().getSid();

				List<Studentfeesstructure> feesstructure = new studentDetailsDAO().getStudentFeesStructurebyFeesCategory(id,feesCatList);
				List<Studentfeesstructure> defaulterFeesstructure = new ArrayList<Studentfeesstructure>();
				Long totalDue = 0l;

				for (Studentfeesstructure studentFeesStructure : feesstructure) {

					String[] feesStartMonth = new DataUtil().getPropertiesValue("feesstartmonth").split("/");
					 LocalDate currentDate = LocalDate.now();
				     LocalDate startDate = LocalDate.of(Integer.parseInt(feesStartMonth[2]), Integer.parseInt(feesStartMonth[1]), Integer.parseInt(feesStartMonth[0]));
				     LocalDate endDate = LocalDate.of(currentDate.getYear(), currentDate.getMonthValue(), currentDate.getDayOfMonth()); // Change this to your desired end date

				        // Calculate the period between the start date and end date
				        Period period = Period.between(startDate, endDate);

				        // Calculate the total number of months between the dates
				        int totalMonths = period.getYears() * 12 + period.getMonths();

				        // Add one month if the end date has not passed the start date's day
				        if (endDate.getDayOfMonth() < startDate.getDayOfMonth()) {
				            totalMonths--;
				        }

				        int totalFeesInstallments = studentFeesStructure.getTotalinstallment();
				        int value = 12/totalFeesInstallments;
				        int quotient = totalMonths/value;
				        Long feesPerInstallment = studentFeesStructure.getFeesamount()/totalFeesInstallments;
				        int installmentTillDate = quotient+1;
						Long ActualFeesToBePaid = feesPerInstallment*installmentTillDate;
						Long feesPaid = studentFeesStructure.getFeespaid();
						Long committedFees = studentFeesStructure.getFeesamount()/totalFeesInstallments;

						if(feesPaid < ActualFeesToBePaid) {
							totalDue = totalDue + (ActualFeesToBePaid-feesPaid);
							//studentFeesStructure.setFeespaid(ActualFeesToBePaid-feesPaid); //Using Fees paid as fees due amount as it is not being used elsewhere
							defaulterFeesstructure.add(studentFeesStructure);
						}
				}
				if(defaulterFeesstructure.size()>0) {
					studentFeesReport.setParents(parents);
					studentFeesReport.setStudentFeesStructure(defaulterFeesstructure);
					studentFeesReport.setDueAmount(totalDue);
					studentFeesReportList.add(studentFeesReport);
				}
			}

			}
		}else {
			for (Parents parents : searchStudentList) {
				StudentFeesReport studentFeesReport = new StudentFeesReport();
				studentFeesReport.setParents(parents);
				studentFeesReport.setDueAmount(0l);
				studentFeesReportList.add(studentFeesReport);
			}
		}
			result.setResultList(studentFeesReportList);
			result.setSuccess(true);
		}
		return result;
	  }


}

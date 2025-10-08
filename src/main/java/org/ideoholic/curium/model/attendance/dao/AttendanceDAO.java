package org.ideoholic.curium.model.attendance.dao;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.ideoholic.curium.model.attendance.dto.Attendancemaster;
import org.ideoholic.curium.model.attendance.dto.Holidaysmaster;
import org.ideoholic.curium.model.attendance.dto.Staffdailyattendance;
import org.ideoholic.curium.model.attendance.dto.Studentdailyattendance;
import org.ideoholic.curium.model.attendance.dto.Weeklyoff;
import org.ideoholic.curium.model.employee.dto.Teacher;
import org.ideoholic.curium.model.student.dto.Student;
import org.ideoholic.curium.repositories.AttendanceMasterRepository;
import org.ideoholic.curium.repositories.HolidaysMasterRepository;
import org.ideoholic.curium.repositories.StaffDailyAttendanceRepository;
import org.ideoholic.curium.repositories.StudentDailyAttendanceRepository;
import org.ideoholic.curium.repositories.WeeklyoffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AttendanceDAO {

	@Autowired
	private HolidaysMasterRepository holidayMasterRepo;

	@Autowired
	private WeeklyoffRepository weeklyoffRepo;

	@Autowired
	private AttendanceMasterRepository attendanceMasterRepo;

	@Autowired
	private StudentDailyAttendanceRepository studentDailyAttendanceRepository;
	
	@Autowired
	private StaffDailyAttendanceRepository staffDailyAttendanceRepository;

	@Transactional
	public List<Holidaysmaster> readListOfHolidays(String currentAcademicYear, int branchId) {
		List<Holidaysmaster> holidayMaster = new ArrayList<Holidaysmaster>();
		try{
			holidayMaster = holidayMasterRepo.findByAcademicyearAndBranchid(currentAcademicYear,branchId);
		}catch (Exception e) {
			log.error(e.getMessage(), e);
			e.printStackTrace();

			throw e;
		}

		return holidayMaster;
	}

	@Transactional
	public boolean saveHolidays(Holidaysmaster holidayMaster) {
		try {
			holidayMasterRepo.save(holidayMaster);
			return true;
		} catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
			throw hibernateException;
		}
	}

	@Transactional
	public boolean saveWeeklyOff(Weeklyoff weeklyOff) {
		try{
			weeklyoffRepo.save(weeklyOff);
			return true;
		} catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
			throw hibernateException;
		}
	}

	@Transactional
	public boolean deleteMultiple(List<Integer> holidayIds) {
		try {
			holidayMasterRepo.deleteAllById(holidayIds);
			return true;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			e.printStackTrace();
			throw e;
		}
	}

	@Transactional
	public List<Weeklyoff> readListOfWeekOff(String academicYear, int branchId) {
			List<Weeklyoff> weeklyOff = new ArrayList<Weeklyoff>();
			try{
				weeklyOff = weeklyoffRepo.findByAcademicyearAndBranchid(academicYear, branchId);
			}catch (Exception e) {
				log.error(e.getMessage(), e);
				e.printStackTrace();
				throw e;
			}
		return weeklyOff;
	}

	@Transactional
	public List<Weeklyoff> readListOfWeeklyOff(List<Integer> weeklyOffList, String academicYear, int branchid) {
		List<Weeklyoff> weeklyOff = new ArrayList<Weeklyoff>();
		try{
			weeklyOff = weeklyoffRepo.findByAcademicyearAndBranchidAndWidIn(academicYear, branchid, weeklyOffList);
		}catch (Exception e) {
			log.error(e.getMessage(), e);
			e.printStackTrace();
			throw e;
		}
		return weeklyOff;

	}

	@Transactional
	public List<Weeklyoff> readListOfWeeklyOff(List<Integer> weeklyOffList,
			String academicYear) {
		List<Weeklyoff> weeklyOff = new ArrayList<Weeklyoff>();
		try{

			weeklyOff = weeklyoffRepo.findByAcademicyearAndWidIn(academicYear,weeklyOffList);

		}catch (Exception e) {
			log.error(e.getMessage(), e);
			e.printStackTrace();

			throw  e;
		}

	return weeklyOff;

	}

	@Transactional
    public List<Holidaysmaster> readListOfholidays(List<Integer> holidaysIntList, String currentAcademicYear, int branchId) {
        try {
        	// session.createQuery("From Holidaysmaster where academicyear='"+currentAcademicYear+"' and shid IN (:ids) and branchid="+branchId);
            return holidayMasterRepo.findByAcademicyearAndShidInAndBranchid(currentAcademicYear, holidaysIntList, branchId);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();

            throw e;
        }
    }

	@Transactional
	public List<Holidaysmaster> readListOfholidays(List<Integer> holidaysIntList, String currentAcademicYear) {
		List<Holidaysmaster> holidayMaster = new ArrayList<Holidaysmaster>();
		try{
			// session.createQuery("From Holidaysmaster where academicyear='"+currentAcademicYear+"' and shid IN (:ids) ");
			holidayMaster = holidayMasterRepo.findByAcademicyearAndShidIn(currentAcademicYear, holidaysIntList);
		}catch (Exception e) {
			log.error(e.getMessage(), e);
			e.printStackTrace();

			throw  e;
		}

		return holidayMaster;
	}

	@Transactional
	public boolean addAttendanceMaster(Attendancemaster attendanceMaster) {

		try {
			attendanceMasterRepo.save(attendanceMaster);
			return true;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			e.printStackTrace();

			throw e;
		}
	}

	@Transactional
    public boolean addAttendanceMaster(List<Attendancemaster> attendanceMasterList) {
        try {
        	// session.saveOrUpdate(attendancemaster);
            attendanceMasterRepo.saveAll(attendanceMasterList);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
            
            throw e;
        }
    }


	@Transactional
	public List<Attendancemaster> getAttendanceMasterDetails(String attendeeId, int branchId) {
		List<Attendancemaster> studentAttendanceMaster = new ArrayList<Attendancemaster>();
		try{

			studentAttendanceMaster = attendanceMasterRepo.findByAttendeeidAndBranchid(attendeeId,branchId);

		}catch (Exception e) {
			log.error(e.getMessage(), e);
			e.printStackTrace();

			throw e;
		}
		return studentAttendanceMaster;
	}

	@Transactional
	public List<Attendancemaster> getAttendanceMasterDetails(String attendeeId) {
		List<Attendancemaster> studentAttendanceMaster = new ArrayList<Attendancemaster>();
		try{
			studentAttendanceMaster = attendanceMasterRepo.findByAttendeeid(attendeeId);
		}catch (Exception e) {
			log.error(e.getMessage(), e);
			e.printStackTrace();

			throw e;
		}
		return studentAttendanceMaster;
	}

	@Transactional
	public boolean saveStudentAttendance(List<Studentdailyattendance> listStudentAttendance) {

		try {
			listStudentAttendance.forEach(studentdailyattendance -> {

				// Fetch attendance by attendee ID, current date, and academic year
				// session.createQuery("from Studentdailyattendance  where" + " attendeeid='"+studentdailyattendance.getAttendeeid()+"' and " +	"date= CURDATE() and academicyear = '"+studentdailyattendance.getAcademicyear()+"'");
				Studentdailyattendance existingAttendance = studentDailyAttendanceRepository
						.findByAttendeeStudentexternalidAndDateAndAcademicyear(
								studentdailyattendance.getAttendeeid().toString(),
								LocalDate.now(),
								studentdailyattendance.getAcademicyear()
						).map(attendance -> {
							// session.createSQLQuery("update Studentdailyattendance set attendancestatus = " + "'"+studentdailyattendance.getAttendancestatus()+"' where attendanceid = " + "'"+studentDailyAttendanceDetails.getAttendanceid()+"'");
							attendance.setAttendancestatus(studentdailyattendance.getAttendancestatus());
							return studentDailyAttendanceRepository.save(attendance);
						})
						.orElseGet(() -> {
							return studentDailyAttendanceRepository.save(studentdailyattendance);
						});
			});
			return true;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			e.printStackTrace();
		
			throw e;
		}
	}

	@Transactional
	public List<Studentdailyattendance> readListOfStudentAttendance(String currentAcademicYear, String date, String studentExternalId, int branchId) {

		List<Studentdailyattendance> studentDailyAttendance = new ArrayList<Studentdailyattendance>();

		try{
			// studentDailyAttendance = session.createQuery("from Studentdailyattendance  where date = '"+date+"' and academicyear = '"+currentAcademicYear+"' and attendeeid = '"+studentExternalId+"' and branchid="+branchId).list();
			studentDailyAttendance = studentDailyAttendanceRepository.findByDateAndAcademicyearAndAttendeeStudentexternalidAndBranchid(date, currentAcademicYear, studentExternalId, branchId);
		}catch (Exception e) {
			log.error(e.getMessage(), e);
			e.printStackTrace();
			
			throw e;
		}
		return studentDailyAttendance;
	}

	@Transactional
	public List<Studentdailyattendance> getStudentDailyAttendance(
			String studentExternalId, String fromTimestamp,
			String toTimestamp, String currentAcademicYear, int branchId) {
		List<Studentdailyattendance> studentDailyAttendance = new ArrayList<>();
		try{
			// studentDailyAttendance = session.createQuery("from Studentdailyattendance  where date between '"+fromTimestamp+"' and '"+toTimestamp+"' and academicyear = '"+currentAcademicYear+"' and attendeeid = '"+studentExternalId+"' and branchid="+branchId).list();
			studentDailyAttendance = studentDailyAttendanceRepository.findByDateBetweenAndAcademicyearAndAttendeeStudentexternalidAndBranchid(
					LocalDate.parse(fromTimestamp), LocalDate.parse(toTimestamp), currentAcademicYear,
					studentExternalId, branchId);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			e.printStackTrace();

			throw e;
		}
		return studentDailyAttendance;
	}

	@Transactional
	public boolean updateStudentAttendanceDetails(List<Integer> attendanceIdsList, List<String> studentAttendanceStatusList, String academicYear) {
		try {
			for (int i = 0; i < attendanceIdsList.size(); i++) {

				//Query query = session.createQuery("update Studentdailyattendance set attendancestatus = '"+studentAttendanceStatusList.get(i)+"' where attendanceid = '"+attIn+"'");
				studentDailyAttendanceRepository.updateAttendanceStatusById(attendanceIdsList.get(i), studentAttendanceStatusList.get(i));
			}
			return true;

		}catch (Exception e) {
		log.error(e.getMessage(), e);
		e.printStackTrace();

		throw e;
		}
	}

	@Transactional
	public List<Studentdailyattendance> getStudentDailyAttendanceGraph(
			String studentExternalIdGraph, String timestampFrom,
			String timestampto, String currentAcademicYear, int branchId) {
		List<Studentdailyattendance> studentDailyAttendance = new ArrayList<>();
		try{
			//studentDailyAttendance = session.createQuery("from Studentdailyattendance  where date between '"+timestampFrom+"' and '"+timestampto+"' and academicyear = '"+currentAcademicYear+"' and attendeeid = '"+studentExternalIdGraph+"' and branchid="+branchId).list();
  			studentDailyAttendance = studentDailyAttendanceRepository.findByDateBetweenAndAcademicyearAndAttendeeStudentexternalidAndBranchid(LocalDate.parse(timestampFrom),LocalDate.parse(timestampto),currentAcademicYear, studentExternalIdGraph, branchId);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			e.printStackTrace();

			throw e;
		}
		return studentDailyAttendance;
	}

	@Transactional
	public String checkAndMarkStudentAttendance(List<Studentdailyattendance> studentDailyAttendanceList) {
		
		try {
			for (Studentdailyattendance studentDailyAttendance : studentDailyAttendanceList) {

				//Query query = session.createQuery("from Studentdailyattendance  where attendeeid='"+studentDailyAttendance.getAttendeeid()+"' and date= CURDATE() and academicyear = '"+studentDailyAttendance.getAcademicyear()+"'");

				Optional<Studentdailyattendance> existingAttendance = studentDailyAttendanceRepository
						.findByAttendeeStudentexternalidAndDateAndAcademicyear(studentDailyAttendance.getAttendeeid(), LocalDate.now(), studentDailyAttendance.getAcademicyear());

				if (existingAttendance.isPresent()) {
					return "error-Can't Mark the attendance twice!!!";
				} else {
					studentDailyAttendanceRepository.save(studentDailyAttendance);
				}
			}
			return "success-Attendance has been marked successfully.";
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			e.printStackTrace();

			throw e;
		}
	}

	@Transactional
	public boolean markDailyAttendanceJob(List<Studentdailyattendance> studentDailyAttendance) {
		boolean result = true;

		try{

			for (Studentdailyattendance student : studentDailyAttendance) {
				// Query query = session.createQuery("from Studentdailyattendance where attendeeid = '"+student.getAttendeeid()+"' and academicyear='"+student.getAcademicyear()+"' and date=CURDATE()");
				Studentdailyattendance studentSingle = studentDailyAttendanceRepository.findByAttendeeStudentexternalidAndDateAndAcademicyear(student.getAttendeeid(), LocalDate.now(), student.getAcademicyear())
						.orElse(studentDailyAttendanceRepository.save(student));
				if(studentSingle != null) {
					result &= true;
				}
			}
		}catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
		return result;
	}

	@Transactional
	public Map<String, List<Studentdailyattendance>> readListOfStudentAttendanceExport(
			String currentAcademicYear, String timestampFrom, String timestampto,
			List<Student> searchStudentList, int branchId) {
		
		Map<String, List<Studentdailyattendance>> mapStudentAttendance = new HashMap<String, List<Studentdailyattendance>>();
		
		try{
			
			for (Student student : searchStudentList) {
				List<Studentdailyattendance> studentAttendance = new ArrayList<Studentdailyattendance>();
				// studentAttendance = session.createQuery("from Studentdailyattendance  where date between '"+timestampFrom+"' and '"+timestampto+"' and academicyear = '"+currentAcademicYear+"' and attendeeid = '"+student.getStudentexternalid()+"' and branchid="+branchId).list();
				mapStudentAttendance.put(student.getName(), studentAttendance);
				studentAttendance = studentDailyAttendanceRepository.findByDateBetweenAndAcademicyearAndAttendeeStudentexternalidAndBranchid(
						LocalDate.parse(timestampFrom), LocalDate.parse(timestampto), currentAcademicYear,
						student.getStudentexternalid(), branchId);
			}
		}catch (Exception e) {
			log.error(e.getMessage(), e);
			e.printStackTrace();
			
			throw e;
		}
		return mapStudentAttendance;
	}

	@Transactional
    public boolean saveStaffAttendance(List<Staffdailyattendance> listStaffAttendance) {
        try {
            for (Staffdailyattendance staffdailyattendance : listStaffAttendance) {
            	// session.createQuery("from Staffdailyattendance  where attendeeid='"+staffdailyattendance.getAttendeeid()+"' and date= CURDATE() and academicyear = '"+staffdailyattendance.getAcademicyear()+"'");
                Staffdailyattendance existingAttendance = staffDailyAttendanceRepository
                        .findByAttendeeidAndDateAndAcademicyear(staffdailyattendance.getAttendeeid(),LocalDate.now(), staffdailyattendance.getAcademicyear()).orElse(null);
                
                if (existingAttendance == null) {
                    staffDailyAttendanceRepository.save(staffdailyattendance);
                } else {
                	// session.createSQLQuery("update Staffdailyattendance set attendancestatus = '"+staffdailyattendance.getAttendancestatus()+"' where attendanceid = '"+staffDaily.getAttendanceid()+"'");
                    existingAttendance.setAttendancestatus(staffdailyattendance.getAttendancestatus());
                    staffDailyAttendanceRepository.save(existingAttendance);
                }
            }
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();

			throw e;
        }
    }

	@Transactional
	public List<Staffdailyattendance> readListOfStaffAttendance(String currentAcademicYear,
			Timestamp timestamp, String teacherexternalid, int branchId) {

		List<Staffdailyattendance> staffDailyAttendance = new ArrayList<Staffdailyattendance>();

		try {
			// session.createQuery("from Staffdailyattendance where date = '"+timestamp+"' and academicyear = '"+currentAcademicYear+"' and attendeeid = '"+teacherexternalid+"' and branchid="+branchId).list();
			staffDailyAttendance = staffDailyAttendanceRepository.findByDateAndAcademicyearAndAttendeeidAndBranchid(new Date(timestamp.getTime()), currentAcademicYear, teacherexternalid, branchId);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			log.info(e.getMessage());
			log.debug("column " + e);
			e.printStackTrace();

			throw e;
		}
		return staffDailyAttendance;
	}

	@Transactional
    public boolean updateStaffAttendanceDetails(List<Integer> attendanceIdsList, List<String> staffAttendanceStatusList) {
        try {
            for (int i = 0; i < attendanceIdsList.size(); i++) {
				// session.createSQLQuery("update Staffdailyattendance set attendancestatus = '"+staffAttendanceStatusList.get(i)+"' where attendanceid = '"+attIn+"'");
                Staffdailyattendance attendance = staffDailyAttendanceRepository.findById(attendanceIdsList.get(i)).orElse(null);
                if (attendance != null) {
                    attendance.setAttendancestatus(staffAttendanceStatusList.get(i));
                    staffDailyAttendanceRepository.save(attendance);
                }
            }
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
			log.debug("error "+e);
            e.printStackTrace();

			throw e;
        }
    }

	@Transactional
	public List<Staffdailyattendance> getStaffDailyAttendance(String staffExternalId, Timestamp fromTimestamp,
			Timestamp toTimestamp, String currentAcademicYear, int branchId) {
		List<Staffdailyattendance> staffDailyAttendance = new ArrayList<Staffdailyattendance>();
		try{
			// session.createQuery("from Staffdailyattendance  where date between '"+fromTimestamp+"' and '"+toTimestamp+"' and academicyear = '"+currentAcademicYear+"' and attendeeid = '"+staffExternalId+"' and branchid="+branchId).list();
			staffDailyAttendance = staffDailyAttendanceRepository.findByDateBetweenAndAcademicyearAndAttendeeidAndBranchid(fromTimestamp, toTimestamp, currentAcademicYear, staffExternalId, branchId);
		} catch (Exception e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();

			throw e;
        }
		return staffDailyAttendance;
	}

	@Transactional
	public boolean checkStaffAttendance(List<Staffdailyattendance> staffDailyAttendanceList) {

		boolean result = false;
		try{
	        for (Staffdailyattendance staffDailyAttendance : staffDailyAttendanceList) {
				// session.createQuery("from Staffdailyattendance  where attendeeid='"+staffDailyAttendance.getAttendeeid()+"' and date= CURDATE() and academicyear = '"+staffDailyAttendance.getAcademicyear()+"'");
	            Staffdailyattendance existingAttendance = staffDailyAttendanceRepository
	                    .findByAttendeeidAndDateAndAcademicyear(staffDailyAttendance.getAttendeeid(), LocalDate.now(), staffDailyAttendance.getAcademicyear()).orElse(null);

	            if (existingAttendance == null) {
	                staffDailyAttendanceRepository.save(staffDailyAttendance);
	            } else {
					// session.createSQLQuery("update att_staffdailyattendance set attendancestatus = '"+staffDailyAttendance.getAttendancestatus()+"' where attendanceid = '"+staffDailyAttendanceDetails.getAttendanceid()+"'");
	                existingAttendance.setAttendancestatus(staffDailyAttendance.getAttendancestatus());
	                staffDailyAttendanceRepository.save(existingAttendance);
	            }
	        }
			
			result = true;
		} catch (Exception e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();

			throw e;
        }
		return result;
	}

	@Transactional
	public Map<String, List<Staffdailyattendance>> readListOfStaffAttendanceExport(String currentAcademicYear, Timestamp timestampFrom,
			Timestamp timestampto, List<Teacher> staffList, int branchId) {

		Map<String, List<Staffdailyattendance>> mapStaffAttendance = new HashMap<String, List<Staffdailyattendance>>();
		try{
			
			for (Teacher teacher : staffList) {
				// session.createQuery("from Staffdailyattendance  where date between '"+timestampFrom+"' and '"+timestampto+"' and academicyear = '"+currentAcademicYear+"' and attendeeid = '"+teacher.getTeacherexternalid()+"' and branchid="+branchId).list();
				List<Staffdailyattendance> staffAttendance = staffDailyAttendanceRepository
	                    .findByDateBetweenAndAcademicyearAndAttendeeidAndBranchid(timestampFrom, timestampto, currentAcademicYear, teacher.getTeacherexternalid(), branchId);
				mapStaffAttendance.put(teacher.getTeachername(), staffAttendance);
			}
		} catch (Exception e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();

			throw e;
        }
		return mapStaffAttendance;
		
	}

	@Transactional
	public void markDailyAttendanceJobStaff(List<Staffdailyattendance> listStaffAttendance) {
		
		try{
			for (Staffdailyattendance staff : listStaffAttendance) {
				// session.createQuery("from Staffdailyattendance where attendeeid = '"+staff.getAttendeeid()+"' and academicyear='"+staff.getAcademicyear()+"' and date=CURDATE()");
				Staffdailyattendance staffSingle = staffDailyAttendanceRepository.findByAttendeeidAndDateAndAcademicyear(staff.getAttendeeid(), LocalDate.now(), staff.getAcademicyear()).orElse(null);
				if(staffSingle == null){
					staffDailyAttendanceRepository.save(staff);
				}
			}
		} catch (Exception e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();

			throw e;
        }
	}

	@Transactional
	public List<Studentdailyattendance> getStudentAttendance(String date) {
		List<Studentdailyattendance> studentdailyattendance = new ArrayList<Studentdailyattendance>();
		try{
			// session.createQuery("from Studentdailyattendance  where date = '"+date+"'").list();
			studentdailyattendance = studentDailyAttendanceRepository.findByDate(date);
		} catch (Exception e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();

			throw e;
        }
		return studentdailyattendance;
	}

	@Transactional
	public List<Studentdailyattendance> getStudentClassAttendance(String date, List<String> attendeeIds) {
		List<Studentdailyattendance> studentdailyattendance = new ArrayList<Studentdailyattendance>();
		try{
			// session.createQuery("from Studentdailyattendance  where date = '"+date+"' and attendeeid IN (:ids)");
			studentdailyattendance = studentDailyAttendanceRepository.findByDateAndAttendeeStudentexternalidIn(date, attendeeIds);
		} catch (Exception e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();

			throw e;
        }
		return studentdailyattendance;
	}

	@Transactional
	public Studentdailyattendance getStudentTodaysAttendance(String userName, LocalDate currentDate) {
		Studentdailyattendance attendance = new Studentdailyattendance();
		try{
			// session.createQuery("from Studentdailyattendance  where attendeeid = '"+userName+"' and date = '"+currentDate+"'");
			attendance = studentDailyAttendanceRepository.findByAttendeeIdAndDate(userName, currentDate);
		} catch (Exception e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();

			throw e;
        }
		return attendance;
	}
	
	/*
	 * public List<Studentdailyattendance> getStudentTotalAttendance( String
	 * studentExternalId, String currentAcademicYear, int branchId) {
	 * List<Studentdailyattendance> studentDailyAttendance = new
	 * ArrayList<Studentdailyattendance>(); try { transaction =
	 * session.beginTransaction(); studentDailyAttendance =
	 * session.createQuery("from Studentdailyattendance  where  academicyear = '"
	 * +currentAcademicYear+"' and attendeeid = '"
	 * +studentExternalId+"' and branchid="+branchId).list(); transaction.commit();
	 * } catch (Exception e) { transaction.rollback(); logger.error(e); // TODO:
	 * handle exception }finally { HibernateUtil.closeSession(); } return
	 * studentDailyAttendance; }
	 */
	@SuppressWarnings("unchecked")
	public List<Studentdailyattendance> getStudentTotalAttendanceDateWise(String studentexternalid,
			String currentAcademicYear, int branchid, Date datePresent) {
		List<Studentdailyattendance> studentDailyAttendance = new ArrayList<Studentdailyattendance>();
		    Transaction transaction = null;
		    Session session = HibernateUtil.openCurrentSession();

		    try {
		        transaction = session.beginTransaction();

		        // Query: from start (no lower bound) till given date
		        studentDailyAttendance = session.createQuery(
		                "from Studentdailyattendance " +
		                "where date <= :datePresent " +
		                "and academicyear = :currentAcademicYear " +
		                "and attendeeid = :studentexternalid " +
		                "and branchid = :branchid")
		                .setParameter("datePresent", datePresent)
		                .setParameter("currentAcademicYear", currentAcademicYear)
		                .setParameter("studentexternalid", studentexternalid)
		                .setParameter("branchid", branchid)
		                .list();

		        transaction.commit();
		    } catch (Exception e) {
		        if (transaction != null) transaction.rollback();
		        logger.error("Error while fetching student attendance", e);
		    } finally {
		        HibernateUtil.closeSession();
		    }
		return studentDailyAttendance;
	}

}

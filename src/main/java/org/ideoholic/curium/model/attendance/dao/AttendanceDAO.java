package org.ideoholic.curium.model.attendance.dao;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.*;

import javax.transaction.Transactional;

import org.hibernate.query.Query;
import org.ideoholic.curium.model.attendance.dto.Attendancemaster;
import org.ideoholic.curium.model.attendance.dto.Holidaysmaster;
import org.ideoholic.curium.model.attendance.dto.Staffdailyattendance;
import org.ideoholic.curium.model.attendance.dto.Studentdailyattendance;
import org.ideoholic.curium.model.attendance.dto.Weeklyoff;
import org.ideoholic.curium.model.employee.dto.Teacher;
import org.ideoholic.curium.model.student.dto.Student;
import org.ideoholic.curium.repositories.AttendanceMasterRepository;
import org.ideoholic.curium.repositories.HolidaysMasterRepository;
import org.ideoholic.curium.repositories.StudentDailyAttendanceRepository;
import org.ideoholic.curium.repositories.WeeklyoffRepository;
import org.ideoholic.curium.util.HibernateUtil;
import org.ideoholic.curium.util.Session;
import org.ideoholic.curium.util.Session.Transaction;
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

	public List<Holidaysmaster> readListOfholidays(
			List<Integer> holidaysIntList, String currentAcademicYear, int branchId) {
		List<Holidaysmaster> holidayMaster = new ArrayList<Holidaysmaster>();
		Transaction transaction = null;
		try{
			Session session = HibernateUtil.openCurrentSession();
			transaction = session.beginTransaction();
			Query query = session.createQuery("From Holidaysmaster where academicyear='"+currentAcademicYear+"' and shid IN (:ids) and branchid="+branchId);
			query.setParameterList("ids", holidaysIntList);
			holidayMaster = query.list();
			transaction.commit();
		}catch (Exception e) { transaction.rollback(); log.error(e.getMessage(), e);

		}finally {
			HibernateUtil.closeSession();
		}

		return holidayMaster;
	}

	public List<Holidaysmaster> readListOfholidays(
			List<Integer> holidaysIntList, String currentAcademicYear) {
		List<Holidaysmaster> holidayMaster = new ArrayList<Holidaysmaster>();
		Transaction transaction = null;
		try{
			Session session = HibernateUtil.openCurrentSession();
			transaction = session.beginTransaction();
			Query query = session.createQuery("From Holidaysmaster where academicyear='"+currentAcademicYear+"' and shid IN (:ids) ");
			query.setParameterList("ids", holidaysIntList);
			holidayMaster = query.list();
			transaction.commit();
		}catch (Exception e) { transaction.rollback(); log.error(e.getMessage(), e);

		} finally {
			HibernateUtil.closeSession();
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
	public boolean addAttendanceMaster(List<Attendancemaster> attendanceMasterList) {
		Transaction transaction = null;
		try{
			Session session = HibernateUtil.openCurrentSession();
			transaction = session.beginTransaction();
			for (Attendancemaster attendancemaster : attendanceMasterList) {
				session.saveOrUpdate(attendancemaster);
			}
			transaction.commit();
			return true;
		}catch (Exception e) { transaction.rollback(); log.error(e.getMessage(), e);
				e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return false;
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
						.findByAttendee_studentexternalidAndDateAndAcademicyear(
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
			studentDailyAttendance = studentDailyAttendanceRepository.findByDateAndAcademicyearAndAttendee_studentexternalidAndBranchid(date, currentAcademicYear, studentExternalId, branchId);
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
			studentDailyAttendance = studentDailyAttendanceRepository.findByDateBetweenAndAcademicyearAndAttendee_studentexternalidAndBranchid(
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
  			studentDailyAttendance = studentDailyAttendanceRepository.findByDateBetweenAndAcademicyearAndAttendee_studentexternalidAndBranchid(LocalDate.parse(timestampFrom),LocalDate.parse(timestampto),currentAcademicYear, studentExternalIdGraph, branchId);
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
						.findByAttendee_studentexternalidAndDateAndAcademicyear(studentDailyAttendance.getAttendeeid(), LocalDate.now(), studentDailyAttendance.getAcademicyear());

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
				Studentdailyattendance studentSingle = studentDailyAttendanceRepository.findByAttendee_studentexternalidAndDateAndAcademicyear(student.getAttendeeid(), LocalDate.now(), student.getAcademicyear())
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

				//studentAttendance = session.createQuery("from Studentdailyattendance  where date between '"+timestampFrom+"' and '"+timestampto+"' and academicyear = '"+currentAcademicYear+"' and attendeeid = '"+student.getStudentexternalid()+"' and branchid="+branchId).list();


				List<Studentdailyattendance> attendanceList = studentDailyAttendanceRepository
						.findByDateBetweenAndAcademicyearAndAttendeeidAndBranchid(
								 currentAcademicYear,timestampFrom, timestampto, student.getStudentexternalid(), branchId
						);
				mapStudentAttendance.put(student.getName(), attendanceList);
			}
		}catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
		return mapStudentAttendance;
	}

	public boolean saveStaffAttendance(List<Staffdailyattendance> listStaffAttendance) {
		
		Transaction transaction = null;
		try{
			Session session = HibernateUtil.openCurrentSession();
			transaction = session.beginTransaction();
			
			for (Staffdailyattendance staffdailyattendance : listStaffAttendance) {
				Staffdailyattendance staffDaily = new Staffdailyattendance();
				
				Query query = session.createQuery("from Staffdailyattendance  where attendeeid='"+staffdailyattendance.getAttendeeid()+"' and date= CURDATE() and academicyear = '"+staffdailyattendance.getAcademicyear()+"'");
				staffDaily = (Staffdailyattendance) query.uniqueResult();
				
				if(staffDaily == null){
					session.save(staffdailyattendance);
				}else{
					Query queryTwo = session.createSQLQuery("update Staffdailyattendance set attendancestatus = '"+staffdailyattendance.getAttendancestatus()+"' where attendanceid = '"+staffDaily.getAttendanceid()+"'");
					queryTwo.executeUpdate();
				}
			}
			
			transaction.commit();
			return true;
		}catch (Exception e) { transaction.rollback(); log.error(e.getMessage(), e);
			log.info(e.getMessage());
			System.out.println(""+e);
		}finally {
			HibernateUtil.closeSession();
		}
		return false;
	}

	public List<Staffdailyattendance> readListOfStaffAttendance(String currentAcademicYear,
			Timestamp timestamp, String teacherexternalid, int branchId) {
		
		List<Staffdailyattendance> staffDailyAttendance = new ArrayList<Staffdailyattendance>();
		
		Transaction transaction = null;
		try{
			Session session = HibernateUtil.openCurrentSession();
			transaction = session.beginTransaction();
			staffDailyAttendance = session.createQuery("from Staffdailyattendance  where date = '"+timestamp+"' and academicyear = '"+currentAcademicYear+"' and attendeeid = '"+teacherexternalid+"' and branchid="+branchId).list();
			transaction.commit();
		}catch (Exception e) { transaction.rollback(); log.error(e.getMessage(), e);
			log.info(e.getMessage());
			System.out.println("column "+e);
		}finally {
			HibernateUtil.closeSession();
		}
		return staffDailyAttendance;
	}

	public boolean updateStaffAttendanceDetails(List<Integer> attendanceIdsList,
			List<String> staffAttendanceStatusList) {
		Transaction transaction = null;
		try{
			Session session = HibernateUtil.openCurrentSession();
			transaction = session.beginTransaction();
			int i =0;
			for (Integer attIn : attendanceIdsList) {
				Query query = session.createSQLQuery("update Staffdailyattendance set attendancestatus = '"+staffAttendanceStatusList.get(i)+"' where attendanceid = '"+attIn+"'");
				query.executeUpdate();
				i++;
			}
			transaction.commit();
			return true;
		}catch (Exception e) { transaction.rollback(); log.error(e.getMessage(), e);
			System.out.println("error "+e);
		}finally {
			HibernateUtil.closeSession();
		}
		return false;
	}

	public List<Staffdailyattendance> getStaffDailyAttendance(
			String staffExternalId, Timestamp fromTimestamp,
			Timestamp toTimestamp, String currentAcademicYear, int branchId) {
		List<Staffdailyattendance> staffDailyAttendance = new ArrayList<Staffdailyattendance>();
		Transaction transaction = null;
		try{
			Session session = HibernateUtil.openCurrentSession();
			transaction = session.beginTransaction();
			staffDailyAttendance = session.createQuery("from Staffdailyattendance  where date between '"+fromTimestamp+"' and '"+toTimestamp+"' and academicyear = '"+currentAcademicYear+"' and attendeeid = '"+staffExternalId+"' and branchid="+branchId).list();
			transaction.commit();
		} catch (Exception e) { transaction.rollback(); log.error(e.getMessage(), e);
			// TODO: handle exception
		}finally {
			HibernateUtil.closeSession();
		}
		return staffDailyAttendance;
	}

	public boolean checkStaffAttendance(List<Staffdailyattendance> staffDailyAttendanceList) {

		boolean result = false;
		Transaction transaction = null;
		try{
			Session session = HibernateUtil.openCurrentSession();
			transaction = session.beginTransaction();
			
			for (Staffdailyattendance staffDailyAttendance : staffDailyAttendanceList) {
				Staffdailyattendance staffDailyAttendanceDetails = new Staffdailyattendance();
				Query query = session.createQuery("from Staffdailyattendance  where attendeeid='"+staffDailyAttendance.getAttendeeid()+"' and date= CURDATE() and academicyear = '"+staffDailyAttendance.getAcademicyear()+"'");
				staffDailyAttendanceDetails = (Staffdailyattendance) query.uniqueResult();
				if(staffDailyAttendanceDetails == null){
					session.save(staffDailyAttendance);
					session.flush();
			        session.clear();
				}else{
					Query queryTwo = session.createSQLQuery("update att_staffdailyattendance set attendancestatus = '"+staffDailyAttendance.getAttendancestatus()+"' where attendanceid = '"+staffDailyAttendanceDetails.getAttendanceid()+"'");
					queryTwo.executeUpdate();
					session.flush();
			        session.clear();
				}
			}
			
			transaction.commit();
			result = true;
		} catch (Exception e) { transaction.rollback(); log.error(e.getMessage(), e);
			System.out.println(""+e);
		}finally {
			HibernateUtil.closeSession();
		}
		return result;
	}

	public Map<String, List<Staffdailyattendance>> readListOfStaffAttendanceExport(
			String currentAcademicYear, Timestamp timestampFrom, Timestamp timestampto,
			List<Teacher> staffList, int branchId) {


		Map<String, List<Staffdailyattendance>> mapStaffAttendance = new HashMap<String, List<Staffdailyattendance>>();
		
		Transaction transaction = null;
		try{
			Session session = HibernateUtil.openCurrentSession();
			transaction = session.beginTransaction();
			
			for (Teacher teacher : staffList) {
				List<Staffdailyattendance> staffAttendance = new ArrayList<Staffdailyattendance>();
				staffAttendance = session.createQuery("from Staffdailyattendance  where date between '"+timestampFrom+"' and '"+timestampto+"' and academicyear = '"+currentAcademicYear+"' and attendeeid = '"+teacher.getTeacherexternalid()+"' and branchid="+branchId).list();
				mapStaffAttendance.put(teacher.getTeachername(), staffAttendance);
			}
			transaction.commit();
		}catch (Exception e) { transaction.rollback(); log.error(e.getMessage(), e);
			System.out.println(""+e);
		}finally {
			HibernateUtil.closeSession();
		}
		return mapStaffAttendance;
		
	}

	public void markDailyAttendanceJobStaff(List<Staffdailyattendance> listStaffAttendance) {
		
		Transaction transaction = null;
		try{
			Session session = HibernateUtil.openCurrentSession();
			transaction = session.beginTransaction();
			
			for (Staffdailyattendance staff : listStaffAttendance) {
				Staffdailyattendance staffSingle = new Staffdailyattendance();
				Query query = session.createQuery("from Staffdailyattendance where attendeeid = '"+staff.getAttendeeid()+"' and academicyear='"+staff.getAcademicyear()+"' and date=CURDATE()");
				staffSingle = (Staffdailyattendance) query.uniqueResult();

				if(staffSingle == null){
					session.save(staff);
				}
			}
			transaction.commit();
		}catch (Exception e) { transaction.rollback(); log.error(e.getMessage(), e);
			System.out.println(""+e);
		}finally {
			HibernateUtil.closeSession();
		}
	}
	
	public List<Studentdailyattendance> getStudentAttendance(String date) {
		List<Studentdailyattendance> studentdailyattendance = new ArrayList<Studentdailyattendance>();
		Transaction transaction = null;
		try{
			Session session = HibernateUtil.openCurrentSession();
			transaction = session.beginTransaction();
			studentdailyattendance = session.createQuery("from Studentdailyattendance  where date = '"+date+"'").list();
			transaction.commit();
		} catch (Exception e) { transaction.rollback(); log.error(e.getMessage(), e);
		}finally {
			HibernateUtil.closeSession();
		}
		return studentdailyattendance;
	}
	
	public List<Studentdailyattendance> getStudentClassAttendance(String date, List<String> attendeeid) {
		List<Studentdailyattendance> studentdailyattendance = new ArrayList<Studentdailyattendance>();
		Transaction transaction = null;
		try{
			Session session = HibernateUtil.openCurrentSession();
			transaction = session.beginTransaction();
			Query query = session.createQuery("from Studentdailyattendance  where date = '"+date+"' and attendeeid IN (:ids)");
			query.setParameterList("ids", attendeeid);
			studentdailyattendance = query.list();
			transaction.commit();
		} catch (Exception e) { transaction.rollback(); log.error(e.getMessage(), e);
		}finally {
			HibernateUtil.closeSession();
		}
		return studentdailyattendance;
	}
	
	public Studentdailyattendance getStudentTodaysAttendance(String userName, LocalDate currentDate) {
		Studentdailyattendance attendance = new Studentdailyattendance();
		Transaction transaction = null;
		try{
			Session session = HibernateUtil.openCurrentSession();
			transaction = session.beginTransaction();
			Query query = session.createQuery("from Studentdailyattendance  where attendeeid = '"+userName+"' and date = '"+currentDate+"'");
			attendance = (Studentdailyattendance) query.uniqueResult();
			transaction.commit();
		} catch (Exception e) { transaction.rollback(); log.error(e.getMessage(), e);
		}finally {
			HibernateUtil.closeSession();
		}
		return attendance;
	}

}

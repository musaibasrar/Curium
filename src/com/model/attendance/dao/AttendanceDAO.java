package com.model.attendance.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.model.attendance.dto.Attendancemaster;
import com.model.attendance.dto.Holidaysmaster;
import com.model.attendance.dto.Studentdailyattendance;
import com.model.attendance.dto.Weeklyoff;
import com.model.employee.dto.Teacher;
import com.model.student.dto.Student;
import com.util.HibernateUtil;

public class AttendanceDAO {
	Session session = null;
	/**
	 * * Hibernate Session Variable
	 */
	Transaction transaction = null;
	
	private static final Logger logger = LogManager.getLogger(AttendanceDAO.class);

	public AttendanceDAO() {
		session = HibernateUtil.openSession();
	}

	public List<Teacher> readListOfObjects() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Holidaysmaster> readListOfHolidays(String currentAcademicYear) {
		List<Holidaysmaster> holidayMaster = new ArrayList<Holidaysmaster>();
		try{
			transaction = session.beginTransaction();
			holidayMaster = session.createQuery("From Holidaysmaster where academicyear='"+currentAcademicYear+"'").list();
			transaction.commit();
		}catch(Exception e){
			transaction.rollback();
		} finally {
			session.close();
		}
		
		return holidayMaster;
	}

	public boolean saveHolidays(Holidaysmaster holidayMaster) {
		try {
			transaction = session.beginTransaction();
			session.save(holidayMaster);
			transaction.commit();
			return true;

		} catch (HibernateException hibernateException) {
			transaction.rollback();
			hibernateException.printStackTrace();
		} finally {
			session.close();
		}
		return false;
	}

	public boolean saveWeeklyOff(Weeklyoff weeklyOff) {
		try {
			transaction = session.beginTransaction();
			session.save(weeklyOff);
			transaction.commit();
			return true;

		} catch (HibernateException hibernateException) {
			transaction.rollback();
			hibernateException.printStackTrace();
		} finally {
			session.close();
		}
		return false;
	}

	public boolean deleteMultiple(List<Integer> holidayIds) {
		try{
			transaction = session.beginTransaction();
			Query query = session.createQuery("delete from Holidaysmaster as holidaysmaster where holidaysmaster.shid IN (:holidayIds)");
			query.setParameterList("holidayIds", holidayIds);
			query.executeUpdate();
			transaction.commit();
			return true;
		}catch(HibernateException e){
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return false;
	}

	@SuppressWarnings("unchecked")
	public List<Weeklyoff> readListOfWeekOff(String academicYear) {
			List<Weeklyoff> weeklyOff = new ArrayList<Weeklyoff>();
			try{
				transaction = session.beginTransaction();
				weeklyOff = session.createQuery("from Weeklyoff where academicyear='"+academicYear+"'").list();
				transaction.commit();
			}catch(Exception e){
				e.printStackTrace();
			} finally {
				session.close();
			}
			
		return weeklyOff;
	}

	public List<Weeklyoff> readListOfWeeklyOff(List<Integer> weeklyOffList,
			String academicYear) {
		List<Weeklyoff> weeklyOff = new ArrayList<Weeklyoff>();
		try{
			transaction = session.beginTransaction();
			Query query = session.createQuery("from Weeklyoff where academicyear='"+academicYear+"' and wid IN (:ids)");
			query.setParameterList("ids", weeklyOffList);
			weeklyOff = query.list();
			transaction.commit();
		}catch(Exception e){
			e.printStackTrace();
		} finally {
			session.close();
		}
		
	return weeklyOff;

	}

	public List<Holidaysmaster> readListOfholidays(
			List<Integer> holidaysIntList, String currentAcademicYear) {
		List<Holidaysmaster> holidayMaster = new ArrayList<Holidaysmaster>();
		try{
			transaction = session.beginTransaction();
			Query query = session.createQuery("From Holidaysmaster where academicyear='"+currentAcademicYear+"' and shid IN (:ids)");
			query.setParameterList("ids", holidaysIntList);
			holidayMaster = query.list();
			transaction.commit();
		}catch(Exception e){
			transaction.rollback();
		} finally {
			session.close();
		}
		
		return holidayMaster;
	}

	public boolean addAttendanceMaster(Attendancemaster attendanceMaster) {

		try {
			transaction = session.beginTransaction();
			session.save(attendanceMaster);
			transaction.commit();
			return true;
		} catch (Exception e) {
				e.printStackTrace();
		}finally {
			session.close();
		}
		return false;
	}

	public boolean addAttendanceMaster(List<Attendancemaster> attendanceMasterList) {
		try {
			transaction = session.beginTransaction();
			for (Attendancemaster attendancemaster : attendanceMasterList) {
				session.saveOrUpdate(attendancemaster);
			}
			transaction.commit();
			return true;
		}catch(Exception e){
				e.printStackTrace();
		}finally {
			session.close();
		}
		return false;
	}

	public List<Attendancemaster> getAttendanceMasterDetails(String attendeeId) {
		List<Attendancemaster> studentAttendanceMaster = new ArrayList<Attendancemaster>();
		try{
			transaction = session.beginTransaction();
			studentAttendanceMaster = session.createQuery("From Attendancemaster where attendeeid = "+attendeeId+"").list();
			transaction.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return studentAttendanceMaster;
	}

	public boolean saveStudentAttendance(List<Studentdailyattendance> listStudentAttendance) {
		
		try{
			transaction = session.beginTransaction();
			
			for (Studentdailyattendance studentdailyattendance : listStudentAttendance) {
				session.save(studentdailyattendance);
			}
			
			transaction.commit();
			return true;
		}catch(HibernateException e){
			logger.info(e);
			System.out.println(""+e);
		}finally{
			session.close();
		}
		return false;
	}

	public List<Studentdailyattendance> readListOfStudentAttendance(String currentAcademicYear, Timestamp date, String studentExternalId) {
		
		List<Studentdailyattendance> studentDailyAttendance = new ArrayList<Studentdailyattendance>();
		
		try{
			transaction = session.beginTransaction();
			studentDailyAttendance = session.createQuery("from Studentdailyattendance  where date = '"+date+"' and academicyear = '"+currentAcademicYear+"' and attendeeid = '"+studentExternalId+"'").list();
			transaction.commit();
		}catch(HibernateException e){
			logger.info(e);
			System.out.println("column "+e);
		}finally{
			session.close();
		}
		return studentDailyAttendance;
	}

	public List<Studentdailyattendance> getStudentDailyAttendance(
			String studentExternalId, Timestamp fromTimestamp,
			Timestamp toTimestamp, String currentAcademicYear) {
		List<Studentdailyattendance> studentDailyAttendance = new ArrayList<Studentdailyattendance>();
		try {
			transaction = session.beginTransaction();
			studentDailyAttendance = session.createQuery("from Studentdailyattendance  where date between '"+fromTimestamp+"' and '"+toTimestamp+"' and academicyear = '"+currentAcademicYear+"' and attendeeid = '"+studentExternalId+"'").list();
			transaction.commit();
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			session.close();
		}
		
		return studentDailyAttendance;
	}

	public boolean updateStudentAttendanceDetails(List<Integer> attendanceIdsList, List<String> studentAttendanceStatusList, String academicYear) {
		try{
			transaction = session.beginTransaction();
			int i =0;
			for (Integer attIn : attendanceIdsList) {
				Query query = session.createSQLQuery("update Studentdailyattendance set attendancestatus = '"+studentAttendanceStatusList.get(i)+"' where attendanceid = '"+attIn+"'");
				query.executeUpdate();
				
				i++;
			}
			transaction.commit();
			/*Query query = session.createQuery("update Studentdailyattendance set attendancestatus = :attendancestatus where attendanceid IN (:ids)");
			query.setParameterList("attendancestatus", studentAttendanceStatusList);
			query.setParameterList("ids", attendanceIdsList);
			query.executeUpdate();*/
		}catch(Exception e){
			System.out.println("error "+e);
		}
		return false;
	}

	public List<Studentdailyattendance> getStudentDailyAttendanceGraph(
			String studentExternalIdGraph, Timestamp timestampFrom,
			Timestamp timestampto, String currentAcademicYear) {
		List<Studentdailyattendance> studentDailyAttendance = new ArrayList<Studentdailyattendance>();
		try {
			transaction = session.beginTransaction();
			studentDailyAttendance = session.createQuery("from Studentdailyattendance  where date between '"+timestampFrom+"' and '"+timestampto+"' and academicyear = '"+currentAcademicYear+"' and attendeeid = '"+studentExternalIdGraph+"'").list();
			transaction.commit();
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			session.close();
		}
		
		return studentDailyAttendance;
	}

	public boolean checkStudentAttendance(Studentdailyattendance studentDailyAttendance) {
		Studentdailyattendance studentDailyAttendanceDetails = new Studentdailyattendance();
		try {
			transaction = session.beginTransaction();
			Query query = session.createQuery("from Studentdailyattendance  where attendeeid='"+studentDailyAttendance.getAttendeeid()+"' and date= CURDATE() and academicyear = '"+studentDailyAttendance.getAcademicyear()+"'");
			studentDailyAttendanceDetails = (Studentdailyattendance) query.uniqueResult();
			if(studentDailyAttendanceDetails == null){
				session.save(studentDailyAttendance);
			}else{
				Query queryTwo = session.createSQLQuery("update Studentdailyattendance set attendancestatus = '"+studentDailyAttendance.getAttendancestatus()+"' where attendanceid = '"+studentDailyAttendanceDetails.getAttendanceid()+"'");
				queryTwo.executeUpdate();
			}
			transaction.commit();
			return true;
		} catch (Exception e) {
			System.out.println(""+e);
		}finally{
			session.close();
		}
		
		return false;
	}

	public void markDailyAttendanceJob(List<Student> studentList, String currentAcademicYear) {

		
		try{
			transaction = session.beginTransaction();
			
			for (Student student : studentList) {
				Student studentSingle = new Student();
				Query query = session.createQuery("from Studentdailyattendance where attendeeid = '"+student.getStudentexternalid()+"' and academicyear='"+currentAcademicYear+"' and date=CURDATE()");
				studentSingle = (Student) query.uniqueResult();
			
				if(studentSingle == null){
					Studentdailyattendance studentDailyAttendance = new Studentdailyattendance();
					studentDailyAttendance.setAttendeeid(student.getStudentexternalid());
					studentDailyAttendance.setDate(new Date());
					studentDailyAttendance.setAttendancestatus("A");
					studentDailyAttendance.setAcademicyear(currentAcademicYear);
					session.save(studentDailyAttendance);
				}
				
			}
			transaction.commit();
		}catch(Exception e){
			System.out.println(""+e);
		}finally{
			session.close();
		}
	}

}

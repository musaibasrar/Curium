package com.model.attendance.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.model.attendance.dto.Attendancemaster;
import com.model.attendance.dto.Holidaysmaster;
import com.model.attendance.dto.Weeklyoff;
import com.model.employee.dto.Teacher;
import com.util.HibernateUtil;

public class AttendanceDAO {
	Session session = null;
	/**
	 * * Hibernate Session Variable
	 */
	Transaction transaction = null;
	

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

}

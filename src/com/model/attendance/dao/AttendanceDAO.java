package com.model.attendance.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.model.attendance.dto.Attendancemaster;
import com.model.attendance.dto.Holidaysmaster;
import com.model.attendance.dto.Staffdailyattendance;
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

	public List<Holidaysmaster> readListOfHolidays(String currentAcademicYear, int branchId) {
		List<Holidaysmaster> holidayMaster = new ArrayList<Holidaysmaster>();
		try{
			transaction = session.beginTransaction();
			holidayMaster = session.createQuery("From Holidaysmaster where academicyear='"+currentAcademicYear+"' and branchid="+branchId).list();
			transaction.commit();
		}catch(Exception e){
			transaction.rollback();
		} finally {
			//session.close();
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
			//session.close();
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
			//session.close();
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
			//session.close();
		}
		
		return false;
	}

	@SuppressWarnings("unchecked")
	public List<Weeklyoff> readListOfWeekOff(String academicYear, int branchId) {
			List<Weeklyoff> weeklyOff = new ArrayList<Weeklyoff>();
			try{
				transaction = session.beginTransaction();
				weeklyOff = session.createQuery("from Weeklyoff where academicyear='"+academicYear+"' and branchid="+branchId).list();
				transaction.commit();
			}catch(Exception e){
				e.printStackTrace();
			} finally {
				//session.close();
			}
			
		return weeklyOff;
	}

	public List<Weeklyoff> readListOfWeeklyOff(List<Integer> weeklyOffList,
			String academicYear, int branchid) {
		List<Weeklyoff> weeklyOff = new ArrayList<Weeklyoff>();
		try{
			transaction = session.beginTransaction();
			Query query = session.createQuery("from Weeklyoff where academicyear='"+academicYear+"' and wid IN (:ids) and branchid="+branchid);
			query.setParameterList("ids", weeklyOffList);
			weeklyOff = query.list();
			transaction.commit();
		}catch(Exception e){
			e.printStackTrace();
		} finally {
			//session.close();
		}
		
	return weeklyOff;

	}
	
	public List<Weeklyoff> readListOfWeeklyOff(List<Integer> weeklyOffList,
			String academicYear) {
		List<Weeklyoff> weeklyOff = new ArrayList<Weeklyoff>();
		try{
			transaction = session.beginTransaction();
			Query query = session.createQuery("from Weeklyoff where academicyear='"+academicYear+"' and wid IN (:ids) ");
			query.setParameterList("ids", weeklyOffList);
			weeklyOff = query.list();
			transaction.commit();
		}catch(Exception e){
			e.printStackTrace();
		} finally {
			//session.close();
		}
		
	return weeklyOff;

	}

	public List<Holidaysmaster> readListOfholidays(
			List<Integer> holidaysIntList, String currentAcademicYear, int branchId) {
		List<Holidaysmaster> holidayMaster = new ArrayList<Holidaysmaster>();
		try{
			transaction = session.beginTransaction();
			Query query = session.createQuery("From Holidaysmaster where academicyear='"+currentAcademicYear+"' and shid IN (:ids) and branchid="+branchId);
			query.setParameterList("ids", holidaysIntList);
			holidayMaster = query.list();
			transaction.commit();
		}catch(Exception e){
			transaction.rollback();
		} finally {
			//session.close();
		}
		
		return holidayMaster;
	}
	
	public List<Holidaysmaster> readListOfholidays(
			List<Integer> holidaysIntList, String currentAcademicYear) {
		List<Holidaysmaster> holidayMaster = new ArrayList<Holidaysmaster>();
		try{
			transaction = session.beginTransaction();
			Query query = session.createQuery("From Holidaysmaster where academicyear='"+currentAcademicYear+"' and shid IN (:ids) ");
			query.setParameterList("ids", holidaysIntList);
			holidayMaster = query.list();
			transaction.commit();
		}catch(Exception e){
			transaction.rollback();
		} finally {
			//session.close();
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
			//session.close();
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
			//session.close();
		}
		return false;
	}

	public List<Attendancemaster> getAttendanceMasterDetails(String attendeeId, int branchId) {
		List<Attendancemaster> studentAttendanceMaster = new ArrayList<Attendancemaster>();
		try{
			transaction = session.beginTransaction();
			studentAttendanceMaster = session.createQuery("From Attendancemaster where attendeeid = "+attendeeId+" and branchid="+branchId).list();
			transaction.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			//session.close();
		}
		return studentAttendanceMaster;
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
			//session.close();
		}
		return studentAttendanceMaster;
	}

	public boolean saveStudentAttendance(List<Studentdailyattendance> listStudentAttendance) {
		
		try{
			transaction = session.beginTransaction();
			
			for (Studentdailyattendance studentdailyattendance : listStudentAttendance) {
				Studentdailyattendance studentDailyAttendanceDetails = new Studentdailyattendance();
				Query query = session.createQuery("from Studentdailyattendance  where" +
						" attendeeid='"+studentdailyattendance.getAttendeeid()+"' and " +
								"date= CURDATE() and academicyear = '"+studentdailyattendance.getAcademicyear()+"'");
				studentDailyAttendanceDetails = (Studentdailyattendance) query.uniqueResult();
				if(studentDailyAttendanceDetails == null){
					session.save(studentdailyattendance);
				}else{
					Query queryTwo = session.createQuery("update Studentdailyattendance set attendancestatus = " +
							"'"+studentdailyattendance.getAttendancestatus()+"' where attendanceid = " +
									"'"+studentDailyAttendanceDetails.getAttendanceid()+"'");
					queryTwo.executeUpdate();
				}
			}
			
			transaction.commit();
			return true;
		}catch(HibernateException e){
			logger.info(e);
			System.out.println(""+e);
		}finally{
			//session.close();
		}
		return false;
	}

	public List<Studentdailyattendance> readListOfStudentAttendance(String currentAcademicYear, Timestamp date, String studentExternalId, int branchId) {
		
		List<Studentdailyattendance> studentDailyAttendance = new ArrayList<Studentdailyattendance>();
		
		try{
			transaction = session.beginTransaction();
			studentDailyAttendance = session.createQuery("from Studentdailyattendance  where date = '"+date+"' and academicyear = '"+currentAcademicYear+"' and attendeeid = '"+studentExternalId+"' and branchid="+branchId).list();
			transaction.commit();
		}catch(HibernateException e){
			logger.info(e);
			System.out.println("column "+e);
		}finally{
			//session.close();
		}
		return studentDailyAttendance;
	}

	public List<Studentdailyattendance> getStudentDailyAttendance(
			String studentExternalId, Timestamp fromTimestamp,
			Timestamp toTimestamp, String currentAcademicYear, int branchId) {
		List<Studentdailyattendance> studentDailyAttendance = new ArrayList<Studentdailyattendance>();
		try {
			transaction = session.beginTransaction();
			studentDailyAttendance = session.createQuery("from Studentdailyattendance  where date between '"+fromTimestamp+"' and '"+toTimestamp+"' and academicyear = '"+currentAcademicYear+"' and attendeeid = '"+studentExternalId+"' and branchid="+branchId).list();
			transaction.commit();
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			//session.close();
		}
		
		return studentDailyAttendance;
	}

	public boolean updateStudentAttendanceDetails(List<Integer> attendanceIdsList, List<String> studentAttendanceStatusList, String academicYear) {
		try{
			transaction = session.beginTransaction();
			int i =0;
			for (Integer attIn : attendanceIdsList) {
				Query query = session.createQuery("update Studentdailyattendance set attendancestatus = '"+studentAttendanceStatusList.get(i)+"' where attendanceid = '"+attIn+"'");
				query.executeUpdate();
				
				i++;
			}
			transaction.commit();
			return true;
		}catch(Exception e){
			System.out.println("error "+e);
		}finally{
			//session.close();
		}
		return false;
	}

	public List<Studentdailyattendance> getStudentDailyAttendanceGraph(
			String studentExternalIdGraph, Timestamp timestampFrom,
			Timestamp timestampto, String currentAcademicYear, int branchId) {
		List<Studentdailyattendance> studentDailyAttendance = new ArrayList<Studentdailyattendance>();
		try {
			transaction = session.beginTransaction();
			studentDailyAttendance = session.createQuery("from Studentdailyattendance  where date between '"+timestampFrom+"' and '"+timestampto+"' and academicyear = '"+currentAcademicYear+"' and attendeeid = '"+studentExternalIdGraph+"' and branchid="+branchId).list();
			transaction.commit();
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			//session.close();
		}
		
		return studentDailyAttendance;
	}

	public boolean checkStudentAttendance(List<Studentdailyattendance> studentDailyAttendanceList) {
		
		try {
			transaction = session.beginTransaction();
		
			for (Studentdailyattendance studentDailyAttendance : studentDailyAttendanceList) {
				Studentdailyattendance studentDailyAttendanceDetails = new Studentdailyattendance();
				Query query = session.createQuery("from Studentdailyattendance  where attendeeid='"+studentDailyAttendance.getAttendeeid()+"' and date= CURDATE() and academicyear = '"+studentDailyAttendance.getAcademicyear()+"'");
				studentDailyAttendanceDetails = (Studentdailyattendance) query.uniqueResult();
				if(studentDailyAttendanceDetails == null){
					session.save(studentDailyAttendance);
				}else{
					Query queryTwo = session.createSQLQuery("update Studentdailyattendance set attendancestatus = '"+studentDailyAttendance.getAttendancestatus()+"' where attendanceid = '"+studentDailyAttendanceDetails.getAttendanceid()+"'");
					queryTwo.executeUpdate();
				}
			}
			
			transaction.commit();
			return true;
		} catch (Exception e) {
			System.out.println(""+e);
		}finally{
			//session.close();
		}
		
		return false;
	}

	public void markDailyAttendanceJob(List<Studentdailyattendance> studentDailyAttendance) {

		
		try{
			transaction = session.beginTransaction();
			
			for (Studentdailyattendance student : studentDailyAttendance) {
				Studentdailyattendance studentSingle = new Studentdailyattendance();
				Query query = session.createQuery("from Studentdailyattendance where attendeeid = '"+student.getAttendeeid()+"' and academicyear='"+student.getAcademicyear()+"' and date=CURDATE()");
				studentSingle = (Studentdailyattendance) query.uniqueResult();
			
				if(studentSingle == null){
					session.save(student);
				}
			}
			transaction.commit();
		}catch(Exception e){
			System.out.println(""+e);
		}finally{
			//session.close();
		}
	}

	public Map<String, List<Studentdailyattendance>> readListOfStudentAttendanceExport(
			String currentAcademicYear, Timestamp timestampFrom, Timestamp timestampto,
			List<Student> searchStudentList, int branchId) {
		
		Map<String, List<Studentdailyattendance>> mapStudentAttendance = new HashMap<String, List<Studentdailyattendance>>();
		
		try{
			transaction = session.beginTransaction();
			
			for (Student student : searchStudentList) {
				List<Studentdailyattendance> studentAttendance = new ArrayList<Studentdailyattendance>();
				studentAttendance = session.createQuery("from Studentdailyattendance  where date between '"+timestampFrom+"' and '"+timestampto+"' and academicyear = '"+currentAcademicYear+"' and attendeeid = '"+student.getStudentexternalid()+"' and branchid="+branchId).list();
				mapStudentAttendance.put(student.getName(), studentAttendance);
			}
			transaction.commit();
		}catch(Exception e){
			System.out.println(""+e);
		}
		return mapStudentAttendance;
	}

	public boolean saveStaffAttendance(List<Staffdailyattendance> listStaffAttendance) {
		
		try{
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
		}catch(HibernateException e){
			logger.info(e);
			System.out.println(""+e);
		}finally{
			//session.close();
		}
		return false;
	}

	public List<Staffdailyattendance> readListOfStaffAttendance(String currentAcademicYear,
			Timestamp timestamp, String teacherexternalid, int branchId) {
		
List<Staffdailyattendance> staffDailyAttendance = new ArrayList<Staffdailyattendance>();
		
		try{
			transaction = session.beginTransaction();
			staffDailyAttendance = session.createQuery("from Staffdailyattendance  where date = '"+timestamp+"' and academicyear = '"+currentAcademicYear+"' and attendeeid = '"+teacherexternalid+"' and branchid="+branchId).list();
			transaction.commit();
		}catch(HibernateException e){
			logger.info(e);
			System.out.println("column "+e);
		}finally{
			//session.close();
		}
		return staffDailyAttendance;
	}

	public boolean updateStaffAttendanceDetails(List<Integer> attendanceIdsList,
			List<String> staffAttendanceStatusList) {
		try{
			transaction = session.beginTransaction();
			int i =0;
			for (Integer attIn : attendanceIdsList) {
				Query query = session.createQuery("update Staffdailyattendance set attendancestatus = '"+staffAttendanceStatusList.get(i)+"' where attendanceid = '"+attIn+"'");
				query.executeUpdate();
				i++;
			}
			transaction.commit();
			return true;
		}catch(Exception e){
			System.out.println("error "+e);
		}finally{
			//session.close();
		}
		return false;
	}

	public List<Staffdailyattendance> getStaffDailyAttendance(
			String staffExternalId, Timestamp fromTimestamp,
			Timestamp toTimestamp, String currentAcademicYear, int branchId) {
		List<Staffdailyattendance> staffDailyAttendance = new ArrayList<Staffdailyattendance>();
		try {
			transaction = session.beginTransaction();
			staffDailyAttendance = session.createQuery("from Staffdailyattendance  where date between '"+fromTimestamp+"' and '"+toTimestamp+"' and academicyear = '"+currentAcademicYear+"' and attendeeid = '"+staffExternalId+"' and branchid="+branchId).list();
			transaction.commit();
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			//session.close();
		}
		
		return staffDailyAttendance;
	}

	public boolean checkStaffAttendance(List<Staffdailyattendance> staffDailyAttendanceList) {
		

		try {
			transaction = session.beginTransaction();
			
			for (Staffdailyattendance staffDailyAttendance : staffDailyAttendanceList) {
				Staffdailyattendance staffDailyAttendanceDetails = new Staffdailyattendance();
				Query query = session.createQuery("from Staffdailyattendance  where attendeeid='"+staffDailyAttendance.getAttendeeid()+"' and date= CURDATE() and academicyear = '"+staffDailyAttendance.getAcademicyear()+"'");
				staffDailyAttendanceDetails = (Staffdailyattendance) query.uniqueResult();
				if(staffDailyAttendanceDetails == null){
					session.save(staffDailyAttendance);
				}else{
					Query queryTwo = session.createSQLQuery("update Staffdailyattendance set attendancestatus = '"+staffDailyAttendance.getAttendancestatus()+"' where attendanceid = '"+staffDailyAttendanceDetails.getAttendanceid()+"'");
					queryTwo.executeUpdate();
				}
			}
			
			transaction.commit();
			return true;
		} catch (Exception e) {
			System.out.println(""+e);
		}finally{
			//session.close();
		}
		
		return false;
	}

	public Map<String, List<Staffdailyattendance>> readListOfStaffAttendanceExport(
			String currentAcademicYear, Timestamp timestampFrom, Timestamp timestampto,
			List<Teacher> staffList, int branchId) {


		Map<String, List<Staffdailyattendance>> mapStaffAttendance = new HashMap<String, List<Staffdailyattendance>>();
		
		try{
			transaction = session.beginTransaction();
			
			for (Teacher teacher : staffList) {
				List<Staffdailyattendance> staffAttendance = new ArrayList<Staffdailyattendance>();
				staffAttendance = session.createQuery("from Staffdailyattendance  where date between '"+timestampFrom+"' and '"+timestampto+"' and academicyear = '"+currentAcademicYear+"' and attendeeid = '"+teacher.getTeacherexternalid()+"' and branchid="+branchId).list();
				mapStaffAttendance.put(teacher.getTeachername(), staffAttendance);
			}
			transaction.commit();
		}catch(Exception e){
			System.out.println(""+e);
		}
		return mapStaffAttendance;
		
	}

	public void markDailyAttendanceJobStaff(List<Staffdailyattendance> listStaffAttendance) {
		
		try{
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
		}catch(Exception e){
			System.out.println(""+e);
		}finally{
			//session.close();
		}
	}

    public boolean checkAttendace(String subjectName, String examLevelCode, String currentAcademicYear, String branchId) {
        
                List<Studentdailyattendance> studentDailyAttendance = new ArrayList<Studentdailyattendance>();
                try{
                        transaction = session.beginTransaction();
                        studentDailyAttendance = session.createQuery("from Studentdailyattendance  where subject = '"+subjectName+"' and examlevelcode = '"+examLevelCode+"' and academicyear = '"+currentAcademicYear+"' and branchid="+branchId).list();
                        transaction.commit();
                }catch(HibernateException e){
                        logger.info(e);
                }finally{
                        //session.close();
                }
                
                if(studentDailyAttendance.size()>0){
                    return true;
                }
                return false;
        
}

    public List<Studentdailyattendance> getStudentAttendance(String HQL) {
        
        List<Studentdailyattendance> studentDailyAttendance = new ArrayList<Studentdailyattendance>();
        
        try{
                transaction = session.beginTransaction();
                studentDailyAttendance = session.createQuery(HQL).list();
                transaction.commit();
        }catch(HibernateException e){
                logger.info(e);
        }finally{
                //session.close();
        }
        return studentDailyAttendance;
        
    }

    public boolean updateStudentAttendanceDetails(String[] studentAttendanceStatusId, String[] studentAttendanceStatus) {
        
        try{
            transaction = session.beginTransaction();
            int i =0;
            for (String attIn : studentAttendanceStatusId) {
                    if(!attIn.equalsIgnoreCase("")) {
                        Query query = session.createQuery("update Studentdailyattendance set attendancestatus = '"+studentAttendanceStatus[i]+"' where attendanceid = '"+Integer.parseInt(attIn)+"'");
                        query.executeUpdate();
                    }
                    i++;
            }
            transaction.commit();
            return true;
    }catch(Exception e){
            System.out.println("error "+e);
    }finally{
            //session.close();
    }
    return false;
    }

}

package org.ideoholic.curium.model.std.dao;

import java.util.ArrayList;
import java.util.List;

import org.ideoholic.curium.model.parents.dto.Parents;
import org.ideoholic.curium.model.std.dto.Classhierarchy;
import org.ideoholic.curium.model.std.dto.Classsec;
import org.ideoholic.curium.model.student.dto.Student;
import org.ideoholic.curium.repositories.ClassHierarchyRepository;
import org.ideoholic.curium.repositories.ClasssecRepository;
import org.ideoholic.curium.repositories.ParentsRepository;
import org.ideoholic.curium.repositories.StudentRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class StandardDetailsDAO {


	private final StudentRepository studentRepository;

	private final ParentsRepository parentsRepository;

	private final ClasssecRepository classsecRepository;
	
	private final ClassHierarchyRepository classhierarchyRepository;

	@Transactional
	public Classsec create(Classsec classsec) {
		try {
			// Original: session.save(classsec);
			classsecRepository.save(classsec);
		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}
		return classsec;
	}

	@Transactional(readOnly = true)
	public List<Classsec> viewClasses(int branchId) {
		List<Classsec> classsecList = new ArrayList<>();
		try {
			// Original: session.createQuery("From Classsec where branchid="+branchId)
			classsecList = classsecRepository.findByBranchid(branchId);
		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}
		return classsecList;
	}

	@Transactional
	public void deleteMultiple(List<Integer> ids) {
		try {
			// Original: session.createQuery("delete from Classsec where stdrdid IN (:ids)")
			classsecRepository.deleteAllById(ids);
		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}
	}

	@Transactional
	public void createClassHierarchy(Classhierarchy classHierarchy) {
		try {
			// Original: session.save(classHierarchy);
			classhierarchyRepository.save(classHierarchy);
		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}
	}

	@Transactional
	public void deleteClassHierarchy(List<Integer> ids) {
		try {
			// Original: session.createQuery("delete from Classhierarchy where idclasshierarchy IN (:ids)")
			classhierarchyRepository.deleteAllById(ids);
		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}
	}

	@Transactional(readOnly = true)
	public List<Classhierarchy> viewClassHierarchy(int branchid) {
		List<Classhierarchy> classHierarchyList = new ArrayList<>();
		try {
			// Original: session.createQuery("From Classhierarchy where branchid=" + branchid)
			classHierarchyList = classhierarchyRepository.findByBranchid(branchid);
		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}
		return classHierarchyList;
	}

	@Transactional
	public boolean graduateMultiple(List<Integer> ids) {
		boolean result = false;
		try {
			// Original: session.createQuery("update Student set passedout = 1 where id IN (:ids)")
			for (Integer studentId : ids) {
				studentRepository.findById(studentId).ifPresent(student -> {
					student.setPassedout(1);
					studentRepository.save(student);
				});
			}
			result = true;
		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
			result = false;
		}
		return result;
	}

	@Transactional
	public boolean droppedoutMultiple(List<Integer> ids) {
		boolean result = false;
		try {
			// Original: session.createQuery("update Student set droppedout = 1 where id IN (:ids)")
			for (Integer studentId : ids) {
				studentRepository.findById(studentId).ifPresent(student -> {
					student.setDroppedout(1);
					studentRepository.save(student);
				});
			}
			result = true;
		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
			result = false;
		}
		return result;
	}

	@Transactional
	public boolean leftoutMultiple(List<Integer> ids) {
		boolean result = false;
		try {
			// Original: session.createQuery("update Student set leftout = 1 where id IN (:ids)")
			for (Integer studentId : ids) {
				studentRepository.findById(studentId).ifPresent(student -> {
					student.setLeftout(1);
					studentRepository.save(student);
				});
			}
			result = true;
		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
			result = false;
		}
		return result;
	}

	@Transactional(readOnly = true)
	public List<Student> readListOfStudentsGraduated() {
		List<Student> results = new ArrayList<Student>();
		try {
			// Original: session.createQuery("FROM Student s where s.passedout = 1 order by s.admissionnumber DESC")
			results = studentRepository.findStudentsGraduated();
		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}
		return results;
	}

	@Transactional(readOnly = true)
	public List<Student> readListOfStudentsDropped() {
		List<Student> results = new ArrayList<Student>();
		try {
			// Original: session.createQuery("FROM Student s where s.droppedout = 1 order by s.admissionnumber DESC")
			results = studentRepository.findStudentsDropped();
		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}
		return results;
	}

	@Transactional
	public void restoreMultipleGraduate(List<Integer> ids) {
		try {
			// Original: session.createQuery("update Student set passedout = 0 where id IN (:ids)")
			for (Integer studentId : ids) {
				studentRepository.findById(studentId).ifPresent(student -> {
					student.setPassedout(0);
					studentRepository.save(student);
				});
			}
		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}
	}

	@Transactional
	public void restoreMultipleDroppedout(List<Integer> ids) {
		try {
			// Original: session.createQuery("update Student set droppedout = 0 where id IN (:ids)")
			for (Integer studentId : ids) {
				studentRepository.findById(studentId).ifPresent(student -> {
					student.setDroppedout(0);
					studentRepository.save(student);
				});
			}
		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}
	}

	@Transactional(readOnly = true)
	public List<Parents> getStudentsByClass(String classofStd, int branchId, String currentAcademicYear) {
		List<Parents> results = new ArrayList<Parents>();
		try {
			// Original: "From Parents p where p.student.branchid="+branchId+" AND p.student.classstudying LIKE '"+classofStd+"%' AND p.student.archive=0 AND p.student.yearofadmission = '"+currentAcademicYear+"'"
			results = parentsRepository.findByClassAndBranchAndYear(classofStd + "%", branchId, currentAcademicYear);
		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}
		return results;
	}

	@Transactional(readOnly = true)
	public List<Student> readListOfStudentsLeft() {
		List<Student> results = new ArrayList<Student>();
		try {
			// Original: session.createQuery("FROM Student s where s.leftout = 1 order by s.admissionnumber DESC")
			results = studentRepository.findStudentsLeft();
		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}
		return results;
	}

	@Transactional
	public void restoreMultipleLeftout(List<Integer> ids) {
		try {
			// Original: session.createQuery("update Student set leftout = 0 where id IN (:ids)")
			for (Integer studentId : ids) {
				studentRepository.findById(studentId).ifPresent(student -> {
					student.setLeftout(0);
					studentRepository.save(student);
				});
			}
		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}
	}
}
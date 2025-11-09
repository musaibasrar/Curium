package org.ideoholic.curium.model.student.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.ideoholic.curium.model.degreedetails.dto.Degreedetails;
import org.ideoholic.curium.model.mess.card.dto.Card;
import org.ideoholic.curium.model.parents.dto.Parents;
import org.ideoholic.curium.model.pudetails.dto.Pudetails;
import org.ideoholic.curium.model.std.dto.Classhierarchy;
import org.ideoholic.curium.model.student.dto.Student;
import org.ideoholic.curium.model.student.dto.Studentfeesstructure;
import org.ideoholic.curium.model.student.dto.Studentotherfeesstructure;
import org.ideoholic.curium.repositories.CardRepository;
import org.ideoholic.curium.repositories.ClassHierarchyRepository;
import org.ideoholic.curium.repositories.DegreeDetailsRepository;
import org.ideoholic.curium.repositories.FeesCategoryRepository;
import org.ideoholic.curium.repositories.ParentsRepository;
import org.ideoholic.curium.repositories.PuDetailsRepository;
import org.ideoholic.curium.repositories.StudentFeesStructureRepository;
import org.ideoholic.curium.repositories.StudentOtherFeesStructureRepository;
import org.ideoholic.curium.repositories.StudentRepository;
import org.ideoholic.curium.util.QueryUtil;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.CollectionUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class StudentDetailsDAO {
	
	private final StudentOtherFeesStructureRepository studentOtherFeesStructureRepo;
	private final StudentFeesStructureRepository studentFeesStructureRepo;
	private final ClassHierarchyRepository classHierarchyRepo;
	private final DegreeDetailsRepository degreeDetailsRepo;
	private final FeesCategoryRepository feesCategoryRepo;
	private final PuDetailsRepository puDetailsRepo;
	private final StudentRepository studentRepo;
	private final ParentsRepository parentsRepo;
	private final CardRepository cardRepo;
	private final QueryUtil queryUtil;
	
	@Transactional
	public Student create(Student student) {
		try {
			// original:
			// session.save(student);
			student = studentRepo.save(student);
			log.debug("in add3");
		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}
		return student;
	}

	@Transactional
	public List<Object[]> readListOfObjectsPagination(int offset, int noOfRecords, int branchId) {
		List<Object[]> results = new ArrayList<Object[]>();
		try {
			// original:
			// Query query = session.createQuery("select s.sid, s.studentexternalid, s.admissionnumber, s.name, s.classstudying, f.fathersname, f.mothersname FROM Student s JOIN Parents f ON s.sid=f.student.sid WHERE s.archive = 0 AND s.passedout=0 AND s.droppedout=0 and s.leftout=0 AND s.branchid="+branchId+" order by name ASC");
			// query.setFirstResult(offset);
			// query.setMaxResults(noOfRecords);
			// results = query.list();

			if (noOfRecords <= 0) {
				noOfRecords = 10;
			}
			int page = offset / noOfRecords;
			Pageable pageable = PageRequest.of(page, noOfRecords);
			List<List<Object[]>> fetchResult = studentRepo.findExistingStudentDetailsByBranchIdPageable(branchId, pageable).toList();
			if (!CollectionUtils.isEmpty(fetchResult)) {
				results = fetchResult.get(0);
			}
		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}
		return results;
	}

	@Transactional
	public int getNoOfRecords(int branchId) {
		int noOfRecords = 0;
		try {
			// original:
			// Query query = session.createQuery("select count(*) FROM Student WHERE  archive=0 AND passedout=0 AND droppedout=0 and leftout=0 AND branchid="+branchId).setCacheable(true).setCacheRegion("commonregion");
			// noOfRecords = Integer.parseInt(query.uniqueResult().toString()); 
			Long count = studentRepo.countByArchiveAndPassedoutAndDroppedoutAndLeftoutAndBranchid(0, 0, 0, 0, branchId);
			if (count != null) {
				noOfRecords = count.intValue();
			}
		} catch (Exception hibernateException) { 
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}
		return noOfRecords;
	}

	@Transactional
	public Student readUniqueObject(long id) {
		Student student = null;
		try {
			// original:
			// Query query = session.createQuery("FROM Student as student WHERE student.sid=" + id);
			int intId = (int) id;
			student = studentRepo.findById(intId).orElse(null);
		} catch (Exception hibernateException) { 
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}
		return student;
	}
	
	@Transactional
	public Student readploginUniqueObject(String id) {
		Student student = new Student();
		try {
			// original:
			// Query query = session.createQuery("FROM Student as student WHERE student.studentexternalid='"+id+"'");
			student = studentRepo.findByStudentexternalid(id);
		} catch (Exception hibernateException) { 
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}
		return student;
	}

	@Transactional
	public Parents readUniqueObjectParents(long id) {
		Parents parents = new Parents();
		try {
			// original:
			// Query query = session.createQuery("FROM Parents as parent WHERE parent.student.sid=" + id);
			// parents = (Parents) query.uniqueResult();
			List<Parents> list = parentsRepo.findByStudentSidIn(Collections.singletonList((int) id));
			if (!CollectionUtils.isEmpty(list)) {
				parents = list.get(0);
			}
		} catch (Exception hibernateException) { 
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}
		return parents;
	}

	@Transactional
	public Student update(Student student) {
		try {
			// original:
			// session.update(student);
			student = studentRepo.save(student);
			System.out.println("in add2");
		} catch (Exception hibernateException) { 
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}
		return student;
	}

	@Transactional
	public List<Student> readListOfStudents(int branchId) {
		List<Student> results = new ArrayList<Student>();
		try {
			// original:
			// results = (List<Student>) session.createQuery("FROM Student WHERE archive=0 AND passedout=0 AND droppedout=0 and leftout=0 AND branchid="+branchId).setCacheable(true).setCacheRegion("commonregion").list();

			results = studentRepo.findByArchiveAndPassedoutAndDroppedoutAndLeftoutAndBranchid(0, 0, 0, 0, branchId);
		} catch (Exception hibernateException) { 
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}
		return results;
	}

	@Transactional
	public void archiveMultiple(List<Integer> ids) {
		try {
			// original:
			// Query query = session.createQuery("UPDATE Student SET archive = 1  WHERE id IN (:ids)");
			// query.setParameterList("ids", ids);
			// query.executeUpdate();

			// Bulk update using QueryUtil (kept as last resort for dynamic IN):
			if (ids == null || ids.isEmpty()) {
				return;
			}
			String joined = QueryUtil.joinIds(ids);
			String query = "UPDATE Student SET archive = 1 WHERE sid IN (" + joined + ")";
			queryUtil.runUpdateQuery(query);
		} catch (Exception hibernateException) { 
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}
	}

	@Transactional
	public List<Student> readListOfStudentsArchive(int branchId) {
		List<Student> results = new ArrayList<Student>();
		try {
			// original:
			// results = (List<Student>) session.createQuery("FROM Student s WHERE s.archive = 1 and branchid="+branchId+"").setCacheable(true).setCacheRegion("commonregion").list();
			results = studentRepo.findByArchiveAndBranchid(1, branchId);
		} catch (Exception hibernateException) { 
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}
		return results;
	}

	@Transactional
	public void deleteMultiple(List<Integer> ids, List<Integer> iddetails) {
		try {

			if (ids == null || ids.isEmpty()) {
				return;
			}
			// delete related Pudetails if provided
			if (iddetails != null && !iddetails.isEmpty()) {
				//    Query query3 = session.createQuery("delete FROM Pudetails WHERE idpudetails IN (:iddetails)");
				//    query3.setParameterList("iddetails", iddetails);
				puDetailsRepo.deleteAllById(iddetails);
			}
			
			// original:
			// Query query = session.createQuery("delete FROM Parents as parents WHERE parents.student.sid IN (:ids)");
			// query.setParameterList("ids", ids);
			// Query query2 = session.createQuery("delete FROM Student WHERE sid IN (:ids)");
			// query2.setParameterList("ids", ids);
			// query.executeUpdate();
			// query2.executeUpdate();

			String q1 = "delete FROM Parents as parents WHERE parents.student.sid IN (" + QueryUtil.joinIds(ids) + ")";
			queryUtil.runUpdateQuery(q1);
			studentRepo.deleteAllById(ids);
		} catch (Exception hibernateException) { 
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}
	}

	@Transactional
	public void restoreMultiple(List<Integer> ids) {
		try {
			// original:
			// Query query = session.createQuery("UPDATE Student SET archive = 0  WHERE id IN (:ids)");
			// query.setParameterList("ids", ids);
			// query.executeUpdate();
			if (ids == null || ids.isEmpty()) {
				return;
			}
			String query = "UPDATE Student SET archive = 0 WHERE id IN (" + QueryUtil.joinIds(ids) + ")";
			queryUtil.runUpdateQuery(query);
		} catch (Exception hibernateException) { 
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}
	}

	@Transactional
	public boolean promoteMultiple(List<Student> students, String classStudying, String promotedYear, int branchid) {
		boolean result = false;
		String stringclassStudying = classStudying;
		String[] temp;

		String delimiter = "--";
		temp = stringclassStudying.split(delimiter);
		classStudying = temp[0];
		if (temp.length >= 2) {
		}

		try {
			// original:
			// Query query1 = session.createQuery("FROM Classhierarchy WHERE lowerclass = '"+classStudying+"'  and branchid="+branchid+"");
			List<Classhierarchy> chList = classHierarchyRepo.findByLowerclassAndBranchid(classStudying, branchid);
			Classhierarchy ch = null;
			if (!CollectionUtils.isEmpty(chList)) {
				ch = chList.get(0);
			}

			if (ch != null) {
				for (Student student : students) {
					String[] tempstudent = student.getClassstudying().split(delimiter);
					String sect = "";
					if (tempstudent.length >= 2) {
						sect = tempstudent[1];
					}
					// String hql = "UPDATE Student set classstudying = '"+ch.getUpperclass()+"--"+sect+"', promotedyear='"+promotedYear+"', branchid='"+ch.getBranchid()+"' WHERE sid = "+student.getSid()+"";
					Optional<Student> opFetchedStudent = studentRepo.findById(student.getSid());
					if(opFetchedStudent.isPresent()) {
						Student fetchedStudent = opFetchedStudent.get();
						fetchedStudent.setClassstudying(ch.getUpperclass() + "--" + sect);
						fetchedStudent.setPromotedyear(promotedYear);
						fetchedStudent.setBranchid(ch.getBranchid());
					}
				}
			} else {
				// Query queryUpdateBranchClassHierarchy = session.createQuery("FROM Classhierarchy WHERE lowerclass = '"+classStudying+"'");
				// Classhierarchy chUpdateBranch = (Classhierarchy) queryUpdateBranchClassHierarchy.uniqueResult();
				List<Classhierarchy> chUpdateList = classHierarchyRepo.findByLowerclass(classStudying);
				Classhierarchy chUpdateBranch = null;
				if (!CollectionUtils.isEmpty(chUpdateList)) {
					chUpdateBranch = chUpdateList.get(0);
				}

				if (chUpdateBranch != null) {
					for (Student student : students) {
						String[] tempstudent = student.getClassstudying().split(delimiter);
						String sect = "";
						if (tempstudent.length >= 2) {
							sect = tempstudent[1];
						}
						String hqlUpdateBranch = "UPDATE Student SET classstudying = '" + chUpdateBranch.getUpperclass() + "--" + sect + "', promotedyear='" + promotedYear + "', branchid='" + chUpdateBranch.getBranchid() + "' WHERE sid = " + student.getSid();
						queryUtil.runUpdateQuery(hqlUpdateBranch);
					}
				}
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
	public List<Parents> readListOfObjectsPaginationALL(int offset,
			int noOfRecords, int branchId) {
		List<Parents> results = new ArrayList<Parents>();
		try {
			// original:
			// Query query = session.createQuery("FROM Parents as parents WHERE parents.student.archive=0 and parents.student.passedout=0 AND parents.student.droppedout=0 and parents.student.leftout=0 AND parents.student.branchid ="+branchId+" order by parents.student.name ASC");
			// query.setFirstResult(offset);
			// query.setMaxResults(noOfRecords);
			// results = query.getResultList();
			if (noOfRecords <= 0) {
				noOfRecords = 10;
			}
			int page = offset / noOfRecords;
			results = parentsRepo.findActiveParentsByBranchId(branchId, PageRequest.of(page, noOfRecords)).toList();
		} catch (Exception hibernateException) {  
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			System.out.println("Exception is "+hibernateException);
			hibernateException.printStackTrace();
		}
		return results;
	}

	@Transactional
	public List<Student> readListOfObjectsForIcon(int branchId) {
		List<Student> results = new ArrayList<Student>();
		try {
			// original:
			// Query query = session.createQuery("FROM Student s WHERE s.archive = 0 and s.passedout=0 AND s.droppedout=0 and s.leftout=0 and s.branchid= "+branchId+" order by name ASC");
			results = studentRepo.findByArchiveAndPassedoutAndDroppedoutAndLeftoutAndBranchidOrderByNameAsc(0, 0, 0, 0, branchId);
		} catch (Exception hibernateException) { 
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}
		return results;
	}

	@Transactional
	public void addStudentfeesstructure(List<Studentfeesstructure> listOfstudentfeesstructure, String currentYear) {
		try {
			// original (loop using session queries):
			// Query query = session.createQuery("FROM Studentfeesstructure as sfs WHERE sfs.sid = '"+studentfeesstructure.fetchSid()+"' and sfs.Feescategory.idfeescategory = '"+studentfeesstructure.fetchIdfeescategory()+"' and sfs.academicyear = '"+studentfeesstructure.getAcademicyear()+"'");
			// Studentfeesstructure feesStructure = (Studentfeesstructure) query.uniqueResult();
			// if(feesStructure != null){
			//    Query queryUpdate = session.createQuery("UPDATE Studentfeesstructure SET idfeescategory = '"+studentfeesstructure.fetchIdfeescategory()+"',feesamount = '"+studentfeesstructure.getFeesamount()+"'  WHERE sid = '"+studentf...);
			//    queryUpdate.executeUpdate();
			// } else {
			//    session.save(studentfeesstructure);
			// }

			for (Studentfeesstructure studentfeesstructure : listOfstudentfeesstructure) {
				// Optional<Studentfeesstructure> findFirstByStudentSidAndFeescategoryIdfeescategoryAndAcademicyear(Integer sid, Integer feescatId, String academicyear);
				List<Studentfeesstructure> feeStructureList = studentFeesStructureRepo.findByStudentSidAndFeescategoryIdfeescategoryAndAcademicyear(studentfeesstructure.fetchSid(), studentfeesstructure.fetchIdfeescategory(), studentfeesstructure.getAcademicyear());
				if (feeStructureList != null && !feeStructureList.isEmpty()) {
					// Query queryUpdate = session.createQuery("update Studentfeesstructure set idfeescategory = '"+studentfeesstructure.fetchIdfeescategory()
					// +"',feesamount = '"+studentfeesstructure.getFeesamount()+"'  where sid = '"+studentfeesstructure.fetchSid()+"' and academicyear = '"+currentYear+"'");
					Studentfeesstructure feesStructure = feeStructureList.get(0);
					feesCategoryRepo.findById(studentfeesstructure.fetchIdfeescategory()).ifPresent(feesCat -> {
						feesStructure.setFeescategory(feesCat);
						feesStructure.setFeesamount(studentfeesstructure.getFeesamount());
						studentFeesStructureRepo.save(feesStructure);
					});
				} else {
					studentFeesStructureRepo.save(studentfeesstructure);
				}
			}
		} catch (Exception hibernateException) { 
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}
	}

	@Transactional
	public List<Studentfeesstructure> getStudentFeesStructure(Integer id, String currentYear) {
		List<Studentfeesstructure> results = new ArrayList<Studentfeesstructure>();
		try {
			// original:
			// Query query = session.createQuery("FROM Studentfeesstructure sfs WHERE sfs.student.sid = '"+id+"' and sfs.academicyear = '"+currentYear+"'");
			results = studentFeesStructureRepo.findByStudentSidAndAcademicyear(id, currentYear);
		} catch (Exception hibernateException) { 
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}
		return results;
	}

	@Transactional
	public Parents getStudentRecords(String queryMain) {
		Parents parents = new Parents();
        try {
        	// original:
        	// Query HQLquery = session.createQuery(queryMain);
        	// parents = (Parents) HQLquery.setCacheable(true).setCacheRegion("commonregion").uniqueResult();
            List<Parents> list = (List<Parents>) queryUtil.findByClassLimitedTo(queryMain, Parents.class, 1);
            if (!CollectionUtils.isEmpty(list)) {
            	parents = list.get(0);
            }
        } catch (Exception hibernateException) {
        	TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        	log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
        }
        return parents;
	}

	@Transactional
	public List<Parents> getStudentsList(String query) {
		java.util.List<Parents> parents = new ArrayList<Parents>();
        try {
        	// original:
        	// Query HQLquery = session.createQuery(query);
        	// parents = (java.util.List<Parents>) HQLquery.setCacheable(true).setCacheRegion("commonregion").list();
            parents = (List<Parents>) queryUtil.runGivenQuery(query, Parents.class);
        } catch (Exception hibernateException) {
        	TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        	log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
        }
        return parents;
	}
	
	@Transactional
	public List<Student> getListStudents(String query) {
		java.util.List<Student> student = new ArrayList<Student>();
        try {
        	// original:
        	// Query HQLquery = session.createQuery(query);
        	// student = HQLquery.setCacheable(true).setCacheRegion("commonregion").list();
            student = (List<Student>) queryUtil.runGivenQuery(query, Student.class);
        } catch (Exception hibernateException) { 
        	TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        	log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
        }
        return student;
	}

	@Transactional
	public List<Studentfeesstructure> getStudentFeesStructureDetails(int sfsid) {
		List<Studentfeesstructure> studentFeesStructure = new ArrayList<Studentfeesstructure>();
		try {
			// original:
			// studentFeesStructure = session.createQuery("FROM Studentfeesstructure sfs WHERE sfs.sfsid = '"+sfsid+"'").list();
			studentFeesStructure = studentFeesStructureRepo.findBySfsid(sfsid);
		} catch (Exception hibernateException) { 
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}
		return studentFeesStructure;
	}

	@Transactional
	public boolean updateStudent(Student student) {
		try {
			// original:
			// Query queryUpdate = session.createQuery("UPDATE Student SET reasonleaving = '"+student.getReasonleaving()+"'  WHERE sid = '"+student.getSid()+"'");
			// queryUpdate.executeUpdate();
			studentRepo.findById(student.getSid()).ifPresent(fetchedStudent -> {
				fetchedStudent.setReasonleaving(student.getReasonleaving());
				studentRepo.save(fetchedStudent);
			});
			return true;
		} catch (Exception e) { 
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return false;
	}

    @Transactional
	public void updatePuDetails(Pudetails puDetails) {
		try {
			// original:
			// session.update(puDetails);
			puDetailsRepo.save(puDetails);
		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}
	}
    
    @Transactional
	public void updateDegreeDetails(Degreedetails degreeDetails) {
		try {
			// original:
			// session.update(degreeDetails);
			// transaction.commit();
			degreeDetailsRepo.save(degreeDetails);
		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}
	}

    @Transactional
    public List<Parents> readListStudentsSuperAdmin(int offset, int noOfRecords) {
        List<Parents> results = new ArrayList<Parents>();
        try {
        	// original:
        	// Query query = session.createQuery("FROM Parents as parents WHERE parents.student.archive=0 and parents.student.passedout=0 AND parents.student.droppedout=0 and parents.student.leftout=0 order by parents.student.name ASC");
            if (noOfRecords <= 0) {
            	noOfRecords = 10;
            }
            int page = offset / noOfRecords;
            results = parentsRepo.findAllActiveParentsByBranchId(PageRequest.of(page, noOfRecords)).toList();
        } catch (Exception hibernateException) { 
        	TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        	log.debug("Exception is {}", hibernateException);
            hibernateException.printStackTrace();
        }
        return results;
    }

    @Transactional
    public int getNoOfRecords() {
        List<Student> results = new ArrayList<Student>();
        int noOfRecords = 0;
        try {
        	// original:
        	// results = (List<Student>) session.createQuery("FROM Student WHERE archive=0 and passedout=0 AND droppedout=0 and leftout=0").setCacheable(true).setCacheRegion("commonregion").list();
            List<Student> list = studentRepo.findByArchiveAndPassedoutAndDroppedoutAndLeftout(0, 0, 0, 0);
            noOfRecords = list.size();
        } catch (Exception hibernateException) { 
        	TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        	log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
        }
        return noOfRecords;
    }

	public String getPromotionClass(String classStudying) {
		// TODO Auto-generated method stub
		return null;	
	}

	@Transactional
	public List<Card> getCardDetails(List<Integer> ids) {
		List<Card> cardDetailsList = new ArrayList<>();
		try {
			// original:
			// .createQuery("FROM Card as card WHERE card.sid IN (:ids)");
			cardDetailsList = cardRepo.findBySidIn(ids);
		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();

			throw hibernateException;
		}
		return cardDetailsList;
	}

	@Transactional
	public List<Studentfeesstructure> getStudentFeesStructurebyFeesCategory(Integer id, List<Integer> feesCat) {
		List<Studentfeesstructure> results = new ArrayList<Studentfeesstructure>();
		try {
			// original:
			// createQuery("FROM Studentfeesstructure sfs WHERE sfs.student.sid = '"+id+"' and sfs.feescategory.idfeescategory IN (:feescat)");
			results = studentFeesStructureRepo.findByStudentSidAndFeescategoryIdfeescategoryIn(id, feesCat);
		} catch (Exception hibernateException) { 
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();

			throw hibernateException;
		}
		return results;
	}

	@Transactional
	public List<Studentotherfeesstructure> getStudentOtherFeesStructure(Integer id, String currentYear) {
		List<Studentotherfeesstructure> results = new ArrayList<Studentotherfeesstructure>();
		try {
			// original:
			// .createQuery("FROM Studentotherfeesstructure sfs WHERE sfs.sid = '"+id+"' and sfs.academicyear = '"+currentYear+"'");
			results = studentOtherFeesStructureRepo.findByStudentSidAndAcademicyear(id, currentYear);
		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();

			throw hibernateException;
		}
		return results;
	}
	
	@Transactional
	public List<Studentotherfeesstructure> getotherStudentFeesStructureDetails(int sfsid) {
		List<Studentotherfeesstructure> studentFeesStructure = new ArrayList<Studentotherfeesstructure>();
		try {
			// original:
			// studentFeesStructure = session.createQuery("FROM Studentotherfeesstructure sfs WHERE sfs.sfsid = '"+sfsid+"'").list();
			studentFeesStructure = studentOtherFeesStructureRepo.findBySfsid(sfsid);
		} catch (Exception hibernateException) { 
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);

			hibernateException.printStackTrace();
		}
		return studentFeesStructure;
	}

	@Transactional
	public List<Studentotherfeesstructure> getStudentotherFeesStructurebyFeesCategory(Long id, List<Integer> feesCat) {
		List<Studentotherfeesstructure> results = new ArrayList<Studentotherfeesstructure>();
		try {

			// original:
			// .createQuery("FROM Studentotherfeesstructure sfs WHERE sfs.sid = '"+id+"' and sfs.otherfeescategory.idfeescategory IN (:feescat)");
			results = studentOtherFeesStructureRepo.findByStudentSidAndOtherfeescategoryIdfeescategoryIn(id.intValue(), feesCat);
		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();

			throw hibernateException;
		}
		return results;
	}
	
	@Transactional
	public Studentotherfeesstructure getStudentOtherFeesStructureDetails(int sfsid) {
		Studentotherfeesstructure studentFeesStructure = new Studentotherfeesstructure();
		try {
			// original:
			// studentFeesStructure = (Studentotherfeesstructure) session.createQuery("FROM Studentotherfeesstructure sfs WHERE sfs.sfsid = '"+sfsid+"'").uniqueResult();
			studentFeesStructure = studentOtherFeesStructureRepo.findById(sfsid).orElse(studentFeesStructure);
		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();

			throw hibernateException;
		}
		return studentFeesStructure;
	}
	
	@Transactional
	public List<Object[]> readStudentsParentsPerBranch(int branchId) {
		List<Object[]> results = new ArrayList<>();
		try {

			// original:
			// .createQuery("select s.sid, s.studentexternalid, s.admissionnumber, s.name, s.classstudying, f.fathersname, f.mothersname FROM Student s JOIN Parents f ON s.sid=f.student.sid WHERE s.archive = 0 AND s.passedout=0 AND s.droppedout=0 and s.leftout=0 AND s.branchid="+branchId+" order by name ASC");
			// Prefer repository method:
			results = studentRepo.findStudentDetailsByBranchId(branchId);
		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();

			throw hibernateException;
		}
		return results;
	}

	@Transactional
	public List<Student> getListofsid(String classsec) {
		List<Student> results = new ArrayList<Student>();
		try {
			// original:
			// Query query = session.createQuery("FROM Student WHERE classstudying = "+classsec+"");
			results = studentRepo.findByClassstudying(classsec);
		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
			
			throw hibernateException;
		}
		return results;
	}
	
	@Transactional
	public List<Parents> getReferredList(List<Integer> sidList) {
		List<Parents> detailsList = new ArrayList<>();
		try {
			// original:
			// Query query = session.createQuery("FROM Parents as parents WHERE parents.student.sid IN (:ids)");
			detailsList = parentsRepo.findByStudentSidIn(sidList);
		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
			
			throw hibernateException;
		}
		return detailsList;
	}

	@Transactional
	public Student readUniqueStudent(String queryString) {
		Student student = new Student();
		try {
			List<Student> studentList = queryUtil.findByClassLimitedTo(queryString, Student.class, 1);
			if (!CollectionUtils.isEmpty(studentList)) {
				student = studentList.get(0);
			}
		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();

			throw hibernateException;
		}
		return student;
	}

}
package org.ideoholic.curium.model.std.service;

import java.util.ArrayList;
import java.util.List;

import org.ideoholic.curium.model.std.dto.*;
import org.ideoholic.curium.model.student.dto.StudentIdsDto;
import org.ideoholic.curium.model.parents.dto.Parents;
import org.ideoholic.curium.model.std.dao.StandardDetailsDAO;
import org.ideoholic.curium.model.student.dto.Student;
import org.ideoholic.curium.util.DataUtil;
import org.ideoholic.curium.util.ResultResponse;
import org.springframework.stereotype.Service;

@Service
public class StandardService {


    public ResultResponse createClass(ClassDto classDto, String branchId, String userId) {
        ResultResponse result = ResultResponse.builder().build();
        if(branchId!=null){
            Classsec classsec = new Classsec();
            classsec.setClassdetails(DataUtil.emptyString(classDto.getClassDetails()));
            classsec.setSection(DataUtil.emptyString(classDto.getSection()));
            classsec.setBranchid(Integer.parseInt(branchId));
            classsec.setUserid(Integer.parseInt(userId));
            new StandardDetailsDAO().create(classsec);
            viewClasses();
            result.setSuccess(true);
            return result;
            }
            
        return result;
    
    }

    //TODO:This method is placed here for PeriodService. Please delete this method after migrating PeriodAction.
    public boolean viewClasses(){
        return false;
    }

    public ResultResponse viewClasses(String branchId) {

        if(branchId!=null){
            List<Classsec> classsecList = new StandardDetailsDAO().viewClasses(Integer.parseInt(branchId));
            return ResultResponse
                    .builder()
                    .resultList(classsecList)
                    .success(true)
                    .build();
        }

        return ResultResponse
                .builder()
                .success(false)
                .build();
    }

    // TODO: Migrate the return type of this method once viewClasses() is fully migrated
    public ResultResponse deleteClasses(ClassIdsDto dto) {
        ResultResponse result = ResultResponse.builder().build();
        String[] classIds = dto.getClassIds();
        if (classIds != null) {
                List<Integer> ids = new ArrayList();
                for (String id : classIds) {
                        ids.add(Integer.valueOf(id));
                }
                new StandardDetailsDAO().deleteMultiple(ids);
                result.setSuccess(viewClasses());
                return result;
        }
        return result;
    }

	public void addClassHierarchy(UpperLowerClassDto dto, String branchId, String userId) {
        
        if(branchId!=null){
            Classhierarchy classHierarchy = new Classhierarchy();
            classHierarchy.setLowerclass(DataUtil.emptyString(dto.getLowerClass()));
            classHierarchy.setUpperclass(DataUtil.emptyString(dto.getUpperClass()));
            classHierarchy.setBranchid(Integer.parseInt(branchId));
            classHierarchy.setUserid(Integer.parseInt(userId));
            new StandardDetailsDAO().createClassHierarchy(classHierarchy);
            viewClasses();
            }
    }

	public void deleteClassHierarchy(ClassIdsDto dto) {
        
        String[] classIds = dto.getClassIds();
        if (classIds != null) {
                List<Integer> ids = new ArrayList();
                for (String id : classIds) {
                        ids.add(Integer.valueOf(id));
                }
                new StandardDetailsDAO().deleteClassHierarchy(ids);
                viewClasses();
        }
    }

	public ResultResponse viewClassHierarchy(String branchId) {
        ResultResponse result = ResultResponse.builder().build();
        if(branchId!=null){
            List<Classhierarchy> classHierarchy = new StandardDetailsDAO().viewClassHierarchy(Integer.parseInt(branchId));
            result.setResultList(classHierarchy);
        }
        result.setSuccess(true);
        return result;
    }

	public ResultResponse graduateMultiple(StudentIdsDto dto) {
        String[] studentIds = dto.getStudentIds();
        ResultResponse result = ResultResponse.builder().build();
            List<Integer> ids = new ArrayList();
            for (String id : studentIds) {
                System.out.println("id" + id);
                ids.add(Integer.valueOf(id));
        }
        if (new StandardDetailsDAO().graduateMultiple(ids)) {
            result.setSuccess(true);
        }
        return result;

	}
	
	public ResultResponse droppedoutMultiple(StudentIdsDto dto) {
        String[] studentIds = dto.getStudentIds();
        ResultResponse result = ResultResponse.builder().build();
            List<Integer> ids = new ArrayList();
            for (String id : studentIds) {
                System.out.println("id" + id);
                ids.add(Integer.valueOf(id));
        }
        if(new StandardDetailsDAO().droppedoutMultiple(ids)){
            result.setSuccess(true);
        }
        return result;
    }

	public ResultResponse leftoutMultiple(StudentIdsDto dto) {
		String[] studentIds = dto.getStudentIds();
		ResultResponse result = ResultResponse.builder().build();
		List<Integer> ids = new ArrayList();
		for (String id : studentIds) {
			System.out.println("id" + id);
			ids.add(Integer.valueOf(id));

		}
		if(new StandardDetailsDAO().leftoutMultiple(ids)) {
			result.setSuccess(true);
		}
		return result;
	}
	
	public ResultResponse viewGraduated() {

        ResultResponse result = ResultResponse.builder().build();

        try {
                List<Student> list = new StandardDetailsDAO().readListOfStudentsGraduated();
                result.setResultList(list);
                result.setSuccess(true);
        } catch (Exception e) {
                e.printStackTrace();
        }
        return result;
}

	public ResultResponse viewDropped() {

        ResultResponse result = ResultResponse.builder().build();

        try {
                List<Student> list = new StandardDetailsDAO().readListOfStudentsDropped();
                result.setResultList(list);
                result.setSuccess(true);
        } catch (Exception e) {
                e.printStackTrace();
        }
        return result;
	}
	
    public void restoreMultipleGraduate(StudentIdsDto dto) {
        String[] studentIds = dto.getStudentIds();
        if (studentIds != null) {
                List<Integer> ids = new ArrayList();
                for (String id : studentIds) {
                        ids.add(Integer.valueOf(id));

                }
                new StandardDetailsDAO().restoreMultipleGraduate(ids);
        }
}

    public void restoreMultipleDroppedout(StudentIdsDto dto) {
        String[] studentIds = dto.getStudentIds();
        if (studentIds != null) {
                List<Integer> ids = new ArrayList();
                for (String id : studentIds) {
                        ids.add(Integer.valueOf(id));

                }
                new StandardDetailsDAO().restoreMultipleDroppedout(ids);
        }
    }

    @SuppressWarnings("finally")
	public ResultResponse searchByClass(StdOfClassDto dto, String branchId, String currentAcademicYear) {
		
		String classofStd = dto.getClassOfStd();
		ResultResponse result = ResultResponse.builder().build();
		
		if(branchId!=null){
			try {
				if(classofStd!=null) {
					classofStd=classofStd+"--";
				}
				List<Parents> studentList = new StandardDetailsDAO().getStudentsByClass(classofStd, Integer.parseInt(branchId), currentAcademicYear);
                result.setResultList(studentList);
                result.setSuccess(true);
			} catch (Exception e) {
                result.setSuccess(false);
			} 
		}
		return result;

	}

	public ResultResponse viewleft() {

        ResultResponse result = ResultResponse.builder().build();

        try {
                List<Student> list = new StandardDetailsDAO().readListOfStudentsLeft();
                result.setResultList(list);
                result.setSuccess(true);
        } catch (Exception e) {
                e.printStackTrace();
        }
        return result;
	}

	public void restoreMultipleLeftout(StudentIdsDto dto) {
        String[] studentIds = dto.getStudentIds();
        if (studentIds != null) {
                List<Integer> ids = new ArrayList();
                for (String id : studentIds) {
                        ids.add(Integer.valueOf(id));

                }
                new StandardDetailsDAO().restoreMultipleLeftout(ids);
        }
    }
    
}

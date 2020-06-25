package org.ideoholic.standard.service;

import java.util.ArrayList;
import java.util.List;

import com.model.std.dao.StandardDetailsDAO;
import com.model.std.dto.Classhierarchy;
import com.model.std.dto.Classsec;
import com.model.student.dto.Student;
import com.util.DataUtil;

public class StandardServiceImpl implements StandardService {
	
	public String viewClasses(String branchId) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
        if(branchId!=null){
            List<Classsec> classsecList = new StandardDetailsDAO().viewClasses(Integer.parseInt(branchId.toString()));
            sb.append("classdetailslist").append(classsecList);
            sb.append("result:").append(true);
            return sb.toString();
        }
        sb.append("result:").append(false);
        sb.append("}");
        return sb.toString();
    }

public String createClass(String branchId, String classDetails,String section) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
        
        if(branchId!=null){
            Classsec classsec = new Classsec();
            classsec.setClassdetails(DataUtil.emptyString(classDetails));
            classsec.setSection(DataUtil.emptyString(section));
            classsec.setBranchid(Integer.parseInt(branchId.toString()));
            new StandardDetailsDAO().create(classsec);
            viewClasses(branchId);
            sb.append("result:").append(true);
            return sb.toString();
            }
            
        sb.append("result:").append(false);
        sb.append("}");
        return sb.toString();
    
    }

public String deleteClasses(String[] classIds,String branchId) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
    
    if (classIds != null) {
            List ids = new ArrayList();
            for (String id : classIds) {
                    ids.add(Integer.valueOf(id));
            }
            new StandardDetailsDAO().deleteMultiple(ids);
            return viewClasses(branchId);
    }
    sb.append("result:").append(false);
    sb.append("}");
    return sb.toString();
}

public String addClassHierarchy(String branchId,String lowerClass,String upperClass) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
	
    if(branchId!=null){
        Classhierarchy classHierarchy = new Classhierarchy();
        classHierarchy.setLowerclass(DataUtil.emptyString(lowerClass));
        classHierarchy.setUpperclass(DataUtil.emptyString(upperClass));
        classHierarchy.setBranchid(Integer.parseInt(branchId.toString()));
        new StandardDetailsDAO().createClassHierarchy(classHierarchy);
        viewClasses(branchId);
        }
    sb.append("}");
	return sb.toString();
}

public String deleteClassHierarchy(String[] classIds,String branchId) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
    
    if (classIds != null) {
            List ids = new ArrayList();
            for (String id : classIds) {
                    ids.add(Integer.valueOf(id));
            }
            new StandardDetailsDAO().deleteClassHierarchy(ids);
            viewClasses(branchId);
    }
    sb.append("}");
	return sb.toString();
}

public String graduateMultiple(String[] studentIds) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
	boolean result = false;
		List ids = new ArrayList();
		for (String id : studentIds) {
			System.out.println("id" + id);
			ids.add(Integer.valueOf(id));
	}
	if (new StandardDetailsDAO().graduateMultiple(ids)) {
		result = true;
	}
	sb.append("}");
	return sb.toString();

}

public String droppedoutMultiple(String[] studentIds) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
	boolean result = false;
		List ids = new ArrayList();
		for (String id : studentIds) {
			System.out.println("id" + id);
			ids.add(Integer.valueOf(id));
		}
		if(new StandardDetailsDAO().droppedoutMultiple(ids)){
			result = true;
		}
		sb.append("}");
		return sb.toString();
	}

public String leftoutMultiple(String[] studentIds) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
	boolean result = false;
	List ids = new ArrayList();
	for (String id : studentIds) {
		System.out.println("id" + id);
		ids.add(Integer.valueOf(id));

	}
	if(new StandardDetailsDAO().leftoutMultiple(ids)) {
		result = true;
	}
	sb.append("}");
	return sb.toString();
}

public String viewGraduated() {
	StringBuffer sb = new StringBuffer();
	sb.append("{");

    boolean result = false;

    try {
            List<Student> list = new StandardDetailsDAO().readListOfStudentsGraduated();
            sb.append("studentListGraduated").append(list);
            result = true;
    } catch (Exception e) {
            e.printStackTrace();
    }
    sb.append("}");
    return sb.toString();
}

public String viewDropped() {
	StringBuffer sb = new StringBuffer();
	sb.append("{");

    boolean result = false;

    try {
            List<Student> list = new StandardDetailsDAO().readListOfStudentsDropped();
            sb.append("studentListDropped").append(list);
            result = true;
    } catch (Exception e) {
            e.printStackTrace();
    }
    sb.append("}");
    return sb.toString();
}

public String viewleft() {
	StringBuffer sb = new StringBuffer();
	sb.append("{");

    boolean result = false;

    try {
            List<Student> list = new StandardDetailsDAO().readListOfStudentsLeft();
            sb.append("studentListLeft").append(list);
            result = true;
    } catch (Exception e) {
            e.printStackTrace();
    }
    sb.append("}");
    return sb.toString();
}

public String restoreMultipleGraduate(String[] studentIds) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
    if (studentIds != null) {
            List ids = new ArrayList();
            for (String id : studentIds) {
                    ids.add(Integer.valueOf(id));

            }
            new StandardDetailsDAO().restoreMultipleGraduate(ids);
    }
    sb.append("}");
	return sb.toString();
}

public String restoreMultipleDroppedout(String[] studentIds) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
    if (studentIds != null) {
            List ids = new ArrayList();
            for (String id : studentIds) {
                    ids.add(Integer.valueOf(id));

            }
            new StandardDetailsDAO().restoreMultipleDroppedout(ids);
    }
    sb.append("}");
	return sb.toString();
}

public String restoreMultipleLeftout(String[] studentIds) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
    if (studentIds != null) {
            List ids = new ArrayList();
            for (String id : studentIds) {
                    ids.add(Integer.valueOf(id));

            }
            new StandardDetailsDAO().restoreMultipleLeftout(ids);
    }
    sb.append("}");
	return sb.toString();
}

@SuppressWarnings("finally")
public String searchByClass(String classofStd,String branchId) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
	
	boolean result = false;
	
	if(branchId!=null){
		try {
			if(classofStd!=null) {
				classofStd=classofStd+"--";
			}
			List<Student> studentList = new StandardDetailsDAO().getStudentsByClass(classofStd, Integer.parseInt(branchId.toString()));
			sb.append("studentList").append(studentList);
			result = true;
		} catch (Exception e) {
			result = false;
		} 
	}
	sb.append("}");
	return sb.toString();

}
}

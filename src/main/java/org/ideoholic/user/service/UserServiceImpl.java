package org.ideoholic.user.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.model.academicyear.dao.YearDAO;
import com.model.academicyear.dto.Currentacademicyear;
import com.model.user.dao.UserDAO;
import com.model.user.dto.Login;

@Service
public class UserServiceImpl implements UserService {
	private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	public String authenticateUser(String userName, String password) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		Login login = new UserDAO().readUniqueObject(userName, password);

		if (login != null) {
			Currentacademicyear currentAcademicYear = new YearDAO().showYear();
			String academicyear = "";
			if (currentAcademicYear != null) {
				academicyear = currentAcademicYear.getCurrentacademicyear();
			}
			sb.append("currentAcademicYear:").append(academicyear);
			sb.append(", username:").append(login.getUsername());
			sb.append(", branchid:").append(login.getBranch().getIdbranch());
			sb.append("branchname:").append(login.getBranch().getBranchname());
			String[] userType = login.getUsertype().split("-");
			sb.append(", userType:").append(userType[0]);
			sb.append(", typeOfUser:").append(userType[0]);
			sb.append(", userAuth:").append(userType[0]);

		} else {
			sb.append("authenticated:").append(false);
		}
		sb.append("}");
		return sb.toString();
	}

	@Override
	public String logout() {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("logout:").append(true);
		sb.append("}");
		new UserDAO().sessionClose();
		return sb.toString();
	}
	
   public String changePassword(String currentPassword, String newPassword, String confirmNewPassword) {
		
		StringBuffer sb = new StringBuffer();
		sb.append("{"); 
         Login login = new UserDAO().readPassword(currentPassword);
        
        if (login != null && newPassword.equals(confirmNewPassword)) {
            login.setPassword(newPassword);  
            login = new UserDAO().update(login);
            sb.append("result:").append(true);
        } else {
        	sb.append("result:").append(false);
        }
        
        sb.append("}");
        return sb.toString();
    }



}


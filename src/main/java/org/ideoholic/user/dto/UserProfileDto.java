package org.ideoholic.user.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserProfile {
	private String userName;
	private String password;

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String firstName) {
		this.userName = firstName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String lastName) {
		this.password = lastName;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("userName: ").append(getUserName());
		sb.append(", password: ").append(getPassword());
		sb.append("}");
		return sb.toString();
	}
}
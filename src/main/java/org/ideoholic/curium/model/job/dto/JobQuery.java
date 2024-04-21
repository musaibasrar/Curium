package org.ideoholic.curium.model.job.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.ideoholic.curium.model.employee.dto.Teacher;
import org.ideoholic.curium.model.feescollection.dto.Feescollection;
import org.ideoholic.curium.model.parents.dto.Parents;
import org.ideoholic.curium.model.task.dto.Task;

/**
 * Branch generated by hbm2java
 */
@Entity
@Table(name = "h_job")
public class JobQuery implements java.io.Serializable {

	private Integer id;
	private Integer stdid;
	private String externalid;
	private Integer staffid;
	private String response;
	private Integer createduserid;
	private Integer updateduserid;
	private Date createddate;
	private Date updateddate;
	private Date expecteddeliverydate;
	private Integer branchid;
	private String academicyear;
	private String status;
	private Teacher teacher;
	private String feedback;
	private String referredby;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "JobQuery")
	private Set<Task> tasks =
			new HashSet<Task>(0);
	
	public JobQuery() {
	}

	public JobQuery(Integer stdid, String externalid, String response, Integer createduserid, Integer updateduserid, 
			Set<Task> tasks,Date createddate, Date updateddate, Date expecteddeliverydate, Integer branchid, String academicyear, String status, String feedback, String referredby){
		this.stdid = stdid;
		this.externalid = externalid;
		this.response = response;
		this.createduserid = createduserid;
		this.updateduserid = updateduserid;
		this.createddate = createddate;
		this.updateddate = updateddate;
		this.branchid = branchid;
		this.academicyear = academicyear;
		this.status = status;
		this.feedback = feedback;
		this.expecteddeliverydate = expecteddeliverydate;
		this.tasks = tasks;
		this.referredby = referredby;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStdid() {
		return stdid;
	}

	public void setStdid(Integer stdid) {
		this.stdid = stdid;
	}

	public String getExternalid() {
		return externalid;
	}

	public void setExternalid(String externalid) {
		this.externalid = externalid;
	}

	public Integer getCreateduserid() {
		return createduserid;
	}

	public void setCreateduserid(Integer createduserid) {
		this.createduserid = createduserid;
	}

	public Integer getUpdateduserid() {
		return updateduserid;
	}

	public void setUpdateduserid(Integer updateduserid) {
		this.updateduserid = updateduserid;
	}

	public Date getCreateddate() {
		return createddate;
	}

	public void setCreateddate(Date createddate) {
		this.createddate = createddate;
	}

	public Integer getBranchid() {
		return branchid;
	}

	public void setBranchid(Integer branchid) {
		this.branchid = branchid;
	}

	public String getAcademicyear() {
		return academicyear;
	}

	public void setAcademicyear(String academicyear) {
		this.academicyear = academicyear;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getStaffid() {
		return staffid;
	}


	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public Date getUpdateddate() {
		return updateddate;
	}

	public void setUpdateddate(Date updateddate) {
		this.updateddate = updateddate;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public void setStaffid(Integer staffid) {
		this.staffid = staffid;
	}

	public Date getExpecteddeliverydate() {
		return expecteddeliverydate;
	}

	public void setExpecteddeliverydate(Date expecteddeliverydate) {
		this.expecteddeliverydate = expecteddeliverydate;
	}

	public Set<Task> getTasks() {
		return tasks;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}

	public String getReferredby() {
		return referredby;
	}

	public void setReferredby(String referredby) {
		this.referredby = referredby;
	}

}

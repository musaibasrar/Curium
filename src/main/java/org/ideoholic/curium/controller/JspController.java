package org.ideoholic.curium.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class JspController {

	@GetMapping("/index_superadmin")
	public String getIndexSuperadmin() {
		return "index_superadmin";
	}

	@RequestMapping(value = { "/", "/index_admin" }, method = RequestMethod.GET)
	public String getIndexAdmin() {
		return "index_admin";
	}

	@GetMapping("/index_feescollector")
	public String getIndexFeescollector() {
		return "index_feescollector";
	}

	@GetMapping("/index")
	public String getIndex() {
		return "index";
	}

	@GetMapping("/login")
	public String getLogin() {
		return "login";
	}

	@GetMapping("/loginFail")
	public String getLoginFail() {
		return "loginFail";
	}

	@GetMapping("/index_reception")
	public String getIndexReception() {
		return "index_reception";
	}

	@GetMapping("/addStudent")
	public String getAddStudent() {
		return "addStudent";
	}

	@GetMapping("/check")
	public String getCheck() {
		return "check";
	}

	@GetMapping("/addContact")
	public String getAddContact() {
		return "addContact";
	}

	@GetMapping("/addEmployee")
	public String getAddEmployee() {
		return "addEmployee";
	}

	@GetMapping("/feesCollectionDetails")
	public String getFeesCollectionDetails() {
		return "feesCollectionDetails";
	}

	@GetMapping("/stampfees")
	public String getStampFees() {
		return "stampfees";
	}

	@GetMapping("/left")
	public String getLeft() {
		return "left";
	}

	@GetMapping("/leftsettings")
	public String getLeftSettings() {
		return "leftsettings";
	}

	@GetMapping("/sendsms")
	public String getSendSms() {
		return "sendsms";
	}

	@GetMapping("/left_admin")
	public String getLeftAdmin() {
		return "left_admin";
	}

	@GetMapping("/welcome")
	public String getWelcome() {
		return "welcome";
	}

	@GetMapping("/left_superadmin")
	public String getLeftSuperadmin() {
		return "left_superadmin";
	}

	@GetMapping("/sendsms_superadmin")
	public String getSendSmsSuperadmin() {
		return "sendsms_superadmin";
	}

	@GetMapping("/left_reception")
	public String getLeftReception() {
		return "left_reception";
	}

	@GetMapping("/header")
	public String getHeader() {
		return "header";
	}

	@GetMapping("/header_feescollector")
	public String getHeaderFeescollector() {
		return "header_feescollector";
	}

	@GetMapping("/header_admin")
	public String getHeaderAdmin() {
		return "header_admin";
	}

	@GetMapping("/header_superadmin")
	public String getHeaderSuperadmin() {
		return "header_superadmin";
	}

	@GetMapping("/header1")
	public String getHeader1() {
		return "header1";
	}

	@GetMapping("/notication")
	public String getNotication() {
		return "notication";
	}

	@GetMapping("/feescancelledreceipts")
	public String getFeesCancelledReceipts() {
		return "feescancelledreceipts";
	}

	@GetMapping("/Backup&Restore")
	public String getBackupRestore() {
		return "Backup&Restore";
	}

	@GetMapping("/changePassword")
	public String getChangePassword() {
		return "changePassword";
	}

	@GetMapping("/uploadattendance")
	public String getUploadAttendance() {
		return "uploadattendance";
	}

	@GetMapping("/sendemail")
	public String getSendEmail() {
		return "sendemail";
	}

	@GetMapping("/incomestatement")
	public String getIncomeStatement() {
		return "incomestatement";
	}

	@GetMapping("/trialbalance")
	public String getTrialBalance() {
		return "trialbalance";
	}

	@GetMapping("/feesstructure")
	public String getFeesStructure() {
		return "feesstructure";
	}

	@GetMapping("/AdvanceSearch")
	public String getAdvanceSearch() {
		return "AdvanceSearch";
	}

	@GetMapping("/Promotion")
	public String getPromotion() {
		return "Promotion";
	}

	@GetMapping("/currentIssue")
	public String getCurrentIssue() {
		return "currentIssue";
	}

	@GetMapping("/studentsdetailsreports")
	public String getStudentsDetailsReports() {
		return "studentsdetailsreports";
	}

	@GetMapping("/adminexpenses")
	public String getAdminExpenses() {
		return "adminexpenses";
	}

	@GetMapping("/marksentry")
	public String getMarksEntry() {
		return "marksentry";
	}

	@GetMapping("/attendancemark")
	public String getAttendanceMark() {
		return "attendancemark";
	}

	@GetMapping("/attendanceexport")
	public String getAttendanceExport() {
		return "attendanceexport";
	}

	@GetMapping("/generateids")
	public String getGenerateIds() {
		return "generateids";
	}

	@GetMapping("/studentsdetailsbonafide")
	public String getStudentsDetailsBonafide() {
		return "studentsdetailsbonafide";
	}

	@GetMapping("/progressreport")
	public String getProgressReport() {
		return "progressreport";
	}

}
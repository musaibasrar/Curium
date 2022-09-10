package org.ideoholic.curium.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class JspController {

	@GetMapping("/")
	public String getLogin() {
		return "login";
	}

	@GetMapping("/index_superadmin")
	public String getIndexSuperadmin() {
		return "index_superadmin";
	}

	@GetMapping("/index_admin")
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
	
	@GetMapping("/reports")
	public String getReports() {
		return "reports";
	}
	
	@GetMapping("/printstudentdetailsfeesstructure")
	public String getPrintstudentdetailsfeesstructure() {
		return "printstudentdetailsfeesstructure";
	}
	
	@GetMapping("/feesreportexportsuccess")
	public String getFeesreportexportsuccess() {
		return "feesreportexportsuccess";
	}
	
	@GetMapping("/exportfailure")
	public String getExportfailure() {
		return "exportfailure";
	}
	
	@GetMapping("/trialbalanceexportsuccess")
	public String getTrialbalanceexportsuccess() {
		return "trialbalanceexportsuccess";
	}
	
	@GetMapping("/trialbalanceprint")
	public String getTrialbalanceprint() {
		return "trialbalanceprint";
	}
	
	@GetMapping("/welcomesinglebranch")
	public String getWelcomeSinglebranch() {
		return "welcomesinglebranch";
	}
	
	@GetMapping("/generalledgerreport")
	public String getGeneralledgerreport() {
		return "generalledgerreport";
	}
	
	@GetMapping("/printgeneralledgerreport")
	public String getPrintgeneralledgerreport() {
		return "printgeneralledgerreport";
	}
	
	@GetMapping("/adminexpensesreport")
	public String getAdminExpensesReport() {
		return "adminexpensesreport";
	}
	
	@GetMapping("/mainsearch")
	public String getMainSearch() {
		return "mainsearch";
	}
	
	@GetMapping("/advancesearchresult")
	public String getAdvanceSearchResult() {
		return "advancesearchresult";
	}
	
	@GetMapping("/exportsuccessappointment")
	public String getExportSuccessAppointment() {
		return "exportsuccessappointment";
	}
	
	@GetMapping("/printappointmentsreport")
	public String getPrintAppointmentsReport() {
		return "printappointmentsreport";
	}
	
	@GetMapping("/appointmentsreport")
	public String getAppointmentsReport() {
		return "appointmentsreport";
	}
	
	@GetMapping("/appointments")
	public String getAppointments() {
		return "appointments";
	}
	
	@GetMapping("/appointmentsuccess")
	public String getAppointmentsuccess() {
		return "appointmentsuccess";
	}
	
	@GetMapping("/exportsuccessquery")
	public String getExportsuccessquery() {
		return "exportsuccessquery";
	}
	
	@GetMapping("/feedbackthankyou")
	public String getFeedbackthankyou() {
		return "feedbackthankyou";
	}
	
	@GetMapping("/feedbackthankyoufail")
	public String getFeedbackthankyoufail() {
		return "feedbackthankyoufail";
	}
	
	@GetMapping("/printqueriesreport")
	public String getPrintqueriesreport() {
		return "printqueriesreport";
	}
	
	@GetMapping("/queriesreport")
	public String getQueriesreport() {
		return "queriesreport";
	}
	
	@GetMapping("/queries")
	public String getQueries() {
		return "queries";
	}
	
	@GetMapping("/querysuccess")
	public String getQuerysuccess() {
		return "querysuccess";
	}
	
	@GetMapping("/index_staff")
	public String getIndexstaff() {
		return "index_staff";
	}
	
	@GetMapping("/header_staff")
	public String getHeaderstaff() {
		return "header_staff";
	}
	
	@GetMapping("/createquery")
	public String getCreatequery() {
		return "createquery";
	}
	
	@GetMapping("/tasks")
	public String getTasks() {
		return "tasks";
	}
	
	@GetMapping("/viewalltasks")
	public String getViewalltasks() {
		return "viewalltasks";
	}
	
	@GetMapping("/left_staff")
	public String getLeftStaff() {
		return "left_staff";
	}
	
	@GetMapping("/viewalltasksreadonly")
	public String getViewAllTasksReadOnly() {
		return "viewalltasksreadonly";
	}
	
	@GetMapping("/header_reception")
	public String getHeaderReception() {
		return "header_reception";
	}
	
	@GetMapping("/queriesreadonly")
	public String getQueriesReadOnly() {
		return "queriesreadonly";
	}
	
	@GetMapping("/createtask")
	public String getCreatetask() {
		return "createtask";
	}
	
	@GetMapping("/tasksreport")
	public String getTasksreport() {
		return "tasksreport";
	}
	
	@GetMapping("/printtasksreport")
	public String getPrintTasksReport() {
		return "printtasksreport";
	}
	
	@GetMapping("/createcases")
	public String getCreateCases() {
		return "createcases";
	}
	
	@GetMapping("/cases")
	public String getCases() {
		return "cases";
	}
	
	@GetMapping("/createnewcases")
	public String getCreateNewCases() {
		return "createnewcases";
	}
	
	@GetMapping("/casesreport")
	public String getCasesReport() {
		return "casesreport";
	}
	
	@GetMapping("/printcasesreport")
	public String getPrintCasesReport() {
		return "printcasesreport";
	}
	
	@GetMapping("/createcaveat")
	public String getCreateCaveat() {
		return "createcaveat";
	}
	
	@GetMapping("/caveats")
	public String getcaveats() {
		return "caveat";
	}
	
	@GetMapping("/createnewcaveats")
	public String getCreateNewCaveat() {
		return "createnewcaveat";
	}
	
	@GetMapping("/caveatsreport")
	public String getCaveatReport() {
		return "caveatreport";
	}
	
	@GetMapping("/printcaveatsreport")
	public String getPrintCaveatsReport() {
		return "printcaveatsreport";
	}
}
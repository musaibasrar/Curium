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

	@GetMapping("/index_teacher")
	public String getIndexTeacher() {
		return "index_teacher";
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

	@GetMapping("/left_teacher")
	public String getLeftTeacher() {
		return "left_teacher";
	}

	@GetMapping("/header")
	public String getHeader() {
		return "header";
	}

	@GetMapping("/header_teacher")
	public String getHeaderTeacher() {
		return "header_teacher";
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
	
	@GetMapping("/studentsadmissionreports")
	public String getStudentsAdmissionReports() {
		return "studentsadmissionreports";
	}
	
	@GetMapping("/studentspendingadmissionreports.jsp")
	public String getStudentsPendingAdmissionReports() {
		return "studentspendingadmissionreports";
	}

	@GetMapping("/feesstampdue.jsp")
	public String getfeesStampDue() {
		return "feesstampdue";
	}
	
	@GetMapping("/printadmissionform.jsp")
	public String getPrintAdmissionForm() {
		return "printadmissionform";
	}
	
	@GetMapping("/header_officeadmin")
	public String getHeaderOfficeAdmin() {
		return "header_officeadmin";
	}
	
	@GetMapping("/index_officeadmin")
	public String getIndexOfficeAdmin() {
		return "index_officeadmin";
	}
	
	@GetMapping("/left_officeadmin")
	public String getLeftOfficeAdmin() {
		return "left_officeadmin";
	}
	
	@GetMapping("/leftsettings_officeadmin")
	public String getLeftSettingsOfficeAdmin() {
		return "leftsettings_officeadmin";
	}
	
	@GetMapping("/reports_officeadmin")
	public String getReportsOfficeAdmin() {
		return "reports_officeadmin";
	}
	@GetMapping("/reports_parents")
	public String getReportsParents() {
		return "reports_parents";
	}
	
	@GetMapping("/header_marksentry")
	public String getHeaderMarksEntry() {
		return "header_marksentry";
	}
	
	@GetMapping("/index_marksentry")
	public String getIndexMarksEntry() {
		return "index_marksentry";
	}
	
	
	@GetMapping("/left_marksentry")
	public String getLeftMarksEntry() {
		return "left_marksentry";
	}
	
	@GetMapping("/previewFeesReceiptDetail")
	public String getPreviewFeesReceiptDetail() {
		return "previewFeesReceiptDetail";
	}
	
	@GetMapping("/printReceiptFeesDetail")
	public String getPrintReceiptFeesDetail() {
		return "printReceiptFeesDetail";
	}
	
	@GetMapping("/billsreport")
	public String getBillsreport() {
		return "billsreport";
	}
	
	@GetMapping("/bill")
	public String getBill() {
		return "bill";
	}
	
	@GetMapping("/importfile")
	public String getImportFile() {
		return "importfile";
	}
	
	@GetMapping("/duereport")
	public String getDuereport() {
		return "duereport";
	}
	
	@GetMapping("/otherfeecategory")
	public String getOtherfeecategory() {
		return "otherfeecategory";
	}
	
	@GetMapping("/otherstampfees")
	public String getOtherStampFees() {
		return "otherstampfees";
	}
	
	@GetMapping("/otherfeesCollection")
	public String getOtherfeesCollection() {
		return "otherfeesCollection";
	}
	
	@GetMapping("/otherpreviewfeesdetail")
	public String getOtherPreviewFeesDetail() {
		return "otherpreviewfeesdetail";
	}
	
	@GetMapping("/otherfeesreport")
	public String getOtherfeesreport() {
		return "otherfeesreport";
	}
	
	@GetMapping("/otherfeesCollectionDetails")
	public String getOtherFeesCollectionDetails() {
		return "otherfeesCollectionDetails";
	}
	
	@GetMapping("/generateidsemployee")
	public String getGenerateidsemployee() {
		return "generateidsemployee";
	}
	
	@GetMapping("/printpreviewemployee")
	public String getPrintpreviewemployee() {
		return "printpreviewemployee";
	}
	
	@GetMapping("/student_details_other_feesstructure")
	public String getStudentDetailsOtherFeesstructure() {
		return "student_details_other_feesstructure";
	}
	
	@GetMapping("/feessummary")
	public String getFeesSummary() {
		return "feessummary";
	}
	
	@GetMapping("/dashboardloader")
	public String getDashboardLoader() {
		return "dashboardloader";
	}
	
	@GetMapping("/student_details_other_feesstructure_admin")
	public String getStudentDetailsOtherFeesstructureAdmin() {
		return "student_details_other_feesstructure_admin";
	}
	
	@GetMapping("/reports_teacher")
	public String getReportsTeacher() {
		return "reports_teacher";
	}
	
	@GetMapping("/index_feescollector")
	public String getIndexFeescollector() {
		return "index_feescollector";
	}
	
	@GetMapping("/header_feescollector")
	public String getHeaderFeescollector() {
		return "header_feescollector";
	}
	
	@GetMapping("/left_feescollector")
	public String getLeftFeescollector() {
		return "left_feescollector";
	}
	
	@GetMapping("/reports_feescollector")
	public String getReportsfeescollector() {
		return "reports_feescollector";
	}
	
	@GetMapping("/index_parents")
	public String getIndexParents() {
		return "index_parents";
	}
	@GetMapping("/header_parent")
	public String getHeaderParents() {
		return "header_parent";
	}
	@GetMapping("/left_parent")
	public String getLeftParents() {
		return "left_parent";
	}
	@GetMapping("/Views_student_detail")
	public String getViewsstudentdetail() {
		return "Views_student_detail";
	}
	@GetMapping("/diary")
	public String getDiary() {
		return "diary";
	}
	@GetMapping("/viewDiary")
	public String getViewDiary() {
		return "viewDiary";
	}
	@GetMapping("/welcomeparent")
	public String getWelcomeparent() {
		return "welcomeparent";
	}
	@GetMapping("/studentdiary")
	public String getStudentDiary() {
		return "studentdiary";
	}
	@GetMapping("/studentviewdiary")
	public String getStudentViewDiary() {
		return "studentviewdiary";
	}
	@GetMapping("/studentviewdiarymessage")
	public String getStudentViewDiaryMessage() {
		return "studentviewdiarymessage";
	}
	@GetMapping("/viewdiarymessagestudent")
	public String getViewDiaryMessageStudent() {
		return "viewdiarymessagestudent";
	}
	@GetMapping("/viewdiarystudent")
	public String getViewDiaryStudent() {
		return "viewdiarystudent";
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
	
	@GetMapping("/vouchersearch")
	public String getVoucherSearch() {
		return "vouchersearch";
	}
	
	@GetMapping("/vouchercancelsuccess")
	public String getVoucherCancelSuccess() {
		return "vouchercancelsuccess";
	}
	
	@GetMapping("/printfeesreport")
	public String getPrintfeesreport() {
		return "printfeesreport";
	}
	
	@GetMapping("/feescollectiondetailscategory")
	public String getFeescollectiondetailscategory() {
		return "feescollectiondetailscategory";
	}
	
	@GetMapping("/feesreportbusfees")
	public String getFeesReportBusFees() {
		return "feesreportbusfees";
	}
}

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
	
	@GetMapping("/index_accountant")
	public String getIndexAccountant() {
		return "index_accountant";
	}
	
	@GetMapping("/header_accountant")
	public String getHeaderAccountant() {
		return "header_accountant";
	}
	
	@GetMapping("/left_accountant")
	public String getLeftAccountant() {
		return "left_accountant";
	}
	
	@GetMapping("/leftsettings_accountant")
	public String getLeftSettingsAccountant() {
		return "leftsettings_accountant";
	}
	
	@GetMapping("/reports_accountant")
	public String getReportsAccountant() {
		return "reports_accountant";
	}
	
	@GetMapping("/index_clerk")
	public String getIndexClerk() {
		return "index_clerk";
	}
	
	@GetMapping("/header_clerk")
	public String getHeaderClerk() {
		return "header_clerk";
	}
	
	@GetMapping("/left_clerk")
	public String getLeftClerk() {
		return "left_clerk";
	}
	
	@GetMapping("/leftsettings_clerk")
	public String getLeftSettingsClerk() {
		return "leftsettings_clerk";
	}
	
	@GetMapping("/reports_clerk")
	public String getReportsClerk() {
		return "reports_clerk";
	}
	
	@GetMapping("/index_principal")
	public String getIndexPrincipal() {
		return "index_principal";
	}
	
	@GetMapping("/header_principal")
	public String getHeaderPrincipal() {
		return "header_principal";
	}
	
	@GetMapping("/left_principal")
	public String getLeftPrincipal() {
		return "left_principal";
	}
	
	@GetMapping("/leftsettings_principal")
	public String getLeftSettingsprincipal() {
		return "leftsettings_principal";
	}
	
	@GetMapping("/reports_principal")
	public String getReportsPrincipal() {
		return "reports_principal";
	}
	
	@GetMapping("/index_teacher")
	public String getIndexTeacher() {
		return "index_teacher";
	}
	
	@GetMapping("/left_teacher")
	public String getLeftTeacher() {
		return "left_teacher";
	}
	
	@GetMapping("/header_teacher")
	public String getHeaderTeacher() {
		return "header_teacher";
	}
	
	@GetMapping("/reports_teacher")
	public String getReportsTeacher() {
		return "reports_teacher";
	}
	
	@GetMapping("/feescollectiondetailscategory")
	public String getFeescollectionDetailsCategory() {
		return "feescollectiondetailscategory";
	}

	@GetMapping("/vouchersearch")
	public String voucherSearch() {
		return "vouchersearch";
	}
	
	@GetMapping("/vouchercancelsuccess")
	public String voucherCancelSuccess() {
		return "vouchercancelsuccess";
	}
	
	@GetMapping("/otherfeescancelledreceipts")
	public String otherFeesCancelledReceipts() {
		return "otherfeescancelledreceipts";
	}
	
	@GetMapping("/feesreportdue")
	public String getFeesReportDue() {
		return "feesreportdue";
	}
	
	@GetMapping("/smsdeliveryreport")
	public String getsmsDeliveryReport() {
		return "smsdeliveryreport";
	}
	
	@GetMapping("/studentsregistrationreport")
	public String getstudentsRegistrationReport() {
		return "studentsregistrationreport";
	}
	
	@GetMapping("/defaulterreportloader")
	public String getDefaulterReportLoader() {
		return "defaulterreportloader";
	}
}

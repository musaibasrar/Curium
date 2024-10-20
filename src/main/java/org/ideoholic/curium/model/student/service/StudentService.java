package org.ideoholic.curium.model.student.service;

import org.apache.commons.codec.binary.Base64;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.academicyear.dao.YearDAO;
import org.ideoholic.curium.model.academicyear.dto.Currentacademicyear;
import org.ideoholic.curium.model.account.dao.AccountDAO;
import org.ideoholic.curium.model.account.dto.VoucherEntrytransactions;
import org.ideoholic.curium.model.attendance.dto.StudentAttendanceDetailsResponseDto;
import org.ideoholic.curium.model.branch.dto.Branch;
import org.ideoholic.curium.model.degreedetails.dto.Degreedetails;
import org.ideoholic.curium.model.documents.dto.StudentDetailsDto;
import org.ideoholic.curium.model.feescategory.dto.Feescategory;
import org.ideoholic.curium.model.feescategory.dto.StudentListResponseDto;
import org.ideoholic.curium.model.feescollection.dao.feesCollectionDAO;
import org.ideoholic.curium.model.feescollection.dto.FeesDetailsResponseDto;
import org.ideoholic.curium.model.feescollection.dto.Otherreceiptinfo;
import org.ideoholic.curium.model.feescollection.dto.Receiptinfo;
import org.ideoholic.curium.model.parents.dao.parentsDetailsDAO;
import org.ideoholic.curium.model.parents.dto.ParentListResponseDto;
import org.ideoholic.curium.model.parents.dto.Parents;
import org.ideoholic.curium.model.pudetails.dto.Pudetails;
import org.ideoholic.curium.model.stampfees.dao.StampFeesDAO;
import org.ideoholic.curium.model.stampfees.dto.Academicfeesstructure;
import org.ideoholic.curium.model.std.action.StandardActionAdapter;
import org.ideoholic.curium.model.student.dao.studentDetailsDAO;
import org.ideoholic.curium.model.student.dto.*;
import org.ideoholic.curium.model.user.dao.UserDAO;
import org.ideoholic.curium.model.user.dto.Login;
import org.ideoholic.curium.util.DataUtil;
import org.ideoholic.curium.util.DateUtil;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.math.BigDecimal;
import java.util.*;

public class StudentService {

	private StandardActionAdapter standardActionAdapter;

	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession httpSession;
	private String BRANCHID = "branchid";
	private String USERID = "userloginid";
	private String CURRENTACADEMICYEAR = "currentAcademicYear";
	private StringBuilder optional = new StringBuilder();
	private StringBuilder compulsory = new StringBuilder();

	/**
	 * Size of a byte buffer to read/write file
	 */
	private static final int BUFFER_SIZE = 4096;

	public StudentService(HttpServletRequest request, HttpServletResponse response, StandardActionAdapter standardActionAdapter) {
		this.request = request;
		this.response = response;
		this.httpSession = request.getSession();
		this.standardActionAdapter=standardActionAdapter;
	}

	public ResultResponse addStudent(CreateStudentDto createStudentDto, MultipartFile[] listOfFiles, String branchCode, String branchId, String userId, String strCurrentAcademicYear) {
		ResultResponse result = ResultResponse.builder().build();

		Student student = StudentMapper.INSTANCE.mapStudent(createStudentDto);
		Parents parents = StudentMapper.INSTANCE.mapParent(createStudentDto);
		Pudetails puDetails = StudentMapper.INSTANCE.mapPudetails(createStudentDto);
		Degreedetails degreeDetails = StudentMapper.INSTANCE.mapDegreedetails(createStudentDto);

		try {
			// Process form file field (input type="file")
			if (listOfFiles != null && listOfFiles.length != 0) {
				for (MultipartFile fileItem : listOfFiles) {
					String fileName = (DataUtil.emptyString(fileItem.getOriginalFilename()));
					String fileValue = (DataUtil.emptyString(fileItem.getName()));
					if (!fileName.equalsIgnoreCase("")) {
						byte[] bytesEncoded = Base64.encodeBase64(fileItem.getBytes());
						String saveFile = new String(bytesEncoded);
						student.setStudentpic(saveFile);
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		student.setArchive(0);
		student.setPassedout(0);
		student.setDroppedout(0);
		student.setLeftout(0);
		student.setStudentexternalid(branchCode);
		student.setBranchid(Integer.parseInt(branchId));
		student.setUserid(Integer.parseInt(userId));
		puDetails.setOptionalsubjects(optional.toString());
		puDetails.setCompulsorysubjects(compulsory.toString());
		student.setPudetails(puDetails);
		student.setDegreedetails(degreeDetails);
		parents.setStudent(student);
		parents.setBranchid(Integer.parseInt(branchId));
		parents.setUserid(Integer.parseInt(userId));
		parents = new parentsDetailsDAO().create(parents);

		if (parents != null) {
			String[] yearofAdmission = parents.getStudent().getYearofadmission().split("/");
			String[] currentAcademicYear = strCurrentAcademicYear.split("/");
			String setYear = null;
			int yoa = Integer.parseInt(yearofAdmission[0]);
			int ca = Integer.parseInt(currentAcademicYear[0]);

			if(yoa == ca || yoa < ca) {
				setYear = strCurrentAcademicYear;
			}else if (yoa > ca) {
				setYear = createStudentDto.getYearofadmission();
			}

			stampFees(parents.getStudent().getSid(),setYear, createStudentDto, strCurrentAcademicYear, branchId, userId);
			createParentLogin(parents.getStudent().getStudentexternalid(),parents.getContactnumber(),parents.getBranchid());
			result.setSuccess(true);
			return result;
		}
		result.setSuccess(false);
		return result;
	}

	private void createParentLogin(String studentexternalid, String contactnumber, int branchid) {
		// TODO Auto-generated method stub
		Login login= new Login();
		Branch branch = new Branch();
		login.setUsername(studentexternalid);
		login.setPassword(contactnumber);
		branch.setIdbranch(branchid);
		login.setBranch(branch);
		login.setUsertype("parents");
		new UserDAO().addUser(login);
	}


	private void stampFees(Integer stdIds, String setYear, CreateStudentDto dto, String currentAcademicYear, String branchId, String userId) {

		if(currentAcademicYear!=null){
			String[] feesCategoryIds = dto.getFeesCategory();
			if(feesCategoryIds!=null) {

				String[] studentIds = {stdIds.toString()};
				if(studentIds!=null){
					Academicfeesstructure academicfessstructure = new Academicfeesstructure();
					List<Academicfeesstructure> listOfacademicfessstructure = new ArrayList<Academicfeesstructure>();
					List<Studentfeesstructure> listOfstudentfeesstructure = new ArrayList<Studentfeesstructure>();

					String feesTotalAmount = dto.getFeesTotalAmount();
					Long grandTotal = 0l;

					String[] feesAmount = dto.getFeesAmount();
					String[] concession = dto.getConcession();
					String[] totalInstallments = dto.getTotalInstallments();

					List<Integer> ids = new ArrayList();
					listOfacademicfessstructure.clear();
					for (String id : studentIds) {
						System.out.println("id" + id);
						academicfessstructure = new Academicfeesstructure();
						academicfessstructure.setSid(Integer.valueOf(id));
						academicfessstructure.setAcademicyear(setYear);
						academicfessstructure.setUserid(Integer.parseInt(userId));
						academicfessstructure.setTotalfees(feesTotalAmount);
						grandTotal = grandTotal + Long.parseLong(academicfessstructure.getTotalfees());
						academicfessstructure.setBranchid(Integer.parseInt(branchId));
						academicfessstructure.setUserid(Integer.parseInt(userId));

						listOfacademicfessstructure.add(academicfessstructure);
						// ids.add(Integer.valueOf(id));

					}

					for (String id : studentIds) {

						for(int i=0; i < feesCategoryIds.length ; i++){
							String[] feesCategoryIdsdiv = 	feesCategoryIds[i].split("--");
							
							Studentfeesstructure studentfeesstructure = new Studentfeesstructure();
							Feescategory feescategory = new Feescategory();
							studentfeesstructure.setSid(Integer.valueOf(id));
							feescategory.setIdfeescategory(Integer.parseInt(feesCategoryIdsdiv[0]));
							studentfeesstructure.setFeescategory(feescategory);
							studentfeesstructure.setFeesamount(Long.parseLong(feesAmount[Integer.parseInt(feesCategoryIdsdiv[1])]));
							studentfeesstructure.setFeespaid((long) 0);
							studentfeesstructure.setWaiveoff((long) 0);
							studentfeesstructure.setTotalinstallment(Integer.parseInt(totalInstallments[Integer.parseInt(feesCategoryIdsdiv[1])]));
							studentfeesstructure.setAcademicyear(setYear);
							studentfeesstructure.setBranchid(Integer.parseInt(branchId));
							studentfeesstructure.setUserid(Integer.parseInt(userId));
							studentfeesstructure.setConcession(Integer.parseInt(concession[Integer.parseInt(feesCategoryIdsdiv[1])]));
							listOfstudentfeesstructure.add(studentfeesstructure);
						}



					}

					//Accounts
					//Pass J.V. : credit the Fees as income & debit the cash

					int crFees = getLedgerAccountId("unearnedstudentfeesincome"+Integer.parseInt(branchId));
					int drAccount = getLedgerAccountId("studentfeesreceivable"+Integer.parseInt(branchId));;

					VoucherEntrytransactions transactions = new VoucherEntrytransactions();

					transactions.setDraccountid(drAccount);
					transactions.setCraccountid(crFees);
					transactions.setDramount(new BigDecimal(grandTotal));
					transactions.setCramount(new BigDecimal(grandTotal));
					transactions.setVouchertype(4);
					transactions.setTransactiondate(DateUtil.todaysDate());
					transactions.setEntrydate(DateUtil.todaysDate());
					transactions.setNarration("Towards Fees Stamp");
					transactions.setCancelvoucher("no");
					transactions.setFinancialyear(new AccountDAO().getCurrentFinancialYear(Integer.parseInt(branchId)).getFinancialid());
					transactions.setBranchid(Integer.parseInt(branchId));
					transactions.setUserid(Integer.parseInt(userId));

					String updateDrAccount="update Accountdetailsbalance set currentbalance=currentbalance+"+grandTotal+" where accountdetailsid="+drAccount;

					String updateCrAccount="update Accountdetailsbalance set currentbalance=currentbalance+"+grandTotal+" where accountdetailsid="+crFees;

					// End J.V
					new StampFeesDAO().addStampFees(listOfacademicfessstructure,currentAcademicYear,listOfstudentfeesstructure,transactions,updateDrAccount,updateCrAccount);
					//new studentDetailsDAO().addStudentfeesstructure(listOfstudentfeesstructure,httpSession.getAttribute(CURRENTACADEMICYEAR).toString());

				}
			}
		}
	}

	private int getLedgerAccountId(String itemAccount) {

		int result = 0;

		Properties properties = new Properties();
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("Util.properties");

		try {
			properties.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}

		String ItemLedgerId = properties.getProperty(itemAccount);

		if(ItemLedgerId!=null) {
			result = Integer.parseInt(ItemLedgerId);
		}else {
			String ItemLedger = properties.getProperty(itemAccount.toLowerCase());
			result = Integer.parseInt(ItemLedger.toLowerCase());
		}

		return result;
	}

	public StudentDetailsDto viewAllStudents(String branchId) {

		StudentDetailsDto result = StudentDetailsDto.builder().build();
		String pages = "1";
		try {
			int page = 1;
			int recordsPerPage = 2000;
			if (pages != null) {
				page = Integer.parseInt(pages);
			}

			List<Parents> list = new studentDetailsDAO().readListOfObjectsPaginationALL((page - 1) * recordsPerPage,
				recordsPerPage, Integer.parseInt(branchId));
			int noOfRecords = new studentDetailsDAO().getNoOfRecords(Integer.parseInt(branchId));
			int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
			result.setParentsList(list);
			result.setNoOfPages(noOfPages);
			result.setPage(page);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}

	public StudentDetailsResponseDto viewDetailsOfStudent(String studentId) {
		StudentDetailsResponseDto result = StudentDetailsResponseDto.builder().success(false).build();
		try {
			long id = Long.parseLong(studentId);

			Parents parents = new parentsDetailsDAO().readUniqueObject(id);

			/*httpSession.setAttribute("studentfromservice",student);
			httpSession.setAttribute("parentsfromservice",parents);
			httpSession.setAttribute("idofstudentfromservice",id);*/

			Currentacademicyear currentYear = new YearDAO().showYear();
			result.setCurrentYearFromService(currentYear.getCurrentacademicyear());

			//List<Feesdetails> feesdetails = new feesDetailsDAO().readList(id, currentYear.getCurrentacademicyear());
			//httpSession.setAttribute("feesdetailsfromservice",feesdetails);
			List<Receiptinfo> rinfo = new feesCollectionDAO().getReceiptDetailsPerStudent(id,currentYear.getCurrentacademicyear());;
			result.setReceiptInfo(rinfo);
			List<Studentfeesstructure> feesstructure = new studentDetailsDAO().getStudentFeesStructure(id, currentYear.getCurrentacademicyear());

			long totalSum = 0l;
			long totalFineAmount = 0l;
			long totalMiscAmount = 0l;
			for (Receiptinfo receiptInfoSingle : rinfo) {
				totalSum = totalSum + receiptInfoSingle.getTotalamount()-receiptInfoSingle.getFine()-receiptInfoSingle.getMisc();
				totalFineAmount = receiptInfoSingle.getFine();
				totalMiscAmount = receiptInfoSingle.getMisc();
			}

			long totalFeesAmount = 0l;
			long totalFeesConcession = 0l;
			for (Studentfeesstructure studentfeesstructureSingle : feesstructure) {
				totalFeesAmount = totalFeesAmount+studentfeesstructureSingle.getFeesamount()-studentfeesstructureSingle.getWaiveoff()-studentfeesstructureSingle.getConcession();
				totalFeesConcession = totalFeesConcession+studentfeesstructureSingle.getConcession();
			}

			//String sumOfFees = new feesDetailsDAO().feesSum(id, currentYear.getCurrentacademicyear());
			//String totalFees = new feesDetailsDAO().feesTotal(id, currentYear.getCurrentacademicyear());
			//String dueAmount = new feesDetailsDAO().dueAmount(id, currentYear.getCurrentacademicyear());
			if (parents == null) {
				result.setSuccess(false);
			} else {
				result.setStudent(parents.getStudent());
				String classStudying = parents.getStudent().getClassstudying();
				if (!classStudying.equalsIgnoreCase("")) {
					String[] classParts = classStudying.split("--");
					result.setClassStudying(classStudying);
					result.setClassParts(classParts[0]);
					result.setSecStudying("");
					if(classParts.length>1) {
						result.setSecStudying(classParts[1]);
					}

				} else {
					result.setClassStudying(classStudying);
					result.setSecStudying("");
				}

				String classAdmitted = parents.getStudent().getClassadmittedin();

				if (!classAdmitted.equalsIgnoreCase("")) {

					String[] classAdmittedParts = classAdmitted.split("--");
					result.setClassAdm(classAdmittedParts[0]);
					result.setSecAdm("");
					if(classAdmittedParts.length>1) {
						result.setSecAdm(classAdmittedParts[1]);
					}

				} else {
					result.setClassAdm(classAdmitted);
					result.setSecAdm("");
				}

				//httpSession.setAttribute("feesdetails", feesdetails);

				result.setParents(parents);
				result.setFeesStructure(feesstructure);
				result.setTotalSum(totalSum);
				result.setDueAmount(totalFeesAmount-totalSum);
				result.setTotalFeesAmount(totalFeesAmount);
				result.setAcademicPerYear(currentYear.getCurrentacademicyear());
				result.setCurrentAcademicYear(currentYear.getCurrentacademicyear());
				result.setTotalFeesConcession(totalFeesConcession);
				result.setTotalFineAmount(totalFineAmount);
				result.setTotalMiscAmount(totalMiscAmount);
				result.setSuccess(true);
			}
			standardActionAdapter.viewClasses();
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		result.setSuccess(true);
		return result;
	}
	//code for viewDetailsbySidOfStudent

	public StudentDetailsResponseDto viewDetailsbySidStudent(String studentId) {

		StudentDetailsResponseDto result = StudentDetailsResponseDto.builder().success(false).build();
		try {
			Student student = new studentDetailsDAO().readploginUniqueObject(studentId);
			Parents parents = new parentsDetailsDAO().readploginUniqueObject(studentId);


			Currentacademicyear currentYear = new YearDAO().showYear();
			result.setCurrentYearFromService(currentYear.getCurrentacademicyear());

			List<Receiptinfo> rinfo = new feesCollectionDAO().getReceiptDetailsPerStudent(student.getSid(),currentYear.getCurrentacademicyear());;
			result.setReceiptInfo(rinfo);
			List<Studentfeesstructure> feesstructure = new studentDetailsDAO().getStudentFeesStructure(student.getSid(), currentYear.getCurrentacademicyear());
			long totalSum = 0l;
			for (Receiptinfo receiptInfoSingle : rinfo) {
				totalSum = totalSum + receiptInfoSingle.getTotalamount();
			}

			long totalFeesAmount = 0l;
			long totalFeesConcession = 0l;
			for (Studentfeesstructure studentfeesstructureSingle : feesstructure) {
				totalFeesAmount = totalFeesAmount+studentfeesstructureSingle.getFeesamount()-studentfeesstructureSingle.getWaiveoff()-studentfeesstructureSingle.getConcession();
				totalFeesConcession = totalFeesConcession+studentfeesstructureSingle.getConcession();
			}

			if (student == null) {
				result.setSuccess(false);
			} else {
				result.setStudent(student);
				String classStudying = student.getClassstudying();
				if (!classStudying.equalsIgnoreCase("")) {
					String[] classParts = classStudying.split("--");
					result.setClassStudying(classParts[0]);
					result.setSecStudying("");
					if(classParts.length>1) {
						result.setSecStudying(classParts[1]);
					}

				} else {
					result.setClassStudying(classStudying);
					result.setSecStudying("");
				}

				String classAdmitted = student.getClassadmittedin();

				if (!classAdmitted.equalsIgnoreCase("")) {

					String[] classAdmittedParts = classAdmitted.split("--");
					result.setClassAdm(classAdmittedParts[0]);
					result.setSecAdm("");
					if(classAdmittedParts.length>1) {
							result.setSecAdm(classAdmittedParts[1]);
					}

				} else {
					result.setClassAdm(classAdmitted);
					result.setSecAdm("");
				}
;
				result.setParents(parents);
				result.setFeesStructure(feesstructure);
				result.setTotalSum(totalSum);
				result.setDueAmount(totalFeesAmount-totalSum);
				result.setTotalFeesAmount(totalFeesAmount);
				result.setAcademicPerYear(currentYear.getCurrentacademicyear());
				result.setCurrentAcademicYear(currentYear.getCurrentacademicyear());
				result.setTotalFeesConcession(totalFeesConcession);
				result.setSuccess(true);
			}
			standardActionAdapter.viewClasses();
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		result.setSuccess(true);
		return result;
	}
	//end of viewDetailsbySidOfStudent
//other view detail of students
	public StudentDetailsResponseDto otherviewDetailsOfStudent(String studentId) {
		StudentDetailsResponseDto result = StudentDetailsResponseDto.builder().build();
		try {
			long id = Long.parseLong(studentId);
			Student student = new studentDetailsDAO().readUniqueObject(id);
			Parents parents = new parentsDetailsDAO().readUniqueObject(id);

			/*httpSession.setAttribute("studentfromservice",student);
			httpSession.setAttribute("parentsfromservice",parents);
			httpSession.setAttribute("idofstudentfromservice",id);*/

			Currentacademicyear currentYear = new YearDAO().showYear();
			result.setCurrentAcademicYear(currentYear.getCurrentacademicyear());

			//List<Feesdetails> feesdetails = new feesDetailsDAO().readList(id, currentYear.getCurrentacademicyear());
			//httpSession.setAttribute("feesdetailsfromservice",feesdetails);
			List<Otherreceiptinfo> rinfo = new feesCollectionDAO().getotherReceiptDetailsPerStudent(id,currentYear.getCurrentacademicyear());
			result.setOtherReceiptInfo(rinfo);
			List<Studentotherfeesstructure> feesstructure = new studentDetailsDAO().getStudentOtherFeesStructure(id, currentYear.getCurrentacademicyear());

			long totalSum = 0l;
			for (Otherreceiptinfo receiptInfoSingle : rinfo) {
				totalSum = totalSum + receiptInfoSingle.getTotalamount();
			}

			long totalFeesAmount = 0l;
			long totalFeesConcession = 0l;
			for (Studentotherfeesstructure studentfeesstructureSingle : feesstructure) {
				totalFeesAmount = totalFeesAmount+studentfeesstructureSingle.getFeesamount()-studentfeesstructureSingle.getWaiveoff()-studentfeesstructureSingle.getConcession();
				totalFeesConcession = totalFeesConcession+studentfeesstructureSingle.getConcession();
			}

			//String sumOfFees = new feesDetailsDAO().feesSum(id, currentYear.getCurrentacademicyear());
			//String totalFees = new feesDetailsDAO().feesTotal(id, currentYear.getCurrentacademicyear());
			//String dueAmount = new feesDetailsDAO().dueAmount(id, currentYear.getCurrentacademicyear());
			if (student == null) {
				result.setSuccess(false);
			} else {
				result.setStudent(student);
				String classStudying = student.getClassstudying();
				if (!classStudying.equalsIgnoreCase("")) {
					String[] classParts = classStudying.split("--");
					result.setClassStudying(classParts[0]);
					result.setSecStudying("");
					if(classParts.length>1) {
						result.setSecStudying(classParts[1]);
					}

				} else {
					result.setClassStudying(classStudying);
					result.setSecStudying("");
				}

				String classAdmitted = student.getClassadmittedin();

				result.setSuccess(true);

			}
			standardActionAdapter.viewClasses();
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}

	public Student updateStudent(MultipartFile[] listOfFiles, StudentDto studentDto, String strBranchId, String userId) {
		Student student = StudentMapper.INSTANCE.mapStudent(studentDto);
		Parents parents = StudentMapper.INSTANCE.mapParent(studentDto);
		Pudetails puDetails = StudentMapper.INSTANCE.mapPudetails(studentDto);
		Degreedetails degreeDetails = StudentMapper.INSTANCE.mapDegreedetails(studentDto);

		String id = studentDto.getSid().toString();
		String pid = studentDto.getPid().toString();

		System.out.println("THE ID IS: " + id + "," + pid);

		int studentId = DataUtil.parseInt(id);
		int parentsId = DataUtil.parseInt(pid);

		String addClass = null,addSec =null,addClassE=null,addSecE=null,conClassStudying=null,conClassAdmittedIn=null;

		student.setSid(studentId);
		parents.setPid(parentsId);

		addClass = DataUtil.emptyString(studentDto.getClassSec());
		if (!addClass.equalsIgnoreCase("")) {
			conClassStudying = addClass+"--";

		}

		addSec = DataUtil.emptyString(studentDto.getSecstudying());
		if (!addSec.equalsIgnoreCase("")) {
			conClassStudying = conClassStudying+addSec;
		}

		student.setClassstudying(DataUtil.emptyString(conClassStudying));

		addClassE = DataUtil.emptyString(studentDto.getClassadm());

		if (!addClassE.equalsIgnoreCase("")) {
			conClassAdmittedIn = addClassE+"--";

		}

		addSecE = DataUtil.emptyString(studentDto.getAdmsecE());
		if (!addSecE.equalsIgnoreCase("")) {
			conClassAdmittedIn = conClassAdmittedIn+addSecE;
		}

		student.setClassadmittedin(DataUtil.emptyString(conClassAdmittedIn));

		String studentPicUpdate = studentDto.getStudentPicUpdate();
		String studentDoc1Update = studentDto.getStudentDoc1Update();
		String studentDoc2Update = studentDto.getStudentDoc2Update();
		String studentDoc3Update = studentDto.getStudentDoc3Update();
		String studentDoc4Update = studentDto.getStudentDoc4Update();
		String studentDoc5Update = studentDto.getStudentDoc5Update();
		String studentPicDelete = studentDto.getStudentPicDelete();
		String studentdoc1delete = studentDto.getStudentDoc1Delete();
		String studentdoc2delete = studentDto.getStudentDoc2Delete();
		String studentdoc3delete = studentDto.getStudentDoc3Delete();
		String studentdoc4delete = studentDto.getStudentDoc4Delete();
		String studentdoc5delete = studentDto.getStudentDoc5Delete();

		String dropdowncateg = studentDto.getSpecialcategory();
		String newcateg = studentDto.getNewcategory();

		student.setAdmissionnumber(studentDto.getAdmissionnumber());


		try{
			if(listOfFiles != null && listOfFiles.length != 0){

				MultipartFile fileItem = listOfFiles[0];
				String fileName = (DataUtil.emptyString(fileItem.getOriginalFilename()));
				String fileValue = (DataUtil.emptyString(fileItem.getName()));

				if (!fileName.equalsIgnoreCase("")) {
					// Resize the image
					byte[]   bytesEncoded = Base64.encodeBase64(fileItem.getBytes());
					System.out.println("ecncoded value is " + new String(bytesEncoded ));
					String saveFile = new String(bytesEncoded);

					student.setStudentpic(saveFile);

				} else if (studentPicDelete!=null) {
					student.setStudentpic(null);
				} else{

					student.setStudentpic(studentPicUpdate);
				}

				MultipartFile fileItem1 = listOfFiles[1];
				String studentdoc1 = (DataUtil.emptyString(fileItem1.getOriginalFilename()));

				if (!studentdoc1.equalsIgnoreCase("")) {
					// Resize the image
					byte[]   bytesEncoded = Base64.encodeBase64(fileItem1.getBytes());
					System.out.println("ecncoded value is " + new String(bytesEncoded ));
					String saveFile = new String(bytesEncoded);

					student.setStudentdoc1(saveFile);

				} else if(studentdoc1delete!=null) {
					student.setStudentdoc1(null);
				}else {
					student.setStudentdoc1(studentDoc1Update);
				}


				MultipartFile fileItem2 = listOfFiles[2];
				String studentdoc2 = (DataUtil.emptyString(fileItem2.getOriginalFilename()));
				if (!studentdoc2.equalsIgnoreCase("")) {
					// Resize the image
					byte[]   bytesEncoded = Base64.encodeBase64(fileItem2.getBytes());
					System.out.println("ecncoded value is " + new String(bytesEncoded ));
					String saveFile = new String(bytesEncoded);

					student.setStudentdoc2(saveFile);

				} else if(studentdoc2delete!=null) {
					student.setStudentdoc2(null);
				}else {
					student.setStudentdoc2(studentDoc2Update);
				}


				MultipartFile fileItem3 = listOfFiles[3];
				String studentdoc3 = (DataUtil.emptyString(fileItem3.getOriginalFilename()));
				if (!studentdoc3.equalsIgnoreCase("")) {
					// Resize the image
					byte[]   bytesEncoded = Base64.encodeBase64(fileItem3.getBytes());
					System.out.println("ecncoded value is " + new String(bytesEncoded ));
					String saveFile = new String(bytesEncoded);

					student.setStudentdoc3(saveFile);

				} else if(studentdoc3delete!=null) {
					student.setStudentdoc3(null);
				}else {
					student.setStudentdoc3(studentDoc3Update);
				}

				MultipartFile fileItem4 = listOfFiles[4];
				String studentdoc4 = (DataUtil.emptyString(fileItem4.getOriginalFilename()));
				if (!studentdoc4.equalsIgnoreCase("")) {
					// Resize the image
					byte[]   bytesEncoded = Base64.encodeBase64(fileItem4.getBytes());
					System.out.println("ecncoded value is " + new String(bytesEncoded ));
					String saveFile = new String(bytesEncoded);

					student.setStudentdoc4(saveFile);

				} else if(studentdoc4delete!=null) {
					student.setStudentdoc4(null);
				}else {
					student.setStudentdoc4(studentDoc4Update);
				}

				MultipartFile fileItem5 = listOfFiles[5];
				String studentdoc5 = (DataUtil.emptyString(fileItem5.getOriginalFilename()));
				if (!studentdoc5.equalsIgnoreCase("")) {
					// Resize the image
					byte[]   bytesEncoded = Base64.encodeBase64(fileItem5.getBytes());
					System.out.println("ecncoded value is " + new String(bytesEncoded ));
					String saveFile = new String(bytesEncoded);

					student.setStudentdoc5(saveFile);

				} else if(studentdoc5delete!=null) {
					student.setStudentdoc5(null);
				}else {
					student.setStudentdoc5(studentDoc5Update);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		if(newcateg != null && newcateg.isEmpty()) {
			student.setSpecialcategory(dropdowncateg);
		}else {
			student.setSpecialcategory(newcateg);
		}
		//student.setArchive(0);
		student.setBranchid(Integer.parseInt(strBranchId));
		student.setUserid(studentDto.getUserid());

		if(puDetails.getIdpudetails()!=null) {
			new studentDetailsDAO().updatePuDetails(puDetails);
			student.setPudetails(puDetails);
		}

		if(degreeDetails.getIddegreedetails()!=0) {
			new studentDetailsDAO().updateDegreeDetails(degreeDetails);
			student.setDegreedetails(degreeDetails);
		}
		student = new studentDetailsDAO().update(student);
		if (pid != "") {
			parents.setStudent(student);
			parents.setBranchid(Integer.parseInt(strBranchId));
			parents.setUserid(Integer.parseInt(userId));

			parents = new parentsDetailsDAO().update(parents);
		}
		return student;
	}

	public StudentListResponseDto viewAllStudentsList(String branchId) {

		StudentListResponseDto result = StudentListResponseDto.builder().build();
		if (branchId != null) {

			try {

				List<Student> list = new studentDetailsDAO().readListOfStudents(Integer.parseInt(branchId));
				result.setStudentList(list);
				result.setSuccess(true);
			} catch (Exception e) {
				e.printStackTrace();
				result.setSuccess(false);
			}

		}
		return result;
	}

	public void archiveMultiple(StudentIdsDto dto) {
		String[] studentIds = dto.getStudentIds();

		if (studentIds != null) {
			List<Integer> ids = new ArrayList();
			for (String id : studentIds) {
				ids.add(Integer.valueOf(id));

			}
			new studentDetailsDAO().archiveMultiple(ids);
		}
	}

	public StudentAttendanceDetailsResponseDto viewAllStudentsArchive() {

		StudentAttendanceDetailsResponseDto result = StudentAttendanceDetailsResponseDto.builder().success(false).build();

		try {
			List<Student> list = new studentDetailsDAO().readListOfStudentsArchive();
			result.setStudentList(list);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void deleteMultiple(StudentIdsDto dto) {
		String[] studentIds = dto.getStudentIds();
		if (studentIds != null) {
			List<Integer> ids = new ArrayList();
			List<Integer> iddetails = new ArrayList();
			for (String id : studentIds) {
				String[] iddetailsarray = id.split(":");
				ids.add(Integer.valueOf(iddetailsarray[0]));
				if(iddetailsarray.length>1) {
					iddetails.add(Integer.valueOf(iddetailsarray[1]));
				}
			}
			new studentDetailsDAO().deleteMultiple(ids,iddetails);
		}
	}

	public void restoreMultiple(StudentIdsDto dto) {
		String[] studentIds = dto.getStudentIds();
		if (studentIds != null) {
			List<Integer> ids = new ArrayList();
			for (String id : studentIds) {
				String[] iddetailsarray = id.split(":");
				ids.add(Integer.valueOf(iddetailsarray[0]));
			}
			System.out.println("id length" + studentIds.length);
			new studentDetailsDAO().restoreMultiple(ids);
		}
	}

	public ResultResponse promoteMultiple(PromoteMultipleDto dto, String currentAcademicYear, String branchId) {
		ResultResponse result = ResultResponse.builder().build();

		String[] studentIds = dto.getStudentIds();
		String classStudying = dto.getClassStudying();
        List<Student> studentList = new ArrayList<>();

		for (String id : studentIds) {
			Student student = new Student();
			student.setSid(Integer.valueOf(id));
			student.setClassstudying(dto.getRequestParams().get("classstudying_"+id));
			studentList.add(student);
		}

		if (new studentDetailsDAO().promoteMultiple(studentList, classStudying, currentAcademicYear, Integer.parseInt(branchId))) {
			result.setSuccess(true);
		}
		result.setSuccess(false);
		return result;
	}

	public ParentListResponseDto viewAllStudentsParents(String strPage, String branchId) {
		ParentListResponseDto result = ParentListResponseDto.builder().success(false).build();

		//String pages = "1";
		if(branchId!=null){
			try {
				int page = 1;
				int recordsPerPage = 1000;
				if (!"".equalsIgnoreCase(DataUtil.emptyString(strPage))) {
					page = Integer.parseInt(strPage);
				}

				/*
				 * List<Parents> list = new
				 * studentDetailsDAO().readListOfObjectsPaginationALL((page - 1) *
				 * recordsPerPage, recordsPerPage,
				 * Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
				 */

				List<Object[]> list = new studentDetailsDAO().readListOfObjectsPagination((page - 1) * recordsPerPage,
					recordsPerPage, Integer.parseInt(branchId));

				List<Parents> parentDetails = new ArrayList<Parents>();
				for(Object[] parentdetails: list){
					Parents parent = new Parents();
					Student student = new Student();
					student.setSid((Integer)parentdetails[0]);
					student.setStudentexternalid((String)parentdetails[1]);
					student.setAdmissionnumber((String)parentdetails[2]);
					student.setName((String)parentdetails[3]);
					student.setClassstudying((String)parentdetails[4]);
					parent.setFathersname((String)parentdetails[5]);
					parent.setMothersname((String)parentdetails[6]);
					parent.setStudent(student);
					parentDetails.add(parent);
				}

				int noOfRecords = new studentDetailsDAO().getNoOfRecords(Integer.parseInt(branchId));
				int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
				result.setParentsList(parentDetails);
				result.setNoOfPages(noOfPages);
				result.setPage(page);
				result.setSuccess(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public FeesDetailsResponseDto viewfeesStructurePerYear(StudentIdDto dto) {
		FeesDetailsResponseDto result = FeesDetailsResponseDto.builder().build();

		try {

			long id = Long.parseLong(dto.getStudentId());
			String academicYear = dto.getAcademicYear();

			List<Receiptinfo> rinfo = new feesCollectionDAO().getReceiptDetailsPerStudent(id,academicYear);
			result.setReceiptInfo(rinfo);
			List<Studentfeesstructure> feesstructure = new studentDetailsDAO().getStudentFeesStructure(id, academicYear);

			long totalSum = 0l;
			for (Receiptinfo receiptInfoSingle : rinfo) {
				totalSum = totalSum + receiptInfoSingle.getTotalamount();
			}

			long totalFeesAmount = 0l;
			long totalFeesConcession = 0l;
			for (Studentfeesstructure studentfeesstructureSingle : feesstructure) {
				totalFeesAmount = totalFeesAmount+studentfeesstructureSingle.getFeesamount()-studentfeesstructureSingle.getWaiveoff()-studentfeesstructureSingle.getConcession();
				totalFeesConcession = totalFeesConcession+studentfeesstructureSingle.getConcession();
			}
			result.setFeesStructure(feesstructure);
			result.setTotalSum(totalSum);
			result.setDueAmount(totalFeesAmount-totalSum);
			result.setTotalFeesAmount(totalFeesAmount);
			result.setAcademicPerYear(academicYear);
			result.setTotalFeesConcession(totalFeesConcession);
			result.setSuccess(true);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


	public ResultResponse exportDataForStudents(StudentIdsDto dto, String branchId) {

		String[] studentIds = dto.getStudentIds();
		ResultResponse result = ResultResponse.builder().build();

		List<Parents> listOfStudentRecords = new ArrayList<Parents>();

		if (studentIds != null) {
			for (String id : studentIds) {
				if (id != null || id != "") {
					String queryMain = "From Parents as parents where parents.Student.branchid="+Integer.parseInt(branchId)+" AND";
					String querySub = " parents.Student.id = "+id+" order by parents.Student.admissionnumber ASC";
					queryMain = queryMain + querySub;

					List<Parents> searchStudentList = new studentDetailsDAO().getStudentsList(queryMain);
					result.setResultList(searchStudentList);

					Parents searchStudentRecords = new studentDetailsDAO().getStudentRecords(queryMain);
					listOfStudentRecords.add(searchStudentRecords);
				}

			}
			try {
				if (exportDataToExcel(listOfStudentRecords)) {
					result.setSuccess(true);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;

	}

	public boolean exportDataToExcel(List<Parents> listOfStudentRecords)
		throws Exception {

		boolean writeSucees = false;

		try {

			// Creating an excel file
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("ListOfStudents");
			Map<String, Object[]> data = new HashMap<String, Object[]>();
			Map<String, Object[]> headerData = new HashMap<String, Object[]>();
			headerData.put("Header",
				new Object[] { "Admission No.","STS","UID", "Student Name", "Gender", "Date Of Birth", "Age", "Studying In Class",
					"Admitted In Class", "Admission Date","Admission Year", "Promoted Year", "Blood Group", "Religion", "Student Aadhar Card",
					"Caste", "Fathers Name", "Mothers Name","Contact No.", "Archive", "Graduated", "Left Out", "Dropped Out"});
			int i = 1;
			for (Parents studentDetails : listOfStudentRecords) {
				data.put(Integer.toString(i),
					new Object[] { DataUtil.emptyString(studentDetails.getStudent().getAdmissionnumber()),
						DataUtil.emptyString(studentDetails.getStudent().getSts()),
						DataUtil.emptyString(studentDetails.getStudent().getStudentexternalid()),
						DataUtil.emptyString(studentDetails.getStudent().getName()),  DataUtil.emptyString(studentDetails.getStudent().getGender()),
						DateUtil.dateParserddMMYYYY(studentDetails.getStudent().getDateofbirth()),
						DataUtil.emptyString(Integer.toString(studentDetails.getStudent().getAge())),
						DataUtil.emptyString(studentDetails.getStudent().getClassstudying().replace("--", " ")),
						DataUtil.emptyString(studentDetails.getStudent().getClassadmittedin().replace("--", " ")),
						DateUtil.dateParserddMMYYYY(studentDetails.getStudent().getAdmissiondate()),
						DataUtil.emptyString(studentDetails.getStudent().getYearofadmission()),DataUtil.emptyString(studentDetails.getStudent().getPromotedyear()),
						DataUtil.emptyString(studentDetails.getStudent().getBloodgroup()),  DataUtil.emptyString(studentDetails.getStudent().getReligion()),
						DataUtil.emptyString(studentDetails.getStudent().getDisabilitychild()),
						DataUtil.emptyString(studentDetails.getStudent().getCaste()),  DataUtil.emptyString(studentDetails.getFathersname()),
						DataUtil.emptyString(studentDetails.getMothersname()),DataUtil.emptyString(studentDetails.getContactnumber()),


						studentDetails.getStudent().getArchive()==1 ? "Yes" : "No" ,
						studentDetails.getStudent().getPassedout()==1 ? "Yes" : "No", studentDetails.getStudent().getLeftout()==1 ? "Yes" : "No",
						studentDetails.getStudent().getDroppedout()==1 ? "Yes" : "No"});
				i++;
			}
			Row headerRow = sheet.createRow(0);
			Object[] objArrHeader = headerData.get("Header");
			int cellnum1 = 0;
			for (Object obj : objArrHeader) {
				Cell cell = headerRow.createCell(cellnum1++);
				if (obj instanceof String)
					cell.setCellValue((String) obj);
			}
			Set<String> keyset = data.keySet();
			int rownum = 1;
			for (String key : keyset) {
				Row row = sheet.createRow(rownum++);
				Object[] objArr = data.get(key);
				int cellnum = 0;
				for (Object obj : objArr) {
					Cell cell = row.createCell(cellnum++);
					if (obj instanceof Date)
						cell.setCellValue((Date) obj);
					else if (obj instanceof Boolean)
						cell.setCellValue((Boolean) obj);
					else if (obj instanceof String)
						cell.setCellValue((String) obj);
					else if (obj instanceof Double)
						cell.setCellValue((Double) obj);
				}
			}


			//ClassLoader classLoader = getClass().getClassLoader();
			//Local
			//FileOutputStream out = new FileOutputStream("D:/schoolfiles/test.xlsx");
			//FileOutputStream out = new FileOutputStream(new File("/usr/local/tomcat/webapps/www.searchmysearch.com/musarpbiabha/studentsdetails.xlsx"));
			FileOutputStream out = new FileOutputStream(new File(System.getProperty("java.io.tmpdir")+"/studentsdetails.xlsx"));
			workbook.write(out);
			out.close();
			writeSucees = true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return writeSucees;
		// getFile(name, path);
	}

	public BonafideGenerationResponseDto generateBonafide(StudentIdsDto dto) {
		BonafideGenerationResponseDto result = BonafideGenerationResponseDto.builder().build();

		String[] studentIds = dto.getStudentIds();
		String bonafidePage = null;

		if(studentIds!=null){
			String getStudentInfo  = "from Parents as parents where parents.Student.sid="+studentIds[0];
			Parents parents = new studentDetailsDAO().getStudentRecords(getStudentInfo);
			result.setParents(parents);
			result.setSuccess(true);
			result.setMessage("bonafidecertificateprint");
		}

		return result;
	}

	public ResultResponse downlaodFile() {
		ResultResponse result = ResultResponse.builder().build();
		try {

			File downloadFile = new File(System.getProperty("java.io.tmpdir")+"/studentsdetails.xlsx");
			FileInputStream inStream = new FileInputStream(downloadFile);

			// get MIME type of the file
			String mimeType = "application/vnd.ms-excel";

			// set content attributes for the response
			response.setContentType(mimeType);
			// response.setContentLength((int) bis.length());

			// set headers for the response
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"",
				"studentdetails.xlsx");
			response.setHeader(headerKey, headerValue);

			// get output stream of the response
			OutputStream outStream = response.getOutputStream();

			byte[] buffer = new byte[BUFFER_SIZE];
			int bytesRead = -1;

			// write bytes read from the input stream into the output stream
			while ((bytesRead = inStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, bytesRead);
			}

			inStream.close();
			outStream.close();
			result.setSuccess(true);
		} catch (Exception e) {
			System.out.println("" + e);
		}
		return result;
	}

	public ResultResponse addNew(String branchId) {
		ResultResponse result = ResultResponse.builder().build();

		if(branchId!=null){
			result.setSuccess(true);
			result.setMessage("addStudent");
			return result;
           /* if("1".equalsIgnoreCase(branchId) || "2".equalsIgnoreCase(branchId) || "3".equalsIgnoreCase(branchId)) {
                return "addStudent.jsp";
            }else if("4".equalsIgnoreCase(branchId)) {
                return "addStudentPU.jsp";
            }else if("5".equalsIgnoreCase(branchId)) {
                return "addStudentDC.jsp";
            }*/
		}
		result.setMessage("sessiontimeout");
		return result;
	}

	public StudentsSuperAdminResponseDto viewAllStudentsSuperAdmin() {
		StudentsSuperAdminResponseDto result = StudentsSuperAdminResponseDto.builder().build();

		String pages = "1";

		try {
			int page = 1;
			int recordsPerPage = 5000;
			if (pages != null) {
				page = Integer.parseInt(pages);
			}
			List<Parents> list = new studentDetailsDAO().readListStudentsSuperAdmin((page - 1) * recordsPerPage,
				recordsPerPage);
			int noOfRecords = new studentDetailsDAO().getNoOfRecords();
			int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
			result.setStudentList(list);
			result.setNoOfPages(noOfPages);
			result.setPage(page);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public StudentListResponseDto viewStudentsParentsPerBranch(String branchId) {

		StudentListResponseDto result = StudentListResponseDto.builder().build();
		//String pages = "1";
		if(branchId!=null){
			try {

				List<Object[]> list = new studentDetailsDAO().readStudentsParentsPerBranch(Integer.parseInt(branchId));

				List<Parents> parentDetails = new ArrayList<Parents>();
				for(Object[] parentdetails: list){
					Parents parent = new Parents();
					Student student = new Student();
					student.setSid((Integer)parentdetails[0]);
					student.setStudentexternalid((String)parentdetails[1]);
					student.setAdmissionnumber((String)parentdetails[2]);
					student.setName((String)parentdetails[3]);
					student.setClassstudying((String)parentdetails[4]);
					parent.setFathersname((String)parentdetails[5]);
					parent.setMothersname((String)parentdetails[6]);
					parent.setStudent(student);
					parentDetails.add(parent);
				}

				result.setParentDetails(parentDetails);
				result.setSuccess(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public StudentDetailsResponseDto viewOtherFeesDetailsOfStudent(String studentId) {
		StudentDetailsResponseDto result = StudentDetailsResponseDto.builder().success(false).build();
		try {
			long id = Long.parseLong(studentId);
			Student student = new studentDetailsDAO().readUniqueObject(id);
			Parents parents = new parentsDetailsDAO().readUniqueObject(id);

			/*httpSession.setAttribute("studentfromservice",student);
			httpSession.setAttribute("parentsfromservice",parents);
			httpSession.setAttribute("idofstudentfromservice",id);*/

			Currentacademicyear currentYear = new YearDAO().showYear();
			result.setCurrentYearFromService(currentYear.getCurrentacademicyear());

			//List<Feesdetails> feesdetails = new feesDetailsDAO().readList(id, currentYear.getCurrentacademicyear());
			//httpSession.setAttribute("feesdetailsfromservice",feesdetails);
			List<Otherreceiptinfo> rinfo = new feesCollectionDAO().getotherReceiptDetailsPerStudent(id,currentYear.getCurrentacademicyear());
			result.setOtherReceiptInfo(rinfo);
			List<Studentotherfeesstructure> feesstructure = new studentDetailsDAO().getStudentOtherFeesStructure(id, currentYear.getCurrentacademicyear());

			long totalSum = 0l;
			for (Otherreceiptinfo receiptInfoSingle : rinfo) {
				totalSum = totalSum + receiptInfoSingle.getTotalamount();
			}

			long totalFeesAmount = 0l;
			long totalFeesConcession = 0l;
			for (Studentotherfeesstructure studentfeesstructureSingle : feesstructure) {
				totalFeesAmount = totalFeesAmount+studentfeesstructureSingle.getFeesamount()-studentfeesstructureSingle.getWaiveoff()-studentfeesstructureSingle.getConcession();
				totalFeesConcession = totalFeesConcession+studentfeesstructureSingle.getConcession();
			}

			//String sumOfFees = new feesDetailsDAO().feesSum(id, currentYear.getCurrentacademicyear());
			//String totalFees = new feesDetailsDAO().feesTotal(id, currentYear.getCurrentacademicyear());
			//String dueAmount = new feesDetailsDAO().dueAmount(id, currentYear.getCurrentacademicyear());
			if (student == null) {
				result.setSuccess(false);
			} else {
				result.setStudent(student);
				String classStudying = student.getClassstudying();
				if (!classStudying.equalsIgnoreCase("")) {
					String[] classParts = classStudying.split("--");
					result.setClassStudying(classParts[0]);
					result.setSecStudying("");
					if(classParts.length>1) {
						result.setSecStudying(classParts[1]);
					}

				} else {
					result.setClassStudying(classStudying);
					result.setSecStudying("");
				}

				String classAdmitted = student.getClassadmittedin();

				if (!classAdmitted.equalsIgnoreCase("")) {

					String[] classAdmittedParts = classAdmitted.split("--");
					result.setClassAdm(classAdmittedParts[0]);
					result.setSecAdm("");
					if(classAdmittedParts.length>1) {
						result.setSecAdm(classAdmittedParts[1]);
					}

				} else {
					result.setClassAdm(classAdmitted);
					result.setSecAdm("");
				}

				result.setParents(parents);
				//httpSession.setAttribute("feesdetails", feesdetails);
				result.setStudentOtherFeesStructure(feesstructure);
				result.setTotalSum(totalSum);
				result.setDueAmount(totalFeesAmount-totalSum);
				result.setTotalFeesAmount(totalFeesAmount);
				result.setAcademicPerYear(currentYear.getCurrentacademicyear());
				result.setCurrentAcademicYear(currentYear.getCurrentacademicyear());
				result.setTotalFeesConcession(totalFeesConcession);
				result.setSuccess(true);
			}
			standardActionAdapter.viewClasses();
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		result.setSuccess(true);
		return result;
	}
	//end of otherview of student

}
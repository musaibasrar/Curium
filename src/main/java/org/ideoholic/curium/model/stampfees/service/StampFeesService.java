package org.ideoholic.curium.model.stampfees.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.ideoholic.curium.model.account.dao.AccountDAO;
import org.ideoholic.curium.model.account.dto.VoucherEntrytransactions;
import org.ideoholic.curium.model.documents.dto.SearchStudentDto;
import org.ideoholic.curium.model.documents.dto.SearchStudentResponseDto;
import org.ideoholic.curium.model.feescategory.dao.FeesCategoryDAO;
import org.ideoholic.curium.model.feescategory.dto.Feescategory;
import org.ideoholic.curium.model.feescategory.dto.FeescategoryResponseDto;
import org.ideoholic.curium.model.feescategory.dto.OtherFeecategory;
import org.ideoholic.curium.model.feescategory.dto.OtherFeesCategoryResponseDto;
import org.ideoholic.curium.model.parents.dto.Parents;
import org.ideoholic.curium.model.stampfees.dao.StampFeesDAO;
import org.ideoholic.curium.model.stampfees.dto.Academicfeesstructure;
import org.ideoholic.curium.model.stampfees.dto.Academicotherfeesstructure;
import org.ideoholic.curium.model.stampfees.dto.StampFeesDto;
import org.ideoholic.curium.model.student.dao.StudentDetailsDAO;
import org.ideoholic.curium.model.student.dto.StudentIdsDto;
import org.ideoholic.curium.model.student.dto.Studentfeesstructure;
import org.ideoholic.curium.model.student.dto.Studentotherfeesstructure;
import org.ideoholic.curium.util.DataUtil;
import org.ideoholic.curium.util.DateUtil;
import org.ideoholic.curium.util.PropertiesUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class StampFeesService {
	
    private final AccountDAO accountDao;
	
	private final StampFeesDAO stampFeesDao;

	private final StudentDetailsDAO studentDetailsDao;
	
	private final FeesCategoryDAO feesCategoryDao;

	private final PropertiesUtil propertiesUtil;

	public SearchStudentResponseDto advanceSearch(SearchStudentDto searchStudentDto, String branchid) {
		SearchStudentResponseDto searchStudentResponseDto = new SearchStudentResponseDto();
		List<Parents> searchStudentList = new ArrayList<>();
		
		if(StringUtils.hasLength(branchid)){
		
		String queryMain = "From Parents as parents where";
		String studentname = DataUtil.emptyString(searchStudentDto.getNameSearch());
		String addClass = searchStudentDto.getClassSearch();
		String addSec = searchStudentDto.getSecSearch();
		String conClassStudying = "";

		if (StringUtils.hasLength(addClass)) {
			conClassStudying = addClass+"--"+"%";
		}
		if (StringUtils.hasLength(addSec)) {
			conClassStudying = addClass;
			conClassStudying = conClassStudying+"--"+addSec+"%";
		}

		String classStudying = DataUtil.emptyString(conClassStudying);
		String querySub = "";

		if (StringUtils.hasLength(studentname)) {
			querySub = " parents.student.name like '%" + studentname + "%' AND parents.student.branchid="+Integer.parseInt(branchid);
		}

		if (StringUtils.hasLength(classStudying) && StringUtils.hasLength(querySub)) {
			querySub = querySub + " AND parents.student.classstudying like '"
					+ classStudying + "' AND parents.student.archive=0 and parents.student.passedout=0 AND parents.student.droppedout=0 and parents.student.leftout=0";
		} else if (StringUtils.hasLength(classStudying)) {
			querySub = querySub + " parents.student.classstudying like '"
					+ classStudying + "' AND parents.student.archive=0 and parents.student.passedout=0 AND parents.student.droppedout=0 and parents.student.leftout=0 AND parents.student.branchid="+Integer.parseInt(branchid)+" order by parents.student.admissionnumber ASC";
		}

		if(StringUtils.hasLength(querySub)) {
			queryMain = queryMain + querySub;
			searchStudentList = studentDetailsDao.getStudentsList(queryMain);
		}
		
	}
		searchStudentResponseDto.setSearchStudentList(searchStudentList);
		return searchStudentResponseDto;
	}
	
	public SearchStudentResponseDto multiClassSearch(SearchStudentDto searchStudentDto,String branchid) {

		SearchStudentResponseDto searchStudentResponseDto = new SearchStudentResponseDto();
		List<Parents> searchStudentList = new ArrayList<>();
		
		if(StringUtils.hasLength(branchid)){
		
		String academicYear = searchStudentDto.getAcademicyear();
			
		String queryMain = "From Parents as parents where (parents.student.promotedyear='"+academicYear+"' or parents.student.yearofadmission='"+academicYear+"') AND parents.student.archive=0 and parents.student.passedout=0 AND parents.student.droppedout=0 and parents.student.leftout=0 and ";
		String studentname = DataUtil.emptyString(searchStudentDto.getNameSearch());
		String[] addClass = searchStudentDto.getClassesSearch();
		//String addSec = request.getParameter("secsearch");
		StringBuffer conClassStudying = new StringBuffer();

			int i = 0;
			for (String classOne : addClass) {
				
				if(i>0) {
					conClassStudying.append("' OR parents.student.classstudying LIKE '"+classOne+"--"+"%");
				}else {
					conClassStudying.append(classOne+"--"+"%");
				}
				
				i++;
			}
			
		
		/*if (StringUtils.hasLength(addSec)) {
			//conClassStudying = addClass;
			conClassStudying = conClassStudying+"--"+addSec+"%";
		}*/

		String classStudying = DataUtil.emptyString(conClassStudying.toString());
		String querySub = "";

		if (StringUtils.hasLength(studentname)) {
			querySub = " parents.student.name like '%" + studentname + "%' and parents.student.branchid="+Integer.parseInt(branchid);
		}

		if (StringUtils.hasLength(classStudying) && StringUtils.hasLength(querySub)) {
			querySub = querySub + " AND (parents.student.classstudying like '"
					+ classStudying + "') AND parents.student.branchid="+Integer.parseInt(branchid)+" order by parents.student.admissionnumber ASC";
		} else if (StringUtils.hasLength(classStudying)) {
			querySub = querySub + " (parents.student.classstudying like '"
					+ classStudying + "') AND parents.student.branchid="+Integer.parseInt(branchid)+" order by parents.student.admissionnumber ASC";
		}

		if(StringUtils.hasLength(querySub)) {
			queryMain = queryMain + querySub;
			searchStudentList = studentDetailsDao.getStudentsList(queryMain);
		}
		
	}
		searchStudentResponseDto.setSearchStudentList(searchStudentList);

	    return searchStudentResponseDto;
	}
	public SearchStudentResponseDto advanceSearchByParents(String fatherName,String motherName,String branchid) {
		SearchStudentResponseDto searchStudentResponseDto = new SearchStudentResponseDto();
		
		if(StringUtils.hasLength(branchid)){
			String queryMain = "From Parents as parents where parents.branchid="+Integer.parseInt(branchid);
			String fathersname = DataUtil.emptyString(fatherName);
			String mothersname = DataUtil.emptyString(motherName);
			String querySub = "";

			if (StringUtils.hasLength(fathersname)) {
				querySub = "AND parents.fathersname like '%" + fathersname + "%'";
			}

			if (StringUtils.hasLength(motherName) && StringUtils.hasLength(querySub)) {
				querySub = querySub + " AND parents.mothersname like '%"
						+ mothersname + "%'";
			} else if (StringUtils.hasLength(motherName)) {
				querySub = querySub + "AND parents.mothersname like '%" + mothersname
						+ "%'";
			}

			queryMain = queryMain + querySub;
			/*
			 * queryMain =
			 * "FROM Parents as parents where  parents.student.dateofbirth = '2006-04-06'"
			 * ;
			 */
			log.debug("SEARCH QUERY ***** {}", queryMain);
			List<Parents> searchParentsList = studentDetailsDao.getStudentsList(queryMain);
			searchStudentResponseDto.setSearchStudentList(searchParentsList);
		}
		
        return searchStudentResponseDto;
	}

	public void addFeesStamp(StampFeesDto stampFeesDto,String currentAcademicYear,String branchid,String userid ) {
		
		if (currentAcademicYear != null) {
			String[] studentIds = stampFeesDto.getStudentIds();
			if (studentIds != null) {
				Academicfeesstructure academicfessstructure = new Academicfeesstructure();
				List<Academicfeesstructure> listOfacademicfessstructure = new ArrayList<>();
				List<Studentfeesstructure> listOfstudentfeesstructure = new ArrayList<>();

				String feesTotalAmount = stampFeesDto.getFeesTotalAmount();
				Long grandTotal = 0l;

				String[] feesCategoryIds = stampFeesDto.getFeesCategoryIds();
				String[] feesAmount = stampFeesDto.getFeesAmount();
				String[] concession = stampFeesDto.getConcession();
				String[] totalInstallments = stampFeesDto.getTotalInstallments();
				String[] feesYears = stampFeesDto.getFeesYears();

				listOfacademicfessstructure.clear();
				for (String id : studentIds) {
					Long totalFeesAmount = 0l;
					for (int i = 0; i < feesCategoryIds.length; i++) {
						String[] feesCatAndIndex = feesCategoryIds[i].split("_");
						int feesCatIndex = Integer.parseInt(feesCatAndIndex[1]);
						// check whether the fees category is already stamped
						List<Studentfeesstructure> result = stampFeesDao.getStudentFeesStructure(Integer.parseInt(id), Integer.parseInt(feesCatAndIndex[0]), currentAcademicYear);
						// END

						if (CollectionUtils.isEmpty(result)) {

							Studentfeesstructure studentfeesstructure = new Studentfeesstructure();
							Feescategory feescategory = new Feescategory();
							studentfeesstructure.setStudent(studentDetailsDao.readUniqueObject(DataUtil.parseInt(id)));
							feescategory.setIdfeescategory(Integer.parseInt(feesCatAndIndex[0]));
							studentfeesstructure.setFeescategory(feescategory);
							studentfeesstructure.setFeesamount(Long.parseLong(feesAmount[feesCatIndex]));
							studentfeesstructure.setFeespaid((long) 0);
							studentfeesstructure.setWaiveoff((long) 0);
							studentfeesstructure.setTotalinstallment(Integer.parseInt(totalInstallments[feesCatIndex]));
							studentfeesstructure.setAcademicyear(feesYears[feesCatIndex]);
							studentfeesstructure.setBranchid(Integer.parseInt(branchid));
							studentfeesstructure.setUserid(Integer.parseInt(userid));
							studentfeesstructure.setConcession(Integer.parseInt(concession[feesCatIndex]));
							listOfstudentfeesstructure.add(studentfeesstructure);

							totalFeesAmount = totalFeesAmount + Long.parseLong(feesAmount[feesCatIndex]);
						}

					}

					academicfessstructure = new Academicfeesstructure();
					academicfessstructure.setSid(Integer.valueOf(id));
					academicfessstructure.setAcademicyear(feesYears[0]);
					academicfessstructure.setUserid(Integer.parseInt(userid));
					academicfessstructure.setTotalfees(totalFeesAmount.toString());
					grandTotal = grandTotal + Long.parseLong(academicfessstructure.getTotalfees());
					academicfessstructure.setBranchid(Integer.parseInt(branchid));
					academicfessstructure.setUserid(Integer.parseInt(userid));

					listOfacademicfessstructure.add(academicfessstructure);
					// ids.add(Integer.valueOf(id));
					grandTotal = grandTotal + totalFeesAmount;

				}

				for (String id : studentIds) {

					for (int i = 0; i < feesCategoryIds.length; i++) {
						String[] feesCatAndIndex = feesCategoryIds[i].split("_");
						int feesCatIndex = Integer.parseInt(feesCatAndIndex[1]);
						Studentfeesstructure studentfeesstructure = new Studentfeesstructure();
						Feescategory feescategory = new Feescategory();
						studentfeesstructure.setStudent(studentDetailsDao.readUniqueObject(Integer.valueOf(id)));
						feescategory.setIdfeescategory(Integer.parseInt(feesCatAndIndex[0]));
						studentfeesstructure.setFeescategory(feescategory);
						studentfeesstructure.setFeesamount(Long.parseLong(feesAmount[feesCatIndex]));
						studentfeesstructure.setFeespaid((long) 0);
						studentfeesstructure.setWaiveoff((long) 0);
						studentfeesstructure.setTotalinstallment(Integer.parseInt(totalInstallments[feesCatIndex]));
						studentfeesstructure.setAcademicyear(feesYears[feesCatIndex]);
						studentfeesstructure.setBranchid(Integer.parseInt(branchid));
						studentfeesstructure.setUserid(Integer.parseInt(userid));
						studentfeesstructure.setConcession(Integer.parseInt(concession[feesCatIndex]));
						listOfstudentfeesstructure.add(studentfeesstructure);
					}

				}

				// Accounts
				// Pass J.V. : credit the Fees as income & debit the cash

				int crFees = getLedgerAccountId("unearnedstudentfeesincome" + Integer.parseInt(branchid));
				int drAccount = getLedgerAccountId("studentfeesreceivable" + Integer.parseInt(branchid));

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
				transactions.setFinancialyear(accountDao.getCurrentFinancialYear(Integer.parseInt(branchid)).getFinancialid());
				transactions.setBranchid(Integer.parseInt(branchid));
				transactions.setUserid(Integer.parseInt(userid));

				String updateDrAccount = "update Accountdetailsbalance set currentbalance=currentbalance+" + grandTotal + " where accountdetailsid=" + drAccount;

				String updateCrAccount = "update Accountdetailsbalance set currentbalance=currentbalance+" + grandTotal + " where accountdetailsid=" + crFees;

				// End J.V
				stampFeesDao.addStampFees(listOfacademicfessstructure, currentAcademicYear, listOfstudentfeesstructure, transactions, updateDrAccount, updateCrAccount);
				// studentDetailsDao.addStudentfeesstructure(listOfstudentfeesstructure,httpSession.getAttribute(CURRENTACADEMICYEAR).toString());

			}
		}
	}

	private int getLedgerAccountId(String itemAccount) {
		
		int result = 0;
	 	
        	int itemLedgerId = propertiesUtil.getIntPropertiesValue(itemAccount, -1);
        	log.debug("The value of ItemLedgerId:{}", itemLedgerId);
		    if(itemLedgerId != -1) {
		    	result = itemLedgerId;
		    }else {
		    	itemLedgerId = propertiesUtil.getIntPropertiesValue(itemAccount.toLowerCase(), 0);
		    }
		    
		    return result;
	}

	public void deleteFeesStamp(StudentIdsDto studentIdsDto) {
		String currentYear = studentIdsDto.getCurrentYear();
		String[] studentIds = studentIdsDto.getStudentIds();
		if(studentIds!=null){
			List<Integer> ids = new ArrayList<>();
	        for (String id : studentIds) {
	            log.debug("id:{}", id);
	            ids.add(Integer.valueOf(id));
	            
	        }
	        log.debug("id length:{}", studentIds.length);
	        stampFeesDao.deleteMultiple(ids,currentYear);
		
	}
		}
	
	public void addotherFeesStamp(StampFeesDto stampFeesDto,String currentAcademicYear,String branchid,String userid ) {

		if (currentAcademicYear != null) {
			String[] studentIds = stampFeesDto.getStudentIds();
			Long totalFeesAmount = 0l;
			if (studentIds != null) {
				Academicotherfeesstructure academicfessstructure = new Academicotherfeesstructure();
				List<Academicotherfeesstructure> listOfacademicfessstructure = new ArrayList<>();
				List<Studentotherfeesstructure> listOfstudentfeesstructure = new ArrayList<>();

				String feesTotalAmount = stampFeesDto.getFeesTotalAmount();
				Long grandTotal = 0l;

				String[] feesCategoryIds = stampFeesDto.getFeesCategoryIds();
				String[] feesAmount = stampFeesDto.getFeesAmount();
				String[] concession = stampFeesDto.getConcession();
				String[] totalInstallments = stampFeesDto.getTotalInstallments();
				String[] feesYears = stampFeesDto.getFeesYears();

				listOfacademicfessstructure.clear();
				for (String id : studentIds) {
					academicfessstructure = new Academicotherfeesstructure();
					academicfessstructure.setSid(Integer.valueOf(id));
					academicfessstructure.setAcademicyear(feesYears[0]);
					academicfessstructure.setUserid(Integer.parseInt(userid));
					academicfessstructure.setTotalfees(feesTotalAmount);
					grandTotal = grandTotal + Long.parseLong(academicfessstructure.getTotalfees());
					academicfessstructure.setBranchid(Integer.parseInt(branchid));
					academicfessstructure.setUserid(Integer.parseInt(userid));

					listOfacademicfessstructure.add(academicfessstructure);
				}

				for (String id : studentIds) {

					for (int i = 0; i < feesCategoryIds.length; i++) {

						String[] feesCatAndIndex = feesCategoryIds[i].split("_");
						int feesCatIndex = Integer.parseInt(feesCatAndIndex[1]);

						Studentotherfeesstructure studentfeesstructure = new Studentotherfeesstructure();
						OtherFeecategory feescategory = new OtherFeecategory();
						studentfeesstructure.setStudent(studentDetailsDao.readUniqueObject(Integer.valueOf(id)));
						feescategory.setIdfeescategory(Integer.parseInt(feesCatAndIndex[0]));
						studentfeesstructure.setOtherfeescategory(feescategory);
						studentfeesstructure.setFeesamount(Long.parseLong(feesAmount[feesCatIndex]));
						studentfeesstructure.setFeespaid((long) 0);
						studentfeesstructure.setWaiveoff((long) 0);
						studentfeesstructure.setTotalinstallment(Integer.parseInt(totalInstallments[feesCatIndex]));
						studentfeesstructure.setAcademicyear(feesYears[feesCatIndex]);
						studentfeesstructure.setBranchid(Integer.parseInt(branchid));
						studentfeesstructure.setUserid(Integer.parseInt(userid));
						studentfeesstructure.setConcession(Integer.parseInt(concession[feesCatIndex]));
						listOfstudentfeesstructure.add(studentfeesstructure);

						totalFeesAmount = totalFeesAmount + Long.parseLong(feesAmount[feesCatIndex]);
					}
				}

				stampFeesDao.addotherStampFees(listOfacademicfessstructure, currentAcademicYear, listOfstudentfeesstructure);

			}
		}
	}
	
	public OtherFeesCategoryResponseDto otheradvanceSearch(SearchStudentDto searchStudentDto,String branchid,String currentAcademicYear) {
		OtherFeesCategoryResponseDto otherFeescategoryResponseDto = new OtherFeesCategoryResponseDto();
		List<Parents> searchStudentList = new ArrayList<>();

		if (branchid != null) {
			
			String className = searchStudentDto.getClassSearch();
        	
			List<OtherFeecategory> otherFeecategoryList = feesCategoryDao.getOtherFeeCategory(className, currentAcademicYear, Integer.parseInt(branchid));
            otherFeescategoryResponseDto.setOtherFeesCategory(otherFeecategoryList);
    		
    		// Get Student Details

			String queryMain = "From Parents as parents where";
			String studentname = DataUtil.emptyString(searchStudentDto.getNameSearch());
			String addClass = searchStudentDto.getClassSearch();
			String addSec = searchStudentDto.getSecSearch();
			String conClassStudying = "";
	
			if (StringUtils.hasLength(addClass)) {
				conClassStudying = addClass+"--"+"%";
			}
			if (StringUtils.hasLength(addSec)) {
				conClassStudying = addClass;
				conClassStudying = conClassStudying + "--" + addSec + "%";
			}
	
			String classStudying = DataUtil.emptyString(conClassStudying);
			String querySub = "";
	
			if (StringUtils.hasLength(studentname)) {
				querySub = " parents.student.name like '%" + studentname + "%' AND parents.student.archive=0 and parents.student.passedout=0 AND parents.student.droppedout=0 and parents.student.leftout=0 AND parents.student.branchid="+Integer.parseInt(branchid);
			}
	
			if (StringUtils.hasLength(classStudying)
					&& StringUtils.hasLength(querySub)) {
				querySub = querySub + " AND parents.student.classstudying like '"
						+ classStudying + "' AND parents.student.archive=0 and parents.student.passedout=0 AND parents.student.droppedout=0 and parents.student.leftout=0";
			} else if (StringUtils.hasLength(classStudying)) {
				querySub = querySub + " parents.student.classstudying like '"
						+ classStudying + "' AND parents.student.archive=0 and parents.student.passedout=0 AND parents.student.droppedout=0 and parents.student.leftout=0 AND parents.student.branchid="+Integer.parseInt(branchid)+" order by parents.student.admissionnumber ASC";
			}
	
			if(StringUtils.hasLength(querySub)) {
				queryMain = queryMain + querySub;
				searchStudentList = studentDetailsDao.getStudentsList(queryMain);
			}
			otherFeescategoryResponseDto.setSearchStudentList(searchStudentList);
		}
		
		return otherFeescategoryResponseDto;
	}

	public FeescategoryResponseDto advanceSearchForStampFees(SearchStudentDto searchStudentDto,String branchid,String currentAcademicYear,String branchId){
		
		FeescategoryResponseDto feescategoryResponseDto = new FeescategoryResponseDto();

        if(StringUtils.hasLength(branchid)){
        	String className = searchStudentDto.getClassSearch();
        	
            List<Feescategory> feecategoryList= feesCategoryDao.getfeecategoryofstudent(className,currentAcademicYear,branchId);
            feescategoryResponseDto.setFeescategory(feecategoryList);
  		
    		
    		
    		// Get Student Details
    		
    		List<Parents> searchStudentList = new ArrayList<>();
    		
    		String queryMain = "From Parents as parents where";
    		String studentname = DataUtil.emptyString(searchStudentDto.getNameSearch());
    		String addClass = searchStudentDto.getClassSearch();
    		String addSec = searchStudentDto.getSecSearch();
    		String studentType = searchStudentDto.getStudentType();
    		String conClassStudying = "";
    		String querySub = "";
    		String classStudying = "";
    		String academicYear = searchStudentDto.getCategoryYear();

    		switch (studentType) {
			case "Active":
				if (StringUtils.hasLength(addClass)) {
	    			conClassStudying = addClass+"--"+"%";
	    		}
	    		if (StringUtils.hasLength(addSec)) {
	    			conClassStudying = addClass;
	    			conClassStudying = conClassStudying+"--"+addSec+"%";
	    		}

	    		classStudying = DataUtil.emptyString(conClassStudying);

	    		if (StringUtils.hasLength(studentname)) {
	    			querySub = " parents.student.name like '%" + studentname + "%' AND parents.student.archive=0 and parents.student.passedout=0 AND parents.student.droppedout=0 and parents.student.leftout=0 AND (parents.student.yearofadmission='"+academicYear+"' OR parents.student.promotedyear='"+academicYear+"') AND parents.student.branchid="+Integer.parseInt(branchid);
	    		}

	    		if (StringUtils.hasLength(classStudying) && StringUtils.hasLength(querySub)) {
	    			querySub = querySub + " AND parents.student.classstudying like '"
	    					+ classStudying + "' AND parents.student.archive=0 and parents.student.passedout=0 AND parents.student.droppedout=0 and parents.student.leftout=0";
	    		} else if (StringUtils.hasLength(classStudying)) {
	    			querySub = querySub + " parents.student.classstudying like '"
	    					+ classStudying + "' AND parents.student.archive=0 and parents.student.passedout=0 AND parents.student.droppedout=0 and parents.student.leftout=0 AND (parents.student.yearofadmission='"+academicYear+"' OR parents.student.promotedyear='"+academicYear+"') AND parents.student.branchid="+Integer.parseInt(branchid)+" order by parents.student.admissionnumber ASC";
	    		}
				break;
			case "InActive":
				if (StringUtils.hasLength(addClass)) {
	    			conClassStudying = addClass+"--"+"%";
	    		}
	    		if (StringUtils.hasLength(addSec)) {
	    			conClassStudying = addClass;
	    			conClassStudying = conClassStudying+"--"+addSec+"%";
	    		}

	    		classStudying = DataUtil.emptyString(conClassStudying);

	    		if (StringUtils.hasLength(studentname)) {
	    			querySub = " parents.student.name like '%" + studentname + "%' AND (parents.student.archive=1 or parents.student.passedout=1 or parents.student.droppedout=1 or parents.student.leftout=1) AND parents.student.branchid="+Integer.parseInt(branchid);
	    		}

	    		if (StringUtils.hasLength(classStudying) && StringUtils.hasLength(querySub)) {
	    			querySub = querySub + " AND parents.student.classstudying like '"
	    					+ classStudying + "' AND (parents.student.archive=1 or parents.student.passedout=1 or parents.student.droppedout=1 or parents.student.leftout=1)";
	    		} else if (StringUtils.hasLength(classStudying)) {
	    			querySub = querySub + " parents.student.classstudying like '"
	    					+ classStudying + "' AND (parents.student.archive=1 or parents.student.passedout=1 or parents.student.droppedout=1 or parents.student.leftout=1) AND parents.student.branchid="+Integer.parseInt(branchid)+" order by parents.student.admissionnumber ASC";
	    		}
				break;	
			case "All":	
				
				if (StringUtils.hasLength(addClass)) {
	    			conClassStudying = addClass+"--"+"%";
	    		}
	    		if (StringUtils.hasLength(addSec)) {
	    			conClassStudying = addClass;
	    			conClassStudying = conClassStudying+"--"+addSec+"%";
	    		}

	    		classStudying = DataUtil.emptyString(conClassStudying);

	    		if (StringUtils.hasLength(studentname)) {
	    			querySub = " parents.student.name like '%" + studentname + "%' AND  parents.student.branchid="+Integer.parseInt(branchid);
	    		}

	    		if (StringUtils.hasLength(classStudying) && StringUtils.hasLength(querySub)) {
	    			querySub = querySub + " AND parents.student.classstudying like '"
	    					+ classStudying + "'";
	    		} else if (StringUtils.hasLength(classStudying)) {
	    			querySub = querySub + " parents.student.classstudying like '"
	    					+ classStudying + "' AND  parents.student.branchid="+Integer.parseInt(branchid)+" order by parents.student.admissionnumber ASC";
	    		}
				break;
			default:
				if (StringUtils.hasLength(addClass)) {
	    			conClassStudying = addClass+"--"+"%";
	    		}
	    		if (StringUtils.hasLength(addSec)) {
	    			conClassStudying = addClass;
	    			conClassStudying = conClassStudying+"--"+addSec+"%";
	    		}

	    		classStudying = DataUtil.emptyString(conClassStudying);

	    		if (StringUtils.hasLength(studentname)) {
	    			querySub = " parents.student.name like '%" + studentname + "%' AND parents.student.archive=0 and parents.student.passedout=0 AND parents.student.droppedout=0 and parents.student.leftout=0 AND parents.student.branchid="+Integer.parseInt(branchid);
	    		}

	    		if (StringUtils.hasLength(classStudying) && StringUtils.hasLength(querySub)) {
	    			querySub = querySub + " AND parents.student.classstudying like '"
	    					+ classStudying + "' AND parents.student.archive=0 and parents.student.passedout=0 AND parents.student.droppedout=0 and parents.student.leftout=0";
	    		} else if (StringUtils.hasLength(classStudying)) {
	    			querySub = querySub + " parents.student.classstudying like '"
	    					+ classStudying + "' AND parents.student.archive=0 and parents.student.passedout=0 AND parents.student.droppedout=0 and parents.student.leftout=0 AND parents.student.branchid="+Integer.parseInt(branchid)+" order by parents.student.admissionnumber ASC";
	    		}
				break;
			}

    		if(StringUtils.hasLength(querySub)) {
    			queryMain = queryMain + querySub;
    			searchStudentList = studentDetailsDao.getStudentsList(queryMain);
    		}
    		
    		feescategoryResponseDto.setSearchStudentList(searchStudentList);


        }
        return feescategoryResponseDto;
	}

	public FeescategoryResponseDto advanceSearchForStampFeesByCategory(SearchStudentDto searchStudentDto,String branchid){
		
		FeescategoryResponseDto feescategoryResponseDto = new FeescategoryResponseDto();

        if(StringUtils.hasLength(branchid)){
        	String[] className = searchStudentDto.getClassesSearch();
       
            List<Feescategory> feecategoryList= feesCategoryDao.getFeecategoryByName(className,searchStudentDto,branchid);
            feescategoryResponseDto.setFeescategory(feecategoryList);
    		
    		// Get Student Details
    		
    		List<Parents> searchStudentList = new ArrayList<>();
    		
    		if(StringUtils.hasLength(branchid)) {
    		
    		String queryMain = "From Parents as parents where";
    		String[] addClass = searchStudentDto.getClassesSearch();
    		String querySub = "";
    		String classStudying = "";

				if (addClass.length>0) {
					StringBuilder sb = new StringBuilder();

					for (int i = 0; i < addClass.length; i++) {

					    sb.append(" parents.student.classstudying LIKE '%")
					      .append(addClass[i])
					      .append("%' ");

					    if (i < addClass.length - 1) {
					        sb.append(" OR ");
					    }
					}

					classStudying = sb.toString();
	    		}

	    		if (StringUtils.hasLength(classStudying)) {
	    			querySub = classStudying+"  AND parents.student.archive=0 and parents.student.passedout=0 AND parents.student.droppedout=0 and parents.student.leftout=0 AND parents.student.branchid="+Integer.parseInt(branchid)+" order by parents.student.admissionnumber ASC";
	    		}
				

    		if(StringUtils.hasLength(querySub)) {
    			queryMain = queryMain + querySub;
    			searchStudentList = studentDetailsDao.getStudentsList(queryMain);
    		}
    		
    	}
    		
    		feescategoryResponseDto.setSearchStudentList(searchStudentList);

        }
        return feescategoryResponseDto;
	}
	
	public void addFeesStampAll(StampFeesDto stampFeesDto,String currentAcademicYear,String branchid,String userid ) {
		
		if(StringUtils.hasLength(currentAcademicYear)) {
		String[] studentIds = stampFeesDto.getStudentIds();
		if(studentIds!=null){
		Academicfeesstructure academicfessstructure = new Academicfeesstructure();
		List<Academicfeesstructure> listOfacademicfessstructure = new ArrayList<>();
		List<Studentfeesstructure> listOfstudentfeesstructure = new ArrayList<>();
		
		Long grandTotal = 0l;
		
		String[] feesCategoryIds = stampFeesDto.getFeesCategoryIds();
		String[] feesAmount = stampFeesDto.getFeesAmount();
		String[] concession = stampFeesDto.getConcession();
		String[] totalInstallments = stampFeesDto.getTotalInstallments();
		String[] feesYears = stampFeesDto.getFeesYears();
		
		listOfacademicfessstructure.clear();
			
		for (String idStudent : studentIds) {
			String[] studentIdClass = idStudent.split("_");
			Long totalFeesAmount = 0l;
			
			for(int i=0; i < feesCategoryIds.length ; i++){
				String[] feesCatAndIndex =  feesCategoryIds[i].split("_");
				int feesCatIndex = Integer.parseInt(feesCatAndIndex[1]);
				
				if(studentIdClass[1].equalsIgnoreCase(feesCatAndIndex[2])) {
					
				//check whether the fees category is already stamped 
				List<Studentfeesstructure> result = stampFeesDao.getStudentFeesStructure(Integer.parseInt(studentIdClass[0]),Integer.parseInt(feesCatAndIndex[0]),currentAcademicYear);
				// END
				
				if(CollectionUtils.isEmpty(result)) {
					
					Studentfeesstructure studentfeesstructure = new Studentfeesstructure();   
					Feescategory feescategory = new Feescategory();
					studentfeesstructure.setStudent(studentDetailsDao.readUniqueObject(Integer.valueOf(studentIdClass[0])));
					feescategory.setIdfeescategory(Integer.parseInt(feesCatAndIndex[0]));
					studentfeesstructure.setFeescategory(feescategory);
					studentfeesstructure.setFeesamount(Long.parseLong(feesAmount[feesCatIndex]));
					studentfeesstructure.setFeespaid((long) 0);
					studentfeesstructure.setWaiveoff((long) 0);
					studentfeesstructure.setTotalinstallment(Integer.parseInt(totalInstallments[feesCatIndex]));
					studentfeesstructure.setAcademicyear(feesYears[feesCatIndex]);
					studentfeesstructure.setBranchid(Integer.parseInt(branchid));
					studentfeesstructure.setUserid(Integer.parseInt(userid));
					studentfeesstructure.setConcession(Integer.parseInt(concession[feesCatIndex]));
					listOfstudentfeesstructure.add(studentfeesstructure);
					
					totalFeesAmount = totalFeesAmount+ Long.parseLong(feesAmount[feesCatIndex]);
				}
		
		}}
			
			academicfessstructure = new Academicfeesstructure();
			academicfessstructure.setSid(Integer.valueOf(studentIdClass[0]));
			academicfessstructure.setAcademicyear(feesYears[0]);
			academicfessstructure.setUserid(Integer.parseInt(userid));
			academicfessstructure.setTotalfees(totalFeesAmount.toString());
			academicfessstructure.setBranchid(Integer.parseInt(branchid));
			academicfessstructure.setUserid(Integer.parseInt(userid));
			
			listOfacademicfessstructure.add(academicfessstructure);
			
			grandTotal = grandTotal + totalFeesAmount;
		}
		
		//Accounts
		//Pass J.V. : credit the Fees as income & debit the cash
		
		int crFees = getLedgerAccountId("unearnedstudentfeesincome"+Integer.parseInt(branchid));
		int drAccount = getLedgerAccountId("studentfeesreceivable"+Integer.parseInt(branchid));;
		
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
		transactions.setFinancialyear(accountDao.getCurrentFinancialYear(Integer.parseInt(branchid)).getFinancialid());
		transactions.setBranchid(Integer.parseInt(branchid));
		transactions.setUserid(Integer.parseInt(userid));
		
		String updateDrAccount="update Accountdetailsbalance set currentbalance=currentbalance+"+grandTotal+" where accountdetailsid="+drAccount;

		String updateCrAccount="update Accountdetailsbalance set currentbalance=currentbalance+"+grandTotal+" where accountdetailsid="+crFees;
		
		// End J.V
		stampFeesDao.addStampFees(listOfacademicfessstructure,currentAcademicYear,listOfstudentfeesstructure,transactions,updateDrAccount,updateCrAccount);
		//new studentDetailsDAO().addStudentfeesstructure(listOfstudentfeesstructure,httpSession.getAttribute(CURRENTACADEMICYEAR).toString());

		}
		}
	}
	
public void addSingleFeesStamp(StampFeesDto stampFeesDto,String currentAcademicYear,String branchid,String userid ) {
		
		if (currentAcademicYear != null) {
			String[] studentIds = stampFeesDto.getStudentIds();
			if (studentIds != null) {
				Academicfeesstructure academicfessstructure = new Academicfeesstructure();
				List<Academicfeesstructure> listOfacademicfessstructure = new ArrayList<>();
				List<Studentfeesstructure> listOfstudentfeesstructure = new ArrayList<>();

				String feesTotalAmount = stampFeesDto.getFeesTotalAmount();
				Long grandTotal = 0l;

				String feesCategoryId = stampFeesDto.getFeesCategoryId();
				String[] feesAmount = stampFeesDto.getFeesAmount();
				String[] concession = stampFeesDto.getConcession();
				String[] feesYears = stampFeesDto.getFeesYears();

				listOfacademicfessstructure.clear();
				for (String ids : studentIds) {
					
					String[] studentids = ids.split("_");
					String id = studentids[0];
					int studentIndex = Integer.parseInt(studentids[1]);
						String[] feesCatAndIndex = feesCategoryId.split("_");
						int feesCatIndex = Integer.parseInt(feesCatAndIndex[1]);
						Long totalFeesAmount = Long.parseLong(feesAmount[studentIndex]);
						// check whether the fees category is already stamped
						List<Studentfeesstructure> result = stampFeesDao.getStudentFeesStructure(Integer.parseInt(id), Integer.parseInt(feesCatAndIndex[0]), currentAcademicYear);
						// END
						if (CollectionUtils.isEmpty(result)) {

							Studentfeesstructure studentfeesstructure = new Studentfeesstructure();
							Feescategory feescategory = new Feescategory();
							studentfeesstructure.setStudent(studentDetailsDao.readUniqueObject(DataUtil.parseInt(id)));
							feescategory.setIdfeescategory(Integer.parseInt(feesCatAndIndex[0]));
							studentfeesstructure.setFeescategory(feescategory);
							studentfeesstructure.setFeesamount(Long.parseLong(feesAmount[studentIndex]));
							studentfeesstructure.setFeespaid((long) 0);
							studentfeesstructure.setWaiveoff((long) 0);
							studentfeesstructure.setTotalinstallment(1);
							studentfeesstructure.setAcademicyear(feesYears[feesCatIndex]);
							studentfeesstructure.setBranchid(Integer.parseInt(branchid));
							studentfeesstructure.setUserid(Integer.parseInt(userid));
							studentfeesstructure.setConcession(0);
							listOfstudentfeesstructure.add(studentfeesstructure);

						}


					academicfessstructure = new Academicfeesstructure();
					academicfessstructure.setSid(Integer.valueOf(id));
					academicfessstructure.setAcademicyear(feesYears[0]);
					academicfessstructure.setUserid(Integer.parseInt(userid));
					academicfessstructure.setTotalfees(totalFeesAmount.toString());
					grandTotal = grandTotal + Long.parseLong(academicfessstructure.getTotalfees());
					academicfessstructure.setBranchid(Integer.parseInt(branchid));
					academicfessstructure.setUserid(Integer.parseInt(userid));

					listOfacademicfessstructure.add(academicfessstructure);
					// ids.add(Integer.valueOf(id));
					grandTotal = grandTotal + totalFeesAmount;

				}

				for (String ids : studentIds) {
					String[] studentids = ids.split("_");
					String id = studentids[0];
					int studentIndex = Integer.parseInt(studentids[1]);
						String[] feesCatAndIndex = feesCategoryId.split("_");
						int feesCatIndex = Integer.parseInt(feesCatAndIndex[1]);
						Studentfeesstructure studentfeesstructure = new Studentfeesstructure();
						Feescategory feescategory = new Feescategory();
						studentfeesstructure.setStudent(studentDetailsDao.readUniqueObject(Integer.valueOf(id)));
						feescategory.setIdfeescategory(Integer.parseInt(feesCatAndIndex[0]));
						studentfeesstructure.setFeescategory(feescategory);
						studentfeesstructure.setFeesamount(Long.parseLong(feesAmount[studentIndex]));
						studentfeesstructure.setFeespaid((long) 0);
						studentfeesstructure.setWaiveoff((long) 0);
						studentfeesstructure.setTotalinstallment(1);
						studentfeesstructure.setAcademicyear(feesYears[feesCatIndex]);
						studentfeesstructure.setBranchid(Integer.parseInt(branchid));
						studentfeesstructure.setUserid(Integer.parseInt(userid));
						studentfeesstructure.setConcession(0);
						listOfstudentfeesstructure.add(studentfeesstructure);

				}

				// Accounts
				// Pass J.V. : credit the Fees as income & debit the cash

				int crFees = getLedgerAccountId("unearnedstudentfeesincome" + Integer.parseInt(branchid));
				int drAccount = getLedgerAccountId("studentfeesreceivable" + Integer.parseInt(branchid));

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
				transactions.setFinancialyear(accountDao.getCurrentFinancialYear(Integer.parseInt(branchid)).getFinancialid());
				transactions.setBranchid(Integer.parseInt(branchid));
				transactions.setUserid(Integer.parseInt(userid));

				String updateDrAccount = "update Accountdetailsbalance set currentbalance=currentbalance+" + grandTotal + " where accountdetailsid=" + drAccount;

				String updateCrAccount = "update Accountdetailsbalance set currentbalance=currentbalance+" + grandTotal + " where accountdetailsid=" + crFees;

				// End J.V
				stampFeesDao.addStampFees(listOfacademicfessstructure, currentAcademicYear, listOfstudentfeesstructure, transactions, updateDrAccount, updateCrAccount);
				// studentDetailsDao.addStudentfeesstructure(listOfstudentfeesstructure,httpSession.getAttribute(CURRENTACADEMICYEAR).toString());

			}
		}
	}

}

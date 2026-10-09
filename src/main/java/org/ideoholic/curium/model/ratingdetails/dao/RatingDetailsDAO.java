package org.ideoholic.curium.model.ratingdetails.dao;

import java.util.ArrayList;
import java.util.List;

import org.ideoholic.curium.model.ratingdetails.dto.AssessmentRank;
import org.ideoholic.curium.model.ratingdetails.dto.HolisticRating;
import org.ideoholic.curium.repositories.AssessmentRankRepository;
import org.ideoholic.curium.repositories.HolisticRatingRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class RatingDetailsDAO {

    private final HolisticRatingRepository holisticRatingRepo;
    private final AssessmentRankRepository assessmentRankRepo;

    @Transactional
    public String addRatings(List<HolisticRating> ratingList) {
        try {
            holisticRatingRepo.saveAll(ratingList);
            return "success";
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(e.getMessage(), e);
            return "Duplicate";
        }
    }

    @Transactional
    public List<HolisticRating> readListOfRatings(int sid, int assessmentsubjectid, int assessmentid, String academicYear) {
        List<HolisticRating> ratings = new ArrayList<>();
        try {
            ratings = holisticRatingRepo.findBySidAndAssessmentsubjectidAndAssessmentidAndAcademicyear(sid,
                    assessmentsubjectid, assessmentid, academicYear);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return ratings;
    }

    @Transactional
    public boolean updateRatings(List<HolisticRating> ratingList) {
        try {
            for (HolisticRating rating : ratingList) {
                holisticRatingRepo.updateRatingByRatingid(rating.getRatinggrade(), rating.getRatingvalue(),
                        rating.getRatingid());
            }
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(e.getMessage(), e);
            return false;
        }
    }

    @Transactional
    public boolean deleteMultiple(List<Integer> ids) {
        try {
            holisticRatingRepo.deleteByRatingidIn(ids);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(e.getMessage(), e);
            return false;
        }
    }

    @Transactional
    public List<HolisticRating> readListOfRatingsForAllAssessments(List<Integer> studentIds, String academicYear,
            int branchId) {
        List<HolisticRating> ratings = new ArrayList<>();
        try {
            ratings = holisticRatingRepo.findBySidInAndAcademicyearAndBranchid(studentIds, academicYear, branchId);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return ratings;
    }

    @Transactional
    public boolean addAssessmentRank(List<AssessmentRank> rankList) {
        try {
            assessmentRankRepo.saveAll(rankList);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(e.getMessage(), e);
            return false;
        }
    }

    @Transactional
    public List<Object[]> fetchStudentProgressData(Integer studentId, String academicYear, int branchId) {
        List<Object[]> progressData = new ArrayList<>();
        try {
            progressData = holisticRatingRepo.fetchStudentProgressData(studentId, academicYear, branchId);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return progressData;
    }
}

package org.ideoholic.curium.model.sponsor.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.query.Query;
import org.ideoholic.curium.model.sponsor.dto.Sponsor;
import org.ideoholic.curium.model.student.dto.Studentfeesstructure;
import org.ideoholic.curium.repositories.SponsorRepository;
import org.ideoholic.curium.repositories.StudentFeesStructureRepository;
import org.ideoholic.curium.util.HibernateUtil;
import org.springframework.stereotype.Component;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Component
@RequiredArgsConstructor
public class SponsorDao {

	private final SponsorRepository sponsorRepo;
    private final StudentFeesStructureRepository studentFeesRepo;

    @Transactional
	public boolean addSponsor(Sponsor sponsor) {
        try {
            sponsorRepo.save(sponsor);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return false;
    }

    @Transactional
	public List<Sponsor> viewSponsor(int branchId) {
        List<Sponsor> results = new ArrayList<>();
        try {
            results = sponsorRepo.findByBranchid(branchId);
        }catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return results;
    }

    @Transactional
	public void deleteMultiple(List<Integer> ids) {
        try {
            sponsorRepo.deleteByIdIn(ids);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }

    @Transactional
	public Sponsor readUniqueObject(int id) {
        Sponsor sponsor = new Sponsor();
        try {
            Sponsor found = sponsorRepo.findById(id).orElse(null);
            if (found != null) sponsor = found;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return sponsor;
    }

    @Transactional
	public boolean updateSponsor(Sponsor sponsor) {
        try {
            sponsorRepo.save(sponsor); // save acts as update
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return false;
    }

    @Transactional
	public List<Studentfeesstructure> getFeesStructureBySponsor(int branchId, String sponsorName) {
        List<Studentfeesstructure> results = new ArrayList<>();
        try {
            results = studentFeesRepo.findByConcessionnotesAndBranchid(sponsorName, branchId);
        }  catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return results;
    }
	
}

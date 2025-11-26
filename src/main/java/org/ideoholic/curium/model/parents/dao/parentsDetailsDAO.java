package org.ideoholic.curium.model.parents.dao;

import java.util.List;
import java.util.Optional;

import org.ideoholic.curium.model.parents.dto.Parents;
import org.ideoholic.curium.model.user.dto.Login;
import org.ideoholic.curium.repositories.LoginRepository;
import org.ideoholic.curium.repositories.ParentsRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
@RequiredArgsConstructor
public class parentsDetailsDAO {

    private final ParentsRepository parentsRepo;

    private final LoginRepository loginRepo;

    /**
     * Create a single Parents entry.
     */
    @Transactional
    public Parents create(Parents parents) {
        try {
            // Old: transaction = session.beginTransaction(); session.save(parents); transaction.commit();
            return parentsRepo.save(parents);
        } catch (Exception hibernateException) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            return parents;
        }
    }

    /**
     * Read a unique Parents object by student id.
     */
    @Transactional(readOnly = true)
    public Parents readUniqueObject(Integer id) {
        Parents parents = new Parents();
        try {
            // Old: Query query = session.createQuery("from Parents as parents where parents.student.sid=" + id);
            Optional<Parents> result = parentsRepo.findByStudentSid(id);
            if (result.isPresent()) {
                parents = result.get();
            }
        } catch (Exception hibernateException) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
        }
        return parents;
    }

    /**
     * Read a unique Parents object by student's external id (plogin).
     */
    @Transactional(readOnly = true)
    public Parents readploginUniqueObject(String id) {
        Parents parents = new Parents();
        try {
            // Old: Query query = session.createQuery("from Parents as parents where parents.student.studentexternalid='" + id + "'" );
            Optional<Parents> result = parentsRepo.findByStudentExternalId(id);
            if (result.isPresent()) {
                parents = result.get();
            }
        } catch (Exception hibernateException) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
        }
        return parents;
    }

    /**
     * Update a Parents entry.
     */
    @Transactional
    public Parents update(Parents parents) {
        try {
            // Old: session.update(parents);
            Parents updated = parentsRepo.save(parents);
            log.debug("in update parents");
            return updated;
        } catch (Exception hibernateException) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            return parents;
        }
    }

    /**
     * Create multiple Parents and Login entries at once.
     */
    @Transactional
    public boolean createMultiple(List<Parents> parentsList, List<Login> listParentLogin) {
        boolean result = false;
        try {
            // Old: for (Parents parent : parents) { session.save(parent); }
            parentsRepo.saveAll(parentsList);

            // Old: Query query = session.createQuery("from Login order by userid DESC"); query.setMaxResults(1);
            // This is to find the last userid
            Login last = loginRepo.findTopByOrderByUseridDesc().orElse(null);
            int userid = (last != null && last.getUserid() != null) ? last.getUserid() + 1 : 1;

            for (Login login : listParentLogin) {
                login.setUserid(userid);
                loginRepo.save(login);
                userid++;
            }
            result = true;
        } catch (Exception hibernateException) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
        }
        return result;
    }
}
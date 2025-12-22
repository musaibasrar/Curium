package org.ideoholic.curium.repositories;

import java.util.List;

import org.ideoholic.curium.model.enquiry.dto.AdmissionEnquiry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdmissionEnquiryRepository extends JpaRepository<AdmissionEnquiry, Integer> {

    // Find all enquiries for a branch
    List<AdmissionEnquiry> findByBranchId(int branchId);

    // Get last enquiry (highest id) for a branch
    AdmissionEnquiry findTopByBranchIdOrderByIdDesc(int branchId);

}
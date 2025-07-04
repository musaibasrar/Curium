package org.ideoholic.curium.repositories;

import org.ideoholic.curium.model.enquiry.dto.Enquiry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnquiryRepository extends JpaRepository<Enquiry, Integer> {

}

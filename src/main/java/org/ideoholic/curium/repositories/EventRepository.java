package org.ideoholic.curium.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.ideoholic.curium.model.event.dto.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByStartDateTimeBetweenAndBranchidOrderByStartDateTimeAsc(LocalDateTime start, LocalDateTime end, int branchid);

    List<Event> findAllByOrderByStartDateTimeAsc();
}
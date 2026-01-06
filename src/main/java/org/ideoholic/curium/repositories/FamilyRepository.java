package org.ideoholic.curium.repositories;

import java.util.List;
import java.util.Optional;

import org.ideoholic.curium.model.family.dto.Family;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repository for Family (relation_id) lookups.
 *
 * Provides:
 * - List<Family> findByParentPid(pid)  : returns all families the parent participates in
 * - List<Family> findByStudentSid(sid) : returns all families the student participates in
 *
 * Also provides convenience "findFirst..." methods which return an Optional<Family>
 * (useful when you expect a single family and want a quick result).
 */
@Repository
public interface FamilyRepository extends JpaRepository<Family, Integer> {

    /**
     * Find all families that contain the given parent (by pid).
     * Use this when a parent may belong to multiple families (e.g. complex households).
     */
    @Query("SELECT DISTINCT f FROM Family f JOIN f.members m WHERE m.parent.pid = :pid")
    List<Family> findByParentPid(@Param("pid") Integer pid);

    /**
     * Find all families that contain the given student (by sid).
     */
    @Query("SELECT DISTINCT f FROM Family f JOIN f.members m WHERE m.student.sid = :sid")
    List<Family> findByStudentSid(@Param("sid") Integer sid);

    /**
     * Convenience: return the first matching family for a given parent pid (if any).
     * Spring Data will generate a query that limits to a single result.
     * Use this when you expect at most one family for the parent and prefer an Optional return.
     */
    Optional<Family> findFirstByMembersParentPid(Integer pid);

    /**
     * Convenience: return the first matching family for a given student sid (if any).
     */
    Optional<Family> findFirstByMembersStudentSid(Integer sid);
}
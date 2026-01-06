package org.ideoholic.curium.repositories;

import java.util.List;

import org.ideoholic.curium.model.family.dto.FamilyMember;
import org.ideoholic.curium.model.student.dto.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FamilyMemberRepository extends JpaRepository<FamilyMember, Integer> {

    /**
     * Find siblings of a student (other students that share at least one family with the given student).
     * This returns Student entities — the service will map them to DTOs.
     *
     * Note: The relationship is computed from FamilyMember -> Family -> Family.members.
     * The query excludes the student itself.
     */
    @Query("SELECT DISTINCT m2.student " +
           "FROM FamilyMember m1 " +
           "JOIN m1.family f " +
           "JOIN f.members m2 " +
           "WHERE m1.student.sid = :sid " +
           "  AND m2.student IS NOT NULL " +
           "  AND m2.student.sid <> :sid")
    List<Student> findSiblingsByStudentSid(@Param("sid") Integer sid);

    /**
     * Find offsprings (students) for a parent identified by pid.
     * This returns Student entities that are in any family that the parent belongs to.
     */
    @Query("SELECT DISTINCT m2.student " +
           "FROM FamilyMember mp " +
           "JOIN mp.family f " +
           "JOIN f.members m2 " +
           "WHERE mp.parent.pid = :pid " +
           "  AND m2.student IS NOT NULL")
    List<Student> findOffspringsByParentPid(@Param("pid") Integer pid);

    /**
     * Optional helper methods you may find useful:
     * - fetch all FamilyMember rows for a given student
     * - fetch all FamilyMember rows for a given parent
     *
     * These can be used when you need FamilyMember objects instead of only Student results.
     */
    List<FamilyMember> findByStudentSid(@Param("sid") Integer sid);
    List<FamilyMember> findByParentPid(@Param("pid") Integer pid);
}
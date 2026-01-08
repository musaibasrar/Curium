package org.ideoholic.curium.model.family.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.ideoholic.curium.model.parents.dto.Parents;
import org.ideoholic.curium.model.student.dto.Student;

import lombok.Data;

/**
 * FamilyMember links a Family (relation_id) to exactly one of:
 *  - a Student (sid) OR
 *  - a Parent (pid)
 *
 * Application must ensure only one of student or parent is set. A validation hook is included.
 */
@Data
@Entity
@Table(name = "family_member")
public class FamilyMember implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // owning family (relation_id)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "family_id", referencedColumnName = "id", nullable = false, updatable = false)
    private Family family;

    // "STUDENT" or "PARENT" - convenience and helpful for querying
    @Column(name = "member_type", length = 10)
    private MemberType memberType;

    // when memberType = 'STUDENT' populate this
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sid", referencedColumnName = "sid", insertable = true, updatable = false)
    private Student student;

    // when memberType = 'PARENT' populate this
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pid", referencedColumnName = "pid", insertable = true, updatable = false)
    private Parents parent;

    // optional role inside the family (eg. 'father', 'mother', 'child')
    @Column(name = "role", length = 50)
    private String role;

    @Column(name = "added_at")
    private Date addedAt = new Date();

    /**
     * Validation: ensure exactly one of student or parent is set.
     * Also auto-populates memberType if missing.
     */
    @PrePersist
    @PreUpdate
    private void validateMember() {
        boolean hasStudent = this.student != null;
        boolean hasParent = this.parent != null;
        if (hasStudent == hasParent) { // both true or both false
            throw new IllegalStateException("FamilyMember must have exactly one of student OR parent set (sid XOR pid).");
        }
        if (this.memberType == null) {
            this.memberType = hasStudent ? MemberType.STUDENT : MemberType.PARENT;
        }
    }

    // Convenience constructors
    public FamilyMember(Family family, Student student) {
        this.family = family;
        this.student = student;
        this.memberType = MemberType.STUDENT;
        this.addedAt = new Date();
    }

    public FamilyMember(Family family, Parents parent) {
        this.family = family;
        this.parent = parent;
        this.memberType = MemberType.PARENT;
        this.addedAt = new Date();
    }
}

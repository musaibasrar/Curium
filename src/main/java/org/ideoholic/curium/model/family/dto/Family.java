package org.ideoholic.curium.model.family.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

/**
 * Family (relation_id) master entity.
 * - id is the relation_id you requested
 * - members are FamilyMember rows (students or parents) attached to this family
 *
 * This entity is additive and doesn't change existing Student or Parents tables.
 */
@Data
@Entity
@Table(name = "family")
public class Family implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // relation_id

    @Column(name = "name", length = 200)
    private String name;

    @Column(name = "created_at")
    private Date createdAt = new Date();

    /**
     * Family members (students and parents).
     * CascadeType.ALL is safe here because FamilyMember is a dependent entity.
     * This does NOT cascade to Student or Parents entities (FamilyMember -> Student/Parents has no cascade).
     */
    @OneToMany(mappedBy = "family", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FamilyMember> members;
}
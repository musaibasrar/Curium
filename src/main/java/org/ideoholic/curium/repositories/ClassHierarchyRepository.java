package org.ideoholic.curium.repositories;

import java.util.List;

import org.ideoholic.curium.model.std.dto.Classhierarchy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassHierarchyRepository extends JpaRepository<Classhierarchy, Integer> {

	List<Classhierarchy> findByLowerclassAndBranchid(String classStudying, int branchid);

	List<Classhierarchy> findByLowerclass(String classStudying);

}
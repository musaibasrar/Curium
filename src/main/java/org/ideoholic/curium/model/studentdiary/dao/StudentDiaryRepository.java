package org.ideoholic.curium.model.studentdiary.dao;

import org.ideoholic.curium.model.studentdiary.dto.StudentDiary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentDiaryRepository extends JpaRepository<StudentDiary, Integer> {

}

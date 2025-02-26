package org.ideoholic.curium.model.diary.dao;

import org.ideoholic.curium.model.diary.dto.Diary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryRepository extends JpaRepository<Diary, Integer>{

}

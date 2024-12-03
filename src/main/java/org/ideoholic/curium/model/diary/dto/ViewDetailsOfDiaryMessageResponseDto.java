package org.ideoholic.curium.model.diary.dto;

import org.ideoholic.curium.model.studentdiary.dto.StudentDiary;

import lombok.Data;

@Data
public class ViewDetailsOfDiaryMessageResponseDto {
    private Diary diary;
    private StudentDiary diaries;
    private boolean success;

}

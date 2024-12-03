package org.ideoholic.curium.model.diary.dto;

import org.ideoholic.curium.model.studentdiary.dto.StudentDiary;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class DiaryDetailsMessageResponseDto {
    private Diary diary;
    private StudentDiary diaries;
    private boolean success;

}

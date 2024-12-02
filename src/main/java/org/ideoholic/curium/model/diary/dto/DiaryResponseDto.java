package org.ideoholic.curium.model.diary.dto;

import lombok.Data;

import java.util.List;

import org.ideoholic.curium.model.studentdiary.dto.StudentDiaryDTO;

import com.fasterxml.jackson.annotation.JsonInclude;

@Data
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class DiaryResponseDto {
    private List<Diary> diary;
    private  List<StudentDiaryDTO> diaryDetails;
    private int noOfPages;
    private int currentPage;
    private boolean success;
    private List<Diary> diaryparents;
}

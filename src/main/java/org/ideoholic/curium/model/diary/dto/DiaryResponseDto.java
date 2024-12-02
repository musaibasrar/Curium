package org.ideoholic.curium.model.diary.dto;

import lombok.Data;

import java.util.List;

import org.ideoholic.curium.model.studentdiary.dto.StudentDiaryDTO;

@Data
public class DiaryResponseDto {
    private List<Diary> diary;
    private  List<StudentDiaryDTO> diaryDetails;
    private int noOfPages;
    private int currentPage;
    private boolean success;
    private List<Diary> diaryparents;
}

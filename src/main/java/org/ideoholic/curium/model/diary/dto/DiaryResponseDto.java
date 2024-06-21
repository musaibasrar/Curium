package org.ideoholic.curium.model.diary.dto;

import lombok.Data;

import java.util.List;

@Data
public class DiaryResponseDto {
    private List<Diary> diary;
    private int noOfPages;
    private int currentPage;
    private boolean success;
    private List<Diary> diaryparents;
}

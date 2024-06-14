package org.ideoholic.curium.model.diary.dto;

import lombok.Data;

import java.util.List;

@Data
public class ViewDiaryResponseDto {
    private List<Diary> diary;
    private int noOfPages;
    private int currentPage;
    private boolean success;
}

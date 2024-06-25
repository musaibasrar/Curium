package org.ideoholic.curium.model.diary.dto;

import lombok.Data;

@Data
public class ViewDetailsOfDiaryMessageResponseDto {
    private Diary diary;
    private boolean success;

}

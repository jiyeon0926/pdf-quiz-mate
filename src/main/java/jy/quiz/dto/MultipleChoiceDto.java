package jy.quiz.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MultipleChoiceDto {

    private final int number;
    private final String choice;
}

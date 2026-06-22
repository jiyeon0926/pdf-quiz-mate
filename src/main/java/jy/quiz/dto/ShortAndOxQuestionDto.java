package jy.quiz.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ShortAndOxQuestionDto {

    private final int questionNumber;
    private final String question;
    private final String answer;
    private final String explanation;
}

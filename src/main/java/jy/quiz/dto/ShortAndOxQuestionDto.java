package jy.quiz.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ShortAndOxQuestionDto {

    private int questionNumber;
    private String question;
    private String answer;
    private String explanation;
}

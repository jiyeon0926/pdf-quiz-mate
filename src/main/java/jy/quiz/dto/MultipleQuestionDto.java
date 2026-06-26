package jy.quiz.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MultipleQuestionDto {

    private int questionNumber;
    private String question;
    private List<MultipleChoiceDto> choices;
    private MultipleAnswerDto answer;
    private String explanation;
}

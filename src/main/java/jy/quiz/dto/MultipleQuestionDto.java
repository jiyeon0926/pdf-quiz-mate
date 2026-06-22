package jy.quiz.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class MultipleQuestionDto {

    private final int questionNumber;
    private final String question;
    private final List<MultipleChoiceDto> choices;
    private final MultipleAnswerDto answer;
    private final String explanation;
}

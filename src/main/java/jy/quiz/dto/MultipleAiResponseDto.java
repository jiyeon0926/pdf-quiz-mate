package jy.quiz.dto;

import jy.quiz.enums.QuestionType;
import lombok.Getter;

import java.util.List;

@Getter
public class MultipleAiResponseDto extends QuizCommonAiResponseDto {

    private final List<MultipleQuestionDto> questions;

    public MultipleAiResponseDto(QuestionType questionType, int questionCount, List<MultipleQuestionDto> questions) {
        super(questionType, questionCount);
        this.questions = questions;
    }
}

package jy.quiz.dto;

import jy.quiz.enums.QuestionCount;
import jy.quiz.enums.QuestionType;
import jy.quiz.enums.QuizStatus;
import lombok.Getter;

import java.util.List;

@Getter
public class MultipleResponseDto extends QuizCommonResponseDto {

    private final List<MultipleQuestionDto> questions;

    public MultipleResponseDto(QuestionType questionType, QuestionCount questionCount, QuizStatus status, List<MultipleQuestionDto> questions) {
        super(questionType, questionCount, status);
        this.questions = questions;
    }
}

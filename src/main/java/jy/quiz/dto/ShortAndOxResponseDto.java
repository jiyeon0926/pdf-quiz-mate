package jy.quiz.dto;

import jy.quiz.enums.QuestionCount;
import jy.quiz.enums.QuestionType;
import jy.quiz.enums.QuizStatus;
import lombok.Getter;

import java.util.List;

@Getter
public class ShortAndOxResponseDto extends QuizCommonResponseDto {

    private final List<ShortAndOxQuestionDto> questions;

    public ShortAndOxResponseDto(String id, QuestionType questionType, QuestionCount questionCount, QuizStatus status, List<ShortAndOxQuestionDto> questions) {
        super(id, questionType, questionCount, status);
        this.questions = questions;
    }
}

package jy.quiz.dto;

import jy.quiz.enums.QuestionType;
import lombok.Getter;

import java.util.List;

@Getter
public class ShortAndOxAiResponseDto extends QuizCommonAiResponseDto {

    private final List<ShortAndOxQuestionDto> questions;

    public ShortAndOxAiResponseDto(QuestionType questionType, int questionCount, List<ShortAndOxQuestionDto> questions) {
        super(questionType, questionCount);
        this.questions = questions;
    }
}

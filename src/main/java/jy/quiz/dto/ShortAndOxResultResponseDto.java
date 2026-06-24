package jy.quiz.dto;

import jy.quiz.enums.QuestionType;
import jy.quiz.enums.QuizStatus;
import lombok.Getter;

import java.util.List;

@Getter
public class ShortAndOxResultResponseDto extends QuizCommonResultResponseDto {

    private final List<ShortAndOxQuestionDto> questions;

    public ShortAndOxResultResponseDto(QuestionType questionType, int questionCount, QuizStatus status, List<ShortAndOxQuestionDto> questions) {
        super(questionType, questionCount, status);
        this.questions = questions;
    }

    public static ShortAndOxResultResponseDto completed(ShortAndOxAiResponseDto aiResponseDto) {
        return new ShortAndOxResultResponseDto(
                aiResponseDto.getQuestionType(),
                aiResponseDto.getQuestionCount(),
                QuizStatus.COMPLETED,
                aiResponseDto.getQuestions()
        );
    }
}

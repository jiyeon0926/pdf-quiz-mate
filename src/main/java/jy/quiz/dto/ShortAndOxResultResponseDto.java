package jy.quiz.dto;

import jy.quiz.enums.QuestionType;
import jy.quiz.enums.QuizStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ShortAndOxResultResponseDto extends QuizCommonResultResponseDto {

    private List<ShortAndOxQuestionDto> questions;

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

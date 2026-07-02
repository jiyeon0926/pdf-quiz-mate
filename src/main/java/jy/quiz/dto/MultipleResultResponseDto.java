package jy.quiz.dto;

import jy.quiz.enums.QuestionType;
import jy.quiz.enums.QuizStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MultipleResultResponseDto extends QuizCommonResultResponseDto {

    private List<MultipleQuestionDto> questions;

    public MultipleResultResponseDto(QuestionType questionType, int questionCount, QuizStatus status, List<MultipleQuestionDto> questions) {
        super(questionType, questionCount, status);
        this.questions = questions;
    }

    public static MultipleResultResponseDto completed(MultipleAiResponseDto aiResponseDto) {
        return new MultipleResultResponseDto(
                aiResponseDto.getQuestionType(),
                aiResponseDto.getQuestionCount(),
                QuizStatus.COMPLETED,
                aiResponseDto.getQuestions()
        );
    }
}

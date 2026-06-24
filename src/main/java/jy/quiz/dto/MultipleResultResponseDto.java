package jy.quiz.dto;

import jy.quiz.enums.QuestionType;
import jy.quiz.enums.QuizStatus;
import lombok.Getter;

import java.util.List;

@Getter
public class MultipleResultResponseDto extends QuizCommonResultResponseDto {

    private final List<MultipleQuestionDto> questions;

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

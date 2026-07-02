package jy.quiz.dto;

import jy.quiz.enums.QuestionType;
import jy.quiz.enums.QuizStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class QuizCommonResultResponseDto {

    private QuestionType questionType;
    private int questionCount;
    private QuizStatus status;

    protected QuizCommonResultResponseDto(QuestionType questionType, int questionCount, QuizStatus status) {
        this.questionType = questionType;
        this.questionCount = questionCount;
        this.status = status;
    }
}

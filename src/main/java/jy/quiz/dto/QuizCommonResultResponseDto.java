package jy.quiz.dto;

import jy.quiz.enums.QuestionType;
import jy.quiz.enums.QuizStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public abstract class QuizCommonResultResponseDto {

    private final QuestionType questionType;
    private final int questionCount;
    private final QuizStatus status;
}

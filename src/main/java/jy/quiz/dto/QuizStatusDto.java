package jy.quiz.dto;

import jy.quiz.enums.QuizStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class QuizStatusDto {

    private final QuizStatus status;

    public static QuizStatusDto of(QuizStatus status) {
        return new QuizStatusDto(status);
    }
}

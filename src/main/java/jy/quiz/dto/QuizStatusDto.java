package jy.quiz.dto;

import jy.quiz.enums.QuizStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuizStatusDto {

    private QuizStatus status;

    public QuizStatusDto(QuizStatus status) {
        this.status = status;
    }

    public static QuizStatusDto of(QuizStatus status) {
        return new QuizStatusDto(status);
    }
}

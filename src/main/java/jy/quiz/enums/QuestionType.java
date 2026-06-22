package jy.quiz.enums;

public enum QuestionType {

    MULTIPLE("4지선다"),
    SHORT("단답형"),
    OX("ox 퀴즈");

    private final String description;

    QuestionType(String description) {
        this.description = description;
    }
}

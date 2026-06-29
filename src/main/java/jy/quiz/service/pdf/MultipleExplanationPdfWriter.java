package jy.quiz.service.pdf;

import jy.quiz.dto.MultipleAnswerDto;
import jy.quiz.dto.MultipleQuestionDto;
import jy.quiz.dto.MultipleResultResponseDto;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class MultipleExplanationPdfWriter implements PdfWriter<MultipleResultResponseDto> {

    @Override
    public void write(MultipleResultResponseDto result, PdfContext context) throws IOException {
        for (MultipleQuestionDto q : result.getQuestions()) {
            MultipleAnswerDto answer = q.getAnswer();
            String choiceLabel = getChoiceLabel(answer.getNumber());

            context.write(11, q.getQuestionNumber() + ". " + choiceLabel + " " + answer.getChoice());
            context.write(11, "해설 " + q.getExplanation());

            context.newLine(20);
        }
    }

    private String getChoiceLabel(int number) {
        List<String> labels = List.of("①", "②", "③", "④");

        return labels.get(number - 1);
    }
}

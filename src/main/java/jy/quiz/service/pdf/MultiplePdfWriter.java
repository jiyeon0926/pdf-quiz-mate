package jy.quiz.service.pdf;

import jy.quiz.dto.MultipleChoiceDto;
import jy.quiz.dto.MultipleQuestionDto;
import jy.quiz.dto.MultipleResultResponseDto;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class MultiplePdfWriter implements PdfWriter<MultipleResultResponseDto> {

    @Override
    public void write(MultipleResultResponseDto result, PdfContext context) throws IOException {
        for (MultipleQuestionDto q : result.getQuestions()) {
            context.write(11, q.getQuestionNumber() + ". " + q.getQuestion());
            context.newLine(20);

            for (MultipleChoiceDto c : q.getChoices()) {
                context.write(11, getChoiceLabel(c.getNumber()) + " " + c.getChoice());
                context.newLine(18);
            }

            context.newLine(20);
        }
    }

    private String getChoiceLabel(int number) {
        List<String> labels = List.of("①", "②", "③", "④");

        return labels.get(number - 1);
    }
}

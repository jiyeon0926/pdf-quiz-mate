package jy.quiz.service.pdf;

import jy.quiz.dto.ShortAndOxQuestionDto;
import jy.quiz.dto.ShortAndOxResultResponseDto;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ShortAndOxExplanationPdfWriter implements PdfWriter<ShortAndOxResultResponseDto> {

    @Override
    public void write(ShortAndOxResultResponseDto result, PdfContext context) throws IOException {
        for (ShortAndOxQuestionDto q : result.getQuestions()) {
            context.write(11, q.getQuestionNumber() + ". " + q.getAnswer());
            context.write(11, "해설 " + q.getExplanation());

            context.newLine(20);
        }
    }
}

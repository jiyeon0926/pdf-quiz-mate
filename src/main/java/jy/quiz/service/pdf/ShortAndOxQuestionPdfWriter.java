package jy.quiz.service.pdf;

import jy.quiz.dto.ShortAndOxQuestionDto;
import jy.quiz.dto.ShortAndOxResultResponseDto;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ShortAndOxQuestionPdfWriter implements PdfWriter<ShortAndOxResultResponseDto> {

    @Override
    public void write(ShortAndOxResultResponseDto result, PdfContext context) throws IOException {
        for (ShortAndOxQuestionDto q : result.getQuestions()) {
            context.write(11, q.getQuestionNumber() + ". " + q.getQuestion());
            context.newLine(20);
        }
    }
}

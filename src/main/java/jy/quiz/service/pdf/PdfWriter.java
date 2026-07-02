package jy.quiz.service.pdf;

import java.io.IOException;

public interface PdfWriter<T> {

    void write(T result, PdfContext context) throws IOException;
}

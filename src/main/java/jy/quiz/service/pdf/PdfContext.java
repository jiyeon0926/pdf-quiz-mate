package jy.quiz.service.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

import java.io.IOException;

public class PdfContext {

    private PDDocument document;
    private PDPageContentStream contentStream;
    private PDType0Font font;
    private float x;
    private float y;

    private PdfContext(PDDocument document, PDPageContentStream contentStream, PDType0Font font, float x, float y) {
        this.document = document;
        this.contentStream = contentStream;
        this.font = font;
        this.x = x;
        this.y = y;
    }

    public static PdfContext create(PDDocument document, PDPageContentStream contentStream, PDType0Font font, float x, float y) {
        return new PdfContext(document, contentStream, font, x, y);
    }

    public void write(float fontSize, String text) throws IOException {
        contentStream.beginText();
        contentStream.setFont(font, fontSize);
        contentStream.newLineAtOffset(x, y);
        contentStream.showText(text);
        contentStream.endText();
    }

    public void newLine(float gap) {
        y -= gap;
    }
}

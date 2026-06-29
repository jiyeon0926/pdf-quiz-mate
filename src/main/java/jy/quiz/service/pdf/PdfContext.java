package jy.quiz.service.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PdfContext {

    private static final float START_X = 40;
    private static final float START_Y = 800;
    private static final float RIGHT_MARGIN = 40;
    private static final float BOTTOM_MARGIN = 40;
    private static final float LINE_SPACING = 10;
    private static final float RIGHT_LIMIT = PDRectangle.A4.getWidth() - RIGHT_MARGIN;

    private final PDDocument document;
    private PDPageContentStream contentStream;
    private final PDType0Font font;
    private float x;
    private float y;

    private PdfContext(PDDocument document, PDPageContentStream contentStream, PDType0Font font) {
        this.document = document;
        this.contentStream = contentStream;
        this.font = font;
        this.x = START_X;
        this.y = START_Y;
    }

    public static PdfContext create(PDDocument document, PDPageContentStream contentStream, PDType0Font font) {
        return new PdfContext(document, contentStream, font);
    }

    public void write(float fontSize, String text) throws IOException {
        float lineHeight = fontSize + LINE_SPACING;

        for (String line : wrapText(text, fontSize)) {
            checkPageOverflow(lineHeight);

            contentStream.beginText();
            contentStream.setFont(font, fontSize);
            contentStream.newLineAtOffset(x, y);
            contentStream.showText(line);
            contentStream.endText();

            y -= lineHeight;
        }
    }

    public void close() throws IOException {
        contentStream.close();
    }

    public void newLine(float gap) {
        y -= gap;
    }

    private List<String> wrapText(String text, float fontSize) throws IOException {
        List<String> lines = new ArrayList<>();
        StringBuilder line = new StringBuilder();

        for (char c : text.toCharArray()) {
            line.append(c);

            if (isTextOverflow(line.toString(), fontSize)) {
                line.deleteCharAt(line.length() - 1);
                lines.add(line.toString());
                line.setLength(0);
                line.append(c);
            }
        }

        if (!line.isEmpty()) {
            lines.add(line.toString());
        }

        return lines;
    }

    private boolean isTextOverflow(String text, float fontSize) throws IOException {
        float width = font.getStringWidth(text) / 1000 * fontSize;

        return x + width > RIGHT_LIMIT;
    }

    /**
     * 현재 위치(y)에서 requiredHeight만큼 내용을 출력했을 때
     * 하단 여백 기준(BOTTOM_MARGIN)을 넘으면 새 페이지 추가
     */
    private void checkPageOverflow(float requiredHeight) throws IOException {
        if (y - requiredHeight < BOTTOM_MARGIN) {
            newPage();
        }
    }

    private void newPage() throws IOException {
        close();

        PDPage page = new PDPage(PDRectangle.A4);
        document.addPage(page);

        contentStream = new PDPageContentStream(document, page);
        x = START_X;
        y = START_Y;
    }
}

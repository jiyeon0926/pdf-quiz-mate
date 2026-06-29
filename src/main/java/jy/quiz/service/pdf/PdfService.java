package jy.quiz.service.pdf;

import jy.quiz.dto.MultipleResultResponseDto;
import jy.quiz.dto.QuizCommonResultResponseDto;
import jy.quiz.dto.ShortAndOxResultResponseDto;
import jy.quiz.exception.ServerCustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class PdfService {

    private final MultiplePdfWriter multiplePdfWriter;
    private final ShortAndOxPdfWriter shortAndOxPdfWriter;

    /**
     * PDF 파일로부터 텍스트 추출
     *
     * @param pdfFile
     * @return 추출된 텍스트
     * @throws ServerCustomException 텍스트 추출 실패 시
     */
    public String extractTextFromPdf(MultipartFile pdfFile) {
        log.info("PDF 텍스트 추출 시작: {}", pdfFile.getName());

        try {
            PDDocument document = PDDocument.load(pdfFile.getInputStream());
            log.debug("PDF 문서 로드 성공: {}페이지", document.getNumberOfPages());

            PDFTextStripper pdfTextStripper = new PDFTextStripper();
            String text = pdfTextStripper.getText(document);
            log.debug("PDF 텍스트 추출 완료: {} 문자", text.length());

            return text;
        } catch (IOException e) {
            log.error("PDF 텍스트 추출 실패");
            throw new ServerCustomException("PDF에서 텍스트 추출 실패: " + e.getMessage(), e);
        }
    }

    public byte[] generateQuestionPdf(QuizCommonResultResponseDto result) throws IOException {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage(PDRectangle.A4);
        document.addPage(page);
        PDType0Font font = font("fonts/NanumGothic.ttf", document);

        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        PdfContext context = PdfContext.create(document, contentStream, font);

        switch (result.getQuestionType()) {
            case MULTIPLE -> multiplePdfWriter.write((MultipleResultResponseDto) result, context);
            case SHORT, OX -> shortAndOxPdfWriter.write((ShortAndOxResultResponseDto) result, context);
        }

        context.close();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        document.save(byteArrayOutputStream);
        document.close();

        return byteArrayOutputStream.toByteArray();
    }

    private PDType0Font font(String path, PDDocument document) throws IOException {
        return PDType0Font.load(document, new ClassPathResource(path).getInputStream());
    }
}

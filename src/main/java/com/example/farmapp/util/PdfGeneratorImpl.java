package com.example.farmapp.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.farmapp.Entity.Report;
import com.example.farmapp.dto.ReportResDTO;

import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.w3c.tidy.Tidy;
import org.xhtmlrenderer.pdf.ITextRenderer;

@Service
public class PdfGeneratorImpl implements PdfGenerator {

    @Autowired
    private TemplateEngine templateEngine;

    private static final String UTF_8 = "UTF-8";

    @Override
    public void createPdf(ReportResDTO reportResDTO) throws Exception {
        Context context = new Context();
        context.setVariable("data", reportResDTO);
        context.setVariable("baseUrl", getCurrentBaseUrl());

        String processHTML = templateEngine.process("template", context);

        String xHtml = convertToXhtml(processHTML);

        ITextRenderer renderer = new ITextRenderer();

        String baseUrl = getClass().getResource("/templates/").toString();

        renderer.setDocumentFromString(xHtml, baseUrl);
        renderer.layout();

        // And finally, we create the PDF:
        OutputStream outputStream = new FileOutputStream("./pdf/" + reportResDTO.getReportPdfName() + ".pdf");
        renderer.createPDF(outputStream);
        outputStream.close();

    }

    public void downloadPdf(HttpServletResponse response, List<Report> reportList) {
        response.setContentType("application/octet-stream");
        response.setHeader("Access-Control-Allow-Headers",
                "Access-Control-Allow-Headers, Origin,Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers");
        response.setHeader("Content-Disposition", "attachment;filename=download.zip");
        response.setStatus(HttpServletResponse.SC_OK);

        try (ZipOutputStream zippedOut = new ZipOutputStream(response.getOutputStream())) {
            for (Report report : reportList) {

                FileSystemResource resource = new FileSystemResource(report.getLocation());

                ZipEntry e = new ZipEntry(resource.getFilename());
                // Configure the zip entry, the properties of the file
                e.setSize(resource.contentLength());
                e.setTime(System.currentTimeMillis());
                // etc.
                zippedOut.putNextEntry(e);
                // And the content of the resource:
                StreamUtils.copy(resource.getInputStream(), zippedOut);

                zippedOut.closeEntry();
            }
            zippedOut.finish();
        } catch (Exception e) {
            e.printStackTrace();
            // Exception handling goes here
        }
    }

    private static String getCurrentBaseUrl() {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest req = sra.getRequest();
        return req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + req.getContextPath();
    }

    private String convertToXhtml(String html) throws UnsupportedEncodingException {
        Tidy tidy = new Tidy();
        tidy.setInputEncoding(UTF_8);
        tidy.setOutputEncoding(UTF_8);
        tidy.setXHTML(true);

        tidy.setShowErrors(0);
        tidy.setShowWarnings(false);
        tidy.setQuiet(true);

        ByteArrayInputStream inputStream = new ByteArrayInputStream(html.getBytes(UTF_8));
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        tidy.parseDOM(inputStream, outputStream);
        return outputStream.toString(UTF_8);
    }

}
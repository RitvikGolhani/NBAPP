package com.nobroker.service.impl;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.nobroker.entity.User;
import com.nobroker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class PdfService {

    @Autowired
    private UserRepository userRepository;

    public byte[] generatePdfReport() throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        // Create a PDF document
        PdfDocument pdfDocument = new PdfDocument(new PdfWriter(outputStream));
        Document document = new Document(pdfDocument);

        // Create a table with 6 columns
        Table table = new Table(6);

        // Add table headers
        table.addCell(new Cell().add(new Paragraph("ID")));
        table.addCell(new Cell().add(new Paragraph("Name")));
        table.addCell(new Cell().add(new Paragraph("Email")));
        table.addCell(new Cell().add(new Paragraph("Password")));
        table.addCell(new Cell().add(new Paragraph("Mobile")));
        table.addCell(new Cell().add(new Paragraph("Email Verified")));

        // Fetch all user entities from the database
        List<User> userList = userRepository.findAll();

        // Add table rows
        for (User user : userList) {
            table.addCell(new Cell().add(new Paragraph(String.valueOf(user.getId()))));
            table.addCell(new Cell().add(new Paragraph(user.getName())));
            table.addCell(new Cell().add(new Paragraph(user.getEmail())));
            table.addCell(new Cell().add(new Paragraph(user.getPassword())));
            table.addCell(new Cell().add(new Paragraph(user.getMobile())));
            table.addCell(new Cell().add(new Paragraph(user.isEmailVerified() ? "Yes" : "No")));
        }

        // Add the table to the document
        document.add(table);

        // Close the document
        document.close();

        return outputStream.toByteArray();
    }
}

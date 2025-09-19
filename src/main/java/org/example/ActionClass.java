package org.example;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

public class ActionClass {
    public ActionClass(){}


    // To select an img file for conversion to PDF
    public void imgToPDFButtonClicked(){
        JFileChooser jFileChooser = new JFileChooser();
        FileNameExtensionFilter fileNameExtensionFilter = new FileNameExtensionFilter("Filter for PNG and JPG", "png", "jpg", "jpeg");
        jFileChooser.setFileFilter(fileNameExtensionFilter);
        int val = jFileChooser.showOpenDialog(null);
        if (val == JFileChooser.APPROVE_OPTION) {
            System.out.println("File chosen: " + jFileChooser.getSelectedFile().getName());
            System.out.println("File Path: " + jFileChooser.getSelectedFile().getPath());
            System.out.println("HomeDir of user: " + System.getProperty("user.home"));

            // Conversion process
            try {

                convertImgToPdf(jFileChooser.getSelectedFile());
                JOptionPane.showMessageDialog(null, "PDF saved in "+System.getProperty("user.home") + " !", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException | DocumentException e) {
                JOptionPane.showMessageDialog(null, "Something went wrong. Please select the right File Format for conversion. Aborting.", "Problem", JOptionPane.ERROR_MESSAGE);
                throw new RuntimeException(e);
            }
        }
    }

    private void convertImgToPdf(File file) throws IOException, DocumentException {
        FileOutputStream outputStream = new FileOutputStream(System.getProperty("user.home") + "/img_to_PDF" + UUID.randomUUID() + ".pdf");
        Document document = new Document();
        PdfWriter pdfWriter = PdfWriter.getInstance(document, outputStream);

        pdfWriter.open();

        Image imageToBeConverted = Image.getInstance(file.getPath());
        System.out.println("Width of IMG: " + imageToBeConverted.getWidth() + ", Height:  " + imageToBeConverted.getHeight());

        // Scale to Image to the Pagesize
        imageToBeConverted.scaleToFit(PageSize.A4);

        // Make the Document landscape if the Width of the IMG is greater than the height
        if (imageToBeConverted.getWidth() > imageToBeConverted.getHeight()) {
            document.setPageSize(PageSize.A4.rotate());
            // Scale to Image to the Pagesize
            imageToBeConverted.scaleToFit(PageSize.A4.rotate());
            document.setMargins(0, 0, 5, 0);
        }
        document.setMargins(0,0,5,0);
        document.open();
        document.add(imageToBeConverted);

        document.close();
        pdfWriter.close();
    }
}

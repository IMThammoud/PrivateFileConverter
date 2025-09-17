package org.example;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ActionClass {
    public ActionClass(){}


    // To select an img file for conversion to PDF
    public void imgToPDFButtonClicked(){
        JFileChooser jFileChooser = new JFileChooser();
        FileNameExtensionFilter fileNameExtensionFilter = new FileNameExtensionFilter("Filter for PNG and JPG", "png", "jpg", "jpeg");
        int val = jFileChooser.showOpenDialog(null);
        if (val == JFileChooser.APPROVE_OPTION) {
            System.out.println("File chosen: " + jFileChooser.getSelectedFile().getName());
        }
    }
}

package org.example;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        // Load Available Themes from the UIMANAGER
        // Check for Nimbus and set it as the LookAndFeel of the UI
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }

        ActionClass actionClass = new ActionClass(){};


        // #1 Init First Window Components (Frames, Buttons, etc.)
        JFrame mainFrame = new JFrame();
        JPanel pdfPanelForButtons = new JPanel(new GridLayout(0,1,5,5));
        pdfPanelForButtons.setSize(300,100);
        JPanel heicPanelForButtons = new JPanel(new GridLayout(0,1,5,5));
        heicPanelForButtons.setBounds(0, 110, 300, 100);
        pdfPanelForButtons.setBorder(BorderFactory.createTitledBorder("Convert JPEG, PNG to PDF Files"));
        heicPanelForButtons.setBorder(BorderFactory.createTitledBorder("Convert HEIC to IMG Files (JPEG)"));
        JButton imgToPdfButton = new JButton("Image to PDF");
        JButton imagesToPdfsButton = new JButton("SELECT IMAGE FILES");
        imagesToPdfsButton.setFont(new Font(Font.MONOSPACED, Font.BOLD, 12));
        JButton heicToJpegButton = new JButton("Heic to Jpeg");
        JButton multipleHeicToJpegButton = new JButton("SELECT HEIC FILES");

        // Scale the Icon to fit the button
        ImageIcon pdfIcon = new ImageIcon("src/main/icons/pdf.png");
        Image pdfIconScaled = pdfIcon.getImage().getScaledInstance(26, 26, Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(pdfIconScaled);

        ImageIcon heicIcon = new ImageIcon("src/main/icons/heic.png");
        Image heicIconTemp = heicIcon.getImage().getScaledInstance(26, 26, Image.SCALE_SMOOTH);
        ImageIcon heicImageIcon = new ImageIcon(heicIconTemp);

        // #1 Set first Window components specs
        mainFrame.setTitle("Private-FileConverter");
        mainFrame.setSize(300, 300 );
        mainFrame.setLayout(null);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        imgToPdfButton.setBounds(100,20 ,200,80);
        imagesToPdfsButton.setBounds(100, 110, 200, 80);
        imagesToPdfsButton.setIcon(icon);
        multipleHeicToJpegButton.setIcon(heicImageIcon);
        multipleHeicToJpegButton.setFont(new Font(Font.MONOSPACED, Font.BOLD, 12));
        multipleHeicToJpegButton.setBounds(100, 310, 200, 80);

        // Use Lambda to trigger an Action based on the click event of the button
        imgToPdfButton.addActionListener(e -> actionClass.imgToPDFButtonClicked());
        imagesToPdfsButton.addActionListener(e -> actionClass.imgsToPdfsSeperateClicked());
        heicToJpegButton.addActionListener(e -> actionClass.heicToJpegButtonClicked());
        multipleHeicToJpegButton.addActionListener(e -> actionClass.multipleHeicToJpegButtonClicked());

        // #1 add components to frame and Panels
        pdfPanelForButtons.add(imagesToPdfsButton);
        heicPanelForButtons.add(multipleHeicToJpegButton);
        mainFrame.add(pdfPanelForButtons);
        mainFrame.add(heicPanelForButtons);

        // #2 Add buttons to their Panels

    }
}
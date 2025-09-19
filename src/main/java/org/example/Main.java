package org.example;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // #1 Init First Window Components (Frames, Buttons, etc.)
        JFrame mainFrame = new JFrame();
        JButton imgToPdfButton = new JButton("Convert Image to PDF");
        JButton compressFileButton = new JButton("Compress File");

        ActionClass actionClass = new ActionClass(){};

        // #1 Set first Window components specs
        mainFrame.setTitle("Private-FileConverter");
        mainFrame.setSize(800, 600 );
        mainFrame.setLayout(null);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        imgToPdfButton.setBounds(100,20 ,200,80);
        compressFileButton.setBounds(100, 110, 200, 80);

        // Use Lambda to trigger an Action based on the click event of the button
        imgToPdfButton.addActionListener(e -> actionClass.imgToPDFButtonClicked());

        // #1 add components to frame
        mainFrame.add(imgToPdfButton);
        mainFrame.add(compressFileButton);
    }
}
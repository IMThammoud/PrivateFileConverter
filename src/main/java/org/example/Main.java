package org.example;

import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // #1 Init First Window Components (Frames, Buttons, etc.)
        JFrame mainFrame = new JFrame();
        JButton imgToPdfButton = new JButton("IMG  =>  PDF", null);

        ActionClass actionClass = new ActionClass(){};

        // #1 Set first Window components specs
        mainFrame.setSize(800, 600 );
        mainFrame.setLayout(null);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        imgToPdfButton.setBounds(100,0 ,200,80);

        // Use Lambda to trigger an Action based on the click event of the button
        imgToPdfButton.addActionListener(e -> actionClass.imgToPDFButtonClicked());

        // #1 add components to frame
        mainFrame.add(imgToPdfButton);
    }
}
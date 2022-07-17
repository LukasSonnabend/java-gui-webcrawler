package org.example;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

public class main {
  public static void main(String[] args) {
    System.setProperty("awt.useSystemAAFontSettings","on");
    System.setProperty("swing.aatext", "true");


    JFrame frame = new JFrame("Crawler");
    frame.setContentPane(new App().App);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
  }

}

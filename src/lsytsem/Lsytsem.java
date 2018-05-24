/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lsytsem;

import java.awt.BorderLayout;
import javax.swing.JFrame;

/**
 *
 * @author MazyK
 */
public class Lsytsem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // calling function for creating GUI 
        createAndShowGUI();
    }
    
    private static void createAndShowGUI(){
        // creating of window
        JFrame window = new JFrame("Plants generation using L-system");
        // application will end when the window is closed
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // GUI layout
        window.setLayout(new BorderLayout(10,10));
        // creating DrawPanel object
        DrawPanel dpanel = new DrawPanel();
        // adding DrawPanel object to the middle of the GUI
        window.add(dpanel,BorderLayout.CENTER);
        // creating ControlPanel and ControlPanelUP objects
        ControlPanel cpanel = new ControlPanel(dpanel);
        ControlPanelUP cpanelUP = new ControlPanelUP (dpanel);
        // adding panels to right side and upper area of GUI
        window.add(cpanelUP,BorderLayout.PAGE_START);
        window.add(cpanel,BorderLayout.LINE_END);
        // setting size of window so that every components are visible
        window.pack();
        // opening of the window
        window.setVisible(true);
    }
}





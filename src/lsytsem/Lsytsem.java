/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lsytsem;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

/**
 *
 * @author MazyK
 */
public class Lsytsem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // volání funkce pro vytvoření grafického rozhraní
        createAndShowGUI();
    }
    
    private static void createAndShowGUI(){
        // tvorba okna
        JFrame window = new JFrame("Okno");
        // po zavření okna aplikace skončí
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // layout grafického rozhraní vedle sebe
        window.setLayout(new BorderLayout(10,10));
        // tvorba objektu Plants, DrawPanel a ControlPanel
        DrawPanel dpanel = new DrawPanel();
        // přidání objektu Drawpanel do grafického rozhraní 
        window.add(dpanel,BorderLayout.CENTER);
        ControlPanel cpanel = new ControlPanel(dpanel);
        ControlPanelUP cpanelUP = new ControlPanelUP (dpanel);
        // přidání objektu ControlPanel do grafického rozhraní 
        window.add(cpanelUP,BorderLayout.PAGE_START);
        window.add(cpanel,BorderLayout.LINE_END);
        // nastaví velikost okna tak, aby veškeré komponenty byly vidět
        window.pack();
        // Otevření okna
        window.setVisible(true);
    }
}





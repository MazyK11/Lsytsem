/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lsytsem;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author MazyK
 */
public class Lsytsem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        String rule = "FF+[+F-F-F]-[-F+F+F]"; 
//        String axiom = "F";
//        int distance = 5;
//        double Angle = 22.5;
//        for (int i = 0;i<3;i++){
//            axiom = drawing(rule,axiom);
//        }
        
        SwingUtilities.invokeLater(new Runnable(){
            public void run() {
                createAndShowGUI();
            }
        });
 
    }
    private static void createAndShowGUI(){
        // objekt okna Jframe třída 
        JFrame window = new JFrame("Okno");
        // po zavření okna aplikace skončí
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // nastaví layout okna
        window.setLayout(new FlowLayout());
        // přidám do okno objekt panelu DrawPanel
        DrawPanel dpanel = new DrawPanel();
        window.add(dpanel);
        Rules r = new Rules("FF+[+F-F-F]-[-F+F+F]","F",5,22.5);
        ControlPanel cpanel = new ControlPanel(dpanel,r);
        window.add(cpanel);
        window.pack();
        // otevře okno
        window.setVisible(true);
    }
    
//    public static String drawing(String rule, String axiom){
//        axiom = generate(rule,axiom);
//        System.out.println(axiom);
//        return axiom;
//    }
//    
//    public static String generate(String rule, String axiom){
//        String axioms = "";
//        for (int i = 0;i<axiom.length();i++){ 
//            axioms = rules(axiom,i,rule,axioms);
//        }
//        return axioms;
//    }
//    
//    public static String rules(String axiom, int i,String rule,String axioms){
//        if (axiom.charAt(i) == 'F'){
//            axioms = axioms + rule;
//        }
//        return axioms;
//    }
    
}
class DrawPanel extends JPanel{
    private boolean drawing;
    List<int[]>lines;
    private double sx;
    private double sy;
    private double x;
    private double y;
    
    public DrawPanel(){
        drawing = false;
        sx = 250;
        sy = 500;
        x = 0;
        y = 0;
        lines = new LinkedList<>();
        setBorder(BorderFactory.createLineBorder(Color.black));
        setBackground(Color.white);
    }
    // když se ho pack zeptá jak má být velký - řekne 500 na 500
    @Override
    public Dimension getPreferredSize(){
        return new Dimension(500,500);
    }
    // kreslící funcke předdefinovaná
    @Override
    public void paintComponent(Graphics g){
        // nejdříve si třída Jpanel nakreslí svoje a pak kreslíme my
        super.paintComponent(g);
        g.setColor(Color.black);
        

        g.drawLine(0,0,100,100);

        // mužeme iterovat přes pole v listu
        for (int [] line : lines){
            g.drawLine(line[0],line[1],line[2],line[3]);
        }

    }
    
}

class ControlPanel extends JPanel{
    private JButton but;

    
    public ControlPanel(DrawPanel dpanel, Rules r) {
        
        but = new JButton ("Generovat");
        but.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String c = r.drawing(r.rule,r.axiom);
                System.out.println(c);
                dpanel.repaint();
            }
        });
        this.add(but);
    }
}

class Rules {
    protected  String rule; 
    protected String axiom;
    protected int distance;
    protected double Angle;
    
    public Rules (String rule_, String axiom_, int distance_, double angle_){
        this.rule = rule_;
        this.axiom = axiom_;
        this.distance = distance_;
        this.Angle = angle_;
    }
    
    public String drawing(String rule, String axiom){
        String axioms = "";
        for (int i = 0;i<axiom.length();i++){ 
            if (axiom.charAt(i) == 'F'){
                axioms = axioms + rule;
            }
        }
        
        System.out.println(axioms);
        this.axiom = axioms;
        return this.axiom;
    }
    
}



//            if (c == '+'){
//                
//            }
//            if (c == '['){
//                
//            }
//            if (c == '-'){
//                
//            }
//            if (c == ']'){
//                
//            }
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
import java.util.Stack;
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
        Rules r = new Rules("FF+[+F-F-F]-[-F+F+F]","F",50,22.5);
        DrawPanel dpanel = new DrawPanel(r);
        window.add(dpanel);
        ControlPanel cpanel = new ControlPanel(dpanel,r);
        window.add(cpanel);
        window.pack();
        // otevře okno
        window.setVisible(true);
    }
    
    
}
class DrawPanel extends JPanel{
    private List<int[]>lines;
    private int sx;
    private int sy;
    private int  x;
    private int y;
    private Stack s;
    
    public DrawPanel(Rules r){
        sx = 250;
        sy = 500;
        x = (int) (Math.sin(Math.toRadians(0)) * r.distance);
        y = (int) (Math.cos(Math.toRadians(0)) * r.distance);
        lines = new LinkedList<>();
        s = new Stack();
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
    }
    
    public void DrawingLines(Rules r){
        Graphics g = getGraphics();
        g.drawLine(250,500,250,450);
        g.setColor(Color.black);
        double rotate = 0;
        for (int i =0;i<r.axiom.length();i++){
            if (r.axiom.charAt(i) == '+'){
               rotate = rotate + r.Angle;
               x = (int) (Math.sin(Math.toRadians(rotate)) *  r.distance);
               y = (int) (Math.cos(Math.toRadians(rotate)) *  r.distance);
            }
            
            if (r.axiom.charAt(i) == '-'){
               rotate = rotate - r.Angle;
               x = (int) (Math.sin(Math.toRadians(rotate))*  r.distance);
               y = (int) (Math.cos(Math.toRadians(rotate))*  r.distance);
            }
            
            if (r.axiom.charAt(i) == '['){
                s.push(sx);
                s.push(sy);
            }
            
            if (r.axiom.charAt(i) == ']'){
                sy = (int) s.pop();
                sx = (int) s.pop();
            }
            
            if (r.axiom.charAt(i) == 'F'){
                int [] line = new int[4];
                line[0] = sx;
                line[1] = sy;
                line[2] = sx + x;
                line[3] = sy - y;
                System.out.println(line[0]);
                System.out.println(line[1]);
                System.out.println(line[2]);
                System.out.println(line[3]);
                lines.add(line);
                sx = sx + x;
                sy = sy - y;
            }

        }
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
                dpanel.DrawingLines(r);
                r.distance = r.distance/2;
            }
        });
        this.add(but);
    }
}

class Rules {
    protected String rule; 
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

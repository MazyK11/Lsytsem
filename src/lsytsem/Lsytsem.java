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
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author MazyK
 */
public class Lsytsem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        createAndShowGUI();
    }
    private static void createAndShowGUI(){
        // objekt okna Jframe třída 
        JFrame window = new JFrame("Okno");
        // po zavření okna aplikace skončí
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // nastaví layout okna
        window.setLayout(new FlowLayout());
        // přidám do okno objekt panelu DrawPanel
        Tree r = new Tree();
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
    private List<double[]>lines;
    private double sx;
    private double sy;
    private double x;
    private double y;
    private double rotate;
    private Stack s;
    
    public DrawPanel(Tree r){
        sx = 250;
        sy = 500;
        x = (Math.sin(Math.toRadians(0)) * r.distance);
        y = (Math.cos(Math.toRadians(0)) * r.distance);
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
    public void reset(Tree r){
        sx = 250;
        sy = 500;
        x = (Math.sin(Math.toRadians(0)) * r.distance);
        y = (Math.cos(Math.toRadians(0)) * r.distance);
        s = new Stack();
        rotate = 0;
        lines.clear(); 
    }
    
    public void DrawingLines(Tree r){
        Graphics g = getGraphics();
        Graphics2D g2 = (Graphics2D) g;
        g.setColor(Color.black);
        reset(r);
        for (int i =0;i<r.axiom.length();i++){
            if (r.axiom.charAt(i) == '+'){
               rotate = rotate + r.Angle;
               x = (Math.sin(Math.toRadians(rotate)) *  r.distance);
               y = (Math.cos(Math.toRadians(rotate)) *  r.distance);
            }
            
            if (r.axiom.charAt(i) == '-'){
                rotate = rotate - r.Angle;
                x = (Math.sin(Math.toRadians(rotate))*  r.distance);
                y = (Math.cos(Math.toRadians(rotate))*  r.distance);
            }
            
            if (r.axiom.charAt(i) == '['){
                s.push(sx);
                s.push(sy);
                s.push(rotate);
                s.push(x);
                s.push(y);
            }
            
            if (r.axiom.charAt(i) == ']'){
                y = (double) s.pop();
                x = (double) s.pop();
                rotate = (double) s.pop();
                sy = (double) s.pop();
                sx = (double) s.pop();
                
            }
            
            if (r.axiom.charAt(i) == 'F'){
                double [] line = new double[4];
                line[0] = sx;
                line[1] = sy;
                line[2] = sx + x;
                line[3] = sy - y;
                lines.add(line);
                sx = sx + x;
                sy = sy - y;
            }

        }
        for (double [] line : lines){
            g2.draw(new Line2D.Double(line[0],line[1], line[2], line[3]));
        }
        g2.dispose();
    }
    
}

class ControlPanel extends JPanel{
    private JButton gener;
    private JButton typ1;
    private JButton typ2;
    private JButton typ3;
    private JButton typ4;
    private JButton typ5;
    private JButton typ6;
    private JButton typ7;
    private JButton typ8;
    private JButton typ9;
    private JButton del;
    private JButton end;

    
    public ControlPanel(DrawPanel dpanel, Tree r) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        typ1 = new JButton ("Rostlina 1");
        typ1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                typ2.setEnabled(false);
                typ3.setEnabled(false);
                typ4.setEnabled(false);
                typ5.setEnabled(false);
                typ6.setEnabled(false);
                typ7.setEnabled(false);
                typ8.setEnabled(false);
                typ9.setEnabled(false);
                gener.setEnabled(true);
                r.typ1();
            }
        });
        this.add(typ1);
        
        
        typ2 = new JButton ("Rostlina 2");
        typ2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                typ1.setEnabled(false);
                typ3.setEnabled(false);
                typ4.setEnabled(false);
                typ5.setEnabled(false);
                typ6.setEnabled(false);
                typ7.setEnabled(false);
                typ8.setEnabled(false);
                typ9.setEnabled(false);
                gener.setEnabled(true);
                r.typ2();
            }
        });
        this.add(typ2);
        
        typ3 = new JButton ("Rostlina 3");
        typ3.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                typ1.setEnabled(false);
                typ2.setEnabled(false);
                typ4.setEnabled(false);
                typ5.setEnabled(false);
                typ6.setEnabled(false);
                typ7.setEnabled(false);
                typ8.setEnabled(false);
                typ9.setEnabled(false);
                gener.setEnabled(true);
                r.typ3();
            }
        });
        this.add(typ3);
        
        typ4 = new JButton ("Rostlina 4");
        typ4.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                typ1.setEnabled(false);
                typ2.setEnabled(false);
                typ3.setEnabled(false);
                typ5.setEnabled(false);
                typ6.setEnabled(false);
                typ7.setEnabled(false);
                typ8.setEnabled(false);
                typ9.setEnabled(false);
                gener.setEnabled(true);
                r.typ4();
            }
        });
        this.add(typ4);
        
        typ5 = new JButton ("Rostlina 5");
        typ5.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                typ1.setEnabled(false);
                typ2.setEnabled(false);
                typ3.setEnabled(false);
                typ4.setEnabled(false);
                typ6.setEnabled(false);
                typ7.setEnabled(false);
                typ8.setEnabled(false);
                typ9.setEnabled(false);
                gener.setEnabled(true);
                r.typ5();
            }
        });
        this.add(typ5);
        
        typ6 = new JButton ("Rostlina 6");
        typ6.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                typ1.setEnabled(false);
                typ2.setEnabled(false);
                typ3.setEnabled(false);
                typ4.setEnabled(false);
                typ5.setEnabled(false);
                typ7.setEnabled(false);
                typ8.setEnabled(false);
                typ9.setEnabled(false);
                gener.setEnabled(true);
                r.typ6();
            }
        });
        this.add(typ6);
        
        typ7 = new JButton ("Rostlina 7");
        typ7.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                typ1.setEnabled(false);
                typ2.setEnabled(false);
                typ3.setEnabled(false);
                typ4.setEnabled(false);
                typ5.setEnabled(false);
                typ6.setEnabled(false);
                typ8.setEnabled(false);
                typ9.setEnabled(false);
                gener.setEnabled(true);
                r.typ7();
            }
        });
        this.add(typ7);
        
        
        typ8 = new JButton ("Rostlina 8");
        typ8.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                typ1.setEnabled(false);
                typ2.setEnabled(false);
                typ3.setEnabled(false);
                typ4.setEnabled(false);
                typ5.setEnabled(false);
                typ6.setEnabled(false);
                typ7.setEnabled(false);
                typ9.setEnabled(false);
                gener.setEnabled(true);
                r.typ8();
            }
        });
        this.add(typ8);
        
        typ9 = new JButton ("Rostlina 9");
        typ9.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                typ1.setEnabled(false);
                typ2.setEnabled(false);
                typ3.setEnabled(false);
                typ4.setEnabled(false);
                typ5.setEnabled(false);
                typ6.setEnabled(false);
                typ7.setEnabled(false);
                typ8.setEnabled(false);
                gener.setEnabled(true);
                r.typ9();
            }
        });
        this.add(typ9);
        
        gener = new JButton ("Generovat");
        gener.setEnabled(false);
        gener.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                dpanel.DrawingLines(r);
                System.out.println(r.axiom);
                r.drawing();
                r.distance = (r.distance * 0.5);
            }
        });
        this.add(gener);
        
        del = new JButton ("Začni znovu");
        del.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                typ1.setEnabled(true);
                typ2.setEnabled(true);
                typ3.setEnabled(true);
                typ4.setEnabled(true);
                typ5.setEnabled(true);
                typ6.setEnabled(true);
                typ7.setEnabled(true);
                typ8.setEnabled(true);
                typ9.setEnabled(true);
                gener.setEnabled(false);
                r.reset();
                dpanel.repaint();
            }
        });
        this.add(del);
        
        end = new JButton ("Konec");
        end.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        this.add(end);
        
    }
}

class Tree {
    protected String ruleF;
    protected String ruleX;
    protected String ruleZ;
    protected String axiom;
    protected double distance;
    protected double Angle;
    
    public Tree (){
        this.ruleF = "";
        this.ruleX = "";
        this.ruleZ = "";
        this.axiom = "";
        this.distance = 0;
        this.Angle = 0;
    }
    
    public void drawing(){
        String axioms = "";
        for (int i = 0;i<axiom.length();i++){ 
            if (axiom.charAt(i) == 'F'){
                axioms = axioms + ruleF;
            }
            else if(axiom.charAt(i) == 'X'){
                axioms = axioms + ruleX;
            }
            else if(axiom.charAt(i) == 'Z'){
                axioms = axioms + ruleZ;
            }
            else {
                axioms = axioms + axiom.charAt(i);
            }
        }
        this.axiom = axioms;
    }
    
    public void typ1(){
        this.ruleF = "FF+[+F-F-F]-[-F+F+F]";
        this.axiom = "F";
        this.distance = 135;
        this.Angle = 22.5;
    }
    
    public void typ2(){
        this.ruleF = "F[+F]F[-F][F]";
        this.axiom = "F";
        this.distance = 250;
        this.Angle = 20;
    }
    
    public void typ3(){
        this.ruleF = "F[+F]F[-F]F";
        this.axiom = "F";
        this.distance = 50;
        this.Angle = 25.7;
    }
    
    public void typ4(){
        this.ruleF = "FF";
        this.ruleX = "F[+X]F[-X]+X";
        this.axiom = "X";
        this.distance = 250;
        this.Angle = 20;
    }
    
    public void typ5(){
        this.ruleF = "FF";
        this.ruleX = "F[+X][-X]FX";
        this.axiom = "X";
        this.distance = 250;
        this.Angle = 25.7;
    }
    
    public void typ6(){
        this.ruleF = "FF";
        this.ruleX = "F-[[X]+X]+F[+FX]-X";
        this.axiom = "X";
        this.distance = 180;
        this.Angle = 22.5;
    }
    
    public void typ7(){
        this.ruleF = "F[+F][-F[-F]F]F[+F][-F]";
        this.axiom = "F";
        this.distance = 200;
        this.Angle = 18;
    }
    
    public void typ8(){
        this.ruleF = "FX[FX[+XF]]";
        this.ruleX = "FF[+XZ++X-F[+ZX]][-X++F-X]";
        this.ruleZ = "[+F-X-F][++ZX]";
        this.axiom = "X";
        this.distance = 130;
        this.Angle = 15;
    }
    
    public void typ9(){
        this.ruleF = "FF[+F][-FF][-F+F]";
        this.axiom = "F";
        this.distance = 130;
        this.Angle = 22;
    }
    
    public void reset(){
        this.ruleF = "";
        this.ruleX = "";
        this.ruleZ = "";
        this.axiom = "";
        this.distance = 0;
        this.Angle = 0;
    }
}


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
        Plants r = new Plants();
        DrawPanel dpanel = new DrawPanel(r);
        // přidání objektu Drawpanel do grafického rozhraní 
        window.add(dpanel,BorderLayout.CENTER);
        
        ControlPanel cpanel = new ControlPanel(dpanel,r);
        ControlPanelUP cpanelUP = new ControlPanelUP (r,dpanel);
        // přidání objektu ControlPanel do grafického rozhraní 
        window.add(cpanelUP,BorderLayout.PAGE_START);
        window.add(cpanel,BorderLayout.LINE_END);
        // nastaví velikost okna tak, aby veškeré komponenty byly vidět
        window.pack();
        // Otevření okna
        window.setVisible(true);
    }

}
// třída pro kreslení
class DrawPanel extends JPanel{
    //vlastnosti
    private List<double[]>lines;
    private double sx;
    private double sy;
    private double x;
    private double y;
    private double rotate;
    private Stack s;
    
    // konstruktor
    public DrawPanel(Plants r){
        // začáteční bod (střed dole)
        sx = this.getWidth()/2;
        sy = this.getHeight();
        x = (Math.sin(Math.toRadians(0)) * r.distance);
        y = (Math.cos(Math.toRadians(0)) * r.distance);
        lines = new LinkedList<>();
        s = new Stack();
        // vytvoření pozadí
        setBorder(BorderFactory.createLineBorder(Color.black));
        setBackground(Color.white); 
    }
    // velikost pozadí
    @Override
    public Dimension getPreferredSize(){
        return new Dimension(500,500);
    }
    // kreslící metoda předdefinovaná
    @Override
    public void paintComponent(Graphics g){
        // konstruktor třídy JPanel
        super.paintComponent(g);   
    }
    // metoda pro resetování hodnot na výchozí hodnoty
    public void reset(Plants r){
        sx = this.getWidth()/2;
        sy = this.getHeight();
        x = (Math.sin(Math.toRadians(0)) * r.distance);
        y = (Math.cos(Math.toRadians(0)) * r.distance);
        s = new Stack();
        rotate = 0;
        lines.clear(); 
    }
    
    public void DrawingLines(Plants r){
        //Vytvoření grafického objektu 
        Graphics g = getGraphics();
        Graphics2D g2 = (Graphics2D) g;
        g.setColor(Color.black);
        // resetování hodnot
        reset(r);
        // projde každý znak ve Stringu
        for (int i =0;i<r.strAxiom.length();i++){
            // pokud je znak "+" kurzor se natočí doprava
            if (r.strAxiom.charAt(i) == '+'){
               rotate = rotate + r.Angle;
               x = (Math.sin(Math.toRadians(rotate)) *  r.distance);
               y = (Math.cos(Math.toRadians(rotate)) *  r.distance);
            }
            // pokud je znak "-" kurzor se natočí doleva
            if (r.strAxiom.charAt(i) == '-'){
                rotate = rotate - r.Angle;
                x = (Math.sin(Math.toRadians(rotate))*  r.distance);
                y = (Math.cos(Math.toRadians(rotate))*  r.distance);
            }
            // uloží jednotlivé hodnoty do zásobníku
            if (r.strAxiom.charAt(i) == '['){
                s.push(sx);
                s.push(sy);
                s.push(rotate);
                s.push(x);
                s.push(y);
            }
            // načte a odstraní jednotlivé hodnoty ze zásobníku
            if (r.strAxiom.charAt(i) == ']'){
                y = (double) s.pop();
                x = (double) s.pop();
                rotate = (double) s.pop();
                sy = (double) s.pop();
                sx = (double) s.pop();
                
            }
            // tvorba pole pro vykreslení linie
            if (r.strAxiom.charAt(i) == 'F'){
                double [] line = new double[4];
                // x,y začátečního bodu
                line[0] = sx;
                line[1] = sy;
                // x,y koncového bodu
                line[2] = sx + x;
                line[3] = sy - y;
                // přidání pole do listu
                lines.add(line);
                //přenastavení startovních bodů na body koncové
                sx = sx + x;
                sy = sy - y;
            }
        }
        // vykreslování jednotlivých linií
        for (double [] line : lines){
            g2.draw(new Line2D.Double(line[0],line[1], line[2], line[3]));
        }
        // odstranění grafického objektu
        g2.dispose();
    }
   
    
}
// třída, která reprezentuje tlačítka
class ControlPanel extends JPanel{
    // vlastnosti objektu
    private JButton gener;
    private JButton del;
    private JButton end;

    // konstruktor
    public ControlPanel(DrawPanel dpanel, Plants r) {
        
         setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

         
        // tlačítko pro generování rostlin po jedné iteraci
        gener = new JButton ("Generate");
        gener.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {

                SwingWorker worker = new Worker();

                worker.execute();
                
                r.strAxiom = r.axiom;
                for(int i = 0;i < r.n;i++){
                    r.generate();
                    r.distance = (r.distance * 0.5);
                }
                dpanel.DrawingLines(r);
            }
        });
        this.add(gener);
        
        // tlačítko, které resetuje všechny parametry a pravidla
        // vyčistí pozadí a umožní si znovu vybrat nový typ rostliny
        del = new JButton ("Start again");
        del.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                r.reset();
                dpanel.repaint();
            }
        });
        this.add(del);
        
        // tlačítko pro ukončení programu
        end = new JButton ("Close");
        end.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        this.add(end);
        
    }
    
    private class Worker extends SwingWorker<Graphics, Graphics>{

        @Override
        protected Graphics doInBackground() throws Exception {
            Plants p = new Plants();
            p.generate();
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }
}

class ControlPanelUP extends JPanel{
    private JComboBox typ;
    private JLabel Text;
    private JLabel Text1;
    private JComboBox Iteration;
    
    public ControlPanelUP(Plants r,DrawPanel dpanel){
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
         
        Text = new JLabel("Choose a plant");
        this.add(Text);
        
        String[] types = {"Plant 1","Plant 2","Plant 3","Plant 4",
            "Plant 5","Plant 6","Plant 7","Plant 8","Plant 9"}; 
        typ = new JComboBox(types);
        typ.setSelectedIndex(0);
        r.typ1(dpanel);
        
        String[] number = {"1","2","3","4","5","6","7","8","9","10"};
        Iteration = new JComboBox(number);
        Iteration.setSelectedIndex(4);
        
        
        Dimension preferredSize = typ.getPreferredSize();
        preferredSize.height = 50;
        typ.setPreferredSize(preferredSize);
  
        typ.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = (String) typ.getSelectedItem();
                switch (s){
                    case "Plant 1":
                        r.typ1(dpanel);
                        Iteration.setSelectedIndex(4);
                        ControlPanelUP.this.repaint();
                        break;
                    case "Plant 2":
                        r.typ2(dpanel);
                        Iteration.setSelectedIndex(5);
                        ControlPanelUP.this.repaint();
                        break;
                    case "Plant 3":
                        r.typ3(dpanel);
                        Iteration.setSelectedIndex(4);
                        ControlPanelUP.this.repaint();
                        break;
                    case "Plant 4":
                        r.typ4(dpanel);
                        Iteration.setSelectedIndex(7);
                        ControlPanelUP.this.repaint();
                        break;
                    case "Plant 5":
                        r.typ5(dpanel);
                        Iteration.setSelectedIndex(6);
                        ControlPanelUP.this.repaint();
                        break;
                    case "Plant 6":
                        r.typ6(dpanel);
                        Iteration.setSelectedIndex(6);
                        ControlPanelUP.this.repaint();
                        break;
                    case "Plant 7":
                        r.typ7(dpanel);
                        Iteration.setSelectedIndex(3);
                        ControlPanelUP.this.repaint();
                        break;
                    case "Plant 8":
                        r.typ8(dpanel);
                        Iteration.setSelectedIndex(3);
                        ControlPanelUP.this.repaint();
                        break;
                    case "Plant 9":
                        r.typ9(dpanel);
                        Iteration.setSelectedIndex(4);
                        ControlPanelUP.this.repaint();
                        break;
                }  
            }
        });
        this.add(typ);
        
        Text1 = new JLabel("Number of recommended iteration");
        this.add(Text1);
        
        Dimension preferredSize1 = Iteration.getPreferredSize();
        preferredSize.height = 50;
        Iteration.setPreferredSize(preferredSize1);
        Iteration.addActionListener(new ActionListener(){ 
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = (String) Iteration.getSelectedItem();
                r.iteration(s);
            }
        });
        this.add(Iteration);
    }
}

// třída reprezentující rostliny 
class Plants {
    //vlastnosti objektu
    protected String ruleF;
    protected String ruleX;
    protected String ruleZ;
    protected String axiom;
    protected double distance;
    protected double Angle;
    protected int n;
    protected String strAxiom;
    
    //konstruktor
    public Plants (){
        this.ruleF = "";
        this.ruleX = "";
        this.ruleZ = "";
        this.axiom = "";
        this.distance = 0;
        this.Angle = 0;
        this.n = 0;
        this.strAxiom = "";
    }
    
    // Metoda, která generuje jednotlivé symboly podle určitých pravidel
    public void generate(){
        
       this.strAxiom = this.axiom;
        for(int u = 0;u < this.n;u++){
            String ax = "";
            for (int i = 0;i<strAxiom.length();i++){ 
                if (strAxiom.charAt(i) == 'F'){
                   ax = ax + ruleF;
                }
                else if(strAxiom.charAt(i) == 'X'){
                    ax = ax + ruleX;
                }
                else if(strAxiom.charAt(i) == 'Z'){
                    ax = ax + ruleZ;
                }
                else {
                    ax = ax + strAxiom.charAt(i);
                }
            }
            strAxiom = ax;
            this.distance = (this.distance * 0.5);
        }
    }
    
    public void iteration(String s){
        this.n = Integer.parseInt(s);
    }
    // metody, které reprezentují parametry a pravidla pro generování
    // jednotlivých rostlin
    public void typ1(DrawPanel dpanel){
        this.ruleF = "FF+[+F-F-F]-[-F+F+F]";
        this.axiom = "F";
        this.distance = dpanel.getHeight()/4;
        this.Angle = 22.5;
        this.n = 5;
    }
    
    public void typ2(DrawPanel dpanel){
        this.ruleF = "F[+F]F[-F][F]";
        this.axiom = "F";
        this.distance = dpanel.getHeight()/2;
        this.Angle = 20;
        this.n = 6;
    }
    
    public void typ3(DrawPanel dpanel){
        this.ruleF = "F[+F]F[-F]F";
        this.axiom = "F";
        this.distance = dpanel.getHeight()/8;
        this.Angle = 25.7;
        this.n = 5;
    }
    
    public void typ4(DrawPanel dpanel){
        this.ruleF = "FF";
        this.ruleX = "F[+X]F[-X]+X";
        this.axiom = "X";
        this.distance = dpanel.getHeight()/2;
        this.Angle = 20;
        this.n = 8;
    }
    
    public void typ5(DrawPanel dpanel){
        this.ruleF = "FF";
        this.ruleX = "F[+X][-X]FX";
        this.axiom = "X";
        this.distance = dpanel.getHeight()/2;
        this.Angle = 25.7;
        this.n = 7;
    }
    
    public void typ6(DrawPanel dpanel){
        this.ruleF = "FF";
        this.ruleX = "F-[[X]+X]+F[+FX]-X";
        this.axiom = "X";
        this.distance = dpanel.getHeight()/3;
        this.Angle = 22.5;
        this.n = 7;
    }
    
    public void typ7(DrawPanel dpanel){
        this.ruleF = "F[+F][-F[-F]F]F[+F][-F]";
        this.axiom = "F";
        this.distance = dpanel.getHeight()/2;
        this.Angle = 18;
        this.n = 4;
    }
    
    public void typ8(DrawPanel dpanel){
        this.ruleF = "FX[FX[+XF]]";
        this.ruleX = "FF[+XZ++X-F[+ZX]][-X++F-X]";
        this.ruleZ = "[+F-X-F][++ZX]";
        this.axiom = "X";
        this.distance = dpanel.getHeight()/3.5;
        this.Angle = 15;
        this.n = 4;
    }
    
    public void typ9(DrawPanel dpanel){
        this.ruleF = "FF[+F][-FF][-F+F]";
        this.axiom = "F";
        this.distance = dpanel.getHeight()/3;
        this.Angle = 22;
        this.n = 5;
    }
    // metoda, která resetuje pravidla a parametry
    public void reset(){
        this.strAxiom = "";
    }
}


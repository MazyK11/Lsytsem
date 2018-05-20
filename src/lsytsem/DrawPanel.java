/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lsytsem;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

/**
 *
 * @author MazyK
 */
//třída reprezentující vykreslování na pozadí
class DrawPanel extends JPanel{
    //vlastnosti
    protected List<double[]>lines;
    protected SwingWorker worker;
    protected Plants reseter;
    protected int IterationNumber;
    protected int typeNumber; 
    private double sx;
    private double sy;
    private double x;
    private double y;
    private double rotate;
    private Stack s;
    
    // konstruktor
    public DrawPanel(){
        //začáteční bod (střed dole)
        sx = this.getWidth()/2;
        sy = this.getHeight();
        // posun na ose x a y
        x = 0;
        y = 0;
        //list linií
        lines = new LinkedList<>();
        // zásobník
        s = new Stack();
        // objekt pro nové vlákno
        worker = new GenerateWorker();
        reseter = new Plants();
        // proměnné získané z comboboxů
        IterationNumber = 5;
        typeNumber = 1; 
        // vytvoření pozadí
        setBorder(BorderFactory.createLineBorder(Color.black));
        setBackground(Color.white); 
        
    }
    // defaultní velikost pozadí
    @Override
    public Dimension getPreferredSize(){
        return new Dimension(500,500);
    }
    
    // kreslící metoda předdefinovaná
    @Override
    public void paintComponent(Graphics g){
        // konstruktor třídy JPanel
        super.paintComponent(g);
        // vytvoření objektu Graphics2D
        Graphics2D g2 = (Graphics2D) g;
        g.setColor(Color.black);
        // vykreslení linií, které se nacházejí v listu
        for (double [] line : lines){
            g2.draw(new Line2D.Double(line[0],line[1], line[2], line[3]));
        }
        // odstranění objektu Graphics2D
        g2.dispose();
    }
    
    // metoda pro resetování hodnot
    public void reset(Plants r){
        sx = this.getWidth()/2;
        sy = this.getHeight();
        x = (Math.sin(Math.toRadians(0)) * r.distance);
        y = (Math.cos(Math.toRadians(0)) * r.distance);
        s = new Stack();
        rotate = 0;
        lines = new LinkedList<>();
        worker = new GenerateWorker();
    }
    
    // metoda, která podle významu jednotlivých znaků vytvoří z vygenerované
    // posloupnosti znaků linie a uloží je do listu
    public void DrawingLines(Plants r){
        // resetování hodnot
        reset(r);
        // projde každý znak ve vygenerované posloupnosti 
        for (int i =0;i<r.axiom.length();i++){
            // pokud je znak "+" kurzor se natočí doprava
            if (r.axiom.charAt(i) == '+'){
               rotate = rotate + r.Angle;
               x = (Math.sin(Math.toRadians(rotate)) *  r.distance);
               y = (Math.cos(Math.toRadians(rotate)) *  r.distance);
            }
            // pokud je znak "-" kurzor se natočí doleva
            if (r.axiom.charAt(i) == '-'){
                rotate = rotate - r.Angle;
                x = (Math.sin(Math.toRadians(rotate))*  r.distance);
                y = (Math.cos(Math.toRadians(rotate))*  r.distance);
            }
            // uloží jednotlivé hodnoty do zásobníku
            if (r.axiom.charAt(i) == '['){
                s.push(sx);
                s.push(sy);
                s.push(rotate);
                s.push(x);
                s.push(y);
            }
            // načte a odstraní jednotlivé hodnoty ze zásobníku
            if (r.axiom.charAt(i) == ']'){
                y = (double) s.pop();
                x = (double) s.pop();
                rotate = (double) s.pop();
                sy = (double) s.pop();
                sx = (double) s.pop();
                
            }
            // tvorba pole pro uložení linie
            if (r.axiom.charAt(i) == 'F'){
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
    }
    
    // třída reprezentující vlákno, které pracuje paralelně s grafickým rozhraním
    public class GenerateWorker extends SwingWorker<DrawPanel,Object>{
        // metoda, která vytvoří objekt rostliny pomocí parametrického konstruktoru
        // následně zavolá metodu pro generování posloupnosti znaků a
        // zkopíruje vlastnosti objektu rostliny do vlastnosti DrawPanelu
        // (využití při resetování plátna)
        // dále volá metodu pro vytvoření linií z vygenerované posloupnosti 
        @Override
        protected DrawPanel doInBackground() throws Exception {
            Plants p = new Plants(DrawPanel.this.typeNumber,DrawPanel.this.IterationNumber);
            p.generate();
            DrawPanel.this.reseter = p;
            DrawPanel.this.DrawingLines(p);
            return DrawPanel.this;
        }
        // metoda se volá po doběhnutí metody doInBackground a spustí předdefinovanou
        // kreslící metodu DrawPanelu
        @Override
         protected void done(){
             DrawPanel.this.repaint();
         }
    } 
}

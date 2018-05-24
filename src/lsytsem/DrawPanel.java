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
// class, which represents drawing on background
class DrawPanel extends JPanel{
    // Properties
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
    
    // constructor
    public DrawPanel(){
        // starting point(bottom middle)
        sx = this.getWidth()/2;
        sy = this.getHeight();
        // shift on axis x and y
        x = 0;
        y = 0;
        //list of lines
        lines = new LinkedList<>();
        // stack
        s = new Stack();
        // object for new thread
        worker = new GenerateWorker();
        reseter = new Plants();
        // variables obtained from combobox and spinner button
        // Variables represents number of iteration and type of plants
        IterationNumber = 5;
        typeNumber = 1; 
        // creation of background
        setBorder(BorderFactory.createLineBorder(Color.black));
        setBackground(Color.white); 
        
    }
    // default size of background
    @Override
    public Dimension getPreferredSize(){
        return new Dimension(500,500);
    }
    
    // Drawing method
    @Override
    public void paintComponent(Graphics g){
        // constructor of class JPanel
        super.paintComponent(g);
        // creation of Graphics2D object
        Graphics2D g2 = (Graphics2D) g;
        g.setColor(Color.black);
        // drawing the lines, which are in the list
        for (double [] line : lines){
            g2.draw(new Line2D.Double(line[0],line[1], line[2], line[3]));
        }
        // delete of Graphics2D object
        g2.dispose();
    }
    
    // Method for resetting the values
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
    
    // Method, which takes generated individual symbols and create lines 
    // according to the meaning of that symbols. Lines are saved in the list.
    
    public void DrawingLines(Plants r){
        // resetting of the values
        reset(r);
        // taking every symbol in generated sequence
        for (int i =0;i<r.axiom.length();i++){
            switch (r.axiom.charAt(i)) {
                // if the symbol is "+", cursor will rotate to the right
                case '+':
                    rotate = rotate + r.Angle;
                    x = (Math.sin(Math.toRadians(rotate)) *  r.distance);
                    y = (Math.cos(Math.toRadians(rotate)) *  r.distance);
                    break;
                // if the symbol is "-", cursor will rotate to the left
                case '-':
                    rotate = rotate - r.Angle;
                    x = (Math.sin(Math.toRadians(rotate))*  r.distance);
                    y = (Math.cos(Math.toRadians(rotate))*  r.distance);
                    break;
                // saving values to the stack
                case '[':
                    s.push(sx);
                    s.push(sy);
                    s.push(rotate);
                    s.push(x);
                    s.push(y);
                    break;
                // loading and deleting values from the stack 
                case ']':
                    y = (double) s.pop();
                    x = (double) s.pop();
                    rotate = (double) s.pop();
                    sy = (double) s.pop();
                    sx = (double) s.pop();
                    break;
                // creating array for start and end points of line
                case 'F':
                    double [] line = new double[4];
                    // x,y of starting point
                    line[0] = sx;
                    line[1] = sy;
                    // x,y of ending point
                    line[2] = sx + x;
                    line[3] = sy - y;
                    // adding array to the list
                    lines.add(line);
                    //creating new start point on the place of the end point
                    sx = sx + x;
                    sy = sy - y;
                    break;
                default:
                    break;
            }
        }
    }
    // class, which represents thread, which is working parallel with GUI
    public class GenerateWorker extends SwingWorker<DrawPanel,Object>{
        // Method, which creates Plants object using parametric constructor.
        // After that the method for generating symbol sequence is called and  
        // properties of Plants object are copied to DrawPanel properties 
        // (this is used for background resetting).
        // At the end method, which creates lines from sequence of symbols is 
        // called 
        @Override
        protected DrawPanel doInBackground() throws Exception {
            Plants p = new Plants(DrawPanel.this.typeNumber,DrawPanel.this.IterationNumber);
            p.generate();
            DrawPanel.this.reseter = p;
            DrawPanel.this.DrawingLines(p);
            return DrawPanel.this;
        }
        // This method is called after the method doInBackground is finished. 
        // This method will call drawing method from DrawPanel
        @Override
         protected void done(){
             DrawPanel.this.repaint();
         }
    } 
}

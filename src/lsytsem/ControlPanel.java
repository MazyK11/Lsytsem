/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lsytsem;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author MazyK
 */
// class, which represents buttons on the right side of the GUI 
class ControlPanel extends JPanel{
    // Properties
    private JButton gener;
    private JButton stop;
    private JButton end;

    // constructor
    public ControlPanel(DrawPanel dpanel) {
        // BoxLayout
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //button for plants generation
        gener = new JButton ("Generate");
        gener.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                // new thread will start after the button is pressed
                dpanel.worker.execute(); 
                stop.setEnabled(true);
            }
        });
        this.add(gener);
        // size of the button
        gener.setMinimumSize(new Dimension(350, 30));
        gener.setMaximumSize(new Dimension(350, 30));
        
        // button for stopping the thread and reset DrawPanel
        stop = new JButton ("Stop calculating process and clear out the background");
        stop.setEnabled(false);
        stop.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                dpanel.worker.cancel(true);
                dpanel.reset(dpanel.reseter);
                stop.setEnabled(false);
            }
        });
        this.add(stop);
        // size of the button
        stop.setMinimumSize(new Dimension(350, 30));
        stop.setMaximumSize(new Dimension(350, 30));
        
        // gap between buttons
        this.add(Box.createRigidArea(new Dimension(50,50)));
        // button for closing the program
        end = new JButton ("Close");
        end.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        this.add(end);
        // size of the button
        end.setMinimumSize(new Dimension(350, 30));
        end.setMaximumSize(new Dimension(350, 30));
    }
    
}

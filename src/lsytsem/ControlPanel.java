/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lsytsem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author MazyK
 */
// třída, která reprezentuje tlačítka
class ControlPanel extends JPanel{
    // vlastnosti objektu
    private JButton gener;
    private JButton stop;
    private JButton end;

    // konstruktor
    public ControlPanel(DrawPanel dpanel) {    
         setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // tlačítko pro generování rostlin po jedné iteraci
        gener = new JButton ("Generate");
        gener.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                dpanel.worker.execute(); 
                stop.setEnabled(true);
            }
        });
        this.add(gener);
 
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
    
}

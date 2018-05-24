/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lsytsem;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;


/**
 *
 * @author MazyK
 */
// class, which represents buttons in the upper area of the GUI 
class ControlPanelUP extends JPanel{
    // Properties
    private JComboBox type;
    private JLabel Text;
    private JLabel Text1;
    private JSpinner Iteration;
    
    // constructor
    public ControlPanelUP(DrawPanel dpanel){
        // BoxLayout
        setLayout(new FlowLayout());
        
        // combobox label
        Text = new JLabel("Choose a plant");
        this.add(Text);
        
        // combobox options for plants selection
        String[] types = {"Plant 1","Plant 2","Plant 3","Plant 4",
            "Plant 5","Plant 6","Plant 7","Plant 8","Plant 9"}; 
        
        type = new JComboBox(types);
        type.setSelectedIndex(0);
        
        
        type.addActionListener(new ActionListener(){
            // When some option is chosen, IterationNumber and typeNumber,
            // which represents number of iteration and type of plants are set 
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = (String) type.getSelectedItem();
                switch (s){
                    case "Plant 1":
                        dpanel.typeNumber = 1;
                        dpanel.IterationNumber = 5;
                        Iteration.setValue(5);
                        break;
                    case "Plant 2":
                        dpanel.typeNumber = 2;
                        dpanel.IterationNumber = 6;
                        Iteration.setValue(6);
                        break;
                    case "Plant 3":
                        dpanel.typeNumber = 3;
                        dpanel.IterationNumber = 5;
                        Iteration.setValue(5);
                        break;
                    case "Plant 4":
                        dpanel.typeNumber = 4;
                        dpanel.IterationNumber = 8;
                        Iteration.setValue(8);
                        break;
                    case "Plant 5":
                        dpanel.typeNumber = 5;
                        dpanel.IterationNumber = 7;
                        Iteration.setValue(7);
                        break;
                    case "Plant 6":
                        dpanel.typeNumber = 6;
                        dpanel.IterationNumber = 7;
                        Iteration.setValue(7);
                        break;
                    case "Plant 7":
                        dpanel.typeNumber = 7;
                        dpanel.IterationNumber = 4;
                        Iteration.setValue(4);
                        break;
                    case "Plant 8":
                        dpanel.IterationNumber = 4;
                        dpanel.typeNumber = 8;
                        Iteration.setValue(4);
                        break;
                    case "Plant 9":
                        dpanel.IterationNumber = 4;
                        dpanel.typeNumber = 9;
                        Iteration.setValue(5);
                        break;
                }  
            }
        });
        this.add(type);
        
        // label for spinner button
        Text1 = new JLabel("Number of recommended iterations");
        this.add(Text1);
        // max and min values of iteration, step 1
        SpinnerModel model = new SpinnerNumberModel(dpanel.IterationNumber, 1, 10, 1);
        Iteration = new JSpinner(model);
        // IterationNumber is set when the number is changed
        Iteration.addChangeListener(new javax.swing.event.ChangeListener(){ 
            @Override
            public void stateChanged(javax.swing.event.ChangeEvent e) {
                dpanel.IterationNumber =  (Integer) model.getValue();
            }
        
        });
        this.add(Iteration);
       
    }

}

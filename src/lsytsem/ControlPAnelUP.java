/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lsytsem;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author MazyK
 */
// třída, která reprezentuje tlačítka v horní oblasti grafického rozhraní
class ControlPanelUP extends JPanel{
    // vlastnosti objektu
    private JComboBox type;
    private JLabel Text;
    private JLabel Text1;
    private JComboBox Iteration;
    
    // konstruktor
    public ControlPanelUP(DrawPanel dpanel){
        // BoxLayout
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        // Popis prvního comboboxu
        Text = new JLabel("Choose a plant");
        this.add(Text);
        
        // možnosti comboboxu pro výběr rostliny
        String[] types = {"Plant 1","Plant 2","Plant 3","Plant 4",
            "Plant 5","Plant 6","Plant 7","Plant 8","Plant 9"}; 
        type = new JComboBox(types);
        type.setSelectedIndex(0);
        
        // možnosti comboboxu pro výběr počtu iterací
        String[] number = {"1","2","3","4","5","6","7","8","9","10"};
        Iteration = new JComboBox(number);
        Iteration.setSelectedIndex(4);
        
        // preferovaná velikost comboboxu (pro lepší vizuální stránku)
        Dimension preferredSize = type.getPreferredSize();
        preferredSize.height = 50;
        type.setPreferredSize(preferredSize);
        
        type.addActionListener(new ActionListener(){
            // po vybrání jedné z možností se překreslí combobox znázorňující
            // počet iterací a nastaví se doporučená hodnota
            // zároveň se nastaví vlastnosti DrawPanelu typeNumber, jenž reprezentuje
            // typ rostliny a IterationNumber, jenž reprezentuje počet iterací
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = (String) type.getSelectedItem();
                switch (s){
                    case "Plant 1":
                        dpanel.typeNumber = 1;
                        dpanel.IterationNumber = 5;
                        Iteration.setSelectedIndex(4);
                        ControlPanelUP.this.repaint();
                        break;
                    case "Plant 2":
                        dpanel.typeNumber = 2;
                        dpanel.IterationNumber = 6;
                        Iteration.setSelectedIndex(5);
                        ControlPanelUP.this.repaint();
                        break;
                    case "Plant 3":
                        dpanel.typeNumber = 3;
                        dpanel.IterationNumber = 5;
                        Iteration.setSelectedIndex(4);
                        ControlPanelUP.this.repaint();
                        break;
                    case "Plant 4":
                        dpanel.typeNumber = 4;
                        dpanel.IterationNumber = 8;
                        Iteration.setSelectedIndex(7);
                        ControlPanelUP.this.repaint();
                        break;
                    case "Plant 5":
                        dpanel.typeNumber = 5;
                        dpanel.IterationNumber = 7;
                        Iteration.setSelectedIndex(6);
                        ControlPanelUP.this.repaint();
                        break;
                    case "Plant 6":
                        dpanel.typeNumber = 6;
                        dpanel.IterationNumber = 7;
                        Iteration.setSelectedIndex(6);
                        ControlPanelUP.this.repaint();
                        break;
                    case "Plant 7":
                        dpanel.typeNumber = 7;
                        dpanel.IterationNumber = 4;
                        Iteration.setSelectedIndex(3);
                        ControlPanelUP.this.repaint();
                        break;
                    case "Plant 8":
                        dpanel.IterationNumber = 4;
                        dpanel.typeNumber = 8;
                        Iteration.setSelectedIndex(3);
                        ControlPanelUP.this.repaint();
                        break;
                    case "Plant 9":
                        dpanel.IterationNumber = 4;
                        dpanel.typeNumber = 9;
                        Iteration.setSelectedIndex(4);
                        ControlPanelUP.this.repaint();
                        break;
                }  
            }
        });
        this.add(type);
        
        // Popis druhého comboboxu
        Text1 = new JLabel("Number of recommended iterations");
        this.add(Text1);
        
        // preferovaná velikost comboboxu (pro lepší vizuální stránku)
        Dimension preferredSize1 = Iteration.getPreferredSize();
        preferredSize.height = 50;
        Iteration.setPreferredSize(preferredSize1);
        Iteration.addActionListener(new ActionListener(){ 
            // po výběru jiného, než doporučeného počtu iterací se přenastaví
            // počet iterací
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = (String) Iteration.getSelectedItem();
                dpanel.IterationNumber = Integer.parseInt(s);
            }
        });
        this.add(Iteration);
    }
}

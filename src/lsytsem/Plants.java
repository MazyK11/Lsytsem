/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lsytsem;

/**
 *
 * @author MazyK
 */
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

    
    //konstruktor
    public Plants (){
        this.ruleF = "";
        this.ruleX = "";
        this.ruleZ = "";
        this.axiom = "";
        this.distance = 0;
        this.Angle = 0;
        this.n = 0;
    }
    
    public Plants(DrawPanel dpanel){
        switch (dpanel.typeNumber) {
            case 1:
                this.ruleF = "FF+[+F-F-F]-[-F+F+F]";
                this.axiom = "F";
                this.distance = dpanel.getHeight()/4;
                this.Angle = 22.5;
                this.n = 5;
                break;
            case 2:
                this.ruleF = "F[+F]F[-F][F]";
                this.axiom = "F";
                this.distance = dpanel.getHeight()/2;
                this.Angle = 20;
                this.n = 6;
                break;
            case 3:
                this.ruleF = "F[+F]F[-F]F";
                this.axiom = "F";
                this.distance = dpanel.getHeight()/8;
                this.Angle = 25.7;
                this.n = 5;
                break;
            case 4:
                this.ruleF = "FF";
                this.ruleX = "F[+X]F[-X]+X";
                this.axiom = "X";
                this.distance = dpanel.getHeight()/2;
                this.Angle = 20;
                this.n = 8;
                break;
            case 5:
                this.ruleF = "FF";
                this.ruleX = "F[+X][-X]FX";
                this.axiom = "X";
                this.distance = dpanel.getHeight()/2;
                this.Angle = 25.7;
                this.n = 7;
                break;
            case 6:
                this.ruleF = "FF";
                this.ruleX = "F-[[X]+X]+F[+FX]-X";
                this.axiom = "X";
                this.distance = dpanel.getHeight()/3;
                this.Angle = 22.5;
                this.n = 7;
                break;
            case 7:
                this.ruleF = "F[+F][-F[-F]F]F[+F][-F]";
                this.axiom = "F";
                this.distance = dpanel.getHeight()/2;
                this.Angle = 18;
                this.n = 4;
                break;
            case 8:
                this.ruleF = "FX[FX[+XF]]";
                this.ruleX = "FF[+XZ++X-F[+ZX]][-X++F-X]";
                this.ruleZ = "[+F-X-F][++ZX]";
                this.axiom = "X";
                this.distance = dpanel.getHeight()/3.5;
                this.Angle = 15;
                this.n = 4;
                break;
            case 9:
                this.ruleF = "FF[+F][-FF][-F+F]";
                this.axiom = "F";
                this.distance = dpanel.getHeight()/3;
                this.Angle = 22;
                this.n = 5;
                break;
            default:
                this.ruleF = "FF+[+F-F-F]-[-F+F+F]";
                this.axiom = "F";
                this.distance = dpanel.getHeight()/4;
                this.Angle = 22.5;
                this.n = 5;
                break;
        }

        this.n = dpanel.IterationNumber;
    } 
    
    // Metoda, která generuje jednotlivé symboly podle určitých pravidel
    public void generate(){
        for(int u = 0;u < this.n;u++){
            String ax = "";
            for (int i = 0;i<axiom.length();i++){ 
                if (axiom.charAt(i) == 'F'){
                   ax = ax + ruleF;
                }
                else if(axiom.charAt(i) == 'X'){
                    ax = ax + ruleX;
                }
                else if(axiom.charAt(i) == 'Z'){
                    ax = ax + ruleZ;
                }
                else {
                    ax = ax + axiom.charAt(i);
                }
            }
            axiom = ax;
            this.distance = (this.distance * 0.5);
        }
    }
    public void restart(){
        this.ruleF = "";
        this.ruleX = "";
        this.ruleZ = "";
        this.axiom = "";
        this.distance = 0;
        this.Angle = 0;
        this.n = 0;
    }
}


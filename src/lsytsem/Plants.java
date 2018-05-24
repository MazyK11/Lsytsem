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
// class, which represents plants
class Plants {
    // Properties
    protected String ruleF;
    protected String ruleX;
    protected String ruleZ;
    protected String axiom;
    protected double distance;
    protected double Angle;
    protected int n;
    
    // constructor
    public Plants (){
        this.ruleF = "";
        this.ruleX = "";
        this.ruleZ = "";
        this.axiom = "";
        this.distance = 0;
        this.Angle = 0;
        this.n = 0;
    }
    // parametric constructor
    public Plants(int type,int iteration){
        // calling the method according to the chosen type 
        // Method will set the properties
        switch (type) {
            case 1:
                typ1();
                break;
            case 2:
                typ2();
                break;
            case 3:
                typ3();
                break;
            case 4:
                typ4();
                break;
            case 5:
                typ5();
                break;
            case 6:
                typ6();
                break;
            case 7:
                typ7();
                break;
            case 8:
                typ8();
                break;
            case 9:
                typ9();
                break;
            default:
                typ1();
                break;
        }
        // if user changed number of iteration, it will be set here
        if (this.n != iteration){        
            this.n = iteration;
        }
    } 
    //Method, which generate individual symbols according to rules. Number of 
    // generations depend on number of iteration. Distance of lines is reduce
    // by half after every iteration 
    
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
    // Methods, which set properties of object
    // every methods represents one type of plants
    public void typ1(){
        this.ruleF = "FF+[+F-F-F]-[-F+F+F]";
        this.axiom = "F";
        this.distance = 135;
        this.Angle = 22.5;
        this.n = 5;
    }
    
    public void typ2(){
        this.ruleF = "F[+F]F[-F][F]";
        this.axiom = "F";
        this.distance = 250;
        this.Angle = 20;
        this.n = 6;
    }
    
    public void typ3(){
        this.ruleF = "F[+F]F[-F]F";
        this.axiom = "F";
        this.distance = 50;
        this.Angle = 25.7;
        this.n = 5;
    }
    
    public void typ4(){
        this.ruleF = "FF";
        this.ruleX = "F[+X]F[-X]+X";
        this.axiom = "X";
        this.distance = 250;
        this.Angle = 20;
        this.n = 8;
    }
    
    public void typ5(){
        this.ruleF = "FF";
        this.ruleX = "F[+X][-X]FX";
        this.axiom = "X";
        this.distance = 250;
        this.Angle = 25.7;
        this.n = 7;
    }
    
    public void typ6(){
        this.ruleF = "FF";
        this.ruleX = "F-[[X]+X]+F[+FX]-X";
        this.axiom = "X";
        this.distance = 180;
        this.Angle = 22.5;
        this.n = 7;
    }
    
    public void typ7(){
        this.ruleF = "F[+F][-F[-F]F]F[+F][-F]";
        this.axiom = "F";
        this.distance = 200;
        this.Angle = 18;
        this.n = 4;
    }
    
    public void typ8(){
        this.ruleF = "FX[FX[+XF]]";
        this.ruleX = "FF[+XZ++X-F[+ZX]][-X++F-X]";
        this.ruleZ = "[+F-X-F][++ZX]";
        this.axiom = "X";
        this.distance = 130;
        this.Angle = 15;
        this.n = 4;
    }
    
    public void typ9(){
        this.ruleF = "FF[+F][-FF][-F+F]";
        this.axiom = "F";
        this.distance = 130;
        this.Angle = 22;
        this.n = 5;
    }
}


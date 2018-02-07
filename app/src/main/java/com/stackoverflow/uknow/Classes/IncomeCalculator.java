package com.stackoverflow.uknow.Classes;

public class IncomeCalculator {

    double cg,test_res,M,P,x,y;
    String s;
    String branch;

    public IncomeCalculator(double cg, double test_res, String s, String branch) {
        this.cg = cg;
        this.test_res = test_res;
        this.s = s;
        this.branch = branch;

        M=0;
        P=0;
        x=cg/10;
        y=test_res/100;

        if(s.equals("IITs") || s.equals("NITs") || s.equals("IIITs") || s.equals("BITS Pilani")){
            if(cg>=8.5 && test_res>=80){
                M=70;
                P=(Math.pow(x+y,2)*M)/4;
            }
            if(cg>=7 && cg<8.5 && test_res>=80){
                M=47;
                P=(Math.pow(x+y,2)*M)/4;
            }
            if(cg>=5 && cg<7 && test_res>=80){
                M=27;
                P=(Math.pow(x+y,2)*M)/4;
            }
            if(cg>=8.5 && test_res>=50 && test_res<80){
                M=70;
                P=(Math.pow(x+y,2)*M)/4;
            }
            if(cg>=7 && cg<8.5 && test_res>=50 && test_res<80){
                M=32;
                P=(Math.pow(x+y,2)*M)/4;
            }
            if(cg>=5 && cg<7 && test_res>=50 && test_res<80){
                M=20;
                P=(Math.pow(x+y,2)*M)/4;
            }
            if(cg>=8.5 && test_res>=35 && test_res<50){
                M=55;
                P=(Math.pow(x+y,2)*M)/4;
            }
            if(cg>=7 && cg<8.5 && test_res>=35 && test_res<50){
                M=34;
                P=(Math.pow(x+y,2)*M)/4;
            }
            if(cg>=5 && cg<7 && test_res>=35 && test_res<50){
                M=28;
                P=(Math.pow(x+y,2)*M)/4;
            }
            if (s.equals("IITs")){
                if (branch.equals("CSE"))
                    P*=1.0;
                else if (branch.equals("ECE") || branch.equals("EE"))
                    P*=0.9;
                else
                    P*=0.8;
            }
            else {
                P*= 0.8;
                if (branch.equals("CSE"))
                    P*=1.0;
                else if (branch.equals("ECE") || branch.equals("EE"))
                    P*=0.9;
                else
                    P*=0.8;
            }
        }
        else {
            if(cg>=8.5 && test_res>=80){
                M=30;
                P=(Math.pow(x+y,2)*M)/4;
            }
            if(cg>=7 && cg<8.5 && test_res>=80){
                M=20;
                P=(Math.pow(x+y,2)*M)/4;
            }
            if(cg>=5 && cg<7 && test_res>=80){
                M=12;
                P=(Math.pow(x+y,2)*M)/4;
            }
            if(cg>=8.5 && test_res>=50 && test_res<80){
                M=30;
                P=(Math.pow(x+y,2)*M)/4;
            }
            if(cg>=7 && cg<8.5 && test_res>=50 && test_res<80){
                M=12;
                P=(Math.pow(x+y,2)*M)/4;
            }
            if(cg>=5 && cg<7 && test_res>=50 && test_res<80){
                M=9;
                P=(Math.pow(x+y,2)*M)/4;
            }
            if(cg>=8.5 && test_res>=35 && test_res<50){
                M=30;
                P=(Math.pow(x+y,2)*M)/4;
            }
            if(cg>=7 && cg<8.5 && test_res>=35 && test_res<50){
                M=11;
                P=(Math.pow(x+y,2)*M)/4;
            }
            if(cg>=5 && cg<7 && test_res>=35 && test_res<50){
                M=5.5;
                P=(Math.pow(x+y,2)*M)/4;
            }
        }
        System.out.println(P);
    }

    public double getP() {
        return P;
    }
}

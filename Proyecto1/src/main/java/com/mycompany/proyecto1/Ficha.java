package com.mycompany.proyecto1;

public abstract class  Ficha {
    private char color; //ponemos la incial del color para identificarlo
    private int num;
    private int index;

    public Ficha(){
        setColor(' ');
        setNum(0);
        setIndex(0);
    }
    public Ficha(int num,char color){
        setColor(color);
        setNum(num);
        setIndex(0);
    }

    public void setColor(char color) {
        this.color = color;
    }
    public void setNum(int num) {
        this.num = num;
    }

    public char getColor() {
        return color;
    }
    public int getNum() {
        return num;
    }

    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }
    
    
}


package com.mycompany.proyecto1;

public class Ficha {
    private char color; //ponemos la incial del color para identificarlo
    private int num;

    Ficha(int num,char color){
        setColor(color);
        setNum(num);
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
}


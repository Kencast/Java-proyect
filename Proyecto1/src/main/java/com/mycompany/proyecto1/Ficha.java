package com.mycompany.proyecto1;

public abstract class  Ficha {
    private int color; //ponemos la incial del color para identificarlo
    private int num;
    private int index;
    private int turno;

    public Ficha(){
        setTurno(0);
        setColor(4);
        setNum(0);
        setIndex(0);
    }
    public Ficha(int num,int color){
        setColor(color);
        setNum(num);
        setIndex(0);
        setTurno(0);
    }

    public void setColor(int color) {
        this.color = color;
    }
    public void setNum(int num) {
        this.num = num;
    }

    public int getColor() {
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

    public void setTurno(int turno){
        this.turno = turno;
    }

    public int getTurno(){
        return turno;
    }

}


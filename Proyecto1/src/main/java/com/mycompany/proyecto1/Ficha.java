package com.mycompany.proyecto1;

public abstract class  Ficha {
    private int color; // Se usa un sistema del 0 al 3, para identificar los colores
    private int num;
    private int index;

    public Ficha(){
        //Inicializa el objeto ficha
        setColor(4);
        setNum(0);
        settIndex(0);
    }
    public Ficha(int num,int color){
        //Inicializa el objeto ficha con un color y numero
        setColor(color);
        setNum(num);
        settIndex(0);
    }

    public void setColor(int color) {
        //Modifica el color
        this.color = color;
    }
    public void setNum(int num) {
        //Modifica el numero
        this.num = num;
    }

    public int getColor() {
        //Retorna el color
        return color;
    }
    public int getNum() {
        //Retorna el numero
        return num;
    }

    public int getIndex() {
        //Retorna el indice
        return index;
    }
    public void settIndex(int ind) {
        //Modifica el indice
        this.index = ind;
    }

}


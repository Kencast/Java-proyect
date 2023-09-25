package com.mycompany.proyecto1;
public class Comodin extends Ficha{
    private int valor;
    
    public Comodin(int num, char color){
        super(num, color);
        setValor(30);
    }

    
    
    
    public int getValor() {
        return valor;
    }
    public void setValor(int valor) {
        this.valor = valor;
    }
    
}

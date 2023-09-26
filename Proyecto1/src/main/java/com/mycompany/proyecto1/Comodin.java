package com.mycompany.proyecto1;
public class Comodin extends Ficha{
    private int valor;
    
    public Comodin(){
        super();
        setValor(30);
    }
    
    public Comodin(int num, char color){
        super(num, color);
        setValor(30);
    }
    
    public int getValor() {
        return valor;
    }
    private void setValor(int valor) {
        this.valor = valor;
    }
    
}

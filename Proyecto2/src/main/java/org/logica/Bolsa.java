package org.logica;
import java.util.*;
import java.util.Random;

/**
*Clase para guarda la fichas
*/
public class Bolsa {
    private ArrayList<Ficha> conjuntoFichas;

    public Bolsa(){
        setBolsa();
    }

    private void setBolsa(){
        cicloGeneracional(12,'a',1);
        cicloGeneracional(12,'e',1);
        cicloGeneracional(9,'o',1);
        cicloGeneracional(6,'i',1);
        cicloGeneracional(6,'s',1);
        cicloGeneracional(5,'n',1);
        cicloGeneracional(4,'l',1);
        cicloGeneracional(5,'r',1);
        cicloGeneracional(5,'u',1);
        cicloGeneracional(4,'t',1);
        cicloGeneracional(5,'d',2);
        cicloGeneracional(2,'g',2);
        cicloGeneracional(4,'c',3);
        cicloGeneracional(2,'b',3);
        cicloGeneracional(2,'m',3);
        cicloGeneracional(2,'p',3);
        cicloGeneracional(2,'h',4);
        cicloGeneracional(1,'f',4);
        cicloGeneracional(1,'v',4);
        cicloGeneracional(1,'y',4);
        cicloGeneracional(2,'q',5);
        cicloGeneracional(1,'j',8);
        cicloGeneracional(1,'k',8);
        cicloGeneracional(1,'ñ',8);
        cicloGeneracional(1,'w',8);
        cicloGeneracional(1,'x',8);
        cicloGeneracional(1,'z',10);
        insertarFicha(new Comodin());
        insertarFicha(new Comodin());
    }
    
    
    public void insertarFicha(Ficha f){
        conjuntoFichas.add(f);
    }

    private void cicloGeneracional(int c, char a, int p){
        for(int i = 0; i < c; i++){
            insertarFicha(new FichaNormal(p,a));
        }
    }

    public Ficha tomarFicha(){
        Random rand = new Random();
        int n = rand.nextInt(0,tamanoBolsa());
        Ficha f = conjuntoFichas.get(n);
        sacarFicha(n);
        return f;    }

    private void sacarFicha(int n){
        conjuntoFichas.remove(n);
    }

    public int tamanoBolsa(){
        return conjuntoFichas.size();
    }

    public Ficha sacarSinEliminar(){
        Random rand = new Random();
        int n = rand.nextInt(0,tamanoBolsa());
        Ficha f = conjuntoFichas.get(n);
        return f;
    }
}

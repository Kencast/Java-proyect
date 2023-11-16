package org.logica;
import java.util.*;
import java.util.Random;

/**
* @Description Clase que generar la bolsa y las fichas
*/
public class Bolsa {
    private ArrayList<Ficha> conjuntoFichas;

    /**
    * @Description Constructor
    */
    public Bolsa(){
        setBolsa();
    }

    /**
    * @Description Genera todas las distintas fichas y las insertar en un arraylist
    */
    private void setBolsa(){
        conjuntoFichas = new ArrayList<>();
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
    
    /**
    * @Description Inserta una ficha en la bolsa
    * @param f Ficha a insertar
    */
    public void insertarFicha(Ficha f){
        conjuntoFichas.add(f);
    }

    /**
    * @Description Generar un cantidad de fichas de un caracter especfico con un valor especifico
    * @param c cantidad de fichas a crear
    * @param a caracter de la ficha
    * @param p valor de la ficha
    */
    private void cicloGeneracional(int c, char a, int p){
        for(int i = 0; i < c; i++){
            insertarFicha(new FichaNormal(p,a));
        }
    }

    /**
    * @Description Se obtiene una ficha random de la bolsa y se elimina de la bolsa
    * @return una ficha
    */
    public Ficha tomarFicha(){
        Random rand = new Random();
        int n = rand.nextInt(0,tamanoBolsa());
        Ficha f = conjuntoFichas.get(n);
        sacarFicha(n);
        return f;
    }

    /**
    * @Description Elimina una ficha en una posicion especifica
    * @param n indice a eliminar
    */
    private void sacarFicha(int n){
        conjuntoFichas.remove(n);
    }

    /**
    * @Description Tamano de la bolsa
    * @return entero
    */
    public int tamanoBolsa(){
        return conjuntoFichas.size();
    }

    /**
    * @Description Devuelve una ficha random de la bolsa
    * @return Ficha random
    */
    public Ficha sacarSinEliminar(){
        Random rand = new Random();
        int n = rand.nextInt(0,tamanoBolsa());
        Ficha f = conjuntoFichas.get(n);
        return f;
    }
}

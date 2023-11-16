package org.logica;

/**
* @Description Clase para represenar una ficha
*/
public abstract class Ficha {
    private int valor;
    private char letra;
    private int indice;
    private int turno;

    /**
    * @Description Constructor
    */
    public Ficha(int v, char l){
        //se inicializa Ficha
        setValor(v);
        setLetra(l);
    }

    /**
    * @Description cambia el valor del turno
    * @param t turno;
    */
    public void setTurno(int t){
        //asigna el turno correspondiente
        turno=t;
    }

    /**
    * @Description retorna el turno
    * @return entero del turno
    */
    public int getTurno(){
        return turno;
    }

    /**
    * @Description cambia el valor
    * @param n nuevo valor
    */
    public void setValor(int n){
        //se asigna un numero a la variable 'valor'
        valor=n;
    }

    /**
    * @Description retorna el valor
    * @return entero con el valor
    */
    public int getValor(){
        //retorna el valor de la ficha
        return valor;
    }

    /**
    * @Description cambia la letra
    * @param l nueva letra
    */
    public void setLetra(char l){
        //se asigna una letra a la varible 'letra'
        letra=l;
    }

    /**
    * @Description retorna la letra
    * @return letra
    */
    public char getLetra(){
        //retorna la varibale letra
        return letra;
    }

    /**
    * @Description cambia el indice
    * @param i nuevo indice
    */
    public void setIndice(int i){
        //asigna el indice donde se encuentra la ficha
        indice=i;
    }

    /**
    * @Description retorna el indice
    * @return entero con el indice
    */
    public int getIndice(){
        //retorna el indice donde se encuentra la ficha 
        return indice;
    }

}

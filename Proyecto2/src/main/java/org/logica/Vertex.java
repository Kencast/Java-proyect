package org.logica;
import java.util.*;
/**
* @Descrption Clase que representa el nodo del Trie
*/
public class Vertex {
    private char letra;
    private boolean exist;
    private ArrayList<Vertex> hijos;

    /**
    * @Description Constructor
    * @param n letra
    */
    public Vertex(char n){
        setLetra(n);
        exist = false;
        hijos = new ArrayList<>(Collections.nCopies(27,null));
    }

    /**
    * @Description retorna la letra
    * @return char con la letra
    */
    public char getLetra(){
        return this.letra;
    }

    /**
    * @Description cambia la letra
    * @param a nueva letra
    */
    public void setLetra(char a){
        this.letra = a;
    }

    /**
    * @Description cambia booleano para saber si existe
    * @param f nuevo booleano
    */
    public void setExist(boolean f){
        this.exist = f;
    }

    /**
    * @Description retorna el valor de si existe
    * @return booleano para saber si existe
    */
    public boolean getExist(){
        return this.exist;
    }

    /**
    * @Description retorna el nodo hijo
    * @param a indice del nodo
    */
    public Vertex accederHijos(int a){
        return hijos.get(a);
    }

    /**
    * @Description crea un nodo hijo
    * @param n indice a crear
    * @param a char para inicilizar
    */
    public void setPosHijos(int n, char a){
        hijos.set(n,new Vertex(a));
    }
}

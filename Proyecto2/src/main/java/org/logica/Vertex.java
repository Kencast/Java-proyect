package org.logica;
import java.util.*;
/**
* Nodo del Trie
*/
public class Vertex {
    private char letra;
    private boolean exist;
    private ArrayList<Vertex> hijos;

    /**
    * Constructor
    *
    */
    public Vertex(char n){
            setLetra(n);
        exist = false;
        hijos = new ArrayList<>(Collections.nCopies(27,null));
    }
    
    public char getLetra(){
        return this.letra;
    }

    public void setLetra(char a){
        this.letra = a;
    }

    public void setExist(boolean f){
        this.exist = f;
    }

    public boolean getExist(){
        return this.exist;
    }

    public Vertex accederHijos(int a){
        return hijos.get(a);
    }
    
    public void setPosHijos(int n, char a){
        hijos.set(n,new Vertex(a));
    }
}

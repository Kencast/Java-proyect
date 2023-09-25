package com.mycompany.proyecto1;
import java.util.ArrayList;
        
public class Soporte {
    private ArrayList<Ficha> atril = new ArrayList<Ficha>();
    public void append(int num,char color){
        Ficha ficha = new Ficha(num,color);
        atril.add(ficha);
    }

    public void eliminar(int num, char color){
        Ficha ficha = new Ficha(num,color);
        int index=atril.indexOf(num);
        atril.remove(index);
    }

    public Ficha buscar(int num, char color){
       Ficha ficha = new Ficha(num,color);
       int index=atril.indexOf(num);
       return atril.get(index);
    }
}

//nombreDelArrayList.add(valor);
//nombreDelArrayList.remove(índice); // Eliminar por índice
//nombreDelArrayList.remove(valor);  // Eliminar por valor
//int tamaño = nombreDelArrayList.size();


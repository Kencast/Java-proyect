package com.mycompany.proyecto1;

import java.util.ArrayList;
import java.util.Random;

public class Baraja {
    private ArrayList<Ficha> fichasMesa;

    public Baraja(){
        generarBaraja();
    }

    public void generarBaraja(){
        //Funcion que genera las fichas sobre la mesa
        fichasMesa = new ArrayList<Ficha>();
        for(int i = 0; i < 2; i++){
            for(int j = 1; j <= 13; j++){
                Ficha fa = new FichaNormal(j,0);
                Ficha fn = new FichaNormal(j,1);
                Ficha fr = new FichaNormal(j,2);
                Ficha fy = new FichaNormal(j,3);
                fichasMesa.add(fa);
                fichasMesa.add(fr);
                fichasMesa.add(fn);
                fichasMesa.add(fy);
            }
        }
        Ficha fc1 = new Comodin();
        Ficha fc2 = new Comodin();
        fichasMesa.add(fc1);
        fichasMesa.add(fc2);
    }
  
    public Ficha sacar(){
        //Funcion que saca una ficha apartir de un numero aleatorio
        Random generar= new Random();
        int pos = generar.nextInt(0,getTamaño());
        Ficha ficha = fichasMesa.get(pos);
        fichasMesa.remove(pos);
        return ficha;
    }
    
    public int getTamaño(){
    //Devuelve el tamaño de la baraja
        return fichasMesa.size();
    }
}

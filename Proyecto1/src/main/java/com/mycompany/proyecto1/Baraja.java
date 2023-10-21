package com.mycompany.proyecto1;

import java.util.ArrayList;
import java.util.Random;

    /**
    * @Description La clase es usada para guardar las fichas en la mesa
    */


public class Baraja {
    private ArrayList<Ficha> fichasMesa;

    /**
    * @Description Constructor de la clase
    */
    public Baraja(){
        //Inicializa la baraja
        generarBaraja();
    }

    /**
    * @Description Generar las fichas sobre la mesa
    */    private void generarBaraja(){
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


    /***
     * @Description Saca una ficha de la baraja apartir de un número random
     * @return  la ficha sacada
     */
    public Ficha sacar(){
        Random generar= new Random();
        int pos = generar.nextInt(0,getTamaño());
        Ficha ficha = fichasMesa.get(pos);
        fichasMesa.remove(pos);
        return ficha;
    }

    /**
    * @Description Da el tamaño de la baraja
    * @return el tamaño de la baraja
    *
    */
    public int getTamaño(){
        //Devuelve el tamaño de la baraja
        return fichasMesa.size();
    }
}

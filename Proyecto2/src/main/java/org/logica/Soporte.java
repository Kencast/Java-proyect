package org.logica;
import java.util.ArrayList;

public class Soporte {
    private ArrayList<Ficha> mano;

    public Soporte(){
        setMano();
    }

    public Soporte(Bolsa bag){
        setMano();
        llenarSoporte(bag);
    }

    public Soporte copiarMano(){
        //copia el soporte
        Soporte s=new Soporte();
        for(int i=0;i<7;i++) s.insertarFicha(mano.get(i));
        return s;
    }

    public void setMano(){
        //inicializar la mano del jugador
        this.mano=new ArrayList<>(); //se puede llamar "llenarMano" pero abría que meter la bolsa de parámetro
    }

    public Ficha sacarFicha(int i){
        //saca una ficha (con la intencion de usarla)
        return mano.get(i);
    }

    public void insertarFicha(Ficha f){
        //inserta una ficha a la mano
        mano.add(f);
    }

    public void purgarFicha(int i){
        //saca una ficha (solo para eliminarla de la mano)
        mano.remove(i);
    }

    public int getCantidad(){
        //retorna la cantidad de fichas que posee el jugador
        return mano.size();
    }

    public void llenarSoporte(Bolsa bag){
        //llena el soporte de fichas
        while(getCantidad()<7){
            Ficha f=bag.tomarFicha();
            insertarFicha(f);
        }
    }

}

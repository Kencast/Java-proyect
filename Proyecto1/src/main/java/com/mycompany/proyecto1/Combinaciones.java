package com.mycompany.proyecto1;

import java.util.ArrayList;

public class Combinaciones {
    private ArrayList<Ficha> grupo;
    private boolean escalera;
    private boolean combinacion;
    
    public Combinaciones(Ficha ficha){
        setCombinacion(ficha);
    }
 
    private void setSerie(Ficha ficha){
        //Inicializa una serie
        grupo = new ArrayList<Ficha>();
        insertar(ficha);
    }
    
    public void insertar(Ficha ficha){
        //Inserta una ficha al final
        ficha.setIndex(getTamaño());
        grupo.add(ficha);
    }
    
    public Ficha eliminar(int index){
        // Elimina una ficha en cierto indice
        Ficha ficha = grupo.get(index);
        grupo.remove(index);
        return ficha;
    }
            
    public int getTamaño(){
        //Retorna el tamaño del grupo
        return grupo.size();
    }        
    
    public boolean verificar(){
        //Verifica si el tamaño del grupo es correcto a partir del analisis de si es una serie o una escalera
        if(getTamaño() < 3) {return false;}
        esEscalera(0);
        if(getEscalera()) {return true;}
        if(getTamaño() > 4){return false;}
        esCombinacion(0);
        return getCombinacion();
    }
    
    private void esEscalera(int ind){
        if(ind+1 == getTamaño()){setEscalera(true);}
        else if(grupo.get(ind).getNum() == grupo.get(ind+1).getNum()-1){
            esEscalera(ind+1);
        }
        else setEscalera(false);
    }

    public boolean getEscalera() {
        return escalera;
    }
    public void setEscalera(boolean escalera) {
        this.escalera = escalera;
    }

    public boolean getCombinacion() {
        return combinacion;
    }
    public void setCombinacion(boolean combinacion) {
        this.combinacion = combinacion;
    }
    
    private void esCombinacion(int ind){
        if(ind+1 == getTamaño()){setCombinacion(true);}
        else if(grupo.get(ind).getNum() == grupo.get(ind+1).getNum()){
            esCombinacion(ind+1);
        }
        else setCombinacion(false);
    }
}

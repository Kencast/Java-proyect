package com.mycompany.proyecto1;

import java.util.ArrayList;

public class Combinaciones {
    private ArrayList<Ficha> grupo;
    private boolean escalera;
    private boolean serie;
    
    public Combinaciones(){
        setCombinacion();
    }
 
    private void setCombinacion(){
        //Inicializa una serie
        grupo = new ArrayList<Ficha>();
    }
    
    public void insertar(Ficha ficha){
        //Inserta una ficha al final
        ficha.setIndex(getTamaño() - 1);
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
        esSerie(0);
        return getSerie();
    }
    
    private void esEscalera(int ind) {
        //Verifica si el grupo es una escalera
        if (ind + 1 == getTamaño()) {
            setEscalera(true);
        } else {
            if (grupo.get(ind) instanceof Comodin) {
                grupo.get(ind).setNum(grupo.get(ind + 1).getNum() - 1);
            }
            if (grupo.get(ind).getNum() == grupo.get(ind + 1).getNum() - 1 &&
                    grupo.get(ind).getColor() == grupo.get(ind + 1).getColor()) {
                esEscalera(ind + 1);
            } else setEscalera(false);
        }
    }

    public boolean getEscalera() {
        return escalera;
    }

    public void setEscalera(boolean escalera) {
        this.escalera = escalera;
    }

    public boolean getSerie() {
        return serie;
    }

    public void setSerie(boolean serie) {this.serie = serie;}
    
    private void esSerie(int ind) {
       // Verifica si el grupo es una serie
        if(ind+1 == getTamaño()) {
            setSerie(true);
        }
        else {
            if (grupo.get(ind) instanceof Comodin) {
                grupo.get(ind).setNum(grupo.get(ind + 1).getNum());
            }
            if (grupo.get(ind).getNum() == grupo.get(ind + 1).getNum() &&
                    grupo.get(ind).getColor() != grupo.get(ind + 1).getColor()) {
                esSerie(ind + 1);
            } else setSerie(false);
        }
    }
}

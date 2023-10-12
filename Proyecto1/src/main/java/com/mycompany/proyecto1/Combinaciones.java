package com.mycompany.proyecto1;

import java.util.ArrayList;

public class Combinaciones {
    private ArrayList<Ficha> grupo;

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
        if(esEscalera()) {return true;}
        if(getTamaño() > 4){return false;}
        return esSerie();
    }
    
    private boolean esEscalera() {
        //Verifica si el grupo es una escalera
        int color = grupo.get(0).getColor();
        for(int i = 0; i < getTamaño(); i++){
            if(grupo.get(i) instanceof Comodin){
                if(i == 0){grupo.get(i).setNum(grupo.get(1).getNum()-1);}
                else {grupo.get(i).setNum(grupo.get(i-1).getNum()+1);}
            }
            if(grupo.get(i).getNum() != grupo.get(i).getNum()-1 && color != grupo.get(i).getColor()){return false;}
        }
        return true;
    }

    private boolean esSerie() {
        // Verifica si el grupo es una serie
        int array [] = new int[4];
        for(int i = 0; i < getTamaño(); i++){
            array[i] = grupo.get(i).getColor();
            if(!verificarColor(array,i)){return false;}
        }
        for(int i = 0; i < getTamaño(); i++){
            if(grupo.get(i) instanceof Comodin){
                if(i == 0){grupo.get(i).setNum(grupo.get(1).getNum());}
                else {grupo.get(i).setNum(grupo.get(i-1).getNum());}
                }
            if(grupo.get(i).getNum() != grupo.get(i).getNum()){return false;}
        }
        return true;
    }

    private boolean verificarColor(int array[], int i){
        for(int j = 0; j < 4; j++){
            if(i != j && array[j] == grupo.get(i).getColor()){return false;}
        }
        return true;
    }
}

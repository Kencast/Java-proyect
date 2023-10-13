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
        ficha.setIndex(getTamano() - 1);
        grupo.add(ficha);
    }

    public int getTamano(){
        //Retorna el tamaño del grupo
        return grupo.size();
    }        
    
    public boolean verificar(){
        //Verifica si el tamaño del grupo es correcto y verifica si es una correcta escalera o serie
        if(getTamano() < 3) {return false;}
        if(esEscalera()) {return true;}
        if(getTamano() > 4){return false;}
        return esSerie();
    }
    
    private boolean esEscalera() {
        //Verifica si el grupo es una escalera
        int color = seleccionarColor(0);
        for(int i = 0; i+1 < getTamano(); i++){
            if(grupo.get(i) instanceof Comodin){
                if(i == 0){
                    grupo.get(i).setNum(grupo.get(1).getNum()-1);
                }
                else {
                    grupo.get(i).setNum(grupo.get(i-1).getNum()+1);
                }
                grupo.get(i).setColor(color);
            }
            if(grupo.get(i).getNum() != grupo.get(i+1).getNum()-1 || color != grupo.get(i).getColor()){return false;}
        }
        return true;
    }

    private boolean esSerie() {
        // Verifica si el grupo es una serie
        int array [] = new int[4];
        for(int i = 0; i < 4; i++){array[i] = -1;}
        for(int i = 0; i < getTamano(); i++){
            int color = grupo.get(i).getColor();
            if(!verificarColor(array,color)){return false;}
            array[i] = color;
        }
        for(int i = 0; i+1 < getTamano(); i++){
            if(grupo.get(i) instanceof Comodin){
                if(i == 0){grupo.get(i).setNum(grupo.get(1).getNum());}
                else {grupo.get(i).setNum(grupo.get(i-1).getNum());}
            }
            if(grupo.get(i).getNum() != grupo.get(i+1).getNum()){return false;}
        }
        return true;
    }

    private boolean verificarColor(int array[], int color){
        //Verifica si el color dado no esta en el arreglo
        for(int j = 0; j < 4; j++){
            if(array[j] == color && color != 4){return false;}
        }
        return true;
    }

    private int seleccionarColor(int num){
        //Selecciona un color para verificar la escalera
        if(!(grupo.get(num) instanceof Comodin)){return grupo.get(num).getColor();}
        return seleccionarColor(num+1);
    }
}

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
        int cuenta = 0, flag = 0, color = 0;
        for (int i = 0; i < getTamano(); i++) {
            if (flag == 0 && !(grupo.get(i) instanceof Comodin)) {
                cuenta = grupo.get(i).getNum() + 1;
                color = grupo.get(i).getColor();
                flag++;
            } else {
                if (!(grupo.get(i) instanceof Comodin) &&
                (grupo.get(i).getNum() != cuenta || grupo.get(i).getColor() != color)) return false;
                cuenta++;
            }
        }
        return true;
    }

    private boolean esSerie() {
        // Verifica si el grupo es una serie
        int array [] = new int[4];
        for(int i = 0; i < 4; i++){array[i] = -1;}
        for(int i = 0; i < getTamano(); i++){
            int color = grupo.get(i).getColor();
            if(verificarColor(array,color)){return false;}
            array[i] = color;
        }
        int num [] = new int[getTamano()];
        for(int i = 0; i < getTamano(); i++){
            num[i] = grupo.get(i).getNum();
        }
        return verificarArrayNum(num,seleccionarNum(0));
    }

    private boolean verificarColor(int array[], int color){
        //Verifica si el color dado no esta en el arreglo
        for(int j = 0; j < 4; j++){
            if(array[j] == color && color != 4){return true;}
        }
        return false;
    }

    private int seleccionarColor(int num){
        //Selecciona un color para verificar la escalera
        if(!(grupo.get(num) instanceof Comodin)){return grupo.get(num).getColor();}
        return seleccionarColor(num+1);
    }

    private boolean verificarArrayNum(int array[],int num){
        for(int i = 0; i < getTamano(); i++){
            if(array[i] != 0 && array[i] != num) return false;
        }
        return true;
    }

    private int seleccionarNum(int num){
        if(!(grupo.get(num) instanceof Comodin)) return grupo.get(num).getNum();
        return seleccionarNum(num+1);
    }
}

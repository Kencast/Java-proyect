package com.mycompany.proyecto1;

import java.util.ArrayList;

/**
* @Description Clase que guarda y verifica un escalera o serie
*/

public class Combinaciones {
    private ArrayList<Ficha> grupo;

    /**
    * @Description Constructor de la clase
    */
    public Combinaciones(){
        //Inicializa el objeto combinacion
        setCombinacion();
    }

    /**
    * @Description Inicializa la lista
    */
    private void setCombinacion(){
        //Inicializa una serie
        grupo = new ArrayList<Ficha>();
    }

    /**
    * @Description Inserta una ficha en el grupo
    * @param ficha Ficha
    */
    public void insertar(Ficha ficha){
        //Inserta una ficha al final
        grupo.add(ficha);
    }

    /**
    * @Description Regresa el tamaño del grupo
    * @return tamaño del grupo
    */
    public int getTamano(){
        //Retorna el tamaño del grupo
        return grupo.size();
    }        

    /**
    * @Description Verifica si el tamaño y el grupo es legal
    * @return booleano
    */
    public boolean verificar(){
        //Verifica si el tamaño del grupo es correcto y verifica si es una correcta escalera o serie
        if(getTamano() < 3) {return false;}
        if(esEscalera()) {return true;}
        if(getTamano() > 4){return false;}
        return esSerie();
    }

    /**
    * @Description Verificar si es una escalera
    * @return booleano
    */
    private boolean esEscalera() {
        //Verifica si el grupo es una escalera
        int cuenta = 0, flag = 0, color = 0;
        for (int i = 0; i < getTamano(); i++) {
            if(cuenta > 13) return false;
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

    /**
    * @Description Verificar si es una serie valida
    * @return booleano
    */
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

    /**
    * @Description Verifica que un color no este en el arreglo de colores
    * @param array arreglo con los colores del grupo
    * @param color color a verificar
    * @return booleano
    */
    private boolean verificarColor(int array[], int color){
        //Verifica si el color dado no esta en el arreglo
        for(int j = 0; j < 4; j++){
            if(array[j] == color && color != 4){return true;}
        }
        return false;
    }

    /**
    * @Description Elije un color para verificar la escalera
    * @param num indice de una ficha
    * @return color
    */
    private int seleccionarColor(int num){
        //Selecciona un color para verificar la escalera
        if(!(grupo.get(num) instanceof Comodin)){return grupo.get(num).getColor();}
        return seleccionarColor(num+1);
    }

    /**
    * @Description Verificar que los numeros sean iguales
    * @param array arreglo de los numeros
    * @param num numero de comparacion
    * @return booleano
    */
    private boolean verificarArrayNum(int array[],int num){
        //verifica que los números de la serie sean iguales
        for(int i = 0; i < getTamano(); i++){
            if(array[i] != 0 && array[i] != num) return false;
        }
        return true;
    }

    /**
    * @Description Seleccionar un numero para tomarlo como comparador
    * @return numero del grupo
    */
    private int seleccionarNum(int num){
        //seleccionar un número que no sea de un comodín como referencia en la serie
        if(!(grupo.get(num) instanceof Comodin)) return grupo.get(num).getNum();
        return seleccionarNum(num+1);
    }
}

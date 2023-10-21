package com.mycompany.proyecto1;

/***
 * @Description Estructura de la Ficha y atributos
 */
public abstract class  Ficha {
    private int color; // Se usa un sistema del 0 al 3, para identificar los colores
    private int num;
    private int index;

    /***
     * @Description constructor default
     */
    public Ficha(){
        //Inicializa el objeto ficha
        setColor(4);
        setNum(0);
        settIndex(0);
    }

    /***
     * Ficha personalizada
     * @param num Número de la ficha
     * @param color Color de la ficha
     */
    public Ficha(int num,int color){
        //Inicializa el objeto ficha con un color y numero
        setColor(color);
        setNum(num);
        settIndex(0);
    }

    /***
     * @Description Ponerle un color a una Ficha
     * @param color nuevo color
     */

    public void setColor(int color) {
        //Modifica el color
        this.color = color;
    }

    /***
    * @Description Ponerle número a una ficha
    *@param num nuevo número
    */
    public void setNum(int num) {
        //Modifica el numero
        this.num = num;
    }

    /***
     * @Description Obtener el indice del color de una ficha
     * @return Indice del color
     */
    public int getColor() {
        //Retorna el color
        return color;
    }

    /***
     * @Description Obtener el número de la ficha
     * @return El número de la ficha
     */
    public int getNum() {
        //Retorna el numero
        return num;
    }

    /***
     *
     * @return Obtener el indice de la ficha
     */
    public int getIndex() {
        //Retorna el indice
        return index;
    }

    /***
    *Ponerle un indice a la ficha
    *@param ind el indice a poner
    */
    public void settIndex(int ind) {
        //Modifica el indice
        this.index = ind;
    }

}


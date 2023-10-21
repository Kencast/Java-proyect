package com.mycompany.proyecto1;
/**
* @Description Contiene todas las jugadas hechas por el jugador
*/
public class Mesa {
    private Ficha arrayMesa[];

    /**
    @Description Constructor
    */
    public Mesa(){
        //Inicializa el objeto mesa
        setArrayMesa();
        vaciar();
    }

    /**
    * @Description Inicializa el arreglo
    */
    private void setArrayMesa(){
        //Inicializa el arreglo
        this.arrayMesa = new Ficha[128];
    }

    /**
    *@Description Insertar la ficha en una posicion
    *@param f ficha
    *@param pos posicion en el arreglo
    */
    public void insertarFicha(Ficha f, int pos){
        //Inserta la ficha en "pos"
        arrayMesa[pos] = f;
    }

    /**
    *@Description Elimina la ficha
    *@param pos posicion a eliminar
    */
    public void sacarFicha(int pos){
        //Elimina la ficha en "pos"
        arrayMesa[pos] = null;
    }

    /**
    *@Description Obtener una ficha en una posicion
    *@param pos posicion a obtener
    *@return ficha
    */
    public Ficha obtenerFicha(int pos){
        //Da la ficha en "pos"
        return arrayMesa[pos];
    }

    /**
    *@Description Vacia el arreglo
    */
    private void vaciar(){
        //LLena de null's el arreglo
        for(int i = 0; i < 128; i++){
            arrayMesa[i] = null;
        }
    }

    /**
    *@Description Hace una copia del arreglo
    *@return copia de mesa
    */
    public Mesa copiar(){
        //Genera una copia del arreglo
        Mesa m = new Mesa();
        for(int i = 0; i < 128; i++){
            m.insertarFicha(obtenerFicha(i),i);
        }
        return m;
    }

    /**
    *@Description Reemplaza el arreglo actual
    *@param m mesa a copiar
    */
    public void reemplazar(Mesa m){
        //Reemplaza el arreglo actual por m
        vaciar();
        for(int i = 0; i < 128; i++){
            arrayMesa[i] = m.obtenerFicha(i);
        }
    }

}

package org.logica;
import java.util.ArrayList;
/**
* @Description
*/
public class Soporte {
    private ArrayList<Ficha> mano;

    /**
    * @Description Constructor
    */
    public Soporte(){
        setMano();
    }

    /**
    * @Description Constructor
    * @param bag Bolsa
    */
    public Soporte(Bolsa bag){
        setMano();
        llenarSoporte(bag);
    }

    /**
    * @Description copiar soporte
    * @return Copia del soporte
    */
    public Soporte copiarMano(){
        //copia el soporte
        Soporte s=new Soporte();
        for(int i = 0 ; i < 7; i++){
            Ficha f = mano.get(i), g;
            if(f instanceof Comodin) g = new Comodin();
            else g = new FichaNormal(f.getValor(),f.getLetra());
            s.insertarFicha(g);
        }
        return s;
    }

    /**
    * @Description Inicializa la mano del jugador
    */
    public void setMano(){
        //inicializar la mano del jugador
        this.mano=new ArrayList<>(); //se puede llamar "llenarMano" pero abría que meter la bolsa de parámetro
    }

    /**
    * @Description Obtiene una ficha en una posicion
    * @param i indice
    * @return ficha en el indice
    */
    public Ficha sacarFicha(int i){
        //saca una ficha (con la intencion de usarla)
        return mano.get(i);
    }

    /**
    * @Description Inserta una ficha en la mano
    * @param f Ficha a insertar
    */
    public void insertarFicha(Ficha f){
        //inserta una ficha a la mano
        mano.add(f);
    }

    /**
    * @Description  elimina una ficha en un indice
    * @param i indice a eliminar
    */
    public void purgarFicha(int i){
        //saca una ficha (solo para eliminarla de la mano)
        mano.remove(i);
    }

    /**
    * @Description obtiene el tamaño de la mano
    * @return entero con el tamano
    */
    public int getCantidad(){
        //retorna la cantidad de fichas que posee el jugador
        return mano.size();
    }

    /**
    * @Description Introduce las fichas faltantes en el soporte
    * @param bag Bolsa
    */
    public void llenarSoporte(Bolsa bag){
        //llena el soporte de fichas
        while(getCantidad()<7){
            Ficha f=bag.tomarFicha();
            insertarFicha(f);
        }
    }

}

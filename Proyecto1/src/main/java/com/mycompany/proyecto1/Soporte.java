package com.mycompany.proyecto1;
/**
*@Description Contiene las fichas de un jugador
*/
public class Soporte {
    private Ficha atril[];
    private int cantidad;

    /**
    *@Description Constructor
    */
    public Soporte(){
        //Inicializa el soporte vacio
        this.atril = new Ficha[107];
        vaciar();
        setCantidad(0);
    }
    
    /**
    *@Description Constructor
    *@param mazo fichas sobre la mesa
    */
    public Soporte(Baraja mazo){
        //Inicializa el soporte
        setCantidad(0);
        setAtril(mazo);
    }

    /**
    *@Description Da las 14 fichas iniciales al jugador
    *@param mazo fichas sobre la mesa
    */
    private void setAtril(Baraja mazo){
        //Inicializa el atril con las 14 fichas de la baraja
        this.atril = new Ficha [107];
        vaciar();
        for(int i = 0; i < 14; i++){
            Ficha ficha = mazo.sacar();
            insertar(ficha);
        }   
    }

    /**
    *@Description Insertar comodines
    *@param ficha Ficha
    */
    private void insertarEspecial(Ficha ficha){
        //Inserta comodines
        if(atril[105] != null){
            ficha.settIndex(106);
            atril[106] = ficha;
        }
        else{
            ficha.settIndex(105);
            atril[105] = ficha;
        }
    }

    /**
    *@Description Insertar fichas normales
    *@param ficha Ficha a insertar
    *@param pos posicion en donde se insertara
    */
    private void insertarNormal(Ficha ficha, int pos){
        //Inserta fichas normales
        if(atril[pos] != null){
            ficha.settIndex(pos+13);
            atril[pos+13] = ficha;
        }
        else{
            ficha.settIndex(pos);
            atril[pos] = ficha;
        }
    }

    /**
    *@Description Da una posicion para la ficha
    *@param ficha Ficha
    *@return posicion
    */
    private int ubicar(Ficha ficha){
        //Da un valor para colocar la ficha en el lugar correcto
        int color = ficha.getColor();
        int num = ficha.getNum();
        if(color == 0){return num;}
        if(color == 1){return num + 26;}
        if(color == 2){return num + 52;}
        else return num + 78;
    }

    /**
    *@Description Vaciar el arreglo
    */
    private void vaciar(){
        //Vacia el atril
        for(int i = 0; i < 107; i++){atril[i] = null;}
    }

    /**
    *@Description Insertar una ficha
    *@param ficha Ficha
    */
    public void insertar(Ficha ficha){
        //inserta una ficha
        if(!(ficha instanceof Comodin)){
            int index = ubicar(ficha);
            insertarNormal(ficha,index);
        }
        else {insertarEspecial(ficha);}
        setCantidad(getCantidad()+1);
    }

    /**
    *@Description Da la cantidad de fichas en el soporte
    *@return numero de fichas
    */
    public int getCantidad(){
        //Da la cantidad de fichas en el soporte
        return cantidad;
    }

    /**
    *@Description Coloca una nueva cantidad de fichas
    *@param cantidad Cantidad de fichas
    */
    private void setCantidad(int cantidad){
        //Modifica la cantidad de fichas en el soporte
        this.cantidad = cantidad;
    }

    /**
    *@Description  Elimina una ficha
    *@param index Indice a eliminar
    */
    public void sacar(int index){
        //Saca una ficha del atril
        atril[index] = null;
        setCantidad(getCantidad()-1);
    }

    /**
    *@Description Da una ficha en index
    *@param index Indice a buscar
    *@return ficha en el indice
    */
    public Ficha obtenerFicha(int index){
        //Da la ficha en la posicion index
        return atril[index];
    }

    /**
    *@Description Da la ficha por posicion
    *@param pos posicion de la ficha
    *@return ficha en la posicion
    */
    public Ficha consultarFicha(int pos){
        //Da la ficha que es la numero "pos" del soporte
        int cuenta = 0;
        for(int i = 1; i < 107; i++){
            if(atril[i] != null){
                if(cuenta == pos){return atril[i];}
                else{cuenta++;}
            }
        }
        return null;
    }

    /**
    *@Description Sumar los puntos del soporte
    *@return puntos del soporte
    */
    public int sumarPuntoSoporte(){
        //Suma los puntos del soporte
        int sum=0;
        for(int i=1;i<107;i++){
            if(atril[i]!=null){
                if(atril[i] instanceof Comodin) sum+=30;
                else sum+=atril[i].getNum();
            }
        }
        return sum;
    }

    /**
    *@Description Da una compia del soporte actual
    *@return copia del soporte
    */
    public Soporte copiar(){
        //Devuelve una copia del soporte
        Soporte s = new Soporte();
        for(int i = 1; i < 107; i++){
            if(atril[i] != null){s.insertar(atril[i]);}
        }
        s.setCantidad(getCantidad());
        return s;
    }

    /**
    *@Description Reemplaza el soporte actual
    *@param r soporte que desea usar
    */
    public void reemplazar(Soporte r){
        //Reemplaza el soporte actual por el soporte r
        vaciar();
        for(int i = 1; i < 107; i++){
            if(r.obtenerFicha(i) != null) insertar(r.obtenerFicha(i));
        }
        setCantidad(r.getCantidad());
    }

}
// 1 26 azul 0
// 27 52 roja 1
// 53 78 negro 2 
// 79 104 yellow 3 
// 105 106 comodines 4
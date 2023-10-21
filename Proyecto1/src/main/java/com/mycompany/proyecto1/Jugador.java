package com.mycompany.proyecto1;
/**
 *@Description Administra las acciones y fichas del Jugador
 */
public class Jugador {
    private Soporte atril;
    private int puntajeTotal;
    private int puntos;
    private boolean puedeModificar;
    private Soporte copia;
     /***
    *@Description constructor el cuál inicializa el atril, el puntaje,los puntos y etc
    *@param fichasIniciales Fichas de la mesa
    */
    public Jugador(Baraja fichasIniciales){
        //Inicializa el objeto jugador
        setAtril(fichasIniciales);
        setPuntajeTotal(0);
        setPuntos(0);
        setPuedeModificar(false);
    }
     /***
    *@Description obtiene el puntaje total obtenido
    *@return puntos totales
    */
    public int getPuntajeTotal() { //Retorna el puntaje global del jugador
        return puntajeTotal;
    }
    /***
    *@Description Asigna el total de puntos obtenidos por un jugador
    *@param puntajeTotal numero de puntos
    */
    public void setPuntajeTotal(int puntajeTotal) { // Modifica el puntaje total
        this.puntajeTotal = puntajeTotal;
    }
    /***
    *@Description suma el total de puntos obtenidos por un jugador
    *@param puntos Número a sumar
    */
    public void sumarPuntajeTotal(int puntos) { //Suma los puntos de la ultima partida al total
        puntajeTotal+=puntos;
    }
     /**
    *@Description entrega la cantidad de fichas que hay en el soporte
    *@return Cantidad de fichas en el atril
    */
    public int cantFichas(){ //Retorna la cantida de fichas que tiene en el atril
        return atril.getCantidad();
    }
     /**
    *@Description Indica si un jugador puede modificar las fichas que hay hasta el momento en la mesa
    *@return True/False
    */
    public boolean isPuedeModificar() { //Retorna un booleano para saber si el jugador ya puede modificar el tablero
        return puedeModificar;
    }
    /**
    *@Description Cambia el estado de "puedemodificar"
    *@param puedeModificar True/False
    */
    public void setPuedeModificar(boolean puedeModificar) { // Modifica si se puede modificar la mesa
        this.puedeModificar = puedeModificar;
    }
    /**
    *@Description crea el soporte inicial de un jugador
    *@param fichasIniciales baraja con fichas
    */
    public void setAtril(Baraja fichasIniciales){ //genera un soporte
        atril= new Soporte(fichasIniciales);
    }

     /**
    *@Description obtiene la cantidad de puntos que tiene un jugador
    *@return puntos
    */
    public int getPuntos() { //Retorna los puntos del jugador
        return puntos;
    }
    /***
    *@Description actualiza los puntos de un jugador
    *@param puntos La cantidad de puntos
    */
    public void setPuntos(int puntos) { //Modifica los puntos del jugador
        this.puntos = puntos;
    }
    /***
    *@Description toma una nueva ficha de la baraja y la inserta en el soporte de un jugador
    *@param fichas La Baraja con toda las fichas para sacar
    */
    public void tomarNuevaFicha(Baraja fichas){//saca una ficha y
        atril.insertar(fichas.sacar()); //la mete en el soporte
    }
    /**
    *@Description saca una ficha del soporte
    *@param index indice que se desea eliminar en el atril
    */
    public void sacarFicha(int index){ //Saca ficha 'i' del soporte
        Ficha elegida=atril.obtenerFicha(index);
        atril.sacar(index);
        if(!(elegida instanceof Comodin)){
            puntos+=elegida.getNum();
        }
    }
    /***
    *@Description toma una ficha específica del soporte de un jugador para consultarla luego
    *@param pos Indice de la ficha
    *@return Una ficha del soporte de un jugador
    */
    public Ficha consultarFichaSoporte(int pos){ //pide la ficha número "n" del soporte
        return atril.consultarFicha(pos);
    }
    /**
    *@Description toma una ficha del soporte para usarla después
    *@param index Indice de la ficha
    *@return Ficha
    */
    public Ficha pedirFichaSoporte(int index){ //pide la ficha "i" del soporte
        return atril.obtenerFicha(index);
    }

    /**
    *@Description toma una ficha y la regresa al soporte de un jugador
    *@param f Ficha
    */
    public void regresarFichaSoporte(Ficha f){ //vuelve a insertar una ficha sacada
        if(!(f instanceof Comodin)) setPuntos(getPuntos()-f.getNum());
        atril.insertar(f);
    }

     /**
    *@Description crea una copia del soporte por si al modificarlo, hay que devolverlo a su estado anterior
    *
    */
    public void respaldarSoporte(){ //Genera una copia del soporte actul
        copia = atril.copiar();
    }

     /**
    *@Description Suma los puntos de un jugador con base al número de sus fichas
    *
    */
    public void sumarSoporte(){
        //el jugador consigue la suma de los puntos del soporte
        setPuntos(0);
        puntos=atril.sumarPuntoSoporte();
    }

     /**
    *@Description Reemplaza el soporte original por una copia sacada con anterioridad
    */
    public void reemplazarSoporte(){
        //lo reemplaza por la copia
        atril.reemplazar(copia);
    }

    /**
    *@Description Crear un nuevo soporte
    *@param mazo baraja para crear el soporte
    */
    public void resetearSoporte(Baraja mazo){
        //crear un nuevo soporte
        atril = new Soporte(mazo);
    }
}



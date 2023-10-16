package com.mycompany.proyecto1;
/**
 *
 * @author kencast
 * @author tuPadre
 */
public class Jugador {
    private Soporte atril;
    private int puntajeTotal;
    private int puntos;
    private boolean puedeModificar;
    private Soporte copia;

    public Jugador(Baraja fichasIniciales){
        //Inicializa el objeto jugador
        setAtril(fichasIniciales);
        setPuntajeTotal(0);
        setPuntos(0);
        setPuedeModificar(false);
    }

    public int getPuntajeTotal() { //Retorna el puntaje global del jugador
        return puntajeTotal;
    }
    public void setPuntajeTotal(int puntajeTotal) { // Modifica el puntaje total
        this.puntajeTotal = puntajeTotal;
    }

    public void sumarPuntajeTotal(int puntos) { //Suma los puntos de la ultima partida al total
        puntajeTotal+=puntos;
    }

    public int cantFichas(){ //Retorna la cantida de fichas que tiene en el atril
        return atril.getCantidad();
    }

    public boolean isPuedeModificar() { //Retorna un booleano para saber si el jugador ya puede modificar el tablero
        return puedeModificar;
    }
    public void setPuedeModificar(boolean puedeModificar) { // Modifica si se puede modificar la mesa
        this.puedeModificar = puedeModificar;
    }

    public void setAtril(Baraja fichasIniciales){ //genera un soporte
        atril= new Soporte(fichasIniciales);
    }

    public int getPuntos() { //Retorna los puntos del jugador
        return puntos;
    }
    public void setPuntos(int puntos) { //Modifica los puntos del jugador
        this.puntos = puntos;
    }
    
    public void tomarNuevaFicha(Baraja fichas){//saca una ficha y
        atril.insertar(fichas.sacar()); //la mete en el soporte
    }
    
    public void sacarFicha(int index){ //Saca ficha 'i' del soporte
        Ficha elegida=atril.obtenerFicha(index);
        atril.sacar(index);
        if(!(elegida instanceof Comodin)){
            puntos+=elegida.getNum();
        }
    }

    public Ficha consultarFichaSoporte(int pos){ //pide la ficha número "n" del soporte
        return atril.consultarFicha(pos);
    }

    public Ficha pedirFichaSoporte(int index){ //pide la ficha "i" del soporte
        return atril.obtenerFicha(index);
    }

    public void regresarFichaSoporte(Ficha f){ //vuelve a insertar una ficha sacada
        if(!(f instanceof Comodin)) setPuntos(getPuntos()-f.getNum());
        atril.insertar(f);
    }

    public void respaldarSoporte(){ //Genera una copia del soporte actul
        copia = atril.copiar();
    }

    public void sumarSoporte(){
        //el jugador consigue la suma de los puntos del soporte
        setPuntos(0);
        puntos=atril.sumarPuntoSoporte();
    }

    public void reemplazarSoporte(){
        //lo reemplaza por la copia
        atril.reemplazar(copia);
    }

    public void resetearSoporte(Baraja mazo){
        //crear un nuevo soporte
        atril = new Soporte(mazo);
    }

}



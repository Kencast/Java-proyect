package com.mycompany.proyecto1;

public class Partida {
    private Jugador jugadores[];
    private int rondas; //número de la ronda actual
    private int turnoActual; //número del jugador para el turno
    private Mesa tablero;
    private Baraja fichas;
    private Mesa copia;

    public Partida(){
        fichas=new Baraja();
        jugadores=new Jugador[4];
        jugadores[0] = new Jugador(fichas);
        jugadores[1] = new Jugador(fichas);
        jugadores[2] = new Jugador(fichas);
        jugadores[3] = new Jugador(fichas);
        tablero=new Mesa();
    }

    public int getTurnoActual() {
        return turnoActual;
    }
    public void setTurnoActual(int turnoActual) {
        this.turnoActual = turnoActual;
    }

    public void setRondas(int cant){
        this.rondas=cant;
    }
    public int getRondas(){return rondas;}

    private void aumentarRonda(){
        rondas++;
    }

    public void empezarPartida(){
        setRondas(1);
        setTurnoActual(0);
    }

    public void siguienteRonda(){
        aumentarRonda();
        setTurnoActual(0);
        resetear();
    }

    private void aumentarTurno(){
        if(turnoActual>4) setTurnoActual(1); //empieza de nuevo
        else turnoActual++;
    }
    public void cambiarTurno(){ //pasa al turno del siguiente jugador
        aumentarTurno();
    }
    private void verificarJugadas(Jugador actual){ //boolean
        //verificar las jugadas de la mesa
        //retornar false si están mal o true en caso contrario
    }


    public void comerFicha(Jugador actual, int fichasInicial){//comer ficha de baraja
        if(actual.cantFichas()==fichasInicial){//verifica que no haya jugado ninguna ficha
            actual.tomarNuevaFicha(fichas);
            cambiarTurno();
        }
    }

    public Jugador actualJugador(){
        return jugadores[getTurnoActual()];
    }

    public Ficha obtenerFichaTablero(int pos){
        return tablero.obtenerFicha(pos);
    }

    public void sacarFichaTablero(int pos){
        tablero.sacarFicha(pos);
    }

    public void insertarFichaTablero(int pos, Ficha f){
        tablero.insertarFicha(f,pos);
    }

    public void respaldarMesa(){
        copia = tablero.copiar();
    }

    public void calcularPuntosRonda(){
        int totalPuntos=0, puntos;
        for(int i=0; i<4; i++){
            jugadores[i].sumarSoporte();
            puntos=jugadores[i].getPuntos();
            totalPuntos+=puntos;
            jugadores[i].sumarPuntajeTotal(-puntos);
        }
        jugadores[getTurnoActual()].sumarPuntajeTotal(totalPuntos);
    }

    public void reemplazarMesa(){
        tablero.reemplazar(copia);
    }

    public void resetear(){
        fichas = new Baraja();
        tablero = new Mesa();
        for(int i = 0; i < 4; i++){
            jugadores[i].resetearSoporte(fichas);
            jugadores[i].setPuntos(0);
            jugadores[i].setPuedeModificar(false);
        }
    }
}

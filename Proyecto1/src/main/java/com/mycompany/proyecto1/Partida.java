package com.mycompany.proyecto1;

public class Partida {
    private Jugador jugadores[];
    private int rondas; //número de la ronda actual
    private int turnoActual; //número del juegador para el turno
    private Mesa tablero;
    private Baraja fichas;

    public Partida(){
        jugadores=new Jugador[4];
        tablero=new Mesa();
        fichas=new Baraja();
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
        setTurnoActual(1);
        turno(jugadores[0]); //empieza el primer turno de la ronda
    }

    public void siguienteRonda(){
        aumentarRonda();
        setTurnoActual(1);
        turno(jugadores[0]);
    }

    private void aumentarTurno(){
        if(turnoActual>4) setTurnoActual(1); //empieza de nuevo
        else turnoActual++;
    }
    private void cambiarTurno(){ //pasa al turno del siguiente jugador
        aumentarTurno();
        int index=getTurnoActual()-1;
        turno(jugadores[index]);
    }
    private void verificarJugadas(Jugador actual){ //boolean
        //verificar las jugadas de la mesa
        //retornar false si están mal o true en caso contrario
    }

    public void siguienteTurno(Jugador actual){//evalua si se puede pasar de turno

    }

    public void turno(Jugador jugador){
        int fichasInicial=jugador.cantFichas();

    }

    public void comerFicha(Jugador actual, int fichasInicial){//comer ficha de baraja
        if(actual.cantFichas()==fichasInicial){//verifica que no haya jugado ninguna ficha
            actual.tomarNuevaFicha(fichas);
            cambiarTurno();
        }
    }


}

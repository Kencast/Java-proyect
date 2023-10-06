package com.mycompany.proyecto1;

public class Partida {
    private Jugador jugadores[];
    private int rondas; //número de la ronda actual
    private int turnoActual; //número del jugador para el turno
    private Mesa tablero;
    private Baraja fichas;

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
        setTurnoActual(1);
    }

    private void aumentarTurno(){
        if(turnoActual>4) setTurnoActual(1); //empieza de nuevo
        else turnoActual++;
    }
    private void cambiarTurno(){ //pasa al turno del siguiente jugador
        aumentarTurno();
        int index=getTurnoActual();
        turno(jugadores[index]);
    }
    private void verificarJugadas(Jugador actual){ //boolean
        //verificar las jugadas de la mesa
        //retornar false si están mal o true en caso contrario
    }

    public void siguienteTurno(Jugador actual){//evalua si se puede pasar de turno

    }

    public void turno(Jugador jugador){ //clase incompleta 
        int fichasInicial=jugador.cantFichas(); //donde seteó la cantidad de fichas?

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
}

package com.mycompany.proyecto1;

public class Partida {
    private Jugador jugadores[];
    private int rondas; //número de la ronda actual
    private int turnoActual; //número del jugador para el turno
    private Mesa tablero;
    private Baraja fichas;
    private Mesa copia;
    private int numGanador;

    public Partida(){
        //Inicializa el objeto partida
        fichas=new Baraja();
        jugadores=new Jugador[4];
        setNumGanador(0);
        jugadores[0] = new Jugador(fichas);
        jugadores[1] = new Jugador(fichas);
        jugadores[2] = new Jugador(fichas);
        jugadores[3] = new Jugador(fichas);
        tablero=new Mesa();
    }

    public int getTurnoActual() {
        //Retorna el turno actual
        return turnoActual%4;
    }
    public void setTurnoActual(int turnoActual) {
        //Modifica el turno actual
        this.turnoActual = turnoActual;
    }
    public int turnoReal(){return turnoActual;} // Da el turno de la partida sin modulo

    public void setRondas(int cant){
        //Modifica las rondas
        this.rondas=cant;
    }
    public int getRondas(){return rondas;} //Devuelve las rondas

    private void aumentarRonda(){ // Aumenta las rondas
        rondas++;
    }

    public void empezarPartida(){ //empieza por la ronda 1 y jugador 1
        setRondas(1);
        setTurnoActual(0);
    }

    public void siguienteRonda(){ //aumenta la ronda y pone al jugador 1
        aumentarRonda();
        setTurnoActual(0);
        resetear();
    }
    
    public void setNumGanador(int n){numGanador=n;} //Modifica el numero del ganador
    
    public int getNumGanador(){return numGanador;} // Devuelve el numero del ganador
    
    private void aumentarTurno(){
        turnoActual++; //empieza de nuevo
    }
    public void cambiarTurno(){
        //pasa al turno del siguiente jugador
        aumentarTurno();
    }


    public void comerFicha(){//el jugador actual come ficha de la baraja
        Jugador p=actualJugador();
        p.tomarNuevaFicha(fichas);
    }

    public Jugador actualJugador(){ //Devuelve el jugador del turno actual
        return jugadores[getTurnoActual()];
    }

    public Ficha obtenerFichaTablero(int pos){
        //ficha "i" de la mesa
        return tablero.obtenerFicha(pos);
    }

    public void sacarFichaTablero(int pos){ //Saca una ficha en de la mesa
        tablero.sacarFicha(pos);
    }

    public void insertarFichaTablero(int pos, Ficha f){//Inseta una ficha en la mesa en "pos"
        tablero.insertarFicha(f,pos);
    }

    public void respaldarMesa(){
        //Respalda la mesa
        copia = tablero.copiar();
    }

    public void calcularPuntosRonda(){
        //si un jugador se queda sin ficha, gana y calcula los puntos
        int totalPuntos=0, puntos;
        for(int i=0; i<4; i++){
            jugadores[i].sumarSoporte();
            puntos=jugadores[i].getPuntos();
            totalPuntos+=puntos;
            jugadores[i].sumarPuntajeTotal(-puntos);
        }
        jugadores[getNumGanador()].sumarPuntajeTotal(totalPuntos); //le suma los puntos al ganador
    }

    public void jugadorGanador(){
        //si la baraja se queda sin fichas, calcula los puntos y saca a un ganador
        int minPuntos=10000, index=0, puntos=0;
        for(int i=0; i<4; i++){
            jugadores[i].sumarSoporte();
            puntos+=jugadores[i].getPuntos();
            if(jugadores[i].getPuntos()<minPuntos){
                minPuntos=jugadores[i].getPuntos();
                index=i;
            }
            jugadores[i].sumarPuntajeTotal(-jugadores[i].getPuntos()); //lo suma en negativo
        }
        setNumGanador(index); //guarda el num del ganador
        jugadores[index].sumarPuntajeTotal(puntos); //le suma los puntos al ganador
    }

    public void reemplazarMesa(){
        //Remplaza la meza
        tablero.reemplazar(copia);
    }

    public void resetear(){
        //Resetea todo para iniciar una nueva ronda
        fichas = new Baraja();
        tablero = new Mesa();
        for(int i = 0; i < 4; i++){
            jugadores[i].resetearSoporte(fichas);
            jugadores[i].setPuntos(0);
            jugadores[i].setPuedeModificar(false);
        }
    }

    public boolean existenFichas(){
        //Retorna un booleano si la baraja no esta vacia
        return fichas.getTamaño() == 0;
    }

    public String ganadorTotal(){
        //saca al jugador o jugadores de la partida
        int ganador1 = 0;
        int ganador2 = 0;
        for(int i = 1; i < 4; i++){
            if(jugadores[i].getPuntajeTotal() == jugadores[ganador1].getPuntajeTotal()){
                ganador2 = i;
            }
            if(jugadores[i].getPuntajeTotal() > jugadores[ganador1].getPuntajeTotal()){
                ganador1 = i;
                ganador2 = i;
            } 
        }
        ganador1++;
        ganador2++;
        if(ganador1 == ganador2) return "El jugador " + ganador1 + " ganó la partida";
        return "Los jugadores " + ganador1 + " y " + ganador2 + " ganaron la partida";
    }

    public Jugador jugadorIndex(int index){
        // saca al jugador número "i"
        return jugadores[index];
    }

}

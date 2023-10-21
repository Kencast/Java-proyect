package com.mycompany.proyecto1;

/***
 * @Description Administra el desarrollo de la Partida y las acciones
 */
public class Partida {
    private Jugador jugadores[];
    private int rondas; //número de la ronda actual
    private int turnoActual; //número del jugador para el turno
    private Mesa tablero;
    private Baraja fichas;
    private Mesa copia;
    private int numGanador;

    /***
     * @Description Constructor
     */
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

    /***
     * @Description Saber qué jugado está jugando
     * @return Número del turno actual
     */
    public int getTurnoActual() {
        //Retorna el turno actual
        return turnoActual%4;
    }

    /***
    * @Description Establecer el turno
    *@param turnoActual Número a poner
    */
    public void setTurnoActual(int turnoActual) {
        //Modifica el turno actual
        this.turnoActual = turnoActual;
    }

    /***
     * @Description Turno actual sin la operación módulo
     * @return Un número
     */
    public int turnoReal(){return turnoActual;} // Da el turno de la partida sin modulo

    /***
    * @Description Establecer la ronda
    *@param cant Número a poner
    */
    public void setRondas(int cant){
        //Modifica las rondas
        this.rondas=cant;
    }

    /***
     * @Description Obtener la rondas
     * @return Un número
     */
    public int getRondas(){return rondas;} //Devuelve las rondas

    /***
     * @Description Aumenta las rondas jugadas
     */
    private void aumentarRonda(){ // Aumenta las rondas
        rondas++;
    }

    /***
     * @Description Empieza la ronda 1 con el jugador 1
     */
    public void empezarPartida(){ //empieza por la ronda 1 y jugador 1
        setRondas(1);
        setTurnoActual(0);
    }

    /***
     * @Description Pasa a una nueva ronda
     */
    public void siguienteRonda(){ //aumenta la ronda y pone al jugador 1
        aumentarRonda();
        setTurnoActual(0);
        resetear();
    }

    /***
    * @Description Guarda al ganador de la ronda
    *@param n El número del jugador
    */
    public void setNumGanador(int n){numGanador=n;} //Modifica el numero del ganador

    /***
     * @Description Obtiene el número del jugador ganador
     * @return El número del jugador ganador
     */
    public int getNumGanador(){return numGanador;} // Devuelve el numero del ganador

    /***
     * @Description Aumenta el turno
     */
    private void aumentarTurno(){
        turnoActual++; //empieza de nuevo
    }

    /***
     * @Description Pasa al siguiente jugador
     */
    public void cambiarTurno(){
        //pasa al turno del siguiente jugador
        aumentarTurno();
    }

    /***
     * @Description El jugador come una Ficha de la Baraja
     */
    public void comerFicha(){//el jugador actual come ficha de la baraja
        Jugador p=actualJugador();
        p.tomarNuevaFicha(fichas);
    }

    /***
     * @Description Obtiene al jugador del turno actual
     * @return Al jugador actual
     */
    public Jugador actualJugador(){ //Devuelve el jugador del turno actual
        return jugadores[getTurnoActual()];
    }

    /***
    * @Description Obtiene una Ficha X de la mesa
    *@param pos Número de la ficha solicitada
    */
    public Ficha obtenerFichaTablero(int pos){
        //ficha "i" de la mesa
        return tablero.obtenerFicha(pos);
    }

    /***
    * @Description Saca a una Ficha X de la mesa
    *@param pos Número de la ficha solicitada
    */
    public void sacarFichaTablero(int pos){ //Saca una ficha en de la mesa
        tablero.sacarFicha(pos);
    }

    /***
    * @Description Inserta una ficha en una posición especifica
    *@param pos Número de la posición deseada
     * @param f Ficha
    */
    public void insertarFichaTablero(int pos, Ficha f){//Inseta una ficha en la mesa en "pos"
        tablero.insertarFicha(f,pos);
    }

    /***
     * @Description Hace una copia de la mesa
     */
    public void respaldarMesa(){
        //Respalda la mesa
        copia = tablero.copiar();
    }

    /***
     * @Description Al terminar una ronda calcula los puntos de cada jugador y los guarda
     */
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

    /***
     * @Description Si la Baraja se queda sin fichas saca a un ganador y calcula los puntos
     */
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

    /***
     * @Description Reemplaza la mesa con la copia guardada
     */
    public void reemplazarMesa(){
        //Remplaza la meza
        tablero.reemplazar(copia);
    }

    /***
     * @Description Restaura todos los atributos a su valor inicial
     */
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

    /***
     * @Description Verifica si la Baraja está vacia
     * @return True si está vacia, false lo contrario
     */
    public boolean existenFichas(){
        //Retorna un booleano si la baraja no esta vacia
        return fichas.getTamaño() == 0;
    }

    /***
     * @Description Saca al ganador de toda la Partida y calcula puntos
     * @return Un String con el mensaje a mostrar
     */
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

    /***
    * @Description Retorna al un Jugador X
    *@param index Número del jugador deseado
    */
    public Jugador jugadorIndex(int index){
        // saca al jugador número "i"
        return jugadores[index];
    }

}

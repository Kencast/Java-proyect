package org.logica;

/***
 * @Description Clase encargada de administrar el desarrollo del juego
 */
public class Partida {
    private Jugador[] jugadores;
    private int cantJugadores;
    private Tablero tablero;
    private Bolsa bolsa;
    private int turnoActual;
    private Tablero copiaTablero;
    private Diccionario diccionario;
    private int contador;
    private int suma;

    /***
     * @Description Constructor
     * @param cantJ cantidad de jugadores
     */
    public Partida(int cantJ) {
        bolsa = new Bolsa();
        jugadores = new Jugador[cantJ];
        for(int i = 0; i < cantJ; i++) jugadores[i] = new Jugador(bolsa);
        cantJugadores = cantJ;
        tablero = new Tablero();
        copiaTablero = new Tablero();
        generarCopiaTablero();
        diccionario = new Diccionario();
        contador = 0;
        suma = 0;
        sorteo();
    }

    /***
     * @Description iniciar la partida
     */
    public void empezar(){
        setTurno(0);
    }

    /***
     * @Description retorna el nombre del jugador
     * @param i indice del jugador
     * @return el nombre del jugador en el indice i
     */
    public String getNameJugador(int i){
        return getJugador(i).getNombre();
    }

    /***
     *@Description indica a cantidad de jugadores
     *@return cantidad de jugadores
     */
    public int getCantJugadores() {
        return cantJugadores;
    }

    /***
     * @Description retorna el jugador en el indice
     * @param i indice del jugador
     * @return el jugador i
     */
    public Jugador getJugador(int i) {
        return jugadores[i];
    }

    /***
     * @Description establecer el turno
     * @param n número del turno
     */
    public void setTurno(int n){
        turnoActual=n;
    }

    /***
     * @Description retorna el turno actual
     * @return turno actual
     */
    public int getTurno(){
        return turnoActual;
    }

    /***
     * @Description cambia la posicion de dos jugadores
     * @param i jugador i
     * @param j jugador j
     */
    private void swap(int i, int j) {
        Jugador aux = getJugador(i);
        jugadores[i] = jugadores[j];
        jugadores[j] = aux;
    }

    /***
     * @Description indica cuál es el jugador del turno actual
     * @return el jugador actual
     */
    public Jugador getJugadorActual(){
        return getJugador(getTurno()%cantJugadores);
    }

    /***
     * @Description le asigna una ficha a cada jugador
     */
    public void sorteo(){
        int i=1;
        char l;
        while(i<=getCantJugadores()){
            Ficha ficha=bolsa.sacarSinEliminar();
            if(ficha instanceof Comodin) l='*';
            else l=ficha.getLetra();
            Jugador jug=getJugador(i-1);
            jug.setNombre("Jugador "+ i);
            jug.setLetra(l);
            i++;
        }
    }

    /***
     * @Description ordena a los jugadores por la ficha del sorteo
     */
    private void ordenarJug(){
        int cambio = 1;
        while(cambio > 0) {
            cambio = 0;
            for (int i = 0; i + 1 < getCantJugadores(); i++) {
                if (jugadores[i].getLetra() > jugadores[1 + i].getLetra()) {
                    swap(i, 1 + i);
                    cambio++;
                }
            }
        }
    }

    /***
     *@Description verifica el sorteo inicial
     * @return true si el sorteo está correcto o falso
     */
    public boolean verificarSorteo(){
        ordenarJug();
        return jugadores[0].getLetra() != jugadores[1].getLetra();
    }

    /***
     * @Description inserta una ficha en el tablero
     * @param indS indice de la ficha a colocar
     * @param indB indice de la posición
     * @param j indice del jugador
     */
    public void insertarTablero(int indS, int indB, Jugador j){
        Ficha f = j.tomarFicha(indS);
        j.eliminarFichaSoporte(indS);
        f.setTurno(getTurno());
        tablero.colocarFicha(f,indB);
    }

    /***
     * @Description hacer una copia del tablero
     */
    public void generarCopiaTablero(){
        copiaTablero = tablero.copiarTablero();
    }

    /***
     * @Description reemplaza el tablero por la copia
     */
    public void reemplazarCopiaTablero(){
        tablero = copiaTablero.copiarTablero();
    }

    /***
     * @Description consulta una posicion del tablero
     * @param i indice
     * @return Ficha
     */
    public Ficha consultarFichaTablero(int i){
        return tablero.consultarFicha(i);
    }

    /***
     * @Description resetear el tablero
     */
    public void reset(){
        reemplazarCopiaTablero();
        getJugadorActual().intercambiarSoporte();
        getJugadorActual().setPuedeMover(true);
    }

    /***
     * @Description pasar al siguiente turno
     */
    public void pasarTurno(){
        Jugador j = getJugadorActual();
        if(noHayCambios() || !j.getPuedeMover()) contador++;
        else contador=0;
        j.sumarPuntaje(suma);
        j.completarSoporte(bolsa);
        setTurno(getTurno()+1);
        getJugadorActual().copiaSoporte();
        getJugadorActual().setPuedeMover(true);
        generarCopiaTablero();
    }

    /***
     * @Description verifica si hubo cambios en la mano
     * @return booleano
     */
    public boolean noHayCambios(){
        return getJugadorActual().compararManoCopia();
    }

    /***
     * @Description hace el proceso del cambio de una ficha
     * @param i indice de la ficha a cambiar por una de la bolsa
     */
    public void cambiarFicha(int i){
        Jugador j = getJugadorActual();
        Ficha f = j.tomarFicha(i);
        j.eliminarFichaSoporte(i);
        j.sacarBolsa(bolsa);
        bolsa.insertarFicha(f);
        j.setPuedeMover(false);
    }

    /***
     * @Description verifica si hay un ganador
     * @return booleano
     */
    public boolean verificarGanar(){
        if(contador == cantJugadores-1) return ganadorNingun();
        if(!verificarBolsa()) return ganadorVacio();
        return false;
    }

    /***
     * @Description revisa si la bolsa esta vacia o quedara vacia
     * @return booleano
     */
    private boolean verificarBolsa(){
        return bolsa.tamanoBolsa() != 0 && getJugadorActual().faltantes() < bolsa.tamanoBolsa();
    }

    /**
    * @Description verifica si el tablero
    * @return booleano
    */
    public boolean verificarTablero(){
        suma = tablero.verificacionTotal(diccionario,getTurno());
        return suma != -1;
    }

    /***
     * @Description resta a cada jugador el soporte
     * @return booleano
     */
    private boolean ganadorNingun(){
        for(int i = 0; i < cantJugadores; i++){
            jugadores[i].restarSoporte();
        }
        return true;
    }

    /***
     * @Description sacar un ganador si la bolsa se quedó sin fichas
     * @return booleano
     */
    private boolean ganadorVacio(){
        int puntos = 0;
        Jugador j = getJugadorActual();
        for(int i = 0; i < cantJugadores; i++){
            if(jugadores[i] == j) continue;// tal vez no sirva (le apuesto un huevo a que si)
            puntos += jugadores[i].puntosSoporte();
            jugadores[i].restarSoporte();
        }
        j.setPuntaje(j.getPuntaje() + puntos);
        return true;
    }

    /***
     * @Description retorna el índice en donde está el jugaador ganador
     * @return entero
     */
    public int ganador(){
        //retorna el indice en donde está el jugador ganador, en caso de empate, hagarra cualquiera
        int res = 0;
        for(int i = 1; i < cantJugadores; i++){
            if(jugadores[i].getPuntaje()>jugadores[res].getPuntaje()) res=i;
        } return res;
    }
}

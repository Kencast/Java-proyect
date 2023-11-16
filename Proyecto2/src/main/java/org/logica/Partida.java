package org.logica;

/***
 * @Description Clase encargada de administrar el desarrollo del juego
 *
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
     *
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

    public void empezar(){
        setTurno(0);
    }

    public String getNameJugador(int i){
        return getJugador(i).getNombre();
    }

    public int getCantJugadores() {
        return cantJugadores;
    }

    public Jugador getJugador(int i) {
        return jugadores[i];
    }
    
    public void setTurno(int n){
        turnoActual=n;
    }
    
    public int getTurno(){
        return turnoActual;
    }


    private void swap(int i, int j) {
        Jugador aux = getJugador(i);
        jugadores[i] = jugadores[j];
        jugadores[j] = aux;
    }

    public Jugador getJugadorActual(){
        return getJugador(getTurno()%cantJugadores);
    }

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

    public boolean verificarSorteo(){
        ordenarJug();
        return jugadores[0].getLetra() != jugadores[1].getLetra();
    }

    public void insertarTablero(int indS, int indB, Jugador j){
        Ficha f = j.tomarFicha(indS);
        j.eliminarFichaSoporte(indS);
        f.setTurno(getTurno());
        tablero.colocarFicha(f,indB);
    }

    public void generarCopiaTablero(){
        copiaTablero = tablero.copiarTablero();
    }

    public void reemplazarCopiaTablero(){
        tablero = copiaTablero.copiarTablero();
    }

    public Ficha consultarFichaTablero(int i){
        return tablero.consultarFicha(i);
    }

    public void reset(){
        reemplazarCopiaTablero();
        getJugadorActual().intercambiarSoporte();
        getJugadorActual().setPuedeMover(true);
    }

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

    public boolean noHayCambios(){
        return getJugadorActual().compararManoCopia();
    }

    public void cambiarFicha(int i){
        Jugador j = getJugadorActual();
        Ficha f = j.tomarFicha(i);
        j.eliminarFichaSoporte(i);
        j.sacarBolsa(bolsa);
        bolsa.insertarFicha(f);
        j.setPuedeMover(false);
    }

    public boolean verificarGanar(){
        if(contador == cantJugadores) return ganadorNingun();
        if(!verificarBolsa()) return ganadorVacio();
        return false;
    }

    private boolean verificarBolsa(){
        return bolsa.tamanoBolsa() != 0 && getJugadorActual().faltantes() < bolsa.tamanoBolsa();
    }

    public boolean verificarTablero(){
        suma = tablero.verificacionTotal(diccionario,getTurno());
        return suma != -1;
    }
    
    private boolean ganadorNingun(){
        for(int i = 0; i < cantJugadores; i++){
            jugadores[i].restarSoporte();
        }
        return true;
    }

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

    public int ganador(){
        //retorna el indice en donde está el jugador ganador, en caso de empate, hagarra cualquiera
        int res = 0;
        for(int i = 1; i < cantJugadores; i++){
            if(jugadores[i].getPuntaje()>jugadores[res].getPuntaje()) res=i;
        } return res;
    }
}

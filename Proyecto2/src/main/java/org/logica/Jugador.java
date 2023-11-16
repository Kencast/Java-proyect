package org.logica;

public class Jugador {
    private Soporte mano;
    private int puntaje;
    private Soporte copia;
    private char letra;
    private String nombre;
    private boolean puedeMover;

    public Jugador(Bolsa bag){
        mano=new Soporte(bag);
        setPuntaje(0);
        setPuedeMover(true);
        copiaSoporte();
    }

    public void setPuedeMover(boolean n){
        //asigna la varibale puedeMover la cual indica si un jugador puede poner ficha en el tablero
        puedeMover=n;
    }

    public boolean getPuedeMover(){
        //retorna True si el jugador puede mover fichas al tablero
        return puedeMover;
    }

    public void setPuntaje(int p){
        //se asigna el puntaje de un jugador
        puntaje=p;
    }

    public void copiaSoporte(){
        //copia el soporte original
        copia = mano.copiarMano();
    }

    public void intercambiarSoporte(){  //no hago funcion de reemplazar porque al copiar ya se cumple la funcion
        //cambia el soporte original por el respaldo
        mano = copia.copiarMano();
    }

    public void sumarPuntaje(int p){
        //se asigna el puntaje total del jugador
        puntaje+=p;
    }

    public void setNombre(String nom){
        //asigna un nombre al jugador
        nombre=nom;
    }

    public String getNombre(){
        //retorna la variable nombre
        return nombre;
    }

    public void setLetra(char l){
        //asigna un valor a la variable letra
        letra=l;
    }

    public char getLetra(){
        //devuelve la varible letra
        return letra;
    }

    public int getPuntaje(){
        //retorna el puntaje hasta el momento del jugador
        return puntaje;
    }

    public int cantidadFichas(){
        //retorna la cantidad de fichas que tiene el jugador hasta el momento
        return mano.getCantidad();
    }

    public void regresarFicha(Ficha f){
        //regresa la ficha en el primer lugar vacio (que no haya ficha) que encuentre
        mano.insertarFicha(f);
    }

    public Ficha tomarFicha(int i){
        //toma una ficha especifica de la mano
        return mano.sacarFicha(i);
    }

    public void eliminarFichaSoporte(int i){
        //elimina una ficha del soporte
        mano.purgarFicha(i);
    }

    public boolean compararManoCopia(){
        //compara la copia de la mano del jugador
        if(mano.getCantidad() != copia.getCantidad()) return false;
        for(int i = 0; i < copia.getCantidad(); i++){
            if(mano.sacarFicha(i).getLetra() != copia.sacarFicha(i).getLetra()) return false;
        } return true;
    }
    
    public void completarSoporte(Bolsa bolsa){
        //llena el soporte
        for(int i = cantidadFichas(); i < 7; i++){
            mano.insertarFicha(bolsa.tomarFicha());
        }
    }

    public void sacarBolsa(Bolsa bolsa){
        //toma una ficha de la bolsa y la inserta en la mano
        mano.insertarFicha(bolsa.tomarFicha());
    }

    public int faltantes(){
        //retorna la cantidad de fichas faltantes para tener 7 en el soporte
        return 7 - mano.getCantidad();
    }

    public void restarSoporte(){
        //resta el soporte
        for(int i = 0; i < 7; i++){
            Ficha f = mano.sacarFicha(i);
            setPuntaje(getPuntaje() - f.getValor());
        }
    }

    public int puntosSoporte(){
        int count = 0;
        for(int i = 0; i < 7; i++){
            count += mano.sacarFicha(i).getValor();
        }
        return count;
    }
}

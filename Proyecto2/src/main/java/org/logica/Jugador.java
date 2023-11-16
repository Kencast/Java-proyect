package org.logica;

public class Jugador {
    private Soporte mano;
    private int puntaje;
    private Soporte copia;
    private char letra;
    private String nombre;
    private boolean puedeMover;

    /**
    * @Description Constructor
    * @param bag Bolsa
    */
    public Jugador(Bolsa bag){
        mano=new Soporte(bag);
        setPuntaje(0);
        setPuedeMover(true);
        copiaSoporte();
    }

    /**
    * @Description cambia el valor de puedeMover
    * @param n booleano a cambiar
    */
    public void setPuedeMover(boolean n){
        //asigna la varibale puedeMover la cual indica si un jugador puede poner ficha en el tablero
        puedeMover=n;
    }

    /**
    * @Descriptionretorna el vvalor de puedeMover
    * @return booleano con el valor de puedeMover
    */
    public boolean getPuedeMover(){
        //retorna True si el jugador puede mover fichas al tablero
        return puedeMover;
    }

    /**
    * @Description cambia el valor del puntaje
    * @param p nuevo puntaje
    */
    public void setPuntaje(int p){
        //se asigna el puntaje de un jugador
        puntaje=p;
    }

    /**
    * @Description copia el soporte
    */
    public void copiaSoporte(){
        //copia el soporte original
        copia = mano.copiarMano();
    }

    /**
    * @Description cambia el soporte por la copia
    */
    public void intercambiarSoporte(){  //no hago funcion de reemplazar porque al copiar ya se cumple la funcion
        //cambia el soporte original por el respaldo
        mano = copia.copiarMano();
    }

    /**
    * @Description suma el puntaje
    * @param p puntaje a sumar
    */
    public void sumarPuntaje(int p){
        puntaje+=p;
    }

    /**
    * @Description cambia el valor del nombre
    * @param nom string a cambiar
    */
    public void setNombre(String nom){
        //asigna un nombre al jugador
        nombre=nom;
    }

    /**
    * @Description retorna el valor en nombre
    * @return string con nombre
    */
    public String getNombre(){
        //retorna la variable nombre
        return nombre;
    }

    /**
    * @Description cambia el valor de la letra
    * @param l char a cambiar
    */
    public void setLetra(char l){
        //asigna un valor a la variable letra
        letra=l;
    }

    /**
    * @Description retorna el valor de letra
    *@ @return char con la letra
    */
    public char getLetra(){
        //devuelve la varible letra
        return letra;
    }

    /**
    * @Description retorna el puntaje
    * @return entero con el puntaje
    */
    public int getPuntaje(){
        //retorna el puntaje hasta el momento del jugador
        return puntaje;
    }

    /**
    * @Description retorna la cantidad de fichas que tiene
    * @return entero con la cantidad
    */
    public int cantidadFichas(){
        //retorna la cantidad de fichas que tiene el jugador hasta el momento
        return mano.getCantidad();
    }

    /**
    * @Description toma una ficha en un indice
    * @param i indice
    * @return ficha en el indice
    */
    public Ficha tomarFicha(int i){
        //toma una ficha especifica de la mano
        return mano.sacarFicha(i);
    }

    /**
    * @Description elimina la ficha en un indice
    * @param i indice
    */
    public void eliminarFichaSoporte(int i){
        //elimina una ficha del soporte
        mano.purgarFicha(i);
    }

    /**
    * @Description compara las manos
    * @return boolean si son iguales
    */
    public boolean compararManoCopia(){
        //compara la copia de la mano del jugador
        if(mano.getCantidad() != copia.getCantidad()) return false;
        for(int i = 0; i < copia.getCantidad(); i++){
            if(mano.sacarFicha(i).getLetra() != copia.sacarFicha(i).getLetra()) return false;
        } return true;
    }

    /**
    * @Description llena el soporte con las fichas faltantes
    * @param bolsa Bolsa
    */
    public void completarSoporte(Bolsa bolsa){
        //llena el soporte
        for(int i = cantidadFichas(); i < 7; i++){
            mano.insertarFicha(bolsa.tomarFicha());
        }
    }

    /**
    * @Description toma una ficha de la bolsa y la inserta en la mano
    * @param bolsa Bolsa
    */
    public void sacarBolsa(Bolsa bolsa){
        //toma una ficha de la bolsa y la inserta en la mano
        mano.insertarFicha(bolsa.tomarFicha());
    }

    /**
    * @Description retorna la cantidad faltante de fichas
    * @return entero
    */
    public int faltantes(){
        //retorna la cantidad de fichas faltantes para tener 7 en el soporte
        return 7 - mano.getCantidad();
    }

    /**
    * @Description resta el soporte a los puntos del jugador
    */
    public void restarSoporte(){
        //resta el soporte
        for(int i = 0; i < 7; i++){
            Ficha f = mano.sacarFicha(i);
            setPuntaje(getPuntaje() - f.getValor());
        }
    }

    /**
    * @Description calcula los puntos del soporte
    * @return entero
    */
    public int puntosSoporte(){
        int count = 0;
        for(int i = 0; i < 7; i++){
            count += mano.sacarFicha(i).getValor();
        }
        return count;
    }
}

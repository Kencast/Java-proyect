
package com.mycompany.proyecto1;

/**
 *
 * @author kencast
 */
public class Jugador {
    private Soporte atril;
    private int puntajeTotal;
    private int puntos;
    private boolean puedeModificar;

    public Jugador(Baraja fichasIniciales){
        setAtril(fichasIniciales);
        setPuedeModificar(false);
    }

    public int getPuntajeTotal() {
        return puntajeTotal;
    }
    public void setPuntajeTotal(int puntajeTotal) {
        this.puntajeTotal = puntajeTotal;
    }

    public int cantFichas(){
        return atril.getCantidad();
    }

    public boolean isPuedeModificar() {
        return puedeModificar;
    }
    public void setPuedeModificar(boolean puedeModificar) {
        this.puedeModificar = puedeModificar;
    }

    public void setAtril(Baraja fichasIniciales){
        atril= new Soporte(fichasIniciales);
    }
    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
    
    public void tomarNuevaFicha(Baraja fichas){//saca una ficha y
        atril.insertar(fichas.sacar()); //la mete en el soporte
    }
    
    public void sacarFicha(int index){ //Saca ficha 'i' del soporte
        Ficha elegida=atril.sacar(index);
        puntos+=elegida.getNum();
    }

    public void crearCombinación(Mesa grupos, Ficha elegida){
        //crear un nuevo grupo en la Mesa y meter esta ficha
    }

    public void meterEnCombinacion(Mesa grupos, Ficha n, int grupo){
        //meter la ficha 'n' en el grupo 'i'
    }
    public Ficha fichaEnMesa(Mesa grupos, int grupo, int elemento){
        //si puedoModificar es true sacar la ficha de la mesa
    }
}

package com.mycompany.proyecto1;
/**
 *
 * @author kencast
 * @author Tupadre
 */
public class Jugador {
    private Soporte atril;
    private int puntajeTotal;
    private int puntos;
    private boolean puedeModificar;
    private Soporte copia;

    public Jugador(Baraja fichasIniciales){
        setAtril(fichasIniciales);
        setPuntajeTotal(0);
        setPuntos(0);
        setPuedeModificar(false);
    }

    public int getPuntajeTotal() {
        return puntajeTotal;
    }
    public void setPuntajeTotal(int puntajeTotal) {
        this.puntajeTotal = puntajeTotal;
    }

    public void sumarPuntajeTotal(int puntos) {
        puntajeTotal+=puntos;
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
        Ficha elegida=atril.obtenerFicha(index);
        atril.sacar(index);
        puntos+=elegida.getNum();
    }

    public Ficha consultarFichaSoporte(int pos){
        return atril.consultarFicha(pos);
    }

    public Ficha pedirFichaSoporte(int index){
        return atril.fichaIndex(index);
    }

    public void regresarFichaSoporte(Ficha f){
        setPuntos(getPuntos()-f.getNum());
        atril.insertar(f);
    }

    public void respaldarSoporte(){
        copia = atril.copiar();
    }

    public void sumarSoporte(){
        puntos=atril.sumarpuntosoporte();
    }

    public void reemplazarSoporte(){
        atril.reemplazar(copia);
    }

    public void resetearSoporte(Baraja mazo){
        atril = new Soporte(mazo);
    }

}



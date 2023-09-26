/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1;

/**
 *
 * @author kencast
 */
public class Jugador {
    private Soporte atril;
    private int puntos;

    public Jugador(Baraja fichasIniciales){
        setAtril(fichasIniciales);
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
        atril.insertar(fichas.sacar()); //la mete en la baraja
    }
    
    public void sacarFicha(int index){
        Ficha elegida=atril.sacar(index);
        puntos+=elegida.getNum();
    }
    
    
}

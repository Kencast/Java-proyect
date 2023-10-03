package com.mycompany.proyecto1;

import java.util.ArrayList;

public class Mesa {
    private ArrayList<Combinaciones> grupos;
    
    public Mesa(){
        setGrupos();
    }
    
    private void setGrupos(){
        grupos = new ArrayList<Combinaciones>();
    }
    
    public boolean verificarGrupos(){
        for(int i = 0; i < getTamaño(); i++){
            if(!(grupos.get(i).verificar())) {return false;}
        }
        return true;
    }

    public void generargrupo(Ficha ficha){ //(Joshua) para el antepenúltimo método de la clase jugador
        //grupos.add(ficha);
    }

    public Combinaciones sacargrupo(int index){ //(Joshua) para el penúltimo método de la clase jugador
        return grupos.get(index);
    }

    public int getTamaño(){
        return grupos.size();
    }
    
    
}

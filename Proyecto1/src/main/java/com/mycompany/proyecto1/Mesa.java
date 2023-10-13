package com.mycompany.proyecto1;

public class Mesa {
    private Ficha arrayMesa[];

    public Mesa(){
        setArrayMesa();
        vaciar();
    }

    private void setArrayMesa(){
        //Inicializa el arreglo
        this.arrayMesa = new Ficha[128];
    }

    public void insertarFicha(Ficha f, int pos){
        //Inserta la ficha en "pos"
        arrayMesa[pos] = f;
    }

    public void sacarFicha(int pos){
        //Elimina la ficha en "pos"
        arrayMesa[pos] = null;
    }

    public Ficha obtenerFicha(int pos){
        //Da la ficha en "pos"
        return arrayMesa[pos];
    }

    private void vaciar(){
        //LLena de null's el arreglo
        for(int i = 0; i < 128; i++){
            arrayMesa[i] = null;
        }
    }

    public Mesa copiar(){
        //Genera una copia del arreglo
        Mesa m = new Mesa();
        for(int i = 0; i < 128; i++){
            m.insertarFicha(obtenerFicha(i),i);
        }
        return m;
    }
    
    public void reemplazar(Mesa m){
        //Reemplaza el arreglo actual por m
        vaciar();
        for(int i = 0; i < 128; i++){
            arrayMesa[i] = m.obtenerFicha(i);
        }
    }

}

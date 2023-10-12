package com.mycompany.proyecto1;

public class Mesa {
    private Ficha arrayMesa[];

    public Mesa(){
        setArrayMesa();
        vaciar();
    }

    private void setArrayMesa(){
        this.arrayMesa = new Ficha[128];
    }

    public void insertarFicha(Ficha f, int pos){
        arrayMesa[pos] = f;
    }

    public void sacarFicha(int pos){
        arrayMesa[pos] = null;
    }

    public Ficha obtenerFicha(int pos){
        return arrayMesa[pos];
    }

    private void vaciar(){
        for(int i = 0; i < 128; i++){
            arrayMesa[i] = null;
        }
    }

    public Mesa copiar(){
        Mesa m = new Mesa();
        for(int i = 0; i < 128; i++){
            m.insertarFicha(obtenerFicha(i),i);
        }
        return m;
    }
    
    public void reemplazar(Mesa m){
        vaciar();
        for(int i = 0; i < 128; i++){
            arrayMesa[i] = m.obtenerFicha(i);
        }
    }

}

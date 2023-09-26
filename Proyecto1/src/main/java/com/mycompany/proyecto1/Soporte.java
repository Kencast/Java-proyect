package com.mycompany.proyecto1;

public class Soporte {
    private Ficha atril [];
    private int cantidad;
    
    public Soporte(Baraja mazo){
        cantidad = 0;
        setAtril(mazo);
    }
    
    private void setAtril(Baraja mazo){
        //Inicializa el atril con las 14 fichas de la baraja
        this.atril = new Ficha [107];
        vaciar();
        for(int i = 0; i < 14; i++){
            Ficha ficha = mazo.sacar();
            insertar(ficha);
        }   
    }
    
    private void insertarEspecial(Ficha ficha){
        //Inserta comodines
        if(atril[105] != null){
            ficha.setIndex(106);
            atril[106] = ficha;
        }
        else{
            ficha.setIndex(105);
            atril[105] = ficha;
        }
    }
    
    private void insertarNormal(Ficha ficha, int pos){
        //Inserta fichas normales
        if(atril[pos] != null){
            ficha.setIndex(pos+13);
            atril[pos+13] = ficha;
        }
        else{
            ficha.setIndex(pos);
            atril[pos] = ficha;
        }
    }
    
    private int ubicar(Ficha ficha){
        //Da un valor para colocar la ficha en el lugar correcto
        char color = ficha.getColor();
        int num = ficha.getNum();
        if(color == 'a'){return num;}
        if(color == 'r'){return num + 26;}
        if(color == 'n'){return num + 52;}
        else return num + 78;
    }
    
    private void vaciar(){
        //Vacia el atril
        for(int i = 0; i < 105; i++){atril[i] = null;}
    }
    
    public void insertar(Ficha ficha){
        //inserta una ficha
        if(!(ficha instanceof Comodin)){
            int index = ubicar(ficha);
            insertarNormal(ficha,index);
        }
        else {insertarEspecial(ficha);}
        setCantidad(getCantidad()+1);
    }

    public int getCantidad() {
        return cantidad;
    }
    private void setCantidad(int cantidad){
        this.cantidad = cantidad;
    }

    public Ficha sacar(int index){
        //Saca una ficha del atril
        Ficha ficha = atril[index];
        atril[index] = null;
        return ficha;
    }
}
// 1 26 azul
// 27 52 roja
// 53 78 negro
// 79 104 yellow
// 105 106 comodines
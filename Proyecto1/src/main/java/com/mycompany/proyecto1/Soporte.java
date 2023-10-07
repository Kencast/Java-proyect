package com.mycompany.proyecto1;

public class Soporte {
    private Ficha atril[];
    private int cantidad;
    
    public Soporte(Baraja mazo){
        setCantidad(0);;
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
        int color = ficha.getColor();
        int num = ficha.getNum();
        if(color == 0){return num;}
        if(color == 1){return num + 26;}
        if(color == 2){return num + 52;}
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
        cantidad--;
        return ficha;
    }

    public Ficha consultarFicha(int pos){
        int cuenta = 0;
        for(int i = 0; i < 106; i++){
            if(atril[i] != null){
                if(cuenta == pos){return atril[i];}
                else{cuenta++;}
            }
        }
        return null;
    }

    public Ficha fichaIndex(int index){
        return atril[index];
    }

}
// 1 26 azul 0
// 27 52 roja 1
// 53 78 negro 2 
// 79 104 yellow 3 
// 105 106 comodines 4
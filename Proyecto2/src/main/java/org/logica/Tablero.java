package org.logica;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.Stack;
/**
* @Description Clase para representar el tablero del juego
*/
public class Tablero {
    private Ficha [][] mesa;
    private int [][] tP = {{0,0},{0,7},{0,14},{7,0},{7,14},{14,0},{14,7},{14,14}};
    private int [][] dL = {{0,3},{0,11},{2,6},{2,8},{3,0},{3,7},{3,14},{6,2},{6,6},{6,8},{6,12},{7,3},{7,11},{8,2},{8,6},
                            {8,8},{8,12},{11,0},{11,7},{11,14},{12,6},{12,8},{14,3},{14,11}};
    private int [][] tL = {{1,5},{1,9},{5,1},{5,5},{5,9},{5,13},{9,1},{9,5},{9,9},{9,13},{13,5},{13,9}};
    private int [][] dP = {{1,1},{2,2},{3,3},{4,4},{7,7},{10,10},{11,11},{12,12},{13,13},{1,13},{2,12},{3,11},{4,10},{10,4},
                            {11,3},{12,2},{13,1}};
    private Queue<int []> cola;
    private int [] vF = {-1,1,0,0};
    private int [] vC = {0,0,-1,1};

    /**
    * @Description Constructor
    */
    public Tablero(){
        mesa = new Ficha [15][15];
        cola = new ArrayDeque<>();
    }

    /**
    * @Description coloca una ficha en el tablero en donde se pida
    * @param f Ficha a insertar
    * @param i donde se busca insertar
    */
    public void colocarFicha(Ficha f, int i){
        mesa[i/15][i%15] = f;
    }

    /**
    * @Description suma los puntos de la palabra
    * @param turno entero dado por el turno actual
    * @return entero con la suma de la palabra
    */
    private int sumarPuntos(int turno){
        int flag = 0;
        int suma = 0;
        Stack<Integer> pila = new Stack<>();
        while(!cola.isEmpty()){
            int [] coor = cola.poll();
            if(mesa[coor[0]][coor[1]].getTurno() == turno) flag++;
            if(verificartP(coor[0],coor[1]) && mesa[coor[0]][coor[1]].getTurno() == turno){
                pila.push(3);
                suma += mesa[coor[0]][coor[1]].getValor();
            } else if(verificardP(coor[0],coor[1]) && mesa[coor[0]][coor[1]].getTurno() == turno){
                pila.push(2);
                suma += mesa[coor[0]][coor[1]].getValor();
            } else if(verificardL(coor[0],coor[1]) && mesa[coor[0]][coor[1]].getTurno() == turno){
                suma += mesa[coor[0]][coor[1]].getValor() * 2;
            } else if(verificartL(coor[0],coor[1]) && mesa[coor[0]][coor[1]].getTurno() == turno){
                suma += mesa[coor[0]][coor[1]].getValor() * 3;
            } else suma += mesa[coor[0]][coor[1]].getValor();
        }
        while(!pila.empty()){
            suma *= pila.pop();
        }
        if(flag > 0) return suma;
        return 0;
    }

    /**
    * @Description verifica y suma todo el tablero
    * @param d diccionario sobre el que se revisa
    * @param turno turno actual
    * @return suma o bandera de la verificacion
    */
    public int verificacionTotal(Diccionario d, int turno){
        if(turno == 0 && mesa[7][7] == null) return -1;
        int suma = 0;
        for(int i = 0; i < 15; i++){
            String s = "";
            for(int j = 0; j < 15; j++){
                if(mesa[i][j] != null){
                    s += mesa[i][j].getLetra();
                    int [] par = {i,j};
                    cola.offer(par);
                }else {
                    if(s.length() > 1){
                        if(!d.verificar(s)) return -1;
                        suma += sumarPuntos(turno);
                    } else if(fichaSola()) return -1;
                    limpiarCola();
                    s = "";
                }
            }
            if(s.length() > 1){
                if(!d.verificar(s)) return -1;
                suma += sumarPuntos(turno);
            }else if(fichaSola()) return -1;
            limpiarCola();
            s = "";
        }
        for(int i = 0; i < 15; i++){
            String s = "";
            for(int j = 0; j < 15; j++){
                if(mesa[j][i] != null){
                    s += mesa[j][i].getLetra();
                    int [] par = {j,i};
                    cola.offer(par);
                }else {
                    if(s.length() > 1){
                        if(!d.verificar(s)) return -1;
                        suma += sumarPuntos(turno);
                    } else if(fichaSola()) return -1;
                    limpiarCola();
                    s = "";
                }
            }
            if(s.length() > 1){
                if(!d.verificar(s)) return -1;
                suma += sumarPuntos(turno);
            }else if(fichaSola()) return -1;
            limpiarCola();
            s = "";
        }
        return suma;
    }

    /**
    * @Description verifica si hay un potenciador de 3*palabra
    * @param i fila
    * @param j columna
    * @return booleano para indicar si es un potenciador
    */
    private boolean verificartP(int i, int j){
        for(int k = 0; k < 8; k++){
            if(i == tP[k][0] && j == tP[k][1]) return true;
        }
        return false;
    }

    /**
    * @Description verifica si hay un potenciador de 2*letra
    * @param i fila
    * @param j columna
    * @return booleano para indicar si es un potenciador
    */
    private boolean verificardL(int i, int j){
        for(int k = 0; k < 24; k++){
            if(i == dL[k][0] && dL[k][1] == j) return true;
        }
        return false;
    }

    /**
    * @Description verifica si hay un potenciador de 2*letra
    * @param i fila
    * @param j columna
    * @return booleano para indicar si es un potenciador
    */
    private boolean verificardP(int i, int j){
        for(int k = 0;k < 17; k++){
            if(dP[k][0] == i && dP[k][1] == j) return true;
        }
        return false;
    }

    /**
    * @Description verifica si hay un potenciador de 3*letra
    * @param i fila
    * @param j columna
    * @return booleano para indicar si es un potenciador
    */
    private boolean verificartL(int i, int j){
        for(int k = 0; k < 12; k++){
            if(tL[k][0] == i && tL[k][1] == j) return true;
        }
        return false;
    }

    /**
    * @Description genera una copia del tablero
    * @return tablero copia
    */
    public Tablero copiarTablero(){
        Tablero t = new Tablero();
        for(int i = 0; i < 15; i++){
            for(int j = 0; j < 15; j++){
                if(mesa[i][j] != null) t.colocarFicha(mesa[i][j],(i*15)+j);
            }
        }
        return t;
    }

    /**
    * @Description retorna la ficha en el indice
    * @param i indice a consultar
    * @return ficha en el indice
    */
    public Ficha consultarFicha(int i){
        return mesa[i/15][i%15];
    }

    /**
    * @Description vacia la cola
    */
    private void limpiarCola(){
      while(!cola.isEmpty()) cola.poll();
    }

    /**
    * @Description determina si hay una ficha sola
    * @return booleano si la ficha esta sola
    */
    private boolean fichaSola(){
        if(cola.isEmpty()) return false;
        int [] par = cola.poll();
        for(int i = 0; i < 4; i++){
            int x = par[0] + vF[i], y =  par[1] + vC[i];
            if(verificarCoor(x,y) && mesa[x][y] != null) return false;
        }
        return true;
    }

    /**
    * @Description verifica que la coordenada este dentro de mesa
    * @return booleano si es una coordenada valida
    */
    private boolean verificarCoor(int x, int y){
        return x >= 0 && x < 15 && y < 15 && y >= 0;
    }
}

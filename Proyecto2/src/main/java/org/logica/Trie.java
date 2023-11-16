package org.logica;
/**
* @Description arbol para guardar el diccionario
*/
public class Trie {
    private Vertex root;

    /**
    * @Description Constructor
    */
    public Trie(){
        setRoot('!');
    }

    /**
    * @Description cambia el valor de la raiz
    * @param a nueva letra
    */
    public void setRoot(char a){
        //inicializa el nodo inicial del trie
        this.root = new Vertex(a);
    }

    /**
    * @Description retorna la raiz
    * @return vertex raiz
    */
    public Vertex getRoot(){
        //retorna el nodo inicial del trie
        return this.root;
    }

    /**
    * @Description inserta una palabra
    * @param s string a insertar
    */
    public void insertar(String s){
        //inserta un valor en el trie
        Vertex actual =  getRoot();
        for(int i = 0; i < s.length(); i++){
            int n = s.charAt(i) - 'a';
            if(s.charAt(i) == 'ñ') n = 26;
            if(actual.accederHijos(n) == null) actual.setPosHijos(n,s.charAt(i));
            actual = actual.accederHijos(n);
        }
        actual.setExist(true);
    }

    /**
    * @Description busca la palabra
    * @param s string a buscar
    * @return booleano que indica si esta la palabra
    */
    public boolean buscar(String s){
        //busca si la palabra esta en el trie
        Vertex actual = getRoot();
        for(int i = 0; i < s.length(); i++){
            int n;
            if(s.charAt(i) == 'ñ') n = 26;
            else n = s.charAt(i) - 'a';
            if(actual.accederHijos(n) == null) return false;
            actual = actual.accederHijos(n);
        }
        return actual.getExist();
    }
}

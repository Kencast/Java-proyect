package org.logica;
/**
* ED para guardar el diccionario
*/
public class Trie {
    private Vertex root;

    public Trie(){
        setRoot('!');
    }

    public void setRoot(char a){
        //inicializa el nodo inicial del trie
        this.root = new Vertex(a);
    }

    public Vertex getRoot(){
        //retorna el nodo inicial del trie
        return this.root;
    }

    public void insertar(String s){
        //inserta un valor en el trie
        Vertex actual =  getRoot();
        for(int i = 0; i < s.length(); i++){
            int n;
            if(s.charAt(i) == 'ñ') n = 26;
            else n = s.charAt(i) - 'a';
            if(actual.accederHijos(n) == null) actual.setPosHijos(n,s.charAt(i));
            actual = actual.accederHijos(n);
        }
        actual.setExist(true);
    }

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

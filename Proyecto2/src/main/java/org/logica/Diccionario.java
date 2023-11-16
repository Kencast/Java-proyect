package org.logica;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.io.FileInputStream;
/**
* @Description Clase que contiene las palabras validas
*/
public class Diccionario {
    private Trie diccionary;

    /**
    * @Description Constructor
    */
    public Diccionario(){
        diccionary = new Trie();
        generarDiccionario();
    }

    /**
    * @Description Carga el archivo, cambia las letras invalidas y crea el trie
    * @exception Error al cargar el archivo
    */
    private void generarDiccionario(){
        String archivo = "src/main/java/org/logica/Diccionario.txt";
        try{
            FileInputStream doc = new FileInputStream(archivo);
            InputStreamReader input = new InputStreamReader(doc,StandardCharsets.UTF_8);
            BufferedReader lector = new BufferedReader(input);
            String linea;
            while((linea = lector.readLine()) != null){
                if(linea.length() < 2) continue;
                for(int i = 0; i < linea.length(); i++){
                    if(linea.charAt(i) > 64 && linea.charAt(i) < 91){
                        linea = linea.replace(linea.charAt(i), (char)(linea.charAt(i)+32));
                    }
                    switch(linea.charAt(i)){
                        case 'á':linea = linea.replace('á','a'); break;
                        case 'é':linea = linea.replace('é','e'); break;
                        case 'í':linea = linea.replace('í','i'); break;
                        case 'ó':linea = linea.replace('ó','o'); break;
                        case 'ú':linea = linea.replace('ú','u'); break;
                        case 'ü':linea = linea.replace('ü','u'); break;
                        case 'ö':linea = linea.replace('ö','o'); break;
                        case 'î': linea = linea.replace('î','i'); break;
                        case 'Á':linea = linea.replace('Á','a'); break;
                    }
                }
                insertarDiccionary(linea);
            }
        } catch (IOException e) {
            System.out.println("Ocurrió un error al leer el archivo: " + e.getMessage());
        }
    }

    /**
    * @Description inserta una palabra dentro del trie
    * @param a String a insertar
    */
    private void insertarDiccionary(String a){
        diccionary.insertar(a);
    }

    /**
    * @Description verifica si un string esta en el trie
    * @param a string a verificar
    * @return Booleano para indicar si esta o no
    */
    public boolean verificar(String a){
        return diccionary.buscar(a);
    }
}

package org.logica;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
/**
* Clase para validar palabras
*/
public class Diccionario {
    private Trie diccionary;

    public Diccionario(){
        generarDiccionario();
    }

    private void generarDiccionario(){
        String archivo = "Diccionario.txt";
        try{
            FileReader doc = new FileReader(archivo);
            BufferedReader lector = new BufferedReader(doc);
            String linea;
            while((linea = lector.readLine()) != null){
                if(linea.length() < 2) continue;
                for(int i = 0; i < linea.length(); i++){
                    switch(linea.charAt(i)){
                        case 'á':linea = linea.replace('á','a'); break; 
                        case 'é':linea = linea.replace('é','e'); break;
                        case 'í':linea = linea.replace('í','i'); break;
                        case 'ó':linea = linea.replace('ó','o'); break;
                        case 'ú':linea = linea.replace('ú','u'); break;
                    }
                }
                insertarDiccionary(linea);
            }
        } catch (IOException e) {
            System.out.println("Ocurrió un error al leer el archivo: " + e.getMessage());
        }
    }
    
    private void insertarDiccionary(String a){
        diccionary.insertar(a);
    }
    
    public boolean verificar(String a){
        return diccionary.buscar(a);
    }
}

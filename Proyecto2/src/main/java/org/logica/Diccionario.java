package org.logica;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.io.FileInputStream;
/**
* Clase para validar palabras
*/
public class Diccionario {
    private Trie diccionary;

    public Diccionario(){
        diccionary = new Trie();
        generarDiccionario();
    }

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
    
    private void insertarDiccionary(String a){
        diccionary.insertar(a);
    }
    
    public boolean verificar(String a){
        return diccionary.buscar(a);
    }
}

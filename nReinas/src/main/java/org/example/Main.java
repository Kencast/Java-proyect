package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner leer=new Scanner(System.in);
        int tam=leer.nextInt();
        int band=leer.nextInt();
        nQueens solve=new nQueens(tam, band);
        solve.start();
    }
}

package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.Scanner;

class Individuo{
    public int[] queens;
    public int puntaje;

    Individuo(int l){
        queens=new int[l];
        puntaje=0;
    }
}

public class nQueens {
    public int n;
    public int cantPob;
    public int elitism;
    public int probMut;
    public boolean withEl;
    public Individuo[] poblacion;
    public Individuo[] nextGen;
    public Random rand;
    public JFrame ventana;
    Container pane;


    nQueens(int m, int band){
        n=m;
        withEl= band > 0;
        probMut=50;
        cantPob=(n>50)? 50*n:n*n;
        if((cantPob&1)==0) cantPob++;
        elitism=0;
        poblacion=new Individuo[cantPob];
        nextGen=new Individuo[cantPob];
        rand=new Random();
    }

    public void initInterfaz(){
        ventana=new JFrame("Generación 0");
        ventana.setSize(712, 712);
        ventana.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ventana.setLocationRelativeTo(null);
        pane=ventana.getContentPane();
        pane.setLayout(new GridLayout(n, n));
        pane.setBackground(Color.WHITE);

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++) {
                JButton a= new JButton("");
                if((i+j)%2==0) a.setBackground(Color.WHITE);
                else a.setBackground(Color.BLACK);
                a.setBorderPainted(false);
                pane.add(a);
            }
        }
        ventana.setVisible(true);
    }

    public int randon(int m){
        return rand.nextInt(m);
    }

    void firstGen(){
        for(int i = 0; i <cantPob; i++) {
            poblacion[i]=new Individuo(n);
            nextGen[i]=new Individuo(n);
            for(int j=0; j<n; j++) poblacion[i].queens[j]=randon(n);
            fitness(poblacion[i]);
        }
    }

    void fitness(Individuo ind){
        int cont=0, col;
        int[] same=new int[n];
        int[] diagDown=new int[2*n];
        int[] diagUp=new int[2*n];
        for(int i=0; i<n; i++){
            same[i]=0;
            diagDown[2*i]=diagDown[(2*i)+1]=0;
            diagUp[2*i]=diagUp[(2*i)+1]=0;
        }
        for(int i=0; i<n; i++){
            col=ind.queens[i];
            same[col]++;
            diagDown[n-col+i]++; diagUp[col+i]++;
        }
        for(int i=0; i<n; i++){
            col=ind.queens[i];
            cont+=same[col]-1; cont+=diagDown[n-col+i]-1;
            cont+=diagUp[col+i]-1;
        }
        ind.puntaje=cont*cont;
    }

    int candidato(){
        int ganador=randon(cantPob), otro;
        for(int i=0; i<3; i++){
            otro=randon(cantPob);
            if(poblacion[otro].puntaje<poblacion[ganador].puntaje) ganador=otro;
        }
        return ganador;
    }

    void mutar(int i){
        if(randon(100)>probMut) return;
        nextGen[i].queens[randon(n)]=randon(n);
    }

    void procrear(int i){
        int padre1=candidato(), padre2=candidato();
        for(int p=0; p<n; p++){
            nextGen[i].queens[p]=(randon(5)<2)? poblacion[padre2].queens[p]:poblacion[padre1].queens[p];
        }
        mutar(i);
        fitness(nextGen[i]);
    }

    void calcularNextG(){
        nextGen[0].puntaje=poblacion[elitism].puntaje;
        for(int i=0; i<n; i++) nextGen[0].queens[i]=poblacion[elitism].queens[i];
        elitism=0;
        for(int i=1; i<cantPob; i++){
            procrear(i);
            if(nextGen[i].puntaje<nextGen[elitism].puntaje) elitism=i;
        }
    }

    void imprimir(int g){
        System.out.printf("Generación: %d\n", g);
        System.out.printf("Fitness: sqrt( %d )\n", poblacion[0].puntaje);
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(j==poblacion[0].queens[i]) System.out.print(" R ");
                else System.out.print(" O ");
            }
            System.out.println();
        }
        System.out.println();
    }



    public void mostrar(int gen) {
        ventana.setTitle("Generación "+gen);
        int pos;
        for(int i=0; i<n; i++){
            pos=poblacion[0].queens[i];
            for(int j=0; j<n; j++){
                JButton a= (JButton) pane.getComponent(i*n+j);
                if(j==pos) a.setBackground(Color.green);
                else{
                    if((i+j)%2==0) a.setBackground(Color.WHITE);
                    else a.setBackground(Color.BLACK);
                }
            }
        }
    }

    void start() {
        initInterfaz();
        firstGen();
        int contRep = 1, contGen = 1;
        int beforeElit = 10000000;
        while (true) {
            if (contRep > n * n || beforeElit == 0) {
                mostrar(contGen);
                break;
            }
            if (poblacion[elitism].puntaje == beforeElit) contRep++;
            else {
                contRep = 1;
                beforeElit = poblacion[elitism].puntaje;
            }
            if(withEl){
                mostrar(contGen);
            }
            calcularNextG();
            Individuo[] aux = poblacion;
            poblacion = nextGen;
            nextGen = aux;
            contGen++;
        }
        while(true){

        }
    }
}
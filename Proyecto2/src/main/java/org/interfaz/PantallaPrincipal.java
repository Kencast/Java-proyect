package org.interfaz;

import org.logica.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class PantallaPrincipal extends JFrame {
    private int [][] tP = {{0,0},{0,7},{0,14},{7,0},{7,14},{14,0},{14,7},{14,14}};
    private int [][] dL = {{0,3},{0,11},{2,6},{2,8},{3,0},{3,7},{3,14},{6,2},{6,6},{6,8},{6,12},{7,3},{7,11},{8,2},{8,6},
                                {8,8},{8,12},{11,0},{11,7},{11,14},{12,6},{12,8},{14,3},{14,11}};
    private int [][] tL = {{1,5},{1,9},{5,1},{5,5},{5,9},{5,13},{9,1},{9,5},{9,9},{9,13},{13,5},{13,9}};
    private int [][] dP = {{1,1},{2,2},{3,3},{4,4},{7,7},{10,10},{11,11},{12,12},{13,13},{1,13},{2,12},{3,11},{4,10},{10,4},
                                {11,3},{12,2},{13,1}};
    private Partida partida;
    private int cantJugadores;
    private JPanel principal;
    private JPanel tablero;
    private JPanel soporte;
    private Color[] arrayColor;
    private JButton selecto;
    private JLabel titulo;
    private int ejeX;
    private int ejeY;
    private JPanel opciones;
    private JButton aux;
    private JButton siguiente;
    private Result res;

    public PantallaPrincipal(int cantJ, Partida p){
        super("Scrabble");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        partida=p;
        cantJugadores=cantJ;
        arrayColor = new Color[7];
        arrayColor[0]=new Color(255, 255, 204); //color de las fichas
        arrayColor[1]=new Color(0, 204, 102); //color de las casillas
        arrayColor[2]=new Color(255, 51, 51);
        arrayColor[3]=new Color(255, 204, 204);
        arrayColor[4]=new Color(153, 204, 255);
        arrayColor[5]=new Color(51, 153, 255);
        arrayColor[6]=new Color(0, 102, 51);

        Toolkit tool= getToolkit();
        Dimension screen= tool.getScreenSize();
        setSize(screen);
        ejeX=screen.width/10;
        ejeY=screen.height/10;
        
        titulo=new JLabel();
        titulo.setBounds(ejeX*8-30, 20, ejeX*2-30, ejeY);
        titulo.setForeground(Color.DARK_GRAY);
        titulo.setFont(new Font("Arial", Font.BOLD, 30));

        principal=new JPanel(null);
        principal.setBackground(new Color(204, 229, 255));
        initSoporte();
        initTablero();
        initOpciones();
        initSiguiente();

        setContentPane(principal);
        principal.add(titulo);
        principal.add(opciones);
        principal.add(siguiente);
        empezar();
    }



    private void mostrarGanadores(){
        res=new Result(cantJugadores, partida);
        res.setVisible(true);
        dispose();
    }

    private void pasarTurno(){
        Jugador jug=partida.getJugadorActual();
        titulo.setText(jug.getNombre());
        opciones.setVisible(false);
        selecto=null;
        aux=null;
        modificarBotonesSoporte(jug);
    }
    
    private void verificar(){
        if(!partida.verificarTablero()){
            partida.reset();
            JOptionPane.showMessageDialog(principal, "Error: Jugada invalida");
            pasarTurno();
            modificarBotonesTablero();
        }
        else{
            if(partida.verificarGanar()){
                mostrarGanadores();
            }else{
                partida.pasarTurno();
                pasarTurno();
            }
        }
    }
    
    private void initSiguiente(){
        siguiente=new JButton("Siguiente");
        siguiente.setBounds(ejeX*9, ejeY*9-20, ejeX, 40);
        siguiente.setFont(new Font("Arial", Font.BOLD, 18));
        siguiente.setForeground(Color.DARK_GRAY);
        siguiente.setBackground(Color.WHITE);
        siguiente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verificar();
            }
        });
    }

    private void seleccionarFicha(){
        if(selecto!=null){
            selecto.setBackground(arrayColor[0]);
            selecto=null;
        }if(aux.getBackground() == arrayColor[0]){
            selecto = aux;
            aux.setBackground(Color.LIGHT_GRAY);
        }
        opciones.setVisible(false);
    }
    
    private void cambiarFicha(){
        Jugador jug=partida.getJugadorActual();
        if(partida.noHayCambios() || !jug.getPuedeMover()){
            jug.setPuedeMover(false);
            int n=(int) aux.getClientProperty("indice");
            partida.cambiarFicha(n);
            modificarBotonesSoporte(jug);
        }
        opciones.setVisible(false);
    }

    private void initOpciones(){
        opciones=new JPanel(new GridLayout(2, 1));
        opciones.setVisible(false);
        opciones.setBounds((int) (ejeX*7.7), ejeY*5, ejeX, ejeY);
        opciones.setBackground(arrayColor[6]);
        opciones.setBorder(BorderFactory.createLoweredBevelBorder());
        JButton[] arr =new JButton[2];
        for(int i=0; i<2; i++){
            arr[i]=new JButton();
            arr[i].setBackground(Color.WHITE);
            arr[i].setForeground(Color.BLACK);
            arr[i].setFont(new Font("Arial", Font.BOLD, 15));
        }
        arr[0].setText("Seleccionar");
        arr[1].setText("Cambiar");
        arr[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seleccionarFicha();
            }
        });
        arr[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cambiarFicha();
            }
        });
        opciones.add(arr[0]);
        opciones.add(arr[1]);
    }

    private void initSoporte(){
        soporte=new JPanel(new GridLayout(1, 7));
        soporte.setBackground(arrayColor[6]);
        soporte.setBorder(BorderFactory.createRaisedBevelBorder());
        soporte.setBounds(ejeX*7-60, ejeY*7, ejeX*3+50, 70);
        crearBotonesSoporte();
        principal.add(soporte);
    }

    private void initTablero(){
        tablero=new JPanel(new GridLayout(15, 15));
        tablero.setBackground(arrayColor[6]);
        tablero.setBounds(20, 10, ejeX*6, ejeY*9+10);
        crearBotonesTablero();
        principal.add(tablero);
    }

    private void mostrarOpciones(){
        opciones.setVisible(true);
    }

    private void empezar(){
        partida.empezar();
        selecto=null; aux=null;
        Jugador jug=partida.getJugadorActual();
        titulo.setText(jug.getNombre());
        modificarBotonesSoporte(jug);
    }

    private void crearBotonesSoporte(){
        Font f=new Font("Arial", Font.BOLD, 20);
        for(int i = 0; i < 7; i++){
            JButton b = new JButton("");
            b.setBackground(arrayColor[0]);
            b.putClientProperty("indice",i);
            b.setFont(f);
            b.setForeground(Color.DARK_GRAY);
            b.setBorder(BorderFactory.createRaisedBevelBorder());
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    aux= (JButton) e.getSource();
                    mostrarOpciones();
                }
            });
            soporte.add(b);
        }
    }

    private void crearBotonesTablero(){
        Font f=new Font("Arial", Font.BOLD, 20);
        for(int i = 0; i < 225; i++){
            JButton b = new JButton("");
            b.setBackground(arrayColor[1]);
            b.putClientProperty("indice",i);
            b.setForeground(Color.DARK_GRAY);
            b.setFont(f);
            b.setBorder(BorderFactory.createRaisedBevelBorder());
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton b = (JButton) e.getSource();
                    Jugador jug=partida.getJugadorActual();
                    if(!jug.getPuedeMover()) return;
                    if(b.getBackground()!=arrayColor[0] && selecto != null){
                        int indB = (int) b.getClientProperty("indice");
                        int indS = (int) selecto.getClientProperty("indice");
                        Ficha f=jug.tomarFicha(indS);
                        if(f instanceof Comodin){
                            String input=JOptionPane.showInputDialog(principal, "Ingrese la letra");
                            if(input!=null){
                                f.setLetra(input.charAt(0));
                            }
                            else return;
                        }
                        b.setText("" + f.getLetra());
                        b.setBackground(arrayColor[0]);
                        partida.insertarTablero(indS,indB,jug);
                        selecto.setText("");
                        selecto.setBackground(arrayColor[6]);
                        selecto=null;
                        modificarBotonesSoporte(jug);
                    }
                }
            });
            tablero.add(b);
        }
        modificarBotonesEspeciales();
    }

    private void modificarBotonesEspeciales(){
        for(int i = 0; i < 8; i++){
            JButton b = (JButton) tablero.getComponent(tP[i][0] * 15 + tP[i][1]);
            b.setBackground(arrayColor[2]);
            b.setText("3P");
        }
        for(int i = 0; i < 24; i++){
            JButton b = (JButton) tablero.getComponent(dL[i][0] * 15 + dL[i][1]);
            b.setBackground(arrayColor[4]);
            b.setText("2L");
        }
        for(int i = 0; i < 17; i++){
            JButton b = (JButton) tablero.getComponent(dP[i][0] * 15 + dP[i][1]);
            b.setBackground(arrayColor[3]);
            b.setText("2P");
        }
        for(int i = 0; i < 12; i++){
            JButton b = (JButton) tablero.getComponent(tL[i][0] * 15 + tL[i][1]);
            b.setBackground(arrayColor[5]);
            b.setText("3L");
        }
    }

    private void modificarBotonesSoporte(Jugador j){
        int i;
        for(i = 0; i < j.cantidadFichas(); i++){
            JButton b = (JButton) soporte.getComponent(i);
            b.setText("" + j.tomarFicha(i).getLetra());
            b.setBackground(arrayColor[0]);
        }
        limpiarExcesoSoporte(i);
    }

    private void limpiarExcesoSoporte(int i){
        while(i < 7){
            JButton b = (JButton) soporte.getComponent(i);
            b.setText("");
            b.setBackground(arrayColor[6]);
            i++;
        }
    }

    private void modificarBotonesTablero(){
        limpiarExcesoTablero();
        for(int i = 0; i < 225; i++){
            Ficha f = partida.consultarFichaTablero(i);
            if(f == null) continue;
            JButton b = (JButton) tablero.getComponent(i);
            b.setBackground(arrayColor[0]);
            b.setText("" + f.getLetra());
            b.putClientProperty("indice",i);
        }
    }

    private void limpiarExcesoTablero(){
        for(int i = 0; i < 225; i++){
            JButton b = (JButton) tablero.getComponent(i);
            b.setBackground(arrayColor[1]);
            b.setText("");
        }
        modificarBotonesEspeciales();
    }
}

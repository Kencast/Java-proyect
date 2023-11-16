package org.interfaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Inicio extends JFrame {
    private JPanel menu;
    private JPanel opciones;
    private JButton empezar;
    private JButton salir;
    private JLabel titulo;
    private Font fuente;

    public Inicio(){
        super("Scrabble");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        empezar=new JButton("Empezar Partida");
        salir=new JButton("Salir");
        menu=new JPanel(null);
        titulo=new JLabel("¡BIENVENIDOS AL JUEGO SCRABBLE!");
        fuente= new Font("Arial", Font.BOLD, 20);

        Toolkit tool= getToolkit();
        Dimension screen= tool.getScreenSize();
        setSize(screen);
        int x=screen.width/10, y= screen.height/10;

        titulo.setFont(new Font("Arial", Font.BOLD, 40));
        titulo.setBounds(x*2, y*2, x*6, y*3);
        titulo.setForeground(Color.WHITE);

        empezar.setBounds(x*4, y*5, x*2, y);
        empezar.setBackground(Color.WHITE);
        empezar.setFont(fuente);
        empezar.setForeground(Color.BLACK);

        salir.setBounds(x*4, y*6+20, x*2, y);
        salir.setFont(fuente);
        salir.setBackground(Color.RED);
        salir.setForeground(Color.WHITE);
        empezar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cantJugadores(x, y);
            }
        });
        salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        menu.add(titulo);
        menu.add(empezar);
        menu.add(salir);
        menu.setBackground(new Color(0, 51, 51));
        setContentPane(menu);
    }

    private void cantJugadores(int x, int y){
        opciones=new JPanel(null);
        opciones.setBackground(new Color(0, 51, 51));
        titulo.setText("ELIJA LA CANTIDAD DE JUGADORES");
        ActionListener accion=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton n=(JButton) e.getSource();
                cambiar((int) n.getClientProperty("valor"));
            }
        };
        JButton[] b =new JButton[3];
        for(int i=0; i<3; i++){
            b[i]=new JButton(Integer.toString(i+2));
            b[i].putClientProperty("valor", i+2);
            b[i].setBackground(Color.WHITE);
            b[i].setForeground(Color.BLACK);
            b[i].setFont(fuente);
            b[i].addActionListener(accion);
        }
        b[0].setBounds(x*3+30, y*5, x-10, y);
        b[1].setBounds(x*4+30, y*5, x-10, y);
        b[2].setBounds(x*5+30, y*5, x-10, y);
        opciones.add(titulo);
        opciones.add(b[0]); opciones.add(b[1]); opciones.add(b[2]);
        setContentPane(opciones);
    }

    void cambiar(int cantJ){
        PantallaSorteo part=new PantallaSorteo(cantJ);
        part.setVisible(true);
        dispose();
    }
}

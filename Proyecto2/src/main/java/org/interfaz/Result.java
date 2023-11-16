package org.interfaz;

import org.logica.Jugador;
import org.logica.Partida;

import javax.swing.*;
import java.awt.*;

public class Result extends JFrame {
    private Partida partida;
    private int cantJugadores;
    private int ejeX;
    private int ejeY;
    private JPanel ganador;
    private JPanel puestos;

    public Result(int cantJ, Partida part){
        super("Scrable");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Toolkit tool= getToolkit();
        Dimension screen= tool.getScreenSize();
        setSize(screen);
        ejeX=screen.width/10;
        ejeY= screen.height/10;
        partida=part;
        cantJugadores=cantJ;
        initGanador();
        setContentPane(ganador);
    }

    private void set(){
        Font f=new Font("Arial", Font.BOLD, 25);
        int n= partida.ganador();
        for(int i=0; i<cantJugadores; i++){
            Jugador j=partida.getJugador(i);
            JLabel b=new JLabel("   "+j.getNombre());
            b.setForeground(Color.WHITE);
            if(i==n) b.setForeground(Color.CYAN);
            b.setFont(f);
            puestos.add(b);
        }
        for(int i=0; i<cantJugadores; i++){
            Jugador j=partida.getJugador(i);
            JLabel b=new JLabel("        "+j.getPuntaje());
            b.setForeground(Color.WHITE);
            b.setFont(f);
            puestos.add(b);
        }
    }

    private void initGanador(){
        ganador=new JPanel(null);
        ganador.setBackground(new Color(153, 204, 255));
        JLabel t=new JLabel("Resultados");
        t.setForeground(Color.WHITE);
        t.setFont(new Font("Arial", Font.BOLD, 40));
        t.setBounds(ejeX*4, ejeY+20, ejeX*2, ejeY);
        puestos=new JPanel(new GridLayout(2, cantJugadores));
        puestos.setBackground(new Color(0, 204, 102));
        puestos.setBounds(ejeX*2, ejeY*4, ejeX*6, ejeY*2);
        puestos.setBorder(BorderFactory.createRaisedBevelBorder());
        ganador.add(t);
        ganador.add(puestos);
        set();
    }
}

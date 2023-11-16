package org.interfaz;

import org.logica.Jugador;
import org.logica.Partida;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class PantallaSorteo extends JFrame {
    private Partida partida;
    private JPanel principal;
    private JLabel titulo;
    private JPanel bot;
    private JButton empezar;
    private int num;
    public PantallaSorteo(int cantJ){
        super("Scrabble");
        partida=new Partida(cantJ);
        num=0;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        principal=new JPanel(null);
        bot=new JPanel(new GridLayout(2, cantJ));
        Toolkit tool= getToolkit();
        Dimension screen= tool.getScreenSize();
        setSize(screen);
        int x=screen.width/10, y= screen.height/10;

        titulo=new JLabel("Sorteo");
        titulo.setFont(new Font("Arial", Font.BOLD, 40));
        titulo.setBounds((int) (x *4.5), 20, x*6, y*2);
        titulo.setForeground(Color.WHITE);

        empezar=new JButton("Empezar");
        empezar.setBackground(Color.WHITE);
        empezar.setForeground(Color.BLACK);
        empezar.setFont(new Font("Arial", Font.BOLD, 16));
        empezar.setBounds(x*9, 20, x, 50);
        empezar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(num!=cantJ) return;
                if(partida.verificarSorteo()){
                    PantallaPrincipal p= new PantallaPrincipal(cantJ, partida);
                    p.setVisible(true);
                    dispose();
                }
                else{
                    JOptionPane.showMessageDialog(principal, "Empate");
                    num=0;
                    partida.sorteo();
                    reset(cantJ);
                }
            }
        });
        principal.add(empezar);

        int largo;
        if(cantJ%2==0) largo=x*(5-cantJ/2);
        else largo= (int) (x*3.5);
        bot.setBounds(largo, y*3, x*cantJ, y*4);
        bot.setBackground(new Color(153, 153, 255));
        for(int i=0; i<cantJ; i++){
            JButton b=new JButton("?");
            b.setBackground(Color.WHITE);
            b.setBorder(BorderFactory.createRaisedBevelBorder());
            b.setFont(new Font("Arial", Font.BOLD, 28));
            b.setForeground(Color.DARK_GRAY);
            b.putClientProperty("indice", i);
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton b=(JButton) e.getSource();
                    if(Objects.equals(b.getText(), "?")){
                        num+=1;
                        int n=(int) b.getClientProperty("indice");
                        Jugador jug=partida.getJugador(n);
                        if(jug.getLetra()=='*') b.setText("Cmd");
                        else b.setText(""+jug.getLetra());
                        b.setBackground(Color.LIGHT_GRAY);
                    }
                }
            });
            bot.add(b);
        }
        for(int i=1; i<=cantJ; i++){
            JLabel l=new JLabel("         Jugador " + Integer.toString(i));
            l.setFont(new Font("Arial", Font.BOLD, 15));
            l.setForeground(Color.BLACK);
            l.setForeground(Color.DARK_GRAY);
            bot.add(l);
        }

        principal.setBackground(new Color(153, 153, 255));
        principal.add(titulo);
        principal.add(bot);
        setContentPane(principal);
    }

    private void reset(int n){
        for(int i=0; i<n; i++){
            JButton b= (JButton) bot.getComponent(i);
            b.setText("?");
            b.setBackground(Color.WHITE);
        }
    }
    
}

package interfaz;
import com.mycompany.proyecto1.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import static java.lang.System.exit;


public class PantallaResultado extends JFrame{
    private JPanel results;
    private JPanel pantalla;
    private JLabel otro;
    private JButton jugar;
    private JButton salir;
    private Partida partida;
    private Color color;

    public PantallaResultado(Partida juego){
        super("Rummikub");
        this.partida = juego;
        Toolkit tool=getToolkit();
        Dimension screen=tool.getScreenSize(); //obtener el tamaño de la pantalla

        int x=screen.width/10, y=screen.height/10;
        setBounds(x*2, y, x*7, y*6);
        setLayout(null);
        setUndecorated(true);
        color=new Color(16, 8, 34);

        pantalla=new JPanel(null);
        pantalla.setBackground(color);
        pantalla.setBounds(0, 0, x*7, y*6);
        add(pantalla);

        //Crea un panel para presentar los resultados
        results=new JPanel(new GridLayout(3, 5));
        results.setBounds(50, 50, x*6, y*5);
        results.setBackground(color);
        generarLabels();
        pantalla.add(results);

        //Genera el titulo
        JLabel titulo=new JLabel("Resultados");
        Font tipo=new Font("Symbol", Font.BOLD, 20);
        titulo.setBounds(x*3, 5, 150, 25);
        titulo.setFont(tipo);
        titulo.setForeground(Color.RED);
        pantalla.add(titulo);

        //Genera el boton para salir
        JButton salir=new JButton("Salir");
        salir.setFont(new Font("Symbol", Font.BOLD, 16));
        salir.setBackground(Color.WHITE);
        salir.setBounds(40, 10, 200, 30);
        salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                mostrarMensaje(partida.ganadorTotal());
                exit(0);
            }
        });
        pantalla.add(salir);

        //Genera el boton para seguir jugando
        JButton jugar =new JButton("Jugar otra ronda");
            jugar.setFont(new Font("Symbol", Font.BOLD, 16));
            jugar.setBackground(Color.WHITE);
            jugar.setBounds(x*5, 10, 200, 30);
            jugar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setVisible(false);
                }
            });
        pantalla.add(jugar);
    }

    public void mostrar(){
        //Muestra esta ventana
        modificarLabels();
        setVisible(true);
    }

    private void mostrarMensaje(String mensaje){
        // Genera un JOptionPane apartir del string
        JOptionPane.showMessageDialog(this, mensaje);
    }
    private void generarLabels(){
        //Genera los labels para mostrar los resultados
        for(int i = 0; i < 15; i++){
            JLabel t = new JLabel();
            t.setBackground(color);
            t.setFont(new Font("Symbol", Font.BOLD, 17));
            t.setForeground(Color.WHITE);
            results.add(t);
        }
        JLabel t = (JLabel)results.getComponent(0);
        t.setText("");
        for(int i = 1; i < 5; i++){
            t = (JLabel)results.getComponent(i);
            t.setText("Jugador   " + i);
        }
        t = (JLabel)results.getComponent(5);
        t.setText("Resutados:   ");
        t = (JLabel)results.getComponent(10);
        t.setText("Puntaje Total: ");
    }

    private void modificarLabels(){
        //Actualiza los labels para mostrar los resultados mas recientes
        JLabel t;
        Jugador jug;
        for(int i=1; i<5; i++){
            t=(JLabel) results.getComponent(i);
            t.setForeground(Color.WHITE);
        }
        for(int i = 6, j = 0; i < 10; i++, j++){
            jug = partida.jugadorIndex(j);
            t = (JLabel) results.getComponent(i);
            t.setBackground(color);
            t.setText("     " + jug.getPuntos());
        }
        for(int i = 11, j = 0; i < 15; i++, j++){
            jug = partida.jugadorIndex(j);
            t = (JLabel)results.getComponent(i);
            t.setText("     " + jug.getPuntajeTotal());
        }
        //pone en verde al ganador de la ronda
        t=(JLabel) results.getComponent(partida.getNumGanador()+1);
        t.setForeground(Color.GREEN);
    }


}

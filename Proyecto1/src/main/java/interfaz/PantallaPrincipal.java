package interfaz;
import com.mycompany.proyecto1.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class PantallaPrincipal extends JFrame {
    private Color arrayColor[];
    private JPanel mesa;
    private JPanel soporte;
    private JPanel pantalla;
    private Partida partida;
    private JLabel titulo;
    private JLabel subtitulo;
    private JButton selecto = null;


    public PantallaPrincipal(){
        super("Rummikub");
        partida=new Partida();

        mesa =new JPanel(new GridLayout(8, 16));
        soporte=new JPanel(new GridLayout(2, 15));
        pantalla=new JPanel(null);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        arrayColor = new Color[7];
        arrayColor[0] = Color.BLUE;
        arrayColor[1] = Color.RED;
        arrayColor[2] = Color.BLACK;
        arrayColor[3] = Color.YELLOW;
        arrayColor[4] = Color.WHITE;
        arrayColor[5]= new Color(100, 30, 22);
        arrayColor[6]= new Color(22, 160, 133);
        //setUndecorated(true);

        Toolkit tool=getToolkit();
        Dimension screen=tool.getScreenSize();
        setSize(screen);
        int x=screen.width/10, y=screen.height/10;
        pantalla.setBounds(0, 0, getWidth(), getHeight());
        pantalla.setBackground(arrayColor[6]);
        mesa.setBackground(arrayColor[6]);
        mesa.setBounds(x*2, y*1, x*7, y*6); //puede ser cualquier size

        titulo=new JLabel();
        subtitulo=new JLabel();
        Font tipo=new Font("Symbol", Font.BOLD, 20);
        titulo.setBounds(x*5, 5, 100, 25);
        titulo.setFont(tipo);
        titulo.setForeground(Color.BLACK);
        subtitulo.setBounds(x*5-5, 25, 150, 25);
        subtitulo.setFont(tipo);
        subtitulo.setForeground(Color.BLACK);
        pantalla.add(titulo);
        pantalla.add(subtitulo);

        soporte.setBackground(arrayColor[5]);
        soporte.setBounds(x*2, y*8-50, x*6, y*2);
        add(pantalla, BorderLayout.CENTER);
        botonesMesa();
        pantalla.add(mesa);
        botonesSoporte();
        pantalla.add(soporte);
        partida.empezarPartida();
        inicioTurno();
    }

    private void inicioTurno(){
        titulo.setText("Ronda "+partida.getRondas());
        subtitulo.setText("Jugador "+(partida.getTurnoActual()+1));
        Jugador j=partida.actualJugador();
        modificarBotonesSoporte(j);
    }

    private void botonesMesa(){
        Font letra=new Font("Symbol", Font.BOLD, 14);
        JButton b;
        for(int i=0; i<128; i++){
            b=new JButton("");
            b.setBackground(arrayColor[5]);
            b.setFont(letra);
            b.setForeground(Color.WHITE);
            b.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton p= (JButton) e.getSource();
                    if(p.getBackground() == arrayColor[5] && selecto!=null){
                        p.setBackground(selecto.getBackground());
                        p.setText(selecto.getText());
                        p.setIcon(selecto.getIcon());
                        selecto = null;
                    }
                    else{selecto = p;}
                }
            });
            mesa.add(b);
            mesa.repaint();
        }
    }

    private void botonesSoporte(){
        for(int i = 0; i<30; i++){
            JButton b = new JButton("");
            b.setBackground(arrayColor[5]);
            b.setFont(new Font("Symbol", Font.BOLD, 15));
            b.setForeground(Color.WHITE);
            b.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton p= (JButton) e.getSource();
                    selecto = p;
                }
            });
            soporte.add(b);
            soporte.repaint();
        }
    }

    private void modificarBotonesSoporte(Jugador player){
        JButton b;
        for(int i = 0; i < player.cantFichas(); i++){
            Ficha f = player.consultarFichaSoporte(i);
            b=(JButton) soporte.getComponent(i);
            if(f instanceof Comodin){
                b.setBackground(Color.WHITE);
                ImageIcon icono = new ImageIcon(getClass().getResource("imagenComodin.png"));
                b.setIcon(icono);
            }
            else{
                b.setBackground(arrayColor[f.getColor()]);
                b.setText("" + f.getNum());
                b.putClientProperty("Indice", f.getIndex());
            }
        }
        soporte.repaint();
    }
}

// Calcula las coordenadas para centrar el panelCentro
//         int x = (panelPrincipal.getWidth() - panelCentro.getPreferredSize().width) / 2;
//         int y = (panelPrincipal.getHeight() - panelCentro.getPreferredSize().height) / 2;
//
//         panelCentro.setBounds(x, y, panelCentro.getPreferredSize().width, panelCentro.getPreferredSize().height);
//         panelPrincipal.add(panelCentro);


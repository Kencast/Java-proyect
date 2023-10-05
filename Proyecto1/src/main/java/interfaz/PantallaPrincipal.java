package interfaz;
import com.mycompany.proyecto1.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PantallaPrincipal extends JFrame {
    private JPanel mesa;
    private JPanel soporte;
    private JPanel pantalla;
    private Partida partida;

    public PantallaPrincipal(){
        super("Rummikub");
        partida=new Partida();
        //partida.empezarPartida();
        mesa =new JPanel(new GridLayout(8, 16));
        soporte=new JPanel(new GridBagLayout());
        pantalla=new JPanel(null);
        setLayout(new BorderLayout());
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setUndecorated(true);

        int x=getWidth(), y=getWidth();
        pantalla.setBounds(0, 0, x, y);
        pantalla.setBackground(Color.BLUE);
        mesa.setBackground(Color.WHITE);
        int x2=(x/10)*3, y2=(y/10)*2;
        mesa.setBounds(x2, y2, 800, 400); //puede ser cualquier size
        soporte.setBackground(Color.BLACK);
        add(pantalla, BorderLayout.CENTER);
        botonesMesa();
        pantalla.add(mesa);
        botonesSoporte();
        add(soporte, BorderLayout.SOUTH);
    }

    private void botonesMesa(){
        Font letra=new Font("Arial", Font.PLAIN, 10);
        JButton b;
        for(int i=0; i<128; i++){
            b=new JButton("");
            b.setBackground(Color.BLUE);
            b.setFont(letra);
            b.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showInternalMessageDialog(mesa,"Hola"); //mesa verdad?
                }
            });
            mesa.add(b);
        }
    }

    private void botonesSoporte(){
        for(int i = 0; i < 14; i++){
            JButton b = new JButton(" ");
            b.setBackground(Color.WHITE);
            b.setFont(new Font("Arial", Font.PLAIN, 10));
            b.setPreferredSize(new Dimension(40, 40));
            b.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(soporte, "A");
                }
            });
            soporte.add(b);
        }
    }

}

// Calcula las coordenadas para centrar el panelCentro
//         int x = (panelPrincipal.getWidth() - panelCentro.getPreferredSize().width) / 2;
//         int y = (panelPrincipal.getHeight() - panelCentro.getPreferredSize().height) / 2;
//
//         panelCentro.setBounds(x, y, panelCentro.getPreferredSize().width, panelCentro.getPreferredSize().height);
//         panelPrincipal.add(panelCentro);


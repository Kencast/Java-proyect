package interfaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame{
    private JPanel menuPrincipal;
    private JLabel tituloMenu;
    private JButton empezarPartidaButton;
    private JButton salirBoton;

    public Menu(){
        super("Rummikub");
        setContentPane(menuPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        empezarPartidaButton.addActionListener(new ActionListener() {
            //empieza la partida
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                mostrarPartida();
            }
        });
        salirBoton.addActionListener(new ActionListener() {
            //termina el programa
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private void mostrarPartida(){
        //crea la siguiente ventana y la muestra
        PantallaPrincipal p = new PantallaPrincipal();
        p.setVisible(true);
        dispose();
    }

}

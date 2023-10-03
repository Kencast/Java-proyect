package interfaz;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame{
    private JPanel menuPrincipal;
    private JLabel tituloMenu;
    private JButton empezarPartidaButton;
    private JButton salirButton;

    public Menu(){
        super("Rummikub");
        setContentPane(menuPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        empezarPartidaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                mostrarPartida();
            }
        });
    }

    private void mostrarPartida(){
        PantallaPrincipal p = new PantallaPrincipal();
        p.setVisible(true);
        dispose();
    }

}

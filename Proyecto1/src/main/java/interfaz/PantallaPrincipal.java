package interfaz;
import com.mycompany.proyecto1.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PantallaPrincipal extends JFrame {

    private JPanel pantalla;
    private JPanel soporte;

    public PantallaPrincipal(){
        super("Rummikub");
        setLayout(new BorderLayout());
        setSize(700, 600);
        add(pantalla, BorderLayout.NORTH);
        crearBotones();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pantalla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Obtén las coordenadas del clic
                int x = e.getX();
                int y = e.getY();
            }
        });
    }
    private void crearBotones(){
        soporte.setLayout(new FlowLayout());
        soporte.add(new JLabel("Soporte", SwingConstants.CENTER));
        for(int i = 0; i < 14; i++){
            JButton b = new JButton(" ");
            b.setBackground(Color.WHITE);
            b.setFont(new Font("Arial", Font.PLAIN, 10));
            b.setPreferredSize(new Dimension(40, 40));
            b.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(pantalla, "A");
                }
            });
            soporte.add(b);
        }
        add(soporte, BorderLayout.CENTER);
    }



}





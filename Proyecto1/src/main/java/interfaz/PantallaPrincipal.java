package interfaz;
import com.mycompany.proyecto1.*;

import javax.swing.*;
import javax.swing.border.Border;
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
    private Ficha arrayMesa[];

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
        arrayColor[3] = new Color(241, 196, 15);
        arrayColor[4] = Color.WHITE;
        arrayColor[5]= new Color(100, 30, 22);
        arrayColor[6]= new Color(22, 160, 133);
        //setUndecorated(true);
        arrayMesa = new Ficha[128];

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
            b.putClientProperty("posicion",i);
            b.putClientProperty("turno", 0);
            b.setForeground(Color.WHITE);
            b.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton p = (JButton) e.getSource();
                    Jugador j = partida.actualJugador();
                    int turno=(int)p.getClientProperty("turno");
                    if (p.getBackground() == arrayColor[5] && selecto != null) {
                        if(!partida.actualJugador().isPuedeModificar()){
                            int pos = (int) p.getClientProperty("posicion");
                            if(pos % 16 != 0){
                                JButton ant = (JButton) mesa.getComponent(pos-1);
                                int turnoAnt = (int) ant.getClientProperty("turno");
                                if(ant.getBackground() != arrayColor[5] && turnoAnt != partida.getTurnoActual()){return;}
                            }
                            if((pos+1) % 16 != 0 && pos != 127){
                                JButton sig = (JButton) mesa.getComponent(pos+1);
                                int turnoSig = (int) sig.getClientProperty("turno");
                                if(sig.getBackground() != arrayColor[5] && turnoSig != partida.getTurnoActual()){return;}
                            }
                        }
                        p.setBackground(selecto.getBackground());
                        p.setText(selecto.getText());
                        p.setIcon(selecto.getIcon());
                        p.putClientProperty("turno",partida.getTurnoActual());
                        int pos = (int) p.getClientProperty("posicion");
                        int place = (int) selecto.getClientProperty("place");
                        if(place == 2){
                           int index = (int) selecto.getClientProperty("Indice");
                            Ficha f = j.pedirFichaSoporte(index);
                            j.sacarFicha(index);
                            selecto = null;
                            arrayMesa[pos] = f;
                            modificarBotonesSoporte(j);
                        }else{
                            int posS = (int) selecto.getClientProperty("posicion");
                            arrayMesa[pos] = arrayMesa[posS];
                            arrayMesa[posS] = null;
                            selecto.setBackground(arrayColor[5]);
                            selecto.setText(" ");
                            selecto.setIcon(null);
                        }
                    }else if(j.isPuedeModificar() || turno==partida.getTurnoActual()){
                        selecto = p;
                        selecto.putClientProperty("place", 1); //Significa que esta en la mesa
                    }
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
            b.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
            b.setForeground(Color.WHITE);
            b.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton p= (JButton) e.getSource();
                    if(p.getBackground()!=arrayColor[5]){
                        if(selecto!=null) selecto.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
                        if(selecto==p) selecto = null;
                        else{
                            p.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                            selecto=p;
                            selecto.putClientProperty("place", 2); //Significa que está en el soporte
                        }
                    }
                    else{
                        if(selecto==null) return;
                        int turno = (int) selecto.getClientProperty("turno");
                        if(turno == partida.getTurnoActual()){
                            p.setText(selecto.getText());
                            p.setBackground(selecto.getBackground());
                            p.setIcon(selecto.getIcon());
                            selecto.setText("");
                            selecto.setBackground(arrayColor[5]);
                            selecto.setIcon(null);
                            int pos = (int) selecto.getClientProperty("posicion");
                            Jugador j = partida.actualJugador();
                            j.regresarFichaSoporte(arrayMesa[pos]);
                            modificarBotonesSoporte(j);
                        }
                    }
                }
            });
            soporte.add(b);
            soporte.repaint();
        }
    }

    private void modificarBotonesSoporte(Jugador player){
        int i;
        JButton b;
        for(i = 0; i < player.cantFichas(); i++){
            Ficha f = player.consultarFichaSoporte(i);
            b=(JButton) soporte.getComponent(i);
            if(f instanceof Comodin){
                b.setBackground(Color.WHITE);
                ImageIcon icono = new ImageIcon(getClass().getResource("imagenComodin.png"));
                b.setIcon(icono);
                b.putClientProperty("Indice", f.getIndex());
            }
            else{
                b.setBackground(arrayColor[f.getColor()]);
                b.setText("" + f.getNum());
                b.putClientProperty("Indice", f.getIndex());
            }
            b.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        }
        limpiarExceso(i);
        soporte.repaint();
    }

    private void limpiarExceso(int i){ //
        for(int j = i; j < 30; j++){
            JButton b = (JButton) soporte.getComponent(j);
            b.setBackground(arrayColor[5]);
            b.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
            b.setText("");
            b.setIcon(null);
        }
    }

    private boolean verificarMesa(){
        Combinaciones grupo = new Combinaciones();
        for(int i = 0; i < 128; i++){
           JButton b = (JButton) mesa.getComponent(i);
           if(b.getBackground() == arrayColor[5]){
               if(grupo.getTamaño() != 0){
                   if(grupo.verificar()){grupo = new Combinaciones();}
                   else return false;
               }
           }else{
               if(b.getBackground() == arrayColor[4]){
                   Ficha f = new Comodin();
                   grupo.insertar(f);
               }else{
                   int color = 3;
                   if(b.getBackground() == arrayColor[0]){color = 0;}
                   if(b.getBackground() == arrayColor[1]){color = 1;}
                   if(b.getBackground() == arrayColor[2]){color = 2;}
                  int num = Integer.parseInt(b.getText());
                   Ficha f = new FichaNormal(num,color);
                   grupo.insertar(f);
               }
           }
        }
        return true;
    }
}

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
    private JButton selecto;
    private int cantFichasInicial;
    private PantallaResultado result;

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

        result=new PantallaResultado(partida);

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

        JButton boton=new JButton("Siguiente jugador");
        boton.setFont(new Font("Symbol", Font.BOLD, 16));
        boton.setBackground(Color.WHITE);
        boton.setBounds(x*9-60, 10, 200, 30);
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                siguienteTurno();
            }
        });
        pantalla.add(boton);

        JButton comer=new JButton("Tomar ficha");
        comer.setFont(new Font("Symbol", Font.BOLD, 16));
        comer.setBackground(Color.WHITE);
        comer.setBounds(10, y*8, 200, 30);
        comer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Jugador actual=partida.actualJugador();
                if(fichasIniciales()==actual.cantFichas()) {
                    if(partida.existenFichas()){
                        sinFichas();
                        return;
                    }
                    partida.comerFicha();
                    partida.reemplazarMesa();
                    limpiarExcesoMesa();
                    partida.cambiarTurno();
                    inicioTurno();
                }
                else mostrarMensaje("No puede tomar ficha: ya jugó una ficha de su soporte");
            }
        });
        pantalla.add(comer);

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
        selecto=null;
        j.respaldarSoporte();
        partida.respaldarMesa();
        guardarFichasInicial(j.cantFichas());
        modificarBotonesSoporte(j);
        modificarBotonesMesa();
    }

    private void guardarFichasInicial(int cant){
        cantFichasInicial=cant;
    }
    private int fichasIniciales(){return cantFichasInicial;}

    private void mostrarMensaje(String mensaje){
        JOptionPane.showMessageDialog(this, mensaje);
    }

    private void ganador(){
        partida.setNumGanador(partida.getTurnoActual());
        partida.calcularPuntosRonda();
        result.mostrar();
        partida.siguienteRonda();
        limpiarExcesoMesa();
        inicioTurno();
    }

    private void sinFichas(){
        partida.jugadorGanador();
        result.mostrar();
        partida.siguienteRonda();
        limpiarExcesoMesa();
        inicioTurno();
    }

    private void siguienteTurno(){
        Jugador jug=partida.actualJugador();
        if(jug.cantFichas()<fichasIniciales()){
            if(!verificarMesa()){
                mostrarMensaje("La jugada es incorrecta");
                restaurarJuego(jug);
                return;
            }
            if(!jug.isPuedeModificar() && jug.getPuntos()<30){
                mostrarMensaje("La primera jugada tiene que sumar mínimo 30 puntos");
                restaurarJuego(jug);
                return;
            }
            if(jug.cantFichas()==0){
                ganador();
                return;
            }
            jug.setPuedeModificar(true);
            partida.cambiarTurno();
            inicioTurno();
        }

    }
    private void botonesMesa(){
        Font letra=new Font("Symbol", Font.BOLD, 14);
        JButton b;
        for(int i=0; i<128; i++){
            b=new JButton("");
            b.setBackground(arrayColor[5]);
            b.setFont(letra);
            b.putClientProperty("posicion",i);
            b.putClientProperty("place", 1);
            b.putClientProperty("turno", -1);
            b.setForeground(Color.WHITE);
            b.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton p = (JButton) e.getSource();
                    Jugador j = partida.actualJugador();
                    int turno=(int)p.getClientProperty("turno");
                    if (p.getBackground() == arrayColor[5] && selecto != null) {
                        if(!j.isPuedeModificar()){
                            int pos = (int) p.getClientProperty("posicion");
                            if(pos % 16 != 0){
                                JButton ant = (JButton) mesa.getComponent(pos-1);
                                int turnoAnt = (int) ant.getClientProperty("turno");
                                if(ant.getBackground() != arrayColor[5] && turnoAnt != partida.turnoReal()){return;}
                            }
                            if((pos+1) % 16 != 0 && pos != 127){
                                JButton sig = (JButton) mesa.getComponent(pos+1);
                                int turnoSig = (int) sig.getClientProperty("turno");
                                if(sig.getBackground() != arrayColor[5] && turnoSig != partida.turnoReal()){return;}
                            }
                        }
                        p.setBackground(selecto.getBackground());
                        p.setText(selecto.getText());
                        p.setIcon(selecto.getIcon());
                        int pos = (int) p.getClientProperty("posicion");
                        int place = (int) selecto.getClientProperty("place");
                        if(place == 2){
                            int index = (int) selecto.getClientProperty("Indice");
                            Ficha f = j.pedirFichaSoporte(index);
                            j.sacarFicha(index);
                            selecto = null;
                            p.putClientProperty("turno", partida.turnoReal());
                            partida.insertarFichaTablero(pos,f);
                            modificarBotonesSoporte(j);
                        }else{
                            int posS = (int) selecto.getClientProperty("posicion");
                            partida.insertarFichaTablero(pos,partida.obtenerFichaTablero(posS));
                            partida.sacarFichaTablero(posS);
                            p.putClientProperty("turno", selecto.getClientProperty("turno"));
                            selecto.setBackground(arrayColor[5]);
                            selecto.putClientProperty("turno", -1);
                            selecto.setText("");
                            selecto.setIcon(null);
                            selecto=null;
                        }
                    }else if(p.getBackground()!=arrayColor[5]){
                        if(j.isPuedeModificar() || turno==partida.turnoReal()) {
                            selecto = p;
                            selecto.putClientProperty("place", 1); //Significa que está en la mesa
                        }
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
            b.putClientProperty("place", 2);
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
                    else if(selecto!=null){
                        int turno = (int) selecto.getClientProperty("turno");
                        int lugar=(int) selecto.getClientProperty("place");
                        if(turno < partida.turnoReal() || lugar==2) return;
                        p.setText(selecto.getText());
                        p.setBackground(selecto.getBackground());
                        p.setIcon(selecto.getIcon());
                        selecto.setText("");
                        selecto.putClientProperty("turno", -1);
                        selecto.setBackground(arrayColor[5]);
                        selecto.setIcon(null);
                        int pos = (int) selecto.getClientProperty("posicion");
                        Jugador j = partida.actualJugador();
                        j.regresarFichaSoporte(partida.obtenerFichaTablero(pos));
                        partida.sacarFichaTablero(pos);
                        selecto=null;
                        modificarBotonesSoporte(j);
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
                b.setIcon(null);
                b.putClientProperty("Indice", f.getIndex());
            }
            b.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
            b.putClientProperty("turno", partida.turnoReal());
        }
        limpiarExcesoSoporte(i);
        soporte.repaint();
    }

    private void limpiarExcesoSoporte(int i){
        for(int j = i; j < 30; j++){
            JButton b = (JButton) soporte.getComponent(j);
            b.setBackground(arrayColor[5]);
            b.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
            b.setText("");
            b.putClientProperty("turno", -1);
            b.setIcon(null);
        }
    }

    private void limpiarExcesoMesa(){
        for(int i = 0; i < 128; i++){
            JButton b = (JButton) mesa.getComponent(i);
            b.setBackground(arrayColor[5]);
            b.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
            b.setText("");

            b.setIcon(null);
        }
        mesa.repaint();
    }

    private boolean verificarMesa(){
        Combinaciones grupo = new Combinaciones();
        for(int i = 0; i < 128; i++){
           JButton b = (JButton) mesa.getComponent(i);
           if(b.getBackground() == arrayColor[5]){
               if(grupo.getTamano() != 0){
                   if(grupo.verificar()){grupo = new Combinaciones();}
                   else return false;
               }
           }else{
               if(i % 16 == 0){
                   if(grupo.getTamano() != 0){
                       if(grupo.verificar()){grupo = new Combinaciones();}
                       else return false;
                   }
               }
               Ficha f = partida.obtenerFichaTablero(i);
               grupo.insertar(f);
           }
        }
        return true;
    }

    private void modificarBotonesMesa(){
        for(int i = 0; i < 128; i++){
            if(partida.obtenerFichaTablero(i) != null){
                Ficha f = partida.obtenerFichaTablero(i);
                JButton b = (JButton) mesa.getComponent(i);
                if(f instanceof Comodin){
                    b.setText("");
                    b.setBackground(arrayColor[4]);
                    ImageIcon icono = new ImageIcon(getClass().getResource("imagenComodin.png"));
                    b.setIcon(icono);
                }else{
                    b.setText("" + f.getNum());
                    b.setBackground(arrayColor[f.getColor()]);
                }
                b.putClientProperty("Indice",f.getIndex());
                b.putClientProperty("place",1);
            }
        }
        mesa.repaint();
    }

    private void restaurarJuego(Jugador j){
        partida.reemplazarMesa();
        j.reemplazarSoporte();
        j.setPuntos(0);
        selecto=null;
        modificarBotonesSoporte(j);
        limpiarExcesoMesa();
        modificarBotonesMesa();
    }
}

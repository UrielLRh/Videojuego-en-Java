/*
    RUDE Games
    Luis Uriel Avila Vargas     A00815578
    Diego Adolfo Jose Villa     A0081
    Roberto Arcos Moreno        A0

*/

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LearnVille;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.util.List;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


/**
 *
 * @author Uriel Avila
 */

//----------------------------------------------------------------------------------------------------------------
public class LearnVille extends JFrame implements Runnable,
        KeyListener, MouseListener, MouseMotionListener{

    private static final long serialVersionUID = 1L;

    //  Declaraciones de variables de graficos y recursos
    // IMAGENES DE FONDO
    private ImageIcon imaInicio;           // Imagen de la pantalla Inicial
    private ImageIcon imaFondo;            // Color de Fondo
    private ImageIcon imaDialogo;          // Imagen de dialogo
    private ImageIcon imaMenuPrincipal;    // Imagen de la pantalla Inicial
    private ImageIcon imaHistoria;         // Imagen de Modo Historia
    private ImageIcon imaPerfil;           // Imagen fondo de Perfil
    private ImageIcon imaQuizes;           // Imagen fondo de Quizes
    private ImageIcon imaPerdedor;         // Imagen fondo de pant. Perdedor
    private ImageIcon imaGanador;          // Imagen fondo de pant. Ganador
    private ImageIcon imaOpciones;         // Imagen fondo de pant. Opciones
    private ImageIcon imaControles;        // Imagen fondo de pant. Controles
    private ImageIcon imaMiniJuego1;       // Imagen fondo de Minijuego 1
    private ImageIcon imaMiniJuego2;       // Imagen fondo de Minijuego 2
    private ImageIcon imaMiniJuego3;       // Imagen fondo de Minijuego 3
    private ImageIcon imaSalida;           // Imagen fondo de pant. Salida
    
    // PERFIL ==================================================================
    private String sNombreJugador;          // Nombre del jugador actual
    private int iPuntuacionTotal;
    private String sArchivoPerfil;
    // Puntuaciones totales por minijuego
    private int iPuntuacionMiniJuego1;
    private int iPuntuacionMiniJuego2;
    private int iPuntuacionQuizes;
    
    // Puntuaciones maximas
    private int iPuntuacionMAX_M1;
    private int iPuntuacionMAX_M2;
    private int iPuntuacionMAX_Q3;
    
    // Puntuaciones por materia
    private int iPuntuacionMatematicas;
    private int iPuntuacionEspañol;
    private int iPuntuacionHistoria;
    private int iPuntuacionEstructuraIngles;
    private int iPuntuacionMatematicas_M1;
    
    //==========================================================================
    // OBJETOS DEL JUEGO
    private Reloj RelojMG;                 // Cuenta Regresiva
    private Objeto Nube1;
    private Objeto Nube2;
    private Objeto Nube3;
    private Objeto Nube4;
    private Objeto Nube5;
    private Objeto Nube6;
    
    // PERSONAJE
    private Personaje PerJugador;
    private int iVelocidadJugador;
    private int iDireccionPer;
    
    // MINIJUEGO 1
    /*private Respuesta Respuesta1;
    private Respuesta Respuesta2;
    private Respuesta Respuesta3;*/
    private int iPuntuacionM1;
    private String [][]matPreguntas;
    private int iVelocidadResp;
    private LinkedList<Objeto> lstElementos;
    private Objeto oPersonaje;
    private boolean bPausa;
    
    // BANDERAS DE PANTALLA
    private char cPantallaActual;          // Pantalla Activa
    private String sBotonActivo;           // Boton activo (Aquel que cambia)
    
    // VARIABLES DEL JUEGO
    private int iX;                        // Posicion X de mouse
    private int iY;                        // Posicion Y de mouse
    private final int iAnchoPantalla;
    private final int iAltoPantalla;
    private int iDuracionAnim;
    private float fTransparenciaAni;
    // 50 Ciclos igual a 1 seg
    private int iTiempo;                   // Control de Tiempo
    private int iPantalla;                 // Pantalla Activa
    private int iPantallaAnterior;
    private boolean bPerdio;
    private boolean bGanar;
    
    //================================================================ PANTALLAS
    // PANTALLA 0 - ANIMACIÓN
    private ImageIcon imaLogoLetras;
    private ImageIcon imaLogoPuño;
    
    // PANTALLA 1 - BIENVENIDA
    private boolean bEntraJuego;
    private ImageIcon imaTitulo;
    
    // PANTALLA 2 - MENU PRINCIPAL
    private Boton btnInicio;               // Boton para Iniciar Juego
    private Boton btnHistoria;             // Boton para entrar a Historia
    private Boton btnQuizes;               // Boton para entrar a Quizes
    private Boton btnOpciones;             // Boton para entrar a Opciones
    private Boton btnPerfil;               // Boton para entrar al Perfil
    private Boton btnSalida;               // Boton para entrar al Perfil
    
    // PANTALLA 3 - MAPA
    private Boton btnMiniJuego1;
    private Boton btnMiniJuego2;
    private Boton btnMiniJuego3;
    
    // PANTALLA 4 - MINIJUEGO 1
    private boolean bEntraMiniJuego1;
    private List<List<String>> lstPreguntasM1;
    private List<Integer> lstRespuestasM1;
    private String ArchivoMiniJuego1;
    
    // PANTALLA 5 - MINIJUEGO 2
    private String [][]matPalabras;
    private Objeto [][]matObjetos;
    private int iCantidadObjetos;
    private int iPuntuacionM2;
    private boolean bEntraMiniJuego2;
    
    // PANTALLA 6 - QUIZES
        // Variables del Cuadro de Dialogo
    private int iAnchoMAX;
    private int iAltoMAX;
    private int iCuadroAlto;
    private int iCuadroAncho;
    private int iCuadroPosX;
    private int iCuadroPosY;
        // Respuestas
    private Respuesta respRespA;
    private Respuesta respRespB;
    private Respuesta respRespC;
    private Respuesta respRespD;
    private List<List<List<String>>> lstPreguntas;
    private List<List<Integer>> lstRespuestas;
    private String sNombreArchivo;
    private String sPregunta;
    private String sPregunta2;
    private String sRespuestaActiva;
    private boolean bRespuesta;
    private int iMateria;
    private int iNumeroPregunta;
    private int iPreguntasHechas;
    private int iPuntuacion_Quiz;
    private boolean bEntraQuizes;
    private boolean bDespliegaPregunta;
    private Color colorCuadro;
    
    // PANTALLA 7 - PERFIL
    private Boton btnModificar;
    private Boton btnCambiarUsuario;
    private Boton btnNuevoPerfil;
    private Boton btnEliminar;
    
    // PANTALLA 8 - CONFIGURACIONES
    private Boton btnRestablecerDatos;
    private Boton btnNoche;
    private Boton btnDia;
    private URL urlBackInicio;
    private URL urlBackInicio2;
    
    // PANTALLA 3,4,5 - MENU
    private Boton btnBackJuego;
    private Boton btnSalirPartida;
    private Boton btnAyuda;
    
    // PANTALLA 12 - SALIDA
    private Boton btnConfirmar;
    private Boton btnRegresar;
    
    // PANTALLAS 5,6,7,8 - BOTON ATRAS
    private Boton btnAtras;
    private boolean bBotonAtras;
    //==========================================================================
    
    // OBJETO GRAFICO
    private Graphics graGraficoDB;      // Grafico que se usa para dibujar
    private Image imaImageDB;           // Objeto para dibujar el Buffer
    
    //  Declaraciones de variables de graficos y recursos
    private ImageIcon imaBG;
    private ImageIcon imaColision;

    // DIEGO
    private int iNumPreg;
    private int iPuntMJ1;
    private boolean bEntradaUnica = true;
    
    // MUSICA
    private SoundClip soundSmack;
    private SoundClip soundCancion1;
    private SoundClip soundCancion2;
    private SoundClip soundCorrecto;
    private SoundClip soundIncorrecto;
    
    //  Declaraciones de variables posiciones
    //  Posiciones personaje
    private int iPosX;
    private int iPosY;
    
//----------------------------------------------------------------------------------------------------------------
    public LearnVille()
    {
        //Define el título de la ventana
        setTitle("LearnVille");
        setSize(1280, 720);
        iAnchoPantalla = 1280;
        iAltoPantalla = 720;
        setVisible(true);
        setResizable(false);
        //Define la operación que se llevará acabo cuando la ventana sea cerrada.
        // Al cerrar, el programa terminará su ejecución
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //  Mouse
        addMouseListener(this);
        addMouseMotionListener(this);
        // Teclado
        addKeyListener(this);
        
        //  Declaracion de un hilo
        Thread th = new Thread(this);
        //  Empieza el hilo
        th.start();
        //Define el tamaño inicial de la ventana
        
        init();
        init2();

        soundCancion1 = new SoundClip("mainSound.wav");
        soundCancion1.setLooping(true);
        soundCancion2 = new SoundClip("sonidoFondo.wav");
        soundCancion2.setLooping(true);
        soundCorrecto = new SoundClip("Correct-answer.wav");
        soundIncorrecto = new SoundClip("Wrong-answer.wav");
        soundSmack = new SoundClip("Punch.wav");
        soundCancion2.play();
    }
//----------------------------------------------------------------------------------------------------------------
    /**
     * Metodo que inicializa las variables y objetos del juego
     */
    public void init()
    {
        //String[] fontNames=GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        //System.out.println(Arrays.toString(fontNames));
        
        // Variables Booleanas de Control de Pantallas
        /* Las pantallas son las siguientes
            b -> Pantalla de Bienvenida;
            m -> Pantalla de Menu Principal
            h -> Pantalla del mapa Modo Historia
            o -> Pantalla de Opciones
            c -> Pantalla de Controles
            1 -> Minijuego 1
            2 -> Minijuego 2
            3 -> Minijuego 3
            p -> Pantalla del Perfil
            W -> Pantalla de Ganar
            L -> Pantalla de Perder
        */
        
        
        cPantallaActual = 'b';      // Se inicializa en Pantalla de Bienvenida
        bGanar = false;
        bPerdio = false;
        iVelocidadJugador = 4;      // Velocidad con la que se mueve el personaje
        
        /* DIRECCIONES
            1 - Arriba
            2 - Abajo
            3 - Izquierda
            4 - Derecha
        */
        iDireccionPer = 0;
        
        // Boton Activo - Es el boton que cambia de Color (Raton posicionado en el)
        sBotonActivo = "boton1";
        
        // Se inicializa el tiempo
        iTiempo = 0;
        iPantalla = 0;
        iPantallaAnterior = 0;
        bDespliegaPregunta = false;
        sPregunta2 = "";
        sPregunta = "Hola";
        sRespuestaActiva = "";
        iMateria = 1;
        bRespuesta = false;
        
        
        //Inicializaciones Temporales aqui
        iAnchoMAX = 800;
        iAltoMAX = 500;
        iCuadroAlto = 0;
        iCuadroAncho = 0;
        
        // Nombres de Archivos
        sNombreArchivo = "Quizes.txt";
        ArchivoMiniJuego1 = "MiniJuego1.txt";
        sArchivoPerfil = "PerfilUsuario.txt";
        
        //======================================================================
        // URL's Imagenes
        URL urlTitulo = this.getClass().getResource("titulo.png");
        URL urlInicio = this.getClass().getResource("FondoMain.png");
        urlBackInicio = this.getClass().getResource("Cielo1.png");
        urlBackInicio2 = this.getClass().getResource("Cielo2.png");
        URL urlHistoria = this.getClass().getResource("pantallaIsla.png");
        URL urlQuizes = this.getClass().getResource("pantQuizes.png");
        URL urlOpciones = this.getClass().getResource("pantConfiguraciones.png");
        URL urlControles = this.getClass().getResource("pantControles.png");
        URL urlPerfil = this.getClass().getResource("pantPerfil.png");
        URL urlPerdiste = this.getClass().getResource("pantPerdiste.png");
        URL urlGanaste = this.getClass().getResource("pantGanaste.png");
        URL urlMinijuego1 = this.getClass().getResource("Fondo_Juego1.png");
        URL urlMinijuego2 = this.getClass().getResource("Fondo_Minijuego2.png");
        URL urlSalida = this.getClass().getResource("pantAbandonarJuego.png");
        
        // URL's Botones - Menu
        URL urlBotonHistoria = this.getClass().getResource("btnJugarOFF.png");
        URL urlBotonHistoria2 = this.getClass().getResource("btnJugarON.png");
        URL urlBotonOpciones = this.getClass().getResource("btnOpcionesOFF.png");
        URL urlBotonOpciones2 = this.getClass().getResource("btnOpcionesON.png");
        URL urlBotonPerfil = this.getClass().getResource("btnPerfilOFF.png");
        URL urlBotonPerfil2 = this.getClass().getResource("btnPerfilON.png");
        URL urlBotonQuiz = this.getClass().getResource("btnQuizesOFF.png");
        URL urlBotonQuiz2 = this.getClass().getResource("btnQuizesON.png");
        URL urlBotonSalida = this.getClass().getResource("btnSalirOFF.png");
        URL urlBotonSalida2 = this.getClass().getResource("btnSalirON.png");
        // Otros
        URL urlDialogo1 = this.getClass().getResource("Dialogo1.png");
        
        // Otros Objetos e Imagenes
        imaDialogo = new
                ImageIcon(Toolkit.getDefaultToolkit().getImage(urlDialogo1));
        
        // Pantalla de Bienvenida - Nubes
        URL urlNube = this.getClass().getResource("nube1.png");
        Nube1 = new Objeto(420, 150,
                Toolkit.getDefaultToolkit().getImage(urlNube));
        urlNube = this.getClass().getResource("nube2.png");
        Nube2 = new Objeto(170, 420,
                Toolkit.getDefaultToolkit().getImage(urlNube));
        urlNube = this.getClass().getResource("nube3c.png");
        Nube3 = new Objeto(910, 430,
                Toolkit.getDefaultToolkit().getImage(urlNube));
        urlNube = this.getClass().getResource("nube4.png");
        Nube4 = new Objeto(1130, 300,
                Toolkit.getDefaultToolkit().getImage(urlNube));
        urlNube = this.getClass().getResource("NubeH1.png");
        Nube5 = new Objeto(150, 130,
                Toolkit.getDefaultToolkit().getImage(urlNube));
        urlNube = this.getClass().getResource("NubeH2.png");
        Nube6 = new Objeto(1000, 520,
                Toolkit.getDefaultToolkit().getImage(urlNube));
        
        // Inicializacion - Fondos
        imaTitulo = new ImageIcon(Toolkit.getDefaultToolkit()
                    .getImage(urlTitulo));
        imaInicio = new ImageIcon(Toolkit.getDefaultToolkit()
                    .getImage(urlInicio));
        imaFondo = new ImageIcon(Toolkit.getDefaultToolkit()
                    .getImage(urlBackInicio));
        imaHistoria = new ImageIcon(Toolkit.getDefaultToolkit()
                    .getImage(urlHistoria));
        imaQuizes = new ImageIcon(Toolkit.getDefaultToolkit()
                    .getImage(urlQuizes));
        imaPerfil = new ImageIcon(Toolkit.getDefaultToolkit()
                    .getImage(urlPerfil));
        imaOpciones = new ImageIcon(Toolkit.getDefaultToolkit()
                    .getImage(urlOpciones));
        imaControles = new ImageIcon(Toolkit.getDefaultToolkit()
                    .getImage(urlControles));
        imaGanador = new ImageIcon(Toolkit.getDefaultToolkit()
                    .getImage(urlGanaste));
        imaPerdedor = new ImageIcon(Toolkit.getDefaultToolkit()
                    .getImage(urlPerdiste));
        imaSalida = new ImageIcon(Toolkit.getDefaultToolkit()
                    .getImage(urlSalida));
        // Minijuegos
        imaMiniJuego1 = new ImageIcon(Toolkit.getDefaultToolkit()
                    .getImage(urlMinijuego1));
        imaMiniJuego2 = new ImageIcon(Toolkit.getDefaultToolkit()
                    .getImage(urlMinijuego2));

        // Inicializacion - Botones Salida
        URL urlBotonX = this.getClass().getResource("Seguro.png");
        URL urlBotonY = this.getClass().getResource("Seguro2.png");
        btnConfirmar = new Boton((int)(iAnchoPantalla*0.33),
                (int)(iAltoPantalla*0.60),
                Toolkit.getDefaultToolkit().getImage(urlBotonX),
                Toolkit.getDefaultToolkit().getImage(urlBotonY));
        urlBotonX = this.getClass().getResource("Regresar.png");
        urlBotonY = this.getClass().getResource("Regresar2.png");
        btnRegresar = new Boton(0, (int)(iAltoPantalla*0.60),
                Toolkit.getDefaultToolkit().getImage(urlBotonX),
                Toolkit.getDefaultToolkit().getImage(urlBotonY));
        btnRegresar.setPosX((int)(iAnchoPantalla*0.75 - iAnchoPantalla*0.08 -
                btnRegresar.getAncho()));
        
        // Inicializacion Botones - Configuración
        URL urlBotonC1 = this.getClass().getResource("btnDayON.png");
        URL urlBotonC2 = this.getClass().getResource("btnDayOFF.png");
        btnDia = new Boton((int)(iAnchoPantalla*0.28),
                (int)(iAltoPantalla*0.50),
                Toolkit.getDefaultToolkit().getImage(urlBotonC1),
                Toolkit.getDefaultToolkit().getImage(urlBotonC2));
        urlBotonC1 = this.getClass().getResource("btnNightON.png");
        urlBotonC2 = this.getClass().getResource("btnNightOFF.png");
        btnNoche = new Boton(0, (int)(iAltoPantalla*0.50),
                Toolkit.getDefaultToolkit().getImage(urlBotonC1),
                Toolkit.getDefaultToolkit().getImage(urlBotonC2));
        btnNoche.setPosX((int)(iAnchoPantalla*0.75 - iAnchoPantalla*0.03 -
                btnNoche.getAncho()));
        
        // Inicializacion - Botones Menu de minijuegos
        URL urlBotonM1 = this.getClass().getResource("RegresarON.png");
        URL urlBotonM2 = this.getClass().getResource("RegresarOFF.png");
        btnBackJuego = new Boton(0, (int)(iAltoPantalla*0.30),
                Toolkit.getDefaultToolkit().getImage(urlBotonM1),
                Toolkit.getDefaultToolkit().getImage(urlBotonM2));
        urlBotonM1 = this.getClass().getResource("SalirON.png");
        urlBotonM2 = this.getClass().getResource("SalirOFF.png");
        btnSalirPartida = new Boton(0, (int)(iAltoPantalla*0.47),
                Toolkit.getDefaultToolkit().getImage(urlBotonM1),
                Toolkit.getDefaultToolkit().getImage(urlBotonM2));
        urlBotonM1 = this.getClass().getResource("AyudaON.png");
        urlBotonM2 = this.getClass().getResource("AyudaOFF.png");
        btnAyuda = new Boton(0, (int)(iAltoPantalla*0.64),
                Toolkit.getDefaultToolkit().getImage(urlBotonM1),
                Toolkit.getDefaultToolkit().getImage(urlBotonM2));
        btnBackJuego.setPosX(iAnchoPantalla/2-btnBackJuego.getAncho()/2);
        btnSalirPartida.setPosX(iAnchoPantalla/2-btnSalirPartida.getAncho()/2);
        btnAyuda.setPosX(iAnchoPantalla/2-btnAyuda.getAncho()/2);
        
        // Inicializacion - Botones Bienvenida
            // URL's Botones - Bienvenida
        URL urlBotonInicio1 = this.getClass().getResource("btnInicioOFF.png");
        URL urlBotonInicio2 = this.getClass().getResource("btnInicioON.png");
        btnInicio = new Boton(0, 550,
                Toolkit.getDefaultToolkit().getImage(urlBotonInicio1), 
                Toolkit.getDefaultToolkit().getImage(urlBotonInicio2));
        btnInicio.setPosX((int)(iAnchoPantalla/2 - btnInicio.getAncho()/2));
        
        // Inicializacion - Botones Menu
        int iTamañoBotones = 250;
        btnHistoria = new Boton(640-iTamañoBotones/2, 300,
                Toolkit.getDefaultToolkit().getImage(urlBotonHistoria), 
                Toolkit.getDefaultToolkit().getImage(urlBotonHistoria2));
        btnOpciones = new Boton(640-iTamañoBotones/2, 380,
                Toolkit.getDefaultToolkit().getImage(urlBotonOpciones), 
                Toolkit.getDefaultToolkit().getImage(urlBotonOpciones2));
        btnPerfil = new Boton(640-iTamañoBotones/2, 460,
                Toolkit.getDefaultToolkit().getImage(urlBotonPerfil), 
                Toolkit.getDefaultToolkit().getImage(urlBotonPerfil2));
        btnQuizes = new Boton(640-iTamañoBotones/2, 540,
                Toolkit.getDefaultToolkit().getImage(urlBotonQuiz), 
                Toolkit.getDefaultToolkit().getImage(urlBotonQuiz2));
        btnSalida = new Boton(640-iTamañoBotones/2, 620,
                Toolkit.getDefaultToolkit().getImage(urlBotonSalida), 
                Toolkit.getDefaultToolkit().getImage(urlBotonSalida2));
        
        // Boton Atras
        URL urlBotonAtras = this.getClass().getResource("btnRegresarOFF.png");
        URL urlBotonAtras2 = this.getClass().getResource("btnRegresarON.png");
        btnAtras = new Boton(0,0,
                Toolkit.getDefaultToolkit().getImage(urlBotonAtras),
                Toolkit.getDefaultToolkit().getImage(urlBotonAtras2));
        btnAtras.setPosX(iAnchoPantalla - btnAtras.getAncho()-20);
        btnAtras.setPosY(30);
        
        // Inicializacion - Mapa Historia
        URL urlBotonON = this.getClass().getResource("btLevel1ONN.png");
        URL urlBotonOFF = this.getClass().getResource("btLevel1OFF.png");
        btnMiniJuego1 = new Boton(390, 470,
                Toolkit.getDefaultToolkit().getImage(urlBotonOFF), 
                Toolkit.getDefaultToolkit().getImage(urlBotonON));
        urlBotonON = this.getClass().getResource("btLevel2ONN.png");
        urlBotonOFF = this.getClass().getResource("btLevel2OFF.png");
        btnMiniJuego2 = new Boton(600, 330,
                Toolkit.getDefaultToolkit().getImage(urlBotonOFF), 
                Toolkit.getDefaultToolkit().getImage(urlBotonON));
        urlBotonON = this.getClass().getResource("btLevel3ONN.png");
        urlBotonOFF = this.getClass().getResource("btLevel3OFF.png");
        btnMiniJuego3 = new Boton(700, 140,
                Toolkit.getDefaultToolkit().getImage(urlBotonOFF), 
                Toolkit.getDefaultToolkit().getImage(urlBotonON));
        
        // Inicializacion de Botones de Quizes
        URL urlBotonR = this.getClass().getResource("RespuestaInactiva.png");
        URL urlBotonR2 = this.getClass().getResource("RespuestaActiva.png");
        respRespA = new Respuesta((int)(iAnchoPantalla-iCuadroAncho)/2 + 50,
                (int)(iAltoPantalla*0.50), 1,
                Toolkit.getDefaultToolkit().getImage(urlBotonR),
                Toolkit.getDefaultToolkit().getImage(urlBotonR2));
        respRespB = new Respuesta((int)(iAnchoPantalla-iCuadroAncho)/2 + 50,
                (int)(iAltoPantalla*0.70), 2,
                Toolkit.getDefaultToolkit().getImage(urlBotonR),
                Toolkit.getDefaultToolkit().getImage(urlBotonR2));
        respRespC = new Respuesta(300,
                (int)(iAltoPantalla*0.50), 3,
                Toolkit.getDefaultToolkit().getImage(urlBotonR),
                Toolkit.getDefaultToolkit().getImage(urlBotonR2));
        respRespD = new Respuesta(300,
                (int)(iAltoPantalla*0.70), 4,
                Toolkit.getDefaultToolkit().getImage(urlBotonR),
                Toolkit.getDefaultToolkit().getImage(urlBotonR2));
        
        
        // Inicializacion del Reloj
        int iTiempoReloj = 20;
        RelojMG = new Reloj(iTiempoReloj, 100, 200);
        URL urlReloj;
        for(int iNum = 0; iNum < iTiempoReloj+1; iNum++)
        {
            urlReloj = this.getClass().getResource(Integer.toString(iNum)+".png");
            RelojMG.agregarImagen(Toolkit.getDefaultToolkit().getImage(urlReloj),iNum);
        }
        
        // Inicializacion del Personaje
        PerJugador = new Personaje(100,iAltoPantalla/2);
        URL urlPersonaje = this.getClass().getResource("Zorro_Frontal.png");
        PerJugador.setImageIcon(Toolkit.getDefaultToolkit().getImage(urlPersonaje), 0);
        urlPersonaje = this.getClass().getResource("Zorro_Frontal.png");
        PerJugador.setImageIcon(Toolkit.getDefaultToolkit().getImage(urlPersonaje), 1);
        urlPersonaje = this.getClass().getResource("Zorro_Izq_1.png");
        PerJugador.setImageIcon(Toolkit.getDefaultToolkit().getImage(urlPersonaje), 2);
        urlPersonaje = this.getClass().getResource("Zorro_Der_1.png");
        PerJugador.setImageIcon(Toolkit.getDefaultToolkit().getImage(urlPersonaje), 3);
        
        
        // Inicializacion de Animación
        fTransparenciaAni = 0f;
        iDuracionAnim = 400;
        URL urlLogoLetras = this.getClass().getResource("logo5.png");
        URL urlLogoPuño = this.getClass().getResource("fistbump_fondo4.gif");
        imaLogoLetras = new ImageIcon(
                Toolkit.getDefaultToolkit().getImage(urlLogoLetras));
        imaLogoPuño = new ImageIcon(
                Toolkit.getDefaultToolkit().getImage(urlLogoPuño));
        
        // Inicialización de Minijuegos
        initMiniJuego2();
        initJugador();
        // Inicializar Quizes
        try
        {
            // Intentar abrir archivo
            initQuizes();    //lee el contenido del archivo
        } catch(IOException ex) {
            System.out.println("Error en " + ex.toString());
        }
        try
        {
            // Intentar abrir archivo
            cargarPreguntasM1();    //lee el contenido del archivo
        } catch(IOException ex) {
            System.out.println("Error en " + ex.toString());
        }
        
        // True inidica que va a ser la primera entrada al minijuego
        // Cambia a false cuando ya se entro
        bEntraQuizes = true;
        bEntraMiniJuego2 = true;
        bEntraJuego = true;
    }
//----------------------------------------------------------------------------------------------------------------
    public void cargarPregunta()
    {
        
    }
    
    public void initQuizes() throws IOException
    {
        iNumeroPregunta = 1;
        BufferedReader fileIn;
        try {
                fileIn = new BufferedReader(new FileReader(sNombreArchivo));
        } catch (FileNotFoundException e){
            // Si no existe el archivo con las preguntas, desplegar mensaje de error
            String nl = System.getProperty("line.separator");
            JOptionPane.showMessageDialog(null,
                    "No hay información para hacer los Quizes." + nl +
                    "Por favor contacte a PrepaNet para resolver el problema");
            // Regresar a la pantalla de Menu
            return; // Finalizar función
        }
        iPreguntasHechas = 0;
        lstPreguntas = new ArrayList<>();  
        lstRespuestas = new ArrayList<>();
        int iMaterias = 3;
        
        // Ejecutar ciclo por cada materia
        for (int i = 0; i < iMaterias; i++)
        {
            String sLinea = fileIn.readLine(); // Se lee la materia
            // Leer Materia
            int iPosX = sLinea.indexOf(":");
            // Tomar la cantidad de preguntas de la materia
            int iCantPreguntas = Integer.parseInt(sLinea.substring(iPosX + 1));
            System.out.println("Cantidad Preguntas: "+iCantPreguntas);
            sLinea = sLinea.substring(0, iPosX);
            // Ejecutar ciclo cada pregunta
            List<List<String>> list2 = new ArrayList<>();
            List<String> list3 = new ArrayList<>();
            list3.add(sLinea);
            list2.add(list3);   //<- Se agrega la materia
            System.out.println("Materia: "+list2.get(0).get(0));
            sLinea = fileIn.readLine();
            
            // Lista de respuestas para preguntas de una materia
            List<Integer> lstResps = new ArrayList<>();
            
            while(!"---".equals(sLinea))
            {
                
                // Se vacia la lista nivel 3
                list3 = new ArrayList<>();
                
                // Agregar la pregunta a lista nivel 3
                list3.add(sLinea);
                System.out.println("Pregunta: "+list3.get(0));
                
                // Mientras no se hayan agregado las 4 respuestas 
                int iResp = 1;
                while(list3.size() < 5)
                {
                    // Leer una respuesta
                    sLinea = fileIn.readLine();
                    // Cortar el insiso de la respuesta y el punto
                    sLinea = sLinea.substring(2);

                    // Verificar si tiene la marca de respuesta correcta
                    //System.out.println("L: "+sLinea.substring(0, 1));
                    if(">".equals(sLinea.substring(0, 1)))
                    {
                        // Quitar la marca a la respuestas
                        sLinea = sLinea.substring(1);
                        // Se guarda la respuesta correcta
                        lstResps.add(list3.size());
                        System.out.println("Size: "+list3.size());
                        System.out.println("Respuesta: "+sLinea);
                    }
                    // Guardar respuesta
                    list3.add(sLinea);
                    System.out.println("Linea: "+list3.get(list3.size() - 1));
                }
                list2.add(list3);   // Se agrega una pregunta con respuestas
                sLinea = fileIn.readLine();
            }
            lstRespuestas.add(lstResps);    // Se agregan las respuestas
            lstPreguntas.add(list2);
        }
    }
    
    public void cargarPreguntasM1() throws IOException
    {
        BufferedReader fileIn;
        try {
                fileIn = new BufferedReader(new FileReader(ArchivoMiniJuego1));
        } catch (FileNotFoundException e){
            // Si no existe el archivo con las preguntas, desplegar mensaje de error
            String nl = System.getProperty("line.separator");
            JOptionPane.showMessageDialog(null,
                    "No hay información para el MiniJuego1." + nl +
                    "Por favor contacte a PrepaNet para resolver el problema");
            // Regresar a la pantalla de Menu
            return; // Finalizar función
        }
        lstPreguntasM1 = new ArrayList<>();  
        lstRespuestasM1 = new ArrayList<>();
        //System.out.println("Hola");
        String sLinea = fileIn.readLine(); // Se lee la primera linea
        // Mientras haya mas respuestas
        while(sLinea != null)
        {
            // Ejecutar ciclo cada pregunta
            List<String> list2 = new ArrayList<>();
            // Agregar pregunta a la lista nivel 2
            list2.add(sLinea);
            System.out.println(sLinea);
            
            for(int iC = 1; iC < 5; iC++)
            {
                sLinea = fileIn.readLine();
                if(">".equals(sLinea.substring(0, 1)))
                {
                    // Quitar la marca a la respuestas
                    sLinea = sLinea.substring(1);
                    // Se guarda la respuesta correcta
                    lstRespuestasM1.add(iC);
                    //System.out.println("Size: "+list3.size());
                    //System.out.println("Respuesta: "+sLinea);
                }
                list2.add(sLinea);
                System.out.println("sLinea "+iC+" "+sLinea);
                System.out.println("lista "+iC+" "+list2.get(iC));
            }
            lstPreguntasM1.add(list2);
            sLinea = fileIn.readLine();
        }
    }
//----------------------------------------------------------------------------------------------------------------
    public void initMiniJuego1()
    {
        
    }
//----------------------------------------------------------------------------------------------------------------
    public void initMiniJuego2()
    {
        // Inicializacion de Minijuego 2
        
        //
        bPausa = false;
        PerJugador.setPosX(100);
        PerJugador.setPosY(iAltoPantalla/2);
        
        iCantidadObjetos = 4;
        matObjetos = new Objeto[iCantidadObjetos][iCantidadObjetos];
        int iTamañoCuadro = 146;
        int iSeparacion = 10;
        int iPosicionX = 360;
        int iPosicionY = 60;
        for(int iC = 0; iC < iCantidadObjetos; iC++)
        {
            for(int iR = 0; iR < iCantidadObjetos; iR++)
            {
                Objeto X = new Objeto(iPosicionX + iTamañoCuadro*iC +
                        iSeparacion*iC, iPosicionY  + iTamañoCuadro*iR +
                        iSeparacion*iR, iTamañoCuadro, iTamañoCuadro,
                        220, "Hola");
                matObjetos[iC][iR] = X;
            }
        }
    }
    
    public void initJugador()
    {
        sNombreJugador = "";
        iPuntuacionTotal = 0;
        
        // Puntuaciones totales por minijuego
        iPuntuacionMiniJuego1 = 250;
        iPuntuacionMiniJuego2 = 300;
        iPuntuacionQuizes = 100;
    
        // Puntuaciones maximas
        iPuntuacionMAX_M1 = 0;
        iPuntuacionMAX_M2 = 0;
        iPuntuacionMAX_Q3 = 0;
    
    // Puntuaciones por materia
        iPuntuacionMatematicas = 250;
        iPuntuacionEspañol = 250;
        iPuntuacionHistoria = 250;
        iPuntuacionEstructuraIngles = 250;
        iPuntuacionMatematicas_M1 = 250;
    }
    
//----------------------------------------------------------------------------------------------------------------    
    /**
     * Metodo que inicializa las variables y objetos del minijuego 2
     */
    public void init2()
    {        
        bEntraMiniJuego1 = true;
        iNumPreg = 0;
        iPuntMJ1 = 0;
        
        URL urlImaBG = this.getClass().getResource("imaBG.png");
        URL urlImaSO = this.getClass().getResource("perPersonaje1.png");
        URL urlImaCo = this.getClass().getResource("imaColision.png");
        
        imaBG = new ImageIcon(Toolkit.getDefaultToolkit().getImage(urlImaBG));
        imaColision = new ImageIcon(Toolkit.getDefaultToolkit().getImage(urlImaCo));

        oPersonaje = new Objeto(35, 404, Toolkit.getDefaultToolkit()
                .getImage(urlImaSO));
        
        // Lista de opciones
        lstElementos = new LinkedList<Objeto>();
        
        int iPosObj = 241;
        int iPosOX = 0;
        int iPosOY = 0;
        
        for(int iJ = 0; iJ < 4; iJ++) {
            
            URL urlEle = this.getClass().getResource("imaCub1.png");
                    
            if(iJ == 0) iPosOY = iPosObj;
            else
            {           
                iPosObj = iPosObj + 119;
                iPosOY = iPosObj;
            }
            
            iPosOX =  (int)(Math.random()*(2)) ;
            iPosOX = (300 * iPosOX) + 1280 ;
            
            if (iJ == 0)
                urlEle = this.getClass().getResource("imaCub1.png");
            else if(iJ == 1)
                urlEle = this.getClass().getResource("imaCub2.png");
            else if(iJ == 2)
                urlEle = this.getClass().getResource("imaCub3.png");
            else if(iJ == 3)
                urlEle = this.getClass().getResource("imaCub4.png");
            
            Objeto oCubo = new Objeto(iPosOX, iPosOY, Toolkit.
                        getDefaultToolkit().getImage(urlEle));
                
            lstElementos.add(oCubo);   
        }

    }
    
    public void pausa(Graphics g)
    {
        g.setColor(new Color(24,24,24,140));
        g.fillRect(0, 0, iAnchoPantalla, iAltoPantalla);
        g.setColor(new Color(240,240,255,150));
        // Cuadro
        // param 1: 25% de Ancho Pantalla | Posicion X
        // param 2: 26% de Alto Pantalla  | Posicion en Y
        // param 3: 50% de Ancho Pantalla | Anchura del cuadro
        // param 4: 60% de Alto Pantalla  | Altura del cuadro
        // param 5: Ezquina
        // param 6: Ezquina
        g.fillRoundRect((int)(iAnchoPantalla*0.25), (int) (iAltoPantalla*0.22),
                (int)(iAnchoPantalla*0.50),(int)(iAltoPantalla*0.60), 20,20);
        
        // Botones
        g.drawImage(btnBackJuego.getImageI(), btnBackJuego.getPosX(),
                    btnBackJuego.getPosY(), this);
        g.drawImage(btnSalirPartida.getImageI(), btnSalirPartida.getPosX(),
                    btnSalirPartida.getPosY(), this);
        g.drawImage(btnAyuda.getImageI(), btnAyuda.getPosX(),
                    btnAyuda.getPosY(), this);
        if(sBotonActivo == "btnRegresar")
        {
            g.drawImage(btnBackJuego.getImageI2(), btnBackJuego.getPosX(),
                    btnBackJuego.getPosY(), this);
        }
        else if(sBotonActivo == "btnSalir")
        {
            g.drawImage(btnSalirPartida.getImageI2(), btnSalirPartida.getPosX(),
                    btnSalirPartida.getPosY(), this);
        }
        else if(sBotonActivo == "btnAyuda")
        {
            g.drawImage(btnAyuda.getImageI2(), btnAyuda.getPosX(),
                    btnAyuda.getPosY(), this);
        }
    }
//----------------------------------------------------------------------------------------------------------------
    public void dibujaFondo(Graphics g)
    {
        g.drawImage(imaFondo.getImage(), 0, 0, this);
        g.drawImage(Nube4.getImageI(), Nube4.getPosX(),
                Nube4.getPosY(), this);
        g.drawImage(imaInicio.getImage(), 0, 0, this);
        g.drawImage(imaTitulo.getImage(), (int)(iAnchoPantalla/2 - 
                imaTitulo.getIconWidth()/2), 100, this);
        g.drawImage(Nube1.getImageI(), Nube1.getPosX(),
                Nube1.getPosY(), this);
        g.drawImage(Nube2.getImageI(), Nube2.getPosX(),
                Nube2.getPosY(), this);
        g.drawImage(Nube3.getImageI(), Nube3.getPosX(),
                Nube3.getPosY(), this);
        actualizaPantBienvenida();
    }
    
//----------------------------------------------------------------------------------------------------------------
    public void botonAtras(Graphics g)
    {
        bBotonAtras = true;
        if("btnAtras".equals(sBotonActivo))
        {
            g.drawImage(btnAtras.getImageI2(), btnAtras.getPosX(),
                    btnAtras.getPosY(), this);
        }
        else
        {
            g.drawImage(btnAtras.getImageI(), btnAtras.getPosX(),
                    btnAtras.getPosY(), this);
        }
    }
//----------------------------------------------------------------------------------------------------------------
    /**
     * Metodo que pinta las pantallas del juego
     */
    public void paint2(Graphics g)
    {
        bBotonAtras = false;
        g.setFont(new Font("Arial", Font.PLAIN, 32));
        g.setColor(Color.white);
        Graphics2D g2 = (Graphics2D) g;
//==============================================================================
        if(imaInicio != null && iPantalla == 0)
        {
            pausarEjecucion();
            Composite c2 = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f);
            g2.setComposite(c2);
            g.setColor(new Color(30,30,30));
            g.fillRect(0, 0, 1280, 720);
            if(iTiempo > 50)
            {
                if(iTiempo > 50 && iTiempo < 320)
                {
                    if(iTiempo == 155)
                    {
                        // Reproducir Sonido de puño
                        soundSmack.play();
                    }
                    // Mostrar puño
                    if(iTiempo > 140)
                    {
                        g.drawImage(imaLogoPuño.getImage(),
                            iAnchoPantalla/2-imaLogoPuño.getIconWidth()/2-80,
                            iAltoPantalla/2-imaLogoPuño.getIconHeight()/2-110,
                            this);
                    }
                    Composite c = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
                            fTransparenciaAni);
                    g2.setComposite(c);
                    g.drawImage(imaLogoLetras.getImage(),
                            iAnchoPantalla/2-imaLogoLetras.getIconWidth()/2,
                            iAltoPantalla/2-imaLogoLetras.getIconHeight()/2+100,
                            this);
                    if(fTransparenciaAni < 1f) fTransparenciaAni += 0.008f;
                }
                else if(iTiempo < 440)
                {
                    if(fTransparenciaAni >= 0)
                    {
                        fTransparenciaAni -= 0.01f;
                        Composite c = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
                                fTransparenciaAni);
                        g2.setComposite(c);
                        g.drawImage(imaLogoPuño.getImage(),
                                iAnchoPantalla/2-imaLogoPuño.getIconWidth()/2-80,
                                iAltoPantalla/2-imaLogoPuño.getIconHeight()/2-110,
                                this);
                        g.drawImage(imaLogoLetras.getImage(),
                                iAnchoPantalla/2-imaLogoLetras.getIconWidth()/2,
                                iAltoPantalla/2-imaLogoLetras.getIconHeight()/2+100,
                                this);
                    }
                }
                else
                {
                    Composite c = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
                            1f);
                    g2.setComposite(c);
                    iPantalla++;
                    soundCancion2.stop();
                }
            }
        }
//==============================================================================
        else if (imaInicio != null && iPantalla == 1)
        {
            // Pantalla de Bienvenida
            //  Dibuja el fondo
            dibujaFondo(g);
            
            // Iniciar Musica
            if(bEntraJuego)
            {
                //soundCancion2.stop();
                soundCancion1.play();
                bEntraJuego = false;
                /*if(bEntradaUnica)
                {
                    try
                    {
                        leePerfil();
                    } catch(IOException ex) {
                        System.out.println("Error en " + ex.toString());
                    }
                    bEntradaUnica = false;
                }*/
            }
            
            // Determina que boton Pintar (Activo o Inactivo)
            if(sBotonActivo == "boton1")
            {
                g.drawImage(btnInicio.getImageI(), btnInicio.getPosX(),
                btnInicio.getPosY(), this);
            }
            else
            {
                g.drawImage(btnInicio.getImageI2(), btnInicio.getPosX(),
                btnInicio.getPosY(), this);
            }
        }
//==============================================================================
        else if(iPantalla == 2)
        {
            // Pantalla del Menu Principal
            //g.drawImage(imaMenuPrincipal.getImage(), 0, 0, this);
            //  Dibuja el fondo
            dibujaFondo(g);
            
            // Iniciar Musica
            if(bEntraJuego)
            {
                //soundCancion2.stop();
                soundCancion1.play();
                bEntraJuego = false;
            }
            
            // Botones
            g.drawImage(btnHistoria.getImageI(), btnHistoria.getPosX(),
                    btnHistoria.getPosY(), this);
            g.drawImage(btnOpciones.getImageI(), btnOpciones.getPosX(),
                    btnOpciones.getPosY(), this);
            g.drawImage(btnPerfil.getImageI(), btnPerfil.getPosX(),
                    btnPerfil.getPosY(), this);
            g.drawImage(btnQuizes.getImageI(), btnQuizes.getPosX(),
                    btnQuizes.getPosY(), this);
            g.drawImage(btnSalida.getImageI(), btnSalida.getPosX(),
                    btnSalida.getPosY(), this);
            // Boton Activo
            if(sBotonActivo == "botonHistoria")
            {
                g.drawImage(btnHistoria.getImageI2(), btnHistoria.getPosX(),
                    btnHistoria.getPosY(), this);
                /*g.drawImage(imaDialogo.getImage(), btnHistoria.getPosX() -
                        imaDialogo.getIconWidth() - 10, btnHistoria.getPosY() -
                        btnHistoria.getAlto() - 4, this); */               
            }
            else if(sBotonActivo == "botonOpciones")
            {
                g.drawImage(btnOpciones.getImageI2(), btnOpciones.getPosX(),
                    btnOpciones.getPosY(), this);
                /*g.drawImage(imaDialogo.getImage(), btnOpciones.getPosX() -
                        imaDialogo.getIconWidth() - 10, btnOpciones.getPosY() -
                        btnOpciones.getAlto() - 4, this);*/
            }
            else if(sBotonActivo == "botonPerfil")
            {
                g.drawImage(btnPerfil.getImageI2(), btnPerfil.getPosX(),
                    btnPerfil.getPosY(), this);
                /*g.drawImage(imaDialogo.getImage(), btnPerfil.getPosX() -
                        imaDialogo.getIconWidth() - 10, btnPerfil.getPosY() -
                        btnPerfil.getAlto() - 4, this);*/
            }
            else if(sBotonActivo == "botonQuizes")
            {
                g.drawImage(btnQuizes.getImageI2(), btnQuizes.getPosX(),
                    btnQuizes.getPosY(), this);
                /*g.drawImage(imaDialogo.getImage(), btnQuizes.getPosX() -
                        imaDialogo.getIconWidth() - 10, btnQuizes.getPosY() -
                        btnQuizes.getAlto() - 4, this);*/
            }
            else if(sBotonActivo == "botonSalida")
            {
                g.drawImage(btnSalida.getImageI2(), btnSalida.getPosX(),
                    btnSalida.getPosY(), this);
                /*g.drawImage(imaDialogo.getImage(), btnSalida.getPosX() -
                        imaDialogo.getIconWidth() - 10, btnSalida.getPosY() -
                        btnSalida.getAlto() - 4, this);*/
            }
            //this.getContentPane().add(Panel1);
            //Panel1.paint(g);
            actualizaPantBienvenida();
        }
//==============================================================================
        else if(iPantalla == 3)
        {
            // Pantalla de Modo Historia
            g.drawImage(imaHistoria.getImage(), 0, 0, this);
            //g.drawString("Tiempo: "+ iTiempo, 30, 130);
            actualizaPantReloj();
            //g.drawImage(RelojMG.despliegaReloj(), RelojMG.getX(),
            //        RelojMG.getY(), this);
            g.drawImage(Nube5.getImageI(), Nube5.getPosX(),
                    Nube5.getPosY(), this);
            g.drawImage(Nube6.getImageI(), Nube6.getPosX(),
                    Nube6.getPosY(), this);
            g.drawImage(btnMiniJuego1.getImageI(),
                    btnMiniJuego1.getPosX(), btnMiniJuego1.getPosY(), this);
            g.drawImage(btnMiniJuego2.getImageI(),
                    btnMiniJuego2.getPosX(), btnMiniJuego2.getPosY(), this);
            g.drawImage(btnMiniJuego3.getImageI(),
                    btnMiniJuego3.getPosX(), btnMiniJuego3.getPosY(), this);
            
            // Mostrar boton de atras
            botonAtras(g);
            
            if(sBotonActivo == "botonMiniJuego1")
            {
                g.drawImage(btnMiniJuego1.getImageI2(), btnMiniJuego1.getPosX(),
                    btnMiniJuego1.getPosY(), this);                
            }
            else if(sBotonActivo == "botonMiniJuego2")
            {
                g.drawImage(btnMiniJuego2.getImageI2(), btnMiniJuego2.getPosX(),
                    btnMiniJuego2.getPosY(), this);
            }
            else if(sBotonActivo == "botonMiniJuego3")
            {
                g.drawImage(btnMiniJuego3.getImageI2(), btnMiniJuego3.getPosX(),
                    btnMiniJuego3.getPosY(), this);
            }
        }
//==============================================================================
       else if(iPantalla == 4)
        {
            // Diego
            g.drawImage(imaBG.getImage(), 0, 0, this);
            if(!bGanar)
            {
                if(bPausa)
                {
                    pausa(g);
                }
                else
                {
                    g.drawImage(oPersonaje.getImageI(), oPersonaje.getPosX(), 
                            oPersonaje.getPosY(), this);

                    for(Objeto oCubos : lstElementos)
                    {
                        if (oCubos != null) {
                            g.drawImage(oCubos.getImageI(),
                                    oCubos.getPosX(), oCubos.getPosY(), this);
                        }
                    }

                    actualizaMiniJuego();
                    checaColisionM1();
                    
                    g.setColor(new Color(100,166,56,160));
                    g.fillRoundRect(110, 90, 1040, 
                            100, 15, 15);
                    
                    g.setColor(new Color(255,255,255,230));
                    g.setFont(new Font("Arial", Font.BOLD, 42));
                    g.drawString("PUNTUACION: "+iPuntMJ1, 50, 80);

                    g.setFont(new Font("Arial Narrow",Font.PLAIN, 50));
                    g.setColor(new Color(20,20,20,190));
                    g.drawString(lstPreguntasM1.get(iNumPreg).get(0), 150, 160);
                    
                    g.setFont(new Font("Helvetica",Font.PLAIN, 25));
                    g.setColor(Color.black);
                    for(int iI=0; iI < lstElementos.size(); iI++)
                    {
                        Objeto oCubos = (Objeto) lstElementos.get(iI);
                        g.drawString(lstPreguntasM1.get(iNumPreg).get(iI+1),
                                oCubos.getPosX() + 80, oCubos.getPosY()+48);
                    }
                }
            }
            else
            {
                g.setColor(new Color(166,166,56,180));
                g.fillRoundRect(200, 100, 880, 
                        500, 50, 50);
                g.setColor(Color.black);
                g.setFont(new Font("Eras Demi ITC", Font.PLAIN, 55));
                g.drawString("FIN DE LA PARTIDA", 360, 300);
                g.setFont(new Font("Eras Demi ITC", Font.PLAIN, 65));
                g.drawString("PUNTUACION: " + iPuntMJ1, 480, 400);
                botonAtras(g);
            }
        }
//==============================================================================
        else if(iPantalla == 5)
        {
            // Pantalla de 2do Minijuego
            g.drawImage(imaFondo.getImage(), 0, 0, this);
            g.drawImage(imaMiniJuego2.getImage(), 0, 0, this);

            if(bPausa)
            {
                pausa(g);
            }
            else
            {
                // Dibujar Cuadros a Pisar
                for(int iR = 0; iR < iCantidadObjetos; iR++)
                    for(int iC = 0; iC < iCantidadObjetos; iC++)
                    {
                        g.setColor(matObjetos[iR][iC].getColorInactivo());
                        g.fillRect(matObjetos[iR][iC].getPosX(),
                                matObjetos[iR][iC].getPosY(),
                                matObjetos[iR][iC].getiAlto(),
                                matObjetos[iR][iC].getiAncho());
                    }

                // Dibujar Personaje
                if(iDireccionPer > 0)
                    g.drawImage(PerJugador.getImage(iDireccionPer - 1),
                            PerJugador.getPosX(), PerJugador.getPosY(), this);
                else
                    g.drawImage(PerJugador.getImage(1), PerJugador.getPosX(),
                            PerJugador.getPosY(), this);
                
                // Actualizar Juego
                actualizaMiniJuego2();
            }
        }
//==============================================================================
        else if(iPantalla == 6)
        {
            // Pantalla de Quizes
            // PRIMERA ENTRADA A LA PANTALLA
            if(bEntraQuizes)
            {
                
            }

            dibujaFondo(g);
            
            // PAUSA
            if(bPausa)
            {
                pausa(g);
            }
            // JUEGO CORRIENDO
            else if(iPreguntasHechas <= 10)
            {
                // Elementos de los Quizes
                
                actualizaQuizes();
                g.setColor(new Color(255,255,255,230));
                g.setFont(new Font("Arial", Font.PLAIN, 42));
                g.drawString("PUNTUACION: "+iPuntuacion_Quiz, 50, 80);
                g.drawString("PREGUNTA #: "+iPreguntasHechas, iAnchoPantalla-400, 80);
                // Cuadro de fondo
                g.setColor(new Color(166,166,56,180));
                g.fillRoundRect(iCuadroPosX, iCuadroPosY, iCuadroAncho, 
                        iCuadroAlto, 50, 50);
                g.setColor(Color.white);
                // Cuando el cuadro se termina de pintar
                // Desplegar la pregunta y las respuestas
                if(iCuadroAncho >= iAnchoMAX)
                {
                    g.setFont(new Font("Arial Narrow", Font.BOLD, 42));
                    g.drawString("PREGUNTA", iCuadroPosX+50, iCuadroPosY+80);
                    g.setFont(new Font("Arial Narrow", Font.PLAIN, 35));
                    
                    if("".equals(sPregunta2))
                    {
                        g.drawString(sPregunta,
                                iCuadroPosX+40, iCuadroPosY+140);
                    }
                    else
                    {
                        g.drawString(sPregunta, iCuadroPosX+40, iCuadroPosY+100);
                        g.drawString(sPregunta2, iCuadroPosX+40, iCuadroPosY+1500);
                    }                     
                    g.drawImage(respRespA.getImageI(), respRespA.getPosX(),
                            respRespA.getPosY(), this);
                    g.drawImage(respRespB.getImageI(), respRespB.getPosX(),
                            respRespB.getPosY(), this);
                    g.drawImage(respRespC.getImageI(), respRespC.getPosX(),
                            respRespC.getPosY(), this);
                    g.drawImage(respRespD.getImageI(), respRespD.getPosX(),
                            respRespD.getPosY(), this);
                    if(sRespuestaActiva == "A")
                    {
                        g.drawImage(respRespA.getImageI2(), respRespA.getPosX(),
                            respRespA.getPosY(), this);
                    }
                    else if(sRespuestaActiva == "B")
                    {
                        g.drawImage(respRespB.getImageI2(), respRespB.getPosX(),
                            respRespB.getPosY(), this);
                    }
                    else if(sRespuestaActiva == "C")
                    {
                        g.drawImage(respRespC.getImageI2(), respRespC.getPosX(),
                            respRespC.getPosY(), this);
                    }
                    else if(sRespuestaActiva == "D")
                    {
                        g.drawImage(respRespD.getImageI2(), respRespD.getPosX(),
                            respRespD.getPosY(), this);
                    }
                    // Desplegar Respuestas
                    g.drawString(lstPreguntas.get(iMateria-1).get(iNumeroPregunta).
                        get(1), respRespA.getPosX()+10,(int) respRespA.getPosY()+4*respRespA.getAlto()/5);
                    g.drawString(lstPreguntas.get(iMateria-1).get(iNumeroPregunta).
                        get(2), respRespB.getPosX()+10,(int) respRespB.getPosY()+4*respRespA.getAlto()/5);
                    g.drawString(lstPreguntas.get(iMateria-1).get(iNumeroPregunta).
                        get(3), respRespC.getPosX()+10,(int) respRespC.getPosY()+4*respRespA.getAlto()/5);
                    g.drawString(lstPreguntas.get(iMateria-1).get(iNumeroPregunta).
                        get(4), respRespD.getPosX()+10,(int) respRespD.getPosY()+4*respRespA.getAlto()/5);
                    
                    g.drawImage(RelojMG.despliegaReloj(),(int) iAnchoPantalla/2 -
                            RelojMG.getAncho()/2, 35, this);
                }
                
                // STRINGS DE PRUEBA
                g.setColor(new Color(0,0,0));
                /*g.drawString("Numero Preguntas 1: "+iNumPreguntas[0], 200, 400);
                g.drawString("Numero Preguntas 2: "+iNumPreguntas[1], 200, 450);
                g.drawString("Numero Preguntas 3: "+iNumPreguntas[2], 200, 500);*/
                
                /*g.drawString("Numero Pregunta: "+(iNumeroPregunta), 100, 400);
                g.drawString("Respuesta: "+lstRespuestas.get(iMateria-1).
                        get(iNumeroPregunta), 100, 450);*/
                /*g.drawString("Numero Respuesta 1: "+lstRespuestas.get(iMateria-1).get(2), 200, 500);
                g.drawString("Numero Respuesta 1: "+lstRespuestas.get(iMateria-1).get(3), 200, 550);    */            

            }
            else
            {
                g.setColor(new Color(186,186,100,190));
                g.fillRoundRect(200, 100, 880, 
                        500, 50, 50);
                g.setColor(new Color(30,120,200));
                g.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 55));
                g.drawString("FIN DE LA PARTIDA", 380, 260);
                g.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 65));
                g.drawString("PUNTUACION: ", 380, 430);
                g.setColor(new Color(240,240,255));
                g.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 75));
                g.drawString("" + iPuntuacion_Quiz, 820, 430);
                botonAtras(g);
            }
        }
//==============================================================================
        else if(iPantalla == 7)
        {
            // Pantalla de Perfil del Jugador
            // Pintar Fondo
            g.drawImage(imaFondo.getImage(), 0, 0, this);
            g.drawImage(imaInicio.getImage(), 0, 0, this);
            
            // Pintar Cuadro
            g.setColor(new Color(255,255,255,200));     // Color Blanco
            g.fillRoundRect((int)(iAnchoPantalla*0.10),
                    (int)(iAltoPantalla*0.12),(int) (iAnchoPantalla*0.80),
                    (int) (iAltoPantalla*0.80), 15, 15);
            g.setColor(new Color(0,0,0,100));
            g2.setStroke(new BasicStroke(3f));
            g2.drawRoundRect((int)(iAnchoPantalla*0.10),
                    (int)(iAltoPantalla*0.12),(int) (iAnchoPantalla*0.80),
                    (int) (iAltoPantalla*0.80), 15, 15);
            
            // Pintar Nombre
            g.setColor(new Color(136,171,230));
            g.setFont(new Font("Eras Demi ITC", Font.PLAIN, 48));
            g.drawString(sNombreJugador, (int)(iAnchoPantalla*0.43),
                    (int)(iAltoPantalla*0.20));
            
            // Rectangulo contenedor de graficas
            g.setColor(new Color(0,0,0, 100));
            g.fillRect((int)(iAnchoPantalla*0.13), (int)(iAltoPantalla*0.25),
                    (int)(iAnchoPantalla*0.38), (int)(iAltoPantalla*0.50));
            g.fillRect((int)(iAnchoPantalla*0.13), (int)(iAltoPantalla*0.25),
                    (int)(iAnchoPantalla*0.38), (int)(iAltoPantalla*0.50));
            
            // Grafica - Barras
            /* Parametros:
                Posicion X - 14% del Ancho de la Pantalla
                Posicion Y - 27% de la Altura
                Ancho Barra
                Altura barra
            */
            g.setColor(new Color(217,77,76));
            g.fillRect((int)(iAnchoPantalla*0.14),
                    (int)(iAltoPantalla*0.29),iPuntuacionMatematicas,
                    40);
            g.setColor(new Color(217,130,77));
            g.fillRect((int)(iAnchoPantalla*0.14),
                    (int)(iAltoPantalla*0.37),iPuntuacionEspañol,
                    40);
            g.setColor(new Color(166,166,56));
            g.fillRect((int)(iAnchoPantalla*0.14),
                    (int)(iAltoPantalla*0.45),iPuntuacionHistoria,
                    40);
            g.setColor(new Color(135,170,102));
            g.fillRect((int)(iAnchoPantalla*0.14),
                    (int)(iAltoPantalla*0.53),iPuntuacionEstructuraIngles,
                    40);
            g.setColor(new Color(136,171,173));
            g.fillRect((int)(iAnchoPantalla*0.14),
                    (int)(iAltoPantalla*0.61),iPuntuacionMatematicas_M1,
                    40);
            //g.setColor(new Color(76,179,210));
            
            // Pintar Linea
            g2.setStroke(new BasicStroke(1.5f));
            g.setColor(new Color(255,255,255));
            g2.drawLine((int)(iAnchoPantalla*0.14), (int)(iAltoPantalla*0.27),
                    (int)(iAnchoPantalla*0.14),
                    (int)(iAnchoPantalla*0.13) + (int)(iAltoPantalla*0.50));
            
            // Pintar Nombre de Graficas
            g.setColor(new Color(255,255,255));
            g.setFont(new Font("SansSerif", Font.ROMAN_BASELINE, 24));
            g.drawString("Español", (int)(iAnchoPantalla*0.15),
                    (int)(iAltoPantalla*0.29)+28);
            g.drawString("Matemaricas", (int)(iAnchoPantalla*0.15),
                    (int)(iAltoPantalla*0.37)+28);
            g.drawString("Historia", (int)(iAnchoPantalla*0.15),
                    (int)(iAltoPantalla*0.45)+28);
            g.drawString("Estructura de Ingles", (int)(iAnchoPantalla*0.15),
                    (int)(iAltoPantalla*0.53)+28);
            g.drawString("Matematicas basicas", (int)(iAnchoPantalla*0.15),
                    (int)(iAltoPantalla*0.61)+28);
            
            // Pintar Puntuaciones del Jugador
            g.setColor(new Color(136,171,173));
            g.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 30));
            g.drawString("PUNTUACIONES EN MATERIAS",
                    (int)(iAnchoPantalla*0.55),(int)(iAltoPantalla*0.29));
            g.setColor(new Color(0,0,0));
            g.setFont(new Font("SansSerif", Font.PLAIN, 20));
            g.drawString("Español: " + iPuntuacionEspañol,
                    (int)(iAnchoPantalla*0.55),(int)(iAltoPantalla*0.34));
            g.drawString("Matematicas: " + iPuntuacionMatematicas,
                    (int)(iAnchoPantalla*0.55), (int)(iAltoPantalla*0.38));
            g.drawString("Historia: " + iPuntuacionHistoria,
                    (int)(iAnchoPantalla*0.55), (int)(iAltoPantalla*0.42));
            
            g.setColor(new Color(136,171,173));
            g.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 30));
            g.drawString("PUNTOS TOTALES",
                    (int)(iAnchoPantalla*0.55),(int)(iAltoPantalla*0.49));
            g.setColor(new Color(0,0,0));
            g.setFont(new Font("SansSerif", Font.PLAIN, 20));
            g.drawString("Estructura de Ingles: " + iPuntuacionEstructuraIngles,
                    (int)(iAnchoPantalla*0.55), (int)(iAltoPantalla*0.54));
            g.drawString("Ejercicios de Matematicas: " + iPuntuacionMatematicas_M1,
                    (int)(iAnchoPantalla*0.55), (int)(iAltoPantalla*0.58));
            
            g.setColor(new Color(136,171,173));
            g.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 30));
            g.drawString("MEJORES PUNTUACIONES",
                    (int)(iAnchoPantalla*0.55),(int)(iAltoPantalla*0.65));
            g.setColor(new Color(0,0,0));
            g.setFont(new Font("SansSerif", Font.PLAIN, 20));
            g.drawString("Estructura de Ingles: " + iPuntuacionEstructuraIngles,
                    (int)(iAnchoPantalla*0.55), (int)(iAltoPantalla*0.70));
            g.drawString("Ejercicios de Matematicas: " + iPuntuacionMatematicas_M1,
                    (int)(iAnchoPantalla*0.55), (int)(iAltoPantalla*0.74));
            
            // Mostrar boton de atras
            botonAtras(g);
            //System.out.println(Arrays.toString(Toolkit.getDefaultToolkit().getFontList()));
        }
//==============================================================================
        else if(iPantalla == 8)
        {
            // Pantalla de Opciones
            g.drawImage(imaFondo.getImage(), 0, 0, this);
            g.drawImage(imaInicio.getImage(), 0, 0, this);
            
            //g.drawImage(imaOpciones.getImage(), 0, 0, this);
            
            if(sBotonActivo == "btnDia")
            {
                g.drawImage(btnDia.getImageI(), btnDia.getPosX(),
                    btnDia.getPosY(), this);
            }
            else g.drawImage(btnDia.getImageI2(), btnDia.getPosX(),
                    btnDia.getPosY(), this);

            
            if(sBotonActivo == "btnNoche")
            {
                g.drawImage(btnNoche.getImageI(), btnNoche.getPosX(),
                    btnNoche.getPosY(), this);
            }
            else g.drawImage(btnNoche.getImageI2(), btnNoche.getPosX(),
                    btnNoche.getPosY(), this);

            
            // Mostrar boton de atras
            botonAtras(g);
        }
//==============================================================================
        else if(iPantalla == 9)
        {
            // Pantalla de Controles
            g.drawImage(imaControles.getImage(), 0, 0, this);
            
            // Mostrar boton de atras
            botonAtras(g);
        }
//==============================================================================
        else if(iPantalla == 10)
        {
            // Pantalla de Ganar
            g.drawImage(imaGanador.getImage(), 0, 0, this);
        }
//==============================================================================
        else if(iPantalla == 11)
        {
            // Pantalla de Perder
            g.drawImage(imaPerdedor.getImage(), 0, 0, this);
        }
//==============================================================================
        else if(iPantalla == 12)
        {
            // Pantalla de Confirmacion de Salida
            g.drawImage(imaFondo.getImage(), 0, 0, this);
            g.drawImage(imaInicio.getImage(), 0, 0, this);
            
            // Obcurecer fondo
            g.setColor(new Color(24,24,24,180));    // Color Negro
            g.fillRect(0,0, iAnchoPantalla, iAltoPantalla);
            g.setColor(new Color(255,0,20,180));
            // fillRect: Posicion X, Posicion Y, Ancho, Alto
            g.setColor(new Color(255,255,255,120));     // Color Blanco
            // Cuadro
            g.fillRoundRect((int)(iAnchoPantalla*0.25), (int) (iAltoPantalla*0.26),
                    (int)(iAnchoPantalla*0.50),(int)(iAltoPantalla*0.50), 20,20);
            // Botones
            g.drawImage(btnConfirmar.getImageI(), btnConfirmar.getPosX(), 
                    btnConfirmar.getPosY(), this);
            g.drawImage(btnRegresar.getImageI(), btnRegresar.getPosX(), 
                    btnRegresar.getPosY(), this);
            // Mensaje
            g.setFont(new Font("SansSerif", Font.PLAIN, 45));
            g.setColor(Color.black);
            g.drawString("¿Estas seguro que deseas", 370, 300);
            g.drawString("abandonar la partida? ", 430, 360);
            // Boton Activo
            if(sBotonActivo == "botonConfirmar")
            {
                g.drawImage(btnConfirmar.getImageI2(), btnConfirmar.getPosX(), 
                    btnConfirmar.getPosY(), this);
            }
            else if(sBotonActivo == "botonRegresar")
            {
                g.drawImage(btnRegresar.getImageI2(), btnRegresar.getPosX(), 
                    btnRegresar.getPosY(), this);
            }
        }
//==============================================================================
    }
//----------------------------------------------------------------------------------------------------------------
    /**
     * Metodo main que crea el juego
     */
    public static void main(String [] args){
        // Crear un Juego
        LearnVille nGame = new LearnVille();

    }
//----------------------------------------------------------------------------------------------------------------
    /**
     * Metodo que estara actualizando el juego hasta el final de la ejecución
     */
    public void run() {
        // Ciclo que mantiene actualizado el juego mientras este se este ejecutando  
        while (true)
        {
            actualiza();
            checaColision();
            repaint();
            try
            {
                Thread.sleep(20);       //  El thread se duerme
            }
            catch (InterruptedException ex)
            {
                System.out.println("Error en " + ex.toString());
            }
        }
    }
//----------------------------------------------------------------------------------------------------------------
    /**
     * Metodo que reviza colisiciones del juego
     */
    public void checaColision()
    {

    }
//----------------------------------------------------------------------------------------------------------------
    /**
     * Metodo que actualiza el juego
     */
    public void actualiza()
    {
        iTiempo++;
    }
//----------------------------------------------------------------------------------------------------------------
    /**
     * Metodo que actualiza la posicion del personaje
     */
    public void actualizaPersonaje()
    {
        // Personaje del Jugador
        if(iDireccionPer == 1 && PerJugador.getPosY() - 40 > 0)
            PerJugador.setPosY(PerJugador.getPosY() - iVelocidadJugador);
        else if(iDireccionPer == 2  && PerJugador.getPosY() +
                PerJugador.getAlto() + 20 < iAltoPantalla)
            PerJugador.setPosY(PerJugador.getPosY() + iVelocidadJugador);
        else if(iDireccionPer == 3 && PerJugador.getPosX() > 0)
            PerJugador.setPosX(PerJugador.getPosX() - iVelocidadJugador);
        else if(iDireccionPer == 4 && PerJugador.getPosX() +
                PerJugador.getAncho()< iAnchoPantalla)
            PerJugador.setPosX(PerJugador.getPosX() + iVelocidadJugador);
    }
//----------------------------------------------------------------------------------------------------------------
    /**
     * Metodo que actualiza todo lo concerniente al minijuego 1
     * En desuso actualmente
     */
    /*public void actualizaMiniJuego1()
    {
        if(bEntraMiniJuego1)
        {
            soundCancion1.stop();
            soundCancion2.play();
            bEntraMiniJuego1 = false;
        }
        if(!bPerdio && !bGano)
        {            
            // Personaje del Jugador
            if(iDireccionPer == 1)
                PerJugador.setPosY(PerJugador.getPosY() - iVelocidadJugador);
            else if(iDireccionPer == 2)
                PerJugador.setPosY(PerJugador.getPosY() + iVelocidadJugador);
            else if(iDireccionPer == 3)
                PerJugador.setPosX(PerJugador.getPosX() - iVelocidadJugador);
            else if(iDireccionPer == 4)
                PerJugador.setPosX(PerJugador.getPosX() + iVelocidadJugador);
        }
    }*/
//----------------------------------------------------------------------------------------------------------------
    /**
     * Metodo que verifica las colisiones en el minijuego 1
     */
    public void checaColisionM1() {
        // Diego
        if(oPersonaje.getPosY() - 1 < 241)
            oPersonaje.setPosY(241);
        if(oPersonaje.getPosY() + 1  > 597)
            oPersonaje.setPosY(597);
        
        int iCtrl = 0;
        for (Objeto oCubos : lstElementos)
        {
                if (oCubos != null) {
                    if(oCubos.intersecta(oPersonaje))
                    {
                        int iPR = (int) lstRespuestasM1.get(iNumPreg);
                        if(iCtrl + 1 == iPR)
                        {
                            iPuntMJ1 = iPuntMJ1 + 10;
                            soundCorrecto.play();
                        }
                        else soundIncorrecto.play();
                        for (Objeto oCubos2 : lstElementos)
                        {
                            if(oCubos2 != null)
                            {
                                oCubos2.nuevaPosicion(imaColision); 
                            }
                        }
                        iNumPreg = iNumPreg + 1;
                    }
                }
                iCtrl++;
            }
    }
//----------------------------------------------------------------------------------------------------------------
    /**
     * Metodo que actualiza el minijuego 1
     */
    public void actualizaMiniJuego()
    {
        if(!bGanar)
        {
            if(bEntraMiniJuego1)
            {
                soundCancion1.stop();
                soundCancion2.play();
                bEntraMiniJuego1 = false;
            }
            for(Objeto oCubos : lstElementos)
            {
                if (oCubos != null)
                {
                    int iNPosCubos = oCubos.getPosX() - 3;
                    oCubos.setPosX(iNPosCubos);
                }
            }
            for(Objeto oCubos : lstElementos)
            {
                if (oCubos != null)
                {
                    if(oCubos.getRectangle().
                            intersects(oPersonaje.getPerimetro())) {
                       oCubos.activar(); 
                    }
                }
            }
            if(iNumPreg >= 10)
            {
                iNumPreg = 0;
                bGanar = true;
            }
        }
    }   
//----------------------------------------------------------------------------------------------------------------
    /**
     * Metodo que verifica las colisiones en el minijuego 2
     */
    public void checaColisionM2()
    {
        for(int iR = 0; iR < iCantidadObjetos; iR++)
        {
            for(int iC = 0; iC < iCantidadObjetos; iC++)
            {
                if(matObjetos[iR][iC].getRectangle().
                        intersects(PerJugador.getPosicion()))
                {
                    matObjetos[iR][iC].activar();
                }
            }
        }
    }    
//----------------------------------------------------------------------------------------------------------------
    /**
     * Metodo que actualiza el minijuego 2
     */
    public void actualizaMiniJuego2()
    {
        if(bEntraMiniJuego2)
        {
            soundCancion1.stop();
            soundCancion2.play();
            bEntraMiniJuego2 = false;
        }
        actualizaPersonaje();
        checaColisionM2();
    }
    
//----------------------------------------------------------------------------------------------------------------
    /**
     * Metodo que actualiza los quizes
     */
    public void actualizaQuizes()
    {
        // Si ya no hay mas preguntas regresar a la primera pregunta
        if(iNumeroPregunta > lstRespuestas.get(iMateria-1).size())
        {
            iNumeroPregunta = 1;
        }
        if(bEntraQuizes)
        {
            soundCancion2.stop();
            soundCancion1.play();
            iPuntuacion_Quiz = 0;
            bEntraQuizes = false;
            //initQuizes();
        }
        if(iCuadroAlto < iAltoMAX)
        {
            iCuadroAlto += 10;
            iCuadroPosY = (int) (iAltoPantalla/2 - iCuadroAlto/2 + 
                    iAltoPantalla*0.05);
            }
        if(iCuadroAncho < iAnchoMAX)
        {
            iCuadroPosX = (int) (iAnchoPantalla/2 - iCuadroAncho/2);
            iCuadroAncho += 10;
        }
        // JUEGO EN EJECUCION
        if(iCuadroAncho >= iAnchoMAX)
        {
            sPregunta = lstPreguntas.get(iMateria-1).get(iNumeroPregunta).get(0);
            bDespliegaPregunta = true;
            RelojMG.actualizaTiempo();
        }
        if(RelojMG.getTiempo() < 0)
        {
            soundIncorrecto.play();
            iNumeroPregunta++;
            iPreguntasHechas++;
            // Detener Tiempo
            pausarEjecucion();
            RelojMG.setTiempo(20);
            iCuadroAlto = 0;
            iCuadroAncho = 0;
        }
    }
    
//----------------------------------------------------------------------------------------------------------------
    /**
     * Metodo que actualiza la pantalla de Bienvenida
     */
    public void actualizaPantBienvenida()
    {
        if(iTiempo % 2 == 0)
        {
            Nube1.setPosX(Nube1.getPosX() - 1);
            Nube2.setPosX(Nube2.getPosX() - 1);
            Nube3.setPosX(Nube3.getPosX() - 1);
            Nube4.setPosX(Nube4.getPosX() - 1);
        }
        if(Nube1.getPosX() < -1*Nube1.getAncho()) Nube1.setPosX(1280);
        if(Nube2.getPosX() < -1*Nube2.getAncho()) Nube2.setPosX(1280 +
                Nube1.getAncho() - Nube2.getAncho());
        if(Nube3.getPosX() < -1*Nube3.getAncho()) Nube3.setPosX(1280 +
                Nube1.getAncho() - Nube3.getAncho());
        if(Nube4.getPosX() < -1*Nube3.getAncho()) Nube4.setPosX(1280 +
                Nube1.getAncho() - Nube4.getAncho());
    }
//----------------------------------------------------------------------------------------------------------------
    /**
     * Metodo que actualiza la pantalla del Mapa
     */
    public void actualizaPantReloj()
    {
        // Iniciar Musica
        if(bEntraJuego)
        {
            //soundCancion2.stop();
            soundCancion1.play();
            bEntraJuego = false;
        }
        Nube5.setPosX(Nube5.getPosX() + 1);
        Nube6.setPosX(Nube6.getPosX() - 1);
        if(iTiempo % 2 == 0)
        {
            if(Nube5.getPosX() > 1280) Nube5.setPosX(0-Nube5.getAncho());
            if(Nube6.getPosX() < -1*Nube6.getAncho()) Nube6.setPosX(1280);
        }
    }
    
//----------------------------------------------------------------------------------------------------------------
    /*public void GuardarPartida() throws IOException
    {
        String nombre = JOptionPane.showInputDialog("Nombre: ");
        PrintWriter ArchivoEscritura = new PrintWriter(new FileWriter(sNombreArchivo));
        ArchivoEscritura.println(nombre);
        ArchivoEscritura.println(lstElementos.size());
        ArchivoEscritura.println(iVidas);
        ArchivoEscritura.println(iVelocidad);
        ArchivoEscritura.println(iPuntuacion);
        ArchivoEscritura.println(Pelota1.getPosX() + " " + Pelota1.getPosY());
        ArchivoEscritura.println(Pelota1.getVelocidadX() + " " + Pelota1.getVelocidadY());
        ArchivoEscritura.println(BarSirena.getPosX());
        // Guarda todas las posiciones de las cajas en pantalla
        for(int iC = 0; iC < lstElementos.size(); iC++)
        {
            ArchivoEscritura.println(iPosElementos[iC][0] + " " +
                    iPosElementos[iC][1] + " " + iPosElementos[iC][2]);
        }
        ArchivoEscritura.println("---");
        ArchivoEscritura.close();
        JOptionPane.showMessageDialog(null, "Juego Guardado", "", 
            JOptionPane.PLAIN_MESSAGE);
    }*/
//----------------------------------------------------------------------------------------------------------------
    /*public void leeArchivo() throws IOException {                                                      
            BufferedReader fileIn;
            try {
                    fileIn = new BufferedReader(new FileReader(sNombreArchivo));
            } catch (FileNotFoundException e){
                    File PartidasGuardadas = new File(sNombreArchivo);
                    PartidasGuardadas.createNewFile();
                    //PrintWriter fileOut = new PrintWriter(PartidasGuardadas);
                    //fileOut.println("");
                    //fileOut.close();
                    fileIn = new BufferedReader(new FileReader(sNombreArchivo));
            }
            String sLinea = fileIn.readLine();
            if(sLinea == null)
            {
                bAbrir = false;
            }
    }*/
//----------------------------------------------------------------------------------------------------------------
    public void leePerfil() throws IOException {                                                      
        BufferedReader fileIn;
        try {
                fileIn = new BufferedReader(new FileReader(sArchivoPerfil));
        } catch (FileNotFoundException e){
                String sNombre = JOptionPane.showInputDialog("Nombre: ");
                sNombreJugador = sNombre;
                File PartidasGuardadas = new File(sArchivoPerfil);
                PartidasGuardadas.createNewFile();
                //PrintWriter fileOut = new PrintWriter(PartidasGuardadas);
                //fileOut.println("");
                //fileOut.close();
                fileIn = new BufferedReader(new FileReader(sArchivoPerfil));
        }
        String sLinea = fileIn.readLine();
        sNombreJugador = sLinea;
    }
//----------------------------------------------------------------------------------------------------------------
    public void pintaCuadro(Graphics g)
    {
        g.setColor(new Color(166,166,56,180));
        g.fillRoundRect(iCuadroPosX, iCuadroPosY, iCuadroAncho, 
                        iCuadroAlto, 50, 50);
    }
//----------------------------------------------------------------------------------------------------------------
    public void pausarEjecucion()
    {
        try
        {
            Thread.sleep(1000);       //  El thread se duerme
        }
        catch (InterruptedException ex)
        {
            System.out.println("Error en " + ex.toString());
        }
    }
    
    
//----------------------------------------------------------------------------------------------------------------
    /**
     * Metodo paint que pinta en el buffer para luego pintar en pantalla
     */
    public void paint(Graphics g)
    {
            // Inicializan el DoubleBuffer
            if (imaImageDB == null){
                    imaImageDB = createImage (this.getSize().width, this.getSize().height);
                    graGraficoDB = imaImageDB.getGraphics ();
            }

            // Actualiza la imagen de fondo.
            graGraficoDB.setColor(getBackground());
            graGraficoDB.fillRect(0, 0, this.getSize().width, this.getSize().height);

            // Actualiza el Foreground.
            graGraficoDB.setColor(getForeground());
            paint2(graGraficoDB);

            // Dibuja la imagen actualizada
            g.drawImage (imaImageDB, 0, 0, this);
    }
//----------------------------------------------------------------------------------------------------------------
    //Metodo que verifica los eventos de click del mouse
    public void mouseClicked(MouseEvent e) {
        // Reviza si el boton de la pantalla de Bienvenida es presionado
        if(
                iPantalla == 1
                //cPantallaActual == 'b'
                &&
                btnInicio.getPerimetro().contains(e.getX(), e.getY())
                )
        {
            //cPantallaActual = 'm';
            iPantalla = 2;
            sBotonActivo = "";
        }
        // Pantalla de Menu
        // cPantallaActual == 'm'
        // Reviza si alguno de los botones del Menu son presionados
        else if(iPantalla == 2)
        {
            iPantallaAnterior = iPantalla;
            if(btnHistoria.getPerimetro().contains(e.getX(), e.getY()))
            {
                //cPantallaActual = 'h';
                iPantalla = 3;
            }
            else if(btnOpciones.getPerimetro().contains(e.getX(), e.getY()))
            {
                //cPantallaActual = 'o';
                iPantalla = 8;
            }
            else if(btnPerfil.getPerimetro().contains(e.getX(), e.getY()))
            {
                //cPantallaActual = 'p';
                iPantalla = 7;
            }
            else if(btnQuizes.getPerimetro().contains(e.getX(), e.getY()))
            {
                //cPantallaActual = 'Q';
                iPantalla = 6;
            }
            else if(btnSalida.getPerimetro().contains(e.getX(), e.getY()))
            {
                //cPantallaActual = 'Q';
                iPantalla = 12;
            }
        }
        else if(iPantalla == 3)
        {
            if(btnMiniJuego1.getPerimetro().contains(e.getX(), e.getY()))
            {
                //cPantallaActual = 'h';
                iPantalla = 4;
            }
            else if(btnMiniJuego2.getPerimetro().contains(e.getX(), e.getY()))
            {
                //cPantallaActual = 'o';
                iPantalla = 5;
            }
            else if(btnMiniJuego3.getPerimetro().contains(e.getX(), e.getY()))
            {
                //cPantallaActual = 'p';
                iPantalla = 6;
            }
        }
        else if(!bPausa && iPantalla == 6)
        {
            int iRespuestaQ =lstRespuestas.get(iMateria-1).
                        get(iNumeroPregunta-1);
            if(respRespA.getPerimetro().contains(e.getX(), e.getY()))
            {
                if(respRespA.getRespuesta() == iRespuestaQ)
                {
                    iPuntuacion_Quiz += 20;
                    soundCorrecto.play();
                }
                else
                {
                    soundIncorrecto.play();
                    colorCuadro = new Color(166,56,56,180);
                }
                iNumeroPregunta++;
                iPreguntasHechas++;
                pausarEjecucion();
                RelojMG.setTiempo(20);
                iCuadroAlto = 0;
                iCuadroAncho = 0;
            }
            if(respRespB.getPerimetro().contains(e.getX(), e.getY()))
            {
                if(respRespB.getRespuesta()== iRespuestaQ)
                {
                    iPuntuacion_Quiz += 20;
                    soundCorrecto.play();
                }
                else soundIncorrecto.play();
                iNumeroPregunta++;
                iPreguntasHechas++;
                pausarEjecucion();
                RelojMG.setTiempo(20);
                iCuadroAlto = 0;
                iCuadroAncho = 0;
            }
            if(respRespC.getPerimetro().contains(e.getX(), e.getY()))
            {
                if(respRespC.getRespuesta() == iRespuestaQ)
                {
                    iPuntuacion_Quiz += 20;
                    soundCorrecto.play();
                }
                else soundIncorrecto.play();
                iNumeroPregunta++;
                iPreguntasHechas++;
                pausarEjecucion();
                RelojMG.setTiempo(20);
                iCuadroAlto = 0;
                iCuadroAncho = 0;
            }
            if(respRespD.getPerimetro().contains(e.getX(), e.getY()))
            {
                if(respRespD.getRespuesta() == iRespuestaQ)
                {
                    iPuntuacion_Quiz += 20;
                    soundCorrecto.play();
                }
                else soundIncorrecto.play();
                iNumeroPregunta++;
                iPreguntasHechas++;
                pausarEjecucion();
                RelojMG.setTiempo(20);
                iCuadroAlto = 0;
                iCuadroAncho = 0;
            }
        }
        else if(iPantalla == 8)
        {
            if(btnDia.getPerimetro().contains(e.getX(), e.getY()))
            {
                imaFondo = new ImageIcon(Toolkit.getDefaultToolkit()
                    .getImage(urlBackInicio));
            }
            else if(btnNoche.getPerimetro().contains(e.getX(), e.getY()))
            {
                imaFondo = new ImageIcon(Toolkit.getDefaultToolkit()
                    .getImage(urlBackInicio2));
            }
        }
        else if(iPantalla == 12)
        {
            if(btnConfirmar.getPerimetro().contains(e.getX(), e.getY()))
            {
                // Cerrar juego
                System.exit(0);
            }
            else if(btnRegresar.getPerimetro().contains(e.getX(), e.getY()))
            {
                // Regresar al juego
                iPantalla = 2;
            }
        }
        // Si algun minijuego esta pausado, mostrar los botones del menu de pausa
        // Pantallas 4,5 y 6 son minijuegos
        if(bPausa && (iPantalla == 4 || iPantalla == 5 || iPantalla == 6))
        {          
            if(btnBackJuego.getPerimetro().contains(e.getX(), e.getY()))
            {
                // Regresar al juego
                bPausa = false;
            }
            else if(btnSalirPartida.getPerimetro().contains(e.getX(), e.getY()))
            {
                // Al salir del juego
                if(iPantalla == 4) init2();
                // Reiniciar variables de control
                bEntraMiniJuego1 = true;
                bEntraMiniJuego2 = true;
                bEntraQuizes = true;
                bEntraJuego = true;
                // Detener las canciones
                soundCancion1.stop();
                soundCancion2.stop();
                
                // Regresar al juego
                bPausa = false;
                iPantalla = 3;
                initMiniJuego2();

            }
            else if(btnAyuda.getPerimetro().contains(e.getX(), e.getY()))
            {
                // Activar Ayuda
            }
        }
        if(bBotonAtras)
        {
            if(btnAtras.getPerimetro().contains(e.getX(), e.getY()))
            {
                iPantalla = iPantallaAnterior;
                bGanar = false;
                iPuntMJ1 = 0;
                iPreguntasHechas = 0;
                iNumeroPregunta = 1;
            }
        }
    }
//----------------------------------------------------------------------------------------------------------------
    public void mousePressed(MouseEvent e) {

    }
//----------------------------------------------------------------------------------------------------------------
    public void mouseReleased(MouseEvent e) {

    }
//----------------------------------------------------------------------------------------------------------------
    public void mouseEntered(MouseEvent e) {

    }
//----------------------------------------------------------------------------------------------------------------
    public void mouseExited(MouseEvent e) {

    }
//----------------------------------------------------------------------------------------------------------------
    public void mouseDragged(MouseEvent e) {

    }
//----------------------------------------------------------------------------------------------------------------
    // Metodo que verifica cuando el mouse se mueve
    public void mouseMoved(MouseEvent e) {
        // Cambio de Botones
        if(iPantalla == 1)
        {
            // Botones de Pantalla de Bienvenida
            if(btnInicio.getPerimetro().contains(e.getX(), e.getY()))
                sBotonActivo = "";
            else sBotonActivo = "boton1";
        }
        else if(iPantalla == 2)
        {
            // Botones de Pantalla Menu
            if(btnPerfil.getPerimetro().contains(e.getX(), e.getY()))
            {
                sBotonActivo = "botonPerfil";
            }
            else if(btnHistoria.getPerimetro().contains(e.getX(), e.getY()))
            {
                sBotonActivo = "botonHistoria";
            }
            else if(btnQuizes.getPerimetro().contains(e.getX(), e.getY()))
            {
                sBotonActivo = "botonQuizes";
            }
            else if(btnOpciones.getPerimetro().contains(e.getX(), e.getY()))
            {
                sBotonActivo = "botonOpciones";
            }
            else if(btnSalida.getPerimetro().contains(e.getX(), e.getY()))
            {
                sBotonActivo = "botonSalida";
            }
            else sBotonActivo = "";
        }
        // Pantalla del Mapa
        else if(iPantalla == 3)
        {
            if(btnMiniJuego1.getPerimetro().contains(e.getX(), e.getY()))
            {
                sBotonActivo = "botonMiniJuego1";
            }
            else if(btnMiniJuego2.getPerimetro().contains(e.getX(), e.getY()))
            {
                sBotonActivo = "botonMiniJuego2";
            }
            else if(btnMiniJuego3.getPerimetro().contains(e.getX(), e.getY()))
            {
                sBotonActivo = "botonMiniJuego3";
            }
            else sBotonActivo = "";
        }
        else if(iPantalla == 6)
        {
            if(respRespA.getPerimetro().contains(e.getX(), e.getY()))
            {
                sRespuestaActiva = "A";
            }
            else if(respRespB.getPerimetro().contains(e.getX(), e.getY()))
            {
                sRespuestaActiva = "B";
            }
            else if(respRespC.getPerimetro().contains(e.getX(), e.getY()))
            {
                sRespuestaActiva = "C";
            }
            else if(respRespD.getPerimetro().contains(e.getX(), e.getY()))
            {
                sRespuestaActiva = "D";
            }
            else sRespuestaActiva = "";
        }
        
        // Botones de pantalla de Minijuego
        if(iPantalla == 4 || iPantalla == 5 || iPantalla == 6)
        {
            if(btnSalirPartida.getPerimetro().contains(e.getX(), e.getY()))
            {
                sBotonActivo = "btnSalir";
            }
            else if(btnBackJuego.getPerimetro().contains(e.getX(), e.getY()))
            {
                sBotonActivo = "btnRegresar";
            }
            else if(btnAyuda.getPerimetro().contains(e.getX(), e.getY()))
            {
                sBotonActivo = "btnAyuda";
            }
            else sBotonActivo = "";
        }
        // Pantalla de COnfiguraciones
        else if(iPantalla == 8)
        {
            if(btnDia.getPerimetro().contains(e.getX(), e.getY()))
            {
                sBotonActivo = "btnDia";
            }
            else if(btnNoche.getPerimetro().contains(e.getX(), e.getY()))
            {
                sBotonActivo = "btnNoche";
            }
            else sBotonActivo = "";
        }
        // Botones de pantalla de Salida
        else if(iPantalla == 12)
        {
            if(btnConfirmar.getPerimetro().contains(e.getX(), e.getY()))
            {
                sBotonActivo = "botonConfirmar";
            }
            else if(btnRegresar.getPerimetro().contains(e.getX(), e.getY()))
            {
                sBotonActivo = "botonRegresar";
            }
            else sBotonActivo = "";
        }
        if(bBotonAtras)
        {
            if(btnAtras.getPerimetro().contains(e.getX(), e.getY()))
            {
                sBotonActivo = "btnAtras";
            }
            // Si no se esta sobre ningun boton, sBotonActivo se vuelve nada
            else if(iPantalla == 6 || iPantalla == 7) sBotonActivo = "";            
        }
    }
//----------------------------------------------------------------------------------------------------------------
    public void keyTyped(KeyEvent e) {

    }
//----------------------------------------------------------------------------------------------------------------
    public void keyPressed(KeyEvent e) {
        // Obtener tecla presionada
        int iTecla = e.getKeyCode();
        
        // Teclas para cambiar de Pantallas
        switch(iTecla)
        {
            case KeyEvent.VK_UP:
                if(iPantalla != 4) iDireccionPer = 1;
                else oPersonaje.setPosY(oPersonaje.getPosY()-15);
                break;
            case KeyEvent.VK_DOWN:
                if(iPantalla != 4) iDireccionPer = 2;
                else oPersonaje.setPosY(oPersonaje.getPosY()+15);
                break;
            case KeyEvent.VK_LEFT:
                iDireccionPer = 3;
                break;
            case KeyEvent.VK_RIGHT:
            {
                iDireccionPer = 4;
                break;
            }
        }
        if(iPantalla == 4 || iPantalla == 5 || iPantalla == 6)
        {
                if(iTecla == KeyEvent.VK_P)
                bPausa = !bPausa;
        }
    }
//----------------------------------------------------------------------------------------------------------------
    public void keyReleased(KeyEvent e) {
        iDireccionPer = 0;
    }
//----------------------------------------------------------------------------------------------------------------
}

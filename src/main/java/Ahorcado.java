package main.java;

import java.util.HashMap;
import java.util.Map;

import main.java.Cronometro;
import main.java.Helper;
import main.java.Jugador;
import main.java.Palabra;

import static java.nio.file.Paths.get;

public class Ahorcado {
    private String palabras[] = {"ahorcado", "primavera", "jugador", "facultad", "programacion"};
    private Map<String, Integer> tiempos = new HashMap<String, Integer>();
    private char palabraActual[] = {'_','_','_','_','_','_','_','_'}; //ahorcado
    private String modoJuego = "Estandar";
    private String letrasAcertadas = "";
    private Cronometro cronometro;
    private Jugador jugador;
    private int errores = 6;
    private Palabra palabra;

    public Ahorcado() {
        BasePalabras.generarPalabras();
    }

    public boolean ingresarLetra(String letra) {
        boolean letraEnPalabra = false;
        if(quedanErrores()) {
            letraEnPalabra = isEnPalabra(letra);
            if (letraEnPalabra) {
                ubicarLetraEnPalabra(letra.charAt(0));
                letrasAcertadas += letra + ',';
            } else {
                errores--;
            }
        }
        return letraEnPalabra;
    }

    public void seleccionarModo(String modo) {
        switch (modo) {
            case "Estandar":
                this.modoJuego = "Estandar";
                break;
            /*case "Por tiempo":
                this.modoJuego = "Por tiempo";
                break;
            case "Cuenta regresiva":
                this.modoJuego = "Cuenta regresiva";
                break;*/
        }
    }

    public String rankingPorTiempo() {
        int i = 0; //Posición
        String salida = "Ranking por tiempo\n"; //Titulo de la salida
        tiempos = Helper.ordenarPorValor(tiempos); //Ordeno los tiempos de menor a mayor
        for (String key : tiempos.keySet()) { //Armo la salida para mostrarla
            i++;
            salida += i + ") " + key + ": " + tiempos.get(key) + " seg.\n";
        }
        return salida.substring(0,salida.length()-1);
    }

    public String getResultado() {
        if (!quedanErrores()) {
            return "Perdiste!";
        } else if (isPalabraDescubierta() && quedanErrores()) {
            return "Ganaste!";
        } else {
            return "Jugando";
        }
    }

    private void ubicarLetraEnPalabra(char letra) {
        int cantLetras = palabras[0].length();
        //int cantLetras = this.palabra.getPalabra().length();
        for (int i = 0; i < cantLetras; i++) {
            if (palabras[0].charAt(i) == letra) {
                palabraActual[i] = letra;
            }
        }
    }

    public int getTiempo() {
        cronometro.stop();
        int tiempo = (int) cronometro.getSeconds();
        tiempos.put(jugador.getNombre(), tiempo);
        return (int) cronometro.getSeconds();
    }

    public void iniciarCronometro() {
        cronometro = new Cronometro();
        cronometro.start();
    }

    public void comenzarJuego() {
        this.palabra = BasePalabras.obtenerPalabra(Opciones.getIdioma(), Opciones.getDificultad(),
                Opciones.getTematica());
        //prepararPalabra();
    }

    public void seleccionarDificultad(String dificultad) {
        Opciones.seleccionarDificultadPalabras(dificultad);
    }

    private boolean isPalabraDescubierta() {
        return Helper.ArrayToString(palabraActual).equals(palabras[0]);
    }

    public void seleccionarIdioma(String idioma) {
        Opciones.seleccionarIdiomaPalabras(idioma);
    }

    public void seleccionarTematica(String tematica) {
        Opciones.seleccionarTematicaPalabras(tematica);
    }

    public void seleccionarTiempoLimite(int tiempo) { Opciones.seleccionarTiempoLimite(tiempo); }

    private boolean isEnPalabra(String letra) {
        return palabras[0].contains(letra);
    }

    public boolean arriesgarPalabra(String palabra) {
        return this.palabras[0].equals(palabra);
    }

    public void jugadorAnonimo() {
        this.jugador = new Jugador("anonimo");
    }

    public void altaJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    private boolean quedanErrores() {
        return errores > 0;
    }

    public String getLetrasAcertadas() {
        return letrasAcertadas.substring(0, letrasAcertadas.length()-1);
    }

    public String getPalabraFija() {
        return palabras[0];
    }

    public String[] getPalabras() {
        return palabras;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public String getModoJuego() {
        return this.modoJuego;
    }

    public String getIdiomaPalabra() {
        return Opciones.getIdioma();
    }

    public String getDificultadPal() {
        return Opciones.getDificultad();
    }

    public String getTematicaPal() {
        return Opciones.getTematica();
    }

    public int getTiempoLimite() {
        return Opciones.getTiempoLimite();
    }

    public Palabra getPalabra() {
        return palabra;
    }

    /*public String getPalabraActual() {
        return Helper.ArrayToString(palabraActual);
    }*/

    /*private void prepararPalabra() {
        int longitudPal = this.palabra.getLongitud();
        this.palabraActual = new char[longitudPal];
        for (int i = 0; i < longitudPal; i++) {
            this.palabraActual[i] = '_';
        }
    }*/
}

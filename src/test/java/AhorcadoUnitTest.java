package test.java;

import org.junit.Test;

import main.java.Ahorcado;
import main.java.Jugador;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class AhorcadoUnitTest {
    @Test
    public void jugadorAnonimoTest() {
        //arrange
        Ahorcado ahorcado = new Ahorcado();
        //Act
        ahorcado.jugadorAnonimo();
        //Assert
        assertEquals("anonimo", ahorcado.getJugador().getNombre());
    }

    @Test
    public void palabraFijaTest() {
        //arrange
        Ahorcado ahorcado = new Ahorcado();
        //act
        //assert
        assertEquals("ahorcado", ahorcado.getPalabraFija());
    }

    @Test
    public void ingresarLetraIncorrectaTest() {
        //arrange
        Ahorcado ahorcado = new Ahorcado();
        //act
        boolean estaEnPalabra = ahorcado.ingresarLetra("y");
        //assert
        assertEquals(false, estaEnPalabra);
    }

    @Test
    public void ingresarLetraCorrectaTest() {
        //arrange
        Ahorcado ahorcado = new Ahorcado();
        //act
        boolean letraEnPalabra = ahorcado.ingresarLetra("h");
        //assert
        assertEquals(true, letraEnPalabra);
    }

    @Test
    public void mostrarLetrasAcertadasTest() {
        //arrange
        Ahorcado ahorcado = new Ahorcado();
        //act
        //letras correctas
        ahorcado.ingresarLetra("a");
        ahorcado.ingresarLetra("h");
        ahorcado.ingresarLetra("o");
        ahorcado.ingresarLetra("d");
        //letras incorrectas
        ahorcado.ingresarLetra("s");
        ahorcado.ingresarLetra("e");
        //assert
        assertEquals("a,h,o,d", ahorcado.getLetrasAcertadas());
    }

    @Test
    //La palabra correcta es ahorcado
    public void mostrarFinJuegoPositivoTest() {
        //arrange
        Ahorcado ahorcado = new Ahorcado();
        //act
        //letras correctas
        ahorcado.ingresarLetra("a"); //a _ _ _ _ a _ _
        ahorcado.ingresarLetra("h"); //a h _ _ _ a _ _
        ahorcado.ingresarLetra("o"); //a h o _ _ a _ o
        ahorcado.ingresarLetra("r"); //a h o r _ a _ o
        ahorcado.ingresarLetra("c"); //a h o r c a _ o
        ahorcado.ingresarLetra("d"); //a h o r c a d o !!!
        //assert
        assertEquals("Ganaste!", ahorcado.getResultado());
    }

    @Test
    //Tiene 6 intentos
    public void mostrarFinJuegoNegativoTest() {
        //arrange
        Ahorcado ahorcado = new Ahorcado();
        //act
        //letras incorrectas
        ahorcado.ingresarLetra("n");
        ahorcado.ingresarLetra("m");
        ahorcado.ingresarLetra("s");
        ahorcado.ingresarLetra("e");
        ahorcado.ingresarLetra("q");
        ahorcado.ingresarLetra("w");
        //assert
        assertEquals("Perdiste!", ahorcado.getResultado());
    }

    @Test
    public void altaJugadorTest() {
        //arrange
        Ahorcado ahorcado = new Ahorcado();
        //act
        Jugador jugador = new Jugador("Juan","juan@gmail.com","juanpass",'h');
        ahorcado.altaJugador(jugador);
        //assert
        assertEquals(jugador, ahorcado.getJugador());
    }

    @Test
    //Evaluamos si hay varias palabras fijas cargadas
    public void palabrasFijasTest() {
        //arrange
        Ahorcado ahorcado = new Ahorcado();
        //act
        int cantidadPalabras = ahorcado.getPalabras().length;
        //assert
        assertEquals(true, cantidadPalabras > 1);
    }

    @Test
    //La palabra correcta es ahorcado
    public void arriesgarPalabraCorrectaTest() {
        //arrange
        Ahorcado ahorcado = new Ahorcado();
        //act
        boolean resultado = ahorcado.arriesgarPalabra("ahorcado");
        //assert
        assertEquals(true, resultado);
    }

    @Test
    //La palabra correcta es ahorcado
    public void arriesgarPalabraIncorrectaTest() {
        //arrange
        Ahorcado ahorcado = new Ahorcado();
        //act
        boolean resultado = ahorcado.arriesgarPalabra("deporte");
        //assert
        assertEquals(false, resultado);
        //print to console
    }

    @Test
    public void rankingPorTiempoTest() {
        //arrange
        Jugador jugador1 = null, jugador2 = null;
        int tiempoJugador1 = 0, tiempoJugador2 = 0;
        Ahorcado ahorcado = new Ahorcado();
        //act
        //Juega primero el jugador1
        jugador1 = new Jugador("Juan","juan@gmail.com","juanpass",'h');
        ahorcado.altaJugador(jugador1);
        ahorcado.iniciarCronometro();
        try {
            ahorcado.ingresarLetra("a");
            ahorcado.ingresarLetra("h");
            ahorcado.ingresarLetra("o");
            ahorcado.ingresarLetra("r");
            ahorcado.ingresarLetra("c");
            ahorcado.ingresarLetra("d");
            Thread.sleep(2000); //Simulamos 2 segundos de demora
            tiempoJugador1 = ahorcado.getTiempo();
        }
        catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        //Juega el jugador2 que es anónimo
        //Para que su tiempo aparezca en el ranking debe ingresar un nombre
        ahorcado.jugadorAnonimo();
        ahorcado.getJugador().setNombre("Miguel");
        ahorcado.iniciarCronometro();
        try {
            ahorcado.ingresarLetra("a");
            ahorcado.ingresarLetra("h");
            ahorcado.ingresarLetra("o");
            ahorcado.ingresarLetra("r");
            ahorcado.ingresarLetra("c");
            ahorcado.ingresarLetra("d");
            Thread.sleep(5000); //Simulamos 5 segundos de demora
            tiempoJugador2 = ahorcado.getTiempo();
        }
        catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        //assert
        assertEquals("Ranking por tiempo\n" +
                             "1) Juan: 2 seg.\n" +
                             "2) Miguel: 5 seg.",
                ahorcado.rankingPorTiempo());
        //Print to console
        System.out.print(ahorcado.rankingPorTiempo());
    }

    @Test
    public void seleccionarModoDeJuegoTest() {
        //arrange
        Ahorcado ahorcado = new Ahorcado();
        //act
        ahorcado.seleccionarModo("Estandar");
        //assert
        assertEquals("Estandar", ahorcado.getModoJuego());
    }

    @Test
    public void seleccionarIdiomaTest() {
        //arrange
        Ahorcado ahorcado = new Ahorcado();
        //act
        ahorcado.seleccionarIdioma("esp");
        //assert
        assertEquals("esp", ahorcado.getIdiomaPalabra());
    }

    @Test
    public void seleccionarDificultadTest() {
        //arrange
        Ahorcado ahorcado = new Ahorcado();
        //act
        ahorcado.seleccionarDificultad("Facil");
        //assert
        assertEquals("Facil", ahorcado.getDificultadPal());
    }

    @Test
    public void seleccionarTematicaTest() {
        //arrange
        Ahorcado ahorcado = new Ahorcado();
        //act
        ahorcado.seleccionarTematica("Animales");
        //assert
        assertEquals("Animales", ahorcado.getTematicaPal());
    }

    @Test
    public void seleccionarTiempoLimiteTest() {
        //arrange
        Ahorcado ahorcado = new Ahorcado();
        //act
        ahorcado.seleccionarTiempoLimite(60); // segundos
        //assert
        assertEquals(60, ahorcado.getTiempoLimite());
    }

    @Test
    //Verificamos que el idioma de la palabra obtenida para jugar
    //corresponda con el idioma seleccionado en opciones
    public void verificarIdiomaPalabraTest() {
        //arrange
        Ahorcado ahorcado = new Ahorcado();
        //act
        ahorcado.seleccionarIdioma("esp");
        ahorcado.comenzarJuego();
        //assert
        assertEquals("esp", ahorcado.getPalabra().getIdioma());
    }

    @Test
    //Verificamos que la dificultad de la palabra obtenida para jugar
    //corresponda con la dificultad seleccionada en opciones
    public void verificarDificultadPalabraTest() {
        //arrange
        Ahorcado ahorcado = new Ahorcado();
        //act
        ahorcado.seleccionarDificultad("Facil");
        ahorcado.comenzarJuego();
        //assert
        assertEquals("Facil", ahorcado.getPalabra().getDificultad());
    }

    @Test
    //Verificamos que la tematica de la palabra obtenida para jugar
    //corresponda con la tematica seleccionada en opciones
    public void verificarTematicaPalabraTest() {
        //arrange
        Ahorcado ahorcado = new Ahorcado();
        //act
        ahorcado.seleccionarTematica("Animales");
        ahorcado.comenzarJuego();
        //assert
        assertEquals("Animales", ahorcado.getPalabra().getTematica());
    }

}

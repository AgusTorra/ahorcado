package main.java;

public class Palabra {
    private String dificultad;
    private String tematica;
    private String idioma;
    private String palabra;
    private int longitud;

    public Palabra() {
    }

    public Palabra(String dificultad, String idioma, String tematica, String palabra) {
        this.dificultad = dificultad;
        this.idioma = idioma;
        this.tematica = tematica;
        this.palabra = palabra;
    }

    /*public String getPalabra() {
        return palabra;
    }*/

    /*public int getLongitud() {
        return this.palabra.length();
    }*/

    /*public void setLongitud(int longitud) {
        this.longitud = longitud;
    }*/

    /*public void setPalabra(String palabra) {
        this.palabra = palabra;
    }*/

    public String getDificultad() {
        return dificultad;
    }

    /*public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }*/

    public String getTematica() {
        return tematica;
    }

    /*public void setTematica(String tematica) {
        this.tematica = tematica;
    }*/

    public String getIdioma() {
        return idioma;
    }

    /*public void setIdioma(String idioma) {
        this.idioma = idioma;
    }*/
}

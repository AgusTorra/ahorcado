package main.java;

public class Jugador {

    private char sexo;
    private String nombre;
    private String mail;
    private String password;

    public Jugador(String nombre, String mail, String password, char sexo) {
        this.sexo = sexo;
        this.nombre = nombre;
        this.mail = mail;
        this.password = password;
    }

    public Jugador(String nombre) {
        this.nombre = nombre;
    }

    /*public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }*/

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /*public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }*/
}

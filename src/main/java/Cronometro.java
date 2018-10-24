package main.java;

public final class Cronometro{
    private long inicio, fin;

    public void start(){
        inicio = System.currentTimeMillis();
    }

    public void stop(){
        fin = System.currentTimeMillis();
    }

    /*public long getTime() {
        return fin - inicio;
    }

    public long getMilliseconds() {
        return fin - inicio;
    }*/

    public long getSeconds() {
        return (fin - inicio) / 1000;
    }

    /*public double getMinutes() {
        return (fin - inicio) / 60000.0;
    }

    public double getHours() {
        return (fin - inicio) / 3600000.0;
    }*/

}

package Modelo;

public class Proceso implements Cloneable{//ya hice los respectivos cambios

    private String id;
    private int tiempoLlegada;
    private int tiempoRafaga;
    private int tiempoComienzo;
    private int tiempoFinal;
    private int tiempoRetorno;
    private int tiempoEspera;
    private int tiempoLlegadaCabeza;
    //private int prioridad;
    private int tiempoRafagaEjecutada;

    public Proceso(int id, int tiempoLlegada, int tiempoRafaga/*, int prioridad*/) {
        this.id = "P" + id;
        this.tiempoLlegada = tiempoLlegadaCabeza = tiempoLlegada;
        this.tiempoRafaga = tiempoRafaga;
        //this.prioridad = prioridad;
        tiempoComienzo = 0;
        tiempoFinal = 0;
        tiempoRetorno = 0;
        tiempoEspera = 0;
        tiempoRafagaEjecutada = 0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTiempoLlegada() {
        return tiempoLlegada;
    }

    public void setTiempoLlegada(int tiempoLlegada) {
        this.tiempoLlegada = tiempoLlegada;
    }

    public int getTiempoRafaga() {
        return tiempoRafaga;
    }

    public void setTiempoRafaga(int tiempoRafaga) {
        this.tiempoRafaga = tiempoRafaga;
    }

    public int getTiempoComienzo() {
        return tiempoComienzo;
    }

    public void setTiempoComienzo(int tiempoComienzo) {
        this.tiempoComienzo = tiempoComienzo;
    }

    public int getTiempoFinal() {
        return tiempoFinal;
    }

    public void setTiempoFinal(int tiempoFinal) {
        this.tiempoFinal = tiempoFinal;
    }

    public int getTiempoRetorno() {
        return tiempoRetorno;
    }

    public void setTiempoRetorno(int tiempoRetorno) {
        this.tiempoRetorno = tiempoRetorno;
    }

    public int getTiempoEspera() {
        return tiempoEspera;
    }

    public void setTiempoEspera(int tiempoEspera) {
        this.tiempoEspera = tiempoEspera;
    }

    public int getTiempoLlegadaCabeza() {
        return tiempoLlegadaCabeza;
    }

    public void setTiempoLlegadaCabeza(int tiempoLlegadaCabeza) {
        this.tiempoLlegadaCabeza = tiempoLlegadaCabeza;
    }

    /*public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }*/

    public int getTiempoRafagaEjecutada() {
        return tiempoRafagaEjecutada;
    }

    public void setTiempoRafagaEjecutada(int tiempoRafagaEjecutada) {
        this.tiempoRafagaEjecutada = tiempoRafagaEjecutada;
    }
    
    public Object[] resume() {
        return new Object[]{id/*, prioridad*/, tiempoLlegadaCabeza, tiempoRafaga, tiempoComienzo, tiempoFinal, tiempoRetorno, tiempoEspera};
    }
    
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

}

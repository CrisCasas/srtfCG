package Modelo;

public class Prioridad {//acá esta la lógica fuerte | Ya se han realizado todos los cambios

    private Cola<Proceso> cola;
    private Cola<Memento> guardarEstados;
    private int tiempoFinalActual;
    private int serialId;

    public Prioridad() {
        cola = new Cola<Proceso>();
        guardarEstados = new Cola<Memento>();
        tiempoFinalActual = 0;
        serialId = 0;
        guardarEstados.aniadir(saveToMemento());
    }

    private int aleatorio(int min, int max) {
        return (int) (Math.random() * max + min);
    }

    public void calcularTiempos(Proceso proceso) {
        proceso.setTiempoComienzo(tiempoFinalActual);
        proceso.setTiempoFinal(proceso.getTiempoRafaga() + proceso.getTiempoComienzo());
        proceso.setTiempoRetorno(proceso.getTiempoFinal() - proceso.getTiempoLlegadaCabeza());
        proceso.setTiempoEspera(proceso.getTiempoRetorno() - proceso.getTiempoRafagaEjecutada());
        tiempoFinalActual = proceso.getTiempoFinal();
        guardarEstados.aniadir(saveToMemento());
    }

    public void recalcuteTime(Proceso proceso) {
        retoreFromMemento(guardarEstados.getDatos(guardarEstados.getTamanio() - 1));
        calcularTiempos(proceso);
    }
    

    public Object[] getDatoProceso(Proceso proceso) {
        calcularTiempos(proceso);
        return proceso.resume();
    }

    public Proceso aniadirProceso() {
        Proceso proceso = new Proceso(serialId + 1, serialId, aleatorio(1, 12));
        cola.aniadir(proceso);
        serialId++;
        return proceso;
    }

    public void aniadirUltimoProceso(Proceso proceso) {
        cola.aniadir(proceso);
    }

    public Proceso atenderProceso() {
        return cola.atender();
    }

    public void createBlockProcess(Proceso proceso) {
        proceso.setId(proceso.getId() + "*");
        proceso.setTiempoRafaga(proceso.getTiempoRafaga() - proceso.getTiempoRafagaEjecutada());
        proceso.setTiempoLlegada(tiempoFinalActual);
    }

    public Proceso atenderProcesoPorPrioridad() {
        Proceso proceso = null;
        for (int i = 1; i <= 12; i++) {
            for (int j = 1; j <= cola.getTamanio(); j++) {
                proceso = cola.getDatos(j);
                if (proceso.getTiempoRafaga() == i) {
                    if (proceso.getTiempoLlegada() <= tiempoFinalActual) {
                        cola.eliminar(j);
                        return proceso;
                    }
                }
            }
        }
        return proceso;
    }

    private Memento saveToMemento() {
        return new Memento(tiempoFinalActual);
    }

    private void retoreFromMemento(Memento memento) {
        tiempoFinalActual = memento.getSavedState();
    }

    public int getSerialId() {
        return serialId;
    }

    public int getTiempoFinalActual() {
        return tiempoFinalActual;
    }

    public boolean estaVaciaCola() {
        return cola.estaVacio();
    }

    public void finalizeCola() throws Throwable {
        cola.finalize();
        guardarEstados.finalize();
    }
}

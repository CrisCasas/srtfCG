package Modelo;

public class Modelo {//Esta igual

	private Prioridad colaListos;
	private Prioridad colaBloqueados;

	public Modelo() {
		colaListos = new Prioridad();
		colaBloqueados = new Prioridad();
	}

	public Prioridad getColaListos() {
		return colaListos;
	}

	public Prioridad getColaBloqueados() {
		return colaBloqueados;
	}

	public void finalizeColas() throws Throwable {
		colaListos.finalizeCola();
		colaBloqueados.finalizeCola();
	}
}

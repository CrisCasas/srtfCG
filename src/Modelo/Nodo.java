package Modelo;

public class Nodo<T> {//acá no se necesitan cambios
	private T dato;
	private Nodo<T> siguiente;

	public Nodo() {
		dato = null;
		siguiente = null;
	}

	public T getDato() {
		return dato;
	}

	public void setDato(T dato) {
		this.dato = dato;
	}

	public Nodo<T> getSiguiente() {
		return siguiente;
	}

	public void setSiguiente(Nodo<T> siguiente) {
		this.siguiente = siguiente;
	}
}

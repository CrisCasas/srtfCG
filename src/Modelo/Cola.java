package Modelo;

public class Cola<T> {

	private Nodo<T> cabeza;
	private int tamanio;

	public Cola() {
		cabeza = null;
		tamanio = 0;
	}

	public void aniadir(T dato) {
		Nodo<T> nodo = new Nodo<T>();
		nodo.setDato(dato);
		nodo.setSiguiente(null);
		if (cabeza == null) {
			cabeza = nodo;
		} else {
			Nodo<T> auxiliar = cabeza;
			while (auxiliar.getSiguiente() != null) {
				auxiliar = auxiliar.getSiguiente();
			}
			auxiliar.setSiguiente(nodo);
		}
		tamanio++;
	}

	public T atender() {
		T dato;
		Nodo<T> auxiliar = cabeza;
		cabeza = auxiliar.getSiguiente();
		dato = auxiliar.getDato();
		auxiliar = null;
		tamanio--;
		return dato;
	}

	public boolean eliminar(int posicion) {
		if (tamanio >= posicion) {
			Nodo<T> auxiliar = cabeza;
			if (posicion == 1) {
				cabeza = auxiliar.getSiguiente();
				auxiliar = null;
			} else {
				for (int i = 1; i < posicion - 1; i++) {
					auxiliar = auxiliar.getSiguiente();
				}
				Nodo<T> temporal = auxiliar.getSiguiente();
				auxiliar.setSiguiente(temporal.getSiguiente());
				temporal = null;
			}
			tamanio--;
			return true;
		}
		return false;
	}

	public T getDatos(int pos) {
		Nodo<T> auxiliar = cabeza;
		int p = 1;
		if ((pos <= tamanio) && (pos >= 0)) {
			while ((p < pos) && (auxiliar != null)) {
				auxiliar = auxiliar.getSiguiente();
				p++;
			}
			return auxiliar.getDato();
		}
		return auxiliar.getDato();
	}

	public boolean estaVacio() {
		if (cabeza == null)
			return true;
		return false;
	}//hasta acá todo esta igual

	public int getTamanio() {
		return tamanio;
	}

	@Override
	protected void finalize() throws Throwable {
		Nodo<T> auxiliar;
		while (cabeza != null) {
			auxiliar = cabeza;
			cabeza = auxiliar.getSiguiente();
			auxiliar = null;
		}
		cabeza = null;
		System.gc();
	}
}

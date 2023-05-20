package Vista;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class ColorCeldaTabla extends DefaultTableCellRenderer {//Esta esta igual
	
	private Component componente;

	@Override
	public Component getTableCellRendererComponent(JTable tabla, Object valor, boolean estaSeleccionado, boolean foco,
			int fila, int columna) {
		componente = super.getTableCellRendererComponent(tabla, valor, estaSeleccionado, foco, fila, columna);
		if (valor == null)
			componente.setBackground(Color.WHITE);
		else if (valor.equals("  "))
			componente.setBackground(new Color(251, 255, 83));
		else if(valor.equals(" "))
			componente.setBackground(new Color(78, 218, 18));
		else
			componente.setBackground(Color.WHITE);
		
		return componente; 	
	}
	
}

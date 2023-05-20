package Vista;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class TablaPnl extends JPanel {

    private JTable tabla;
    private DefaultTableModel tablaModelo;

    public TablaPnl(JFrame ventana, String titulo) {
        setBorder(BorderFactory.createTitledBorder(titulo));
        setLayout(new BorderLayout());

        tabla = new JTable();
        JScrollPane scrollTabla = new JScrollPane(tabla);
        tabla.setFillsViewportHeight(true);

        DefaultTableCellRenderer celdaRender = new DefaultTableCellRenderer();
        celdaRender.setHorizontalAlignment(SwingConstants.CENTER);
        tabla.setDefaultRenderer(Object.class, celdaRender);

        add(tabla.getTableHeader(), BorderLayout.PAGE_START);
        add(scrollTabla, BorderLayout.CENTER);

        tabla.setEnabled(false);
        setVisible(true);
    }

    public JTable getTabla() {
        return tabla;
    }

    public void setTabla(JTable tabla) {
        this.tabla = tabla;
    }

    public DefaultTableModel getTablaModelo() {
        return tablaModelo;
    }

    public void setTablaModelo(DefaultTableModel tablaModelo) {
        this.tablaModelo = tablaModelo;
        tabla.setModel(tablaModelo);
    }

    public int removeRow(int columnaIndice, String valor) {
        int fila = 0;
        for (; fila < tabla.getRowCount(); fila++) {
            if (tabla.getValueAt(fila, columnaIndice).equals(valor)) {
                tablaModelo.removeRow(fila);
                break;
            }
        }
        return fila;
    }

    public int buscarFila(int columnaIndice, String valor) {
        int fila = 0;
        for (; fila < tabla.getRowCount(); fila++) {
            if (tabla.getValueAt(fila, columnaIndice).equals(valor)) {
                return fila;
            }
        }
        return -1;
    }
}

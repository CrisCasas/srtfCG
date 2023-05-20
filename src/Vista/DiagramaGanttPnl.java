package Vista;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class DiagramaGanttPnl extends TablaPnl {//Ya se hicieron lo cambios

    ColorCeldaTabla celdaRender;

    public DiagramaGanttPnl(JFrame ventana, String titulo) {
        super(ventana, titulo);
        getTabla().setShowGrid(false);
        getTabla().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        setTablaModelo(new DefaultTableModel(new Object[]{"Procesos"}, 0));
        celdaRender = new ColorCeldaTabla();
        celdaRender.setHorizontalAlignment(SwingConstants.CENTER);
        getTabla().setDefaultRenderer(Object.class, celdaRender);
    }

    public void setInitialColumns(int max) {
        for (int i = 1; i <= max; i++) {
            getTablaModelo().addColumn(i);
        }
    }

    public void pintarProceso(Object[] proceso) throws Exception { //acá sobra el método run que tiene un hilo
        int fila = getTablaModelo().getRowCount();
        getTablaModelo().addRow(new Object[]{proceso[0]});
        for (int i = (int) proceso[4]; i < (int) proceso[5]; i++) {
            getTablaModelo().addColumn(String.valueOf(i));
        }
    }
}

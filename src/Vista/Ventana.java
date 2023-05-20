package Vista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

public class Ventana extends JFrame {//Esta es igual

	private final String[] nombreColumna = { "Proceso", "T. Llegada", "T. Rafaga" };

	private DiagramaGanttPnl pnlTablaGantt;
	private TablaPnl pnlTablaColaListos;
	private TablaPnl pnlTablaColaBloqueados;
	private TablaPnl pnlTabla;
	private ActionPanel pnlAction;

	public Ventana(String titulo) {
		setTitle(titulo);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setSize((int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.9),
				(int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.84));
		setLocationRelativeTo(null);

		pnlTablaGantt = new DiagramaGanttPnl(this, "DIAGRAMA GANTT");
		add(pnlTablaGantt, BorderLayout.SOUTH);

		JPanel pnlEstado = new JPanel();

		pnlTablaColaListos = new TablaPnl(this, "Cola Listos");
		iniciarTablaEstadoPnl(pnlTablaColaListos, nombreColumna);
		pnlEstado.add(pnlTablaColaListos);

		pnlTablaColaBloqueados = new TablaPnl(this, "Cola Bloqueados");
		iniciarTablaEstadoPnl(pnlTablaColaBloqueados, nombreColumna);
		pnlEstado.add(pnlTablaColaBloqueados);

		add(pnlEstado, BorderLayout.EAST);

		pnlTabla = new TablaPnl(this, "Tabla Procesos");
		add(pnlTabla, BorderLayout.CENTER);

		pnlAction = new ActionPanel(this);
		add(pnlAction, BorderLayout.NORTH);

		setResizable(false);
	}

	private void iniciarTablaEstadoPnl(TablaPnl tabla, String[] nombreColumna) {
		tabla.setPreferredSize(new Dimension(getSize().width / 6, (int) (getSize().height * 0.42)));
		tabla.setTablaModelo(new DefaultTableModel(nombreColumna, 0));
	}

	public DiagramaGanttPnl getPnlTablaGantt() {
		return pnlTablaGantt;
	}

	public void setPnlTablaGantt(DiagramaGanttPnl pnlTablaGantt) {
		this.pnlTablaGantt = pnlTablaGantt;
	}

	public TablaPnl getPnlTablaColaListos() {
		return pnlTablaColaListos;
	}

	public void setPnlTableColaListos(TablaPnl pnlTablaColaListos) {
		this.pnlTablaColaListos = pnlTablaColaListos;
	}

	public TablaPnl getPnlTableColaBloqueados() {
		return pnlTablaColaBloqueados;
	}

	public void setPnlTablaColaBloqueados(TablaPnl pnlTablaColaBloqueados) {
		this.pnlTablaColaBloqueados = pnlTablaColaBloqueados;
	}

	public TablaPnl getPnlTabla() {
		return pnlTabla;
	}

	public void setPnlTabla(TablaPnl pnlTabla) {
		this.pnlTabla = pnlTabla;
	}

	public ActionPanel getPnlAction() {
		return pnlAction;
	}

	public void setPnlAction(ActionPanel pnlAction) {
		this.pnlAction = pnlAction;
	}
}

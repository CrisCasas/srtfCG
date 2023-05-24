package Vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ActionPanel extends JPanel {//Esta también esta igual.

	private JButton btnComenzar;
	private JButton btnAtender;
	private JButton btnAniadir;
	private JButton btnBloqueo;
	private JButton btnDesbloqueo;
	private JButton btnReiniciar;
	private JButton btnSalir;
	private boolean isButtonPressed;
	
	public ActionPanel(JFrame window) {

		btnComenzar = new JButton("Comenzar");
		add(btnComenzar);

		btnAtender = new JButton("Servicio");
		add(btnAtender);

		btnAniadir = new JButton("Añadir");
		btnAniadir.addActionListener(new ButtonClickListener());
		add(btnAniadir);

		btnBloqueo = new JButton("Bloqueo");
		add(btnBloqueo);

		btnDesbloqueo = new JButton("Desbloqueo");
		add(btnDesbloqueo);

		btnReiniciar = new JButton("Reinicio");
		add(btnReiniciar);

		btnSalir = new JButton("Salir");
		add(btnSalir);

		setVisible(true);
	}
	
    public boolean isButtonPressed() {
		return isButtonPressed;
	}

	public void setButtonPressed(boolean isButtonPressed) {
		this.isButtonPressed = isButtonPressed;
	}

	private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            isButtonPressed = true;
        }
    }

	public JButton getBtnComenzar() {
		return btnComenzar;
	}

	public void setBtnComenzar(JButton btnComenzar) {
		this.btnComenzar = btnComenzar;
	}

	public JButton getBtnAtender() {
		return btnAtender;
	}

	public void setBtnAtender(JButton btnAtender) {
		this.btnAtender = btnAtender;
	}

	public JButton getBtnAniadir() {
		return btnAniadir;
	}

	public void setBtnAniadir(JButton btnAniadir) {
		this.btnAniadir = btnAniadir;
	}

	public JButton getBtnBloqueo() {
		return btnBloqueo;
	}

	public void setBtnBloqueo(JButton btnBloqueo) {
		this.btnBloqueo = btnBloqueo;
	}

	public JButton getBtnDesbloqueo() {
		return btnDesbloqueo;
	}

	public void setBtnDesbloqueo(JButton btnDesbloqueo) {
		this.btnDesbloqueo = btnDesbloqueo;
	}
	
	public JButton getBtnReiniciar() {
		return btnReiniciar;
	}

	public void setBtnReiniciar(JButton btnReiniciar) {
		this.btnReiniciar = btnReiniciar;
	}

	public JButton getBtnSalir() {
		return btnSalir;
	}

	public void setBtnSalir(JButton btnSalir) {
		this.btnSalir = btnSalir;
	}
}

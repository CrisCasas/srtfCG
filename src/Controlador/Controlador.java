package Controlador;

import javax.swing.JOptionPane;

import javax.swing.table.DefaultTableModel;

import Modelo.Modelo;
import Modelo.Proceso;

import Vista.Ventana;

import java.awt.Color;

import javax.swing.ImageIcon;

public class Controlador {

    private Modelo modelo;
    private Ventana vista;
    private Thread criticalSection;
    private int remainingTime;
    private boolean isAvalible;

    public Controlador(Modelo modelo, Ventana vista) {
        this.modelo = modelo;
        this.vista = vista;
        this.remainingTime = 0;
        this.isAvalible = true;
    }

    public void inicializarControlador(String[] nombreColumna) {
        setTablaNombreColumna(nombreColumna);
        vista.getPnlAction().getBtnComenzar().addActionListener(e -> iniciarAction());
        vista.getPnlAction().getBtnAtender().addActionListener(e -> atenderAction());
        vista.getPnlAction().getBtnAniadir().addActionListener(e -> aniadirAction());
        vista.getPnlAction().getBtnBloqueo().addActionListener(e -> bloquearAction());
        vista.getPnlAction().getBtnDesbloqueo().addActionListener(e -> desbloquearAction());
        vista.getPnlAction().getBtnReiniciar().addActionListener(e -> reiniciarAction());
        vista.getPnlAction().getBtnSalir().addActionListener(e -> salirAction());
        vista.setVisible(true);
    }

    private void setTablaNombreColumna(String[] nombreColumna) {
        if (vista.getPnlTabla().getTablaModelo() == null) {
            vista.getPnlTabla().setTablaModelo(new DefaultTableModel(nombreColumna, 0));
            vista.getPnlTablaGantt().setInitialColumns(100);
        }
    }

    private void iniciarAction() {
        if (modelo.getColaListos().getSerialId() == 0) {
            for (int i = 1; i <= 5; i++) {
                vista.getPnlTablaColaListos().getTablaModelo().addRow(modelo.getColaListos().aniadirProceso().resume());
            }
        } else {
            JOptionPane.showMessageDialog(null, "NO puede iniciar más de una vez!", "Comenzar",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    private void atenderAction() {

        if (!modelo.getColaListos().estaVaciaCola()) {
            Proceso proceso = modelo.getColaListos().atenderProcesoPorPrioridad();
            vista.getPnlTablaColaListos().removeRow(0,proceso.getId());
            if (remainingTime > proceso.getTiempoRafaga()) {
                isAvalible = false;
                criticalSection.interrupt();
            } else {
                if (remainingTime > 0) {
                    modelo.getColaBloqueados().aniadirUltimoProceso(proceso);
                    vista.getPnlTablaColaListos().getTablaModelo().addRow(proceso.resume());
                    return;
                }
                 
            }
            while (true) {
                if (isAvalible) {
                    paintProcess(proceso);
                    break;
                }
            }

        } else {
            JOptionPane.showMessageDialog(null, "¡No hay ningún procesos por atender!", "Atender",
                    JOptionPane.WARNING_MESSAGE);
        }

    }
    

    private void aniadirAction() {
    	
        //vista.getPnlTablaColaListos().getTablaModelo().addRow(modelo.getColaListos().aniadirProceso());
    	//vista.getPnlTablaColaListos().getTablaModelo().addRow(modelo.getColaListos().aniadirProceso().resume());
    	
    	try {
    		//Object[] child = modelo.getColaListos().aniadirProceso().resume();
        	//vista.getPnlTablaColaListos().getTablaModelo().addRow(child);
    		vista.getPnlTablaColaListos().getTablaModelo().addRow(modelo.getColaListos().aniadirProceso().resume());
        	criticalSection.interrupt();
			criticalSection.sleep(80);
			if (!modelo.getColaBloqueados().estaVaciaCola()) {
	            Proceso proceso = modelo.getColaBloqueados().atenderProceso();
	            modelo.getColaListos().aniadirUltimoProceso(proceso);
	            //vista.getPnlTableColaBloqueados().getTablaModelo().removeRow(0);
	            vista.getPnlTableColaBloqueados().removeRow(0,proceso.getId());
	            vista.getPnlTablaColaListos().getTablaModelo().addRow(proceso.resume());
	        }
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
      				
				
    }
       

    private void bloquearAction() {
        if (vista.getPnlAction().getBtnBloqueo().isEnabled()) {
            criticalSection.interrupt();
        } else {
            JOptionPane.showMessageDialog(null, "NO hay proceso para bloquear", "Bloquear",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    private void desbloquearAction() {
        if (!modelo.getColaBloqueados().estaVaciaCola()) {
            Proceso proceso = modelo.getColaBloqueados().atenderProceso();
            modelo.getColaListos().aniadirUltimoProceso(proceso);
            vista.getPnlTableColaBloqueados().getTablaModelo().removeRow(0);
            vista.getPnlTablaColaListos().getTablaModelo().addRow(proceso.resume());
        } else {
            JOptionPane.showMessageDialog(null, "NO hay proceso por desbloquear", "Desbloquear",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    private void reiniciarAction() {
        int entrada = JOptionPane.showConfirmDialog(null, "¿Quiere reiniciar el programa?", "Reiniciar",
                JOptionPane.YES_NO_OPTION);
        if (entrada == JOptionPane.YES_OPTION) {
            try {
                modelo.finalizeColas();
                modelo = new Modelo();
                vista.getPnlTabla().getTablaModelo().setNumRows(0);
                vista.getPnlTablaColaListos().getTablaModelo().setNumRows(0);
                vista.getPnlTableColaBloqueados().getTablaModelo().setNumRows(0);
                vista.getPnlTablaGantt().getTablaModelo().setNumRows(0);
                vista.getPnlTablaGantt().getTablaModelo().setColumnCount(1);
                vista.getPnlTablaGantt().setInitialColumns(100);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }

    private void salirAction() {
        int entrada = JOptionPane.showConfirmDialog(null, "Hasta la próxima", "Salir",
                JOptionPane.YES_NO_OPTION);
        if (entrada == JOptionPane.YES_OPTION)
	try {
            modelo.finalizeColas();
            System.exit(0);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    private void paintProcess(Proceso proceso) {
        modelo.getColaListos().calcularTiempos(proceso);
        DefaultTableModel tablaModeloGantt = vista.getPnlTablaGantt().getTablaModelo();
        int fila = getProcesoFila(proceso.getId());
        criticalSection = new Thread(new Runnable() {
            @Override
            public void run() {
                vista.getPnlAction().getBtnAtender().setEnabled(false);
                for (int i = proceso.getTiempoLlegada(); i < proceso.getTiempoComienzo(); i++) {
                    tablaModeloGantt.setValueAt("  ", fila, i + 1);
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                    }
                }
                vista.getPnlAction().getBtnAtender().setEnabled(true);
                vista.getPnlAction().getBtnBloqueo().setEnabled(true);
                vista.getPnlAction().getBtnBloqueo().setBackground(Color.red);
                for (int i = proceso.getTiempoComienzo(); i < proceso.getTiempoFinal(); i++) {
                    proceso.setTiempoRafagaEjecutada(proceso.getTiempoRafagaEjecutada() + 1);
                    remainingTime = proceso.getTiempoRafaga() - proceso.getTiempoRafagaEjecutada();
                    tablaModeloGantt.setValueAt(" ", fila, i + 1);
                    tablaModeloGantt.fireTableDataChanged();
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        vista.getPnlAction().getBtnBloqueo().setEnabled(false);
                        
                        try {
                            Proceso aux = (Proceso) proceso.clone();
                            proceso.setTiempoRafaga(proceso.getTiempoRafagaEjecutada());
                            if (proceso.getTiempoRafaga() > 0) {
                                modelo.getColaListos().recalcuteTime(proceso);
                                modelo.getColaListos().createBlockProcess(aux);
                                modelo.getColaBloqueados().aniadirUltimoProceso(aux);
                                vista.getPnlTableColaBloqueados().getTablaModelo().addRow(aux.resume());
                                vista.getPnlTabla().getTablaModelo().addRow(proceso.resume());
                            }
                            isAvalible = true;
                            remainingTime = 0;
                        } catch (CloneNotSupportedException e1) {
                            System.err.println("Error al clonar - " + e1);
                        }
                        Thread.currentThread().stop();
                    }
                }
                isAvalible = true;
                remainingTime = 0;
                modelo.getColaListos().recalcuteTime(proceso);
                vista.getPnlAction().getBtnBloqueo().setEnabled(false);
                vista.getPnlAction().getBtnBloqueo().setBackground(Color.green);
                vista.getPnlTabla().getTablaModelo().addRow(proceso.resume());
            }
        });
        criticalSection.start();
    }

    private int getProcesoFila(String id) {
        int max = id.indexOf("*");
        int fila = vista.getPnlTablaGantt().getTablaModelo().getRowCount();
        if (max != -1) {
            fila = vista.getPnlTablaGantt().buscarFila(0, id.substring(0, max));
        } else {
            vista.getPnlTablaGantt().getTablaModelo().addRow(new Object[]{id});
        }
        return fila;
    }

}

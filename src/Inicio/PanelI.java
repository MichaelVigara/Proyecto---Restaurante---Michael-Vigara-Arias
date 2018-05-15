package Inicio;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;

public class PanelI extends JPanel {

	/**
	 * Create the panel.
	 */
	
	ImageIcon imagen;
	String nombre;
	private final Action action = new SwingAction();
	
	public PanelI(String nombre) {
		this.nombre = nombre;
		setLayout(null);
		
		JButton btnNewButton = new JButton("Administracion");
		btnNewButton.setAction(action);
		btnNewButton.setBounds(39, 415, 127, 48);
		add(btnNewButton);
		
		JButton btnRestaurante = new JButton("Restaurante");
		btnRestaurante.setBounds(570, 415, 127, 48);
		add(btnRestaurante);
		

	}
	
	public void paint(Graphics g) {
		Dimension tamano = getSize();
		imagen = new ImageIcon(getClass().getResource(nombre));
		g.drawImage(imagen.getImage(),0,0,tamano.width,tamano.height, null);
		setOpaque(false);
		super.paint(g);
	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}

package Administracion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ConexionBBDD.ConexionBBDD;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class HistorialTicketsII {

	JFrame frame;
	private JTable HistorialTickets;
	private JButton button;
	private JLabel lblFondo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HistorialTicketsII window = new HistorialTicketsII();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public HistorialTicketsII() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 420, 374);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 384, 283);
		frame.getContentPane().add(scrollPane);
		
		HistorialTickets = new JTable();
		HistorialTickets.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Fecha", "Mesa", "Tipo de Pago", "Importe Total"
			}
		));
		scrollPane.setViewportView(HistorialTickets);
		
		ConexionBBDD Prueba = new ConexionBBDD();
		HistorialTickets.setModel(Prueba.HistorialTickets());
		
		button = new JButton("Atras");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
		button.setBounds(10, 305, 89, 23);
		frame.getContentPane().add(button);
		
		lblFondo = new JLabel("Fondo");
		lblFondo.setIcon(new ImageIcon("C:\\Users\\DAW1\\Documents\\DAW1\\Programacion\\Marte\\Proyecto BBDD - Restaurante\\src\\Inicio\\fondo3.jpg"));
		lblFondo.setBounds(0, 0, 404, 335);
		frame.getContentPane().add(lblFondo);
	}
	
}

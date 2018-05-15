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

public class HistorialPedidosII {

	JFrame frame;
	private JTable HistorialPedidos;
	private JButton btnAtras;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HistorialPedidosII window = new HistorialPedidosII();
					window.frame.setVisible(true);
					window.frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public HistorialPedidosII() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 611, 307);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 581, 213);
		frame.getContentPane().add(scrollPane);
		
		HistorialPedidos = new JTable();
		HistorialPedidos.setModel(new DefaultTableModel(
			new Object[][] {
				{"2018-05-09 11:08:56.0", "1", "8", "Holandesa", "3"},
				{"2018-05-09 11:08:56.0", "2", "2", "Gazpacho o Salmorejo de Temporada", "1"},
				{"2018-05-09 11:08:56.0", "3", "16", "Solomillo de vacuno, salteado de setas y su jugo", "2"},
			},
			new String[] {
				"Fecha de la Comanda", "Numero de Comanda", "Numero de Mesa", "Productos", "Cantidad"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				true, true, true, false, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		HistorialPedidos.getColumnModel().getColumn(0).setMaxWidth(150);
		HistorialPedidos.getColumnModel().getColumn(1).setMaxWidth(75);
		HistorialPedidos.getColumnModel().getColumn(2).setMaxWidth(75);
		HistorialPedidos.getColumnModel().getColumn(3).setResizable(false);
		HistorialPedidos.getColumnModel().getColumn(3).setPreferredWidth(15);
		HistorialPedidos.getColumnModel().getColumn(3).setMaxWidth(300);
		HistorialPedidos.getColumnModel().getColumn(4).setPreferredWidth(70);
		HistorialPedidos.getColumnModel().getColumn(4).setMaxWidth(70);
		scrollPane.setViewportView(HistorialPedidos);
		
		ConexionBBDD Prueba = new ConexionBBDD();
		HistorialPedidos.setModel(Prueba.HistorialPedidos());
		
		btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
		btnAtras.setBounds(10, 235, 89, 23);
		frame.getContentPane().add(btnAtras);
		
		JLabel lblFondo = new JLabel("Fondo");
		lblFondo.setIcon(new ImageIcon("C:\\Users\\DAW1\\Documents\\DAW1\\Programacion\\Marte\\Proyecto BBDD - Restaurante\\src\\Inicio\\fondo3.jpg"));
		lblFondo.setBounds(0, 0, 595, 268);
		frame.getContentPane().add(lblFondo);
	}
}

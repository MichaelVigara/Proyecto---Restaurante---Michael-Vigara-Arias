package Administracion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ConexionBBDD.ConexionBBDD;

public class ModificarProd {

	private JFrame frame;
	private JTable ModProd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModificarProd window = new ModificarProd();
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
	public ModificarProd() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 577, 417);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 336, 302);
		frame.getContentPane().add(scrollPane);
		
		ModProd = new JTable();
		ModProd.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID Producto", "Nombre del Producto", "Categoria"
			}
		));
		scrollPane.setViewportView(ModProd);
		
		ConexionBBDD Prueba = new ConexionBBDD();
		ModProd.setModel(Prueba.ModificarProd());
	}
}

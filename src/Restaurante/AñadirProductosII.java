package Restaurante;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ConexionBBDD.ConexionBBDD;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import javax.swing.JSpinner;

public class A˝adirProductosII {

	JFrame frame;
	private JTextField textNombreProd;
	private JTable A˝adirProductoProdCateg;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					A˝adirProductosII window = new A˝adirProductosII();
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
	public A˝adirProductosII() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 594, 449);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 46, 335, 313);
		frame.getContentPane().add(scrollPane);
		
		A˝adirProductoProdCateg = new JTable();
		A˝adirProductoProdCateg.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nombre del Producto", "Categoria"
			}
		));
		scrollPane.setViewportView(A˝adirProductoProdCateg);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(355, 145, 46, 14);
		frame.getContentPane().add(lblNombre);
		
		textNombreProd = new JTextField();

		textNombreProd.setBounds(468, 142, 100, 20);
		frame.getContentPane().add(textNombreProd);
		textNombreProd.setColumns(10);
		
		JComboBox Categoria = new JComboBox();

		Categoria.setModel(new DefaultComboBoxModel(new String[] {"Todas las Categorias", "Refrescos", "Bebidas Alcoholicas", "Casqueria", "Carnes", "Pescados", "Sopas", "Entrantes", "Pizzas", "Ensaladas", "Arroces", "Bocadillos", "Postres", "Menu Infantil", "Hamburguesas", "Pasta", "Vinos"}));
		Categoria.setBounds(10, 11, 148, 20);
		frame.getContentPane().add(Categoria);
		
		
		
		JButton btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
		btnAtras.setBounds(10, 380, 65, 23);
		frame.getContentPane().add(btnAtras);
		
		ConexionBBDD Prueba = new ConexionBBDD();
		A˝adirProductoProdCateg.setModel(Prueba.A˝adirProd());
		
		JButton btnNewButton = new JButton("A\u00F1adir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(468, 342, 100, 57);
		frame.getContentPane().add(btnNewButton);
		
		JComboBox Mesa = new JComboBox();

		Mesa.setModel(new DefaultComboBoxModel(new String[] {"Mesa 01", "Mesa 02", "Mesa 03", "Mesa 04", "Mesa 05", "Mesa 06", "Mesa 07", "Mesa 08", "Mesa 09", "Mesa 10", "Mesa 11", "Mesa 12", "Mesa 13", "Mesa 14", "Mesa 15"}));
		Mesa.setBounds(468, 173, 100, 20);
		frame.getContentPane().add(Mesa);

		
		textNombreProd.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String nomb_producto = textNombreProd.getText();
				Prueba.BuscarProdRestA˝adProd(nomb_producto);
				A˝adirProductoProdCateg.setModel(Prueba.BuscarProdRestA˝adProd(nomb_producto));

			}
		});
		
		
		JLabel lblNumeroDeMesa = new JLabel("Numero de Mesa");
		lblNumeroDeMesa.setBounds(355, 176, 100, 14);
		frame.getContentPane().add(lblNumeroDeMesa);
		

		
		Categoria.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				String nomb_producto = textNombreProd.getText();
				String nomb_categoria =  (String) Categoria.getSelectedItem();
				if(nomb_producto != "" || nomb_categoria != "") {
					if(nomb_categoria.equals("Todas las Categorias")) {
						A˝adirProductoProdCateg.setModel(Prueba.A˝adirProd());

					}else {
						A˝adirProductoProdCateg.setModel(Prueba.BuscarCateRestA˝adProd(nomb_categoria));
					}
										
			}
				}
		});
		
		JLabel labelCantidad = new JLabel("Cantidad");
		labelCantidad.setBounds(355, 207, 46, 14);
		frame.getContentPane().add(labelCantidad);
		
		JSpinner Cantidad = new JSpinner();
		Cantidad.setBounds(468, 204, 40, 20);
		frame.getContentPane().add(Cantidad);
			
		
		JLabel lblFondo = new JLabel("Fondo");
		lblFondo.setIcon(new ImageIcon("imagenes\\fondo8.jpg"));
		lblFondo.setBounds(-40, 0, 618, 506);
		frame.getContentPane().add(lblFondo);
		
		A˝adirProductoProdCateg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			int row = A˝adirProductoProdCateg.getSelectedRow();
			String nomb_producto=A˝adirProductoProdCateg.getValueAt(row, 0).toString();
			textNombreProd.setText(nomb_producto);
		}
	});
	}
}

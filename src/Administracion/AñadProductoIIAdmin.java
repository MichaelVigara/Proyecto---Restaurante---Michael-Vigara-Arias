package Administracion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import ConexionBBDD.ConexionBBDD;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class AñadProductoIIAdmin {

	public JFrame frame;
	private JTextField textNombre;
	private JTextField textPrecio;
	ConexionBBDD Prueba;
	private JTextField textID;
	private JTable IDNombProdPrecioCateg;
	private JTextField textIDCateg;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AñadProductoIIAdmin window = new AñadProductoIIAdmin();
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
	public AñadProductoIIAdmin() {
		initialize();
		Prueba = new ConexionBBDD();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 595, 469);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		

		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 360, 46, 14);
		frame.getContentPane().add(lblNombre);
		
		textNombre = new JTextField();
		textNombre.setBounds(66, 357, 130, 20);
		frame.getContentPane().add(textNombre);
		textNombre.setColumns(10);
		
		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(218, 329, 46, 14);
		frame.getContentPane().add(lblPrecio);
		
		textPrecio = new JTextField();
		textPrecio.setColumns(10);
		textPrecio.setBounds(288, 326, 46, 20);
		frame.getContentPane().add(textPrecio);
		
		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setBounds(218, 360, 60, 14);
		frame.getContentPane().add(lblCategoria);
		
		JComboBox comboBoxCategoria = new JComboBox();
		comboBoxCategoria.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String nomb_categoria =  (String) comboBoxCategoria.getSelectedItem();
				Prueba.ImprimirIDCateg(nomb_categoria);
				textIDCateg.setText(-);
				
				
			}
		});

		comboBoxCategoria.setModel(new DefaultComboBoxModel(new String[] {"Todas las Categorias", "Refrescos", "Bebidas Alcoholicas", "Casqueria", "Carnes", "Pescados", "Sopas", "Entrantes", "Pizzas", "Ensaladas", "Arroces", "Bocadillos", "Postres", "Menu Infantil", "Hamburguesas", "Pasta", "Vinos"}));
		comboBoxCategoria.setBounds(288, 357, 144, 20);
		frame.getContentPane().add(comboBoxCategoria);
		
		
		
		JButton btnNewButton = new JButton("A\u00F1adir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id_producto = textID.getText();
				String producto = textNombre.getText();
				String precio = textPrecio.getText();
				String id_categoria = textIDCateg.getText();
				Prueba.AñadirProd(id_producto, producto, precio, id_categoria);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setBounds(442, 312, 127, 83);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
		btnAtras.setBounds(10, 406, 70, 23);
		frame.getContentPane().add(btnAtras);
		
		JButton btnListo = new JButton("Listo");
		btnListo.setBounds(499, 406, 70, 23);
		frame.getContentPane().add(btnListo);
		
		textID = new JTextField();
		textID.setBounds(66, 326, 31, 20);
		frame.getContentPane().add(textID);
		textID.setColumns(10);
		
		JLabel lblIdID = new JLabel("ID");
		lblIdID.setBounds(10, 329, 46, 14);
		frame.getContentPane().add(lblIdID);
		
		JLabel label = new JLabel("\u20AC");
		label.setBounds(344, 329, 46, 14);
		frame.getContentPane().add(label);
		
		textIDCateg = new JTextField();
		textIDCateg.setEditable(false);
		textIDCateg.setColumns(10);
		textIDCateg.setBounds(298, 388, 46, 20);
		frame.getContentPane().add(textIDCateg);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 559, 290);
		frame.getContentPane().add(scrollPane);
		
		IDNombProdPrecioCateg = new JTable();
		IDNombProdPrecioCateg.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID Producto", "Nombre Producto", "Precio", "Categoria"
			}
		));
		scrollPane.setViewportView(IDNombProdPrecioCateg);
		
		JLabel lblIdCategoria = new JLabel("ID Categoria");
		lblIdCategoria.setBounds(218, 391, 93, 14);
		frame.getContentPane().add(lblIdCategoria);
		
		JLabel lblFondo = new JLabel("Fondo");
		lblFondo.setIcon(new ImageIcon("imagenes\\fondo3.jpg"));
		lblFondo.setBounds(-500, 0, 1118, 456);
		frame.getContentPane().add(lblFondo);
		

		ConexionBBDD Prueba = new ConexionBBDD();
		IDNombProdPrecioCateg.setModel(Prueba.AñadirProdAdm());
	}
}

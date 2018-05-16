package Administracion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import ConexionBBDD.ConexionBBDD;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class AñadirCategoriaAdminII {

	JFrame frame;
	ConexionBBDD Prueba;
	private JTextField textNombCategoria;
	private JTextField textIDCate;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AñadirCategoriaAdminII window = new AñadirCategoriaAdminII();
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
	public AñadirCategoriaAdminII() {
		initialize();
		Prueba = new ConexionBBDD();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 288, 157);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 11, 46, 14);
		frame.getContentPane().add(lblNombre);
		
		textNombCategoria = new JTextField();
		textNombCategoria.setBounds(10, 36, 124, 20);
		frame.getContentPane().add(textNombCategoria);
		textNombCategoria.setColumns(10);
		
		JButton button = new JButton("A\u00F1adir");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id_categoria = textIDCate.getText();
				String categoria = textNombCategoria.getText();
				Prueba.AñadirCat(id_categoria, categoria);
			}
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 18));
		button.setBounds(163, 11, 98, 68);
		frame.getContentPane().add(button);
		
		JButton btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
		btnAtras.setBounds(7, 90, 67, 23);
		frame.getContentPane().add(btnAtras);
		
		JButton btnListo = new JButton("Listo");
		btnListo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String id_categoria = textIDCate.getText();
				String categoria = textNombCategoria.getText();
				Prueba.ModificarCategoria(id_categoria, categoria);
			}
		});
		btnListo.setBounds(192, 90, 67, 23);
		frame.getContentPane().add(btnListo);
		
		textIDCate = new JTextField();
		textIDCate.setBounds(20, 59, 54, 20);
		frame.getContentPane().add(textIDCate);
		textIDCate.setColumns(10);
		
		JLabel lblFondo = new JLabel("Fondo");
		lblFondo.setIcon(new ImageIcon("C:\\Users\\DAW1\\Documents\\DAW1\\Programacion\\Marte\\Proyecto BBDD - Restaurante\\src\\Inicio\\fondo3.jpg"));
		lblFondo.setBounds(-571, -269, 855, 455);
		frame.getContentPane().add(lblFondo);
	}

}

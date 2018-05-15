package ConexionBBDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

public class ConexionBBDD {

	private String bd;
	private String url= "jdbc:oracle:thin:@localhost:1521:XE";
	private String usr = "SYSTEM";
	private String pwd = "kiko";
	private Connection conexion;
	

	public ConexionBBDD()  {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conexion = DriverManager.getConnection(url, usr, pwd);
			
			if(!conexion.isClosed()) {
				System.out.println("Conexi�n establecida");
			}
			else
				System.out.println("Fallo en Conexi�n");	
			

		}catch (Exception e) {
			System.out.println("ERROR en conexi�n con ORACLE");	
			e.printStackTrace();
		}
		
	}
	
	public DefaultTableModel Productos() {
		String [] columnas={"Nombre del Producto","Precio","Categoria"};
		String [] registro=new String[3];
		DefaultTableModel Productos = new DefaultTableModel(null,columnas);
		String query = "select nomb_producto, precio, nomb_categoria FROM JUPITER2.productos p, JUPITER2.categoria c WHERE p.id_categoria = c.id_categoria";
		 
		try {
			Statement stmt = conexion.createStatement();
			ResultSet rset = stmt.executeQuery(query);
			while(rset.next()) {
				 registro[0]=rset.getString("nomb_producto");
				 registro[1]=rset.getString("precio");
				 registro[1]+=" �";
		         registro[2]=rset.getString("nomb_categoria");
		         Productos.addRow(registro);
			}
			rset.close();
			stmt.close();
			
		}catch (SQLException s){
			s.printStackTrace();
		}
		
		return Productos;
		
	}
		
	public DefaultTableModel Comandas() {
		String [] columnas={"Numero de Mesa","Numero de Comandas", "Productos", "Cantidad"};
		String [] registro=new String[4];
		DefaultTableModel Comanda = new DefaultTableModel(null,columnas);
		String query = "SELECT num_mesa, comanda, nomb_producto, cantidad FROM JUPITER2.mesa m, JUPITER2.pedidos e, JUPITER2.productos p, JUPITER2.categoria c WHERE p.id_producto = e.id_producto and m.id_mesa = e.id_mesa and p.id_categoria = c.id_categoria";
		 
		try {
			Statement stmt = conexion.createStatement();
			ResultSet rset = stmt.executeQuery(query);
			while(rset.next()) {
				 registro[0]=rset.getString("num_mesa");
		         registro[1]=rset.getString("comanda");
		         registro[2]=rset.getString("nomb_producto");
		         registro[3]=rset.getString("cantidad");
		         Comanda.addRow(registro);
			}
			rset.close();
			stmt.close();
			
		}catch (SQLException s){
			s.printStackTrace();
		}
		
		return Comanda;
		
	}
	
	public DefaultTableModel A�adirProd() {
		String [] columnas={"Nombre del Producto", "Categoria"};
		String [] registro=new String[2];
		DefaultTableModel Comanda = new DefaultTableModel(null,columnas);
		String query = "select nomb_producto, nomb_categoria FROM Jupiter2.productos p, JUPITER2.categoria c WHERE p.id_categoria = c.id_categoria order by p.id_categoria";
		try {
			Statement stmt = conexion.createStatement();
			ResultSet rset = stmt.executeQuery(query);
			while(rset.next()) {
				 registro[0]=rset.getString("nomb_producto");
		         registro[1]=rset.getString("nomb_categoria");
		         Comanda.addRow(registro);
			}
			rset.close();
			stmt.close();
			
		}catch (SQLException s){
			s.printStackTrace();
		}
		
		return Comanda;
		
	}
	
	public DefaultTableModel Ticket() {
		String [] columnas={"Mesa", "Tipo de Pago", "Importe Total"};
		String [] registro=new String[3];
		DefaultTableModel Ticket = new DefaultTableModel(null,columnas);
		String query = "SELECT num_mesa, TIPO_PAGO,TOTAL_PAGO FROM Jupiter2.mesa m, Jupiter2.TICKET t WHERE m.id_mesa = t.id_mesa";
		try {
			Statement stmt = conexion.createStatement();
			ResultSet rset = stmt.executeQuery(query);
			while(rset.next()) {
				 registro[0]=rset.getString("num_mesa");
		         registro[1]=rset.getString("TIPO_PAGO");
		         registro[2]=rset.getString("TOTAL_PAGO");
				 registro[2]+=" �";
		         Ticket.addRow(registro);
			}
			rset.close();
			stmt.close();
			
		}catch (SQLException s){
			s.printStackTrace();
		}
		
		return Ticket;					
	}
	
	public DefaultTableModel HistorialPedidos() {
		String [] columnas={"Fecha de la Comanda", "Numero de Comanda", "Numero de Mesa", "Productos", "Cantidad"};
		String [] registro=new String[5];
		DefaultTableModel HistorialPedidos = new DefaultTableModel(null,columnas);
		String query = "SELECT fecha_pedido, comanda, num_mesa, nomb_producto, cantidad FROM JUPITER2.mesa m, JUPITER2.pedidos e, JUPITER2.productos p, JUPITER2.categoria c WHERE p.id_producto = e.id_producto and m.id_mesa = e.id_mesa and p.id_categoria = c.id_categoria";
		try {
			Statement stmt = conexion.createStatement();
			ResultSet rset = stmt.executeQuery(query);
			while(rset.next()) {
	
				 registro[0]=rset.getString("fecha_pedido");
		         registro[1]=rset.getString("comanda");
		         registro[2]=rset.getString("num_mesa");
		         registro[3]=rset.getString("nomb_producto");
		         registro[4]=rset.getString("cantidad");
		         HistorialPedidos.addRow(registro);
			}
			rset.close();
			stmt.close();
			
		}catch (SQLException s){
			s.printStackTrace();
		}
		
		return HistorialPedidos;					
	}
	
	public DefaultTableModel HistorialTickets() {
		String [] columnas={"Fecha", "Mesa", "Tipo de Pago", "Importe Total"};
		String [] registro=new String[5];
		DefaultTableModel HistorialTickets = new DefaultTableModel(null,columnas);
		String query = "SELECT FECHA_TICKET, num_mesa, TIPO_PAGO,TOTAL_PAGO FROM Jupiter2.mesa m, Jupiter2.TICKET t WHERE m.id_mesa = t.id_mesa";
		try {
			Statement stmt = conexion.createStatement();
			ResultSet rset = stmt.executeQuery(query);
			while(rset.next()) {
	
				 registro[0]=rset.getString("FECHA_TICKET");
				 registro[1]=rset.getString("num_mesa");
		         registro[2]=rset.getString("TIPO_PAGO");
		         registro[3]=rset.getString("TOTAL_PAGO");
				 registro[3]+=" �";
		         HistorialTickets.addRow(registro);
			}
			rset.close();
			stmt.close();
			
		}catch (SQLException s){
			s.printStackTrace();
		}
		
		return HistorialTickets;					
	}
}

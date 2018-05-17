package ConexionBBDD;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ConexionBBDD {

	private String bd;
	private String url;
	private String usr;
	private String pwd;
	private Connection conexion;
	private String esquema;
	

	public ConexionBBDD()  {
		
		FicheroINI();
		
		
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
		String query = "select nomb_producto, precio, nomb_categoria FROM JUPITER2.productos p, JUPITER2.categoria c WHERE p.id_categoria = c.id_categoria order by nomb_categoria";
		 
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
	
	public void A�adirProd(String id_producto, String nomb_producto, String precio, String id_categoria) {
		if(id_producto != "" && nomb_producto != "" && precio != "" && id_categoria != "") {
			String query = "INSERT INTO JUPITER2.productos VALUES ("+ id_producto + ", '" + nomb_producto + "', " + precio + ", " + id_categoria + ")";
			System.out.println(query);
			try {
				System.out.println(query);
				Statement stmt = conexion.createStatement();
				stmt.executeUpdate(query);
				stmt.close();
				
			}catch (SQLException e) {
				e.printStackTrace();
			}
			
		} else {
			JOptionPane.showMessageDialog(null, "Error, necesita rellenar todos los campos");
		}
	}
	
	public void A�adirCat(String id_categoria, String nomb_categoria) {
		if(id_categoria != "" && nomb_categoria != "") {
			String query = "INSERT INTO JUPITER2.categoria VALUES ("+ id_categoria + ", '" + nomb_categoria + "')";
			try {
				System.out.println(query);
				Statement stmt = conexion.createStatement();
				stmt.executeUpdate(query);
				stmt.close();
				
			}catch (SQLException e) {
				e.printStackTrace();
			}
			
		} else {
			JOptionPane.showMessageDialog(null, "Error, necesita rellenar todos los campos");
		}
	}
	
	public void A�adirProdCom(String num_mesa, String comanda, String nomb_producto, String cantidad) {
		if(num_mesa != "" && comanda != "" && nomb_producto != "" && cantidad != "") {
			String query = "INSERT INTO JUPITER2.pedidos  VALUES (" + num_mesa + ", " + comanda + ", " + nomb_producto + ", " + comanda + ")";
			try {
				System.out.println(query);
				Statement stmt = conexion.createStatement();
				stmt.executeUpdate(query);
				stmt.close();
				
			}catch (SQLException e) {
				e.printStackTrace();
			}
			
		} else {
			JOptionPane.showMessageDialog(null, "Error, necesita rellenar todos los campos");
		}
	}
	
	public void EliminarProd(String nomb_producto) {
		String query = "DELETE FROM JUPITER2.productos WHERE nomb_producto = '" + nomb_producto + "'";
		System.out.println(query);
		try {
			Statement stmt = conexion.createStatement();
			stmt.executeUpdate(query);
			stmt.close();
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public DefaultTableModel BuscarProdAdmin(String nomb_producto) {
		String [] columnas={"Nombre del Producto", "Precio", "Categoria"};
		String [] registro=new String[3];
		DefaultTableModel BuscarProdAdmin = new DefaultTableModel(null,columnas);
		if(nomb_producto != "") {
		String query = "Select nomb_producto, precio, nomb_categoria FROM Jupiter2.productos p, JUPITER2.categoria c WHERE p.id_categoria = c.id_categoria and UPPER(nomb_producto) like UPPER('" + nomb_producto + "%')";
		try {
			Statement stmt = conexion.createStatement();
			ResultSet rset = stmt.executeQuery(query);
			while(rset.next()) {
				 registro[0]=rset.getString("nomb_producto");
				 registro[1]=rset.getString("precio");
		         registro[2]=rset.getString("nomb_categoria");
		         BuscarProdAdmin.addRow(registro);
			}
			stmt.execute(query);
			stmt.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
			}
		return BuscarProdAdmin;
	}
	
	public DefaultTableModel BuscarCatAdmin(String nomb_categoria) {
		String [] columnas={"Nombre del Producto", "Precio", "Categoria"};
		String [] registro=new String[3];
		DefaultTableModel BuscarCatAdmin = new DefaultTableModel(null,columnas);
		if(nomb_categoria != "") {
		String query = "Select nomb_producto, precio, nomb_categoria FROM Jupiter2.productos p, JUPITER2.categoria c WHERE p.id_categoria = c.id_categoria and nomb_categoria = '" + nomb_categoria + "'";
		try {
			Statement stmt = conexion.createStatement();
			ResultSet rset = stmt.executeQuery(query);
			while(rset.next()) {
				 registro[0]=rset.getString("nomb_producto");
				 registro[1]=rset.getString("precio");
		         registro[2]=rset.getString("nomb_categoria");
		         BuscarCatAdmin.addRow(registro);
			}
			stmt.execute(query);
			stmt.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
			}
		
			
		return BuscarCatAdmin;
	}
	
	
	
	public void ModificarCategoria(String id_categoria, String nomb_categoria) {
		if(id_categoria != "") {
			if(nomb_categoria != "") {
				String query =  "UPDATE JUPITER2.categoria SET nomb_categoria = '" + nomb_categoria + "' WHERE ID_categoria = " + id_categoria;
				try {
					Statement stmt = conexion.createStatement();
					stmt.executeUpdate(query);
					stmt.close();
				}catch (SQLException e) {
					e.printStackTrace();
				}
			}
			}

		}
	
	public DefaultTableModel ModificarProd() {
		String [] columnas={"ID Producto", "Nombre del Producto", "Precio", "Categoria"};
		String [] registro=new String[4];
		DefaultTableModel ModificarProd = new DefaultTableModel(null,columnas);
		String query = "select id_producto, nomb_producto, precio, nomb_categoria FROM JUPITER2.productos p, JUPITER2.categoria c WHERE p.id_categoria = c.id_categoria order by id_producto";
		 
		try {
			Statement stmt = conexion.createStatement();
			ResultSet rset = stmt.executeQuery(query);
			while(rset.next()) {
				 registro[0]=rset.getString("id_producto");
				 registro[1]=rset.getString("nomb_producto");
				 registro[2]=rset.getString("precio");
		         registro[3]=rset.getString("nomb_categoria");
		         ModificarProd.addRow(registro);
			}
			rset.close();
			stmt.close();
			
		}catch (SQLException s){
			s.printStackTrace();
		}
		
		return ModificarProd;
	}
	
	public DefaultTableModel VerProd() {
		String [] columnas={"ID Producto", "Nombre del Producto"};
		String [] registro=new String[2];
		DefaultTableModel VerProd = new DefaultTableModel(null,columnas);
		String query = "select id_producto, nomb_producto FROM JUPITER2.productos";
		 
		try {
			Statement stmt = conexion.createStatement();
			ResultSet rset = stmt.executeQuery(query);
			while(rset.next()) {
				 registro[0]=rset.getString("id_producto");
				 registro[1]=rset.getString("nomb_producto");
				 VerProd.addRow(registro);
			}
			rset.close();
			stmt.close();
			
		}catch (SQLException s){
			s.printStackTrace();
		}
		
		return VerProd;
	}
	
	public DefaultTableModel VerProdPrec() {
		String [] columnas={"ID Producto", "Nombre del Producto", "Precio"};
		String [] registro=new String[3];
		DefaultTableModel VerProdPrec = new DefaultTableModel(null,columnas);
		String query = "select id_producto, nomb_producto, precio FROM JUPITER2.productos";
		 
		try {
			Statement stmt = conexion.createStatement();
			ResultSet rset = stmt.executeQuery(query);
			while(rset.next()) {
				 registro[0]=rset.getString("id_producto");
				 registro[1]=rset.getString("nomb_producto");
				 registro[2]=rset.getString("precio");
				 VerProdPrec.addRow(registro);
			}
			rset.close();
			stmt.close();
			
		}catch (SQLException s){
			s.printStackTrace();
		}
		
		return VerProdPrec;
	}
	
	public DefaultTableModel VerProdCateg() {
		String [] columnas={"ID Producto", "Nombre del Producto", "Categoria"};
		String [] registro=new String[3];
		DefaultTableModel VerProdCateg = new DefaultTableModel(null,columnas);
		String query = "select id_producto, nomb_producto, nomb_categoria FROM JUPITER2.productos p, JUPITER2.categoria c WHERE p.id_categoria = c.id_categoria";
		 
		try {
			Statement stmt = conexion.createStatement();
			ResultSet rset = stmt.executeQuery(query);
			while(rset.next()) {
				 registro[0]=rset.getString("id_producto");
				 registro[1]=rset.getString("nomb_producto");
				 registro[2]=rset.getString("nomb_categoria");
				 VerProdCateg.addRow(registro);
			}
			rset.close();
			stmt.close();
			
		}catch (SQLException s){
			s.printStackTrace();
		}
		
		return VerProdCateg;
	}
	
	public DefaultTableModel VerCateg() {
		String [] columnas={"ID Categoria", "Nombre de la Categoria"};
		String [] registro=new String[2];
		DefaultTableModel VerCateg = new DefaultTableModel(null,columnas);
		String query = "select id_categoria, nomb_categoria FROM JUPITER2.categoria";
		 
		try {
			Statement stmt = conexion.createStatement();
			ResultSet rset = stmt.executeQuery(query);
			while(rset.next()) {
				 registro[0]=rset.getString("id_categoria");
				 registro[1]=rset.getString("nomb_categoria");
				 VerCateg.addRow(registro);
			}
			rset.close();
			stmt.close();
			
		}catch (SQLException s){
			s.printStackTrace();
		}
		
		return VerCateg;
	}
	
	public DefaultTableModel VerTotal() {
		String [] columnas={"ID Producto", "Producto", "Precio", "Categoria"};
		String [] registro=new String[5];
		DefaultTableModel VerTotal = new DefaultTableModel(null,columnas);
		String query = "select id_producto, nomb_producto, precio, nomb_categoria FROM JUPITER2.productos p, JUPITER2.categoria c WHERE p.id_categoria = c.id_categoria";
		 
		try {
			Statement stmt = conexion.createStatement();
			ResultSet rset = stmt.executeQuery(query);
			while(rset.next()) {
				 registro[0]=rset.getString("id_producto");
				 registro[1]=rset.getString("nomb_producto");
				 registro[1]=rset.getString("precio");
				 registro[3]=rset.getString("id_categoria");
				 registro[4]=rset.getString("nomb_categoria");
				 VerTotal.addRow(registro);
			}
			rset.close();
			stmt.close();
			
		}catch (SQLException s){
			s.printStackTrace();
		}
		
		return VerTotal;
	}
	
	public DefaultTableModel BuscarMesa(String nomb_mesa) {
		String [] columnas={"Numero de Mesa","Numero de Comandas", "Productos", "Cantidad"};
		String [] registro=new String[4];
		DefaultTableModel BuscarMesa = new DefaultTableModel(null,columnas);
		if(nomb_mesa != "") {
		String query = "SELECT num_mesa, comanda, nomb_producto, cantidad FROM JUPITER2.mesa m, JUPITER2.pedidos e, JUPITER2.productos p, JUPITER2.categoria c WHERE p.id_producto = e.id_producto and m.id_mesa = e.id_mesa and p.id_categoria = c.id_categoria  and UPPER(nomb_mesa) like UPPER('" + nomb_mesa + "%')";
		try {
			Statement stmt = conexion.createStatement();
			ResultSet rset = stmt.executeQuery(query);
			while(rset.next()) {
				 registro[0]=rset.getString("num_mesa");
		         registro[1]=rset.getString("comanda");
		         registro[2]=rset.getString("nomb_producto");
		         registro[3]=rset.getString("cantidad");
		         BuscarMesa.addRow(registro);
			}
			stmt.execute(query);
			stmt.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
			}
		
			
		return BuscarMesa;
	}
	
	public DefaultTableModel BuscarProdRestA�adProd(String nomb_producto) {
		String [] columnas={"Nombre del Producto", "Categoria"};
		String [] registro=new String[2];
		DefaultTableModel BuscarProdRestA�adProd = new DefaultTableModel(null,columnas);
		if(nomb_producto != "") {
		String query = "Select nomb_producto, nomb_categoria FROM Jupiter2.productos p, JUPITER2.categoria c WHERE p.id_categoria = c.id_categoria and UPPER(nomb_producto) like UPPER('" + nomb_producto + "%')";
		try {
			Statement stmt = conexion.createStatement();
			ResultSet rset = stmt.executeQuery(query);
			while(rset.next()) {
				 registro[0]=rset.getString("nomb_producto");
		         registro[1]=rset.getString("nomb_categoria");
		         BuscarProdRestA�adProd.addRow(registro);
			}
			stmt.execute(query);
			stmt.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
			}
		return BuscarProdRestA�adProd;
	}
	
	public DefaultTableModel BuscarCateRestA�adProd(String nomb_categoria) {
		String [] columnas={"Nombre del Producto", "Categoria"};
		String [] registro=new String[2];
		DefaultTableModel BuscarCateRestA�adProd = new DefaultTableModel(null,columnas);
		if(nomb_categoria != "") {
		String query = "Select nomb_producto, nomb_categoria FROM Jupiter2.productos p, JUPITER2.categoria c WHERE p.id_categoria = c.id_categoria and nomb_categoria = '" + nomb_categoria + "'";
		try {
			Statement stmt = conexion.createStatement();
			ResultSet rset = stmt.executeQuery(query);
			while(rset.next()) {
				 registro[0]=rset.getString("nomb_producto");
		         registro[1]=rset.getString("nomb_categoria");
		         BuscarCateRestA�adProd.addRow(registro);
			}
			stmt.execute(query);
			stmt.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
			}
		return BuscarCateRestA�adProd;
	}
	
	public DefaultTableModel BuscarProdCateA�adProdSlect(String nomb_categoria) {
		String [] columnas={"Nombre del Producto", "Categoria"};
		String [] registro=new String[2];
		DefaultTableModel BuscarProdCateA�adProdSlect = new DefaultTableModel(null,columnas);
		if(nomb_categoria != "") {
		String query = "Select nomb_producto, nomb_categoria FROM Jupiter2.productos p, JUPITER2.categoria c WHERE p.id_categoria = c.id_categoria and nomb_categoria = '" + nomb_categoria + "'";
		try {
			Statement stmt = conexion.createStatement();
			ResultSet rset = stmt.executeQuery(query);
			while(rset.next()) {
				 registro[0]=rset.getString("nomb_producto");
		         registro[1]=rset.getString("nomb_categoria");
		         BuscarProdCateA�adProdSlect.addRow(registro);
			}
			stmt.execute(query);
			stmt.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
			}
		return BuscarProdCateA�adProdSlect;
	}
	
	public DefaultTableModel IDCategoria() {
		String [] columnas={"ID", "Categoria"};
		String [] registro=new String[2];
		DefaultTableModel IDCategoria = new DefaultTableModel(null,columnas);
		String query = "select id_categoria, nomb_categoria FROM JUPITER2.categoria";
		try {
			Statement stmt = conexion.createStatement();
			ResultSet rset = stmt.executeQuery(query);
			while(rset.next()) {
				 registro[0]=rset.getString("id_categoria");
				 registro[1]=rset.getString("nomb_categoria");
				 IDCategoria.addRow(registro);
			}
			rset.close();
			stmt.close();
			
		}catch (SQLException s){
			s.printStackTrace();
		}
		
		return IDCategoria;
	}
	
	public DefaultTableModel BuscarTicketMNesa(String nomb_mesa) {
		String [] columnas={"Mesa", "Tipo de Pago", "Importe Total"};
		String [] registro=new String[3];
		DefaultTableModel BuscarTicketMNesa = new DefaultTableModel(null,columnas);
		if(nomb_mesa != "") {
		String query = "SELECT num_mesa, TIPO_PAGO,TOTAL_PAGO FROM Jupiter2.mesa m, Jupiter2.TICKET t WHERE m.id_mesa = t.id_mesa and UPPER(nomb_mesa) like UPPER('" + nomb_mesa + "%')";
		try {
			Statement stmt = conexion.createStatement();
			ResultSet rset = stmt.executeQuery(query);
			while(rset.next()) {
				 registro[0]=rset.getString("num_mesa");
		         registro[1]=rset.getString("tipo_pago");
		         registro[2]=rset.getString("total_pago");

		         BuscarTicketMNesa.addRow(registro);
			}
			stmt.execute(query);
			stmt.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
			}
		return BuscarTicketMNesa;
	}
	
	public DefaultTableModel A�adirProdAdm() {
		String [] columnas={"ID Producto", "Nombre del Producto", "Precio", "Categoria"};
		String [] registro=new String[4];
		DefaultTableModel A�adirProdAdm = new DefaultTableModel(null,columnas);
		String query = "select id_producto, nomb_producto, precio, nomb_categoria FROM JUPITER2.productos p, JUPITER2.categoria c WHERE p.id_categoria = c.id_categoria order by id_producto";
		 
		try {
			Statement stmt = conexion.createStatement();
			ResultSet rset = stmt.executeQuery(query);
			while(rset.next()) {
				 registro[0]=rset.getString("id_producto");
				 registro[1]=rset.getString("nomb_producto");
				 registro[2]=rset.getString("precio");
		         registro[3]=rset.getString("nomb_categoria");
		         A�adirProdAdm.addRow(registro);
			}
			rset.close();
			stmt.close();
			
		}catch (SQLException s){
			s.printStackTrace();
		}
		
		return A�adirProdAdm;
	}
	
	public void ModificarProd(String id_producto, String nomb_producto, String precio) {
		if(id_producto != "" && nomb_producto != "" && precio != "") {
			if(id_producto != "") {
				String query =  "UPDATE JUPITER2.productos SET nomb_producto = '" + nomb_producto + "', precio = " + precio + "WHERE id_producto = " + id_producto;
				try {
					Statement stmt = conexion.createStatement();
					stmt.executeUpdate(query);
					stmt.close();
				}catch (SQLException e) {
					e.printStackTrace();
				}
			}
			}

		}

	public void FicheroINI() {
		Properties propiedades = new Properties();
		InputStream entrada = null;
		try {
			File miFichero = new File("src/archivo.ini");
			if(miFichero.exists()) {
				entrada = new FileInputStream(miFichero);
				propiedades.load(entrada);
				url=propiedades.getProperty("basedatos");
				usr=propiedades.getProperty("usuario");
				pwd=propiedades.getProperty("clave");
				bd=propiedades.getProperty("esquema");
			}
			else {
				System.err.println("fichero no encontrado");					
			}
			
			
		}catch(IOException ex) {
			ex.printStackTrace();
		}finally {
			if(entrada != null) {
				try {
					entrada.close();
					
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		
	}
	
	public DefaultTableModel BuscarModProdCat(String nomb_categoria) {
		String [] columnas={"ID Producto", "Nombre del Producto", "Precio", "Categoria"};
		String [] registro=new String[4];
		DefaultTableModel BuscarModProdCat = new DefaultTableModel(null,columnas);
		if(nomb_categoria != "") {
		String query = "select id_producto, nomb_producto, precio, nomb_categoria FROM JUPITER2.productos p, JUPITER2.categoria c WHERE p.id_categoria = c.id_categoria and nomb_categoria = '" + nomb_categoria + "'" +" order by id_producto";
		try {
			Statement stmt = conexion.createStatement();
			ResultSet rset = stmt.executeQuery(query);
			while(rset.next()) {
				 registro[0]=rset.getString("id_producto");
				 registro[1]=rset.getString("nomb_producto");
				 registro[2]=rset.getString("precio");
		         registro[3]=rset.getString("nomb_categoria");
		         BuscarModProdCat.addRow(registro);
			}
			stmt.execute(query);
			stmt.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
			}
		return BuscarModProdCat;
	}
	
	public void EliminarPago(String num_mesa, String tipo_pago, String importe_total) {
		String query = "DELETE FROM JUPITER2.mesa WHERE num_mesa = " + num_mesa + ", tipo_pago = '" + tipo_pago + "', importe_total = " + importe_total;
		System.out.println(query);
		try {
			Statement stmt = conexion.createStatement();
			stmt.executeUpdate(query);
			stmt.close();
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void EliminarCate(String id_categoria, String nomb_categoria) {
		String query = "DELETE * FROM JUPITER2.categoria WHERE id_categoria = " + id_categoria + ", nomb_categoria = '" + nomb_categoria + "'";
		System.out.println(query);
		try {
			Statement stmt = conexion.createStatement();
			stmt.executeUpdate(query);
			stmt.close();
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void ImprimirIDCateg(String nomb_categoria) {
		if(nomb_categoria != "") {
		String query = "select id_categoria, nomb_categoria FROM JUPITER2.categoria WHERE nomb_categoria = '" + nomb_categoria +"'";
		try {
			Statement stmt = conexion.createStatement();
			
			stmt.executeUpdate(query);
			stmt.close();
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	}	
}

package ups.edu.ec.controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ups.edu.ec.modelo.Usuario;

public class DataBase {
	private static DataBase singletonDB;
	private static Connection connection = null;

	public DataBase(){ 
	      try{
	         connection = DriverManager.getConnection( "jdbc:sqlite:data.sqlite" );
	         if ( connection != null ){
	            System.out.println("Conexión exitosa!");
	            
	         }
	      }
	      catch ( Exception ex ) {
//	         System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
	         System.out.println("hubo un error");
	      }
	}
	
	public String crearDB() {
		 Statement sentencia;
		try {
			sentencia = connection.createStatement();
			String sql="CREATE TABLE "+"usuario"+" "+
	                 "(ID INT PRIMARY KEY NOT NULL,"+
	                 " name TEXT NOT NULL,"+
	                 " user TEXT NOT NULL,"+
	                 " password TEXT NOT NULL)";
	         sentencia.execute(sql);
	        return "Se creo correctamente la DB";
		} catch (SQLException e) {
			
//			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			return "La DB ya existe";
		}
		
         
		
	}
	
	public String insert (Usuario usuario) {
		String sql = "INSERT INTO usuario(id,name,user,password) VALUES(?,?,?)";
		
		 PreparedStatement sentencia;
			try {
				sentencia = connection.prepareStatement(sql);
				
				sentencia.setString(1, usuario.getName());
				sentencia.setString(2, usuario.getUser());
				sentencia.setString(3, usuario.getPassword());
				
				sentencia.executeUpdate();
				return"Se creo el usuario con exito";
				
			}catch (Exception e) {
//				System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				
				return "Hubo un error al crear el usuario";
			}
		
		
	}
	 public List<Usuario> getUsers()
		        throws SQLException
		    {
		        String query = "select * from usuario";
		        PreparedStatement ps
		            = connection.prepareStatement(query);
		        ResultSet rs = ps.executeQuery();
		        List<Usuario> ls = new ArrayList();
		  
		        while (rs.next()) {
		            Usuario emp = new Usuario();
		            
		            emp.setName(rs.getString("name"));
		            emp.setUser(rs.getString("user"));
		            emp.setPassword(rs.getString("password"));
		            
		            ls.add(emp);
		        }
		        return ls;
		    }
	
	
	
	public String update(Usuario usuario) {
		String sql = "UPDATE usuario SET name = ? , "
                + "user = ?, "
                + "password = ? "
                + "WHERE name = ?";
		
		PreparedStatement sentencia;
		try {
			sentencia = connection.prepareStatement(sql);
			sentencia.setString(1, usuario.getName());
			sentencia.setString(2, usuario.getUser());
			sentencia.setString(3, usuario.getPassword());
			sentencia.setString(4, usuario.getName());
			
			sentencia.executeUpdate();
			return "Se actualizo correctamente";
			
		}catch (Exception e) {
//			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			
			return "Error al actualizar ";
		}
		
		
	
		
	}
	
	
	
    private static synchronized void createConnection(){
        if(singletonDB ==null){
        	singletonDB = new DataBase();
        }
    }
    
	 public static DataBase getConnection(){
		  
		 if (singletonDB == null){
		      createConnection();
		 }else {
			 System.out.println("La base de datos ya esta instanciada");
		 }
		  
		 return singletonDB;
		    } // Fin getConnection
}

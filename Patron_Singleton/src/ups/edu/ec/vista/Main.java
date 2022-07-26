package ups.edu.ec.vista;
import java.sql.*;
import java.util.List;
import java.util.Scanner;

import ups.edu.ec.controlador.DataBase;
import ups.edu.ec.modelo.Usuario;
public class Main {
	
	public static void main(String[] args) {
		int bandera=1;
		DataBase db1 = null;
		Scanner entradaEscaner = new Scanner (System.in);
		String name = null,us,pass=null;
		int id=0;
		Usuario user=null;
		while(bandera!=0) {
		System.out.println("**************Registro de Usuarios********************");
		System.out.println("1. Crear instancia a la DB");
		System.out.println("2. Crear DB");
//		System.out.println("3. Crear usuario");
		System.out.println("3. Actualizar Usuario");
		System.out.println("4. Listar Usuario");
		int input= entradaEscaner.nextInt(); //Invocamos un método sobre un objeto Scanner
		
		
		switch (input) {
		case 1:
			try {
				
				db1=DataBase.getConnection();
				List<Usuario> lt=db1.getUsers();
				System.out.println(lt);
				break;
				
				
			}catch (Exception e) {
				break;
			}
			
		case 2:
			try {
				
				 System.out.println(db1.crearDB());
				 break;
			}catch (Exception e) {
				// TODO: handle exception
				 break;
			}
//		case 3:
////			
////			System.out.println("Ingrese Nombre");
////			 name=entradaEscaner.nextLine();
////			System.out.println("Ingrese Usuario");
////			 us=entradaEscaner.nextLine();
////			System.out.println("Ingrese Contrasenia");
////			 pass=entradaEscaner.nextLine();
////			 user = new Usuario(name,us,pass);
////			System.out.println(db1.insert(user));
////			break;
//			break;
			
		case 3:
			System.out.println("Ingrese Nombre");
		 name=entradaEscaner.next();
			System.out.println("Ingrese Usuario");
			 us=entradaEscaner.next();
			System.out.println("Ingrese Contrasenia");
			 pass=entradaEscaner.next();
			 user = new Usuario(name,us,pass);
			 System.out.println(user);
			System.out.println(db1.update(user));
			break;
			
		case 4:
			try {
				List<Usuario> lt=db1.getUsers();
				System.out.println(lt);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + input);
		}
			
		}
		
		
		
	}
	
}

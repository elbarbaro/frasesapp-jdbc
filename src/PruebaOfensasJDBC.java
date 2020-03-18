
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.generationc20.ofensasapp.model.Frase;
import com.generationc20.ofensasapp.model.Persona;
import java.util.Scanner;

public class PruebaOfensasJDBC {

	public static final String JDBC_URL_FORMAT = "jdbc:%s://%s:%s/%s";          
  
	public static void main(String[] arsg) {
		
		Scanner lector = new Scanner(System.in);
		
		String dbms = "mysql";
		String host = "localhost";
		String port = "3306";
		String databaseName = "ofensas";
		
		String url = String.format(JDBC_URL_FORMAT, dbms, host, port, databaseName);
		
		System.out.println(url);
		
		String username = "root";
		String password = "barbarillo";
		
		Connection puenteConnection = null;
		
		Statement chalanSQL = null;
		
		ResultSet cajaResultados = null;
		
		try {
			
			// Registrar Driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// Creando el puente a través del DriverManager quien entiende el Drive de MySQL
			puenteConnection = DriverManager.getConnection(url, username, password);
			
			//Frase frase = obtenerFrase(lector);
			//insertarFrase(puenteConnection, frase);
			
			//Persona persona = obtenerPersona(lector);
			//insertarPersona(puenteConnection, persona);
			
			chalanSQL = puenteConnection.createStatement();
			
			// Realizar una operacion/accion en el servidor de bases de datos
			// guardamos esa caja que trae el chalan para procesar  lo que tiene dentro (tabla)
			cajaResultados = chalanSQL.executeQuery("select * from persona");
			
			
			while(cajaResultados.next()) {
				
				// Para sacar los valores necesitamos:
				// 1. Nombre del campo o indice (numero empezando de 1)
				// 2. Tipo de dato del campo
				
				System.out.println("Id: " + cajaResultados.getInt("id"));
				System.out.println("IdPais: " + cajaResultados.getInt("idPais"));
				System.out.println("Nombre: " + cajaResultados.getString("nombre"));
				System.out.println("Edad: " + cajaResultados.getInt("edad"));
				System.out.println("Estado: " + cajaResultados.getString("estado"));
				System.out.println("Fecha: " + cajaResultados.getDate("fecha"));
				
				System.out.println();
			}
			
			//operacionActualizar(puenteConnection, lector);
			operacionConsultarPersona(puenteConnection, lector);
			
			cajaResultados.close();
			chalanSQL.close();
			puenteConnection.close();
			lector.close();
			
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	private static void insertarFrase(Connection conn, Frase frase) throws SQLException {
		
		Statement chalanSQL = conn.createStatement();
		
		String queryInsert = "insert into frase(contenido, significado, ofensividad, contexto) "
				+ "values('" + frase.getContenido() + "', "
						+ "'"  + frase.getSignificado() + "', "
						+ "" + frase.getOfensividad() + ", "
						+ "'" + frase.getContexto() + "')" ;
		
		
		System.out.println(queryInsert);
		
		
		int result = chalanSQL.executeUpdate(queryInsert);
		if(result > 0) {
			System.out.println("Se insertaron los datos");
		} else {
			System.out.println("No se insertaron los datos");
		}
		
		chalanSQL.close();
	}
	
	private static void insertarPersona(Connection conn, Persona persona) throws SQLException {
		
		Statement chalanSQL = conn.createStatement();
		String queryInsert = "insert into persona(idPais, nombre, edad, estado) "
							+ "values(" + persona.getIdPais() + ", "
									+ "'" + persona.getNombre() + "', "
									+ "" + persona.getEdad() + ", "
									+ "'" + persona.getEstado() + "')";
		
		System.out.println(queryInsert);
		
		int result = chalanSQL.executeUpdate(queryInsert);
		if(result > 0) {
			System.out.println("Se insertaron los datos");
		} else {
			System.out.println("No se insertaron los datos");
		}
		
		chalanSQL.close();
	}
	
	
	private static void actualizarFrase(Connection conn, int id, Frase frase) throws SQLException {
	
		String queryPlantillaUpdate = "update frase "
				                    + "set contenido = ?, significado = ?, ofensividad = ?, contexto = ? "
								    + "where id = ?";
		
		// Decirle al chalan lo que necesita
		PreparedStatement chalanChidoSQL = conn.prepareStatement(queryPlantillaUpdate);
		chalanChidoSQL.setString(1, frase.getContenido());
		chalanChidoSQL.setString(2, frase.getSignificado());
		chalanChidoSQL.setInt(3, frase.getOfensividad());
		chalanChidoSQL.setString(4, frase.getContexto());
		chalanChidoSQL.setInt(5, id);
		
		int result = chalanChidoSQL.executeUpdate();
		if(result > 0) {
			System.out.println("Se actualizaron los datos");
		} else {
			System.out.println("No se actualizaron los datos");
		}
		
		chalanChidoSQL.close();
	}
	
	private static Frase obtenerFrase(Scanner lector) {

		// Molde de datos
		Frase frase = null;
		
		// Variables para datos de entrada
		String contenido = null;
		String significado = null;
		int ofensividad;
		String contexto = null;
		
		System.out.println("Ingrese datos para la frase");
		System.out.println("Ingrese contenido: ");
		contenido = lector.nextLine();
		System.out.println("Ingrese significado: ");
		significado = lector.nextLine();
		System.out.println("Ingrese nivel ofensividad (1- 5):");
		ofensividad = lector.nextInt();
		lector.nextLine();
		System.out.println("Ingrese el contexto: ");
		contexto = lector.nextLine();
		
		// Crear el molde
		frase = new Frase();
		
		// Moldear datos
		frase.setContenido(contenido);
		frase.setSignificado(significado);
		frase.setOfensividad(ofensividad);
		frase.setContexto(contexto);
		
		return frase;
	}
	
	public static Persona obtenerPersona(Scanner lector) {
		
		Persona persona = null;
		
		int idPais;
		String nombre = null;
		int edad = 0;
		String estado = null;
		
		System.out.println("Ingresa datos persona");
		System.out.println("Ingresa el idPais: ");
		idPais = lector.nextInt();
		lector.nextLine();
		System.out.println("Ingresa el nombre: ");
		nombre = lector.nextLine();
		System.out.println("Ingresa la edad: ");
		edad = lector.nextInt();
		lector.nextLine();
		System.out.println("Ingresa el estado: ");
		estado = lector.nextLine();
		
		persona = new Persona(idPais, nombre, edad, estado);
		
		return persona;
		
	}
	
	private static void operacionActualizar(Connection conn, Scanner lector) throws SQLException {
		
		int id = 0;
		Frase frase = null;
		
		System.out.println("Actualizar una frase");
		System.out.println("Ingresa el id: ");
		id = lector.nextInt();
		lector.nextLine();
		
		frase = obtenerFrase(lector);
		
		actualizarFrase(conn, id, frase);
	}
	
	private static Persona consultarPersonaPorId(Connection conn, int id) throws SQLException {
		
		String queryObtenerUno = "select * from persona where id = ?";
		PreparedStatement chalanChidoriSQL = null;
		ResultSet cajaResultado = null;
		
		// Molde de datos
		Persona persona = null;

		chalanChidoriSQL = conn.prepareStatement(queryObtenerUno);
		chalanChidoriSQL.setInt(1, id);
		
		// Guardar caja que trae el chalan
		cajaResultado = chalanChidoriSQL.executeQuery();
		
		if(cajaResultado.first()) {
			// Crear el molde de datos
			persona = new Persona();
			
			// Moldear datos
			persona.setId(cajaResultado.getInt("id"));
			persona.setIdPais(cajaResultado.getInt("idPais"));
			persona.setNombre(cajaResultado.getString("nombre"));
			persona.setEdad(cajaResultado.getInt("edad"));
			persona.setEstado(cajaResultado.getString("estado"));
			persona.setFecha(cajaResultado.getDate("fecha"));
		} else {
			System.out.println("No se encontro el registro");
		}
		
		cajaResultado.close();
		chalanChidoriSQL.close();
		
		return persona;
	}
	
	private static void operacionConsultarPersona(Connection conn, Scanner lector) throws SQLException {
		
		int id = 0;
		Persona persona = null;
		
		System.out.println("Consultar persona");
		System.out.println("Ingresa el id: ");
		id = lector.nextInt();
		
		persona = consultarPersonaPorId(conn, id);
		
		System.out.println("Datos de la persona: ");
		System.out.println("Id: " + persona.getId());
		System.out.println("IdPais: " + persona.getIdPais());
		System.out.println("Nombre: " + persona.getNombre());
		System.out.println("Edad: " + persona.getEdad());
		System.out.println("Estado: " + persona.getEstado());
		System.out.println("Fecha: " + persona.getFecha());
	}
	
	
	
	
	
	
	
	
	
	
	
}

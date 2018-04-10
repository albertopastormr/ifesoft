package Integracion.Pabellon;


import Exceptions.DAOException;
import Integracion.Pabellon.DAOPabellon;
import Negocio.Pabellon.Tpabellon;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class DAOPabellonImp implements DAOPabellon {
	protected static final String connectionChain = "jdbc:mysql://localhost:3306/ifesoft";

	/***
	 * Inserts a valid Tpabellon to database 'ifesoft'
	 * @param tPabellon
	 * @return ID of the tPabellon created
	 * @throws DAOException error from database
	 */
	public Integer create(Tpabellon tPabellon) throws DAOException {
		int id = -1;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		Connection connec = null;
		try { // Conexion db
			connec = DriverManager.getConnection(connectionChain,"manager","manager-if"); // Datos de acceso a la db: user//manager pw//manager-if
		} catch (SQLException e) {
			throw new DAOException("ERROR: acceso a la conexion a DB para 'create' Name Pabellon "+ tPabellon.getId() +" no logrado\n");
		}

		try { // Tratamiento db
			PreparedStatement ps;
			ps = connec.prepareStatement("INSERT INTO pabellon(capacity, total_m2, util_m2, active) VALUES (?,?,?,?)");
			ps.setInt(1, tPabellon.getCapacity());
			ps.setInt(2, tPabellon.getTotal_m2());
			ps.setInt(3, tPabellon.getUtil_m2());
			ps.setBoolean(4, true);
			ps.execute();


			ps = connec.prepareStatement("SELECT LAST_INSERT_ID() FROM pabellon");

			ResultSet rs = ps.executeQuery();
			if (rs.next())
				id = rs.getInt("LAST_INSERT_ID()");
		}
		catch (SQLException e){
			throw new DAOException("ERROR: tratamiento DB para 'create' Name Pabellon "+ tPabellon.getId() +" no logrado\n");
		}
		finally {
			try {
				connec.close();
			} catch (SQLException e) {
				throw new DAOException("ERROR: cerrando conexion a DB para 'create' no logrado\n");			}
		}


		try { // Desconexion db
			connec.close();
		} catch (SQLException e) {
			throw new DAOException("ERROR: cerrando conexion a DB para 'create' Name Pabellon "+ tPabellon.getId() +" no logrado\n");
		}
		return id;
	}

	/***
	 * reads every Tpabellon(collection) from database 'ifesoft' with any constraint
	 * @return Collection<Tpabellon>
	 * @throws DAOException error from database
	 */
	public Collection<Tpabellon> readAll() throws DAOException {
		ArrayList<Tpabellon> readPabellonList = new ArrayList<>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		Connection connec = null;
		try { // Conexion db
			connec = DriverManager.getConnection(connectionChain,"manager","manager-if");
		} catch (SQLException e) {
			throw new DAOException("ERROR: acceso a la conexion a DB para 'readAll' no logrado\n");
		}


		try { // Tratamiento db
			PreparedStatement ps;

			ps = connec.prepareStatement("SELECT * FROM pabellon WHERE active = true");

			ResultSet rs = ps.executeQuery();
			while (rs.next())
				readPabellonList.add( new Tpabellon( rs.getInt("capacity"), rs.getInt("total_m2"), rs.getInt("util_m2"), rs.getBoolean("active") ) );
		}
		catch (SQLException e){
			throw new DAOException("ERROR: tratamiento DB para 'readAll' no logrado\n");
		}
		finally {
			try {
				connec.close();
			} catch (SQLException e) {
				throw new DAOException("ERROR: cerrando conexion a DB para 'readAll' no logrado\n");			}
		}

		return readPabellonList;
	}

	/***
	 * reads a Tpabellon from database ifesoft by a name
	 * @param id Tpabellon name to be read
	 * @return Tpabellon tPabellon read from database
	 * @throws DAOException error from database
	 */
	public Tpabellon readById(Integer id) throws DAOException {
		Tpabellon readPabellon = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		Connection connec = null;
		try { // Conexion db
			connec = DriverManager.getConnection(connectionChain,"manager","manager-if");
		} catch (SQLException e) {
			throw new DAOException("ERROR: acceso a la conexion a DB para 'readByName' Name Pabellon "+ id +" no logrado\n");
		}

		try { // Tratamiento db
			PreparedStatement ps;

			ps = connec.prepareStatement("SELECT * FROM pabellon WHERE id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()){
				readPabellon = new Tpabellon( rs.getInt("capacity"), rs.getInt("total_m2"), rs.getInt("util_m2"), rs.getBoolean("active") ) ;
			}
		}
		catch (SQLException e){
			throw new DAOException("ERROR: tratamiento DB para 'readByName' Name Pabellon "+ id +" no logrado\n");
		}
		finally {
			try { // Desconexion db
				connec.close();
			} catch (SQLException e) {
				throw new DAOException("ERROR: cerrando conexion a DB para 'readByName' Name Pabellon "+ id +" no logrado\n");
			}
		}

		return readPabellon;
	}

	/***
	 * Updates the database ifesoft information of a tFeria(param) which already exists
	 * @param tPabellon it needs a valid ID read from db
	 * @return Integer ID of the Tpabellon updated
	 * @throws DAOException error from database
	 */
	public Integer update(Tpabellon tPabellon) throws DAOException {
		int id = -1;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		Connection connec = null;
		try { // Conexion db
			connec = DriverManager.getConnection(connectionChain,"manager","manager-if");
		} catch (SQLException e) {
			throw new DAOException("ERROR: acceso a la conexion a DB para 'update' Name Pabellon "+ tPabellon.getId() +" no logrado\n");
		}

		try { // Tratamiento db
			PreparedStatement ps;
			ps = connec.prepareStatement("UPDATE pabellon SET (capacity, total_m2, util_m2, active) VALUES (?,?,?,?)");
			ps.setInt(1, tPabellon.getCapacity());
			ps.setInt(2, tPabellon.getTotal_m2());
			ps.setInt(3, tPabellon.getUtil_m2());
			ps.setBoolean(4, true);
			ps.execute();

			ps = connec.prepareStatement("SELECT id FROM pabellon WHERE id = ?");
			ps.setInt(1, tPabellon.getId());
			ResultSet rs = ps.executeQuery();

			if (rs.next())
				id = rs.getInt("id");
		}
		catch (SQLException e){
			throw new DAOException("ERROR: tratamiento DB para 'update' Name Pabellon "+ tPabellon.getId() +" no logrado\n");
		}
		finally {
			try {
				connec.close();
			} catch (SQLException e) {
				throw new DAOException("ERROR: cerrando conexion a DB para 'update' no logrado\n");			}
		}


		try { // Desconexion db
			connec.close();
		} catch (SQLException e) {
			throw new DAOException("ERROR: cerrando conexion a DB para 'update' Name Pabellon "+ tPabellon.getId() +" no logrado\n");
		}

		return id;
	}

	/***
	 * deletes a tFeria from database
	 * @param id tFeria to delete
	 * @return boolean has_deleted_tPabellon
	 * @throws DAOException error from database
	 */
	public boolean delete (Integer id) throws DAOException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		Connection connec = null;
		try { // Conexion db
			connec = DriverManager.getConnection(connectionChain,"manager","manager-if");
		} catch (SQLException e) {
			throw new DAOException("ERROR: acceso a la conexion para 'delete' ID Pabellon "+ id +" no logrado\n");
		}


		try { // Tratamiento db
			PreparedStatement ps;
			ps = connec.prepareStatement("DELETE FROM pabellon WHERE id = ?");
			ps.setInt(1, id);
			ps.execute();
		}
		catch (SQLException e){
			throw new DAOException("ERROR: tratamiento para 'delete' ID Pabellon "+ id +" no logrado\n");
		}
		finally {
			try { // Desconexion db
				connec.close();
			} catch (SQLException e) {
				throw new DAOException("ERROR: cerrando conexion a DB para 'delete' ID Pabellon "+ id +" no logrado\n");
			}
		}

		return true;
	}

	/***
	 * Deletes every tFeria from database
	 * @throws DAOException error from database
	 */
	public void deleteAll() throws DAOException {
		Connection connec = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
			throw new DAOException("Error al registrar el driver de mariadb: " + ex);
		}
		try { // Conexion db
			connec = DriverManager.getConnection(connectionChain); // Datos de acceso a la db: user//manager pw//manager-if
		} catch (SQLException e) {
			throw new DAOException("ERROR: acceso a la conexion para 'deleteAll' no logrado\n");
		}


		try { // Tratamiento db
			PreparedStatement ps = connec.prepareStatement("TRUNCATE TABLE pabellon");
			ps.close();
		}
		catch (SQLException e){
			throw new DAOException("ERROR: deleteAll Tpabellon no logrado\n");
		}
		finally {
			try {
				connec.close();
			} catch (SQLException e) {
				throw new DAOException("ERROR: cerrando conexion a DB para 'deleteAll' no logrado\n");			}
		}

	}

}

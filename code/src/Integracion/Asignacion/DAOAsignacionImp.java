package Integracion.Asignacion;


import Exceptions.DAOException;
import Integracion.Asignacion.DAOAsignacion;
import Negocio.Asignacion.Tasignacion;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class DAOAsignacionImp implements DAOAsignacion {

	protected static final String connectionChain = "jdbc:mysql://localhost:3306/ifesoft_bd";

	/***
	 * Inserts a valid Tferia to database 'ifesoft'
	 * @param tAsignacion
	 * @return
	 * @throws DAOException
	 */
	public Integer create(Tasignacion tAsignacion) throws DAOException {
		int id = -1;

		Connection connec = null;
		try { // Conexion db
			connec = DriverManager.getConnection(connectionChain,"manager","manager-if"); // Datos de acceso a la db: user//manager pw//manager-if
		} catch (SQLException e) {
			throw new DAOException("ERROR: acceso a la conexion a DB para 'create' Asignacion ID Feria "+ tAsignacion.getFair_id() + " ID Pabellon " + tAsignacion.getPavilion_id() + " ID Stand " + tAsignacion.getStand_id()+" no logrado\n");
		}

		try { // Tratamiento db
			PreparedStatement ps;
			ps = connec.prepareStatement("INSERT INTO asignacion(fair_id, pavilion_id, stand_id, used_m2, active) VALUES (?,?,?,?,?)");
			ps.setInt(1, tAsignacion.getFair_id());
			ps.setInt(2, tAsignacion.getPavilion_id());
			ps.setInt(3, tAsignacion.getStand_id());
			ps.setInt(4, tAsignacion.getUsed_m2());
			ps.setBoolean(5, true);
			ps.execute();

			ps = connec.prepareStatement("SELECT LAST_INSERT_ID() FROM asignacion");

			ResultSet rs = ps.executeQuery();
			if (rs.next())
				id = rs.getInt("LAST_INSERT_ID()");
		}
		catch (SQLException e){
			throw new DAOException("ERROR: tratamiento DB para 'create' Asignacion ID Feria "+ tAsignacion.getFair_id() + " ID Pabellon " + tAsignacion.getPavilion_id() + " ID Stand " + tAsignacion.getStand_id()+" no logrado\n");
		}

		try { // Desconexion db
			connec.close();
		} catch (SQLException e) {
			throw new DAOException("ERROR: cerrando conexion a DB para 'create' Asignacion ID Feria "+ tAsignacion.getFair_id() + " ID Pabellon " + tAsignacion.getPavilion_id() + " ID Stand " + tAsignacion.getStand_id()+" no logrado\n");
		}
		return id;
	}

	/***
	 * reads every Tferia(collection) from database 'ifesoft' with any constraint
	 * @return
	 * @throws DAOException
	 */
	public Collection<Tasignacion> readAll() throws DAOException {
		ArrayList<Tasignacion> readAsignacionList = new ArrayList<>();
		Connection connec = null;
		try { // Conexion db
			connec = DriverManager.getConnection(connectionChain,"manager","manager-if");
		} catch (SQLException e) {
			throw new DAOException("ERROR: acceso a la conexion a DB para 'readAll' no logrado\n");
		}



		try { // Tratamiento db
			PreparedStatement ps;

			ps = connec.prepareStatement("SELECT * FROM asignacion WHERE active = true");

			ResultSet rs = ps.executeQuery();
			while (rs.next())
				readAsignacionList.add( new Tasignacion( rs.getInt("fair_id"), rs.getInt("pavilion_id"), rs.getInt("stand_id"), rs.getInt("used_m2"), rs.getBoolean("active") ) );
		}
		catch (SQLException e){
			throw new DAOException("ERROR: tratamiento DB para 'readAll' no logrado\n");
		}


		try { // Desconexion db
			connec.close();
		} catch (SQLException e) {
			throw new DAOException("ERROR: cerrando conexion a DB para 'readAll' no logrado\n");
		}
		return readAsignacionList;
	}

	/***
	 * reads a Tferia from database ifesoft by a fair name
	 * @param name Tferia name to be read
	 * @return
	 * @throws DAOException
	 */
	public Tasignacion readByFairName(String name) throws DAOException {
		Tasignacion readAsignacion = null;

		Connection connec = null;
		try { // Conexion db
			connec = DriverManager.getConnection(connectionChain,"manager","manager-if");
		} catch (SQLException e) {
			throw new DAOException("ERROR: acceso a la conexion a DB para 'readByName' Fair Name "+ name +" no logrado\n");
		}

		try { // Tratamiento db
			PreparedStatement ps;

			ps = connec.prepareStatement("SELECT * FROM asignacion as JOIN feria f ON as.fair_id = f.id WHERE f.name = ?");
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();

			if (rs.next()){
				readAsignacion = new Tasignacion( rs.getInt("fair_id"), rs.getInt("pavilion_id"), rs.getInt("stand_id"), rs.getInt("used_m2"), rs.getBoolean("active") ) ;
			}
		}
		catch (SQLException e){
			throw new DAOException("ERROR: tratamiento DB para 'readByName' Fair Name "+ name +" no logrado\n");
		}



		try { // Desconexion db
			connec.close();
		} catch (SQLException e) {
			throw new DAOException("ERROR: cerrando conexion a DB para 'readByName' Fair Name "+ name +" no logrado\n");
		}
		return readAsignacion;
	}

	/***
	 * reads a Tferia from database ifesoft by a pavilion name
	 * @param name Tferia name to be read
	 * @return
	 * @throws DAOException
	 */
	public Tasignacion readByPavilionName(String name) throws DAOException {
		Tasignacion readAsignacion = null;

		Connection connec = null;
		try { // Conexion db
			connec = DriverManager.getConnection(connectionChain,"manager","manager-if");
		} catch (SQLException e) {
			throw new DAOException("ERROR: acceso a la conexion a DB para 'readByName' Pavilion Name "+ name +" no logrado\n");
		}

		try { // Tratamiento db
			PreparedStatement ps;

			ps = connec.prepareStatement("SELECT * FROM asignacion as JOIN pabellon pa ON as.pavilion_id = pa.id WHERE pa.name = ?");
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();

			if (rs.next()){
				readAsignacion = new Tasignacion( rs.getInt("fair_id"), rs.getInt("pavilion_id"), rs.getInt("stand_id"), rs.getInt("used_m2"), rs.getBoolean("active") ) ;
			}
		}
		catch (SQLException e){
			throw new DAOException("ERROR: tratamiento DB para 'readByName' Pavilion Name "+ name +" no logrado\n");
		}



		try { // Desconexion db
			connec.close();
		} catch (SQLException e) {
			throw new DAOException("ERROR: cerrando conexion a DB para 'readByName' Pavilion Name "+ name +" no logrado\n");
		}
		return readAsignacion;
	}

	/***
	 * Updates the database ifesoft information of a tAsignacion(param) which already exists
	 * @param tAsignacion it needs a valid ID read from db
	 * @return
	 * @throws DAOException
	 */
	public Integer update(Tasignacion tAsignacion) throws DAOException {
		int id = -1;

		Connection connec = null;
		try { // Conexion db
			connec = DriverManager.getConnection(connectionChain,"manager","manager-if");
		} catch (SQLException e) {
			throw new DAOException("ERROR: acceso a la conexion a DB para 'update' Asignacion ID Feria "+ tAsignacion.getFair_id() + " ID Pabellon " + tAsignacion.getPavilion_id() + " ID Stand " + tAsignacion.getStand_id()+" no logrado\n");
		}

		try { // Tratamiento db
			PreparedStatement ps;
			ps = connec.prepareStatement("UPDATE asignacion SET (fair_id, pavilion_id, stand_id, used_m2, active) VALUES (?,?,?,?,?)");
			ps.setInt(1, tAsignacion.getFair_id());
			ps.setInt(2, tAsignacion.getPavilion_id());
			ps.setInt(3, tAsignacion.getStand_id());
			ps.setInt(4, tAsignacion.getUsed_m2());
			ps.setBoolean(5, true);
			ps.execute();


		}
		catch (SQLException e){
			throw new DAOException("ERROR: tratamiento DB para 'update' Asignacion ID Feria "+ tAsignacion.getFair_id() + " ID Pabellon " + tAsignacion.getPavilion_id() + " ID Stand " + tAsignacion.getStand_id()+" no logrado\n");
		}



		try { // Desconexion db
			connec.close();
		} catch (SQLException e) {
			throw new DAOException("ERROR: cerrando conexion a DB para 'update' Asignacion ID Feria "+ tAsignacion.getFair_id() + " ID Pabellon " + tAsignacion.getPavilion_id() + " ID Stand " + tAsignacion.getStand_id() +" no logrado\n");
		}

		return id;
	}

	/***
	 * deletes a tAsignacion from database
	 * @param pavilion_id
	 * @param stand_id
	 * @param fair_id tAsignacion to delete
	 * @return boolean
	 * @throws DAOException error from database
	 */
	public boolean delete (Integer fair_id, Integer pavilion_id, Integer stand_id) throws DAOException {
		Connection connec = null;
		try { // Conexion db
			connec = DriverManager.getConnection(connectionChain,"manager","manager-if");
		} catch (SQLException e) {
			throw new DAOException("ERROR: acceso a la conexion para 'delete' Asignacion con ID Feria "+ fair_id + " ID Pabellon " + pavilion_id + " ID Stand " + stand_id +" no logrado\n");
		}


		try { // Tratamiento db
			PreparedStatement ps;
			ps = connec.prepareStatement("DELETE FROM asignacion WHERE fair_id = ? AND pavilion_id = ? AND stand_id = ?");
			ps.setInt(1, fair_id);
			ps.setInt(2, pavilion_id);
			ps.setInt(3, stand_id);
			ps.execute();
		}
		catch (SQLException e){
			throw new DAOException("ERROR: tratamiento para 'delete' Asignacion con ID Feria "+ fair_id + " ID Pabellon " + pavilion_id + " ID Stand " + stand_id +" no logrado\n");
		}


		try { // Desconexion db
			connec.close();
		} catch (SQLException e) {
			throw new DAOException("ERROR: cerrando conexion a DB para 'delete' Asignacion con ID Feria "+ fair_id + " ID Pabellon " + pavilion_id + " ID Stand " + stand_id +" no logrado\n");
		}
		return true;
	}

	/***
	 * Deletes every tAsignacion from database
	 * @throws DAOException
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
			PreparedStatement ps = connec.prepareStatement("TRUNCATE TABLE asignacion");
			ps.close();
		}
		catch (SQLException e){
			throw new DAOException("ERROR: deleteAll Tferia no logrado\n");
		}


		try { // Desconexion db
			connec.close();
		} catch (SQLException e) {
			throw new DAOException("ERROR: cerrando conexion a DB para 'deleteAll' no logrado\n");
		}
	}
}

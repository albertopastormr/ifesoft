package Integracion.Asignacion;


import Exceptions.DAOException;
import Integracion.Asignacion.DAOAsignacion;
import Negocio.Asignacion.Tasignacion;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class DAOAsignacionImp implements DAOAsignacion {

	protected static final String connectionChain = "jdbc:mariadb://localhost:3306/ifesoft?user=manager&password=manager-if";

	/***
	 * Inserts a valid Tasignacion to database 'ifesoft'
	 * @param tAsignacion
	 * @return Integer ID of tAsignacion created at database
	 * @throws DAOException error from database
	 */
	public Integer create(Tasignacion tAsignacion) throws DAOException {
		int id = -1;

		driverIdentify();
		Connection connec = null;

		try { // Conexion db
			connec = DriverManager.getConnection(connectionChain); // Datos de acceso a la db: user//manager pw//manager-if
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
		finally {
			try { // Desconexion db
				connec.close();
			} catch (SQLException e) {
				throw new DAOException("ERROR: cerrando conexion a DB para 'create' Asignacion ID Feria "+ tAsignacion.getFair_id() + " ID Pabellon " + tAsignacion.getPavilion_id() + " ID Stand " + tAsignacion.getStand_id()+" no logrado\n");
			}
		}
		return id;
	}

	/***
	 * reads every Tasignacion(collection) from database 'ifesoft' with any constraint
	 * @return Collection<Tasignacion>
	 * @throws DAOException error from database
	 */
	public Collection<Tasignacion> readAll() throws DAOException {
		ArrayList<Tasignacion> readAsignacionList = new ArrayList<>();

		driverIdentify();
		Connection connec = null;

		try { // Conexion db
			connec = DriverManager.getConnection(connectionChain); // Datos de acceso a la db: user//manager pw//manager-if
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
		finally {
			try { // Desconexion db
				connec.close();
			} catch (SQLException e) {
				throw new DAOException("ERROR: cerrando conexion a DB para 'readAll' no logrado\n");
			}
		}

		return readAsignacionList;
	}

	/***
	 * reads a Tasignacion from database ifesoft by a fair name
	 * @param name Tasignacion name to be read
	 * @return Tasignacion read from database
	 * @throws DAOException error from database
	 */
	public Collection<Tasignacion> readByFairName(String name) throws DAOException {
		ArrayList<Tasignacion> readAsignacionList = new ArrayList<>();

		driverIdentify();
		Connection connec = null;

		try { // Conexion db
			connec = DriverManager.getConnection(connectionChain); // Datos de acceso a la db: user//manager pw//manager-if
		} catch (SQLException e) {
			throw new DAOException("ERROR: acceso a la conexion a DB para 'readByFairName' Fair Name "+ name +" no logrado\n");
		}

		try { // Tratamiento db
			PreparedStatement ps;

			ps = connec.prepareStatement("SELECT * FROM asignacion as JOIN feria f ON as.fair_id = f.id WHERE f.name = ?");
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();

			while (rs.next()){
				readAsignacionList.add(new Tasignacion( rs.getInt("fair_id"), rs.getInt("pavilion_id"), rs.getInt("stand_id"), rs.getInt("used_m2"), rs.getBoolean("active") ) );
			}
		}
		catch (SQLException e){
			throw new DAOException("ERROR: tratamiento DB para 'readByFairName' Fair Name "+ name +" no logrado\n");
		}
		finally {
			try { // Desconexion db
				connec.close();
			} catch (SQLException e) {
				throw new DAOException("ERROR: cerrando conexion a DB para 'readByFairName' Fair Name "+ name +" no logrado\n");
			}
		}


		return readAsignacionList;
	}

	/***
	 * reads a Tasignacion from database ifesoft by a fair name
	 * @param id Tasignacion name to be read
	 * @return Tasignacion read from database
	 * @throws DAOException error from database
	 */
	public Collection<Tasignacion> readByFairId(Integer id) throws DAOException {
		ArrayList<Tasignacion> readAsignacionList = new ArrayList<>();

		driverIdentify();
		Connection connec = null;

		try { // Conexion db
			connec = DriverManager.getConnection(connectionChain); // Datos de acceso a la db: user//manager pw//manager-if
		} catch (SQLException e) {
			throw new DAOException("ERROR: acceso a la conexion a DB para 'readByFairId' Fair ID "+ id +" no logrado\n");
		}

		try { // Tratamiento db
			PreparedStatement ps;

			ps = connec.prepareStatement("SELECT * FROM asignacion as JOIN feria f ON as.fair_id = f.id WHERE f.id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()){
				readAsignacionList.add(new Tasignacion( rs.getInt("fair_id"), rs.getInt("pavilion_id"), rs.getInt("stand_id"), rs.getInt("used_m2"), rs.getBoolean("active") ) );
			}
		}
		catch (SQLException e){
			throw new DAOException("ERROR: tratamiento DB para 'readByFairId' Fair ID "+ id +" no logrado\n");
		}
		finally {
			try { // Desconexion db
				connec.close();
			} catch (SQLException e) {
				throw new DAOException("ERROR: cerrando conexion a DB para 'readByFairId' Fair ID "+ id +" no logrado\n");
			}
		}


		return readAsignacionList;
	}

	/***
	 * reads a Tasignacion from database ifesoft by a pavilion name
	 * @param id Tasignacion name to be read
	 * @return Tasignacion read from database
	 * @throws DAOException error from database
	 */
	public Collection<Tasignacion> readByPavilionId(Integer id) throws DAOException {
		ArrayList<Tasignacion> readAsignacionList = new ArrayList<>();

		driverIdentify();
		Connection connec = null;

		try { // Conexion db
			connec = DriverManager.getConnection(connectionChain); // Datos de acceso a la db: user//manager pw//manager-if
		} catch (SQLException e) {
			throw new DAOException("ERROR: acceso a la conexion a DB para 'readByPavilionID' Pavilion Id "+ id +" no logrado\n");
		}

		try { // Tratamiento db
			PreparedStatement ps;

			ps = connec.prepareStatement("SELECT * FROM asignacion as JOIN pabellon pa ON as.pavilion_id = pa.id WHERE pa.id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()){
				readAsignacionList.add( new Tasignacion( rs.getInt("fair_id"), rs.getInt("pavilion_id"), rs.getInt("stand_id"), rs.getInt("used_m2"), rs.getBoolean("active") )) ;
			}
		}
		catch (SQLException e){
			throw new DAOException("ERROR: tratamiento DB para 'readByPavilionID' Pavilion ID "+ id +" no logrado\n");
		}
		finally {
			try { // Desconexion db
				connec.close();
			} catch (SQLException e) {
				throw new DAOException("ERROR: cerrando conexion a DB para 'readByPavilionID' Pavilion ID "+ id +" no logrado\n");
			}
		}


		return readAsignacionList;
	}

	/***
	 * Updates the database ifesoft information of a tAsignacion(param) which already exists
	 * @param tAsignacion it needs a valid ID read from db
	 * @return Integer id tAsignacion updated at database
	 * @throws DAOException error from database
	 */
	public Integer update(Tasignacion tAsignacion) throws DAOException {
		int id = -1;

		driverIdentify();
		Connection connec = null;

		try { // Conexion db
			connec = DriverManager.getConnection(connectionChain); // Datos de acceso a la db: user//manager pw//manager-if
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
		finally {
			try { // Desconexion db
				connec.close();
			} catch (SQLException e) {
				throw new DAOException("ERROR: cerrando conexion a DB para 'update' Asignacion ID Feria "+ tAsignacion.getFair_id() + " ID Pabellon " + tAsignacion.getPavilion_id() + " ID Stand " + tAsignacion.getStand_id() +" no logrado\n");
			}
		}



		return id;
	}

	/***
	 * deletes a tAsignacion from database
	 * @param pavilion_id
	 * @param stand_id
	 * @param fair_id
	 * @return boolean
	 * @throws DAOException error from database
	 */
	public boolean delete (Integer fair_id, Integer pavilion_id, Integer stand_id) throws DAOException {

		driverIdentify();
		Connection connec = null;

		try { // Conexion db
			connec = DriverManager.getConnection(connectionChain); // Datos de acceso a la db: user//manager pw//manager-if
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
		finally {
			try { // Desconexion db
				connec.close();
			} catch (SQLException e) {
				throw new DAOException("ERROR: cerrando conexion a DB para 'delete' Asignacion con ID Feria " + fair_id + " ID Pabellon " + pavilion_id + " ID Stand " + stand_id + " no logrado\n");
			}
		}

		return true;
	}

	/***
	 * Deletes every tAsignacion from database
	 * @throws DAOException error from database
	 */
	public void deleteAll() throws DAOException {

		Connection connec = null;
		driverIdentify();

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
			throw new DAOException("ERROR: deleteAll Tasignacion no logrado\n");
		}
		finally {
			try { // Desconexion db
				connec.close();
			} catch (SQLException e) {
				throw new DAOException("ERROR: cerrando conexion a DB para 'deleteAll' no logrado\n");
			}
		}

	}

	private void driverIdentify() throws DAOException {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
			throw new DAOException("Error al registrar el driver de mariadb: " + ex);
		}
	}
}

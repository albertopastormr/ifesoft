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
		Integer id = -1;

		driverIdentify();
		Connection connec = null;

		try { // Conexion db
			connec = DriverManager.getConnection(connectionChain); // Datos de acceso a la db: user//manager pw//manager-if
		} catch (SQLException e) {
			throw new DAOException("ERROR: acceso a DB para 'create' Asignacion ID Feria "+ tAsignacion.getFair_id() + " ID Pabellon " + tAsignacion.getPavilion_id() + " no logrado\n");
		}

		try { // Tratamiento db
			PreparedStatement ps;
			ps = connec.prepareStatement("INSERT INTO asignacion(fair_id, pavilion_id, total_m2, used_m2, active) VALUES (?,?,?,?,?)");
			ps.setInt(1, tAsignacion.getFair_id());
			ps.setInt(2, tAsignacion.getPavilion_id());
			ps.setInt(3, tAsignacion.getTotal_m2());
			ps.setInt(4, tAsignacion.getUsed_m2());
			ps.setBoolean(5, tAsignacion.getActive());
			ps.execute();
			ps.close();

			ps = connec.prepareStatement("SELECT LAST_INSERT_ID() FROM asignacion");
			ResultSet rs = ps.executeQuery();
			ps.close();

			if (rs.next())
				id = rs.getInt("LAST_INSERT_ID()");
			else
				return -1;
		}
		catch (SQLException e){
			throw new DAOException("ERROR: tratamiento DB para 'create' Asignacion ID Feria "+ tAsignacion.getFair_id() + " ID Pabellon " + tAsignacion.getPavilion_id() +" no logrado\n");
		}
		finally {
			try { // Desconexion db
				connec.close();
			} catch (SQLException e) {
				throw new DAOException("ERROR: cerrando conexion a DB para 'create' Asignacion ID Feria "+ tAsignacion.getFair_id() + " ID Pabellon " + tAsignacion.getPavilion_id() +" no logrado\n");
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
			ps.close();
			while (rs.next())
				readAsignacionList.add( new Tasignacion( rs.getInt("fair_id"), rs.getInt("pavilion_id"), rs.getInt("total_m2"), rs.getInt("used_m2"), rs.getBoolean("active") ) );
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
			ps.close();

			while (rs.next()){
				readAsignacionList.add(new Tasignacion( rs.getInt("fair_id"), rs.getInt("pavilion_id"), rs.getInt("total_m2"), rs.getInt("used_m2"), rs.getBoolean("active") ) );
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
	 * reads a Tasignacion from database ifesoft by a fair id
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

			ps = connec.prepareStatement("SELECT * FROM asignacion WHERE fair_id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			ps.close();

			while (rs.next()){
				readAsignacionList.add(new Tasignacion( rs.getInt("fair_id"), rs.getInt("pavilion_id"), rs.getInt("total_m2"), rs.getInt("used_m2"), rs.getBoolean("active") ) );
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
	 * reads a Collection<Tasignacion> from database ifesoft by a pavilion id
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

			ps = connec.prepareStatement("SELECT * FROM asignacion WHERE pavilion_id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			ps.close();

			while (rs.next()){
				readAsignacionList.add( new Tasignacion( rs.getInt("fair_id"), rs.getInt("pavilion_id"), rs.getInt("total_m2"), rs.getInt("used_m2"), rs.getBoolean("active") )) ;
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
	 * reads a Tasignacion from database ifesoft
	 * @param id id to read from database
	 * @return Tasignacion read from database
	 * @throws DAOException error from database
	 */
	public Tasignacion readById(Integer id) throws DAOException {
		Tasignacion read = null;
		driverIdentify();
		Connection connec = null;

		try { // Conexion db
			connec = DriverManager.getConnection(connectionChain); // Datos de acceso a la db: user//manager pw//manager-if
		} catch (SQLException e) {
			throw new DAOException("ERROR: acceso a la conexion a DB para 'readById' Asignacion ID "+ id +" no logrado\n");
		}

		try { // Tratamiento db
			PreparedStatement ps;

			ps = connec.prepareStatement("SELECT * FROM asignacion WHERE id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			ps.close();

			if (rs.next()){
				read = new Tasignacion( rs.getInt("fair_id"), rs.getInt("pavilion_id"), rs.getInt("total_m2"), rs.getInt("used_m2"), rs.getBoolean("active")) ;
			}
		}
		catch (SQLException e){
			throw new DAOException("ERROR: tratamiento DB para 'readById' Asignacion ID " + id +" no logrado\n");
		}
		finally {
			try { // Desconexion db
				connec.close();
			} catch (SQLException e) {
				throw new DAOException("ERROR: cerrar DB para 'readById' Asignacion ID " + id +" no logrado\n");
			}
		}


		return read;
	}

	/***
	 * Updates the database ifesoft information of a tAsignacion(param) which already exists
	 * @param tAsignacion it needs a valid ID read from db
	 * @return Integer id tAsignacion updated at database
	 * @throws DAOException error from database
	 */
	public Integer update(Tasignacion tAsignacion) throws DAOException {

		driverIdentify();
		Connection connec = null;

		try { // Conexion db
			connec = DriverManager.getConnection(connectionChain); // Datos de acceso a la db: user//manager pw//manager-if
		} catch (SQLException e) {
			throw new DAOException("ERROR: acceso a la conexion a DB para 'update' Asignacion ID " + tAsignacion.getId()+" no logrado\n");
		}

		try { // Tratamiento db
			PreparedStatement ps;
			ps = connec.prepareStatement("UPDATE asignacion SET AND AND total_m2 = ? AND used_m2 = ? AND active = ? WHERE id = ?");
			ps.setInt(1, tAsignacion.getTotal_m2());
			ps.setInt(2, tAsignacion.getUsed_m2());
			ps.setBoolean(3, tAsignacion.getActive());
			ps.setInt(4, tAsignacion.getId());
			ps.execute();
			ps.close();

			ps = connec.prepareStatement("SELECT id FROM asignacion WHERE id = ?");
			ps.setInt(1, tAsignacion.getId());
			ResultSet rs_id = ps.executeQuery();
			ps.close();

			if (rs_id.next()) {
				if (!tAsignacion.getActive()) { // Caso desactivado tAsignacion
					// Desactivado de los stands y participacion relacionados con la asignacion a desactivar
					ps = connec.prepareStatement("UPDATE stand s JOIN participacion p ON s.id = p.stand_id SET s.active = ? AND p.active = ? WHERE s.assignation_id = ?");
					ps.setBoolean(1, tAsignacion.getActive());
					ps.setBoolean(2, tAsignacion.getActive());
					ps.setInt(3, tAsignacion.getId());
					ps.execute();
					ps.close();
				}
				return  rs_id.getInt("id");
			}
			else
				return -1;

		}
		catch (SQLException e){
			throw new DAOException("ERROR: tratamiento DB para 'update' Asignacion ID " + tAsignacion.getId()+" no logrado\n");
		}
		finally {
			try { // Desconexion db
				connec.close();
			} catch (SQLException e) {
				throw new DAOException("ERROR: cerrando conexion a DB para 'update' Asignacion ID " + tAsignacion.getId() +" no logrado\n");
			}
		}
	}


	/***
	 * deletes a tAsignacion from database
	 * @param id
	 * @return boolean
	 * @throws DAOException error from database
	 */
	public boolean delete (Integer id) throws DAOException {

		driverIdentify();
		Connection connec = null;

		try { // Conexion db
			connec = DriverManager.getConnection(connectionChain); // Datos de acceso a la db: user//manager pw//manager-if
		} catch (SQLException e) {
			throw new DAOException("ERROR: acceso a la conexion para 'delete' Asignacion con ID " + id +" no logrado\n");
		}


		try { // Tratamiento db
			PreparedStatement ps;
			ps = connec.prepareStatement("DELETE FROM asignacion WHERE id = ?");
			ps.setInt(1, id);
			ps.execute();
			ps.close();
		}
		catch (SQLException e){
			throw new DAOException("ERROR: tratamiento para 'delete' Asignacion con ID " + id +" no logrado\n");
		}
		finally {
			try { // Desconexion db
				connec.close();
			} catch (SQLException e) {
				throw new DAOException("ERROR: cerrando conexion a DB para 'delete' Asignacion con ID " + id + " no logrado\n");
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
			PreparedStatement ps = connec.prepareStatement("SET FOREIGN_KEY_CHECKS = 0");
			ps.execute();
			ps.close();
			ps = connec.prepareStatement("TRUNCATE TABLE asignacion");
			ps.execute();
			ps.close();
			ps = connec.prepareStatement("SET FOREIGN_KEY_CHECKS = 1");
			ps.execute();
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

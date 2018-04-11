package Integracion.Participacion;


import Exceptions.DAOException;
import Integracion.Participacion.DAOParticipacion;
import Negocio.Participacion.Tparticipacion;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class DAOParticipacionImp implements DAOParticipacion {
	protected static final String connectionChain = "jdbc:mariadb://localhost:3306/ifesoft?user=manager&password=manager-if";

	/***
	 * Inserts a valid Tparticipacion to database 'ifesoft'
	 * @param tParticipacion tParticipacion to create
	 * @return Integer ID Tparticipacion created
	 * @throws DAOException error from database
	 */
	public Integer create(Tparticipacion tParticipacion) throws DAOException {
		int id = -1;

		Connection connec = null;
		driverIdentify();

		try { // Conexion db
			connec = DriverManager.getConnection(connectionChain,"manager","manager-if"); // Datos de acceso a la db: user//manager pw//manager-if
		} catch (SQLException e) {
			throw new DAOException("ERROR: acceso a la conexion a DB para 'create' Participacion con ID Feria "+ tParticipacion.getFair_id() + " ID Participante " + tParticipacion.getClient_id() + " ID Stand " + tParticipacion.getStand_id()+" no logrado\n");
		}

		try { // Tratamiento db
			PreparedStatement ps;
			ps = connec.prepareStatement("INSERT INTO participacion(fair_id, client_id, stand_id, active) VALUES (?,?,?,?)");
			ps.setInt(1, tParticipacion.getFair_id());
			ps.setInt(2, tParticipacion.getClient_id());
			ps.setInt(3, tParticipacion.getStand_id());
			ps.setBoolean(4, true);
			ps.execute();

			ps = connec.prepareStatement("SELECT LAST_INSERT_ID() FROM participacion");

			ResultSet rs = ps.executeQuery();
			if (rs.next())
				id = rs.getInt("LAST_INSERT_ID()");
		}
		catch (SQLException e){
			throw new DAOException("ERROR: tratamiento DB para 'create' Participacion con ID Feria "+ tParticipacion.getFair_id() + " ID Participante " + tParticipacion.getClient_id() + " ID Stand " + tParticipacion.getStand_id()+" no logrado\n");
		}
		finally {
			try { // Desconexion db
				connec.close();
			} catch (SQLException e) {
				throw new DAOException("ERROR: cerrando conexion a DB para 'create' Participacion con ID Feria "+ tParticipacion.getFair_id() + " ID Participante " + tParticipacion.getClient_id() + " ID Stand " + tParticipacion.getStand_id()+" no logrado\n");
			}
		}

		return id;
	}

	/***
	 * reads every Tparticipacion(collection) from database 'ifesoft' with any constraint
	 * @return Collection<Tparticipacion>
	 * @throws DAOException error from database
	 */
	public Collection<Tparticipacion> readAll() throws DAOException {
		ArrayList<Tparticipacion> readParticipacionList = new ArrayList<>();

		Connection connec = null;
		driverIdentify();

		try { // Conexion db
			connec = DriverManager.getConnection(connectionChain); // Datos de acceso a la db: user//manager pw//manager-if
		} catch (SQLException e) {
			throw new DAOException("ERROR: acceso a la conexion a DB para 'readAll' no logrado\n");
		}

		try { // Tratamiento db
			PreparedStatement ps;

			ps = connec.prepareStatement("SELECT * FROM participacion WHERE active = true");

			ResultSet rs = ps.executeQuery();
			while (rs.next())
				readParticipacionList.add( new Tparticipacion( rs.getInt("fair_id"), rs.getInt("client_id"), rs.getInt("stand_id"), rs.getBoolean("active") ) );
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

		return readParticipacionList;
	}

	/***
	 * reads a Tparticipacion from database ifesoft by a fair name
	 * @param name Tparticipacion name to be read
	 * @return Tparticipacion read from database
	 * @throws DAOException
	 */
	public Collection<Tparticipacion> readByFairName(String name) throws DAOException {
		ArrayList<Tparticipacion> readParticipacionList = new ArrayList<>();

		Connection connec = null;
		driverIdentify();

		try { // Conexion db
			connec = DriverManager.getConnection(connectionChain); // Datos de acceso a la db: user//manager pw//manager-if
		} catch (SQLException e) {
			throw new DAOException("ERROR: acceso a la conexion a DB para 'readByName' Fair Name "+ name +" no logrado\n");
		}

		try { // Tratamiento db
			PreparedStatement ps;

			ps = connec.prepareStatement("SELECT * FROM participacion pn JOIN feria f ON pn.fair_id = f.id  WHERE f.name = ?");
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				readParticipacionList.add(new Tparticipacion( rs.getInt("fair_id"), rs.getInt("client_id"), rs.getInt("stand_id"), rs.getBoolean("active") ));
			}
		}
		catch (SQLException e){
			throw new DAOException("ERROR: tratamiento DB para 'readByName' Fair Name "+ name +" no logrado\n");
		}
		finally {
			try { // Desconexion db
				connec.close();
			} catch (SQLException e) {
				throw new DAOException("ERROR: cerrando conexion a DB para 'readByFairName' Fair Name "+ name +" no logrado\n");
			}
		}

		return readParticipacionList;
	}

	/***
	 * reads a Tparticipacion from database ifesoft by a client name
	 * @param name Tparticipacion name to be read
	 * @return Tparticipacion read from database
	 * @throws DAOException error from database
	 */
	public Collection<Tparticipacion> readByClientName(String name) throws DAOException {
		ArrayList<Tparticipacion> readParticipacionList = new ArrayList<>();

		Connection connec = null;
		driverIdentify();

		try { // Conexion db
			connec = DriverManager.getConnection(connectionChain); // Datos de acceso a la db: user//manager pw//manager-if
		} catch (SQLException e) {
			throw new DAOException("ERROR: acceso a la conexion a DB para 'readByName' Client Name "+ name +" no logrado\n");
		}

		try { // Tratamiento db
			PreparedStatement ps;

			ps = connec.prepareStatement("SELECT * FROM participacion pn JOIN participante pe ON pn.client_id = pe.id WHERE pe.name = ?");
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();

			while (rs.next()){
				readParticipacionList.add( new Tparticipacion( rs.getInt("fair_id"), rs.getInt("client_id"), rs.getInt("stand_id"), rs.getBoolean("active") )) ;
			}
		}
		catch (SQLException e){
			throw new DAOException("ERROR: tratamiento DB para 'readByName' Client Name "+ name +" no logrado\n");
		}
		finally {
			try { // Desconexion db
				connec.close();
			} catch (SQLException e) {
				throw new DAOException("ERROR: cerrando conexion a DB para 'readByClientName' Participante Name "+ name +" no logrado\n");
			}
		}

		return readParticipacionList;
	}

	/***
	 * Updates the database ifesoft information of a tParticipacion(param) which already exists
	 * @param tParticipacion it needs a valid ID read from db
	 * @return ID of the Tparticipacion updated at database
	 * @throws DAOException error from database
	 */
	public Integer update(Tparticipacion tParticipacion) throws DAOException {
		int id = -1;

		Connection connec = null;
		driverIdentify();

		try { // Conexion db
			connec = DriverManager.getConnection(connectionChain); // Datos de acceso a la db: user//manager pw//manager-if
		} catch (SQLException e) {
			throw new DAOException("ERROR: acceso a la conexion a DB para 'update' Participacion con ID Feria "+ tParticipacion.getFair_id() + " ID Participante " + tParticipacion.getClient_id() + " ID Stand " + tParticipacion.getStand_id()+" no logrado\n");
		}

		try { // Tratamiento db
			PreparedStatement ps;
			ps = connec.prepareStatement("UPDATE participacion SET (fair_id, client_id, stand_id, active) VALUES (?,?,?,?)");
			ps.setInt(1, tParticipacion.getFair_id());
			ps.setInt(2, tParticipacion.getClient_id());
			ps.setInt(3, tParticipacion.getStand_id());
			ps.setBoolean(4, true);
			ps.execute();

		}
		catch (SQLException e){
			throw new DAOException("ERROR: tratamiento DB para 'update' Participacion con ID Feria "+ tParticipacion.getFair_id() + " ID Participante " + tParticipacion.getClient_id() + " ID Stand " + tParticipacion.getStand_id()+" no logrado\n");
		}
		finally {
			try { // Desconexion db
				connec.close();
			} catch (SQLException e) {
				throw new DAOException("ERROR: cerrando conexion a DB para 'update' Participacion con ID Feria "+ tParticipacion.getFair_id() + " ID Participante " + tParticipacion.getClient_id() + " ID Stand " + tParticipacion.getStand_id()+" no logrado\n");
			}
		}

		return id;
	}

	/***
	 * deletes a tParticipacion from database
	 * @param stand_id
	 * @param client_id
	 * @param fair_id
	 * @return boolean has_been_deleted
	 * @throws DAOException error from database
	 */
	public boolean delete (Integer fair_id, Integer client_id, Integer stand_id) throws DAOException {

		Connection connec = null;
		driverIdentify();

		try { // Conexion db
			connec = DriverManager.getConnection(connectionChain); // Datos de acceso a la db: user//manager pw//manager-if
		} catch (SQLException e) {
			throw new DAOException("ERROR: acceso a la conexion para 'delete' Participacion con ID Feria "+ fair_id + " ID Participante " + client_id + " ID Stand " + stand_id +" no logrado\n");
		}


		try { // Tratamiento db
			PreparedStatement ps;
			ps = connec.prepareStatement("DELETE FROM participacion WHERE fair_id = ? AND client_id = ? AND stand_id = ?");
			ps.setInt(1, fair_id);
			ps.setInt(2, client_id);
			ps.setInt(3, stand_id);
			ps.execute();
		}
		catch (SQLException e){
			throw new DAOException("ERROR: tratamiento para 'delete' Participacion con ID Feria "+ fair_id + " ID Participante " + client_id + " ID Stand " + stand_id +" no logrado\n");
		}
		finally {
			try { // Desconexion db
				connec.close();
			} catch (SQLException e) {
				throw new DAOException("ERROR: cerrando conexion a DB para 'delete' Participacion con ID Feria "+ fair_id + " ID Participante " + client_id + " ID Stand " + stand_id +" no logrado\n");
			}
		}

		return true;
	}

	/***
	 * Deletes every tParticipacion from database
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
			PreparedStatement ps = connec.prepareStatement("TRUNCATE TABLE participacion");
			ps.close();
		}
		catch (SQLException e){
			throw new DAOException("ERROR: deleteAll Tparticipacion no logrado\n");
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

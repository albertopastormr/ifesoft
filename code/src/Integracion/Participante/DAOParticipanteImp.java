package Integracion.Participante;


import Exceptions.DAOException;
import Negocio.Participante.Tparticipante;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class DAOParticipanteImp implements DAOParticipante {
	protected static final String connectionChain = "jdbc:mariadb://localhost:3306/ifesoft?user=manager&password=manager-if";

	/***
	 * Inserts a valid Tparticipante to database 'ifesoft'
	 * @param tParticipante tParticipante to be create
	 * @return Integer ID Tparticipante created
	 * @throws DAOException error from database
	 */
	public Integer create(Tparticipante tParticipante) throws DAOException {
		int id = -1;

		Connection connec = null;
		driverIdentify();

		try { // Conexion db
			connec = DriverManager.getConnection(connectionChain,"manager","manager-if"); // Datos de acceso a la db: user//manager pw//manager-if
		} catch (SQLException e) {
			throw new DAOException("ERROR: acceso a la conexion a DB para 'create' Name Participante "+ tParticipante.getName() +" no logrado\n");
		}

		try { // Tratamiento db
			PreparedStatement ps;
			ps = connec.prepareStatement("INSERT INTO participante(name, phone, active) VALUES (?,?,?)");
			ps.setString(1, tParticipante.getName());
			ps.setLong(2, tParticipante.getPhone());
			ps.setBoolean(3, true);
			ps.execute();

			ps = connec.prepareStatement("SELECT LAST_INSERT_ID() FROM participante");

			ResultSet rs = ps.executeQuery();
			if (rs.next())
				id = rs.getInt("LAST_INSERT_ID()");
		}
		catch (SQLException e){
			throw new DAOException("ERROR: tratamiento DB para 'create' Name Participante "+ tParticipante.getName() +" no logrado\n");
		}
		finally {
			try {
				connec.close();
			} catch (SQLException e) {
				throw new DAOException("ERROR: cerrando conexion a DB para 'create' Name Feria "+ tParticipante.getName() +" no logrado\n");			}
		}

		return id;
	}

	/***
	 * reads every Tparticipante(collection) from database 'ifesoft' with any constraint
	 * @return Collection<Tparticipante>
	 * @throws DAOException error from database
	 */
	public Collection<Tparticipante> readAll() throws DAOException {
		ArrayList<Tparticipante> readParticipanteList = new ArrayList<>();

		Connection connec = null;
		driverIdentify();

		try { // Conexion db
			connec = DriverManager.getConnection(connectionChain,"manager","manager-if");
		} catch (SQLException e) {
			throw new DAOException("ERROR: acceso a la conexion a DB para 'readAll' no logrado\n");
		}



		try { // Tratamiento db
			PreparedStatement ps;

			ps = connec.prepareStatement("SELECT * FROM participante WHERE active = true");

			ResultSet rs = ps.executeQuery();
			while (rs.next())
				readParticipanteList.add( new Tparticipante( rs.getString("name"), rs.getLong("phone"),rs.getBoolean("active") ) );
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

		return readParticipanteList;
	}

	/***
	 * reads a Tparticipante from database ifesoft by a name
	 * @param name Tparticipante name to be read
	 * @return
	 * @throws DAOException
	 */
	public Tparticipante readByName(String name) throws DAOException {
		Tparticipante readParticipante = null;

		Connection connec = null;
		driverIdentify();

		try { // Conexion db
			connec = DriverManager.getConnection(connectionChain,"manager","manager-if");
		} catch (SQLException e) {
			throw new DAOException("ERROR: acceso a la conexion a DB para 'readByName' Name Participante "+ name +" no logrado\n");
		}

		try { // Tratamiento db
			PreparedStatement ps;

			ps = connec.prepareStatement("SELECT * FROM participante WHERE name = ?");
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();

			if (rs.next()){
				readParticipante = new Tparticipante( rs.getString("name"), rs.getLong("phone"), rs.getBoolean("active") ) ;
			}
		}
		catch (SQLException e){
			throw new DAOException("ERROR: tratamiento DB para 'readByName' Name Participante "+ name +" no logrado\n");
		}
		finally {
			try { // Desconexion db
				connec.close();
			} catch (SQLException e) {
				throw new DAOException("ERROR: cerrando conexion a DB para 'readByName' Name Participante "+ name +" no logrado\n");
			}
		}

		return readParticipante;
	}

	/***
	 * reads a Tparticipante from database ifesoft by a name
	 * @param id Tparticipante name to be read
	 * @return tParticipante read from database
	 * @throws DAOException error from database
	 */
	public Tparticipante readById(Integer id) throws DAOException {
		Tparticipante readParticipante = null;

		Connection connec = null;
		driverIdentify();

		try { // Conexion db
			connec = DriverManager.getConnection(connectionChain,"manager","manager-if");
		} catch (SQLException e) {
			throw new DAOException("ERROR: acceso a la conexion a DB para 'readByName' ID Participante "+ id +" no logrado\n");
		}

		try { // Tratamiento db
			PreparedStatement ps;

			ps = connec.prepareStatement("SELECT * FROM participante WHERE id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()){
				readParticipante = new Tparticipante( rs.getString("name"), rs.getLong("phone"), rs.getBoolean("active") ) ;
			}
		}
		catch (SQLException e){
			throw new DAOException("ERROR: tratamiento DB para 'readById' ID Participante "+ id +" no logrado\n");
		}
		finally {
			try { // Desconexion db
				connec.close();
			} catch (SQLException e) {
				throw new DAOException("ERROR: cerrando conexion a DB para 'readById' ID Participante "+ id +" no logrado\n");
			}
		}

		return readParticipante;
	}

	/***
	 * Updates the database ifesoft information of a tParticipante(param) which already exists
	 * @param tParticipante it needs a valid ID read from db
	 * @return Integer ID tParticipante updated
	 * @throws DAOException error from database
	 */
	public Integer update(Tparticipante tParticipante) throws DAOException {
		int id = -1;
		Connection connec = null;
		driverIdentify();
		try { // Conexion db
			connec = DriverManager.getConnection(connectionChain,"manager","manager-if");
		} catch (SQLException e) {
			throw new DAOException("ERROR: acceso a la conexion a DB para 'update' Name Participante "+ tParticipante.getName() +" no logrado\n");
		}

		try { // Tratamiento db
			PreparedStatement ps;
			ps = connec.prepareStatement("UPDATE participante SET (name, description, initDate, endDate, active) VALUES (?,?,?,?,?)");
			ps.setString(1, tParticipante.getName());
			ps.setLong(2, tParticipante.getPhone());
			ps.setBoolean(3, true);
			ps.execute();

			ps = connec.prepareStatement("SELECT id FROM participante WHERE name = ?");
			ps.setString(1, tParticipante.getName());
			ResultSet rs = ps.executeQuery();

			if (rs.next())
				id = rs.getInt("id");
		}
		catch (SQLException e){
			throw new DAOException("ERROR: tratamiento DB para 'update' Name Participante "+ tParticipante.getName() +" no logrado\n");
		}
		finally {
			try { // Desconexion db
				connec.close();
			} catch (SQLException e) {
				throw new DAOException("ERROR: cerrando conexion a DB para 'update' Name Participante "+ tParticipante.getName() +" no logrado\n");
			}
		}

		return id;
	}

	/***
	 * deletes a tParticipante from database
	 * @param id tParticipante to delete
	 * @return boolean
	 * @throws DAOException
	 */
	public boolean delete (Integer id) throws DAOException {
		Connection connec = null;
		driverIdentify();
		try { // Conexion db
			connec = DriverManager.getConnection(connectionChain,"manager","manager-if");
		} catch (SQLException e) {
			throw new DAOException("ERROR: acceso a la conexion para 'delete' ID Participante "+ id +" no logrado\n");
		}


		try { // Tratamiento db
			PreparedStatement ps;
			ps = connec.prepareStatement("DELETE FROM participante WHERE id = ?");
			ps.setInt(1, id);
			ps.execute();
		}
		catch (SQLException e){
			throw new DAOException("ERROR: tratamiento para 'delete' ID Participante "+ id +" no logrado\n");
		}
		finally {
			try { // Desconexion db
				connec.close();
			} catch (SQLException e) {
				throw new DAOException("ERROR: cerrando conexion a DB para 'delete' ID Participante "+ id +" no logrado\n");
			}
		}

		return true;
	}

	/***
	 * Deletes every tParticipante from database
	 * @throws DAOException
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
			PreparedStatement ps = connec.prepareStatement("TRUNCATE TABLE participante");
			ps.close();
		}
		catch (SQLException e){
			throw new DAOException("ERROR: deleteAll Tparticipante no logrado\n");
		}
		finally {
			try {
				connec.close();
			} catch (SQLException e) {
				throw new DAOException("ERROR: cerrando conexion a DB para 'deleteAll' no logrado\n");			}
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

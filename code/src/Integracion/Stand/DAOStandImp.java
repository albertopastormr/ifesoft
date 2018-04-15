package Integracion.Stand;


import Exceptions.DAOException;
import Integracion.Stand.DAOStand;
import Negocio.Asignacion.Tasignacion;
import Negocio.Participacion.Tparticipacion;
import Negocio.Stand.Tstand;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class DAOStandImp implements DAOStand {
	protected static final String connectionChain = "jdbc:mariadb://localhost:3306/ifesoft?user=manager&password=manager-if";

	/***
	 * Inserts a valid tStand to database 'ifesoft'
	 * @param tStand to create
	 * @return Integer ID tStand created
	 * @throws DAOException error from database
	 */
	public Integer create(Tstand tStand) throws DAOException {
		int id = -1;

		Connection connec = null;
		driverIdentify();

		try { // Conexion db
			connec = DriverManager.getConnection(connectionChain,"manager","manager-if"); // Datos de acceso a la db: user//manager pw//manager-if
		} catch (SQLException e) {
			throw new DAOException("ERROR: acceso a la conexion a DB para 'create' ID Stand "+ tStand.getId() +" no logrado\n");
		}

		try { // Tratamiento db
			PreparedStatement ps;
			ps = connec.prepareStatement("INSERT INTO stand(num_at_fair, cost, total_m2, active) VALUES (?,?,?,?)");
			ps.setInt(1, tStand.getNum_at_fair());
			ps.setDouble(2, tStand.getCost());
			ps.setBoolean(3, true);
			ps.execute();

			ps = connec.prepareStatement("SELECT LAST_INSERT_ID() FROM stand");

			ResultSet rs = ps.executeQuery();
			if (rs.next())
				id = rs.getInt("LAST_INSERT_ID()");
			else
				return -1;
		}
		catch (SQLException e){
			throw new DAOException("ERROR: tratamiento DB para 'create' ID Stand "+ tStand.getId() +" no logrado\n");
		}
		finally {
			try { // Desconexion db
				connec.close();
			} catch (SQLException e) {
				throw new DAOException("ERROR: cerrando conexion a DB para 'create' ID Stand "+ tStand.getId() +" no logrado\n");
			}
		}
		return id;
	}

	/***
	 * reads every tStand(collection) from database 'ifesoft' with any constraint
	 * @return Collection<Tstand> read from database
	 * @throws DAOException error from database
	 */
	public Collection<Tstand> readAll() throws DAOException {
		ArrayList<Tstand> readStandList = new ArrayList<>();
		Connection connec = null;
		driverIdentify();

		try { // Conexion db
			connec = DriverManager.getConnection(connectionChain,"manager","manager-if");
		} catch (SQLException e) {
			throw new DAOException("ERROR: acceso a la conexion a DB para 'readAll' no logrado\n");
		}



		try { // Tratamiento db
			PreparedStatement ps;

			ps = connec.prepareStatement("SELECT * FROM stand WHERE active = true");

			ResultSet rs = ps.executeQuery();
			while (rs.next())
				readStandList.add( new Tstand(rs.getInt("id"), rs.getInt("num_at_fair"), rs.getDouble("cost"), rs.getInt("total_m2"),rs.getBoolean("active") ) );
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
		return readStandList;
	}

	/***
	 * reads every tStand(collection) from database 'ifesoft' given a fair_id and pavilion_id
	 * @return Collection<Tstand> read from database
	 * @throws DAOException error from database
	 */
	public Collection<Tstand> readByAssignation(Integer fair_id, Integer pavilion_id) throws DAOException {
		ArrayList<Tstand> readStandList = new ArrayList<>();
		Connection connec = null;
		driverIdentify();

		try { // Conexion db
			connec = DriverManager.getConnection(connectionChain,"manager","manager-if");
		} catch (SQLException e) {
			throw new DAOException("ERROR: acceso a la conexion a DB para 'readAssignation' no logrado\n");
		}

		try { // Tratamiento db
			PreparedStatement ps;

			ps = connec.prepareStatement("SELECT * FROM asignacion WHERE active = true AND fair_id = ? AND pavilion_id = ?");
			ps.setInt(1,fair_id);
			ps.setInt(2, pavilion_id);
			ResultSet rs = ps.executeQuery();

			while (rs.next())
				readStandList.add(new Tstand(rs.getInt("id"), rs.getInt("num_at_fair"), rs.getDouble("cost"), rs.getInt("total_m2"), rs.getBoolean("active") ));
		}
		catch (SQLException e){
			throw new DAOException("ERROR: tratamiento DB para 'readAssignation' no logrado\n");
		}
		finally {
			try { // Desconexion db
				connec.close();
			} catch (SQLException e) {
				throw new DAOException("ERROR: cerrando conexion a DB para 'readAssignation' no logrado\n");
			}
		}
		return readStandList;
	}

	/***
	 * reads every tStand(collection) from database 'ifesoft' given a fair_id and client_id
	 * @return Collection<Tstand> read from database
	 * @throws DAOException error from database
	 */
	public Collection<Tstand> readByParticipation(Integer fair_id, Integer client_id) throws DAOException {
		ArrayList<Tstand> readStandList = new ArrayList<>();
		Connection connec = null;
		driverIdentify();

		try { // Conexion db
			connec = DriverManager.getConnection(connectionChain);
		} catch (SQLException e) {
			throw new DAOException("ERROR: acceso a la conexion a DB para 'readByParticipation' no logrado\n");
		}



		try { // Tratamiento db
			PreparedStatement ps;

			ps = connec.prepareStatement("SELECT * FROM participacion WHERE active = true AND fair_id = ? AND client_id = ?");
			ps.setInt(1, fair_id);
			ps.setInt(2, client_id);
			ResultSet rs = ps.executeQuery();

			while (rs.next())
				readStandList.add(  new Tstand(rs.getInt("id"), rs.getInt("num_at_fair"), rs.getDouble("cost"), rs.getInt("total_m2"), rs.getBoolean("active") ));
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
		return readStandList;
	}

	/***
	 * reads a tStand from database ifesoft by a name
	 * @param id tStand name to be read
	 * @return tStand read from database
	 * @throws DAOException error from database
	 */
	public Tstand readById(Integer id) throws DAOException {
		Tstand readStand = null;

		Connection connec = null;
		driverIdentify();

		try { // Conexion db
			connec = DriverManager.getConnection(connectionChain,"manager","manager-if");
		} catch (SQLException e) {
			throw new DAOException("ERROR: acceso a la conexion a DB para 'readByID' ID Stand "+ id +" no logrado\n");
		}

		try { // Tratamiento db
			PreparedStatement ps;

			ps = connec.prepareStatement("SELECT * FROM stand WHERE id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()){
				readStand = new Tstand(rs.getInt("id"), rs.getInt("num_at_fair"), rs.getDouble("cost"), rs.getInt("total_m2"), rs.getBoolean("active") ) ;
			}
			else
				return null;
		}
		catch (SQLException e){
			throw new DAOException("ERROR: tratamiento DB para 'readByID' ID Stand "+ id +" no logrado\n");
		}

		try { // Desconexion db
			connec.close();
		} catch (SQLException e) {
			throw new DAOException("ERROR: cerrando conexion a DB para 'readByID' ID Stand "+ id +" no logrado\n");
		}
		return readStand;
	}

	/***
	 * Updates the database ifesoft information of a tStand(param) which already exists
	 * @param tStand it needs a valid ID read from db
	 * @return tStand updated at database
	 * @throws DAOException error from database
	 */
	public Integer update(Tstand tStand) throws DAOException {
		int id = -1;

		Connection connec = null;
		driverIdentify();

		try { // Conexion db
			connec = DriverManager.getConnection(connectionChain);
		} catch (SQLException e) {
			throw new DAOException("ERROR: acceso a la conexion a DB para 'update' ID Stand "+ tStand.getId() +" no logrado\n");
		}

		try { // Tratamiento db
			PreparedStatement ps;
			ps = connec.prepareStatement("UPDATE stand SET num_at_fair = ? AND cost = ? AND total_m2 = ? AND active = ? WHERE id = ?");
			ps.setInt(1, tStand.getNum_at_fair());
			ps.setDouble(2, tStand.getCost());
			ps.setInt(3, tStand.getTotal_m2());
			ps.setBoolean(4, tStand.getActive());
			ps.setInt(5, tStand.getId());
			ps.execute();
			ps.close();

			ps = connec.prepareStatement("SELECT id FROM stand WHERE id = ?");
			ps.setInt(1, tStand.getId());
			ResultSet rs = ps.executeQuery();
			ps.close();

			if (rs.next()) {
				id = rs.getInt("id");
				if (!tStand.getActive()) {
					// Desactivado asignacion relacionada con stand
					ps = connec.prepareStatement("UPDATE asignacion SET active = ? WHERE stand_id = ?");
					ps.setBoolean(1, tStand.getActive());
					ps.setInt(2, tStand.getId());
					ps.execute();
					ps.close();
					// Desactivado participacion relacionada con stand
					ps = connec.prepareStatement("UPDATE participacion SET active = ? WHERE stand_id = ?");
					ps.setBoolean(1, tStand.getActive());
					ps.setInt(2, tStand.getId());
					ps.execute();
					ps.close();
				}
			}
		}
		catch (SQLException e){
			throw new DAOException("ERROR: tratamiento DB para 'update' ID Stand "+ tStand.getId() +" no logrado\n");
		}


		finally {
			try { // Desconexion db
				connec.close();
			} catch (SQLException e) {
				throw new DAOException("ERROR: cerrando conexion a DB para 'update' ID Stand "+ tStand.getId() +" no logrado\n");
			}
		}

		return id;
	}

	/***
	 * deletes a tStand from database
	 * @param id tStand to delete
	 * @return boolean has_been_deleted
	 * @throws DAOException error from database
	 */
	public boolean delete (Integer id) throws DAOException {
		Connection connec = null;
		driverIdentify();
		try { // Conexion db
			connec = DriverManager.getConnection(connectionChain);
		} catch (SQLException e) {
			throw new DAOException("ERROR: acceso a la conexion para 'delete' ID Stand "+ id +" no logrado\n");
		}


		try { // Tratamiento db
			PreparedStatement ps;
			ps = connec.prepareStatement("DELETE FROM stand WHERE id = ?");
			ps.setInt(1, id);
			ps.execute();
		}
		catch (SQLException e){
			throw new DAOException("ERROR: tratamiento para 'delete' ID Stand "+ id +" no logrado\n");
		}

		finally {
			try { // Desconexion db
				connec.close();
			} catch (SQLException e) {
				throw new DAOException("ERROR: cerrando conexion a DB para 'delete' ID Stand "+ id +" no logrado\n");
			}
		}
		return true;
	}

	/***
	 * Deletes every tStand from database
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
			PreparedStatement ps = connec.prepareStatement("TRUNCATE TABLE stand");
			ps.execute();
			ps.close();
		}
		catch (SQLException e){
			throw new DAOException("ERROR: deleteAll Tstand no logrado\n");
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

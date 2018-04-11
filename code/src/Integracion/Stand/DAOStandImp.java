package Integracion.Stand;


import Exceptions.DAOException;
import Integracion.Stand.DAOStand;
import Negocio.Stand.Tstand;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class DAOStandImp implements DAOStand {
	protected static final String connectionChain = "jdbc:mariadb://localhost:3306/ifesoft?user=manager&password=manager-if";

	/***
	 * Inserts a valid tStand to database 'ifesoft'
	 * @param tStand
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
			throw new DAOException("ERROR: acceso a la conexion a DB para 'create' Name Stand "+ tStand.getNum_at_fair() +" no logrado\n");
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
		}
		catch (SQLException e){
			throw new DAOException("ERROR: tratamiento DB para 'create' Name Stand "+ tStand.getNum_at_fair() +" no logrado\n");
		}
		finally {
			try { // Desconexion db
				connec.close();
			} catch (SQLException e) {
				throw new DAOException("ERROR: cerrando conexion a DB para 'create' Name Stand "+ tStand.getNum_at_fair() +" no logrado\n");
			}
		}
		return id;
	}

	/***
	 * reads every tStand(collection) from database 'ifesoft' with any constraint
	 * @return
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
				readStandList.add( new Tstand( rs.getInt("num_at_fair"), rs.getDouble("cost"), rs.getInt("total_m2"),rs.getBoolean("active") ) );
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
	 * @param name tStand name to be read
	 * @return
	 * @throws DAOException
	 */
	public Tstand readByName(String name) throws DAOException {
		Tstand readStand = null;

		Connection connec = null;
		driverIdentify();

		try { // Conexion db
			connec = DriverManager.getConnection(connectionChain,"manager","manager-if");
		} catch (SQLException e) {
			throw new DAOException("ERROR: acceso a la conexion a DB para 'readByName' Name Stand "+ name +" no logrado\n");
		}

		try { // Tratamiento db
			PreparedStatement ps;

			ps = connec.prepareStatement("SELECT * FROM stand WHERE num_at_fair = ?");
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();

			if (rs.next()){
				readStand = new Tstand( rs.getInt("num_at_fair"), rs.getDouble("cost"), rs.getInt("total_m2"), rs.getBoolean("active") ) ;
			}
		}
		catch (SQLException e){
			throw new DAOException("ERROR: tratamiento DB para 'readByName' Name Stand "+ name +" no logrado\n");
		}



		try { // Desconexion db
			connec.close();
		} catch (SQLException e) {
			throw new DAOException("ERROR: cerrando conexion a DB para 'readByName' Name Stand "+ name +" no logrado\n");
		}
		return readStand;
	}

	/***
	 * Updates the database ifesoft information of a tStand(param) which already exists
	 * @param tStand it needs a valid ID read from db
	 * @return
	 * @throws DAOException
	 */
	public Integer update(Tstand tStand) throws DAOException {
		int id = -1;

		Connection connec = null;
		driverIdentify();

		try { // Conexion db
			connec = DriverManager.getConnection(connectionChain,"manager","manager-if");
		} catch (SQLException e) {
			throw new DAOException("ERROR: acceso a la conexion a DB para 'update' Name Stand "+ tStand.getNum_at_fair() +" no logrado\n");
		}

		try { // Tratamiento db
			PreparedStatement ps;
			ps = connec.prepareStatement("UPDATE stand SET (num_at_fair, cost, total_m2, active) VALUES (?,?,?,?)");
			ps.setInt(1, tStand.getNum_at_fair());
			ps.setDouble(2, tStand.getCost());
			ps.setInt(3, tStand.getTotal_m2());
			ps.setBoolean(4, true);
			ps.execute();

			ps = connec.prepareStatement("SELECT id FROM stand WHERE num_at_fair = ?");
			ps.setInt(1, tStand.getNum_at_fair());
			ResultSet rs = ps.executeQuery();

			if (rs.next())
				id = rs.getInt("id");
		}
		catch (SQLException e){
			throw new DAOException("ERROR: tratamiento DB para 'update' Name Stand "+ tStand.getNum_at_fair() +" no logrado\n");
		}


		finally {
			try { // Desconexion db
				connec.close();
			} catch (SQLException e) {
				throw new DAOException("ERROR: cerrando conexion a DB para 'update' Name Stand "+ tStand.getNum_at_fair() +" no logrado\n");
			}
		}

		return id;
	}

	/***
	 * deletes a tStand from database
	 * @param id tStand to delete
	 * @return boolean
	 * @throws DAOException
	 */
	public boolean delete (Integer id) throws DAOException {
		Connection connec = null;
		driverIdentify();
		try { // Conexion db
			connec = DriverManager.getConnection(connectionChain,"manager","manager-if");
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

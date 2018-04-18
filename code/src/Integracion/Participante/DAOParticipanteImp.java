package Integracion.Participante;


import Exceptions.DAOException;
import Negocio.Participante.Tparticipante;
import Negocio.Participante.TparticipanteInternacional;
import Negocio.Participante.TparticipanteNacional;

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
			connec = DriverManager.getConnection(connectionChain); // Datos de acceso a la db: user//manager pw//manager-if
		} catch (SQLException e) {
			throw new DAOException("ERROR: acceso a la conexion a DB para 'create' Name Participante "+ tParticipante.getName() +" no logrado\n");
		}

		try { // Tratamiento db
			if( tParticipante instanceof TparticipanteNacional) {
				PreparedStatement ps;
				ps = connec.prepareStatement("INSERT INTO participante(name, phone, active, type) VALUES (?,?,?,?)");
				ps.setString(1, tParticipante.getName());
				ps.setLong(2, tParticipante.getPhone());
				ps.setBoolean(3, tParticipante.getActive());
				ps.setString(4, "nacional");
				ps.execute();
				ps.close();

				ps = connec.prepareStatement("SELECT LAST_INSERT_ID() FROM participante");
				ResultSet rs = ps.executeQuery();

				if (rs.next()) { // Insert para referenciar en la tabla part_nacional al ultimo participante insertado
					int db_last_insert_id = rs.getInt("LAST_INSERT_ID()");
					ps = connec.prepareStatement("INSERT INTO part_nacional(id, region) VALUES (?,?)");
					ps.setInt(1, db_last_insert_id);
					ps.setString(2, ((TparticipanteNacional) tParticipante).getRegion());
					ps.execute();
					ps.close();

					id = db_last_insert_id;
				}
				else
					return -1;
			}
			else if ( tParticipante instanceof  TparticipanteInternacional){
				PreparedStatement ps;
				ps = connec.prepareStatement("INSERT INTO participante(name, phone, active, type) VALUES (?,?,?,?)");
				ps.setString(1, tParticipante.getName());
				ps.setLong(2, tParticipante.getPhone());
				ps.setBoolean(3, tParticipante.getActive());
				ps.setString(4, "internacional");
				ps.execute();
				ps.close();

				ps = connec.prepareStatement("SELECT LAST_INSERT_ID() FROM participante");
				ResultSet rs = ps.executeQuery();

				if (rs.next()) { // Referencio en la tabla part_nacional al ultimo participante insertado
					int db_last_insert_id = rs.getInt("LAST_INSERT_ID()");
					ps = connec.prepareStatement("INSERT INTO part_internacional(id, country) VALUES (?,?)");
					ps.setInt(1, db_last_insert_id);
					ps.setString(2, ((TparticipanteInternacional) tParticipante).getCountry());
					ps.execute();
					ps.close();

					id = db_last_insert_id;
				}
				else
					return -1;
			}
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
			connec = DriverManager.getConnection(connectionChain);
		} catch (SQLException e) {
			throw new DAOException("ERROR: acceso a la conexion a DB para 'readAll' no logrado\n");
		}



		try { // Tratamiento db
			PreparedStatement ps;
			ResultSet rs_general_table_client, rs_concrete_table_client;

			ps = connec.prepareStatement("SELECT * FROM participante WHERE active = true");
			rs_general_table_client = ps.executeQuery(); // Listado de todos los participantes activos
			ps.close();

			while(rs_general_table_client.next()){
				switch (rs_general_table_client.getString("type")){
					case "nacional":{
						ps = connec.prepareStatement("SELECT * FROM part_nacional WHERE id = ?");
						ps.setInt(1, rs_general_table_client.getInt("id"));
						rs_concrete_table_client = ps.executeQuery();
						ps.close();

						if (rs_concrete_table_client.next())
							readParticipanteList.add( new TparticipanteNacional(rs_concrete_table_client.getInt("id"), rs_concrete_table_client.getString("name"), rs_concrete_table_client.getLong("phone"),rs_concrete_table_client.getBoolean("active"), rs_concrete_table_client.getString("region")));
						else
							throw new DAOException("ERROR: tablas de la bases de datos incoherentes");
					}
					break;
					case "internacional":{
						ps = connec.prepareStatement("SELECT * FROM part_internacional WHERE id =?");
						ps.setInt(1, rs_general_table_client.getInt("id"));
						rs_concrete_table_client = ps.executeQuery();
						ps.close();

						if (rs_concrete_table_client.next())
							readParticipanteList.add( new TparticipanteInternacional(rs_concrete_table_client.getInt("id"), rs_concrete_table_client.getString("name"), rs_concrete_table_client.getLong("phone"),rs_concrete_table_client.getBoolean("active"), rs_concrete_table_client.getString("country")));
						else
							throw new DAOException("ERROR: tablas de la bases de datos incoherentes");
					}
					break;
				}
			}
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
	 * @return tParticipante read from database
	 * @throws DAOException error from database
	 */
	public Tparticipante readByName(String name) throws DAOException {
		Tparticipante readParticipante = null;

		Connection connec = null;
		driverIdentify();

		try { // Conexion db
			connec = DriverManager.getConnection(connectionChain);
		} catch (SQLException e) {
			throw new DAOException("ERROR: acceso a la conexion a DB para 'readByName' Name Participante "+ name +" no logrado\n");
		}

		try { // Tratamiento db
			PreparedStatement ps;
			ResultSet rs_general_table_client, rs_concrete_table_client;

			ps = connec.prepareStatement("SELECT * FROM participante WHERE name = ?");
			ps.setString(1, name);
			rs_general_table_client = ps.executeQuery(); // Listado de todos los participantes activos

			while(rs_general_table_client.next()){
				switch (rs_general_table_client.getString("type")){
					case "nacional":{
						ps = connec.prepareStatement("SELECT * FROM part_nacional WHERE id = ?");
						ps.setInt(1, rs_general_table_client.getInt("id"));
						rs_concrete_table_client = ps.executeQuery();
						ps.close();

						if (rs_concrete_table_client.next())
							readParticipante = new TparticipanteNacional(rs_concrete_table_client.getInt("id"), rs_concrete_table_client.getString("name"), rs_concrete_table_client.getLong("phone"),rs_concrete_table_client.getBoolean("active"), rs_concrete_table_client.getString("region"));
						else
							throw new DAOException("ERROR: tablas de la bases de datos incoherentes");
					}
					break;
					case "internacional":{
						ps = connec.prepareStatement("SELECT * FROM part_internacional WHERE id =?");
						ps.setInt(1, rs_general_table_client.getInt("id"));
						rs_concrete_table_client = ps.executeQuery();
						ps.close();

						if (rs_concrete_table_client.next())
							readParticipante = new TparticipanteInternacional(rs_concrete_table_client.getInt("id"), rs_concrete_table_client.getString("name"), rs_concrete_table_client.getLong("phone"),rs_concrete_table_client.getBoolean("active"), rs_concrete_table_client.getString("country"));
						else
							throw new DAOException("ERROR: tablas de la bases de datos incoherentes");
					}
					break;
				}
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
			connec = DriverManager.getConnection(connectionChain);
		} catch (SQLException e) {
			throw new DAOException("ERROR: acceso a la conexion a DB para 'readByName' ID Participante "+ id +" no logrado\n");
		}

		try { // Tratamiento db
			PreparedStatement ps;
			ResultSet rs_general_table_client, rs_concrete_table_client;

			ps = connec.prepareStatement("SELECT * FROM participante WHERE id = ?");
			ps.setInt(1, id);
			rs_general_table_client = ps.executeQuery();
			ps.close();

			while(rs_general_table_client.next()){
				switch (rs_general_table_client.getString("type")){
					case "nacional":{
						ps = connec.prepareStatement("SELECT * FROM part_nacional WHERE id = ?");
						ps.setInt(1, rs_general_table_client.getInt("id"));
						rs_concrete_table_client = ps.executeQuery();
						ps.close();

						if (rs_concrete_table_client.next())
							readParticipante = new TparticipanteNacional(rs_concrete_table_client.getInt("id"), rs_concrete_table_client.getString("name"), rs_concrete_table_client.getLong("phone"),rs_concrete_table_client.getBoolean("active"), rs_concrete_table_client.getString("region"));
						else
							throw new DAOException("ERROR: tablas de la bases de datos incoherentes");
					}
					break;
					case "internacional":{
						ps = connec.prepareStatement("SELECT * FROM part_internacional WHERE id =?");
						ps.setInt(1, rs_general_table_client.getInt("id"));
						rs_concrete_table_client = ps.executeQuery();
						ps.close();

						if (rs_concrete_table_client.next())
							readParticipante = new TparticipanteInternacional(rs_concrete_table_client.getInt("id"), rs_concrete_table_client.getString("name"), rs_concrete_table_client.getLong("phone"),rs_concrete_table_client.getBoolean("active"), rs_concrete_table_client.getString("country"));
						else
							throw new DAOException("ERROR: tablas de la bases de datos incoherentes");
					}
					break;
				}
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
			connec = DriverManager.getConnection(connectionChain);
		} catch (SQLException e) {
			throw new DAOException("ERROR: acceso a la conexion a DB para 'update' Name Participante "+ tParticipante.getName() +" no logrado\n");
		}

		try { // Tratamiento db
			PreparedStatement ps;
			if(tParticipante instanceof TparticipanteNacional) {
				ps = connec.prepareStatement("UPDATE participante SET name = ? AND phone = ? AND active = ? AND type = ? WHERE id = ?");
				ps.setString(1, tParticipante.getName());
				ps.setLong(2, tParticipante.getPhone());
				ps.setBoolean(3, tParticipante.getActive());
				ps.setString(4, "nacional");
				ps.setInt(5, tParticipante.getId());
				ps.execute();

				ps = connec.prepareStatement("UPDATE part_nacional SET region = ? WHERE id = ?");
				ps.setString(1, ((TparticipanteNacional) tParticipante).getRegion());
				ps.setInt(2, tParticipante.getId());
				ps.execute();

				ps = connec.prepareStatement("SELECT id FROM participante WHERE id = ?");
				ps.setInt(1, tParticipante.getId());
				ResultSet rs = ps.executeQuery(); // Comprobacion database sigue siendo coherente

				if (rs.next()) {
					id = rs.getInt("id"); // ID a devolver

					if (!tParticipante.getActive()) { // Si se trata de un drop(active==false), se realiza drop en cascada
						ps = connec.prepareStatement("UPDATE (participacion p JOIN stand s ON p.stand_id = s.id) JOIN asignacion a ON s.id = a.stand_id SET p.active = ? AND s.active = ? AND a.active = ? WHERE p.client_id = ?");
						ps.setBoolean(1, tParticipante.getActive());
						ps.setBoolean(2, tParticipante.getActive());
						ps.setBoolean(3, tParticipante.getActive());
						ps.setInt(4, tParticipante.getId());
						ps.execute();
						ps.close();
					}
				}
				else
					return -1;
			}
			else if (tParticipante instanceof TparticipanteInternacional){
				ps = connec.prepareStatement("UPDATE participante SET name = ? AND phone = ? AND active = ? AND type = ? WHERE id = ?");
				ps.setString(1, tParticipante.getName());
				ps.setLong(2, tParticipante.getPhone());
				ps.setBoolean(3, tParticipante.getActive());
				ps.setString(4, "internacional");
				ps.setInt(5, tParticipante.getId());
				ps.execute();

				ps = connec.prepareStatement("UPDATE part_nacional SET country = ? WHERE id = ?");
				ps.setString(1, ((TparticipanteInternacional) tParticipante).getCountry());
				ps.setInt(2, tParticipante.getId());
				ps.execute();

				ps = connec.prepareStatement("SELECT id FROM participante WHERE id = ?");
				ps.setInt(1, tParticipante.getId());
				ResultSet rs = ps.executeQuery(); // Comprobacion database sigue siendo coherente

				if (rs.next()) {
					id = rs.getInt("id");

					if (!tParticipante.getActive()) { // Si se trata de un drop(active==false), se realiza drop en cascada
						ps = connec.prepareStatement("UPDATE (participacion p JOIN stand s ON p.stand_id = s.id) JOIN asignacion a ON s.id = a.stand_id SET p.active = ? AND s.active = ? AND a.active = ? WHERE p.client_id = ?");
						ps.setBoolean(1, tParticipante.getActive());
						ps.setBoolean(2, tParticipante.getActive());
						ps.setBoolean(3, tParticipante.getActive());
						ps.setInt(4, tParticipante.getId());
						ps.execute();
						ps.close();
					}
				}
				else
					return -1;
			}
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
			connec = DriverManager.getConnection(connectionChain);
		} catch (SQLException e) {
			throw new DAOException("ERROR: acceso a la conexion para 'delete' ID Participante "+ id +" no logrado\n");
		}


		try { // Tratamiento db
			PreparedStatement ps;
			ResultSet rs_general_table_client;

			ps = connec.prepareStatement("SELECT * FROM participante WHERE id = ?");
			ps.setInt(1, id);
			rs_general_table_client = ps.executeQuery();

			switch(rs_general_table_client.getString("type")){
				case "nacional":{
					ps = connec.prepareStatement("DELETE FROM part_nacional WHERE id = ?");
					ps.setInt(1, id);
					ps.execute();
					ps.close();
				}
				break;
				case "internacional":{
					ps = connec.prepareStatement("DELETE FROM part_internacional WHERE id = ?");
					ps.setInt(1, id);
					ps.execute();
					ps.close();
				}
				break;
			}
			ps = connec.prepareStatement("DELETE FROM participante WHERE id = ?");
			ps.setInt(1, id);
			ps.execute();
			ps.close();
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
			PreparedStatement ps = connec.prepareStatement("SET FOREIGN_KEY_CHECKS = 0");
			ps.execute(); // Foreign key checks disable para omitir errores
			ps.close();
			ps = connec.prepareStatement("TRUNCATE TABLE part_nacional");
			ps.execute();
			ps.close();
			ps = connec.prepareStatement("TRUNCATE TABLE part_internacional");
			ps.execute();
			ps.close();
			ps = connec.prepareStatement("TRUNCATE TABLE participante");
			ps.execute();
			ps.close();
			ps = connec.prepareStatement("SET FOREIGN_KEY_CHECKS = 1");
			ps.execute();
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

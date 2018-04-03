package Integracion.Stand;


import Exceptions.DAOException;
import Integracion.Participante.DAOParticipante;
import Negocio.Participante.Tparticipante;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class DAOStandImp implements DAOParticipante {
	protected static final String connectionChain = "jdbc:mysql://localhost:3306/ifesoft_bd";
	public Integer create(Tparticipante tParticipante) throws DAOException {
		int id = -1;

		Connection connec = null;
		try { // Conexion db
			connec = DriverManager.getConnection(connectionChain,"manager","manager-if"); // Datos de acceso a la db: user//manager pw//manager-if
		} catch (SQLException e) {
			throw new DAOException("ERROR: acceso a la conexion a DB para 'create' Name Participante "+ tParticipante.getName() +" no logrado\n");
		}

		try { // Tratamiento db
			PreparedStatement ps;
			ps = connec.prepareStatement("INSERT INTO participante(name, description, initDate, endDate, active) VALUES (?,?,?,?,?)");
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

		try { // Desconexion db
			connec.close();
		} catch (SQLException e) {
			throw new DAOException("ERROR: cerrando conexion a DB para 'create' Name Participante "+ tParticipante.getName() +" no logrado\n");
		}
		return id;
	}


	public Collection<Tparticipante> readAll() throws DAOException {
		ArrayList<Tparticipante> readParticipanteList = new ArrayList<>();
		Connection connec = null;
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


		try { // Desconexion db
			connec.close();
		} catch (SQLException e) {
			throw new DAOException("ERROR: cerrando conexion a DB para 'readAll' no logrado\n");
		}
		return readParticipanteList;
	}
	public Tparticipante readByName(String name) throws DAOException {
		Tparticipante readParticipante = null;

		Connection connec = null;
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



		try { // Desconexion db
			connec.close();
		} catch (SQLException e) {
			throw new DAOException("ERROR: cerrando conexion a DB para 'readByName' Name Participante "+ name +" no logrado\n");
		}
		return readParticipante;
	}
	public Integer update(Tparticipante tParticipante) throws DAOException {
		int id = -1;

		Connection connec = null;
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



		try { // Desconexion db
			connec.close();
		} catch (SQLException e) {
			throw new DAOException("ERROR: cerrando conexion a DB para 'update' Name Participante "+ tParticipante.getName() +" no logrado\n");
		}

		return id;
	}


	public boolean delete (Integer id) throws DAOException {
		Connection connec = null;
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


		try { // Desconexion db
			connec.close();
		} catch (SQLException e) {
			throw new DAOException("ERROR: cerrando conexion a DB para 'delete' ID Participante "+ id +" no logrado\n");
		}
		return true;
	}

}

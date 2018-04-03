package Integracion.Participacion;


import Exceptions.DAOException;
import Integracion.Participacion.DAOParticipacion;
import Negocio.Participacion.Tparticipacion;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class DAOParticipacionImp implements DAOParticipacion {
	protected static final String connectionChain = "jdbc:mysql://localhost:3306/ifesoft_bd";
	public Integer create(Tparticipacion tParticipacion) throws DAOException {
		int id = -1;

		Connection connec = null;
		try { // Conexion db
			connec = DriverManager.getConnection(connectionChain,"manager","manager-if"); // Datos de acceso a la db: user//manager pw//manager-if
		} catch (SQLException e) {
			throw new DAOException("ERROR: acceso a la conexion a DB para 'create' Participacion con ID Feria "+ tParticipacion.getFair_id() + " ID Pabellon " + tParticipacion.getClient_id() + " ID Stand " + tParticipacion.getStand_id()+" no logrado\n");
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
			throw new DAOException("ERROR: tratamiento DB para 'create' Participacion con ID Feria "+ tParticipacion.getFair_id() + " ID Pabellon " + tParticipacion.getClient_id() + " ID Stand " + tParticipacion.getStand_id()+" no logrado\n");
		}

		try { // Desconexion db
			connec.close();
		} catch (SQLException e) {
			throw new DAOException("ERROR: cerrando conexion a DB para 'create' Participacion con ID Feria "+ tParticipacion.getFair_id() + " ID Pabellon " + tParticipacion.getClient_id() + " ID Stand " + tParticipacion.getStand_id()+" no logrado\n");
		}
		return id;
	}


	public Collection<Tparticipacion> readAll() throws DAOException {
		ArrayList<Tparticipacion> readParticipacionList = new ArrayList<>();
		Connection connec = null;
		try { // Conexion db
			connec = DriverManager.getConnection(connectionChain,"manager","manager-if");
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


		try { // Desconexion db
			connec.close();
		} catch (SQLException e) {
			throw new DAOException("ERROR: cerrando conexion a DB para 'readAll' no logrado\n");
		}
		return readParticipacionList;
	}
	public Tparticipacion readByFairName(String name) throws DAOException {
		Tparticipacion readParticipacion = null;

		Connection connec = null;
		try { // Conexion db
			connec = DriverManager.getConnection(connectionChain,"manager","manager-if");
		} catch (SQLException e) {
			throw new DAOException("ERROR: acceso a la conexion a DB para 'readByName' Fair Name "+ name +" no logrado\n");
		}

		try { // Tratamiento db
			PreparedStatement ps;

			ps = connec.prepareStatement("SELECT * FROM participacion pn JOIN feria f ON pn.fair_id = f.id  WHERE f.name = ?");
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();

			if (rs.next()){
				readParticipacion = new Tparticipacion( rs.getInt("fair_id"), rs.getInt("client_id"), rs.getInt("stand_id"), rs.getBoolean("active") ) ;
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
		return readParticipacion;
	}
	public Tparticipacion readByClientName(String name) throws DAOException {
		Tparticipacion readParticipacion = null;

		Connection connec = null;
		try { // Conexion db
			connec = DriverManager.getConnection(connectionChain,"manager","manager-if");
		} catch (SQLException e) {
			throw new DAOException("ERROR: acceso a la conexion a DB para 'readByName' Client Name "+ name +" no logrado\n");
		}

		try { // Tratamiento db
			PreparedStatement ps;

			ps = connec.prepareStatement("SELECT * FROM participacion pn JOIN participante pe ON pn.client_id = pe.id WHERE pe.name = ?");
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();

			if (rs.next()){
				readParticipacion = new Tparticipacion( rs.getInt("fair_id"), rs.getInt("client_id"), rs.getInt("stand_id"), rs.getBoolean("active") ) ;
			}
		}
		catch (SQLException e){
			throw new DAOException("ERROR: tratamiento DB para 'readByName' Client Name "+ name +" no logrado\n");
		}



		try { // Desconexion db
			connec.close();
		} catch (SQLException e) {
			throw new DAOException("ERROR: cerrando conexion a DB para 'readByName' Client Name "+ name +" no logrado\n");
		}
		return readParticipacion;
	}

	public Integer update(Tparticipacion tParticipacion) throws DAOException {
		int id = -1;

		Connection connec = null;
		try { // Conexion db
			connec = DriverManager.getConnection(connectionChain,"manager","manager-if");
		} catch (SQLException e) {
			throw new DAOException("ERROR: acceso a la conexion a DB para 'update' Participacion con ID Feria "+ tParticipacion.getFair_id() + " ID Pabellon " + tParticipacion.getClient_id() + " ID Stand " + tParticipacion.getStand_id()+" no logrado\n");
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
			throw new DAOException("ERROR: tratamiento DB para 'update' Participacion con ID Feria "+ tParticipacion.getFair_id() + " ID Pabellon " + tParticipacion.getClient_id() + " ID Stand " + tParticipacion.getStand_id()+" no logrado\n");
		}



		try { // Desconexion db
			connec.close();
		} catch (SQLException e) {
			throw new DAOException("ERROR: cerrando conexion a DB para 'update' Participacion con ID Feria "+ tParticipacion.getFair_id() + " ID Pabellon " + tParticipacion.getClient_id() + " ID Stand " + tParticipacion.getStand_id()+" no logrado\n");
		}

		return id;
	}


	public boolean delete (Integer fair_id, Integer client_id, Integer stand_id) throws DAOException {
		Connection connec = null;
		try { // Conexion db
			connec = DriverManager.getConnection(connectionChain,"manager","manager-if");
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


		try { // Desconexion db
			connec.close();
		} catch (SQLException e) {
			throw new DAOException("ERROR: cerrando conexion a DB para 'delete' Participacion con ID Feria "+ fair_id + " ID Participante " + client_id + " ID Stand " + stand_id +" no logrado\n");
		}
		return true;
	}

}

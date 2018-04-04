package Integracion.Feria;


import Exceptions.DAOException;
import Negocio.Feria.Tferia;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class DAOFeriaImp implements DAOFeria{
	protected static final String connectionChain = "jdbc:mysql://localhost:3306/ifesoft";
	public DAOFeriaImp(){}
	public Integer create(Tferia tFeria) throws DAOException {
		int id = -1;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			throw new DAOException("ERROR: acceso a la conexion a DB para 'create' Name Feria "+ tFeria.getName() +" no logrado\n");
		}
		Connection connec = null;
		try { // Conexion db
			connec = DriverManager.getConnection(connectionChain,"manager","manager-if"); // Datos de acceso a la db: user//manager pw//manager-if
		} catch (SQLException e) {
			throw new DAOException("ERROR: acceso a la conexion a DB para 'create' Name Feria "+ tFeria.getName() +" no logrado\n");
		}
		try { // Tratamiento db
			PreparedStatement ps;
			ps = connec.prepareStatement("INSERT INTO feria(name, description, initDate, endDate, active) VALUES (?,?,?,?,?)");
			ps.setString(1, tFeria.getName());
			ps.setString(2, tFeria.getDescription());
			ps.setDate(3, tFeria.getIniDate());
			ps.setDate(4, tFeria.getEndDate());
			ps.setBoolean(5, true);
			ps.execute();

			ps = connec.prepareStatement("SELECT LAST_INSERT_ID() FROM feria");

			ResultSet rs = ps.executeQuery();
			if (rs.next())
				id = rs.getInt("LAST_INSERT_ID()");
		}
		catch (SQLException e){
			throw new DAOException("ERROR: tratamiento DB para 'create' Name Feria "+ tFeria.getName() +" no logrado\n");
		}

		try { // Desconexion db
			connec.close();
		} catch (SQLException e) {
			throw new DAOException("ERROR: cerrando conexion a DB para 'create' Name Feria "+ tFeria.getName() +" no logrado\n");
		}
		return id;
	}


	public Collection<Tferia> readAll() throws DAOException {
		ArrayList<Tferia> readFeriaList = new ArrayList<>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Connection connec = null;
		try { // Conexion db
			connec = DriverManager.getConnection(connectionChain,"manager","manager-if");
		} catch (SQLException e) {
			throw new DAOException("ERROR: acceso a la conexion a DB para 'readAll' no logrado\n");
		}

		try { // Tratamiento db
			PreparedStatement ps;

			ps = connec.prepareStatement("SELECT * FROM feria WHERE active = true");

			ResultSet rs = ps.executeQuery();
			while (rs.next())
				readFeriaList.add( new Tferia( rs.getString("name"),rs.getString("description"),rs.getDate("initDate"),rs.getDate("endDate"),rs.getBoolean("active") ) );
		}
		catch (SQLException e){
			throw new DAOException("ERROR: tratamiento DB para 'readAll' no logrado\n");
		}


		try { // Desconexion db
			connec.close();
		} catch (SQLException e) {
			throw new DAOException("ERROR: cerrando conexion a DB para 'readAll' no logrado\n");
		}
		return readFeriaList;
	}
	public Tferia readByName(String name) throws DAOException {
		Tferia readFeria = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Connection connec = null;
		try { // Conexion db
			connec = DriverManager.getConnection(connectionChain,"manager","manager-if");
		} catch (SQLException e) {
			throw new DAOException("ERROR: acceso a la conexion a DB para 'readByName' Name Feria "+ name +" no logrado\n");
		}

		try { // Tratamiento db
			PreparedStatement ps;

			ps = connec.prepareStatement("SELECT * FROM feria WHERE name = ?");
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();

			if (rs.next()){
				readFeria = new Tferia( rs.getString("name"),rs.getString("description"),rs.getDate("initDate"),rs.getDate("endDate"),rs.getBoolean("active") ) ;
			}
		}
		catch (SQLException e){
			throw new DAOException("ERROR: tratamiento DB para 'readByName' Name Feria "+ name +" no logrado\n");
		}



		try { // Desconexion db
			connec.close();
		} catch (SQLException e) {
			throw new DAOException("ERROR: cerrando conexion a DB para 'readByName' Name Feria "+ name +" no logrado\n");
		}
		return readFeria;
	}
	public Integer update(Tferia tFeria) throws DAOException {
		int id = -1;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Connection connec = null;
		try { // Conexion db
			connec = DriverManager.getConnection(connectionChain,"manager","manager-if");
		} catch (SQLException e) {
			throw new DAOException("ERROR: acceso a la conexion a DB para 'update' Name Feria "+ tFeria.getName() +" no logrado\n");
		}

		try { // Tratamiento db
			PreparedStatement ps;
			ps = connec.prepareStatement("UPDATE feria SET (name, description, initDate, endDate, active) VALUES (?,?,?,?,?)");
			ps.setString(1, tFeria.getName());
			ps.setString(2, tFeria.getDescription());
			ps.setDate(3, tFeria.getIniDate());
			ps.setDate(4, tFeria.getEndDate());
			ps.setBoolean(5, true);
			ps.execute();

			ps = connec.prepareStatement("SELECT id FROM feria WHERE name = ?");
			ps.setString(1, tFeria.getName());
			ResultSet rs = ps.executeQuery();

			if (rs.next())
				id = rs.getInt("id");
		}
		catch (SQLException e){
			throw new DAOException("ERROR: tratamiento DB para 'update' Name Feria "+ tFeria.getName() +" no logrado\n");
		}



		try { // Desconexion db
			connec.close();
		} catch (SQLException e) {
			throw new DAOException("ERROR: cerrando conexion a DB para 'update' Name Feria "+ tFeria.getName() +" no logrado\n");
		}

		return id;
	}


	public boolean delete (Integer id) throws DAOException {
		Connection connec = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try { // Conexion db
			connec = DriverManager.getConnection(connectionChain,"manager","manager-if");
		} catch (SQLException e) {
			throw new DAOException("ERROR: acceso a la conexion para 'delete' ID Feria "+ id +" no logrado\n");
		}


		try { // Tratamiento db
			PreparedStatement ps;
			ps = connec.prepareStatement("DELETE FROM feria WHERE id = ?");
			ps.setInt(1, id);
			ps.execute();
		}
		catch (SQLException e){
			throw new DAOException("ERROR: tratamiento para 'delete' ID Feria "+ id +" no logrado\n");
		}


		try { // Desconexion db
			connec.close();
		} catch (SQLException e) {
			throw new DAOException("ERROR: cerrando conexion a DB para 'delete' ID Feria "+ id +" no logrado\n");
		}
		return true;
	}

}

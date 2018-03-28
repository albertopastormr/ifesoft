package Integracion.Feria;


import Exceptions.DAOException;

import java.sql.*;
import java.util.Collection;

public class DAOFeriaImp implements DAOFeria{
	public Integer create(Tferia tFeria) throws DAOException {
		int id = -1;

		Connection connec = null;
		try {
			connec = DriverManager.getConnection("jdbc:mysql://localhost:3306/ifesoft_bd","root","");
		} catch (SQLException e) {
			throw new DAOException("Error acceso a la conexion con DB: ID Feria " + tFeria.getName() +"\n");
		}

		try {
			PreparedStatement ps;
			ps = connec.prepareStatement("INSERT INTO feria() VALUES (?,?,?,?)");
			ps.setString(1, tFeria.getName());
			ps.setString(2, );
			ps.setString(3, );
			ps.execute();

			ps = connec.prepareStatement("SELECT ID FROM FERIA WHERE NOMBRE = '?'");
			ps.executeQuery();
			ResultSet rs = ps.executeQuery();
			if (rs.next())
				id = rs.getInt("LAST_INSERT_ID()");
		}
		catch (SQLException e){
			throw new DAOException("Error tratamiento DB: ID Feria " + tFeria.getName() + "\n");
		}

		try {
			connec.close();
		} catch (SQLException e) {
			throw new DAOException("Error cerrando conexion con DB: ID Feria " + tFeria.getName() +"\n");
		}
		return id;
	}
	public Collection<Tferia> readAll(){
		return new Collection<Tferia>();
	}
	public Tferia readByName(String nombre){
		return new Tferia();
	}
	public Integer update(Tferia tFeria){
		return 0;
	}
	public Integer drop (Integer id){
		return 0;
	}
	public Integer activate(Integer id){
		return 0;
	}
}

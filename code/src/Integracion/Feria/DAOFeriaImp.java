package Integracion.Feria;


import Exceptions.DAOException;
import Negocio.Feria.Tferia;

import java.sql.*;
import java.util.Date;
import java.util.ArrayList;
import java.util.Collection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DAOFeriaImp implements DAOFeria{
	protected static final String connectionChain = "jdbc:mariadb://localhost:3306/ifesoft?user=manager&password=manager-if";


	public DAOFeriaImp(){}

	/***
	 * Inserts a valid Tferia to database 'ifesoft'
	 * @param tFeria
	 * @return
	 * @throws DAOException
	 */
	public Integer create(Tferia tFeria) throws DAOException {
		int id = -1;
		Connection connec = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			try { // Conexion db
				connec = DriverManager.getConnection(connectionChain); // Datos de acceso a la db: user//manager pw//manager-if
				PreparedStatement ps = null;
				try { // Tratamiento db
					ps = connec.prepareStatement("INSERT INTO feria(name, description, initDate, endDate, active) VALUES (?,?,?,?,?)");
					ps.setString(1, tFeria.getName());
					ps.setString(2, tFeria.getDescription());
					ps.setDate(3, new java.sql.Date (tFeria.getIniDate().getTime()));
					ps.setDate(4, new java.sql.Date (tFeria.getEndDate().getTime()));
					ps.setBoolean(5, true);
					ps.execute();
					try { ps.close(); }
					catch (SQLException e) {
						throw new DAOException("ERROR: cerrando conexion a DB para la ultima operacion\n");
					}
					ps = connec.prepareStatement("SELECT LAST_INSERT_ID() FROM feria");
					ResultSet rs = ps.executeQuery();
					if (rs.next())
						id = rs.getInt("LAST_INSERT_ID()");
					else
						throw new DAOException("LAST_INSERT_ID() devuelve vacio: 'ifesoft' database no tiene 'feria' registrado\n");
				}
				catch (SQLException e){
					throw new DAOException("ERROR: tratamiento DB para 'create' Name Feria "+ tFeria.getName() +" no logrado\n");
				}
				finally {
					try {ps.close();}
					catch (SQLException e) {
						throw new DAOException("ERROR: cerrando conexion a DB para la ultima operacion\\n");
					}
				}
			} catch (SQLException e) {
				throw new DAOException("ERROR: acceso a la conexion a DB para 'create' Name Feria "+ tFeria.getName() +" no logrado\n");
			}
			finally {
				try { // Desconexion db
						connec.close();
					} catch (SQLException e) {
						throw new DAOException("ERROR: cerrando conexion a DB para 'create' Name Feria "+ tFeria.getName() +" no logrado\n");
					}
				}
		} catch (ClassNotFoundException ex) {
			throw new DAOException("Error al registrar el driver de mariadb: " + ex);
		}
		
		
		return id;
	}

	/***
	 * reads every Tferia(collection) from database 'ifesoft' with any constraint
	 * @return
	 * @throws DAOException
	 */
	public Collection<Tferia> readAll() throws DAOException {
		ArrayList<Tferia> readFeriaList = new ArrayList<>();
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			Connection connec = null;
			PreparedStatement ps = null;
			try { // Conexion db
				connec = DriverManager.getConnection(connectionChain); // Datos de acceso a la db: user//manager pw//manager-if
				try { // Tratamiento db
					ps = connec.prepareStatement("SELECT * FROM feria WHERE active = true");
					ResultSet rs = ps.executeQuery();
					while (rs.next())
						readFeriaList.add( new Tferia( rs.getString("name"),rs.getString("description"),rs.getDate("initDate"),rs.getDate("endDate"),rs.getBoolean("active") ) );
				}
				catch (SQLException e){
					throw new DAOException("ERROR: tratamiento DB para 'readAll' no logrado\n");
				}
				finally {
					try {ps.close();}
					catch (SQLException e) {
						throw new DAOException("ERROR: cerrando conexion a DB para la ultima operacion\\n");
					}
				}
			} catch (SQLException e) {
				throw new DAOException("ERROR: acceso a la conexion a DB para 'readAll' no logrado\n");
			}
			finally {
				try { // Desconexion db
					connec.close();
				} catch (SQLException e) {
					throw new DAOException("ERROR: cerrando conexion a DB para 'readAll' no logrado\n");
				}
			}
		} catch (ClassNotFoundException ex) {
			throw new DAOException("Error al registrar el driver de mariadb: " + ex);
		}
		
		return readFeriaList;
	}

	/***
	 * reads a Tferia from database ifesoft by a name
	 * @param name Tferia name to be read
	 * @return
	 * @throws DAOException
	 */
	public Tferia readByName(String name) throws DAOException {
		Tferia readFeria = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		Connection connec = null;
		PreparedStatement ps = null;
		try { // Conexion db
			connec = DriverManager.getConnection(connectionChain); // Datos de acceso a la db: user//manager pw//manager-if
			try { // Tratamiento d
				ps = connec.prepareStatement("SELECT * FROM feria WHERE name = ?");
				ps.setString(1, name);
				ResultSet rs = ps.executeQuery();

				if (rs.next()){
					readFeria = new Tferia( rs.getString("name"),rs.getString("description"),rs.getDate("initDate"),rs.getDate("endDate"),rs.getBoolean("active") ) ;
				}
				else
					throw new DAOException("Tferia" + name + " does not exist in ifesoft database\n");
			}
			catch (SQLException e){
				throw new DAOException("ERROR: tratamiento DB para 'readByName' Name Feria "+ name +" no logrado\n");
			}
			finally {
				try {ps.close();}
				catch (SQLException e) {
					throw new DAOException("ERROR: cerrando conexion a DB para la ultima operacion\\n");
				}
			}
		} catch (SQLException e) {
			throw new DAOException("ERROR: acceso a la conexion a DB para 'readByName' Name Feria "+ name +" no logrado\n");
		}
		finally {
			try { // Desconexion db
				connec.close();
			} catch (SQLException e) {
				throw new DAOException("ERROR: cerrando conexion a DB para 'readByName' Name Feria "+ name +" no logrado\n");
			}
		}
		} catch (ClassNotFoundException ex) {
			throw new DAOException("Error al registrar el driver de mariadb: " + ex);
		}
		return readFeria;
	}

	/***
	 * Updates the database ifesoft information of a tFeria(param) which already exists
	 * @param tFeria it needs a valid ID read from db
	 * @return
	 * @throws DAOException
	 */
	public Integer update(Tferia tFeria) throws DAOException {
		int id = -1;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			Connection connec = null;
			PreparedStatement ps = null;
			try { // Conexion db
				connec = DriverManager.getConnection(connectionChain); // Datos de acceso a la db: user//manager pw//manager-if
				try { // Tratamiento db
					ps = connec.prepareStatement("UPDATE feria SET name = ?, description = ?, initDate = ?, endDate = ?, active = ? WHERE id = ?");
					ps.setString(1, tFeria.getName());
					ps.setString(2, tFeria.getDescription());
					ps.setDate(3, new java.sql.Date (tFeria.getIniDate().getTime()));
					ps.setDate(4, new java.sql.Date (tFeria.getEndDate().getTime()));
					ps.setBoolean(5, true);
					ps.setInt(6, tFeria.getId());
					ps.execute();
					try {ps.close();}
						catch (SQLException e) {
							throw new DAOException("ERROR: cerrando conexion a DB para la ultima operacion\\n");
						}
					ps = connec.prepareStatement("SELECT id FROM feria WHERE name = ?");
					ps.setString(1, tFeria.getName());
					ResultSet rs = ps.executeQuery();
					if (rs.next())
						id = rs.getInt("id");
					else
						throw new DAOException("Tferia " + tFeria.getName() + " does not exist (id not defined) in ifesoft database\n");
				}
				catch (SQLException e){
					throw new DAOException("ERROR: tratamiento DB para 'update' Name Feria "+ tFeria.getName() +" no logrado\n");
				}
				finally {
					try {ps.close();}
					catch (SQLException e) {
						throw new DAOException("ERROR: cerrando conexion a DB para la ultima operacion\\n");
					}
				}
			} catch (SQLException e) {
				throw new DAOException("ERROR: acceso a la conexion a DB para 'update' Name Feria "+ tFeria.getName() +" no logrado\n");
			}
			finally {
				try { // Desconexion db
					connec.close();
				} catch (SQLException e) {
					throw new DAOException("ERROR: cerrando conexion a DB para 'update' Name Feria "+ tFeria.getName() +" no logrado\n");
				}
			}
		} catch (ClassNotFoundException ex) {
			throw new DAOException("Error al registrar el driver de mariadb: " + ex);
		}
		
		return id;
	}

	// Metodos hechos por el equipo para usar los test, 
	// en la version final apareceran comentados para facilitar el mantenimiento de la aplicacion
	public boolean delete (Integer id) throws DAOException {
		boolean deleteResult;
		Connection connec = null;
		PreparedStatement ps = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			try { // Conexion db
				connec = DriverManager.getConnection(connectionChain); // Datos de acceso a la db: user//manager pw//manager-if
				try { // Tratamiento db
					
					ps = connec.prepareStatement("DELETE FROM feria WHERE id = ?");
					ps.setInt(1, id);
					deleteResult= ps.execute();
					ps.close();
				}
				catch (SQLException e){
					throw new DAOException("ERROR: tratamiento para 'delete' ID Feria "+ id +" no logrado\n");
				}

			} catch (SQLException e) {
				throw new DAOException("ERROR: acceso a la conexion para 'delete' ID Feria "+ id +" no logrado\n");
			}


		
			finally {
				try { // Desconexion db
					connec.close();
				} catch (SQLException e) {
					throw new DAOException("ERROR: cerrando conexion a DB para 'delete' ID Feria "+ id +" no logrado\n");
				}
			}
		} catch (ClassNotFoundException ex) {
			throw new DAOException("Error al registrar el driver de mariadb: " + ex);
		}
		
		return deleteResult;
	}
	public void deleteAll() throws DAOException {
		Connection connec = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			try { // Conexion db
				connec = DriverManager.getConnection(connectionChain); // Datos de acceso a la db: user//manager pw//manager-if
				try { // Tratamiento db
					 PreparedStatement ps = connec.prepareStatement("TRUNCATE TABLE feria");
					 ps.close();
				}
				catch (SQLException e){
					throw new DAOException("ERROR: deleteAll Tferia no logrado\n");
				}


			} catch (SQLException e) {
				throw new DAOException("ERROR: acceso a la conexion para 'deleteAll' no logrado\n");
			}
			finally {
				try { // Desconexion db
					connec.close();
				} catch (SQLException e) {
					throw new DAOException("ERROR: cerrando conexion a DB para 'deleteAll' no logrado\n");
				}
			}
		} catch (ClassNotFoundException ex) {
			throw new DAOException("Error al registrar el driver de mariadb: " + ex);
		}
		
	}

}
